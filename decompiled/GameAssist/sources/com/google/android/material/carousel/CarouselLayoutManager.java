package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Api;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CarouselLayoutManager extends RecyclerView.LayoutManager implements Carousel, RecyclerView.SmoothScroller.ScrollVectorProvider {
    private final View.OnLayoutChangeListener A;
    private int B;
    private int C;
    private int D;

    @VisibleForTesting
    int maxScroll;

    @VisibleForTesting
    int minScroll;

    /* renamed from: s, reason: collision with root package name */
    private boolean f14134s;

    @VisibleForTesting
    int scrollOffset;
    private final DebugItemDecoration t;
    private CarouselStrategy u;
    private KeylineStateList v;
    private KeylineState w;
    private int x;
    private Map y;
    private CarouselOrientationHelper z;

    private static final class ChildCalculations {

        /* renamed from: a, reason: collision with root package name */
        final View f14136a;

        /* renamed from: b, reason: collision with root package name */
        final float f14137b;

        /* renamed from: c, reason: collision with root package name */
        final float f14138c;

        /* renamed from: d, reason: collision with root package name */
        final KeylineRange f14139d;

        ChildCalculations(View view, float f2, float f3, KeylineRange keylineRange) {
            this.f14136a = view;
            this.f14137b = f2;
            this.f14138c = f3;
            this.f14139d = keylineRange;
        }
    }

    private static class DebugItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private final Paint f14140a;

        /* renamed from: b, reason: collision with root package name */
        private List f14141b;

        DebugItemDecoration() {
            Paint paint = new Paint();
            this.f14140a = paint;
            this.f14141b = Collections.unmodifiableList(new ArrayList());
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        void c(List list) {
            this.f14141b = Collections.unmodifiableList(list);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            this.f14140a.setStrokeWidth(recyclerView.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width));
            for (KeylineState.Keyline keyline : this.f14141b) {
                this.f14140a.setColor(ColorUtils.c(-65281, -16776961, keyline.f14171c));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).g()) {
                    canvas.drawLine(keyline.f14170b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).G2(), keyline.f14170b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).B2(), this.f14140a);
                } else {
                    canvas.drawLine(((CarouselLayoutManager) recyclerView.getLayoutManager()).D2(), keyline.f14170b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).E2(), keyline.f14170b, this.f14140a);
                }
            }
        }
    }

    private static class KeylineRange {

        /* renamed from: a, reason: collision with root package name */
        final KeylineState.Keyline f14142a;

        /* renamed from: b, reason: collision with root package name */
        final KeylineState.Keyline f14143b;

        KeylineRange(KeylineState.Keyline keyline, KeylineState.Keyline keyline2) {
            Preconditions.a(keyline.f14169a <= keyline2.f14169a);
            this.f14142a = keyline;
            this.f14143b = keyline2;
        }
    }

    private static class LayoutDirection {
    }

    public CarouselLayoutManager() {
        this(new MultiBrowseCarouselStrategy());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int B2() {
        return this.z.g();
    }

    private int C2() {
        return this.z.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int D2() {
        return this.z.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int E2() {
        return this.z.j();
    }

    private int F2() {
        return this.z.k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int G2() {
        return this.z.l();
    }

    private int H2() {
        if (S() || !this.u.f()) {
            return 0;
        }
        return A2() == 1 ? j0() : m0();
    }

    private int I2(int i2, KeylineState keylineState) {
        return (int) (L2() ? ((s2() - keylineState.h().f14169a) - (i2 * keylineState.f())) - (keylineState.f() / 2.0f) : ((i2 * keylineState.f()) - keylineState.a().f14169a) + (keylineState.f() / 2.0f));
    }

    private int J2(int i2, KeylineState keylineState) {
        int i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        for (KeylineState.Keyline keyline : keylineState.e()) {
            float f2 = (i2 * keylineState.f()) + (keylineState.f() / 2.0f);
            int s2 = (L2() ? (int) ((s2() - keyline.f14169a) - f2) : (int) (f2 - keyline.f14169a)) - this.scrollOffset;
            if (Math.abs(i3) > Math.abs(s2)) {
                i3 = s2;
            }
        }
        return i3;
    }

    private static KeylineRange K2(List list, float f2, boolean z) {
        float f3 = Float.MAX_VALUE;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        float f4 = -3.4028235E38f;
        float f5 = Float.MAX_VALUE;
        float f6 = Float.MAX_VALUE;
        for (int i6 = 0; i6 < list.size(); i6++) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) list.get(i6);
            float f7 = z ? keyline.f14170b : keyline.f14169a;
            float abs = Math.abs(f7 - f2);
            if (f7 <= f2 && abs <= f3) {
                i2 = i6;
                f3 = abs;
            }
            if (f7 > f2 && abs <= f5) {
                i4 = i6;
                f5 = abs;
            }
            if (f7 <= f6) {
                i3 = i6;
                f6 = f7;
            }
            if (f7 > f4) {
                i5 = i6;
                f4 = f7;
            }
        }
        if (i2 == -1) {
            i2 = i3;
        }
        if (i4 == -1) {
            i4 = i5;
        }
        return new KeylineRange((KeylineState.Keyline) list.get(i2), (KeylineState.Keyline) list.get(i4));
    }

    private boolean M2(float f2, KeylineRange keylineRange) {
        float e2 = e2(f2, x2(f2, keylineRange) / 2.0f);
        if (L2()) {
            if (e2 >= 0.0f) {
                return false;
            }
        } else if (e2 <= s2()) {
            return false;
        }
        return true;
    }

    private boolean N2(float f2, KeylineRange keylineRange) {
        float d2 = d2(f2, x2(f2, keylineRange) / 2.0f);
        if (L2()) {
            if (d2 <= s2()) {
                return false;
            }
        } else if (d2 >= 0.0f) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O2(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (i2 == i6 && i3 == i7 && i4 == i8 && i5 == i9) {
            return;
        }
        view.post(new Runnable() { // from class: com.google.android.material.carousel.b
            @Override // java.lang.Runnable
            public final void run() {
                CarouselLayoutManager.this.T2();
            }
        });
    }

    private void P2() {
        if (this.f14134s && Log.isLoggable("CarouselLayoutManager", 3)) {
            Log.d("CarouselLayoutManager", "internal representation of views on the screen");
            for (int i2 = 0; i2 < P(); i2++) {
                View O = O(i2);
                Log.d("CarouselLayoutManager", "item position " + p0(O) + ", center:" + t2(O) + ", child index:" + i2);
            }
            Log.d("CarouselLayoutManager", "==============");
        }
    }

    private ChildCalculations Q2(RecyclerView.Recycler recycler, float f2, int i2) {
        View o2 = recycler.o(i2);
        J0(o2, 0, 0);
        float d2 = d2(f2, this.w.f() / 2.0f);
        KeylineRange K2 = K2(this.w.g(), d2, false);
        return new ChildCalculations(o2, d2, i2(o2, d2, K2), K2);
    }

    private float R2(View view, float f2, float f3, Rect rect) {
        float d2 = d2(f2, f3);
        KeylineRange K2 = K2(this.w.g(), d2, false);
        float i2 = i2(view, d2, K2);
        super.V(view, rect);
        b3(view, d2, K2);
        this.z.o(view, rect, f3, i2);
        return i2;
    }

    private void S2(RecyclerView.Recycler recycler) {
        View o2 = recycler.o(0);
        J0(o2, 0, 0);
        KeylineState g2 = this.u.g(this, o2);
        if (L2()) {
            g2 = KeylineState.n(g2, s2());
        }
        this.v = KeylineStateList.f(this, g2, u2(), w2(), H2());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T2() {
        this.v = null;
        C1();
    }

    private void U2(RecyclerView.Recycler recycler) {
        while (P() > 0) {
            View O = O(0);
            float t2 = t2(O);
            if (!N2(t2, K2(this.w.g(), t2, true))) {
                break;
            } else {
                v1(O, recycler);
            }
        }
        while (P() - 1 >= 0) {
            View O2 = O(P() - 1);
            float t22 = t2(O2);
            if (!M2(t22, K2(this.w.g(), t22, true))) {
                return;
            } else {
                v1(O2, recycler);
            }
        }
    }

    private int V2(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (P() == 0 || i2 == 0) {
            return 0;
        }
        if (this.v == null) {
            S2(recycler);
        }
        int m2 = m2(i2, this.scrollOffset, this.minScroll, this.maxScroll);
        this.scrollOffset += m2;
        c3(this.v);
        float f2 = this.w.f() / 2.0f;
        float j2 = j2(p0(O(0)));
        Rect rect = new Rect();
        float f3 = L2() ? this.w.h().f14170b : this.w.a().f14170b;
        float f4 = Float.MAX_VALUE;
        for (int i3 = 0; i3 < P(); i3++) {
            View O = O(i3);
            float abs = Math.abs(f3 - R2(O, j2, f2, rect));
            if (O != null && abs < f4) {
                this.C = p0(O);
                f4 = abs;
            }
            j2 = d2(j2, this.w.f());
        }
        p2(recycler, state);
        return m2;
    }

    private void W2(RecyclerView recyclerView, int i2) {
        if (g()) {
            recyclerView.scrollBy(i2, 0);
        } else {
            recyclerView.scrollBy(0, i2);
        }
    }

    private void Y2(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Carousel);
            X2(obtainStyledAttributes.getInt(R.styleable.Carousel_carousel_alignment, 0));
            a3(obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 0));
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b3(View view, float f2, KeylineRange keylineRange) {
        if (view instanceof Maskable) {
            KeylineState.Keyline keyline = keylineRange.f14142a;
            float f3 = keyline.f14171c;
            KeylineState.Keyline keyline2 = keylineRange.f14143b;
            float b2 = AnimationUtils.b(f3, keyline2.f14171c, keyline.f14169a, keyline2.f14169a, f2);
            float height = view.getHeight();
            float width = view.getWidth();
            RectF f4 = this.z.f(height, width, AnimationUtils.b(0.0f, height / 2.0f, 0.0f, 1.0f, b2), AnimationUtils.b(0.0f, width / 2.0f, 0.0f, 1.0f, b2));
            float i2 = i2(view, f2, keylineRange);
            RectF rectF = new RectF(i2 - (f4.width() / 2.0f), i2 - (f4.height() / 2.0f), i2 + (f4.width() / 2.0f), (f4.height() / 2.0f) + i2);
            RectF rectF2 = new RectF(D2(), G2(), E2(), B2());
            if (this.u.f()) {
                this.z.a(f4, rectF, rectF2);
            }
            this.z.n(f4, rectF, rectF2);
            ((Maskable) view).setMaskRectF(f4);
        }
    }

    private void c2(View view, int i2, ChildCalculations childCalculations) {
        float f2 = this.w.f() / 2.0f;
        k(view, i2);
        float f3 = childCalculations.f14138c;
        this.z.m(view, (int) (f3 - f2), (int) (f3 + f2));
        b3(view, childCalculations.f14137b, childCalculations.f14139d);
    }

    private void c3(KeylineStateList keylineStateList) {
        int i2 = this.maxScroll;
        int i3 = this.minScroll;
        if (i2 <= i3) {
            this.w = L2() ? keylineStateList.h() : keylineStateList.l();
        } else {
            this.w = keylineStateList.j(this.scrollOffset, i3, i2);
        }
        this.t.c(this.w.g());
    }

    private float d2(float f2, float f3) {
        return L2() ? f2 - f3 : f2 + f3;
    }

    private void d3() {
        int f2 = f();
        int i2 = this.B;
        if (f2 == i2 || this.v == null) {
            return;
        }
        if (this.u.h(this, i2)) {
            T2();
        }
        this.B = f2;
    }

    private float e2(float f2, float f3) {
        return L2() ? f2 + f3 : f2 - f3;
    }

    private void e3() {
        if (!this.f14134s || P() < 1) {
            return;
        }
        int i2 = 0;
        while (i2 < P() - 1) {
            int p0 = p0(O(i2));
            int i3 = i2 + 1;
            int p02 = p0(O(i3));
            if (p0 > p02) {
                P2();
                throw new IllegalStateException("Detected invalid child order. Child at index [" + i2 + "] had adapter position [" + p0 + "] and child at index [" + i3 + "] had adapter position [" + p02 + "].");
            }
            i2 = i3;
        }
    }

    private void f2(RecyclerView.Recycler recycler, int i2, int i3) {
        if (i2 < 0 || i2 >= f()) {
            return;
        }
        ChildCalculations Q2 = Q2(recycler, j2(i2), i2);
        c2(Q2.f14136a, i3, Q2);
    }

    private void g2(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        float j2 = j2(i2);
        while (i2 < state.b()) {
            ChildCalculations Q2 = Q2(recycler, j2, i2);
            if (M2(Q2.f14138c, Q2.f14139d)) {
                return;
            }
            j2 = d2(j2, this.w.f());
            if (!N2(Q2.f14138c, Q2.f14139d)) {
                c2(Q2.f14136a, -1, Q2);
            }
            i2++;
        }
    }

    private void h2(RecyclerView.Recycler recycler, int i2) {
        float j2 = j2(i2);
        while (i2 >= 0) {
            ChildCalculations Q2 = Q2(recycler, j2, i2);
            if (N2(Q2.f14138c, Q2.f14139d)) {
                return;
            }
            j2 = e2(j2, this.w.f());
            if (!M2(Q2.f14138c, Q2.f14139d)) {
                c2(Q2.f14136a, 0, Q2);
            }
            i2--;
        }
    }

    private float i2(View view, float f2, KeylineRange keylineRange) {
        KeylineState.Keyline keyline = keylineRange.f14142a;
        float f3 = keyline.f14170b;
        KeylineState.Keyline keyline2 = keylineRange.f14143b;
        float b2 = AnimationUtils.b(f3, keyline2.f14170b, keyline.f14169a, keyline2.f14169a, f2);
        if (keylineRange.f14143b != this.w.c() && keylineRange.f14142a != this.w.j()) {
            return b2;
        }
        float e2 = this.z.e((RecyclerView.LayoutParams) view.getLayoutParams()) / this.w.f();
        KeylineState.Keyline keyline3 = keylineRange.f14143b;
        return b2 + ((f2 - keyline3.f14169a) * ((1.0f - keyline3.f14171c) + e2));
    }

    private float j2(int i2) {
        return d2(F2() - this.scrollOffset, this.w.f() * i2);
    }

    private int k2(RecyclerView.State state, KeylineStateList keylineStateList) {
        boolean L2 = L2();
        KeylineState l2 = L2 ? keylineStateList.l() : keylineStateList.h();
        KeylineState.Keyline a2 = L2 ? l2.a() : l2.h();
        int b2 = (int) (((((state.b() - 1) * l2.f()) * (L2 ? -1.0f : 1.0f)) - (a2.f14169a - F2())) + (C2() - a2.f14169a) + (L2 ? -a2.f14175g : a2.f14176h));
        return L2 ? Math.min(0, b2) : Math.max(0, b2);
    }

    private static int m2(int i2, int i3, int i4, int i5) {
        int i6 = i3 + i2;
        return i6 < i4 ? i4 - i3 : i6 > i5 ? i5 - i3 : i2;
    }

    private int n2(KeylineStateList keylineStateList) {
        boolean L2 = L2();
        KeylineState h2 = L2 ? keylineStateList.h() : keylineStateList.l();
        return (int) (F2() - e2((L2 ? h2.h() : h2.a()).f14169a, h2.f() / 2.0f));
    }

    private int o2(int i2) {
        int A2 = A2();
        if (i2 == 1) {
            return -1;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 17) {
            if (A2 == 0) {
                return L2() ? 1 : -1;
            }
            return Integer.MIN_VALUE;
        }
        if (i2 == 33) {
            return A2 == 1 ? -1 : Integer.MIN_VALUE;
        }
        if (i2 == 66) {
            if (A2 == 0) {
                return L2() ? -1 : 1;
            }
            return Integer.MIN_VALUE;
        }
        if (i2 == 130) {
            return A2 == 1 ? 1 : Integer.MIN_VALUE;
        }
        Log.d("CarouselLayoutManager", "Unknown focus request:" + i2);
        return Integer.MIN_VALUE;
    }

    private void p2(RecyclerView.Recycler recycler, RecyclerView.State state) {
        U2(recycler);
        if (P() == 0) {
            h2(recycler, this.x - 1);
            g2(recycler, state, this.x);
        } else {
            int p0 = p0(O(0));
            int p02 = p0(O(P() - 1));
            h2(recycler, p0 - 1);
            g2(recycler, state, p02 + 1);
        }
        e3();
    }

    private View q2() {
        return O(L2() ? 0 : P() - 1);
    }

    private View r2() {
        return O(L2() ? P() - 1 : 0);
    }

    private int s2() {
        return g() ? a() : b();
    }

    private float t2(View view) {
        super.V(view, new Rect());
        return g() ? r0.centerX() : r0.centerY();
    }

    private int u2() {
        int i2;
        int i3;
        if (P() <= 0) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) O(0).getLayoutParams();
        if (this.z.f14144a == 0) {
            i2 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            i3 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        } else {
            i2 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            i3 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return i2 + i3;
    }

    private KeylineState v2(int i2) {
        KeylineState keylineState;
        Map map = this.y;
        return (map == null || (keylineState = (KeylineState) map.get(Integer.valueOf(MathUtils.b(i2, 0, Math.max(0, f() + (-1)))))) == null) ? this.v.g() : keylineState;
    }

    private int w2() {
        if (S() || !this.u.f()) {
            return 0;
        }
        return A2() == 1 ? o0() : l0();
    }

    private float x2(float f2, KeylineRange keylineRange) {
        KeylineState.Keyline keyline = keylineRange.f14142a;
        float f3 = keyline.f14172d;
        KeylineState.Keyline keyline2 = keylineRange.f14143b;
        return AnimationUtils.b(f3, keyline2.f14172d, keyline.f14170b, keyline2.f14170b, f2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int A(RecyclerView.State state) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean A0() {
        return true;
    }

    public int A2() {
        return this.z.f14144a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int B(RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean B1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        int J2;
        if (this.v == null || (J2 = J2(p0(view), v2(p0(view)))) == 0) {
            return false;
        }
        W2(recyclerView, J2(p0(view), this.v.j(this.scrollOffset + m2(J2, this.scrollOffset, this.minScroll, this.maxScroll), this.minScroll, this.maxScroll)));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int F1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (q()) {
            return V2(i2, recycler, state);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void G1(int i2) {
        this.C = i2;
        if (this.v == null) {
            return;
        }
        this.scrollOffset = I2(i2, v2(i2));
        this.x = MathUtils.b(i2, 0, Math.max(0, f() - 1));
        c3(this.v);
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int H1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (r()) {
            return V2(i2, recycler, state);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams J() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void J0(View view, int i2, int i3) {
        if (!(view instanceof Maskable)) {
            throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        Rect rect = new Rect();
        p(view, rect);
        int i4 = i2 + rect.left + rect.right;
        int i5 = i3 + rect.top + rect.bottom;
        KeylineStateList keylineStateList = this.v;
        float f2 = (keylineStateList == null || this.z.f14144a != 0) ? ((ViewGroup.MarginLayoutParams) layoutParams).width : keylineStateList.g().f();
        KeylineStateList keylineStateList2 = this.v;
        view.measure(RecyclerView.LayoutManager.Q(w0(), x0(), l0() + m0() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i4, (int) f2, q()), RecyclerView.LayoutManager.Q(c0(), d0(), o0() + j0() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i5, (int) ((keylineStateList2 == null || this.z.f14144a != 1) ? ((ViewGroup.MarginLayoutParams) layoutParams).height : keylineStateList2.g().f()), r()));
    }

    boolean L2() {
        return g() && f0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void P0(RecyclerView recyclerView) {
        super.P0(recyclerView);
        this.u.e(recyclerView.getContext());
        T2();
        recyclerView.addOnLayoutChangeListener(this.A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void R0(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.R0(recyclerView, recycler);
        recyclerView.removeOnLayoutChangeListener(this.A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void R1(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) { // from class: com.google.android.material.carousel.CarouselLayoutManager.1
            @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public PointF a(int i3) {
                return CarouselLayoutManager.this.c(i3);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int t(View view, int i3) {
                if (CarouselLayoutManager.this.v == null || !CarouselLayoutManager.this.g()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.l2(carouselLayoutManager.p0(view));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int u(View view, int i3) {
                if (CarouselLayoutManager.this.v == null || CarouselLayoutManager.this.g()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.l2(carouselLayoutManager.p0(view));
            }
        };
        linearSmoothScroller.p(i2);
        S1(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View S0(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int o2;
        if (P() == 0 || (o2 = o2(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        if (o2 == -1) {
            if (p0(view) == 0) {
                return null;
            }
            f2(recycler, p0(O(0)) - 1, 0);
            return r2();
        }
        if (p0(view) == f() - 1) {
            return null;
        }
        f2(recycler, p0(O(P() - 1)) + 1, -1);
        return q2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void T0(AccessibilityEvent accessibilityEvent) {
        super.T0(accessibilityEvent);
        if (P() > 0) {
            accessibilityEvent.setFromIndex(p0(O(0)));
            accessibilityEvent.setToIndex(p0(O(P() - 1)));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void V(View view, Rect rect) {
        super.V(view, rect);
        float centerY = rect.centerY();
        if (g()) {
            centerY = rect.centerX();
        }
        float x2 = x2(centerY, K2(this.w.g(), centerY, true));
        float width = g() ? (rect.width() - x2) / 2.0f : 0.0f;
        float height = g() ? 0.0f : (rect.height() - x2) / 2.0f;
        rect.set((int) (rect.left + width), (int) (rect.top + height), (int) (rect.right - width), (int) (rect.bottom - height));
    }

    public void X2(int i2) {
        this.D = i2;
        T2();
    }

    public void Z2(CarouselStrategy carouselStrategy) {
        this.u = carouselStrategy;
        T2();
    }

    @Override // com.google.android.material.carousel.Carousel
    public int a() {
        return w0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void a1(RecyclerView recyclerView, int i2, int i3) {
        super.a1(recyclerView, i2, i3);
        d3();
    }

    public void a3(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        m(null);
        CarouselOrientationHelper carouselOrientationHelper = this.z;
        if (carouselOrientationHelper == null || i2 != carouselOrientationHelper.f14144a) {
            this.z = CarouselOrientationHelper.c(this, i2);
            T2();
        }
    }

    @Override // com.google.android.material.carousel.Carousel
    public int b() {
        return c0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF c(int i2) {
        if (this.v == null) {
            return null;
        }
        int y2 = y2(i2, v2(i2));
        return g() ? new PointF(y2, 0.0f) : new PointF(0.0f, y2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void d1(RecyclerView recyclerView, int i2, int i3) {
        super.d1(recyclerView, i2, i3);
        d3();
    }

    @Override // com.google.android.material.carousel.Carousel
    public int e() {
        return this.D;
    }

    @Override // com.google.android.material.carousel.Carousel
    public boolean g() {
        return this.z.f14144a == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.b() <= 0 || s2() <= 0.0f) {
            t1(recycler);
            this.x = 0;
            return;
        }
        boolean L2 = L2();
        boolean z = this.v == null;
        if (z) {
            S2(recycler);
        }
        int n2 = n2(this.v);
        int k2 = k2(state, this.v);
        this.minScroll = L2 ? k2 : n2;
        if (L2) {
            k2 = n2;
        }
        this.maxScroll = k2;
        if (z) {
            this.scrollOffset = n2;
            this.y = this.v.i(f(), this.minScroll, this.maxScroll, L2());
            int i2 = this.C;
            if (i2 != -1) {
                this.scrollOffset = I2(i2, v2(i2));
            }
        }
        int i3 = this.scrollOffset;
        this.scrollOffset = i3 + m2(0, i3, this.minScroll, this.maxScroll);
        this.x = MathUtils.b(this.x, 0, state.b());
        c3(this.v);
        C(recycler);
        p2(recycler, state);
        this.B = f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void h1(RecyclerView.State state) {
        super.h1(state);
        if (P() == 0) {
            this.x = 0;
        } else {
            this.x = p0(O(0));
        }
        e3();
    }

    int l2(int i2) {
        return (int) (this.scrollOffset - I2(i2, v2(i2)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean q() {
        return g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean r() {
        return !g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int w(RecyclerView.State state) {
        if (P() == 0 || this.v == null || f() <= 1) {
            return 0;
        }
        return (int) (w0() * (this.v.g().f() / y(state)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int x(RecyclerView.State state) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int y(RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    int y2(int i2, KeylineState keylineState) {
        return I2(i2, keylineState) - this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int z(RecyclerView.State state) {
        if (P() == 0 || this.v == null || f() <= 1) {
            return 0;
        }
        return (int) (c0() * (this.v.g().f() / B(state)));
    }

    int z2(int i2, boolean z) {
        int y2 = y2(i2, this.v.k(this.scrollOffset, this.minScroll, this.maxScroll, true));
        int y22 = this.y != null ? y2(i2, v2(i2)) : y2;
        return (!z || Math.abs(y22) >= Math.abs(y2)) ? y2 : y22;
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy) {
        this(carouselStrategy, 0);
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy, int i2) {
        this.f14134s = false;
        this.t = new DebugItemDecoration();
        this.x = 0;
        this.A = new View.OnLayoutChangeListener() { // from class: com.google.android.material.carousel.a
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                CarouselLayoutManager.this.O2(view, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.C = -1;
        this.D = 0;
        Z2(carouselStrategy);
        a3(i2);
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f14134s = false;
        this.t = new DebugItemDecoration();
        this.x = 0;
        this.A = new View.OnLayoutChangeListener() { // from class: com.google.android.material.carousel.a
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i32, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                CarouselLayoutManager.this.O2(view, i32, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.C = -1;
        this.D = 0;
        Z2(new MultiBrowseCarouselStrategy());
        Y2(context, attributeSet);
    }
}
