package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ViewDragHelper {
    private static final Interpolator x = new Interpolator() { // from class: androidx.customview.widget.ViewDragHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private int f3602a;

    /* renamed from: b, reason: collision with root package name */
    private int f3603b;

    /* renamed from: d, reason: collision with root package name */
    private float[] f3605d;

    /* renamed from: e, reason: collision with root package name */
    private float[] f3606e;

    /* renamed from: f, reason: collision with root package name */
    private float[] f3607f;

    /* renamed from: g, reason: collision with root package name */
    private float[] f3608g;

    /* renamed from: h, reason: collision with root package name */
    private int[] f3609h;

    /* renamed from: i, reason: collision with root package name */
    private int[] f3610i;

    /* renamed from: j, reason: collision with root package name */
    private int[] f3611j;

    /* renamed from: k, reason: collision with root package name */
    private int f3612k;

    /* renamed from: l, reason: collision with root package name */
    private VelocityTracker f3613l;

    /* renamed from: m, reason: collision with root package name */
    private float f3614m;

    /* renamed from: n, reason: collision with root package name */
    private float f3615n;

    /* renamed from: o, reason: collision with root package name */
    private int f3616o;

    /* renamed from: p, reason: collision with root package name */
    private final int f3617p;

    /* renamed from: q, reason: collision with root package name */
    private int f3618q;

    /* renamed from: r, reason: collision with root package name */
    private OverScroller f3619r;

    /* renamed from: s, reason: collision with root package name */
    private final Callback f3620s;
    private View t;
    private boolean u;
    private final ViewGroup v;

    /* renamed from: c, reason: collision with root package name */
    private int f3604c = -1;
    private final Runnable w = new Runnable() { // from class: androidx.customview.widget.ViewDragHelper.2
        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper.this.L(0);
        }
    };

    public static abstract class Callback {
        public int a(View view, int i2, int i3) {
            return 0;
        }

        public int b(View view, int i2, int i3) {
            return 0;
        }

        public int c(int i2) {
            return i2;
        }

        public int d(View view) {
            return 0;
        }

        public int e(View view) {
            return 0;
        }

        public void f(int i2, int i3) {
        }

        public boolean g(int i2) {
            return false;
        }

        public void h(int i2, int i3) {
        }

        public void i(View view, int i2) {
        }

        public void j(int i2) {
        }

        public void k(View view, int i2, int i3, int i4, int i5) {
        }

        public void l(View view, float f2, float f3) {
        }

        public abstract boolean m(View view, int i2);
    }

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.v = viewGroup;
        this.f3620s = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int i2 = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f3617p = i2;
        this.f3616o = i2;
        this.f3603b = viewConfiguration.getScaledTouchSlop();
        this.f3614m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f3615n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f3619r = new OverScroller(context, x);
    }

    private boolean E(int i2) {
        if (D(i2)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private void H() {
        this.f3613l.computeCurrentVelocity(1000, this.f3614m);
        q(h(this.f3613l.getXVelocity(this.f3604c), this.f3615n, this.f3614m), h(this.f3613l.getYVelocity(this.f3604c), this.f3615n, this.f3614m));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.customview.widget.ViewDragHelper$Callback] */
    private void I(float f2, float f3, int i2) {
        boolean d2 = d(f2, f3, i2, 1);
        boolean z = d2;
        if (d(f3, f2, i2, 4)) {
            z = (d2 ? 1 : 0) | 4;
        }
        boolean z2 = z;
        if (d(f2, f3, i2, 2)) {
            z2 = (z ? 1 : 0) | 2;
        }
        ?? r0 = z2;
        if (d(f3, f2, i2, 8)) {
            r0 = (z2 ? 1 : 0) | 8;
        }
        if (r0 != 0) {
            int[] iArr = this.f3610i;
            iArr[i2] = iArr[i2] | r0;
            this.f3620s.f(r0, i2);
        }
    }

    private void J(float f2, float f3, int i2) {
        t(i2);
        float[] fArr = this.f3605d;
        this.f3607f[i2] = f2;
        fArr[i2] = f2;
        float[] fArr2 = this.f3606e;
        this.f3608g[i2] = f3;
        fArr2[i2] = f3;
        this.f3609h[i2] = z((int) f2, (int) f3);
        this.f3612k |= 1 << i2;
    }

    private void K(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (E(pointerId)) {
                float x2 = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.f3607f[pointerId] = x2;
                this.f3608g[pointerId] = y;
            }
        }
    }

    private boolean d(float f2, float f3, int i2, int i3) {
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        if ((this.f3609h[i2] & i3) != i3 || (this.f3618q & i3) == 0 || (this.f3611j[i2] & i3) == i3 || (this.f3610i[i2] & i3) == i3) {
            return false;
        }
        int i4 = this.f3603b;
        if (abs <= i4 && abs2 <= i4) {
            return false;
        }
        if (abs >= abs2 * 0.5f || !this.f3620s.g(i3)) {
            return (this.f3610i[i2] & i3) == 0 && abs > ((float) this.f3603b);
        }
        int[] iArr = this.f3611j;
        iArr[i2] = iArr[i2] | i3;
        return false;
    }

    private boolean g(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z = this.f3620s.d(view) > 0;
        boolean z2 = this.f3620s.e(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.f3603b) : z2 && Math.abs(f3) > ((float) this.f3603b);
        }
        float f4 = (f2 * f2) + (f3 * f3);
        int i2 = this.f3603b;
        return f4 > ((float) (i2 * i2));
    }

    private float h(float f2, float f3, float f4) {
        float abs = Math.abs(f2);
        if (abs < f3) {
            return 0.0f;
        }
        return abs > f4 ? f2 > 0.0f ? f4 : -f4 : f2;
    }

    private int i(int i2, int i3, int i4) {
        int abs = Math.abs(i2);
        if (abs < i3) {
            return 0;
        }
        return abs > i4 ? i2 > 0 ? i4 : -i4 : i2;
    }

    private void j() {
        float[] fArr = this.f3605d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f3606e, 0.0f);
        Arrays.fill(this.f3607f, 0.0f);
        Arrays.fill(this.f3608g, 0.0f);
        Arrays.fill(this.f3609h, 0);
        Arrays.fill(this.f3610i, 0);
        Arrays.fill(this.f3611j, 0);
        this.f3612k = 0;
    }

    private void k(int i2) {
        if (this.f3605d == null || !D(i2)) {
            return;
        }
        this.f3605d[i2] = 0.0f;
        this.f3606e[i2] = 0.0f;
        this.f3607f[i2] = 0.0f;
        this.f3608g[i2] = 0.0f;
        this.f3609h[i2] = 0;
        this.f3610i[i2] = 0;
        this.f3611j[i2] = 0;
        this.f3612k = (~(1 << i2)) & this.f3612k;
    }

    private int l(int i2, int i3, int i4) {
        if (i2 == 0) {
            return 0;
        }
        int width = this.v.getWidth();
        float f2 = width / 2;
        float r2 = f2 + (r(Math.min(1.0f, Math.abs(i2) / width)) * f2);
        int abs = Math.abs(i3);
        return Math.min(abs > 0 ? Math.round(Math.abs(r2 / abs) * 1000.0f) * 4 : (int) (((Math.abs(i2) / i4) + 1.0f) * 256.0f), 600);
    }

    private int m(View view, int i2, int i3, int i4, int i5) {
        float f2;
        float f3;
        float f4;
        float f5;
        int i6 = i(i4, (int) this.f3615n, (int) this.f3614m);
        int i7 = i(i5, (int) this.f3615n, (int) this.f3614m);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(i6);
        int abs4 = Math.abs(i7);
        int i8 = abs3 + abs4;
        int i9 = abs + abs2;
        if (i6 != 0) {
            f2 = abs3;
            f3 = i8;
        } else {
            f2 = abs;
            f3 = i9;
        }
        float f6 = f2 / f3;
        if (i7 != 0) {
            f4 = abs4;
            f5 = i8;
        } else {
            f4 = abs2;
            f5 = i9;
        }
        return (int) ((l(i2, i6, this.f3620s.d(view)) * f6) + (l(i3, i7, this.f3620s.e(view)) * (f4 / f5)));
    }

    public static ViewDragHelper o(ViewGroup viewGroup, float f2, Callback callback) {
        ViewDragHelper p2 = p(viewGroup, callback);
        p2.f3603b = (int) (p2.f3603b * (1.0f / f2));
        return p2;
    }

    public static ViewDragHelper p(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private void q(float f2, float f3) {
        this.u = true;
        this.f3620s.l(this.t, f2, f3);
        this.u = false;
        if (this.f3602a == 1) {
            L(0);
        }
    }

    private float r(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    private void s(int i2, int i3, int i4, int i5) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        if (i4 != 0) {
            i2 = this.f3620s.a(this.t, i2, i4);
            ViewCompat.S(this.t, i2 - left);
        }
        int i6 = i2;
        if (i5 != 0) {
            i3 = this.f3620s.b(this.t, i3, i5);
            ViewCompat.T(this.t, i3 - top);
        }
        int i7 = i3;
        if (i4 == 0 && i5 == 0) {
            return;
        }
        this.f3620s.k(this.t, i6, i7, i6 - left, i7 - top);
    }

    private void t(int i2) {
        float[] fArr = this.f3605d;
        if (fArr == null || fArr.length <= i2) {
            int i3 = i2 + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f3606e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f3607f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f3608g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f3609h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f3610i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f3611j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f3605d = fArr2;
            this.f3606e = fArr3;
            this.f3607f = fArr4;
            this.f3608g = fArr5;
            this.f3609h = iArr;
            this.f3610i = iArr2;
            this.f3611j = iArr3;
        }
    }

    private boolean v(int i2, int i3, int i4, int i5) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.f3619r.abortAnimation();
            L(0);
            return false;
        }
        this.f3619r.startScroll(left, top, i6, i7, m(this.t, i6, i7, i4, i5));
        L(2);
        return true;
    }

    private int z(int i2, int i3) {
        int i4 = i2 < this.v.getLeft() + this.f3616o ? 1 : 0;
        if (i3 < this.v.getTop() + this.f3616o) {
            i4 |= 4;
        }
        if (i2 > this.v.getRight() - this.f3616o) {
            i4 |= 2;
        }
        return i3 > this.v.getBottom() - this.f3616o ? i4 | 8 : i4;
    }

    public int A() {
        return this.f3603b;
    }

    public int B() {
        return this.f3602a;
    }

    public boolean C(int i2, int i3) {
        return F(this.t, i2, i3);
    }

    public boolean D(int i2) {
        return (this.f3612k & (1 << i2)) != 0;
    }

    public boolean F(View view, int i2, int i3) {
        return view != null && i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom();
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0066, code lost:
    
        if (r9.f3604c == (-1)) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006c, code lost:
    
        H();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void G(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 377
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.G(android.view.MotionEvent):void");
    }

    void L(int i2) {
        this.v.removeCallbacks(this.w);
        if (this.f3602a != i2) {
            this.f3602a = i2;
            this.f3620s.j(i2);
            if (this.f3602a == 0) {
                this.t = null;
            }
        }
    }

    public void M(int i2) {
        this.f3616o = i2;
    }

    public void N(int i2) {
        this.f3618q = i2;
    }

    public void O(float f2) {
        this.f3615n = f2;
    }

    public boolean P(int i2, int i3) {
        if (this.u) {
            return v(i2, i3, (int) this.f3613l.getXVelocity(this.f3604c), (int) this.f3613l.getYVelocity(this.f3604c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00dd, code lost:
    
        if (r12 != r11) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean Q(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.Q(android.view.MotionEvent):boolean");
    }

    public boolean R(View view, int i2, int i3) {
        this.t = view;
        this.f3604c = -1;
        boolean v = v(i2, i3, 0, 0);
        if (!v && this.f3602a == 0 && this.t != null) {
            this.t = null;
        }
        return v;
    }

    boolean S(View view, int i2) {
        if (view == this.t && this.f3604c == i2) {
            return true;
        }
        if (view == null || !this.f3620s.m(view, i2)) {
            return false;
        }
        this.f3604c = i2;
        c(view, i2);
        return true;
    }

    public void a() {
        b();
        if (this.f3602a == 2) {
            int currX = this.f3619r.getCurrX();
            int currY = this.f3619r.getCurrY();
            this.f3619r.abortAnimation();
            int currX2 = this.f3619r.getCurrX();
            int currY2 = this.f3619r.getCurrY();
            this.f3620s.k(this.t, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        L(0);
    }

    public void b() {
        this.f3604c = -1;
        j();
        VelocityTracker velocityTracker = this.f3613l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f3613l = null;
        }
    }

    public void c(View view, int i2) {
        if (view.getParent() == this.v) {
            this.t = view;
            this.f3604c = i2;
            this.f3620s.i(view, i2);
            L(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.v + ")");
    }

    public boolean e(int i2) {
        int length = this.f3605d.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (f(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(int i2, int i3) {
        if (!D(i3)) {
            return false;
        }
        boolean z = (i2 & 1) == 1;
        boolean z2 = (i2 & 2) == 2;
        float f2 = this.f3607f[i3] - this.f3605d[i3];
        float f3 = this.f3608g[i3] - this.f3606e[i3];
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.f3603b) : z2 && Math.abs(f3) > ((float) this.f3603b);
        }
        float f4 = (f2 * f2) + (f3 * f3);
        int i4 = this.f3603b;
        return f4 > ((float) (i4 * i4));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
    
        if (r0 == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean n(boolean r12) {
        /*
            r11 = this;
            int r0 = r11.f3602a
            r1 = 0
            r2 = 2
            if (r0 != r2) goto L6a
            android.widget.OverScroller r0 = r11.f3619r
            boolean r0 = r0.computeScrollOffset()
            android.widget.OverScroller r3 = r11.f3619r
            int r3 = r3.getCurrX()
            android.widget.OverScroller r4 = r11.f3619r
            int r10 = r4.getCurrY()
            android.view.View r4 = r11.t
            int r4 = r4.getLeft()
            int r8 = r3 - r4
            android.view.View r4 = r11.t
            int r4 = r4.getTop()
            int r9 = r10 - r4
            if (r8 == 0) goto L2f
            android.view.View r4 = r11.t
            androidx.core.view.ViewCompat.S(r4, r8)
        L2f:
            if (r9 == 0) goto L36
            android.view.View r4 = r11.t
            androidx.core.view.ViewCompat.T(r4, r9)
        L36:
            if (r8 != 0) goto L3a
            if (r9 == 0) goto L43
        L3a:
            androidx.customview.widget.ViewDragHelper$Callback r4 = r11.f3620s
            android.view.View r5 = r11.t
            r6 = r3
            r7 = r10
            r4.k(r5, r6, r7, r8, r9)
        L43:
            if (r0 == 0) goto L5b
            android.widget.OverScroller r4 = r11.f3619r
            int r4 = r4.getFinalX()
            if (r3 != r4) goto L5b
            android.widget.OverScroller r3 = r11.f3619r
            int r3 = r3.getFinalY()
            if (r10 != r3) goto L5b
            android.widget.OverScroller r0 = r11.f3619r
            r0.abortAnimation()
            goto L5d
        L5b:
            if (r0 != 0) goto L6a
        L5d:
            if (r12 == 0) goto L67
            android.view.ViewGroup r12 = r11.v
            java.lang.Runnable r0 = r11.w
            r12.post(r0)
            goto L6a
        L67:
            r11.L(r1)
        L6a:
            int r11 = r11.f3602a
            if (r11 != r2) goto L6f
            r1 = 1
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.n(boolean):boolean");
    }

    public View u(int i2, int i3) {
        for (int childCount = this.v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.v.getChildAt(this.f3620s.c(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public View w() {
        return this.t;
    }

    public int x() {
        return this.f3617p;
    }

    public int y() {
        return this.f3616o;
    }
}
