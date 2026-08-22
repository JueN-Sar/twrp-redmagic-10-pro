package cn.nubia.gameassist.meditationmode;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import cn.nubia.componentcenter.api.meditation.IMeditationModeController;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseDoubleViewController;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.meditationmode.MeditationModeViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceViewController;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.view.RotationFrameLayout;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class MeditationModeViewController extends BaseDoubleViewController implements IMeditationModeController.MeditationModeCallback {
    private String A;

    /* renamed from: r, reason: collision with root package name */
    private int f6533r;

    /* renamed from: s, reason: collision with root package name */
    private RotationFrameLayout f6534s;
    private ButtonDrawable t;
    private boolean u;
    private ValueAnimator v;
    private AnimatorListener w;
    private MeditationController x;
    private PerformanceViewController.PerformanceViewCallback y;
    private final List z;

    private class AnimatorListener implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

        /* renamed from: c, reason: collision with root package name */
        private float f6536c;

        /* renamed from: h, reason: collision with root package name */
        private View f6537h;

        /* renamed from: i, reason: collision with root package name */
        private float f6538i;

        /* renamed from: j, reason: collision with root package name */
        private float f6539j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f6540k;

        /* renamed from: l, reason: collision with root package name */
        private float f6541l;

        private void b() {
            if (this.f6538i == 0.0f) {
                this.f6538i = ((-this.f6537h.getWidth()) * 57.0f) / 117.0f;
            }
            if (this.f6539j == 0.0f) {
                this.f6539j = -this.f6537h.getHeight();
                View view = this.f6537h;
                if (view != null) {
                    view.setClipBounds(new Rect(0, 3, this.f6537h.getWidth(), this.f6537h.getHeight()));
                }
            }
        }

        public AnimatorListener c(boolean z) {
            this.f6540k = z;
            return this;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MeditationModeViewController.this.u = this.f6540k;
            MeditationModeViewController meditationModeViewController = MeditationModeViewController.this;
            meditationModeViewController.Q(R.id.game_assist_meditation_expand, meditationModeViewController.u ? 0 : 8);
            this.f6538i = 0.0f;
            this.f6539j = 0.0f;
            MeditationModeViewController.this.v = null;
            this.f6537h = null;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f6537h = MeditationModeViewController.this.i(R.id.game_assist_meditation_expand);
            MeditationModeViewController.this.Q(R.id.game_assist_meditation_expand, 0);
            b();
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f6541l = (valueAnimator.getCurrentPlayTime() * 1.0f) / valueAnimator.getDuration();
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f6536c = floatValue;
            View view = this.f6537h;
            if (view != null) {
                view.setAlpha((floatValue * 0.8f) + 0.2f);
            }
            b();
            this.f6537h.setTranslationX((1.0f - this.f6536c) * this.f6538i);
            this.f6537h.setTranslationY((1.0f - this.f6536c) * this.f6539j);
        }

        private AnimatorListener() {
        }
    }

    private class ButtonDrawable extends Drawable {

        /* renamed from: a, reason: collision with root package name */
        private final float f6543a = 0.2f;

        /* renamed from: b, reason: collision with root package name */
        private final float f6544b = 0.05f;

        /* renamed from: c, reason: collision with root package name */
        private final RectF f6545c = new RectF();

        /* renamed from: d, reason: collision with root package name */
        private final Rect f6546d = new Rect();

        /* renamed from: e, reason: collision with root package name */
        private final Paint f6547e;

        /* renamed from: f, reason: collision with root package name */
        protected int f6548f;

        /* renamed from: g, reason: collision with root package name */
        protected ColorFilter f6549g;

        /* renamed from: h, reason: collision with root package name */
        private Theme f6550h;

        /* renamed from: i, reason: collision with root package name */
        private Theme f6551i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f6552j;

        public ButtonDrawable() {
            Paint paint = new Paint();
            this.f6547e = paint;
            this.f6548f = 255;
            paint.setColor(-1);
            paint.setAlpha(250);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3.0f);
            paint.setAntiAlias(true);
        }

        public void a(boolean z) {
            this.f6552j = z;
            invalidateSelf();
        }

        public void b(Theme theme) {
            setColorFilter(theme.f7435b);
            Theme theme2 = this.f6550h;
            if (theme2 == null || (theme2 != null && theme.f7436c != theme2.f7436c)) {
                this.f6551i = theme2;
            }
            this.f6550h = theme;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            float f2;
            boolean z;
            int save = canvas.save();
            Theme theme = this.f6550h;
            if (theme != null) {
                int i2 = 255;
                if (theme.f7451r && this.f6551i != null) {
                    int i3 = (int) (255.0f - (theme.f7449p * 255.0f));
                    Drawable drawable = ((BaseViewController) MeditationModeViewController.this).f6117c.getDrawable(this.f6551i.f7448o);
                    drawable.setBounds(this.f6546d);
                    drawable.setAlpha(i3);
                    drawable.setColorFilter(Theme.v);
                    drawable.draw(canvas);
                    i2 = 255 - i3;
                }
                Drawable drawable2 = ((BaseViewController) MeditationModeViewController.this).f6117c.getDrawable(this.f6550h.f7448o);
                drawable2.setBounds(this.f6546d);
                drawable2.setAlpha(i2);
                drawable2.setColorFilter(this.f6550h.f7435b);
                drawable2.draw(canvas);
            }
            if (this.f6552j == MeditationModeViewController.this.u) {
                z = false;
                f2 = 1.0f;
            } else {
                f2 = MeditationModeViewController.this.w.f6541l;
                z = true;
            }
            Path path = new Path();
            if (this.f6552j) {
                RectF rectF = this.f6545c;
                path.moveTo(rectF.left, rectF.top + (rectF.height() * f2 * 1.1f));
                float centerX = this.f6545c.centerX();
                RectF rectF2 = this.f6545c;
                path.lineTo(centerX, rectF2.bottom - ((rectF2.height() * f2) * 0.9f));
                RectF rectF3 = this.f6545c;
                path.lineTo(rectF3.right, rectF3.top + (f2 * rectF3.height() * 1.1f));
            } else {
                RectF rectF4 = this.f6545c;
                path.moveTo(rectF4.left, rectF4.bottom - ((rectF4.height() * f2) * 1.1f));
                float centerX2 = this.f6545c.centerX();
                RectF rectF5 = this.f6545c;
                path.lineTo(centerX2, rectF5.top + (rectF5.height() * f2 * 0.9f));
                RectF rectF6 = this.f6545c;
                path.lineTo(rectF6.right, rectF6.bottom - ((f2 * rectF6.height()) * 1.1f));
            }
            canvas.clipRect(this.f6545c);
            canvas.drawPath(path, this.f6547e);
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
            this.f6546d.set(new Rect(rect));
            Rect rect2 = this.f6546d;
            rect2.bottom = rect2.top + rect2.height();
            float width = this.f6546d.width() * 0.2f;
            float width2 = this.f6546d.width() * 0.05f;
            Rect rect3 = this.f6546d;
            float width3 = rect3.left + ((rect3.width() - width) / 2.0f);
            float f2 = this.f6546d.bottom - width2;
            this.f6545c.set(width3, f2, width + width3, width2 + f2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i2) {
            this.f6548f = i2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.f6549g = colorFilter;
        }
    }

    public interface MeditationViewCallback {
        default void a(boolean z, boolean z2) {
        }
    }

    public MeditationModeViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.t = new ButtonDrawable();
        this.w = new AnimatorListener();
        this.y = new PerformanceViewController.PerformanceViewCallback() { // from class: cn.nubia.gameassist.meditationmode.MeditationModeViewController.1
            @Override // cn.nubia.gameassist.performance.PerformanceViewController.PerformanceViewCallback
            public void a(boolean z, boolean z2) {
                if (z) {
                    MeditationModeViewController.this.j0(z2);
                }
            }
        };
        this.z = new ArrayList();
        this.A = "on";
        this.x = MeditationController.s();
    }

    private void A0() {
        if (this.f6123m != null) {
            int i2 = this.f6533r;
            if (i2 == 0) {
                u0(R.id.game_assist_meditation_notification_common);
                return;
            }
            if (i2 == 1) {
                u0(R.id.game_assist_meditation_notification_barrage);
            } else if (i2 == 2) {
                u0(R.id.game_assist_meditation_notification_shimmer);
            } else {
                if (i2 != 3) {
                    return;
                }
                u0(R.id.game_assist_meditation_notification_hidden);
            }
        }
    }

    private void B0() {
        if (this.f6123m != null) {
            ((TextView) i(R.id.game_assist_meditation_notification_common)).setText(R.string.meditation_notification_common_title);
            ((TextView) i(R.id.game_assist_meditation_notification_barrage)).setText(R.string.meditation_notification_barrage_title);
            ((TextView) i(R.id.game_assist_meditation_notification_shimmer)).setText(R.string.meditation_notification_shimmer_title);
            ((TextView) i(R.id.game_assist_meditation_notification_hidden)).setText(R.string.meditation_notification_hidden_title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j0(boolean z) {
        try {
            boolean z2 = true;
            if (Settings.Global.getInt(this.f6117c.getContentResolver(), "animator_duration_scale", 1) == 0 || !z) {
                z2 = false;
            }
            if (this.u) {
                if (z2) {
                    k0(false).start();
                } else {
                    Q(R.id.game_assist_meditation_expand, 8);
                    this.u = false;
                }
                l0(false, z2);
                this.t.a(false);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private ValueAnimator k0(boolean z) {
        ValueAnimator ofFloat;
        ValueAnimator valueAnimator = this.v;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.v.cancel();
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
        this.v = ofFloat;
        ofFloat.setDuration(300L);
        this.v.addUpdateListener(this.w.c(z));
        this.v.addListener(this.w);
        return this.v;
    }

    private void l0(final boolean z, final boolean z2) {
        this.z.forEach(new Consumer() { // from class: cn.nubia.gameassist.meditationmode.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((MeditationModeViewController.MeditationViewCallback) obj).a(z, z2);
            }
        });
    }

    private int m0() {
        return this.x.getMeditationMode();
    }

    private void o0(boolean z, boolean z2) {
        if (z) {
            r0(z2);
        } else {
            j0(z2);
        }
        y0();
        B0();
        A0();
    }

    private void q0(int i2) {
        y0();
        A0();
        z0();
    }

    private synchronized void r0(boolean z) {
        try {
            boolean z2 = Settings.Global.getInt(this.f6117c.getContentResolver(), "animator_duration_scale", 1) != 0 && z;
            if (!this.u) {
                if (z2) {
                    k0(true).start();
                } else {
                    Q(R.id.game_assist_meditation_expand, 0);
                    this.u = true;
                }
                l0(true, z2);
                this.t.a(true);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void t0(int i2) {
        this.x.setMeditationMode(i2);
    }

    private void u0(int i2) {
        v0(R.id.game_assist_meditation_notification_common, i2);
        v0(R.id.game_assist_meditation_notification_barrage, i2);
        v0(R.id.game_assist_meditation_notification_shimmer, i2);
        v0(R.id.game_assist_meditation_notification_hidden, i2);
    }

    private void v0(int i2, int i3) {
        w0(i2, (i3 == 0 || i3 == i2) ? -1 : -7829368);
    }

    private void w0(int i2, int i3) {
        TextView textView = (TextView) i(i2);
        if (textView != null) {
            textView.setTextColor(i3);
        }
    }

    private void x0() {
        Bundle bundle = new Bundle();
        bundle.putString("notification_level", this.A);
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "meditation_level", bundle);
    }

    private void y0() {
        TextView textView = (TextView) i(R.id.game_assist_meditation_button);
        int i2 = this.f6533r;
        if (i2 == 0) {
            textView.setText(R.string.meditation_notification_common_title);
            return;
        }
        if (i2 == 1) {
            textView.setText(R.string.meditation_notification_barrage_title);
        } else if (i2 == 2) {
            textView.setText(R.string.meditation_notification_shimmer_title);
        } else {
            if (i2 != 3) {
                return;
            }
            textView.setText(R.string.meditation_notification_hidden_title);
        }
    }

    private void z0() {
        GaLog.a("MeditationModeViewController", "updateLongClick");
        View S = S(R.id.game_assist_meditation_button);
        if (S == null) {
            GaLog.a("MeditationModeViewController", "updateLongClick， clickView is null !");
        } else if (this.f6533r == 1) {
            S.setOnLongClickListener(this);
        } else {
            S.setOnLongClickListener(null);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_meditation_mode;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void D(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        GaLog.a("MeditationModeViewController", "onAICommnadNotify, cmd：" + str);
        str.hashCode();
        switch (str) {
            case "game_set_notification_barrage":
                W(R.id.game_assist_meditation_notification_barrage);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                break;
            case "game_set_notification_gleam":
                W(R.id.game_assist_meditation_notification_shimmer);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                break;
            case "game_set_notification_normal":
                W(R.id.game_assist_meditation_notification_common);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                break;
            case "game_set_notification_shield":
                W(R.id.game_assist_meditation_notification_hidden);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                break;
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void M() {
        if (this.u) {
            j0(true);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void N() {
        super.N();
        j0(false);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        Q(R.id.game_assist_meditation_expand, this.u ? 0 : 8);
        i(R.id.game_assist_meditation_button).setBackground(null);
        ((PerformanceViewController) k(PerformanceViewController.class)).w0(this.y);
        this.x.setListening(false, this);
        this.f6534s = null;
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    public int[] T() {
        return new int[]{R.id.game_assist_meditation_button, R.id.game_assist_meditation_notification_common, R.id.game_assist_meditation_notification_barrage, R.id.game_assist_meditation_notification_shimmer, R.id.game_assist_meditation_notification_hidden};
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    public int[] U() {
        return this.f6533r == 1 ? new int[]{R.id.game_assist_meditation_button} : new int[0];
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    public void V(View view) {
        super.V(view);
        GlobalSearchUtil.r(S(R.id.game_assist_meditation_button), "game_assist_meditation_button");
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    protected void W(int i2) {
        if (i2 == R.id.game_assist_meditation_button) {
            if (this.u) {
                j0(true);
            } else {
                r0(true);
            }
        } else if (i2 == R.id.game_assist_meditation_notification_common) {
            this.A = "on";
            t0(0);
            j0(true);
        } else if (i2 == R.id.game_assist_meditation_notification_barrage) {
            this.A = "bullet";
            t0(1);
            j0(true);
        } else if (i2 == R.id.game_assist_meditation_notification_shimmer) {
            this.A = "light";
            t0(2);
            j0(true);
        } else if (i2 == R.id.game_assist_meditation_notification_hidden) {
            this.A = "off";
            t0(3);
            j0(true);
        }
        x0();
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController
    protected boolean X(int i2) {
        if (this.f6533r == 1) {
            n0();
        }
        return true;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        super.d(theme);
        this.t.b(theme);
    }

    @Override // cn.nubia.gameassist.common.BaseDoubleViewController, cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println(str + "  mIsExpand=" + this.u);
        printWriter.println(str + "  mNotificationMode=" + this.f6533r);
    }

    public void i0(MeditationViewCallback meditationViewCallback) {
        if (this.z.contains(meditationViewCallback)) {
            return;
        }
        this.z.add(meditationViewCallback);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void l() {
        super.l();
        GaLog.a("MeditationModeViewController", "init");
    }

    protected void n0() {
        try {
            Intent intent = new Intent();
            intent.setAction("cn.nubia.gamecenter.settings.action.GAME_CENTER_MANUAL_DETAIL");
            intent.setPackage("cn.nubia.gamelauncher");
            intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
            this.f6117c.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        GaLog.a("MeditationModeViewController", "initView");
        this.f6533r = m0();
        o0(false, false);
        View i2 = i(R.id.game_assist_meditation_button);
        i2.setBackground(this.t);
        i2.setPadding(0, 0, 0, 10);
        ((PerformanceViewController) k(PerformanceViewController.class)).l0(this.y);
        this.x.setListening(true, this);
        z0();
    }

    @Override // cn.nubia.componentcenter.api.meditation.IMeditationModeController.MeditationModeCallback
    public void onMeditationModeCallback(int i2) {
        GaLog.e("MeditationModeViewController", "onMeditationModeChange mode= " + i2 + " mNotificationMode = " + this.f6533r);
        if (this.f6533r != i2) {
            this.f6533r = i2;
            q0(i2);
        }
    }

    public void s0(MeditationViewCallback meditationViewCallback) {
        if (this.z.contains(meditationViewCallback)) {
            this.z.remove(meditationViewCallback);
        }
    }
}
