package cn.nubia.gameassist.theme;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import cn.nubia.gameassist.common.IHostPanel;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.theme.ThemeController;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.TraceWrapper;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class ThemeController implements IHostPanel.PanelCallback {

    /* renamed from: f, reason: collision with root package name */
    private static volatile ThemeController f7475f;

    /* renamed from: a, reason: collision with root package name */
    private final Handler f7476a;

    /* renamed from: b, reason: collision with root package name */
    private final ThemeMode f7477b;

    /* renamed from: c, reason: collision with root package name */
    private final List f7478c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f7479d;

    /* renamed from: e, reason: collision with root package name */
    private PerformanceModeController.PerformanceModeCallback f7480e;

    public static abstract class AnimatorListener implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    private class ThemeMode {

        /* renamed from: a, reason: collision with root package name */
        public final Theme[] f7482a;

        /* renamed from: b, reason: collision with root package name */
        private volatile Theme f7483b;

        /* renamed from: c, reason: collision with root package name */
        private ValueAnimator f7484c;

        public ThemeMode() {
            this.f7482a = new Theme[]{r2, r0, r2, Theme.z, Theme.B, Theme.A};
            Theme theme = Theme.x;
            this.f7483b = theme;
            Theme theme2 = Theme.y;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            ValueAnimator valueAnimator = this.f7484c;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return;
            }
            this.f7484c.cancel();
        }

        private ValueAnimator e(final Theme theme) {
            d();
            if (theme == null) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
                this.f7484c = ofFloat;
                ofFloat.setDuration(1800L);
            } else {
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(f().f7449p, 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
                this.f7484c = ofFloat2;
                ofFloat2.setDuration(2200L);
            }
            this.f7484c.addListener(new AnimatorListener() { // from class: cn.nubia.gameassist.theme.ThemeController.ThemeMode.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (ThemeMode.this.f7483b == theme && ((int) ThemeMode.this.f7483b.f7449p) == 1) {
                        return;
                    }
                    ThemeMode.this.h(theme);
                    ThemeMode.this.f().k(1.0f);
                    ThemeMode themeMode = ThemeMode.this;
                    ThemeController.this.k(themeMode.f());
                }
            });
            this.f7484c.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.theme.ThemeController.ThemeMode.2

                /* renamed from: c, reason: collision with root package name */
                float f7488c = 1.0f;

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    if (theme != null && this.f7488c < floatValue && ThemeMode.this.f() != theme) {
                        ThemeMode.this.f().k(0.0f);
                        ThemeMode.this.h(theme);
                        floatValue = 0.0f;
                    }
                    ThemeMode.this.f().k(floatValue);
                    ThemeMode themeMode = ThemeMode.this;
                    ThemeController.this.k(themeMode.f());
                    this.f7488c = floatValue;
                }
            });
            return this.f7484c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void h(Theme theme) {
            if (this.f7483b != theme && theme != null) {
                this.f7483b = theme;
            }
        }

        public synchronized Theme f() {
            return this.f7483b;
        }

        public void g() {
            d();
            Theme f2 = f();
            if (f2 != null) {
                f2.k(0.0f);
                ThemeController.this.k(f2);
            }
        }

        public void i(int i2) {
            Theme theme = this.f7482a[i2];
            if (f() != theme) {
                GaLog.e("GameAssist.Theme", "theme mode changed , mode = " + theme.f7436c);
                if (ThemeController.this.f7479d) {
                    e(theme).start();
                } else {
                    h(theme);
                    g();
                }
            }
        }

        public void j() {
            e(null).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ThemeWidgetReference {

        /* renamed from: a, reason: collision with root package name */
        private final Handler f7491a;

        /* renamed from: b, reason: collision with root package name */
        private final Reference f7492b;

        private boolean d(Object obj, Object obj2) {
            return obj == obj2 || (obj != null && obj.equals(obj2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void e(ThemeWidget themeWidget, Theme theme) {
            TraceWrapper.traceBegin(8L, "Theme" + themeWidget.getClass().getSimpleName());
            themeWidget.d(theme);
            TraceWrapper.traceEnd(8L);
        }

        public boolean c(final Theme theme) {
            final ThemeWidget themeWidget = (ThemeWidget) this.f7492b.get();
            if (themeWidget == null) {
                return false;
            }
            this.f7491a.post(new Runnable() { // from class: cn.nubia.gameassist.theme.b
                @Override // java.lang.Runnable
                public final void run() {
                    ThemeController.ThemeWidgetReference.e(ThemeWidget.this, theme);
                }
            });
            return true;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return d(this.f7492b.get(), ((ThemeWidgetReference) obj).f7492b.get());
        }

        public int hashCode() {
            return Objects.hash(this.f7491a, this.f7492b);
        }

        private ThemeWidgetReference(Handler handler, ThemeWidget themeWidget) {
            this.f7491a = handler;
            this.f7492b = new SoftReference(themeWidget);
        }
    }

    private ThemeController() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.f7476a = handler;
        this.f7477b = new ThemeMode();
        this.f7478c = new ArrayList();
        this.f7480e = new PerformanceModeController.PerformanceModeCallback() { // from class: cn.nubia.gameassist.theme.ThemeController.1
            @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
            public void n(String str, int i2, boolean z) {
                ThemeController.this.f7477b.i(i2);
            }
        };
        handler.post(new Runnable() { // from class: cn.nubia.gameassist.theme.a
            @Override // java.lang.Runnable
            public final void run() {
                ThemeController.this.o();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(Theme theme) {
        for (int size = this.f7478c.size() - 1; size >= 0; size--) {
            if (!((ThemeWidgetReference) this.f7478c.get(size)).c(theme)) {
                this.f7478c.remove(size);
            }
        }
    }

    public static ThemeController m() {
        if (f7475f == null) {
            synchronized (ThemeController.class) {
                try {
                    if (f7475f == null) {
                        f7475f = new ThemeController();
                    }
                } finally {
                }
            }
        }
        return f7475f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        PerformanceModeController S = PerformanceModeController.S();
        S.P(this.f7480e);
        GameAssistWindowManager.O(ContextWrapper.getContext()).D0(this);
        this.f7477b.i(S.getPerformanceMode());
    }

    @Override // cn.nubia.gameassist.common.IHostPanel.PanelCallback
    public void b(boolean z) {
        this.f7479d = z;
        if (z) {
            this.f7477b.j();
            return;
        }
        this.f7477b.d();
        this.f7477b.g();
        k(this.f7477b.f7483b);
    }

    public void h(ThemeWidget themeWidget) {
        i(themeWidget, this.f7476a);
    }

    public void i(ThemeWidget themeWidget, Handler handler) {
        if (handler == null) {
            handler = this.f7476a;
        }
        ThemeWidgetReference themeWidgetReference = new ThemeWidgetReference(handler, themeWidget);
        if (this.f7478c.contains(themeWidgetReference)) {
            return;
        }
        this.f7478c.add(themeWidgetReference);
        themeWidgetReference.c(n());
    }

    public void j(int i2) {
        this.f7477b.i(i2);
    }

    public void l(PrintWriter printWriter, String str) {
        printWriter.println(str + getClass().getSimpleName());
        for (int i2 = 0; i2 < this.f7478c.size(); i2++) {
            Object obj = ((ThemeWidgetReference) this.f7478c.get(i2)).f7492b.get();
            printWriter.println(str + "" + i2 + " " + obj.getClass().getSimpleName() + "@" + Integer.toHexString(obj.hashCode()));
        }
    }

    public Theme n() {
        return this.f7477b.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void p(ThemeWidget themeWidget) {
        int indexOf = this.f7478c.indexOf(new ThemeWidgetReference(null, themeWidget));
        if (indexOf >= 0) {
            this.f7478c.remove(indexOf);
        }
    }
}
