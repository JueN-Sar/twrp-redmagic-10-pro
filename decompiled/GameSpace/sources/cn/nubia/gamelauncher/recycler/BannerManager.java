package cn.nubia.gamelauncher.recycler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.helper.ImageCache;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.activity.AtmosphereActivity;
import cn.nubia.gamelauncher.activity.LargeGameActivity;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.RelevantBean;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.controller.NeoDownloadManager;
import cn.nubia.gamelauncher.controller.ScoreRecordsController;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.Constants;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.helper.VibratorHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.gamelauncher.neostore.OperationHelper;
import cn.nubia.gamelauncher.observer.OperationKeyObserver;
import cn.nubia.gamelauncher.recycler.FullListAdapter;
import cn.nubia.gamelauncher.recycler.GridAdapter;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import cn.nubia.gamelauncher.view.GamePlayLayout;
import cn.nubia.gamelauncher.view.ZoomTextView;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;
import cn.nubia.gamepad.utils.GamepadContentHelper;
import com.airbnb.lottie.LottieAnimationView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class BannerManager implements IGetAppStatusDataCallBack, FullListAdapter.AppClickListener, AppAddModel.NeoDownloadChangeCallBack, View.OnClickListener, View.OnKeyListener, OperationHelper.OperationCallback, GridAdapter.OnAppBeanClickListener, AppUsageStatsHelper.AppUsageStatsChangedListener {
    public static final String ADD_GAME_COMPONENT = "cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity";
    public static final String CLICK = "click_card";
    public static final int RELEVANT_ANIM_INTERVAL = 60000;
    public static final String SHOW = "show_card";
    private static final String TAG = "BannerManager";
    public static final boolean USE_NUBIA_BANNER_SOURCE = true;
    AnimatorSet mAnimatorSet;
    Callback mCallback;
    CardHelper mCardHelper;
    private Context mContext;
    private Dialog mDialog;
    LottieAnimationView mEnterLottie;
    private FullListAdapter mFullAdapter;
    private LinearLayoutManager mFullManager;
    TextView mGameName;
    ZoomTextView mGift;
    private GridAdapter mGridAdapter;
    private StaggeredGridLayoutManager mGridManager;
    Group mGroup;
    private Handler mHandler;
    BannerHelper mHelper;
    String mLastUrl;
    ZoomTextView mMora;
    ZoomTextView mMore;
    Group mNubiaGroup;
    GamePlayLayout mPlayView;
    PropertyManager mProperty;
    private BannerRecyclerView mRecyclerView;
    ImageView mRedPoint;
    ZoomTextView mRelevant;
    private AppListItemBean mRelevantItem;
    private Resources mResources;
    private AppListItemBean mSelectedItem;
    ZoomTextView mVip;
    private Handler mWorkHandler;
    private HandlerThread mWorkThread;
    String defaultUrl = null;
    private boolean mNeedReset = true;
    private boolean mHasMoved = false;
    private boolean mIsAnimEnd = false;
    private boolean isFirstSwitch = true;
    private boolean isFullMode = true;
    private boolean mIsDelayedRefresh = false;
    private boolean mIsDelayedRefreshScroll = false;
    private int mSelectedItemPosition = 0;
    public long mLastAnimTime = 0;
    HashMap<Integer, Runnable> mStartMap = new HashMap<>();
    private final Runnable mSelectedChangedRunnable = new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda6
        @Override // java.lang.Runnable
        public final void run() {
            BannerManager.this.switchViewMode();
        }
    };
    boolean isHidden = false;
    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() { // from class: cn.nubia.gamelauncher.recycler.BannerManager.5
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i != 0) {
                return;
            }
            BannerManager.this.doMediumModeTrackManager();
        }
    };
    private int mUiThreadTid = Process.myTid();
    OperationHelper mOperationHelper = new OperationHelper(getAppContext(), this);

    public interface AnimCallback {
        void onAnimEnd();
    }

    public interface Callback {
        void scrollDirectionChanged();
    }

    class SortByTotalTime implements Comparator {
        SortByTotalTime() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return Long.valueOf(((AppListItemBean) obj2).getTotalTimeMillisecond()).compareTo(Long.valueOf(((AppListItemBean) obj).getTotalTimeMillisecond()));
        }
    }

    public BannerManager(Context context, View view) {
        this.mContext = context;
        initView(view);
        init(context, view);
        initMap();
    }

    private boolean canUpdateAtmosphere() {
        return (this.isHidden || !isFullMode() || this.mSelectedItem == null) ? false : true;
    }

    private void checkLiveWallpaperIfNeed(Atmosphere atmosphere) {
        if (GameSpaceConfig.supportLiveAtmosphere() && atmosphere != null && atmosphere.isCurrentHighLightType()) {
            Log.i(LiveAtmosphereManager.TAG, "checkLiveWallpaperIfNeed() need !");
            LiveAtmosphereManager.getInstance().doTraversalDirectoryIfNeed();
        }
    }

    private boolean checkSelected() {
        if (this.mSelectedItem == null) {
            return false;
        }
        int indexOf = this.mHelper.getFullList().indexOf(this.mSelectedItem);
        LogUtil.i("Full", "checkSelected() index : " + indexOf + ", sp : " + this.mSelectedItemPosition);
        if (indexOf < 0 || indexOf >= this.mHelper.getFullList().size()) {
            return false;
        }
        int i = this.mSelectedItemPosition;
        if (indexOf == i) {
            return true;
        }
        if (!this.mHasMoved && this.mNeedReset && i == 0) {
            this.mNeedReset = false;
            return false;
        }
        this.mSelectedItemPosition = indexOf;
        updateSelected();
        return true;
    }

    private void click3AGame() {
        LogUtil.i(TAG, "click3AGame()");
        try {
            Intent intent = new Intent(this.mContext, (Class<?>) LargeGameActivity.class);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtils.e(TAG, " start3AGame exception ----- ", e);
        }
        LobbySoundPoolHelper.getInstance().play();
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "pcgame_mode_gamespce_homepage");
    }

    private void clickControl() {
        if (this.mSelectedItem == null) {
            return;
        }
        LogUtil.i(TAG, "click game control panel with : " + this.mSelectedItem.getName());
        Intent intent = new Intent();
        intent.setAction(GameFeatureService.ACTION_CONTROL_PANEL);
        intent.addFlags(268435456);
        if (Util.isZte()) {
            intent.addFlags(16777216);
        }
        StringBuilder sb = new StringBuilder(this.mSelectedItem.getPackageName());
        if (this.mSelectedItem.isShortcut()) {
            sb.append("@").append(this.mSelectedItem.getName().hashCode());
            intent.putExtra(GameFeatureService.ACTION_TYPE_SHORTCUT_LABEL, this.mSelectedItem.getShortcutLabel());
            intent.putExtra(GameFeatureService.ACTION_TYPE_IS_SHORTCUT, true);
        }
        intent.putExtra("packageName", sb.toString());
        intent.putExtra(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY, "gameLauncher");
        intent.putExtra("label", this.mSelectedItem.isShortcut() ? this.mSelectedItem.getName() : null);
        this.mContext.sendBroadcast(intent);
        doTrack(this.mSelectedItem, "game_card_settings_panel_click");
        LobbySoundPoolHelper.getInstance().play();
    }

    private void clickGameNotes() {
        String notesPackageExtra = getNotesPackageExtra();
        LogUtil.i(TAG, "clickGameNotes(" + notesPackageExtra + ")");
        try {
            Intent intent = new Intent();
            intent.setAction("cn.nubia.gamenotes.ACTION_SHOW_WINDOWN");
            intent.addFlags(268435456);
            intent.setPackage("cn.nubia.gamenotes");
            intent.putExtra("packageName", notesPackageExtra);
            intent.putExtra("request_code", 1);
            this.mContext.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.w(TAG, "click game notes Exception e " + e.getMessage());
        }
        LobbySoundPoolHelper.getInstance().play();
        doTrack(this.mSelectedItem, "game_card_magic_note_click");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickGiftBag() {
        LogUtil.i("Full", "clickGiftBag()");
        this.mOperationHelper.clickGift(this.mSelectedItem);
        LobbySoundPoolHelper.getInstance().play();
        doTrack(this.mSelectedItem, "lobby_giftbag_click");
    }

    private void clickItemBean(int i) {
        clickItemBean(this.mSelectedItem, i);
    }

    private void clickItemBean(AppListItemBean appListItemBean, final int i) {
        String componentName;
        if (appListItemBean == null) {
            return;
        }
        LogUtil.i(TAG, "clickItemBean() bean.name = " + appListItemBean.getName());
        if (appListItemBean.isShortcut()) {
            ShortCutHelper.getInstance().startShortcut(appListItemBean.getShortcutInfo());
            m327x48696b58(i);
            return;
        }
        if (appListItemBean.isDownloadItem()) {
            NeoDownloadManager.getInstance().doClick(appListItemBean.getDownloadInfo());
            return;
        }
        try {
            componentName = appListItemBean.getComponentName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity".equals(componentName)) {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setComponent(CommonUtil.createComponentName(componentName));
            this.mContext.startActivity(intent);
            return;
        }
        LogUtil.i(TAG, "clickApp() hasCloneApp() : " + hasCloneApp(componentName));
        if (hasCloneApp(componentName)) {
            this.mCardHelper.m313lambda$showDialog$0$cnnubiagamelauncherhelperCardHelper(appListItemBean, new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    BannerManager.this.m326x8ef1ddb9(i);
                }
            });
        } else {
            this.mCardHelper.startApp(appListItemBean, false, new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    BannerManager.this.m327x48696b58(i);
                }
            });
        }
        doItemTrackManager(appListItemBean, CLICK, getPosition(appListItemBean));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clickMode, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m327x48696b58(int i) {
        Runnable runnable = this.mStartMap.get(Integer.valueOf(i));
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    private void clickModify() {
        Log.d("dw", "clickModify()");
        putCurrentAtmosphereToCache();
        Intent intent = new Intent(this.mContext, (Class<?>) AtmosphereActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("selected", getSelectedPkg());
        intent.putExtra(ShortCutHelper.SHORTCUT_ID, getSelectedShortcutId());
        this.mContext.startActivity(intent);
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayByDefault() {
        LogUtil.i("Full", "clickPlayByDefault()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayByHandle() {
        String packageAndHashCode = getPackageAndHashCode();
        Intent intent = new Intent();
        intent.setPackage("cn.nubia.gamepad");
        intent.setAction("cn.nubia.gamepad.startGamepadService");
        intent.putExtra("packagename", packageAndHashCode);
        intent.putExtra("action_type", 12);
        LogUtil.i("Full", "clickPlayByHandle() pkg : " + packageAndHashCode);
        int gameDeviceState = GamepadContentHelper.getGameDeviceState(getAppContext());
        intent.putExtra("operation_devices_state", gameDeviceState);
        if (gameDeviceState == 2) {
            GamepadContentHelper.setGameDeviceState(getAppContext(), 0);
        }
        getAppContext().startService(intent);
        doXGravity("handle", packageAndHashCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayByMirror() {
        LogUtil.i("Full", "clickPlayByMirror()");
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.touping", "cn.nubia.touping.EnterTouPingHomeActivityService");
        getAppContext().startForegroundService(intent);
        AppListItemBean appListItemBean = this.mSelectedItem;
        doXGravity("screen", appListItemBean != null ? appListItemBean.getPackageName() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayByMouse() {
        String packageAndHashCode = getPackageAndHashCode();
        LogUtil.i("Full", "clickPlayByMouse() pkg : " + packageAndHashCode);
        Intent intent = new Intent("cn.nubia.keymapcenter.intent.action.LKM_MAP");
        intent.putExtra("reason", "open_local_key_mouse");
        intent.putExtra("package_name", packageAndHashCode);
        intent.setPackage(Constants.KEYPOSITION_ASSIST_PACKAGE);
        int gameDeviceState = GamepadContentHelper.getGameDeviceState(getAppContext());
        intent.putExtra("operation_devices_state", gameDeviceState);
        if (gameDeviceState == 1) {
            GamepadContentHelper.setGameDeviceState(getAppContext(), 0);
        }
        getAppContext().startService(intent);
        doXGravity("kmouse", packageAndHashCode);
    }

    private void clickRedTime() {
        LogUtil.i(TAG, "clickRedTime()");
        AppListItemBean appListItemBean = this.mSelectedItem;
        String packageName = appListItemBean != null ? appListItemBean.getPackageName() : null;
        try {
            Intent intent = new Intent("cn.nubia.gamecenter.settings.action.GAME_CENTER_RADMAGICTIME_DETAIL");
            intent.addFlags(268435456);
            intent.putExtra("package_name", packageName);
            if (this.mSelectedItem.isShortcut() && this.mSelectedItem != null) {
                intent.putExtra("package_name", packageName + "@" + this.mSelectedItem.getName().hashCode());
            }
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.w(TAG, "click red magic Exception e " + e.getMessage() + Log.getStackTraceString(new Throwable()));
        }
        LobbySoundPoolHelper.getInstance().play();
        doTrack(this.mSelectedItem, "game_card_time_entrance_click");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickVip() {
        LogUtil.i("Full", "clickVip()");
        this.mOperationHelper.clickVip(this.mSelectedItem);
        LobbySoundPoolHelper.getInstance().play();
        doTrack(this.mSelectedItem, "lobby_vipicon_click");
    }

    private void doBigModeTrackManager() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        doItemTrackManager(appListItemBean, SHOW, getPosition(appListItemBean));
    }

    private void doDelayedRefreshIfNeed() {
        if (this.mIsDelayedRefresh) {
            refreshGameRecycler(this.mIsDelayedRefreshScroll);
            this.mIsDelayedRefresh = false;
        }
    }

    private void doItemTrackManager(AppListItemBean appListItemBean, String str, int i) {
        if (appListItemBean == null || appListItemBean.isRecentItem() || i < 0 || !Util.isTencentAppStore()) {
            return;
        }
        int i2 = !appListItemBean.isOperationItem() ? 1 : 0;
        String valueOf = String.valueOf(System.currentTimeMillis());
        Bundle bundle = new Bundle();
        bundle.putString(NotificationCompat.CATEGORY_EVENT, "GameSpace");
        bundle.putString("eid", str);
        bundle.putInt("showType", AppAddModel.getInstance().getCurrentMode());
        bundle.putInt("gameCardType", i2);
        bundle.putString("details", i2 == 0 ? appListItemBean.getCardId() + "," + appListItemBean.getName() : CommonUtil.convertPackageName(appListItemBean.getComponentName()) + "," + appListItemBean.getName());
        bundle.putInt("position", i);
        bundle.putString("curTime", valueOf);
        LogUtil.i("ttm", "doItemTrackManager() bundle : " + bundle.toString());
        NubiaTrackManager.getInstance().sendEvent(CommonUtil.TX_TRACE_PACKAGENAME, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMediumModeTrackManager() {
    }

    private void doShowCardTrackIfNeed() {
        Handler handler = this.mWorkHandler;
        if (handler == null || this.isFirstSwitch) {
            return;
        }
        handler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.doShowCardTrackManagerAfterSwitch();
            }
        }, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doShowCardTrackManagerAfterSwitch() {
        if (Controller.getInstance().isFullMode()) {
            doBigModeTrackManager();
        } else {
            doMediumModeTrackManager();
        }
    }

    private void doTrack(AppListItemBean appListItemBean, String str) {
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, str);
        bundle.putString("app_name", appListItemBean.getName());
        if (str.contains("recommend")) {
            bundle.putString("recommend_app", getRelevantPkg(this.mSelectedItem));
        } else {
            bundle.putString("package_name", appListItemBean.getPackageName());
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private void doXGravity(String str, String str2) {
        LogUtil.w(TAG, "playMode = " + str + "  pkgName = " + str2);
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, "xgravity_play_record");
        bundle.putString("game_package", str2);
        bundle.putString("xgravity_play", str);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private Context getAppContext() {
        return this.mContext.getApplicationContext();
    }

    private String getBeanName(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return null;
        }
        return appListItemBean.isShortcut() ? appListItemBean.getName() + ", hash: " + appListItemBean.getName().hashCode() : appListItemBean.getName();
    }

    private String getNotesPackageExtra() {
        if (!FeatureUtil.supportGameStrategyStation()) {
            return "cn.nubia.gamenotes";
        }
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return null;
        }
        return appListItemBean.isShortcut() ? this.mSelectedItem.getPackageName() + "_" + this.mSelectedItem.getName() : this.mSelectedItem.getPackageName();
    }

    private String getPackageAndHashCode() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return null;
        }
        String packageName = appListItemBean.getPackageName();
        return (!this.mSelectedItem.isShortcut() || this.mSelectedItem.getName() == null) ? packageName : packageName + "@" + this.mSelectedItem.getName().hashCode();
    }

    private String getPackageNameByComponetName(String str) {
        if (str == null || !str.contains(",")) {
            return null;
        }
        return str.split(",")[0];
    }

    private int getPosition(AppListItemBean appListItemBean) {
        if (appListItemBean == null || appListItemBean.isRecentItem()) {
            return -1;
        }
        return this.mHelper.getFullList().indexOf(appListItemBean);
    }

    private String getRelevantPkg(AppListItemBean appListItemBean) {
        if (appListItemBean == null || appListItemBean.getRelevantList().size() <= 0 || appListItemBean.getCurrentRelevant() == null) {
            return null;
        }
        return appListItemBean.getCurrentRelevant().pkg;
    }

    private String getSelectedComponent() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return null;
        }
        return appListItemBean.getComponentName();
    }

    private String getSelectedPkg() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return null;
        }
        return appListItemBean.getPackageName();
    }

    private String getSelectedShortcutId() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return null;
        }
        return appListItemBean.getShortcutId();
    }

    private boolean hasCloneApp(String str) {
        CardHelper cardHelper = this.mCardHelper;
        return cardHelper != null && cardHelper.hasCloneApp(str);
    }

    private void init(Context context, View view) {
        LogUtil.i(TAG, "BM - init()");
        initHandler();
        initResources(context);
        registerListener();
        initBannerList(view);
    }

    private void initAdapter() {
        this.mGridAdapter = new GridAdapter(this.mContext, this.mHelper.getGridList(), this);
        this.mFullAdapter = new FullListAdapter(this.mContext, this.mHelper.getFullList(), this);
    }

    private void initBannerHelper() {
        this.mHelper = new BannerHelper(getAppContext());
        ScoreRecordsController.getInstance().setFullGameList(this.mHelper.getFullList());
    }

    private void initBannerList(View view) {
        initBannerHelper();
        initAdapter();
        resetSelectedItemIfNeed();
        initLayoutManager();
        initRecyclerView(view);
        switchViewMode();
        AppAddModel.getInstance().setAtmosphereRefreshRunnable(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.refreshAtmosphere();
            }
        });
        AppAddModel.getInstance().setStartRunnable(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.clickStart();
            }
        });
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("WorkThread");
        this.mWorkThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mWorkThread.getLooper());
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    private void initLayoutManager() {
        this.mFullManager = new LinearLayoutManager(this.mContext);
        this.mGridManager = new StaggeredGridLayoutManager(2, 0);
    }

    private void initMap() {
        this.mStartMap.put(Integer.valueOf(R.id.game_start), new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.clickPlayByDefault();
            }
        });
        this.mStartMap.put(Integer.valueOf(R.id.play_by_mouse), new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.clickPlayByMouse();
            }
        });
        this.mStartMap.put(Integer.valueOf(R.id.play_by_handle), new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.clickPlayByHandle();
            }
        });
        this.mStartMap.put(Integer.valueOf(R.id.play_by_mirror), new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.clickPlayByMirror();
            }
        });
    }

    private void initRecyclerView(View view) {
        if (view == null) {
            return;
        }
        BannerRecyclerView bannerRecyclerView = (BannerRecyclerView) view.findViewById(R.id.game_list);
        this.mRecyclerView = bannerRecyclerView;
        bannerRecyclerView.setLayoutManager(this.mFullManager);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(this.mFullAdapter);
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
    }

    private void initResources(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
    }

    private void initView(View view) {
        if (view == null) {
            return;
        }
        this.mGameName = (TextView) view.findViewById(R.id.lobby_game_name);
        this.mGroup = (Group) view.findViewById(R.id.lobby_group);
        this.mNubiaGroup = (Group) view.findViewById(R.id.nubia_group);
        this.mPlayView = (GamePlayLayout) view.findViewById(R.id.lobby_play_view);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.enter_lottie);
        this.mEnterLottie = lottieAnimationView;
        lottieAnimationView.setCacheComposition(false);
        this.mMore = (ZoomTextView) view.findViewById(R.id.lobby_custom);
        this.mVip = (ZoomTextView) view.findViewById(R.id.lobby_vip);
        this.mGift = (ZoomTextView) view.findViewById(R.id.lobby_gift_bag);
        if (Util.isZte() && !GameSpaceConfig.supportGiftBag()) {
            this.mVip.setVisibility(8);
            this.mGift.setVisibility(8);
        }
        this.mEnterLottie.setAlpha(0.0f);
        this.mRedPoint = (ImageView) view.findViewById(R.id.red_magic_point);
        view.findViewById(R.id.lobby_3a).setOnClickListener(this);
        view.findViewById(R.id.game_start).setOnClickListener(this);
        view.findViewById(R.id.lobby_control).setOnClickListener(this);
        view.findViewById(R.id.lobby_custom).setOnClickListener(this);
        view.findViewById(R.id.lobby_gift_bag).setOnClickListener(this);
        view.findViewById(R.id.lobby_vip).setOnClickListener(this);
        view.findViewById(R.id.lobby_notes).setOnClickListener(this);
        view.findViewById(R.id.lobby_red_time).setOnClickListener(this);
        view.findViewById(R.id.play_by_mouse).setOnClickListener(this);
        view.findViewById(R.id.play_by_handle).setOnClickListener(this);
        view.findViewById(R.id.play_by_mirror).setOnClickListener(this);
        view.findViewById(R.id.start_button).setOnKeyListener(this);
        view.findViewById(R.id.lobby_3a).setVisibility(Util.supportStreamGame() ? 0 : 8);
        if (!GameSpaceConfig.supportNotes()) {
            view.findViewById(R.id.lobby_notes).setAlpha(0.0f);
        }
        if (!GameSpaceConfig.supportRedTime()) {
            view.findViewById(R.id.lobby_red_time).setAlpha(0.0f);
        }
        this.mProperty = new PropertyManager(this.mContext);
        this.mCardHelper = new CardHelper(this.mContext);
    }

    public static boolean isFullMode() {
        return Controller.getInstance().isFullMode();
    }

    private boolean isOperationKeyClosed() {
        OperationHelper operationHelper = this.mOperationHelper;
        return operationHelper == null ? OperationKeyObserver.getInstance(getAppContext()).isOperationKeyClose() : operationHelper.isOperationKeyClosed();
    }

    private boolean isSupportGift(AppListItemBean appListItemBean) {
        if (isFullMode()) {
            return (!Util.isZte() || GameSpaceConfig.supportGiftBag()) && appListItemBean != null && appListItemBean.hasGift();
        }
        return false;
    }

    private boolean isSupportOperation() {
        if (isOperationKeyClosed() || CommonUtil.isInternalVersion()) {
            return false;
        }
        return !Util.isZte() || GameSpaceConfig.supportGiftBag();
    }

    private boolean isSupportVip(AppListItemBean appListItemBean) {
        if (isFullMode()) {
            return (!Util.isZte() || GameSpaceConfig.supportGiftBag()) && appListItemBean != null && appListItemBean.isVip() && !Controller.getInstance().isPureMode();
        }
        return false;
    }

    private void putCurrentAtmosphereToCache() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return;
        }
        Atmosphere atmosphere = appListItemBean.getAtmosphere();
        if (atmosphere.isCurrentNetType()) {
            String netUrl = atmosphere.getNetUrl();
            if (TextUtils.isEmpty(netUrl)) {
                return;
            }
            Bitmap bitmap = ImageCache.getInstance().get(netUrl);
            if (bitmap == null || bitmap.isRecycled()) {
                ImageCache.getInstance().put(netUrl, ImageCache.getInstance().get(Atmosphere.TYPE_CURRENT));
            }
        }
    }

    private void registerAppModelCallBack() {
        AppAddModel.getInstance().resisterGetAppStatusDataCallBack(this);
        AppAddModel.getInstance().resisterNeoDownloadChangeCallBack(this);
    }

    private void registerListener() {
        registerAppModelCallBack();
        registerUsageStatsChangedListener();
        Controller.getInstance().addSelectedChangedListener(this.mSelectedChangedRunnable);
    }

    private void registerUsageStatsChangedListener() {
        AppUsageStatsHelper.getInstance().registerAppUsageStatsChangedListener(this);
    }

    private void resetRecordPosition() {
        this.mSelectedItemPosition = 0;
    }

    private void resetSelectedItemIfNeed() {
        StringBuilder append = new StringBuilder("resetSelectedItemIfNeed() sp : ").append(this.mSelectedItemPosition).append(", sItem : ");
        AppListItemBean appListItemBean = this.mSelectedItem;
        LogUtil.i("Full", append.append(appListItemBean != null ? appListItemBean.getName() : null).toString());
        BannerHelper bannerHelper = this.mHelper;
        if (bannerHelper == null || bannerHelper.getFullList() == null || this.mHelper.getFullList().size() <= 1) {
            updateSelectedGame(null);
            this.mGroup.setVisibility(8);
            this.mVip.setVisibility(8);
            this.mNubiaGroup.setVisibility(8);
            WallpaperManager.getInstance().switchToWallpaper();
            return;
        }
        if (checkSelected()) {
            return;
        }
        updateSelectedGame(this.mHelper.getFullList().get(0));
        this.mSelectedItemPosition = 0;
        updateSelected();
    }

    private void runOnUiThread(Runnable runnable) {
        if (this.mUiThreadTid == Process.myTid()) {
            runnable.run();
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    private void runOnWorkThread(Runnable runnable) {
        if (this.mWorkThread != null && Process.myTid() == this.mWorkThread.getThreadId()) {
            runnable.run();
            return;
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    private void showOperationDialog(final Runnable runnable) {
        try {
            if (!CommonUtil.isInstalled(this.mContext, "cn.nubia.neogamecenter")) {
                runnable.run();
                LogUtil.i(TAG, " not found neogamecenter... ");
            } else {
                AlertDialog create = new AlertDialog.Builder(this.mContext, 2131952382).setTitle(R.string.lobby_operation_dialog_title).setPositiveButton(R.string.nubia_game_performance_super_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.recycler.BannerManager.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (BannerManager.this.mDialog == null) {
                            return;
                        }
                        BannerManager.this.mDialog.dismiss();
                        BannerManager.this.mDialog = null;
                        runnable.run();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.recycler.BannerManager.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (BannerManager.this.mDialog == null) {
                            return;
                        }
                        BannerManager.this.mDialog.dismiss();
                        BannerManager.this.mDialog = null;
                    }
                }).create();
                this.mDialog = create;
                create.getWindow().setType(2038);
                this.mDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimEnd() {
        Trace.endSection();
        if (this.mRecyclerView == null) {
            return;
        }
        LogUtil.i(TAG, "---->onStartAnimEnd() alpha : " + this.mRecyclerView.getAlpha() + ", isVisible = " + (this.mRecyclerView.getVisibility() == 0));
        this.mRecyclerView.setAlpha(1.0f);
        this.mIsAnimEnd = true;
        if (!isFullMode()) {
            this.mRecyclerView.setTranslationY(0.0f);
        }
        if (!Util.isZte()) {
            UpgradeManager.getInstance().startCheck(false);
        }
        this.mRecyclerView.requestLayout();
        doDelayedRefreshIfNeed();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.getInstance().updateNewAtmosphere();
            }
        });
    }

    private void switchToFullMode() {
        if (this.mRecyclerView == null) {
            return;
        }
        Log.d("Controller", "Banner - switchToFullMode()");
        this.mGroup.setVisibility(this.mSelectedItem == null ? 8 : 0);
        this.mNubiaGroup.setVisibility((this.mSelectedItem == null || Util.isZte()) ? 8 : 0);
        this.mVip.setVisibility(isSupportVip(this.mSelectedItem) ? 0 : 8);
        this.mGift.setVisibility(isSupportGift(this.mSelectedItem) ? 0 : 8);
        this.mRecyclerView.setLayoutManager(this.mFullManager);
        this.mRecyclerView.setAdapter(this.mFullAdapter);
        updateParams(true);
        updateSelectedItem(0, this.mSelectedItemPosition, this.mSelectedItem);
        AppAddModel.getInstance().setCurrentMode(0);
    }

    private void switchToGridMode() {
        if (this.mRecyclerView == null) {
            return;
        }
        Log.d("Controller", "Banner - switchToGridMode()");
        this.mGroup.setVisibility(8);
        this.mVip.setVisibility(8);
        this.mNubiaGroup.setVisibility(8);
        this.mRecyclerView.setLayoutManager(this.mGridManager);
        this.mRecyclerView.setAdapter(this.mGridAdapter);
        updateParams(false);
        this.mRecyclerView.smoothScrollToPosition(0);
        updateFocusItem(false);
        ImageCache.getInstance().remove(Atmosphere.TYPE_CURRENT);
        AppAddModel.getInstance().setSelected(null);
        WallpaperManager.getInstance().switchToWallpaper();
        AppAddModel.getInstance().setCurrentMode(1);
    }

    private void unregisterListener() {
        unregisterAppModelCallBack();
        unregisterUsageStatsChangedListener();
        Controller.getInstance().removeSelectedChangedListener(this.mSelectedChangedRunnable);
    }

    private void unregisterUsageStatsChangedListener() {
        AppUsageStatsHelper.getInstance().unregisterAppUsageStatsChangedListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAtmosphere() {
        Log.i("Atmosphere", "BM - updateAtmosphere()");
        GridAdapter gridAdapter = this.mGridAdapter;
        if (gridAdapter != null) {
            gridAdapter.notifyDataSetChanged();
        }
        updateSelectedBg();
    }

    private void updateBg(AppListItemBean appListItemBean, boolean z) {
        if (appListItemBean == null) {
            return;
        }
        Atmosphere atmosphere = appListItemBean.getAtmosphere();
        String currentDisplayUrl = atmosphere.getCurrentDisplayUrl();
        boolean z2 = z || !((currentDisplayUrl == null || currentDisplayUrl.equals(this.mLastUrl)) && WallpaperManager.getInstance().isAtmosphereImage() && !WallpaperManager.getInstance().isAtmosphereUrlNull());
        int i = atmosphere.isHighLightAtmosphereValid() ? 12 : 11;
        if (canUpdateAtmosphere() && z2) {
            WallpaperManager.getInstance().switchWallpaper(i, currentDisplayUrl);
        }
        this.mLastUrl = currentDisplayUrl;
        checkLiveWallpaperIfNeed(atmosphere);
    }

    private void updateFocusItem(boolean z) {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return;
        }
        appListItemBean.setFocus(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLastStartTime() {
        LogUtil.i("Full", " updateLastStartTime()");
        AppUsageStatsHelper appUsageStatsHelper = AppUsageStatsHelper.getInstance();
        Iterator<AppListItemBean> it = this.mHelper.getGameList().iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            String packageName = next.getPackageName();
            long appTotalTimeInForegroundByMillisecond = appUsageStatsHelper.getAppTotalTimeInForegroundByMillisecond(packageName);
            long lastStartTime = next.getLastStartTime();
            long appLastTimeUsed = appUsageStatsHelper.getAppLastTimeUsed(packageName);
            LogUtil.i("Full", "updateLastStartTime(" + next.getName() + ") pkg=" + packageName + " totalMs=" + appTotalTimeInForegroundByMillisecond + " lastStartTime(old=" + lastStartTime + ", new=" + appLastTimeUsed + ")");
            next.setTotalTimeInForeground(appTotalTimeInForegroundByMillisecond);
            next.setLastStartTime(appLastTimeUsed);
        }
        AppAddModel.getInstance().refreshGameList();
        if (WallpaperManager.getInstance().isStartAnim()) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.m328x2ba7f977();
            }
        });
    }

    private void updateNeoDownloadApp(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList, AppListItemBean appListItemBean) {
        int indexOf;
        FullListAdapter fullListAdapter;
        GridAdapter gridAdapter;
        if (copyOnWriteArrayList == null || appListItemBean == null || (indexOf = copyOnWriteArrayList.indexOf(appListItemBean)) < 0) {
            return;
        }
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(indexOf);
        if ((findViewHolderForAdapterPosition instanceof GridAdapter.GridHolder) && (gridAdapter = this.mGridAdapter) != null) {
            gridAdapter.updateNeoDownloadIcon(appListItemBean, (GridAdapter.GridHolder) findViewHolderForAdapterPosition);
        } else if (!(findViewHolderForAdapterPosition instanceof FullListAdapter.FullGameHolder) || (fullListAdapter = this.mFullAdapter) == null) {
            LogUtil.i(TAG, "updateNeoDownloadApp() but viewHolder is not instanceof GameCardViewHolder!");
        } else {
            fullListAdapter.updateNeoDownloadIcon(appListItemBean, (FullListAdapter.FullGameHolder) findViewHolderForAdapterPosition);
        }
    }

    private void updateOperation() {
        LogUtil.i("Full", "updateOperation() isSupportOperation : " + isSupportOperation());
        if (!isSupportOperation()) {
            this.mVip.setVisibility(8);
            this.mGift.setAlpha(0.0f);
            this.mGift.setVisibility(8);
        } else {
            AppListItemBean appListItemBean = this.mSelectedItem;
            if (appListItemBean == null) {
                return;
            }
            this.mVip.setVisibility(isSupportVip(appListItemBean) ? 0 : 8);
            this.mGift.setAlpha(isSupportGift(this.mSelectedItem) ? 1.0f : 0.0f);
            this.mGift.setVisibility(isSupportGift(this.mSelectedItem) ? 0 : 8);
        }
    }

    private void updateParams(boolean z) {
        if (Controller.getInstance().isPureMode()) {
            updateParamsWithPure(z);
        } else {
            updateParamsWithNormal(z);
        }
    }

    private void updateParamsWithNormal(boolean z) {
        Log.d("Controller", "Banner - updateParamsWithNormal()");
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(z ? 1700 : -1, z ? 0 : -2);
        layoutParams.leftToLeft = 0;
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        if (z) {
            layoutParams.height = 0;
            layoutParams.topMargin = 144;
            layoutParams.leftMargin = 16;
            layoutParams.bottomMargin = Util.supportStreamGame() ? 15 : 0;
            if (Util.supportStreamGame()) {
                layoutParams.bottomToTop = R.id.lobby_3a;
            }
        } else {
            layoutParams.height = this.mResources.getDimensionPixelSize(R.dimen.lobby_full_recycle_height);
            layoutParams.leftMargin = 60;
            layoutParams.topMargin = 0;
        }
        this.mRecyclerView.setLayoutParams(layoutParams);
    }

    private void updateParamsWithPure(boolean z) {
        Log.d("Controller", "Banner - updateParamsWithPure()");
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(z ? HighLightsUtils.RESET_DELAY_TIME : -1, z ? 0 : -2);
        layoutParams.leftToLeft = 0;
        layoutParams.topToTop = 0;
        if (z) {
            layoutParams.bottomToTop = R.id.lobby_3a;
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 120;
            layoutParams.bottomMargin = Util.supportStreamGame() ? 20 : 0;
        } else {
            layoutParams.bottomToBottom = 0;
            layoutParams.leftMargin = 60;
        }
        this.mRecyclerView.setLayoutParams(layoutParams);
    }

    private void updateProperty() {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        LogUtil.i("Full", "updateProperty()");
        if (this.mProperty == null || this.mSelectedItem == null || this.mRecyclerView == null || this.mFullAdapter == null || !isFullMode() || this.mSelectedItem == null || (findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(this.mSelectedItemPosition)) == null || !(findViewHolderForAdapterPosition instanceof FullListAdapter.FullGameHolder)) {
            return;
        }
        this.mProperty.updateProperty(this.mSelectedItem, ((FullListAdapter.FullGameHolder) findViewHolderForAdapterPosition).mView);
    }

    private void updateScaleView(int i, int i2) {
        if (this.mRecyclerView == null || !isFullMode()) {
            return;
        }
        LogUtil.i("Full", "updateScaleView() lastP : " + i + ", currP : " + i2);
        FullListAdapter fullListAdapter = this.mFullAdapter;
        if (fullListAdapter != null) {
            fullListAdapter.notifyDataSetChanged();
        }
    }

    private void updateSelected() {
        updateSelectedItem(0, this.mSelectedItemPosition, this.mSelectedItem);
        FullListAdapter fullListAdapter = this.mFullAdapter;
        if (fullListAdapter != null) {
            fullListAdapter.updateSelectedPosition(this.mSelectedItemPosition);
        }
    }

    private boolean updateSelectedGame(AppListItemBean appListItemBean) {
        boolean z = (appListItemBean == null || appListItemBean.isSameItem(this.mSelectedItem)) ? false : true;
        StringBuilder append = new StringBuilder("updateSelectedGame() changed : ").append(z).append(", isAddItem : ");
        AppListItemBean appListItemBean2 = this.mSelectedItem;
        Log.d("3A", append.append(appListItemBean2 == null ? null : Boolean.valueOf(appListItemBean2.isAddItem())).toString());
        BannerHelper bannerHelper = this.mHelper;
        if (bannerHelper == null) {
            updateFocusItem(false);
        } else {
            bannerHelper.clearFocus();
        }
        this.mSelectedItem = appListItemBean;
        updateFocusItem(true);
        String selectedComponent = getSelectedComponent();
        LogUtil.d(TAG, "component : " + selectedComponent);
        AppAddModel.getInstance().setSelected(selectedComponent);
        Controller.getInstance().selectedChanged();
        GamePlayLayout gamePlayLayout = this.mPlayView;
        if (gamePlayLayout != null) {
            gamePlayLayout.setSelectedPkg(getSelectedPkg());
        }
        return z;
    }

    private void updateSelectedItem(int i, int i2, AppListItemBean appListItemBean) {
        LogUtil.i("Full", "updateSelectedItem() lastPos : " + i + ", currPos : " + i2 + ", bean : " + getBeanName(appListItemBean));
        AppListItemBean appListItemBean2 = this.mSelectedItem;
        if (appListItemBean2 != null) {
            appListItemBean2.setCustomUpdateRunnable(null);
        }
        this.mSelectedItemPosition = i2;
        boolean updateSelectedGame = updateSelectedGame(appListItemBean);
        Controller.getInstance().notifyChanged();
        if (isFullMode() && appListItemBean != null) {
            this.mGroup.setVisibility(0);
            if (Util.isZte()) {
                this.mNubiaGroup.setVisibility(8);
            }
        }
        updateBg(appListItemBean, updateSelectedGame);
        updateSelectedViewControl(appListItemBean);
        updateProperty();
        updateScaleView(i, i2);
        this.mOperationHelper.loadOperation(this.mSelectedItem);
        updateOperation();
    }

    private void updateSelectedViewControl(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return;
        }
        LogUtil.i(NeoDownloadHelper.TAG, "updateSelectedViewControl() setText : " + appListItemBean.getName() + ", isDownloadItem : " + appListItemBean.isDownloadItem());
        this.mGameName.setText(appListItemBean.getName());
        this.mPlayView.setChildEnabled(!appListItemBean.isDownloadItem());
        this.mMore.setEnabled(!appListItemBean.isDownloadItem());
        this.mRedPoint.setAlpha((this.mHelper == null || !GameSpaceConfig.supportRedTime()) ? 0.0f : this.mHelper.getRedPointAlpha(appListItemBean));
        doAnimator();
    }

    private void updateTopThreeByTotalTime() {
        LogUtil.i("Full", "---->updateTopThreeByTotalTime()");
        int i = GridAdapter.MEDIUM_COUNT_IN_MEDIUM_MODE;
        int i2 = i - 1;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mHelper.getGameList());
        if (arrayList.size() <= i) {
            AppUsageStatsHelper.getInstance().setCutoffTotalTime(1L);
            return;
        }
        Collections.sort(arrayList, new SortByTotalTime());
        for (int i3 = 0; i3 <= i2; i3++) {
            AppListItemBean appListItemBean = (AppListItemBean) arrayList.get(i3);
            LogUtil.d("usage", "usage total time top three app(" + i3 + ") pkg : " + appListItemBean.getPackageName() + ", name : " + appListItemBean.getName() + ", TotalTime : " + appListItemBean.getTotalTimeMillisecond());
        }
        long totalTimeMillisecond = ((AppListItemBean) arrayList.get(i2)).getTotalTimeMillisecond();
        if (totalTimeMillisecond > 0) {
            AppUsageStatsHelper.getInstance().setCutoffTotalTime(totalTimeMillisecond);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void usageStatsChanged() {
        LogUtil.i("Full", " usageStatsChanged(s)");
        runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.updateLastStartTime();
            }
        });
        updateTopThreeByTotalTime();
        updateProperty();
        refreshGameRecycler(false);
        LogUtil.i("Full", " usageStatsChanged(e)");
    }

    public void addCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void cleanCallback() {
        this.mCallback = null;
    }

    public void cleanup() {
        HandlerThread handlerThread = this.mWorkThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mWorkThread = null;
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mWorkHandler = null;
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    public void clickStart() {
        clickItemBean(R.id.game_start);
    }

    public void doAnimator() {
        if (isAnimEnd()) {
            AnimatorSet animatorSet = this.mAnimatorSet;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimBean animBean = new AnimBean(View.TRANSLATION_X, 150.0f, 0.0f);
            AnimBean animBean2 = new AnimBean(View.ALPHA, 0.25f, 1.0f);
            ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mGameName, 300, animBean, animBean2);
            ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mPlayView, 300, animBean, animBean2);
            ObjectAnimator createPropertyAnim3 = AnimHelper.createPropertyAnim(this.mVip, 300, animBean, animBean2);
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mAnimatorSet = animatorSet2;
            animatorSet2.play(createPropertyAnim).with(createPropertyAnim3).with(createPropertyAnim2);
            this.mAnimatorSet.start();
        }
    }

    @Override // cn.nubia.gamelauncher.model.AppAddModel.NeoDownloadChangeCallBack
    public void doChangeNeoDownloadApp(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return;
        }
        if (isFullMode()) {
            updateNeoDownloadApp(this.mHelper.getFullList(), appListItemBean);
        } else {
            updateNeoDownloadApp(this.mHelper.getGridList(), appListItemBean);
        }
    }

    public void doResume(boolean z) {
        this.mLastAnimTime = System.currentTimeMillis();
        LogUtil.i("Full", "doResume() set mIsStop false !");
        StringBuilder sb = new StringBuilder("doResume() selected : ");
        AppListItemBean appListItemBean = this.mSelectedItem;
        Log.d("3A", sb.append(appListItemBean == null ? null : appListItemBean.getName()).toString());
        if (this.mFullManager == null) {
            return;
        }
        updateBg(this.mSelectedItem, false);
        refreshGameRecycler(false);
        doShowCardTrackIfNeed();
        updateProperty();
        updateSelectedViewControl(this.mSelectedItem);
        if (this.mPlayView == null || z || !isAnimEnd()) {
            LogUtil.i(TAG, "doResume() set mIsStop false !");
        } else {
            this.mPlayView.resetLayout();
        }
    }

    public void enterSingleButtonMode(boolean z) {
        if (!isFullMode()) {
            LogUtil.i(TAG, "enterOnlyMouseMode() but is not full mode!");
            return;
        }
        GamePlayLayout gamePlayLayout = this.mPlayView;
        if (gamePlayLayout == null) {
            LogUtil.i(TAG, "enterOnlyMouseMode() but mPlayView is null!");
        } else {
            gamePlayLayout.enterSingleMode(z);
        }
    }

    public void exit() {
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean != null) {
            appListItemBean.setCustomUpdateRunnable(null);
        }
        this.mOperationHelper.exit();
        this.mGridAdapter = null;
        this.mFullAdapter = null;
        this.mRecyclerView = null;
        this.mSelectedItem = null;
        this.mContext = null;
        unregisterListener();
        this.mHelper.unregisterObserver();
        AppAddModel.getInstance().setAtmosphereRefreshRunnable(null);
        AppAddModel.getInstance().setStartRunnable(null);
        cleanCallback();
        LogUtil.i("Full", "exit() set mIsStop true! ");
    }

    public int getSelectedItemPosition() {
        return this.mSelectedItemPosition;
    }

    public boolean isAnimEnd() {
        return this.mIsAnimEnd;
    }

    /* renamed from: lambda$updateLastStartTime$0$cn-nubia-gamelauncher-recycler-BannerManager, reason: not valid java name */
    /* synthetic */ void m328x2ba7f977() {
        refreshGameRecycler(false);
    }

    /* renamed from: lambda$updateSelectedBg$1$cn-nubia-gamelauncher-recycler-BannerManager, reason: not valid java name */
    /* synthetic */ void m329xf67c5b43() {
        updateBg(this.mSelectedItem, false);
    }

    @Override // cn.nubia.gamelauncher.recycler.GridAdapter.OnAppBeanClickListener
    public void onAppBeanClick(AppListItemBean appListItemBean) {
        clickItemBean(appListItemBean, R.id.game_start);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.game_start /* 2131362353 */:
            case R.id.play_by_handle /* 2131363027 */:
            case R.id.play_by_mirror /* 2131363028 */:
            case R.id.play_by_mouse /* 2131363029 */:
                clickItemBean(view.getId());
                LobbySoundPoolHelper.getInstance().play();
                VibratorHelper.getInstance().vibrateSync();
                break;
            case R.id.lobby_3a /* 2131362709 */:
                click3AGame();
                break;
            case R.id.lobby_control /* 2131362710 */:
                clickControl();
                break;
            case R.id.lobby_custom /* 2131362711 */:
                if (!ActivityManager.isUserAMonkey()) {
                    clickModify();
                    break;
                }
                break;
            case R.id.lobby_gift_bag /* 2131362714 */:
                showOperationDialog(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda10
                    @Override // java.lang.Runnable
                    public final void run() {
                        BannerManager.this.clickGiftBag();
                    }
                });
                break;
            case R.id.lobby_notes /* 2131362718 */:
                clickGameNotes();
                break;
            case R.id.lobby_red_time /* 2131362720 */:
                if (!ActivityManager.isUserAMonkey()) {
                    clickRedTime();
                    break;
                }
                break;
            case R.id.lobby_vip /* 2131362721 */:
                showOperationDialog(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        BannerManager.this.clickVip();
                    }
                });
                break;
        }
    }

    @Override // cn.nubia.gamelauncher.neostore.OperationHelper.OperationCallback
    public void onGiftChanged(boolean z) {
        LogUtil.i("Full", "onGiftChanged() hasGift : " + z);
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return;
        }
        this.mGift.setAlpha(isSupportVip(appListItemBean) ? 1.0f : 0.0f);
        this.mGift.setVisibility(isSupportVip(this.mSelectedItem) ? 0 : 8);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Log.i(TAG, "onKey keyEvent " + keyEvent.getAction() + " keyCode : " + i);
        if (view.getId() != R.id.start_button) {
            return false;
        }
        if ((i != 23 && i != 96) || keyEvent.getAction() != 0) {
            return false;
        }
        clickItemBean(R.id.game_start);
        LobbySoundPoolHelper.getInstance().play();
        VibratorHelper.getInstance().vibrateSync();
        return false;
    }

    @Override // cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack
    public void onLoadAddAppListDone(ArrayList<AppListItemBean> arrayList, int i) {
        LogUtil.i(TAG, "onLoadAddAppListDone() list : " + arrayList);
        refreshGameRecycler(true);
        AppUsageStatsHelper.getInstance().resetLastUpdateTime();
        AppUsageStatsHelper.getInstance().updateAppUsageStat();
    }

    @Override // cn.nubia.gamelauncher.neostore.OperationHelper.OperationCallback
    public void onOperationKeyChanged(boolean z) {
        LogUtil.i("Full", "onOperationKeyChanged() isClose : " + z);
        updateOperation();
        if (isOperationKeyClosed()) {
            return;
        }
        this.mOperationHelper.loadOperation(this.mSelectedItem);
    }

    @Override // cn.nubia.gamelauncher.neostore.OperationHelper.OperationCallback
    public void onRelevantChanged(List<RelevantBean> list) {
        LogUtil.i("Full", "onRelevantChanged() list : " + list);
    }

    @Override // cn.nubia.gamelauncher.recycler.FullListAdapter.AppClickListener
    public void onSelectedItemChanged(int i, int i2) {
        AppListItemBean findItemByPosition;
        Log.d("3A", "onSelectedItemChanged() lastPosition : " + i + ", currentPosition : " + i2);
        LobbySoundPoolHelper.getInstance().play();
        VibratorHelper.getInstance().vibrateSync();
        this.mHasMoved = true;
        BannerHelper bannerHelper = this.mHelper;
        if (bannerHelper == null || (findItemByPosition = bannerHelper.findItemByPosition(i2)) == null || findItemByPosition.isAddItem()) {
            return;
        }
        updateSelectedItem(i, i2, findItemByPosition);
        if (i == i2 || this.mPlayView == null || !isAnimEnd()) {
            return;
        }
        this.mPlayView.resetLayout();
    }

    @Override // cn.nubia.common.helper.AppUsageStatsHelper.AppUsageStatsChangedListener
    public void onUsageStatsChanged(boolean z) {
        this.mNeedReset = z;
        BannerHelper bannerHelper = this.mHelper;
        if (bannerHelper == null || bannerHelper.getGameList() == null || this.mHelper.getGameList().size() <= 0) {
            return;
        }
        if (!z) {
            usageStatsChanged();
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BannerManager.this.usageStatsChanged();
                }
            }, 200L);
        }
    }

    @Override // cn.nubia.gamelauncher.neostore.OperationHelper.OperationCallback
    public void onVipChanged(boolean z) {
        LogUtil.i("Full", "onVipChanged() isVip : " + z);
        AppListItemBean appListItemBean = this.mSelectedItem;
        if (appListItemBean == null) {
            return;
        }
        this.mVip.setVisibility(isSupportVip(appListItemBean) ? 0 : 8);
    }

    public void refreshAtmosphere() {
        runOnUiThread(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.updateAtmosphere();
            }
        });
    }

    @Override // cn.nubia.gamelauncher.model.AppAddModel.NeoDownloadChangeCallBack
    public void refreshGameRecycler(boolean z) {
        if (this.mHelper == null) {
            return;
        }
        LogUtil.i("Full", "refreshGameRecycler()");
        this.mHelper.RefillGameList();
        resetSelectedItemIfNeed();
        if (this.mFullAdapter == null || this.mGridAdapter == null) {
            LogUtil.i("Full", "refreshGameRecycler() and adapter is null!");
            initAdapter();
            switchViewMode();
        }
        FullListAdapter fullListAdapter = this.mFullAdapter;
        if (fullListAdapter != null) {
            fullListAdapter.notifyDataSetChanged();
        }
        GridAdapter gridAdapter = this.mGridAdapter;
        if (gridAdapter != null) {
            gridAdapter.notifyDataSetChanged();
        }
        ScoreRecordsController.getInstance().setFullGameList(this.mHelper.getFullList());
    }

    void resetDownloadData() {
        BannerRecyclerView bannerRecyclerView = this.mRecyclerView;
        if (bannerRecyclerView == null) {
            return;
        }
        ((GridAdapter) bannerRecyclerView.getAdapter()).resetNeoDownloadMap();
    }

    public void setHiddenChanged(boolean z) {
        this.isHidden = z;
        updateAtmosphereIfNeed();
    }

    public void startAnimator() {
        Trace.beginSection("startAnimator");
        if (this.mIsAnimEnd) {
            return;
        }
        LogUtil.i(TAG, "startAnimator()");
        if (isFullMode()) {
            this.mRecyclerView.setAlpha(1.0f);
        } else {
            this.mRecyclerView.setTranslationY(200.0f);
        }
        if (this.mPlayView != null) {
            this.mEnterLottie.setTranslationX(Controller.getInstance().isPureMode() ? 10.0f : 0.0f);
            this.mPlayView.doEnterAnim(this.mEnterLottie);
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(500L);
        ofFloat.setInterpolator(Anim3DHelper.PATH_INTERPOLATOR_CARD_ENTER);
        ofFloat.start();
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gamelauncher.recycler.BannerManager.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (floatValue > 1.0f || BannerManager.this.mRecyclerView == null) {
                    return;
                }
                if (!BannerManager.isFullMode()) {
                    BannerManager.this.mRecyclerView.setAlpha(1.0f - floatValue);
                    BannerManager.this.mRecyclerView.setTranslationY(floatValue * 100.0f);
                }
                BannerManager.this.mRecyclerView.requestLayout();
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.gamelauncher.recycler.BannerManager.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                LogUtil.i(BannerManager.TAG, "---->onAnimationCancel()");
                BannerManager.this.startAnimEnd();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                LogUtil.i(BannerManager.TAG, "---->onAnimationEnd()");
                BannerManager.this.startAnimEnd();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
            public void onAnimationPause(Animator animator) {
                super.onAnimationPause(animator);
                LogUtil.i(BannerManager.TAG, "---->onAnimationPause()");
            }
        });
    }

    public void switchViewMode() {
        if (this.mRecyclerView == null) {
            return;
        }
        Log.i("Controller", "Banner - switchViewMode(" + this.isFullMode + ") isFullMode() : " + Controller.getInstance().isFullMode() + ", isFirstSwitch : " + this.isFirstSwitch);
        if ((this.isFirstSwitch || this.isFullMode != Controller.getInstance().isFullMode()) && this.mRecyclerView != null) {
            this.isFirstSwitch = false;
            boolean isFullMode = Controller.getInstance().isFullMode();
            this.isFullMode = isFullMode;
            if (isFullMode) {
                switchToFullMode();
            } else {
                switchToGridMode();
            }
            doShowCardTrackIfNeed();
        }
    }

    public void unregisterAppModelCallBack() {
        AppAddModel.getInstance().unResisterGetAppStatusDataCallBack(this);
        AppAddModel.getInstance().unresisterNeoDownloadChangeCallBack(this);
    }

    public void updateAtmosphereIfNeed() {
        if (canUpdateAtmosphere()) {
            updateBg(this.mSelectedItem, false);
        }
    }

    public void updateBannerAdapterUI() {
        if (!isFullMode() || this.mFullAdapter == null) {
            return;
        }
        LogUtil.i(TAG, "updateBannerAdapterUI");
        this.mFullAdapter.notifyDataSetChanged();
    }

    public void updateSelectedBg() {
        LogUtil.i("Full", "updateSelectedBg()");
        runOnUiThread(new Runnable() { // from class: cn.nubia.gamelauncher.recycler.BannerManager$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.this.m329xf67c5b43();
            }
        });
    }
}
