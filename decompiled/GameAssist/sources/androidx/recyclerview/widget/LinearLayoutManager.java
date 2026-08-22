package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Api;
import java.util.List;

/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    int A;
    int B;
    private boolean C;
    SavedState D;
    final AnchorInfo E;
    private final LayoutChunkResult F;
    private int G;
    private int[] H;

    /* renamed from: s, reason: collision with root package name */
    int f5082s;
    private LayoutState t;
    OrientationHelper u;
    private boolean v;
    private boolean w;
    boolean x;
    private boolean y;
    private boolean z;

    static class AnchorInfo {

        /* renamed from: a, reason: collision with root package name */
        OrientationHelper f5083a;

        /* renamed from: b, reason: collision with root package name */
        int f5084b;

        /* renamed from: c, reason: collision with root package name */
        int f5085c;

        /* renamed from: d, reason: collision with root package name */
        boolean f5086d;

        /* renamed from: e, reason: collision with root package name */
        boolean f5087e;

        AnchorInfo() {
            e();
        }

        void a() {
            this.f5085c = this.f5086d ? this.f5083a.i() : this.f5083a.m();
        }

        public void b(View view, int i2) {
            if (this.f5086d) {
                this.f5085c = this.f5083a.d(view) + this.f5083a.o();
            } else {
                this.f5085c = this.f5083a.g(view);
            }
            this.f5084b = i2;
        }

        public void c(View view, int i2) {
            int o2 = this.f5083a.o();
            if (o2 >= 0) {
                b(view, i2);
                return;
            }
            this.f5084b = i2;
            if (this.f5086d) {
                int i3 = (this.f5083a.i() - o2) - this.f5083a.d(view);
                this.f5085c = this.f5083a.i() - i3;
                if (i3 > 0) {
                    int e2 = this.f5085c - this.f5083a.e(view);
                    int m2 = this.f5083a.m();
                    int min = e2 - (m2 + Math.min(this.f5083a.g(view) - m2, 0));
                    if (min < 0) {
                        this.f5085c += Math.min(i3, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int g2 = this.f5083a.g(view);
            int m3 = g2 - this.f5083a.m();
            this.f5085c = g2;
            if (m3 > 0) {
                int i4 = (this.f5083a.i() - Math.min(0, (this.f5083a.i() - o2) - this.f5083a.d(view))) - (g2 + this.f5083a.e(view));
                if (i4 < 0) {
                    this.f5085c -= Math.min(m3, -i4);
                }
            }
        }

        boolean d(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.c() && layoutParams.a() >= 0 && layoutParams.a() < state.b();
        }

        void e() {
            this.f5084b = -1;
            this.f5085c = Integer.MIN_VALUE;
            this.f5086d = false;
            this.f5087e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f5084b + ", mCoordinate=" + this.f5085c + ", mLayoutFromEnd=" + this.f5086d + ", mValid=" + this.f5087e + '}';
        }
    }

    protected static class LayoutChunkResult {

        /* renamed from: a, reason: collision with root package name */
        public int f5088a;

        /* renamed from: b, reason: collision with root package name */
        public boolean f5089b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f5090c;

        /* renamed from: d, reason: collision with root package name */
        public boolean f5091d;

        protected LayoutChunkResult() {
        }

        void a() {
            this.f5088a = 0;
            this.f5089b = false;
            this.f5090c = false;
            this.f5091d = false;
        }
    }

    static class LayoutState {

        /* renamed from: b, reason: collision with root package name */
        int f5093b;

        /* renamed from: c, reason: collision with root package name */
        int f5094c;

        /* renamed from: d, reason: collision with root package name */
        int f5095d;

        /* renamed from: e, reason: collision with root package name */
        int f5096e;

        /* renamed from: f, reason: collision with root package name */
        int f5097f;

        /* renamed from: g, reason: collision with root package name */
        int f5098g;

        /* renamed from: k, reason: collision with root package name */
        int f5102k;

        /* renamed from: m, reason: collision with root package name */
        boolean f5104m;

        /* renamed from: a, reason: collision with root package name */
        boolean f5092a = true;

        /* renamed from: h, reason: collision with root package name */
        int f5099h = 0;

        /* renamed from: i, reason: collision with root package name */
        int f5100i = 0;

        /* renamed from: j, reason: collision with root package name */
        boolean f5101j = false;

        /* renamed from: l, reason: collision with root package name */
        List f5103l = null;

        LayoutState() {
        }

        private View e() {
            int size = this.f5103l.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = ((RecyclerView.ViewHolder) this.f5103l.get(i2)).f5252a;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!layoutParams.c() && this.f5095d == layoutParams.a()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public void a() {
            b(null);
        }

        public void b(View view) {
            View f2 = f(view);
            if (f2 == null) {
                this.f5095d = -1;
            } else {
                this.f5095d = ((RecyclerView.LayoutParams) f2.getLayoutParams()).a();
            }
        }

        boolean c(RecyclerView.State state) {
            int i2 = this.f5095d;
            return i2 >= 0 && i2 < state.b();
        }

        View d(RecyclerView.Recycler recycler) {
            if (this.f5103l != null) {
                return e();
            }
            View o2 = recycler.o(this.f5095d);
            this.f5095d += this.f5096e;
            return o2;
        }

        public View f(View view) {
            int a2;
            int size = this.f5103l.size();
            View view2 = null;
            int i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            for (int i3 = 0; i3 < size; i3++) {
                View view3 = ((RecyclerView.ViewHolder) this.f5103l.get(i3)).f5252a;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.c() && (a2 = (layoutParams.a() - this.f5095d) * this.f5096e) >= 0 && a2 < i2) {
                    view2 = view3;
                    if (a2 == 0) {
                        break;
                    }
                    i2 = a2;
                }
            }
            return view2;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.recyclerview.widget.LinearLayoutManager.SavedState.1
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
        int f5105c;

        /* renamed from: h, reason: collision with root package name */
        int f5106h;

        /* renamed from: i, reason: collision with root package name */
        boolean f5107i;

        public SavedState() {
        }

        boolean a() {
            return this.f5105c >= 0;
        }

        void b() {
            this.f5105c = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f5105c);
            parcel.writeInt(this.f5106h);
            parcel.writeInt(this.f5107i ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f5105c = parcel.readInt();
            this.f5106h = parcel.readInt();
            this.f5107i = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f5105c = savedState.f5105c;
            this.f5106h = savedState.f5106h;
            this.f5107i = savedState.f5107i;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private void C2(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i3) {
        if (!state.g() || P() == 0 || state.e() || !U1()) {
            return;
        }
        List k2 = recycler.k();
        int size = k2.size();
        int p0 = p0(O(0));
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) k2.get(i6);
            if (!viewHolder.w()) {
                if ((viewHolder.n() < p0) != this.x) {
                    i4 += this.u.e(viewHolder.f5252a);
                } else {
                    i5 += this.u.e(viewHolder.f5252a);
                }
            }
        }
        this.t.f5103l = k2;
        if (i4 > 0) {
            V2(p0(w2()), i2);
            LayoutState layoutState = this.t;
            layoutState.f5099h = i4;
            layoutState.f5094c = 0;
            layoutState.a();
            d2(recycler, this.t, state, false);
        }
        if (i5 > 0) {
            T2(p0(v2()), i3);
            LayoutState layoutState2 = this.t;
            layoutState2.f5099h = i5;
            layoutState2.f5094c = 0;
            layoutState2.a();
            d2(recycler, this.t, state, false);
        }
        this.t.f5103l = null;
    }

    private void E2(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.f5092a || layoutState.f5104m) {
            return;
        }
        int i2 = layoutState.f5098g;
        int i3 = layoutState.f5100i;
        if (layoutState.f5097f == -1) {
            G2(recycler, i2, i3);
        } else {
            H2(recycler, i2, i3);
        }
    }

    private void F2(RecyclerView.Recycler recycler, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        if (i3 <= i2) {
            while (i2 > i3) {
                w1(i2, recycler);
                i2--;
            }
        } else {
            for (int i4 = i3 - 1; i4 >= i2; i4--) {
                w1(i4, recycler);
            }
        }
    }

    private void G2(RecyclerView.Recycler recycler, int i2, int i3) {
        int P = P();
        if (i2 < 0) {
            return;
        }
        int h2 = (this.u.h() - i2) + i3;
        if (this.x) {
            for (int i4 = 0; i4 < P; i4++) {
                View O = O(i4);
                if (this.u.g(O) < h2 || this.u.q(O) < h2) {
                    F2(recycler, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = P - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View O2 = O(i6);
            if (this.u.g(O2) < h2 || this.u.q(O2) < h2) {
                F2(recycler, i5, i6);
                return;
            }
        }
    }

    private void H2(RecyclerView.Recycler recycler, int i2, int i3) {
        if (i2 < 0) {
            return;
        }
        int i4 = i2 - i3;
        int P = P();
        if (!this.x) {
            for (int i5 = 0; i5 < P; i5++) {
                View O = O(i5);
                if (this.u.d(O) > i4 || this.u.p(O) > i4) {
                    F2(recycler, 0, i5);
                    return;
                }
            }
            return;
        }
        int i6 = P - 1;
        for (int i7 = i6; i7 >= 0; i7--) {
            View O2 = O(i7);
            if (this.u.d(O2) > i4 || this.u.p(O2) > i4) {
                F2(recycler, i6, i7);
                return;
            }
        }
    }

    private void J2() {
        if (this.f5082s == 1 || !z2()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    private boolean P2(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        if (P() == 0) {
            return false;
        }
        View b0 = b0();
        if (b0 != null && anchorInfo.d(b0, state)) {
            anchorInfo.c(b0, p0(b0));
            return true;
        }
        if (this.v != this.y) {
            return false;
        }
        View r2 = anchorInfo.f5086d ? r2(recycler, state) : s2(recycler, state);
        if (r2 == null) {
            return false;
        }
        anchorInfo.b(r2, p0(r2));
        if (!state.e() && U1() && (this.u.g(r2) >= this.u.i() || this.u.d(r2) < this.u.m())) {
            anchorInfo.f5085c = anchorInfo.f5086d ? this.u.i() : this.u.m();
        }
        return true;
    }

    private boolean Q2(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        if (!state.e() && (i2 = this.A) != -1) {
            if (i2 >= 0 && i2 < state.b()) {
                anchorInfo.f5084b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.a()) {
                    boolean z = this.D.f5107i;
                    anchorInfo.f5086d = z;
                    if (z) {
                        anchorInfo.f5085c = this.u.i() - this.D.f5106h;
                    } else {
                        anchorInfo.f5085c = this.u.m() + this.D.f5106h;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z2 = this.x;
                    anchorInfo.f5086d = z2;
                    if (z2) {
                        anchorInfo.f5085c = this.u.i() - this.B;
                    } else {
                        anchorInfo.f5085c = this.u.m() + this.B;
                    }
                    return true;
                }
                View I = I(this.A);
                if (I == null) {
                    if (P() > 0) {
                        anchorInfo.f5086d = (this.A < p0(O(0))) == this.x;
                    }
                    anchorInfo.a();
                } else {
                    if (this.u.e(I) > this.u.n()) {
                        anchorInfo.a();
                        return true;
                    }
                    if (this.u.g(I) - this.u.m() < 0) {
                        anchorInfo.f5085c = this.u.m();
                        anchorInfo.f5086d = false;
                        return true;
                    }
                    if (this.u.i() - this.u.d(I) < 0) {
                        anchorInfo.f5085c = this.u.i();
                        anchorInfo.f5086d = true;
                        return true;
                    }
                    anchorInfo.f5085c = anchorInfo.f5086d ? this.u.d(I) + this.u.o() : this.u.g(I);
                }
                return true;
            }
            this.A = -1;
            this.B = Integer.MIN_VALUE;
        }
        return false;
    }

    private void R2(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        if (Q2(state, anchorInfo) || P2(recycler, state, anchorInfo)) {
            return;
        }
        anchorInfo.a();
        anchorInfo.f5084b = this.y ? state.b() - 1 : 0;
    }

    private void S2(int i2, int i3, boolean z, RecyclerView.State state) {
        int m2;
        this.t.f5104m = I2();
        this.t.f5097f = i2;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        V1(state, iArr);
        int max = Math.max(0, this.H[0]);
        int max2 = Math.max(0, this.H[1]);
        boolean z2 = i2 == 1;
        LayoutState layoutState = this.t;
        int i4 = z2 ? max2 : max;
        layoutState.f5099h = i4;
        if (!z2) {
            max = max2;
        }
        layoutState.f5100i = max;
        if (z2) {
            layoutState.f5099h = i4 + this.u.j();
            View v2 = v2();
            LayoutState layoutState2 = this.t;
            layoutState2.f5096e = this.x ? -1 : 1;
            int p0 = p0(v2);
            LayoutState layoutState3 = this.t;
            layoutState2.f5095d = p0 + layoutState3.f5096e;
            layoutState3.f5093b = this.u.d(v2);
            m2 = this.u.d(v2) - this.u.i();
        } else {
            View w2 = w2();
            this.t.f5099h += this.u.m();
            LayoutState layoutState4 = this.t;
            layoutState4.f5096e = this.x ? 1 : -1;
            int p02 = p0(w2);
            LayoutState layoutState5 = this.t;
            layoutState4.f5095d = p02 + layoutState5.f5096e;
            layoutState5.f5093b = this.u.g(w2);
            m2 = (-this.u.g(w2)) + this.u.m();
        }
        LayoutState layoutState6 = this.t;
        layoutState6.f5094c = i3;
        if (z) {
            layoutState6.f5094c = i3 - m2;
        }
        layoutState6.f5098g = m2;
    }

    private void T2(int i2, int i3) {
        this.t.f5094c = this.u.i() - i3;
        LayoutState layoutState = this.t;
        layoutState.f5096e = this.x ? -1 : 1;
        layoutState.f5095d = i2;
        layoutState.f5097f = 1;
        layoutState.f5093b = i3;
        layoutState.f5098g = Integer.MIN_VALUE;
    }

    private void U2(AnchorInfo anchorInfo) {
        T2(anchorInfo.f5084b, anchorInfo.f5085c);
    }

    private void V2(int i2, int i3) {
        this.t.f5094c = i3 - this.u.m();
        LayoutState layoutState = this.t;
        layoutState.f5095d = i2;
        layoutState.f5096e = this.x ? 1 : -1;
        layoutState.f5097f = -1;
        layoutState.f5093b = i3;
        layoutState.f5098g = Integer.MIN_VALUE;
    }

    private void W2(AnchorInfo anchorInfo) {
        V2(anchorInfo.f5084b, anchorInfo.f5085c);
    }

    private int X1(RecyclerView.State state) {
        if (P() == 0) {
            return 0;
        }
        c2();
        return ScrollbarHelper.a(state, this.u, h2(!this.z, true), g2(!this.z, true), this, this.z);
    }

    private int Y1(RecyclerView.State state) {
        if (P() == 0) {
            return 0;
        }
        c2();
        return ScrollbarHelper.b(state, this.u, h2(!this.z, true), g2(!this.z, true), this, this.z, this.x);
    }

    private int Z1(RecyclerView.State state) {
        if (P() == 0) {
            return 0;
        }
        c2();
        return ScrollbarHelper.c(state, this.u, h2(!this.z, true), g2(!this.z, true), this, this.z);
    }

    private View e2() {
        return m2(0, P());
    }

    private View f2(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return q2(recycler, state, 0, P(), state.b());
    }

    private View j2() {
        return m2(P() - 1, -1);
    }

    private View k2(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return q2(recycler, state, P() - 1, -1, state.b());
    }

    private View o2() {
        return this.x ? e2() : j2();
    }

    private View p2() {
        return this.x ? j2() : e2();
    }

    private View r2(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.x ? f2(recycler, state) : k2(recycler, state);
    }

    private View s2(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.x ? k2(recycler, state) : f2(recycler, state);
    }

    private int t2(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i3;
        int i4 = this.u.i() - i2;
        if (i4 <= 0) {
            return 0;
        }
        int i5 = -K2(-i4, recycler, state);
        int i6 = i2 + i5;
        if (!z || (i3 = this.u.i() - i6) <= 0) {
            return i5;
        }
        this.u.r(i3);
        return i3 + i5;
    }

    private int u2(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int m2;
        int m3 = i2 - this.u.m();
        if (m3 <= 0) {
            return 0;
        }
        int i3 = -K2(m3, recycler, state);
        int i4 = i2 + i3;
        if (!z || (m2 = i4 - this.u.m()) <= 0) {
            return i3;
        }
        this.u.r(-m2);
        return i3 - m2;
    }

    private View v2() {
        return O(this.x ? 0 : P() - 1);
    }

    private View w2() {
        return O(this.x ? P() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int A(RecyclerView.State state) {
        return Y1(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean A0() {
        return true;
    }

    public boolean A2() {
        return this.z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int B(RecyclerView.State state) {
        return Z1(state);
    }

    void B2(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int i2;
        int i3;
        int i4;
        int i5;
        int f2;
        View d2 = layoutState.d(recycler);
        if (d2 == null) {
            layoutChunkResult.f5089b = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) d2.getLayoutParams();
        if (layoutState.f5103l == null) {
            if (this.x == (layoutState.f5097f == -1)) {
                j(d2);
            } else {
                k(d2, 0);
            }
        } else {
            if (this.x == (layoutState.f5097f == -1)) {
                h(d2);
            } else {
                i(d2, 0);
            }
        }
        J0(d2, 0, 0);
        layoutChunkResult.f5088a = this.u.e(d2);
        if (this.f5082s == 1) {
            if (z2()) {
                f2 = w0() - m0();
                i5 = f2 - this.u.f(d2);
            } else {
                i5 = l0();
                f2 = this.u.f(d2) + i5;
            }
            if (layoutState.f5097f == -1) {
                int i6 = layoutState.f5093b;
                i4 = i6;
                i3 = f2;
                i2 = i6 - layoutChunkResult.f5088a;
            } else {
                int i7 = layoutState.f5093b;
                i2 = i7;
                i3 = f2;
                i4 = layoutChunkResult.f5088a + i7;
            }
        } else {
            int o0 = o0();
            int f3 = this.u.f(d2) + o0;
            if (layoutState.f5097f == -1) {
                int i8 = layoutState.f5093b;
                i3 = i8;
                i2 = o0;
                i4 = f3;
                i5 = i8 - layoutChunkResult.f5088a;
            } else {
                int i9 = layoutState.f5093b;
                i2 = o0;
                i3 = layoutChunkResult.f5088a + i9;
                i4 = f3;
                i5 = i9;
            }
        }
        I0(d2, i5, i2, i3, i4);
        if (layoutParams.c() || layoutParams.b()) {
            layoutChunkResult.f5090c = true;
        }
        layoutChunkResult.f5091d = d2.hasFocusable();
    }

    void D2(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int i2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int F1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f5082s == 1) {
            return 0;
        }
        return K2(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void G1(int i2) {
        this.A = i2;
        this.B = Integer.MIN_VALUE;
        SavedState savedState = this.D;
        if (savedState != null) {
            savedState.b();
        }
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int H1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f5082s == 0) {
            return 0;
        }
        return K2(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View I(int i2) {
        int P = P();
        if (P == 0) {
            return null;
        }
        int p0 = i2 - p0(O(0));
        if (p0 >= 0 && p0 < P) {
            View O = O(p0);
            if (p0(O) == i2) {
                return O;
            }
        }
        return super.I(i2);
    }

    boolean I2() {
        return this.u.k() == 0 && this.u.h() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams J() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    int K2(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (P() == 0 || i2 == 0) {
            return 0;
        }
        c2();
        this.t.f5092a = true;
        int i3 = i2 > 0 ? 1 : -1;
        int abs = Math.abs(i2);
        S2(i3, abs, true, state);
        LayoutState layoutState = this.t;
        int d2 = layoutState.f5098g + d2(recycler, layoutState, state, false);
        if (d2 < 0) {
            return 0;
        }
        if (abs > d2) {
            i2 = i3 * d2;
        }
        this.u.r(-i2);
        this.t.f5102k = i2;
        return i2;
    }

    public void L2(int i2, int i3) {
        this.A = i2;
        this.B = i3;
        SavedState savedState = this.D;
        if (savedState != null) {
            savedState.b();
        }
        C1();
    }

    public void M2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        m(null);
        if (i2 != this.f5082s || this.u == null) {
            OrientationHelper b2 = OrientationHelper.b(this, i2);
            this.u = b2;
            this.E.f5083a = b2;
            this.f5082s = i2;
            C1();
        }
    }

    public void N2(boolean z) {
        m(null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        C1();
    }

    public void O2(boolean z) {
        m(null);
        if (this.y == z) {
            return;
        }
        this.y = z;
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    boolean P1() {
        return (d0() == 1073741824 || x0() == 1073741824 || !y0()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void R0(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.R0(recyclerView, recycler);
        if (this.C) {
            t1(recycler);
            recycler.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void R1(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.p(i2);
        S1(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View S0(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a2;
        J2();
        if (P() == 0 || (a2 = a2(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        c2();
        S2(a2, (int) (this.u.n() * 0.33333334f), false, state);
        LayoutState layoutState = this.t;
        layoutState.f5098g = Integer.MIN_VALUE;
        layoutState.f5092a = false;
        d2(recycler, layoutState, state, true);
        View p2 = a2 == -1 ? p2() : o2();
        View w2 = a2 == -1 ? w2() : v2();
        if (!w2.hasFocusable()) {
            return p2;
        }
        if (p2 == null) {
            return null;
        }
        return w2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void T0(AccessibilityEvent accessibilityEvent) {
        super.T0(accessibilityEvent);
        if (P() > 0) {
            accessibilityEvent.setFromIndex(i2());
            accessibilityEvent.setToIndex(l2());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean U1() {
        return this.D == null && this.v == this.y;
    }

    protected void V1(RecyclerView.State state, int[] iArr) {
        int i2;
        int x2 = x2(state);
        if (this.t.f5097f == -1) {
            i2 = 0;
        } else {
            i2 = x2;
            x2 = 0;
        }
        iArr[0] = x2;
        iArr[1] = i2;
    }

    void W1(RecyclerView.State state, LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = layoutState.f5095d;
        if (i2 < 0 || i2 >= state.b()) {
            return;
        }
        layoutPrefetchRegistry.a(i2, Math.max(0, layoutState.f5098g));
    }

    int a2(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.f5082s == 1) ? 1 : Integer.MIN_VALUE : this.f5082s == 0 ? 1 : Integer.MIN_VALUE : this.f5082s == 1 ? -1 : Integer.MIN_VALUE : this.f5082s == 0 ? -1 : Integer.MIN_VALUE : (this.f5082s != 1 && z2()) ? -1 : 1 : (this.f5082s != 1 && z2()) ? 1 : -1;
    }

    LayoutState b2() {
        return new LayoutState();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF c(int i2) {
        if (P() == 0) {
            return null;
        }
        int i3 = (i2 < p0(O(0))) != this.x ? -1 : 1;
        return this.f5082s == 0 ? new PointF(i3, 0.0f) : new PointF(0.0f, i3);
    }

    void c2() {
        if (this.t == null) {
            this.t = b2();
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
    public void d(View view, View view2, int i2, int i3) {
        m("Cannot drop a view during a scroll or layout calculation");
        c2();
        J2();
        int p0 = p0(view);
        int p02 = p0(view2);
        char c2 = p0 < p02 ? (char) 1 : (char) 65535;
        if (this.x) {
            if (c2 == 1) {
                L2(p02, this.u.i() - (this.u.g(view2) + this.u.e(view)));
                return;
            } else {
                L2(p02, this.u.i() - this.u.d(view2));
                return;
            }
        }
        if (c2 == 65535) {
            L2(p02, this.u.g(view2));
        } else {
            L2(p02, this.u.d(view2) - this.u.e(view));
        }
    }

    int d2(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i2 = layoutState.f5094c;
        int i3 = layoutState.f5098g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                layoutState.f5098g = i3 + i2;
            }
            E2(recycler, layoutState);
        }
        int i4 = layoutState.f5094c + layoutState.f5099h;
        LayoutChunkResult layoutChunkResult = this.F;
        while (true) {
            if ((!layoutState.f5104m && i4 <= 0) || !layoutState.c(state)) {
                break;
            }
            layoutChunkResult.a();
            B2(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.f5089b) {
                layoutState.f5093b += layoutChunkResult.f5088a * layoutState.f5097f;
                if (!layoutChunkResult.f5090c || layoutState.f5103l != null || !state.e()) {
                    int i5 = layoutState.f5094c;
                    int i6 = layoutChunkResult.f5088a;
                    layoutState.f5094c = i5 - i6;
                    i4 -= i6;
                }
                int i7 = layoutState.f5098g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + layoutChunkResult.f5088a;
                    layoutState.f5098g = i8;
                    int i9 = layoutState.f5094c;
                    if (i9 < 0) {
                        layoutState.f5098g = i8 + i9;
                    }
                    E2(recycler, layoutState);
                }
                if (z && layoutChunkResult.f5091d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - layoutState.f5094c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        int i3;
        int i4;
        int i5;
        int t2;
        int i6;
        View I;
        int g2;
        int i7;
        int i8 = -1;
        if (!(this.D == null && this.A == -1) && state.b() == 0) {
            t1(recycler);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.a()) {
            this.A = this.D.f5105c;
        }
        c2();
        this.t.f5092a = false;
        J2();
        View b0 = b0();
        AnchorInfo anchorInfo = this.E;
        if (!anchorInfo.f5087e || this.A != -1 || this.D != null) {
            anchorInfo.e();
            AnchorInfo anchorInfo2 = this.E;
            anchorInfo2.f5086d = this.x ^ this.y;
            R2(recycler, state, anchorInfo2);
            this.E.f5087e = true;
        } else if (b0 != null && (this.u.g(b0) >= this.u.i() || this.u.d(b0) <= this.u.m())) {
            this.E.c(b0, p0(b0));
        }
        LayoutState layoutState = this.t;
        layoutState.f5097f = layoutState.f5102k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        V1(state, iArr);
        int max = Math.max(0, this.H[0]) + this.u.m();
        int max2 = Math.max(0, this.H[1]) + this.u.j();
        if (state.e() && (i6 = this.A) != -1 && this.B != Integer.MIN_VALUE && (I = I(i6)) != null) {
            if (this.x) {
                i7 = this.u.i() - this.u.d(I);
                g2 = this.B;
            } else {
                g2 = this.u.g(I) - this.u.m();
                i7 = this.B;
            }
            int i9 = i7 - g2;
            if (i9 > 0) {
                max += i9;
            } else {
                max2 -= i9;
            }
        }
        AnchorInfo anchorInfo3 = this.E;
        if (!anchorInfo3.f5086d ? !this.x : this.x) {
            i8 = 1;
        }
        D2(recycler, state, anchorInfo3, i8);
        C(recycler);
        this.t.f5104m = I2();
        this.t.f5101j = state.e();
        this.t.f5100i = 0;
        AnchorInfo anchorInfo4 = this.E;
        if (anchorInfo4.f5086d) {
            W2(anchorInfo4);
            LayoutState layoutState2 = this.t;
            layoutState2.f5099h = max;
            d2(recycler, layoutState2, state, false);
            LayoutState layoutState3 = this.t;
            i3 = layoutState3.f5093b;
            int i10 = layoutState3.f5095d;
            int i11 = layoutState3.f5094c;
            if (i11 > 0) {
                max2 += i11;
            }
            U2(this.E);
            LayoutState layoutState4 = this.t;
            layoutState4.f5099h = max2;
            layoutState4.f5095d += layoutState4.f5096e;
            d2(recycler, layoutState4, state, false);
            LayoutState layoutState5 = this.t;
            i2 = layoutState5.f5093b;
            int i12 = layoutState5.f5094c;
            if (i12 > 0) {
                V2(i10, i3);
                LayoutState layoutState6 = this.t;
                layoutState6.f5099h = i12;
                d2(recycler, layoutState6, state, false);
                i3 = this.t.f5093b;
            }
        } else {
            U2(anchorInfo4);
            LayoutState layoutState7 = this.t;
            layoutState7.f5099h = max2;
            d2(recycler, layoutState7, state, false);
            LayoutState layoutState8 = this.t;
            i2 = layoutState8.f5093b;
            int i13 = layoutState8.f5095d;
            int i14 = layoutState8.f5094c;
            if (i14 > 0) {
                max += i14;
            }
            W2(this.E);
            LayoutState layoutState9 = this.t;
            layoutState9.f5099h = max;
            layoutState9.f5095d += layoutState9.f5096e;
            d2(recycler, layoutState9, state, false);
            LayoutState layoutState10 = this.t;
            i3 = layoutState10.f5093b;
            int i15 = layoutState10.f5094c;
            if (i15 > 0) {
                T2(i13, i2);
                LayoutState layoutState11 = this.t;
                layoutState11.f5099h = i15;
                d2(recycler, layoutState11, state, false);
                i2 = this.t.f5093b;
            }
        }
        if (P() > 0) {
            if (this.x ^ this.y) {
                int t22 = t2(i2, recycler, state, true);
                i4 = i3 + t22;
                i5 = i2 + t22;
                t2 = u2(i4, recycler, state, false);
            } else {
                int u2 = u2(i3, recycler, state, true);
                i4 = i3 + u2;
                i5 = i2 + u2;
                t2 = t2(i5, recycler, state, false);
            }
            i3 = i4 + t2;
            i2 = i5 + t2;
        }
        C2(recycler, state, i3, i2);
        if (state.e()) {
            this.E.e();
        } else {
            this.u.s();
        }
        this.v = this.y;
    }

    View g2(boolean z, boolean z2) {
        return this.x ? n2(0, P(), z, z2) : n2(P() - 1, -1, z, z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void h1(RecyclerView.State state) {
        super.h1(state);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    View h2(boolean z, boolean z2) {
        return this.x ? n2(P() - 1, -1, z, z2) : n2(0, P(), z, z2);
    }

    public int i2() {
        View n2 = n2(0, P(), false, true);
        if (n2 == null) {
            return -1;
        }
        return p0(n2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void l1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.D = (SavedState) parcelable;
            C1();
        }
    }

    public int l2() {
        View n2 = n2(P() - 1, -1, false, true);
        if (n2 == null) {
            return -1;
        }
        return p0(n2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void m(String str) {
        if (this.D == null) {
            super.m(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable m1() {
        if (this.D != null) {
            return new SavedState(this.D);
        }
        SavedState savedState = new SavedState();
        if (P() > 0) {
            c2();
            boolean z = this.v ^ this.x;
            savedState.f5107i = z;
            if (z) {
                View v2 = v2();
                savedState.f5106h = this.u.i() - this.u.d(v2);
                savedState.f5105c = p0(v2);
            } else {
                View w2 = w2();
                savedState.f5105c = p0(w2);
                savedState.f5106h = this.u.g(w2) - this.u.m();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    View m2(int i2, int i3) {
        int i4;
        int i5;
        c2();
        if (i3 <= i2 && i3 >= i2) {
            return O(i2);
        }
        if (this.u.g(O(i2)) < this.u.m()) {
            i4 = 16644;
            i5 = 16388;
        } else {
            i4 = 4161;
            i5 = 4097;
        }
        return this.f5082s == 0 ? this.f5171e.a(i2, i3, i4, i5) : this.f5172f.a(i2, i3, i4, i5);
    }

    View n2(int i2, int i3, boolean z, boolean z2) {
        c2();
        int i4 = z ? 24579 : 320;
        int i5 = z2 ? 320 : 0;
        return this.f5082s == 0 ? this.f5171e.a(i2, i3, i4, i5) : this.f5172f.a(i2, i3, i4, i5);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean q() {
        return this.f5082s == 0;
    }

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
            if (p0 >= 0 && p0 < i4) {
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

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean r() {
        return this.f5082s == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void u(int i2, int i3, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        if (this.f5082s != 0) {
            i2 = i3;
        }
        if (P() == 0 || i2 == 0) {
            return;
        }
        c2();
        S2(i2 > 0 ? 1 : -1, Math.abs(i2), true, state);
        W1(state, this.t, layoutPrefetchRegistry);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void v(int i2, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean z;
        int i3;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.a()) {
            J2();
            z = this.x;
            i3 = this.A;
            if (i3 == -1) {
                i3 = z ? i2 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z = savedState2.f5107i;
            i3 = savedState2.f5105c;
        }
        int i4 = z ? -1 : 1;
        for (int i5 = 0; i5 < this.G && i3 >= 0 && i3 < i2; i5++) {
            layoutPrefetchRegistry.a(i3, 0);
            i3 += i4;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int w(RecyclerView.State state) {
        return X1(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int x(RecyclerView.State state) {
        return Y1(state);
    }

    protected int x2(RecyclerView.State state) {
        if (state.d()) {
            return this.u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int y(RecyclerView.State state) {
        return Z1(state);
    }

    public int y2() {
        return this.f5082s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int z(RecyclerView.State state) {
        return X1(state);
    }

    protected boolean z2() {
        return f0() == 1;
    }

    public LinearLayoutManager(Context context, int i2, boolean z) {
        this.f5082s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new AnchorInfo();
        this.F = new LayoutChunkResult();
        this.G = 2;
        this.H = new int[2];
        M2(i2);
        N2(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f5082s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new AnchorInfo();
        this.F = new LayoutChunkResult();
        this.G = 2;
        this.H = new int[2];
        RecyclerView.LayoutManager.Properties q0 = RecyclerView.LayoutManager.q0(context, attributeSet, i2, i3);
        M2(q0.f5187a);
        N2(q0.f5189c);
        O2(q0.f5190d);
    }
}
