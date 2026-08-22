package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class OrientationHelper {

    /* renamed from: a, reason: collision with root package name */
    protected final RecyclerView.LayoutManager f5143a;

    /* renamed from: b, reason: collision with root package name */
    private int f5144b;

    /* renamed from: c, reason: collision with root package name */
    final Rect f5145c;

    public static OrientationHelper a(RecyclerView.LayoutManager layoutManager) {
        return new OrientationHelper(layoutManager) { // from class: androidx.recyclerview.widget.OrientationHelper.1
            @Override // androidx.recyclerview.widget.OrientationHelper
            public int d(View view) {
                return this.f5143a.Z(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int e(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.f5143a.Y(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int f(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.f5143a.X(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int g(View view) {
                return this.f5143a.W(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int h() {
                return this.f5143a.w0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int i() {
                return this.f5143a.w0() - this.f5143a.m0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int j() {
                return this.f5143a.m0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int k() {
                return this.f5143a.x0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int l() {
                return this.f5143a.d0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int m() {
                return this.f5143a.l0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int n() {
                return (this.f5143a.w0() - this.f5143a.l0()) - this.f5143a.m0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int p(View view) {
                this.f5143a.v0(view, true, this.f5145c);
                return this.f5145c.right;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int q(View view) {
                this.f5143a.v0(view, true, this.f5145c);
                return this.f5145c.left;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public void r(int i2) {
                this.f5143a.L0(i2);
            }
        };
    }

    public static OrientationHelper b(RecyclerView.LayoutManager layoutManager, int i2) {
        if (i2 == 0) {
            return a(layoutManager);
        }
        if (i2 == 1) {
            return c(layoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static OrientationHelper c(RecyclerView.LayoutManager layoutManager) {
        return new OrientationHelper(layoutManager) { // from class: androidx.recyclerview.widget.OrientationHelper.2
            @Override // androidx.recyclerview.widget.OrientationHelper
            public int d(View view) {
                return this.f5143a.U(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int e(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.f5143a.X(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int f(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.f5143a.Y(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int g(View view) {
                return this.f5143a.a0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int h() {
                return this.f5143a.c0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int i() {
                return this.f5143a.c0() - this.f5143a.j0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int j() {
                return this.f5143a.j0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int k() {
                return this.f5143a.d0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int l() {
                return this.f5143a.x0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int m() {
                return this.f5143a.o0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int n() {
                return (this.f5143a.c0() - this.f5143a.o0()) - this.f5143a.j0();
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int p(View view) {
                this.f5143a.v0(view, true, this.f5145c);
                return this.f5145c.bottom;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public int q(View view) {
                this.f5143a.v0(view, true, this.f5145c);
                return this.f5145c.top;
            }

            @Override // androidx.recyclerview.widget.OrientationHelper
            public void r(int i2) {
                this.f5143a.M0(i2);
            }
        };
    }

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f(View view);

    public abstract int g(View view);

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m();

    public abstract int n();

    public int o() {
        if (Integer.MIN_VALUE == this.f5144b) {
            return 0;
        }
        return n() - this.f5144b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i2);

    public void s() {
        this.f5144b = n();
    }

    private OrientationHelper(RecyclerView.LayoutManager layoutManager) {
        this.f5144b = Integer.MIN_VALUE;
        this.f5145c = new Rect();
        this.f5143a = layoutManager;
    }
}
