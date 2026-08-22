package cn.nubia.multisubscreen.mgr;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.componentcenter.api.power.IPowerStateMonitor;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameAssistComService;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.multisubscreen.CastRole;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.data.BatchData;
import cn.nubia.multisubscreen.data.TransferData;
import cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr;
import cn.nubia.multisubscreen.ui.CombinedAlertAty;
import cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity;
import cn.nubia.multisubscreen.utils.ACTION;
import cn.nubia.multisubscreen.utils.COMMAND;
import cn.nubia.multisubscreen.utils.LockScreenHelper;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConnectCodeMgr {

    /* renamed from: g, reason: collision with root package name */
    private static volatile ConnectCodeMgr f7901g;

    /* renamed from: c, reason: collision with root package name */
    private final Context f7904c;

    /* renamed from: d, reason: collision with root package name */
    private IPowerStateMonitor f7905d;

    /* renamed from: e, reason: collision with root package name */
    private final IPowerStateMonitor.PowerStateCallback f7906e = new IPowerStateMonitor.PowerStateCallback() { // from class: cn.nubia.multisubscreen.mgr.ConnectCodeMgr.1
        @Override // cn.nubia.componentcenter.api.power.IPowerStateMonitor.PowerStateCallback
        public void onGotoSleep() {
            ConnectCodeMgr.this.x("SOURCE_NOTIFY_SINK_SCREEN_OFF");
        }

        @Override // cn.nubia.componentcenter.api.power.IPowerStateMonitor.PowerStateCallback
        public void onWakingUp() {
            ConnectCodeMgr.this.x("SOURCE_NOTIFY_SINK_SCREEN_ON");
        }
    };

    /* renamed from: f, reason: collision with root package name */
    private StatusCallback f7907f = new StatusCallback() { // from class: cn.nubia.multisubscreen.mgr.ConnectCodeMgr.2
        @Override // cn.nubia.multisubscreen.callback.StatusCallback
        public void b(String str, int i2) {
            GaLog.e("MultiSubScreen_ConnectCodeMgr", "ConnectCodeMgr connect state change to " + i2);
            if (!MultiSubScreenUtils.v()) {
                if (i2 != 2) {
                    ConnectCodeMgr.this.f();
                }
            } else if (i2 == 2) {
                ConnectCodeMgr.this.w();
            } else if (i2 == 0) {
                ConnectCodeMgr.this.B();
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final DistributeBusMgr f7902a = DistributeBusMgr.getInstance();

    /* renamed from: b, reason: collision with root package name */
    private final MultiSubScreenNotificationMgr f7903b = MultiSubScreenNotificationMgr.g();

    private ConnectCodeMgr(Context context) {
        this.f7904c = context;
        MultiSubScreenUtils.C(this.f7907f);
    }

    private void A() {
        e();
        Intent intent = new Intent();
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        intent.setClass(this.f7904c, MultiSubScreenSinkActivity.class);
        this.f7904c.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        i().setListening(false, this.f7906e);
    }

    private TransferData d(String str) {
        TransferData transferData = new TransferData(COMMAND.CONNECT.name(), (MultiSubScreenUtils.v() ? ACTION.REQUEST_FROM_PRI : ACTION.REQUEST_FROM_SEC).name());
        transferData.setData(g(str));
        return transferData;
    }

    private void e() {
        this.f7904c.sendBroadcast(new Intent("com.zte.multi.subscreen.ACTION_CLOSE_ALERT"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f7904c.sendBroadcast(new Intent("com.zte.multi.subscreen.ACTION_CLOSE_ALT"));
    }

    public static ConnectCodeMgr h() {
        if (f7901g == null) {
            synchronized (ConnectCodeMgr.class) {
                try {
                    if (f7901g == null) {
                        f7901g = new ConnectCodeMgr(GameAssistApplication.j());
                    }
                } finally {
                }
            }
        }
        return f7901g;
    }

    private synchronized IPowerStateMonitor i() {
        try {
            if (this.f7905d == null) {
                this.f7905d = (IPowerStateMonitor) ((GameAssistComService) Router.getInstance().getService(GameAssistComService.class.getSimpleName())).a(IPowerStateMonitor.class);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7905d;
    }

    private void j(String str, ListedDevice listedDevice) {
        if (u(str, listedDevice)) {
            if (!MultiSubScreenUtils.v()) {
                f();
                e();
                MultiSubScreenUtils.H(null);
            }
            DistributeBusMgr.getInstance().disConnectDevice(str);
        }
    }

    private void k(String str, ListedDevice listedDevice) {
        if (u(str, listedDevice)) {
            this.f7902a.disConnectDevice(listedDevice);
            MultiSubScreenUtils.w(str, 3);
        }
    }

    private void l(String str, ListedDevice listedDevice) {
        ListedDevice l2 = MultiSubScreenUtils.l(str);
        int i2 = MultiSubScreenUtils.f8174d;
        if (i2 == 2) {
            if ((listedDevice == null || listedDevice.getDeviceId().equalsIgnoreCase(str)) && (listedDevice != null || str.equalsIgnoreCase(DistributeBusMgr.getInstance().getSinkDeviceId()))) {
                return;
            }
            z(str, l2, 3);
            return;
        }
        if (listedDevice != null && !str.equalsIgnoreCase(listedDevice.getDeviceId())) {
            y(listedDevice.getDeviceId(), "REJECT_CONNECT_CODE");
        } else if (listedDevice == null) {
            x("REJECT_CONNECT_CODE");
        }
        MultiSubScreenUtils.H(l2);
        ListedDevice k2 = MultiSubScreenUtils.k();
        if (k2 == null) {
            DistributeBusMgr.getInstance().setSinkDistributeBus(str);
        }
        z(str, k2, 2);
        if (i2 != 1) {
            MultiSubScreenUtils.f8174d = 1;
        }
    }

    private void m() {
        if (MultiSubScreenUtils.f8174d == 2) {
            GaLog.a("MultiSubScreen_ConnectCodeMgr", "sink in bg, delay 5 min stopListen");
            MultiSubScreenUtils.f8185o = false;
            PrimaryDeviceDataMgr.C().w0();
        }
    }

    private void n() {
        if (MultiSubScreenUtils.f8174d == 2) {
            GaLog.a("MultiSubScreen_ConnectCodeMgr", "sink in fg, check startListen");
            MultiSubScreenUtils.f8185o = true;
            PrimaryDeviceDataMgr.C().w();
        }
    }

    private void o(String str, ListedDevice listedDevice) {
        if (u(str, listedDevice)) {
            e();
            this.f7902a.disConnectDevice(listedDevice);
        }
    }

    private void p(String str, ListedDevice listedDevice) {
        if (u(str, listedDevice)) {
            MultiSubScreenUtils.w(str, 2);
            x("SOURCE_REPLY_TO_SINK_CONNECTED_CODE");
            this.f7903b.a();
        }
    }

    private void q() {
        if (MultiSubScreenUtils.f()) {
            LockScreenHelper.a().d();
        }
    }

    private void r() {
        if (MultiSubScreenUtils.f()) {
            LockScreenHelper.a().e();
        }
    }

    private void s(String str, ListedDevice listedDevice) {
        DistributeBusMgr distributeBusMgr = DistributeBusMgr.getInstance();
        if (listedDevice != null && !str.equalsIgnoreCase(listedDevice.getDeviceId())) {
            distributeBusMgr.disConnectDevice(listedDevice);
            MultiSubScreenUtils.H(MultiSubScreenUtils.l(str));
            listedDevice = MultiSubScreenUtils.k();
        }
        if (listedDevice == null) {
            distributeBusMgr.setSinkDistributeBus(str);
        }
        MultiSubScreenUtils.w(str, 2);
        A();
        this.f7903b.a();
    }

    private void t(ListedDevice listedDevice) {
        if (listedDevice != null) {
            this.f7902a.disConnectDevice(listedDevice);
        }
    }

    private boolean u(String str, ListedDevice listedDevice) {
        return (listedDevice != null && str.equalsIgnoreCase(listedDevice.getDeviceId())) || (listedDevice == null && str.equalsIgnoreCase(DistributeBusMgr.getInstance().getSinkDeviceId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        i().setListening(true, this.f7906e);
    }

    private void z(String str, ListedDevice listedDevice, int i2) {
        Intent intent = new Intent("com.zte.multi.subscreen.ACTION_SHOW_ALERT");
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        intent.putExtra("dialog_type", i2);
        Log.d("MultiSubScreen_ConnectCodeMgr", "showCombinedAlertDialog device: " + listedDevice);
        if (listedDevice != null) {
            intent.putExtra("device_id", listedDevice.getDeviceId());
            intent.putExtra("device_name", TextUtils.isEmpty(listedDevice.getName()) ? MultiSubScreenUtils.i(listedDevice.getDeviceId()) : listedDevice.getName());
        } else {
            intent.putExtra("device_id", str);
            intent.putExtra("device_name", MultiSubScreenUtils.i(str));
        }
        intent.setClass(this.f7904c, CombinedAlertAty.class);
        this.f7904c.startActivity(intent);
    }

    public String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", str);
            if (!"REQUIRED_CONNECT_CODE".equalsIgnoreCase(str)) {
                if ("SINK_YES_CONNECTING_CODE".equalsIgnoreCase(str)) {
                }
                return jSONObject.toString();
            }
            jSONObject.put("connect_device_name", MultiSubScreenUtils.m(this.f7904c));
            return jSONObject.toString();
        } catch (JSONException e2) {
            Log.e("MultiSubScreen_ConnectCodeMgr", "JSON exception", e2);
            return jSONObject.toString();
        }
    }

    public void v(String str, BatchData batchData) {
        String str2;
        ListedDevice k2 = MultiSubScreenUtils.k();
        GaLog.b("MultiSubScreen_ConnectCodeMgr", "processConnectCode gattDevice = " + k2 + ", deviceId = " + str);
        String[] keys = batchData.getKeys();
        int length = keys.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str3 = keys[i2];
            if ("code".equalsIgnoreCase(str3)) {
                GaLog.b("MultiSubScreen_ConnectCodeMgr", "processConnectCode key = " + batchData.get(str3));
                str2 = batchData.get("connect_device_name");
                GaLog.b("MultiSubScreen_ConnectCodeMgr", "processConnectCode sourceDeviceName = " + str2);
                String str4 = batchData.get(str3);
                str4.hashCode();
                switch (str4) {
                    case "NOTIFY_DISCONNECT_CODE":
                        j(str, k2);
                        break;
                    case "SOURCE_REPLY_TO_SINK_CONNECTED_CODE":
                        s(str, k2);
                        break;
                    case "REQUIRED_CONNECT_CODE":
                        if (MultiSubScreenUtils.u()) {
                            MultiSubScreenUtils.f8175e = 1;
                            MultiSubScreenUtils.F(CastRole.SOURCE);
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            MultiSubScreenUtils.G(str, str2);
                        }
                        l(str, k2);
                        break;
                    case "SOURCE_NOTIFY_SINK_SCREEN_ON":
                        r();
                        break;
                    case "SOURCE_REQUIRED_SINK_DISCONNECT_CODE":
                        t(k2);
                        break;
                    case "REQUIRED_CONNECT_FROM_PAD_CODE":
                        if (MultiSubScreenUtils.u()) {
                            MultiSubScreenUtils.f8175e = 2;
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            MultiSubScreenUtils.G(str, str2);
                        }
                        l(str, k2);
                        break;
                    case "SINK_YES_CONNECTING_CODE":
                        String str5 = batchData.get("connect_device_name");
                        GaLog.b("MultiSubScreen_ConnectCodeMgr", "processConnectCode SINK_YES_CONNECTING_CODE sinkDeviceName = " + str5);
                        if (!TextUtils.isEmpty(str5)) {
                            MultiSubScreenUtils.G(str, str5);
                        }
                        p(str, k2);
                        break;
                    case "REJECT_CONNECT_CODE":
                        k(str, k2);
                        break;
                    case "SOURCE_NOTIFY_SINK_SCREEN_OFF":
                        q();
                        break;
                    case "SINK_REQUIRED_DISCONNECT_CODE":
                        o(str, k2);
                        break;
                    case "SINK_NOTIFY_SOURCE_IN_BG":
                        m();
                        break;
                    case "SINK_NOTIFY_SOURCE_IN_FG":
                        n();
                        break;
                }
            }
        }
    }

    public void x(String str) {
        this.f7902a.sendTransferData(d(str));
    }

    public void y(String str, String str2) {
        this.f7902a.sendTransferData(str, d(str2));
    }
}
