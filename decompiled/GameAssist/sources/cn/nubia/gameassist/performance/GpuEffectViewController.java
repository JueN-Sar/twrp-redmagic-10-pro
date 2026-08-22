package cn.nubia.gameassist.performance;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import cn.nubia.componentcenter.api.performance.IGpuMonitor;
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
public class GpuEffectViewController extends BaseViewController implements IGpuMonitor.Callback {

    /* renamed from: q, reason: collision with root package name */
    private LinearLayout f7016q;

    /* renamed from: r, reason: collision with root package name */
    private LinearLayout f7017r;

    /* renamed from: s, reason: collision with root package name */
    private LinearLayout f7018s;
    private final GpuEffectDrawable[] t;
    private final GpuNeonLampDrawable[] u;
    private final GpuLauncherDrawable[] v;
    private GpuEffectDrawable w;
    private GpuNeonLampDrawable x;
    private GpuLauncherDrawable y;

    private class GpuEffectDrawable extends EffectDrawable implements ValueAnimator.AnimatorUpdateListener {
        private float u;
        private final String v;
        private final int w;
        private ValueAnimator x;

        public GpuEffectDrawable(GpuEffectViewController gpuEffectViewController, Context context, int i2) {
            super(context, i2);
            this.v = "Boost";
            this.w = 587;
        }

        private void q(float f2) {
            this.u = f2;
            Theme theme = this.f6852l;
            if (theme == null || theme.f7449p == 1.0f) {
                l(p(f2));
                invalidateSelf();
            }
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable, cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (theme != null) {
                float f2 = theme.f7449p;
                if (f2 != 1.0f) {
                    l(p(f2 < 0.05f ? 0.0f : f2 * this.u));
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
                    this.u = 0.0f;
                } catch (Throwable th) {
                    throw th;
                }
            }
            ValueAnimator valueAnimator = this.x;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.x.cancel();
            }
            this.x = null;
        }

        public String o() {
            return String.valueOf(this.u);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            q(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }

        public String p(float f2) {
            return String.format(Locale.ENGLISH, "%.0f", Float.valueOf(f2 / 1000000.0f));
        }

        public void r(float f2, float f3, float f4, boolean z) {
            if (!z) {
                q(f2);
                return;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.u, f3);
            this.x = ofFloat;
            ofFloat.setDuration(1000L);
            this.x.addUpdateListener(this);
            this.x.start();
            invalidateSelf();
        }
    }

    private class GpuLauncherDrawable extends LaunchDrawable {
        public GpuLauncherDrawable(GpuEffectViewController gpuEffectViewController, Context context, int i2) {
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

    private class GpuNeonLampDrawable extends NeonLampDrawable implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator P;

        public GpuNeonLampDrawable(GpuEffectViewController gpuEffectViewController, Context context, int i2) {
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

    public GpuEffectViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.t = new GpuEffectDrawable[2];
        this.u = new GpuNeonLampDrawable[2];
        this.v = new GpuLauncherDrawable[2];
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return 0;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void E(boolean z) {
        super.E(z);
        if (SystemMgr.H()) {
            this.t[0] = new GpuEffectDrawable(this, j(), 2);
            this.t[1] = new GpuEffectDrawable(this, j(), 3);
            this.u[0] = new GpuNeonLampDrawable(this, j(), 2);
            this.u[1] = new GpuNeonLampDrawable(this, j(), 3);
            this.v[0] = new GpuLauncherDrawable(this, j(), 2);
            this.v[1] = new GpuLauncherDrawable(this, j(), 3);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void G(boolean z) {
        super.G(z);
        if (z) {
            this.t[0] = new GpuEffectDrawable(this, j(), 2);
            this.t[1] = new GpuEffectDrawable(this, j(), 3);
            this.u[0] = new GpuNeonLampDrawable(this, j(), 2);
            this.u[1] = new GpuNeonLampDrawable(this, j(), 3);
            this.v[0] = new GpuLauncherDrawable(this, j(), 2);
            this.v[1] = new GpuLauncherDrawable(this, j(), 3);
            return;
        }
        GpuEffectDrawable[] gpuEffectDrawableArr = this.t;
        gpuEffectDrawableArr[0] = null;
        gpuEffectDrawableArr[1] = null;
        GpuNeonLampDrawable[] gpuNeonLampDrawableArr = this.u;
        gpuNeonLampDrawableArr[0] = null;
        gpuNeonLampDrawableArr[1] = null;
        GpuLauncherDrawable[] gpuLauncherDrawableArr = this.v;
        gpuLauncherDrawableArr[0] = null;
        gpuLauncherDrawableArr[1] = null;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        LinearLayout linearLayout = this.f7016q;
        if (linearLayout != null) {
            linearLayout.setBackground(null);
            this.f7016q = null;
        }
        GpuNeonLampDrawable gpuNeonLampDrawable = this.x;
        if (gpuNeonLampDrawable != null) {
            gpuNeonLampDrawable.a(false);
            this.x.l(true);
            this.x = null;
        }
        LinearLayout linearLayout2 = this.f7018s;
        if (linearLayout2 != null) {
            linearLayout2.setBackground(null);
            this.f7018s = null;
        }
        GpuEffectDrawable gpuEffectDrawable = this.w;
        if (gpuEffectDrawable != null) {
            gpuEffectDrawable.a(false);
            this.w.m();
            this.w = null;
        }
        GpuLauncherDrawable gpuLauncherDrawable = this.y;
        if (gpuLauncherDrawable != null) {
            gpuLauncherDrawable.k(1.0f);
            this.y.a(false);
            this.y = null;
        }
        LinearLayout linearLayout3 = this.f7017r;
        if (linearLayout3 != null) {
            linearLayout3.setBackground(null);
            this.f7017r = null;
        }
        ((IGpuMonitor) Router.getDependence(IGpuMonitor.class)).stopMonitor(this);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        super.d(theme);
        if (theme == null || theme.f7449p != 1.0f) {
            return;
        }
        ((IGpuMonitor) Router.getDependence(IGpuMonitor.class)).resetValue();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        if (this.x != null) {
            printWriter.println(str + "  mCurrentGpuNeonLamp=" + this.x.m());
        }
        if (this.w != null) {
            printWriter.println(str + "  mCurrentGpuEffect=" + this.w.o());
        }
        if (this.y != null) {
            printWriter.println(str + "  mCurrentGpuLauncher=" + this.y.m());
        }
        printWriter.println(str + "  mMonitorCup=" + Router.getDependence(IGpuMonitor.class));
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void l() {
        super.l();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        this.f7016q = (LinearLayout) i(R.id.game_assist_gpu_effect);
        if (this.t[0] == null) {
            G(true);
        }
        LinearLayout linearLayout = this.f7016q;
        if (linearLayout != null) {
            GpuEffectDrawable gpuEffectDrawable = (GpuEffectDrawable) this.t[linearLayout.getOrientation()].a(true);
            this.w = gpuEffectDrawable;
            this.f7016q.setBackground(gpuEffectDrawable);
        }
        LinearLayout linearLayout2 = (LinearLayout) i(R.id.game_assist_gpu_neon_lamp);
        this.f7017r = linearLayout2;
        if (linearLayout2 != null) {
            GpuNeonLampDrawable gpuNeonLampDrawable = (GpuNeonLampDrawable) this.u[linearLayout2.getOrientation()].a(true);
            this.x = gpuNeonLampDrawable;
            this.f7017r.setBackground(gpuNeonLampDrawable);
        }
        LinearLayout linearLayout3 = (LinearLayout) i(R.id.game_assist_gpu_launch);
        this.f7018s = linearLayout3;
        if (linearLayout3 != null) {
            GpuLauncherDrawable gpuLauncherDrawable = (GpuLauncherDrawable) this.v[linearLayout3.getOrientation()].a(true);
            this.y = gpuLauncherDrawable;
            this.f7018s.setBackground(gpuLauncherDrawable);
        }
        ((IGpuMonitor) Router.getDependence(IGpuMonitor.class)).startMonitor(this);
    }

    @Override // cn.nubia.componentcenter.api.performance.IGpuMonitor.Callback
    public void onGpuPerformanceChanged(IGpuMonitor.GpuParameter gpuParameter) {
        GpuEffectDrawable gpuEffectDrawable = this.w;
        if (gpuEffectDrawable != null) {
            gpuEffectDrawable.r(gpuParameter.f5867a, gpuParameter.f5868b, gpuParameter.f5869c, true);
        }
        GpuNeonLampDrawable gpuNeonLampDrawable = this.x;
        if (gpuNeonLampDrawable != null) {
            gpuNeonLampDrawable.n(gpuParameter.f5867a, true);
        }
    }
}
