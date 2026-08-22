package androidx.slidingpanelayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private boolean mDisplayListReflectionLoaded;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private Method mGetDisplayList;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList<DisableLayerRunnable> mPostedRunnables;
    boolean mPreservedOpenState;
    private Field mRecreateDisplayList;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    class AccessibilityDelegate extends AccessibilityDelegateCompat {

        /* renamed from: d, reason: collision with root package name */
        private final Rect f5348d = new Rect();

        AccessibilityDelegate() {
        }

        private void n(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.f5348d;
            accessibilityNodeInfoCompat2.k(rect);
            accessibilityNodeInfoCompat.c0(rect);
            accessibilityNodeInfoCompat2.l(rect);
            accessibilityNodeInfoCompat.d0(rect);
            accessibilityNodeInfoCompat.M0(accessibilityNodeInfoCompat2.U());
            accessibilityNodeInfoCompat.x0(accessibilityNodeInfoCompat2.w());
            accessibilityNodeInfoCompat.h0(accessibilityNodeInfoCompat2.o());
            accessibilityNodeInfoCompat.l0(accessibilityNodeInfoCompat2.r());
            accessibilityNodeInfoCompat.n0(accessibilityNodeInfoCompat2.J());
            accessibilityNodeInfoCompat.i0(accessibilityNodeInfoCompat2.H());
            accessibilityNodeInfoCompat.p0(accessibilityNodeInfoCompat2.K());
            accessibilityNodeInfoCompat.q0(accessibilityNodeInfoCompat2.L());
            accessibilityNodeInfoCompat.b0(accessibilityNodeInfoCompat2.E());
            accessibilityNodeInfoCompat.F0(accessibilityNodeInfoCompat2.R());
            accessibilityNodeInfoCompat.u0(accessibilityNodeInfoCompat2.O());
            accessibilityNodeInfoCompat.a(accessibilityNodeInfoCompat2.i());
            accessibilityNodeInfoCompat.w0(accessibilityNodeInfoCompat2.v());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat X = AccessibilityNodeInfoCompat.X(accessibilityNodeInfoCompat);
            super.g(view, X);
            n(accessibilityNodeInfoCompat, X);
            X.Z();
            accessibilityNodeInfoCompat.h0(SlidingPaneLayout.class.getName());
            accessibilityNodeInfoCompat.H0(view);
            Object A = ViewCompat.A(view);
            if (A instanceof View) {
                accessibilityNodeInfoCompat.z0((View) A);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i2);
                if (!o(childAt) && childAt.getVisibility() == 0) {
                    ViewCompat.s0(childAt, 1);
                    accessibilityNodeInfoCompat.c(childAt);
                }
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (o(view)) {
                return false;
            }
            return super.i(viewGroup, view, accessibilityEvent);
        }

        public boolean o(View view) {
            return SlidingPaneLayout.this.h(view);
        }
    }

    private class DisableLayerRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final View f5350c;

        DisableLayerRunnable(View view) {
            this.f5350c = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5350c.getParent() == SlidingPaneLayout.this) {
                this.f5350c.setLayerType(0, null);
                SlidingPaneLayout.this.g(this.f5350c);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        DragHelperCallback() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int a(View view, int i2, int i3) {
            LayoutParams layoutParams = (LayoutParams) SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin) + SlidingPaneLayout.this.mSlideableView.getWidth());
                return Math.max(Math.min(i2, width), width - SlidingPaneLayout.this.mSlideRange);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            return Math.min(Math.max(i2, paddingLeft), SlidingPaneLayout.this.mSlideRange + paddingLeft);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int d(View view) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void f(int i2, int i3) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            slidingPaneLayout.mDragHelper.c(slidingPaneLayout.mSlideableView, i3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void i(View view, int i2) {
            SlidingPaneLayout.this.p();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void j(int i2) {
            if (SlidingPaneLayout.this.mDragHelper.B() == 0) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                if (slidingPaneLayout.mSlideOffset != 0.0f) {
                    slidingPaneLayout.e(slidingPaneLayout.mSlideableView);
                    SlidingPaneLayout.this.mPreservedOpenState = true;
                } else {
                    slidingPaneLayout.r(slidingPaneLayout.mSlideableView);
                    SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                    slidingPaneLayout2.d(slidingPaneLayout2.mSlideableView);
                    SlidingPaneLayout.this.mPreservedOpenState = false;
                }
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void k(View view, int i2, int i3, int i4, int i5) {
            SlidingPaneLayout.this.l(i2);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void l(View view, float f2, float f3) {
            int paddingLeft;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                if (f2 < 0.0f || (f2 == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.mSlideRange;
                }
                paddingLeft = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.mSlideableView.getWidth();
            } else {
                paddingLeft = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f2 > 0.0f || (f2 == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    paddingLeft += SlidingPaneLayout.this.mSlideRange;
                }
            }
            SlidingPaneLayout.this.mDragHelper.P(paddingLeft, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean m(View view, int i2) {
            if (SlidingPaneLayout.this.mIsUnableToDrag) {
                return false;
            }
            return ((LayoutParams) view.getLayoutParams()).f5355b;
        }
    }

    public interface PanelSlideListener {
        void a(View view);

        void b(View view);

        void c(View view, float f2);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.slidingpanelayout.widget.SlidingPaneLayout.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: i, reason: collision with root package name */
        boolean f5358i;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f5358i ? 1 : 0);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f5358i = parcel.readInt() != 0;
        }
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
        public void a(View view) {
        }

        @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
        public void b(View view) {
        }

        @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
        public void c(View view, float f2) {
        }
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean b(View view, int i2) {
        if (!this.mFirstLayout && !q(0.0f, i2)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    private void c(View view, float f2, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f2 > 0.0f && i2 != 0) {
            int i3 = (((int) ((((-16777216) & i2) >>> 24) * f2)) << 24) | (i2 & 16777215);
            if (layoutParams.f5357d == null) {
                layoutParams.f5357d = new Paint();
            }
            layoutParams.f5357d.setColorFilter(new PorterDuffColorFilter(i3, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, layoutParams.f5357d);
            }
            g(view);
            return;
        }
        if (view.getLayerType() != 0) {
            Paint paint = layoutParams.f5357d;
            if (paint != null) {
                paint.setColorFilter(null);
            }
            DisableLayerRunnable disableLayerRunnable = new DisableLayerRunnable(view);
            this.mPostedRunnables.add(disableLayerRunnable);
            ViewCompat.a0(this, disableLayerRunnable);
        }
    }

    private boolean n(View view, int i2) {
        if (!this.mFirstLayout && !q(1.0f, i2)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void o(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.i()
            android.view.View r1 = r9.mSlideableView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.slidingpanelayout.widget.SlidingPaneLayout$LayoutParams r1 = (androidx.slidingpanelayout.widget.SlidingPaneLayout.LayoutParams) r1
            boolean r2 = r1.f5356c
            r3 = 0
            if (r2 == 0) goto L1c
            if (r0 == 0) goto L16
            int r1 = r1.rightMargin
            goto L18
        L16:
            int r1 = r1.leftMargin
        L18:
            if (r1 > 0) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = r3
        L1d:
            int r2 = r9.getChildCount()
        L21:
            if (r3 >= r2) goto L57
            android.view.View r4 = r9.getChildAt(r3)
            android.view.View r5 = r9.mSlideableView
            if (r4 != r5) goto L2c
            goto L54
        L2c:
            float r5 = r9.mParallaxOffset
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r6 - r5
            int r7 = r9.mParallaxBy
            float r8 = (float) r7
            float r5 = r5 * r8
            int r5 = (int) r5
            r9.mParallaxOffset = r10
            float r8 = r6 - r10
            float r7 = (float) r7
            float r8 = r8 * r7
            int r7 = (int) r8
            int r5 = r5 - r7
            if (r0 == 0) goto L42
            int r5 = -r5
        L42:
            r4.offsetLeftAndRight(r5)
            if (r1 == 0) goto L54
            float r5 = r9.mParallaxOffset
            if (r0 == 0) goto L4d
            float r5 = r5 - r6
            goto L4f
        L4d:
            float r5 = r6 - r5
        L4f:
            int r6 = r9.mCoveredFadeColor
            r9.c(r4, r5, r6)
        L54:
            int r3 = r3 + 1
            goto L21
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.o(float):void");
    }

    private static boolean s(View view) {
        return view.isOpaque();
    }

    public boolean a() {
        return b(this.mSlideableView, 0);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mDragHelper.n(true)) {
            if (this.mCanSlide) {
                ViewCompat.Z(this);
            } else {
                this.mDragHelper.a();
            }
        }
    }

    void d(View view) {
        PanelSlideListener panelSlideListener = this.mPanelSlideListener;
        if (panelSlideListener != null) {
            panelSlideListener.b(view);
        }
        sendAccessibilityEvent(32);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i2;
        int i3;
        super.draw(canvas);
        Drawable drawable = i() ? this.mShadowDrawableRight : this.mShadowDrawableLeft;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (i()) {
            i3 = childAt.getRight();
            i2 = intrinsicWidth + i3;
        } else {
            int left = childAt.getLeft();
            int i4 = left - intrinsicWidth;
            i2 = left;
            i3 = i4;
        }
        drawable.setBounds(i3, top, i2, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int save = canvas.save();
        if (this.mCanSlide && !layoutParams.f5355b && this.mSlideableView != null) {
            canvas.getClipBounds(this.mTmpRect);
            if (i()) {
                Rect rect = this.mTmpRect;
                rect.left = Math.max(rect.left, this.mSlideableView.getRight());
            } else {
                Rect rect2 = this.mTmpRect;
                rect2.right = Math.min(rect2.right, this.mSlideableView.getLeft());
            }
            canvas.clipRect(this.mTmpRect);
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas.restoreToCount(save);
        return drawChild;
    }

    void e(View view) {
        PanelSlideListener panelSlideListener = this.mPanelSlideListener;
        if (panelSlideListener != null) {
            panelSlideListener.a(view);
        }
        sendAccessibilityEvent(32);
    }

    void f(View view) {
        PanelSlideListener panelSlideListener = this.mPanelSlideListener;
        if (panelSlideListener != null) {
            panelSlideListener.c(view, this.mSlideOffset);
        }
    }

    void g(View view) {
        ViewCompat.w0(view, ((LayoutParams) view.getLayoutParams()).f5357d);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @ColorInt
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    @Px
    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    @ColorInt
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    boolean h(View view) {
        if (view == null) {
            return false;
        }
        return this.mCanSlide && ((LayoutParams) view.getLayoutParams()).f5356c && this.mSlideOffset > 0.0f;
    }

    boolean i() {
        return ViewCompat.v(this) == 1;
    }

    public boolean j() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }

    public boolean k() {
        return this.mCanSlide;
    }

    void l(int i2) {
        if (this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        boolean i3 = i();
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        int width = this.mSlideableView.getWidth();
        if (i3) {
            i2 = (getWidth() - i2) - width;
        }
        float paddingRight = (i2 - ((i3 ? getPaddingRight() : getPaddingLeft()) + (i3 ? ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin))) / this.mSlideRange;
        this.mSlideOffset = paddingRight;
        if (this.mParallaxBy != 0) {
            o(paddingRight);
        }
        if (layoutParams.f5356c) {
            c(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        f(this.mSlideableView);
    }

    public boolean m() {
        return n(this.mSlideableView, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int size = this.mPostedRunnables.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mPostedRunnables.get(i2).run();
        }
        this.mPostedRunnables.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.mCanSlide && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.mPreservedOpenState = !this.mDragHelper.F(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.mCanSlide || (this.mIsUnableToDrag && actionMasked != 0)) {
            this.mDragHelper.b();
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (actionMasked == 3 || actionMasked == 1) {
            this.mDragHelper.b();
            return false;
        }
        if (actionMasked == 0) {
            this.mIsUnableToDrag = false;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.mInitialMotionX = x;
            this.mInitialMotionY = y;
            if (this.mDragHelper.F(this.mSlideableView, (int) x, (int) y) && h(this.mSlideableView)) {
                z = true;
                return this.mDragHelper.Q(motionEvent) || z;
            }
        } else if (actionMasked == 2) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float abs = Math.abs(x2 - this.mInitialMotionX);
            float abs2 = Math.abs(y2 - this.mInitialMotionY);
            if (abs > this.mDragHelper.A() && abs2 > abs) {
                this.mDragHelper.b();
                this.mIsUnableToDrag = true;
                return false;
            }
        }
        z = false;
        if (this.mDragHelper.Q(motionEvent)) {
            return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        boolean i10 = i();
        if (i10) {
            this.mDragHelper.N(2);
        } else {
            this.mDragHelper.N(1);
        }
        int i11 = i4 - i2;
        int paddingRight = i10 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = i10 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.mFirstLayout) {
            this.mSlideOffset = (this.mCanSlide && this.mPreservedOpenState) ? 1.0f : 0.0f;
        }
        int i12 = paddingRight;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (layoutParams.f5355b) {
                    int i14 = i11 - paddingLeft;
                    int min = (Math.min(paddingRight, i14 - this.mOverhangSize) - i12) - (((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
                    this.mSlideRange = min;
                    int i15 = i10 ? ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    layoutParams.f5356c = ((i12 + i15) + min) + (measuredWidth / 2) > i14;
                    int i16 = (int) (min * this.mSlideOffset);
                    i12 += i15 + i16;
                    this.mSlideOffset = i16 / min;
                    i6 = 0;
                } else if (!this.mCanSlide || (i7 = this.mParallaxBy) == 0) {
                    i12 = paddingRight;
                    i6 = 0;
                } else {
                    i6 = (int) ((1.0f - this.mSlideOffset) * i7);
                    i12 = paddingRight;
                }
                if (i10) {
                    i9 = (i11 - i12) + i6;
                    i8 = i9 - measuredWidth;
                } else {
                    i8 = i12 - i6;
                    i9 = i8 + measuredWidth;
                }
                childAt.layout(i8, paddingTop, i9, childAt.getMeasuredHeight() + paddingTop);
                paddingRight += childAt.getWidth();
            }
        }
        if (this.mFirstLayout) {
            if (this.mCanSlide) {
                if (this.mParallaxBy != 0) {
                    o(this.mSlideOffset);
                }
                if (((LayoutParams) this.mSlideableView.getLayoutParams()).f5356c) {
                    c(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
                }
            } else {
                for (int i17 = 0; i17 < childCount; i17++) {
                    c(getChildAt(i17), 0.0f, this.mSliderFadeColor);
                }
            }
            r(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int paddingTop;
        int i4;
        int i5;
        int makeMeasureSpec;
        int i6;
        int i7;
        int makeMeasureSpec2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
        } else if (mode2 == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }
            if (mode2 == 0) {
                size2 = 300;
                mode2 = Integer.MIN_VALUE;
            }
        }
        boolean z = false;
        if (mode2 != Integer.MIN_VALUE) {
            i4 = mode2 != 1073741824 ? 0 : (size2 - getPaddingTop()) - getPaddingBottom();
            paddingTop = i4;
        } else {
            paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            i4 = 0;
        }
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e(TAG, "onMeasure: More than two child views are not supported.");
        }
        this.mSlideableView = null;
        int i8 = 0;
        boolean z2 = false;
        int i9 = paddingLeft;
        float f2 = 0.0f;
        while (true) {
            i5 = 8;
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                layoutParams.f5356c = z;
            } else {
                float f3 = layoutParams.f5354a;
                if (f3 > 0.0f) {
                    f2 += f3;
                    if (((ViewGroup.MarginLayoutParams) layoutParams).width == 0) {
                    }
                }
                int i10 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                int i11 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                int makeMeasureSpec3 = i11 == -2 ? View.MeasureSpec.makeMeasureSpec(paddingLeft - i10, Integer.MIN_VALUE) : i11 == -1 ? View.MeasureSpec.makeMeasureSpec(paddingLeft - i10, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME) : View.MeasureSpec.makeMeasureSpec(i11, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                int i12 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                childAt.measure(makeMeasureSpec3, i12 == -2 ? View.MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE) : i12 == -1 ? View.MeasureSpec.makeMeasureSpec(paddingTop, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME) : View.MeasureSpec.makeMeasureSpec(i12, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (mode2 == Integer.MIN_VALUE && measuredHeight > i4) {
                    i4 = Math.min(measuredHeight, paddingTop);
                }
                i9 -= measuredWidth;
                boolean z3 = i9 < 0;
                layoutParams.f5355b = z3;
                z2 |= z3;
                if (z3) {
                    this.mSlideableView = childAt;
                }
            }
            i8++;
            z = false;
        }
        if (z2 || f2 > 0.0f) {
            int i13 = paddingLeft - this.mOverhangSize;
            int i14 = 0;
            while (i14 < childCount) {
                View childAt2 = getChildAt(i14);
                if (childAt2.getVisibility() != i5) {
                    LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != i5) {
                        boolean z4 = ((ViewGroup.MarginLayoutParams) layoutParams2).width == 0 && layoutParams2.f5354a > 0.0f;
                        int measuredWidth2 = z4 ? 0 : childAt2.getMeasuredWidth();
                        if (!z2 || childAt2 == this.mSlideableView) {
                            if (layoutParams2.f5354a > 0.0f) {
                                if (((ViewGroup.MarginLayoutParams) layoutParams2).width == 0) {
                                    int i15 = ((ViewGroup.MarginLayoutParams) layoutParams2).height;
                                    makeMeasureSpec = i15 == -2 ? View.MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE) : i15 == -1 ? View.MeasureSpec.makeMeasureSpec(paddingTop, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME) : View.MeasureSpec.makeMeasureSpec(i15, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                                } else {
                                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                                }
                                if (z2) {
                                    int i16 = paddingLeft - (((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin);
                                    i6 = i13;
                                    int makeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(i16, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                                    if (measuredWidth2 != i16) {
                                        childAt2.measure(makeMeasureSpec4, makeMeasureSpec);
                                    }
                                    i14++;
                                    i13 = i6;
                                    i5 = 8;
                                } else {
                                    i6 = i13;
                                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2 + ((int) ((layoutParams2.f5354a * Math.max(0, i9)) / f2)), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), makeMeasureSpec);
                                    i14++;
                                    i13 = i6;
                                    i5 = 8;
                                }
                            }
                        } else if (((ViewGroup.MarginLayoutParams) layoutParams2).width < 0 && (measuredWidth2 > i13 || layoutParams2.f5354a > 0.0f)) {
                            if (z4) {
                                int i17 = ((ViewGroup.MarginLayoutParams) layoutParams2).height;
                                if (i17 == -2) {
                                    makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                                    i7 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
                                } else if (i17 == -1) {
                                    i7 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
                                    makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(paddingTop, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                                } else {
                                    i7 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
                                    makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i17, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                                }
                            } else {
                                i7 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
                                makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
                            }
                            childAt2.measure(View.MeasureSpec.makeMeasureSpec(i13, i7), makeMeasureSpec2);
                        }
                    }
                }
                i6 = i13;
                i14++;
                i13 = i6;
                i5 = 8;
            }
        }
        setMeasuredDimension(size, i4 + getPaddingTop() + getPaddingBottom());
        this.mCanSlide = z2;
        if (this.mDragHelper.B() == 0 || z2) {
            return;
        }
        this.mDragHelper.a();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        if (savedState.f5358i) {
            m();
        } else {
            a();
        }
        this.mPreservedOpenState = savedState.f5358i;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5358i = k() ? j() : this.mPreservedOpenState;
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            this.mFirstLayout = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mCanSlide) {
            return super.onTouchEvent(motionEvent);
        }
        this.mDragHelper.G(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.mInitialMotionX = x;
            this.mInitialMotionY = y;
        } else if (actionMasked == 1 && h(this.mSlideableView)) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float f2 = x2 - this.mInitialMotionX;
            float f3 = y2 - this.mInitialMotionY;
            int A = this.mDragHelper.A();
            if ((f2 * f2) + (f3 * f3) < A * A && this.mDragHelper.F(this.mSlideableView, (int) x2, (int) y2)) {
                b(this.mSlideableView, 0);
            }
        }
        return true;
    }

    void p() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    boolean q(float f2, int i2) {
        int paddingLeft;
        if (!this.mCanSlide) {
            return false;
        }
        boolean i3 = i();
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (i3) {
            paddingLeft = (int) (getWidth() - (((getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin) + (f2 * this.mSlideRange)) + this.mSlideableView.getWidth()));
        } else {
            paddingLeft = (int) (getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + (f2 * this.mSlideRange));
        }
        ViewDragHelper viewDragHelper = this.mDragHelper;
        View view = this.mSlideableView;
        if (!viewDragHelper.R(view, paddingLeft, view.getTop())) {
            return false;
        }
        p();
        ViewCompat.Z(this);
        return true;
    }

    void r(View view) {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        View view2 = view;
        boolean i6 = i();
        int width = i6 ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = i6 ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !s(view)) {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        } else {
            i2 = view.getLeft();
            i3 = view.getRight();
            i4 = view.getTop();
            i5 = view.getBottom();
        }
        int childCount = getChildCount();
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            if (childAt == view2) {
                return;
            }
            if (childAt.getVisibility() == 8) {
                z = i6;
            } else {
                z = i6;
                childAt.setVisibility((Math.max(i6 ? paddingLeft : width, childAt.getLeft()) < i2 || Math.max(paddingTop, childAt.getTop()) < i4 || Math.min(i6 ? width : paddingLeft, childAt.getRight()) > i3 || Math.min(height, childAt.getBottom()) > i5) ? 0 : 4);
            }
            i7++;
            view2 = view;
            i6 = z;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.mCanSlide) {
            return;
        }
        this.mPreservedOpenState = view == this.mSlideableView;
    }

    public void setCoveredFadeColor(@ColorInt int i2) {
        this.mCoveredFadeColor = i2;
    }

    public void setPanelSlideListener(@Nullable PanelSlideListener panelSlideListener) {
        this.mPanelSlideListener = panelSlideListener;
    }

    public void setParallaxDistance(@Px int i2) {
        this.mParallaxBy = i2;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(@Nullable Drawable drawable) {
        this.mShadowDrawableLeft = drawable;
    }

    public void setShadowDrawableRight(@Nullable Drawable drawable) {
        this.mShadowDrawableRight = drawable;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int i2) {
        setShadowDrawable(getResources().getDrawable(i2));
    }

    public void setShadowResourceLeft(int i2) {
        setShadowDrawableLeft(ContextCompat.e(getContext(), i2));
    }

    public void setShadowResourceRight(int i2) {
        setShadowDrawableRight(ContextCompat.e(getContext(), i2));
    }

    public void setSliderFadeColor(@ColorInt int i2) {
        this.mSliderFadeColor = i2;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: e, reason: collision with root package name */
        private static final int[] f5353e = {R.attr.layout_weight};

        /* renamed from: a, reason: collision with root package name */
        public float f5354a;

        /* renamed from: b, reason: collision with root package name */
        boolean f5355b;

        /* renamed from: c, reason: collision with root package name */
        boolean f5356c;

        /* renamed from: d, reason: collision with root package name */
        Paint f5357d;

        public LayoutParams() {
            super(-1, -1);
            this.f5354a = 0.0f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f5354a = 0.0f;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f5354a = 0.0f;
        }

        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5354a = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f5353e);
            this.f5354a = obtainStyledAttributes.getFloat(0, 0.0f);
            obtainStyledAttributes.recycle();
        }
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSliderFadeColor = DEFAULT_FADE_COLOR;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList<>();
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mOverhangSize = (int) ((32.0f * f2) + 0.5f);
        setWillNotDraw(false);
        ViewCompat.i0(this, new AccessibilityDelegate());
        ViewCompat.s0(this, 1);
        ViewDragHelper o2 = ViewDragHelper.o(this, 0.5f, new DragHelperCallback());
        this.mDragHelper = o2;
        o2.O(f2 * 400.0f);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
