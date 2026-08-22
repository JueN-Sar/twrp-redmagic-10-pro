package cn.nubia.multisubscreen.mgr;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeWidget;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.utils.GaLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class MultiSubScreenThemeMgr {

    /* renamed from: d, reason: collision with root package name */
    private static volatile MultiSubScreenThemeMgr f7918d;

    /* renamed from: a, reason: collision with root package name */
    private final Handler f7919a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    private final ThemeMode f7920b;

    /* renamed from: c, reason: collision with root package name */
    private final List f7921c;

    private class ThemeMode {

        /* renamed from: a, reason: collision with root package name */
        public final Theme[] f7922a;

        /* renamed from: b, reason: collision with root package name */
        private volatile Theme f7923b;

        /* renamed from: c, reason: collision with root package name */
        private volatile Theme f7924c;

        /* renamed from: d, reason: collision with root package name */
        private ValueAnimator f7925d;

        public ThemeMode() {
            Theme[] themeArr = new Theme[6];
            this.f7922a = themeArr;
            Theme theme = Theme.C;
            this.f7923b = theme;
            this.f7924c = theme;
            themeArr[1] = theme;
            themeArr[2] = Theme.D;
            themeArr[3] = Theme.E;
            themeArr[5] = Theme.F;
            themeArr[4] = Theme.G;
        }

        private void c() {
            ValueAnimator valueAnimator = this.f7925d;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return;
            }
            this.f7925d.cancel();
        }

        private ValueAnimator d(final Theme theme) {
            c();
            if (theme == null) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
                this.f7925d = ofFloat;
                ofFloat.setDuration(1800L);
            } else {
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(this.f7923b.f7449p, 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
                this.f7925d = ofFloat2;
                ofFloat2.setDuration(2200L);
            }
            this.f7925d.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.multisubscreen.mgr.MultiSubScreenThemeMgr.ThemeMode.1

                /* renamed from: c, reason: collision with root package name */
                float f7927c = 1.0f;

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    if (theme != null && this.f7927c < floatValue && ThemeMode.this.f7923b != theme) {
                        ThemeMode.this.f7923b.k(0.0f);
                        ThemeMode.this.f7923b = theme;
                        floatValue = 0.0f;
                    }
                    ThemeMode.this.f7923b.k(floatValue);
                    ThemeMode themeMode = ThemeMode.this;
                    MultiSubScreenThemeMgr.this.d(themeMode.f7923b);
                    this.f7927c = floatValue;
                }
            });
            return this.f7925d;
        }

        public Theme e() {
            return this.f7923b;
        }

        public void f(int i2) {
            Theme theme = this.f7922a[i2];
            GaLog.a("MultiSubScreen_MultiSubScreenThemeMgr", "ThemeMode setMode mTargetTheme = " + this.f7924c + " , theme = " + theme);
            if (this.f7924c != theme) {
                this.f7924c = theme;
                d(theme).start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ThemeWidgetReference {

        /* renamed from: a, reason: collision with root package name */
        private final Handler f7930a;

        /* renamed from: b, reason: collision with root package name */
        private final WeakReference f7931b;

        public boolean b(final Theme theme) {
            final ThemeWidget themeWidget = (ThemeWidget) this.f7931b.get();
            if (themeWidget == null) {
                return false;
            }
            this.f7930a.post(new Runnable() { // from class: cn.nubia.multisubscreen.mgr.c
                @Override // java.lang.Runnable
                public final void run() {
                    ThemeWidget.this.d(theme);
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
            return Objects.equals(this.f7931b.get(), ((ThemeWidgetReference) obj).f7931b.get());
        }

        public int hashCode() {
            return Objects.hash(this.f7930a, this.f7931b);
        }

        private ThemeWidgetReference(Handler handler, ThemeWidget themeWidget) {
            this.f7930a = handler;
            this.f7931b = new WeakReference(themeWidget);
        }
    }

    private MultiSubScreenThemeMgr() {
        ThemeMode themeMode = new ThemeMode();
        this.f7920b = themeMode;
        this.f7921c = new ArrayList();
        if (MultiSubScreenUtils.v()) {
            return;
        }
        themeMode.f(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Theme theme) {
        for (int size = this.f7921c.size() - 1; size >= 0; size--) {
            if (!((ThemeWidgetReference) this.f7921c.get(size)).b(theme)) {
                this.f7921c.remove(size);
            }
        }
    }

    public static MultiSubScreenThemeMgr e() {
        if (f7918d == null) {
            synchronized (MultiSubScreenThemeMgr.class) {
                try {
                    if (f7918d == null) {
                        f7918d = new MultiSubScreenThemeMgr();
                    }
                } finally {
                }
            }
        }
        return f7918d;
    }

    public void b(ThemeWidget themeWidget) {
        c(themeWidget, this.f7919a);
    }

    public void c(ThemeWidget themeWidget, Handler handler) {
        if (handler == null) {
            handler = this.f7919a;
        }
        ThemeWidgetReference themeWidgetReference = new ThemeWidgetReference(handler, themeWidget);
        if (this.f7921c.contains(themeWidgetReference)) {
            return;
        }
        this.f7921c.add(themeWidgetReference);
        themeWidgetReference.b(f());
    }

    public Theme f() {
        return this.f7920b.e();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void g(ThemeWidget themeWidget) {
        int indexOf = this.f7921c.indexOf(new ThemeWidgetReference(null, themeWidget));
        if (indexOf >= 0) {
            this.f7921c.remove(indexOf);
        }
    }

    public void h(int i2) {
        GaLog.e("MultiSubScreen_MultiSubScreenThemeMgr", "theme mode change to " + i2);
        this.f7920b.f(i2);
    }
}
