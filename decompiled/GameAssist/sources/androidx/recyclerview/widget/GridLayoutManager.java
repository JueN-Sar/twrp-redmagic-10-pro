package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    boolean I;
    int J;
    int[] K;
    View[] L;
    final SparseIntArray M;
    final SparseIntArray N;
    SpanSizeLookup O;
    final Rect P;
    private boolean Q;

    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int e(int i2, int i3) {
            return i2 % i3;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int f(int i2) {
            return 1;
        }
    }

    public static abstract class SpanSizeLookup {

        /* renamed from: a, reason: collision with root package name */
        final SparseIntArray f5022a = new SparseIntArray();

        /* renamed from: b, reason: collision with root package name */
        final SparseIntArray f5023b = new SparseIntArray();

        /* renamed from: c, reason: collision with root package name */
        private boolean f5024c = false;

        /* renamed from: d, reason: collision with root package name */
        private boolean f5025d = false;

        static int a(SparseIntArray sparseIntArray, int i2) {
            int size = sparseIntArray.size() - 1;
            int i3 = 0;
            while (i3 <= size) {
                int i4 = (i3 + size) >>> 1;
                if (sparseIntArray.keyAt(i4) < i2) {
                    i3 = i4 + 1;
                } else {
                    size = i4 - 1;
                }
            }
            int i5 = i3 - 1;
            if (i5 < 0 || i5 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i5);
        }

        int b(int i2, int i3) {
            if (!this.f5025d) {
                return d(i2, i3);
            }
            int i4 = this.f5023b.get(i2, -1);
            if (i4 != -1) {
                return i4;
            }
            int d2 = d(i2, i3);
            this.f5023b.put(i2, d2);
            return d2;
        }

        int c(int i2, int i3) {
            if (!this.f5024c) {
                return e(i2, i3);
            }
            int i4 = this.f5022a.get(i2, -1);
            if (i4 != -1) {
                return i4;
            }
            int e2 = e(i2, i3);
            this.f5022a.put(i2, e2);
            return e2;
        }

        public int d(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int a2;
            if (!this.f5025d || (a2 = a(this.f5023b, i2)) == -1) {
                i4 = 0;
                i5 = 0;
                i6 = 0;
            } else {
                i4 = this.f5023b.get(a2);
                i5 = a2 + 1;
                i6 = c(a2, i3) + f(a2);
                if (i6 == i3) {
                    i4++;
                    i6 = 0;
                }
            }
            int f2 = f(i2);
            while (i5 < i2) {
                int f3 = f(i5);
                i6 += f3;
                if (i6 == i3) {
                    i4++;
                    i6 = 0;
                } else if (i6 > i3) {
                    i4++;
                    i6 = f3;
                }
                i5++;
            }
            return i6 + f2 > i3 ? i4 + 1 : i4;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002b -> B:10:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002d -> B:10:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002f -> B:10:0x0030). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int e(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.f(r6)
                r1 = 0
                if (r0 != r7) goto L8
                return r1
            L8:
                boolean r2 = r5.f5024c
                if (r2 == 0) goto L20
                android.util.SparseIntArray r2 = r5.f5022a
                int r2 = a(r2, r6)
                if (r2 < 0) goto L20
                android.util.SparseIntArray r3 = r5.f5022a
                int r3 = r3.get(r2)
                int r4 = r5.f(r2)
                int r3 = r3 + r4
                goto L30
            L20:
                r2 = r1
                r3 = r2
            L22:
                if (r2 >= r6) goto L33
                int r4 = r5.f(r2)
                int r3 = r3 + r4
                if (r3 != r7) goto L2d
                r3 = r1
                goto L30
            L2d:
                if (r3 <= r7) goto L30
                r3 = r4
            L30:
                int r2 = r2 + 1
                goto L22
            L33:
                int r0 = r0 + r3
                if (r0 > r7) goto L37
                return r3
            L37:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup.e(int, int):int");
        }

        public abstract int f(int i2);

        public void g() {
            this.f5023b.clear();
        }

        public void h() {
            this.f5022a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new DefaultSpanSizeLookup();
        this.P = new Rect();
        p3(RecyclerView.LayoutManager.q0(context, attributeSet, i2, i3).f5188b);
    }

    private void X2(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        if (z) {
            i5 = 1;
            i4 = i2;
            i3 = 0;
        } else {
            i3 = i2 - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            View view = this.L[i3];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int k3 = k3(recycler, state, p0(view));
            layoutParams.f5021f = k3;
            layoutParams.f5020e = i6;
            i6 += k3;
            i3 += i5;
        }
    }

    private void Y2() {
        int P = P();
        for (int i2 = 0; i2 < P; i2++) {
            LayoutParams layoutParams = (LayoutParams) O(i2).getLayoutParams();
            int a2 = layoutParams.a();
            this.M.put(a2, layoutParams.f());
            this.N.put(a2, layoutParams.e());
        }
    }

    private void Z2(int i2) {
        this.K = a3(this.K, this.J, i2);
    }

    static int[] a3(int[] iArr, int i2, int i3) {
        int i4;
        if (iArr == null || iArr.length != i2 + 1 || iArr[iArr.length - 1] != i3) {
            iArr = new int[i2 + 1];
        }
        int i5 = 0;
        iArr[0] = 0;
        int i6 = i3 / i2;
        int i7 = i3 % i2;
        int i8 = 0;
        for (int i9 = 1; i9 <= i2; i9++) {
            i5 += i7;
            if (i5 <= 0 || i2 - i5 >= i7) {
                i4 = i6;
            } else {
                i4 = i6 + 1;
                i5 -= i2;
            }
            i8 += i4;
            iArr[i9] = i8;
        }
        return iArr;
    }

    private void b3() {
        this.M.clear();
        this.N.clear();
    }

    private int c3(RecyclerView.State state) {
        if (P() != 0 && state.b() != 0) {
            c2();
            boolean A2 = A2();
            View h2 = h2(!A2, true);
            View g2 = g2(!A2, true);
            if (h2 != null && g2 != null) {
                int b2 = this.O.b(p0(h2), this.J);
                int b3 = this.O.b(p0(g2), this.J);
                int max = this.x ? Math.max(0, ((this.O.b(state.b() - 1, this.J) + 1) - Math.max(b2, b3)) - 1) : Math.max(0, Math.min(b2, b3));
                if (A2) {
                    return Math.round((max * (Math.abs(this.u.d(g2) - this.u.g(h2)) / ((this.O.b(p0(g2), this.J) - this.O.b(p0(h2), this.J)) + 1))) + (this.u.m() - this.u.g(h2)));
                }
                return max;
            }
        }
        return 0;
    }

    private int d3(RecyclerView.State state) {
        if (P() != 0 && state.b() != 0) {
            c2();
            View h2 = h2(!A2(), true);
            View g2 = g2(!A2(), true);
            if (h2 != null && g2 != null) {
                if (!A2()) {
                    return this.O.b(state.b() - 1, this.J) + 1;
                }
                int d2 = this.u.d(g2) - this.u.g(h2);
                int b2 = this.O.b(p0(h2), this.J);
                return (int) ((d2 / ((this.O.b(p0(g2), this.J) - b2) + 1)) * (this.O.b(state.b() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    private void e3(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        boolean z = i2 == 1;
        int j3 = j3(recycler, state, anchorInfo.f5084b);
        if (z) {
            while (j3 > 0) {
                int i3 = anchorInfo.f5084b;
                if (i3 <= 0) {
                    return;
                }
                int i4 = i3 - 1;
                anchorInfo.f5084b = i4;
                j3 = j3(recycler, state, i4);
            }
            return;
        }
        int b2 = state.b() - 1;
        int i5 = anchorInfo.f5084b;
        while (i5 < b2) {
            int i6 = i5 + 1;
            int j32 = j3(recycler, state, i6);
            if (j32 <= j3) {
                break;
            }
            i5 = i6;
            j3 = j32;
        }
        anchorInfo.f5084b = i5;
    }

    private void f3() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    private int i3(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.e()) {
            return this.O.b(i2, this.J);
        }
        int f2 = recycler.f(i2);
        if (f2 != -1) {
            return this.O.b(f2, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i2);
        return 0;
    }

    private int j3(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.e()) {
            return this.O.c(i2, this.J);
        }
        int i3 = this.N.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int f2 = recycler.f(i2);
        if (f2 != -1) {
            return this.O.c(f2, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 0;
    }

    private int k3(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.e()) {
            return this.O.f(i2);
        }
        int i3 = this.M.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int f2 = recycler.f(i2);
        if (f2 != -1) {
            return this.O.f(f2);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 1;
    }

    private void m3(float f2, int i2) {
        Z2(Math.max(Math.round(f2 * this.J), i2));
    }

    private void n3(View view, int i2, boolean z) {
        int i3;
        int i4;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.f5192b;
        int i5 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        int i6 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int g3 = g3(layoutParams.f5020e, layoutParams.f5021f);
        if (this.f5082s == 1) {
            i4 = RecyclerView.LayoutManager.Q(g3, i2, i6, ((ViewGroup.MarginLayoutParams) layoutParams).width, false);
            i3 = RecyclerView.LayoutManager.Q(this.u.n(), d0(), i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, true);
        } else {
            int Q = RecyclerView.LayoutManager.Q(g3, i2, i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, false);
            int Q2 = RecyclerView.LayoutManager.Q(this.u.n(), x0(), i6, ((ViewGroup.MarginLayoutParams) layoutParams).width, true);
            i3 = Q;
            i4 = Q2;
        }
        o3(view, i4, i3, z);
    }

    private void o3(View view, int i2, int i3, boolean z) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z ? Q1(view, i2, i3, layoutParams) : O1(view, i2, i3, layoutParams)) {
            view.measure(i2, i3);
        }
    }

    private void r3() {
        int c0;
        int o0;
        if (y2() == 1) {
            c0 = w0() - m0();
            o0 = l0();
        } else {
            c0 = c0() - j0();
            o0 = o0();
        }
        Z2(c0 - o0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int A(RecyclerView.State state) {
        return this.Q ? c3(state) : super.A(state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int B(RecyclerView.State state) {
        return this.Q ? d3(state) : super.B(state);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x009f, code lost:
    
        r21.f5089b = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a1, code lost:
    
        return;
     */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void B2(androidx.recyclerview.widget.RecyclerView.Recycler r18, androidx.recyclerview.widget.RecyclerView.State r19, androidx.recyclerview.widget.LinearLayoutManager.LayoutState r20, androidx.recyclerview.widget.LinearLayoutManager.LayoutChunkResult r21) {
        /*
            Method dump skipped, instructions count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.B2(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, androidx.recyclerview.widget.LinearLayoutManager$LayoutState, androidx.recyclerview.widget.LinearLayoutManager$LayoutChunkResult):void");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void D2(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        super.D2(recycler, state, anchorInfo, i2);
        r3();
        if (state.b() > 0 && !state.e()) {
            e3(recycler, state, anchorInfo, i2);
        }
        f3();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int F1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        r3();
        f3();
        return super.F1(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int H1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        r3();
        f3();
        return super.H1(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams J() {
        return this.f5082s == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams K(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams L(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void L1(Rect rect, int i2, int i3) {
        int t;
        int t2;
        if (this.K == null) {
            super.L1(rect, i2, i3);
        }
        int l0 = l0() + m0();
        int o0 = o0() + j0();
        if (this.f5082s == 1) {
            t2 = RecyclerView.LayoutManager.t(i3, rect.height() + o0, h0());
            int[] iArr = this.K;
            t = RecyclerView.LayoutManager.t(i2, iArr[iArr.length - 1] + l0, i0());
        } else {
            t = RecyclerView.LayoutManager.t(i2, rect.width() + l0, i0());
            int[] iArr2 = this.K;
            t2 = RecyclerView.LayoutManager.t(i3, iArr2[iArr2.length - 1] + o0, h0());
        }
        K1(t, t2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void O2(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.O2(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d1, code lost:
    
        if (r13 == (r2 > r15)) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f1, code lost:
    
        if (r13 == (r2 > r7)) goto L70;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010f  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View S0(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.Recycler r26, androidx.recyclerview.widget.RecyclerView.State r27) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.S0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int T(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f5082s == 1) {
            return this.J;
        }
        if (state.b() < 1) {
            return 0;
        }
        return i3(recycler, state, state.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean U1() {
        return this.D == null && !this.I;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void W1(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.J;
        for (int i3 = 0; i3 < this.J && layoutState.c(state) && i2 > 0; i3++) {
            int i4 = layoutState.f5095d;
            layoutPrefetchRegistry.a(i4, Math.max(0, layoutState.f5098g));
            i2 -= this.O.f(i4);
            layoutState.f5095d += layoutState.f5096e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void Y0(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.X0(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int i3 = i3(recycler, state, layoutParams2.a());
        if (this.f5082s == 0) {
            accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(layoutParams2.e(), layoutParams2.f(), i3, 1, false, false));
        } else {
            accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(i3, 1, layoutParams2.e(), layoutParams2.f(), false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void a1(RecyclerView recyclerView, int i2, int i3) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void b1(RecyclerView recyclerView) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void c1(RecyclerView recyclerView, int i2, int i3, int i4) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void d1(RecyclerView recyclerView, int i2, int i3) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void f1(RecyclerView recyclerView, int i2, int i3, Object obj) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.e()) {
            Y2();
        }
        super.g1(recycler, state);
        b3();
    }

    int g3(int i2, int i3) {
        if (this.f5082s != 1 || !z2()) {
            int[] iArr = this.K;
            return iArr[i3 + i2] - iArr[i2];
        }
        int[] iArr2 = this.K;
        int i4 = this.J;
        return iArr2[i4 - i2] - iArr2[(i4 - i2) - i3];
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void h1(RecyclerView.State state) {
        super.h1(state);
        this.I = false;
    }

    public int h3() {
        return this.J;
    }

    public SpanSizeLookup l3() {
        return this.O;
    }

    public void p3(int i2) {
        if (i2 == this.J) {
            return;
        }
        this.I = true;
        if (i2 >= 1) {
            this.J = i2;
            this.O.h();
            C1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i2);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    View q2(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i3, int i4) {
        c2();
        int m2 = this.u.m();
        int i5 = this.u.i();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View O = O(i2);
            int p0 = p0(O);
            if (p0 >= 0 && p0 < i4 && j3(recycler, state, p0) == 0) {
                if (((RecyclerView.LayoutParams) O.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = O;
                    }
                } else {
                    if (this.u.g(O) < i5 && this.u.d(O) >= m2) {
                        return O;
                    }
                    if (view == null) {
                        view = O;
                    }
                }
            }
            i2 += i6;
        }
        return view != null ? view : view2;
    }

    public void q3(SpanSizeLookup spanSizeLookup) {
        this.O = spanSizeLookup;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean s(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int s0(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f5082s == 0) {
            return this.J;
        }
        if (state.b() < 1) {
            return 0;
        }
        return i3(recycler, state, state.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int x(RecyclerView.State state) {
        return this.Q ? c3(state) : super.x(state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int y(RecyclerView.State state) {
        return this.Q ? d3(state) : super.y(state);
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {

        /* renamed from: e, reason: collision with root package name */
        int f5020e;

        /* renamed from: f, reason: collision with root package name */
        int f5021f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5020e = -1;
            this.f5021f = 0;
        }

        public int e() {
            return this.f5020e;
        }

        public int f() {
            return this.f5021f;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f5020e = -1;
            this.f5021f = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f5020e = -1;
            this.f5021f = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f5020e = -1;
            this.f5021f = 0;
        }
    }

    public GridLayoutManager(Context context, int i2, int i3, boolean z) {
        super(context, i3, z);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new DefaultSpanSizeLookup();
        this.P = new Rect();
        p3(i2);
    }
}
