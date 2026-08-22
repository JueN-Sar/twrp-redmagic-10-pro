package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {

    /* renamed from: k, reason: collision with root package name */
    protected PointF f5110k;

    /* renamed from: l, reason: collision with root package name */
    private final DisplayMetrics f5111l;

    /* renamed from: n, reason: collision with root package name */
    private float f5113n;

    /* renamed from: i, reason: collision with root package name */
    protected final LinearInterpolator f5108i = new LinearInterpolator();

    /* renamed from: j, reason: collision with root package name */
    protected final DecelerateInterpolator f5109j = new DecelerateInterpolator();

    /* renamed from: m, reason: collision with root package name */
    private boolean f5112m = false;

    /* renamed from: o, reason: collision with root package name */
    protected int f5114o = 0;

    /* renamed from: p, reason: collision with root package name */
    protected int f5115p = 0;

    public LinearSmoothScroller(Context context) {
        this.f5111l = context.getResources().getDisplayMetrics();
    }

    private float A() {
        if (!this.f5112m) {
            this.f5113n = v(this.f5111l);
            this.f5112m = true;
        }
        return this.f5113n;
    }

    private int y(int i2, int i3) {
        int i4 = i2 - i3;
        if (i2 * i4 <= 0) {
            return 0;
        }
        return i4;
    }

    protected int B() {
        PointF pointF = this.f5110k;
        if (pointF != null) {
            float f2 = pointF.y;
            if (f2 != 0.0f) {
                return f2 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    protected void C(RecyclerView.SmoothScroller.Action action) {
        PointF a2 = a(f());
        if (a2 == null || (a2.x == 0.0f && a2.y == 0.0f)) {
            action.b(f());
            r();
            return;
        }
        i(a2);
        this.f5110k = a2;
        this.f5114o = (int) (a2.x * 10000.0f);
        this.f5115p = (int) (a2.y * 10000.0f);
        action.d((int) (this.f5114o * 1.2f), (int) (this.f5115p * 1.2f), (int) (x(10000) * 1.2f), this.f5108i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void l(int i2, int i3, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (c() == 0) {
            r();
            return;
        }
        this.f5114o = y(this.f5114o, i2);
        int y = y(this.f5115p, i3);
        this.f5115p = y;
        if (this.f5114o == 0 && y == 0) {
            C(action);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void m() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void n() {
        this.f5115p = 0;
        this.f5114o = 0;
        this.f5110k = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void o(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int t = t(view, z());
        int u = u(view, B());
        int w = w((int) Math.sqrt((t * t) + (u * u)));
        if (w > 0) {
            action.d(-t, -u, w, this.f5109j);
        }
    }

    public int s(int i2, int i3, int i4, int i5, int i6) {
        if (i6 == -1) {
            return i4 - i2;
        }
        if (i6 != 0) {
            if (i6 == 1) {
                return i5 - i3;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i7 = i4 - i2;
        if (i7 > 0) {
            return i7;
        }
        int i8 = i5 - i3;
        if (i8 < 0) {
            return i8;
        }
        return 0;
    }

    public int t(View view, int i2) {
        RecyclerView.LayoutManager e2 = e();
        if (e2 == null || !e2.q()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return s(e2.W(view) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, e2.Z(view) + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, e2.l0(), e2.w0() - e2.m0(), i2);
    }

    public int u(View view, int i2) {
        RecyclerView.LayoutManager e2 = e();
        if (e2 == null || !e2.r()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return s(e2.a0(view) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, e2.U(view) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, e2.o0(), e2.c0() - e2.j0(), i2);
    }

    protected float v(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    protected int w(int i2) {
        return (int) Math.ceil(x(i2) / 0.3356d);
    }

    protected int x(int i2) {
        return (int) Math.ceil(Math.abs(i2) * A());
    }

    protected int z() {
        PointF pointF = this.f5110k;
        if (pointF != null) {
            float f2 = pointF.x;
            if (f2 != 0.0f) {
                return f2 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }
}
