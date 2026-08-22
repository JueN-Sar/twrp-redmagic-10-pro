package cn.nubia.gamelauncher.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import cn.nubia.arkbase.nbaccount.INbAccountLogin;
import cn.nubia.arkbase.nbaccount.INbAccountLoginCallback;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.DailyScoreBean;
import cn.nubia.gamelauncher.bean.ScoreOneBean;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.view.ScoreRecordLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class ScoreRecordsController implements View.OnClickListener {
    private static final String ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME = "cn.nubia.arkbase.service.NbAccountLoginService";
    private static final String ARK_BASE_PACKAGE_NAME = "cn.nubia.arkbase";
    private static volatile ScoreRecordsController INSTANCE;
    private AlertDialog dialog;
    private HashMap<String, String> gameNameMaps;
    private StringBuilder mAllGameStr;
    private CheckBox mCheckBox;
    private View mDiaLogView;
    private boolean mGameIsEnable;
    private INbAccountLogin mNbAccountLoginImpl;
    private ScoreRecordLayout mScoreLayout;
    private String mSelectGameName;
    private boolean mServiceBind;
    String TAG = "ScoreRecord";
    private final String REDMAGIC_CE_SWITCH = "redmagic_ce_switch";
    private List<String> SUPPORT_PART1_GAME = Arrays.asList(HighLightsUtils.WZRY_PACKAGE_NAME, HighLightsUtils.LOL_PACKAGE_NAME, HighLightsUtils.CJZC_PACKAGE_NAME, HighLightsUtils.PUBG_PACKAGE_NAME);
    private List<String> SUPPORT_WIN_RATE = Arrays.asList(HighLightsUtils.WZRY_PACKAGE_NAME, HighLightsUtils.LOL_PACKAGE_NAME);
    private final String GAME_SPLIT_STR = ",";
    private final Object mRecordLock = new Object();
    private final Object mPowerLock = new Object();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ContentObserver mKeyObserver = new ContentObserver(this.mHandler) { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.d(ScoreRecordsController.this.TAG, "setting key onChange");
            ScoreRecordsController.this.getGlobalSettings();
            ScoreRecordsController.this.gameIsEnable();
        }
    };
    private final ServiceConnection mArkBaseServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.d(ScoreRecordsController.this.TAG, "onServiceConnected");
            ScoreRecordsController.this.mServiceBind = true;
            try {
                ScoreRecordsController.this.mNbAccountLoginImpl = INbAccountLogin.Stub.asInterface(iBinder);
                ScoreRecordsController.this.mNbAccountLoginImpl.registerCallback(ScoreRecordsController.this.mNbAccountLoginCb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.d(ScoreRecordsController.this.TAG, "onServiceDisconnected");
            ScoreRecordsController.this.mNbAccountLoginImpl = null;
            ScoreRecordsController.this.mServiceBind = false;
        }
    };
    private INbAccountLoginCallback mNbAccountLoginCb = new INbAccountLoginCallback.Stub() { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.3
        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onAccountInfo(Map map, Bitmap bitmap) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onAccountLabel(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onError(String str) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameHighLights(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameNotes(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGamePowers(List list) {
            if (ScoreRecordsController.this.mScoreLayout == null) {
                return;
            }
            Log.d(ScoreRecordsController.this.TAG, "onGamePowers = " + list);
            synchronized (ScoreRecordsController.this.mPowerLock) {
                ScoreRecordsController.this.mPowerLists = list;
            }
            ScoreRecordsController.this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.3.2
                @Override // java.lang.Runnable
                public void run() {
                    ScoreRecordsController.this.getSelectGameDate(false, true);
                    ScoreRecordsController.this.mScoreLayout.updateLayout();
                }
            });
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameRecords(List list) {
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameScores(List list) {
            if (ScoreRecordsController.this.mScoreLayout == null) {
                return;
            }
            Log.d(ScoreRecordsController.this.TAG, "onGameScores = " + list);
            synchronized (ScoreRecordsController.this.mRecordLock) {
                ScoreRecordsController.this.mRecordLists = list;
            }
            ScoreRecordsController.this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.3.1
                @Override // java.lang.Runnable
                public void run() {
                    ScoreRecordsController.this.getSelectGameDate(true, false);
                }
            });
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onSuccess(String str, Map map, Bitmap bitmap) {
        }
    };
    private Context mContext = GameLauncherApplication.getAppContext();
    private List<String> enableGameLists = new ArrayList();
    private List<Map<String, String>> mRecordLists = new ArrayList();
    private List<Map<String, String>> mPowerLists = new ArrayList();

    private ScoreRecordsController() {
        getGlobalSettings();
        initView();
        initData();
    }

    private void addKeyListener() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("redmagic_ce_switch"), true, this.mKeyObserver);
    }

    private void bindArkBaseService() {
        LogUtil.d(this.TAG, "bindArkBaseService");
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.arkbase", ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME);
        this.mContext.bindService(intent, this.mArkBaseServiceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gameIsEnable() {
        List<String> list = this.enableGameLists;
        if (list != null) {
            this.mGameIsEnable = list.contains(this.mSelectGameName);
        } else {
            this.mGameIsEnable = false;
        }
        ScoreRecordLayout scoreRecordLayout = this.mScoreLayout;
        if (scoreRecordLayout != null) {
            scoreRecordLayout.setCurEnable(this.mGameIsEnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getGlobalSettings() {
        String string = Settings.Global.getString(this.mContext.getContentResolver(), "redmagic_ce_switch");
        LogUtil.d(this.TAG, "Settings getGlobalSettings = " + string);
        if (string == null || string.isEmpty()) {
            List<String> list = this.enableGameLists;
            if (list != null) {
                list.clear();
            }
        } else {
            String[] split = string.split(",");
            List<String> list2 = this.enableGameLists;
            if (list2 != null) {
                list2.clear();
            }
            this.enableGameLists = new ArrayList(Arrays.asList(split));
        }
        if (CommonUtil.isInternalVersion() || this.gameNameMaps == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.enableGameLists.iterator();
        while (it.hasNext()) {
            sb.append(this.gameNameMaps.get(it.next())).append(",");
        }
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "performance_records_switch", "app_name", sb.toString());
    }

    public static ScoreRecordsController getInstance() {
        if (INSTANCE == null) {
            synchronized (ScoreRecordsController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ScoreRecordsController();
                }
            }
        }
        return INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSelectGameDate(boolean z, boolean z2) {
        if (z && this.mRecordLists != null) {
            ArrayList<ScoreOneBean> arrayList = new ArrayList<>();
            for (Map<String, String> map : this.mRecordLists) {
                String str = map.get("package_name");
                if (str != null && str.equals(this.mSelectGameName)) {
                    ScoreOneBean scoreOneBean = new ScoreOneBean();
                    if (map.containsKey("start_time") && map.get("start_time") != null) {
                        scoreOneBean.setStartTime(Long.valueOf(Long.parseLong(map.get("start_time"))));
                    }
                    scoreOneBean.setResult(map.get("result"));
                    scoreOneBean.setKill(map.get("kill"));
                    scoreOneBean.setDead(map.get("death"));
                    scoreOneBean.setAssit(map.get("assist"));
                    scoreOneBean.setPackageName(str);
                    arrayList.add(scoreOneBean);
                }
            }
            Collections.sort(arrayList);
            ScoreRecordLayout scoreRecordLayout = this.mScoreLayout;
            if (scoreRecordLayout != null) {
                scoreRecordLayout.setPart1Data(arrayList);
            }
        }
        if (!z2 || this.mPowerLists == null) {
            return;
        }
        ArrayList<DailyScoreBean> arrayList2 = new ArrayList<>();
        for (Map<String, String> map2 : this.mPowerLists) {
            String str2 = map2.get("package_name");
            if (str2 != null && str2.equals(this.mSelectGameName)) {
                DailyScoreBean dailyScoreBean = new DailyScoreBean();
                if (map2.containsKey("statistic_time") && map2.get("statistic_time") != null) {
                    dailyScoreBean.setDate(Long.valueOf(Long.parseLong(map2.get("statistic_time"))));
                }
                if (map2.containsKey("wins") && map2.get("wins") != null) {
                    dailyScoreBean.setWinCount(Integer.parseInt(map2.get("wins")));
                }
                if (map2.containsKey("cps") && map2.get("cps") != null) {
                    dailyScoreBean.setCpsCount(Integer.parseInt(map2.get("cps")));
                }
                if (map2.containsKey("mpm") && map2.get("mpm") != null) {
                    dailyScoreBean.setMpmCount(Integer.parseInt(map2.get("mpm")));
                }
                arrayList2.add(dailyScoreBean);
            }
        }
        Collections.sort(arrayList2);
        ScoreRecordLayout scoreRecordLayout2 = this.mScoreLayout;
        if (scoreRecordLayout2 != null) {
            scoreRecordLayout2.setPart2Data(arrayList2);
        }
    }

    private void initData() {
        addKeyListener();
        if (this.mServiceBind) {
            return;
        }
        this.mServiceBind = true;
        bindArkBaseService();
    }

    private String list2String() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.enableGameLists.iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append(",");
        }
        return sb.toString();
    }

    private void removeEnableGames() {
        List<String> list = this.enableGameLists;
        if (list == null || !list.contains(this.mSelectGameName)) {
            return;
        }
        this.enableGameLists.remove(this.mSelectGameName);
        LogUtil.d(this.TAG, "close : " + Settings.Global.putString(this.mContext.getContentResolver(), "redmagic_ce_switch", list2String()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGlobalSettings(boolean z) {
        if (z) {
            Settings.Global.putString(this.mContext.getContentResolver(), "redmagic_ce_switch", this.mAllGameStr.toString());
            return;
        }
        this.enableGameLists.add(this.mSelectGameName);
        LogUtil.d(this.TAG, "open : " + Settings.Global.putString(this.mContext.getContentResolver(), "redmagic_ce_switch", list2String()));
    }

    private void showOpenRedMagicTimeAlertDialog() {
        if (this.dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, 2131952382);
            builder.setView(this.mDiaLogView);
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setPositiveButton(R.string.on, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.ScoreRecordsController.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ScoreRecordsController scoreRecordsController = ScoreRecordsController.this;
                    scoreRecordsController.setGlobalSettings(scoreRecordsController.mCheckBox.isChecked());
                    ScoreRecordsController.this.requestData();
                    dialogInterface.dismiss();
                }
            });
            AlertDialog create = builder.create();
            this.dialog = create;
            create.getWindow().setType(2008);
        }
        this.dialog.show();
    }

    public void destroy() {
        LogUtil.d(this.TAG, "destroy()");
        INbAccountLogin iNbAccountLogin = this.mNbAccountLoginImpl;
        if (iNbAccountLogin != null) {
            try {
                iNbAccountLogin.unregisterCallback(this.mNbAccountLoginCb);
                this.mNbAccountLoginImpl = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Context context = this.mContext;
        if (context != null) {
            context.getContentResolver().unregisterContentObserver(this.mKeyObserver);
            if (this.mServiceBind) {
                this.mServiceBind = false;
                this.mContext.unbindService(this.mArkBaseServiceConnection);
            }
        }
        this.mScoreLayout = null;
        this.mHandler = null;
        this.enableGameLists = null;
        this.mRecordLists = null;
        this.mPowerLists = null;
        INSTANCE = null;
    }

    public ScoreRecordLayout getScoreLayout() {
        return this.mScoreLayout;
    }

    public void initView() {
        ScoreRecordLayout scoreRecordLayout = new ScoreRecordLayout(this.mContext);
        this.mScoreLayout = scoreRecordLayout;
        scoreRecordLayout.findViewById(R.id.tv_score_enable).setOnClickListener(this);
        this.mScoreLayout.findViewById(R.id.tv_score_disable).setOnClickListener(this);
        this.mScoreLayout.findViewById(R.id.tv_score_disable_cancle).setOnClickListener(this);
        this.mScoreLayout.findViewById(R.id.iv_score_settings).setOnClickListener(this);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.score_enable_dialog_layout, (ViewGroup) null);
        this.mDiaLogView = inflate;
        this.mCheckBox = (CheckBox) inflate.findViewById(R.id.checkbox_all_game);
        this.mScoreLayout.updateLayout();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_score_settings) {
            this.mScoreLayout.showDisableLayout();
        }
        switch (id) {
            case R.id.tv_score_disable /* 2131363609 */:
                removeEnableGames();
                this.mScoreLayout.showEnableLayout();
                break;
            case R.id.tv_score_disable_cancle /* 2131363610 */:
                this.mScoreLayout.updateLayout();
                break;
            case R.id.tv_score_enable /* 2131363611 */:
                showOpenRedMagicTimeAlertDialog();
                break;
        }
    }

    public void requestData() {
        try {
            if (this.mNbAccountLoginImpl != null) {
                Log.d(this.TAG, "requestData start");
                this.mNbAccountLoginImpl.loadGameScores(this.mSelectGameName);
                this.mNbAccountLoginImpl.loadGamePowers(this.mSelectGameName);
            } else {
                LogUtil.d(this.TAG, "requestData skip");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFullGameList(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        HashMap<String, String> hashMap = this.gameNameMaps;
        if (hashMap == null) {
            this.gameNameMaps = new HashMap<>();
        } else {
            hashMap.clear();
        }
        this.mAllGameStr = new StringBuilder();
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            this.mAllGameStr.append(next.getPackageName()).append(",");
            this.gameNameMaps.put(next.getPackageName(), next.getName());
        }
        if (this.mAllGameStr.toString().endsWith(",")) {
            this.mAllGameStr.deleteCharAt(r4.length() - 1);
        }
    }

    public void updateSelectGames(String str) {
        if (str == null || this.mScoreLayout == null) {
            return;
        }
        this.mSelectGameName = str;
        HashMap<String, String> hashMap = this.gameNameMaps;
        if (hashMap != null && hashMap.containsKey(str)) {
            this.mScoreLayout.setGamePackageName(str, this.gameNameMaps.get(str));
        }
        this.mScoreLayout.setSupportPart1(this.SUPPORT_PART1_GAME.contains(this.mSelectGameName));
        if (FeatureUtil.isSupportGamePrediction()) {
            this.mScoreLayout.setSupportWinRate(this.SUPPORT_WIN_RATE.contains(this.mSelectGameName));
        }
        Log.d(this.TAG, "updateSelectGames pgk: " + str + ", mGameIsEnable = " + this.mGameIsEnable);
        gameIsEnable();
    }
}
