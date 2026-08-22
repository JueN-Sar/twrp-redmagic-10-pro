package cn.nubia.gamecenter.settings.net;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.media3.common.C;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.app.AlertDialogCenter;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.game.networkacceleration.service.INetworkAccelerationCallback;
import cn.nubia.game.networkacceleration.service.INetworkAccelerationService;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterNetAccelerationAppPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterNetAccelerationDetailsPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterNetAccelerationPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.settings.owlsysaciton.OwlSysHelper;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
public class NetFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String BROAD_CAST_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    private static final String BROAD_CAST_WIFI_STATE_CHANGED = "android.net.wifi.STATE_CHANGE";
    public static final String DB_NAME_GAME_ASSISTANT_SIM = "game_assistant_sim_enable";
    private static final String GAME_NETWORKACCELERATION_PACKAGE_NAME = "cn.nubia.game.networkacceleration";
    private static final String GAME_NETWORKACCELERATION_SERVICE_NAME = "cn.nubia.game.networkacceleration.service.NetworkAccelerationService";
    private static final String KEY_GAME_MODE_ASSISTANT_SIM = "gamemode_assistant_sim";
    private static final String KEY_GAME_MODE_NETWORKACCELERATION_APP = "gamemode_networkacceleration_app";
    private static final String KEY_GAME_MODE_NETWORKACCELERATION_DETAILS = "gamemode_networkacceleration_details";
    private static final String KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_DETAIL = "gamemode_networkacceleration_tencent_network_detail";
    private static final String KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_INFO = "gamemode_networkacceleration_tencent_network_info";
    private static final String KEY_GAME_MODE_NETWORKACCELERATION_TX_PROMPT = "gamemode_networkacceleration_tencent_prompt";
    private static final int MSG_REFRESH_NETWORKACC_PREFERENCES = 0;
    private static final int REQUEST_CODE_PHONE_STATE = 10001;
    private static final int SDK_EXPIRED = 5;
    private static final int SDK_FREE = 6;
    private static final int SDK_FREE_TRIAL = 2;
    private static final int SDK_IN_USE = 4;
    private static final int SDK_NOT_QUALIFIED = 0;
    private static final int SDK_QUALIFIED = 1;
    private static final int SDK_TRIAL_EXPIRED = 3;
    private static final String TAG = "NetFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGameModeAssistantSim;
    private GameCenterSwitchPreference mGameModeChangeNetwork;
    private GameCenterSwitchPreference mGameModeNetwork;
    private GameCenterNetAccelerationPreference mGameModeNetworkAcceleration;
    private GameCenterNetAccelerationAppPreference mGameModeNetworkAccelerationApp;
    private GameCenterNetAccelerationDetailsPreference mGameModeNetworkAccelerationDetails;
    private GameCenterPreference mGameModeNetworkAccelerationTxNetDetail;
    private GameCenterSwitchPreference mGameModeNetworkAccelerationTxNetInfo;
    private GameCenterSwitchPreference mGameModeNetworkAccelerationTxPrompt;
    private INetworkAccelerationService mINetworkAccelerationService;
    private boolean mIsVisible;
    private String m_tag;
    private static final String KEY_GAME_MODE_NETWORK = "gamemode_network";
    private static final String KEY_GAME_MODE_CNANGE_NETWORK = "gamemode_change_network";
    private static final String KEY_GAME_MODE_NETWORKACCELERATION = "gamemode_networkacceleration";
    private static final String[] PREFERENCE_ITEMS = {KEY_GAME_MODE_NETWORK, KEY_GAME_MODE_CNANGE_NETWORK, KEY_GAME_MODE_NETWORKACCELERATION};
    private boolean mIsSupportNetworkAcc = false;
    private HandlerThread mWorkHandlerThread = null;
    private Handler mWorkHandler = null;
    private Handler mUIHandler = new Handler();
    private int mLastUserState = -1;
    private int mSDKValue = 0;
    private boolean supportShieldAssistantSim = false;
    private BroadcastReceiver mAssistantSimReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action) || !NetFragment.this.supportShieldAssistantSim) {
                return;
            }
            if (NetFragment.BROAD_CAST_SIM_STATE_CHANGED.equals(action) || NetFragment.BROAD_CAST_WIFI_STATE_CHANGED.equals(action)) {
                NetFragment.this.mUIHandler.removeCallbacksAndMessages(null);
                NetFragment.this.mUIHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NetFragment.this.initGameAssistantSimStatus();
                    }
                }, NetFragment.BROAD_CAST_WIFI_STATE_CHANGED.equals(action) ? C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS : 0L);
            }
        }
    };
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.d(NetFragment.TAG, "onServiceConnected");
            NetFragment.this.mINetworkAccelerationService = INetworkAccelerationService.Stub.asInterface(iBinder);
            NetFragment.this.registerCallback();
            NetFragment.this.updateXunyouUserState();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            NetFragment.this.mINetworkAccelerationService = null;
        }
    };
    private final NetworkAccelerationCallback mINACallback = new NetworkAccelerationCallback(this);
    private Handler mHandler = new Handler() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.9
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            NetFragment.this.refreshNetworkAccelerationPreferences(((Integer) message.obj).intValue());
        }
    };

    private static class NetworkAccelerationCallback extends INetworkAccelerationCallback.Stub {
        private final WeakReference<NetFragment> mNetFragment;

        public NetworkAccelerationCallback(NetFragment netFragment) {
            this.mNetFragment = new WeakReference<>(netFragment);
        }

        @Override // cn.nubia.game.networkacceleration.service.INetworkAccelerationCallback
        public void onAccessTokenExpired() {
            LogUtil.d(NetFragment.TAG, "onAccessTokenExpired");
            if (this.mNetFragment.get() != null) {
                this.mNetFragment.get().onAccessTokenExpired();
            }
        }

        @Override // cn.nubia.game.networkacceleration.service.INetworkAccelerationCallback
        public void onNBAccountLoginError(String str) {
            LogUtil.i(NetFragment.TAG, "onNBAccountLoginError errorType:=" + str);
            if (this.mNetFragment.get() != null) {
                this.mNetFragment.get().onNBAccountLoginError(str);
            }
        }

        @Override // cn.nubia.game.networkacceleration.service.INetworkAccelerationCallback
        public void onNBAccountLoginSuccess(String str, String str2, String str3) {
            LogUtil.d(NetFragment.TAG, "onNBAccountLoginSuccess code:=" + str);
            if (this.mNetFragment.get() != null) {
                this.mNetFragment.get().onNBAccountLoginSuccess(str);
            }
        }

        @Override // cn.nubia.game.networkacceleration.service.INetworkAccelerationCallback
        public void onXunyouUserState(int i) throws RemoteException {
            LogUtil.i(NetFragment.TAG, "onXunyouUserState userState:=" + i);
            if (this.mNetFragment.get() != null) {
                this.mNetFragment.get().onXunyouUserState(i);
            }
        }
    }

    private void bindNetworkAccelerationService() {
        Intent intent = new Intent();
        intent.setClassName(GAME_NETWORKACCELERATION_PACKAGE_NAME, GAME_NETWORKACCELERATION_SERVICE_NAME);
        this.mContext.bindService(intent, this.mServiceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildTencentJiasuqiCenterAlertDialog(final int i) {
        new AlertDialogCenter.Builder(getContext(), R.style.Theme_Nubia_Dialog_Alert).setTitle(R.string.gamemode_tencent_acceleration_dialog_title).setMessage(R.string.gamemode_tencent_acceleration_dialog_message).setPositiveButton(R.string.gamekeys_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                NetFragment.this.mSDKValue = i;
                LogUtil.d(NetFragment.TAG, "TXDBNETSDK which" + i2);
                SettingUtil.putInt(NetFragment.this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_SDK, i);
                NetFragment.this.mGameModeNetworkAcceleration.setAcceleratedSDKName(NetFragment.this.mSDKValue);
                NetFragment.this.handleAccelerationSDKStatusChange();
            }
        }).setNegativeButton(R.string.gamemode_account_login_cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    private void changeAccelerationSDK() {
        new NetAcceleratedDialog(this.mContext, this.mSDKValue, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 1 && !NetFragment.this.isNBAccountLogin()) {
                    NetFragment.this.showAccountLoginAlertDialog();
                    return;
                }
                if (i == 2 && NetAcceleratedConfig.checkIfExistTencentAcceleratedApp(NetFragment.this.mContext)) {
                    if (i != SettingUtil.getInt(NetFragment.this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_SDK, 0)) {
                        NetFragment.this.buildTencentJiasuqiCenterAlertDialog(i);
                    }
                } else {
                    if (i == 2 && !NetAcceleratedConfig.checkIfExistTencentAcceleratedApp(NetFragment.this.mContext)) {
                        NetFragment.this.showTxAcceleratedLoadAlertDialog();
                        return;
                    }
                    SettingUtil.putInt(NetFragment.this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_SDK, i);
                    NetFragment.this.mSDKValue = i;
                    NetFragment.this.mGameModeNetworkAcceleration.setAcceleratedSDKName(NetFragment.this.mSDKValue);
                    NetFragment.this.handleAccelerationSDKStatusChange();
                }
            }
        }).create().show();
    }

    private void doCloseVPN() {
        LogUtil.d(TAG, "doCloseVPN");
        this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.10
            @Override // java.lang.Runnable
            public void run() {
                if (NetFragment.this.mINetworkAccelerationService != null) {
                    try {
                        NetFragment.this.mINetworkAccelerationService.doCloseVPN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAccelerationState() {
        int accelerationState;
        LogUtil.d(TAG, "getAccelerationState");
        INetworkAccelerationService iNetworkAccelerationService = this.mINetworkAccelerationService;
        if (iNetworkAccelerationService != null) {
            try {
                accelerationState = iNetworkAccelerationService.getAccelerationState();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message obtain = Message.obtain(this.mHandler, 0);
            obtain.obj = Integer.valueOf(accelerationState);
            this.mHandler.sendMessage(obtain);
        }
        accelerationState = 0;
        Message obtain2 = Message.obtain(this.mHandler, 0);
        obtain2.obj = Integer.valueOf(accelerationState);
        this.mHandler.sendMessage(obtain2);
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(NetFragment.class, R.drawable.network_settings, R.string.gcs_gamecenter_menu_network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNBAccountCode() {
        INetworkAccelerationService iNetworkAccelerationService = this.mINetworkAccelerationService;
        if (iNetworkAccelerationService != null) {
            try {
                iNetworkAccelerationService.loginNubiaAccount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAccelerationSDKStatusChange() {
        boolean z = this.mSDKValue != 0;
        NubiaTrackManager nubiaTrackManager = NubiaTrackManager.getInstance();
        String str = Util.TENCENT_APP_STORE;
        nubiaTrackManager.sendEvent("cn.nubia.gamelauncher", "gamespace_network_acceleration_switch", "switch_on", z ? this.mSDKValue == 2 ? Util.TENCENT_APP_STORE : "xunyou" : "off");
        if (this.mSDKValue == 1) {
            updateXunyouUserState();
        }
        updateNetworkAccelerationPreferences(z);
        if (this.mSDKValue != 0) {
            SettingUtil.setGameMode(this.mContext, 64, true);
            if (this.mSDKValue != 2) {
                str = "xunyou";
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_acceleration_option", "switch_status option", "on ".concat(str));
        } else {
            SettingUtil.setGameMode(this.mContext, 64, false);
            doCloseVPN();
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_acceleration_option", "switch_status", "off");
        }
        SettingUtil.setNetworkacc(this.mContext, z);
    }

    private void handleNetworkAcceleration() {
        if (NetAcceleratedConfig.checkIfSupportTencentAcceleration(this.mContext) || isNBAccountLogin()) {
            changeAccelerationSDK();
        } else if (isNetworkConnected(this.mContext)) {
            showAccountLoginAlertDialog();
        } else {
            Toast.makeText(this.mContext, R.string.gamemode_network_acceleration_network_error, 0).show();
        }
    }

    private void hideNavigationBar() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void init() {
        List<String> gameCenterNet = FeatureUtil.getGameCenterNet();
        boolean z = false;
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterNet.contains(str)) {
                removePreference(str);
            }
        }
        if (gameCenterNet.contains(KEY_GAME_MODE_ASSISTANT_SIM) && isSupportShieldAssistantSim()) {
            z = true;
        }
        this.supportShieldAssistantSim = z;
        if (!z || isNotSupportShieldSimBySocModel()) {
            removePreference(KEY_GAME_MODE_ASSISTANT_SIM);
        }
    }

    private void initAccelerationSDKStatus() {
        if (NetAcceleratedConfig.checkIfSupportTencentAcceleration(this.mContext) || isNBAccountLogin()) {
            refreshAccelerationSDKStatus();
        } else {
            this.mGameModeNetworkAcceleration.setAcceleratedSDKName(3);
        }
    }

    private void initAllPerferences() {
        this.mGameModeNetwork.setChecked(SettingUtil.getGameMode(this.mContext, 32));
        this.mGameModeChangeNetwork.setChecked(SettingUtil.getGameMode(this.mContext, 128));
        if (NetAcceleratedConfig.checkIfIsTencentAcceletion(this.mContext)) {
            return;
        }
        initAccelerationSDKStatus();
        updateXunyouUserState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initGameAssistantSimStatus() {
        boolean assistantSim = SettingUtil.getAssistantSim(this.mContext);
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.mContext.getApplicationContext()).getActiveSubscriptionInfoList();
        int checkSelfPermission = this.mContext.getApplicationContext().checkSelfPermission("android.permission.READ_PHONE_STATE");
        boolean isWifiConnected = isWifiConnected(this.mContext);
        if (activeSubscriptionInfoList != null) {
            LogUtil.i(TAG, "permission:" + checkSelfPermission + ", subInfos:" + activeSubscriptionInfoList.size() + ", wifi:" + isWifiConnected);
        }
        if (checkSelfPermission != 0 || activeSubscriptionInfoList == null || activeSubscriptionInfoList.size() <= 1 || isWifiConnected) {
            this.mGameModeAssistantSim.setEnabled(false);
            this.mGameModeAssistantSim.setChecked(false);
        } else {
            this.mGameModeAssistantSim.setEnabled(true);
            this.mGameModeAssistantSim.setChecked(assistantSim);
        }
    }

    private void initNetworkAccelerationEnableState() {
        boolean isAppExist = Utils.isAppExist(this.mContext, GAME_NETWORKACCELERATION_PACKAGE_NAME);
        this.mGameModeNetworkAccelerationDetails.setEnabled(isAppExist);
        this.mGameModeNetworkAcceleration.setEnabled(isAppExist);
        this.mGameModeNetworkAccelerationApp.setEnabled(isAppExist);
    }

    private void initNetworkAccelerationPreference() {
        this.mSDKValue = NetAcceleratedConfig.getNetAcceleratedSDKType(this.mContext);
        this.mGameModeNetworkAccelerationTxPrompt.setChecked(SettingUtil.getBoolean(this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_TX_PROMPT, true));
        this.mGameModeNetworkAccelerationTxNetInfo.setChecked(SettingUtil.getBoolean(this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_TX_NET_INFO, true));
        this.mGameModeNetworkAccelerationTxPrompt.setOnPreferenceChangeListener(this);
        this.mGameModeNetworkAccelerationTxNetInfo.setOnPreferenceChangeListener(this);
        if (isFirstOpenNetworkAcceleration()) {
            SettingUtil.setGameMode(this.mContext, 64, true);
        }
        if (!NetAcceleratedConfig.checkIfIsOpenNetworkAcceletion(this.mContext)) {
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_PROMPT);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_INFO);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_DETAIL);
            this.mGameModeNetworkAcceleration.setAcceleratedSDKName(0);
            return;
        }
        if (NetAcceleratedConfig.checkIfIsTencentAcceletion(this.mContext)) {
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
            this.mGameModeNetworkAcceleration.setAcceleratedSDKName(2);
        } else {
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_PROMPT);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_INFO);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_DETAIL);
            this.mGameModeNetworkAcceleration.setAcceleratedSDKName(1);
        }
    }

    private void initNetworkAccelerationPreferences(boolean z) {
        removePreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
        removePreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
        if (z) {
            return;
        }
        removePreference(KEY_GAME_MODE_NETWORKACCELERATION);
    }

    private boolean isDarkTheme() {
        return (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private boolean isFirstOpenNetworkAcceleration() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("networkacceleration", 0);
        if (sharedPreferences == null || sharedPreferences.getInt("is_first", 1) != 1 || SettingUtil.getNetworkacc(this.mContext) != -1) {
            return false;
        }
        SettingUtil.setNetworkacc(this.mContext, true);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("is_first", 0);
        edit.apply();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNBAccountLogin() {
        boolean isAccountLogined;
        INetworkAccelerationService iNetworkAccelerationService = this.mINetworkAccelerationService;
        if (iNetworkAccelerationService != null) {
            try {
                isAccountLogined = iNetworkAccelerationService.isAccountLogined();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtil.d(TAG, "isLogin:=" + isAccountLogined);
            return isAccountLogined;
        }
        isAccountLogined = false;
        LogUtil.d(TAG, "isLogin:=" + isAccountLogined);
        return isAccountLogined;
    }

    private boolean isNotSupportShieldSimBySocModel() {
        return Build.SOC_MODEL.equals("SM8750") || Build.SOC_MODEL.equals("SM8850");
    }

    private boolean isShowGameAssistantSimDialog() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("game_mode_assistant_sim", 0);
        return sharedPreferences != null && sharedPreferences.getInt("is_show", 0) == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isSupportShieldAssistantSim() {
        /*
            r4 = this;
            java.lang.String r0 = "phoneCount:"
            r1 = 0
            android.content.Context r4 = r4.mContext     // Catch: java.lang.Exception -> L28
            java.lang.String r2 = "phone"
            java.lang.Object r4 = r4.getSystemService(r2)     // Catch: java.lang.Exception -> L28
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch: java.lang.Exception -> L28
            int r4 = r4.getPhoneCount()     // Catch: java.lang.Exception -> L28
            java.lang.String r2 = "NetFragment"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L26
            r3.<init>(r0)     // Catch: java.lang.Exception -> L26
            java.lang.StringBuilder r0 = r3.append(r4)     // Catch: java.lang.Exception -> L26
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L26
            cn.nubia.gamecenter.settings.utils.LogUtil.i(r2, r0)     // Catch: java.lang.Exception -> L26
            goto L2d
        L26:
            r0 = move-exception
            goto L2a
        L28:
            r0 = move-exception
            r4 = r1
        L2a:
            r0.printStackTrace()
        L2d:
            r0 = 2
            if (r4 != r0) goto L31
            r1 = 1
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.net.NetFragment.isSupportShieldAssistantSim():boolean");
    }

    private boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(1);
        }
        return false;
    }

    static /* synthetic */ void lambda$showGameAssistantSimDialog$0(ImageView imageView, View view) {
        imageView.setSelected(!imageView.isSelected());
        imageView.setImageResource(imageView.isSelected() ? R.drawable.gcs_checkbox_checked : R.drawable.gcs_checkbox_uncheck);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryXunyouUserState(String str, String str2) {
        LogUtil.d(TAG, "queryXunyouUserState mINetworkAccelerationService is null:" + (this.mINetworkAccelerationService == null));
        INetworkAccelerationService iNetworkAccelerationService = this.mINetworkAccelerationService;
        if (iNetworkAccelerationService != null) {
            try {
                iNetworkAccelerationService.queryXunyouUserState(str, str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshAccelerationSDKStatus() {
        if (SettingUtil.getGameMode(this.mContext, 64)) {
            this.mSDKValue = NetAcceleratedConfig.getNetAcceleratedSDKType(this.mContext);
        } else {
            this.mSDKValue = 0;
        }
        this.mGameModeNetworkAcceleration.setAcceleratedSDKName(this.mSDKValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshNetworkAccelerationPreferences(int i) {
        LogUtil.i(TAG, "userState:=" + i + ", mLastUserState:=" + this.mLastUserState);
        if (isNBAccountLogin()) {
            refreshAccelerationSDKStatus();
        }
        this.mLastUserState = i;
        int i2 = R.string.gamemode_network_acceleration_trial;
        boolean z = false;
        switch (i) {
            case 0:
                i2 = R.string.gamemode_network_acceleration_trial;
                break;
            case 1:
                i2 = R.string.gamemode_network_acceleration_trial;
                z = true;
                break;
            case 2:
            case 4:
            case 6:
                i2 = R.string.gamemode_network_acceleration_in_use;
                z = true;
                break;
            case 3:
            case 5:
                i2 = R.string.gamemode_network_acceleration_expired;
                z = true;
                break;
        }
        if (!z) {
            doCloseVPN();
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
            return;
        }
        if (isFirstOpenNetworkAcceleration()) {
            SettingUtil.setGameMode(this.mContext, 64, true);
            refreshAccelerationSDKStatus();
            updateNetworkAccelerationPreferences(true);
        } else {
            boolean gameMode = SettingUtil.getGameMode(this.mContext, 64);
            refreshAccelerationSDKStatus();
            updateNetworkAccelerationPreferences(gameMode);
        }
        GameCenterNetAccelerationDetailsPreference gameCenterNetAccelerationDetailsPreference = this.mGameModeNetworkAccelerationDetails;
        if (gameCenterNetAccelerationDetailsPreference != null) {
            gameCenterNetAccelerationDetailsPreference.setTitleResId(R.string.gamemode_network_acceleration_details_title);
            this.mGameModeNetworkAccelerationDetails.setSummaryResId(-1);
            this.mGameModeNetworkAccelerationDetails.setStatusResId(i2);
        }
    }

    private void registerAssistantSimReceive() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROAD_CAST_WIFI_STATE_CHANGED);
        intentFilter.addAction(BROAD_CAST_SIM_STATE_CHANGED);
        this.mContext.registerReceiver(this.mAssistantSimReceiver, intentFilter, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCallback() {
        INetworkAccelerationService iNetworkAccelerationService = this.mINetworkAccelerationService;
        if (iNetworkAccelerationService != null) {
            try {
                iNetworkAccelerationService.registerCallback(this.mINACallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setFirstOpenNetworkAcceleration() {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences("networkacceleration", 0).edit();
        edit.putInt("is_first", 1);
        edit.apply();
    }

    private void setNetworkAccelerationState() {
        this.mIsSupportNetworkAcc = Utils.isAppExist(this.mContext, GAME_NETWORKACCELERATION_PACKAGE_NAME) && !Utils.isInternal(this.mContext);
        LogUtil.i(TAG, "CWL mIsSupportNetworkAcc = " + this.mIsSupportNetworkAcc);
        if (this.mIsSupportNetworkAcc) {
            bindNetworkAccelerationService();
        }
        initNetworkAccelerationPreferences(this.mIsSupportNetworkAcc);
        initNetworkAccelerationEnableState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAccountLoginAlertDialog() {
        new AlertDialogCenter.Builder(getContext(), R.style.Theme_Nubia_Dialog_Alert).setTitle(Utils.isZte(this.mContext) ? R.string.gamemode_account_login_zte : R.string.gamemode_account_login).setPositiveButton(R.string.gamemode_account_login_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NetFragment.this.startNetworkAccelerationActivity();
            }
        }).setNegativeButton(R.string.gamemode_account_login_cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    private void showGameAssistantSimDialog() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.gcs_game_network_assistant_sim_dialog, (ViewGroup) null);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.nubia_game_assistant_sim_checkbox);
        inflate.findViewById(R.id.nubia_game_assistant_sim_dialog_selected).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NetFragment.lambda$showGameAssistantSimDialog$0(imageView, view);
            }
        });
        new AlertDialog.Builder(getContext(), R.style.Theme_Nubia_Dialog_Alert).setTitle(R.string.gcs_game_network_assistant_sim_dialog_title).setView(inflate).setNegativeButton(R.string.gcs_game_network_assistant_sim_dialog_cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.gcs_game_network_assistant_sim_dialog_confirm, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NetFragment.this.updateShowGameAssistantSimDialogFlag(imageView.isSelected() ? 1 : 0);
                NetFragment.this.mGameModeAssistantSim.setChecked(true);
                SettingUtil.setAssistantSim(NetFragment.this.mContext, true);
                OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "gamespace_shield_secondary_card_statue", "switch_status", "on");
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    private void showPermission() {
        if (this.mContext.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            requestPermissions(new String[]{"android.permission.READ_PHONE_STATE"}, REQUEST_CODE_PHONE_STATE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTxAcceleratedLoadAlertDialog() {
        new AlertDialogCenter.Builder(getContext(), R.style.Theme_Nubia_Dialog_Alert).setTitle(R.string.gamemode_network_acceleration_dialog_download_title).setMessage(R.string.gamemode_network_acceleration_dialog_download_message).setPositiveButton(R.string.gcs_game_network_assistant_sim_dialog_confirm, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NetAcceleratedConfig.routeToAppMarket(NetFragment.this.mContext);
            }
        }).setNegativeButton(R.string.gamemode_account_login_cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    public static void startManagerAppActivity(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, ManagerAcceleratedAppActivity.class.getName());
        intent.putExtra("game_support_list", 1);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNetworkAccelerationActivity() {
        startActivity(new Intent("cn.nubia.action.LANDSCAPE_GAME_NETWORK_ACCELERATION"));
    }

    private void updateNetworkAccelerationPreferences(boolean z) {
        if (!z) {
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_PROMPT);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_INFO);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_DETAIL);
            return;
        }
        int i = this.mSDKValue;
        if (i == 1) {
            getPreferenceScreen().addPreference(this.mGameModeNetworkAccelerationDetails);
            getPreferenceScreen().addPreference(this.mGameModeNetworkAccelerationApp);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_PROMPT);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_INFO);
            removePreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_DETAIL);
            return;
        }
        if (i != 2) {
            LogUtil.e(TAG, "updateNetworkAccelerationPreferences, is error state !!!!");
            return;
        }
        getPreferenceScreen().addPreference(this.mGameModeNetworkAccelerationTxNetInfo);
        getPreferenceScreen().addPreference(this.mGameModeNetworkAccelerationTxNetDetail);
        getPreferenceScreen().addPreference(this.mGameModeNetworkAccelerationTxPrompt);
        removePreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
        removePreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShowGameAssistantSimDialogFlag(int i) {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences("game_mode_assistant_sim", 0).edit();
        edit.putInt("is_show", i);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateXunyouUserState() {
        if (!this.mIsSupportNetworkAcc || this.mINetworkAccelerationService == null) {
            return;
        }
        LogUtil.d(TAG, "updateXunyouUserState");
        doCloseVPN();
        this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.4
            @Override // java.lang.Runnable
            public void run() {
                if (NetFragment.this.isNBAccountLogin()) {
                    NetFragment.this.getAccelerationState();
                    String string = SettingUtil.getString(NetFragment.this.mContext, "nubia_accesstoken");
                    LogUtil.d(NetFragment.TAG, "token:=" + string);
                    if (string == null || string.equals("-1")) {
                        NetFragment.this.getNBAccountCode();
                    } else {
                        NetFragment.this.queryXunyouUserState(string, null);
                    }
                }
            }
        });
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.m_tag;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public int getMetricsCategory() {
        return 6;
    }

    public boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public void onAccessTokenExpired() {
        if (this.mIsVisible) {
            LogUtil.d(TAG, "onAccessTokenExpired");
            this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.8
                @Override // java.lang.Runnable
                public void run() {
                    NetFragment.this.getNBAccountCode();
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mDashboard = (RecyclerView) getView().findViewById(R.id.recycler_view);
        this.mDashboard.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        this.mDashboard.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        this.mIsVisible = true;
        addPreferencesFromResource(R.xml.gcs_net_settings);
        this.mGameModeNetwork = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_NETWORK);
        this.mGameModeChangeNetwork = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_CNANGE_NETWORK);
        this.mGameModeAssistantSim = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_ASSISTANT_SIM);
        this.mGameModeNetworkAcceleration = (GameCenterNetAccelerationPreference) findPreference(KEY_GAME_MODE_NETWORKACCELERATION);
        this.mGameModeNetworkAccelerationDetails = (GameCenterNetAccelerationDetailsPreference) findPreference(KEY_GAME_MODE_NETWORKACCELERATION_DETAILS);
        this.mGameModeNetworkAccelerationApp = (GameCenterNetAccelerationAppPreference) findPreference(KEY_GAME_MODE_NETWORKACCELERATION_APP);
        this.mGameModeNetworkAccelerationTxPrompt = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_PROMPT);
        this.mGameModeNetworkAccelerationTxNetInfo = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_INFO);
        this.mGameModeNetworkAccelerationTxNetDetail = (GameCenterPreference) findPreference(KEY_GAME_MODE_NETWORKACCELERATION_TX_NETWORK_DETAIL);
        this.mGameModeNetwork.setOnPreferenceChangeListener(this);
        this.mGameModeChangeNetwork.setOnPreferenceChangeListener(this);
        this.mGameModeAssistantSim.setOnPreferenceChangeListener(this);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mWorkHandlerThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mWorkHandlerThread.getLooper());
        setNetworkAccelerationState();
        init();
        if (this.supportShieldAssistantSim) {
            showPermission();
            initGameAssistantSimStatus();
        }
        initNetworkAccelerationPreference();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        HandlerThread handlerThread = this.mWorkHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        INetworkAccelerationService iNetworkAccelerationService = this.mINetworkAccelerationService;
        if (iNetworkAccelerationService != null) {
            try {
                iNetworkAccelerationService.unregisterCallback(this.mINACallback);
            } catch (Exception unused) {
            }
            this.mContext.unbindService(this.mServiceConnection);
        }
        super.onDestroy();
    }

    public void onNBAccountLoginError(String str) {
        if (this.mIsVisible) {
            LogUtil.i(TAG, "onNBAccountLoginError errorType:=" + str);
            this.mHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    NetFragment.this.refreshNetworkAccelerationPreferences(0);
                }
            });
        }
    }

    public void onNBAccountLoginSuccess(final String str) {
        if (this.mIsVisible) {
            LogUtil.d(TAG, "onNBAccountLoginSuccess code:=" + str);
            this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    NetFragment.this.queryXunyouUserState(null, str);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mIsVisible = false;
        if (this.supportShieldAssistantSim) {
            this.mContext.unregisterReceiver(this.mAssistantSimReceiver);
        }
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference == this.mGameModeNetwork) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_background_speed_limit_switch", "switch_on", booleanValue);
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_network_protection_status", "switch_status", booleanValue ? "on" : "off");
            SettingUtil.setGameMode(this.mContext, 32, booleanValue);
            this.mGameModeNetwork.setChecked(booleanValue);
        } else if (switchPreference == this.mGameModeChangeNetwork) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_netchange_protection_switch", "switch_on", booleanValue);
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_network_changing_protection_status", "switch_status", booleanValue ? "on" : "off");
            SettingUtil.setGameMode(this.mContext, 128, booleanValue);
            this.mGameModeChangeNetwork.setChecked(booleanValue);
        } else {
            GameCenterSwitchPreference gameCenterSwitchPreference = this.mGameModeAssistantSim;
            if (switchPreference != gameCenterSwitchPreference) {
                GameCenterSwitchPreference gameCenterSwitchPreference2 = this.mGameModeNetworkAccelerationTxPrompt;
                if (switchPreference == gameCenterSwitchPreference2) {
                    gameCenterSwitchPreference2.setChecked(booleanValue);
                    SettingUtil.putBoolean(this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_TX_PROMPT, booleanValue);
                } else {
                    GameCenterSwitchPreference gameCenterSwitchPreference3 = this.mGameModeNetworkAccelerationTxNetInfo;
                    if (switchPreference == gameCenterSwitchPreference3) {
                        gameCenterSwitchPreference3.setChecked(booleanValue);
                        SettingUtil.putBoolean(this.mContext, NetAcceleratedConfig.DB_NET_ACCELERATED_TX_NET_INFO, booleanValue);
                    }
                }
            } else if (!booleanValue) {
                gameCenterSwitchPreference.setChecked(booleanValue);
                SettingUtil.setAssistantSim(this.mContext, false);
                OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "gamespace_shield_secondary_card_statue", "switch_status", "off");
            } else if (isShowGameAssistantSimDialog()) {
                showGameAssistantSimDialog();
            } else {
                this.mGameModeAssistantSim.setChecked(booleanValue);
                SettingUtil.setAssistantSim(this.mContext, true);
                OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "gamespace_shield_secondary_card_statue", "switch_status", "on");
            }
        }
        return false;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == this.mGameModeNetworkAccelerationDetails) {
            startNetworkAccelerationActivity();
            return true;
        }
        if (preference == this.mGameModeNetworkAccelerationApp) {
            startManagerAppActivity(getActivity());
            return true;
        }
        if (preference == this.mGameModeNetworkAcceleration) {
            handleNetworkAcceleration();
            return true;
        }
        if (preference != this.mGameModeNetworkAccelerationTxNetDetail) {
            return super.onPreferenceTreeClick(preference);
        }
        new Intent();
        startActivity(this.mContext.getPackageManager().getLaunchIntentForPackage(NetAcceleratedConfig.Tencent_Acceleration_Package));
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == REQUEST_CODE_PHONE_STATE && iArr.length > 0 && iArr[0] == 0) {
            initGameAssistantSimStatus();
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mIsVisible = true;
        initAllPerferences();
        hideNavigationBar();
        if (this.supportShieldAssistantSim) {
            registerAssistantSimReceive();
        }
    }

    public void onXunyouUserState(final int i) throws RemoteException {
        if (this.mIsVisible) {
            LogUtil.i(TAG, "onXunyouUserState userState:=" + i);
            this.mHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.net.NetFragment.7
                @Override // java.lang.Runnable
                public void run() {
                    NetFragment.this.refreshNetworkAccelerationPreferences(i);
                }
            });
        }
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
