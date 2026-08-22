package cn.nubia.gamelauncher.gamecontrolpanel.superresolution;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.widget.AppCompatTextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.MarqueeTextView;
import cn.nubia.gamelauncher.gamecontrolpanel.PanelDismissListener;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.service.GameFeatureService;
import com.android.systemui.shared.system.ActivityManagerWrapper;

/* loaded from: classes.dex */
public class SuperResolutionController implements RestartAppWarningDialogCallBack {
    private static String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
    private static final String NUBIA_GAME_SCENE = "nubia_game_scene";
    private static final String TAG = "SuperResolutionController";
    private AlertDialog mAlertDialog;
    private Context mContext;
    private String mCurrentActivity;
    private String mCurrentPackageName;
    private ContentObserver mNubiaGameSceneObserver;
    private PanelDismissListener mPanelDismissListener;
    private Handler mWorkHandler;
    private SuperResolutionSettingsDialog mSuperResolutionSettingsDialog = null;
    private BroadcastReceiver mFinishDialogReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !SuperResolutionController.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                return;
            }
            String stringExtra = intent.getStringExtra("reason");
            LogUtil.i(SuperResolutionController.TAG, "onReceive: mFinishDialogReceiver reason = " + stringExtra);
            if (stringExtra != null) {
                if (ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_HOME_KEY.equals(stringExtra) || ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_RECENTS.equals(stringExtra)) {
                    if (SuperResolutionController.this.mSuperResolutionSettingsDialog != null && SuperResolutionController.this.mSuperResolutionSettingsDialog.isShowing()) {
                        SuperResolutionController.this.mSuperResolutionSettingsDialog.dismiss();
                    }
                    if (SuperResolutionController.this.mAlertDialog == null || !SuperResolutionController.this.mAlertDialog.isShowing()) {
                        return;
                    }
                    SuperResolutionController.this.mAlertDialog.dismiss();
                }
            }
        }
    };

    public SuperResolutionController(Context context) {
        this.mWorkHandler = null;
        this.mNubiaGameSceneObserver = new ContentObserver(this.mWorkHandler) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                SuperResolutionController.this.handleDialogStatus();
            }
        };
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mWorkHandler = new Handler(handlerThread.getLooper());
    }

    private void forceStopApp(final String str) {
        LogUtil.i(TAG, " forceStopApp  curPkgName = " + str);
        try {
            ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY);
            activityManager.getClass().getDeclaredMethod("forceStopPackage", String.class).invoke(activityManager, str);
            this.mWorkHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    SuperResolutionController.this.m302xaf279cc9(str);
                }
            }, 50L);
        } catch (Exception e) {
            LogUtil.e(TAG, "restartApp ----- error----", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDialogStatus() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "nubia_game_scene", 0);
        LogUtil.i(TAG, "handleDialogStatus: gameScene = " + i);
        if (i == 0) {
            SuperResolutionSettingsDialog superResolutionSettingsDialog = this.mSuperResolutionSettingsDialog;
            if (superResolutionSettingsDialog != null && superResolutionSettingsDialog.isShowing()) {
                this.mSuperResolutionSettingsDialog.dismiss();
            }
            AlertDialog alertDialog = this.mAlertDialog;
            if (alertDialog == null || !alertDialog.isShowing()) {
                return;
            }
            this.mAlertDialog.dismiss();
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_CLOSE_SYSTEM_DIALOGS);
        intentFilter.setPriority(1000);
        try {
            this.mContext.registerReceiver(this.mFinishDialogReceiver, intentFilter, 2);
        } catch (Exception e) {
            LogUtil.e(TAG, " registerReceiver -- error ", e);
        }
    }

    private void registerSettingsObserver() {
        try {
            LogUtil.i(TAG, "registerSettingsObserver: ");
            this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_game_scene"), false, this.mNubiaGameSceneObserver);
        } catch (Exception e) {
            LogUtil.i(TAG, "registerSettingsObserver: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: restartApp, reason: merged with bridge method [inline-methods] */
    public void m302xaf279cc9(String str) {
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            LogUtil.i(TAG, " restartApp launchIntent : " + launchIntentForPackage + " curPkgName = " + str);
            try {
                launchIntentForPackage.setFlags(268435456);
                this.mContext.startActivity(launchIntentForPackage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void unRegisterReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mFinishDialogReceiver);
        } catch (Exception e) {
            LogUtil.e(TAG, " unregisterReceiver -- error ", e);
        }
    }

    private void unRegisterSettingsObserver() {
        try {
            LogUtil.i(TAG, "unRegisterSettingsObserver: ");
            this.mContext.getContentResolver().unregisterContentObserver(this.mNubiaGameSceneObserver);
        } catch (Exception e) {
            LogUtil.i(TAG, "unRegisterSettingsObserver: " + e.toString());
        }
    }

    /* renamed from: lambda$showRestartAppWarningDialog$1$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionController, reason: not valid java name */
    /* synthetic */ void m303x1b62bd67(View view) {
        this.mAlertDialog.dismiss();
    }

    /* renamed from: lambda$showRestartAppWarningDialog$2$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionController, reason: not valid java name */
    /* synthetic */ void m304x4a142786(String str, View view) {
        forceStopApp(str);
        this.mAlertDialog.dismiss();
    }

    /* renamed from: lambda$showRestartAppWarningDialog$3$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionController, reason: not valid java name */
    /* synthetic */ void m305x78c591a5(MarqueeTextView marqueeTextView, MarqueeTextView marqueeTextView2) {
        registerObserverAndReceiver();
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        marqueeTextView.setHasFocus(true);
        marqueeTextView2.setHasFocus(true);
    }

    /* renamed from: lambda$showRestartAppWarningDialog$5$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionController, reason: not valid java name */
    /* synthetic */ void m306xd62865e3(MarqueeTextView marqueeTextView, MarqueeTextView marqueeTextView2, DialogInterface dialogInterface) {
        PanelDismissListener panelDismissListener = this.mPanelDismissListener;
        if (panelDismissListener != null) {
            panelDismissListener.panelDismiss();
        }
        unRegisterObserverAndReceiver();
        this.mAlertDialog = null;
        marqueeTextView.onWindowFocusChanged(false);
        marqueeTextView2.onWindowFocusChanged(false);
    }

    /* renamed from: lambda$showSuperResolutionSettingsDialog$0$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionController, reason: not valid java name */
    /* synthetic */ void m307x89eac5a8(DialogInterface dialogInterface) {
        this.mPanelDismissListener.panelDismiss();
        unRegisterObserverAndReceiver();
    }

    public void registerObserverAndReceiver() {
        registerReceiver();
        registerSettingsObserver();
    }

    public void setPanelDismissListener(PanelDismissListener panelDismissListener) {
        this.mPanelDismissListener = panelDismissListener;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.superresolution.RestartAppWarningDialogCallBack
    public void showRestartAppWarningDialog(final String str, String str2, boolean z) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.super_resolution_restart_app_dialog_layout, (ViewGroup) null);
        final MarqueeTextView marqueeTextView = (MarqueeTextView) inflate.findViewById(R.id.dialog_cancel);
        final MarqueeTextView marqueeTextView2 = (MarqueeTextView) inflate.findViewById(R.id.dialog_ok);
        AppCompatTextView appCompatTextView = (AppCompatTextView) inflate.findViewById(R.id.dialog_message);
        StringBuilder sb = new StringBuilder();
        String string = this.mContext.getString(R.string.super_resolution_restart_app_warning_msg);
        if (!z) {
            sb.append(this.mContext.getString(R.string.super_resolution_restart_dialog_instruction_tips_new)).append("\n");
        }
        sb.append(string);
        appCompatTextView.setText(sb.toString());
        marqueeTextView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperResolutionController.this.m303x1b62bd67(view);
            }
        });
        marqueeTextView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperResolutionController.this.m304x4a142786(str, view);
            }
        });
        this.mWorkHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                SuperResolutionController.this.m305x78c591a5(marqueeTextView, marqueeTextView2);
            }
        }, 200L);
        AlertDialog create = new AlertDialog.Builder(this.mContext.getApplicationContext(), 2131952382).setView(inflate).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SuperResolutionController.this.m306xd62865e3(marqueeTextView, marqueeTextView2, dialogInterface);
            }
        }).create();
        this.mAlertDialog = create;
        Window window = create.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.type = 2038;
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(5638);
        this.mAlertDialog.setCanceledOnTouchOutside(true);
        this.mAlertDialog.show();
    }

    public void showSuperResolutionSettingsDialog(String str, String str2, Integer num) {
        SuperResolutionSettingsDialog superResolutionSettingsDialog = this.mSuperResolutionSettingsDialog;
        if (superResolutionSettingsDialog == null || !superResolutionSettingsDialog.isShowing()) {
            registerObserverAndReceiver();
            this.mCurrentPackageName = str;
            this.mCurrentActivity = str2;
            SuperResolutionSettingsDialog superResolutionSettingsDialog2 = new SuperResolutionSettingsDialog(this.mContext, this.mCurrentPackageName, num, this);
            this.mSuperResolutionSettingsDialog = superResolutionSettingsDialog2;
            superResolutionSettingsDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    SuperResolutionController.this.m307x89eac5a8(dialogInterface);
                }
            });
            this.mSuperResolutionSettingsDialog.showDialog();
        }
    }

    public void unRegisterObserverAndReceiver() {
        unRegisterReceiver();
        unRegisterSettingsObserver();
    }
}
