package cn.nubia.gamepanel;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.ReportUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.systemwrapper.InputChannelWrapper;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PowerPanelService extends Service {
    private static final String ACTION_FOREGROUND_PKG_CHANGE = "ACTION_FOREGROUND_PKG_CHANGE";
    private static final String ACTION_TYPE = "type";
    private static final int EVENT_ID_GAME_POWER = 400;
    private static final String GAME_PKG = "currentPkg";
    private static final int MSG_SHOW_VIEW = 0;
    private static final int MSG_STOP_SERVICE = 1;
    private static final String POWER_PANEL_DIALOG_SHOW = "powerPanelDialogShow";
    private static final String SETTINGS_NUBIA_GAME_WINDOW_SHOW = "nubia_game_window_show";
    private static final String TAG = "PowerPanelService";
    private CpsMpmData cpsMpmData;
    private AlertDialog dialog;
    private CheckBox mCheckBox;
    private View mDiaLogView;
    private Handler mHandler;
    private PowerPanelWindowManager powerPanelWindowManager;
    private final String POWER_PANEL_NOT_REMINDING = "power_panel_not_reminding";
    private String gamePkg = "";
    private int isShowPowerPanelDialog = 0;
    HomeReceiver homeReceiver = null;
    private Handler mClosePowerPanelHandler = new Handler();
    private Runnable mClosePowerPanelTimer = new Runnable() { // from class: cn.nubia.gamepanel.PowerPanelService.1
        @Override // java.lang.Runnable
        public void run() {
            if (Settings.Global.getInt(PowerPanelService.this.getApplicationContext().getContentResolver(), "nubia_game_scene", 0) != 0 || PowerPanelService.this.powerPanelWindowManager == null) {
                PowerPanelService.this.mClosePowerPanelHandler.postDelayed(PowerPanelService.this.mClosePowerPanelTimer, 100L);
            } else {
                PowerPanelService.this.sendMessageDelayed(1, 0);
            }
        }
    };

    class HomeReceiver extends BroadcastReceiver {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_RECENTS;
        final String SYSTEM_DIALOG_REASON_HOME_KEY = ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_HOME_KEY;

        HomeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra;
            if (!"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) || (stringExtra = intent.getStringExtra("reason")) == null) {
                return;
            }
            if ((stringExtra.equals(ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_HOME_KEY) || stringExtra.equals(ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_RECENTS)) && PowerPanelService.this.dialog != null) {
                PowerPanelService.this.dialog.dismiss();
                Settings.Global.putInt(PowerPanelService.this.getApplicationContext().getContentResolver(), PowerPanelService.SETTINGS_NUBIA_GAME_WINDOW_SHOW, 0);
            }
        }
    }

    public static void foregroundPkgChange(Context context, String str, int i, int i2) {
        Intent intent = new Intent(context, (Class<?>) PowerPanelService.class);
        intent.setAction(ACTION_FOREGROUND_PKG_CHANGE);
        intent.putExtra("currentPkg", str);
        intent.putExtra("type", i);
        intent.putExtra(POWER_PANEL_DIALOG_SHOW, i2);
        context.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isShowDialog() {
        return Settings.Global.getInt(getApplicationContext().getContentResolver(), "power_panel_not_reminding", 0) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageDelayed(int i, int i2) {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(i);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOpenPowerPanelTipsAlertDialog() {
        if (this.dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext(), 2131952382);
            builder.setView(this.mDiaLogView);
            this.mDiaLogView.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamepanel.PowerPanelService.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PowerPanelService.this.powerPanelWindowManager.createPowerPanelView(PowerPanelService.this.getApplicationContext());
                    PowerPanelService.this.mClosePowerPanelHandler.postDelayed(PowerPanelService.this.mClosePowerPanelTimer, 100L);
                    PowerPanelService.this.cpsMpmData.setStartTime(System.currentTimeMillis());
                    if (PowerPanelService.this.mCheckBox.isChecked()) {
                        Settings.Global.putInt(PowerPanelService.this.getApplicationContext().getContentResolver(), "power_panel_not_reminding", 1);
                    }
                    PowerPanelService.this.dialog.dismiss();
                    Settings.Global.putInt(PowerPanelService.this.getApplicationContext().getContentResolver(), PowerPanelService.SETTINGS_NUBIA_GAME_WINDOW_SHOW, 0);
                }
            });
            AlertDialog create = builder.create();
            this.dialog = create;
            create.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: cn.nubia.gamepanel.PowerPanelService.4
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1) {
                        PowerPanelService.this.powerPanelWindowManager.createPowerPanelView(PowerPanelService.this.getApplicationContext());
                        PowerPanelService.this.mClosePowerPanelHandler.postDelayed(PowerPanelService.this.mClosePowerPanelTimer, 100L);
                        PowerPanelService.this.cpsMpmData.setStartTime(System.currentTimeMillis());
                        dialogInterface.cancel();
                        Settings.Global.putInt(PowerPanelService.this.getApplicationContext().getContentResolver(), PowerPanelService.SETTINGS_NUBIA_GAME_WINDOW_SHOW, 0);
                    }
                    return false;
                }
            });
            this.dialog.getWindow().setType(2008);
        }
        this.dialog.setCancelable(false);
        this.dialog.show();
        Settings.Global.putInt(getApplicationContext().getContentResolver(), SETTINGS_NUBIA_GAME_WINDOW_SHOW, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadPowerPanelData() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        int divData = MathUtils.divData(System.currentTimeMillis() - this.cpsMpmData.getStartTime(), 1000.0d);
        double d = divData;
        double divDataOther = MathUtils.divDataOther(d, 60.0d, 2);
        int divData2 = MathUtils.divData(this.cpsMpmData.getCps(), divDataOther);
        int divData3 = MathUtils.divData(this.cpsMpmData.getMpm(), divDataOther);
        Bundle bundle = new Bundle();
        bundle.putString("packageName", this.gamePkg);
        bundle.putLong("start_time", System.currentTimeMillis());
        bundle.putInt("cps", MathUtils.divData(this.cpsMpmData.getCps(), d));
        bundle.putInt("mpm", MathUtils.divData(divData3, divData2, 2));
        LogUtils.infoPowerPanel(TAG, "uploadPowerPanelData: cps = " + MathUtils.divData(this.cpsMpmData.getCps(), d) + "; mpm = " + MathUtils.divData(divData3, divData2, 2));
        arrayList.add(bundle);
        ArkBaseTrackManager.getInstance().sendEvent(EVENT_ID_GAME_POWER, arrayList);
        ReportUtils.onReportPowerPanelClose(getApplicationContext(), divData);
        this.cpsMpmData.clearData();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.powerPanelWindowManager = PowerPanelWindowManager.getInstance();
        ArkBaseTrackManager.getInstance().init(getApplicationContext());
        this.homeReceiver = new HomeReceiver();
        registerReceiver(this.homeReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 2);
        this.cpsMpmData = CpsMpmData.getInstance();
        this.mHandler = new Handler(getMainLooper()) { // from class: cn.nubia.gamepanel.PowerPanelService.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LogUtils.errorPowerPanel(PowerPanelService.TAG, "MSG msg.what = " + message.what);
                int i = message.what;
                if (i == 0) {
                    PowerPanelService powerPanelService = PowerPanelService.this;
                    powerPanelService.mDiaLogView = LayoutInflater.from(powerPanelService.getApplicationContext()).inflate(R.layout.power_panel_enable_dialog_layout, (ViewGroup) null);
                    PowerPanelService powerPanelService2 = PowerPanelService.this;
                    powerPanelService2.mCheckBox = (CheckBox) powerPanelService2.mDiaLogView.findViewById(R.id.checkbox_not_reminding);
                    if (PowerPanelService.this.isShowDialog() && PowerPanelService.this.isShowPowerPanelDialog == 0) {
                        PowerPanelService.this.showOpenPowerPanelTipsAlertDialog();
                    } else {
                        PowerPanelService.this.powerPanelWindowManager.createPowerPanelView(PowerPanelService.this.getApplicationContext());
                        PowerPanelService.this.mClosePowerPanelHandler.postDelayed(PowerPanelService.this.mClosePowerPanelTimer, 100L);
                        PowerPanelService.this.cpsMpmData.setStartTime(System.currentTimeMillis());
                    }
                } else if (i == 1) {
                    InputChannelWrapper.getInstance().unRegister("GamePanelChannel");
                    PowerPanelService.this.powerPanelWindowManager.removePowerPanelView(PowerPanelService.this.getApplicationContext());
                    PowerPanelService.this.uploadPowerPanelData();
                    PowerPanelService.this.stopSelf();
                }
                super.handleMessage(message);
            }
        };
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        Runnable runnable;
        Handler handler = this.mClosePowerPanelHandler;
        if (handler != null && (runnable = this.mClosePowerPanelTimer) != null) {
            handler.removeCallbacks(runnable);
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            Settings.Global.putInt(getApplicationContext().getContentResolver(), SETTINGS_NUBIA_GAME_WINDOW_SHOW, 0);
        }
        HomeReceiver homeReceiver = this.homeReceiver;
        if (homeReceiver != null) {
            unregisterReceiver(homeReceiver);
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.infoPowerPanel(TAG, "onStartCommand");
        if (intent == null) {
            LogUtils.errorPowerPanel(TAG, "intent is null!");
            return super.onStartCommand(null, i, i2);
        }
        int intExtra = intent.getIntExtra("type", -1);
        this.gamePkg = intent.getStringExtra("currentPkg");
        this.isShowPowerPanelDialog = intent.getIntExtra(POWER_PANEL_DIALOG_SHOW, 0);
        Settings.Global.putString(getContentResolver(), "game_pack_name", this.gamePkg);
        if (intExtra == 0) {
            sendMessageDelayed(0, 20);
        } else if (intExtra == 1) {
            sendMessageDelayed(1, 0);
        }
        return super.onStartCommand(intent, i, i2);
    }
}
