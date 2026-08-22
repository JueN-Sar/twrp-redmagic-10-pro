package com.google.android.material.sidesheet;

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
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements Sheet<SideSheetCallback> {
    private static final int F = R.string.side_sheet_accessibility_pane_title;
    private static final int G = R.style.Widget_Material3_SideSheet;
    private VelocityTracker A;
    private MaterialSideContainerBackHelper B;
    private int C;
    private final Set D;
    private final ViewDragHelper.Callback E;

    /* renamed from: c, reason: collision with root package name */
    private SheetDelegate f15240c;

    /* renamed from: h, reason: collision with root package name */
    private float f15241h;

    /* renamed from: i, reason: collision with root package name */
    private MaterialShapeDrawable f15242i;

    /* renamed from: j, reason: collision with root package name */
    private ColorStateList f15243j;

    /* renamed from: k, reason: collision with root package name */
    private ShapeAppearanceModel f15244k;

    /* renamed from: l, reason: collision with root package name */
    private final StateSettlingTracker f15245l;

    /* renamed from: m, reason: collision with root package name */
    private float f15246m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f15247n;

    /* renamed from: o, reason: collision with root package name */
    private int f15248o;

    /* renamed from: p, reason: collision with root package name */
    private int f15249p;

    /* renamed from: q, reason: collision with root package name */
    private ViewDragHelper f15250q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f15251r;

    /* renamed from: s, reason: collision with root package name */
    private float f15252s;
    private int t;
    private int u;
    private int v;
    private int w;
    private WeakReference x;
    private WeakReference y;
    private int z;

    class StateSettlingTracker {

        /* renamed from: a, reason: collision with root package name */
        private int f15256a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f15257b;

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f15258c = new Runnable() { // from class: com.google.android.material.sidesheet.e
            @Override // java.lang.Runnable
            public final void run() {
                SideSheetBehavior.StateSettlingTracker.this.c();
            }
        };

        StateSettlingTracker() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c() {
            this.f15257b = false;
            if (SideSheetBehavior.this.f15250q != null && SideSheetBehavior.this.f15250q.n(true)) {
                b(this.f15256a);
            } else if (SideSheetBehavior.this.f15248o == 2) {
                SideSheetBehavior.this.L0(this.f15256a);
            }
        }

        void b(int i2) {
            if (SideSheetBehavior.this.x == null || SideSheetBehavior.this.x.get() == null) {
                return;
            }
            this.f15256a = i2;
            if (this.f15257b) {
                return;
            }
            ViewCompat.a0((View) SideSheetBehavior.this.x.get(), this.f15258c);
            this.f15257b = true;
        }
    }

    public SideSheetBehavior() {
        this.f15245l = new StateSettlingTracker();
        this.f15247n = true;
        this.f15248o = 5;
        this.f15249p = 5;
        this.f15252s = 0.1f;
        this.z = -1;
        this.D = new LinkedHashSet();
        this.E = new ViewDragHelper.Callback() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int a(View view, int i2, int i3) {
                return MathUtils.b(i2, SideSheetBehavior.this.f15240c.g(), SideSheetBehavior.this.f15240c.f());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int b(View view, int i2, int i3) {
                return view.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int d(View view) {
                return SideSheetBehavior.this.t + SideSheetBehavior.this.n0();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void j(int i2) {
                if (i2 == 1 && SideSheetBehavior.this.f15247n) {
                    SideSheetBehavior.this.L0(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void k(View view, int i2, int i3, int i4, int i5) {
                ViewGroup.MarginLayoutParams marginLayoutParams;
                View i0 = SideSheetBehavior.this.i0();
                if (i0 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) i0.getLayoutParams()) != null) {
                    SideSheetBehavior.this.f15240c.p(marginLayoutParams, view.getLeft(), view.getRight());
                    i0.setLayoutParams(marginLayoutParams);
                }
                SideSheetBehavior.this.c0(view, i2);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void l(View view, float f2, float f3) {
                int Y = SideSheetBehavior.this.Y(view, f2, f3);
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                sideSheetBehavior.Q0(view, Y, sideSheetBehavior.P0());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean m(View view, int i2) {
                return (SideSheetBehavior.this.f15248o == 1 || SideSheetBehavior.this.x == null || SideSheetBehavior.this.x.get() != view) ? false : true;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean A0(int i2, View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        setState(i2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B0(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, View view, ValueAnimator valueAnimator) {
        this.f15240c.o(marginLayoutParams, AnimationUtils.c(i2, 0, valueAnimator.getAnimatedFraction()));
        view.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C0(int i2) {
        View view = (View) this.x.get();
        if (view != null) {
            Q0(view, i2, false);
        }
    }

    private void D0(CoordinatorLayout coordinatorLayout) {
        int i2;
        View findViewById;
        if (this.y != null || (i2 = this.z) == -1 || (findViewById = coordinatorLayout.findViewById(i2)) == null) {
            return;
        }
        this.y = new WeakReference(findViewById);
    }

    private void E0(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.e0(view, accessibilityActionCompat, null, a0(i2));
    }

    private void F0() {
        VelocityTracker velocityTracker = this.A;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.A = null;
        }
    }

    private void G0(View view, Runnable runnable) {
        if (y0(view)) {
            view.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void J0(int i2) {
        SheetDelegate sheetDelegate = this.f15240c;
        if (sheetDelegate == null || sheetDelegate.j() != i2) {
            if (i2 == 0) {
                this.f15240c = new RightSheetDelegate(this);
                if (this.f15244k == null || v0()) {
                    return;
                }
                ShapeAppearanceModel.Builder v = this.f15244k.v();
                v.I(0.0f).z(0.0f);
                T0(v.m());
                return;
            }
            if (i2 == 1) {
                this.f15240c = new LeftSheetDelegate(this);
                if (this.f15244k == null || u0()) {
                    return;
                }
                ShapeAppearanceModel.Builder v2 = this.f15244k.v();
                v2.E(0.0f).v(0.0f);
                T0(v2.m());
                return;
            }
            throw new IllegalArgumentException("Invalid sheet edge position value: " + i2 + ". Must be 0 or 1.");
        }
    }

    private void K0(View view, int i2) {
        J0(GravityCompat.b(((CoordinatorLayout.LayoutParams) view.getLayoutParams()).f2582c, i2) == 3 ? 1 : 0);
    }

    private boolean M0() {
        return this.f15250q != null && (this.f15247n || this.f15248o == 1);
    }

    private boolean O0(View view) {
        return (view.isShown() || ViewCompat.l(view) != null) && this.f15247n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(View view, int i2, boolean z) {
        if (!z0(view, i2, z)) {
            L0(i2);
        } else {
            L0(2);
            this.f15245l.b(i2);
        }
    }

    private void R0() {
        View view;
        WeakReference weakReference = this.x;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        ViewCompat.c0(view, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE);
        ViewCompat.c0(view, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
        if (this.f15248o != 5) {
            E0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.y, 5);
        }
        if (this.f15248o != 3) {
            E0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.w, 3);
        }
    }

    private void S0() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        WeakReference weakReference = this.x;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.x.get();
        View i0 = i0();
        if (i0 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) i0.getLayoutParams()) == null) {
            return;
        }
        this.f15240c.o(marginLayoutParams, (int) ((this.t * view.getScaleX()) + this.w));
        i0.requestLayout();
    }

    private void T0(ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = this.f15242i;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    private void U0(View view) {
        int i2 = this.f15248o == 5 ? 4 : 0;
        if (view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
    }

    private int W(int i2, View view) {
        int i3 = this.f15248o;
        if (i3 == 1 || i3 == 2) {
            return i2 - this.f15240c.h(view);
        }
        if (i3 == 3) {
            return 0;
        }
        if (i3 == 5) {
            return this.f15240c.e();
        }
        throw new IllegalStateException("Unexpected value: " + this.f15248o);
    }

    private float X(float f2, float f3) {
        return Math.abs(f2 - f3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int Y(View view, float f2, float f3) {
        if (x0(f2)) {
            return 3;
        }
        if (N0(view, f2)) {
            if (!this.f15240c.m(f2, f3) && !this.f15240c.l(view)) {
                return 3;
            }
        } else if (f2 == 0.0f || !SheetUtils.a(f2, f3)) {
            int left = view.getLeft();
            if (Math.abs(left - j0()) < Math.abs(left - this.f15240c.e())) {
                return 3;
            }
        }
        return 5;
    }

    private void Z() {
        WeakReference weakReference = this.y;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.y = null;
    }

    private AccessibilityViewCommand a0(final int i2) {
        return new AccessibilityViewCommand() { // from class: com.google.android.material.sidesheet.c
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean a(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                boolean A0;
                A0 = SideSheetBehavior.this.A0(i2, view, commandArguments);
                return A0;
            }
        };
    }

    private void b0(Context context) {
        if (this.f15244k == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.f15244k);
        this.f15242i = materialShapeDrawable;
        materialShapeDrawable.P(context);
        ColorStateList colorStateList = this.f15243j;
        if (colorStateList != null) {
            this.f15242i.a0(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        this.f15242i.setTint(typedValue.data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(View view, int i2) {
        if (this.D.isEmpty()) {
            return;
        }
        float b2 = this.f15240c.b(i2);
        Iterator it = this.D.iterator();
        while (it.hasNext()) {
            ((SheetCallback) it.next()).b(view, b2);
        }
    }

    private void d0(View view) {
        if (ViewCompat.l(view) == null) {
            ViewCompat.l0(view, view.getResources().getString(F));
        }
    }

    public static SideSheetBehavior e0(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior f2 = ((CoordinatorLayout.LayoutParams) layoutParams).f();
        if (f2 instanceof SideSheetBehavior) {
            return (SideSheetBehavior) f2;
        }
        throw new IllegalArgumentException("The view is not associated with SideSheetBehavior");
    }

    private int f0(int i2, int i3, int i4, int i5) {
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

    private ValueAnimator.AnimatorUpdateListener h0() {
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        final View i0 = i0();
        if (i0 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) i0.getLayoutParams()) == null) {
            return null;
        }
        final int c2 = this.f15240c.c(marginLayoutParams);
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.sidesheet.d
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SideSheetBehavior.this.B0(marginLayoutParams, c2, i0, valueAnimator);
            }
        };
    }

    private int k0() {
        SheetDelegate sheetDelegate = this.f15240c;
        return (sheetDelegate == null || sheetDelegate.j() == 0) ? 5 : 3;
    }

    private CoordinatorLayout.LayoutParams t0() {
        View view;
        WeakReference weakReference = this.x;
        if (weakReference == null || (view = (View) weakReference.get()) == null || !(view.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return null;
        }
        return (CoordinatorLayout.LayoutParams) view.getLayoutParams();
    }

    private boolean u0() {
        CoordinatorLayout.LayoutParams t0 = t0();
        return t0 != null && ((ViewGroup.MarginLayoutParams) t0).leftMargin > 0;
    }

    private boolean v0() {
        CoordinatorLayout.LayoutParams t0 = t0();
        return t0 != null && ((ViewGroup.MarginLayoutParams) t0).rightMargin > 0;
    }

    private boolean w0(MotionEvent motionEvent) {
        return M0() && X((float) this.C, motionEvent.getX()) > ((float) this.f15250q.A());
    }

    private boolean x0(float f2) {
        return this.f15240c.k(f2);
    }

    private boolean y0(View view) {
        ViewParent parent = view.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.M(view);
    }

    private boolean z0(View view, int i2, boolean z) {
        int o0 = o0(i2);
        ViewDragHelper s0 = s0();
        return s0 != null && (!z ? !s0.R(view, o0, view.getTop()) : !s0.P(o0, view.getTop()));
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void C(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.a() != null) {
            super.C(coordinatorLayout, view, savedState.a());
        }
        int i2 = savedState.f15255i;
        if (i2 == 1 || i2 == 2) {
            i2 = 5;
        }
        this.f15248o = i2;
        this.f15249p = i2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable D(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(super.D(coordinatorLayout, view), this);
    }

    public void H0(int i2) {
        this.z = i2;
        Z();
        WeakReference weakReference = this.x;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (i2 == -1 || !ViewCompat.N(view)) {
                return;
            }
            view.requestLayout();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean I(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f15248o == 1 && actionMasked == 0) {
            return true;
        }
        if (M0()) {
            this.f15250q.G(motionEvent);
        }
        if (actionMasked == 0) {
            F0();
        }
        if (this.A == null) {
            this.A = VelocityTracker.obtain();
        }
        this.A.addMovement(motionEvent);
        if (M0() && actionMasked == 2 && !this.f15251r && w0(motionEvent)) {
            this.f15250q.c(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.f15251r;
    }

    public void I0(boolean z) {
        this.f15247n = z;
    }

    void L0(int i2) {
        View view;
        if (this.f15248o == i2) {
            return;
        }
        this.f15248o = i2;
        if (i2 == 3 || i2 == 5) {
            this.f15249p = i2;
        }
        WeakReference weakReference = this.x;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        U0(view);
        Iterator it = this.D.iterator();
        while (it.hasNext()) {
            ((SheetCallback) it.next()).a(view, i2);
        }
        R0();
    }

    boolean N0(View view, float f2) {
        return this.f15240c.n(view, f2);
    }

    public boolean P0() {
        return true;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public void b(SideSheetCallback sideSheetCallback) {
        this.D.add(sideSheetCallback);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void a() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.B;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.f();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void c(BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.B;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.j(backEventCompat);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void d(BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.B;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.k(backEventCompat, k0());
        S0();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void e() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.B;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        BackEventCompat c2 = materialSideContainerBackHelper.c();
        if (c2 == null || Build.VERSION.SDK_INT < 34) {
            setState(5);
        } else {
            this.B.h(c2, k0(), new AnimatorListenerAdapter() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SideSheetBehavior.this.L0(5);
                    if (SideSheetBehavior.this.x == null || SideSheetBehavior.this.x.get() == null) {
                        return;
                    }
                    ((View) SideSheetBehavior.this.x.get()).requestLayout();
                }
            }, h0());
        }
    }

    int g0() {
        return this.t;
    }

    @Nullable
    @VisibleForTesting
    MaterialSideContainerBackHelper getBackHelper() {
        return this.B;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public int getState() {
        return this.f15248o;
    }

    public View i0() {
        WeakReference weakReference = this.y;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    public int j0() {
        return this.f15240c.d();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void l(CoordinatorLayout.LayoutParams layoutParams) {
        super.l(layoutParams);
        this.x = null;
        this.f15250q = null;
        this.B = null;
    }

    public float l0() {
        return this.f15252s;
    }

    float m0() {
        return 0.5f;
    }

    int n0() {
        return this.w;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void o() {
        super.o();
        this.x = null;
        this.f15250q = null;
        this.B = null;
    }

    int o0(int i2) {
        if (i2 == 3) {
            return j0();
        }
        if (i2 == 5) {
            return this.f15240c.e();
        }
        throw new IllegalArgumentException("Invalid state to get outer edge offset: " + i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (!O0(view)) {
            this.f15251r = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            F0();
        }
        if (this.A == null) {
            this.A = VelocityTracker.obtain();
        }
        this.A.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.C = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.f15251r) {
            this.f15251r = false;
            return false;
        }
        return (this.f15251r || (viewDragHelper = this.f15250q) == null || !viewDragHelper.Q(motionEvent)) ? false : true;
    }

    int p0() {
        return this.v;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (ViewCompat.s(coordinatorLayout) && !ViewCompat.s(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.x == null) {
            this.x = new WeakReference(view);
            this.B = new MaterialSideContainerBackHelper(view);
            MaterialShapeDrawable materialShapeDrawable = this.f15242i;
            if (materialShapeDrawable != null) {
                ViewCompat.m0(view, materialShapeDrawable);
                MaterialShapeDrawable materialShapeDrawable2 = this.f15242i;
                float f2 = this.f15246m;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.r(view);
                }
                materialShapeDrawable2.Z(f2);
            } else {
                ColorStateList colorStateList = this.f15243j;
                if (colorStateList != null) {
                    ViewCompat.n0(view, colorStateList);
                }
            }
            U0(view);
            R0();
            if (ViewCompat.t(view) == 0) {
                ViewCompat.s0(view, 1);
            }
            d0(view);
        }
        K0(view, i2);
        if (this.f15250q == null) {
            this.f15250q = ViewDragHelper.p(coordinatorLayout, this.E);
        }
        int h2 = this.f15240c.h(view);
        coordinatorLayout.M(view, i2);
        this.u = coordinatorLayout.getWidth();
        this.v = this.f15240c.i(coordinatorLayout);
        this.t = view.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        this.w = marginLayoutParams != null ? this.f15240c.a(marginLayoutParams) : 0;
        ViewCompat.S(view, W(h2, view));
        D0(coordinatorLayout);
        for (SheetCallback sheetCallback : this.D) {
            if (sheetCallback instanceof SideSheetCallback) {
                ((SideSheetCallback) sheetCallback).c(view);
            }
        }
        return true;
    }

    int q0() {
        return this.u;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean r(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(f0(i2, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, -1, marginLayoutParams.width), f0(i4, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, -1, marginLayoutParams.height));
        return true;
    }

    int r0() {
        return 500;
    }

    ViewDragHelper s0() {
        return this.f15250q;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public void setState(final int i2) {
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATE_");
            sb.append(i2 == 1 ? "DRAGGING" : "SETTLING");
            sb.append(" should not be set externally.");
            throw new IllegalArgumentException(sb.toString());
        }
        WeakReference weakReference = this.x;
        if (weakReference == null || weakReference.get() == null) {
            L0(i2);
        } else {
            G0((View) this.x.get(), new Runnable() { // from class: com.google.android.material.sidesheet.b
                @Override // java.lang.Runnable
                public final void run() {
                    SideSheetBehavior.this.C0(i2);
                }
            });
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.SavedState.1
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
        final int f15255i;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f15255i = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f15255i);
        }

        public SavedState(Parcelable parcelable, SideSheetBehavior sideSheetBehavior) {
            super(parcelable);
            this.f15255i = sideSheetBehavior.f15248o;
        }
    }

    public SideSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15245l = new StateSettlingTracker();
        this.f15247n = true;
        this.f15248o = 5;
        this.f15249p = 5;
        this.f15252s = 0.1f;
        this.z = -1;
        this.D = new LinkedHashSet();
        this.E = new ViewDragHelper.Callback() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int a(View view, int i2, int i3) {
                return MathUtils.b(i2, SideSheetBehavior.this.f15240c.g(), SideSheetBehavior.this.f15240c.f());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int b(View view, int i2, int i3) {
                return view.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int d(View view) {
                return SideSheetBehavior.this.t + SideSheetBehavior.this.n0();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void j(int i2) {
                if (i2 == 1 && SideSheetBehavior.this.f15247n) {
                    SideSheetBehavior.this.L0(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void k(View view, int i2, int i3, int i4, int i5) {
                ViewGroup.MarginLayoutParams marginLayoutParams;
                View i0 = SideSheetBehavior.this.i0();
                if (i0 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) i0.getLayoutParams()) != null) {
                    SideSheetBehavior.this.f15240c.p(marginLayoutParams, view.getLeft(), view.getRight());
                    i0.setLayoutParams(marginLayoutParams);
                }
                SideSheetBehavior.this.c0(view, i2);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void l(View view, float f2, float f3) {
                int Y = SideSheetBehavior.this.Y(view, f2, f3);
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                sideSheetBehavior.Q0(view, Y, sideSheetBehavior.P0());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean m(View view, int i2) {
                return (SideSheetBehavior.this.f15248o == 1 || SideSheetBehavior.this.x == null || SideSheetBehavior.this.x.get() != view) ? false : true;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SideSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_backgroundTint)) {
            this.f15243j = MaterialResources.a(context, obtainStyledAttributes, R.styleable.SideSheetBehavior_Layout_backgroundTint);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.f15244k = ShapeAppearanceModel.e(context, attributeSet, 0, G).m();
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId)) {
            H0(obtainStyledAttributes.getResourceId(R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId, -1));
        }
        b0(context);
        this.f15246m = obtainStyledAttributes.getDimension(R.styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        I0(obtainStyledAttributes.getBoolean(R.styleable.SideSheetBehavior_Layout_behavior_draggable, true));
        obtainStyledAttributes.recycle();
        this.f15241h = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
