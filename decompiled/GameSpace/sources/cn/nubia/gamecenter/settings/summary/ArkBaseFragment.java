package cn.nubia.gamecenter.settings.summary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.nubia.arkbase.nbaccount.INbAccountLogin;
import cn.nubia.arkbase.nbaccount.INbAccountLoginCallback;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AutoNextLineLinearlayout;
import cn.nubia.gamecenter.settings.summary.ArkBaseRecordAdapter;
import cn.nubia.gamecenter.settings.summary.entities.AccountLabel;
import cn.nubia.gamecenter.settings.summary.entities.GameRecord;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.settings.trackclient.Track;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ArkBaseFragment extends BaseFragment implements FragmentInterface, View.OnClickListener, ArkBaseRecordAdapter.OnIconClickListener, View.OnLayoutChangeListener {
    private static final String ACCOUNT_INFO_AVATAR = "avatar";
    private static final String ACCOUNT_INFO_NICKNAME = "nickname";
    private static final String ACCOUNT_INFO_OPENID = "open_id";
    private static final String ACCOUNT_LOGIN_TRANSIT_ACTIVITY_ACTION = "cn.nubia.action.LANDSCAPE_ARKBASE_LOGIN_NBACCOUNT";
    private static final String ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME = "cn.nubia.arkbase.service.NbAccountLoginService";
    private static final String ARK_BASE_PACKAGE_NAME = "cn.nubia.arkbase";
    private static final int MSG_UPDATE_VIEW = 0;
    private static final String RXTERN_ADDR_MARKET_NEOGAMECENTER = "market://details?id=cn.nubia.neogamecenter&startDownload=false";
    private static final String TAG = "ArkBaseFragment";
    private TextView mAccountLabelNone;
    private AutoNextLineLinearlayout mAccountLabelPanel;
    private View mAccountLabelSpace;
    private ArkBaseLabel mAccountLabel_chuanqi;
    private ArkBaseLabel mAccountLabel_gandi;
    private ArkBaseLabel mAccountLabel_hualao;
    private ArkBaseLabel mAccountLabel_jingying;
    private ArkBaseLabel mAccountLabel_mingxing;
    private ArkBaseLabel mAccountLabel_moyu;
    private ArkBaseLabel mAccountLabel_shouhuzhe;
    private ArkBaseLabel mAccountLabel_toutu;
    private ArkBaseLabel mAccountLabel_youxia;
    private TextView mAccountNickName;
    private ImageView mAccountPhoto;
    private Bitmap mAvatarBitmap;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;
    private View mGameRecords;
    private View mLayoutView;
    private TextView mLoginBtn;
    private View mLogined;
    private INbAccountLogin mNbAccountLoginImpl;
    private View mNetWorkSet;
    private View mNoNetWork;
    private View mNotLogin;
    private PackageManager mPkgManager;
    private ArkBaseRecordAdapter mRecordAdapter;
    private View mRecordFirstdivider;
    private View mRecordHelper;
    private ListView mRecordList;
    private TextView mRecordTotalTime;
    private boolean mResumed;
    private List<AccountLabel> mAccountLabelInfos = new ArrayList();
    private int mAccountLabelLayoutRow = 0;
    private Map<String, String> mAccountInfo = new HashMap();
    private List<GameRecord> mRecords = new ArrayList();
    private Object mObject = new Object();
    private final ServiceConnection mArkBaseServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.i(ArkBaseFragment.TAG, "onServiceConnected");
            try {
                ArkBaseFragment.this.mNbAccountLoginImpl = INbAccountLogin.Stub.asInterface(iBinder);
                ArkBaseFragment.this.mNbAccountLoginImpl.registerCallback(ArkBaseFragment.this.mNbAccountLoginCb);
                ArkBaseFragment.this.sendMsgUpdateView();
                if (ArkBaseFragment.this.isNBAccountLogin()) {
                    ArkBaseFragment.this.loadData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.i(ArkBaseFragment.TAG, "onServiceDisconnected");
            ArkBaseFragment.this.mNbAccountLoginImpl = null;
        }
    };
    private final NbAccountLoginCallback mNbAccountLoginCb = new NbAccountLoginCallback(this);
    private Handler mHandler = new Handler() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            ArkBaseFragment.this.updateView();
        }
    };

    private static class NbAccountLoginCallback extends INbAccountLoginCallback.Stub {
        private final WeakReference<ArkBaseFragment> mArkBaseFragment;

        public NbAccountLoginCallback(ArkBaseFragment arkBaseFragment) {
            this.mArkBaseFragment = new WeakReference<>(arkBaseFragment);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onAccountInfo(Map map, Bitmap bitmap) {
            LogUtil.i(ArkBaseFragment.TAG, "onAccountInfo, accountInfo = " + map);
            if (this.mArkBaseFragment.get() != null) {
                this.mArkBaseFragment.get().onAccountInfo(map, bitmap);
            }
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onAccountLabel(List list) {
            LogUtil.i(ArkBaseFragment.TAG, "onAccountLabel, labelList = " + list);
            if (this.mArkBaseFragment.get() != null) {
                this.mArkBaseFragment.get().onAccountLabel(list);
            }
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onError(String str) {
            LogUtil.e(ArkBaseFragment.TAG, "onError errorType:=" + str);
            if (this.mArkBaseFragment.get() != null) {
                this.mArkBaseFragment.get().onError(str);
            }
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameHighLights(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameNotes(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGamePowers(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameRecords(List list) {
            LogUtil.i(ArkBaseFragment.TAG, "onGameRecords, recordList = " + list.size());
            if (this.mArkBaseFragment.get() != null) {
                this.mArkBaseFragment.get().onGameRecords(list);
            }
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameScores(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onSuccess(String str, Map map, Bitmap bitmap) {
            LogUtil.i(ArkBaseFragment.TAG, "onSuccess");
            if (this.mArkBaseFragment.get() != null) {
                this.mArkBaseFragment.get().onSuccess(str, map, bitmap);
            }
        }
    }

    private void bindArkBaseService() {
        LogUtil.i(TAG, "bindArkBaseService");
        if (this.mContext == null) {
            LogUtil.e(TAG, "bindArkBaseService, ignore !");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.arkbase", ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME);
        this.mContext.bindService(intent, this.mArkBaseServiceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downLoadNeoGameCenter() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(RXTERN_ADDR_MARKET_NEOGAMECENTER));
        intent.addFlags(268435456);
        intent.addFlags(32768);
        intent.addFlags(536870912);
        this.mContext.startActivity(intent);
    }

    private List<GameRecord> gameRecords2List(List<Map<String, String>> list) {
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> map : list) {
            GameRecord gameRecord = new GameRecord();
            try {
                gameRecord.icon = this.mPkgManager.getApplicationIcon(this.mPkgManager.getApplicationInfo(map.get("package_name"), 0));
            } catch (PackageManager.NameNotFoundException unused) {
                gameRecord.icon = this.mContext.getDrawable(R.drawable.arkbase_uninstall_record_icon);
            }
            gameRecord.label = map.get("label");
            gameRecord.pkgName = map.get("package_name");
            gameRecord.totalTimeInForeground = Long.parseLong(map.get("game_time"));
            gameRecord.launchIntent = this.mContext.getApplicationContext().getPackageManager().getLaunchIntentForPackage(map.get("package_name"));
            gameRecord.totalTimeAllGames = Long.parseLong(map.get("all_game_time"));
            arrayList.add(gameRecord);
        }
        return arrayList;
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(ArkBaseFragment.class, R.drawable.ark_base_menu, R.string.gcs_gamecenter_menu_arkbase);
    }

    private void initData() {
        String string = Settings.Global.getString(this.mContext.getContentResolver(), "ark_base_open_id");
        String cacheOpenId = ArkBaseHelper.getCacheOpenId();
        if (TextUtils.isEmpty(cacheOpenId) || !cacheOpenId.equals(string)) {
            LogUtil.i(TAG, "cache is null !");
            return;
        }
        this.mAccountInfo = ArkBaseHelper.getCacheAccountInfo();
        this.mAccountLabelInfos = ArkBaseHelper.getCacheAccountLabels();
        this.mRecords = ArkBaseHelper.getCacheRecords();
    }

    private void initView(View view) {
        this.mLayoutView = view.findViewById(R.id.arkbase_anim_layout_id);
        this.mNoNetWork = view.findViewById(R.id.no_network_id);
        View findViewById = view.findViewById(R.id.network_set_id);
        this.mNetWorkSet = findViewById;
        findViewById.setOnClickListener(this);
        this.mNotLogin = view.findViewById(R.id.arkbase_not_login_id);
        this.mLogined = view.findViewById(R.id.arkbase_logined_id);
        TextView textView = (TextView) view.findViewById(R.id.login_id);
        this.mLoginBtn = textView;
        textView.setOnClickListener(this);
        this.mAccountPhoto = (ImageView) view.findViewById(R.id.arkbase_photo_id);
        this.mAccountNickName = (TextView) view.findViewById(R.id.nick_name_id);
        this.mAccountLabelNone = (TextView) view.findViewById(R.id.arkbase_account_label_none);
        this.mAccountLabelSpace = view.findViewById(R.id.space_id);
        AutoNextLineLinearlayout autoNextLineLinearlayout = (AutoNextLineLinearlayout) view.findViewById(R.id.arkbase_account_label_id);
        this.mAccountLabelPanel = autoNextLineLinearlayout;
        autoNextLineLinearlayout.addOnLayoutChangeListener(this);
        this.mAccountLabel_toutu = (ArkBaseLabel) view.findViewById(R.id.account_label_a);
        this.mAccountLabel_gandi = (ArkBaseLabel) view.findViewById(R.id.account_label_b);
        this.mAccountLabel_youxia = (ArkBaseLabel) view.findViewById(R.id.account_label_c);
        this.mAccountLabel_moyu = (ArkBaseLabel) view.findViewById(R.id.account_label_d);
        this.mAccountLabel_mingxing = (ArkBaseLabel) view.findViewById(R.id.account_label_e);
        this.mAccountLabel_hualao = (ArkBaseLabel) view.findViewById(R.id.account_label_f);
        this.mAccountLabel_jingying = (ArkBaseLabel) view.findViewById(R.id.account_label_g);
        this.mAccountLabel_chuanqi = (ArkBaseLabel) view.findViewById(R.id.account_label_h);
        this.mAccountLabel_shouhuzhe = (ArkBaseLabel) view.findViewById(R.id.account_label_i);
        this.mGameRecords = view.findViewById(R.id.arkbase_game_record_id);
        this.mRecordTotalTime = (TextView) view.findViewById(R.id.arkbase_game_total_time_id);
        this.mRecordList = (ListView) view.findViewById(R.id.arkbase_game_list_id);
        this.mRecordFirstdivider = view.findViewById(R.id.first_divider_id);
        View findViewById2 = view.findViewById(R.id.arkbase_record_help_id);
        this.mRecordHelper = findViewById2;
        findViewById2.setOnClickListener(this);
        ArkBaseRecordAdapter arkBaseRecordAdapter = new ArkBaseRecordAdapter(this.mContext);
        this.mRecordAdapter = arkBaseRecordAdapter;
        this.mRecordList.setAdapter((ListAdapter) arkBaseRecordAdapter);
        this.mRecordAdapter.setOnIconClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNBAccountLogin() {
        boolean isAccountLogined;
        INbAccountLogin iNbAccountLogin = this.mNbAccountLoginImpl;
        if (iNbAccountLogin != null) {
            try {
                isAccountLogined = iNbAccountLogin.isAccountLogined();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtil.i(TAG, "isLogin:=" + isAccountLogined + ", mNbAccountLoginImpl = " + this.mNbAccountLoginImpl);
            return isAccountLogined;
        }
        isAccountLogined = false;
        LogUtil.i(TAG, "isLogin:=" + isAccountLogined + ", mNbAccountLoginImpl = " + this.mNbAccountLoginImpl);
        return isAccountLogined;
    }

    private boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    private List<AccountLabel> labelInfos2List(List<Map<String, String>> list) {
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> map : list) {
            AccountLabel accountLabel = new AccountLabel();
            accountLabel.tagId = Integer.parseInt(map.get("tag_id"));
            accountLabel.tagName = map.get("tag_name");
            accountLabel.status = Integer.parseInt(map.get("status"));
            accountLabel.tagDesc = map.get("tag_desc");
            arrayList.add(accountLabel);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        try {
            INbAccountLogin iNbAccountLogin = this.mNbAccountLoginImpl;
            if (iNbAccountLogin != null) {
                iNbAccountLogin.loadAccountInfo();
                this.mNbAccountLoginImpl.loadAccountLabel();
                this.mNbAccountLoginImpl.loadGameRecords();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgUpdateView() {
        if (this.mHandler.hasMessages(0)) {
            this.mHandler.removeMessages(0);
        }
        this.mHandler.sendEmptyMessage(0);
    }

    private void showGameCenterNotFoundDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert);
        builder.setTitle(this.mContext.getString(R.string.arkbase_gamecenter_not_fonund_dialog_text)).setPositiveButton(R.string.gcs_game_network_assistant_sim_dialog_confirm, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ArkBaseFragment.this.downLoadNeoGameCenter();
            }
        }).setNegativeButton(R.string.gcs_game_network_assistant_sim_dialog_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        builder.show();
    }

    private void showGameRecordAlertDialog() {
        if (this.mContext == null) {
            LogUtil.e(TAG, "showGameRecordAlertDialog, ignore !");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert);
        builder.setTitle(this.mContext.getString(R.string.arkbase_record_dialog_title));
        builder.setMessage(this.mContext.getString(R.string.arkbase_record_dialog_msg));
        builder.setNegativeButton(this.mContext.getString(R.string.arkbase_record_dialog_btn), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog create = builder.create();
        create.getWindow().setType(2038);
        create.show();
    }

    private void showRecordDeletedAlertDialog() {
        if (this.mContext == null) {
            LogUtil.e(TAG, "showGameRecordAlertDialog, ignore !");
            return;
        }
        if (!GameSpaceConfig.supportRelevant()) {
            Toast.makeText(this.mContext, R.string.arkbase_record_deleted_dialog_title, 0).show();
            LogUtil.e(TAG, "not support game recommendation");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert);
        builder.setTitle(this.mContext.getString(R.string.arkbase_record_deleted_dialog_title));
        builder.setMessage(this.mContext.getString(R.string.arkbase_record_deleted_dialog_msg));
        builder.setNegativeButton(this.mContext.getString(R.string.arkbase_record_deleted_dialog_cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(this.mContext.getString(R.string.arkbase_record_deleted_dialog_download), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ArkBaseFragment.this.startGameRecommend();
            }
        });
        AlertDialog create = builder.create();
        create.getWindow().setType(2038);
        create.show();
    }

    private void startArkBaseTransitActivity() {
        if (this.mContext == null) {
            return;
        }
        try {
            this.mContext.startActivity(new Intent(ACCOUNT_LOGIN_TRANSIT_ACTIVITY_ACTION));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGameRecommend() {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.addFlags(32768);
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("gameplacesdk://home"));
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog();
        }
    }

    private void startNetworkSet() {
        if (this.mContext != null) {
            Intent intent = new Intent();
            intent.setClassName("com.android.settings", "com.android.settings.Settings");
            this.mContext.startActivity(intent);
        }
    }

    private void updateAccountLabel() {
        LogUtil.i(TAG, "updateAccountLabel, mAccountLabelInfos.size()  = " + this.mAccountLabelInfos.size());
        List<AccountLabel> list = this.mAccountLabelInfos;
        if (list == null || list.size() <= 0) {
            this.mAccountLabelNone.setVisibility(0);
            return;
        }
        this.mAccountLabelNone.setVisibility(8);
        for (AccountLabel accountLabel : this.mAccountLabelInfos) {
            switch (accountLabel.tagId) {
                case 1:
                    updateLabel(this.mAccountLabel_moyu, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 2:
                    updateLabel(this.mAccountLabel_toutu, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 3:
                    updateLabel(this.mAccountLabel_youxia, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 4:
                    updateLabel(this.mAccountLabel_jingying, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 5:
                    updateLabel(this.mAccountLabel_gandi, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 6:
                    updateLabel(this.mAccountLabel_chuanqi, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 7:
                    updateLabel(this.mAccountLabel_shouhuzhe, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 8:
                    updateLabel(this.mAccountLabel_mingxing, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                case 9:
                    updateLabel(this.mAccountLabel_hualao, accountLabel.tagName, accountLabel.tagDesc, accountLabel.status);
                    break;
                default:
                    LogUtil.i(TAG, "updateAccountLabel, error tagId : " + accountLabel.tagId);
                    break;
            }
        }
        this.mAccountLabelPanel.requestLayout();
    }

    private void updateAccountView() {
        Bitmap bitmap = this.mAvatarBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            this.mAccountPhoto.setImageDrawable(this.mContext.getDrawable(R.drawable.arkbase_default_photo));
        } else {
            this.mAccountPhoto.setImageDrawable(ArkBaseHelper.toRoundDrawable(this.mAvatarBitmap, this.mContext));
        }
        String str = this.mAccountInfo.get(ACCOUNT_INFO_NICKNAME);
        if (TextUtils.isEmpty(str)) {
            LogUtil.i(TAG, "updateAccountView, nickname is null !");
        } else {
            this.mAccountNickName.setText(str);
        }
    }

    private void updateLabel(ArkBaseLabel arkBaseLabel, String str, String str2, int i) {
        if (arkBaseLabel != null) {
            arkBaseLabel.setLabelText(str);
            arkBaseLabel.setLabelDescriptionText(str2);
            arkBaseLabel.setLabelMeetAllConditions(2 == i);
            arkBaseLabel.setVisibility(i <= 0 ? 8 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateView() {
        if (!isNetworkConnected()) {
            this.mNotLogin.setVisibility(8);
            this.mLogined.setVisibility(8);
            this.mGameRecords.setVisibility(8);
            this.mNoNetWork.setVisibility(0);
            return;
        }
        boolean isNBAccountLogin = isNBAccountLogin();
        if (isNBAccountLogin) {
            updateAccountView();
            updateAccountLabel();
            String format = String.format(this.mContext.getString(R.string.arkbase_record_total_time, "< 1"), new Object[0]);
            List<GameRecord> list = this.mRecords;
            if (list == null || list.size() <= 0) {
                this.mRecordFirstdivider.setVisibility(8);
            } else {
                int millisToHourRoundedUp = ArkBaseHelper.millisToHourRoundedUp(this.mRecords.get(0).totalTimeAllGames);
                int millisToHour = ArkBaseHelper.millisToHour(this.mRecords.get(0).totalTimeAllGames);
                if (millisToHourRoundedUp >= 1 && millisToHour > 0) {
                    format = String.format(this.mContext.getString(R.string.arkbase_record_total_time, millisToHourRoundedUp + ""), new Object[0]);
                }
                this.mRecordFirstdivider.setVisibility(0);
            }
            this.mRecordTotalTime.setText(format);
            this.mRecordAdapter.setData(this.mRecords);
        }
        this.mNotLogin.setVisibility(isNBAccountLogin ? 8 : 0);
        this.mLogined.setVisibility(isNBAccountLogin ? 0 : 8);
        this.mGameRecords.setVisibility(isNBAccountLogin ? 0 : 8);
        this.mNoNetWork.setVisibility(8);
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment
    protected View createMainView() {
        if (this.m_activity == null) {
            return null;
        }
        Activity activity = this.m_activity;
        this.mContext = activity;
        this.mConnectivityManager = (ConnectivityManager) activity.getApplicationContext().getSystemService("connectivity");
        this.mPkgManager = this.mContext.getApplicationContext().getPackageManager();
        this.mResumed = true;
        bindArkBaseService();
        View inflate = View.inflate(this.mContext, R.layout.ark_base, null);
        initView(inflate);
        initData();
        GcsAnimationUtil.setGcsItemTranslationY(this.mLayoutView);
        return inflate;
    }

    public void onAccountInfo(Map map, Bitmap bitmap) {
        LogUtil.i(TAG, "onAccountInfo, accountInfo = " + map);
        synchronized (this.mObject) {
            if (map != null) {
                this.mAccountInfo = map;
                this.mAvatarBitmap = bitmap;
                ArkBaseHelper.cacheAccountInfo(map);
            }
        }
        sendMsgUpdateView();
    }

    public void onAccountLabel(List list) {
        LogUtil.i(TAG, "onAccountLabel, labelList = " + list);
        synchronized (this.mObject) {
            if (list != null) {
                this.mAccountLabelInfos = labelInfos2List(list);
            }
        }
        sendMsgUpdateView();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mLoginBtn) {
            startArkBaseTransitActivity();
        } else if (view == this.mRecordHelper) {
            showGameRecordAlertDialog();
        } else if (view == this.mNetWorkSet) {
            startNetworkSet();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mAccountLabelPanel.removeOnLayoutChangeListener(this);
        INbAccountLogin iNbAccountLogin = this.mNbAccountLoginImpl;
        if (iNbAccountLogin != null) {
            try {
                iNbAccountLogin.unregisterCallback(this.mNbAccountLoginCb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Context context = this.mContext;
        if (context != null) {
            context.unbindService(this.mArkBaseServiceConnection);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Bitmap bitmap = this.mAvatarBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mAvatarBitmap.recycle();
        }
        super.onDestroy();
    }

    public void onError(String str) {
        if (this.mResumed) {
            LogUtil.e(TAG, "onError errorType:=" + str);
            sendMsgUpdateView();
        }
    }

    public void onGameRecords(List list) {
        if (list != null) {
            synchronized (this.mObject) {
                this.mRecords = gameRecords2List(list);
            }
            sendMsgUpdateView();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    ArkBaseFragment.this.mLayoutView.setAlpha(0.0f);
                }
            });
            return;
        }
        GcsAnimationUtil.setGcsItemTranslationY(this.mLayoutView);
        GcsAnimationUtil.setGcsItemAlpha(this.mLayoutView);
        GcsAnimationUtil.setGcsItemTranslationY(this.mRecordList);
    }

    @Override // cn.nubia.gamecenter.settings.summary.ArkBaseRecordAdapter.OnIconClickListener
    public void onIconClick(int i) {
        ArkBaseRecordAdapter arkBaseRecordAdapter = this.mRecordAdapter;
        if (arkBaseRecordAdapter != null) {
            Intent intent = ((GameRecord) arkBaseRecordAdapter.getItem(i)).launchIntent;
            if (intent == null) {
                showRecordDeletedAlertDialog();
            } else {
                intent.setFlags(268435456);
                this.mContext.startActivity(intent);
            }
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int rowCount = this.mAccountLabelPanel.getRowCount();
        if (rowCount != this.mAccountLabelLayoutRow) {
            this.mAccountLabelLayoutRow = rowCount;
            this.mAccountLabelSpace.setVisibility(rowCount > 1 ? 8 : 0);
            this.mHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.summary.ArkBaseFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    ArkBaseFragment.this.mLogined.requestLayout();
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mResumed = false;
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.d(TAG, "onResume");
        super.onResume();
        this.mResumed = true;
        updateView();
        loadData();
        Track.login(isNBAccountLogin());
    }

    public void onSuccess(String str, Map map, Bitmap bitmap) {
        if (this.mResumed) {
            LogUtil.i(TAG, "onSuccess code:=" + str + ", accountInfo = " + map + ", avatar = " + bitmap);
            synchronized (this.mObject) {
                if (map != null) {
                    this.mAccountInfo = map;
                    this.mAvatarBitmap = bitmap;
                    ArkBaseHelper.cacheAccountInfo(map);
                }
            }
            sendMsgUpdateView();
            loadData();
        }
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
