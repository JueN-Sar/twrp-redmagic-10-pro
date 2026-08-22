package com.google.android.material.bottomsheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBottomContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements MaterialBackHandler {

    @VisibleForTesting
    static final int DEFAULT_SIGNIFICANT_VEL_THRESHOLD = 500;

    @VisibleForTesting
    static final int VIEW_INDEX_ACCESSIBILITY_DELEGATE_VIEW = 1;
    private static final int n0 = R.style.Widget_Design_BottomSheet_Modal;
    private boolean A;
    private boolean B;
    private int C;
    private int D;
    private boolean E;
    private ShapeAppearanceModel F;
    private boolean G;
    private final StateSettlingTracker H;
    private ValueAnimator I;
    int J;
    int K;
    int L;
    float M;
    int N;
    float O;
    boolean P;
    private boolean Q;
    private boolean R;
    int S;
    int T;
    ViewDragHelper U;
    private boolean V;
    private int W;
    private boolean X;
    private float Y;
    private int Z;
    int a0;
    int b0;

    /* renamed from: c, reason: collision with root package name */
    private int f14016c;
    WeakReference c0;
    WeakReference d0;
    WeakReference e0;

    @VisibleForTesting
    final SparseIntArray expandHalfwayActionIds;
    private final ArrayList f0;
    private VelocityTracker g0;

    /* renamed from: h, reason: collision with root package name */
    private boolean f14017h;
    MaterialBottomContainerBackHelper h0;

    /* renamed from: i, reason: collision with root package name */
    private boolean f14018i;
    int i0;

    /* renamed from: j, reason: collision with root package name */
    private float f14019j;
    private int j0;

    /* renamed from: k, reason: collision with root package name */
    private int f14020k;
    boolean k0;

    /* renamed from: l, reason: collision with root package name */
    private int f14021l;
    private Map l0;

    /* renamed from: m, reason: collision with root package name */
    private boolean f14022m;
    private final ViewDragHelper.Callback m0;

    /* renamed from: n, reason: collision with root package name */
    private int f14023n;

    /* renamed from: o, reason: collision with root package name */
    private int f14024o;

    /* renamed from: p, reason: collision with root package name */
    private MaterialShapeDrawable f14025p;

    /* renamed from: q, reason: collision with root package name */
    private ColorStateList f14026q;

    /* renamed from: r, reason: collision with root package name */
    private int f14027r;

    /* renamed from: s, reason: collision with root package name */
    private int f14028s;
    private int t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public static abstract class BottomSheetCallback {
        void a(View view) {
        }

        public abstract void b(View view, float f2);

        public abstract void c(View view, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface SaveFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface StableState {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface State {
    }

    private class StateSettlingTracker {

        /* renamed from: a, reason: collision with root package name */
        private int f14045a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f14046b;

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f14047c;

        private StateSettlingTracker() {
            this.f14047c = new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.StateSettlingTracker.1
                @Override // java.lang.Runnable
                public void run() {
                    StateSettlingTracker.this.f14046b = false;
                    ViewDragHelper viewDragHelper = BottomSheetBehavior.this.U;
                    if (viewDragHelper != null && viewDragHelper.n(true)) {
                        StateSettlingTracker stateSettlingTracker = StateSettlingTracker.this;
                        stateSettlingTracker.c(stateSettlingTracker.f14045a);
                        return;
                    }
                    StateSettlingTracker stateSettlingTracker2 = StateSettlingTracker.this;
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.S == 2) {
                        bottomSheetBehavior.X0(stateSettlingTracker2.f14045a);
                    }
                }
            };
        }

        void c(int i2) {
            WeakReference weakReference = BottomSheetBehavior.this.c0;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f14045a = i2;
            if (this.f14046b) {
                return;
            }
            ViewCompat.a0((View) BottomSheetBehavior.this.c0.get(), this.f14047c);
            this.f14046b = true;
        }
    }

    public BottomSheetBehavior() {
        this.f14016c = 0;
        this.f14017h = true;
        this.f14018i = false;
        this.f14027r = -1;
        this.f14028s = -1;
        this.H = new StateSettlingTracker();
        this.M = 0.5f;
        this.O = -1.0f;
        this.R = true;
        this.S = 4;
        this.T = 4;
        this.Y = 0.1f;
        this.f0 = new ArrayList();
        this.j0 = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.m0 = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5

            /* renamed from: a, reason: collision with root package name */
            private long f14036a;

            private boolean n(View view) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return top > (bottomSheetBehavior.b0 + bottomSheetBehavior.s0()) / 2;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int a(View view, int i2, int i3) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int b(View view, int i2, int i3) {
                return MathUtils.b(i2, BottomSheetBehavior.this.s0(), e(view));
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int e(View view) {
                return BottomSheetBehavior.this.k0() ? BottomSheetBehavior.this.b0 : BottomSheetBehavior.this.N;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void j(int i2) {
                if (i2 == 1 && BottomSheetBehavior.this.R) {
                    BottomSheetBehavior.this.X0(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void k(View view, int i2, int i3, int i4, int i5) {
                BottomSheetBehavior.this.p0(i3);
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:
            
                if (r7.f14037b.Z0(r0, (r9 * 100.0f) / r10.b0) != false) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x003b, code lost:
            
                if (r9 > r7.f14037b.L) goto L63;
             */
            /* JADX WARN: Code restructure failed: missing block: B:30:0x0090, code lost:
            
                if (java.lang.Math.abs(r8.getTop() - r7.f14037b.s0()) < java.lang.Math.abs(r8.getTop() - r7.f14037b.L)) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:40:0x00cf, code lost:
            
                if (r7.f14037b.c1() == false) goto L63;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00f1, code lost:
            
                if (java.lang.Math.abs(r9 - r7.f14037b.K) < java.lang.Math.abs(r9 - r7.f14037b.N)) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:50:0x010d, code lost:
            
                if (r7.f14037b.c1() != false) goto L39;
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x0127, code lost:
            
                if (r7.f14037b.c1() == false) goto L63;
             */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void l(android.view.View r8, float r9, float r10) {
                /*
                    Method dump skipped, instructions count: 308
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass5.l(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean m(View view, int i2) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i3 = bottomSheetBehavior.S;
                if (i3 == 1 || bottomSheetBehavior.k0) {
                    return false;
                }
                if (i3 == 3 && bottomSheetBehavior.i0 == i2) {
                    WeakReference weakReference = bottomSheetBehavior.e0;
                    View view2 = weakReference != null ? (View) weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                this.f14036a = System.currentTimeMillis();
                WeakReference weakReference2 = BottomSheetBehavior.this.c0;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
    }

    private boolean C0(View view) {
        ViewParent parent = view.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.M(view);
    }

    private void F0(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.e0(view, accessibilityActionCompat, null, m0(i2));
    }

    private void G0() {
        this.i0 = -1;
        this.j0 = -1;
        VelocityTracker velocityTracker = this.g0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.g0 = null;
        }
    }

    private void H0(SavedState savedState) {
        int i2 = this.f14016c;
        if (i2 == 0) {
            return;
        }
        if (i2 == -1 || (i2 & 1) == 1) {
            this.f14021l = savedState.f14041j;
        }
        if (i2 == -1 || (i2 & 2) == 2) {
            this.f14017h = savedState.f14042k;
        }
        if (i2 == -1 || (i2 & 4) == 4) {
            this.P = savedState.f14043l;
        }
        if (i2 == -1 || (i2 & 8) == 8) {
            this.Q = savedState.f14044m;
        }
    }

    private void I0(View view, Runnable runnable) {
        if (C0(view)) {
            view.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void Y0(View view) {
        final boolean z = (z0() || this.f14022m) ? false : true;
        if (this.v || this.w || this.x || this.z || this.A || this.B || z) {
            ViewUtils.g(view, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.4
                /* JADX WARN: Code restructure failed: missing block: B:42:0x00a4, code lost:
                
                    if (r6 != false) goto L35;
                 */
                /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
                /* JADX WARN: Removed duplicated region for block: B:28:0x009b  */
                @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public androidx.core.view.WindowInsetsCompat a(android.view.View r11, androidx.core.view.WindowInsetsCompat r12, com.google.android.material.internal.ViewUtils.RelativePadding r13) {
                    /*
                        r10 = this;
                        int r0 = androidx.core.view.WindowInsetsCompat.Type.e()
                        androidx.core.graphics.Insets r0 = r12.f(r0)
                        int r1 = androidx.core.view.WindowInsetsCompat.Type.c()
                        androidx.core.graphics.Insets r1 = r12.f(r1)
                        com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r3 = r0.f2921b
                        com.google.android.material.bottomsheet.BottomSheetBehavior.U(r2, r3)
                        boolean r2 = com.google.android.material.internal.ViewUtils.p(r11)
                        int r3 = r11.getPaddingBottom()
                        int r4 = r11.getPaddingLeft()
                        int r5 = r11.getPaddingRight()
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.V(r6)
                        if (r6 == 0) goto L41
                        com.google.android.material.bottomsheet.BottomSheetBehavior r3 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r6 = r12.i()
                        com.google.android.material.bottomsheet.BottomSheetBehavior.X(r3, r6)
                        int r3 = r13.f14804d
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.W(r6)
                        int r3 = r3 + r6
                    L41:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.Y(r6)
                        if (r6 == 0) goto L53
                        if (r2 == 0) goto L4e
                        int r4 = r13.f14803c
                        goto L50
                    L4e:
                        int r4 = r13.f14801a
                    L50:
                        int r6 = r0.f2920a
                        int r4 = r4 + r6
                    L53:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.Z(r6)
                        if (r6 == 0) goto L66
                        if (r2 == 0) goto L60
                        int r13 = r13.f14801a
                        goto L62
                    L60:
                        int r13 = r13.f14803c
                    L62:
                        int r2 = r0.f2922c
                        int r5 = r13 + r2
                    L66:
                        android.view.ViewGroup$LayoutParams r13 = r11.getLayoutParams()
                        android.view.ViewGroup$MarginLayoutParams r13 = (android.view.ViewGroup.MarginLayoutParams) r13
                        com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.a0(r2)
                        r6 = 1
                        r7 = 0
                        if (r2 == 0) goto L80
                        int r2 = r13.leftMargin
                        int r8 = r0.f2920a
                        if (r2 == r8) goto L80
                        r13.leftMargin = r8
                        r2 = r6
                        goto L81
                    L80:
                        r2 = r7
                    L81:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.b0(r8)
                        if (r8 == 0) goto L92
                        int r8 = r13.rightMargin
                        int r9 = r0.f2922c
                        if (r8 == r9) goto L92
                        r13.rightMargin = r9
                        goto L93
                    L92:
                        r6 = r2
                    L93:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.K(r2)
                        if (r2 == 0) goto La4
                        int r2 = r13.topMargin
                        int r0 = r0.f2921b
                        if (r2 == r0) goto La4
                        r13.topMargin = r0
                        goto La6
                    La4:
                        if (r6 == 0) goto La9
                    La6:
                        r11.setLayoutParams(r13)
                    La9:
                        int r13 = r11.getPaddingTop()
                        r11.setPadding(r4, r13, r5, r3)
                        boolean r11 = r2
                        if (r11 == 0) goto Lbb
                        com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r13 = r1.f2923d
                        com.google.android.material.bottomsheet.BottomSheetBehavior.L(r11, r13)
                    Lbb:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.V(r11)
                        if (r11 != 0) goto Lc7
                        boolean r11 = r2
                        if (r11 == 0) goto Lcc
                    Lc7:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r10 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        com.google.android.material.bottomsheet.BottomSheetBehavior.M(r10, r7)
                    Lcc:
                        return r12
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass4.a(android.view.View, androidx.core.view.WindowInsetsCompat, com.google.android.material.internal.ViewUtils$RelativePadding):androidx.core.view.WindowInsetsCompat");
                }
            });
        }
    }

    private boolean a1() {
        return this.U != null && (this.R || this.S == 1);
    }

    private int c0(View view, int i2, int i3) {
        return ViewCompat.b(view, view.getResources().getString(i2), m0(i3));
    }

    private void e0() {
        int i0 = i0();
        if (this.f14017h) {
            this.N = Math.max(this.b0 - i0, this.K);
        } else {
            this.N = this.b0 - i0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e1(View view, int i2, boolean z) {
        int u0 = u0(i2);
        ViewDragHelper viewDragHelper = this.U;
        if (viewDragHelper == null || (!z ? viewDragHelper.R(view, view.getLeft(), u0) : viewDragHelper.P(view.getLeft(), u0))) {
            X0(i2);
            return;
        }
        X0(2);
        h1(i2, true);
        this.H.c(i2);
    }

    private float f0(float f2, RoundedCorner roundedCorner) {
        if (roundedCorner != null) {
            float radius = roundedCorner.getRadius();
            if (radius > 0.0f && f2 > 0.0f) {
                return radius / f2;
            }
        }
        return 0.0f;
    }

    private void f1() {
        WeakReference weakReference = this.c0;
        if (weakReference != null) {
            g1((View) weakReference.get(), 0);
        }
        WeakReference weakReference2 = this.d0;
        if (weakReference2 != null) {
            g1((View) weakReference2.get(), 1);
        }
    }

    private void g0() {
        this.L = (int) (this.b0 * (1.0f - this.M));
    }

    private void g1(View view, int i2) {
        if (view == null) {
            return;
        }
        l0(view, i2);
        if (!this.f14017h && this.S != 6) {
            this.expandHalfwayActionIds.put(i2, c0(view, R.string.bottomsheet_action_expand_halfway, 6));
        }
        if (this.P && B0() && this.S != 5) {
            F0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.y, 5);
        }
        int i3 = this.S;
        if (i3 == 3) {
            F0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.x, this.f14017h ? 4 : 6);
            return;
        }
        if (i3 == 4) {
            F0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.w, this.f14017h ? 3 : 6);
        } else {
            if (i3 != 6) {
                return;
            }
            F0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.x, 4);
            F0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.w, 3);
        }
    }

    private float h0() {
        WeakReference weakReference;
        WindowInsets rootWindowInsets;
        if (this.f14025p == null || (weakReference = this.c0) == null || weakReference.get() == null) {
            return 0.0f;
        }
        View view = (View) this.c0.get();
        if (!w0() || (rootWindowInsets = view.getRootWindowInsets()) == null) {
            return 0.0f;
        }
        return Math.max(f0(this.f14025p.I(), rootWindowInsets.getRoundedCorner(0)), f0(this.f14025p.J(), rootWindowInsets.getRoundedCorner(1)));
    }

    private void h1(int i2, boolean z) {
        boolean x0;
        ValueAnimator valueAnimator;
        if (i2 == 2 || this.G == (x0 = x0()) || this.f14025p == null) {
            return;
        }
        this.G = x0;
        if (!z || (valueAnimator = this.I) == null) {
            ValueAnimator valueAnimator2 = this.I;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.I.cancel();
            }
            this.f14025p.b0(this.G ? h0() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            this.I.reverse();
        } else {
            this.I.setFloatValues(this.f14025p.y(), x0 ? h0() : 1.0f);
            this.I.start();
        }
    }

    private int i0() {
        int i2;
        int i3;
        int i4;
        if (this.f14022m) {
            i2 = Math.min(Math.max(this.f14023n, this.b0 - ((this.a0 * 9) / 16)), this.Z);
            i3 = this.C;
        } else {
            if (!this.u && !this.v && (i4 = this.t) > 0) {
                return Math.max(this.f14021l, i4 + this.f14024o);
            }
            i2 = this.f14021l;
            i3 = this.C;
        }
        return i2 + i3;
    }

    private void i1(boolean z) {
        Map map;
        WeakReference weakReference = this.c0;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.l0 != null) {
                    return;
                } else {
                    this.l0 = new HashMap(childCount);
                }
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (childAt != this.c0.get()) {
                    if (z) {
                        this.l0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        if (this.f14018i) {
                            ViewCompat.s0(childAt, 4);
                        }
                    } else if (this.f14018i && (map = this.l0) != null && map.containsKey(childAt)) {
                        ViewCompat.s0(childAt, ((Integer) this.l0.get(childAt)).intValue());
                    }
                }
            }
            if (!z) {
                this.l0 = null;
            } else if (this.f14018i) {
                ((View) this.c0.get()).sendAccessibilityEvent(8);
            }
        }
    }

    private float j0(int i2) {
        float f2;
        float f3;
        int i3 = this.N;
        if (i2 > i3 || i3 == s0()) {
            int i4 = this.N;
            f2 = i4 - i2;
            f3 = this.b0 - i4;
        } else {
            int i5 = this.N;
            f2 = i5 - i2;
            f3 = i5 - s0();
        }
        return f2 / f3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j1(boolean z) {
        View view;
        if (this.c0 != null) {
            e0();
            if (this.S != 4 || (view = (View) this.c0.get()) == null) {
                return;
            }
            if (z) {
                setState(4);
            } else {
                view.requestLayout();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k0() {
        return A0() && B0();
    }

    private void l0(View view, int i2) {
        if (view == null) {
            return;
        }
        ViewCompat.c0(view, WindowManagerWrapper.LayoutParams.SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS);
        ViewCompat.c0(view, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE);
        ViewCompat.c0(view, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
        int i3 = this.expandHalfwayActionIds.get(i2, -1);
        if (i3 != -1) {
            ViewCompat.c0(view, i3);
            this.expandHalfwayActionIds.delete(i2);
        }
    }

    private AccessibilityViewCommand m0(final int i2) {
        return new AccessibilityViewCommand() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.6
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean a(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                BottomSheetBehavior.this.setState(i2);
                return true;
            }
        };
    }

    private void n0(Context context) {
        if (this.F == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.F);
        this.f14025p = materialShapeDrawable;
        materialShapeDrawable.P(context);
        ColorStateList colorStateList = this.f14026q;
        if (colorStateList != null) {
            this.f14025p.a0(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        this.f14025p.setTint(typedValue.data);
    }

    private void o0() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(h0(), 1.0f);
        this.I = ofFloat;
        ofFloat.setDuration(500L);
        this.I.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (BottomSheetBehavior.this.f14025p != null) {
                    BottomSheetBehavior.this.f14025p.b0(floatValue);
                }
            }
        });
    }

    public static BottomSheetBehavior q0(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior f2 = ((CoordinatorLayout.LayoutParams) layoutParams).f();
        if (f2 instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) f2;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    private int r0(int i2, int i3, int i4, int i5) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, i3, i5);
        if (i4 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i4), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        if (size != 0) {
            i4 = Math.min(size, i4);
        }
        return View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
    }

    private int u0(int i2) {
        if (i2 == 3) {
            return s0();
        }
        if (i2 == 4) {
            return this.N;
        }
        if (i2 == 5) {
            return this.b0;
        }
        if (i2 == 6) {
            return this.L;
        }
        throw new IllegalArgumentException("Invalid state to get top offset: " + i2);
    }

    private float v0() {
        VelocityTracker velocityTracker = this.g0;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f14019j);
        return this.g0.getYVelocity(this.i0);
    }

    private boolean w0() {
        WeakReference weakReference = this.c0;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        int[] iArr = new int[2];
        ((View) this.c0.get()).getLocationOnScreen(iArr);
        return iArr[1] == 0;
    }

    private boolean x0() {
        return this.S == 3 && (this.E || w0());
    }

    public boolean A0() {
        return this.P;
    }

    public boolean B0() {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void C(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.C(coordinatorLayout, view, savedState.a());
        H0(savedState);
        int i2 = savedState.f14040i;
        if (i2 == 1 || i2 == 2) {
            this.S = 4;
            this.T = 4;
        } else {
            this.S = i2;
            this.T = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable D(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(super.D(coordinatorLayout, view), this);
    }

    public boolean D0() {
        return true;
    }

    public void E0(BottomSheetCallback bottomSheetCallback) {
        this.f0.remove(bottomSheetCallback);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean F(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
        this.W = 0;
        this.X = false;
        return (i2 & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0035, code lost:
    
        if (r4.getTop() <= r2.L) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a9, code lost:
    
        r0 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0065, code lost:
    
        if (java.lang.Math.abs(r3 - r2.K) < java.lang.Math.abs(r3 - r2.N)) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007b, code lost:
    
        if (c1() != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008b, code lost:
    
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.N)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a7, code lost:
    
        if (java.lang.Math.abs(r3 - r2.L) < java.lang.Math.abs(r3 - r2.N)) goto L51;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void H(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.s0()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.X0(r0)
            return
        Lf:
            boolean r3 = r2.D0()
            if (r3 == 0) goto L24
            java.lang.ref.WeakReference r3 = r2.e0
            if (r3 == 0) goto L23
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L23
            boolean r3 = r2.X
            if (r3 != 0) goto L24
        L23:
            return
        L24:
            int r3 = r2.W
            r5 = 6
            if (r3 <= 0) goto L39
            boolean r3 = r2.f14017h
            if (r3 == 0) goto L2f
            goto Laa
        L2f:
            int r3 = r4.getTop()
            int r6 = r2.L
            if (r3 <= r6) goto Laa
            goto La9
        L39:
            boolean r3 = r2.P
            if (r3 == 0) goto L49
            float r3 = r2.v0()
            boolean r3 = r2.b1(r4, r3)
            if (r3 == 0) goto L49
            r0 = 5
            goto Laa
        L49:
            int r3 = r2.W
            r6 = 4
            if (r3 != 0) goto L8e
            int r3 = r4.getTop()
            boolean r1 = r2.f14017h
            if (r1 == 0) goto L68
            int r5 = r2.K
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.N
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L92
            goto Laa
        L68:
            int r1 = r2.L
            if (r3 >= r1) goto L7e
            int r1 = r2.N
            int r1 = r3 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r3 >= r1) goto L77
            goto Laa
        L77:
            boolean r3 = r2.c1()
            if (r3 == 0) goto La9
            goto L92
        L7e:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.N
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
            goto La9
        L8e:
            boolean r3 = r2.f14017h
            if (r3 == 0) goto L94
        L92:
            r0 = r6
            goto Laa
        L94:
            int r3 = r4.getTop()
            int r0 = r2.L
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.N
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
        La9:
            r0 = r5
        Laa:
            r3 = 0
            r2.e1(r4, r0, r3)
            r2.X = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.H(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean I(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.S == 1 && actionMasked == 0) {
            return true;
        }
        if (a1()) {
            this.U.G(motionEvent);
        }
        if (actionMasked == 0) {
            G0();
        }
        if (this.g0 == null) {
            this.g0 = VelocityTracker.obtain();
        }
        this.g0.addMovement(motionEvent);
        if (a1() && actionMasked == 2 && !this.V && Math.abs(this.j0 - motionEvent.getY()) > this.U.A()) {
            this.U.c(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.V;
    }

    void J0(View view) {
        WeakReference weakReference;
        if (view != null || (weakReference = this.d0) == null) {
            this.d0 = new WeakReference(view);
            g1(view, 1);
        } else {
            l0((View) weakReference.get(), 1);
            this.d0 = null;
        }
    }

    public void K0(boolean z) {
        this.R = z;
    }

    public void L0(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.J = i2;
        h1(this.S, true);
    }

    public void M0(boolean z) {
        if (this.f14017h == z) {
            return;
        }
        this.f14017h = z;
        if (this.c0 != null) {
            e0();
        }
        X0((this.f14017h && this.S == 6) ? 3 : this.S);
        h1(this.S, true);
        f1();
    }

    public void N0(boolean z) {
        this.u = z;
    }

    public void O0(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.M = f2;
        if (this.c0 != null) {
            g0();
        }
    }

    public void P0(boolean z) {
        if (this.P != z) {
            this.P = z;
            if (!z && this.S == 5) {
                setState(4);
            }
            f1();
        }
    }

    public void Q0(int i2) {
        this.f14028s = i2;
    }

    public void R0(int i2) {
        this.f14027r = i2;
    }

    public void S0(int i2) {
        T0(i2, false);
    }

    public final void T0(int i2, boolean z) {
        if (i2 == -1) {
            if (this.f14022m) {
                return;
            } else {
                this.f14022m = true;
            }
        } else {
            if (!this.f14022m && this.f14021l == i2) {
                return;
            }
            this.f14022m = false;
            this.f14021l = Math.max(0, i2);
        }
        j1(z);
    }

    public void U0(int i2) {
        this.f14016c = i2;
    }

    public void V0(int i2) {
        this.f14020k = i2;
    }

    public void W0(boolean z) {
        this.Q = z;
    }

    void X0(int i2) {
        View view;
        if (this.S == i2) {
            return;
        }
        this.S = i2;
        if (i2 == 4 || i2 == 3 || i2 == 6 || (this.P && i2 == 5)) {
            this.T = i2;
        }
        WeakReference weakReference = this.c0;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        if (i2 == 3) {
            i1(true);
        } else if (i2 == 6 || i2 == 5 || i2 == 4) {
            i1(false);
        }
        h1(i2, true);
        for (int i3 = 0; i3 < this.f0.size(); i3++) {
            ((BottomSheetCallback) this.f0.get(i3)).c(view, i2);
        }
        f1();
    }

    public boolean Z0(long j2, float f2) {
        return false;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void a() {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.h0;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        materialBottomContainerBackHelper.f();
    }

    boolean b1(View view, float f2) {
        if (this.Q) {
            return true;
        }
        if (B0() && view.getTop() >= this.N) {
            return Math.abs((((float) view.getTop()) + (f2 * this.Y)) - ((float) this.N)) / ((float) i0()) > 0.5f;
        }
        return false;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void c(BackEventCompat backEventCompat) {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.h0;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        materialBottomContainerBackHelper.j(backEventCompat);
    }

    public boolean c1() {
        return false;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void d(BackEventCompat backEventCompat) {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.h0;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        materialBottomContainerBackHelper.k(backEventCompat);
    }

    public void d0(BottomSheetCallback bottomSheetCallback) {
        if (this.f0.contains(bottomSheetCallback)) {
            return;
        }
        this.f0.add(bottomSheetCallback);
    }

    public boolean d1() {
        return true;
    }

    @RestrictTo
    @VisibleForTesting
    public void disableShapeAnimations() {
        this.I = null;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void e() {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.h0;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        BackEventCompat c2 = materialBottomContainerBackHelper.c();
        if (c2 == null || Build.VERSION.SDK_INT < 34) {
            setState(this.P ? 5 : 4);
        } else if (this.P) {
            this.h0.h(c2, new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    BottomSheetBehavior.this.X0(5);
                    WeakReference weakReference = BottomSheetBehavior.this.c0;
                    if (weakReference == null || weakReference.get() == null) {
                        return;
                    }
                    ((View) BottomSheetBehavior.this.c0.get()).requestLayout();
                }
            });
        } else {
            this.h0.i(c2, null);
            setState(4);
        }
    }

    @Nullable
    @VisibleForTesting
    View findScrollingChild(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        if (ViewCompat.O(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i2));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
        }
        return null;
    }

    @Nullable
    @VisibleForTesting
    MaterialBottomContainerBackHelper getBackHelper() {
        return this.h0;
    }

    @VisibleForTesting
    int getPeekHeightMin() {
        return this.f14023n;
    }

    public int getState() {
        return this.S;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void l(CoordinatorLayout.LayoutParams layoutParams) {
        super.l(layoutParams);
        this.c0 = null;
        this.U = null;
        this.h0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void o() {
        super.o();
        this.c0 = null;
        this.U = null;
        this.h0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int i2;
        ViewDragHelper viewDragHelper;
        if (!view.isShown() || !this.R) {
            this.V = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            G0();
        }
        if (this.g0 == null) {
            this.g0 = VelocityTracker.obtain();
        }
        this.g0.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.j0 = (int) motionEvent.getY();
            if (this.S != 2) {
                WeakReference weakReference = this.e0;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.F(view2, x, this.j0)) {
                    this.i0 = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.k0 = true;
                }
            }
            this.V = this.i0 == -1 && !coordinatorLayout.F(view, x, this.j0);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.k0 = false;
            this.i0 = -1;
            if (this.V) {
                this.V = false;
                return false;
            }
        }
        if (!this.V && (viewDragHelper = this.U) != null && viewDragHelper.Q(motionEvent)) {
            return true;
        }
        WeakReference weakReference2 = this.e0;
        View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
        return (actionMasked != 2 || view3 == null || this.V || this.S == 1 || coordinatorLayout.F(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.U == null || (i2 = this.j0) == -1 || Math.abs(((float) i2) - motionEvent.getY()) <= ((float) this.U.A())) ? false : true;
    }

    void p0(int i2) {
        View view = (View) this.c0.get();
        if (view == null || this.f0.isEmpty()) {
            return;
        }
        float j0 = j0(i2);
        for (int i3 = 0; i3 < this.f0.size(); i3++) {
            ((BottomSheetCallback) this.f0.get(i3)).b(view, j0);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (ViewCompat.s(coordinatorLayout) && !ViewCompat.s(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.c0 == null) {
            this.f14023n = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            Y0(view);
            ViewCompat.E0(view, new InsetsAnimationCallback(view));
            this.c0 = new WeakReference(view);
            this.h0 = new MaterialBottomContainerBackHelper(view);
            MaterialShapeDrawable materialShapeDrawable = this.f14025p;
            if (materialShapeDrawable != null) {
                ViewCompat.m0(view, materialShapeDrawable);
                MaterialShapeDrawable materialShapeDrawable2 = this.f14025p;
                float f2 = this.O;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.r(view);
                }
                materialShapeDrawable2.Z(f2);
            } else {
                ColorStateList colorStateList = this.f14026q;
                if (colorStateList != null) {
                    ViewCompat.n0(view, colorStateList);
                }
            }
            f1();
            if (ViewCompat.t(view) == 0) {
                ViewCompat.s0(view, 1);
            }
        }
        if (this.U == null) {
            this.U = ViewDragHelper.p(coordinatorLayout, this.m0);
        }
        int top = view.getTop();
        coordinatorLayout.M(view, i2);
        this.a0 = coordinatorLayout.getWidth();
        this.b0 = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.Z = height;
        int i3 = this.b0;
        int i4 = i3 - height;
        int i5 = this.D;
        if (i4 < i5) {
            if (this.y) {
                int i6 = this.f14028s;
                if (i6 != -1) {
                    i3 = Math.min(i3, i6);
                }
                this.Z = i3;
            } else {
                int i7 = i3 - i5;
                int i8 = this.f14028s;
                if (i8 != -1) {
                    i7 = Math.min(i7, i8);
                }
                this.Z = i7;
            }
        }
        this.K = Math.max(0, this.b0 - this.Z);
        g0();
        e0();
        int i9 = this.S;
        if (i9 == 3) {
            ViewCompat.T(view, s0());
        } else if (i9 == 6) {
            ViewCompat.T(view, this.L);
        } else if (this.P && i9 == 5) {
            ViewCompat.T(view, this.b0);
        } else if (i9 == 4) {
            ViewCompat.T(view, this.N);
        } else if (i9 == 1 || i9 == 2) {
            ViewCompat.T(view, top - view.getTop());
        }
        h1(this.S, false);
        this.e0 = new WeakReference(findScrollingChild(view));
        for (int i10 = 0; i10 < this.f0.size(); i10++) {
            ((BottomSheetCallback) this.f0.get(i10)).a(view);
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean r(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(r0(i2, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, this.f14027r, marginLayoutParams.width), r0(i4, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, this.f14028s, marginLayoutParams.height));
        return true;
    }

    public int s0() {
        if (this.f14017h) {
            return this.K;
        }
        return Math.max(this.J, this.y ? 0 : this.D);
    }

    public void setState(int i2) {
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATE_");
            sb.append(i2 == 1 ? "DRAGGING" : "SETTLING");
            sb.append(" should not be set externally.");
            throw new IllegalArgumentException(sb.toString());
        }
        if (!this.P && i2 == 5) {
            Log.w("BottomSheetBehavior", "Cannot set state: " + i2);
            return;
        }
        final int i3 = (i2 == 6 && this.f14017h && u0(i2) <= this.K) ? 3 : i2;
        WeakReference weakReference = this.c0;
        if (weakReference == null || weakReference.get() == null) {
            X0(i2);
        } else {
            final View view = (View) this.c0.get();
            I0(view, new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior.this.e1(view, i3, false);
                }
            });
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, float f3) {
        WeakReference weakReference;
        if (D0() && (weakReference = this.e0) != null && view2 == weakReference.get()) {
            return this.S != 3 || super.t(coordinatorLayout, view, view2, f2, f3);
        }
        return false;
    }

    MaterialShapeDrawable t0() {
        return this.f14025p;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void v(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 1) {
            return;
        }
        WeakReference weakReference = this.e0;
        View view3 = weakReference != null ? (View) weakReference.get() : null;
        if (!D0() || view2 == view3) {
            int top = view.getTop();
            int i5 = top - i3;
            if (i3 > 0) {
                if (i5 < s0()) {
                    int s0 = top - s0();
                    iArr[1] = s0;
                    ViewCompat.T(view, -s0);
                    X0(3);
                } else {
                    if (!this.R) {
                        return;
                    }
                    iArr[1] = i3;
                    ViewCompat.T(view, -i3);
                    X0(1);
                }
            } else if (i3 < 0 && !view2.canScrollVertically(-1)) {
                if (i5 > this.N && !k0()) {
                    int i6 = top - this.N;
                    iArr[1] = i6;
                    ViewCompat.T(view, -i6);
                    X0(4);
                } else {
                    if (!this.R) {
                        return;
                    }
                    iArr[1] = i3;
                    ViewCompat.T(view, -i3);
                    X0(1);
                }
            }
            p0(view.getTop());
            this.W = i3;
            this.X = true;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void y(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
    }

    public boolean y0() {
        return this.f14017h;
    }

    public boolean z0() {
        return this.u;
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: i, reason: collision with root package name */
        final int f14040i;

        /* renamed from: j, reason: collision with root package name */
        int f14041j;

        /* renamed from: k, reason: collision with root package name */
        boolean f14042k;

        /* renamed from: l, reason: collision with root package name */
        boolean f14043l;

        /* renamed from: m, reason: collision with root package name */
        boolean f14044m;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f14040i = parcel.readInt();
            this.f14041j = parcel.readInt();
            this.f14042k = parcel.readInt() == 1;
            this.f14043l = parcel.readInt() == 1;
            this.f14044m = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f14040i);
            parcel.writeInt(this.f14041j);
            parcel.writeInt(this.f14042k ? 1 : 0);
            parcel.writeInt(this.f14043l ? 1 : 0);
            parcel.writeInt(this.f14044m ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior bottomSheetBehavior) {
            super(parcelable);
            this.f14040i = bottomSheetBehavior.S;
            this.f14041j = bottomSheetBehavior.f14021l;
            this.f14042k = bottomSheetBehavior.f14017h;
            this.f14043l = bottomSheetBehavior.P;
            this.f14044m = bottomSheetBehavior.Q;
        }
    }

    public BottomSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2;
        this.f14016c = 0;
        this.f14017h = true;
        this.f14018i = false;
        this.f14027r = -1;
        this.f14028s = -1;
        this.H = new StateSettlingTracker();
        this.M = 0.5f;
        this.O = -1.0f;
        this.R = true;
        this.S = 4;
        this.T = 4;
        this.Y = 0.1f;
        this.f0 = new ArrayList();
        this.j0 = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.m0 = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5

            /* renamed from: a, reason: collision with root package name */
            private long f14036a;

            private boolean n(View view) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return top > (bottomSheetBehavior.b0 + bottomSheetBehavior.s0()) / 2;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int a(View view, int i22, int i3) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int b(View view, int i22, int i3) {
                return MathUtils.b(i22, BottomSheetBehavior.this.s0(), e(view));
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int e(View view) {
                return BottomSheetBehavior.this.k0() ? BottomSheetBehavior.this.b0 : BottomSheetBehavior.this.N;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void j(int i22) {
                if (i22 == 1 && BottomSheetBehavior.this.R) {
                    BottomSheetBehavior.this.X0(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void k(View view, int i22, int i3, int i4, int i5) {
                BottomSheetBehavior.this.p0(i3);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void l(View view, float f2, float f3) {
                /*
                    Method dump skipped, instructions count: 308
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass5.l(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean m(View view, int i22) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i3 = bottomSheetBehavior.S;
                if (i3 == 1 || bottomSheetBehavior.k0) {
                    return false;
                }
                if (i3 == 3 && bottomSheetBehavior.i0 == i22) {
                    WeakReference weakReference = bottomSheetBehavior.e0;
                    View view2 = weakReference != null ? (View) weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                this.f14036a = System.currentTimeMillis();
                WeakReference weakReference2 = BottomSheetBehavior.this.c0;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
        this.f14024o = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_backgroundTint)) {
            this.f14026q = MaterialResources.a(context, obtainStyledAttributes, R.styleable.BottomSheetBehavior_Layout_backgroundTint);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.F = ShapeAppearanceModel.e(context, attributeSet, R.attr.bottomSheetStyle, n0).m();
        }
        n0(context);
        o0();
        this.O = obtainStyledAttributes.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if (obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_android_maxWidth)) {
            R0(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_android_maxWidth, -1));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_android_maxHeight)) {
            Q0(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_android_maxHeight, -1));
        }
        TypedValue peekValue = obtainStyledAttributes.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue != null && (i2 = peekValue.data) == -1) {
            S0(i2);
        } else {
            S0(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        P0(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        N0(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        M0(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        W0(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        K0(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        U0(obtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        O0(obtainStyledAttributes.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if (peekValue2 != null && peekValue2.type == 16) {
            L0(peekValue2.data);
        } else {
            L0(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset, 0));
        }
        V0(obtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, DEFAULT_SIGNIFICANT_VEL_THRESHOLD));
        this.v = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.w = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.x = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.y = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.z = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.A = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.B = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.E = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        obtainStyledAttributes.recycle();
        this.f14019j = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
