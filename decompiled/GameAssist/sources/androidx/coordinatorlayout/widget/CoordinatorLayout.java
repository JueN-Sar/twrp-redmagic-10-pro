package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.R;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.card.MaterialCardView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2, NestedScrollingParent3 {
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
    private static final Pools.Pool<Rect> sRectPool;
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private final int[] mBehaviorConsumed;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private final int[] mNestedScrollingV2ConsumedCompat;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final List<View> mTempList1;

    public interface AttachedBehavior {
        Behavior getBehavior();
    }

    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public void A(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
            if (i3 == 0) {
                z(coordinatorLayout, view, view2, view3, i2);
            }
        }

        public boolean B(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            return false;
        }

        public void C(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        }

        public Parcelable D(CoordinatorLayout coordinatorLayout, View view) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        public boolean E(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2) {
            return false;
        }

        public boolean F(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
            if (i3 == 0) {
                return E(coordinatorLayout, view, view2, view3, i2);
            }
            return false;
        }

        public void G(CoordinatorLayout coordinatorLayout, View view, View view2) {
        }

        public void H(CoordinatorLayout coordinatorLayout, View view, View view2, int i2) {
            if (i2 == 0) {
                G(coordinatorLayout, view, view2);
            }
        }

        public boolean I(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return false;
        }

        public boolean f(CoordinatorLayout coordinatorLayout, View view) {
            return i(coordinatorLayout, view) > 0.0f;
        }

        public boolean g(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            return false;
        }

        public int h(CoordinatorLayout coordinatorLayout, View view) {
            return -16777216;
        }

        public float i(CoordinatorLayout coordinatorLayout, View view) {
            return 0.0f;
        }

        public boolean j(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return false;
        }

        public WindowInsetsCompat k(CoordinatorLayout coordinatorLayout, View view, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public void l(LayoutParams layoutParams) {
        }

        public boolean m(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return false;
        }

        public void n(CoordinatorLayout coordinatorLayout, View view, View view2) {
        }

        public void o() {
        }

        public boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return false;
        }

        public boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
            return false;
        }

        public boolean r(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
            return false;
        }

        public boolean s(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, float f3, boolean z) {
            return false;
        }

        public boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, float f3) {
            return false;
        }

        public void u(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr) {
        }

        public void v(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr, int i4) {
            if (i4 == 0) {
                u(coordinatorLayout, view, view2, i2, i3, iArr);
            }
        }

        public void w(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5) {
        }

        public void x(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6) {
            if (i6 == 0) {
                w(coordinatorLayout, view, view2, i2, i3, i4, i5);
            }
        }

        public void y(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
            x(coordinatorLayout, view, view2, i2, i3, i4, i5, i6);
        }

        public void z(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2) {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    public @interface DefaultBehavior {
        Class value();
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface DispatchChangeEvent {
    }

    private class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        HierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.L(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        OnPreDrawListener() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.L(0);
            return true;
        }
    }

    static class ViewElevationComparator implements Comparator<View> {
        ViewElevationComparator() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            float H = ViewCompat.H(view);
            float H2 = ViewCompat.H(view2);
            if (H > H2) {
                return -1;
            }
            return H < H2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = r0 != null ? r0.getName() : null;
        TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal<>();
        sRectPool = new Pools.SynchronizedPool(12);
    }

    public CoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.coordinatorLayoutStyle);
    }

    private int A(int i2) {
        int[] iArr = this.mKeylines;
        if (iArr == null) {
            Log.e(TAG, "No keylines defined for " + this + " - attempted index lookup " + i2);
            return 0;
        }
        if (i2 >= 0 && i2 < iArr.length) {
            return iArr[i2];
        }
        Log.e(TAG, "Keyline index " + i2 + " out of range for " + this);
        return 0;
    }

    private void D(List list) {
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator<View> comparator = TOP_SORTED_CHILDREN_COMPARATOR;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    private boolean E(View view) {
        return this.mChildDag.j(view);
    }

    private void G(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect k2 = k();
        k2.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        if (this.mLastInsets != null && ViewCompat.s(this) && !ViewCompat.s(view)) {
            k2.left += this.mLastInsets.j();
            k2.top += this.mLastInsets.l();
            k2.right -= this.mLastInsets.k();
            k2.bottom -= this.mLastInsets.i();
        }
        Rect k3 = k();
        GravityCompat.a(W(layoutParams.f2582c), view.getMeasuredWidth(), view.getMeasuredHeight(), k2, k3, i2);
        view.layout(k3.left, k3.top, k3.right, k3.bottom);
        S(k2);
        S(k3);
    }

    private void H(View view, View view2, int i2) {
        Rect k2 = k();
        Rect k3 = k();
        try {
            x(view2, k2);
            y(view, i2, k2, k3);
            view.layout(k3.left, k3.top, k3.right, k3.bottom);
        } finally {
            S(k2);
            S(k3);
        }
    }

    private void I(View view, int i2, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int b2 = GravityCompat.b(X(layoutParams.f2582c), i3);
        int i4 = b2 & 7;
        int i5 = b2 & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i3 == 1) {
            i2 = width - i2;
        }
        int A = A(i2) - measuredWidth;
        if (i4 == 1) {
            A += measuredWidth / 2;
        } else if (i4 == 5) {
            A += measuredWidth;
        }
        int i6 = i5 != 16 ? i5 != 80 ? 0 : measuredHeight : measuredHeight / 2;
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, Math.min(A, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, Math.min(i6, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin));
        view.layout(max, max2, measuredWidth + max, measuredHeight + max2);
    }

    private void J(View view, Rect rect, int i2) {
        boolean z;
        int width;
        int i3;
        int i4;
        int i5;
        int height;
        int i6;
        int i7;
        int i8;
        if (ViewCompat.N(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Behavior f2 = layoutParams.f();
            Rect k2 = k();
            Rect k3 = k();
            k3.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (f2 == null || !f2.g(this, view, k2)) {
                k2.set(k3);
            } else if (!k3.contains(k2)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + k2.toShortString() + " | Bounds:" + k3.toShortString());
            }
            S(k3);
            if (k2.isEmpty()) {
                S(k2);
                return;
            }
            int b2 = GravityCompat.b(layoutParams.f2587h, i2);
            boolean z2 = true;
            if ((b2 & 48) != 48 || (i7 = (k2.top - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - layoutParams.f2589j) >= (i8 = rect.top)) {
                z = false;
            } else {
                Z(view, i8 - i7);
                z = true;
            }
            if ((b2 & 80) == 80 && (height = ((getHeight() - k2.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) + layoutParams.f2589j) < (i6 = rect.bottom)) {
                Z(view, height - i6);
            } else if (!z) {
                Z(view, 0);
            }
            if ((b2 & 3) != 3 || (i4 = (k2.left - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - layoutParams.f2588i) >= (i5 = rect.left)) {
                z2 = false;
            } else {
                Y(view, i5 - i4);
            }
            if ((b2 & 5) == 5 && (width = ((getWidth() - k2.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin) + layoutParams.f2588i) < (i3 = rect.right)) {
                Y(view, width - i3);
            } else if (!z2) {
                Y(view, 0);
            }
            S(k2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static Behavior O(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = WIDGET_PACKAGE_NAME;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<Behavior>>> threadLocal = sConstructors;
            Map<String, Constructor<Behavior>> map = threadLocal.get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            Constructor<Behavior> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e2) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e2);
        }
    }

    private boolean P(MotionEvent motionEvent, int i2) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.mTempList1;
        D(list);
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z = false;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = list.get(i3);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Behavior f2 = layoutParams.f();
            if (!(z || z2) || actionMasked == 0) {
                if (!z && f2 != null) {
                    if (i2 == 0) {
                        z = f2.p(this, view, motionEvent);
                    } else if (i2 == 1) {
                        z = f2.I(this, view, motionEvent);
                    }
                    if (z) {
                        this.mBehaviorTouchView = view;
                    }
                }
                boolean c2 = layoutParams.c();
                boolean i4 = layoutParams.i(this, view);
                z2 = i4 && !c2;
                if (i4 && !z2) {
                    break;
                }
            } else if (f2 != null) {
                if (motionEvent2 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i2 == 0) {
                    f2.p(this, view, motionEvent2);
                } else if (i2 == 1) {
                    f2.I(this, view, motionEvent2);
                }
            }
        }
        list.clear();
        return z;
    }

    private void Q() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.c();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams C = C(childAt);
            C.d(this, childAt);
            this.mChildDag.b(childAt);
            for (int i3 = 0; i3 < childCount; i3++) {
                if (i3 != i2) {
                    View childAt2 = getChildAt(i3);
                    if (C.b(this, childAt, childAt2)) {
                        if (!this.mChildDag.d(childAt2)) {
                            this.mChildDag.b(childAt2);
                        }
                        this.mChildDag.a(childAt2, childAt);
                    }
                }
            }
        }
        this.mDependencySortedChildren.addAll(this.mChildDag.i());
        Collections.reverse(this.mDependencySortedChildren);
    }

    private static void S(Rect rect) {
        rect.setEmpty();
        sRectPool.release(rect);
    }

    private void U(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            Behavior f2 = ((LayoutParams) childAt.getLayoutParams()).f();
            if (f2 != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    f2.p(this, childAt, obtain);
                } else {
                    f2.I(this, childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            ((LayoutParams) getChildAt(i3).getLayoutParams()).m();
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    private static int V(int i2) {
        if (i2 == 0) {
            return 17;
        }
        return i2;
    }

    private static int W(int i2) {
        if ((i2 & 7) == 0) {
            i2 |= 8388611;
        }
        return (i2 & 112) == 0 ? i2 | 48 : i2;
    }

    private static int X(int i2) {
        return i2 == 0 ? MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END : i2;
    }

    private void Y(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.f2588i;
        if (i3 != i2) {
            ViewCompat.S(view, i2 - i3);
            layoutParams.f2588i = i2;
        }
    }

    private void Z(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.f2589j;
        if (i3 != i2) {
            ViewCompat.T(view, i2 - i3);
            layoutParams.f2589j = i2;
        }
    }

    private void b0() {
        if (!ViewCompat.s(this)) {
            ViewCompat.x0(this, null);
            return;
        }
        if (this.mApplyWindowInsetsListener == null) {
            this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() { // from class: androidx.coordinatorlayout.widget.CoordinatorLayout.1
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                    return CoordinatorLayout.this.a0(windowInsetsCompat);
                }
            };
        }
        ViewCompat.x0(this, this.mApplyWindowInsetsListener);
        setSystemUiVisibility(1280);
    }

    private static Rect k() {
        Rect rect = (Rect) sRectPool.acquire();
        return rect == null ? new Rect() : rect;
    }

    private static int m(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    private void n(LayoutParams layoutParams, Rect rect, int i2, int i3) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i2) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i3) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin));
        rect.set(max, max2, i2 + max, i3 + max2);
    }

    private WindowInsetsCompat o(WindowInsetsCompat windowInsetsCompat) {
        Behavior f2;
        if (windowInsetsCompat.p()) {
            return windowInsetsCompat;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (ViewCompat.s(childAt) && (f2 = ((LayoutParams) childAt.getLayoutParams()).f()) != null) {
                windowInsetsCompat = f2.k(this, childAt, windowInsetsCompat);
                if (windowInsetsCompat.p()) {
                    break;
                }
            }
        }
        return windowInsetsCompat;
    }

    private void z(View view, int i2, Rect rect, Rect rect2, LayoutParams layoutParams, int i3, int i4) {
        int b2 = GravityCompat.b(V(layoutParams.f2582c), i2);
        int b3 = GravityCompat.b(W(layoutParams.f2583d), i2);
        int i5 = b2 & 7;
        int i6 = b2 & 112;
        int i7 = b3 & 7;
        int i8 = b3 & 112;
        int width = i7 != 1 ? i7 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int height = i8 != 16 ? i8 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i5 == 1) {
            width -= i3 / 2;
        } else if (i5 != 5) {
            width -= i3;
        }
        if (i6 == 16) {
            height -= i4 / 2;
        } else if (i6 != 80) {
            height -= i4;
        }
        rect2.set(width, height, i3 + width, i4 + height);
    }

    void B(View view, Rect rect) {
        rect.set(((LayoutParams) view.getLayoutParams()).h());
    }

    /* JADX WARN: Multi-variable type inference failed */
    LayoutParams C(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.f2581b) {
            if (view instanceof AttachedBehavior) {
                Behavior behavior = ((AttachedBehavior) view).getBehavior();
                if (behavior == null) {
                    Log.e(TAG, "Attached behavior class is null");
                }
                layoutParams.o(behavior);
                layoutParams.f2581b = true;
            } else {
                DefaultBehavior defaultBehavior = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    defaultBehavior = (DefaultBehavior) cls.getAnnotation(DefaultBehavior.class);
                    if (defaultBehavior != null) {
                        break;
                    }
                }
                if (defaultBehavior != null) {
                    try {
                        layoutParams.o((Behavior) defaultBehavior.value().getDeclaredConstructor(null).newInstance(null));
                    } catch (Exception e2) {
                        Log.e(TAG, "Default behavior class " + defaultBehavior.value().getName() + " could not be instantiated. Did you forget a default constructor?", e2);
                    }
                }
                layoutParams.f2581b = true;
            }
        }
        return layoutParams;
    }

    public boolean F(View view, int i2, int i3) {
        Rect k2 = k();
        x(view, k2);
        try {
            return k2.contains(i2, i3);
        } finally {
            S(k2);
        }
    }

    void K(View view, int i2) {
        Behavior f2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.f2590k != null) {
            Rect k2 = k();
            Rect k3 = k();
            Rect k4 = k();
            x(layoutParams.f2590k, k2);
            u(view, false, k3);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            z(view, i2, k2, k4, layoutParams, measuredWidth, measuredHeight);
            boolean z = (k4.left == k3.left && k4.top == k3.top) ? false : true;
            n(layoutParams, k4, measuredWidth, measuredHeight);
            int i3 = k4.left - k3.left;
            int i4 = k4.top - k3.top;
            if (i3 != 0) {
                ViewCompat.S(view, i3);
            }
            if (i4 != 0) {
                ViewCompat.T(view, i4);
            }
            if (z && (f2 = layoutParams.f()) != null) {
                f2.m(this, view, layoutParams.f2590k);
            }
            S(k2);
            S(k3);
            S(k4);
        }
    }

    final void L(int i2) {
        boolean z;
        int v = ViewCompat.v(this);
        int size = this.mDependencySortedChildren.size();
        Rect k2 = k();
        Rect k3 = k();
        Rect k4 = k();
        for (int i3 = 0; i3 < size; i3++) {
            View view = this.mDependencySortedChildren.get(i3);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (i2 != 0 || view.getVisibility() != 8) {
                for (int i4 = 0; i4 < i3; i4++) {
                    if (layoutParams.f2591l == this.mDependencySortedChildren.get(i4)) {
                        K(view, v);
                    }
                }
                u(view, true, k3);
                if (layoutParams.f2586g != 0 && !k3.isEmpty()) {
                    int b2 = GravityCompat.b(layoutParams.f2586g, v);
                    int i5 = b2 & 112;
                    if (i5 == 48) {
                        k2.top = Math.max(k2.top, k3.bottom);
                    } else if (i5 == 80) {
                        k2.bottom = Math.max(k2.bottom, getHeight() - k3.top);
                    }
                    int i6 = b2 & 7;
                    if (i6 == 3) {
                        k2.left = Math.max(k2.left, k3.right);
                    } else if (i6 == 5) {
                        k2.right = Math.max(k2.right, getWidth() - k3.left);
                    }
                }
                if (layoutParams.f2587h != 0 && view.getVisibility() == 0) {
                    J(view, k2, v);
                }
                if (i2 != 2) {
                    B(view, k4);
                    if (!k4.equals(k3)) {
                        R(view, k3);
                    }
                }
                for (int i7 = i3 + 1; i7 < size; i7++) {
                    View view2 = this.mDependencySortedChildren.get(i7);
                    LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                    Behavior f2 = layoutParams2.f();
                    if (f2 != null && f2.j(this, view2, view)) {
                        if (i2 == 0 && layoutParams2.g()) {
                            layoutParams2.k();
                        } else {
                            if (i2 != 2) {
                                z = f2.m(this, view2, view);
                            } else {
                                f2.n(this, view2, view);
                                z = true;
                            }
                            if (i2 == 1) {
                                layoutParams2.p(z);
                            }
                        }
                    }
                }
            }
        }
        S(k2);
        S(k3);
        S(k4);
    }

    public void M(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.a()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        View view2 = layoutParams.f2590k;
        if (view2 != null) {
            H(view, view2, i2);
            return;
        }
        int i3 = layoutParams.f2584e;
        if (i3 >= 0) {
            I(view, i3, i2);
        } else {
            G(view, i2);
        }
    }

    public void N(View view, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    void R(View view, Rect rect) {
        ((LayoutParams) view.getLayoutParams()).q(rect);
    }

    void T() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    final WindowInsetsCompat a0(WindowInsetsCompat windowInsetsCompat) {
        if (ObjectsCompat.a(this.mLastInsets, windowInsetsCompat)) {
            return windowInsetsCompat;
        }
        this.mLastInsets = windowInsetsCompat;
        boolean z = false;
        boolean z2 = windowInsetsCompat != null && windowInsetsCompat.l() > 0;
        this.mDrawStatusBarBackground = z2;
        if (!z2 && getBackground() == null) {
            z = true;
        }
        setWillNotDraw(z);
        WindowInsetsCompat o2 = o(windowInsetsCompat);
        requestLayout();
        return o2;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void b(View view, View view2, int i2, int i3) {
        Behavior f2;
        this.mNestedScrollingParentHelper.c(view, view2, i2, i3);
        this.mNestedScrollingTarget = view2;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.j(i3) && (f2 = layoutParams.f()) != null) {
                f2.A(this, childAt, view, view2, i2, i3);
            }
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void c(View view, int i2) {
        this.mNestedScrollingParentHelper.e(view, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.j(i2)) {
                Behavior f2 = layoutParams.f();
                if (f2 != null) {
                    f2.H(this, childAt, view, i2);
                }
                layoutParams.l(i2);
                layoutParams.k();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void d(View view, int i2, int i3, int[] iArr, int i4) {
        Behavior f2;
        int childCount = getChildCount();
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.j(i4) && (f2 = layoutParams.f()) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    f2.v(this, childAt, view, i2, i3, iArr2, i4);
                    int[] iArr3 = this.mBehaviorConsumed;
                    i5 = i2 > 0 ? Math.max(i5, iArr3[0]) : Math.min(i5, iArr3[0]);
                    int[] iArr4 = this.mBehaviorConsumed;
                    i6 = i3 > 0 ? Math.max(i6, iArr4[1]) : Math.min(i6, iArr4[1]);
                    z = true;
                }
            }
        }
        iArr[0] = i5;
        iArr[1] = i6;
        if (z) {
            L(1);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Behavior behavior = layoutParams.f2580a;
        if (behavior != null) {
            float i2 = behavior.i(this, view);
            if (i2 > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(layoutParams.f2580a.h(this, view));
                this.mScrimPaint.setAlpha(m(Math.round(i2 * 255.0f), 0, 255));
                int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, j2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mStatusBarBackground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidate();
        }
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void g(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        Behavior f2;
        boolean z;
        int min;
        int childCount = getChildCount();
        boolean z2 = false;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.j(i6) && (f2 = layoutParams.f()) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    f2.y(this, childAt, view, i2, i3, i4, i5, i6, iArr2);
                    int[] iArr3 = this.mBehaviorConsumed;
                    i7 = i4 > 0 ? Math.max(i7, iArr3[0]) : Math.min(i7, iArr3[0]);
                    if (i5 > 0) {
                        z = true;
                        min = Math.max(i8, this.mBehaviorConsumed[1]);
                    } else {
                        z = true;
                        min = Math.min(i8, this.mBehaviorConsumed[1]);
                    }
                    i8 = min;
                    z2 = z;
                }
            }
        }
        iArr[0] = iArr[0] + i7;
        iArr[1] = iArr[1] + i8;
        if (z2) {
            L(1);
        }
    }

    @VisibleForTesting
    final List<View> getDependencySortedChildren() {
        Q();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    @RestrictTo
    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.a();
    }

    @Nullable
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void h(View view, int i2, int i3, int i4, int i5, int i6) {
        g(view, i2, i3, i4, i5, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean i(View view, View view2, int i2, int i3) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Behavior f2 = layoutParams.f();
                if (f2 != null) {
                    boolean F = f2.F(this, childAt, view, view2, i2, i3);
                    z |= F;
                    layoutParams.r(i3, F);
                } else {
                    layoutParams.r(i3, false);
                }
            }
        }
        return z;
    }

    void l() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        U(false);
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.s(this)) {
            ViewCompat.f0(this);
        }
        this.mIsAttachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        U(false);
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view = this.mNestedScrollingTarget;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDrawStatusBarBackground || this.mStatusBarBackground == null) {
            return;
        }
        WindowInsetsCompat windowInsetsCompat = this.mLastInsets;
        int l2 = windowInsetsCompat != null ? windowInsetsCompat.l() : 0;
        if (l2 > 0) {
            this.mStatusBarBackground.setBounds(0, 0, getWidth(), l2);
            this.mStatusBarBackground.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            U(true);
        }
        boolean P = P(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            U(true);
        }
        return P;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        Behavior f2;
        int v = ViewCompat.v(this);
        int size = this.mDependencySortedChildren.size();
        for (int i6 = 0; i6 < size; i6++) {
            View view = this.mDependencySortedChildren.get(i6);
            if (view.getVisibility() != 8 && ((f2 = ((LayoutParams) view.getLayoutParams()).f()) == null || !f2.q(this, view, v))) {
                M(view, v);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x011c, code lost:
    
        if (r0.r(r30, r20, r11, r21, r23, 0) == false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x011f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 393
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        Behavior f4;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.j(0) && (f4 = layoutParams.f()) != null) {
                    z2 |= f4.s(this, childAt, view, f2, f3, z);
                }
            }
        }
        if (z2) {
            L(1);
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        Behavior f4;
        int childCount = getChildCount();
        boolean z = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.j(0) && (f4 = layoutParams.f()) != null) {
                    z |= f4.t(this, childAt, view, f2, f3);
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        d(view, i2, i3, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        h(view, i2, i3, i4, i5, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        b(view, view2, i2, 0);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        SparseArray sparseArray = savedState.f2599i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            Behavior f2 = C(childAt).f();
            if (id != -1 && f2 != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                f2.C(this, childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable D;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            Behavior f2 = ((LayoutParams) childAt.getLayoutParams()).f();
            if (id != -1 && f2 != null && (D = f2.D(this, childAt)) != null) {
                sparseArray.append(id, D);
            }
        }
        savedState.f2599i = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return i(view, view2, i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        c(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
    
        if (r3 != false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.mBehaviorTouchView
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L17
            boolean r3 = r0.P(r1, r4)
            if (r3 == 0) goto L15
            goto L18
        L15:
            r6 = r5
            goto L2c
        L17:
            r3 = r5
        L18:
            android.view.View r6 = r0.mBehaviorTouchView
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r6 = r6.f()
            if (r6 == 0) goto L15
            android.view.View r7 = r0.mBehaviorTouchView
            boolean r6 = r6.I(r0, r7, r1)
        L2c:
            android.view.View r7 = r0.mBehaviorTouchView
            r8 = 0
            if (r7 != 0) goto L37
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L4a
        L37:
            if (r3 == 0) goto L4a
            long r11 = android.os.SystemClock.uptimeMillis()
            r15 = 0
            r16 = 0
            r13 = 3
            r14 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L4a:
            if (r8 == 0) goto L4f
            r8.recycle()
        L4f:
            if (r2 == r4) goto L54
            r1 = 3
            if (r2 != r1) goto L57
        L54:
            r0.U(r5)
        L57:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(View view) {
        List g2 = this.mChildDag.g(view);
        if (g2 == null || g2.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < g2.size(); i2++) {
            View view2 = (View) g2.get(i2);
            Behavior f2 = ((LayoutParams) view2.getLayoutParams()).f();
            if (f2 != null) {
                f2.m(this, view2, view);
            }
        }
    }

    void q() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            if (E(getChildAt(i2))) {
                z = true;
                break;
            }
            i2++;
        }
        if (z != this.mNeedsPreDrawListener) {
            if (z) {
                l();
            } else {
                T();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Behavior f2 = ((LayoutParams) view.getLayoutParams()).f();
        if (f2 == null || !f2.B(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (!z || this.mDisallowInterceptReset) {
            return;
        }
        U(false);
        this.mDisallowInterceptReset = true;
    }

    @Override // android.view.ViewGroup
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        b0();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(@Nullable Drawable drawable) {
        Drawable drawable2 = this.mStatusBarBackground;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.mStatusBarBackground = mutate;
            if (mutate != null) {
                if (mutate.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                DrawableCompat.m(this.mStatusBarBackground, ViewCompat.v(this));
                this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            ViewCompat.Z(this);
        }
    }

    public void setStatusBarBackgroundColor(@ColorInt int i2) {
        setStatusBarBackground(new ColorDrawable(i2));
    }

    public void setStatusBarBackgroundResource(@DrawableRes int i2) {
        setStatusBarBackground(i2 != 0 ? ContextCompat.e(getContext(), i2) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        Drawable drawable = this.mStatusBarBackground;
        if (drawable == null || drawable.isVisible() == z) {
            return;
        }
        this.mStatusBarBackground.setVisible(z, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: t, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    void u(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            x(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List v(View view) {
        List h2 = this.mChildDag.h(view);
        this.mTempDependenciesList.clear();
        if (h2 != null) {
            this.mTempDependenciesList.addAll(h2);
        }
        return this.mTempDependenciesList;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }

    public List w(View view) {
        List g2 = this.mChildDag.g(view);
        this.mTempDependenciesList.clear();
        if (g2 != null) {
            this.mTempDependenciesList.addAll(g2);
        }
        return this.mTempDependenciesList;
    }

    void x(View view, Rect rect) {
        ViewGroupUtils.a(this, view, rect);
    }

    void y(View view, int i2, Rect rect, Rect rect2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        z(view, i2, rect, rect2, layoutParams, measuredWidth, measuredHeight);
        n(layoutParams, rect2, measuredWidth, measuredHeight);
    }

    public CoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new DirectedAcyclicGraph<>();
        this.mTempList1 = new ArrayList();
        this.mTempDependenciesList = new ArrayList();
        this.mBehaviorConsumed = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        TypedArray obtainStyledAttributes = i2 == 0 ? context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout, 0, R.style.Widget_Support_CoordinatorLayout) : context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout, i2, 0);
        if (i2 == 0) {
            saveAttributeDataForStyleable(context, R.styleable.CoordinatorLayout, attributeSet, obtainStyledAttributes, 0, R.style.Widget_Support_CoordinatorLayout);
        } else {
            saveAttributeDataForStyleable(context, R.styleable.CoordinatorLayout, attributeSet, obtainStyledAttributes, i2, 0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.mKeylines.length;
            for (int i3 = 0; i3 < length; i3++) {
                this.mKeylines[i3] = (int) (r12[i3] * f2);
            }
        }
        this.mStatusBarBackground = obtainStyledAttributes.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        b0();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener());
        if (ViewCompat.t(this) == 0) {
            ViewCompat.s0(this, 1);
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.coordinatorlayout.widget.CoordinatorLayout.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
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
        SparseArray f2599i;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.f2599i = new SparseArray(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                this.f2599i.append(iArr[i2], readParcelableArray[i2]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            SparseArray sparseArray = this.f2599i;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i3 = 0; i3 < size; i3++) {
                iArr[i3] = this.f2599i.keyAt(i3);
                parcelableArr[i3] = (Parcelable) this.f2599i.valueAt(i3);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i2);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        Behavior f2580a;

        /* renamed from: b, reason: collision with root package name */
        boolean f2581b;

        /* renamed from: c, reason: collision with root package name */
        public int f2582c;

        /* renamed from: d, reason: collision with root package name */
        public int f2583d;

        /* renamed from: e, reason: collision with root package name */
        public int f2584e;

        /* renamed from: f, reason: collision with root package name */
        int f2585f;

        /* renamed from: g, reason: collision with root package name */
        public int f2586g;

        /* renamed from: h, reason: collision with root package name */
        public int f2587h;

        /* renamed from: i, reason: collision with root package name */
        int f2588i;

        /* renamed from: j, reason: collision with root package name */
        int f2589j;

        /* renamed from: k, reason: collision with root package name */
        View f2590k;

        /* renamed from: l, reason: collision with root package name */
        View f2591l;

        /* renamed from: m, reason: collision with root package name */
        private boolean f2592m;

        /* renamed from: n, reason: collision with root package name */
        private boolean f2593n;

        /* renamed from: o, reason: collision with root package name */
        private boolean f2594o;

        /* renamed from: p, reason: collision with root package name */
        private boolean f2595p;

        /* renamed from: q, reason: collision with root package name */
        final Rect f2596q;

        /* renamed from: r, reason: collision with root package name */
        Object f2597r;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f2581b = false;
            this.f2582c = 0;
            this.f2583d = 0;
            this.f2584e = -1;
            this.f2585f = -1;
            this.f2586g = 0;
            this.f2587h = 0;
            this.f2596q = new Rect();
        }

        private void n(View view, CoordinatorLayout coordinatorLayout) {
            View findViewById = coordinatorLayout.findViewById(this.f2585f);
            this.f2590k = findViewById;
            if (findViewById == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.f2591l = null;
                    this.f2590k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f2585f) + " to anchor view " + view);
            }
            if (findViewById == coordinatorLayout) {
                if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
                this.f2591l = null;
                this.f2590k = null;
                return;
            }
            for (ViewParent parent = findViewById.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (!coordinatorLayout.isInEditMode()) {
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    this.f2591l = null;
                    this.f2590k = null;
                    return;
                }
                if (parent instanceof View) {
                    findViewById = parent;
                }
            }
            this.f2591l = findViewById;
        }

        private boolean s(View view, int i2) {
            int b2 = GravityCompat.b(((LayoutParams) view.getLayoutParams()).f2586g, i2);
            return b2 != 0 && (GravityCompat.b(this.f2587h, i2) & b2) == b2;
        }

        private boolean t(View view, CoordinatorLayout coordinatorLayout) {
            if (this.f2590k.getId() != this.f2585f) {
                return false;
            }
            View view2 = this.f2590k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.f2591l = null;
                    this.f2590k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.f2591l = view2;
            return true;
        }

        boolean a() {
            return this.f2590k == null && this.f2585f != -1;
        }

        boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            Behavior behavior;
            return view2 == this.f2591l || s(view2, ViewCompat.v(coordinatorLayout)) || ((behavior = this.f2580a) != null && behavior.j(coordinatorLayout, view, view2));
        }

        boolean c() {
            if (this.f2580a == null) {
                this.f2592m = false;
            }
            return this.f2592m;
        }

        View d(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f2585f == -1) {
                this.f2591l = null;
                this.f2590k = null;
                return null;
            }
            if (this.f2590k == null || !t(view, coordinatorLayout)) {
                n(view, coordinatorLayout);
            }
            return this.f2590k;
        }

        public int e() {
            return this.f2585f;
        }

        public Behavior f() {
            return this.f2580a;
        }

        boolean g() {
            return this.f2595p;
        }

        Rect h() {
            return this.f2596q;
        }

        boolean i(CoordinatorLayout coordinatorLayout, View view) {
            boolean z = this.f2592m;
            if (z) {
                return true;
            }
            Behavior behavior = this.f2580a;
            boolean f2 = (behavior != null ? behavior.f(coordinatorLayout, view) : false) | z;
            this.f2592m = f2;
            return f2;
        }

        boolean j(int i2) {
            if (i2 == 0) {
                return this.f2593n;
            }
            if (i2 != 1) {
                return false;
            }
            return this.f2594o;
        }

        void k() {
            this.f2595p = false;
        }

        void l(int i2) {
            r(i2, false);
        }

        void m() {
            this.f2592m = false;
        }

        public void o(Behavior behavior) {
            Behavior behavior2 = this.f2580a;
            if (behavior2 != behavior) {
                if (behavior2 != null) {
                    behavior2.o();
                }
                this.f2580a = behavior;
                this.f2597r = null;
                this.f2581b = true;
                if (behavior != null) {
                    behavior.l(this);
                }
            }
        }

        void p(boolean z) {
            this.f2595p = z;
        }

        void q(Rect rect) {
            this.f2596q.set(rect);
        }

        void r(int i2, boolean z) {
            if (i2 == 0) {
                this.f2593n = z;
            } else {
                if (i2 != 1) {
                    return;
                }
                this.f2594o = z;
            }
        }

        LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2581b = false;
            this.f2582c = 0;
            this.f2583d = 0;
            this.f2584e = -1;
            this.f2585f = -1;
            this.f2586g = 0;
            this.f2587h = 0;
            this.f2596q = new Rect();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout_Layout);
            this.f2582c = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f2585f = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f2583d = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f2584e = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.f2586g = obtainStyledAttributes.getInt(R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.f2587h = obtainStyledAttributes.getInt(R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            boolean hasValue = obtainStyledAttributes.hasValue(R.styleable.CoordinatorLayout_Layout_layout_behavior);
            this.f2581b = hasValue;
            if (hasValue) {
                this.f2580a = CoordinatorLayout.O(context, attributeSet, obtainStyledAttributes.getString(R.styleable.CoordinatorLayout_Layout_layout_behavior));
            }
            obtainStyledAttributes.recycle();
            Behavior behavior = this.f2580a;
            if (behavior != null) {
                behavior.l(this);
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.f2581b = false;
            this.f2582c = 0;
            this.f2583d = 0;
            this.f2584e = -1;
            this.f2585f = -1;
            this.f2586g = 0;
            this.f2587h = 0;
            this.f2596q = new Rect();
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2581b = false;
            this.f2582c = 0;
            this.f2583d = 0;
            this.f2584e = -1;
            this.f2585f = -1;
            this.f2586g = 0;
            this.f2587h = 0;
            this.f2596q = new Rect();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2581b = false;
            this.f2582c = 0;
            this.f2583d = 0;
            this.f2584e = -1;
            this.f2585f = -1;
            this.f2586g = 0;
            this.f2587h = 0;
            this.f2596q = new Rect();
        }
    }
}
