package cn.nubia.gameassist.performance;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseDoubleViewController;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.meditationmode.MeditationModeViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.performance.PerformanceViewController;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.view.RotationFrameLayout;
import cn.nubia.hostassist.controller.HostViewController;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class PerformanceViewController extends BaseDoubleViewController implements PerformanceModeController.PerformanceModeCallback {

    /* renamed from: r, reason: collision with root package name */
    private RotationFrameLayout f7088r;

    /* renamed from: s, reason: collision with root package name */
    private ButtonDrawable f7089s;
    private boolean t;
    private ValueAnimator u;
    private AnimatorListener v;
    private final PerformanceModeController w;
    private final List x;
    private MeditationModeViewController.MeditationViewCallback y;

    private class AnimatorListener implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

        /* renamed from: c, reason: collision with root package name */
        private float f7091c;

        /* renamed from: h, reason: collision with root package name */
        private View f7092h;

        /* renamed from: i, reason: collision with root package name */
        private float f7093i;

        /* renamed from: j, reason: collision with root package name */
        private float f7094j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f7095k;

        /* renamed from: l, reason: collision with root package name */
        private float f7096l;

        private void b() {
            if (this.f7093i == 0.0f) {
                this.f7093i = (this.f7092h.getWidth() * 47.0f) / 117.0f;
            }
            if (this.f7094j == 0.0f) {
                this.f7094j = -this.f7092h.getHeight();
                View view = this.f7092h;
                if (view != null) {
                    view.setClipBounds(new Rect(0, 3, this.f7092h.getWidth(), this.f7092h.getHeight()));
                }
            }
        }

        public AnimatorListener c(boolean z) {
            this.f7095k = z;
            return this;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PerformanceViewController.this.t = this.f7095k;
            PerformanceViewController performanceViewController = PerformanceViewController.this;
            performanceViewController.Q(R.id.game_assist_performance_effect_expand, performanceViewController.t ? 0 : 8);
            this.f7093i = 0.0f;
            this.f7094j = 0.0f;
            PerformanceViewController.this.u = null;
            this.f7092h = null;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f7092h = PerformanceViewController.this.i(R.id.game_assist_performance_effect_expand);
            PerformanceViewController.this.Q(R.id.game_assist_performance_effect_expand, 0);
            b();
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f7096l = (valueAnimator.getCurrentPlayTime() * 1.0f) / valueAnimator.getDuration();
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f7091c = floatValue;
            View view = this.f7092h;
            if (view != null) {
                view.setAlpha((floatValue * 0.8f) + 0.2f);
            }
            b();
            this.f7092h.setTranslationX((1.0f - this.f7091c) * this.f7093i);
            this.f7092h.setTranslationY((1.0f - this.f7091c) * this.f7094j);
        }

        private AnimatorListener() {
        }
    }

    private class ButtonDrawable extends Drawable {

        /* renamed from: a, reason: collision with root package name */
        private final float f7098a = 0.2f;

        /* renamed from: b, reason: collision with root package name */
        private final float f7099b = 0.05f;

        /* renamed from: c, reason: collision with root package name */
        private final RectF f7100c = new RectF();

        /* renamed from: d, reason: collision with root package name */
        private final Rect f7101d = new Rect();

        /* renamed from: e, reason: collision with root package name */
        private final Paint f7102e;

        /* renamed from: f, reason: collision with root package name */
        protected int f7103f;

        /* renamed from: g, reason: collision with root package name */
        protected ColorFilter f7104g;

        /* renamed from: h, reason: collision with root package name */
        private Theme f7105h;

        /* renamed from: i, reason: collision with root package name */
        private Theme f7106i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f7107j;

        public ButtonDrawable() {
            Paint paint = new Paint();
            this.f7102e = paint;
            this.f7103f = 1;
            paint.setColor(-1);
            paint.setAlpha(250);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3.0f);
            paint.setAntiAlias(true);
        }

        public void a(boolean z) {
            this.f7107j = z;
            invalidateSelf();
        }

        public void b(Theme theme) {
            setColorFilter(theme.f7435b);
            Theme theme2 = this.f7105h;
            if (theme2 == null || (theme2 != null && theme.f7436c != theme2.f7436c)) {
                this.f7106i = theme2;
            }
            this.f7105h = theme;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            float f2;
            boolean z;
            int save = canvas.save();
            Theme theme = this.f7105h;
            if (theme != null) {
                int i2 = 255;
                if (theme.f7451r && this.f7106i != null) {
                    int i3 = (int) (255.0f - (theme.f7449p * 255.0f));
                    Drawable drawable = ((BaseViewController) PerformanceViewController.this).f6117c.getDrawable(this.f7106i.f7448o);
                    drawable.setBounds(this.f7101d);
                    drawable.setAlpha(i3);
                    drawable.setColorFilter(Theme.v);
                    drawable.draw(canvas);
                    i2 = 255 - i3;
                }
                Drawable drawable2 = ((BaseViewController) PerformanceViewController.this).f6117c.getDrawable(this.f7105h.f7448o);
                drawable2.setBounds(this.f7101d);
                drawable2.setAlpha(i2);
                drawable2.setColorFilter(this.f7105h.f7435b);
                drawable2.draw(canvas);
            }
            if (this.f7107j == PerformanceViewController.this.t) {
                z = false;
                f2 = 1.0f;
            } else {
                f2 = PerformanceViewController.this.v.f7096l;
                z = true;
            }
            Path path = new Path();
            if (this.f7107j) {
                RectF rectF = this.f7100c;
                path.moveTo(rectF.left, rectF.top + (rectF.height() * f2 * 1.1f));
                float centerX = this.f7100c.centerX();
                RectF rectF2 = this.f7100c;
                path.lineTo(centerX, rectF2.bottom - ((rectF2.height() * f2) * 0.9f));
                RectF rectF3 = this.f7100c;
                path.lineTo(rectF3.right, rectF3.top + (f2 * rectF3.height() * 1.1f));
            } else {
                RectF rectF4 = this.f7100c;
                path.moveTo(rectF4.left, rectF4.bottom - ((rectF4.height() * f2) * 1.1f));
                float centerX2 = this.f7100c.centerX();
                RectF rectF5 = this.f7100c;
                path.lineTo(centerX2, rectF5.top + (rectF5.height() * f2 * 0.9f));
                RectF rectF6 = this.f7100c;
                path.lineTo(rectF6.right, rectF6.bottom - ((f2 * rectF6.height()) * 1.1f));
            }
            canvas.clipRect(this.f7100c);
            canvas.drawPath(path, this.f7102e);
            canvas.restoreToCount(save);
            if (z) {
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.f7101d.set(new Rect(rect));
            float width = this.f7101d.width() * 0.2f;
            float width2 = this.f7101d.width() * 0.05f;
            Rect rect2 = this.f7101d;
            float width3 = rect2.left + ((rect2.width() - width) / 2.0f);
            float f2 = this.f7101d.bottom - width2;
            this.f7100c.set(width3, f2, width + width3, width2 + f2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i2) {
            this.f7103f = i2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.f7104g = colorFilter;
        }
    }

    public interface PerformanceViewCallback {
        default void a(boolean z, boolean z2) {
        }

        default void onModeChanged(int i2) {
        }
    }

    public PerformanceViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.f7089s = new ButtonDrawable();
        this.v = new AnimatorListener();
        this.x = new ArrayList();
        this.y = new MeditationModeViewController.MeditationViewCallback() { // from class: cn.nubia.gameassist.performance.PerformanceViewController.1
            @Override // cn.nubia.gameassist.meditationmode.MeditationModeViewController.MeditationViewCallback
            public void a(boolean z, boolean z2) {
                if (z) {
                    PerformanceViewController.this.n0(z2);
                }
            }
        };
        PerformanceModeController S = PerformanceModeController.S();
        this.w = S;
        S.P(this);
        HostViewController.e(this.f6117c).a();
    }

    private void A0(int i2, int i3) {
        B0(i2, (i3 == 0 || i3 != i2) ? -7829368 : -1);
    }

    private void B0(int i2, int i3) {
        TextView textView = (TextView) i(i2);
        if (textView != null) {
            textView.setTextColor(i3);
        }
    }

    private void C0() {
        if (this.f6123m != null) {
            TextView textView = (TextView) i(R.id.game_assist_performance_effect_button);
            int q0 = q0();
            if (q0 == 1) {
                textView.setText(R.string.nubia_game_performance_mode_0);
                return;
            }
            if (q0 == 2) {
                textView.setText(R.string.nubia_game_performance_mode_1);
                return;
            }
            if (q0 == 3) {
                textView.setText(R.string.nubia_game_performance_mode_2);
            } else if (q0 == 4) {
                textView.setText(R.string.nubia_game_performance_mode_custom_title);
            } else {
                if (q0 != 5) {
                    return;
                }
                textView.setText(R.string.nubia_game_performance_mode_diablo_title);
            }
        }
    }

    private void D0() {
        if (this.f6123m != null) {
            int q0 = q0();
            if (q0 == 1) {
                z0(R.id.game_assist_performance_effect_expand_economize);
                return;
            }
            if (q0 == 2) {
                z0(R.id.game_assist_performance_effect_expand_balance);
                return;
            }
            if (q0 == 3) {
                z0(R.id.game_assist_performance_effect_expand_awakening);
            } else if (q0 == 4) {
                z0(R.id.game_assist_performance_effect_expand_custome);
            } else {
                if (q0 != 5) {
                    return;
                }
                z0(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E0() {
        C0();
        D0();
    }

    private void F0() {
        if (this.f6123m != null) {
            ((TextView) i(R.id.game_assist_performance_effect_expand_economize)).setText(R.string.nubia_game_performance_mode_0);
            ((TextView) i(R.id.game_assist_performance_effect_expand_balance)).setText(R.string.nubia_game_performance_mode_1);
            ((TextView) i(R.id.game_assist_performance_effect_expand_awakening)).setText(R.string.nubia_game_performance_mode_2);
            if (ZteFeature.isSupportCustom()) {
                ((TextView) i(R.id.game_assist_performance_effect_expand_custome)).setText(R.string.nubia_game_performance_mode_custom_title);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void n0(boolean z) {
        try {
            boolean z2 = true;
            if (Settings.Global.getInt(this.f6117c.getContentResolver(), "animator_duration_scale", 1) == 0 || !z) {
                z2 = false;
            }
            if (this.t) {
                if (z2) {
                    o0(false).start();
                } else {
                    Q(R.id.game_assist_performance_effect_expand, 8);
                    this.t = false;
                }
                p0(false, z2);
                this.f7089s.a(false);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private ValueAnimator o0(boolean z) {
        ValueAnimator ofFloat;
        ValueAnimator valueAnimator = this.u;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.u.cancel();
        }
        float[] fArr = {1.0f, 0.0f};
        if (z) {
            // fill-array-data instruction
            fArr[0] = 0.0f;
            fArr[1] = 1.0f;
            ofFloat = ValueAnimator.ofFloat(fArr);
        } else {
            ofFloat = ValueAnimator.ofFloat(fArr);
        }
        this.u = ofFloat;
        ofFloat.setDuration(300L);
        this.u.addUpdateListener(this.v.c(z));
        this.u.addListener(this.v);
        return this.u;
    }

    private void p0(final boolean z, final boolean z2) {
        this.x.forEach(new Consumer() { // from class: cn.nubia.gameassist.performance.M
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((PerformanceViewController.PerformanceViewCallback) obj).a(z, z2);
            }
        });
    }

    private void r0(boolean z, boolean z2) {
        if (z) {
            v0(z2);
        } else {
            n0(z2);
        }
        C0();
        F0();
        D0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u0(final int i2) {
        E0();
        this.x.forEach(new Consumer() { // from class: cn.nubia.gameassist.performance.P
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((PerformanceViewController.PerformanceViewCallback) obj).onModeChanged(i2);
            }
        });
    }

    private synchronized void v0(boolean z) {
        try {
            boolean z2 = Settings.Global.getInt(this.f6117c.getContentResolver(), "animator_duration_scale", 1) != 0 && z;
            if (!this.t) {
                if (z2) {
                    o0(true).start();
                } else {
                    Q(R.id.game_assist_performance_effect_expand, 0);
                    this.t = true;
                }
            }
            p0(true, z2);
            this.f7089s.a(true);
        } catch (Throwable th) {
            throw th;
        }
    }

    private void x0(int i2) {
        this.w.savePerformanceMode(SystemMgr.t(), i2);
    }

    private void y0(String str) {
        NubiaTrackManager.p().z("cn.nubia.gamelauncher", "assistant_dashboard", "performance", str);
    }

    private void z0(int i2) {
        A0(R.id.game_assist_performance_effect_expand_economize, i2);
        A0(R.id.game_assist_performance_effect_expand_balance, i2);
        A0(R.id.game_assist_performance_effect_expand_awakening, i2);
        if (ZteFeature.isSupportCustom()) {
            A0(R.id.game_assist_performance_effect_expand_custome, i2);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return ZteFeature.isSupportCustom() ? R.id.game_assist_performance_effect : R.id.game_assist_performance_effect_legacy;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void D(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        str.hashCode();
        switch (str) {
            case "game_lower_perform_mode":
                if (!this.w.Q(false)) {
                    if (!this.w.Z()) {
                        GameAgentUtil.l(this.f6117c, iGameAssistClientCallback, inMsg, false);
                        break;
                    } else {
                        GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.nubia_game_performance_mode_0);
                        break;
                    }
                } else {
                    W(R.id.game_assist_performance_effect_expand_economize);
                    GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.nubia_game_performance_mode_0);
                    break;
                }
            case "game_higher_perform_mode":
                if (!this.w.Q(false)) {
                    Context context = this.f6117c;
                    GameAgentUtil.i(context, iGameAssistClientCallback, inMsg, context.getString(R.string.performancemode_is_lowpowermode_tip), false);
                    break;
                } else {
                    W(R.id.game_assist_performance_effect_expand_awakening);
                    GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.nubia_game_performance_mode_2);
                    break;
                }
            case "game_middle_perform_mode":
                if (!this.w.Q(false)) {
                    Context context2 = this.f6117c;
                    GameAgentUtil.i(context2, iGameAssistClientCallback, inMsg, context2.getString(R.string.performancemode_is_lowpowermode_tip), false);
                    break;
                } else {
                    W(R.id.game_assist_performance_effect_expand_balance);
                    GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.nubia_game_performance_mode_1);
                    break;
                }
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void M() {
        if (this.t) {
            n0(true);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void N() {
        super.N();
        n0(false);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        Q(R.id.game_assist_performance_effect_expand, 8);
        i(R.id.game_assist_performance_effect_button).setBackground(null);
        ((MeditationModeViewController) k(MeditationModeViewController.class)).s0(this.y);
        n0(false);
        this.f7088r = null;
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    public int[] T() {
        return new int[]{R.id.game_assist_performance_effect_button, R.id.game_assist_performance_effect_expand_economize, R.id.game_assist_performance_effect_expand_balance, R.id.game_assist_performance_effect_expand_awakening, R.id.game_assist_performance_effect_expand_custome};
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    public void V(View view) {
        super.V(view);
        GlobalSearchUtil.r(S(R.id.game_assist_performance_effect_button), "game_assist_performance_effect_button");
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    protected void W(int i2) {
        if (i2 == R.id.game_assist_performance_effect_button) {
            if (this.t) {
                n0(true);
                return;
            } else {
                v0(true);
                return;
            }
        }
        if (i2 == R.id.game_assist_performance_effect_expand_economize && m0(false)) {
            x0(1);
            n0(true);
            y0("low");
            return;
        }
        if (i2 == R.id.game_assist_performance_effect_expand_balance && m0(true)) {
            x0(2);
            n0(true);
            y0("middle");
        } else if (i2 == R.id.game_assist_performance_effect_expand_awakening && m0(true)) {
            x0(3);
            n0(true);
            y0("high");
        } else if (i2 == R.id.game_assist_performance_effect_expand_custome && m0(true)) {
            x0(4);
            n0(true);
            y0("custome");
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        super.d(theme);
        if (theme != null && this.f6123m != null) {
            if (theme.f7450q) {
                S(R.id.game_assist_performance_effect_button).setEnabled(false);
            } else {
                S(R.id.game_assist_performance_effect_button).setEnabled(true);
            }
        }
        this.f7089s.b(theme);
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController, cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        this.w.dump(printWriter, str);
        printWriter.println(str + "  mIsExpand=" + this.t);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void l() {
        super.l();
    }

    public void l0(PerformanceViewCallback performanceViewCallback) {
        if (this.x.contains(performanceViewCallback)) {
            return;
        }
        this.x.add(performanceViewCallback);
    }

    public boolean m0(boolean z) {
        if (this.w.Q(z)) {
            return true;
        }
        GameAssistWindowManager.O(this.f6117c).g0("setPerformanceMode");
        return false;
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, final int i2, boolean z) {
        this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.performance.O
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceViewController.this.u0(i2);
            }
        });
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        View i2 = i(R.id.game_assist_performance_effect_button);
        i2.setBackground(this.f7089s);
        i2.setPadding(0, 0, 0, 10);
        r0(false, false);
        ((MeditationModeViewController) k(MeditationModeViewController.class)).i0(this.y);
        this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.performance.N
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceViewController.this.E0();
            }
        });
        ThemeController.m().j(this.w.getPerformanceMode());
    }

    public int q0() {
        return this.w.getPerformanceMode();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int s() {
        return ZteFeature.isSupportCustom() ? R.id.game_assist_performance_effect_legacy : R.id.game_assist_performance_effect;
    }

    public void w0(PerformanceViewCallback performanceViewCallback) {
        if (this.x.contains(performanceViewCallback)) {
            this.x.remove(performanceViewCallback);
        }
    }
}
