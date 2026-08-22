package androidx.viewpager2.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.StatefulAdapter;
import com.google.android.material.card.MaterialCardView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public static final int OFFSCREEN_PAGE_LIMIT_DEFAULT = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static boolean sFeatureEnhancedA11yEnabled = true;
    AccessibilityProvider mAccessibilityProvider;
    int mCurrentItem;
    private RecyclerView.AdapterDataObserver mCurrentItemDataSetChangeObserver;
    boolean mCurrentItemDirty;
    private CompositeOnPageChangeCallback mExternalPageChangeCallbacks;
    private FakeDrag mFakeDragger;
    private LinearLayoutManager mLayoutManager;
    private int mOffscreenPageLimit;
    private CompositeOnPageChangeCallback mPageChangeEventDispatcher;
    private PageTransformerAdapter mPageTransformerAdapter;
    private PagerSnapHelper mPagerSnapHelper;
    private Parcelable mPendingAdapterState;
    private int mPendingCurrentItem;
    RecyclerView mRecyclerView;
    private RecyclerView.ItemAnimator mSavedItemAnimator;
    private boolean mSavedItemAnimatorPresent;
    ScrollEventAdapter mScrollEventAdapter;
    private final Rect mTmpChildRect;
    private final Rect mTmpContainerRect;
    private boolean mUserInputEnabled;

    private abstract class AccessibilityProvider {
        private AccessibilityProvider() {
        }

        boolean a() {
            return false;
        }

        boolean b(int i2) {
            return false;
        }

        boolean c(int i2, Bundle bundle) {
            return false;
        }

        boolean d() {
            return false;
        }

        void e(RecyclerView.Adapter adapter) {
        }

        void f(RecyclerView.Adapter adapter) {
        }

        String g() {
            throw new IllegalStateException("Not implemented.");
        }

        void h(CompositeOnPageChangeCallback compositeOnPageChangeCallback, RecyclerView recyclerView) {
        }

        void i(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        void j(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        boolean k(int i2) {
            throw new IllegalStateException("Not implemented.");
        }

        boolean l(int i2, Bundle bundle) {
            throw new IllegalStateException("Not implemented.");
        }

        void m() {
        }

        CharSequence n() {
            throw new IllegalStateException("Not implemented.");
        }

        void o(AccessibilityEvent accessibilityEvent) {
        }

        void p() {
        }

        void q() {
        }

        void r() {
        }

        void s() {
        }
    }

    class BasicAccessibilityProvider extends AccessibilityProvider {
        BasicAccessibilityProvider() {
            super();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public boolean b(int i2) {
            return (i2 == 8192 || i2 == 4096) && !ViewPager2.this.e();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public boolean d() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void j(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (ViewPager2.this.e()) {
                return;
            }
            accessibilityNodeInfoCompat.a0(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3495r);
            accessibilityNodeInfoCompat.a0(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3494q);
            accessibilityNodeInfoCompat.E0(false);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public boolean k(int i2) {
            if (b(i2)) {
                return false;
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public CharSequence n() {
            if (d()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }
    }

    private static abstract class DataSetChangeObserver extends RecyclerView.AdapterDataObserver {
        private DataSetChangeObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public abstract void a();

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void b(int i2, int i3) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void c(int i2, int i3, Object obj) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void d(int i2, int i3) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void e(int i2, int i3, int i4) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void f(int i2, int i3) {
            a();
        }
    }

    private class LinearLayoutManagerImpl extends LinearLayoutManager {
        LinearLayoutManagerImpl(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean B1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            return false;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void V1(RecyclerView.State state, int[] iArr) {
            int offscreenPageLimit = ViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.V1(state, iArr);
                return;
            }
            int pageSize = ViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void W0(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.W0(recycler, state, accessibilityNodeInfoCompat);
            ViewPager2.this.mAccessibilityProvider.j(accessibilityNodeInfoCompat);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean q1(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, Bundle bundle) {
            return ViewPager2.this.mAccessibilityProvider.b(i2) ? ViewPager2.this.mAccessibilityProvider.k(i2) : super.q1(recycler, state, i2, bundle);
        }
    }

    @IntRange
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface OffscreenPageLimit {
    }

    public static abstract class OnPageChangeCallback {
        public void a(int i2) {
        }

        public void b(int i2, float f2, int i3) {
        }

        public void c(int i2) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Orientation {
    }

    class PageAwareAccessibilityProvider extends AccessibilityProvider {

        /* renamed from: b, reason: collision with root package name */
        private final AccessibilityViewCommand f5838b;

        /* renamed from: c, reason: collision with root package name */
        private final AccessibilityViewCommand f5839c;

        /* renamed from: d, reason: collision with root package name */
        private RecyclerView.AdapterDataObserver f5840d;

        PageAwareAccessibilityProvider() {
            super();
            this.f5838b = new AccessibilityViewCommand() { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.1
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean a(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                    PageAwareAccessibilityProvider.this.v(((ViewPager2) view).getCurrentItem() + 1);
                    return true;
                }
            };
            this.f5839c = new AccessibilityViewCommand() { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.2
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean a(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                    PageAwareAccessibilityProvider.this.v(((ViewPager2) view).getCurrentItem() - 1);
                    return true;
                }
            };
        }

        private void t(AccessibilityNodeInfo accessibilityNodeInfo) {
            int i2;
            int i3;
            if (ViewPager2.this.getAdapter() == null) {
                i2 = 0;
                i3 = 0;
            } else if (ViewPager2.this.getOrientation() == 1) {
                i2 = ViewPager2.this.getAdapter().m();
                i3 = 0;
            } else {
                i3 = ViewPager2.this.getAdapter().m();
                i2 = 0;
            }
            AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo).j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.b(i2, i3, false, 0));
        }

        private void u(AccessibilityNodeInfo accessibilityNodeInfo) {
            int m2;
            RecyclerView.Adapter adapter = ViewPager2.this.getAdapter();
            if (adapter == null || (m2 = adapter.m()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.mCurrentItem > 0) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (ViewPager2.this.mCurrentItem < m2 - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public boolean a() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public boolean c(int i2, Bundle bundle) {
            return i2 == 8192 || i2 == 4096;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void e(RecyclerView.Adapter adapter) {
            w();
            if (adapter != null) {
                adapter.I(this.f5840d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void f(RecyclerView.Adapter adapter) {
            if (adapter != null) {
                adapter.K(this.f5840d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public String g() {
            if (a()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void h(CompositeOnPageChangeCallback compositeOnPageChangeCallback, RecyclerView recyclerView) {
            ViewCompat.s0(recyclerView, 2);
            this.f5840d = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.3
                @Override // androidx.viewpager2.widget.ViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void a() {
                    PageAwareAccessibilityProvider.this.w();
                }
            };
            if (ViewCompat.t(ViewPager2.this) == 0) {
                ViewCompat.s0(ViewPager2.this, 1);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
            t(accessibilityNodeInfo);
            u(accessibilityNodeInfo);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public boolean l(int i2, Bundle bundle) {
            if (!c(i2, bundle)) {
                throw new IllegalStateException();
            }
            v(i2 == 8192 ? ViewPager2.this.getCurrentItem() - 1 : ViewPager2.this.getCurrentItem() + 1);
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void m() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void o(AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName(g());
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void p() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void q() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void r() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void s() {
            w();
        }

        void v(int i2) {
            if (ViewPager2.this.e()) {
                ViewPager2.this.k(i2, true);
            }
        }

        void w() {
            int m2;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i2 = R.id.accessibilityActionPageLeft;
            ViewCompat.c0(viewPager2, R.id.accessibilityActionPageLeft);
            ViewCompat.c0(viewPager2, R.id.accessibilityActionPageRight);
            ViewCompat.c0(viewPager2, R.id.accessibilityActionPageUp);
            ViewCompat.c0(viewPager2, R.id.accessibilityActionPageDown);
            if (ViewPager2.this.getAdapter() == null || (m2 = ViewPager2.this.getAdapter().m()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.getOrientation() != 0) {
                if (ViewPager2.this.mCurrentItem < m2 - 1) {
                    ViewCompat.e0(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibilityActionPageDown, null), null, this.f5838b);
                }
                if (ViewPager2.this.mCurrentItem > 0) {
                    ViewCompat.e0(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibilityActionPageUp, null), null, this.f5839c);
                    return;
                }
                return;
            }
            boolean d2 = ViewPager2.this.d();
            int i3 = d2 ? 16908360 : 16908361;
            if (d2) {
                i2 = 16908361;
            }
            if (ViewPager2.this.mCurrentItem < m2 - 1) {
                ViewCompat.e0(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i3, null), null, this.f5838b);
            }
            if (ViewPager2.this.mCurrentItem > 0) {
                ViewCompat.e0(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, null), null, this.f5839c);
            }
        }
    }

    public interface PageTransformer {
        void a(View view, float f2);
    }

    private class PagerSnapHelperImpl extends PagerSnapHelper {
        PagerSnapHelperImpl() {
        }

        @Override // androidx.recyclerview.widget.PagerSnapHelper, androidx.recyclerview.widget.SnapHelper
        public View h(RecyclerView.LayoutManager layoutManager) {
            if (ViewPager2.this.c()) {
                return null;
            }
            return super.h(layoutManager);
        }
    }

    private class RecyclerViewImpl extends RecyclerView {
        RecyclerViewImpl(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public CharSequence getAccessibilityClassName() {
            return ViewPager2.this.mAccessibilityProvider.d() ? ViewPager2.this.mAccessibilityProvider.n() : super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setToIndex(ViewPager2.this.mCurrentItem);
            ViewPager2.this.mAccessibilityProvider.o(accessibilityEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.e() && super.onInterceptTouchEvent(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.e() && super.onTouchEvent(motionEvent);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ScrollState {
    }

    private static class SmoothScrollToPosition implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final int f5849c;

        /* renamed from: h, reason: collision with root package name */
        private final RecyclerView f5850h;

        SmoothScrollToPosition(int i2, RecyclerView recyclerView) {
            this.f5849c = i2;
            this.f5850h = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f5850h.s1(this.f5849c);
        }
    }

    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.ViewPager2.1
            @Override // androidx.viewpager2.widget.ViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void a() {
                ViewPager2 viewPager2 = ViewPager2.this;
                viewPager2.mCurrentItemDirty = true;
                viewPager2.mScrollEventAdapter.l();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        b(context, attributeSet);
    }

    private RecyclerView.OnChildAttachStateChangeListener a() {
        return new RecyclerView.OnChildAttachStateChangeListener() { // from class: androidx.viewpager2.widget.ViewPager2.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void a(View view) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void b(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }
        };
    }

    private void b(Context context, AttributeSet attributeSet) {
        this.mAccessibilityProvider = sFeatureEnhancedA11yEnabled ? new PageAwareAccessibilityProvider() : new BasicAccessibilityProvider();
        RecyclerViewImpl recyclerViewImpl = new RecyclerViewImpl(context);
        this.mRecyclerView = recyclerViewImpl;
        recyclerViewImpl.setId(ViewCompat.i());
        this.mRecyclerView.setDescendantFocusability(131072);
        LinearLayoutManagerImpl linearLayoutManagerImpl = new LinearLayoutManagerImpl(context);
        this.mLayoutManager = linearLayoutManagerImpl;
        this.mRecyclerView.setLayoutManager(linearLayoutManagerImpl);
        this.mRecyclerView.setScrollingTouchSlop(1);
        l(context, attributeSet);
        this.mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mRecyclerView.j(a());
        ScrollEventAdapter scrollEventAdapter = new ScrollEventAdapter(this);
        this.mScrollEventAdapter = scrollEventAdapter;
        this.mFakeDragger = new FakeDrag(this, scrollEventAdapter, this.mRecyclerView);
        PagerSnapHelperImpl pagerSnapHelperImpl = new PagerSnapHelperImpl();
        this.mPagerSnapHelper = pagerSnapHelperImpl;
        pagerSnapHelperImpl.b(this.mRecyclerView);
        this.mRecyclerView.l(this.mScrollEventAdapter);
        CompositeOnPageChangeCallback compositeOnPageChangeCallback = new CompositeOnPageChangeCallback(3);
        this.mPageChangeEventDispatcher = compositeOnPageChangeCallback;
        this.mScrollEventAdapter.o(compositeOnPageChangeCallback);
        OnPageChangeCallback onPageChangeCallback = new OnPageChangeCallback() { // from class: androidx.viewpager2.widget.ViewPager2.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void a(int i2) {
                if (i2 == 0) {
                    ViewPager2.this.o();
                }
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void c(int i2) {
                ViewPager2 viewPager2 = ViewPager2.this;
                if (viewPager2.mCurrentItem != i2) {
                    viewPager2.mCurrentItem = i2;
                    viewPager2.mAccessibilityProvider.q();
                }
            }
        };
        OnPageChangeCallback onPageChangeCallback2 = new OnPageChangeCallback() { // from class: androidx.viewpager2.widget.ViewPager2.3
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void c(int i2) {
                ViewPager2.this.clearFocus();
                if (ViewPager2.this.hasFocus()) {
                    ViewPager2.this.mRecyclerView.requestFocus(2);
                }
            }
        };
        this.mPageChangeEventDispatcher.d(onPageChangeCallback);
        this.mPageChangeEventDispatcher.d(onPageChangeCallback2);
        this.mAccessibilityProvider.h(this.mPageChangeEventDispatcher, this.mRecyclerView);
        this.mPageChangeEventDispatcher.d(this.mExternalPageChangeCallbacks);
        PageTransformerAdapter pageTransformerAdapter = new PageTransformerAdapter(this.mLayoutManager);
        this.mPageTransformerAdapter = pageTransformerAdapter;
        this.mPageChangeEventDispatcher.d(pageTransformerAdapter);
        RecyclerView recyclerView = this.mRecyclerView;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    private void f(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            adapter.I(this.mCurrentItemDataSetChangeObserver);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void i() {
        RecyclerView.Adapter adapter;
        if (this.mPendingCurrentItem == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            if (adapter instanceof StatefulAdapter) {
                ((StatefulAdapter) adapter).f(parcelable);
            }
            this.mPendingAdapterState = null;
        }
        int max = Math.max(0, Math.min(this.mPendingCurrentItem, adapter.m() - 1));
        this.mCurrentItem = max;
        this.mPendingCurrentItem = -1;
        this.mRecyclerView.l1(max);
        this.mAccessibilityProvider.m();
    }

    private void l(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.viewpager2.R.styleable.ViewPager2);
        saveAttributeDataForStyleable(context, androidx.viewpager2.R.styleable.ViewPager2, attributeSet, obtainStyledAttributes, 0, 0);
        try {
            setOrientation(obtainStyledAttributes.getInt(androidx.viewpager2.R.styleable.ViewPager2_android_orientation, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void m(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            adapter.K(this.mCurrentItemDataSetChangeObserver);
        }
    }

    public boolean c() {
        return this.mFakeDragger.a();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return this.mRecyclerView.canScrollHorizontally(i2);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        return this.mRecyclerView.canScrollVertically(i2);
    }

    boolean d() {
        return this.mLayoutManager.f0() == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).f5846c;
            sparseArray.put(this.mRecyclerView.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        i();
    }

    public boolean e() {
        return this.mUserInputEnabled;
    }

    public void g(OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.d(onPageChangeCallback);
    }

    @Override // android.view.ViewGroup, android.view.View
    @RequiresApi
    public CharSequence getAccessibilityClassName() {
        return this.mAccessibilityProvider.a() ? this.mAccessibilityProvider.g() : super.getAccessibilityClassName();
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    public int getItemDecorationCount() {
        return this.mRecyclerView.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getOrientation() {
        return this.mLayoutManager.y2();
    }

    int getPageSize() {
        int height;
        int paddingBottom;
        RecyclerView recyclerView = this.mRecyclerView;
        if (getOrientation() == 0) {
            height = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            paddingBottom = recyclerView.getPaddingRight();
        } else {
            height = recyclerView.getHeight() - recyclerView.getPaddingTop();
            paddingBottom = recyclerView.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.mScrollEventAdapter.h();
    }

    public void h() {
        if (this.mPageTransformerAdapter.d() == null) {
            return;
        }
        double g2 = this.mScrollEventAdapter.g();
        int i2 = (int) g2;
        float f2 = (float) (g2 - i2);
        this.mPageTransformerAdapter.b(i2, f2, Math.round(getPageSize() * f2));
    }

    public void j(int i2, boolean z) {
        if (c()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        k(i2, z);
    }

    void k(int i2, boolean z) {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null) {
            if (this.mPendingCurrentItem != -1) {
                this.mPendingCurrentItem = Math.max(i2, 0);
                return;
            }
            return;
        }
        if (adapter.m() <= 0) {
            return;
        }
        int min = Math.min(Math.max(i2, 0), adapter.m() - 1);
        if (min == this.mCurrentItem && this.mScrollEventAdapter.j()) {
            return;
        }
        int i3 = this.mCurrentItem;
        if (min == i3 && z) {
            return;
        }
        double d2 = i3;
        this.mCurrentItem = min;
        this.mAccessibilityProvider.q();
        if (!this.mScrollEventAdapter.j()) {
            d2 = this.mScrollEventAdapter.g();
        }
        this.mScrollEventAdapter.m(min, z);
        if (!z) {
            this.mRecyclerView.l1(min);
            return;
        }
        double d3 = min;
        if (Math.abs(d3 - d2) <= 3.0d) {
            this.mRecyclerView.s1(min);
            return;
        }
        this.mRecyclerView.l1(d3 > d2 ? min - 3 : min + 3);
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.post(new SmoothScrollToPosition(min, recyclerView));
    }

    public void n(OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.e(onPageChangeCallback);
    }

    void o() {
        PagerSnapHelper pagerSnapHelper = this.mPagerSnapHelper;
        if (pagerSnapHelper == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View h2 = pagerSnapHelper.h(this.mLayoutManager);
        if (h2 == null) {
            return;
        }
        int p0 = this.mLayoutManager.p0(h2);
        if (p0 != this.mCurrentItem && getScrollState() == 0) {
            this.mPageChangeEventDispatcher.c(p0);
        }
        this.mCurrentItemDirty = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.mAccessibilityProvider.i(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = getPaddingLeft();
        this.mTmpContainerRect.right = (i4 - i2) - getPaddingRight();
        this.mTmpContainerRect.top = getPaddingTop();
        this.mTmpContainerRect.bottom = (i5 - i3) - getPaddingBottom();
        Gravity.apply(MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START, measuredWidth, measuredHeight, this.mTmpContainerRect, this.mTmpChildRect);
        RecyclerView recyclerView = this.mRecyclerView;
        Rect rect = this.mTmpChildRect;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.mCurrentItemDirty) {
            o();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        measureChild(this.mRecyclerView, i2, i3);
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        int measuredState = this.mRecyclerView.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, measuredState << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPendingCurrentItem = savedState.f5847h;
        this.mPendingAdapterState = savedState.f5848i;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5846c = this.mRecyclerView.getId();
        int i2 = this.mPendingCurrentItem;
        if (i2 == -1) {
            i2 = this.mCurrentItem;
        }
        savedState.f5847h = i2;
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            savedState.f5848i = parcelable;
        } else {
            Object adapter = this.mRecyclerView.getAdapter();
            if (adapter instanceof StatefulAdapter) {
                savedState.f5848i = ((StatefulAdapter) adapter).a();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        return this.mAccessibilityProvider.c(i2, bundle) ? this.mAccessibilityProvider.l(i2, bundle) : super.performAccessibilityAction(i2, bundle);
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = this.mRecyclerView.getAdapter();
        this.mAccessibilityProvider.f(adapter2);
        m(adapter2);
        this.mRecyclerView.setAdapter(adapter);
        this.mCurrentItem = 0;
        i();
        this.mAccessibilityProvider.e(adapter);
        f(adapter);
    }

    public void setCurrentItem(int i2) {
        j(i2, true);
    }

    @Override // android.view.View
    @RequiresApi
    public void setLayoutDirection(int i2) {
        super.setLayoutDirection(i2);
        this.mAccessibilityProvider.p();
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1 && i2 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.mOffscreenPageLimit = i2;
        this.mRecyclerView.requestLayout();
    }

    public void setOrientation(int i2) {
        this.mLayoutManager.M2(i2);
        this.mAccessibilityProvider.r();
    }

    public void setPageTransformer(@Nullable PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            if (!this.mSavedItemAnimatorPresent) {
                this.mSavedItemAnimator = this.mRecyclerView.getItemAnimator();
                this.mSavedItemAnimatorPresent = true;
            }
            this.mRecyclerView.setItemAnimator(null);
        } else if (this.mSavedItemAnimatorPresent) {
            this.mRecyclerView.setItemAnimator(this.mSavedItemAnimator);
            this.mSavedItemAnimator = null;
            this.mSavedItemAnimatorPresent = false;
        }
        if (pageTransformer == this.mPageTransformerAdapter.d()) {
            return;
        }
        this.mPageTransformerAdapter.e(pageTransformer);
        h();
    }

    public void setUserInputEnabled(boolean z) {
        this.mUserInputEnabled = z;
        this.mAccessibilityProvider.s();
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.viewpager2.widget.ViewPager2.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return createFromParcel(parcel, null);
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

        /* renamed from: c, reason: collision with root package name */
        int f5846c;

        /* renamed from: h, reason: collision with root package name */
        int f5847h;

        /* renamed from: i, reason: collision with root package name */
        Parcelable f5848i;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            a(parcel, classLoader);
        }

        private void a(Parcel parcel, ClassLoader classLoader) {
            this.f5846c = parcel.readInt();
            this.f5847h = parcel.readInt();
            this.f5848i = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f5846c);
            parcel.writeInt(this.f5847h);
            parcel.writeParcelable(this.f5848i, i2);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.ViewPager2.1
            @Override // androidx.viewpager2.widget.ViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void a() {
                ViewPager2 viewPager2 = ViewPager2.this;
                viewPager2.mCurrentItemDirty = true;
                viewPager2.mScrollEventAdapter.l();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        b(context, attributeSet);
    }

    @RequiresApi
    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.ViewPager2.1
            @Override // androidx.viewpager2.widget.ViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void a() {
                ViewPager2 viewPager2 = ViewPager2.this;
                viewPager2.mCurrentItemDirty = true;
                viewPager2.mScrollEventAdapter.l();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        b(context, attributeSet);
    }
}
