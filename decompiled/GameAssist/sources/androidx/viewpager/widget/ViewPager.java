package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    PagerAdapter mAdapter;
    private List<OnAdapterChangeListener> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    static final int[] LAYOUT_ATTRS = {R.attr.layout_gravity};
    private static final Comparator<ItemInfo> COMPARATOR = new Comparator<ItemInfo>() { // from class: androidx.viewpager.widget.ViewPager.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.f5761b - itemInfo2.f5761b;
        }
    };
    private static final Interpolator sInterpolator = new Interpolator() { // from class: androidx.viewpager.widget.ViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DecorView {
    }

    static class ItemInfo {

        /* renamed from: a, reason: collision with root package name */
        Object f5760a;

        /* renamed from: b, reason: collision with root package name */
        int f5761b;

        /* renamed from: c, reason: collision with root package name */
        boolean f5762c;

        /* renamed from: d, reason: collision with root package name */
        float f5763d;

        /* renamed from: e, reason: collision with root package name */
        float f5764e;

        ItemInfo() {
        }
    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        private boolean n() {
            PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
            return pagerAdapter != null && pagerAdapter.e() > 1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            PagerAdapter pagerAdapter;
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(n());
            if (accessibilityEvent.getEventType() != 4096 || (pagerAdapter = ViewPager.this.mAdapter) == null) {
                return;
            }
            accessibilityEvent.setItemCount(pagerAdapter.e());
            accessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
            accessibilityEvent.setToIndex(ViewPager.this.mCurItem);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.g(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.h0(ViewPager.class.getName());
            accessibilityNodeInfoCompat.E0(n());
            if (ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.a(8192);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean j(View view, int i2, Bundle bundle) {
            if (super.j(view, i2, bundle)) {
                return true;
            }
            if (i2 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.mCurItem + 1);
                return true;
            }
            if (i2 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.mCurItem - 1);
            return true;
        }
    }

    public interface OnAdapterChangeListener {
        void a(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    public interface OnPageChangeListener {
        void d(int i2, float f2, int i3);

        void f(int i2);

        void g(int i2);
    }

    public interface PageTransformer {
        void a(View view, float f2);
    }

    private class PagerObserver extends DataSetObserver {
        PagerObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.h();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.h();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.viewpager.widget.ViewPager.SavedState.1
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
        int f5773i;

        /* renamed from: j, reason: collision with root package name */
        Parcelable f5774j;

        /* renamed from: k, reason: collision with root package name */
        ClassLoader f5775k;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f5773i + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f5773i);
            parcel.writeParcelable(this.f5774j, i2);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f5773i = parcel.readInt();
            this.f5774j = parcel.readParcelable(classLoader);
            this.f5775k = classLoader;
        }
    }

    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void d(int i2, float f2, int i3) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void f(int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void g(int i2) {
        }
    }

    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            boolean z = layoutParams.f5765a;
            return z != layoutParams2.f5765a ? z ? 1 : -1 : layoutParams.f5769e - layoutParams2.f5769e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: androidx.viewpager.widget.ViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.E();
            }
        };
        this.mScrollState = 0;
        v();
    }

    private boolean C(int i2) {
        if (this.mItems.size() == 0) {
            if (this.mFirstLayout) {
                return false;
            }
            this.mCalledSuper = false;
            y(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo t = t();
        int clientWidth = getClientWidth();
        int i3 = this.mPageMargin;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = t.f5761b;
        float f3 = ((i2 / f2) - t.f5764e) / (t.f5763d + (i3 / f2));
        this.mCalledSuper = false;
        y(i5, f3, (int) (i4 * f3));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean D(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.mLastMotionX - f2;
        this.mLastMotionX = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.mFirstOffset * clientWidth;
        float f5 = this.mLastOffset * clientWidth;
        boolean z3 = false;
        ItemInfo itemInfo = this.mItems.get(0);
        ArrayList<ItemInfo> arrayList = this.mItems;
        ItemInfo itemInfo2 = arrayList.get(arrayList.size() - 1);
        if (itemInfo.f5761b != 0) {
            f4 = itemInfo.f5764e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (itemInfo2.f5761b != this.mAdapter.e() - 1) {
            f5 = itemInfo2.f5764e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.mLeftEdge.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.mRightEdge.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        C(i2);
        return z3;
    }

    private void G(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.mItems.isEmpty()) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        ItemInfo u = u(this.mCurItem);
        int min = (int) ((u != null ? Math.min(u.f5764e, this.mLastOffset) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (min != getScrollX()) {
            g(false);
            scrollTo(min, getScrollY());
        }
    }

    private void H() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).f5765a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void K(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean L() {
        this.mActivePointerId = -1;
        o();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        return this.mLeftEdge.isFinished() || this.mRightEdge.isFinished();
    }

    private void M(int i2, boolean z, int i3, boolean z2) {
        ItemInfo u = u(i2);
        int clientWidth = u != null ? (int) (getClientWidth() * Math.max(this.mFirstOffset, Math.min(u.f5764e, this.mLastOffset))) : 0;
        if (z) {
            R(clientWidth, 0, i3);
            if (z2) {
                k(i2);
                return;
            }
            return;
        }
        if (z2) {
            k(i2);
        }
        g(false);
        scrollTo(clientWidth, 0);
        C(clientWidth);
    }

    private void S() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.mDrawingOrderedChildren.add(getChildAt(i2));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void e(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        int i3;
        int i4;
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int e2 = this.mAdapter.e();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        if (itemInfo2 != null) {
            int i5 = itemInfo2.f5761b;
            int i6 = itemInfo.f5761b;
            if (i5 < i6) {
                float f3 = itemInfo2.f5764e + itemInfo2.f5763d + f2;
                int i7 = i5 + 1;
                int i8 = 0;
                while (i7 <= itemInfo.f5761b && i8 < this.mItems.size()) {
                    ItemInfo itemInfo5 = this.mItems.get(i8);
                    while (true) {
                        itemInfo4 = itemInfo5;
                        if (i7 <= itemInfo4.f5761b || i8 >= this.mItems.size() - 1) {
                            break;
                        }
                        i8++;
                        itemInfo5 = this.mItems.get(i8);
                    }
                    while (i7 < itemInfo4.f5761b) {
                        f3 += this.mAdapter.h(i7) + f2;
                        i7++;
                    }
                    itemInfo4.f5764e = f3;
                    f3 += itemInfo4.f5763d + f2;
                    i7++;
                }
            } else if (i5 > i6) {
                int size = this.mItems.size() - 1;
                float f4 = itemInfo2.f5764e;
                while (true) {
                    i5--;
                    if (i5 < itemInfo.f5761b || size < 0) {
                        break;
                    }
                    ItemInfo itemInfo6 = this.mItems.get(size);
                    while (true) {
                        itemInfo3 = itemInfo6;
                        if (i5 >= itemInfo3.f5761b || size <= 0) {
                            break;
                        }
                        size--;
                        itemInfo6 = this.mItems.get(size);
                    }
                    while (i5 > itemInfo3.f5761b) {
                        f4 -= this.mAdapter.h(i5) + f2;
                        i5--;
                    }
                    f4 -= itemInfo3.f5763d + f2;
                    itemInfo3.f5764e = f4;
                }
            }
        }
        int size2 = this.mItems.size();
        float f5 = itemInfo.f5764e;
        int i9 = itemInfo.f5761b;
        int i10 = i9 - 1;
        this.mFirstOffset = i9 == 0 ? f5 : -3.4028235E38f;
        int i11 = e2 - 1;
        this.mLastOffset = i9 == i11 ? (itemInfo.f5763d + f5) - 1.0f : Float.MAX_VALUE;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            ItemInfo itemInfo7 = this.mItems.get(i12);
            while (true) {
                i4 = itemInfo7.f5761b;
                if (i10 <= i4) {
                    break;
                }
                f5 -= this.mAdapter.h(i10) + f2;
                i10--;
            }
            f5 -= itemInfo7.f5763d + f2;
            itemInfo7.f5764e = f5;
            if (i4 == 0) {
                this.mFirstOffset = f5;
            }
            i12--;
            i10--;
        }
        float f6 = itemInfo.f5764e + itemInfo.f5763d + f2;
        int i13 = itemInfo.f5761b + 1;
        int i14 = i2 + 1;
        while (i14 < size2) {
            ItemInfo itemInfo8 = this.mItems.get(i14);
            while (true) {
                i3 = itemInfo8.f5761b;
                if (i13 >= i3) {
                    break;
                }
                f6 += this.mAdapter.h(i13) + f2;
                i13++;
            }
            if (i3 == i11) {
                this.mLastOffset = (itemInfo8.f5763d + f6) - 1.0f;
            }
            itemInfo8.f5764e = f6;
            f6 += itemInfo8.f5763d + f2;
            i14++;
            i13++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    private void g(boolean z) {
        boolean z2 = this.mScrollState == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        C(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            ItemInfo itemInfo = this.mItems.get(i2);
            if (itemInfo.f5762c) {
                itemInfo.f5762c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                ViewCompat.a0(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int i(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.mFlingDistance || Math.abs(i3) <= this.mMinimumVelocity) {
            i2 += (int) (f2 + (i2 >= this.mCurItem ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.mItems.size() <= 0) {
            return i2;
        }
        return Math.max(this.mItems.get(0).f5761b, Math.min(i2, this.mItems.get(r1.size() - 1).f5761b));
    }

    private void j(int i2, float f2, int i3) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.d(i2, f2, i3);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i4);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.d(i2, f2, i3);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.d(i2, f2, i3);
        }
    }

    private void k(int i2) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.g(i2);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i3);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.g(i2);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.g(i2);
        }
    }

    private void l(int i2) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.f(i2);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i3);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.f(i2);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.f(i2);
        }
    }

    private void n(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.mPageTransformerLayerType : 0, null);
        }
    }

    private void o() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private Rect q(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    private ItemInfo t() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        int i3 = 0;
        boolean z = true;
        ItemInfo itemInfo = null;
        int i4 = -1;
        float f4 = 0.0f;
        while (i3 < this.mItems.size()) {
            ItemInfo itemInfo2 = this.mItems.get(i3);
            if (!z && itemInfo2.f5761b != (i2 = i4 + 1)) {
                itemInfo2 = this.mTempItem;
                itemInfo2.f5764e = f2 + f4 + f3;
                itemInfo2.f5761b = i2;
                itemInfo2.f5763d = this.mAdapter.h(i2);
                i3--;
            }
            ItemInfo itemInfo3 = itemInfo2;
            f2 = itemInfo3.f5764e;
            float f5 = itemInfo3.f5763d + f2 + f3;
            if (!z && scrollX < f2) {
                return itemInfo;
            }
            if (scrollX < f5 || i3 == this.mItems.size() - 1) {
                return itemInfo3;
            }
            int i5 = itemInfo3.f5761b;
            float f6 = itemInfo3.f5763d;
            i3++;
            z = false;
            i4 = i5;
            f4 = f6;
            itemInfo = itemInfo3;
        }
        return itemInfo;
    }

    private static boolean w(View view) {
        return view.getClass().getAnnotation(DecorView.class) != null;
    }

    private boolean x(float f2, float f3) {
        return (f2 < ((float) this.mGutterSize) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.mGutterSize)) && f3 < 0.0f);
    }

    private void z(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    boolean A() {
        int i2 = this.mCurItem;
        if (i2 <= 0) {
            return false;
        }
        N(i2 - 1, true);
        return true;
    }

    boolean B() {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || this.mCurItem >= pagerAdapter.e() - 1) {
            return false;
        }
        N(this.mCurItem + 1, true);
        return true;
    }

    void E() {
        F(this.mCurItem);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0060, code lost:
    
        if (r9 == r10) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0066, code lost:
    
        r8 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void F(int r18) {
        /*
            Method dump skipped, instructions count: 615
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.F(int):void");
    }

    public void I(OnAdapterChangeListener onAdapterChangeListener) {
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(onAdapterChangeListener);
        }
    }

    public void J(OnPageChangeListener onPageChangeListener) {
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(onPageChangeListener);
        }
    }

    public void N(int i2, boolean z) {
        this.mPopulatePending = false;
        O(i2, z, false);
    }

    void O(int i2, boolean z, boolean z2) {
        P(i2, z, z2, 0);
    }

    void P(int i2, boolean z, boolean z2, int i3) {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || pagerAdapter.e() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.mCurItem == i2 && this.mItems.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.mAdapter.e()) {
            i2 = this.mAdapter.e() - 1;
        }
        int i4 = this.mOffscreenPageLimit;
        int i5 = this.mCurItem;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.mItems.size(); i6++) {
                this.mItems.get(i6).f5762c = true;
            }
        }
        boolean z3 = this.mCurItem != i2;
        if (!this.mFirstLayout) {
            F(i2);
            M(i2, z, i3, z3);
        } else {
            this.mCurItem = i2;
            if (z3) {
                k(i2);
            }
            requestLayout();
        }
    }

    OnPageChangeListener Q(OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener onPageChangeListener2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = onPageChangeListener;
        return onPageChangeListener2;
    }

    void R(int i2, int i3, int i4) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.isFinished()) {
            scrollX = getScrollX();
        } else {
            scrollX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            g(false);
            E();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float m2 = f3 + (m(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        int min = Math.min(abs > 0 ? Math.round(Math.abs(m2 / abs) * 1000.0f) * 4 : (int) (((Math.abs(i6) / ((f2 * this.mAdapter.h(this.mCurItem)) + this.mPageMargin)) + 1.0f) * 100.0f), MAX_SETTLE_DURATION);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(i5, scrollY, i6, i7, min);
        ViewCompat.Z(this);
    }

    ItemInfo a(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.f5761b = i2;
        itemInfo.f5760a = this.mAdapter.j(this, i2);
        itemInfo.f5763d = this.mAdapter.h(i2);
        if (i3 < 0 || i3 >= this.mItems.size()) {
            this.mItems.add(itemInfo);
        } else {
            this.mItems.add(i3, itemInfo);
        }
        return itemInfo;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i2, int i3) {
        ItemInfo s2;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (s2 = s(childAt)) != null && s2.f5761b == this.mCurItem) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList arrayList) {
        ItemInfo s2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (s2 = s(childAt)) != null && s2.f5761b == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        boolean w = layoutParams2.f5765a | w(view);
        layoutParams2.f5765a = w;
        if (!this.mInLayout) {
            super.addView(view, i2, layoutParams);
        } else {
            if (w) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            layoutParams2.f5768d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public void b(OnAdapterChangeListener onAdapterChangeListener) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(onAdapterChangeListener);
    }

    public void c(OnPageChangeListener onPageChangeListener) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(onPageChangeListener);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.mFirstOffset)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.mLastOffset));
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!C(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.Z(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(int r5) {
        /*
            r4 = this;
            android.view.View r0 = r4.findFocus()
            r1 = 0
            if (r0 != r4) goto L9
        L7:
            r0 = r1
            goto L63
        L9:
            if (r0 == 0) goto L63
            android.view.ViewParent r2 = r0.getParent()
        Lf:
            boolean r3 = r2 instanceof android.view.ViewGroup
            if (r3 == 0) goto L1b
            if (r2 != r4) goto L16
            goto L63
        L16:
            android.view.ViewParent r2 = r2.getParent()
            goto Lf
        L1b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.Class r3 = r0.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r2.append(r3)
            android.view.ViewParent r0 = r0.getParent()
        L2f:
            boolean r3 = r0 instanceof android.view.ViewGroup
            if (r3 == 0) goto L48
            java.lang.String r3 = " => "
            r2.append(r3)
            java.lang.Class r3 = r0.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r2.append(r3)
            android.view.ViewParent r0 = r0.getParent()
            goto L2f
        L48:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "arrowScroll tried to find focus based on non-child current focused view "
            r0.append(r3)
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "ViewPager"
            android.util.Log.e(r2, r0)
            goto L7
        L63:
            android.view.FocusFinder r1 = android.view.FocusFinder.getInstance()
            android.view.View r1 = r1.findNextFocus(r4, r0, r5)
            r2 = 66
            r3 = 17
            if (r1 == 0) goto Lb3
            if (r1 == r0) goto Lb3
            if (r5 != r3) goto L93
            android.graphics.Rect r2 = r4.mTempRect
            android.graphics.Rect r2 = r4.q(r2, r1)
            int r2 = r2.left
            android.graphics.Rect r3 = r4.mTempRect
            android.graphics.Rect r3 = r4.q(r3, r0)
            int r3 = r3.left
            if (r0 == 0) goto L8e
            if (r2 < r3) goto L8e
            boolean r0 = r4.A()
            goto Lca
        L8e:
            boolean r0 = r1.requestFocus()
            goto Lca
        L93:
            if (r5 != r2) goto Lbf
            android.graphics.Rect r2 = r4.mTempRect
            android.graphics.Rect r2 = r4.q(r2, r1)
            int r2 = r2.left
            android.graphics.Rect r3 = r4.mTempRect
            android.graphics.Rect r3 = r4.q(r3, r0)
            int r3 = r3.left
            if (r0 == 0) goto Lae
            if (r2 > r3) goto Lae
            boolean r0 = r4.B()
            goto Lca
        Lae:
            boolean r0 = r1.requestFocus()
            goto Lca
        Lb3:
            if (r5 == r3) goto Lc6
            r0 = 1
            if (r5 != r0) goto Lb9
            goto Lc6
        Lb9:
            if (r5 == r2) goto Lc1
            r0 = 2
            if (r5 != r0) goto Lbf
            goto Lc1
        Lbf:
            r0 = 0
            goto Lca
        Lc1:
            boolean r0 = r4.B()
            goto Lca
        Lc6:
            boolean r0 = r4.A()
        Lca:
            if (r0 == 0) goto Ld3
            int r5 = android.view.SoundEffectConstants.getContantForFocusDirection(r5)
            r4.playSoundEffect(r5)
        Ld3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.d(int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || p(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo s2;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (s2 = s(childAt)) != null && s2.f5761b == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        PagerAdapter pagerAdapter;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        if (overScrollMode != 0 && (overScrollMode != 1 || (pagerAdapter = this.mAdapter) == null || pagerAdapter.e() <= 1)) {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
            return;
        }
        if (this.mLeftEdge.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int width = getWidth();
            canvas.rotate(270.0f);
            canvas.translate((-height) + getPaddingTop(), this.mFirstOffset * width);
            this.mLeftEdge.setSize(height, width);
            z = this.mLeftEdge.draw(canvas);
            canvas.restoreToCount(save);
        }
        if (!this.mRightEdge.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            canvas.rotate(90.0f);
            canvas.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * width2);
            this.mRightEdge.setSize(height2, width2);
            z |= this.mRightEdge.draw(canvas);
            canvas.restoreToCount(save2);
        }
        if (z) {
            ViewCompat.Z(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    protected boolean f(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && f(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    @Nullable
    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.mDrawingOrder == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.mDrawingOrderedChildren.get(i3).getLayoutParams()).f5770f;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    void h() {
        int e2 = this.mAdapter.e();
        this.mExpectedAdapterCount = e2;
        boolean z = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < e2;
        int i2 = this.mCurItem;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < this.mItems.size()) {
            ItemInfo itemInfo = this.mItems.get(i3);
            int f2 = this.mAdapter.f(itemInfo.f5760a);
            if (f2 != -1) {
                if (f2 == -2) {
                    this.mItems.remove(i3);
                    i3--;
                    if (!z2) {
                        this.mAdapter.t(this);
                        z2 = true;
                    }
                    this.mAdapter.b(this, itemInfo.f5761b, itemInfo.f5760a);
                    int i4 = this.mCurItem;
                    if (i4 == itemInfo.f5761b) {
                        i2 = Math.max(0, Math.min(i4, e2 - 1));
                    }
                } else {
                    int i5 = itemInfo.f5761b;
                    if (i5 != f2) {
                        if (i5 == this.mCurItem) {
                            i2 = f2;
                        }
                        itemInfo.f5761b = f2;
                    }
                }
                z = true;
            }
            i3++;
        }
        if (z2) {
            this.mAdapter.d(this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i6).getLayoutParams();
                if (!layoutParams.f5765a) {
                    layoutParams.f5767c = 0.0f;
                }
            }
            O(i2, false, true);
            requestLayout();
        }
    }

    float m(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.mPageMargin <= 0 || this.mMarginDrawable == null || this.mItems.size() <= 0 || this.mAdapter == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f4 = this.mPageMargin / width;
        int i3 = 0;
        ItemInfo itemInfo = this.mItems.get(0);
        float f5 = itemInfo.f5764e;
        int size = this.mItems.size();
        int i4 = itemInfo.f5761b;
        int i5 = this.mItems.get(size - 1).f5761b;
        while (i4 < i5) {
            while (true) {
                i2 = itemInfo.f5761b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                itemInfo = this.mItems.get(i3);
            }
            if (i4 == i2) {
                float f6 = itemInfo.f5764e;
                float f7 = itemInfo.f5763d;
                f2 = (f6 + f7) * width;
                f5 = f6 + f7 + f4;
            } else {
                float h2 = this.mAdapter.h(i4);
                f2 = (f5 + h2) * width;
                f5 += h2 + f4;
            }
            if (this.mPageMargin + f2 > scrollX) {
                f3 = f4;
                this.mMarginDrawable.setBounds(Math.round(f2), this.mTopPageBounds, Math.round(this.mPageMargin + f2), this.mBottomPageBounds);
                this.mMarginDrawable.draw(canvas);
            } else {
                f3 = f4;
            }
            if (f2 > scrollX + r2) {
                return;
            }
            i4++;
            f4 = f3;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            L();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) {
                g(false);
                this.mIsBeingDragged = false;
            } else {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                E();
                this.mIsBeingDragged = true;
                K(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.mActivePointerId;
            if (i2 != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i2);
                float x2 = motionEvent.getX(findPointerIndex);
                float f2 = x2 - this.mLastMotionX;
                float abs = Math.abs(f2);
                float y2 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.mInitialMotionY);
                if (f2 != 0.0f && !x(this.mLastMotionX, f2) && f(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.mLastMotionX = x2;
                    this.mLastMotionY = y2;
                    this.mIsUnableToDrag = true;
                    return false;
                }
                int i3 = this.mTouchSlop;
                if (abs > i3 && abs * 0.5f > abs2) {
                    this.mIsBeingDragged = true;
                    K(true);
                    setScrollState(1);
                    float f3 = this.mInitialMotionX;
                    float f4 = this.mTouchSlop;
                    this.mLastMotionX = f2 > 0.0f ? f3 + f4 : f3 - f4;
                    this.mLastMotionY = y2;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > i3) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && D(x2)) {
                    ViewCompat.Z(this);
                }
            }
        } else if (action == 6) {
            z(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0094  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        int i4;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i2), ViewGroup.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i5 = 0;
        while (true) {
            boolean z = true;
            int i6 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
            if (i5 >= childCount) {
                break;
            }
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8 && (layoutParams2 = (LayoutParams) childAt.getLayoutParams()) != null && layoutParams2.f5765a) {
                int i7 = layoutParams2.f5766b;
                int i8 = i7 & 7;
                int i9 = i7 & 112;
                boolean z2 = i9 == 48 || i9 == 80;
                if (i8 != 3 && i8 != 5) {
                    z = false;
                }
                int i10 = Integer.MIN_VALUE;
                if (z2) {
                    i4 = Integer.MIN_VALUE;
                    i10 = 1073741824;
                } else {
                    i4 = z ? 1073741824 : Integer.MIN_VALUE;
                }
                int i11 = ((ViewGroup.LayoutParams) layoutParams2).width;
                if (i11 != -2) {
                    if (i11 == -1) {
                        i11 = paddingLeft;
                    }
                    i10 = 1073741824;
                } else {
                    i11 = paddingLeft;
                }
                int i12 = ((ViewGroup.LayoutParams) layoutParams2).height;
                if (i12 == -2) {
                    i12 = measuredHeight;
                    i6 = i4;
                } else if (i12 == -1) {
                    i12 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i11, i10), View.MeasureSpec.makeMeasureSpec(i12, i6));
                if (z2) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i5++;
        }
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paddingLeft, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        this.mInLayout = true;
        E();
        this.mInLayout = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((layoutParams = (LayoutParams) childAt2.getLayoutParams()) == null || !layoutParams.f5765a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * layoutParams.f5767c), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), this.mChildHeightMeasureSpec);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        int i5;
        ItemInfo s2;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (s2 = s(childAt)) != null && s2.f5761b == this.mCurItem && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i5;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.n(savedState.f5774j, savedState.f5775k);
            O(savedState.f5773i, false, true);
        } else {
            this.mRestoredCurItem = savedState.f5773i;
            this.mRestoredAdapterState = savedState.f5774j;
            this.mRestoredClassLoader = savedState.f5775k;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5773i = this.mCurItem;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            savedState.f5774j = pagerAdapter.o();
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.mPageMargin;
            G(i2, i4, i6, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0139  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 351
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? A() : d(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? B() : d(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return d(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return d(1);
                }
            }
        }
        return false;
    }

    ItemInfo r(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return s(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    ItemInfo s(View view) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            ItemInfo itemInfo = this.mItems.get(i2);
            if (this.mAdapter.k(view, itemInfo.f5760a)) {
                return itemInfo;
            }
        }
        return null;
    }

    public void setAdapter(@Nullable PagerAdapter pagerAdapter) {
        PagerAdapter pagerAdapter2 = this.mAdapter;
        if (pagerAdapter2 != null) {
            pagerAdapter2.r(null);
            this.mAdapter.t(this);
            for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                ItemInfo itemInfo = this.mItems.get(i2);
                this.mAdapter.b(this, itemInfo.f5761b, itemInfo.f5760a);
            }
            this.mAdapter.d(this);
            this.mItems.clear();
            H();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter3 = this.mAdapter;
        this.mAdapter = pagerAdapter;
        this.mExpectedAdapterCount = 0;
        if (pagerAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.r(this.mObserver);
            this.mPopulatePending = false;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.e();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.n(this.mRestoredAdapterState, this.mRestoredClassLoader);
                O(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (z) {
                requestLayout();
            } else {
                E();
            }
        }
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.mAdapterChangeListeners.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mAdapterChangeListeners.get(i3).a(this, pagerAdapter3, pagerAdapter);
        }
    }

    public void setCurrentItem(int i2) {
        this.mPopulatePending = false;
        O(i2, !this.mFirstLayout, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w(TAG, "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i2;
            E();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setPageMargin(int i2) {
        int i3 = this.mPageMargin;
        this.mPageMargin = i2;
        int width = getWidth();
        G(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(@Nullable Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    void setScrollState(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        if (this.mPageTransformer != null) {
            n(i2 != 0);
        }
        l(i2);
    }

    ItemInfo u(int i2) {
        for (int i3 = 0; i3 < this.mItems.size(); i3++) {
            ItemInfo itemInfo = this.mItems.get(i3);
            if (itemInfo.f5761b == i2) {
                return itemInfo;
            }
        }
        return null;
    }

    void v() {
        setWillNotDraw(false);
        setDescendantFocusability(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f2);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f2);
        this.mCloseEnough = (int) (2.0f * f2);
        this.mDefaultGutterSize = (int) (f2 * 16.0f);
        ViewCompat.i0(this, new MyAccessibilityDelegate());
        if (ViewCompat.t(this) == 0) {
            ViewCompat.s0(this, 1);
        }
        ViewCompat.x0(this, new OnApplyWindowInsetsListener() { // from class: androidx.viewpager.widget.ViewPager.4

            /* renamed from: a, reason: collision with root package name */
            private final Rect f5758a = new Rect();

            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat U = ViewCompat.U(view, windowInsetsCompat);
                if (U.p()) {
                    return U;
                }
                Rect rect = this.f5758a;
                rect.left = U.j();
                rect.top = U.l();
                rect.right = U.k();
                rect.bottom = U.i();
                int childCount = ViewPager.this.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    WindowInsetsCompat f3 = ViewCompat.f(ViewPager.this.getChildAt(i2), U);
                    rect.left = Math.min(f3.j(), rect.left);
                    rect.top = Math.min(f3.l(), rect.top);
                    rect.right = Math.min(f3.k(), rect.right);
                    rect.bottom = Math.min(f3.i(), rect.bottom);
                }
                return U.q(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void y(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.mDecorChildCount
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6c
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r1
        L1b:
            if (r7 >= r6) goto L6c
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r9 = (androidx.viewpager.widget.ViewPager.LayoutParams) r9
            boolean r10 = r9.f5765a
            if (r10 != 0) goto L2c
            goto L69
        L2c:
            int r9 = r9.f5766b
            r9 = r9 & 7
            if (r9 == r2) goto L50
            r10 = 3
            if (r9 == r10) goto L4a
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5d
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
        L46:
            r11 = r9
            r9 = r3
            r3 = r11
            goto L5d
        L4a:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5d
        L50:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
            goto L46
        L5d:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L68
            r8.offsetLeftAndRight(r3)
        L68:
            r3 = r9
        L69:
            int r7 = r7 + 1
            goto L1b
        L6c:
            r12.j(r13, r14, r15)
            androidx.viewpager.widget.ViewPager$PageTransformer r13 = r12.mPageTransformer
            if (r13 == 0) goto La0
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7b:
            if (r1 >= r14) goto La0
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r0 = (androidx.viewpager.widget.ViewPager.LayoutParams) r0
            boolean r0 = r0.f5765a
            if (r0 == 0) goto L8c
            goto L9d
        L8c:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$PageTransformer r3 = r12.mPageTransformer
            r3.a(r15, r0)
        L9d:
            int r1 = r1 + 1
            goto L7b
        La0:
            r12.mCalledSuper = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.y(int, float, int):void");
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        /* renamed from: a, reason: collision with root package name */
        public boolean f5765a;

        /* renamed from: b, reason: collision with root package name */
        public int f5766b;

        /* renamed from: c, reason: collision with root package name */
        float f5767c;

        /* renamed from: d, reason: collision with root package name */
        boolean f5768d;

        /* renamed from: e, reason: collision with root package name */
        int f5769e;

        /* renamed from: f, reason: collision with root package name */
        int f5770f;

        public LayoutParams() {
            super(-1, -1);
            this.f5767c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5767c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.f5766b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(@DrawableRes int i2) {
        setPageMarginDrawable(ContextCompat.e(getContext(), i2));
    }

    public ViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList<>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: androidx.viewpager.widget.ViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.E();
            }
        };
        this.mScrollState = 0;
        v();
    }
}
