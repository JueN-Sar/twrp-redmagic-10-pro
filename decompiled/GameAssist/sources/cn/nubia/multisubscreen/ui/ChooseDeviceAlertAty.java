package cn.nubia.multisubscreen.ui;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.multisubscreen.CastStatus;
import cn.nubia.multisubscreen.DeviceAdapter;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.mgr.ConnectCodeMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.extres.R;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ChooseDeviceAlertAty extends BaseAlertActivity implements LifecycleOwner, GameMonitor.Callback, RotationMgr.Callback {

    /* renamed from: p, reason: collision with root package name */
    protected LifecycleRegistry f8097p;

    /* renamed from: q, reason: collision with root package name */
    View f8098q;

    /* renamed from: r, reason: collision with root package name */
    TextView f8099r;

    /* renamed from: s, reason: collision with root package name */
    RecyclerView f8100s;
    private DeviceAdapter t;
    private int v;
    private List u = new ArrayList();
    private Handler w = new Handler(Looper.getMainLooper());
    private StatusCallback x = new StatusCallback() { // from class: cn.nubia.multisubscreen.ui.ChooseDeviceAlertAty.1
        @Override // cn.nubia.multisubscreen.callback.StatusCallback
        public void a(ListedDevice listedDevice) {
            GaLog.b("MultiSubScreen_ChooseDeviceAlertAty", "ChooseDeviceAlertAty onStatusChange device.getStatus() = " + listedDevice.getStatus());
            if (listedDevice.getStatus() == 102 && MultiSubScreenUtils.f8174d == 1) {
                if (MultiSubScreenUtils.u()) {
                    ConnectCodeMgr.h().x("REQUIRED_CONNECT_CODE");
                } else {
                    ConnectCodeMgr.h().x("REQUIRED_CONNECT_FROM_PAD_CODE");
                }
            }
        }

        @Override // cn.nubia.multisubscreen.callback.StatusCallback
        public void b(String str, int i2) {
            if (i2 == 2) {
                ChooseDeviceAlertAty.this.L(str, CastStatus.STATUS_BLE_CONNECTED);
                ChooseDeviceAlertAty.this.finish();
            } else if (i2 == 3) {
                ChooseDeviceAlertAty.this.L(str, CastStatus.STATUS_BLE_CONNECT_FAIL);
            } else if (i2 == 1) {
                ChooseDeviceAlertAty.this.L(str, CastStatus.STATUS_BLE_CONNECTING);
            } else {
                ChooseDeviceAlertAty.this.L(str, CastStatus.STATUS_DEFAULT);
            }
        }
    };
    Runnable y = new Runnable() { // from class: cn.nubia.multisubscreen.ui.ChooseDeviceAlertAty.5
        @Override // java.lang.Runnable
        public void run() {
            if (ChooseDeviceAlertAty.this.u.isEmpty()) {
                ChooseDeviceAlertAty.this.H();
            }
            ChooseDeviceAlertAty.this.f8098q.setVisibility(8);
            ChooseDeviceAlertAty.this.v = 2;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(DialogInterface dialogInterface, int i2) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(DialogInterface dialogInterface, int i2) {
        K();
        finish();
    }

    private void C() {
        MultiSubScreenUtils.j().i(this, new Observer() { // from class: cn.nubia.multisubscreen.ui.b
            @Override // androidx.lifecycle.Observer
            public final void a(Object obj) {
                ChooseDeviceAlertAty.this.y((ArrayList) obj);
            }
        });
    }

    private void D() {
        AlertDialog alertDialog = this.f8093m;
        if (alertDialog != null) {
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.multisubscreen.ui.a
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    ChooseDeviceAlertAty.this.z(dialogInterface);
                }
            });
        }
    }

    private void E() {
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "showDeviceList");
        TextView textView = this.f8099r;
        if (textView != null) {
            textView.setVisibility(8);
        }
        RecyclerView recyclerView = this.f8100s;
        if (recyclerView != null) {
            recyclerView.setVisibility(0);
        }
    }

    private void F() {
        this.f8094n = 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_ZTE_Light_Dialog_Alert);
        builder.l(cn.nubia.gameassist.R.string.connection_dialog_title).c(true).e(getString(cn.nubia.gameassist.R.string.disconnection_dailog_msg, new Object[]{MultiSubScreenUtils.k() != null ? MultiSubScreenUtils.k().getName() : MultiSubScreenUtils.i(DistributeBusMgr.getInstance().getSinkDeviceId())})).f(cn.nubia.gameassist.R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.ChooseDeviceAlertAty.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ChooseDeviceAlertAty.this.finish();
            }
        }).i(cn.nubia.gameassist.R.string.disconnect_msg, new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.ChooseDeviceAlertAty.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                MultiSubScreenUtils.D(true);
                if (MultiSubScreenUtils.v()) {
                    ChooseDeviceAlertAty.this.s();
                } else {
                    ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
                }
                ChooseDeviceAlertAty.this.finish();
            }
        });
        AlertDialog a2 = builder.a();
        this.f8093m = a2;
        Window window = a2.getWindow();
        if (window != null) {
            window.setType(2008);
        }
        this.f8093m.show();
        GameAssistDialog.f(this.f8093m.getWindow());
    }

    private void G() {
        this.f8094n = 6;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_ZTE_Light_Dialog_Alert);
        builder.l(cn.nubia.gameassist.R.string.connection_dialog_title).c(true).d(cn.nubia.gameassist.R.string.goto_and_open_multi_sub_screen_popup).f(cn.nubia.gameassist.R.string.cancel_set_multi_sub_screen, new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ChooseDeviceAlertAty.this.A(dialogInterface, i2);
            }
        }).i(cn.nubia.gameassist.R.string.goto_and_set_multi_sub_screen, new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.d
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ChooseDeviceAlertAty.this.B(dialogInterface, i2);
            }
        });
        AlertDialog a2 = builder.a();
        this.f8093m = a2;
        Window window = a2.getWindow();
        if (window != null) {
            window.setType(2008);
        }
        this.f8093m.show();
        GameAssistDialog.f(this.f8093m.getWindow());
    }

    private void I() {
        J();
        this.w.postDelayed(this.y, 35000L);
    }

    private void J() {
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "showSearching");
        this.f8098q.setVisibility(0);
        this.f8100s.setVisibility(8);
        this.f8099r.setVisibility(8);
        this.v = 1;
    }

    private void K() {
        startActivity(new Intent(this, (Class<?>) MultiSubScreenSourceActivity.class).setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(String str, CastStatus castStatus) {
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "updateDeviceItemStatus -> castStatus = " + castStatus);
        if (!this.u.isEmpty()) {
            for (DeviceAdapter.DeviceItem deviceItem : this.u) {
                if (deviceItem.f7892b.getDeviceId().equalsIgnoreCase(str) && (deviceItem.f7891a != CastStatus.STATUS_BLE_CONNECT_FAIL || castStatus != CastStatus.STATUS_DEFAULT)) {
                    deviceItem.f7891a = castStatus;
                }
            }
        }
        this.t.S(this.u);
    }

    private void q(ListedDevice listedDevice) {
        ListedDevice listedDevice2 = this.t.f7890d;
        if (listedDevice.getDeviceId().equalsIgnoreCase(listedDevice2 != null ? listedDevice2.getDeviceId() : "")) {
            this.u.add(new DeviceAdapter.DeviceItem(u(), listedDevice));
        } else {
            this.u.add(new DeviceAdapter.DeviceItem(CastStatus.STATUS_DEFAULT, listedDevice));
        }
    }

    private void r() {
        this.f8094n = 4;
        DistributeBusMgr.getInstance().setScanNeeded(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_ZTE_Light_Dialog_Alert);
        View inflate = LayoutInflater.from(this).inflate(cn.nubia.gameassist.R.layout.multi_sub_screen_choose_device, (ViewGroup) null);
        builder.l(cn.nubia.gameassist.R.string.multi_subscreen_choose_device).c(true).n(inflate).f(cn.nubia.gameassist.R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.multisubscreen.ui.ChooseDeviceAlertAty.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ChooseDeviceAlertAty.this.s();
            }
        });
        this.f8098q = inflate.findViewById(cn.nubia.gameassist.R.id.searching_container);
        this.f8099r = (TextView) inflate.findViewById(cn.nubia.gameassist.R.id.no_devices_found);
        this.f8100s = (RecyclerView) inflate.findViewById(cn.nubia.gameassist.R.id.devices_list);
        this.f8100s.setLayoutManager(new LinearLayoutManager(this));
        DeviceAdapter deviceAdapter = new DeviceAdapter(this.u);
        this.t = deviceAdapter;
        this.f8100s.setAdapter(deviceAdapter);
        AlertDialog a2 = builder.a();
        this.f8093m = a2;
        Window window = a2.getWindow();
        if (window != null) {
            window.setType(2008);
        }
        this.f8093m.show();
        GameAssistDialog.f(this.f8093m.getWindow());
        I();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (MultiSubScreenUtils.v()) {
            DistributeBusMgr.getInstance().disConnectDevice(MultiSubScreenUtils.k());
        } else {
            ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
        }
        finish();
    }

    private void t(String str, ArrayList arrayList) {
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "filtrateDevices -> serviceUuid = " + str);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListedDevice listedDevice = (ListedDevice) it.next();
            GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "filtrateDevices -> device = " + listedDevice);
            Iterator<ListedDevice.ServiceItem> it2 = listedDevice.serviceList.iterator();
            while (it2.hasNext()) {
                if ("ceb574816000".equalsIgnoreCase(it2.next().getUuid())) {
                    q(listedDevice);
                }
            }
        }
    }

    private CastStatus u() {
        CastStatus castStatus = CastStatus.STATUS_DEFAULT;
        int i2 = MultiSubScreenUtils.f8174d;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? castStatus : CastStatus.STATUS_BLE_CONNECT_FAIL : CastStatus.STATUS_BLE_CONNECTED : CastStatus.STATUS_BLE_CONNECTING;
    }

    private void v() {
        if (getIntent().getBooleanExtra("IS_SHOW_DISCONNECT_DIALOG", false) || MultiSubScreenUtils.f8174d == 2) {
            F();
        } else {
            r();
            w();
        }
    }

    private void w() {
        C();
        MultiSubScreenUtils.C(this.x);
    }

    private void x() {
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.f8097p = lifecycleRegistry;
        lifecycleRegistry.m(Lifecycle.State.CREATED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(ArrayList arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("registerObserver -> onChanged count = ");
        sb.append(arrayList == null ? 0 : arrayList.size());
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", sb.toString());
        this.u.clear();
        if (arrayList == null || arrayList.isEmpty()) {
            this.f8100s.setVisibility(8);
        } else {
            t("ceb574816000", arrayList);
            E();
            this.f8100s.setVisibility(0);
        }
        this.t.S(this.u);
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "getItemCount is " + this.t.m());
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "mDeviceList isEmpty = " + this.u.isEmpty());
        if (this.v == 2 && this.u.isEmpty()) {
            H();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(DialogInterface dialogInterface) {
        if (this.f8094n == 4 && MultiSubScreenUtils.f8174d == 1) {
            s();
        } else {
            finish();
        }
    }

    public void H() {
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "showNoDevices");
        this.f8098q.setVisibility(8);
        this.f8100s.setVisibility(8);
        this.f8099r.setVisibility(0);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        return this.f8097p;
    }

    @Override // cn.nubia.multisubscreen.ui.BaseAlertActivity
    protected void f() {
    }

    @Override // cn.nubia.multisubscreen.ui.BaseAlertActivity, com.zte.mifavor.widget.AlertActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "onCreate");
        x();
        int i2 = Settings.Global.getInt(getContentResolver(), "multi_sub_screen_enable", 0);
        if (ZteFeature.IS_INTER_VERSION && i2 == 0) {
            G();
        } else {
            v();
        }
        D();
        RotationMgr.e(this).c(this);
        SystemMgr.y(this).h(this);
    }

    @Override // cn.nubia.multisubscreen.ui.BaseAlertActivity, android.app.Activity
    protected void onDestroy() {
        MultiSubScreenUtils.N(this.x);
        SystemMgr.y(this).i(this);
        RotationMgr.e(this).p(this);
        super.onDestroy();
        DistributeBusMgr.getInstance().setScanNeeded(false);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onFullscreenActivityChange */
    public void p(ComponentName componentName) {
        String packageName = componentName.getPackageName();
        GaLog.a("MultiSubScreen_ChooseDeviceAlertAty", "onFullscreenActivityChange pkg = " + packageName);
        if ("cn.nubia.gamelauncher".equals(packageName) || "com.zte.mifavor.launcher".equals(packageName) || "cn.nubia.redmagickyi".equals(packageName)) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.f8097p.m(Lifecycle.State.STARTED);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.f8097p.m(Lifecycle.State.RESUMED);
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
