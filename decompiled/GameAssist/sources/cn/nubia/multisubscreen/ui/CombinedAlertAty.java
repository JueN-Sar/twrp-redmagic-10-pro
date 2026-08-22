package cn.nubia.multisubscreen.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Window;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.CastRole;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.mgr.ConnectCodeMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.mgr.MultiSubScreenNotificationMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.mifavor.widget.AlertDialog;

/* loaded from: classes.dex */
public class CombinedAlertAty extends BaseAlertActivity implements RotationMgr.Callback {

    /* renamed from: q, reason: collision with root package name */
    private String f8107q;

    /* renamed from: r, reason: collision with root package name */
    private String f8108r;

    /* renamed from: s, reason: collision with root package name */
    private String f8109s;
    private String t;
    private String u;
    private String v;
    private DistributeBusMgr x;

    /* renamed from: p, reason: collision with root package name */
    Handler f8106p = new Handler(Looper.getMainLooper());
    private boolean w = false;
    private boolean y = true;
    private StatusCallback z = new StatusCallback() { // from class: cn.nubia.multisubscreen.ui.CombinedAlertAty.1
        @Override // cn.nubia.multisubscreen.callback.StatusCallback
        public void a(ListedDevice listedDevice) {
            GaLog.b("MultiSubScreen_CombinedAlertAty", "CombinedAlertAty onStatusChange device.getStatus() = " + listedDevice.getStatus());
            if (listedDevice.getStatus() == 102 && MultiSubScreenUtils.f8174d == 1) {
                MultiSubScreenUtils.w(listedDevice.getDeviceId(), 2);
                ConnectCodeMgr.h().x("SOURCE_REPLY_TO_SINK_CONNECTED_CODE");
            }
            CombinedAlertAty.this.finish();
        }
    };
    private final BroadcastReceiver A = new BroadcastReceiver() { // from class: cn.nubia.multisubscreen.ui.CombinedAlertAty.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            GaLog.a("MultiSubScreen_CombinedAlertAty", "mFinishActivityReceiver action = " + action);
            if (action != null && action.equals("android.bluetooth.adapter.action.STATE_CHANGED") && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0) == 10) {
                GaLog.a("MultiSubScreen_CombinedAlertAty", "mFinishActivityReceiver bt change and finish!");
                CombinedAlertAty.this.finish();
            }
        }
    };
    private final DialogInterface.OnClickListener B = new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.e
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i2) {
            CombinedAlertAty.this.m(dialogInterface, i2);
        }
    };
    private final DialogInterface.OnClickListener C = new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.f
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i2) {
            CombinedAlertAty.this.o(dialogInterface, i2);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        GaLog.a("MultiSubScreen_CombinedAlertAty", "conflictReplay mDeviceName = " + this.v + ", mDeviceId = " + this.u);
        if (TextUtils.isEmpty(this.u)) {
            return;
        }
        ConnectCodeMgr.h().y(this.u, "REJECT_CONNECT_CODE");
    }

    private void l() {
        Intent intent = getIntent();
        GaLog.a("MultiSubScreen_CombinedAlertAty", "handleIntent intent = " + intent);
        if (intent == null || !"com.zte.multi.subscreen.ACTION_SHOW_ALERT".equals(intent.getAction())) {
            finish();
            return;
        }
        this.x = DistributeBusMgr.getInstance();
        this.f8094n = intent.getIntExtra("dialog_type", -1);
        this.u = intent.getStringExtra("device_id");
        this.v = intent.getStringExtra("device_name");
        p(this.f8094n);
        AlertDialog alertDialog = this.f8093m;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f8093m.dismiss();
        }
        AlertDialog a2 = new AlertDialog.Builder(this).b(false).m(this.f8107q).c(true).e(this.f8108r).j(this.f8109s, this.B).g(this.t, this.C).a();
        this.f8093m = a2;
        Window window = a2.getWindow();
        if (window != null) {
            window.setType(2008);
        }
        this.f8093m.show();
        GameAssistDialog.f(this.f8093m.getWindow());
        int i2 = this.f8094n;
        if (i2 == 3) {
            this.f8106p.postDelayed(new Runnable() { // from class: cn.nubia.multisubscreen.ui.CombinedAlertAty.3
                @Override // java.lang.Runnable
                public void run() {
                    GaLog.a("MultiSubScreen_CombinedAlertAty", "handleIntent CONFLICT_ALERT timeout and finish!");
                    CombinedAlertAty.this.k();
                    CombinedAlertAty.this.finish();
                }
            }, 10000L);
        } else if (i2 == 2) {
            this.f8106p.postDelayed(new Runnable() { // from class: cn.nubia.multisubscreen.ui.CombinedAlertAty.4
                @Override // java.lang.Runnable
                public void run() {
                    GaLog.a("MultiSubScreen_CombinedAlertAty", "handleIntent CONFIRM_CONNECT_ALERT timeout and finish!");
                    ConnectCodeMgr.h().x("REJECT_CONNECT_CODE");
                    CombinedAlertAty.this.finish();
                }
            }, 30000L);
        }
        if (this.w) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zte.multi.subscreen.ACTION_CLOSE_ALERT");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.A, intentFilter, 2);
        this.w = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(DialogInterface dialogInterface, int i2) {
        this.y = false;
        int i3 = this.f8094n;
        if (i3 == 1) {
            GaLog.b("MultiSubScreen_CombinedAlertAty", "Disconnected role = " + MultiSubScreenUtils.h());
            if (MultiSubScreenUtils.v()) {
                MultiSubScreenUtils.H(null);
            } else {
                ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
            }
            finish();
            return;
        }
        if (i3 == 2) {
            if (MultiSubScreenUtils.f8175e == 2) {
                GaLog.a("MultiSubScreen_CombinedAlertAty", "now is required by pad and version is 2!");
                MultiSubScreenUtils.F(CastRole.SINK);
                ConnectCodeMgr.h().x("SINK_YES_CONNECTING_CODE");
            } else if (MultiSubScreenUtils.v()) {
                ListedDevice k2 = MultiSubScreenUtils.k();
                if (k2 != null) {
                    this.x.connectDevice(k2);
                } else {
                    DistributeBusMgr distributeBusMgr = this.x;
                    distributeBusMgr.connectDevice(distributeBusMgr.getSinkDeviceId());
                }
                ConnectCodeMgr.h().x("SOURCE_REPLY_TO_SINK_CONNECTED_CODE");
                MultiSubScreenUtils.w(k2 != null ? k2.getDeviceId() : this.x.getSinkDeviceId(), 2);
                MultiSubScreenNotificationMgr.g().a();
            } else {
                ConnectCodeMgr.h().x("SINK_YES_CONNECTING_CODE");
            }
            finish();
            return;
        }
        if (i3 != 3) {
            return;
        }
        MultiSubScreenUtils.f8183m = false;
        if (MultiSubScreenUtils.f8175e == 2) {
            if (MultiSubScreenUtils.v()) {
                DistributeBusMgr.getInstance().disConnectDevice(MultiSubScreenUtils.k());
            } else {
                ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
            }
            ConnectCodeMgr.h().y(this.u, "SINK_YES_CONNECTING_CODE");
            MultiSubScreenUtils.H(MultiSubScreenUtils.l(this.u));
            if (MultiSubScreenUtils.k() == null) {
                this.x.setSinkDistributeBus(this.u);
            }
        } else if (MultiSubScreenUtils.v()) {
            DistributeBusMgr.getInstance().disConnectDevice(MultiSubScreenUtils.k());
            this.x.connectDevice(this.u);
            ConnectCodeMgr.h().x("SOURCE_REPLY_TO_SINK_CONNECTED_CODE");
            ListedDevice k3 = MultiSubScreenUtils.k();
            MultiSubScreenUtils.w(k3 != null ? k3.getDeviceId() : this.x.getSinkDeviceId(), 2);
            MultiSubScreenNotificationMgr.g().a();
        } else {
            ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
            ConnectCodeMgr.h().y(this.u, "SINK_YES_CONNECTING_CODE");
            MultiSubScreenUtils.H(MultiSubScreenUtils.l(this.u));
            if (MultiSubScreenUtils.k() == null) {
                this.x.setSinkDistributeBus(this.u);
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(DialogInterface dialogInterface, int i2) {
        this.y = false;
        int i3 = this.f8094n;
        if (i3 == 1) {
            GaLog.a("MultiSubScreen_CombinedAlertAty", "showDisconnectDialog DISCONNECT_ALERT negative button clicked");
            finish();
            return;
        }
        if (i3 != 2) {
            if (i3 != 3) {
                return;
            }
            GaLog.a("MultiSubScreen_CombinedAlertAty", "showDisconnectDialog CONFLICT_ALERT negative button clicked");
            k();
            finish();
            return;
        }
        GaLog.a("MultiSubScreen_CombinedAlertAty", "NegativeButton clicked deviceId = " + this.u);
        ConnectCodeMgr.h().x("REJECT_CONNECT_CODE");
        finish();
    }

    private void p(int i2) {
        if (i2 == 1) {
            this.f8107q = getString(R.string.connection_dialog_title);
            this.f8108r = getString(R.string.disconnection_dailog_msg, new Object[]{this.v});
            this.f8109s = getString(R.string.confirm);
            this.t = getString(R.string.cancel);
            return;
        }
        if (i2 == 2) {
            this.f8107q = getString(R.string.connection_dialog_title);
            this.f8108r = getString(MultiSubScreenUtils.v() ? R.string.sink_connection_dialog_msg : R.string.connection_dialog_msg, new Object[]{this.v});
            this.f8109s = getString(R.string.confirm);
            this.t = getString(R.string.cancel);
            return;
        }
        if (i2 != 3) {
            return;
        }
        this.f8107q = getString(R.string.connection_dialog_title);
        this.f8108r = getString(R.string.cast_conflict_alert, new Object[]{MultiSubScreenUtils.k() == null ? MultiSubScreenUtils.i(this.x.getSinkDeviceId()) : MultiSubScreenUtils.k().getName(), this.v});
        this.f8109s = getString(R.string.confirm);
        this.t = getString(R.string.cancel);
    }

    @Override // cn.nubia.multisubscreen.ui.BaseAlertActivity
    protected void f() {
    }

    @Override // cn.nubia.multisubscreen.ui.BaseAlertActivity, com.zte.mifavor.widget.AlertActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        GaLog.a("MultiSubScreen_CombinedAlertAty", "onCreate");
        if (MultiSubScreenUtils.v()) {
            MultiSubScreenUtils.C(this.z);
        }
        RotationMgr.e(this).c(this);
        l();
    }

    @Override // cn.nubia.multisubscreen.ui.BaseAlertActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GaLog.a("MultiSubScreen_CombinedAlertAty", "onDestroy mIsNeedRejectWhenDestroy = " + this.y + ", mDialogType = " + this.f8094n);
        if (this.y) {
            int i2 = this.f8094n;
            if (i2 == 2) {
                ConnectCodeMgr.h().x("REJECT_CONNECT_CODE");
            } else if (i2 == 3) {
                k();
            }
        }
        this.f8106p.removeCallbacksAndMessages(null);
        RotationMgr.e(this).p(this);
        if (this.w) {
            unregisterReceiver(this.A);
            this.w = false;
        }
        MultiSubScreenUtils.N(this.z);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        GaLog.b("MultiSubScreen_CombinedAlertAty", "CombinedAlerAty onNewIntent intent = " + intent);
        setIntent(intent);
        l();
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        AlertDialog alertDialog = this.f8093m;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.f8093m.getWindow());
    }
}
