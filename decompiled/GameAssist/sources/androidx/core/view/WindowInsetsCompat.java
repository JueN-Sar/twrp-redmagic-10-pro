package androidx.core.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Objects;

/* loaded from: classes.dex */
public class WindowInsetsCompat {

    /* renamed from: b, reason: collision with root package name */
    public static final WindowInsetsCompat f3439b = Impl30.f3462l;

    /* renamed from: a, reason: collision with root package name */
    private final Impl f3440a;

    @RequiresApi
    @SuppressLint({"SoonBlockedPrivateApi"})
    static class Api21ReflectionHolder {

        /* renamed from: a, reason: collision with root package name */
        private static Field f3441a;

        /* renamed from: b, reason: collision with root package name */
        private static Field f3442b;

        /* renamed from: c, reason: collision with root package name */
        private static Field f3443c;

        /* renamed from: d, reason: collision with root package name */
        private static boolean f3444d;

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                f3441a = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                f3442b = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                f3443c = declaredField3;
                declaredField3.setAccessible(true);
                f3444d = true;
            } catch (ReflectiveOperationException e2) {
                Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + e2.getMessage(), e2);
            }
        }

        public static WindowInsetsCompat a(View view) {
            if (f3444d && view.isAttachedToWindow()) {
                try {
                    Object obj = f3441a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) f3442b.get(obj);
                        Rect rect2 = (Rect) f3443c.get(obj);
                        if (rect != null && rect2 != null) {
                            WindowInsetsCompat a2 = new Builder().c(Insets.c(rect)).d(Insets.c(rect2)).a();
                            a2.t(a2);
                            a2.d(view.getRootView());
                            return a2;
                        }
                    }
                } catch (IllegalAccessException e2) {
                    Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e2.getMessage(), e2);
                }
            }
            return null;
        }
    }

    private static class BuilderImpl {

        /* renamed from: a, reason: collision with root package name */
        private final WindowInsetsCompat f3446a;

        /* renamed from: b, reason: collision with root package name */
        Insets[] f3447b;

        BuilderImpl() {
            this(new WindowInsetsCompat((WindowInsetsCompat) null));
        }

        protected final void a() {
            Insets[] insetsArr = this.f3447b;
            if (insetsArr != null) {
                Insets insets = insetsArr[Type.b(1)];
                Insets insets2 = this.f3447b[Type.b(2)];
                if (insets2 == null) {
                    insets2 = this.f3446a.f(2);
                }
                if (insets == null) {
                    insets = this.f3446a.f(1);
                }
                g(Insets.a(insets, insets2));
                Insets insets3 = this.f3447b[Type.b(16)];
                if (insets3 != null) {
                    f(insets3);
                }
                Insets insets4 = this.f3447b[Type.b(32)];
                if (insets4 != null) {
                    d(insets4);
                }
                Insets insets5 = this.f3447b[Type.b(64)];
                if (insets5 != null) {
                    h(insets5);
                }
            }
        }

        @NonNull
        WindowInsetsCompat b() {
            a();
            return this.f3446a;
        }

        void c(int i2, @NonNull Insets insets) {
            if (this.f3447b == null) {
                this.f3447b = new Insets[9];
            }
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    this.f3447b[Type.b(i3)] = insets;
                }
            }
        }

        void d(@NonNull Insets insets) {
        }

        void e(@NonNull Insets insets) {
        }

        void f(@NonNull Insets insets) {
        }

        void g(@NonNull Insets insets) {
        }

        void h(@NonNull Insets insets) {
        }

        BuilderImpl(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.f3446a = windowInsetsCompat;
        }
    }

    @RequiresApi
    private static class BuilderImpl20 extends BuilderImpl {

        /* renamed from: c, reason: collision with root package name */
        private WindowInsets f3448c;

        /* renamed from: d, reason: collision with root package name */
        private Insets f3449d;

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        @NonNull
        WindowInsetsCompat b() {
            a();
            WindowInsetsCompat w = WindowInsetsCompat.w(this.f3448c);
            w.r(this.f3447b);
            w.u(this.f3449d);
            return w;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void e(@Nullable Insets insets) {
            this.f3449d = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void g(@NonNull Insets insets) {
            WindowInsets windowInsets = this.f3448c;
            if (windowInsets != null) {
                this.f3448c = windowInsets.replaceSystemWindowInsets(insets.f2920a, insets.f2921b, insets.f2922c, insets.f2923d);
            }
        }
    }

    @RequiresApi
    private static class BuilderImpl30 extends BuilderImpl29 {
        BuilderImpl30() {
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void c(int i2, @NonNull Insets insets) {
            this.f3450c.setInsets(TypeImpl30.a(i2), insets.e());
        }

        BuilderImpl30(@NonNull WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
        }
    }

    private static class Impl {

        /* renamed from: b, reason: collision with root package name */
        static final WindowInsetsCompat f3451b = new Builder().a().a().b().c();

        /* renamed from: a, reason: collision with root package name */
        final WindowInsetsCompat f3452a;

        Impl(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.f3452a = windowInsetsCompat;
        }

        @NonNull
        WindowInsetsCompat a() {
            return this.f3452a;
        }

        @NonNull
        WindowInsetsCompat b() {
            return this.f3452a;
        }

        @NonNull
        WindowInsetsCompat c() {
            return this.f3452a;
        }

        void d(@NonNull View view) {
        }

        void e(@NonNull WindowInsetsCompat windowInsetsCompat) {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Impl)) {
                return false;
            }
            Impl impl = (Impl) obj;
            return o() == impl.o() && n() == impl.n() && ObjectsCompat.a(k(), impl.k()) && ObjectsCompat.a(i(), impl.i()) && ObjectsCompat.a(f(), impl.f());
        }

        @Nullable
        DisplayCutoutCompat f() {
            return null;
        }

        @NonNull
        Insets g(int i2) {
            return Insets.f2919e;
        }

        @NonNull
        Insets h() {
            return k();
        }

        public int hashCode() {
            return ObjectsCompat.b(Boolean.valueOf(o()), Boolean.valueOf(n()), k(), i(), f());
        }

        @NonNull
        Insets i() {
            return Insets.f2919e;
        }

        @NonNull
        Insets j() {
            return k();
        }

        @NonNull
        Insets k() {
            return Insets.f2919e;
        }

        @NonNull
        Insets l() {
            return k();
        }

        @NonNull
        WindowInsetsCompat m(int i2, int i3, int i4, int i5) {
            return f3451b;
        }

        boolean n() {
            return false;
        }

        boolean o() {
            return false;
        }

        public void p(Insets[] insetsArr) {
        }

        void q(@NonNull Insets insets) {
        }

        void r(@Nullable WindowInsetsCompat windowInsetsCompat) {
        }

        public void s(Insets insets) {
        }
    }

    @RequiresApi
    private static class Impl28 extends Impl21 {
        Impl28(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        WindowInsetsCompat a() {
            return WindowInsetsCompat.w(this.f3453c.consumeDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Impl28)) {
                return false;
            }
            Impl28 impl28 = (Impl28) obj;
            return Objects.equals(this.f3453c, impl28.f3453c) && Objects.equals(this.f3457g, impl28.f3457g);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @Nullable
        DisplayCutoutCompat f() {
            return DisplayCutoutCompat.e(this.f3453c.getDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public int hashCode() {
            return this.f3453c.hashCode();
        }

        Impl28(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Impl28 impl28) {
            super(windowInsetsCompat, impl28);
        }
    }

    @RequiresApi
    private static class Impl30 extends Impl29 {

        /* renamed from: l, reason: collision with root package name */
        static final WindowInsetsCompat f3462l = WindowInsetsCompat.w(WindowInsets.CONSUMED);

        Impl30(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        final void d(@NonNull View view) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets g(int i2) {
            return Insets.d(this.f3453c.getInsets(TypeImpl30.a(i2)));
        }

        Impl30(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Impl30 impl30) {
            super(windowInsetsCompat, impl30);
        }
    }

    public static final class Type {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface InsetsType {
        }

        public static int a() {
            return 8;
        }

        static int b(int i2) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 32) {
                return 5;
            }
            if (i2 == 64) {
                return 6;
            }
            if (i2 == 128) {
                return 7;
            }
            if (i2 == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i2);
        }

        public static int c() {
            return 32;
        }

        public static int d() {
            return 2;
        }

        public static int e() {
            return 7;
        }
    }

    @RequiresApi
    private static final class TypeImpl30 {
        static int a(int i2) {
            int statusBars;
            int i3 = 0;
            for (int i4 = 1; i4 <= 256; i4 <<= 1) {
                if ((i2 & i4) != 0) {
                    if (i4 == 1) {
                        statusBars = WindowInsets.Type.statusBars();
                    } else if (i4 == 2) {
                        statusBars = WindowInsets.Type.navigationBars();
                    } else if (i4 == 4) {
                        statusBars = WindowInsets.Type.captionBar();
                    } else if (i4 == 8) {
                        statusBars = WindowInsets.Type.ime();
                    } else if (i4 == 16) {
                        statusBars = WindowInsets.Type.systemGestures();
                    } else if (i4 == 32) {
                        statusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i4 == 64) {
                        statusBars = WindowInsets.Type.tappableElement();
                    } else if (i4 == 128) {
                        statusBars = WindowInsets.Type.displayCutout();
                    }
                    i3 |= statusBars;
                }
            }
            return i3;
        }
    }

    private WindowInsetsCompat(WindowInsets windowInsets) {
        this.f3440a = new Impl30(this, windowInsets);
    }

    static Insets o(Insets insets, int i2, int i3, int i4, int i5) {
        int max = Math.max(0, insets.f2920a - i2);
        int max2 = Math.max(0, insets.f2921b - i3);
        int max3 = Math.max(0, insets.f2922c - i4);
        int max4 = Math.max(0, insets.f2923d - i5);
        return (max == i2 && max2 == i3 && max3 == i4 && max4 == i5) ? insets : Insets.b(max, max2, max3, max4);
    }

    public static WindowInsetsCompat w(WindowInsets windowInsets) {
        return x(windowInsets, null);
    }

    public static WindowInsetsCompat x(WindowInsets windowInsets, View view) {
        WindowInsetsCompat windowInsetsCompat = new WindowInsetsCompat((WindowInsets) Preconditions.h(windowInsets));
        if (view != null && view.isAttachedToWindow()) {
            windowInsetsCompat.t(ViewCompat.B(view));
            windowInsetsCompat.d(view.getRootView());
        }
        return windowInsetsCompat;
    }

    public WindowInsetsCompat a() {
        return this.f3440a.a();
    }

    public WindowInsetsCompat b() {
        return this.f3440a.b();
    }

    public WindowInsetsCompat c() {
        return this.f3440a.c();
    }

    void d(View view) {
        this.f3440a.d(view);
    }

    public DisplayCutoutCompat e() {
        return this.f3440a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WindowInsetsCompat) {
            return ObjectsCompat.a(this.f3440a, ((WindowInsetsCompat) obj).f3440a);
        }
        return false;
    }

    public Insets f(int i2) {
        return this.f3440a.g(i2);
    }

    public Insets g() {
        return this.f3440a.i();
    }

    public Insets h() {
        return this.f3440a.j();
    }

    public int hashCode() {
        Impl impl = this.f3440a;
        if (impl == null) {
            return 0;
        }
        return impl.hashCode();
    }

    public int i() {
        return this.f3440a.k().f2923d;
    }

    public int j() {
        return this.f3440a.k().f2920a;
    }

    public int k() {
        return this.f3440a.k().f2922c;
    }

    public int l() {
        return this.f3440a.k().f2921b;
    }

    public boolean m() {
        return !this.f3440a.k().equals(Insets.f2919e);
    }

    public WindowInsetsCompat n(int i2, int i3, int i4, int i5) {
        return this.f3440a.m(i2, i3, i4, i5);
    }

    public boolean p() {
        return this.f3440a.n();
    }

    public WindowInsetsCompat q(int i2, int i3, int i4, int i5) {
        return new Builder(this).d(Insets.b(i2, i3, i4, i5)).a();
    }

    void r(Insets[] insetsArr) {
        this.f3440a.p(insetsArr);
    }

    void s(Insets insets) {
        this.f3440a.q(insets);
    }

    void t(WindowInsetsCompat windowInsetsCompat) {
        this.f3440a.r(windowInsetsCompat);
    }

    void u(Insets insets) {
        this.f3440a.s(insets);
    }

    public WindowInsets v() {
        Impl impl = this.f3440a;
        if (impl instanceof Impl20) {
            return ((Impl20) impl).f3453c;
        }
        return null;
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final BuilderImpl f3445a;

        public Builder() {
            this.f3445a = new BuilderImpl30();
        }

        public WindowInsetsCompat a() {
            return this.f3445a.b();
        }

        public Builder b(int i2, Insets insets) {
            this.f3445a.c(i2, insets);
            return this;
        }

        public Builder c(Insets insets) {
            this.f3445a.e(insets);
            return this;
        }

        public Builder d(Insets insets) {
            this.f3445a.g(insets);
            return this;
        }

        public Builder(WindowInsetsCompat windowInsetsCompat) {
            this.f3445a = new BuilderImpl30(windowInsetsCompat);
        }
    }

    @RequiresApi
    private static class BuilderImpl29 extends BuilderImpl {

        /* renamed from: c, reason: collision with root package name */
        final WindowInsets.Builder f3450c;

        BuilderImpl29() {
            this.f3450c = new WindowInsets.Builder();
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        @NonNull
        WindowInsetsCompat b() {
            a();
            WindowInsetsCompat w = WindowInsetsCompat.w(this.f3450c.build());
            w.r(this.f3447b);
            return w;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void d(@NonNull Insets insets) {
            this.f3450c.setMandatorySystemGestureInsets(insets.e());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void e(@NonNull Insets insets) {
            this.f3450c.setStableInsets(insets.e());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void f(@NonNull Insets insets) {
            this.f3450c.setSystemGestureInsets(insets.e());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void g(@NonNull Insets insets) {
            this.f3450c.setSystemWindowInsets(insets.e());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        void h(@NonNull Insets insets) {
            this.f3450c.setTappableElementInsets(insets.e());
        }

        BuilderImpl29(@NonNull WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
            WindowInsets.Builder builder;
            WindowInsets v = windowInsetsCompat.v();
            if (v != null) {
                builder = new WindowInsets.Builder(v);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f3450c = builder;
        }
    }

    @RequiresApi
    private static class Impl21 extends Impl20 {

        /* renamed from: h, reason: collision with root package name */
        private Insets f3458h;

        Impl21(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
            this.f3458h = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        WindowInsetsCompat b() {
            return WindowInsetsCompat.w(this.f3453c.consumeStableInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        WindowInsetsCompat c() {
            return WindowInsetsCompat.w(this.f3453c.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        final Insets i() {
            if (this.f3458h == null) {
                this.f3458h = Insets.b(this.f3453c.getStableInsetLeft(), this.f3453c.getStableInsetTop(), this.f3453c.getStableInsetRight(), this.f3453c.getStableInsetBottom());
            }
            return this.f3458h;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        boolean n() {
            return this.f3453c.isConsumed();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void s(@Nullable Insets insets) {
            this.f3458h = insets;
        }

        Impl21(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Impl21 impl21) {
            super(windowInsetsCompat, impl21);
            this.f3458h = null;
            this.f3458h = impl21.f3458h;
        }
    }

    @RequiresApi
    private static class Impl20 extends Impl {

        /* renamed from: c, reason: collision with root package name */
        final WindowInsets f3453c;

        /* renamed from: d, reason: collision with root package name */
        private Insets[] f3454d;

        /* renamed from: e, reason: collision with root package name */
        private Insets f3455e;

        /* renamed from: f, reason: collision with root package name */
        private WindowInsetsCompat f3456f;

        /* renamed from: g, reason: collision with root package name */
        Insets f3457g;

        Impl20(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat);
            this.f3455e = null;
            this.f3453c = windowInsets;
        }

        @NonNull
        @SuppressLint({"WrongConstant"})
        private Insets t(int i2, boolean z) {
            Insets insets = Insets.f2919e;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    insets = Insets.a(insets, u(i3, z));
                }
            }
            return insets;
        }

        private Insets v() {
            WindowInsetsCompat windowInsetsCompat = this.f3456f;
            return windowInsetsCompat != null ? windowInsetsCompat.g() : Insets.f2919e;
        }

        @Nullable
        private Insets w(@NonNull View view) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        void d(@NonNull View view) {
            Insets w = w(view);
            if (w == null) {
                w = Insets.f2919e;
            }
            q(w);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        void e(@NonNull WindowInsetsCompat windowInsetsCompat) {
            windowInsetsCompat.t(this.f3456f);
            windowInsetsCompat.s(this.f3457g);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.f3457g, ((Impl20) obj).f3457g);
            }
            return false;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets g(int i2) {
            return t(i2, false);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        final Insets k() {
            if (this.f3455e == null) {
                this.f3455e = Insets.b(this.f3453c.getSystemWindowInsetLeft(), this.f3453c.getSystemWindowInsetTop(), this.f3453c.getSystemWindowInsetRight(), this.f3453c.getSystemWindowInsetBottom());
            }
            return this.f3455e;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        WindowInsetsCompat m(int i2, int i3, int i4, int i5) {
            Builder builder = new Builder(WindowInsetsCompat.w(this.f3453c));
            builder.d(WindowInsetsCompat.o(k(), i2, i3, i4, i5));
            builder.c(WindowInsetsCompat.o(i(), i2, i3, i4, i5));
            return builder.a();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        boolean o() {
            return this.f3453c.isRound();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void p(Insets[] insetsArr) {
            this.f3454d = insetsArr;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        void q(@NonNull Insets insets) {
            this.f3457g = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        void r(@Nullable WindowInsetsCompat windowInsetsCompat) {
            this.f3456f = windowInsetsCompat;
        }

        @NonNull
        protected Insets u(int i2, boolean z) {
            Insets g2;
            int i3;
            if (i2 == 1) {
                return z ? Insets.b(0, Math.max(v().f2921b, k().f2921b), 0, 0) : Insets.b(0, k().f2921b, 0, 0);
            }
            if (i2 == 2) {
                if (z) {
                    Insets v = v();
                    Insets i4 = i();
                    return Insets.b(Math.max(v.f2920a, i4.f2920a), 0, Math.max(v.f2922c, i4.f2922c), Math.max(v.f2923d, i4.f2923d));
                }
                Insets k2 = k();
                WindowInsetsCompat windowInsetsCompat = this.f3456f;
                g2 = windowInsetsCompat != null ? windowInsetsCompat.g() : null;
                int i5 = k2.f2923d;
                if (g2 != null) {
                    i5 = Math.min(i5, g2.f2923d);
                }
                return Insets.b(k2.f2920a, 0, k2.f2922c, i5);
            }
            if (i2 != 8) {
                if (i2 == 16) {
                    return j();
                }
                if (i2 == 32) {
                    return h();
                }
                if (i2 == 64) {
                    return l();
                }
                if (i2 != 128) {
                    return Insets.f2919e;
                }
                WindowInsetsCompat windowInsetsCompat2 = this.f3456f;
                DisplayCutoutCompat e2 = windowInsetsCompat2 != null ? windowInsetsCompat2.e() : f();
                return e2 != null ? Insets.b(e2.b(), e2.d(), e2.c(), e2.a()) : Insets.f2919e;
            }
            Insets[] insetsArr = this.f3454d;
            g2 = insetsArr != null ? insetsArr[Type.b(8)] : null;
            if (g2 != null) {
                return g2;
            }
            Insets k3 = k();
            Insets v2 = v();
            int i6 = k3.f2923d;
            if (i6 > v2.f2923d) {
                return Insets.b(0, 0, 0, i6);
            }
            Insets insets = this.f3457g;
            return (insets == null || insets.equals(Insets.f2919e) || (i3 = this.f3457g.f2923d) <= v2.f2923d) ? Insets.f2919e : Insets.b(0, 0, 0, i3);
        }

        Impl20(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Impl20 impl20) {
            this(windowInsetsCompat, new WindowInsets(impl20.f3453c));
        }
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat != null) {
            Impl impl = windowInsetsCompat.f3440a;
            if (impl instanceof Impl30) {
                this.f3440a = new Impl30(this, (Impl30) impl);
            } else if (impl instanceof Impl29) {
                this.f3440a = new Impl29(this, (Impl29) impl);
            } else if (impl instanceof Impl28) {
                this.f3440a = new Impl28(this, (Impl28) impl);
            } else if (impl instanceof Impl21) {
                this.f3440a = new Impl21(this, (Impl21) impl);
            } else if (impl instanceof Impl20) {
                this.f3440a = new Impl20(this, (Impl20) impl);
            } else {
                this.f3440a = new Impl(this);
            }
            impl.e(this);
            return;
        }
        this.f3440a = new Impl(this);
    }

    @RequiresApi
    private static class Impl29 extends Impl28 {

        /* renamed from: i, reason: collision with root package name */
        private Insets f3459i;

        /* renamed from: j, reason: collision with root package name */
        private Insets f3460j;

        /* renamed from: k, reason: collision with root package name */
        private Insets f3461k;

        Impl29(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
            this.f3459i = null;
            this.f3460j = null;
            this.f3461k = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        Insets h() {
            if (this.f3460j == null) {
                this.f3460j = Insets.d(this.f3453c.getMandatorySystemGestureInsets());
            }
            return this.f3460j;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        Insets j() {
            if (this.f3459i == null) {
                this.f3459i = Insets.d(this.f3453c.getSystemGestureInsets());
            }
            return this.f3459i;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        Insets l() {
            if (this.f3461k == null) {
                this.f3461k = Insets.d(this.f3453c.getTappableElementInsets());
            }
            return this.f3461k;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        WindowInsetsCompat m(int i2, int i3, int i4, int i5) {
            return WindowInsetsCompat.w(this.f3453c.inset(i2, i3, i4, i5));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl21, androidx.core.view.WindowInsetsCompat.Impl
        public void s(@Nullable Insets insets) {
        }

        Impl29(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Impl29 impl29) {
            super(windowInsetsCompat, impl29);
            this.f3459i = null;
            this.f3460j = null;
            this.f3461k = null;
        }
    }
}
