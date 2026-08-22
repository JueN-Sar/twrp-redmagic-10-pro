package cn.nubia.gamelauncher.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.helper.HideAppsHelper;
import cn.nubia.common.helper.IdentifyHelper;
import cn.nubia.common.helper.ImageCache;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.AtmosphereBean;
import cn.nubia.gamelauncher.bean.GameItemBean;
import cn.nubia.gamelauncher.bean.ResponseBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.commoninterface.IGetPackageIsAutoAddGame;
import cn.nubia.gamelauncher.commoninterface.IOnAppAddedListener;
import cn.nubia.gamelauncher.commoninterface.IRequestListener;
import cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp;
import cn.nubia.gamelauncher.gamecontrolpanel.PerformanceUtils;
import cn.nubia.gamelauncher.helper.GameIdentifyHelper;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameCountTrack;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.SortUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class AppAddModel implements IOnAppAddedListener {
    private static final String CUSTOM_IMAGE_DIR_NAME = "custom_image";
    private static final String DB_GAMES_ICON_HIDE = "switch_hide_games_icon";
    public static final String DYNAMIC_SHOW_HIDDEN_APPS_URI = "content://com.zte.mifavor.launcher.dynamicshowhiddenapps";
    public static final int REQUEST_APP_INSTALL = 1;
    public static final int REQUEST_NEO_INSERT = 4;
    public static final int REQUEST_NEO_LIST = 3;
    public static final int REQUEST_UPDATE_URL = 2;
    public static final int REQUEST_VERIFY_APP = 0;
    public static final String TAG = "AppAddModel";
    private static String mHideGamesIconStr;
    AppAddModelHelper mAppAddModelHelper;
    private CopyOnWriteArrayList<AppListItemBean> mAppAddedList;
    private ArrayList<AppListItemBean> mAppNotAddedList;
    Runnable mAtmosphereRefreshRunnable;
    BusinessRequestorImp mBusinessRequestorImp;
    private CopyOnWriteArrayList<IGetAppStatusDataCallBack> mCallbackList;
    private Context mContext;
    private int mCurrentLobbyMode;
    LauncherApps mLauncherApps;
    private boolean mLoadAllAppListDone;
    private Handler mMainHandler;
    private volatile boolean mNeedCallback;
    private ArrayList<AppListItemBean> mNeoDownloadAppItemList;
    ArrayList<NeoDownloadChangeCallBack> mNeoDownloadChangeCallBacks;
    NeoDownloadHelper mNeoDownloadHelper;
    PackageChangedCallback mPackageChangedCallback;
    private String mSelectedItemComponentName;
    private CopyOnWriteArrayList<AppListItemBean> mShortcutList;
    Runnable mStartRunnable;

    private static class AppAddModelHolder {
        public static final AppAddModel INSTANCE = new AppAddModel();

        private AppAddModelHolder() {
        }
    }

    public interface NeoDownloadChangeCallBack {
        void doChangeNeoDownloadApp(AppListItemBean appListItemBean);

        void refreshGameRecycler(boolean z);
    }

    static class PackageChangedCallback extends LauncherApps.Callback {
        PackageChangedCallback() {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageAdded(final String str, UserHandle userHandle) {
            Log.i(AppAddModel.TAG, "------>onPackageAdded packageName == " + str);
            if (userHandle == null || userHandle.hashCode() != Util.TWIN_PROFILEID) {
                AppAddModel.getInstance().doPackageAddBusinessByPackName(str);
                WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$PackageChangedCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppAddModel.getInstance().updateNewAtmosphereByPackage(str);
                    }
                });
                AppAddModel.getInstance().verifyComponentName(str);
            }
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageChanged(String str, UserHandle userHandle) {
            Log.i(AppAddModel.TAG, "onPackageChanged packageName == " + str);
            AppAddModel.getInstance().doPackageUpdateBusinessByPackName(str);
            AppAddModel.getInstance().verifyComponentName(str);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageRemoved(String str, UserHandle userHandle) {
            if (Util.isAppInstall(GameLauncherApplication.getAppContext(), str)) {
                Log.i(AppAddModel.TAG, "onPackageRemoved() packageName == " + str + ", but only remove clone app, return ");
            } else {
                Log.i(AppAddModel.TAG, "onPackageRemoved packageName == " + str);
                AppAddModel.getInstance().doRemoveBusinessByPackName(str);
            }
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onShortcutsChanged(String str, List<ShortcutInfo> list, UserHandle userHandle) {
            Log.i(ShortCutHelper.TAG, "onShortcutsChanged() packageName : " + str + ", shortcuts : " + list.size());
            super.onShortcutsChanged(str, list, userHandle);
            if ("com.tencent.mm".equals(str)) {
                ShortCutHelper.getInstance().onShortcutsChanged();
            }
        }
    }

    private AppAddModel() {
        this.mAppAddedList = new CopyOnWriteArrayList<>();
        this.mShortcutList = new CopyOnWriteArrayList<>();
        this.mAppNotAddedList = new ArrayList<>();
        this.mNeoDownloadAppItemList = null;
        this.mCallbackList = new CopyOnWriteArrayList<>();
        this.mLoadAllAppListDone = false;
        this.mNeedCallback = false;
        this.mMainHandler = null;
        this.mLauncherApps = null;
        this.mPackageChangedCallback = null;
        this.mNeoDownloadChangeCallBacks = new ArrayList<>();
        this.mBusinessRequestorImp = null;
        this.mAppAddModelHelper = null;
        this.mNeoDownloadHelper = null;
        this.mAtmosphereRefreshRunnable = null;
        this.mStartRunnable = null;
        this.mSelectedItemComponentName = null;
        this.mCurrentLobbyMode = 0;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mBusinessRequestorImp = new BusinessRequestorImp();
        this.mAppAddModelHelper = new AppAddModelHelper();
        NeoDownloadHelper neoDownloadHelper = new NeoDownloadHelper();
        this.mNeoDownloadHelper = neoDownloadHelper;
        neoDownloadHelper.setBusinessRequestorImp(this.mBusinessRequestorImp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addShortcutList() {
        if (GameSpaceConfig.supportWechatShortcut()) {
            this.mShortcutList.clear();
            ShortCutHelper.getInstance().loadShortcutList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appAddedListChanage(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        GameCountTrack.getInstance().sendGameCount(copyOnWriteArrayList.size());
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), DB_GAMES_ICON_HIDE, 0);
        LogUtil.i(TAG, "dynamicShowHiddenApps: hideIconValue = " + i);
        if (i != 1) {
            return;
        }
        Uri parse = Uri.parse(DYNAMIC_SHOW_HIDDEN_APPS_URI);
        String components = getComponents(copyOnWriteArrayList);
        if (components.equals(mHideGamesIconStr)) {
            return;
        }
        mHideGamesIconStr = components;
        LogUtil.i(TAG, "dynamicShowHiddenApps:hidden , appList = " + components);
        Bundle bundle = new Bundle();
        bundle.putString("app_component_name_list_and_user", components);
        try {
            this.mContext.getContentResolver().call(parse, "hidden", (String) null, bundle);
        } catch (Exception e) {
            LogUtil.i(TAG, "dynamicShowHiddenApps exception: " + e.toString());
        }
    }

    private ArrayList<AppListItemBean> convertToListViewItemBean(List<ResolveInfo> list, PackageManager packageManager) {
        if (this.mAppNotAddedList == null) {
            this.mAppNotAddedList = new ArrayList<>();
        }
        if (list == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : list) {
            String str = resolveInfo.activityInfo.packageName + "," + resolveInfo.activityInfo.name;
            if (!this.mAppAddModelHelper.isInSystemAppList(resolveInfo.activityInfo.packageName) && !isExistInAppAddedList(str)) {
                Bitmap originalIcon = Util.getOriginalIcon(resolveInfo.activityInfo.packageName);
                if (originalIcon == null) {
                    originalIcon = CommonUtil.drawableToBitmap(resolveInfo.loadIcon(packageManager));
                }
                this.mAppNotAddedList.add(new AppListItemBean(originalIcon, resolveInfo.loadLabel(packageManager).toString(), str, false, "", null));
            }
        }
        Log.i(TAG, "convertToListViewItemBean == " + this.mAppNotAddedList);
        return this.mAppNotAddedList;
    }

    private void doRequestByPackageName(final String str, final IGetPackageIsAutoAddGame iGetPackageIsAutoAddGame) {
        this.mBusinessRequestorImp.getApplicationByPackageName(GameLauncherApplication.CONTEXT, str, new IRequestListener() { // from class: cn.nubia.gamelauncher.model.AppAddModel.10
            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseError(String str2) {
                Log.i(AppAddModel.TAG, "doRequestByPackageName isAutoAddGame responseError errorMsg = " + str2 + " packageName : " + str + " listener: " + iGetPackageIsAutoAddGame);
                IGetPackageIsAutoAddGame iGetPackageIsAutoAddGame2 = iGetPackageIsAutoAddGame;
                if (iGetPackageIsAutoAddGame2 != null) {
                    iGetPackageIsAutoAddGame2.onGetPackageIsAutoAddGame(AppAddModel.this.mAppAddModelHelper.isInLocalGameList(str) || AppAddModel.this.mAppAddModelHelper.isGameByAppSelfFlag(str), true, null);
                }
            }

            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseInfo(ResponseBean responseBean) {
                GameItemBean gameItemBean;
                IGetPackageIsAutoAddGame iGetPackageIsAutoAddGame2;
                Log.i(AppAddModel.TAG, "doRequestByPackageName responseInfo == " + responseBean + " packageName " + str);
                if (responseBean != null && responseBean.getStateCode() == ConstantVariable.STATE_CODE_SUCESS && responseBean.getGameItemBean() != null && responseBean.getGameItemBean().size() > 0 && (gameItemBean = responseBean.getGameItemBean().get(0)) != null && gameItemBean.getAppType() == ConstantVariable.APP_TYPE_GAME && (iGetPackageIsAutoAddGame2 = iGetPackageIsAutoAddGame) != null) {
                    iGetPackageIsAutoAddGame2.onGetPackageIsAutoAddGame(true, false, responseBean.getGameItemBean().get(0));
                    return;
                }
                IGetPackageIsAutoAddGame iGetPackageIsAutoAddGame3 = iGetPackageIsAutoAddGame;
                if (iGetPackageIsAutoAddGame3 != null) {
                    iGetPackageIsAutoAddGame3.onGetPackageIsAutoAddGame(AppAddModel.this.mAppAddModelHelper.isInLocalGameList(str), false, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRequestByPackageNames(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.mBusinessRequestorImp.getApplicationsByPackageNames(GameLauncherApplication.CONTEXT, arrayList, new IRequestListener() { // from class: cn.nubia.gamelauncher.model.AppAddModel.9
            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseError(String str) {
                Log.i(AppAddModel.TAG, "verifyGameApp  responseError begin doAddLocalToListByPackName");
                AppAddModel.this.doAddLocalToListByPackName();
            }

            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseInfo(ResponseBean responseBean) {
                ArrayList<GameItemBean> gameItemBean;
                Log.i(AppAddModel.TAG, "verifyNotAddGameApp responseInfo == " + responseBean);
                if (responseBean == null || responseBean.getStateCode() != ConstantVariable.STATE_CODE_SUCESS || (gameItemBean = responseBean.getGameItemBean()) == null || gameItemBean.size() <= 0) {
                    return;
                }
                AppAddModel.this.convertGameItemListToAppAddedList(gameItemBean);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0210, code lost:
    
        if (r22 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0203, code lost:
    
        if (r22 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0212, code lost:
    
        r22.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0215, code lost:
    
        updateGameBeanImageUrl(r25.mAppAddedList);
        cn.nubia.gamelauncher.util.WorkThread.runOnWorkThread(new cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda7(r25));
        android.util.Log.i(r2, "getAddedAppListFromDB mAppAddedList ==  " + r25.mAppAddedList);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0238, code lost:
    
        return r25.mAppAddedList;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x015b A[Catch: Exception -> 0x01aa, all -> 0x0206, TryCatch #3 {all -> 0x0206, blocks: (B:40:0x011c, B:42:0x015b, B:45:0x0166, B:48:0x019e, B:51:0x01e6, B:75:0x01ae, B:77:0x01bb, B:78:0x01c0, B:81:0x01ca), top: B:39:0x011c }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0166 A[Catch: Exception -> 0x01aa, all -> 0x0206, TRY_LEAVE, TryCatch #3 {all -> 0x0206, blocks: (B:40:0x011c, B:42:0x015b, B:45:0x0166, B:48:0x019e, B:51:0x01e6, B:75:0x01ae, B:77:0x01bb, B:78:0x01c0, B:81:0x01ca), top: B:39:0x011c }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.concurrent.CopyOnWriteArrayList<cn.nubia.gamelauncher.bean.AppListItemBean> getAddedAppListFromDB() {
        /*
            Method dump skipped, instructions count: 569
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.AppAddModel.getAddedAppListFromDB():java.util.concurrent.CopyOnWriteArrayList");
    }

    private AppListItemBean getBeanInAddedList(String str, ShortcutInfo shortcutInfo) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mAppAddedList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return null;
        }
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && next.isSameItem(str, shortcutInfo)) {
                return next;
            }
        }
        return null;
    }

    private AppListItemBean getBeanInNotAddList(String str, ShortcutInfo shortcutInfo) {
        ArrayList<AppListItemBean> arrayList = this.mAppNotAddedList;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        Iterator<AppListItemBean> it = this.mAppNotAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && next.isSameItem(str, shortcutInfo)) {
                return next;
            }
        }
        return null;
    }

    private String getComponents(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        StringBuilder sb = new StringBuilder();
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            String replace = it.next().getComponentName().replace(",", "/");
            sb.append(replace);
            sb.append(",0;");
            sb.append(replace);
            sb.append(",99900000;");
        }
        return sb.toString();
    }

    public static AppAddModel getInstance() {
        return AppAddModelHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<AppListItemBean> getNotAddedAppListFromSys() {
        Log.i(TAG, "getNotAddedAppListFromSys()");
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        return convertToListViewItemBean(packageManager.queryIntentActivities(intent, 0), packageManager);
    }

    private ArrayList<String> getVerifiedAppsList() {
        return this.mAppAddModelHelper.getVerifiedAppsList();
    }

    private boolean isCustomUrl(String str) {
        Context context;
        File externalFilesDir;
        if (str == null || (context = this.mContext) == null || (externalFilesDir = context.getExternalFilesDir("custom_image")) == null) {
            return false;
        }
        return str.contains(externalFilesDir.getAbsolutePath());
    }

    private boolean isExistInAppAddedList(String str) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mAppAddedList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return false;
        }
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            if (it.next().getComponentName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistInAppNotAddedList(String str, ShortcutInfo shortcutInfo) {
        ArrayList<AppListItemBean> arrayList = this.mAppNotAddedList;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        Iterator<AppListItemBean> it = this.mAppNotAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && next.isSameItem(str, shortcutInfo)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLauncherMainActivity(ComponentName componentName) {
        if (componentName != null) {
            List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(componentName.getPackageName(), Process.myUserHandle());
            for (int i = 0; i < activityList.size(); i++) {
                if (componentName.equals(activityList.get(i).getComponentName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLocalUrlAndDBUrlTheSameValue(String str, String str2) {
        return str2 == null ? str == null : str2.equals(str);
    }

    private boolean isNotLocalUrl(String str) {
        if (str == null) {
            return false;
        }
        try {
            File externalFilesDir = this.mContext.getExternalFilesDir("custom_image");
            if (externalFilesDir != null) {
                if (str.contains(externalFilesDir.getAbsolutePath())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.contains("http") || str.contains("storage");
    }

    private boolean isUrlChanged(AtmosphereBean atmosphereBean, AppListItemBean appListItemBean) {
        if (atmosphereBean == null || atmosphereBean.getUrl() == null || appListItemBean == null || appListItemBean.getAtmosphereUrl() == null) {
            return false;
        }
        return !atmosphereBean.getUrl().equals(appListItemBean.getAtmosphereUrl());
    }

    private boolean isVerifiedApp(String str) {
        return this.mAppAddModelHelper.isVerifiedApp(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortListData() {
        Log.i(TAG, "sortListData())");
        try {
            SortUtil.sortByStartTime(this.mAppAddedList);
            SortUtil.sortByPinYinFirstChar(this.mAppNotAddedList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllComponent() {
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            m321x727bc4a1(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppList() {
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            boolean isHideApp = HideAppsHelper.getInstance().isHideApp(next.getPackageName());
            LogUtil.d(TAG, "updateAppList(" + next.getName() + ") isHideApp : " + isHideApp);
            next.isHide = isHideApp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAtmosphereToGameList(AtmosphereBean atmosphereBean) {
        if (atmosphereBean == null || atmosphereBean.getPackageName() == null) {
            return;
        }
        String packageName = atmosphereBean.getPackageName();
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AppListItemBean next = it.next();
            if (packageName.equals(next.getPackageName()) && isUrlChanged(atmosphereBean, next)) {
                next.setImageUrl(atmosphereBean.getUrl(), Atmosphere.TYPE_NET);
                next.updateAtmosphereTime();
                Log.i("Atmosphere", "updateAtmosphereToGameList() update AtmosphereUrl for " + next.getName() + ", url : " + atmosphereBean.getUrl());
                updateAppItemBeanInAppAddDB(next);
                break;
            }
        }
        notifyRefreshRunnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateAtmosphereUrlWithNubia, reason: merged with bridge method [inline-methods] */
    public void m319xbc23728c(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Log.i("Atmosphere", "------------>updateAtmosphereUrlWithNubia() packageList.size == " + arrayList.size());
        this.mBusinessRequestorImp.getBannersByPackageNames(GameLauncherApplication.CONTEXT, arrayList, new ICoverUrlCallback() { // from class: cn.nubia.gamelauncher.model.AppAddModel.5
            @Override // cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback
            public void responseError(String str) {
                Log.i("Atmosphere", "updateAtmosphereUrlWithNubia  responseError :" + str);
            }

            @Override // cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback
            public void responseInfo(ArrayList<AtmosphereBean> arrayList2) {
                if (arrayList2 == null) {
                    return;
                }
                Log.i("Atmosphere", "updateAtmosphereUrlWithNubia responseInfo == " + arrayList2.size());
                Iterator<AtmosphereBean> it = arrayList2.iterator();
                while (it.hasNext()) {
                    AppAddModel.this.updateAtmosphereToGameList(it.next());
                }
            }
        });
    }

    private void updateGameBeanImageUrl(final CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.3
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "updateGameBeanImageUrl() - run())");
                CopyOnWriteArrayList copyOnWriteArrayList2 = copyOnWriteArrayList;
                if (copyOnWriteArrayList2 == null || copyOnWriteArrayList2.size() <= 0) {
                    return;
                }
                ArrayList<String> willUpdateUrlPackageList = AppAddModel.this.mAppAddModelHelper.getWillUpdateUrlPackageList(copyOnWriteArrayList);
                Util.isTencentAppStore();
                AppAddModel.this.updateImageUrlWithNubia(willUpdateUrlPackageList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGameImageUrl(ArrayList<GameItemBean> arrayList) {
        AppListItemBean appListItemBean;
        Log.i(TAG, "updateGameImageUrl GameItemBean  list == " + arrayList);
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<GameItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            GameItemBean next = it.next();
            if (next.getAppType() == ConstantVariable.APP_TYPE_GAME) {
                String packageName = next.getPackageName();
                if (packageName == null || (TextUtils.isEmpty(next.getUrl()) && TextUtils.isEmpty(next.getMiddleUrl()))) {
                    updateUpdateUrlTime(packageName);
                } else {
                    Iterator<AppListItemBean> it2 = this.mAppAddedList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            appListItemBean = null;
                            break;
                        } else {
                            appListItemBean = it2.next();
                            if (packageName.equals(CommonUtil.convertPackageName(appListItemBean.getComponentName()))) {
                                break;
                            }
                        }
                    }
                    updateUrlIfNeed(next, appListItemBean);
                }
            } else {
                updateUpdateUrlTime(next.getPackageName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateImageUrlWithNubia(ArrayList<String> arrayList) {
        Log.i(TAG, "------------>updateImageUrlWithNubia() packageList.size == " + arrayList.size());
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.mBusinessRequestorImp.getApplicationsByPackageNames(GameLauncherApplication.CONTEXT, arrayList, new IRequestListener() { // from class: cn.nubia.gamelauncher.model.AppAddModel.4
            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseError(String str) {
                Log.i(AppAddModel.TAG, "updateImageUrlWithNubia  responseError  updateGameBeanImageUrl");
            }

            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseInfo(ResponseBean responseBean) {
                Log.i(AppAddModel.TAG, "updateImageUrlWithNubia responseInfo == " + responseBean);
                if (responseBean == null || responseBean.getStateCode() != ConstantVariable.STATE_CODE_SUCESS) {
                    return;
                }
                AppAddModel.this.updateGameImageUrl(responseBean.getGameItemBean());
            }
        });
    }

    private void updateUpdateUrlTime(String str) {
        Log.i(TAG, "updateUpdateUrlTime()  pkgName == " + str);
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (str.equals(CommonUtil.convertPackageName(next.getComponentName()))) {
                next.setLastUpdateUrlTime(System.currentTimeMillis());
                updateAppItemBeanInAppAddDB(next);
                Log.i(TAG, "updateUpdateUrlTime() bean == " + next.getLastUpdateUrlTime());
                return;
            }
        }
    }

    private void updateUrlIfNeed(GameItemBean gameItemBean, AppListItemBean appListItemBean) {
        boolean z;
        if (appListItemBean == null || gameItemBean == null) {
            return;
        }
        Log.i(TAG, "updateUrlIfNeed() gameItemBean ==" + gameItemBean);
        boolean z2 = true;
        if (gameItemBean.getUrl() == null || isCustomUrl(appListItemBean.getAtmosphereUrl())) {
            z = false;
        } else {
            appListItemBean.setImageUrl(gameItemBean.getUrl(), Atmosphere.TYPE_NET);
            Log.i(TAG, "updateGameBeanImageUrl update find bean ==" + appListItemBean);
            z = true;
        }
        if (gameItemBean.getMiddleUrl() == null || isCustomUrl(appListItemBean.getMediumUrl())) {
            z2 = z;
        } else {
            appListItemBean.setMediumUrl(gameItemBean.getMiddleUrl());
            Log.i(TAG, "updateGameBeanMiddleImageUrl update find bean ==" + appListItemBean);
        }
        appListItemBean.setLastUpdateUrlTime(System.currentTimeMillis());
        if (z2) {
            updateAppItemBeanInAppAddDB(appListItemBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyNotAddGameApp() {
        Log.i(TAG, "verifyNotAddGameApp()");
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.8
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "verifyNotAddGameApp() - run())");
                if (AppAddModel.this.mAppNotAddedList == null || AppAddModel.this.mAppNotAddedList.size() <= 0) {
                    return;
                }
                ArrayList<String> willVerifyPackageList = AppAddModel.this.mAppAddModelHelper.getWillVerifyPackageList(AppAddModel.this.mAppNotAddedList);
                Util.isTencentAppStore();
                AppAddModel.this.doRequestByPackageNames(willVerifyPackageList);
            }
        });
    }

    private void writeSettings(String str) {
        Settings.Global.putString(this.mContext.getContentResolver(), "game_app_instant_list", str);
    }

    public void convertGameItemListToAppAddedList(ArrayList<GameItemBean> arrayList) {
        ArrayList<AppListItemBean> arrayList2 = new ArrayList<>();
        Iterator<GameItemBean> it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            GameItemBean next = it.next();
            if (next.getAppType() == ConstantVariable.APP_TYPE_GAME || this.mAppAddModelHelper.isInLocalGameList(next.getPackageName())) {
                arrayList2.addAll(this.mAppAddModelHelper.getListBeanByGameItemBean(next, this.mAppNotAddedList));
                str = str + next.getPackageName() + ";";
            }
            this.mAppAddModelHelper.insertAppToVerifiedAppsDB(next.getPackageName());
        }
        writeSettings(str);
        this.mAppAddModelHelper.convertToAppAddList(arrayList2);
        this.mAppAddModelHelper.removeAppListItemBeanInRemoveDB(arrayList2);
        this.mAppAddModelHelper.insertAppToAppAddDB(arrayList2);
        this.mAppAddedList.addAll(arrayList2);
        appAddedListChanage(this.mAppAddedList);
        this.mAppNotAddedList.removeAll(arrayList2);
        notifyChangedData();
    }

    public void doAddLocalToListByPackName() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.11
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "doAddLocalToListByPackName() - run())");
                if (AppAddModel.this.mAppNotAddedList == null || AppAddModel.this.mAppNotAddedList.isEmpty()) {
                    return;
                }
                ArrayList<AppListItemBean> arrayList = new ArrayList<>();
                Iterator it = AppAddModel.this.mAppNotAddedList.iterator();
                while (it.hasNext()) {
                    AppListItemBean appListItemBean = (AppListItemBean) it.next();
                    String convertPackageName = CommonUtil.convertPackageName(appListItemBean.getComponentName());
                    if (AppAddModel.this.mAppAddModelHelper.isInLocalGameList(convertPackageName)) {
                        appListItemBean.setImageUrl(ConstantVariable.LOCAL_GAME_IMAGE_MAP.get(convertPackageName), null);
                        appListItemBean.setGame(true);
                        if (!AppAddModel.this.mAppAddModelHelper.componentExistInUserRemoveDB(appListItemBean.getComponentName(), GameLauncherApplication.CONTEXT.getContentResolver())) {
                            arrayList.add(appListItemBean);
                        }
                    }
                }
                Log.i(AppAddModel.TAG, "doAddLocalToListByPackName willAddList == " + arrayList);
                AppAddModel.this.mAppAddModelHelper.insertAppToAppAddDB(arrayList);
                AppAddModel.this.mAppAddModelHelper.deleteAppItemBeanInUserRemoveDB(arrayList);
                AppAddModel.this.mAppNotAddedList.removeAll(arrayList);
                AppAddModel.this.mAppAddedList.addAll(arrayList);
                AppAddModel appAddModel = AppAddModel.this;
                appAddModel.appAddedListChanage(appAddModel.mAppAddedList);
            }
        });
    }

    public void doPackageAddBusinessByPackName(final String str) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.12
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "doPackageAddBusinessByPackName() - run())");
                AppAddModel.this.isAutoAddGame(str, new IGetPackageIsAutoAddGame() { // from class: cn.nubia.gamelauncher.model.AppAddModel.12.1
                    @Override // cn.nubia.gamelauncher.commoninterface.IGetPackageIsAutoAddGame
                    public void onGetPackageIsAutoAddGame(boolean z, boolean z2, GameItemBean gameItemBean) {
                        Log.i(AppAddModel.TAG, "onGetPackageIsAutoAddGame isAutoAddGame== " + z + " isNetworkError = " + z2 + "  gameItemBean == " + gameItemBean);
                        AppAddModel.this.onGetPackageIsGame(z, gameItemBean, str);
                    }
                });
            }
        });
    }

    void doPackageUpdateBusinessByPackName(final String str) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.13
            @Override // java.lang.Runnable
            public void run() {
                List<LauncherActivityInfo> activityList;
                AppListItemBean appListItemBean;
                Log.i(AppAddModel.TAG, "doPackageUpdateBusinessByPackName() - run())");
                if (AppAddModel.this.mLauncherApps == null || (activityList = AppAddModel.this.mLauncherApps.getActivityList(str, Process.myUserHandle())) == null || activityList.size() <= 0) {
                    return;
                }
                for (LauncherActivityInfo launcherActivityInfo : activityList) {
                    String obj = launcherActivityInfo.getLabel().toString();
                    ComponentName componentName = launcherActivityInfo.getComponentName();
                    Iterator it = AppAddModel.this.mAppAddedList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            appListItemBean = null;
                            break;
                        }
                        appListItemBean = (AppListItemBean) it.next();
                        if (CommonUtil.convertPackageName(appListItemBean.getComponentName()).equals(str)) {
                            appListItemBean.setName(obj);
                            break;
                        }
                    }
                    if (appListItemBean == null) {
                        Iterator it2 = AppAddModel.this.mAppNotAddedList.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            AppListItemBean appListItemBean2 = (AppListItemBean) it2.next();
                            if (CommonUtil.convertPackageName(appListItemBean2.getComponentName()).equals(str)) {
                                appListItemBean2.setName(obj);
                                appListItemBean2.setComponentName(componentName.getPackageName() + "," + componentName.getClassName());
                                appListItemBean = appListItemBean2;
                                break;
                            }
                        }
                    }
                    Log.i(AppAddModel.TAG, "doPackageUpdateBusinessByPackName  findBean ==" + appListItemBean);
                    if (appListItemBean != null) {
                        AppAddModel.this.updateAppItemBeanInAppAddDB(appListItemBean);
                    }
                }
            }
        });
    }

    public void doRemoveBusinessByPackName(final String str) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.6
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "doRemoveBusinessByPackName() - run())");
                ArrayList<AppListItemBean> listBeanByPackName = AppAddModel.this.mAppAddModelHelper.getListBeanByPackName(str, AppAddModel.this.getGameList());
                if (listBeanByPackName != null && listBeanByPackName.size() > 0) {
                    AppAddModel.this.mAppAddedList.removeAll(listBeanByPackName);
                    AppAddModel appAddModel = AppAddModel.this;
                    appAddModel.appAddedListChanage(appAddModel.mAppAddedList);
                    Log.i(AppAddModel.TAG, " mAppAddedList == " + AppAddModel.this.mAppAddedList);
                    AppAddModel.this.mAppAddModelHelper.deleteAppItemBeanInAppAddDB(listBeanByPackName);
                    AppAddModel.this.mAppAddModelHelper.deleteAppItemBeanInUserRemoveDB(listBeanByPackName);
                }
                ArrayList<AppListItemBean> listBeanByPackName2 = AppAddModel.this.mAppAddModelHelper.getListBeanByPackName(str, AppAddModel.this.mAppNotAddedList);
                if (listBeanByPackName2 != null && listBeanByPackName2.size() > 0) {
                    AppAddModel.this.mAppNotAddedList.removeAll(listBeanByPackName2);
                    AppAddModel.this.mAppAddModelHelper.deleteAppItemBeanInAppAddDB(listBeanByPackName2);
                    AppAddModel.this.mAppAddModelHelper.deleteAppItemBeanInUserRemoveDB(listBeanByPackName2);
                }
                AppAddModel.this.mAppAddModelHelper.deleteAppItemBeanInVerifiedAppsDB(str);
                PerformanceUtils.cleanOperationParamFromDB(AppAddModel.this.mContext, str);
                AppAddModel.this.notifyChangedData();
            }
        });
    }

    public boolean doStartRunnable() {
        Log.d("assist", "doStartRunnable() - mStartRunnable : " + this.mStartRunnable);
        if (this.mStartRunnable == null) {
            return false;
        }
        Log.i("Atmosphere", "doStartRunnable()");
        this.mStartRunnable.run();
        return true;
    }

    public void end() {
        HideAppsHelper.getInstance().removeCallback(new AppAddModel$$ExternalSyntheticLambda0(this));
        this.mCallbackList.clear();
        LauncherApps launcherApps = this.mLauncherApps;
        if (launcherApps != null) {
            try {
                launcherApps.unregisterCallback(this.mPackageChangedCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AppListItemBean findBeanFromAllList(String str) {
        if (str == null) {
            return null;
        }
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (CommonUtil.convertPackageName(next.getComponentName()).equals(str)) {
                return next;
            }
        }
        Iterator<AppListItemBean> it2 = this.mAppNotAddedList.iterator();
        while (it2.hasNext()) {
            AppListItemBean next2 = it2.next();
            if (CommonUtil.convertPackageName(next2.getComponentName()).equals(str)) {
                return next2;
            }
        }
        return null;
    }

    public AppListItemBean findBeanInAppAddedList(String str) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList;
        if (str != null && (copyOnWriteArrayList = this.mAppAddedList) != null && copyOnWriteArrayList.size() > 0) {
            Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
            while (it.hasNext()) {
                AppListItemBean next = it.next();
                if (next.getComponentName().equals(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    public AppListItemBean findItemFromAllList(String str, String str2) {
        return str2 == null ? findBeanFromAllList(str) : ShortCutHelper.getInstance().findItemByShortcutId(str2);
    }

    public ArrayList<AppListItemBean> getAllAddList() {
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        arrayList.addAll(this.mAppAddedList);
        arrayList.addAll(ShortCutHelper.getInstance().getShortcutAddList());
        return arrayList;
    }

    public ArrayList<AppListItemBean> getAllNotAddList() {
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        arrayList.addAll(this.mAppNotAddedList);
        arrayList.addAll(ShortCutHelper.getInstance().getShortcutNotAddList());
        return arrayList;
    }

    public CopyOnWriteArrayList<AppListItemBean> getAppAddedCopyOnWriteList() {
        if (!this.mLoadAllAppListDone) {
            this.mNeedCallback = true;
            Log.i(TAG, "getAppAddedCopyOnWriteList null NeedCallback");
            return null;
        }
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Iterator<AppListItemBean> it = getAllAddList().iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (!HideAppsHelper.getInstance().isHideApp(next.getPackageName())) {
                copyOnWriteArrayList.add(next);
            }
        }
        LogUtil.i(TAG, "getAppAddedCopyOnWriteList() count : " + copyOnWriteArrayList.size());
        return copyOnWriteArrayList;
    }

    public ArrayList<AppListItemBean> getAppAddedList() {
        if (!this.mLoadAllAppListDone) {
            this.mNeedCallback = true;
            Log.i(TAG, "getAppAddedList null NeedCallback");
            return null;
        }
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = getAllAddList().iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (HideAppsHelper.getInstance().isHideApp(next.getPackageName())) {
                LogUtil.i(TAG, "getAppAddedList() hideApp : " + next.getName());
            } else {
                arrayList.add(next);
            }
        }
        LogUtil.i(TAG, "getAppAddedList() count : " + arrayList.size());
        return arrayList;
    }

    public ArrayList<AppListItemBean> getAppNotAddList() {
        if (!this.mLoadAllAppListDone) {
            this.mNeedCallback = true;
            Log.i(TAG, "getAppNotAddList null NeedCallback ");
            return null;
        }
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = getAllNotAddList().iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (HideAppsHelper.getInstance().isHideApp(next.getPackageName())) {
                LogUtil.i(TAG, "getAppNotAddList() hideApp : " + next.getName());
            } else {
                arrayList.add(next);
            }
        }
        LogUtil.i(TAG, "getAppNotAddList() count : " + arrayList.size());
        return arrayList;
    }

    public byte[] getCurrentGameBanner() {
        if (getSelectedItem() == null) {
            Log.d("currentGame", "getCurrentGameBanner() is null !");
            return null;
        }
        Bitmap bitmap = ImageCache.getInstance().get(Atmosphere.TYPE_CURRENT);
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Log.d("currentGame", "getCurrentGameBanner()  length : " + byteArray.length);
        return byteArray;
    }

    public String getCurrentGameComponent() {
        return this.mSelectedItemComponentName;
    }

    public int getCurrentGameListSize() {
        return getAllAddList().size();
    }

    public String getCurrentGameName() {
        if (getSelectedItem() == null) {
            return null;
        }
        return getSelectedItem().getName();
    }

    public int getCurrentMode() {
        return this.mCurrentLobbyMode;
    }

    public ShortcutInfo getCurrentShortcutInfo() {
        if (getSelectedItem() == null) {
            return null;
        }
        return getSelectedItem().getShortcutInfo();
    }

    public ArrayList<AppListItemBean> getGameList() {
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        arrayList.addAll(this.mAppAddedList);
        arrayList.addAll(ShortCutHelper.getInstance().getShortcutAddList());
        return arrayList;
    }

    public ArrayList<AppListItemBean> getNeoDownloadAppItemList() {
        return this.mNeoDownloadAppItemList;
    }

    public NeoDownloadHelper getNeoDownloadHelper() {
        return this.mNeoDownloadHelper;
    }

    public ArrayList<String> getProtectedAppList() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getComponentName());
        }
        Iterator<AppListItemBean> it2 = this.mAppNotAddedList.iterator();
        while (it2.hasNext()) {
            AppListItemBean next = it2.next();
            if (next.isGame()) {
                arrayList.add(next.getComponentName());
            }
        }
        LogUtil.i(TAG, "getProtectedAppList() list.size() = " + arrayList.size());
        return arrayList;
    }

    public AppListItemBean getSelectedItem() {
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next.isFocusItem()) {
                return next;
            }
        }
        Iterator<AppListItemBean> it2 = ShortCutHelper.getInstance().getShortcutAddList().iterator();
        while (it2.hasNext()) {
            AppListItemBean next2 = it2.next();
            if (next2.isFocusItem()) {
                return next2;
            }
        }
        return null;
    }

    public ArrayList<AppListItemBean> getShortCutList() {
        if (!this.mLoadAllAppListDone) {
            this.mNeedCallback = true;
            Log.d("SCT", "getShortCutList() null NeedCallback ");
            Log.i(TAG, "getShortCutList null NeedCallback ");
            return null;
        }
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = this.mShortcutList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (HideAppsHelper.getInstance().isHideApp(next.getPackageName())) {
                LogUtil.i(TAG, "getShortCutList() hideApp : " + next.getName());
            } else {
                arrayList.add(next);
            }
        }
        LogUtil.i(TAG, "getShortCutList() count : " + arrayList.size());
        Log.d("SCT", "getShortCutList() list : " + arrayList);
        return arrayList;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mAppAddModelHelper.initMaxIdInTable();
        initLauncherApps();
        loadAllAppList();
        this.mNeoDownloadHelper.init();
        this.mCallbackList.clear();
        HideAppsHelper.getInstance().addCallback(new AppAddModel$$ExternalSyntheticLambda0(this));
        LiveAtmosphereManager.getInstance().doTraversalDirectoryIfNeed();
        IdentifyHelper.getInstance().setOpenCallback(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.this.verifyNotAddGameAppByFeature();
            }
        });
    }

    void initLauncherApps() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.1
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "initLauncherApps() - run())");
                try {
                    AppAddModel appAddModel = AppAddModel.this;
                    appAddModel.mLauncherApps = (LauncherApps) appAddModel.mContext.getSystemService("launcherapps");
                    LauncherApps launcherApps = AppAddModel.this.mLauncherApps;
                    AppAddModel appAddModel2 = AppAddModel.this;
                    PackageChangedCallback packageChangedCallback = new PackageChangedCallback();
                    appAddModel2.mPackageChangedCallback = packageChangedCallback;
                    launcherApps.registerCallback(packageChangedCallback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isAppExitsInSystem(String str) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mAppAddedList;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
            while (it.hasNext()) {
                if (CommonUtil.convertPackageName(it.next().getComponentName()).equals(str)) {
                    return true;
                }
            }
        }
        ArrayList<AppListItemBean> arrayList = this.mAppNotAddedList;
        if (arrayList == null || arrayList.size() <= 0) {
            return false;
        }
        Iterator<AppListItemBean> it2 = this.mAppNotAddedList.iterator();
        while (it2.hasNext()) {
            if (CommonUtil.convertPackageName(it2.next().getComponentName()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void isAutoAddGame(String str, IGetPackageIsAutoAddGame iGetPackageIsAutoAddGame) {
        Log.i(TAG, "isAutoAddGame packageName " + str);
        if (this.mAppAddModelHelper.isInSystemAppList(str)) {
            return;
        }
        if (this.mAppAddModelHelper.isInLocalGameList(str) && iGetPackageIsAutoAddGame != null) {
            Log.i(TAG, "isAutoAddGame() - isInLocalGameList " + str);
            iGetPackageIsAutoAddGame.onGetPackageIsAutoAddGame(true, false, new GameItemBean(str, ConstantVariable.APP_TYPE_GAME, ConstantVariable.LOCAL_GAME_IMAGE_MAP.get(str)));
        } else if (GameIdentifyHelper.getInstance().isGameByFeatures(str, null) && iGetPackageIsAutoAddGame != null && IdentifyHelper.getInstance().isIdentifyOpen()) {
            Log.i(TAG, "isAutoAddGame() - isGameByFeatures " + str);
            iGetPackageIsAutoAddGame.onGetPackageIsAutoAddGame(true, false, new GameItemBean(str, ConstantVariable.APP_TYPE_GAME, ConstantVariable.LOCAL_GAME_IMAGE_MAP.get(str)));
        } else {
            Util.isTencentAppStore();
            doRequestByPackageName(str, iGetPackageIsAutoAddGame);
        }
    }

    public boolean isNeedUpdateAtmosphere(String str) {
        AppAddModelHelper appAddModelHelper = this.mAppAddModelHelper;
        if (appAddModelHelper == null) {
            return false;
        }
        AppListItemBean findItemFromList = appAddModelHelper.findItemFromList(str, this.mAppAddedList);
        if (findItemFromList == null) {
            Log.w("Atmosphere", "------->isNeedUpdateAtmosphere() find in mAppNotAddedList");
            findItemFromList = this.mAppAddModelHelper.findItemFromList(str, this.mAppNotAddedList);
        }
        Log.w("Atmosphere", "------->isNeedUpdateAtmosphere() pkg : " + str + ", bean : " + findItemFromList);
        return (findItemFromList == null || !findItemFromList.isIntervalOverStepDay() || findItemFromList.isLocalImage()) ? false : true;
    }

    public void loadAllAppList() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.2
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "loadAllAppList() - run())");
                AppAddModel.this.getAddedAppListFromDB();
                AppAddModel.this.getNotAddedAppListFromSys();
                AppAddModel.this.addShortcutList();
                AppAddModel.this.mLoadAllAppListDone = true;
                if (AppAddModel.this.mNeedCallback) {
                    AppAddModel.this.notifyChangedData();
                } else {
                    AppAddModel.this.sortListData();
                }
                AppAddModel.this.verifyNotAddGameApp();
                AppAddModel.this.updateNewAtmosphere();
            }
        });
    }

    public void notifyChangedData() {
        Log.i(TAG, "notifyChangedData())");
        final ArrayList arrayList = new ArrayList();
        sortListData();
        arrayList.addAll(this.mAppAddedList);
        arrayList.addAll(this.mAppNotAddedList);
        arrayList.addAll(ShortCutHelper.getInstance().getShortcutAddList());
        arrayList.addAll(ShortCutHelper.getInstance().getShortcutNotAddList());
        final int size = ShortCutHelper.getInstance().getShortcutAddList().size() + this.mAppAddedList.size();
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.7
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AppAddModel.TAG, "notifyChangedData() - run() mCallbackList : " + AppAddModel.this.mCallbackList);
                if (AppAddModel.this.mCallbackList == null || AppAddModel.this.mCallbackList.size() <= 0) {
                    return;
                }
                Iterator it = AppAddModel.this.mCallbackList.iterator();
                while (it.hasNext()) {
                    ((IGetAppStatusDataCallBack) it.next()).onLoadAddAppListDone(arrayList, size);
                }
            }
        });
        updateNewAtmosphere();
    }

    public void notifyRefreshRunnable() {
        if (this.mAtmosphereRefreshRunnable == null) {
            return;
        }
        Log.i("Atmosphere", "notifyRefreshRunnable()");
        this.mAtmosphereRefreshRunnable.run();
    }

    @Override // cn.nubia.gamelauncher.commoninterface.IOnAppAddedListener
    public void onAppAddedCallback(String str, ShortcutInfo shortcutInfo, boolean z) {
        Log.i(TAG, "onAppAddedCallback component == " + str + "  isChecked == " + z);
        if (shortcutInfo != null) {
            ShortCutHelper.getInstance().doShortcutCheckedAfterManagerGame(shortcutInfo, z);
            return;
        }
        if (z) {
            final AppListItemBean beanInNotAddList = getBeanInNotAddList(str, shortcutInfo);
            if (beanInNotAddList == null) {
                return;
            }
            this.mAppAddedList.add(beanInNotAddList);
            appAddedListChanage(this.mAppAddedList);
            this.mAppNotAddedList.remove(beanInNotAddList);
            beanInNotAddList.setSelect(true);
            ArrayList<AppListItemBean> arrayList = new ArrayList<>();
            arrayList.add(beanInNotAddList);
            this.mAppAddModelHelper.insertAppToAppAddDB(arrayList);
            this.mAppAddModelHelper.deleteAppItemBeanInUserRemoveDB(arrayList);
            WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AppAddModel.getInstance().updateNewAtmosphereByPackage(AppListItemBean.this.getPackageName());
                }
            });
        } else {
            AppListItemBean beanInAddedList = getBeanInAddedList(str, shortcutInfo);
            if (beanInAddedList == null) {
                return;
            }
            this.mAppAddedList.remove(beanInAddedList);
            appAddedListChanage(this.mAppAddedList);
            this.mAppNotAddedList.add(beanInAddedList);
            beanInAddedList.setSelect(false);
            ArrayList<AppListItemBean> arrayList2 = new ArrayList<>();
            arrayList2.add(beanInAddedList);
            this.mAppAddModelHelper.deleteAppItemBeanInAppAddDB(arrayList2);
            this.mAppAddModelHelper.insertAppToUserRemoveDB(arrayList2);
        }
        sortListData();
    }

    public void onGetPackageIsGame(boolean z, GameItemBean gameItemBean, String str) {
        if (this.mLauncherApps == null) {
            return;
        }
        Log.i(TAG, "onGetPackageIsGame() isAutoAddGame ==" + z + ", packageName : " + str);
        List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(str, Process.myUserHandle());
        if (activityList == null || activityList.size() <= 0) {
            return;
        }
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        for (LauncherActivityInfo launcherActivityInfo : activityList) {
            ComponentName componentName = launcherActivityInfo.getComponentName();
            AppListItemBean appListItemBean = new AppListItemBean(Util.getOriginalIcon(componentName.getPackageName()), launcherActivityInfo.getLabel().toString(), componentName.getPackageName() + "," + componentName.getClassName(), true, "", Atmosphere.TYPE_NET);
            if (z) {
                if (gameItemBean != null) {
                    appListItemBean.setImageUrl(gameItemBean.getUrl(), Atmosphere.TYPE_NET);
                    appListItemBean.setMediumUrl(gameItemBean.getMiddleUrl());
                }
                appListItemBean.setGame(true);
                if (isExistInAppAddedList(appListItemBean.getComponentName())) {
                    Log.i(TAG, "onGetPackageIsGame() not add but update if need");
                    updateUrlIfNeed(gameItemBean, findBeanInAppAddedList(appListItemBean.getComponentName()));
                } else {
                    Log.i(TAG, "onGetPackageIsGame() mAppAddedList.add bean : " + appListItemBean);
                    this.mAppAddedList.add(appListItemBean);
                    appAddedListChanage(this.mAppAddedList);
                    writeSettings(appListItemBean.getPackageName());
                    this.mAppNotAddedList.remove(appListItemBean);
                }
                arrayList.add(appListItemBean);
            } else {
                appListItemBean.setGame(false);
                appListItemBean.setSelect(false);
                if (!isExistInAppNotAddedList(appListItemBean.getComponentName(), appListItemBean.getShortcutInfo())) {
                    this.mAppNotAddedList.add(appListItemBean);
                    this.mAppAddedList.remove(appListItemBean);
                    appAddedListChanage(this.mAppAddedList);
                }
            }
        }
        this.mAppAddModelHelper.insertAppToAppAddDB(arrayList);
        notifyChangedData();
    }

    public void onLanguageChanged() {
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            next.setName(Util.getAppName(next.getPackageName()));
        }
        Iterator<AppListItemBean> it2 = this.mAppNotAddedList.iterator();
        while (it2.hasNext()) {
            AppListItemBean next2 = it2.next();
            next2.setName(Util.getAppName(next2.getPackageName()));
        }
    }

    public void onNeoDownloadGameChange(int i) {
        if (i == -200) {
            this.mNeoDownloadAppItemList = this.mNeoDownloadHelper.getNeoDownloadAppList();
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.14
                @Override // java.lang.Runnable
                public void run() {
                    Log.i(AppAddModel.TAG, "onNeoDownloadGameChange() - run() - refreshGameRecycler()");
                    Iterator<NeoDownloadChangeCallBack> it = AppAddModel.this.mNeoDownloadChangeCallBacks.iterator();
                    while (it.hasNext()) {
                        it.next().refreshGameRecycler(true);
                    }
                }
            });
        } else {
            final AppListItemBean appListItemBeanByAppId = this.mNeoDownloadHelper.getAppListItemBeanByAppId(i);
            if (appListItemBeanByAppId != null) {
                this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel.15
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator<NeoDownloadChangeCallBack> it = AppAddModel.this.mNeoDownloadChangeCallBacks.iterator();
                        while (it.hasNext()) {
                            it.next().doChangeNeoDownloadApp(appListItemBeanByAppId);
                        }
                    }
                });
            }
        }
    }

    public void onTencentRequestFailed(int i, String str) {
        NeoDownloadHelper neoDownloadHelper;
        Log.i(TAG, "onTencentRequestFailed()  requetType ==" + i);
        if (i == 0) {
            doAddLocalToListByPackName();
            return;
        }
        boolean z = true;
        if (i != 1) {
            if (i == 3 && (neoDownloadHelper = this.mNeoDownloadHelper) != null) {
                neoDownloadHelper.onRequestFailed();
                return;
            }
            return;
        }
        if (!this.mAppAddModelHelper.isInLocalGameList(str) && !this.mAppAddModelHelper.isGameByAppSelfFlag(str)) {
            z = false;
        }
        onGetPackageIsGame(z, null, str);
    }

    public void onTencentRequestSuccess(int i, ArrayList<GameItemBean> arrayList) {
        NeoDownloadHelper neoDownloadHelper;
        Log.i(TAG, "onTencentRequestSuccess() requetType ==" + i + ", list.size() : " + arrayList.size());
        if (i == 0) {
            convertGameItemListToAppAddedList(arrayList);
            return;
        }
        if (i == 1) {
            GameItemBean gameItemBean = arrayList.get(0);
            onGetPackageIsGame(gameItemBean.getAppType() == ConstantVariable.APP_TYPE_GAME, gameItemBean, gameItemBean.getPackageName());
            return;
        }
        if (i == 2) {
            updateGameImageUrl(arrayList);
            return;
        }
        if (i != 3) {
            if (i == 4 && (neoDownloadHelper = this.mNeoDownloadHelper) != null) {
                neoDownloadHelper.onVerfyItemSuccess(arrayList.get(0));
                return;
            }
            return;
        }
        NeoDownloadHelper neoDownloadHelper2 = this.mNeoDownloadHelper;
        if (neoDownloadHelper2 == null) {
            return;
        }
        neoDownloadHelper2.onRequestSuccess(arrayList);
    }

    public void refreshGameList() {
        Log.i("Full", "refreshGameList()");
        SortUtil.sortByStartTime(this.mAppAddedList);
    }

    public void resisterGetAppStatusDataCallBack(IGetAppStatusDataCallBack iGetAppStatusDataCallBack) {
        this.mCallbackList.add(iGetAppStatusDataCallBack);
    }

    public void resisterNeoDownloadChangeCallBack(NeoDownloadChangeCallBack neoDownloadChangeCallBack) {
        this.mNeoDownloadChangeCallBacks.add(neoDownloadChangeCallBack);
    }

    public void sendGameCount() {
        GameCountTrack.getInstance().sendGameCount(this.mAppAddedList.size());
    }

    public void setAtmosphereRefreshRunnable(Runnable runnable) {
        this.mAtmosphereRefreshRunnable = runnable;
    }

    public void setCurrentMode(int i) {
        Log.d("currentMode", "setCurrentMode() mode : " + i);
        this.mCurrentLobbyMode = i;
    }

    public void setSelected(String str) {
        this.mSelectedItemComponentName = str;
    }

    public void setStartRunnable(Runnable runnable) {
        this.mStartRunnable = runnable;
    }

    public void unResisterGetAppStatusDataCallBack(IGetAppStatusDataCallBack iGetAppStatusDataCallBack) {
        this.mCallbackList.remove(iGetAppStatusDataCallBack);
    }

    public void unresisterNeoDownloadChangeCallBack(NeoDownloadChangeCallBack neoDownloadChangeCallBack) {
        if (this.mNeoDownloadChangeCallBacks.contains(neoDownloadChangeCallBack)) {
            this.mNeoDownloadChangeCallBacks.remove(neoDownloadChangeCallBack);
        }
    }

    public void updateAppItemBeanInAppAddDB(AppListItemBean appListItemBean) {
        this.mAppAddModelHelper.updateAppItemBeanInAppAddDB(appListItemBean);
    }

    public void updateAppItemBeanInAppAddDB(String str) {
        this.mAppAddModelHelper.updateAppItemBeanInAppAddDB(findBeanFromAllList(str));
    }

    /* renamed from: updateComponent, reason: merged with bridge method [inline-methods] */
    public void m321x727bc4a1(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return;
        }
        String packageName = appListItemBean.getPackageName();
        if (this.mLauncherApps == null) {
            this.mLauncherApps = (LauncherApps) GameLauncherApplication.getAppContext().getSystemService("launcherapps");
        }
        List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(packageName, Process.myUserHandle());
        if (activityList == null || activityList.size() == 0) {
            return;
        }
        Iterator<LauncherActivityInfo> it = activityList.iterator();
        while (it.hasNext()) {
            ComponentName componentName = it.next().getComponentName();
            Log.i(TAG, "updateComponent() component : " + componentName);
            String str = componentName.getPackageName() + "," + componentName.getClassName();
            if (str.equals(appListItemBean.getComponentName())) {
                return;
            }
            appListItemBean.setComponentName(str);
            updateAppItemBeanInAppAddDB(appListItemBean);
        }
    }

    public void updateGameBeanImageUrlAgain() {
        updateGameBeanImageUrl(this.mAppAddedList);
    }

    public void updateGameList() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.this.updateGameListLastStartTime();
            }
        });
    }

    public void updateGameListLastStartTime() {
        if (this.mAppAddedList == null) {
            return;
        }
        Log.i("Full", "updateGameListLastStartTime()");
        Iterator<AppListItemBean> it = this.mAppAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            next.setLastStartTime(AppUsageStatsHelper.getInstance().getAppLastTimeUsed(next.getPackageName()));
        }
        refreshGameList();
    }

    public void updateNewAtmosphere() {
        AppAddModelHelper appAddModelHelper;
        Log.i("Atmosphere", "updateNewAtmosphere() size : " + this.mAppAddedList.size());
        if (!Util.isNubiaAppStore() || (appAddModelHelper = this.mAppAddModelHelper) == null) {
            return;
        }
        final ArrayList<String> packageList = appAddModelHelper.getPackageList(this.mAppAddedList);
        if (packageList == null) {
            Log.i("Atmosphere", "updateNewAtmosphere() but packList is null!");
        }
        Log.i("Atmosphere", "updateNewAtmosphere()");
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.this.m319xbc23728c(packageList);
            }
        });
    }

    public void updateNewAtmosphereByPackage(String str) {
        Log.i("Atmosphere", "updateNewAtmosphereForItem()");
        if (str == null) {
            Log.w("Atmosphere", "updateNewAtmosphereForItem() cancel because pkg is null!");
        }
        if (!isNeedUpdateAtmosphere(str)) {
            Log.w("Atmosphere", "updateNewAtmosphereForItem() cancel because isNeedUpdateAtmosphere false!");
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        m319xbc23728c(arrayList);
    }

    /* renamed from: verifyComponentName, reason: merged with bridge method [inline-methods] */
    public void m320xe5db99a0(final AppListItemBean appListItemBean) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.this.m321x727bc4a1(appListItemBean);
            }
        });
    }

    public void verifyComponentName(String str) {
        final AppListItemBean findBeanInAppAddedList = findBeanInAppAddedList(str);
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModel$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.this.m320xe5db99a0(findBeanInAppAddedList);
            }
        });
    }

    public void verifyNotAddGameAppByFeature() {
        Log.i(TAG, "verifyNotAddGameAppByFeature(s)");
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = this.mAppNotAddedList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (GameIdentifyHelper.getInstance().isGameByFeatures(next)) {
                arrayList.add(next);
                Log.i(TAG, "verifyNotAddGameAppByFeature() find game : " + next.getName());
            }
        }
        this.mAppAddModelHelper.insertAppToAppAddDB(arrayList);
        this.mAppAddedList.addAll(arrayList);
        appAddedListChanage(this.mAppAddedList);
        this.mAppNotAddedList.removeAll(arrayList);
        notifyChangedData();
        Log.i(TAG, "verifyNotAddGameAppByFeature(e) findGameList == " + arrayList);
    }
}
