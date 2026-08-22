package androidx.drawerlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.Openable;
import androidx.customview.widget.ViewDragHelper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DrawerLayout extends ViewGroup implements Openable {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.drawerlayout.widget.DrawerLayout";
    private static final boolean ALLOW_EDGE_LOCK = false;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final AccessibilityViewCommand mActionDismiss;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private Rect mChildHitRect;
    private Matrix mChildInvertedMatrix;
    private boolean mChildrenCanceledTouch;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;

    @Nullable
    private DrawerListener mListener;
    private List<DrawerListener> mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList<View> mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;
    private static final int[] THEME_ATTRS = {R.attr.colorPrimaryDark};
    static final int[] LAYOUT_ATTRS = {R.attr.layout_gravity};
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    static final boolean CAN_HIDE_DESCENDANTS = CHILDREN_DISALLOW_INTERCEPT;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = CHILDREN_DISALLOW_INTERCEPT;
    private static boolean sEdgeSizeUsingSystemGestureInsets = CHILDREN_DISALLOW_INTERCEPT;

    class AccessibilityDelegate extends AccessibilityDelegateCompat {

        /* renamed from: d, reason: collision with root package name */
        private final Rect f3624d = new Rect();

        AccessibilityDelegate() {
        }

        private void n(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (DrawerLayout.y(childAt)) {
                    accessibilityNodeInfoCompat.c(childAt);
                }
            }
        }

        private void o(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.f3624d;
            accessibilityNodeInfoCompat2.l(rect);
            accessibilityNodeInfoCompat.d0(rect);
            accessibilityNodeInfoCompat.M0(accessibilityNodeInfoCompat2.U());
            accessibilityNodeInfoCompat.x0(accessibilityNodeInfoCompat2.w());
            accessibilityNodeInfoCompat.h0(accessibilityNodeInfoCompat2.o());
            accessibilityNodeInfoCompat.l0(accessibilityNodeInfoCompat2.r());
            accessibilityNodeInfoCompat.n0(accessibilityNodeInfoCompat2.J());
            accessibilityNodeInfoCompat.q0(accessibilityNodeInfoCompat2.L());
            accessibilityNodeInfoCompat.b0(accessibilityNodeInfoCompat2.E());
            accessibilityNodeInfoCompat.F0(accessibilityNodeInfoCompat2.R());
            accessibilityNodeInfoCompat.a(accessibilityNodeInfoCompat2.i());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.a(view, accessibilityEvent);
            }
            List<CharSequence> text = accessibilityEvent.getText();
            View o2 = DrawerLayout.this.o();
            if (o2 == null) {
                return DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
            }
            CharSequence r2 = DrawerLayout.this.r(DrawerLayout.this.s(o2));
            if (r2 == null) {
                return DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
            }
            text.add(r2);
            return DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.ACCESSIBILITY_CLASS_NAME);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.g(view, accessibilityNodeInfoCompat);
            } else {
                AccessibilityNodeInfoCompat X = AccessibilityNodeInfoCompat.X(accessibilityNodeInfoCompat);
                super.g(view, X);
                accessibilityNodeInfoCompat.H0(view);
                Object A = ViewCompat.A(view);
                if (A instanceof View) {
                    accessibilityNodeInfoCompat.z0((View) A);
                }
                o(accessibilityNodeInfoCompat, X);
                X.Z();
                n(accessibilityNodeInfoCompat, (ViewGroup) view);
            }
            accessibilityNodeInfoCompat.h0(DrawerLayout.ACCESSIBILITY_CLASS_NAME);
            accessibilityNodeInfoCompat.p0(false);
            accessibilityNodeInfoCompat.q0(false);
            accessibilityNodeInfoCompat.a0(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3482e);
            accessibilityNodeInfoCompat.a0(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3483f);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.y(view)) {
                return super.i(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }

    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.g(view, accessibilityNodeInfoCompat);
            if (DrawerLayout.y(view)) {
                return;
            }
            accessibilityNodeInfoCompat.z0(null);
        }
    }

    public interface DrawerListener {
        void a(View view);

        void b(View view);

        void c(int i2);

        void d(View view, float f2);
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void a(View view) {
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void b(View view) {
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void c(int i2) {
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void d(View view, float f2) {
        }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {

        /* renamed from: a, reason: collision with root package name */
        private final int f3635a;

        /* renamed from: b, reason: collision with root package name */
        private ViewDragHelper f3636b;

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f3637c = new Runnable() { // from class: androidx.drawerlayout.widget.DrawerLayout.ViewDragCallback.1
            @Override // java.lang.Runnable
            public void run() {
                ViewDragCallback.this.o();
            }
        };

        ViewDragCallback(int i2) {
            this.f3635a = i2;
        }

        private void n() {
            View m2 = DrawerLayout.this.m(this.f3635a == 3 ? 5 : 3);
            if (m2 != null) {
                DrawerLayout.this.f(m2);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int a(View view, int i2, int i3) {
            if (DrawerLayout.this.c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i2, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i2, width));
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int d(View view) {
            if (DrawerLayout.this.B(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void f(int i2, int i3) {
            View m2 = (i2 & 1) == 1 ? DrawerLayout.this.m(3) : DrawerLayout.this.m(5);
            if (m2 == null || DrawerLayout.this.q(m2) != 0) {
                return;
            }
            this.f3636b.c(m2, i3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean g(int i2) {
            return false;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void h(int i2, int i3) {
            DrawerLayout.this.postDelayed(this.f3637c, 160L);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void i(View view, int i2) {
            ((LayoutParams) view.getLayoutParams()).f3628c = false;
            n();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void j(int i2) {
            DrawerLayout.this.U(i2, this.f3636b.w());
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void k(View view, int i2, int i3, int i4, int i5) {
            float width = (DrawerLayout.this.c(view, 3) ? i2 + r3 : DrawerLayout.this.getWidth() - i2) / view.getWidth();
            DrawerLayout.this.R(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void l(View view, float f2, float f3) {
            int i2;
            float t = DrawerLayout.this.t(view);
            int width = view.getWidth();
            if (DrawerLayout.this.c(view, 3)) {
                i2 = (f2 > 0.0f || (f2 == 0.0f && t > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f2 < 0.0f || (f2 == 0.0f && t > 0.5f)) {
                    width2 -= width;
                }
                i2 = width2;
            }
            this.f3636b.P(i2, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean m(View view, int i2) {
            if (DrawerLayout.this.B(view) && DrawerLayout.this.c(view, this.f3635a) && DrawerLayout.this.q(view) == 0) {
                return DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
            }
            return false;
        }

        void o() {
            View m2;
            int width;
            int y = this.f3636b.y();
            boolean z = this.f3635a == 3;
            if (z) {
                m2 = DrawerLayout.this.m(3);
                width = (m2 != null ? -m2.getWidth() : 0) + y;
            } else {
                m2 = DrawerLayout.this.m(5);
                width = DrawerLayout.this.getWidth() - y;
            }
            if (m2 != null) {
                if (((!z || m2.getLeft() >= width) && (z || m2.getLeft() <= width)) || DrawerLayout.this.q(m2) != 0) {
                    return;
                }
                LayoutParams layoutParams = (LayoutParams) m2.getLayoutParams();
                this.f3636b.R(m2, width, m2.getTop());
                layoutParams.f3628c = DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
                DrawerLayout.this.invalidate();
                n();
                DrawerLayout.this.b();
            }
        }

        public void p() {
            DrawerLayout.this.removeCallbacks(this.f3637c);
        }

        public void q(ViewDragHelper viewDragHelper) {
            this.f3636b = viewDragHelper;
        }
    }

    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, androidx.drawerlayout.R.attr.drawerLayoutStyle);
    }

    private boolean E(float f2, float f3, View view) {
        if (this.mChildHitRect == null) {
            this.mChildHitRect = new Rect();
        }
        view.getHitRect(this.mChildHitRect);
        return this.mChildHitRect.contains((int) f2, (int) f3);
    }

    private void F(Drawable drawable, int i2) {
        if (drawable == null || !DrawableCompat.h(drawable)) {
            return;
        }
        DrawableCompat.m(drawable, i2);
    }

    private Drawable M() {
        int v = ViewCompat.v(this);
        if (v == 0) {
            Drawable drawable = this.mShadowStart;
            if (drawable != null) {
                F(drawable, v);
                return this.mShadowStart;
            }
        } else {
            Drawable drawable2 = this.mShadowEnd;
            if (drawable2 != null) {
                F(drawable2, v);
                return this.mShadowEnd;
            }
        }
        return this.mShadowLeft;
    }

    private Drawable N() {
        int v = ViewCompat.v(this);
        if (v == 0) {
            Drawable drawable = this.mShadowEnd;
            if (drawable != null) {
                F(drawable, v);
                return this.mShadowEnd;
            }
        } else {
            Drawable drawable2 = this.mShadowStart;
            if (drawable2 != null) {
                F(drawable2, v);
                return this.mShadowStart;
            }
        }
        return this.mShadowRight;
    }

    private void O() {
        if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }
        this.mShadowLeftResolved = M();
        this.mShadowRightResolved = N();
    }

    private void S(View view) {
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.y;
        ViewCompat.c0(view, accessibilityActionCompat.b());
        if (!A(view) || q(view) == 2) {
            return;
        }
        ViewCompat.e0(view, accessibilityActionCompat, null, this.mActionDismiss);
    }

    private void T(View view, boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((z || B(childAt)) && !(z && childAt == view)) {
                ViewCompat.s0(childAt, 4);
            } else {
                ViewCompat.s0(childAt, 1);
            }
        }
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent transformedMotionEvent = getTransformedMotionEvent(motionEvent, view);
            boolean dispatchGenericMotionEvent = view.dispatchGenericMotionEvent(transformedMotionEvent);
            transformedMotionEvent.recycle();
            return dispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean dispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return dispatchGenericMotionEvent2;
    }

    private MotionEvent getTransformedMotionEvent(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.mChildInvertedMatrix == null) {
                this.mChildInvertedMatrix = new Matrix();
            }
            matrix.invert(this.mChildInvertedMatrix);
            obtain.transform(this.mChildInvertedMatrix);
        }
        return obtain;
    }

    static String u(int i2) {
        return (i2 & 3) == 3 ? "LEFT" : (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
    }

    private static boolean v(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return CHILDREN_DISALLOW_INTERCEPT;
    }

    private boolean w() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).f3628c) {
                return CHILDREN_DISALLOW_INTERCEPT;
            }
        }
        return false;
    }

    private boolean x() {
        if (o() != null) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        return false;
    }

    static boolean y(View view) {
        if (ViewCompat.t(view) == 4 || ViewCompat.t(view) == 2) {
            return false;
        }
        return CHILDREN_DISALLOW_INTERCEPT;
    }

    public boolean A(View view) {
        if (B(view)) {
            if ((((LayoutParams) view.getLayoutParams()).f3629d & 1) == 1) {
                return CHILDREN_DISALLOW_INTERCEPT;
            }
            return false;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    boolean B(View view) {
        int b2 = GravityCompat.b(((LayoutParams) view.getLayoutParams()).f3626a, ViewCompat.v(view));
        if ((b2 & 3) == 0 && (b2 & 5) == 0) {
            return false;
        }
        return CHILDREN_DISALLOW_INTERCEPT;
    }

    public boolean C(int i2) {
        View m2 = m(i2);
        if (m2 != null) {
            return D(m2);
        }
        return false;
    }

    public boolean D(View view) {
        if (B(view)) {
            if (((LayoutParams) view.getLayoutParams()).f3627b > 0.0f) {
                return CHILDREN_DISALLOW_INTERCEPT;
            }
            return false;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    void G(View view, float f2) {
        float t = t(view);
        float width = view.getWidth();
        int i2 = ((int) (width * f2)) - ((int) (t * width));
        if (!c(view, 3)) {
            i2 = -i2;
        }
        view.offsetLeftAndRight(i2);
        R(view, f2);
    }

    public void H(int i2) {
        I(i2, CHILDREN_DISALLOW_INTERCEPT);
    }

    public void I(int i2, boolean z) {
        View m2 = m(i2);
        if (m2 != null) {
            K(m2, z);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + u(i2));
    }

    public void J(View view) {
        K(view, CHILDREN_DISALLOW_INTERCEPT);
    }

    public void K(View view, boolean z) {
        if (!B(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.mFirstLayout) {
            layoutParams.f3627b = TOUCH_SLOP_SENSITIVITY;
            layoutParams.f3629d = 1;
            T(view, CHILDREN_DISALLOW_INTERCEPT);
            S(view);
        } else if (z) {
            layoutParams.f3629d |= 2;
            if (c(view, 3)) {
                this.mLeftDragger.R(view, 0, view.getTop());
            } else {
                this.mRightDragger.R(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            G(view, TOUCH_SLOP_SENSITIVITY);
            U(0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    public void L(DrawerListener drawerListener) {
        List<DrawerListener> list;
        if (drawerListener == null || (list = this.mListeners) == null) {
            return;
        }
        list.remove(drawerListener);
    }

    public void P(Object obj, boolean z) {
        this.mLastInsets = obj;
        this.mDrawStatusBarBackground = z;
        setWillNotDraw((z || getBackground() != null) ? false : CHILDREN_DISALLOW_INTERCEPT);
        requestLayout();
    }

    public void Q(int i2, int i3) {
        View m2;
        int b2 = GravityCompat.b(i3, ViewCompat.v(this));
        if (i3 == 3) {
            this.mLockModeLeft = i2;
        } else if (i3 == 5) {
            this.mLockModeRight = i2;
        } else if (i3 == 8388611) {
            this.mLockModeStart = i2;
        } else if (i3 == 8388613) {
            this.mLockModeEnd = i2;
        }
        if (i2 != 0) {
            (b2 == 3 ? this.mLeftDragger : this.mRightDragger).b();
        }
        if (i2 != 1) {
            if (i2 == 2 && (m2 = m(b2)) != null) {
                J(m2);
                return;
            }
            return;
        }
        View m3 = m(b2);
        if (m3 != null) {
            f(m3);
        }
    }

    void R(View view, float f2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f2 == layoutParams.f3627b) {
            return;
        }
        layoutParams.f3627b = f2;
        l(view, f2);
    }

    void U(int i2, View view) {
        int i3;
        int B = this.mLeftDragger.B();
        int B2 = this.mRightDragger.B();
        if (B == 1 || B2 == 1) {
            i3 = 1;
        } else {
            i3 = 2;
            if (B != 2 && B2 != 2) {
                i3 = 0;
            }
        }
        if (view != null && i2 == 0) {
            float f2 = ((LayoutParams) view.getLayoutParams()).f3627b;
            if (f2 == 0.0f) {
                j(view);
            } else if (f2 == TOUCH_SLOP_SENSITIVITY) {
                k(view);
            }
        }
        if (i3 != this.mDrawerState) {
            this.mDrawerState = i3;
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.mListeners.get(size).c(i3);
                }
            }
        }
    }

    public void a(DrawerListener drawerListener) {
        if (drawerListener == null) {
            return;
        }
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(drawerListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i2, int i3) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (!B(childAt)) {
                this.mNonDrawerViews.add(childAt);
            } else if (A(childAt)) {
                childAt.addFocusables(arrayList, i2, i3);
                z = CHILDREN_DISALLOW_INTERCEPT;
            }
        }
        if (!z) {
            int size = this.mNonDrawerViews.size();
            for (int i5 = 0; i5 < size; i5++) {
                View view = this.mNonDrawerViews.get(i5);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i2, i3);
                }
            }
        }
        this.mNonDrawerViews.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (n() != null || B(view)) {
            ViewCompat.s0(view, 4);
        } else {
            ViewCompat.s0(view, 1);
        }
        if (CAN_HIDE_DESCENDANTS) {
            return;
        }
        ViewCompat.i0(view, this.mChildAccessibilityDelegate);
    }

    void b() {
        if (this.mChildrenCanceledTouch) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).dispatchTouchEvent(obtain);
        }
        obtain.recycle();
        this.mChildrenCanceledTouch = CHILDREN_DISALLOW_INTERCEPT;
    }

    boolean c(View view, int i2) {
        if ((s(view) & i2) == i2) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams)) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        return false;
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            f2 = Math.max(f2, ((LayoutParams) getChildAt(i2).getLayoutParams()).f3627b);
        }
        this.mScrimOpacity = f2;
        boolean n2 = this.mLeftDragger.n(CHILDREN_DISALLOW_INTERCEPT);
        boolean n3 = this.mRightDragger.n(CHILDREN_DISALLOW_INTERCEPT);
        if (n2 || n3) {
            ViewCompat.Z(this);
        }
    }

    public void d(int i2) {
        e(i2, CHILDREN_DISALLOW_INTERCEPT);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.mScrimOpacity <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            View childAt = getChildAt(i2);
            if (E(x, y, childAt) && !z(childAt) && dispatchTransformedGenericPointerEvent(motionEvent, childAt)) {
                return CHILDREN_DISALLOW_INTERCEPT;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        int height = getHeight();
        boolean z = z(view);
        int width = getWidth();
        int save = canvas.save();
        int i2 = 0;
        if (z) {
            int childCount = getChildCount();
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view && childAt.getVisibility() == 0 && v(childAt) && B(childAt) && childAt.getHeight() >= height) {
                    if (c(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i3) {
                            i3 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i3, 0, width, getHeight());
            i2 = i3;
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas.restoreToCount(save);
        float f2 = this.mScrimOpacity;
        if (f2 > 0.0f && z) {
            this.mScrimPaint.setColor((this.mScrimColor & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f2)) << 24));
            canvas.drawRect(i2, 0.0f, width, getHeight(), this.mScrimPaint);
        } else if (this.mShadowLeftResolved != null && c(view, 3)) {
            int intrinsicWidth = this.mShadowLeftResolved.getIntrinsicWidth();
            int right2 = view.getRight();
            float max = Math.max(0.0f, Math.min(right2 / this.mLeftDragger.y(), TOUCH_SLOP_SENSITIVITY));
            this.mShadowLeftResolved.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.mShadowLeftResolved.setAlpha((int) (max * 255.0f));
            this.mShadowLeftResolved.draw(canvas);
        } else if (this.mShadowRightResolved != null && c(view, 5)) {
            int intrinsicWidth2 = this.mShadowRightResolved.getIntrinsicWidth();
            int left2 = view.getLeft();
            float max2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.mRightDragger.y(), TOUCH_SLOP_SENSITIVITY));
            this.mShadowRightResolved.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.mShadowRightResolved.setAlpha((int) (max2 * 255.0f));
            this.mShadowRightResolved.draw(canvas);
        }
        return drawChild;
    }

    public void e(int i2, boolean z) {
        View m2 = m(i2);
        if (m2 != null) {
            g(m2, z);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + u(i2));
    }

    public void f(View view) {
        g(view, CHILDREN_DISALLOW_INTERCEPT);
    }

    public void g(View view, boolean z) {
        if (!B(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.mFirstLayout) {
            layoutParams.f3627b = 0.0f;
            layoutParams.f3629d = 0;
        } else if (z) {
            layoutParams.f3629d |= 4;
            if (c(view, 3)) {
                this.mLeftDragger.R(view, -view.getWidth(), view.getTop());
            } else {
                this.mRightDragger.R(view, getWidth(), view.getTop());
            }
        } else {
            G(view, 0.0f);
            U(0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public float getDrawerElevation() {
        if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return this.mDrawerElevation;
        }
        return 0.0f;
    }

    @Nullable
    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    public void h() {
        i(false);
    }

    void i(boolean z) {
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (B(childAt) && (!z || layoutParams.f3628c)) {
                z2 |= c(childAt, 3) ? this.mLeftDragger.R(childAt, -childAt.getWidth(), childAt.getTop()) : this.mRightDragger.R(childAt, getWidth(), childAt.getTop());
                layoutParams.f3628c = false;
            }
        }
        this.mLeftCallback.p();
        this.mRightCallback.p();
        if (z2) {
            invalidate();
        }
    }

    void j(View view) {
        View rootView;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.f3629d & 1) == 1) {
            layoutParams.f3629d = 0;
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.mListeners.get(size).b(view);
                }
            }
            T(view, false);
            S(view);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    void k(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.f3629d & 1) == 0) {
            layoutParams.f3629d = 1;
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.mListeners.get(size).a(view);
                }
            }
            T(view, CHILDREN_DISALLOW_INTERCEPT);
            S(view);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    void l(View view, float f2) {
        List<DrawerListener> list = this.mListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mListeners.get(size).d(view, f2);
            }
        }
    }

    View m(int i2) {
        int b2 = GravityCompat.b(i2, ViewCompat.v(this)) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((s(childAt) & 7) == b2) {
                return childAt;
            }
        }
        return null;
    }

    View n() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((((LayoutParams) childAt.getLayoutParams()).f3629d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    View o() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (B(childAt) && D(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = CHILDREN_DISALLOW_INTERCEPT;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = CHILDREN_DISALLOW_INTERCEPT;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDrawStatusBarBackground || this.mStatusBarBackground == null) {
            return;
        }
        Object obj = this.mLastInsets;
        int systemWindowInsetTop = obj != null ? ((WindowInsets) obj).getSystemWindowInsetTop() : 0;
        if (systemWindowInsetTop > 0) {
            this.mStatusBarBackground.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.mStatusBarBackground.draw(canvas);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:
    
        if (r0 != 3) goto L13;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getActionMasked()
            androidx.customview.widget.ViewDragHelper r1 = r6.mLeftDragger
            boolean r1 = r1.Q(r7)
            androidx.customview.widget.ViewDragHelper r2 = r6.mRightDragger
            boolean r2 = r2.Q(r7)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L38
            if (r0 == r2) goto L31
            r7 = 2
            r4 = 3
            if (r0 == r7) goto L1e
            if (r0 == r4) goto L31
            goto L36
        L1e:
            androidx.customview.widget.ViewDragHelper r7 = r6.mLeftDragger
            boolean r7 = r7.e(r4)
            if (r7 == 0) goto L36
            androidx.drawerlayout.widget.DrawerLayout$ViewDragCallback r7 = r6.mLeftCallback
            r7.p()
            androidx.drawerlayout.widget.DrawerLayout$ViewDragCallback r7 = r6.mRightCallback
            r7.p()
            goto L36
        L31:
            r6.i(r2)
            r6.mChildrenCanceledTouch = r3
        L36:
            r7 = r3
            goto L60
        L38:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.mInitialMotionX = r0
            r6.mInitialMotionY = r7
            float r4 = r6.mScrimOpacity
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5d
            androidx.customview.widget.ViewDragHelper r4 = r6.mLeftDragger
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.u(r0, r7)
            if (r7 == 0) goto L5d
            boolean r7 = r6.z(r7)
            if (r7 == 0) goto L5d
            r7 = r2
            goto L5e
        L5d:
            r7 = r3
        L5e:
            r6.mChildrenCanceledTouch = r3
        L60:
            if (r1 != 0) goto L70
            if (r7 != 0) goto L70
            boolean r7 = r6.w()
            if (r7 != 0) goto L70
            boolean r6 = r6.mChildrenCanceledTouch
            if (r6 == 0) goto L6f
            goto L70
        L6f:
            r2 = r3
        L70:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !x()) {
            return super.onKeyDown(i2, keyEvent);
        }
        keyEvent.startTracking();
        return CHILDREN_DISALLOW_INTERCEPT;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        View o2 = o();
        if (o2 != null && q(o2) == 0) {
            h();
        }
        if (o2 != null) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        WindowInsets rootWindowInsets;
        float f2;
        int i6;
        boolean z2 = CHILDREN_DISALLOW_INTERCEPT;
        this.mInLayout = CHILDREN_DISALLOW_INTERCEPT;
        int i7 = i4 - i2;
        int childCount = getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z(childAt)) {
                    int i9 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    childAt.layout(i9, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, childAt.getMeasuredWidth() + i9, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (c(childAt, 3)) {
                        float f3 = measuredWidth;
                        i6 = (-measuredWidth) + ((int) (layoutParams.f3627b * f3));
                        f2 = (measuredWidth + i6) / f3;
                    } else {
                        float f4 = measuredWidth;
                        f2 = (i7 - r11) / f4;
                        i6 = i7 - ((int) (layoutParams.f3627b * f4));
                    }
                    boolean z3 = f2 != layoutParams.f3627b ? z2 : false;
                    int i10 = layoutParams.f3626a & 112;
                    if (i10 == 16) {
                        int i11 = i5 - i3;
                        int i12 = (i11 - measuredHeight) / 2;
                        int i13 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        if (i12 < i13) {
                            i12 = i13;
                        } else {
                            int i14 = i12 + measuredHeight;
                            int i15 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            if (i14 > i11 - i15) {
                                i12 = (i11 - i15) - measuredHeight;
                            }
                        }
                        childAt.layout(i6, i12, measuredWidth + i6, measuredHeight + i12);
                    } else if (i10 != 80) {
                        int i16 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        childAt.layout(i6, i16, measuredWidth + i6, measuredHeight + i16);
                    } else {
                        int i17 = i5 - i3;
                        childAt.layout(i6, (i17 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i6, i17 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                    }
                    if (z3) {
                        R(childAt, f2);
                    }
                    int i18 = layoutParams.f3627b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i18) {
                        childAt.setVisibility(i18);
                    }
                }
            }
            i8++;
            z2 = CHILDREN_DISALLOW_INTERCEPT;
        }
        if (sEdgeSizeUsingSystemGestureInsets && (rootWindowInsets = getRootWindowInsets()) != null) {
            Insets h2 = WindowInsetsCompat.w(rootWindowInsets).h();
            ViewDragHelper viewDragHelper = this.mLeftDragger;
            viewDragHelper.M(Math.max(viewDragHelper.x(), h2.f2920a));
            ViewDragHelper viewDragHelper2 = this.mRightDragger;
            viewDragHelper2.M(Math.max(viewDragHelper2.x(), h2.f2922c));
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode == 0) {
                size = 300;
            }
            if (mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        boolean z = (this.mLastInsets == null || !ViewCompat.s(this)) ? false : CHILDREN_DISALLOW_INTERCEPT;
        int v = ViewCompat.v(this);
        int childCount = getChildCount();
        boolean z2 = false;
        boolean z3 = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z) {
                    int b2 = GravityCompat.b(layoutParams.f3626a, v);
                    if (ViewCompat.s(childAt)) {
                        WindowInsets windowInsets = (WindowInsets) this.mLastInsets;
                        if (b2 == 3) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
                        } else if (b2 == 5) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                        }
                        childAt.dispatchApplyWindowInsets(windowInsets);
                    } else {
                        WindowInsets windowInsets2 = (WindowInsets) this.mLastInsets;
                        if (b2 == 3) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(windowInsets2.getSystemWindowInsetLeft(), windowInsets2.getSystemWindowInsetTop(), 0, windowInsets2.getSystemWindowInsetBottom());
                        } else if (b2 == 5) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(0, windowInsets2.getSystemWindowInsetTop(), windowInsets2.getSystemWindowInsetRight(), windowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = windowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = windowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = windowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = windowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (z(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
                } else {
                    if (!B(childAt)) {
                        throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
                        float r2 = ViewCompat.r(childAt);
                        float f2 = this.mDrawerElevation;
                        if (r2 != f2) {
                            ViewCompat.q0(childAt, f2);
                        }
                    }
                    int s2 = s(childAt) & 7;
                    boolean z4 = s2 == 3 ? CHILDREN_DISALLOW_INTERCEPT : false;
                    if ((z4 && z2) || (!z4 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + u(s2) + " but this " + TAG + " already has a drawer view along that edge");
                    }
                    if (z4) {
                        z2 = CHILDREN_DISALLOW_INTERCEPT;
                    } else {
                        z3 = CHILDREN_DISALLOW_INTERCEPT;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i2, this.mMinDrawerMargin + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, ((ViewGroup.MarginLayoutParams) layoutParams).width), ViewGroup.getChildMeasureSpec(i3, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, ((ViewGroup.MarginLayoutParams) layoutParams).height));
                }
            }
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View m2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        int i2 = savedState.f3630i;
        if (i2 != 0 && (m2 = m(i2)) != null) {
            J(m2);
        }
        int i3 = savedState.f3631j;
        if (i3 != 3) {
            Q(i3, 3);
        }
        int i4 = savedState.f3632k;
        if (i4 != 3) {
            Q(i4, 5);
        }
        int i5 = savedState.f3633l;
        if (i5 != 3) {
            Q(i5, 8388611);
        }
        int i6 = savedState.f3634m;
        if (i6 != 3) {
            Q(i6, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        O();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
            int i3 = layoutParams.f3629d;
            boolean z = CHILDREN_DISALLOW_INTERCEPT;
            boolean z2 = i3 == 1;
            if (i3 != 2) {
                z = false;
            }
            if (z2 || z) {
                savedState.f3630i = layoutParams.f3626a;
                break;
            }
        }
        savedState.f3631j = this.mLockModeLeft;
        savedState.f3632k = this.mLockModeRight;
        savedState.f3633l = this.mLockModeStart;
        savedState.f3634m = this.mLockModeEnd;
        return savedState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
    
        if (q(r7) != 2) goto L20;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            androidx.customview.widget.ViewDragHelper r0 = r6.mLeftDragger
            r0.G(r7)
            androidx.customview.widget.ViewDragHelper r0 = r6.mRightDragger
            r0.G(r7)
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5f
            if (r0 == r2) goto L20
            r7 = 3
            if (r0 == r7) goto L1a
            goto L6d
        L1a:
            r6.i(r2)
            r6.mChildrenCanceledTouch = r1
            goto L6d
        L20:
            float r0 = r7.getX()
            float r7 = r7.getY()
            androidx.customview.widget.ViewDragHelper r3 = r6.mLeftDragger
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.u(r4, r5)
            if (r3 == 0) goto L5a
            boolean r3 = r6.z(r3)
            if (r3 == 0) goto L5a
            float r3 = r6.mInitialMotionX
            float r0 = r0 - r3
            float r3 = r6.mInitialMotionY
            float r7 = r7 - r3
            androidx.customview.widget.ViewDragHelper r3 = r6.mLeftDragger
            int r3 = r3.A()
            float r0 = r0 * r0
            float r7 = r7 * r7
            float r0 = r0 + r7
            int r3 = r3 * r3
            float r7 = (float) r3
            int r7 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r7 >= 0) goto L5a
            android.view.View r7 = r6.n()
            if (r7 == 0) goto L5a
            int r7 = r6.q(r7)
            r0 = 2
            if (r7 != r0) goto L5b
        L5a:
            r1 = r2
        L5b:
            r6.i(r1)
            goto L6d
        L5f:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.mInitialMotionX = r0
            r6.mInitialMotionY = r7
            r6.mChildrenCanceledTouch = r1
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public int p(int i2) {
        int v = ViewCompat.v(this);
        if (i2 == 3) {
            int i3 = this.mLockModeLeft;
            if (i3 != 3) {
                return i3;
            }
            int i4 = v == 0 ? this.mLockModeStart : this.mLockModeEnd;
            if (i4 != 3) {
                return i4;
            }
            return 0;
        }
        if (i2 == 5) {
            int i5 = this.mLockModeRight;
            if (i5 != 3) {
                return i5;
            }
            int i6 = v == 0 ? this.mLockModeEnd : this.mLockModeStart;
            if (i6 != 3) {
                return i6;
            }
            return 0;
        }
        if (i2 == 8388611) {
            int i7 = this.mLockModeStart;
            if (i7 != 3) {
                return i7;
            }
            int i8 = v == 0 ? this.mLockModeLeft : this.mLockModeRight;
            if (i8 != 3) {
                return i8;
            }
            return 0;
        }
        if (i2 != 8388613) {
            return 0;
        }
        int i9 = this.mLockModeEnd;
        if (i9 != 3) {
            return i9;
        }
        int i10 = v == 0 ? this.mLockModeRight : this.mLockModeLeft;
        if (i10 != 3) {
            return i10;
        }
        return 0;
    }

    public int q(View view) {
        if (B(view)) {
            return p(((LayoutParams) view.getLayoutParams()).f3626a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public CharSequence r(int i2) {
        int b2 = GravityCompat.b(i2, ViewCompat.v(this));
        if (b2 == 3) {
            return this.mTitleLeft;
        }
        if (b2 == 5) {
            return this.mTitleRight;
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            i(CHILDREN_DISALLOW_INTERCEPT);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInLayout) {
            return;
        }
        super.requestLayout();
    }

    int s(View view) {
        return GravityCompat.b(((LayoutParams) view.getLayoutParams()).f3626a, ViewCompat.v(this));
    }

    public void setDrawerElevation(float f2) {
        this.mDrawerElevation = f2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (B(childAt)) {
                ViewCompat.q0(childAt, this.mDrawerElevation);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerListener) {
        DrawerListener drawerListener2 = this.mListener;
        if (drawerListener2 != null) {
            L(drawerListener2);
        }
        if (drawerListener != null) {
            a(drawerListener);
        }
        this.mListener = drawerListener;
    }

    public void setDrawerLockMode(int i2) {
        Q(i2, 3);
        Q(i2, 5);
    }

    public void setScrimColor(@ColorInt int i2) {
        this.mScrimColor = i2;
        invalidate();
    }

    public void setStatusBarBackground(@Nullable Drawable drawable) {
        this.mStatusBarBackground = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int i2) {
        this.mStatusBarBackground = new ColorDrawable(i2);
        invalidate();
    }

    float t(View view) {
        return ((LayoutParams) view.getLayoutParams()).f3627b;
    }

    boolean z(View view) {
        if (((LayoutParams) view.getLayoutParams()).f3626a == 0) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        return false;
    }

    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = DEFAULT_SCRIM_COLOR;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = CHILDREN_DISALLOW_INTERCEPT;
        this.mLockModeLeft = 3;
        this.mLockModeRight = 3;
        this.mLockModeStart = 3;
        this.mLockModeEnd = 3;
        this.mShadowStart = null;
        this.mShadowEnd = null;
        this.mShadowLeft = null;
        this.mShadowRight = null;
        this.mActionDismiss = new AccessibilityViewCommand() { // from class: androidx.drawerlayout.widget.DrawerLayout.1
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean a(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                if (!DrawerLayout.this.A(view) || DrawerLayout.this.q(view) == 2) {
                    return false;
                }
                DrawerLayout.this.f(view);
                return DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
            }
        };
        setDescendantFocusability(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE);
        float f2 = getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int) ((64.0f * f2) + 0.5f);
        float f3 = f2 * 400.0f;
        ViewDragCallback viewDragCallback = new ViewDragCallback(3);
        this.mLeftCallback = viewDragCallback;
        ViewDragCallback viewDragCallback2 = new ViewDragCallback(5);
        this.mRightCallback = viewDragCallback2;
        ViewDragHelper o2 = ViewDragHelper.o(this, TOUCH_SLOP_SENSITIVITY, viewDragCallback);
        this.mLeftDragger = o2;
        o2.N(1);
        o2.O(f3);
        viewDragCallback.q(o2);
        ViewDragHelper o3 = ViewDragHelper.o(this, TOUCH_SLOP_SENSITIVITY, viewDragCallback2);
        this.mRightDragger = o3;
        o3.N(2);
        o3.O(f3);
        viewDragCallback2.q(o3);
        setFocusableInTouchMode(CHILDREN_DISALLOW_INTERCEPT);
        ViewCompat.s0(this, 1);
        ViewCompat.i0(this, new AccessibilityDelegate());
        setMotionEventSplittingEnabled(false);
        if (ViewCompat.s(this)) {
            setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: androidx.drawerlayout.widget.DrawerLayout.2
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    ((DrawerLayout) view).P(windowInsets, windowInsets.getSystemWindowInsetTop() > 0 ? DrawerLayout.CHILDREN_DISALLOW_INTERCEPT : false);
                    return windowInsets.consumeSystemWindowInsets();
                }
            });
            setSystemUiVisibility(1280);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(THEME_ATTRS);
            try {
                this.mStatusBarBackground = obtainStyledAttributes.getDrawable(0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, androidx.drawerlayout.R.styleable.DrawerLayout, i2, 0);
        try {
            if (obtainStyledAttributes2.hasValue(androidx.drawerlayout.R.styleable.DrawerLayout_elevation)) {
                this.mDrawerElevation = obtainStyledAttributes2.getDimension(androidx.drawerlayout.R.styleable.DrawerLayout_elevation, 0.0f);
            } else {
                this.mDrawerElevation = getResources().getDimension(androidx.drawerlayout.R.dimen.def_drawer_elevation);
            }
            obtainStyledAttributes2.recycle();
            this.mNonDrawerViews = new ArrayList<>();
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            throw th;
        }
    }

    public void setStatusBarBackground(int i2) {
        this.mStatusBarBackground = i2 != 0 ? ContextCompat.e(getContext(), i2) : null;
        invalidate();
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        public int f3626a;

        /* renamed from: b, reason: collision with root package name */
        float f3627b;

        /* renamed from: c, reason: collision with root package name */
        boolean f3628c;

        /* renamed from: d, reason: collision with root package name */
        int f3629d;

        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3626a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.LAYOUT_ATTRS);
            this.f3626a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f3626a = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.f3626a = 0;
            this.f3626a = layoutParams.f3626a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3626a = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f3626a = 0;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.drawerlayout.widget.DrawerLayout.SavedState.1
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
        int f3630i;

        /* renamed from: j, reason: collision with root package name */
        int f3631j;

        /* renamed from: k, reason: collision with root package name */
        int f3632k;

        /* renamed from: l, reason: collision with root package name */
        int f3633l;

        /* renamed from: m, reason: collision with root package name */
        int f3634m;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3630i = 0;
            this.f3630i = parcel.readInt();
            this.f3631j = parcel.readInt();
            this.f3632k = parcel.readInt();
            this.f3633l = parcel.readInt();
            this.f3634m = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f3630i);
            parcel.writeInt(this.f3631j);
            parcel.writeInt(this.f3632k);
            parcel.writeInt(this.f3633l);
            parcel.writeInt(this.f3634m);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f3630i = 0;
        }
    }
}
