package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.DrawableCompat;

/* loaded from: classes.dex */
public class DrawableContainerCompat extends Drawable implements Drawable.Callback {

    /* renamed from: c, reason: collision with root package name */
    private DrawableContainerState f371c;

    /* renamed from: h, reason: collision with root package name */
    private Rect f372h;

    /* renamed from: i, reason: collision with root package name */
    private Drawable f373i;

    /* renamed from: j, reason: collision with root package name */
    private Drawable f374j;

    /* renamed from: l, reason: collision with root package name */
    private boolean f376l;

    /* renamed from: n, reason: collision with root package name */
    private boolean f378n;

    /* renamed from: o, reason: collision with root package name */
    private Runnable f379o;

    /* renamed from: p, reason: collision with root package name */
    private long f380p;

    /* renamed from: q, reason: collision with root package name */
    private long f381q;

    /* renamed from: r, reason: collision with root package name */
    private BlockInvalidateCallback f382r;

    /* renamed from: k, reason: collision with root package name */
    private int f375k = 255;

    /* renamed from: m, reason: collision with root package name */
    private int f377m = -1;

    @RequiresApi
    private static class Api21Impl {
        public static boolean a(Drawable.ConstantState constantState) {
            return constantState.canApplyTheme();
        }

        public static void b(Drawable drawable, Outline outline) {
            drawable.getOutline(outline);
        }

        public static Resources c(Resources.Theme theme) {
            return theme.getResources();
        }
    }

    static class BlockInvalidateCallback implements Drawable.Callback {

        /* renamed from: c, reason: collision with root package name */
        private Drawable.Callback f384c;

        BlockInvalidateCallback() {
        }

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f384c;
            this.f384c = null;
            return callback;
        }

        public BlockInvalidateCallback b(Drawable.Callback callback) {
            this.f384c = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
            Drawable.Callback callback = this.f384c;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j2);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f384c;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    static abstract class DrawableContainerState extends Drawable.ConstantState {
        int A;
        int B;
        boolean C;
        ColorFilter D;
        boolean E;
        ColorStateList F;
        PorterDuff.Mode G;
        boolean H;
        boolean I;

        /* renamed from: a, reason: collision with root package name */
        final DrawableContainerCompat f385a;

        /* renamed from: b, reason: collision with root package name */
        Resources f386b;

        /* renamed from: c, reason: collision with root package name */
        int f387c;

        /* renamed from: d, reason: collision with root package name */
        int f388d;

        /* renamed from: e, reason: collision with root package name */
        int f389e;

        /* renamed from: f, reason: collision with root package name */
        SparseArray f390f;

        /* renamed from: g, reason: collision with root package name */
        Drawable[] f391g;

        /* renamed from: h, reason: collision with root package name */
        int f392h;

        /* renamed from: i, reason: collision with root package name */
        boolean f393i;

        /* renamed from: j, reason: collision with root package name */
        boolean f394j;

        /* renamed from: k, reason: collision with root package name */
        Rect f395k;

        /* renamed from: l, reason: collision with root package name */
        boolean f396l;

        /* renamed from: m, reason: collision with root package name */
        boolean f397m;

        /* renamed from: n, reason: collision with root package name */
        int f398n;

        /* renamed from: o, reason: collision with root package name */
        int f399o;

        /* renamed from: p, reason: collision with root package name */
        int f400p;

        /* renamed from: q, reason: collision with root package name */
        int f401q;

        /* renamed from: r, reason: collision with root package name */
        boolean f402r;

        /* renamed from: s, reason: collision with root package name */
        int f403s;
        boolean t;
        boolean u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        int z;

        DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainerCompat drawableContainerCompat, Resources resources) {
            this.f393i = false;
            this.f396l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.f385a = drawableContainerCompat;
            this.f386b = resources != null ? resources : drawableContainerState != null ? drawableContainerState.f386b : null;
            int resolveDensity = DrawableContainerCompat.resolveDensity(resources, drawableContainerState != null ? drawableContainerState.f387c : 0);
            this.f387c = resolveDensity;
            if (drawableContainerState == null) {
                this.f391g = new Drawable[10];
                this.f392h = 0;
                return;
            }
            this.f388d = drawableContainerState.f388d;
            this.f389e = drawableContainerState.f389e;
            this.v = true;
            this.w = true;
            this.f393i = drawableContainerState.f393i;
            this.f396l = drawableContainerState.f396l;
            this.x = drawableContainerState.x;
            this.y = drawableContainerState.y;
            this.z = drawableContainerState.z;
            this.A = drawableContainerState.A;
            this.B = drawableContainerState.B;
            this.C = drawableContainerState.C;
            this.D = drawableContainerState.D;
            this.E = drawableContainerState.E;
            this.F = drawableContainerState.F;
            this.G = drawableContainerState.G;
            this.H = drawableContainerState.H;
            this.I = drawableContainerState.I;
            if (drawableContainerState.f387c == resolveDensity) {
                if (drawableContainerState.f394j) {
                    this.f395k = drawableContainerState.f395k != null ? new Rect(drawableContainerState.f395k) : null;
                    this.f394j = true;
                }
                if (drawableContainerState.f397m) {
                    this.f398n = drawableContainerState.f398n;
                    this.f399o = drawableContainerState.f399o;
                    this.f400p = drawableContainerState.f400p;
                    this.f401q = drawableContainerState.f401q;
                    this.f397m = true;
                }
            }
            if (drawableContainerState.f402r) {
                this.f403s = drawableContainerState.f403s;
                this.f402r = true;
            }
            if (drawableContainerState.t) {
                this.u = drawableContainerState.u;
                this.t = true;
            }
            Drawable[] drawableArr = drawableContainerState.f391g;
            this.f391g = new Drawable[drawableArr.length];
            this.f392h = drawableContainerState.f392h;
            SparseArray sparseArray = drawableContainerState.f390f;
            if (sparseArray != null) {
                this.f390f = sparseArray.clone();
            } else {
                this.f390f = new SparseArray(this.f392h);
            }
            int i2 = this.f392h;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable != null) {
                    Drawable.ConstantState constantState = drawable.getConstantState();
                    if (constantState != null) {
                        this.f390f.put(i3, constantState);
                    } else {
                        this.f391g[i3] = drawableArr[i3];
                    }
                }
            }
        }

        private void f() {
            SparseArray sparseArray = this.f390f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.f391g[this.f390f.keyAt(i2)] = u(((Drawable.ConstantState) this.f390f.valueAt(i2)).newDrawable(this.f386b));
                }
                this.f390f = null;
            }
        }

        private Drawable u(Drawable drawable) {
            DrawableCompat.m(drawable, this.z);
            Drawable mutate = drawable.mutate();
            mutate.setCallback(this.f385a);
            return mutate;
        }

        final void A(Resources resources) {
            if (resources != null) {
                this.f386b = resources;
                int resolveDensity = DrawableContainerCompat.resolveDensity(resources, this.f387c);
                int i2 = this.f387c;
                this.f387c = resolveDensity;
                if (i2 != resolveDensity) {
                    this.f397m = false;
                    this.f394j = false;
                }
            }
        }

        public final int a(Drawable drawable) {
            int i2 = this.f392h;
            if (i2 >= this.f391g.length) {
                p(i2, i2 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f385a);
            this.f391g[i2] = drawable;
            this.f392h++;
            this.f389e = drawable.getChangingConfigurations() | this.f389e;
            q();
            this.f395k = null;
            this.f394j = false;
            this.f397m = false;
            this.v = false;
            return i2;
        }

        final void b(Resources.Theme theme) {
            if (theme != null) {
                f();
                int i2 = this.f392h;
                Drawable[] drawableArr = this.f391g;
                for (int i3 = 0; i3 < i2; i3++) {
                    Drawable drawable = drawableArr[i3];
                    if (drawable != null && DrawableCompat.b(drawable)) {
                        DrawableCompat.a(drawableArr[i3], theme);
                        this.f389e |= drawableArr[i3].getChangingConfigurations();
                    }
                }
                A(Api21Impl.c(theme));
            }
        }

        public boolean c() {
            if (this.v) {
                return this.w;
            }
            f();
            this.v = true;
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getConstantState() == null) {
                    this.w = false;
                    return false;
                }
            }
            this.w = true;
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable == null) {
                    Drawable.ConstantState constantState = (Drawable.ConstantState) this.f390f.get(i3);
                    if (constantState != null && Api21Impl.a(constantState)) {
                        return true;
                    }
                } else if (DrawableCompat.b(drawable)) {
                    return true;
                }
            }
            return false;
        }

        final void d() {
            this.y = false;
        }

        protected void e() {
            this.f397m = true;
            f();
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            this.f399o = -1;
            this.f398n = -1;
            this.f401q = 0;
            this.f400p = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.f398n) {
                    this.f398n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.f399o) {
                    this.f399o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.f400p) {
                    this.f400p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.f401q) {
                    this.f401q = minimumHeight;
                }
            }
        }

        final int g() {
            return this.f391g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f389e | this.f388d;
        }

        public final Drawable h(int i2) {
            int indexOfKey;
            Drawable drawable = this.f391g[i2];
            if (drawable != null) {
                return drawable;
            }
            SparseArray sparseArray = this.f390f;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i2)) < 0) {
                return null;
            }
            Drawable u = u(((Drawable.ConstantState) this.f390f.valueAt(indexOfKey)).newDrawable(this.f386b));
            this.f391g[i2] = u;
            this.f390f.removeAt(indexOfKey);
            if (this.f390f.size() == 0) {
                this.f390f = null;
            }
            return u;
        }

        public final int i() {
            return this.f392h;
        }

        public final int j() {
            if (!this.f397m) {
                e();
            }
            return this.f399o;
        }

        public final int k() {
            if (!this.f397m) {
                e();
            }
            return this.f401q;
        }

        public final int l() {
            if (!this.f397m) {
                e();
            }
            return this.f400p;
        }

        public final Rect m() {
            Rect rect = null;
            if (this.f393i) {
                return null;
            }
            Rect rect2 = this.f395k;
            if (rect2 != null || this.f394j) {
                return rect2;
            }
            f();
            Rect rect3 = new Rect();
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getPadding(rect3)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    int i4 = rect3.left;
                    if (i4 > rect.left) {
                        rect.left = i4;
                    }
                    int i5 = rect3.top;
                    if (i5 > rect.top) {
                        rect.top = i5;
                    }
                    int i6 = rect3.right;
                    if (i6 > rect.right) {
                        rect.right = i6;
                    }
                    int i7 = rect3.bottom;
                    if (i7 > rect.bottom) {
                        rect.bottom = i7;
                    }
                }
            }
            this.f394j = true;
            this.f395k = rect;
            return rect;
        }

        public final int n() {
            if (!this.f397m) {
                e();
            }
            return this.f398n;
        }

        public final int o() {
            if (this.f402r) {
                return this.f403s;
            }
            f();
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            int opacity = i2 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i3 = 1; i3 < i2; i3++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i3].getOpacity());
            }
            this.f403s = opacity;
            this.f402r = true;
            return opacity;
        }

        public void p(int i2, int i3) {
            Drawable[] drawableArr = new Drawable[i3];
            Drawable[] drawableArr2 = this.f391g;
            if (drawableArr2 != null) {
                System.arraycopy(drawableArr2, 0, drawableArr, 0, i2);
            }
            this.f391g = drawableArr;
        }

        void q() {
            this.f402r = false;
            this.t = false;
        }

        public final boolean r() {
            return this.f396l;
        }

        public final boolean s() {
            if (this.t) {
                return this.u;
            }
            f();
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            boolean z = false;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                if (drawableArr[i3].isStateful()) {
                    z = true;
                    break;
                }
                i3++;
            }
            this.u = z;
            this.t = true;
            return z;
        }

        void t() {
            int i2 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable != null) {
                    drawable.mutate();
                }
            }
            this.y = true;
        }

        public final void v(boolean z) {
            this.f396l = z;
        }

        public final void w(int i2) {
            this.A = i2;
        }

        public final void x(int i2) {
            this.B = i2;
        }

        final boolean y(int i2, int i3) {
            int i4 = this.f392h;
            Drawable[] drawableArr = this.f391g;
            boolean z = false;
            for (int i5 = 0; i5 < i4; i5++) {
                Drawable drawable = drawableArr[i5];
                if (drawable != null) {
                    boolean m2 = DrawableCompat.m(drawable, i2);
                    if (i5 == i3) {
                        z = m2;
                    }
                }
            }
            this.z = i2;
            return z;
        }

        public final void z(boolean z) {
            this.f393i = z;
        }
    }

    private void d(Drawable drawable) {
        if (this.f382r == null) {
            this.f382r = new BlockInvalidateCallback();
        }
        drawable.setCallback(this.f382r.b(drawable.getCallback()));
        try {
            if (this.f371c.A <= 0 && this.f376l) {
                drawable.setAlpha(this.f375k);
            }
            DrawableContainerState drawableContainerState = this.f371c;
            if (drawableContainerState.E) {
                drawable.setColorFilter(drawableContainerState.D);
            } else {
                if (drawableContainerState.H) {
                    DrawableCompat.o(drawable, drawableContainerState.F);
                }
                DrawableContainerState drawableContainerState2 = this.f371c;
                if (drawableContainerState2.I) {
                    DrawableCompat.p(drawable, drawableContainerState2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f371c.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            DrawableCompat.m(drawable, DrawableCompat.f(this));
            DrawableCompat.j(drawable, this.f371c.C);
            Rect rect = this.f372h;
            if (rect != null) {
                DrawableCompat.l(drawable, rect.left, rect.top, rect.right, rect.bottom);
            }
            drawable.setCallback(this.f382r.a());
        } catch (Throwable th) {
            drawable.setCallback(this.f382r.a());
            throw th;
        }
    }

    private boolean e() {
        return isAutoMirrored() && DrawableCompat.f(this) == 1;
    }

    static int resolveDensity(Resources resources, int i2) {
        if (resources != null) {
            i2 = resources.getDisplayMetrics().densityDpi;
        }
        if (i2 == 0) {
            return 160;
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.f376l = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f373i
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r8 = 0
            if (r3 == 0) goto L36
            long r9 = r13.f380p
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.f375k
            r3.setAlpha(r9)
            r13.f380p = r6
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r10 = r13.f371c
            int r10 = r10.A
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.f375k
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.f380p = r6
        L38:
            r3 = r8
        L39:
            android.graphics.drawable.Drawable r9 = r13.f374j
            if (r9 == 0) goto L61
            long r10 = r13.f381q
            int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r8, r8)
            r0 = 0
            r13.f374j = r0
            r13.f381q = r6
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r4 = r13.f371c
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.f375k
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.f381q = r6
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            java.lang.Runnable r14 = r13.f379o
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.DrawableContainerCompat.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.f371c.b(theme);
    }

    DrawableContainerState b() {
        return this.f371c;
    }

    int c() {
        return this.f377m;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.f371c.canApplyTheme();
    }

    void clearMutated() {
        this.f371c.d();
        this.f378n = false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f373i;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f374j;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean f(int r10) {
        /*
            r9 = this;
            int r0 = r9.f377m
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r0 = r9.f371c
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.f374j
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.f373i
            if (r0 == 0) goto L29
            r9.f374j = r0
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r0 = r9.f371c
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.f381q = r0
            goto L35
        L29:
            r9.f374j = r4
            r9.f381q = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.f373i
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r0 = r9.f371c
            int r1 = r0.f392h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.h(r10)
            r9.f373i = r0
            r9.f377m = r10
            if (r0 == 0) goto L5a
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r10 = r9.f371c
            int r10 = r10.A
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.f380p = r2
        L51:
            r9.d(r0)
            goto L5a
        L55:
            r9.f373i = r4
            r10 = -1
            r9.f377m = r10
        L5a:
            long r0 = r9.f380p
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.f381q
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L79
        L67:
            java.lang.Runnable r10 = r9.f379o
            if (r10 != 0) goto L73
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$1 r10 = new androidx.appcompat.graphics.drawable.DrawableContainerCompat$1
            r10.<init>()
            r9.f379o = r10
            goto L76
        L73:
            r9.unscheduleSelf(r10)
        L76:
            r9.a(r0)
        L79:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.DrawableContainerCompat.f(int):boolean");
    }

    void g(DrawableContainerState drawableContainerState) {
        this.f371c = drawableContainerState;
        int i2 = this.f377m;
        if (i2 >= 0) {
            Drawable h2 = drawableContainerState.h(i2);
            this.f373i = h2;
            if (h2 != null) {
                d(h2);
            }
        }
        this.f374j = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f375k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.f371c.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f371c.c()) {
            return null;
        }
        this.f371c.f388d = getChangingConfigurations();
        return this.f371c;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f373i;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f372h;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f371c.r()) {
            return this.f371c.j();
        }
        Drawable drawable = this.f373i;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f371c.r()) {
            return this.f371c.n();
        }
        Drawable drawable = this.f373i;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.f371c.r()) {
            return this.f371c.k();
        }
        Drawable drawable = this.f373i;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.f371c.r()) {
            return this.f371c.l();
        }
        Drawable drawable = this.f373i;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f373i;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f371c.o();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.f373i;
        if (drawable != null) {
            Api21Impl.b(drawable, outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect m2 = this.f371c.m();
        if (m2 != null) {
            rect.set(m2);
            padding = (m2.right | ((m2.left | m2.top) | m2.bottom)) != 0;
        } else {
            Drawable drawable = this.f373i;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (e()) {
            int i2 = rect.left;
            rect.left = rect.right;
            rect.right = i2;
        }
        return padding;
    }

    final void h(Resources resources) {
        this.f371c.A(resources);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        DrawableContainerState drawableContainerState = this.f371c;
        if (drawableContainerState != null) {
            drawableContainerState.q();
        }
        if (drawable != this.f373i || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f371c.C;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.f371c.s();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f374j;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f374j = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f373i;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f376l) {
                this.f373i.setAlpha(this.f375k);
            }
        }
        if (this.f381q != 0) {
            this.f381q = 0L;
        } else {
            z2 = z;
        }
        if (this.f380p != 0) {
            this.f380p = 0L;
        } else if (!z2) {
            return;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f378n && super.mutate() == this) {
            DrawableContainerState b2 = b();
            b2.t();
            g(b2);
            this.f378n = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f374j;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f373i;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        return this.f371c.y(i2, c());
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        Drawable drawable = this.f374j;
        if (drawable != null) {
            return drawable.setLevel(i2);
        }
        Drawable drawable2 = this.f373i;
        if (drawable2 != null) {
            return drawable2.setLevel(i2);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f374j;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f373i;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        if (drawable != this.f373i || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.f376l && this.f375k == i2) {
            return;
        }
        this.f376l = true;
        this.f375k = i2;
        Drawable drawable = this.f373i;
        if (drawable != null) {
            if (this.f380p == 0) {
                drawable.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        DrawableContainerState drawableContainerState = this.f371c;
        if (drawableContainerState.C != z) {
            drawableContainerState.C = z;
            Drawable drawable = this.f373i;
            if (drawable != null) {
                DrawableCompat.j(drawable, z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        DrawableContainerState drawableContainerState = this.f371c;
        drawableContainerState.E = true;
        if (drawableContainerState.D != colorFilter) {
            drawableContainerState.D = colorFilter;
            Drawable drawable = this.f373i;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        DrawableContainerState drawableContainerState = this.f371c;
        if (drawableContainerState.x != z) {
            drawableContainerState.x = z;
            Drawable drawable = this.f373i;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        Drawable drawable = this.f373i;
        if (drawable != null) {
            DrawableCompat.k(drawable, f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        Rect rect = this.f372h;
        if (rect == null) {
            this.f372h = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        Drawable drawable = this.f373i;
        if (drawable != null) {
            DrawableCompat.l(drawable, i2, i3, i4, i5);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        DrawableContainerState drawableContainerState = this.f371c;
        drawableContainerState.H = true;
        if (drawableContainerState.F != colorStateList) {
            drawableContainerState.F = colorStateList;
            DrawableCompat.o(this.f373i, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        DrawableContainerState drawableContainerState = this.f371c;
        drawableContainerState.I = true;
        if (drawableContainerState.G != mode) {
            drawableContainerState.G = mode;
            DrawableCompat.p(this.f373i, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f374j;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f373i;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f373i || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
