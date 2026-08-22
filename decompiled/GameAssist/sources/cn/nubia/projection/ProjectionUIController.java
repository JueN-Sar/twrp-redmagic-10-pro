package cn.nubia.projection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import cn.nubia.projection.ProjectionUIController;
import cn.nubia.projection.ui.NubiaProjectionExpandedPanel;
import cn.nubia.projection.ui.NubiaProjectionPanel;
import cn.nubia.projection.ui.ProjectionWindowView;
import cn.nubia.projection.util.PLog;
import cn.nubia.projection.util.ProjectionUtil;
import com.google.android.material.card.MaterialCardView;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.permission.PermissionApplyActivity;
import com.zte.gameassist.view.PassThroughView;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.MyOsUtilsWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class ProjectionUIController {
    private static final Uri M = Uri.parse("content://cn.nubia.touping.TouPingProvider/");
    private int A;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private boolean F;
    private boolean G;
    private boolean H;
    private PermissionCallback I;
    AlertDialog J;

    /* renamed from: a, reason: collision with root package name */
    private View f8827a;

    /* renamed from: b, reason: collision with root package name */
    private ImageView f8828b;

    /* renamed from: c, reason: collision with root package name */
    private NubiaProjectionPanel f8829c;

    /* renamed from: d, reason: collision with root package name */
    private NubiaProjectionExpandedPanel f8830d;

    /* renamed from: e, reason: collision with root package name */
    private ProjectionWindowView f8831e;

    /* renamed from: f, reason: collision with root package name */
    private PassThroughView f8832f;

    /* renamed from: g, reason: collision with root package name */
    private Context f8833g;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager.LayoutParams f8835i;

    /* renamed from: j, reason: collision with root package name */
    private WindowManager.LayoutParams f8836j;

    /* renamed from: k, reason: collision with root package name */
    private WindowManager f8837k;

    /* renamed from: l, reason: collision with root package name */
    private PackageManager f8838l;

    /* renamed from: m, reason: collision with root package name */
    private BluetoothProfile f8839m;

    /* renamed from: n, reason: collision with root package name */
    private HidHostServiceListener f8840n;

    /* renamed from: o, reason: collision with root package name */
    private BluetoothReceiver f8841o;

    /* renamed from: p, reason: collision with root package name */
    private ProjectionContentObserver f8842p;
    private volatile String u;
    private int z;

    /* renamed from: q, reason: collision with root package name */
    private boolean f8843q = false;

    /* renamed from: r, reason: collision with root package name */
    private boolean f8844r = false;

    /* renamed from: s, reason: collision with root package name */
    private boolean f8845s = false;
    private volatile boolean t = false;
    private boolean v = false;
    private int w = 0;
    private boolean x = false;
    private int y = 0;
    private final Runnable K = new AnonymousClass2();
    private final String L = "cn.nubia.dualscreen.aod.MainActivity";

    /* renamed from: h, reason: collision with root package name */
    private ProjectionManager f8834h = ProjectionManager.o();

    /* renamed from: cn.nubia.projection.ProjectionUIController$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(View view) {
            ProjectionUIController.this.P();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(View view) {
            ProjectionUIController.this.P();
            ProjectionUIController.this.f8833g.startActivity(new Intent().setClassName("com.android.settings", "com.android.settings.Settings$BluetoothDashboardFragmentActivity").setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED));
        }

        @Override // java.lang.Runnable
        public void run() {
            View f2 = InflaterHelper.f(R.layout.dialog_gamebox_open_prompt, null);
            TextView textView = (TextView) f2.findViewById(R.id.tv_dialog_message);
            TextView textView2 = (TextView) f2.findViewById(R.id.tv_dialog_sub_message);
            TextView textView3 = (TextView) f2.findViewById(R.id.tv_to_bluetooth_setting);
            f2.findViewById(R.id.btn_know).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProjectionUIController.AnonymousClass2.this.c(view);
                }
            });
            textView2.setText(ProjectionUIController.this.f8833g.getString(R.string.dialog_game_box_open_not_devices_sub_content));
            if (ProjectionUIController.this.B) {
                textView.setText(ProjectionUIController.this.f8833g.getString(R.string.dialog_game_box_open_have_devices_content2));
                textView3.setVisibility(8);
            } else {
                textView.setText(ProjectionUIController.this.f8833g.getString(R.string.dialog_game_box_open_not_devices_content2));
                textView3.setVisibility(0);
                textView3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.r
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ProjectionUIController.AnonymousClass2.this.d(view);
                    }
                });
            }
            ContextWrapper.updateDisplay(ProjectionUIController.this.f8833g);
            ProjectionUIController projectionUIController = ProjectionUIController.this;
            projectionUIController.J = new AlertDialog.Builder(projectionUIController.f8833g, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(R.string.dialog_game_box_open_title).c(true).n(f2).a();
            ProjectionUIController.this.J.getWindow().setType(2008);
            ProjectionUIController.this.J.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            ProjectionUIController.this.J.setCanceledOnTouchOutside(false);
            ProjectionUIController.this.J.show();
        }
    }

    private final class BluetoothReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (ProjectionUIController.this.u0()) {
                if (ProjectionUIController.this.x0((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"))) {
                    ProjectionUIController.this.P();
                    ProjectionUIController.this.h1();
                }
            }
        }

        private BluetoothReceiver() {
        }
    }

    private final class HidHostServiceListener implements BluetoothProfile.ServiceListener {
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i2, BluetoothProfile bluetoothProfile) {
            ProjectionUIController.this.f8839m = bluetoothProfile;
            if (ProjectionUIController.this.f8839m != null) {
                if (!ProjectionUIController.this.v0()) {
                    ProjectionUIController.this.h1();
                    return;
                }
                if (ContextCompat.a(ProjectionUIController.this.f8833g, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    ProjectionUIController.this.i0();
                    return;
                }
                if (ProjectionUIController.this.I == null) {
                    ProjectionUIController projectionUIController = ProjectionUIController.this;
                    projectionUIController.I = new PermissionCallback();
                }
                PermissionApplyActivity.k(ProjectionUIController.this.f8833g, ProjectionUIController.this.I, new String[]{"android.permission.BLUETOOTH_CONNECT"});
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i2) {
            ProjectionUIController.this.B = false;
            ProjectionUIController.this.g1();
        }

        private HidHostServiceListener() {
        }
    }

    private class PermissionCallback implements PermissionApplyActivity.RequestPermissionListener {
        @Override // com.zte.gameassist.permission.PermissionApplyActivity.RequestPermissionListener
        public void a() {
            ProjectionUIController.this.O0();
            ProjectionUIController.this.b1();
        }

        @Override // com.zte.gameassist.permission.PermissionApplyActivity.RequestPermissionListener
        public void onSuccess() {
            ProjectionUIController.this.i0();
        }

        private PermissionCallback() {
        }
    }

    class ProjectionContentObserver extends ContentObserver {
        public ProjectionContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            PLog.e("onChange uri=" + uri);
            if (Settings.Global.getUriFor("app_mirror_displayid").equals(uri)) {
                ProjectionUIController.this.e0();
                return;
            }
            if (Settings.Global.getUriFor("gamebox_mirror_displayid").equals(uri)) {
                ProjectionUIController.this.h0();
                return;
            }
            if (Settings.Global.getUriFor("mirror_input_status").equals(uri)) {
                boolean z2 = Settings.Global.getInt(ProjectionUIController.this.f8833g.getContentResolver(), "mirror_input_status", 0) > 0;
                PLog.e("onChange, showInputmethod =" + z2);
                if (z2) {
                    ProjectionUIController.this.P();
                    return;
                }
                return;
            }
            if (Settings.Global.getUriFor("app_mirror_list").equals(uri)) {
                ProjectionUIController.this.f0();
                return;
            }
            if (Settings.Global.getUriFor("nubia_virtual_handle_enable").equals(uri)) {
                ProjectionUIController.this.k0();
                return;
            }
            if (Settings.Global.getUriFor("tp_type_for_games").equals(uri)) {
                ProjectionUIController projectionUIController = ProjectionUIController.this;
                projectionUIController.w = Settings.Global.getInt(projectionUIController.f8833g.getContentResolver(), "tp_type_for_games", 0);
                PLog.e("onChange, mProjectionType =" + ProjectionUIController.this.w);
                ProjectionUIController.this.f1(ProjectionWindowView.UIStatus.PROJECTION_TYPE_CHANGE);
                return;
            }
            if (Settings.Global.getUriFor("isCurrenSuspendStatus").equals(uri)) {
                ProjectionUIController projectionUIController2 = ProjectionUIController.this;
                projectionUIController2.x = Settings.Global.getInt(projectionUIController2.f8833g.getContentResolver(), "isCurrenSuspendStatus", 0) > 0;
                PLog.e("onChange, mProjectionSuspend =" + ProjectionUIController.this.x);
                ProjectionUIController.this.f1(ProjectionWindowView.UIStatus.UPDATE_SUSPEND);
                return;
            }
            if (Settings.Global.getUriFor("nb_app_mirror_support_fit").equals(uri)) {
                ProjectionUIController projectionUIController3 = ProjectionUIController.this;
                projectionUIController3.D = Settings.Global.getInt(projectionUIController3.f8833g.getContentResolver(), "nb_app_mirror_support_fit", 0);
                ProjectionUIController.this.f8830d.q0(ProjectionUIController.this.D, ProjectionUIController.this.E);
                return;
            }
            if (Settings.Global.getUriFor("nb_app_mirror_now_fit").equals(uri)) {
                ProjectionUIController projectionUIController4 = ProjectionUIController.this;
                projectionUIController4.E = Settings.Global.getInt(projectionUIController4.f8833g.getContentResolver(), "nb_app_mirror_now_fit", 0);
                ProjectionUIController.this.f8830d.q0(ProjectionUIController.this.D, ProjectionUIController.this.E);
            } else if (Settings.Global.getUriFor("cc_nubia_game_key").equals(uri)) {
                ProjectionUIController projectionUIController5 = ProjectionUIController.this;
                projectionUIController5.F = Settings.Global.getInt(projectionUIController5.f8833g.getContentResolver(), "cc_nubia_game_key", 0) == 1;
                ProjectionUIController.this.e1();
            } else if (Settings.System.getUriFor("fourth_physical_key_function_value").equals(uri)) {
                ProjectionUIController projectionUIController6 = ProjectionUIController.this;
                projectionUIController6.G = Settings.System.getInt(projectionUIController6.f8833g.getContentResolver(), "fourth_physical_key_function_value", 2) == 2;
            } else if (Settings.Secure.getUriFor("default_home").equals(uri)) {
                ProjectionUIController.this.g0();
            }
        }
    }

    public ProjectionUIController(Context context) {
        this.f8833g = context;
        this.f8838l = this.f8833g.getPackageManager();
        this.f8837k = (WindowManager) this.f8833g.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F0() {
        if (this.f8843q || this.f8834h.C()) {
            return;
        }
        K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G0(Drawable drawable) {
        this.f8829c.setExpandedAppIcon(drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H0() {
        f1(ProjectionWindowView.UIStatus.PROJECTION_MODE_CHANGE);
    }

    private Drawable I0() {
        ComponentName T = T();
        if (T == null) {
            return null;
        }
        try {
            int i2 = this.f8838l.getActivityInfo(T, 0).icon;
            if (!"cn.nubia.dualscreen.aod.MainActivity".equals(T.getClassName())) {
                return i2 != 0 ? this.f8838l.getActivityIcon(T) : this.f8838l.getApplicationIcon(Y());
            }
            PLog.e("loadIcon aod icon");
            return this.f8838l.getApplicationIcon("cn.nubia.diyaod");
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void K0() {
        PLog.a("on destroy");
        i1();
        h1();
        j0();
        this.f8834h.H();
    }

    private void N() {
        ProjectionWindowView projectionWindowView;
        if (!this.f8834h.C()) {
            b0().postDelayed(new Runnable() { // from class: cn.nubia.projection.p
                @Override // java.lang.Runnable
                public final void run() {
                    ProjectionUIController.this.F0();
                }
            }, 5000L);
        } else {
            if (V() || (projectionWindowView = this.f8831e) == null || projectionWindowView.getDelayRemoveForFullScreen()) {
                return;
            }
            PLog.e("need reAdd window");
            M(false);
        }
    }

    private void N0() {
        if (v0()) {
            e1();
            boolean isEnabled = BluetoothAdapter.getDefaultAdapter().isEnabled();
            P0();
            if (isEnabled) {
                return;
            }
            this.B = false;
            b1();
        }
    }

    private void O() {
        this.f8834h.M();
        P();
        h1();
        this.C = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O0() {
        if (this.f8841o == null) {
            this.f8833g.registerReceiver(new BluetoothReceiver(), new IntentFilter("android.bluetooth.device.action.ACL_CONNECTED"), 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        b0().removeCallbacks(this.K);
        AlertDialog alertDialog = this.J;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.J.dismiss();
    }

    private void P0() {
        if (this.f8840n == null) {
            this.B = false;
            this.f8840n = new HidHostServiceListener();
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(this.f8833g, this.f8840n, 4);
        }
    }

    private void Q0() {
        String str;
        String str2;
        if (this.f8842p == null) {
            this.f8842p = new ProjectionContentObserver(S());
            str2 = "app_mirror_status";
            str = "default_home";
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("app_mirror_status"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("app_mirror_displayid"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("gamebox_mirror_displayid"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("mirror_input_status"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("app_mirror_list"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_virtual_handle_enable"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("tp_type_for_games"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("isCurrenSuspendStatus"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nb_app_mirror_support_fit"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nb_app_mirror_now_fit"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Global.getUriFor("cc_nubia_game_key"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.System.getUriFor("fourth_physical_key_function_value"), false, this.f8842p);
            this.f8833g.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(str), false, this.f8842p);
            PLog.a("register observer");
        } else {
            str = "default_home";
            str2 = "app_mirror_status";
        }
        this.f8842p.onChange(true, Settings.Global.getUriFor(str2));
        this.f8842p.onChange(true, Settings.Global.getUriFor("app_mirror_displayid"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("gamebox_mirror_displayid"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("mirror_input_status"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("app_mirror_list"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("nubia_virtual_handle_enable"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("tp_type_for_games"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("isCurrenSuspendStatus"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("nb_app_mirror_support_fit"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("nb_app_mirror_now_fit"));
        this.f8842p.onChange(true, Settings.Global.getUriFor("cc_nubia_game_key"));
        this.f8842p.onChange(true, Settings.System.getUriFor("fourth_physical_key_function_value"));
        this.f8842p.onChange(true, Settings.Secure.getUriFor(str));
    }

    private Handler S() {
        return this.f8834h.m();
    }

    private ComponentName T() {
        if (this.u == null) {
            return null;
        }
        return ComponentName.unflattenFromString(this.u);
    }

    private void V0() {
        f1(ProjectionWindowView.UIStatus.RESET_UI);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b1() {
        if (this.C || !v0()) {
            return;
        }
        AlertDialog alertDialog = this.J;
        if (alertDialog == null || !alertDialog.isShowing()) {
            if (p0()) {
                PLog.e("3d mode no need this dialog");
                return;
            }
            b0().removeCallbacks(this.K);
            b0().postDelayed(this.K, 200L);
            this.C = true;
        }
    }

    private Bitmap d1() {
        HashMap hashMap = new HashMap();
        hashMap.put("display", this.f8833g.getDisplay());
        hashMap.put("crop", new Rect(0, 0, this.f8831e.getScreenShotViewWidth(), this.f8831e.getScreenShotViewHeight()));
        return MyOsUtilsWrapper.nubiaScreenshot(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0() {
        int i2 = Settings.Global.getInt(this.f8833g.getContentResolver(), "app_mirror_displayid", 0);
        PLog.e("onChange id=" + i2 + " " + this.f8843q);
        this.A = i2;
        if (v0()) {
            return;
        }
        this.y = i2 > 0 ? 2 : 1;
        if (this.f8843q) {
            if (i2 > 0) {
                j1();
                e1();
            } else {
                V0();
                J0(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e1() {
        b0().post(new Runnable() { // from class: cn.nubia.projection.n
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionUIController.this.H0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0() {
        this.u = Settings.Global.getString(this.f8833g.getContentResolver(), "app_mirror_list");
        if (y0()) {
            j1();
            Z0(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g0() {
        boolean equals = "com.zte.usmartlauncher".equals(Settings.Secure.getString(this.f8833g.getContentResolver(), "default_home"));
        this.H = equals;
        if (equals && r0()) {
            PLog.a("cloud computer force exit expanded projection");
            W0(2, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g1() {
        BluetoothReceiver bluetoothReceiver = this.f8841o;
        if (bluetoothReceiver != null) {
            this.f8833g.unregisterReceiver(bluetoothReceiver);
            this.f8841o = null;
        }
        this.I = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0() {
        PLog.e("onChange displayId=" + this.z + " " + this.f8843q);
        int i2 = Settings.Global.getInt(this.f8833g.getContentResolver(), "gamebox_mirror_displayid", 0);
        if (this.z == i2) {
            PLog.e("onChange gameBoxMirrorId=" + i2 + " ,gameBoxMirrorId has no change!");
            return;
        }
        this.z = i2;
        if (i2 > 0) {
            this.y = 3;
            N0();
        } else if (this.y == 3) {
            this.y = 1;
            O();
        } else {
            O();
        }
        if (!this.f8843q || this.z > 0) {
            return;
        }
        V0();
        J0(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h1() {
        if (this.f8839m != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(4, this.f8839m);
                this.f8840n = null;
                this.f8839m = null;
            } catch (Throwable th) {
                PLog.d("Error cleaning up HID proxy", th);
            }
        }
        g1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i0() {
        List<BluetoothDevice> devicesMatchingConnectionStates;
        BluetoothProfile bluetoothProfile = this.f8839m;
        if (bluetoothProfile == null || (devicesMatchingConnectionStates = bluetoothProfile.getDevicesMatchingConnectionStates(new int[]{2})) == null || devicesMatchingConnectionStates.isEmpty()) {
            O0();
        } else {
            this.B = true;
        }
        b1();
    }

    private void i1() {
        if (this.f8842p == null) {
            return;
        }
        this.f8833g.getContentResolver().unregisterContentObserver(this.f8842p);
        this.f8842p = null;
        PLog.a("unregister observer");
    }

    private void j0() {
        this.f8843q = false;
        f1(ProjectionWindowView.UIStatus.REMOVE_WINDOW);
        S0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k0() {
        boolean z = Settings.Global.getInt(this.f8833g.getContentResolver(), "nubia_virtual_handle_enable", 0) > 0;
        this.v = z;
        if (z) {
            f1(ProjectionWindowView.UIStatus.VIRTUAL_HANDLES_ENABLE);
        } else {
            f1(ProjectionWindowView.UIStatus.VIRTUAL_HANDLES_DISABLE);
        }
    }

    private void n0() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = this.f8831e.getScreenShotViewWidth();
        layoutParams.height = this.f8831e.getScreenShotViewHeight();
        layoutParams.gravity = 51;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.setTitle("ProjectionScreenShot");
        layoutParams.format = -2;
        layoutParams.type = 2008;
        layoutParams.flags = 792;
        this.f8836j = layoutParams;
    }

    private void o0() {
        int dimensionPixelSize = this.f8833g.getResources().getDimensionPixelSize(R.dimen.projection_window_default_x);
        int dimensionPixelSize2 = this.f8833g.getResources().getDimensionPixelSize(R.dimen.projection_window_default_y);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START;
        layoutParams.x = dimensionPixelSize;
        layoutParams.y = dimensionPixelSize2;
        layoutParams.setTitle("ProjectionIcon");
        layoutParams.format = -2;
        layoutParams.type = 2008;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        WindowManagerWrapper.LayoutParams.addHidePrivateFlags(layoutParams, 64);
        WindowManagerWrapper.LayoutParams.addHidePrivateFlags(layoutParams, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
        layoutParams.flags = 296;
        layoutParams.layoutInDisplayCutoutMode = 3;
        this.f8835i = layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean u0() {
        AlertDialog alertDialog = this.J;
        return alertDialog != null && alertDialog.isShowing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x0(BluetoothDevice bluetoothDevice) {
        return (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null || bluetoothDevice.getBluetoothClass().getMajorDeviceClass() != 1280) ? false : true;
    }

    public boolean A0() {
        if (C0()) {
            return this.x;
        }
        return false;
    }

    public boolean B0() {
        return (this.f8834h.B() || v0()) ? false : true;
    }

    public boolean C0() {
        int c0 = c0();
        return this.f8834h.P() && (c0 == 3 || c0 == 2) && !r0();
    }

    public boolean D0() {
        return t0() && this.f8834h.O();
    }

    public boolean E0() {
        return this.v;
    }

    public void J0(boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("SMALL_MIRROR_STATUS", z);
            PLog.e("notifyDisplayNotChangOnOrientationChanged " + this.f8833g.getContentResolver().call(M, "", "CALL_9", bundle));
        } catch (Exception e2) {
            PLog.c("notifyDisplayNotChangOnOrientationChanged error", e2);
        }
    }

    public void L() {
        if (this.f8831e == null || this.f8844r) {
            return;
        }
        l0();
        ImageView imageView = (ImageView) this.f8827a.findViewById(R.id.iv_screen_shot);
        this.f8828b = imageView;
        imageView.setBackground(new BitmapDrawable(d1()));
        this.f8828b.setAlpha(1.0f);
        this.f8828b.setScaleX(1.0f);
        this.f8828b.setScaleY(1.0f);
        this.f8828b.setTranslationX(0.0f);
        this.f8828b.setTranslationY(0.0f);
        try {
            this.f8837k.addView(this.f8827a, this.f8836j);
            this.f8844r = true;
            PLog.e("addProjectionScreenShotView ");
        } catch (Exception e2) {
            PLog.b("add screen shot window exception," + e2);
        }
    }

    public void L0() {
        NubiaProjectionExpandedPanel nubiaProjectionExpandedPanel = this.f8830d;
        if (nubiaProjectionExpandedPanel != null) {
            nubiaProjectionExpandedPanel.Z();
        }
    }

    public void M(boolean z) {
        PLog.a("addView: " + z);
        ContextWrapper.updateDisplay(this.f8833g);
        if (this.f8843q) {
            PLog.e("window not removed , not add window again!");
            return;
        }
        m0();
        M0();
        PLog.e("add window reset=" + z);
        try {
            if (this.f8832f == null) {
                PassThroughView passThroughView = new PassThroughView(this.f8833g);
                this.f8832f = passThroughView;
                passThroughView.j("ProjectionIcon", this.f8834h.t().getLooper());
                this.f8832f.addView(this.f8831e, new FrameLayout.LayoutParams(-1, -1));
            }
            this.f8837k.addView(this.f8832f, this.f8835i);
        } catch (Exception e2) {
            PLog.b("add window exception," + e2);
        }
        V0();
        this.f8843q = true;
        final ViewTreeObserver viewTreeObserver = this.f8831e.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cn.nubia.projection.ProjectionUIController.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (ProjectionUIController.this.f8831e == null) {
                    return true;
                }
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnPreDrawListener(this);
                } else {
                    ProjectionUIController.this.f8831e.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                ProjectionUIController.this.f8830d.setVisibility(8);
                ProjectionUIController.this.f8829c.setVisibility(8);
                ProjectionUIController.this.f8829c.k();
                return true;
            }
        });
    }

    public void M0() {
        Q0();
    }

    public void Q(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Nubia ProjectionUIController Status:");
        printWriter.println("    mProjectionIconViewAdd=" + this.f8843q);
        printWriter.println("    mProjectionScreenShotAdd=" + this.f8844r);
        printWriter.println("    mHideWindowForFullScreen=" + this.f8845s);
        printWriter.println("    mPendingUpdateAppIcon=" + this.t);
        printWriter.println("    isVirtualHandleEnable=" + E0());
        printWriter.println("    mProjectionSuspend=" + this.x);
        printWriter.println("    mProjectionType=" + this.w);
        printWriter.println("    vt=" + c0());
        printWriter.println("    mProjectionMode=" + this.y);
        printWriter.println("    mHidDeviceConnected=" + this.B);
        printWriter.println("    mShowedGameBoxDialog=" + this.C);
        printWriter.println("    mMirrorSupportFitType=" + this.D);
        printWriter.println("    mMirrorNowFitType=" + this.E);
        printWriter.println("    mCloudComputerMode=" + this.H);
        printWriter.println("    pwv=" + this.f8831e);
        ProjectionWindowView projectionWindowView = this.f8831e;
        if (projectionWindowView != null) {
            projectionWindowView.q(printWriter, strArr);
        }
        PassThroughView passThroughView = this.f8832f;
        if (passThroughView != null) {
            passThroughView.d(printWriter, strArr);
        }
    }

    public int R() {
        return this.A;
    }

    public boolean R0() {
        return (this.E & 1) != 0;
    }

    public void S0() {
        if (this.f8844r) {
            try {
                try {
                    this.f8837k.removeViewImmediate(this.f8827a);
                } catch (Exception e2) {
                    PLog.b("remove screen shot window exception," + e2);
                }
                PLog.e("removeProjectionScreenShotView");
            } finally {
                this.f8827a = null;
                this.f8844r = false;
            }
        }
    }

    public void T0() {
        PLog.a("removeView: " + this.f8843q);
        try {
            if (this.f8843q) {
                try {
                    PassThroughView passThroughView = this.f8832f;
                    if (passThroughView != null) {
                        this.f8837k.removeViewImmediate(passThroughView);
                    }
                } catch (Exception e2) {
                    PLog.b("remove window exception," + e2);
                }
            }
        } finally {
            j0();
            PLog.e("remove window");
            N();
        }
    }

    public int U() {
        return this.z;
    }

    public void U0() {
        ProjectionWindowView projectionWindowView = this.f8831e;
        if (projectionWindowView != null) {
            projectionWindowView.P();
        }
    }

    public boolean V() {
        return this.f8845s;
    }

    public ImageView W() {
        return this.f8828b;
    }

    public void W0(int i2, int i3, int i4) {
        PLog.a("setCmdToDisplay: cmd:" + i2 + ",arg1:" + i3 + ",arg2:" + i4);
        this.f8834h.J(i2, i3, i4, null);
    }

    public int X() {
        return this.f8834h.p();
    }

    public void X0(final Drawable drawable) {
        b0().post(new Runnable() { // from class: cn.nubia.projection.o
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionUIController.this.G0(drawable);
            }
        });
    }

    public String Y() {
        return ProjectionUtil.a(this.u);
    }

    public void Y0(boolean z) {
        PLog.e("setHideWindowForFullScreen " + z);
        this.f8845s = z;
    }

    public PackageManager Z() {
        return this.f8838l;
    }

    public void Z0(boolean z) {
        this.t = z;
    }

    public WindowManager.LayoutParams a0() {
        return this.f8835i;
    }

    public void a1(boolean z) {
        this.y = z ? 2 : 1;
    }

    public Handler b0() {
        return this.f8834h.t();
    }

    public int c0() {
        return Settings.Global.getInt(this.f8833g.getContentResolver(), "tp_type_for_games_systemui", 0);
    }

    public void c1(int i2) {
        this.f8834h.N(i2);
    }

    public WindowManager.LayoutParams d0() {
        return this.f8835i;
    }

    public void f1(ProjectionWindowView.UIStatus uIStatus) {
        ProjectionWindowView projectionWindowView = this.f8831e;
        if (projectionWindowView != null) {
            projectionWindowView.T(uIStatus);
        }
    }

    public void j1() {
        if (Looper.myLooper() != S().getLooper()) {
            S().post(new Runnable() { // from class: cn.nubia.projection.m
                @Override // java.lang.Runnable
                public final void run() {
                    ProjectionUIController.this.j1();
                }
            });
            return;
        }
        Drawable I0 = I0();
        if (I0 != null) {
            X0(I0);
        }
    }

    public void k1() {
        if (this.f8834h.z()) {
            this.f8834h.R(3);
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("STOP_REDMAGICTOUPING_MIRRACAST_LEBO", false);
            Bundle call = this.f8833g.getContentResolver().call(M, "", "CALL_15", bundle);
            if (call != null) {
                PLog.e("updateFinishProjection " + call.getInt("STOP_REDMAGICTOUPING_MIRRACAST_LEBO"));
            }
        } catch (Exception e2) {
            PLog.c("updateFinishProjection error", e2);
        }
    }

    public void l0() {
        if (this.f8827a == null) {
            n0();
            View f2 = InflaterHelper.f(R.layout.projection_screen_shot, null);
            this.f8827a = f2;
            f2.setSystemUiVisibility(770);
        }
    }

    public void l1(int i2, int i3) {
        PassThroughView passThroughView = this.f8832f;
        if (passThroughView == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.f8835i;
        layoutParams.x = i2;
        layoutParams.y = i3;
        try {
            this.f8837k.updateViewLayout(passThroughView, layoutParams);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            PLog.b("error location x:" + i2 + ",y:" + i3);
        }
    }

    public void m0() {
        if (this.f8831e == null) {
            o0();
            ProjectionWindowView projectionWindowView = (ProjectionWindowView) InflaterHelper.f(R.layout.projection_window, null);
            this.f8831e = projectionWindowView;
            projectionWindowView.setProjectionUIControl(this);
            this.f8829c = (NubiaProjectionPanel) this.f8831e.findViewById(R.id.projection_panel);
            NubiaProjectionExpandedPanel nubiaProjectionExpandedPanel = (NubiaProjectionExpandedPanel) this.f8831e.findViewById(R.id.projection_expanded_panel);
            this.f8830d = nubiaProjectionExpandedPanel;
            nubiaProjectionExpandedPanel.setProjectionUIControl(this);
            this.f8829c.setProjectionUIControl(this);
        }
    }

    public void m1() {
        NubiaProjectionExpandedPanel nubiaProjectionExpandedPanel = this.f8830d;
        if (nubiaProjectionExpandedPanel != null) {
            nubiaProjectionExpandedPanel.m0();
        }
    }

    public boolean n1(boolean z) {
        boolean z2 = false;
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("SUSPEND_REDMAGIC_TOUPING", z ? 1 : 0);
            Bundle call = this.f8833g.getContentResolver().call(M, "", "CALL_14", bundle);
            if (call != null && call.getInt("SUSPEND_REDMAGIC_TOUPING") > 0) {
                z2 = true;
            }
            PLog.e("updateSuspendProjection result=" + z2 + " " + call);
        } catch (Exception e2) {
            PLog.c("updateSuspendProjection error", e2);
        }
        return z2;
    }

    public void o1() {
        if (FoldMgr.f()) {
            EventListenerMgr.g(5, Boolean.valueOf(!this.v), "projection_floating_window");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("from", "projection_floating_window");
        try {
            this.f8833g.getContentResolver().call("cn.nubia.virtualgamehandle", Constants.EXTRA_ENABLE, this.v ? "0" : "1", bundle);
        } catch (Exception e2) {
            PLog.c("updateVirtualHandles error", e2);
        }
    }

    public boolean p0() {
        return Settings.Global.getInt(this.f8833g.getContentResolver(), "wrap_displayid", 0) > 0;
    }

    public void p1(boolean z) {
        PLog.a("updateWindowForFullScreen fullScreen：" + z + "," + this.f8843q);
        if (this.f8831e == null || this.f8845s == z) {
            return;
        }
        Y0(z);
        if (!z) {
            M(false);
            this.f8831e.setDelayRemoveForFullScreen(false);
            return;
        }
        this.f8831e.setDelayRemoveForFullScreen(true);
        if (this.f8831e.I() || q0()) {
            return;
        }
        PLog.a("for full screen update timeout ui");
        this.f8831e.Z();
    }

    public boolean q0() {
        ProjectionWindowView projectionWindowView = this.f8831e;
        return projectionWindowView != null && projectionWindowView.D();
    }

    public boolean r0() {
        return this.y > 1;
    }

    public boolean s0() {
        return this.H;
    }

    public boolean t0() {
        return this.y == 2;
    }

    public boolean v0() {
        return this.z > 0;
    }

    public boolean w0() {
        return this.F;
    }

    public boolean y0() {
        return this.t;
    }

    public boolean z0() {
        return this.G;
    }
}
