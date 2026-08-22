package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* loaded from: classes.dex */
final class ScrollEventAdapter extends RecyclerView.OnScrollListener {

    /* renamed from: a, reason: collision with root package name */
    private ViewPager2.OnPageChangeCallback f5816a;

    /* renamed from: b, reason: collision with root package name */
    private final ViewPager2 f5817b;

    /* renamed from: c, reason: collision with root package name */
    private final RecyclerView f5818c;

    /* renamed from: d, reason: collision with root package name */
    private final LinearLayoutManager f5819d;

    /* renamed from: e, reason: collision with root package name */
    private int f5820e;

    /* renamed from: f, reason: collision with root package name */
    private int f5821f;

    /* renamed from: g, reason: collision with root package name */
    private ScrollEventValues f5822g;

    /* renamed from: h, reason: collision with root package name */
    private int f5823h;

    /* renamed from: i, reason: collision with root package name */
    private int f5824i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f5825j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f5826k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f5827l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f5828m;

    private static final class ScrollEventValues {

        /* renamed from: a, reason: collision with root package name */
        int f5829a;

        /* renamed from: b, reason: collision with root package name */
        float f5830b;

        /* renamed from: c, reason: collision with root package name */
        int f5831c;

        ScrollEventValues() {
        }

        void a() {
            this.f5829a = -1;
            this.f5830b = 0.0f;
            this.f5831c = 0;
        }
    }

    ScrollEventAdapter(ViewPager2 viewPager2) {
        this.f5817b = viewPager2;
        RecyclerView recyclerView = viewPager2.mRecyclerView;
        this.f5818c = recyclerView;
        this.f5819d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.f5822g = new ScrollEventValues();
        n();
    }

    private void c(int i2, float f2, int i3) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.f5816a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.b(i2, f2, i3);
        }
    }

    private void d(int i2) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.f5816a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.c(i2);
        }
    }

    private void e(int i2) {
        if ((this.f5820e == 3 && this.f5821f == 0) || this.f5821f == i2) {
            return;
        }
        this.f5821f = i2;
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.f5816a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.a(i2);
        }
    }

    private int f() {
        return this.f5819d.i2();
    }

    private boolean k() {
        int i2 = this.f5820e;
        return i2 == 1 || i2 == 4;
    }

    private void n() {
        this.f5820e = 0;
        this.f5821f = 0;
        this.f5822g.a();
        this.f5823h = -1;
        this.f5824i = -1;
        this.f5825j = false;
        this.f5826k = false;
        this.f5828m = false;
        this.f5827l = false;
    }

    private void p(boolean z) {
        this.f5828m = z;
        this.f5820e = z ? 4 : 1;
        int i2 = this.f5824i;
        if (i2 != -1) {
            this.f5823h = i2;
            this.f5824i = -1;
        } else if (this.f5823h == -1) {
            this.f5823h = f();
        }
        e(1);
    }

    private void q() {
        int top;
        ScrollEventValues scrollEventValues = this.f5822g;
        int i2 = this.f5819d.i2();
        scrollEventValues.f5829a = i2;
        if (i2 == -1) {
            scrollEventValues.a();
            return;
        }
        View I = this.f5819d.I(i2);
        if (I == null) {
            scrollEventValues.a();
            return;
        }
        int g0 = this.f5819d.g0(I);
        int r0 = this.f5819d.r0(I);
        int u0 = this.f5819d.u0(I);
        int N = this.f5819d.N(I);
        ViewGroup.LayoutParams layoutParams = I.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            g0 += marginLayoutParams.leftMargin;
            r0 += marginLayoutParams.rightMargin;
            u0 += marginLayoutParams.topMargin;
            N += marginLayoutParams.bottomMargin;
        }
        int height = I.getHeight() + u0 + N;
        int width = I.getWidth() + g0 + r0;
        if (this.f5819d.y2() == 0) {
            top = (I.getLeft() - g0) - this.f5818c.getPaddingLeft();
            if (this.f5817b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (I.getTop() - u0) - this.f5818c.getPaddingTop();
        }
        int i3 = -top;
        scrollEventValues.f5831c = i3;
        if (i3 >= 0) {
            scrollEventValues.f5830b = height == 0 ? 0.0f : i3 / height;
        } else {
            if (!new AnimateLayoutChangeDetector(this.f5819d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(scrollEventValues.f5831c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void a(RecyclerView recyclerView, int i2) {
        if (!(this.f5820e == 1 && this.f5821f == 1) && i2 == 1) {
            p(false);
            return;
        }
        if (k() && i2 == 2) {
            if (this.f5826k) {
                e(2);
                this.f5825j = true;
                return;
            }
            return;
        }
        if (k() && i2 == 0) {
            q();
            if (this.f5826k) {
                ScrollEventValues scrollEventValues = this.f5822g;
                if (scrollEventValues.f5831c == 0) {
                    int i3 = this.f5823h;
                    int i4 = scrollEventValues.f5829a;
                    if (i3 != i4) {
                        d(i4);
                    }
                }
            } else {
                int i5 = this.f5822g.f5829a;
                if (i5 != -1) {
                    c(i5, 0.0f, 0);
                }
            }
            e(0);
            n();
        }
        if (this.f5820e == 2 && i2 == 0 && this.f5827l) {
            q();
            ScrollEventValues scrollEventValues2 = this.f5822g;
            if (scrollEventValues2.f5831c == 0) {
                int i6 = this.f5824i;
                int i7 = scrollEventValues2.f5829a;
                if (i6 != i7) {
                    if (i7 == -1) {
                        i7 = 0;
                    }
                    d(i7);
                }
                e(0);
                n();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
    
        if ((r5 < 0) == r3.f5817b.d()) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.f5826k = r4
            r3.q()
            boolean r0 = r3.f5825j
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L37
            r3.f5825j = r2
            if (r6 > 0) goto L1f
            if (r6 != 0) goto L29
            if (r5 >= 0) goto L16
            r5 = r4
            goto L17
        L16:
            r5 = r2
        L17:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f5817b
            boolean r6 = r6.d()
            if (r5 != r6) goto L29
        L1f:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.f5822g
            int r6 = r5.f5831c
            if (r6 == 0) goto L29
            int r5 = r5.f5829a
            int r5 = r5 + r4
            goto L2d
        L29:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.f5822g
            int r5 = r5.f5829a
        L2d:
            r3.f5824i = r5
            int r6 = r3.f5823h
            if (r6 == r5) goto L45
            r3.d(r5)
            goto L45
        L37:
            int r5 = r3.f5820e
            if (r5 != 0) goto L45
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.f5822g
            int r5 = r5.f5829a
            if (r5 != r1) goto L42
            r5 = r2
        L42:
            r3.d(r5)
        L45:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.f5822g
            int r6 = r5.f5829a
            if (r6 != r1) goto L4c
            r6 = r2
        L4c:
            float r0 = r5.f5830b
            int r5 = r5.f5831c
            r3.c(r6, r0, r5)
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.f5822g
            int r6 = r5.f5829a
            int r0 = r3.f5824i
            if (r6 == r0) goto L5d
            if (r0 != r1) goto L6b
        L5d:
            int r5 = r5.f5831c
            if (r5 != 0) goto L6b
            int r5 = r3.f5821f
            if (r5 == r4) goto L6b
            r3.e(r2)
            r3.n()
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    double g() {
        q();
        ScrollEventValues scrollEventValues = this.f5822g;
        return scrollEventValues.f5829a + scrollEventValues.f5830b;
    }

    int h() {
        return this.f5821f;
    }

    boolean i() {
        return this.f5828m;
    }

    boolean j() {
        return this.f5821f == 0;
    }

    void l() {
        this.f5827l = true;
    }

    void m(int i2, boolean z) {
        this.f5820e = z ? 2 : 3;
        this.f5828m = false;
        boolean z2 = this.f5824i != i2;
        this.f5824i = i2;
        e(2);
        if (z2) {
            d(i2);
        }
    }

    void o(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.f5816a = onPageChangeCallback;
    }
}
