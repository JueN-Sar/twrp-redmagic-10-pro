package cn.nubia.gamelauncher.recycler;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import androidx.core.content.ContextCompat;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsFileUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class BannerHelper {
    private static final String TAG = "BannerHelper";
    AppListItemBean mAddGameItem;
    Context mContext;
    GameAddedContentObserver mObserver;
    CopyOnWriteArrayList<AppListItemBean> mFullGameList = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<AppListItemBean> mGridGameList = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<AppListItemBean> mOperationList = new CopyOnWriteArrayList<>();

    private class GameAddedContentObserver extends ContentObserver {
        public GameAddedContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Log.d(BannerHelper.TAG, "GameAddedContentObserver ---- onChange()");
            BannerHelper.this.RefillGameList();
        }

        public void register() {
            BannerHelper.this.getContext().getContentResolver().registerContentObserver(ConstantVariable.APPADD_URI, false, this);
        }

        public void unregister() {
            BannerHelper.this.getContext().getContentResolver().unregisterContentObserver(this);
        }
    }

    public BannerHelper(Context context) {
        this.mContext = context;
        registerObserver();
        clearGameListIfNeed();
        fillGameList();
    }

    private void addManagerItem(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        copyOnWriteArrayList.add(getManagerItem());
    }

    private void addOperationList() {
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        this.mFullGameList.addAll(getOperationList());
    }

    private void clearGameListIfNeed() {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mFullGameList;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList2 = this.mGridGameList;
        if (copyOnWriteArrayList2 != null) {
            copyOnWriteArrayList2.clear();
        }
    }

    private void fillFullGameList() {
        addOperationList();
        addGameList();
        addNeoDownloadList(this.mFullGameList);
        addManagerItem(this.mFullGameList);
    }

    private void fillGameList() {
        fillFullGameList();
        fillGridGameList();
    }

    private void fillGridGameList() {
        if (getGameList() == null) {
            return;
        }
        this.mGridGameList.clear();
        this.mGridGameList.addAll(getGameList());
        addNeoDownloadList(this.mGridGameList);
        addManagerItem(this.mGridGameList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        Context context = this.mContext;
        return context != null ? context : GameLauncherApplication.getAppContext();
    }

    public void RefillGameList() {
        clearGameListIfNeed();
        fillGameList();
    }

    public void addGameList() {
        if (getGameList() == null) {
            return;
        }
        this.mFullGameList.addAll(getGameList());
    }

    public void addNeoDownloadList(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        ArrayList<AppListItemBean> neoDownloadAppItemList = AppAddModel.getInstance().getNeoDownloadAppItemList();
        if (neoDownloadAppItemList == null || neoDownloadAppItemList.size() <= 0 || copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.addAll(neoDownloadAppItemList);
    }

    public void clearFocus() {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mFullGameList;
        if (copyOnWriteArrayList != null) {
            Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().setFocus(false);
            }
        }
    }

    public AppListItemBean findItemByPosition(int i) {
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = this.mFullGameList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= i) {
            return null;
        }
        return this.mFullGameList.get(i);
    }

    public CopyOnWriteArrayList<AppListItemBean> getFullList() {
        return this.mFullGameList;
    }

    public CopyOnWriteArrayList<AppListItemBean> getGameList() {
        return AppAddModel.getInstance().getAppAddedCopyOnWriteList();
    }

    public CopyOnWriteArrayList<AppListItemBean> getGridList() {
        return this.mGridGameList;
    }

    public AppListItemBean getManagerItem() {
        if (this.mAddGameItem == null) {
            Context context = getContext();
            String string = context.getString(R.string.add_game);
            Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.add_game_icon);
            AppListItemBean appListItemBean = new AppListItemBean(string, "cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity", null, Atmosphere.TYPE_LOCAL);
            this.mAddGameItem = appListItemBean;
            appListItemBean.setIcon(BitmapUtils.convertDrawableToBitmap(drawable));
        }
        return this.mAddGameItem;
    }

    public CopyOnWriteArrayList<AppListItemBean> getOperationList() {
        return this.mOperationList;
    }

    public float getRedPointAlpha(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return 0.0f;
        }
        boolean isNotPreviewedExist = isNotPreviewedExist(appListItemBean);
        float f = isNotPreviewedExist ? 0.0f : 1.0f;
        Log.d("magic", "getRedPointAlpha() isNotPreviewedExist : " + isNotPreviewedExist + ", redPointAlpha : " + f);
        return f;
    }

    public boolean isNotPreviewedExist(AppListItemBean appListItemBean) {
        boolean isNotPreviewedExist = HighLightsFileUtils.isNotPreviewedExist(getContext(), appListItemBean.getPackageName());
        Log.d("magic", "isNotPreviewedExist() " + appListItemBean.getPackageName() + ", isNotPreviewedExist : " + isNotPreviewedExist);
        return isNotPreviewedExist;
    }

    public void registerObserver() {
        GameAddedContentObserver gameAddedContentObserver = new GameAddedContentObserver(new Handler());
        this.mObserver = gameAddedContentObserver;
        gameAddedContentObserver.register();
    }

    public void unregisterObserver() {
        GameAddedContentObserver gameAddedContentObserver = this.mObserver;
        if (gameAddedContentObserver != null) {
            gameAddedContentObserver.unregister();
        }
    }

    public void updateOperationList(ArrayList<AppListItemBean> arrayList) {
        this.mOperationList.clear();
        this.mOperationList.addAll(arrayList);
    }
}
