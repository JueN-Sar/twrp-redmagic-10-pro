package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.RestrictTo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private BitSet B;
    private boolean G;
    private boolean H;
    private SavedState I;
    private int J;
    private int[] O;
    Span[] t;
    OrientationHelper u;
    OrientationHelper v;
    private int w;
    private int x;
    private final LayoutState y;

    /* renamed from: s, reason: collision with root package name */
    private int f5283s = -1;
    boolean z = false;
    boolean A = false;
    int C = -1;
    int D = Integer.MIN_VALUE;
    LazySpanLookup E = new LazySpanLookup();
    private int F = 2;
    private final Rect K = new Rect();
    private final AnchorInfo L = new AnchorInfo();
    private boolean M = false;
    private boolean N = true;
    private final Runnable P = new Runnable() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.b2();
        }
    };

    class AnchorInfo {

        /* renamed from: a, reason: collision with root package name */
        int f5285a;

        /* renamed from: b, reason: collision with root package name */
        int f5286b;

        /* renamed from: c, reason: collision with root package name */
        boolean f5287c;

        /* renamed from: d, reason: collision with root package name */
        boolean f5288d;

        /* renamed from: e, reason: collision with root package name */
        boolean f5289e;

        /* renamed from: f, reason: collision with root package name */
        int[] f5290f;

        AnchorInfo() {
            c();
        }

        void a() {
            this.f5286b = this.f5287c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        void b(int i2) {
            if (this.f5287c) {
                this.f5286b = StaggeredGridLayoutManager.this.u.i() - i2;
            } else {
                this.f5286b = StaggeredGridLayoutManager.this.u.m() + i2;
            }
        }

        void c() {
            this.f5285a = -1;
            this.f5286b = Integer.MIN_VALUE;
            this.f5287c = false;
            this.f5288d = false;
            this.f5289e = false;
            int[] iArr = this.f5290f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(Span[] spanArr) {
            int length = spanArr.length;
            int[] iArr = this.f5290f;
            if (iArr == null || iArr.length < length) {
                this.f5290f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.f5290f[i2] = spanArr[i2].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {

        /* renamed from: e, reason: collision with root package name */
        Span f5292e;

        /* renamed from: f, reason: collision with root package name */
        boolean f5293f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            Span span = this.f5292e;
            if (span == null) {
                return -1;
            }
            return span.f5314e;
        }

        public boolean f() {
            return this.f5293f;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        int f5300c;

        /* renamed from: h, reason: collision with root package name */
        int f5301h;

        /* renamed from: i, reason: collision with root package name */
        int f5302i;

        /* renamed from: j, reason: collision with root package name */
        int[] f5303j;

        /* renamed from: k, reason: collision with root package name */
        int f5304k;

        /* renamed from: l, reason: collision with root package name */
        int[] f5305l;

        /* renamed from: m, reason: collision with root package name */
        List f5306m;

        /* renamed from: n, reason: collision with root package name */
        boolean f5307n;

        /* renamed from: o, reason: collision with root package name */
        boolean f5308o;

        /* renamed from: p, reason: collision with root package name */
        boolean f5309p;

        public SavedState() {
        }

        void a() {
            this.f5303j = null;
            this.f5302i = 0;
            this.f5300c = -1;
            this.f5301h = -1;
        }

        void b() {
            this.f5303j = null;
            this.f5302i = 0;
            this.f5304k = 0;
            this.f5305l = null;
            this.f5306m = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f5300c);
            parcel.writeInt(this.f5301h);
            parcel.writeInt(this.f5302i);
            if (this.f5302i > 0) {
                parcel.writeIntArray(this.f5303j);
            }
            parcel.writeInt(this.f5304k);
            if (this.f5304k > 0) {
                parcel.writeIntArray(this.f5305l);
            }
            parcel.writeInt(this.f5307n ? 1 : 0);
            parcel.writeInt(this.f5308o ? 1 : 0);
            parcel.writeInt(this.f5309p ? 1 : 0);
            parcel.writeList(this.f5306m);
        }

        SavedState(Parcel parcel) {
            this.f5300c = parcel.readInt();
            this.f5301h = parcel.readInt();
            int readInt = parcel.readInt();
            this.f5302i = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.f5303j = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.f5304k = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.f5305l = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f5307n = parcel.readInt() == 1;
            this.f5308o = parcel.readInt() == 1;
            this.f5309p = parcel.readInt() == 1;
            this.f5306m = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f5302i = savedState.f5302i;
            this.f5300c = savedState.f5300c;
            this.f5301h = savedState.f5301h;
            this.f5303j = savedState.f5303j;
            this.f5304k = savedState.f5304k;
            this.f5305l = savedState.f5305l;
            this.f5307n = savedState.f5307n;
            this.f5308o = savedState.f5308o;
            this.f5309p = savedState.f5309p;
            this.f5306m = savedState.f5306m;
        }
    }

    class Span {

        /* renamed from: a, reason: collision with root package name */
        ArrayList f5310a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        int f5311b = Integer.MIN_VALUE;

        /* renamed from: c, reason: collision with root package name */
        int f5312c = Integer.MIN_VALUE;

        /* renamed from: d, reason: collision with root package name */
        int f5313d = 0;

        /* renamed from: e, reason: collision with root package name */
        final int f5314e;

        Span(int i2) {
            this.f5314e = i2;
        }

        void a(View view) {
            LayoutParams n2 = n(view);
            n2.f5292e = this;
            this.f5310a.add(view);
            this.f5312c = Integer.MIN_VALUE;
            if (this.f5310a.size() == 1) {
                this.f5311b = Integer.MIN_VALUE;
            }
            if (n2.c() || n2.b()) {
                this.f5313d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void b(boolean z, int i2) {
            int l2 = z ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (l2 == Integer.MIN_VALUE) {
                return;
            }
            if (!z || l2 >= StaggeredGridLayoutManager.this.u.i()) {
                if (z || l2 <= StaggeredGridLayoutManager.this.u.m()) {
                    if (i2 != Integer.MIN_VALUE) {
                        l2 += i2;
                    }
                    this.f5312c = l2;
                    this.f5311b = l2;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem f2;
            ArrayList arrayList = this.f5310a;
            View view = (View) arrayList.get(arrayList.size() - 1);
            LayoutParams n2 = n(view);
            this.f5312c = StaggeredGridLayoutManager.this.u.d(view);
            if (n2.f5293f && (f2 = StaggeredGridLayoutManager.this.E.f(n2.a())) != null && f2.f5297h == 1) {
                this.f5312c += f2.a(this.f5314e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem f2;
            View view = (View) this.f5310a.get(0);
            LayoutParams n2 = n(view);
            this.f5311b = StaggeredGridLayoutManager.this.u.g(view);
            if (n2.f5293f && (f2 = StaggeredGridLayoutManager.this.E.f(n2.a())) != null && f2.f5297h == -1) {
                this.f5311b -= f2.a(this.f5314e);
            }
        }

        void e() {
            this.f5310a.clear();
            q();
            this.f5313d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.z ? i(this.f5310a.size() - 1, -1, true) : i(0, this.f5310a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.z ? i(0, this.f5310a.size(), true) : i(this.f5310a.size() - 1, -1, true);
        }

        int h(int i2, int i3, boolean z, boolean z2, boolean z3) {
            int m2 = StaggeredGridLayoutManager.this.u.m();
            int i4 = StaggeredGridLayoutManager.this.u.i();
            int i5 = i3 > i2 ? 1 : -1;
            while (i2 != i3) {
                View view = (View) this.f5310a.get(i2);
                int g2 = StaggeredGridLayoutManager.this.u.g(view);
                int d2 = StaggeredGridLayoutManager.this.u.d(view);
                boolean z4 = false;
                boolean z5 = !z3 ? g2 >= i4 : g2 > i4;
                if (!z3 ? d2 > m2 : d2 >= m2) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (g2 >= m2 && d2 <= i4) {
                            return StaggeredGridLayoutManager.this.p0(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.p0(view);
                        }
                        if (g2 < m2 || d2 > i4) {
                            return StaggeredGridLayoutManager.this.p0(view);
                        }
                    }
                }
                i2 += i5;
            }
            return -1;
        }

        int i(int i2, int i3, boolean z) {
            return h(i2, i3, false, false, z);
        }

        public int j() {
            return this.f5313d;
        }

        int k() {
            int i2 = this.f5312c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f5312c;
        }

        int l(int i2) {
            int i3 = this.f5312c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f5310a.size() == 0) {
                return i2;
            }
            c();
            return this.f5312c;
        }

        public View m(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.f5310a.size() - 1;
                while (size >= 0) {
                    View view2 = (View) this.f5310a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.p0(view2) >= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.p0(view2) <= i2) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f5310a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = (View) this.f5310a.get(i4);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.p0(view3) <= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.p0(view3) >= i2) || !view3.hasFocusable()) {
                        break;
                    }
                    i4++;
                    view = view3;
                }
            }
            return view;
        }

        LayoutParams n(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        int o() {
            int i2 = this.f5311b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.f5311b;
        }

        int p(int i2) {
            int i3 = this.f5311b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f5310a.size() == 0) {
                return i2;
            }
            d();
            return this.f5311b;
        }

        void q() {
            this.f5311b = Integer.MIN_VALUE;
            this.f5312c = Integer.MIN_VALUE;
        }

        void r(int i2) {
            int i3 = this.f5311b;
            if (i3 != Integer.MIN_VALUE) {
                this.f5311b = i3 + i2;
            }
            int i4 = this.f5312c;
            if (i4 != Integer.MIN_VALUE) {
                this.f5312c = i4 + i2;
            }
        }

        void s() {
            int size = this.f5310a.size();
            View view = (View) this.f5310a.remove(size - 1);
            LayoutParams n2 = n(view);
            n2.f5292e = null;
            if (n2.c() || n2.b()) {
                this.f5313d -= StaggeredGridLayoutManager.this.u.e(view);
            }
            if (size == 1) {
                this.f5311b = Integer.MIN_VALUE;
            }
            this.f5312c = Integer.MIN_VALUE;
        }

        void t() {
            View view = (View) this.f5310a.remove(0);
            LayoutParams n2 = n(view);
            n2.f5292e = null;
            if (this.f5310a.size() == 0) {
                this.f5312c = Integer.MIN_VALUE;
            }
            if (n2.c() || n2.b()) {
                this.f5313d -= StaggeredGridLayoutManager.this.u.e(view);
            }
            this.f5311b = Integer.MIN_VALUE;
        }

        void u(View view) {
            LayoutParams n2 = n(view);
            n2.f5292e = this;
            this.f5310a.add(0, view);
            this.f5311b = Integer.MIN_VALUE;
            if (this.f5310a.size() == 1) {
                this.f5312c = Integer.MIN_VALUE;
            }
            if (n2.c() || n2.b()) {
                this.f5313d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void v(int i2) {
            this.f5311b = i2;
            this.f5312c = i2;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.LayoutManager.Properties q0 = RecyclerView.LayoutManager.q0(context, attributeSet, i2, i3);
        T2(q0.f5187a);
        V2(q0.f5188b);
        U2(q0.f5189c);
        this.y = new LayoutState();
        j2();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void C2(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.A
            if (r0 == 0) goto L9
            int r0 = r6.t2()
            goto Ld
        L9:
            int r0 = r6.s2()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1b
            if (r7 >= r8) goto L17
            int r2 = r8 + 1
        L15:
            r3 = r7
            goto L1e
        L17:
            int r2 = r7 + 1
            r3 = r8
            goto L1e
        L1b:
            int r2 = r7 + r8
            goto L15
        L1e:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.E
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3d
            r5 = 2
            if (r9 == r5) goto L37
            if (r9 == r1) goto L2c
            goto L42
        L2c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.E
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.E
            r7.j(r8, r4)
            goto L42
        L37:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.E
            r9.k(r7, r8)
            goto L42
        L3d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.E
            r9.j(r7, r8)
        L42:
            if (r2 > r0) goto L45
            return
        L45:
            boolean r7 = r6.A
            if (r7 == 0) goto L4e
            int r7 = r6.s2()
            goto L52
        L4e:
            int r7 = r6.t2()
        L52:
            if (r3 > r7) goto L57
            r6.C1()
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.C2(int, int, int):void");
    }

    private void G2(View view, int i2, int i3, boolean z) {
        p(view, this.K);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.K;
        int d3 = d3(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.K;
        int d32 = d3(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (z ? Q1(view, d3, d32, layoutParams) : O1(view, d3, d32, layoutParams)) {
            view.measure(d3, d32);
        }
    }

    private void H2(View view, LayoutParams layoutParams, boolean z) {
        if (layoutParams.f5293f) {
            if (this.w == 1) {
                G2(view, this.J, RecyclerView.LayoutManager.Q(c0(), d0(), o0() + j0(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z);
                return;
            } else {
                G2(view, RecyclerView.LayoutManager.Q(w0(), x0(), l0() + m0(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), this.J, z);
                return;
            }
        }
        if (this.w == 1) {
            G2(view, RecyclerView.LayoutManager.Q(this.x, x0(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).width, false), RecyclerView.LayoutManager.Q(c0(), d0(), o0() + j0(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z);
        } else {
            G2(view, RecyclerView.LayoutManager.Q(w0(), x0(), l0() + m0(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), RecyclerView.LayoutManager.Q(this.x, d0(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).height, false), z);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x0152, code lost:
    
        if (b2() != false) goto L87;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void I2(androidx.recyclerview.widget.RecyclerView.Recycler r9, androidx.recyclerview.widget.RecyclerView.State r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.I2(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, boolean):void");
    }

    private boolean J2(int i2) {
        if (this.w == 0) {
            return (i2 == -1) != this.A;
        }
        return ((i2 == -1) == this.A) == F2();
    }

    private void L2(View view) {
        for (int i2 = this.f5283s - 1; i2 >= 0; i2--) {
            this.t[i2].u(view);
        }
    }

    private void M2(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.f5073a || layoutState.f5081i) {
            return;
        }
        if (layoutState.f5074b == 0) {
            if (layoutState.f5077e == -1) {
                N2(recycler, layoutState.f5079g);
                return;
            } else {
                O2(recycler, layoutState.f5078f);
                return;
            }
        }
        if (layoutState.f5077e != -1) {
            int w2 = w2(layoutState.f5079g) - layoutState.f5079g;
            O2(recycler, w2 < 0 ? layoutState.f5078f : Math.min(w2, layoutState.f5074b) + layoutState.f5078f);
        } else {
            int i2 = layoutState.f5078f;
            int v2 = i2 - v2(i2);
            N2(recycler, v2 < 0 ? layoutState.f5079g : layoutState.f5079g - Math.min(v2, layoutState.f5074b));
        }
    }

    private void N2(RecyclerView.Recycler recycler, int i2) {
        for (int P = P() - 1; P >= 0; P--) {
            View O = O(P);
            if (this.u.g(O) < i2 || this.u.q(O) < i2) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) O.getLayoutParams();
            if (layoutParams.f5293f) {
                for (int i3 = 0; i3 < this.f5283s; i3++) {
                    if (this.t[i3].f5310a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.f5283s; i4++) {
                    this.t[i4].s();
                }
            } else if (layoutParams.f5292e.f5310a.size() == 1) {
                return;
            } else {
                layoutParams.f5292e.s();
            }
            v1(O, recycler);
        }
    }

    private void O2(RecyclerView.Recycler recycler, int i2) {
        while (P() > 0) {
            View O = O(0);
            if (this.u.d(O) > i2 || this.u.p(O) > i2) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) O.getLayoutParams();
            if (layoutParams.f5293f) {
                for (int i3 = 0; i3 < this.f5283s; i3++) {
                    if (this.t[i3].f5310a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.f5283s; i4++) {
                    this.t[i4].t();
                }
            } else if (layoutParams.f5292e.f5310a.size() == 1) {
                return;
            } else {
                layoutParams.f5292e.t();
            }
            v1(O, recycler);
        }
    }

    private void P2() {
        if (this.v.k() == 1073741824) {
            return;
        }
        int P = P();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < P; i2++) {
            View O = O(i2);
            float e2 = this.v.e(O);
            if (e2 >= f2) {
                if (((LayoutParams) O.getLayoutParams()).f()) {
                    e2 = (e2 * 1.0f) / this.f5283s;
                }
                f2 = Math.max(f2, e2);
            }
        }
        int i3 = this.x;
        int round = Math.round(f2 * this.f5283s);
        if (this.v.k() == Integer.MIN_VALUE) {
            round = Math.min(round, this.v.n());
        }
        b3(round);
        if (this.x == i3) {
            return;
        }
        for (int i4 = 0; i4 < P; i4++) {
            View O2 = O(i4);
            LayoutParams layoutParams = (LayoutParams) O2.getLayoutParams();
            if (!layoutParams.f5293f) {
                if (F2() && this.w == 1) {
                    int i5 = this.f5283s;
                    int i6 = layoutParams.f5292e.f5314e;
                    O2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.x) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = layoutParams.f5292e.f5314e;
                    int i8 = this.x * i7;
                    int i9 = i7 * i3;
                    if (this.w == 1) {
                        O2.offsetLeftAndRight(i8 - i9);
                    } else {
                        O2.offsetTopAndBottom(i8 - i9);
                    }
                }
            }
        }
    }

    private void Q2() {
        if (this.w == 1 || !F2()) {
            this.A = this.z;
        } else {
            this.A = !this.z;
        }
    }

    private void S2(int i2) {
        LayoutState layoutState = this.y;
        layoutState.f5077e = i2;
        layoutState.f5076d = this.A != (i2 == -1) ? -1 : 1;
    }

    private void V1(View view) {
        for (int i2 = this.f5283s - 1; i2 >= 0; i2--) {
            this.t[i2].a(view);
        }
    }

    private void W1(AnchorInfo anchorInfo) {
        SavedState savedState = this.I;
        int i2 = savedState.f5302i;
        if (i2 > 0) {
            if (i2 == this.f5283s) {
                for (int i3 = 0; i3 < this.f5283s; i3++) {
                    this.t[i3].e();
                    SavedState savedState2 = this.I;
                    int i4 = savedState2.f5303j[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += savedState2.f5308o ? this.u.i() : this.u.m();
                    }
                    this.t[i3].v(i4);
                }
            } else {
                savedState.b();
                SavedState savedState3 = this.I;
                savedState3.f5300c = savedState3.f5301h;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.f5309p;
        U2(savedState4.f5307n);
        Q2();
        SavedState savedState5 = this.I;
        int i5 = savedState5.f5300c;
        if (i5 != -1) {
            this.C = i5;
            anchorInfo.f5287c = savedState5.f5308o;
        } else {
            anchorInfo.f5287c = this.A;
        }
        if (savedState5.f5304k > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f5294a = savedState5.f5305l;
            lazySpanLookup.f5295b = savedState5.f5306m;
        }
    }

    private void W2(int i2, int i3) {
        for (int i4 = 0; i4 < this.f5283s; i4++) {
            if (!this.t[i4].f5310a.isEmpty()) {
                c3(this.t[i4], i2, i3);
            }
        }
    }

    private boolean X2(RecyclerView.State state, AnchorInfo anchorInfo) {
        anchorInfo.f5285a = this.G ? p2(state.b()) : l2(state.b());
        anchorInfo.f5286b = Integer.MIN_VALUE;
        return true;
    }

    private void Z1(View view, LayoutParams layoutParams, LayoutState layoutState) {
        if (layoutState.f5077e == 1) {
            if (layoutParams.f5293f) {
                V1(view);
                return;
            } else {
                layoutParams.f5292e.a(view);
                return;
            }
        }
        if (layoutParams.f5293f) {
            L2(view);
        } else {
            layoutParams.f5292e.u(view);
        }
    }

    private int a2(int i2) {
        if (P() == 0) {
            return this.A ? 1 : -1;
        }
        return (i2 < s2()) != this.A ? -1 : 1;
    }

    private void a3(int i2, RecyclerView.State state) {
        int i3;
        int i4;
        int c2;
        LayoutState layoutState = this.y;
        boolean z = false;
        layoutState.f5074b = 0;
        layoutState.f5075c = i2;
        if (!F0() || (c2 = state.c()) == -1) {
            i3 = 0;
            i4 = 0;
        } else {
            if (this.A == (c2 < i2)) {
                i3 = this.u.n();
                i4 = 0;
            } else {
                i4 = this.u.n();
                i3 = 0;
            }
        }
        if (S()) {
            this.y.f5078f = this.u.m() - i4;
            this.y.f5079g = this.u.i() + i3;
        } else {
            this.y.f5079g = this.u.h() + i3;
            this.y.f5078f = -i4;
        }
        LayoutState layoutState2 = this.y;
        layoutState2.f5080h = false;
        layoutState2.f5073a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        layoutState2.f5081i = z;
    }

    private boolean c2(Span span) {
        if (this.A) {
            if (span.k() < this.u.i()) {
                ArrayList arrayList = span.f5310a;
                return !span.n((View) arrayList.get(arrayList.size() - 1)).f5293f;
            }
        } else if (span.o() > this.u.m()) {
            return !span.n((View) span.f5310a.get(0)).f5293f;
        }
        return false;
    }

    private void c3(Span span, int i2, int i3) {
        int j2 = span.j();
        if (i2 == -1) {
            if (span.o() + j2 <= i3) {
                this.B.set(span.f5314e, false);
            }
        } else if (span.k() - j2 >= i3) {
            this.B.set(span.f5314e, false);
        }
    }

    private int d2(RecyclerView.State state) {
        if (P() == 0) {
            return 0;
        }
        return ScrollbarHelper.a(state, this.u, n2(!this.N), m2(!this.N), this, this.N);
    }

    private int d3(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    private int e2(RecyclerView.State state) {
        if (P() == 0) {
            return 0;
        }
        return ScrollbarHelper.b(state, this.u, n2(!this.N), m2(!this.N), this, this.N, this.A);
    }

    private int f2(RecyclerView.State state) {
        if (P() == 0) {
            return 0;
        }
        return ScrollbarHelper.c(state, this.u, n2(!this.N), m2(!this.N), this, this.N);
    }

    private int g2(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && F2()) ? -1 : 1 : (this.w != 1 && F2()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem h2(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f5298i = new int[this.f5283s];
        for (int i3 = 0; i3 < this.f5283s; i3++) {
            fullSpanItem.f5298i[i3] = i2 - this.t[i3].l(i2);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem i2(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f5298i = new int[this.f5283s];
        for (int i3 = 0; i3 < this.f5283s; i3++) {
            fullSpanItem.f5298i[i3] = this.t[i3].p(i2) - i2;
        }
        return fullSpanItem;
    }

    private void j2() {
        this.u = OrientationHelper.b(this, this.w);
        this.v = OrientationHelper.b(this, 1 - this.w);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int k2(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        Span span;
        int e2;
        int i2;
        int i3;
        int e3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.f5283s, true);
        int i4 = this.y.f5081i ? layoutState.f5077e == 1 ? Api.BaseClientBuilder.API_PRIORITY_OTHER : Integer.MIN_VALUE : layoutState.f5077e == 1 ? layoutState.f5079g + layoutState.f5074b : layoutState.f5078f - layoutState.f5074b;
        W2(layoutState.f5077e, i4);
        int i5 = this.A ? this.u.i() : this.u.m();
        boolean z2 = false;
        while (layoutState.a(state) && (this.y.f5081i || !this.B.isEmpty())) {
            View b2 = layoutState.b(recycler);
            LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
            int a2 = layoutParams.a();
            int g2 = this.E.g(a2);
            boolean z3 = g2 == -1 ? true : r9;
            if (z3) {
                span = layoutParams.f5293f ? this.t[r9] : y2(layoutState);
                this.E.n(a2, span);
            } else {
                span = this.t[g2];
            }
            Span span2 = span;
            layoutParams.f5292e = span2;
            if (layoutState.f5077e == 1) {
                j(b2);
            } else {
                k(b2, r9);
            }
            H2(b2, layoutParams, r9);
            if (layoutState.f5077e == 1) {
                int u2 = layoutParams.f5293f ? u2(i5) : span2.l(i5);
                int e4 = this.u.e(b2) + u2;
                if (z3 && layoutParams.f5293f) {
                    LazySpanLookup.FullSpanItem h2 = h2(u2);
                    h2.f5297h = -1;
                    h2.f5296c = a2;
                    this.E.a(h2);
                }
                i2 = e4;
                e2 = u2;
            } else {
                int x2 = layoutParams.f5293f ? x2(i5) : span2.p(i5);
                e2 = x2 - this.u.e(b2);
                if (z3 && layoutParams.f5293f) {
                    LazySpanLookup.FullSpanItem i22 = i2(x2);
                    i22.f5297h = 1;
                    i22.f5296c = a2;
                    this.E.a(i22);
                }
                i2 = x2;
            }
            if (layoutParams.f5293f && layoutState.f5076d == -1) {
                if (z3) {
                    this.M = true;
                } else {
                    if (!(layoutState.f5077e == 1 ? X1() : Y1())) {
                        LazySpanLookup.FullSpanItem f2 = this.E.f(a2);
                        if (f2 != null) {
                            f2.f5299j = true;
                        }
                        this.M = true;
                    }
                }
            }
            Z1(b2, layoutParams, layoutState);
            if (F2() && this.w == 1) {
                int i6 = layoutParams.f5293f ? this.v.i() : this.v.i() - (((this.f5283s - 1) - span2.f5314e) * this.x);
                e3 = i6;
                i3 = i6 - this.v.e(b2);
            } else {
                int m2 = layoutParams.f5293f ? this.v.m() : (span2.f5314e * this.x) + this.v.m();
                i3 = m2;
                e3 = this.v.e(b2) + m2;
            }
            if (this.w == 1) {
                I0(b2, i3, e2, e3, i2);
            } else {
                I0(b2, e2, i3, i2, e3);
            }
            if (layoutParams.f5293f) {
                W2(this.y.f5077e, i4);
            } else {
                c3(span2, this.y.f5077e, i4);
            }
            M2(recycler, this.y);
            if (this.y.f5080h && b2.hasFocusable()) {
                if (layoutParams.f5293f) {
                    this.B.clear();
                } else {
                    z = false;
                    this.B.set(span2.f5314e, false);
                    r9 = z;
                    z2 = true;
                }
            }
            z = false;
            r9 = z;
            z2 = true;
        }
        int i7 = r9;
        if (!z2) {
            M2(recycler, this.y);
        }
        int m3 = this.y.f5077e == -1 ? this.u.m() - x2(this.u.m()) : u2(this.u.i()) - this.u.i();
        return m3 > 0 ? Math.min(layoutState.f5074b, m3) : i7;
    }

    private int l2(int i2) {
        int P = P();
        for (int i3 = 0; i3 < P; i3++) {
            int p0 = p0(O(i3));
            if (p0 >= 0 && p0 < i2) {
                return p0;
            }
        }
        return 0;
    }

    private int p2(int i2) {
        for (int P = P() - 1; P >= 0; P--) {
            int p0 = p0(O(P));
            if (p0 >= 0 && p0 < i2) {
                return p0;
            }
        }
        return 0;
    }

    private void q2(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int u2 = u2(Integer.MIN_VALUE);
        if (u2 != Integer.MIN_VALUE && (i2 = this.u.i() - u2) > 0) {
            int i3 = i2 - (-R2(-i2, recycler, state));
            if (!z || i3 <= 0) {
                return;
            }
            this.u.r(i3);
        }
    }

    private void r2(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int m2;
        int x2 = x2(Api.BaseClientBuilder.API_PRIORITY_OTHER);
        if (x2 != Integer.MAX_VALUE && (m2 = x2 - this.u.m()) > 0) {
            int R2 = m2 - R2(m2, recycler, state);
            if (!z || R2 <= 0) {
                return;
            }
            this.u.r(-R2);
        }
    }

    private int u2(int i2) {
        int l2 = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.f5283s; i3++) {
            int l3 = this.t[i3].l(i2);
            if (l3 > l2) {
                l2 = l3;
            }
        }
        return l2;
    }

    private int v2(int i2) {
        int p2 = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.f5283s; i3++) {
            int p3 = this.t[i3].p(i2);
            if (p3 > p2) {
                p2 = p3;
            }
        }
        return p2;
    }

    private int w2(int i2) {
        int l2 = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.f5283s; i3++) {
            int l3 = this.t[i3].l(i2);
            if (l3 < l2) {
                l2 = l3;
            }
        }
        return l2;
    }

    private int x2(int i2) {
        int p2 = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.f5283s; i3++) {
            int p3 = this.t[i3].p(i2);
            if (p3 < p2) {
                p2 = p3;
            }
        }
        return p2;
    }

    private Span y2(LayoutState layoutState) {
        int i2;
        int i3;
        int i4;
        if (J2(layoutState.f5077e)) {
            i3 = this.f5283s - 1;
            i2 = -1;
            i4 = -1;
        } else {
            i2 = this.f5283s;
            i3 = 0;
            i4 = 1;
        }
        Span span = null;
        if (layoutState.f5077e == 1) {
            int m2 = this.u.m();
            int i5 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            while (i3 != i2) {
                Span span2 = this.t[i3];
                int l2 = span2.l(m2);
                if (l2 < i5) {
                    span = span2;
                    i5 = l2;
                }
                i3 += i4;
            }
            return span;
        }
        int i6 = this.u.i();
        int i7 = Integer.MIN_VALUE;
        while (i3 != i2) {
            Span span3 = this.t[i3];
            int p2 = span3.p(i6);
            if (p2 > i7) {
                span = span3;
                i7 = p2;
            }
            i3 += i4;
        }
        return span;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int A(RecyclerView.State state) {
        return e2(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean A0() {
        return this.F != 0;
    }

    public boolean A2() {
        return this.z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int B(RecyclerView.State state) {
        return f2(state);
    }

    public int B2() {
        return this.f5283s;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.view.View D2() {
        /*
            r12 = this;
            int r0 = r12.P()
            int r1 = r0 + (-1)
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.f5283s
            r2.<init>(r3)
            int r3 = r12.f5283s
            r4 = 0
            r5 = 1
            r2.set(r4, r3, r5)
            int r3 = r12.w
            r6 = -1
            if (r3 != r5) goto L21
            boolean r3 = r12.F2()
            if (r3 == 0) goto L21
            r3 = r5
            goto L22
        L21:
            r3 = r6
        L22:
            boolean r7 = r12.A
            if (r7 == 0) goto L28
            r0 = r6
            goto L29
        L28:
            r1 = r4
        L29:
            if (r1 >= r0) goto L2c
            r6 = r5
        L2c:
            if (r1 == r0) goto La4
            android.view.View r7 = r12.O(r1)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span r9 = r8.f5292e
            int r9 = r9.f5314e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L52
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span r9 = r8.f5292e
            boolean r9 = r12.c2(r9)
            if (r9 == 0) goto L4b
            return r7
        L4b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span r9 = r8.f5292e
            int r9 = r9.f5314e
            r2.clear(r9)
        L52:
            boolean r9 = r8.f5293f
            if (r9 == 0) goto L57
            goto La2
        L57:
            int r9 = r1 + r6
            if (r9 == r0) goto La2
            android.view.View r9 = r12.O(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L75
            androidx.recyclerview.widget.OrientationHelper r10 = r12.u
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.OrientationHelper r11 = r12.u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L72
            return r7
        L72:
            if (r10 != r11) goto La2
            goto L86
        L75:
            androidx.recyclerview.widget.OrientationHelper r10 = r12.u
            int r10 = r10.g(r7)
            androidx.recyclerview.widget.OrientationHelper r11 = r12.u
            int r11 = r11.g(r9)
            if (r10 <= r11) goto L84
            return r7
        L84:
            if (r10 != r11) goto La2
        L86:
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span r8 = r8.f5292e
            int r8 = r8.f5314e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span r9 = r9.f5292e
            int r9 = r9.f5314e
            int r8 = r8 - r9
            if (r8 >= 0) goto L99
            r8 = r5
            goto L9a
        L99:
            r8 = r4
        L9a:
            if (r3 >= 0) goto L9e
            r9 = r5
            goto L9f
        L9e:
            r9 = r4
        L9f:
            if (r8 == r9) goto La2
            return r7
        La2:
            int r1 = r1 + r6
            goto L2c
        La4:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.D2():android.view.View");
    }

    public void E2() {
        this.E.b();
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int F1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return R2(i2, recycler, state);
    }

    boolean F2() {
        return f0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void G1(int i2) {
        SavedState savedState = this.I;
        if (savedState != null && savedState.f5300c != i2) {
            savedState.a();
        }
        this.C = i2;
        this.D = Integer.MIN_VALUE;
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int H1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return R2(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams J() {
        return this.w == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams K(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    void K2(int i2, RecyclerView.State state) {
        int s2;
        int i3;
        if (i2 > 0) {
            s2 = t2();
            i3 = 1;
        } else {
            s2 = s2();
            i3 = -1;
        }
        this.y.f5073a = true;
        a3(s2, state);
        S2(i3);
        LayoutState layoutState = this.y;
        layoutState.f5075c = s2 + layoutState.f5076d;
        layoutState.f5074b = Math.abs(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams L(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void L0(int i2) {
        super.L0(i2);
        for (int i3 = 0; i3 < this.f5283s; i3++) {
            this.t[i3].r(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void L1(Rect rect, int i2, int i3) {
        int t;
        int t2;
        int l0 = l0() + m0();
        int o0 = o0() + j0();
        if (this.w == 1) {
            t2 = RecyclerView.LayoutManager.t(i3, rect.height() + o0, h0());
            t = RecyclerView.LayoutManager.t(i2, (this.x * this.f5283s) + l0, i0());
        } else {
            t = RecyclerView.LayoutManager.t(i2, rect.width() + l0, i0());
            t2 = RecyclerView.LayoutManager.t(i3, (this.x * this.f5283s) + o0, h0());
        }
        K1(t, t2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void M0(int i2) {
        super.M0(i2);
        for (int i3 = 0; i3 < this.f5283s; i3++) {
            this.t[i3].r(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void R0(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.R0(recyclerView, recycler);
        x1(this.P);
        for (int i2 = 0; i2 < this.f5283s; i2++) {
            this.t[i2].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void R1(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.p(i2);
        S1(linearSmoothScroller);
    }

    int R2(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (P() == 0 || i2 == 0) {
            return 0;
        }
        K2(i2, state);
        int k2 = k2(recycler, this.y, state);
        if (this.y.f5074b >= k2) {
            i2 = i2 < 0 ? -k2 : k2;
        }
        this.u.r(-i2);
        this.G = this.A;
        LayoutState layoutState = this.y;
        layoutState.f5074b = 0;
        M2(recycler, layoutState);
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View S0(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View H;
        View m2;
        if (P() == 0 || (H = H(view)) == null) {
            return null;
        }
        Q2();
        int g2 = g2(i2);
        if (g2 == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) H.getLayoutParams();
        boolean z = layoutParams.f5293f;
        Span span = layoutParams.f5292e;
        int t2 = g2 == 1 ? t2() : s2();
        a3(t2, state);
        S2(g2);
        LayoutState layoutState = this.y;
        layoutState.f5075c = layoutState.f5076d + t2;
        layoutState.f5074b = (int) (this.u.n() * 0.33333334f);
        LayoutState layoutState2 = this.y;
        layoutState2.f5080h = true;
        layoutState2.f5073a = false;
        k2(recycler, layoutState2, state);
        this.G = this.A;
        if (!z && (m2 = span.m(t2, g2)) != null && m2 != H) {
            return m2;
        }
        if (J2(g2)) {
            for (int i3 = this.f5283s - 1; i3 >= 0; i3--) {
                View m3 = this.t[i3].m(t2, g2);
                if (m3 != null && m3 != H) {
                    return m3;
                }
            }
        } else {
            for (int i4 = 0; i4 < this.f5283s; i4++) {
                View m4 = this.t[i4].m(t2, g2);
                if (m4 != null && m4 != H) {
                    return m4;
                }
            }
        }
        boolean z2 = (this.z ^ true) == (g2 == -1);
        if (!z) {
            View I = I(z2 ? span.f() : span.g());
            if (I != null && I != H) {
                return I;
            }
        }
        if (J2(g2)) {
            for (int i5 = this.f5283s - 1; i5 >= 0; i5--) {
                if (i5 != span.f5314e) {
                    View I2 = I(z2 ? this.t[i5].f() : this.t[i5].g());
                    if (I2 != null && I2 != H) {
                        return I2;
                    }
                }
            }
        } else {
            for (int i6 = 0; i6 < this.f5283s; i6++) {
                View I3 = I(z2 ? this.t[i6].f() : this.t[i6].g());
                if (I3 != null && I3 != H) {
                    return I3;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int T(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.w == 1 ? this.f5283s : super.T(recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void T0(AccessibilityEvent accessibilityEvent) {
        super.T0(accessibilityEvent);
        if (P() > 0) {
            View n2 = n2(false);
            View m2 = m2(false);
            if (n2 == null || m2 == null) {
                return;
            }
            int p0 = p0(n2);
            int p02 = p0(m2);
            if (p0 < p02) {
                accessibilityEvent.setFromIndex(p0);
                accessibilityEvent.setToIndex(p02);
            } else {
                accessibilityEvent.setFromIndex(p02);
                accessibilityEvent.setToIndex(p0);
            }
        }
    }

    public void T2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        m(null);
        if (i2 == this.w) {
            return;
        }
        this.w = i2;
        OrientationHelper orientationHelper = this.u;
        this.u = this.v;
        this.v = orientationHelper;
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean U1() {
        return this.I == null;
    }

    public void U2(boolean z) {
        m(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.f5307n != z) {
            savedState.f5307n = z;
        }
        this.z = z;
        C1();
    }

    public void V2(int i2) {
        m(null);
        if (i2 != this.f5283s) {
            E2();
            this.f5283s = i2;
            this.B = new BitSet(this.f5283s);
            this.t = new Span[this.f5283s];
            for (int i3 = 0; i3 < this.f5283s; i3++) {
                this.t[i3] = new Span(i3);
            }
            C1();
        }
    }

    boolean X1() {
        int l2 = this.t[0].l(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.f5283s; i2++) {
            if (this.t[i2].l(Integer.MIN_VALUE) != l2) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void Y0(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.X0(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.w == 0) {
            accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(layoutParams2.e(), layoutParams2.f5293f ? this.f5283s : 1, -1, -1, false, false));
        } else {
            accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(-1, -1, layoutParams2.e(), layoutParams2.f5293f ? this.f5283s : 1, false, false));
        }
    }

    boolean Y1() {
        int p2 = this.t[0].p(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.f5283s; i2++) {
            if (this.t[i2].p(Integer.MIN_VALUE) != p2) {
                return false;
            }
        }
        return true;
    }

    boolean Y2(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        if (!state.e() && (i2 = this.C) != -1) {
            if (i2 >= 0 && i2 < state.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f5300c == -1 || savedState.f5302i < 1) {
                    View I = I(this.C);
                    if (I != null) {
                        anchorInfo.f5285a = this.A ? t2() : s2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (anchorInfo.f5287c) {
                                anchorInfo.f5286b = (this.u.i() - this.D) - this.u.d(I);
                            } else {
                                anchorInfo.f5286b = (this.u.m() + this.D) - this.u.g(I);
                            }
                            return true;
                        }
                        if (this.u.e(I) > this.u.n()) {
                            anchorInfo.f5286b = anchorInfo.f5287c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int g2 = this.u.g(I) - this.u.m();
                        if (g2 < 0) {
                            anchorInfo.f5286b = -g2;
                            return true;
                        }
                        int i3 = this.u.i() - this.u.d(I);
                        if (i3 < 0) {
                            anchorInfo.f5286b = i3;
                            return true;
                        }
                        anchorInfo.f5286b = Integer.MIN_VALUE;
                    } else {
                        int i4 = this.C;
                        anchorInfo.f5285a = i4;
                        int i5 = this.D;
                        if (i5 == Integer.MIN_VALUE) {
                            anchorInfo.f5287c = a2(i4) == 1;
                            anchorInfo.a();
                        } else {
                            anchorInfo.b(i5);
                        }
                        anchorInfo.f5288d = true;
                    }
                } else {
                    anchorInfo.f5286b = Integer.MIN_VALUE;
                    anchorInfo.f5285a = this.C;
                }
                return true;
            }
            this.C = -1;
            this.D = Integer.MIN_VALUE;
        }
        return false;
    }

    void Z2(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (Y2(state, anchorInfo) || X2(state, anchorInfo)) {
            return;
        }
        anchorInfo.a();
        anchorInfo.f5285a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void a1(RecyclerView recyclerView, int i2, int i3) {
        C2(i2, i3, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void b1(RecyclerView recyclerView) {
        this.E.b();
        C1();
    }

    boolean b2() {
        int s2;
        int t2;
        if (P() == 0 || this.F == 0 || !z0()) {
            return false;
        }
        if (this.A) {
            s2 = t2();
            t2 = s2();
        } else {
            s2 = s2();
            t2 = t2();
        }
        if (s2 == 0 && D2() != null) {
            this.E.b();
            D1();
            C1();
            return true;
        }
        if (!this.M) {
            return false;
        }
        int i2 = this.A ? -1 : 1;
        int i3 = t2 + 1;
        LazySpanLookup.FullSpanItem e2 = this.E.e(s2, i3, i2, true);
        if (e2 == null) {
            this.M = false;
            this.E.d(i3);
            return false;
        }
        LazySpanLookup.FullSpanItem e3 = this.E.e(s2, e2.f5296c, i2 * (-1), true);
        if (e3 == null) {
            this.E.d(e2.f5296c);
        } else {
            this.E.d(e3.f5296c + 1);
        }
        D1();
        C1();
        return true;
    }

    void b3(int i2) {
        this.x = i2 / this.f5283s;
        this.J = View.MeasureSpec.makeMeasureSpec(i2, this.v.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF c(int i2) {
        int a2 = a2(i2);
        PointF pointF = new PointF();
        if (a2 == 0) {
            return null;
        }
        if (this.w == 0) {
            pointF.x = a2;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = a2;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void c1(RecyclerView recyclerView, int i2, int i3, int i4) {
        C2(i2, i3, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void d1(RecyclerView recyclerView, int i2, int i3) {
        C2(i2, i3, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void f1(RecyclerView recyclerView, int i2, int i3, Object obj) {
        C2(i2, i3, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
        I2(recycler, state, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void h1(RecyclerView.State state) {
        super.h1(state);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void l1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.I = (SavedState) parcelable;
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void m(String str) {
        if (this.I == null) {
            super.m(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable m1() {
        int p2;
        int m2;
        int[] iArr;
        if (this.I != null) {
            return new SavedState(this.I);
        }
        SavedState savedState = new SavedState();
        savedState.f5307n = this.z;
        savedState.f5308o = this.G;
        savedState.f5309p = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f5294a) == null) {
            savedState.f5304k = 0;
        } else {
            savedState.f5305l = iArr;
            savedState.f5304k = iArr.length;
            savedState.f5306m = lazySpanLookup.f5295b;
        }
        if (P() > 0) {
            savedState.f5300c = this.G ? t2() : s2();
            savedState.f5301h = o2();
            int i2 = this.f5283s;
            savedState.f5302i = i2;
            savedState.f5303j = new int[i2];
            for (int i3 = 0; i3 < this.f5283s; i3++) {
                if (this.G) {
                    p2 = this.t[i3].l(Integer.MIN_VALUE);
                    if (p2 != Integer.MIN_VALUE) {
                        m2 = this.u.i();
                        p2 -= m2;
                        savedState.f5303j[i3] = p2;
                    } else {
                        savedState.f5303j[i3] = p2;
                    }
                } else {
                    p2 = this.t[i3].p(Integer.MIN_VALUE);
                    if (p2 != Integer.MIN_VALUE) {
                        m2 = this.u.m();
                        p2 -= m2;
                        savedState.f5303j[i3] = p2;
                    } else {
                        savedState.f5303j[i3] = p2;
                    }
                }
            }
        } else {
            savedState.f5300c = -1;
            savedState.f5301h = -1;
            savedState.f5302i = 0;
        }
        return savedState;
    }

    View m2(boolean z) {
        int m2 = this.u.m();
        int i2 = this.u.i();
        View view = null;
        for (int P = P() - 1; P >= 0; P--) {
            View O = O(P);
            int g2 = this.u.g(O);
            int d2 = this.u.d(O);
            if (d2 > m2 && g2 < i2) {
                if (d2 <= i2 || !z) {
                    return O;
                }
                if (view == null) {
                    view = O;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void n1(int i2) {
        if (i2 == 0) {
            b2();
        }
    }

    View n2(boolean z) {
        int m2 = this.u.m();
        int i2 = this.u.i();
        int P = P();
        View view = null;
        for (int i3 = 0; i3 < P; i3++) {
            View O = O(i3);
            int g2 = this.u.g(O);
            if (this.u.d(O) > m2 && g2 < i2) {
                if (g2 >= m2 || !z) {
                    return O;
                }
                if (view == null) {
                    view = O;
                }
            }
        }
        return view;
    }

    int o2() {
        View m2 = this.A ? m2(true) : n2(true);
        if (m2 == null) {
            return -1;
        }
        return p0(m2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean q() {
        return this.w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean r() {
        return this.w == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean s(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int s0(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.w == 0 ? this.f5283s : super.s0(recycler, state);
    }

    int s2() {
        if (P() == 0) {
            return 0;
        }
        return p0(O(0));
    }

    int t2() {
        int P = P();
        if (P == 0) {
            return 0;
        }
        return p0(O(P - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void u(int i2, int i3, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int l2;
        int i4;
        if (this.w != 0) {
            i2 = i3;
        }
        if (P() == 0 || i2 == 0) {
            return;
        }
        K2(i2, state);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.f5283s) {
            this.O = new int[this.f5283s];
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.f5283s; i6++) {
            LayoutState layoutState = this.y;
            if (layoutState.f5076d == -1) {
                l2 = layoutState.f5078f;
                i4 = this.t[i6].p(l2);
            } else {
                l2 = this.t[i6].l(layoutState.f5079g);
                i4 = this.y.f5079g;
            }
            int i7 = l2 - i4;
            if (i7 >= 0) {
                this.O[i5] = i7;
                i5++;
            }
        }
        Arrays.sort(this.O, 0, i5);
        for (int i8 = 0; i8 < i5 && this.y.a(state); i8++) {
            layoutPrefetchRegistry.a(this.y.f5075c, this.O[i8]);
            LayoutState layoutState2 = this.y;
            layoutState2.f5075c += layoutState2.f5076d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int w(RecyclerView.State state) {
        return d2(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int x(RecyclerView.State state) {
        return e2(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int y(RecyclerView.State state) {
        return f2(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int z(RecyclerView.State state) {
        return d2(state);
    }

    public int z2() {
        return this.w;
    }

    static class LazySpanLookup {

        /* renamed from: a, reason: collision with root package name */
        int[] f5294a;

        /* renamed from: b, reason: collision with root package name */
        List f5295b;

        LazySpanLookup() {
        }

        private int i(int i2) {
            if (this.f5295b == null) {
                return -1;
            }
            FullSpanItem f2 = f(i2);
            if (f2 != null) {
                this.f5295b.remove(f2);
            }
            int size = this.f5295b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (((FullSpanItem) this.f5295b.get(i3)).f5296c >= i2) {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = (FullSpanItem) this.f5295b.get(i3);
            this.f5295b.remove(i3);
            return fullSpanItem.f5296c;
        }

        private void l(int i2, int i3) {
            List list = this.f5295b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f5295b.get(size);
                int i4 = fullSpanItem.f5296c;
                if (i4 >= i2) {
                    fullSpanItem.f5296c = i4 + i3;
                }
            }
        }

        private void m(int i2, int i3) {
            List list = this.f5295b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f5295b.get(size);
                int i5 = fullSpanItem.f5296c;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.f5295b.remove(size);
                    } else {
                        fullSpanItem.f5296c = i5 - i3;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.f5295b == null) {
                this.f5295b = new ArrayList();
            }
            int size = this.f5295b.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = (FullSpanItem) this.f5295b.get(i2);
                if (fullSpanItem2.f5296c == fullSpanItem.f5296c) {
                    this.f5295b.remove(i2);
                }
                if (fullSpanItem2.f5296c >= fullSpanItem.f5296c) {
                    this.f5295b.add(i2, fullSpanItem);
                    return;
                }
            }
            this.f5295b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.f5294a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f5295b = null;
        }

        void c(int i2) {
            int[] iArr = this.f5294a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.f5294a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[o(i2)];
                this.f5294a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f5294a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i2) {
            List list = this.f5295b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (((FullSpanItem) this.f5295b.get(size)).f5296c >= i2) {
                        this.f5295b.remove(size);
                    }
                }
            }
            return h(i2);
        }

        public FullSpanItem e(int i2, int i3, int i4, boolean z) {
            List list = this.f5295b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f5295b.get(i5);
                int i6 = fullSpanItem.f5296c;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || fullSpanItem.f5297h == i4 || (z && fullSpanItem.f5299j))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i2) {
            List list = this.f5295b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f5295b.get(size);
                if (fullSpanItem.f5296c == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i2) {
            int[] iArr = this.f5294a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        int h(int i2) {
            int[] iArr = this.f5294a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int i3 = i(i2);
            if (i3 == -1) {
                int[] iArr2 = this.f5294a;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.f5294a.length;
            }
            int i4 = i3 + 1;
            Arrays.fill(this.f5294a, i2, i4, -1);
            return i4;
        }

        void j(int i2, int i3) {
            int[] iArr = this.f5294a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f5294a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.f5294a, i2, i4, -1);
            l(i2, i3);
        }

        void k(int i2, int i3) {
            int[] iArr = this.f5294a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f5294a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.f5294a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            m(i2, i3);
        }

        void n(int i2, Span span) {
            c(i2);
            this.f5294a[i2] = span.f5314e;
        }

        int o(int i2) {
            int length = this.f5294a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public FullSpanItem[] newArray(int i2) {
                    return new FullSpanItem[i2];
                }
            };

            /* renamed from: c, reason: collision with root package name */
            int f5296c;

            /* renamed from: h, reason: collision with root package name */
            int f5297h;

            /* renamed from: i, reason: collision with root package name */
            int[] f5298i;

            /* renamed from: j, reason: collision with root package name */
            boolean f5299j;

            FullSpanItem(Parcel parcel) {
                this.f5296c = parcel.readInt();
                this.f5297h = parcel.readInt();
                this.f5299j = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    int[] iArr = new int[readInt];
                    this.f5298i = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int a(int i2) {
                int[] iArr = this.f5298i;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i2];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f5296c + ", mGapDir=" + this.f5297h + ", mHasUnwantedGapAfter=" + this.f5299j + ", mGapPerSpan=" + Arrays.toString(this.f5298i) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f5296c);
                parcel.writeInt(this.f5297h);
                parcel.writeInt(this.f5299j ? 1 : 0);
                int[] iArr = this.f5298i;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f5298i);
                }
            }

            FullSpanItem() {
            }
        }
    }

    public StaggeredGridLayoutManager(int i2, int i3) {
        this.w = i3;
        V2(i2);
        this.y = new LayoutState();
        j2();
    }
}
