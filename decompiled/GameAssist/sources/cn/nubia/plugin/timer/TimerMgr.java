package cn.nubia.plugin.timer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.provider.FunctionCallController;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class TimerMgr implements GameMonitor.Callback, FunctionCallController.Callback {

    /* renamed from: o, reason: collision with root package name */
    private static volatile TimerMgr f8735o;

    /* renamed from: c, reason: collision with root package name */
    private Context f8736c;

    /* renamed from: k, reason: collision with root package name */
    private TimerWindow f8740k;

    /* renamed from: m, reason: collision with root package name */
    private SettingObserver f8742m;

    /* renamed from: h, reason: collision with root package name */
    private String f8737h = "";

    /* renamed from: i, reason: collision with root package name */
    private boolean f8738i = false;

    /* renamed from: j, reason: collision with root package name */
    private final Handler f8739j = new Handler(Looper.getMainLooper());

    /* renamed from: l, reason: collision with root package name */
    private TimerDataMgr f8741l = new TimerDataMgr();

    /* renamed from: n, reason: collision with root package name */
    private Runnable f8743n = new Runnable() { // from class: cn.nubia.plugin.timer.TimerMgr.7
        @Override // java.lang.Runnable
        public void run() {
            String j2 = Utils.j();
            if (!TimerMgr.this.f8737h.equals(j2) && TimerMgr.this.f8741l.l() && TimerMgr.this.f8740k != null) {
                GaLog.e("TimerMgr", "resetLast last=" + TimerMgr.this.f8737h);
                TimerMgr.this.f8740k.F();
            }
            TimerMgr.this.f8737h = j2;
            GaLog.e("TimerMgr", "onGameStart :  " + TimerMgr.this.f8737h);
            TimerMgr.this.f8741l.h(TimerMgr.this.f8737h);
            TimerMgr.this.v();
        }
    };

    class SettingObserver extends ContentObserver {
        public SettingObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            boolean z2 = Settings.Global.getInt(TimerMgr.this.f8736c.getContentResolver(), TimerMgr.this.t(), 1) != 0;
            if (!TimerMgr.this.w() || z2) {
                return;
            }
            GaLog.e("TimerMgr", "onChange plugin disable");
            TimerMgr.this.o(false);
        }
    }

    private TimerMgr() {
        u();
    }

    public static TimerMgr r() {
        if (f8735o == null) {
            synchronized (TimerMgr.class) {
                try {
                    if (f8735o == null) {
                        f8735o = new TimerMgr();
                    }
                } finally {
                }
            }
        }
        return f8735o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String t() {
        return this.f8737h + "_timer_plugin_enable";
    }

    private void u() {
        this.f8736c = GameAssistApplication.j();
        this.f8737h = Utils.j();
        this.f8742m = new SettingObserver(new Handler(ThreadManager.c().b()));
        SystemMgr.y(this.f8736c).h(this);
        FunctionCallController.c(this.f8736c).b("timer", this);
        FoldMgr.c().a(new FoldMgr.Callback() { // from class: cn.nubia.plugin.timer.TimerMgr.1
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public void onDisplayInUseStateChanged(int i2) {
                if (TimerMgr.this.f8740k != null) {
                    TimerMgr.this.f8740k.y();
                }
            }
        });
        RotationMgr.e(this.f8736c).c(new RotationMgr.Callback() { // from class: cn.nubia.plugin.timer.TimerMgr.2
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            /* renamed from: onRotationChanged */
            public void y(int i2) {
                if (SystemMgr.G) {
                    TimerMgr.this.f8739j.removeCallbacks(TimerMgr.this.f8743n);
                    TimerMgr.this.f8739j.postDelayed(TimerMgr.this.f8743n, 300L);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (!w()) {
            TimerWindow timerWindow = this.f8740k;
            if (timerWindow != null) {
                timerWindow.l();
                return;
            }
            return;
        }
        if (this.f8740k == null) {
            this.f8740k = new TimerWindow(this.f8736c);
        }
        TimerWindow timerWindow2 = this.f8740k;
        TimerDataMgr timerDataMgr = this.f8741l;
        timerWindow2.H(timerDataMgr.f8724b, timerDataMgr.f8725c);
        if (this.f8738i) {
            return;
        }
        this.f8738i = true;
        Uri uriFor = Settings.Global.getUriFor(t());
        this.f8736c.getContentResolver().registerContentObserver(uriFor, false, this.f8742m);
        this.f8742m.onChange(true, uriFor);
    }

    @Override // cn.nubia.gameassist.provider.FunctionCallController.Callback
    public void j(String... strArr) {
        GaLog.e("TimerMgr", "onFunctionCall: data = " + Arrays.toString(strArr));
        final int parseInt = Integer.parseInt(strArr[1]);
        if (!w()) {
            o(true);
        }
        this.f8739j.postDelayed(new Runnable() { // from class: cn.nubia.plugin.timer.TimerMgr.6
            @Override // java.lang.Runnable
            public void run() {
                if (TimerMgr.this.f8740k != null) {
                    TimerMgr.this.f8740k.B(parseInt);
                }
            }
        }, 200L);
    }

    public void n(int i2) {
        this.f8741l.a(this.f8737h, i2);
        this.f8741l.o(this.f8737h);
        TimerWindow timerWindow = this.f8740k;
        if (timerWindow != null) {
            timerWindow.D(i2);
        }
    }

    public void o(final boolean z) {
        this.f8739j.post(new Runnable() { // from class: cn.nubia.plugin.timer.TimerMgr.3
            @Override // java.lang.Runnable
            public void run() {
                TimerMgr.this.f8741l.b(TimerMgr.this.f8737h, z);
                TimerMgr.this.v();
                if (TimerMgr.this.f8740k != null) {
                    TimerMgr.this.f8740k.F();
                }
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f8739j.removeCallbacks(this.f8743n);
        this.f8739j.postDelayed(this.f8743n, 800L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f8739j.removeCallbacks(this.f8743n);
        this.f8739j.post(new Runnable() { // from class: cn.nubia.plugin.timer.TimerMgr.8
            @Override // java.lang.Runnable
            public void run() {
                if (TimerMgr.this.f8740k != null) {
                    TimerMgr.this.f8740k.l();
                    TimerMgr.this.f8740k.E();
                }
                if (TimerMgr.this.f8738i) {
                    TimerMgr.this.f8738i = false;
                    TimerMgr.this.f8736c.getContentResolver().unregisterContentObserver(TimerMgr.this.f8742m);
                }
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        TimerWindow timerWindow;
        if (this.f8741l.l() && (timerWindow = this.f8740k) != null) {
            timerWindow.l();
            GaLog.e("TimerMgr", "onGameUpdate: " + this.f8737h);
        }
        y();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onLauncherFirstPackage(final String str) {
        this.f8739j.post(new Runnable() { // from class: cn.nubia.plugin.timer.TimerMgr.5
            @Override // java.lang.Runnable
            public void run() {
                if (TimerMgr.this.f8737h.equals(str) && TimerMgr.this.f8741l.l() && TimerMgr.this.f8740k != null) {
                    GaLog.e("TimerMgr", "onLauncherFirstActivity resetLast last=" + TimerMgr.this.f8737h);
                    TimerMgr.this.f8740k.F();
                }
            }
        });
    }

    public void p() {
        this.f8739j.post(new Runnable() { // from class: cn.nubia.plugin.timer.TimerMgr.4
            @Override // java.lang.Runnable
            public void run() {
                if (TimerMgr.this.f8740k != null) {
                    TimerMgr.this.f8740k.n();
                }
            }
        });
    }

    public void q(PrintWriter printWriter) {
        printWriter.println("TimerMgr:");
        printWriter.println("  mCurApp=" + this.f8737h);
        printWriter.println("  getData=" + this.f8741l.e());
    }

    public TimerItemData s(int i2) {
        return this.f8741l.d(i2);
    }

    public boolean w() {
        return this.f8741l.l();
    }

    public void x(List list) {
        this.f8741l.n(list);
    }

    public void y(int i2, int i3) {
        this.f8741l.p(this.f8737h, i2, i3);
    }

    public void z(int i2) {
        this.f8741l.o(this.f8737h);
        TimerWindow timerWindow = this.f8740k;
        if (timerWindow != null) {
            timerWindow.D(i2);
        }
    }
}
