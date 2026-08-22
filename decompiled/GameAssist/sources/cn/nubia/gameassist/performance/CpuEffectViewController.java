package cn.nubia.gameassist.performance;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import cn.nubia.componentcenter.api.performance.ICpuMonitor;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable;
import cn.nubia.gameassist.panel.drawable.diplogen.LaunchDrawable;
import cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable;
import cn.nubia.gameassist.theme.Theme;
import com.zte.gameassist.common.SystemMgr;
import java.io.PrintWriter;
import java.util.Locale;

/* loaded from: classes.dex */
public class CpuEffectViewController extends BaseViewController implements ICpuMonitor.Callback {

    /* renamed from: q, reason: collision with root package name */
    private LinearLayout f6993q;

    /* renamed from: r, reason: collision with root package name */
    private LinearLayout f6994r;

    /* renamed from: s, reason: collision with root package name */
    private LinearLayout f6995s;
    private final CpuEffectDrawable[] t;
    private final CpuNeonLampDrawable[] u;
    private final CpuLauncherDrawable[] v;
    private CpuEffectDrawable w;
    private CpuNeonLampDrawable x;
    private CpuLauncherDrawable y;

    private class CpuEffectDrawable extends EffectDrawable implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator u;
        private float v;

        public CpuEffectDrawable(CpuEffectViewController cpuEffectViewController, Context context, int i2) {
            super(context, i2);
        }

        private void p(float f2) {
            this.v = f2;
            Theme theme = this.f6852l;
            if (theme == null || theme.f7449p == 1.0f) {
                l(String.format(Locale.ENGLISH, "%.2f", Float.valueOf(f2 / 1000000.0f)));
                invalidateSelf();
            }
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable, cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (theme != null) {
                float f2 = theme.f7449p;
                if (f2 != 1.0f) {
                    l(String.format(Locale.ENGLISH, "%.2f", Float.valueOf(f2 < 0.05f ? 0.0f : (this.v * f2) / 1000000.0f)));
                    invalidateSelf();
                }
            }
        }

        public synchronized void m() {
            n(false);
        }

        public synchronized void n(boolean z) {
            if (z) {
                try {
                    this.v = 0.0f;
                } catch (Throwable th) {
                    throw th;
                }
            }
            ValueAnimator valueAnimator = this.u;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.u.cancel();
            }
            this.u = null;
        }

        public String o() {
            return String.valueOf(this.v);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            p(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }

        public void q(float f2, float f3, float f4, boolean z) {
            if (!z) {
                p(f2);
                return;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.v, f3);
            this.u = ofFloat;
            ofFloat.setDuration(1000L);
            this.u.addUpdateListener(this);
            this.u.start();
            invalidateSelf();
        }
    }

    private class CpuLauncherDrawable extends LaunchDrawable {
        public CpuLauncherDrawable(CpuEffectViewController cpuEffectViewController, Context context, int i2) {
            super(context, i2);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.LaunchDrawable, cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (theme.f7451r) {
                float f2 = theme.f7449p;
                if (f2 < 0.4f) {
                    k(f2 / 0.4f);
                    return;
                }
            }
            if (theme.f7449p != 1.0f) {
                k(1.0f);
            }
        }

        public String m() {
            return String.valueOf(this.y);
        }
    }

    private class CpuNeonLampDrawable extends NeonLampDrawable implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator P;

        public CpuNeonLampDrawable(CpuEffectViewController cpuEffectViewController, Context context, int i2) {
            super(context, i2);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable, cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (theme == null || !theme.f7451r || this.P == null) {
                return;
            }
            l(false);
        }

        public synchronized void l(boolean z) {
            if (z) {
                try {
                    k(0.0f);
                } catch (Throwable th) {
                    throw th;
                }
            }
            ValueAnimator valueAnimator = this.P;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.P.cancel();
            }
            this.P = null;
        }

        public String m() {
            return this.f6927r + "-" + this.f6926q;
        }

        public synchronized void n(float f2, boolean z) {
            Theme theme = this.f6852l;
            if (theme == null || !theme.f7451r) {
                if (theme == null || theme.f7449p == 1.0f) {
                    if (z) {
                        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.f6926q, f2);
                        this.P = ofFloat;
                        ofFloat.setDuration(1000L);
                        this.P.addUpdateListener(this);
                        this.P.start();
                        invalidateSelf();
                    } else {
                        k(f2);
                    }
                }
            }
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            k(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public CpuEffectViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.t = new CpuEffectDrawable[2];
        this.u = new CpuNeonLampDrawable[2];
        this.v = new CpuLauncherDrawable[2];
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_left_panel;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void E(boolean z) {
        super.E(z);
        if (SystemMgr.H()) {
            this.t[0] = new CpuEffectDrawable(this, j(), 0);
            this.t[1] = new CpuEffectDrawable(this, j(), 1);
            this.u[0] = new CpuNeonLampDrawable(this, j(), 0);
            this.u[1] = new CpuNeonLampDrawable(this, j(), 1);
            this.v[0] = new CpuLauncherDrawable(this, j(), 0);
            this.v[1] = new CpuLauncherDrawable(this, j(), 1);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void G(boolean z) {
        super.G(z);
        if (z) {
            this.t[0] = new CpuEffectDrawable(this, j(), 0);
            this.t[1] = new CpuEffectDrawable(this, j(), 1);
            this.u[0] = new CpuNeonLampDrawable(this, j(), 0);
            this.u[1] = new CpuNeonLampDrawable(this, j(), 1);
            this.v[0] = new CpuLauncherDrawable(this, j(), 0);
            this.v[1] = new CpuLauncherDrawable(this, j(), 1);
            return;
        }
        CpuEffectDrawable[] cpuEffectDrawableArr = this.t;
        cpuEffectDrawableArr[0] = null;
        cpuEffectDrawableArr[1] = null;
        CpuNeonLampDrawable[] cpuNeonLampDrawableArr = this.u;
        cpuNeonLampDrawableArr[0] = null;
        cpuNeonLampDrawableArr[1] = null;
        CpuLauncherDrawable[] cpuLauncherDrawableArr = this.v;
        cpuLauncherDrawableArr[0] = null;
        cpuLauncherDrawableArr[1] = null;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        LinearLayout linearLayout = this.f6993q;
        if (linearLayout != null) {
            linearLayout.setBackground(null);
            this.f6993q = null;
        }
        CpuNeonLampDrawable cpuNeonLampDrawable = this.x;
        if (cpuNeonLampDrawable != null) {
            cpuNeonLampDrawable.a(false);
            this.x.l(true);
            this.x = null;
        }
        LinearLayout linearLayout2 = this.f6994r;
        if (linearLayout2 != null) {
            linearLayout2.setBackground(null);
            this.f6994r = null;
        }
        CpuEffectDrawable cpuEffectDrawable = this.w;
        if (cpuEffectDrawable != null) {
            cpuEffectDrawable.a(false);
            this.w.m();
            this.w = null;
        }
        CpuLauncherDrawable cpuLauncherDrawable = this.y;
        if (cpuLauncherDrawable != null) {
            cpuLauncherDrawable.k(1.0f);
            this.y.a(false);
            this.y = null;
        }
        LinearLayout linearLayout3 = this.f6995s;
        if (linearLayout3 != null) {
            linearLayout3.setBackground(null);
            this.f6995s = null;
        }
        ((ICpuMonitor) Router.getDependence(ICpuMonitor.class)).stopMonitor(this);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        super.d(theme);
        if (theme == null || theme.f7449p != 1.0f) {
            return;
        }
        ((ICpuMonitor) Router.getDependence(ICpuMonitor.class)).resetValue();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        if (this.x != null) {
            printWriter.println(str + "  mCurrentCpuNeonLamp=" + this.x.m());
        }
        if (this.w != null) {
            printWriter.println(str + "  mCurrentCpuEffect=" + this.w.o());
        }
        if (this.y != null) {
            printWriter.println(str + "  mCurrentCpuLauncher=" + this.y.m());
        }
        printWriter.println(str + "  mMonitorCup=" + Router.getDependence(ICpuMonitor.class));
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void l() {
        super.l();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        this.f6993q = (LinearLayout) i(R.id.game_assist_cup_effect);
        if (this.t[0] == null) {
            G(true);
        }
        LinearLayout linearLayout = this.f6993q;
        if (linearLayout != null) {
            CpuEffectDrawable cpuEffectDrawable = (CpuEffectDrawable) this.t[linearLayout.getOrientation()].a(true);
            this.w = cpuEffectDrawable;
            this.f6993q.setBackground(cpuEffectDrawable);
        }
        LinearLayout linearLayout2 = (LinearLayout) i(R.id.game_assist_cpu_neon_lamp);
        this.f6994r = linearLayout2;
        if (linearLayout2 != null) {
            CpuNeonLampDrawable cpuNeonLampDrawable = (CpuNeonLampDrawable) this.u[this.f6993q.getOrientation()].a(true);
            this.x = cpuNeonLampDrawable;
            this.f6994r.setBackground(cpuNeonLampDrawable);
        }
        LinearLayout linearLayout3 = (LinearLayout) i(R.id.game_assist_cpu_launch);
        this.f6995s = linearLayout3;
        if (linearLayout3 != null) {
            CpuLauncherDrawable cpuLauncherDrawable = (CpuLauncherDrawable) this.v[linearLayout3.getOrientation()].a(true);
            this.y = cpuLauncherDrawable;
            this.f6995s.setBackground(cpuLauncherDrawable);
        }
        ((ICpuMonitor) Router.getDependence(ICpuMonitor.class)).startMonitor(this);
    }

    @Override // cn.nubia.componentcenter.api.performance.ICpuMonitor.Callback
    public void onCpuPerformanceChanged(ICpuMonitor.CpuParameter cpuParameter) {
        CpuEffectDrawable cpuEffectDrawable = this.w;
        if (cpuEffectDrawable != null) {
            cpuEffectDrawable.q(cpuParameter.f5863a, cpuParameter.f5864b, cpuParameter.f5865c, true);
        }
        CpuNeonLampDrawable cpuNeonLampDrawable = this.x;
        if (cpuNeonLampDrawable != null) {
            cpuNeonLampDrawable.n(cpuParameter.f5863a, true);
        }
    }
}
