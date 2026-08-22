package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.customview.widget.FocusStrategy;
import com.google.android.gms.common.api.Api;
import com.zte.distbus.basetransfer.Status;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {

    /* renamed from: n, reason: collision with root package name */
    private static final Rect f3584n = new Rect(Api.BaseClientBuilder.API_PRIORITY_OTHER, Api.BaseClientBuilder.API_PRIORITY_OTHER, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* renamed from: o, reason: collision with root package name */
    private static final FocusStrategy.BoundsAdapter f3585o = new FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>() { // from class: androidx.customview.widget.ExploreByTouchHelper.1
        @Override // androidx.customview.widget.FocusStrategy.BoundsAdapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
            accessibilityNodeInfoCompat.k(rect);
        }
    };

    /* renamed from: p, reason: collision with root package name */
    private static final FocusStrategy.CollectionAdapter f3586p = new FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() { // from class: androidx.customview.widget.ExploreByTouchHelper.2
        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public AccessibilityNodeInfoCompat a(SparseArrayCompat sparseArrayCompat, int i2) {
            return (AccessibilityNodeInfoCompat) sparseArrayCompat.k(i2);
        }

        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int b(SparseArrayCompat sparseArrayCompat) {
            return sparseArrayCompat.j();
        }
    };

    /* renamed from: h, reason: collision with root package name */
    private final AccessibilityManager f3591h;

    /* renamed from: i, reason: collision with root package name */
    private final View f3592i;

    /* renamed from: j, reason: collision with root package name */
    private MyNodeProvider f3593j;

    /* renamed from: d, reason: collision with root package name */
    private final Rect f3587d = new Rect();

    /* renamed from: e, reason: collision with root package name */
    private final Rect f3588e = new Rect();

    /* renamed from: f, reason: collision with root package name */
    private final Rect f3589f = new Rect();

    /* renamed from: g, reason: collision with root package name */
    private final int[] f3590g = new int[2];

    /* renamed from: k, reason: collision with root package name */
    int f3594k = Integer.MIN_VALUE;

    /* renamed from: l, reason: collision with root package name */
    int f3595l = Integer.MIN_VALUE;

    /* renamed from: m, reason: collision with root package name */
    private int f3596m = Integer.MIN_VALUE;

    private class MyNodeProvider extends AccessibilityNodeProviderCompat {
        MyNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat b(int i2) {
            return AccessibilityNodeInfoCompat.X(ExploreByTouchHelper.this.J(i2));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat d(int i2) {
            int i3 = i2 == 2 ? ExploreByTouchHelper.this.f3594k : ExploreByTouchHelper.this.f3595l;
            if (i3 == Integer.MIN_VALUE) {
                return null;
            }
            return b(i3);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean f(int i2, int i3, Bundle bundle) {
            return ExploreByTouchHelper.this.R(i2, i3, bundle);
        }
    }

    public ExploreByTouchHelper(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f3592i = view;
        this.f3591h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ViewCompat.t(view) == 0) {
            ViewCompat.s0(view, 1);
        }
    }

    private static Rect D(View view, int i2, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i2 == 17) {
            rect.set(width, 0, width, height);
        } else if (i2 == 33) {
            rect.set(0, height, width, height);
        } else if (i2 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean G(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f3592i.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.f3592i.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private static int H(int i2) {
        if (i2 == 19) {
            return 33;
        }
        if (i2 != 21) {
            return i2 != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean I(int i2, Rect rect) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        SparseArrayCompat y = y();
        int i3 = this.f3595l;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = i3 == Integer.MIN_VALUE ? null : (AccessibilityNodeInfoCompat) y.e(i3);
        if (i2 == 1 || i2 == 2) {
            accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) FocusStrategy.d(y, f3586p, f3585o, accessibilityNodeInfoCompat2, i2, ViewCompat.v(this.f3592i) == 1, false);
        } else {
            if (i2 != 17 && i2 != 33 && i2 != 66 && i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i4 = this.f3595l;
            if (i4 != Integer.MIN_VALUE) {
                z(i4, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                D(this.f3592i, i2, rect2);
            }
            accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) FocusStrategy.c(y, f3586p, f3585o, accessibilityNodeInfoCompat2, rect2, i2);
        }
        return V(accessibilityNodeInfoCompat != null ? y.h(y.g(accessibilityNodeInfoCompat)) : Integer.MIN_VALUE);
    }

    private boolean S(int i2, int i3, Bundle bundle) {
        return i3 != 1 ? i3 != 2 ? i3 != 64 ? i3 != 128 ? L(i2, i3, bundle) : n(i2) : U(i2) : o(i2) : V(i2);
    }

    private boolean T(int i2, Bundle bundle) {
        return ViewCompat.X(this.f3592i, i2, bundle);
    }

    private boolean U(int i2) {
        int i3;
        if (!this.f3591h.isEnabled() || !this.f3591h.isTouchExplorationEnabled() || (i3 = this.f3594k) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            n(i3);
        }
        this.f3594k = i2;
        this.f3592i.invalidate();
        W(i2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS);
        return true;
    }

    private void X(int i2) {
        int i3 = this.f3596m;
        if (i3 == i2) {
            return;
        }
        this.f3596m = i2;
        W(i2, 128);
        W(i3, 256);
    }

    private boolean n(int i2) {
        if (this.f3594k != i2) {
            return false;
        }
        this.f3594k = Integer.MIN_VALUE;
        this.f3592i.invalidate();
        W(i2, 65536);
        return true;
    }

    private boolean p() {
        int i2 = this.f3595l;
        return i2 != Integer.MIN_VALUE && L(i2, 16, null);
    }

    private AccessibilityEvent q(int i2, int i3) {
        return i2 != -1 ? r(i2, i3) : s(i3);
    }

    private AccessibilityEvent r(int i2, int i3) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i3);
        AccessibilityNodeInfoCompat J = J(i2);
        obtain.getText().add(J.y());
        obtain.setContentDescription(J.r());
        obtain.setScrollable(J.Q());
        obtain.setPassword(J.P());
        obtain.setEnabled(J.J());
        obtain.setChecked(J.G());
        N(i2, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(J.o());
        AccessibilityRecordCompat.c(obtain, this.f3592i, i2);
        obtain.setPackageName(this.f3592i.getContext().getPackageName());
        return obtain;
    }

    private AccessibilityEvent s(int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        this.f3592i.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    private AccessibilityNodeInfoCompat t(int i2) {
        AccessibilityNodeInfoCompat V = AccessibilityNodeInfoCompat.V();
        V.n0(true);
        V.p0(true);
        V.h0("android.view.View");
        Rect rect = f3584n;
        V.c0(rect);
        V.d0(rect);
        V.z0(this.f3592i);
        P(i2, V);
        if (V.y() == null && V.r() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        V.k(this.f3588e);
        if (this.f3588e.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int i3 = V.i();
        if ((i3 & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((i3 & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        V.x0(this.f3592i.getContext().getPackageName());
        V.I0(this.f3592i, i2);
        if (this.f3594k == i2) {
            V.b0(true);
            V.a(128);
        } else {
            V.b0(false);
            V.a(64);
        }
        boolean z = this.f3595l == i2;
        if (z) {
            V.a(2);
        } else if (V.K()) {
            V.a(1);
        }
        V.q0(z);
        this.f3592i.getLocationOnScreen(this.f3590g);
        V.l(this.f3587d);
        if (this.f3587d.equals(rect)) {
            V.k(this.f3587d);
            if (V.f3480b != -1) {
                AccessibilityNodeInfoCompat V2 = AccessibilityNodeInfoCompat.V();
                for (int i4 = V.f3480b; i4 != -1; i4 = V2.f3480b) {
                    V2.A0(this.f3592i, -1);
                    V2.c0(f3584n);
                    P(i4, V2);
                    V2.k(this.f3588e);
                    Rect rect2 = this.f3587d;
                    Rect rect3 = this.f3588e;
                    rect2.offset(rect3.left, rect3.top);
                }
                V2.Z();
            }
            this.f3587d.offset(this.f3590g[0] - this.f3592i.getScrollX(), this.f3590g[1] - this.f3592i.getScrollY());
        }
        if (this.f3592i.getLocalVisibleRect(this.f3589f)) {
            this.f3589f.offset(this.f3590g[0] - this.f3592i.getScrollX(), this.f3590g[1] - this.f3592i.getScrollY());
            if (this.f3587d.intersect(this.f3589f)) {
                V.d0(this.f3587d);
                if (G(this.f3587d)) {
                    V.M0(true);
                }
            }
        }
        return V;
    }

    private AccessibilityNodeInfoCompat u() {
        AccessibilityNodeInfoCompat W = AccessibilityNodeInfoCompat.W(this.f3592i);
        ViewCompat.V(this.f3592i, W);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (W.n() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            W.d(this.f3592i, ((Integer) arrayList.get(i2)).intValue());
        }
        return W;
    }

    private SparseArrayCompat y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            sparseArrayCompat.i(((Integer) arrayList.get(i2)).intValue(), t(((Integer) arrayList.get(i2)).intValue()));
        }
        return sparseArrayCompat;
    }

    private void z(int i2, Rect rect) {
        J(i2).k(rect);
    }

    public final int A() {
        return this.f3595l;
    }

    protected abstract int B(float f2, float f3);

    protected abstract void C(List list);

    public final void E(int i2) {
        F(i2, 0);
    }

    public final void F(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.f3591h.isEnabled() || (parent = this.f3592i.getParent()) == null) {
            return;
        }
        AccessibilityEvent q2 = q(i2, 2048);
        AccessibilityEventCompat.b(q2, i3);
        parent.requestSendAccessibilityEvent(this.f3592i, q2);
    }

    AccessibilityNodeInfoCompat J(int i2) {
        return i2 == -1 ? u() : t(i2);
    }

    public final void K(boolean z, int i2, Rect rect) {
        int i3 = this.f3595l;
        if (i3 != Integer.MIN_VALUE) {
            o(i3);
        }
        if (z) {
            I(i2, rect);
        }
    }

    protected abstract boolean L(int i2, int i3, Bundle bundle);

    protected void M(AccessibilityEvent accessibilityEvent) {
    }

    protected void N(int i2, AccessibilityEvent accessibilityEvent) {
    }

    protected void O(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    protected abstract void P(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    protected void Q(int i2, boolean z) {
    }

    boolean R(int i2, int i3, Bundle bundle) {
        return i2 != -1 ? S(i2, i3, bundle) : T(i3, bundle);
    }

    public final boolean V(int i2) {
        int i3;
        if ((!this.f3592i.isFocused() && !this.f3592i.requestFocus()) || (i3 = this.f3595l) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            o(i3);
        }
        if (i2 == Integer.MIN_VALUE) {
            return false;
        }
        this.f3595l = i2;
        Q(i2, true);
        W(i2, 8);
        return true;
    }

    public final boolean W(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.f3591h.isEnabled() || (parent = this.f3592i.getParent()) == null) {
            return false;
        }
        return parent.requestSendAccessibilityEvent(this.f3592i, q(i2, i3));
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat b(View view) {
        if (this.f3593j == null) {
            this.f3593j = new MyNodeProvider();
        }
        return this.f3593j;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        M(accessibilityEvent);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.g(view, accessibilityNodeInfoCompat);
        O(accessibilityNodeInfoCompat);
    }

    public final boolean o(int i2) {
        if (this.f3595l != i2) {
            return false;
        }
        this.f3595l = Integer.MIN_VALUE;
        Q(i2, false);
        W(i2, 8);
        return true;
    }

    public final boolean v(MotionEvent motionEvent) {
        if (!this.f3591h.isEnabled() || !this.f3591h.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int B = B(motionEvent.getX(), motionEvent.getY());
            X(B);
            return B != Integer.MIN_VALUE;
        }
        if (action != 10 || this.f3596m == Integer.MIN_VALUE) {
            return false;
        }
        X(Integer.MIN_VALUE);
        return true;
    }

    public final boolean w(KeyEvent keyEvent) {
        int i2 = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return I(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return I(1, null);
            }
            return false;
        }
        if (keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                case 22:
                    if (!keyEvent.hasNoModifiers()) {
                        return false;
                    }
                    int H = H(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z = false;
                    while (i2 < repeatCount && I(H, null)) {
                        i2++;
                        z = true;
                    }
                    return z;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
            return false;
        }
        p();
        return true;
    }

    public final int x() {
        return this.f3594k;
    }
}
