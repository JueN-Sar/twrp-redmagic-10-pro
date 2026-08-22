package cn.nubia.projection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.Display;
import cn.nubia.projection.util.PLog;
import cn.nubia.projection.util.ProjectionUtil;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.ActivityManagerWrapper;
import com.zte.shared.wrapper.DisplayManagerWrapper;
import com.zte.shared.wrapper.DisplayWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes.dex */
public class ProjectionManager implements DisplayManager.DisplayListener, DumpController.Dump, IGameAssistCommander {

    /* renamed from: c, reason: collision with root package name */
    private Context f8812c;

    /* renamed from: h, reason: collision with root package name */
    private DisplayManager f8813h;

    /* renamed from: i, reason: collision with root package name */
    private ProjectionUIController f8814i;

    /* renamed from: j, reason: collision with root package name */
    private ProjectionDialogController f8815j;

    /* renamed from: k, reason: collision with root package name */
    private Handler f8816k;

    /* renamed from: l, reason: collision with root package name */
    private Handler f8817l;

    /* renamed from: m, reason: collision with root package name */
    private int f8818m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f8819n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f8820o;

    /* renamed from: p, reason: collision with root package name */
    private final HashMap f8821p;

    /* renamed from: q, reason: collision with root package name */
    private Messenger f8822q;

    /* renamed from: r, reason: collision with root package name */
    private final ServiceConnection f8823r;

    private static class Holder {

        /* renamed from: a, reason: collision with root package name */
        private static final ProjectionManager f8825a = new ProjectionManager();
    }

    class ProjectionUIHandler extends Handler {
        public ProjectionUIHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PLog.e("handleMessage " + message.what);
            int i2 = message.what;
            if (i2 == 1001) {
                ProjectionManager.this.v();
            } else {
                if (i2 != 1002) {
                    return;
                }
                ProjectionManager.this.T(message.arg1 == 1);
            }
        }
    }

    private boolean A(Display display) {
        if (display == null) {
            return false;
        }
        return "display_multisrc_copy".equals(display.getName()) && DisplayWrapper.getUniqueId(display).contains("com.zte.multscr");
    }

    private boolean D() {
        return Settings.Global.getInt(this.f8812c.getContentResolver(), "single_hand_on_off", 0) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(Display display) {
        onDisplayAdded(display.getDisplayId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(String str, String str2) {
        String substring = str.substring(12);
        String l2 = l(substring);
        Bundle bundle = new Bundle();
        bundle.putString("app_name", l2);
        bundle.putString("package_name", substring);
        bundle.putString("way", str2);
        NubiaTrackManager.p().x(this.f8812c.getPackageName(), "small_window_cast_failure", bundle);
    }

    private void I(final String str, final String str2) {
        m().post(new Runnable() { // from class: cn.nubia.projection.j
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionManager.this.F(str, str2);
            }
        });
    }

    private void K(int i2) {
        this.f8818m = i2;
    }

    private void L(boolean z) {
        this.f8819n = z;
    }

    private void Q() {
        for (Display display : this.f8813h.getDisplays()) {
            if (j(display)) {
                this.f8818m = display.getDisplayId();
                this.f8821p.put(Integer.valueOf(display.getDisplayId()), display);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(boolean z) {
        this.f8814i.p1(z);
    }

    private void h() {
        if (this.f8822q != null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName("com.zte.multscr", "com.zte.screencastservice.source.MessengerService");
            this.f8812c.bindService(intent, this.f8823r, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean j(Display display) {
        if (display == null) {
            PLog.e("not support,null");
            return false;
        }
        int type = DisplayWrapper.getType(display);
        String name = display.getName();
        if (k(type)) {
            return true;
        }
        if (type == 4 || type == 1 || type == 0) {
            return false;
        }
        if (type != 5) {
            return true;
        }
        String uniqueId = DisplayWrapper.getUniqueId(display);
        if ("display_multisrc_copy".equals(name) && uniqueId.contains("com.zte.multscr")) {
            h();
            return true;
        }
        if ("ScreenCastThread-display".equals(name)) {
            return uniqueId.contains("cn.nubia.touping") || uniqueId.contains("com.zte.smartcast");
        }
        return false;
    }

    private String l(String str) {
        PackageManager Z = this.f8814i.Z();
        try {
            return Z.getApplicationLabel(Z.getApplicationInfo(str, 0)).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private int n() {
        int i2 = 0;
        for (Display display : this.f8813h.getDisplays()) {
            if (j(display)) {
                i2++;
            }
        }
        return i2;
    }

    public static ProjectionManager o() {
        return Holder.f8825a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        boolean C = C();
        PLog.e("handleRemoveWindow, screen casted " + C);
        if (C) {
            return;
        }
        this.f8814i.U0();
    }

    private boolean y(String str) {
        if (str != null) {
            return str.equals("home") || str.contains("cn.nubia.gamelauncher") || str.contains("com.nd.android.pandahome");
        }
        return false;
    }

    public boolean B() {
        for (Display display : this.f8813h.getDisplays()) {
            if (j(display) && 2 != DisplayWrapper.getType(display)) {
                return false;
            }
        }
        return true;
    }

    public boolean C() {
        Display[] displays = this.f8813h.getDisplays();
        if (displays == null) {
            return false;
        }
        for (Display display : displays) {
            if (j(display)) {
                this.f8818m = display.getDisplayId();
                return true;
            }
        }
        return false;
    }

    public void G(boolean z) {
        if (this.f8820o && this.f8819n != z) {
            L(z);
            if (this.f8818m <= 0) {
                return;
            }
            PLog.a("notifyFullScreen:" + z);
            this.f8816k.removeMessages(1002);
            Message obtain = Message.obtain();
            obtain.arg1 = z ? 1 : 0;
            obtain.what = 1002;
            this.f8816k.sendMessage(obtain);
        }
    }

    public void H() {
        if (this.f8822q != null) {
            this.f8812c.unbindService(this.f8823r);
            this.f8822q = null;
        }
    }

    public void J(int i2, int i3, int i4, Bundle bundle) {
        DisplayManagerWrapper.setCmdToDisplay(this.f8813h, i2, i3, i4, bundle);
    }

    public void M() {
        this.f8815j.j();
    }

    public void N(int i2) {
        ProjectionUtil.c(this.f8812c, i2, 0).show();
    }

    public boolean O() {
        if (this.f8818m <= 0) {
            return false;
        }
        PLog.a("support resolution display " + this.f8818m);
        Display display = this.f8813h.getDisplay(this.f8818m);
        if (display == null) {
            return false;
        }
        int type = DisplayWrapper.getType(display);
        if (k(type)) {
            return true;
        }
        return type == 2 || type == 3;
    }

    public boolean P() {
        for (Display display : this.f8813h.getDisplays()) {
            if (j(display) && 5 == DisplayWrapper.getType(display) && !A(display)) {
                return true;
            }
        }
        return false;
    }

    public void R(int i2) {
        if (this.f8822q == null) {
            PLog.b("send action fail, service is null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i2;
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_expand_mode", this.f8814i.t0());
        bundle.putInt("remote_display_id", this.f8818m);
        obtain.setData(bundle);
        PLog.a("send action " + i2 + ", id:" + this.f8818m);
        try {
            this.f8822q.send(obtain);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public void S(boolean z, boolean z2) {
        if (z()) {
            R(z ? z2 ? 4 : 2 : 1);
            return;
        }
        int i2 = this.f8818m;
        if (i2 >= 0) {
            if (z) {
                J(1, i2, 0, null);
            } else {
                J(0, 0, 0, null);
            }
        }
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.f8820o) {
            printWriter.println("Nubia ProjectionManager Status:");
            printWriter.println("    lastDisplayId=" + this.f8818m);
            printWriter.println("    singleHand=" + D());
            printWriter.println("    isScreenCasted=" + C());
            printWriter.println("    getTopActivityType=" + s());
            printWriter.println("    statusBarWindowHidden=" + this.f8819n);
            printWriter.println("    isMultiScr=" + z());
            printWriter.println("    multiScrService=" + this.f8822q);
            for (Integer num : this.f8821p.keySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append("    key=");
                sb.append(num);
                sb.append(",t:");
                Display display = (Display) this.f8821p.get(num);
                Objects.requireNonNull(display);
                sb.append(DisplayWrapper.getType(display));
                sb.append(",d:");
                sb.append(this.f8821p.get(num));
                printWriter.println(sb.toString());
            }
            this.f8814i.Q(printWriter, strArr);
        }
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if ("statusbar_show".equals(str)) {
            G(false);
        } else if ("statusbar_hide".equals(str)) {
            G(true);
        }
    }

    public void g() {
        for (final Display display : this.f8813h.getDisplays()) {
            if (j(display)) {
                PLog.e("addValidDisplay " + display.getDisplayId());
                this.f8816k.postDelayed(new Runnable() { // from class: cn.nubia.projection.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProjectionManager.this.E(display);
                    }
                }, 50L);
            }
        }
    }

    public boolean i() {
        return n() < 2;
    }

    public boolean k(int i2) {
        return "userdebug".equals(Build.TYPE) && i2 == 4;
    }

    public Handler m() {
        return this.f8817l;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i2) {
        Display display = this.f8813h.getDisplay(i2);
        PLog.e("onDisplayAdded " + i2 + " display=" + display);
        if (j(display)) {
            this.f8821p.put(Integer.valueOf(i2), display);
            Settings.Global.putInt(this.f8812c.getContentResolver(), "nubia_systemui_wifidisplay_status", 1);
            K(i2);
            this.f8814i.M(true);
            this.f8814i.m1();
            this.f8814i.Y0(false);
            T(this.f8819n);
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i2) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i2) {
        Display display = this.f8813h.getDisplay(i2);
        if (!this.f8821p.containsKey(Integer.valueOf(i2))) {
            PLog.e("onDisplayRemoved, display:" + i2 + " not added,not need to removed");
            return;
        }
        this.f8821p.remove(Integer.valueOf(i2));
        if (i2 == this.f8818m) {
            Q();
        }
        boolean C = C();
        PLog.e("display remove id=" + i2 + ",screenCasted=" + C + ",display=" + display);
        if (C) {
            this.f8814i.m1();
            return;
        }
        Settings.Global.putInt(this.f8812c.getContentResolver(), "nubia_systemui_wifidisplay_status", 0);
        K(-1);
        this.f8816k.removeMessages(1001);
        this.f8816k.sendEmptyMessageDelayed(1001, 2000L);
        this.f8814i.Y0(false);
    }

    public int p() {
        return this.f8818m;
    }

    public ProjectionDialogController q() {
        if (this.f8815j == null) {
            ProjectionDialogController projectionDialogController = new ProjectionDialogController();
            this.f8815j = projectionDialogController;
            projectionDialogController.D(this.f8812c, r());
        }
        return this.f8815j;
    }

    public ProjectionUIController r() {
        if (this.f8814i == null) {
            this.f8814i = new ProjectionUIController(this.f8812c);
        }
        return this.f8814i;
    }

    public String s() {
        return ActivityManagerWrapper.getTopActivityTypeInDefaultDisplay();
    }

    public Handler t() {
        return this.f8816k;
    }

    public void u() {
        this.f8815j.l();
    }

    public void w(Context context) {
        this.f8820o = true;
        this.f8812c = context;
        this.f8813h = (DisplayManager) context.getSystemService("display");
        SystemMgr.y(this.f8812c).o(this);
        HandlerThread handlerThread = new HandlerThread("nubia_projection_ui", -2);
        handlerThread.start();
        this.f8816k = new ProjectionUIHandler(handlerThread.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("nubia_projection_async", 10);
        handlerThread2.start();
        this.f8817l = new Handler(handlerThread2.getLooper());
        DumpController.c().a(this);
        Settings.Global.putInt(this.f8812c.getContentResolver(), "nubia_systemui_wifidisplay_status", 0);
        this.f8813h.registerDisplayListener(this, this.f8816k);
        q();
        if (r().r0() || C()) {
            g();
        }
    }

    public boolean x() {
        String s2 = s();
        if (s2 == null) {
            return true;
        }
        if (s2.equals("freeform") || s2.equals("splitscreen")) {
            ProjectionUtil.c(this.f8812c, R.string.not_allowd_small_window_for_splitscreen, 0).show();
            return false;
        }
        if (D() || y(s2) || !i()) {
            ProjectionUtil.c(this.f8812c, R.string.not_allowd_small_window_for_home, 0).show();
            return false;
        }
        if (s2.equals("dialog")) {
            ProjectionUtil.c(this.f8812c, R.string.dialog_not_support_small_window, 0).show();
            return false;
        }
        if (!s2.contains("com.android.incallui") && !s2.contains("not_support")) {
            return true;
        }
        ProjectionUtil.c(this.f8812c, R.string.not_allowd_small_window_for_incall, 0).show();
        if (s2.contains("not_support")) {
            I(s2, this.f8814i.r0() ? "floating_window_replace" : "floating_window");
        }
        return false;
    }

    public boolean z() {
        for (Display display : this.f8813h.getDisplays()) {
            if (j(display)) {
                return A(display);
            }
        }
        return false;
    }

    private ProjectionManager() {
        this.f8818m = -1;
        this.f8819n = false;
        this.f8821p = new HashMap();
        this.f8823r = new ServiceConnection() { // from class: cn.nubia.projection.ProjectionManager.1
            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName componentName) {
                PLog.b("bind died");
                ProjectionManager.this.f8822q = null;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                PLog.a("service connected");
                ProjectionManager.this.f8822q = new Messenger(iBinder);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                PLog.b("service disconnected");
                ProjectionManager.this.f8822q = null;
            }
        };
    }
}
