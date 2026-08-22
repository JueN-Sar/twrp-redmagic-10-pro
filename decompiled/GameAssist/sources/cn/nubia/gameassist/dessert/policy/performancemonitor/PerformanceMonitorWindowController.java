package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import cn.nubia.gameassist.common.GameDurationManager;
import cn.nubia.gameassist.dessert.policy.performancemonitor.present.UseTimeUtils;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceMonitorWindowController implements GameDurationManager.CallBack {

    /* renamed from: k, reason: collision with root package name */
    private static final String f6439k = "PerformanceMonitorWindowController";

    /* renamed from: h, reason: collision with root package name */
    private Context f6441h;
    public PerformanceMonitorFloatingWindow mPerformanceMonitorWindow;
    public Runnable mTimeChangeRunnable;
    public String mCurPkg = "";

    /* renamed from: c, reason: collision with root package name */
    private Handler f6440c = new Handler(Looper.getMainLooper());

    /* renamed from: i, reason: collision with root package name */
    private int f6442i = 0;

    /* renamed from: j, reason: collision with root package name */
    private Runnable f6443j = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorWindowController.1
        @Override // java.lang.Runnable
        public void run() {
            if (PerformanceMonitorWindowController.this.mPerformanceMonitorWindow.isWindowAdd()) {
                PerformanceMonitorWindowController.this.mPerformanceMonitorWindow.removeFloatView();
                PerformanceMonitorWindowController.this.f6442i = 0;
            }
        }
    };

    public PerformanceMonitorWindowController(Context context, boolean z) {
        this.f6441h = context;
        this.mPerformanceMonitorWindow = new PerformanceMonitorFloatingWindow(context, false, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        GaLog.g(f6439k, "TimeChangeRunnable: ");
        GameDurationManager.n().p(this.mCurPkg, this);
        this.f6440c.postDelayed(this.mTimeChangeRunnable, 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(long j2) {
        String msToH = UseTimeUtils.msToH(j2);
        GaLog.g(f6439k, "updateGameTime: hour = " + msToH);
        this.mPerformanceMonitorWindow.updateGameDuration(msToH);
    }

    private void f() {
        if (this.mTimeChangeRunnable == null) {
            this.mTimeChangeRunnable = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.f
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceMonitorWindowController.this.d();
                }
            };
        }
        this.f6440c.post(this.mTimeChangeRunnable);
    }

    private void g(final long j2) {
        this.f6440c.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.e
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceMonitorWindowController.this.e(j2);
            }
        });
    }

    public void addFloatView(String str) {
        if (this.mPerformanceMonitorWindow.isWindowAdd()) {
            GaLog.g(f6439k, "addFloatView already added");
            return;
        }
        this.mCurPkg = str;
        this.f6440c.removeCallbacks(this.f6443j);
        this.mPerformanceMonitorWindow.addFloatView(str);
        f();
        GameDurationManager.n().p(this.mCurPkg, this);
    }

    public void addHostWindow(Context context, boolean z) {
        this.mPerformanceMonitorWindow = new PerformanceMonitorFloatingWindow(context, true, z);
    }

    public PerformanceMonitorFloatingWindow getWindow() {
        return this.mPerformanceMonitorWindow;
    }

    @Override // cn.nubia.gameassist.common.GameDurationManager.CallBack
    public void onBundlePrepare(Bundle bundle) {
        GaLog.g(f6439k, "onBundlePrepare: bundle = " + bundle);
        if (bundle != null) {
            long j2 = bundle.getLong("time");
            g(j2);
            MultiSubScreenUtils.x(j2);
        } else if (this.f6442i < 3) {
            this.f6440c.postDelayed(this.mTimeChangeRunnable, 3000L);
            this.f6442i++;
        }
    }

    public void removeFloatView() {
        this.f6440c.removeCallbacks(this.mTimeChangeRunnable);
        this.f6440c.postDelayed(this.f6443j, 100L);
    }

    public void updateCurrPkg(String str) {
        this.mCurPkg = str;
        this.mPerformanceMonitorWindow.updateCurrPkg(str);
    }

    public void updateFullScreen(boolean z) {
        GaLog.a(f6439k, "updateFullScreen  isFullScreen = " + z);
        PerformanceMonitorFloatingWindow performanceMonitorFloatingWindow = this.mPerformanceMonitorWindow;
        if (performanceMonitorFloatingWindow != null) {
            performanceMonitorFloatingWindow.updateFullScreen(z);
        }
    }

    public void updateHostScreenSize(int i2, int i3) {
        this.mPerformanceMonitorWindow.updateHostScreenSize(i2, i3);
    }
}
