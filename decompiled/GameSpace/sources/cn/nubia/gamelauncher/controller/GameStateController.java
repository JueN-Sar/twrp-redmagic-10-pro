package cn.nubia.gamelauncher.controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.SharedPreferencesUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.activity.GameSpaceActivity;
import cn.nubia.gamelauncher.helper.BaseSdkHelper;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.helper.SDKHelper;
import cn.nubia.gamelauncher.neostore.NeoHelper;
import cn.nubia.gamelauncher.processmanager.IProcessManagerService;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameCenterHelper;
import cn.nubia.gamelauncher.util.GameKeysConstant;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.TimerServiceUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.view.MarqueeTextView;
import cn.nubia.gamelauncher.view.OneMoreThing;
import cn.nubia.neostore.api.callback.ICallback;
import cn.nubia.neostore.api.model.ErrorMsg;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GameStateController implements View.OnClickListener, BaseSdkHelper.RedDotCallback, ICallback<Boolean> {
    public static final String TAG = "Controller";
    private static final int THREE_LIGHT_VISIBLE_GAMETIME = 6;
    private static final String VIRTUAL_GAME_KEY = "virtual_game_key";
    private Activity mActivity;
    private BottomController mBottomController;
    private Context mContext;
    private String mDotResId;
    private Button mExternDeviceBtn;
    private Button mGameCenterBtn;
    private ImageView mGameCenterRedPoint;
    private Button mGameExit;
    private GameKeySOffOnContentObserver mGameKeySOffOnContentObserver;
    private Button mGameRecommendBtn;
    private IProcessManagerService mIProcessManagerService;
    private ImageView mMagicLogo;
    private TextView mOMTZan;
    private OneMoreThing mOneMoreThing;
    private Button mPersonalCenter80;
    private ImageView mRedDot;
    private ImageView mRedPoint;
    private ShortcutController mShortcutController;
    private MarqueeTextView mTabBase;
    private MarqueeTextView mTabLobby;
    private TopController mTopController;
    private boolean mHasRedDot = false;
    private int mTimeHour = 0;
    private boolean mIsTimeOut4H = true;
    private boolean mIsTimeOutShowDialog = false;
    private final int DURATION = 1000;
    private final int COUNTS = 4;
    private long[] mHitLogos = new long[4];
    private final ServiceConnection mProcessServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamelauncher.controller.GameStateController.7
        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            GameStateController.this.mIProcessManagerService = null;
            GameStateController.this.binderProcessManagerService();
        }

        /* JADX WARN: Type inference failed for: r4v3, types: [cn.nubia.gamelauncher.controller.GameStateController$7$1] */
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GameStateController.this.mIProcessManagerService = IProcessManagerService.Stub.asInterface(iBinder);
            final WeakReference weakReference = new WeakReference(GameStateController.this);
            new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.gamelauncher.controller.GameStateController.7.1
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    GameStateController gameStateController = (GameStateController) weakReference.get();
                    if (gameStateController == null) {
                        return null;
                    }
                    gameStateController.clearAllunLockTaskExcludeTopTask("gamelauncher#cn.nubia.gamelauncher");
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GameStateController.this.mIProcessManagerService = null;
        }
    };

    private class GameKeySOffOnContentObserver extends ContentObserver {
        public GameKeySOffOnContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
        }

        public void register() {
            GameStateController.this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_db_game_keys"), false, this);
        }

        public void unregister() {
            GameStateController.this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }

    public GameStateController(Activity activity) {
        this.mActivity = activity;
        this.mContext = activity.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void binderProcessManagerService() {
        try {
            if (this.mIProcessManagerService == null) {
                Intent intent = new Intent();
                intent.setClassName("cn.nubia.processmanager", "cn.nubia.processmanager.service.ProcessManagerService");
                this.mContext.bindService(intent, this.mProcessServiceConnection, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clickRecommend() {
        if (Util.isTencentAppStore()) {
            clickRedDot(this.mContext);
            GameCenterHelper.startTencentGameRecommend(this.mActivity);
        } else {
            GameCenterHelper.startGameRecommend(this.mActivity);
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_game_recommendation_click");
    }

    private void clickRedDot(Context context) {
        if (this.mHasRedDot) {
            Log.d("tx", "GS---->clickRedDot()");
            this.mHasRedDot = false;
            SDKHelper.getInstance().requestRedDotClick(context, this.mDotResId);
            this.mRedDot.setVisibility(8);
        }
    }

    private void exitGameSpace() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity, 2131952382);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.dialog_text_exit, (ViewGroup) null);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.is_add_shortcut);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cn.nubia.gamelauncher.controller.GameStateController.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                checkBox.setChecked(z);
            }
        });
        if (this.mShortcutController.hasShortcut() || Util.isZte() || Util.isRedMagicRunOnMyOs()) {
            checkBox.setChecked(false);
            checkBox.setVisibility(8);
        }
        builder.setView(inflate);
        builder.setPositiveButton(R.string.exit_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.GameStateController.3
            /* JADX WARN: Type inference failed for: r3v1, types: [cn.nubia.gamelauncher.controller.GameStateController$3$1] */
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.gamelauncher.controller.GameStateController.3.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Void doInBackground(Void... voidArr) {
                        if (checkBox.isChecked() && !GameStateController.this.mShortcutController.hasShortcut()) {
                            GameStateController.this.mShortcutController.addShortcut(true);
                        }
                        GameStateController.this.switchVirtualGameKey(0);
                        return null;
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.GameStateController.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog create = builder.create();
        create.show();
        create.getWindow().setWindowAnimations(2131952381);
    }

    private boolean getGameTimeRemindState() {
        boolean z = 1 == Settings.Global.getInt(this.mContext.getContentResolver(), GameKeysConstant.DB_GAME_TIME_REMIND, 1);
        Log.i("Controller", "getGameTimeRemindState " + z);
        return z;
    }

    private void initAddShortcut(View view) {
        this.mShortcutController = new ShortcutController(view);
    }

    private void initBottomController(View view) {
        this.mBottomController = new BottomController(view);
    }

    private void initRedDot(Context context) {
        if (Util.isTencentAppStore()) {
            SDKHelper.getInstance().requestHasRedDot(context, this);
        }
    }

    private void initTopController(View view) {
        this.mTopController = new TopController(view, this.mShortcutController);
    }

    private void initView(Context context, View view) {
        Log.d("viewm", "GS - initView()");
        initAddShortcut(view);
        Button button = (Button) view.findViewById(R.id.game_recommend);
        this.mGameRecommendBtn = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.extern_device);
        this.mExternDeviceBtn = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) view.findViewById(R.id.play_center);
        this.mGameCenterBtn = button3;
        button3.setOnClickListener(this);
        this.mGameCenterRedPoint = (ImageView) view.findViewById(R.id.play_center_point);
        Button button4 = (Button) view.findViewById(R.id.personal_center);
        this.mPersonalCenter80 = button4;
        button4.setOnClickListener(this);
        this.mTabLobby = (MarqueeTextView) view.findViewById(R.id.tab_game_lobby);
        this.mTabBase = (MarqueeTextView) view.findViewById(R.id.tab_red_magic_planet);
        this.mOneMoreThing = (OneMoreThing) view.findViewById(R.id.one_more_thing_in);
        this.mOMTZan = (TextView) view.findViewById(R.id.omt_zan);
        ImageView imageView = (ImageView) view.findViewById(R.id.magic_logo80);
        this.mMagicLogo = imageView;
        imageView.setOnClickListener(this);
        Button button5 = (Button) view.findViewById(R.id.exit_button80);
        this.mGameExit = button5;
        button5.setOnClickListener(this);
        this.mRedPoint = (ImageView) view.findViewById(R.id.red_point);
        this.mRedDot = (ImageView) view.findViewById(R.id.red_dot);
        initRedDot(this.mContext);
        initTopController(view);
        initBottomController(view);
        if (!GameSpaceConfig.supportExternDevice()) {
            this.mExternDeviceBtn.setVisibility(8);
        }
        if (!GameSpaceConfig.supportRelevant()) {
            this.mGameRecommendBtn.setVisibility(8);
        }
        updateExitVisible();
        setTabVisibility(false);
        this.mGameCenterBtn.setVisibility(8);
    }

    private void onHitLogo() {
        if (LogUtil.DEBUG) {
            return;
        }
        long[] jArr = this.mHitLogos;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long uptimeMillis = SystemClock.uptimeMillis();
        long[] jArr2 = this.mHitLogos;
        jArr2[jArr2.length - 1] = uptimeMillis;
        if (uptimeMillis - jArr2[0] <= 1000) {
            Arrays.fill(jArr2, 0L);
            LogUtil.DEBUG = true;
            LogUtil.i(this, "------>go to debug mode!");
        }
    }

    private void setGameTimeDatas(Context context) {
        if (TimerServiceUtil.isUpdateDate(context)) {
            updateTimerView(0);
        } else {
            updateTimerView(TimerServiceUtil.readTimerTosharedPrefs(context));
        }
    }

    private void showGameTimeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity, 2131952381);
        builder.setMessage(this.mContext.getString(R.string.gametime_overtime_message)).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.GameStateController.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                TimerServiceUtil.setTimeOut4HValue(GameStateController.this.mContext);
                dialogInterface.dismiss();
            }
        }).create();
        if (this.mActivity.isFinishing()) {
            return;
        }
        builder.show();
        this.mIsTimeOutShowDialog = true;
    }

    private void showGameTimeWeeklyRemindDialog() {
        Log.i("Controller", "showGameTimeWeeklyRemindDialog");
        Activity activity = this.mActivity;
        if (activity == null || activity.getWindow().getDecorView().getVisibility() != 0) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity, 2131952381);
        builder.setMessage(this.mContext.getString(R.string.game_time_weekly_message)).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.GameStateController.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(R.string.game_time_weekly_positive, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.GameStateController.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                GameCenterHelper.startUsersGameSetttings(GameStateController.this.mContext, true);
                dialogInterface.dismiss();
            }
        });
        AlertDialog create = builder.create();
        create.show();
        DisplayMetrics displayMetrics = this.mActivity.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.width = displayMetrics.widthPixels;
        create.getWindow().setAttributes(attributes);
    }

    private void showGameTimeout4hDialog() {
        boolean timeOut4HValue = TimerServiceUtil.getTimeOut4HValue(this.mContext);
        this.mIsTimeOut4H = timeOut4HValue;
        if (this.mTimeHour < 6 || !timeOut4HValue || this.mIsTimeOutShowDialog) {
            return;
        }
        showGameTimeDialog();
    }

    private void startKeysHelperActivity() {
        GameCenterHelper.startRedMagicDevice(this.mContext);
    }

    private void unregisterObserver() {
        GameKeySOffOnContentObserver gameKeySOffOnContentObserver = this.mGameKeySOffOnContentObserver;
        if (gameKeySOffOnContentObserver != null) {
            gameKeySOffOnContentObserver.unregister();
        }
    }

    public void checkUnionNewData() {
        NeoHelper.initIfNeed();
        NeoHelper.checkUnionNewData(this.mContext, this);
    }

    public void clearAllunLockTaskExcludeTopTask(String str) {
        try {
            IProcessManagerService iProcessManagerService = this.mIProcessManagerService;
            if (iProcessManagerService != null) {
                iProcessManagerService.oneKeyCleanExcludeCurrentApp(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doResume() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        if (!SharedPreferencesUtil.getInstance(context).isFirstStartApp()) {
            showGameTimeout4hDialog();
        }
        checkUnionNewData();
        setGameTimeDatas(this.mContext);
        updateExitVisible();
        updateTopController();
        updateBgm();
    }

    public void exit() {
        TopController topController = this.mTopController;
        if (topController != null) {
            topController.exit();
        }
        BottomController bottomController = this.mBottomController;
        if (bottomController != null) {
            bottomController.exit();
        }
        unBinderProcessManagerService();
        unregisterObserverAndService();
        this.mActivity = null;
    }

    public void initGameSpaceSwitchView(Context context, View view) {
        Log.d("viewm", "initGameSpaceSwitchView()");
        this.mContext = context.getApplicationContext();
        if (!Util.isZte()) {
            binderProcessManagerService();
        }
        initView(context, view);
    }

    public boolean isConsumptionTouch(MotionEvent motionEvent) {
        TopController topController = this.mTopController;
        if (topController == null) {
            return false;
        }
        return topController.isConsumptionTouch(motionEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exit_button80 /* 2131362169 */:
                exitGameSpace();
                LobbySoundPoolHelper.getInstance().play();
                break;
            case R.id.extern_device /* 2131362226 */:
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_redmagic_handle_click");
                startKeysHelperActivity();
                LobbySoundPoolHelper.getInstance().play();
                break;
            case R.id.game_recommend /* 2131362347 */:
                clickRecommend();
                LobbySoundPoolHelper.getInstance().play();
                break;
            case R.id.magic_logo80 /* 2131362732 */:
                onHitLogo();
                break;
            case R.id.personal_center /* 2131363011 */:
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_personal_center_click");
                GameCenterHelper.startUsersGameSetttings(this.mContext, false);
                LobbySoundPoolHelper.getInstance().play();
                break;
            case R.id.play_center /* 2131363031 */:
                NeoHelper.startRedMagicUnionActivity(this.mActivity, 0);
                updateGameCenterRedPointVisble(false);
                LobbySoundPoolHelper.getInstance().play();
                break;
        }
    }

    @Override // cn.nubia.neostore.api.callback.ICallback
    public void onError(ErrorMsg errorMsg) {
        Log.d("RPoint", "GS---->onError(" + errorMsg.errMsg + ")");
        updateGameCenterRedPointVisble(false);
    }

    @Override // cn.nubia.gamelauncher.helper.BaseSdkHelper.RedDotCallback
    public void onRedDotResult(String str) {
        Log.d("tx", "GS---->onRedDotResult() dotResId : " + str);
        this.mHasRedDot = true;
        this.mDotResId = str;
        this.mRedDot.setVisibility(0);
    }

    @Override // cn.nubia.neostore.api.callback.ICallback
    public void onSuccess(Boolean bool) {
        Log.d("RPoint", "GS---->onSuccess(" + bool + ")");
        updateGameCenterRedPointVisble(bool.booleanValue());
    }

    public void refreshTimer(int i) {
        Log.i("Controller", "refreshTimer");
        if (getGameTimeRemindState() && TimerServiceUtil.isGameTimeWeeklyRemind(this.mContext) && TimerServiceUtil.getWeeklyTimeoutValue(this.mContext)) {
            showGameTimeWeeklyRemindDialog();
            TimerServiceUtil.setWeeklyTimeoutValue(this.mContext, false);
        }
    }

    public void registerObserver() {
        GameKeySOffOnContentObserver gameKeySOffOnContentObserver = new GameKeySOffOnContentObserver(new Handler());
        this.mGameKeySOffOnContentObserver = gameKeySOffOnContentObserver;
        gameKeySOffOnContentObserver.register();
        ShortcutController shortcutController = this.mShortcutController;
        if (shortcutController != null) {
            shortcutController.registerObserver();
        }
    }

    public void setLottieInvisible() {
        this.mBottomController.setLottieVisibility(4);
    }

    public void setLottieVisible() {
        this.mBottomController.setLottieVisibility(0);
    }

    public void setOneMoreThingVisible(int i) {
        OneMoreThing oneMoreThing = this.mOneMoreThing;
        if (oneMoreThing == null || this.mOMTZan == null) {
            return;
        }
        oneMoreThing.setVisibility(i);
        if (CommonUtil.isInternalVersion()) {
            this.mOMTZan.setVisibility(8);
        } else {
            this.mOMTZan.setVisibility(i);
        }
        if (Controller.getInstance().supportOneMoreThing()) {
            return;
        }
        this.mOneMoreThing.setVisibility(8);
        this.mOMTZan.setVisibility(8);
    }

    public void setSwitchCallback(Runnable runnable, Runnable runnable2) {
        this.mBottomController.setSwitchCallback(runnable, runnable2);
    }

    public void setTabVisibility(boolean z) {
        this.mTabBase.setVisibility((!GameSpaceConfig.supportBase() || z) ? 8 : 0);
        this.mTabLobby.setVisibility((!GameSpaceConfig.supportBase() || z) ? 8 : 0);
    }

    public boolean supportExitButton() {
        return (Util.supportVirtualGameKey() && GameSpaceConfig.supportGameKey()) || Util.isSwitchGameKeyToOtherFunctions();
    }

    public void switchVirtualGameKey(int i) {
        Context context = this.mContext;
        if (context == null && this.mActivity == null) {
            return;
        }
        if (context == null) {
            context = this.mActivity.getApplicationContext();
        }
        Settings.Global.putInt(context.getContentResolver(), VIRTUAL_GAME_KEY, i);
        Log.d("zteg", "switchVirtualGameKey() value : " + i);
    }

    public void unBinderProcessManagerService() {
        try {
            this.mContext.unbindService(this.mProcessServiceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregisterObserverAndService() {
        unregisterObserver();
        ShortcutController shortcutController = this.mShortcutController;
        if (shortcutController != null) {
            shortcutController.unregisterObserver();
        }
    }

    public void updateBgm() {
        BottomController bottomController = this.mBottomController;
        if (bottomController == null) {
            return;
        }
        bottomController.updateBgm();
    }

    public void updateExitVisible() {
        this.mGameExit.setVisibility(supportExitButton() ? 0 : 8);
    }

    public void updateGameCenterRedPointVisble(boolean z) {
        if (Util.isRedMagicRunOnMyOs() || Util.isZte() || this.mGameCenterRedPoint == null) {
            return;
        }
        Log.d("RPoint", "GS---->updateGameCenterRedPointVisble -> setVisibility(" + z + ")");
    }

    public void updateRedPointVisble(boolean z) {
        if (this.mRedPoint == null) {
            return;
        }
        Log.d("RPoint", "GS---->updateRedPointVisble -> setVisibility(" + z + ")");
    }

    public void updateTabView(String str) {
        if (this.mTabLobby == null || this.mTabBase == null || this.mContext == null) {
            return;
        }
        str.hashCode();
        if (str.equals(GameSpaceActivity.TAG_GAME_LOBBY)) {
            this.mTabLobby.setBackgroundResource(R.drawable.tab_game_lobby_select);
            this.mTabBase.setBackgroundResource(R.drawable.tab_game_planet);
            this.mTabLobby.setTextColor(this.mContext.getResources().getColor(R.color.text_color_btn));
            this.mTabBase.setTextColor(this.mContext.getResources().getColor(R.color.text_color_unselected));
            if (Util.isPureMode()) {
                this.mTabBase.requestFocus();
                this.mTabLobby.requestFocus();
                return;
            }
            return;
        }
        if (str.equals(GameSpaceActivity.TAG_RED_MAGIC_PLANET)) {
            this.mTabLobby.setBackgroundResource(R.drawable.tab_game_lobby);
            this.mTabBase.setBackgroundResource(R.drawable.tab_red_magic_planet_select);
            this.mTabLobby.setTextColor(this.mContext.getResources().getColor(R.color.text_color_unselected));
            this.mTabBase.setTextColor(this.mContext.getResources().getColor(R.color.text_color_btn));
            if (Util.isPureMode()) {
                this.mTabLobby.requestFocus();
                this.mTabBase.requestFocus();
            }
        }
    }

    public void updateTimerView(int i) {
        if (this.mActivity == null) {
            return;
        }
        refreshTimer(i);
    }

    public void updateTopController() {
        TopController topController = this.mTopController;
        if (topController == null) {
            return;
        }
        topController.updateRelevantVisible();
    }
}
