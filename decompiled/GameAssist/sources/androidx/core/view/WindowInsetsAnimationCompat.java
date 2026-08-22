package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {

    /* renamed from: a, reason: collision with root package name */
    private Impl f3404a;

    public static abstract class Callback {

        /* renamed from: a, reason: collision with root package name */
        WindowInsets f3407a;

        /* renamed from: b, reason: collision with root package name */
        private final int f3408b;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface DispatchMode {
        }

        public Callback(int i2) {
            this.f3408b = i2;
        }

        public final int a() {
            return this.f3408b;
        }

        public void b(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        public void c(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        public abstract WindowInsetsCompat d(WindowInsetsCompat windowInsetsCompat, List list);

        public BoundsCompat e(WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat) {
            return boundsCompat;
        }
    }

    private static class Impl {

        /* renamed from: a, reason: collision with root package name */
        private final int f3409a;

        /* renamed from: b, reason: collision with root package name */
        private float f3410b;

        /* renamed from: c, reason: collision with root package name */
        private final Interpolator f3411c;

        /* renamed from: d, reason: collision with root package name */
        private final long f3412d;

        Impl(int i2, Interpolator interpolator, long j2) {
            this.f3409a = i2;
            this.f3411c = interpolator;
            this.f3412d = j2;
        }

        public long a() {
            return this.f3412d;
        }

        public float b() {
            Interpolator interpolator = this.f3411c;
            return interpolator != null ? interpolator.getInterpolation(this.f3410b) : this.f3410b;
        }

        public int c() {
            return this.f3409a;
        }

        public void d(float f2) {
            this.f3410b = f2;
        }
    }

    @RequiresApi
    private static class Impl21 extends Impl {

        /* renamed from: e, reason: collision with root package name */
        private static final Interpolator f3413e = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);

        /* renamed from: f, reason: collision with root package name */
        private static final Interpolator f3414f = new FastOutLinearInInterpolator();

        /* renamed from: g, reason: collision with root package name */
        private static final Interpolator f3415g = new DecelerateInterpolator();

        @RequiresApi
        private static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {

            /* renamed from: a, reason: collision with root package name */
            final Callback f3416a;

            /* renamed from: b, reason: collision with root package name */
            private WindowInsetsCompat f3417b;

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(final View view, WindowInsets windowInsets) {
                final int e2;
                if (!view.isLaidOut()) {
                    this.f3417b = WindowInsetsCompat.x(windowInsets, view);
                    return Impl21.l(view, windowInsets);
                }
                final WindowInsetsCompat x = WindowInsetsCompat.x(windowInsets, view);
                if (this.f3417b == null) {
                    this.f3417b = ViewCompat.B(view);
                }
                if (this.f3417b == null) {
                    this.f3417b = x;
                    return Impl21.l(view, windowInsets);
                }
                Callback m2 = Impl21.m(view);
                if ((m2 == null || !Objects.equals(m2.f3407a, windowInsets)) && (e2 = Impl21.e(x, this.f3417b)) != 0) {
                    final WindowInsetsCompat windowInsetsCompat = this.f3417b;
                    final WindowInsetsAnimationCompat windowInsetsAnimationCompat = new WindowInsetsAnimationCompat(e2, Impl21.g(e2, x, windowInsetsCompat), 160L);
                    windowInsetsAnimationCompat.e(0.0f);
                    final ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(windowInsetsAnimationCompat.a());
                    final BoundsCompat f2 = Impl21.f(x, windowInsetsCompat, e2);
                    Impl21.i(view, windowInsetsAnimationCompat, windowInsets, false);
                    duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            windowInsetsAnimationCompat.e(valueAnimator.getAnimatedFraction());
                            Impl21.j(view, Impl21.n(x, windowInsetsCompat, windowInsetsAnimationCompat.b(), e2), Collections.singletonList(windowInsetsAnimationCompat));
                        }
                    });
                    duration.addListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            windowInsetsAnimationCompat.e(1.0f);
                            Impl21.h(view, windowInsetsAnimationCompat);
                        }
                    });
                    OneShotPreDrawListener.a(view, new Runnable() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.3
                        @Override // java.lang.Runnable
                        public void run() {
                            Impl21.k(view, windowInsetsAnimationCompat, f2);
                            duration.start();
                        }
                    });
                    this.f3417b = x;
                    return Impl21.l(view, windowInsets);
                }
                return Impl21.l(view, windowInsets);
            }
        }

        static int e(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2) {
            int i2 = 0;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if (!windowInsetsCompat.f(i3).equals(windowInsetsCompat2.f(i3))) {
                    i2 |= i3;
                }
            }
            return i2;
        }

        static BoundsCompat f(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, int i2) {
            Insets f2 = windowInsetsCompat.f(i2);
            Insets f3 = windowInsetsCompat2.f(i2);
            return new BoundsCompat(Insets.b(Math.min(f2.f2920a, f3.f2920a), Math.min(f2.f2921b, f3.f2921b), Math.min(f2.f2922c, f3.f2922c), Math.min(f2.f2923d, f3.f2923d)), Insets.b(Math.max(f2.f2920a, f3.f2920a), Math.max(f2.f2921b, f3.f2921b), Math.max(f2.f2922c, f3.f2922c), Math.max(f2.f2923d, f3.f2923d)));
        }

        static Interpolator g(int i2, WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2) {
            return (i2 & 8) != 0 ? windowInsetsCompat.f(WindowInsetsCompat.Type.a()).f2923d > windowInsetsCompat2.f(WindowInsetsCompat.Type.a()).f2923d ? f3413e : f3414f : f3415g;
        }

        static void h(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
            Callback m2 = m(view);
            if (m2 != null) {
                m2.b(windowInsetsAnimationCompat);
                if (m2.a() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    h(viewGroup.getChildAt(i2), windowInsetsAnimationCompat);
                }
            }
        }

        static void i(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsets windowInsets, boolean z) {
            Callback m2 = m(view);
            if (m2 != null) {
                m2.f3407a = windowInsets;
                if (!z) {
                    m2.c(windowInsetsAnimationCompat);
                    z = m2.a() == 0;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    i(viewGroup.getChildAt(i2), windowInsetsAnimationCompat, windowInsets, z);
                }
            }
        }

        static void j(View view, WindowInsetsCompat windowInsetsCompat, List list) {
            Callback m2 = m(view);
            if (m2 != null) {
                windowInsetsCompat = m2.d(windowInsetsCompat, list);
                if (m2.a() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    j(viewGroup.getChildAt(i2), windowInsetsCompat, list);
                }
            }
        }

        static void k(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat) {
            Callback m2 = m(view);
            if (m2 != null) {
                m2.e(windowInsetsAnimationCompat, boundsCompat);
                if (m2.a() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    k(viewGroup.getChildAt(i2), windowInsetsAnimationCompat, boundsCompat);
                }
            }
        }

        static WindowInsets l(View view, WindowInsets windowInsets) {
            return view.getTag(R.id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
        }

        static Callback m(View view) {
            Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
            if (tag instanceof Impl21OnApplyWindowInsetsListener) {
                return ((Impl21OnApplyWindowInsetsListener) tag).f3416a;
            }
            return null;
        }

        static WindowInsetsCompat n(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, float f2, int i2) {
            WindowInsetsCompat.Builder builder = new WindowInsetsCompat.Builder(windowInsetsCompat);
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) == 0) {
                    builder.b(i3, windowInsetsCompat.f(i3));
                } else {
                    Insets f3 = windowInsetsCompat.f(i3);
                    Insets f4 = windowInsetsCompat2.f(i3);
                    float f5 = 1.0f - f2;
                    builder.b(i3, WindowInsetsCompat.o(f3, (int) (((f3.f2920a - f4.f2920a) * f5) + 0.5d), (int) (((f3.f2921b - f4.f2921b) * f5) + 0.5d), (int) (((f3.f2922c - f4.f2922c) * f5) + 0.5d), (int) (((f3.f2923d - f4.f2923d) * f5) + 0.5d)));
                }
            }
            return builder.a();
        }
    }

    public WindowInsetsAnimationCompat(int i2, Interpolator interpolator, long j2) {
        this.f3404a = new Impl30(i2, interpolator, j2);
    }

    static void d(View view, Callback callback) {
        Impl30.h(view, callback);
    }

    static WindowInsetsAnimationCompat f(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    public long a() {
        return this.f3404a.a();
    }

    public float b() {
        return this.f3404a.b();
    }

    public int c() {
        return this.f3404a.c();
    }

    public void e(float f2) {
        this.f3404a.d(f2);
    }

    @RequiresApi
    private static class Impl30 extends Impl {

        /* renamed from: e, reason: collision with root package name */
        private final WindowInsetsAnimation f3432e;

        @RequiresApi
        private static class ProxyCallback extends WindowInsetsAnimation.Callback {

            /* renamed from: a, reason: collision with root package name */
            private final Callback f3433a;

            /* renamed from: b, reason: collision with root package name */
            private List f3434b;

            /* renamed from: c, reason: collision with root package name */
            private ArrayList f3435c;

            /* renamed from: d, reason: collision with root package name */
            private final HashMap f3436d;

            ProxyCallback(Callback callback) {
                super(callback.a());
                this.f3436d = new HashMap();
                this.f3433a = callback;
            }

            private WindowInsetsAnimationCompat a(WindowInsetsAnimation windowInsetsAnimation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = (WindowInsetsAnimationCompat) this.f3436d.get(windowInsetsAnimation);
                if (windowInsetsAnimationCompat != null) {
                    return windowInsetsAnimationCompat;
                }
                WindowInsetsAnimationCompat f2 = WindowInsetsAnimationCompat.f(windowInsetsAnimation);
                this.f3436d.put(windowInsetsAnimation, f2);
                return f2;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                this.f3433a.b(a(windowInsetsAnimation));
                this.f3436d.remove(windowInsetsAnimation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                this.f3433a.c(a(windowInsetsAnimation));
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public WindowInsets onProgress(WindowInsets windowInsets, List list) {
                ArrayList arrayList = this.f3435c;
                if (arrayList == null) {
                    ArrayList arrayList2 = new ArrayList(list.size());
                    this.f3435c = arrayList2;
                    this.f3434b = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = (WindowInsetsAnimation) list.get(size);
                    WindowInsetsAnimationCompat a2 = a(windowInsetsAnimation);
                    a2.e(windowInsetsAnimation.getFraction());
                    this.f3435c.add(a2);
                }
                return this.f3433a.d(WindowInsetsCompat.w(windowInsets), this.f3434b).v();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                return this.f3433a.e(a(windowInsetsAnimation), BoundsCompat.d(bounds)).c();
            }
        }

        Impl30(WindowInsetsAnimation windowInsetsAnimation) {
            super(0, null, 0L);
            this.f3432e = windowInsetsAnimation;
        }

        public static WindowInsetsAnimation.Bounds e(BoundsCompat boundsCompat) {
            return new WindowInsetsAnimation.Bounds(boundsCompat.a().e(), boundsCompat.b().e());
        }

        public static Insets f(WindowInsetsAnimation.Bounds bounds) {
            return Insets.d(bounds.getUpperBound());
        }

        public static Insets g(WindowInsetsAnimation.Bounds bounds) {
            return Insets.d(bounds.getLowerBound());
        }

        public static void h(View view, Callback callback) {
            view.setWindowInsetsAnimationCallback(callback != null ? new ProxyCallback(callback) : null);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public long a() {
            return this.f3432e.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float b() {
            return this.f3432e.getInterpolatedFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public int c() {
            return this.f3432e.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public void d(float f2) {
            this.f3432e.setFraction(f2);
        }

        Impl30(int i2, Interpolator interpolator, long j2) {
            this(new WindowInsetsAnimation(i2, interpolator, j2));
        }
    }

    public static final class BoundsCompat {

        /* renamed from: a, reason: collision with root package name */
        private final Insets f3405a;

        /* renamed from: b, reason: collision with root package name */
        private final Insets f3406b;

        public BoundsCompat(Insets insets, Insets insets2) {
            this.f3405a = insets;
            this.f3406b = insets2;
        }

        public static BoundsCompat d(WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }

        public Insets a() {
            return this.f3405a;
        }

        public Insets b() {
            return this.f3406b;
        }

        public WindowInsetsAnimation.Bounds c() {
            return Impl30.e(this);
        }

        public String toString() {
            return "Bounds{lower=" + this.f3405a + " upper=" + this.f3406b + "}";
        }

        private BoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            this.f3405a = Impl30.g(bounds);
            this.f3406b = Impl30.f(bounds);
        }
    }

    private WindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        this(0, null, 0L);
        this.f3404a = new Impl30(windowInsetsAnimation);
    }
}
