package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.widget.AdapterHelper;
import androidx.recyclerview.widget.ChildHelper;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.ViewInfoStore;
import com.google.android.gms.common.api.Api;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2, NestedScrollingChild3 {
    static final boolean DEBUG = false;
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final int MAX_SCROLL_DURATION = 2000;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;

    @NonNull
    private EdgeEffectFactory mEdgeEffectFactory;
    boolean mEnableFastScroller;

    @VisibleForTesting
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private OnItemTouchListener mInterceptingOnItemTouchListener;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;

    @VisibleForTesting
    LayoutManager mLayout;
    private int mLayoutOrScrollCounter;
    boolean mLayoutSuppressed;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;

    @VisibleForTesting
    final List<ViewHolder> mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    final int[] mReusableIntPair;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    private static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    static final boolean POST_UPDATES_ON_ANIMATION = true;
    static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;

    public static abstract class Adapter<VH extends ViewHolder> {

        /* renamed from: a, reason: collision with root package name */
        private final AdapterDataObservable f5154a = new AdapterDataObservable();

        /* renamed from: b, reason: collision with root package name */
        private boolean f5155b = false;

        public abstract void A(ViewHolder viewHolder, int i2);

        public void B(ViewHolder viewHolder, int i2, List list) {
            A(viewHolder, i2);
        }

        public abstract ViewHolder C(ViewGroup viewGroup, int i2);

        public void D(RecyclerView recyclerView) {
        }

        public boolean E(ViewHolder viewHolder) {
            return false;
        }

        public void F(ViewHolder viewHolder) {
        }

        public void G(ViewHolder viewHolder) {
        }

        public void H(ViewHolder viewHolder) {
        }

        public void I(AdapterDataObserver adapterDataObserver) {
            this.f5154a.registerObserver(adapterDataObserver);
        }

        public void J(boolean z) {
            if (p()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f5155b = z;
        }

        public void K(AdapterDataObserver adapterDataObserver) {
            this.f5154a.unregisterObserver(adapterDataObserver);
        }

        public final void k(ViewHolder viewHolder, int i2) {
            viewHolder.f5254c = i2;
            if (q()) {
                viewHolder.f5256e = n(i2);
            }
            viewHolder.G(1, 519);
            TraceCompat.a(RecyclerView.TRACE_BIND_VIEW_TAG);
            B(viewHolder, i2, viewHolder.p());
            viewHolder.e();
            ViewGroup.LayoutParams layoutParams = viewHolder.f5252a.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).f5193c = true;
            }
            TraceCompat.b();
        }

        public final ViewHolder l(ViewGroup viewGroup, int i2) {
            try {
                TraceCompat.a(RecyclerView.TRACE_CREATE_VIEW_TAG);
                ViewHolder C = C(viewGroup, i2);
                if (C.f5252a.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                C.f5257f = i2;
                return C;
            } finally {
                TraceCompat.b();
            }
        }

        public abstract int m();

        public long n(int i2) {
            return -1L;
        }

        public int o(int i2) {
            return 0;
        }

        public final boolean p() {
            return this.f5154a.a();
        }

        public final boolean q() {
            return this.f5155b;
        }

        public final void r() {
            this.f5154a.b();
        }

        public final void s(int i2) {
            this.f5154a.d(i2, 1);
        }

        public final void t(int i2, Object obj) {
            this.f5154a.e(i2, 1, obj);
        }

        public final void u(int i2, int i3) {
            this.f5154a.c(i2, i3);
        }

        public final void v(int i2, int i3) {
            this.f5154a.d(i2, i3);
        }

        public final void w(int i2, int i3, Object obj) {
            this.f5154a.e(i2, i3, obj);
        }

        public final void x(int i2, int i3) {
            this.f5154a.f(i2, i3);
        }

        public final void y(int i2, int i3) {
            this.f5154a.g(i2, i3);
        }

        public void z(RecyclerView recyclerView) {
        }
    }

    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        AdapterDataObservable() {
        }

        public boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).a();
            }
        }

        public void c(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).e(i2, i3, 1);
            }
        }

        public void d(int i2, int i3) {
            e(i2, i3, null);
        }

        public void e(int i2, int i3, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).c(i2, i3, obj);
            }
        }

        public void f(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).d(i2, i3);
            }
        }

        public void g(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).f(i2, i3);
            }
        }
    }

    public static abstract class AdapterDataObserver {
        public void a() {
        }

        public void b(int i2, int i3) {
        }

        public void c(int i2, int i3, Object obj) {
            b(i2, i3);
        }

        public void d(int i2, int i3) {
        }

        public void e(int i2, int i3, int i4) {
        }

        public void f(int i2, int i3) {
        }
    }

    public interface ChildDrawingOrderCallback {
        int a(int i2, int i3);
    }

    public static class EdgeEffectFactory {

        @Retention(RetentionPolicy.SOURCE)
        public @interface EdgeDirection {
        }

        protected EdgeEffect a(RecyclerView recyclerView, int i2) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class ItemAnimator {

        /* renamed from: a, reason: collision with root package name */
        private ItemAnimatorListener f5156a = null;

        /* renamed from: b, reason: collision with root package name */
        private ArrayList f5157b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        private long f5158c = 120;

        /* renamed from: d, reason: collision with root package name */
        private long f5159d = 120;

        /* renamed from: e, reason: collision with root package name */
        private long f5160e = 250;

        /* renamed from: f, reason: collision with root package name */
        private long f5161f = 250;

        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }

        public interface ItemAnimatorFinishedListener {
            void a();
        }

        interface ItemAnimatorListener {
            void a(ViewHolder viewHolder);
        }

        public static class ItemHolderInfo {

            /* renamed from: a, reason: collision with root package name */
            public int f5162a;

            /* renamed from: b, reason: collision with root package name */
            public int f5163b;

            /* renamed from: c, reason: collision with root package name */
            public int f5164c;

            /* renamed from: d, reason: collision with root package name */
            public int f5165d;

            public ItemHolderInfo a(ViewHolder viewHolder) {
                return b(viewHolder, 0);
            }

            public ItemHolderInfo b(ViewHolder viewHolder, int i2) {
                View view = viewHolder.f5252a;
                this.f5162a = view.getLeft();
                this.f5163b = view.getTop();
                this.f5164c = view.getRight();
                this.f5165d = view.getBottom();
                return this;
            }
        }

        static int e(ViewHolder viewHolder) {
            int i2 = viewHolder.f5261j;
            int i3 = i2 & 14;
            if (viewHolder.u()) {
                return 4;
            }
            if ((i2 & 4) != 0) {
                return i3;
            }
            int o2 = viewHolder.o();
            int k2 = viewHolder.k();
            return (o2 == -1 || k2 == -1 || o2 == k2) ? i3 : i3 | 2048;
        }

        public void A(long j2) {
            this.f5159d = j2;
        }

        public abstract boolean a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean b(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean d(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public boolean f(ViewHolder viewHolder) {
            return true;
        }

        public boolean g(ViewHolder viewHolder, List list) {
            return f(viewHolder);
        }

        public final void h(ViewHolder viewHolder) {
            s(viewHolder);
            ItemAnimatorListener itemAnimatorListener = this.f5156a;
            if (itemAnimatorListener != null) {
                itemAnimatorListener.a(viewHolder);
            }
        }

        public final void i() {
            int size = this.f5157b.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((ItemAnimatorFinishedListener) this.f5157b.get(i2)).a();
            }
            this.f5157b.clear();
        }

        public abstract void j(ViewHolder viewHolder);

        public abstract void k();

        public long l() {
            return this.f5158c;
        }

        public long m() {
            return this.f5161f;
        }

        public long n() {
            return this.f5160e;
        }

        public long o() {
            return this.f5159d;
        }

        public abstract boolean p();

        public final boolean q(ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean p2 = p();
            if (itemAnimatorFinishedListener != null) {
                if (p2) {
                    this.f5157b.add(itemAnimatorFinishedListener);
                } else {
                    itemAnimatorFinishedListener.a();
                }
            }
            return p2;
        }

        public ItemHolderInfo r() {
            return new ItemHolderInfo();
        }

        public void s(ViewHolder viewHolder) {
        }

        public ItemHolderInfo t(State state, ViewHolder viewHolder) {
            return r().a(viewHolder);
        }

        public ItemHolderInfo u(State state, ViewHolder viewHolder, int i2, List list) {
            return r().a(viewHolder);
        }

        public abstract void v();

        public void w(long j2) {
            this.f5158c = j2;
        }

        public void x(long j2) {
            this.f5161f = j2;
        }

        void y(ItemAnimatorListener itemAnimatorListener) {
            this.f5156a = itemAnimatorListener;
        }

        public void z(long j2) {
            this.f5160e = j2;
        }
    }

    private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        ItemAnimatorRestoreListener() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorListener
        public void a(ViewHolder viewHolder) {
            viewHolder.H(true);
            if (viewHolder.f5259h != null && viewHolder.f5260i == null) {
                viewHolder.f5259h = null;
            }
            viewHolder.f5260i = null;
            if (viewHolder.J() || RecyclerView.this.Y0(viewHolder.f5252a) || !viewHolder.y()) {
                return;
            }
            RecyclerView.this.removeDetachedView(viewHolder.f5252a, false);
        }
    }

    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(@NonNull Rect rect, int i2, @NonNull RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        @Deprecated
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).a(), recyclerView);
        }

        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDraw(canvas, recyclerView);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
            onDrawOver(canvas, recyclerView);
        }
    }

    public static abstract class LayoutManager {

        /* renamed from: a, reason: collision with root package name */
        ChildHelper f5167a;

        /* renamed from: b, reason: collision with root package name */
        RecyclerView f5168b;

        /* renamed from: c, reason: collision with root package name */
        private final ViewBoundsCheck.Callback f5169c;

        /* renamed from: d, reason: collision with root package name */
        private final ViewBoundsCheck.Callback f5170d;

        /* renamed from: e, reason: collision with root package name */
        ViewBoundsCheck f5171e;

        /* renamed from: f, reason: collision with root package name */
        ViewBoundsCheck f5172f;

        /* renamed from: g, reason: collision with root package name */
        SmoothScroller f5173g;

        /* renamed from: h, reason: collision with root package name */
        boolean f5174h;

        /* renamed from: i, reason: collision with root package name */
        boolean f5175i;

        /* renamed from: j, reason: collision with root package name */
        boolean f5176j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f5177k;

        /* renamed from: l, reason: collision with root package name */
        private boolean f5178l;

        /* renamed from: m, reason: collision with root package name */
        int f5179m;

        /* renamed from: n, reason: collision with root package name */
        boolean f5180n;

        /* renamed from: o, reason: collision with root package name */
        private int f5181o;

        /* renamed from: p, reason: collision with root package name */
        private int f5182p;

        /* renamed from: q, reason: collision with root package name */
        private int f5183q;

        /* renamed from: r, reason: collision with root package name */
        private int f5184r;

        public interface LayoutPrefetchRegistry {
            void a(int i2, int i3);
        }

        public static class Properties {

            /* renamed from: a, reason: collision with root package name */
            public int f5187a;

            /* renamed from: b, reason: collision with root package name */
            public int f5188b;

            /* renamed from: c, reason: collision with root package name */
            public boolean f5189c;

            /* renamed from: d, reason: collision with root package name */
            public boolean f5190d;
        }

        public LayoutManager() {
            ViewBoundsCheck.Callback callback = new ViewBoundsCheck.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.LayoutManager.1
                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public View a(int i2) {
                    return LayoutManager.this.O(i2);
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int b(View view) {
                    return LayoutManager.this.W(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).leftMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int c() {
                    return LayoutManager.this.l0();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int d() {
                    return LayoutManager.this.w0() - LayoutManager.this.m0();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int e(View view) {
                    return LayoutManager.this.Z(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).rightMargin;
                }
            };
            this.f5169c = callback;
            ViewBoundsCheck.Callback callback2 = new ViewBoundsCheck.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.LayoutManager.2
                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public View a(int i2) {
                    return LayoutManager.this.O(i2);
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int b(View view) {
                    return LayoutManager.this.a0(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).topMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int c() {
                    return LayoutManager.this.o0();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int d() {
                    return LayoutManager.this.c0() - LayoutManager.this.j0();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int e(View view) {
                    return LayoutManager.this.U(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
                }
            };
            this.f5170d = callback2;
            this.f5171e = new ViewBoundsCheck(callback);
            this.f5172f = new ViewBoundsCheck(callback2);
            this.f5174h = false;
            this.f5175i = false;
            this.f5176j = false;
            this.f5177k = true;
            this.f5178l = true;
        }

        private boolean B0(RecyclerView recyclerView, int i2, int i3) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int l0 = l0();
            int o0 = o0();
            int w0 = w0() - m0();
            int c0 = c0() - j0();
            Rect rect = this.f5168b.mTempRect;
            V(focusedChild, rect);
            return rect.left - i2 < w0 && rect.right - i2 > l0 && rect.top - i3 < c0 && rect.bottom - i3 > o0;
        }

        private void E(int i2, View view) {
            this.f5167a.d(i2);
        }

        private static boolean E0(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            if (i4 > 0 && i2 != i4) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i2;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i2;
            }
            return true;
        }

        private void E1(Recycler recycler, int i2, View view) {
            ViewHolder i0 = RecyclerView.i0(view);
            if (i0.K()) {
                return;
            }
            if (i0.u() && !i0.w() && !this.f5168b.mAdapter.q()) {
                z1(i2);
                recycler.C(i0);
            } else {
                D(i2);
                recycler.D(view);
                this.f5168b.mViewInfoStore.k(i0);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
        
            if (r5 == 1073741824) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int Q(int r4, int r5, int r6, int r7, boolean r8) {
            /*
                int r4 = r4 - r6
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r8 == 0) goto L1d
                if (r7 < 0) goto L12
            L10:
                r5 = r3
                goto L30
            L12:
                if (r7 != r1) goto L1a
                if (r5 == r2) goto L22
                if (r5 == 0) goto L1a
                if (r5 == r3) goto L22
            L1a:
                r5 = r6
                r7 = r5
                goto L30
            L1d:
                if (r7 < 0) goto L20
                goto L10
            L20:
                if (r7 != r1) goto L24
            L22:
                r7 = r4
                goto L30
            L24:
                if (r7 != r0) goto L1a
                if (r5 == r2) goto L2e
                if (r5 != r3) goto L2b
                goto L2e
            L2b:
                r7 = r4
                r5 = r6
                goto L30
            L2e:
                r7 = r4
                r5 = r2
            L30:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.Q(int, int, int, int, boolean):int");
        }

        private int[] R(View view, Rect rect) {
            int l0 = l0();
            int o0 = o0();
            int w0 = w0() - m0();
            int c0 = c0() - j0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width = rect.width() + left;
            int height = rect.height() + top;
            int i2 = left - l0;
            int min = Math.min(0, i2);
            int i3 = top - o0;
            int min2 = Math.min(0, i3);
            int i4 = width - w0;
            int max = Math.max(0, i4);
            int max2 = Math.max(0, height - c0);
            if (f0() != 1) {
                if (min == 0) {
                    min = Math.min(i2, max);
                }
                max = min;
            } else if (max == 0) {
                max = Math.max(min, i4);
            }
            if (min2 == 0) {
                min2 = Math.min(i3, max2);
            }
            return new int[]{max, min2};
        }

        private void l(View view, int i2, boolean z) {
            ViewHolder i0 = RecyclerView.i0(view);
            if (z || i0.w()) {
                this.f5168b.mViewInfoStore.b(i0);
            } else {
                this.f5168b.mViewInfoStore.p(i0);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (i0.M() || i0.x()) {
                if (i0.x()) {
                    i0.L();
                } else {
                    i0.f();
                }
                this.f5167a.c(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f5168b) {
                int m2 = this.f5167a.m(view);
                if (i2 == -1) {
                    i2 = this.f5167a.g();
                }
                if (m2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f5168b.indexOfChild(view) + this.f5168b.Q());
                }
                if (m2 != i2) {
                    this.f5168b.mLayout.K0(m2, i2);
                }
            } else {
                this.f5167a.a(view, i2, false);
                layoutParams.f5193c = true;
                SmoothScroller smoothScroller = this.f5173g;
                if (smoothScroller != null && smoothScroller.h()) {
                    this.f5173g.k(view);
                }
            }
            if (layoutParams.f5194d) {
                i0.f5252a.invalidate();
                layoutParams.f5194d = false;
            }
        }

        public static Properties q0(Context context, AttributeSet attributeSet, int i2, int i3) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.recyclerview.R.styleable.RecyclerView, i2, i3);
            properties.f5187a = obtainStyledAttributes.getInt(androidx.recyclerview.R.styleable.RecyclerView_android_orientation, 1);
            properties.f5188b = obtainStyledAttributes.getInt(androidx.recyclerview.R.styleable.RecyclerView_spanCount, 1);
            properties.f5189c = obtainStyledAttributes.getBoolean(androidx.recyclerview.R.styleable.RecyclerView_reverseLayout, false);
            properties.f5190d = obtainStyledAttributes.getBoolean(androidx.recyclerview.R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        public static int t(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i3, i4) : size : Math.min(size, Math.max(i3, i4));
        }

        public int A(State state) {
            return 0;
        }

        public boolean A0() {
            return this.f5176j;
        }

        public boolean A1(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return B1(recyclerView, view, rect, z, false);
        }

        public int B(State state) {
            return 0;
        }

        public boolean B1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] R = R(view, rect);
            int i2 = R[0];
            int i3 = R[1];
            if ((z2 && !B0(recyclerView, i2, i3)) || (i2 == 0 && i3 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i2, i3);
            } else {
                recyclerView.o1(i2, i3);
            }
            return true;
        }

        public void C(Recycler recycler) {
            for (int P = P() - 1; P >= 0; P--) {
                E1(recycler, P, O(P));
            }
        }

        public final boolean C0() {
            return this.f5178l;
        }

        public void C1() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public void D(int i2) {
            E(i2, O(i2));
        }

        public boolean D0(Recycler recycler, State state) {
            return false;
        }

        public void D1() {
            this.f5174h = true;
        }

        void F(RecyclerView recyclerView) {
            this.f5175i = true;
            P0(recyclerView);
        }

        public boolean F0() {
            SmoothScroller smoothScroller = this.f5173g;
            return smoothScroller != null && smoothScroller.h();
        }

        public int F1(int i2, Recycler recycler, State state) {
            return 0;
        }

        void G(RecyclerView recyclerView, Recycler recycler) {
            this.f5175i = false;
            R0(recyclerView, recycler);
        }

        public boolean G0(View view, boolean z, boolean z2) {
            boolean z3 = this.f5171e.b(view, 24579) && this.f5172f.b(view, 24579);
            return z ? z3 : !z3;
        }

        public void G1(int i2) {
        }

        public View H(View view) {
            View T;
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null || (T = recyclerView.T(view)) == null || this.f5167a.n(T)) {
                return null;
            }
            return T;
        }

        public void H0(View view, int i2, int i3, int i4, int i5) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f5192b;
            view.layout(i2 + rect.left, i3 + rect.top, i4 - rect.right, i5 - rect.bottom);
        }

        public int H1(int i2, Recycler recycler, State state) {
            return 0;
        }

        public View I(int i2) {
            int P = P();
            for (int i3 = 0; i3 < P; i3++) {
                View O = O(i3);
                ViewHolder i0 = RecyclerView.i0(O);
                if (i0 != null && i0.n() == i2 && !i0.K() && (this.f5168b.mState.e() || !i0.w())) {
                    return O;
                }
            }
            return null;
        }

        public void I0(View view, int i2, int i3, int i4, int i5) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.f5192b;
            view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        void I1(RecyclerView recyclerView) {
            J1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
        }

        public abstract LayoutParams J();

        public void J0(View view, int i2, int i3) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect n0 = this.f5168b.n0(view);
            int i4 = i2 + n0.left + n0.right;
            int i5 = i3 + n0.top + n0.bottom;
            int Q = Q(w0(), x0(), l0() + m0() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i4, ((ViewGroup.MarginLayoutParams) layoutParams).width, q());
            int Q2 = Q(c0(), d0(), o0() + j0() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, r());
            if (O1(view, Q, Q2, layoutParams)) {
                view.measure(Q, Q2);
            }
        }

        void J1(int i2, int i3) {
            this.f5183q = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.f5181o = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.f5183q = 0;
            }
            this.f5184r = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.f5182p = mode2;
            if (mode2 != 0 || RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                return;
            }
            this.f5184r = 0;
        }

        public LayoutParams K(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public void K0(int i2, int i3) {
            View O = O(i2);
            if (O != null) {
                D(i2);
                n(O, i3);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.f5168b.toString());
            }
        }

        public void K1(int i2, int i3) {
            this.f5168b.setMeasuredDimension(i2, i3);
        }

        public LayoutParams L(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        public void L0(int i2) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                recyclerView.C0(i2);
            }
        }

        public void L1(Rect rect, int i2, int i3) {
            K1(t(i2, rect.width() + l0() + m0(), i0()), t(i3, rect.height() + o0() + j0(), h0()));
        }

        public int M() {
            return -1;
        }

        public void M0(int i2) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                recyclerView.D0(i2);
            }
        }

        void M1(int i2, int i3) {
            int P = P();
            if (P == 0) {
                this.f5168b.x(i2, i3);
                return;
            }
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MIN_VALUE;
            int i7 = Integer.MAX_VALUE;
            for (int i8 = 0; i8 < P; i8++) {
                View O = O(i8);
                Rect rect = this.f5168b.mTempRect;
                V(O, rect);
                int i9 = rect.left;
                if (i9 < i7) {
                    i7 = i9;
                }
                int i10 = rect.right;
                if (i10 > i4) {
                    i4 = i10;
                }
                int i11 = rect.top;
                if (i11 < i5) {
                    i5 = i11;
                }
                int i12 = rect.bottom;
                if (i12 > i6) {
                    i6 = i12;
                }
            }
            this.f5168b.mTempRect.set(i7, i5, i4, i6);
            L1(this.f5168b.mTempRect, i2, i3);
        }

        public int N(View view) {
            return ((LayoutParams) view.getLayoutParams()).f5192b.bottom;
        }

        public void N0(Adapter adapter, Adapter adapter2) {
        }

        void N1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f5168b = null;
                this.f5167a = null;
                this.f5183q = 0;
                this.f5184r = 0;
            } else {
                this.f5168b = recyclerView;
                this.f5167a = recyclerView.mChildHelper;
                this.f5183q = recyclerView.getWidth();
                this.f5184r = recyclerView.getHeight();
            }
            this.f5181o = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
            this.f5182p = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
        }

        public View O(int i2) {
            ChildHelper childHelper = this.f5167a;
            if (childHelper != null) {
                return childHelper.f(i2);
            }
            return null;
        }

        public boolean O0(RecyclerView recyclerView, ArrayList arrayList, int i2, int i3) {
            return false;
        }

        boolean O1(View view, int i2, int i3, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.f5177k && E0(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).width) && E0(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public int P() {
            ChildHelper childHelper = this.f5167a;
            if (childHelper != null) {
                return childHelper.g();
            }
            return 0;
        }

        public void P0(RecyclerView recyclerView) {
        }

        boolean P1() {
            return false;
        }

        public void Q0(RecyclerView recyclerView) {
        }

        boolean Q1(View view, int i2, int i3, LayoutParams layoutParams) {
            return (this.f5177k && E0(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).width) && E0(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public void R0(RecyclerView recyclerView, Recycler recycler) {
            Q0(recyclerView);
        }

        public void R1(RecyclerView recyclerView, State state, int i2) {
            Log.e(RecyclerView.TAG, "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public boolean S() {
            RecyclerView recyclerView = this.f5168b;
            return recyclerView != null && recyclerView.mClipToPadding;
        }

        public View S0(View view, int i2, Recycler recycler, State state) {
            return null;
        }

        public void S1(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.f5173g;
            if (smoothScroller2 != null && smoothScroller != smoothScroller2 && smoothScroller2.h()) {
                this.f5173g.r();
            }
            this.f5173g = smoothScroller;
            smoothScroller.q(this.f5168b, this);
        }

        public int T(Recycler recycler, State state) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null || recyclerView.mAdapter == null || !q()) {
                return 1;
            }
            return this.f5168b.mAdapter.m();
        }

        public void T0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f5168b;
            U0(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        }

        void T1() {
            SmoothScroller smoothScroller = this.f5173g;
            if (smoothScroller != null) {
                smoothScroller.r();
            }
        }

        public int U(View view) {
            return view.getBottom() + N(view);
        }

        public void U0(Recycler recycler, State state, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f5168b.canScrollVertically(-1) && !this.f5168b.canScrollHorizontally(-1) && !this.f5168b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            Adapter adapter = this.f5168b.mAdapter;
            if (adapter != null) {
                accessibilityEvent.setItemCount(adapter.m());
            }
        }

        public boolean U1() {
            return false;
        }

        public void V(View view, Rect rect) {
            RecyclerView.k0(view, rect);
        }

        void V0(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            RecyclerView recyclerView = this.f5168b;
            W0(recyclerView.mRecycler, recyclerView.mState, accessibilityNodeInfoCompat);
        }

        public int W(View view) {
            return view.getLeft() - g0(view);
        }

        public void W0(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.f5168b.canScrollVertically(-1) || this.f5168b.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.a(8192);
                accessibilityNodeInfoCompat.E0(true);
            }
            if (this.f5168b.canScrollVertically(1) || this.f5168b.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.a(4096);
                accessibilityNodeInfoCompat.E0(true);
            }
            accessibilityNodeInfoCompat.j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.b(s0(recycler, state), T(recycler, state), D0(recycler, state), t0(recycler, state)));
        }

        public int X(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f5192b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        void X0(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder i0 = RecyclerView.i0(view);
            if (i0 == null || i0.w() || this.f5167a.n(i0.f5252a)) {
                return;
            }
            RecyclerView recyclerView = this.f5168b;
            Y0(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
        }

        public int Y(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f5192b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void Y0(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(r() ? p0(view) : 0, 1, q() ? p0(view) : 0, 1, false, false));
        }

        public int Z(View view) {
            return view.getRight() + r0(view);
        }

        public View Z0(View view, int i2) {
            return null;
        }

        public int a0(View view) {
            return view.getTop() - u0(view);
        }

        public void a1(RecyclerView recyclerView, int i2, int i3) {
        }

        public View b0() {
            View focusedChild;
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f5167a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void b1(RecyclerView recyclerView) {
        }

        public int c0() {
            return this.f5184r;
        }

        public void c1(RecyclerView recyclerView, int i2, int i3, int i4) {
        }

        public int d0() {
            return this.f5182p;
        }

        public void d1(RecyclerView recyclerView, int i2, int i3) {
        }

        public int e0(View view) {
            return RecyclerView.i0(view).m();
        }

        public void e1(RecyclerView recyclerView, int i2, int i3) {
        }

        public int f() {
            RecyclerView recyclerView = this.f5168b;
            Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.m();
            }
            return 0;
        }

        public int f0() {
            return ViewCompat.v(this.f5168b);
        }

        public void f1(RecyclerView recyclerView, int i2, int i3, Object obj) {
            e1(recyclerView, i2, i3);
        }

        public int g0(View view) {
            return ((LayoutParams) view.getLayoutParams()).f5192b.left;
        }

        public void g1(Recycler recycler, State state) {
            Log.e(RecyclerView.TAG, "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void h(View view) {
            i(view, -1);
        }

        public int h0() {
            return ViewCompat.w(this.f5168b);
        }

        public void h1(State state) {
        }

        public void i(View view, int i2) {
            l(view, i2, true);
        }

        public int i0() {
            return ViewCompat.x(this.f5168b);
        }

        public void i1(Recycler recycler, State state, int i2, int i3) {
            this.f5168b.x(i2, i3);
        }

        public void j(View view) {
            k(view, -1);
        }

        public int j0() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public boolean j1(RecyclerView recyclerView, View view, View view2) {
            return F0() || recyclerView.x0();
        }

        public void k(View view, int i2) {
            l(view, i2, false);
        }

        public int k0() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return ViewCompat.y(recyclerView);
            }
            return 0;
        }

        public boolean k1(RecyclerView recyclerView, State state, View view, View view2) {
            return j1(recyclerView, view, view2);
        }

        public int l0() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void l1(Parcelable parcelable) {
        }

        public void m(String str) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int m0() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public Parcelable m1() {
            return null;
        }

        public void n(View view, int i2) {
            o(view, i2, (LayoutParams) view.getLayoutParams());
        }

        public int n0() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return ViewCompat.z(recyclerView);
            }
            return 0;
        }

        public void n1(int i2) {
        }

        public void o(View view, int i2, LayoutParams layoutParams) {
            ViewHolder i0 = RecyclerView.i0(view);
            if (i0.w()) {
                this.f5168b.mViewInfoStore.b(i0);
            } else {
                this.f5168b.mViewInfoStore.p(i0);
            }
            this.f5167a.c(view, i2, layoutParams, i0.w());
        }

        public int o0() {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        void o1(SmoothScroller smoothScroller) {
            if (this.f5173g == smoothScroller) {
                this.f5173g = null;
            }
        }

        public void p(View view, Rect rect) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.n0(view));
            }
        }

        public int p0(View view) {
            return ((LayoutParams) view.getLayoutParams()).a();
        }

        boolean p1(int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f5168b;
            return q1(recyclerView.mRecycler, recyclerView.mState, i2, bundle);
        }

        public boolean q() {
            return false;
        }

        public boolean q1(Recycler recycler, State state, int i2, Bundle bundle) {
            int c0;
            int w0;
            int i3;
            int i4;
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null) {
                return false;
            }
            if (i2 == 4096) {
                c0 = recyclerView.canScrollVertically(1) ? (c0() - o0()) - j0() : 0;
                if (this.f5168b.canScrollHorizontally(1)) {
                    w0 = (w0() - l0()) - m0();
                    i3 = c0;
                    i4 = w0;
                }
                i3 = c0;
                i4 = 0;
            } else if (i2 != 8192) {
                i4 = 0;
                i3 = 0;
            } else {
                c0 = recyclerView.canScrollVertically(-1) ? -((c0() - o0()) - j0()) : 0;
                if (this.f5168b.canScrollHorizontally(-1)) {
                    w0 = -((w0() - l0()) - m0());
                    i3 = c0;
                    i4 = w0;
                }
                i3 = c0;
                i4 = 0;
            }
            if (i3 == 0 && i4 == 0) {
                return false;
            }
            this.f5168b.r1(i4, i3, null, Integer.MIN_VALUE, true);
            return true;
        }

        public boolean r() {
            return false;
        }

        public int r0(View view) {
            return ((LayoutParams) view.getLayoutParams()).f5192b.right;
        }

        boolean r1(View view, int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f5168b;
            return s1(recyclerView.mRecycler, recyclerView.mState, view, i2, bundle);
        }

        public boolean s(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public int s0(Recycler recycler, State state) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView == null || recyclerView.mAdapter == null || !r()) {
                return 1;
            }
            return this.f5168b.mAdapter.m();
        }

        public boolean s1(Recycler recycler, State state, View view, int i2, Bundle bundle) {
            return false;
        }

        public int t0(Recycler recycler, State state) {
            return 0;
        }

        public void t1(Recycler recycler) {
            for (int P = P() - 1; P >= 0; P--) {
                if (!RecyclerView.i0(O(P)).K()) {
                    w1(P, recycler);
                }
            }
        }

        public void u(int i2, int i3, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public int u0(View view) {
            return ((LayoutParams) view.getLayoutParams()).f5192b.top;
        }

        void u1(Recycler recycler) {
            int j2 = recycler.j();
            for (int i2 = j2 - 1; i2 >= 0; i2--) {
                View n2 = recycler.n(i2);
                ViewHolder i0 = RecyclerView.i0(n2);
                if (!i0.K()) {
                    i0.H(false);
                    if (i0.y()) {
                        this.f5168b.removeDetachedView(n2, false);
                    }
                    ItemAnimator itemAnimator = this.f5168b.mItemAnimator;
                    if (itemAnimator != null) {
                        itemAnimator.j(i0);
                    }
                    i0.H(true);
                    recycler.y(n2);
                }
            }
            recycler.e();
            if (j2 > 0) {
                this.f5168b.invalidate();
            }
        }

        public void v(int i2, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void v0(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).f5192b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f5168b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f5168b.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void v1(View view, Recycler recycler) {
            y1(view);
            recycler.B(view);
        }

        public int w(State state) {
            return 0;
        }

        public int w0() {
            return this.f5183q;
        }

        public void w1(int i2, Recycler recycler) {
            View O = O(i2);
            z1(i2);
            recycler.B(O);
        }

        public int x(State state) {
            return 0;
        }

        public int x0() {
            return this.f5181o;
        }

        public boolean x1(Runnable runnable) {
            RecyclerView recyclerView = this.f5168b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public int y(State state) {
            return 0;
        }

        boolean y0() {
            int P = P();
            for (int i2 = 0; i2 < P; i2++) {
                ViewGroup.LayoutParams layoutParams = O(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void y1(View view) {
            this.f5167a.p(view);
        }

        public int z(State state) {
            return 0;
        }

        public boolean z0() {
            return this.f5175i;
        }

        public void z1(int i2) {
            if (O(i2) != null) {
                this.f5167a.q(i2);
            }
        }
    }

    public interface OnChildAttachStateChangeListener {
        void a(View view);

        void b(View view);
    }

    public static abstract class OnFlingListener {
        public abstract boolean a(int i2, int i3);
    }

    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class OnScrollListener {
        public void a(RecyclerView recyclerView, int i2) {
        }

        public void b(RecyclerView recyclerView, int i2, int i3) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Orientation {
    }

    public static class RecycledViewPool {

        /* renamed from: a, reason: collision with root package name */
        SparseArray f5195a = new SparseArray();

        /* renamed from: b, reason: collision with root package name */
        private int f5196b = 0;

        static class ScrapData {

            /* renamed from: a, reason: collision with root package name */
            final ArrayList f5197a = new ArrayList();

            /* renamed from: b, reason: collision with root package name */
            int f5198b = 5;

            /* renamed from: c, reason: collision with root package name */
            long f5199c = 0;

            /* renamed from: d, reason: collision with root package name */
            long f5200d = 0;

            ScrapData() {
            }
        }

        private ScrapData g(int i2) {
            ScrapData scrapData = (ScrapData) this.f5195a.get(i2);
            if (scrapData != null) {
                return scrapData;
            }
            ScrapData scrapData2 = new ScrapData();
            this.f5195a.put(i2, scrapData2);
            return scrapData2;
        }

        void a() {
            this.f5196b++;
        }

        public void b() {
            for (int i2 = 0; i2 < this.f5195a.size(); i2++) {
                ((ScrapData) this.f5195a.valueAt(i2)).f5197a.clear();
            }
        }

        void c() {
            this.f5196b--;
        }

        void d(int i2, long j2) {
            ScrapData g2 = g(i2);
            g2.f5200d = j(g2.f5200d, j2);
        }

        void e(int i2, long j2) {
            ScrapData g2 = g(i2);
            g2.f5199c = j(g2.f5199c, j2);
        }

        public ViewHolder f(int i2) {
            ScrapData scrapData = (ScrapData) this.f5195a.get(i2);
            if (scrapData == null || scrapData.f5197a.isEmpty()) {
                return null;
            }
            ArrayList arrayList = scrapData.f5197a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!((ViewHolder) arrayList.get(size)).s()) {
                    return (ViewHolder) arrayList.remove(size);
                }
            }
            return null;
        }

        void h(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                c();
            }
            if (!z && this.f5196b == 0) {
                b();
            }
            if (adapter2 != null) {
                a();
            }
        }

        public void i(ViewHolder viewHolder) {
            int m2 = viewHolder.m();
            ArrayList arrayList = g(m2).f5197a;
            if (((ScrapData) this.f5195a.get(m2)).f5198b <= arrayList.size()) {
                return;
            }
            viewHolder.E();
            arrayList.add(viewHolder);
        }

        long j(long j2, long j3) {
            return j2 == 0 ? j3 : ((j2 / 4) * 3) + (j3 / 4);
        }

        boolean k(int i2, long j2, long j3) {
            long j4 = g(i2).f5200d;
            return j4 == 0 || j2 + j4 < j3;
        }

        boolean l(int i2, long j2, long j3) {
            long j4 = g(i2).f5199c;
            return j4 == 0 || j2 + j4 < j3;
        }
    }

    public final class Recycler {

        /* renamed from: a, reason: collision with root package name */
        final ArrayList f5201a;

        /* renamed from: b, reason: collision with root package name */
        ArrayList f5202b;

        /* renamed from: c, reason: collision with root package name */
        final ArrayList f5203c;

        /* renamed from: d, reason: collision with root package name */
        private final List f5204d;

        /* renamed from: e, reason: collision with root package name */
        private int f5205e;

        /* renamed from: f, reason: collision with root package name */
        int f5206f;

        /* renamed from: g, reason: collision with root package name */
        RecycledViewPool f5207g;

        /* renamed from: h, reason: collision with root package name */
        private ViewCacheExtension f5208h;

        public Recycler() {
            ArrayList arrayList = new ArrayList();
            this.f5201a = arrayList;
            this.f5202b = null;
            this.f5203c = new ArrayList();
            this.f5204d = Collections.unmodifiableList(arrayList);
            this.f5205e = 2;
            this.f5206f = 2;
        }

        private boolean H(ViewHolder viewHolder, int i2, int i3, long j2) {
            viewHolder.f5268q = RecyclerView.this;
            int m2 = viewHolder.m();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j2 != RecyclerView.FOREVER_NS && !this.f5207g.k(m2, nanoTime, j2)) {
                return false;
            }
            RecyclerView.this.mAdapter.k(viewHolder, i2);
            this.f5207g.d(viewHolder.m(), RecyclerView.this.getNanoTime() - nanoTime);
            b(viewHolder);
            if (!RecyclerView.this.mState.e()) {
                return true;
            }
            viewHolder.f5258g = i3;
            return true;
        }

        private void b(ViewHolder viewHolder) {
            if (RecyclerView.this.w0()) {
                View view = viewHolder.f5252a;
                if (ViewCompat.t(view) == 0) {
                    ViewCompat.s0(view, 1);
                }
                RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.mAccessibilityDelegate;
                if (recyclerViewAccessibilityDelegate == null) {
                    return;
                }
                AccessibilityDelegateCompat n2 = recyclerViewAccessibilityDelegate.n();
                if (n2 instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
                    ((RecyclerViewAccessibilityDelegate.ItemDelegate) n2).o(view);
                }
                ViewCompat.i0(view, n2);
            }
        }

        private void q(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    q((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }

        private void r(ViewHolder viewHolder) {
            View view = viewHolder.f5252a;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        void A(int i2) {
            a((ViewHolder) this.f5203c.get(i2), true);
            this.f5203c.remove(i2);
        }

        public void B(View view) {
            ViewHolder i0 = RecyclerView.i0(view);
            if (i0.y()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (i0.x()) {
                i0.L();
            } else if (i0.M()) {
                i0.f();
            }
            C(i0);
            if (RecyclerView.this.mItemAnimator == null || i0.v()) {
                return;
            }
            RecyclerView.this.mItemAnimator.j(i0);
        }

        void C(ViewHolder viewHolder) {
            boolean z;
            boolean z2 = true;
            if (viewHolder.x() || viewHolder.f5252a.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(viewHolder.x());
                sb.append(" isAttached:");
                sb.append(viewHolder.f5252a.getParent() != null);
                sb.append(RecyclerView.this.Q());
                throw new IllegalArgumentException(sb.toString());
            }
            if (viewHolder.y()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder + RecyclerView.this.Q());
            }
            if (viewHolder.K()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.Q());
            }
            boolean i2 = viewHolder.i();
            Adapter adapter = RecyclerView.this.mAdapter;
            if ((adapter != null && i2 && adapter.E(viewHolder)) || viewHolder.v()) {
                if (this.f5206f <= 0 || viewHolder.q(526)) {
                    z = false;
                } else {
                    int size = this.f5203c.size();
                    if (size >= this.f5206f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !RecyclerView.this.mPrefetchRegistry.d(viewHolder.f5254c)) {
                        int i3 = size - 1;
                        while (i3 >= 0) {
                            if (!RecyclerView.this.mPrefetchRegistry.d(((ViewHolder) this.f5203c.get(i3)).f5254c)) {
                                break;
                            } else {
                                i3--;
                            }
                        }
                        size = i3 + 1;
                    }
                    this.f5203c.add(size, viewHolder);
                    z = true;
                }
                if (z) {
                    z2 = false;
                } else {
                    a(viewHolder, true);
                }
                r1 = z;
            } else {
                z2 = false;
            }
            RecyclerView.this.mViewInfoStore.q(viewHolder);
            if (r1 || z2 || !i2) {
                return;
            }
            viewHolder.f5268q = null;
        }

        void D(View view) {
            ViewHolder i0 = RecyclerView.i0(view);
            if (!i0.q(12) && i0.z() && !RecyclerView.this.q(i0)) {
                if (this.f5202b == null) {
                    this.f5202b = new ArrayList();
                }
                i0.I(this, true);
                this.f5202b.add(i0);
                return;
            }
            if (!i0.u() || i0.w() || RecyclerView.this.mAdapter.q()) {
                i0.I(this, false);
                this.f5201a.add(i0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.Q());
            }
        }

        void E(RecycledViewPool recycledViewPool) {
            RecycledViewPool recycledViewPool2 = this.f5207g;
            if (recycledViewPool2 != null) {
                recycledViewPool2.c();
            }
            this.f5207g = recycledViewPool;
            if (recycledViewPool == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f5207g.a();
        }

        void F(ViewCacheExtension viewCacheExtension) {
            this.f5208h = viewCacheExtension;
        }

        public void G(int i2) {
            this.f5205e = i2;
            K();
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x0186  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x01a3  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x01c6  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01ff  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x0229 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x020d  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x01d5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        androidx.recyclerview.widget.RecyclerView.ViewHolder I(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instructions count: 616
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.I(int, boolean, long):androidx.recyclerview.widget.RecyclerView$ViewHolder");
        }

        void J(ViewHolder viewHolder) {
            if (viewHolder.f5266o) {
                this.f5202b.remove(viewHolder);
            } else {
                this.f5201a.remove(viewHolder);
            }
            viewHolder.f5265n = null;
            viewHolder.f5266o = false;
            viewHolder.f();
        }

        void K() {
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            this.f5206f = this.f5205e + (layoutManager != null ? layoutManager.f5179m : 0);
            for (int size = this.f5203c.size() - 1; size >= 0 && this.f5203c.size() > this.f5206f; size--) {
                A(size);
            }
        }

        boolean L(ViewHolder viewHolder) {
            if (viewHolder.w()) {
                return RecyclerView.this.mState.e();
            }
            int i2 = viewHolder.f5254c;
            if (i2 >= 0 && i2 < RecyclerView.this.mAdapter.m()) {
                if (RecyclerView.this.mState.e() || RecyclerView.this.mAdapter.o(viewHolder.f5254c) == viewHolder.m()) {
                    return !RecyclerView.this.mAdapter.q() || viewHolder.l() == RecyclerView.this.mAdapter.n(viewHolder.f5254c);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder + RecyclerView.this.Q());
        }

        void M(int i2, int i3) {
            int i4;
            int i5 = i3 + i2;
            for (int size = this.f5203c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f5203c.get(size);
                if (viewHolder != null && (i4 = viewHolder.f5254c) >= i2 && i4 < i5) {
                    viewHolder.c(2);
                    A(size);
                }
            }
        }

        void a(ViewHolder viewHolder, boolean z) {
            RecyclerView.s(viewHolder);
            View view = viewHolder.f5252a;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.mAccessibilityDelegate;
            if (recyclerViewAccessibilityDelegate != null) {
                AccessibilityDelegateCompat n2 = recyclerViewAccessibilityDelegate.n();
                ViewCompat.i0(view, n2 instanceof RecyclerViewAccessibilityDelegate.ItemDelegate ? ((RecyclerViewAccessibilityDelegate.ItemDelegate) n2).n(view) : null);
            }
            if (z) {
                g(viewHolder);
            }
            viewHolder.f5268q = null;
            i().i(viewHolder);
        }

        public void c() {
            this.f5201a.clear();
            z();
        }

        void d() {
            int size = this.f5203c.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((ViewHolder) this.f5203c.get(i2)).d();
            }
            int size2 = this.f5201a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((ViewHolder) this.f5201a.get(i3)).d();
            }
            ArrayList arrayList = this.f5202b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    ((ViewHolder) this.f5202b.get(i4)).d();
                }
            }
        }

        void e() {
            this.f5201a.clear();
            ArrayList arrayList = this.f5202b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i2) {
            if (i2 >= 0 && i2 < RecyclerView.this.mState.b()) {
                return !RecyclerView.this.mState.e() ? i2 : RecyclerView.this.mAdapterHelper.m(i2);
            }
            throw new IndexOutOfBoundsException("invalid position " + i2 + ". State item count is " + RecyclerView.this.mState.b() + RecyclerView.this.Q());
        }

        void g(ViewHolder viewHolder) {
            RecyclerListener recyclerListener = RecyclerView.this.mRecyclerListener;
            if (recyclerListener != null) {
                recyclerListener.a(viewHolder);
            }
            Adapter adapter = RecyclerView.this.mAdapter;
            if (adapter != null) {
                adapter.H(viewHolder);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.q(viewHolder);
            }
        }

        ViewHolder h(int i2) {
            int size;
            int m2;
            ArrayList arrayList = this.f5202b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    ViewHolder viewHolder = (ViewHolder) this.f5202b.get(i3);
                    if (!viewHolder.M() && viewHolder.n() == i2) {
                        viewHolder.c(32);
                        return viewHolder;
                    }
                }
                if (RecyclerView.this.mAdapter.q() && (m2 = RecyclerView.this.mAdapterHelper.m(i2)) > 0 && m2 < RecyclerView.this.mAdapter.m()) {
                    long n2 = RecyclerView.this.mAdapter.n(m2);
                    for (int i4 = 0; i4 < size; i4++) {
                        ViewHolder viewHolder2 = (ViewHolder) this.f5202b.get(i4);
                        if (!viewHolder2.M() && viewHolder2.l() == n2) {
                            viewHolder2.c(32);
                            return viewHolder2;
                        }
                    }
                }
            }
            return null;
        }

        RecycledViewPool i() {
            if (this.f5207g == null) {
                this.f5207g = new RecycledViewPool();
            }
            return this.f5207g;
        }

        int j() {
            return this.f5201a.size();
        }

        public List k() {
            return this.f5204d;
        }

        ViewHolder l(long j2, int i2, boolean z) {
            for (int size = this.f5201a.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f5201a.get(size);
                if (viewHolder.l() == j2 && !viewHolder.M()) {
                    if (i2 == viewHolder.m()) {
                        viewHolder.c(32);
                        if (viewHolder.w() && !RecyclerView.this.mState.e()) {
                            viewHolder.G(2, 14);
                        }
                        return viewHolder;
                    }
                    if (!z) {
                        this.f5201a.remove(size);
                        RecyclerView.this.removeDetachedView(viewHolder.f5252a, false);
                        y(viewHolder.f5252a);
                    }
                }
            }
            int size2 = this.f5203c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                ViewHolder viewHolder2 = (ViewHolder) this.f5203c.get(size2);
                if (viewHolder2.l() == j2 && !viewHolder2.s()) {
                    if (i2 == viewHolder2.m()) {
                        if (!z) {
                            this.f5203c.remove(size2);
                        }
                        return viewHolder2;
                    }
                    if (!z) {
                        A(size2);
                        return null;
                    }
                }
            }
        }

        ViewHolder m(int i2, boolean z) {
            View e2;
            int size = this.f5201a.size();
            for (int i3 = 0; i3 < size; i3++) {
                ViewHolder viewHolder = (ViewHolder) this.f5201a.get(i3);
                if (!viewHolder.M() && viewHolder.n() == i2 && !viewHolder.u() && (RecyclerView.this.mState.f5234h || !viewHolder.w())) {
                    viewHolder.c(32);
                    return viewHolder;
                }
            }
            if (z || (e2 = RecyclerView.this.mChildHelper.e(i2)) == null) {
                int size2 = this.f5203c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ViewHolder viewHolder2 = (ViewHolder) this.f5203c.get(i4);
                    if (!viewHolder2.u() && viewHolder2.n() == i2 && !viewHolder2.s()) {
                        if (!z) {
                            this.f5203c.remove(i4);
                        }
                        return viewHolder2;
                    }
                }
                return null;
            }
            ViewHolder i0 = RecyclerView.i0(e2);
            RecyclerView.this.mChildHelper.s(e2);
            int m2 = RecyclerView.this.mChildHelper.m(e2);
            if (m2 != -1) {
                RecyclerView.this.mChildHelper.d(m2);
                D(e2);
                i0.c(8224);
                return i0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + i0 + RecyclerView.this.Q());
        }

        View n(int i2) {
            return ((ViewHolder) this.f5201a.get(i2)).f5252a;
        }

        public View o(int i2) {
            return p(i2, false);
        }

        View p(int i2, boolean z) {
            return I(i2, z, RecyclerView.FOREVER_NS).f5252a;
        }

        void s() {
            int size = this.f5203c.size();
            for (int i2 = 0; i2 < size; i2++) {
                LayoutParams layoutParams = (LayoutParams) ((ViewHolder) this.f5203c.get(i2)).f5252a.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.f5193c = true;
                }
            }
        }

        void t() {
            int size = this.f5203c.size();
            for (int i2 = 0; i2 < size; i2++) {
                ViewHolder viewHolder = (ViewHolder) this.f5203c.get(i2);
                if (viewHolder != null) {
                    viewHolder.c(6);
                    viewHolder.b(null);
                }
            }
            Adapter adapter = RecyclerView.this.mAdapter;
            if (adapter == null || !adapter.q()) {
                z();
            }
        }

        void u(int i2, int i3) {
            int size = this.f5203c.size();
            for (int i4 = 0; i4 < size; i4++) {
                ViewHolder viewHolder = (ViewHolder) this.f5203c.get(i4);
                if (viewHolder != null && viewHolder.f5254c >= i2) {
                    viewHolder.B(i3, true);
                }
            }
        }

        void v(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            if (i2 < i3) {
                i4 = -1;
                i6 = i2;
                i5 = i3;
            } else {
                i4 = 1;
                i5 = i2;
                i6 = i3;
            }
            int size = this.f5203c.size();
            for (int i8 = 0; i8 < size; i8++) {
                ViewHolder viewHolder = (ViewHolder) this.f5203c.get(i8);
                if (viewHolder != null && (i7 = viewHolder.f5254c) >= i6 && i7 <= i5) {
                    if (i7 == i2) {
                        viewHolder.B(i3 - i2, false);
                    } else {
                        viewHolder.B(i4, false);
                    }
                }
            }
        }

        void w(int i2, int i3, boolean z) {
            int i4 = i2 + i3;
            for (int size = this.f5203c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f5203c.get(size);
                if (viewHolder != null) {
                    int i5 = viewHolder.f5254c;
                    if (i5 >= i4) {
                        viewHolder.B(-i3, z);
                    } else if (i5 >= i2) {
                        viewHolder.c(8);
                        A(size);
                    }
                }
            }
        }

        void x(Adapter adapter, Adapter adapter2, boolean z) {
            c();
            i().h(adapter, adapter2, z);
        }

        void y(View view) {
            ViewHolder i0 = RecyclerView.i0(view);
            i0.f5265n = null;
            i0.f5266o = false;
            i0.f();
            C(i0);
        }

        void z() {
            for (int size = this.f5203c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f5203c.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.b();
            }
        }
    }

    public interface RecyclerListener {
        void a(ViewHolder viewHolder);
    }

    private class RecyclerViewDataObserver extends AdapterDataObserver {
        RecyclerViewDataObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void a() {
            RecyclerView.this.p(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.f5233g = true;
            recyclerView.S0(true);
            if (RecyclerView.this.mAdapterHelper.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void c(int i2, int i3, Object obj) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.mAdapterHelper.r(i2, i3, obj)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void d(int i2, int i3) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.mAdapterHelper.s(i2, i3)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void e(int i2, int i3, int i4) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.mAdapterHelper.t(i2, i3, i4)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void f(int i2, int i3) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.mAdapterHelper.u(i2, i3)) {
                g();
            }
        }

        void g() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    ViewCompat.a0(recyclerView, recyclerView.mUpdateChildViewsRunnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }
    }

    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }
    }

    public static abstract class SmoothScroller {

        /* renamed from: b, reason: collision with root package name */
        private RecyclerView f5213b;

        /* renamed from: c, reason: collision with root package name */
        private LayoutManager f5214c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f5215d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f5216e;

        /* renamed from: f, reason: collision with root package name */
        private View f5217f;

        /* renamed from: h, reason: collision with root package name */
        private boolean f5219h;

        /* renamed from: a, reason: collision with root package name */
        private int f5212a = -1;

        /* renamed from: g, reason: collision with root package name */
        private final Action f5218g = new Action(0, 0);

        public static class Action {

            /* renamed from: a, reason: collision with root package name */
            private int f5220a;

            /* renamed from: b, reason: collision with root package name */
            private int f5221b;

            /* renamed from: c, reason: collision with root package name */
            private int f5222c;

            /* renamed from: d, reason: collision with root package name */
            private int f5223d;

            /* renamed from: e, reason: collision with root package name */
            private Interpolator f5224e;

            /* renamed from: f, reason: collision with root package name */
            private boolean f5225f;

            /* renamed from: g, reason: collision with root package name */
            private int f5226g;

            public Action(int i2, int i3) {
                this(i2, i3, Integer.MIN_VALUE, null);
            }

            private void e() {
                if (this.f5224e != null && this.f5222c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f5222c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            boolean a() {
                return this.f5223d >= 0;
            }

            public void b(int i2) {
                this.f5223d = i2;
            }

            void c(RecyclerView recyclerView) {
                int i2 = this.f5223d;
                if (i2 >= 0) {
                    this.f5223d = -1;
                    recyclerView.z0(i2);
                    this.f5225f = false;
                } else {
                    if (!this.f5225f) {
                        this.f5226g = 0;
                        return;
                    }
                    e();
                    recyclerView.mViewFlinger.f(this.f5220a, this.f5221b, this.f5222c, this.f5224e);
                    int i3 = this.f5226g + 1;
                    this.f5226g = i3;
                    if (i3 > 10) {
                        Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f5225f = false;
                }
            }

            public void d(int i2, int i3, int i4, Interpolator interpolator) {
                this.f5220a = i2;
                this.f5221b = i3;
                this.f5222c = i4;
                this.f5224e = interpolator;
                this.f5225f = true;
            }

            public Action(int i2, int i3, int i4, Interpolator interpolator) {
                this.f5223d = -1;
                this.f5225f = false;
                this.f5226g = 0;
                this.f5220a = i2;
                this.f5221b = i3;
                this.f5222c = i4;
                this.f5224e = interpolator;
            }
        }

        public interface ScrollVectorProvider {
            PointF c(int i2);
        }

        public PointF a(int i2) {
            Object e2 = e();
            if (e2 instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) e2).c(i2);
            }
            Log.w(RecyclerView.TAG, "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            return null;
        }

        public View b(int i2) {
            return this.f5213b.mLayout.I(i2);
        }

        public int c() {
            return this.f5213b.mLayout.P();
        }

        public int d(View view) {
            return this.f5213b.g0(view);
        }

        public LayoutManager e() {
            return this.f5214c;
        }

        public int f() {
            return this.f5212a;
        }

        public boolean g() {
            return this.f5215d;
        }

        public boolean h() {
            return this.f5216e;
        }

        protected void i(PointF pointF) {
            float f2 = pointF.x;
            float f3 = pointF.y;
            float sqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }

        void j(int i2, int i3) {
            PointF a2;
            RecyclerView recyclerView = this.f5213b;
            if (this.f5212a == -1 || recyclerView == null) {
                r();
            }
            if (this.f5215d && this.f5217f == null && this.f5214c != null && (a2 = a(this.f5212a)) != null) {
                float f2 = a2.x;
                if (f2 != 0.0f || a2.y != 0.0f) {
                    recyclerView.k1((int) Math.signum(f2), (int) Math.signum(a2.y), null);
                }
            }
            this.f5215d = false;
            View view = this.f5217f;
            if (view != null) {
                if (d(view) == this.f5212a) {
                    o(this.f5217f, recyclerView.mState, this.f5218g);
                    this.f5218g.c(recyclerView);
                    r();
                } else {
                    Log.e(RecyclerView.TAG, "Passed over target position while smooth scrolling.");
                    this.f5217f = null;
                }
            }
            if (this.f5216e) {
                l(i2, i3, recyclerView.mState, this.f5218g);
                boolean a3 = this.f5218g.a();
                this.f5218g.c(recyclerView);
                if (a3 && this.f5216e) {
                    this.f5215d = true;
                    recyclerView.mViewFlinger.e();
                }
            }
        }

        protected void k(View view) {
            if (d(view) == f()) {
                this.f5217f = view;
            }
        }

        protected abstract void l(int i2, int i3, State state, Action action);

        protected abstract void m();

        protected abstract void n();

        protected abstract void o(View view, State state, Action action);

        public void p(int i2) {
            this.f5212a = i2;
        }

        void q(RecyclerView recyclerView, LayoutManager layoutManager) {
            recyclerView.mViewFlinger.g();
            if (this.f5219h) {
                Log.w(RecyclerView.TAG, "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f5213b = recyclerView;
            this.f5214c = layoutManager;
            int i2 = this.f5212a;
            if (i2 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.mState.f5227a = i2;
            this.f5216e = true;
            this.f5215d = true;
            this.f5217f = b(f());
            m();
            this.f5213b.mViewFlinger.e();
            this.f5219h = true;
        }

        protected final void r() {
            if (this.f5216e) {
                this.f5216e = false;
                n();
                this.f5213b.mState.f5227a = -1;
                this.f5217f = null;
                this.f5212a = -1;
                this.f5215d = false;
                this.f5214c.o1(this);
                this.f5214c = null;
                this.f5213b = null;
            }
        }
    }

    public static class State {

        /* renamed from: b, reason: collision with root package name */
        private SparseArray f5228b;

        /* renamed from: m, reason: collision with root package name */
        int f5239m;

        /* renamed from: n, reason: collision with root package name */
        long f5240n;

        /* renamed from: o, reason: collision with root package name */
        int f5241o;

        /* renamed from: p, reason: collision with root package name */
        int f5242p;

        /* renamed from: q, reason: collision with root package name */
        int f5243q;

        /* renamed from: a, reason: collision with root package name */
        int f5227a = -1;

        /* renamed from: c, reason: collision with root package name */
        int f5229c = 0;

        /* renamed from: d, reason: collision with root package name */
        int f5230d = 0;

        /* renamed from: e, reason: collision with root package name */
        int f5231e = 1;

        /* renamed from: f, reason: collision with root package name */
        int f5232f = 0;

        /* renamed from: g, reason: collision with root package name */
        boolean f5233g = false;

        /* renamed from: h, reason: collision with root package name */
        boolean f5234h = false;

        /* renamed from: i, reason: collision with root package name */
        boolean f5235i = false;

        /* renamed from: j, reason: collision with root package name */
        boolean f5236j = false;

        /* renamed from: k, reason: collision with root package name */
        boolean f5237k = false;

        /* renamed from: l, reason: collision with root package name */
        boolean f5238l = false;

        void a(int i2) {
            if ((this.f5231e & i2) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.f5231e));
        }

        public int b() {
            return this.f5234h ? this.f5229c - this.f5230d : this.f5232f;
        }

        public int c() {
            return this.f5227a;
        }

        public boolean d() {
            return this.f5227a != -1;
        }

        public boolean e() {
            return this.f5234h;
        }

        void f(Adapter adapter) {
            this.f5231e = 1;
            this.f5232f = adapter.m();
            this.f5234h = false;
            this.f5235i = false;
            this.f5236j = false;
        }

        public boolean g() {
            return this.f5238l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f5227a + ", mData=" + this.f5228b + ", mItemCount=" + this.f5232f + ", mIsMeasuring=" + this.f5236j + ", mPreviousLayoutItemCount=" + this.f5229c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f5230d + ", mStructureChanged=" + this.f5233g + ", mInPreLayout=" + this.f5234h + ", mRunSimpleAnimations=" + this.f5237k + ", mRunPredictiveAnimations=" + this.f5238l + '}';
        }
    }

    public static abstract class ViewCacheExtension {
        public abstract View a(Recycler recycler, int i2, int i3);
    }

    class ViewFlinger implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private int f5244c;

        /* renamed from: h, reason: collision with root package name */
        private int f5245h;

        /* renamed from: i, reason: collision with root package name */
        OverScroller f5246i;

        /* renamed from: j, reason: collision with root package name */
        Interpolator f5247j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f5248k;

        /* renamed from: l, reason: collision with root package name */
        private boolean f5249l;

        ViewFlinger() {
            Interpolator interpolator = RecyclerView.sQuinticInterpolator;
            this.f5247j = interpolator;
            this.f5248k = false;
            this.f5249l = false;
            this.f5246i = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int a(int i2, int i3, int i4, int i5) {
            int i6;
            int abs = Math.abs(i2);
            int abs2 = Math.abs(i3);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((i4 * i4) + (i5 * i5));
            int sqrt2 = (int) Math.sqrt((i2 * i2) + (i3 * i3));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i7 = width / 2;
            float f2 = width;
            float f3 = i7;
            float b2 = f3 + (b(Math.min(1.0f, (sqrt2 * 1.0f) / f2)) * f3);
            if (sqrt > 0) {
                i6 = Math.round(Math.abs(b2 / sqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i6 = (int) (((abs / f2) + 1.0f) * 300.0f);
            }
            return Math.min(i6, RecyclerView.MAX_SCROLL_DURATION);
        }

        private float b(float f2) {
            return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
        }

        private void d() {
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.a0(RecyclerView.this, this);
        }

        public void c(int i2, int i3) {
            RecyclerView.this.setScrollState(2);
            this.f5245h = 0;
            this.f5244c = 0;
            Interpolator interpolator = this.f5247j;
            Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
            if (interpolator != interpolator2) {
                this.f5247j = interpolator2;
                this.f5246i = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f5246i.fling(0, 0, i2, i3, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER);
            e();
        }

        void e() {
            if (this.f5248k) {
                this.f5249l = true;
            } else {
                d();
            }
        }

        public void f(int i2, int i3, int i4, Interpolator interpolator) {
            if (i4 == Integer.MIN_VALUE) {
                i4 = a(i2, i3, 0, 0);
            }
            int i5 = i4;
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            if (this.f5247j != interpolator) {
                this.f5247j = interpolator;
                this.f5246i = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f5245h = 0;
            this.f5244c = 0;
            RecyclerView.this.setScrollState(2);
            this.f5246i.startScroll(0, 0, i2, i3, i5);
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f5246i.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2;
            int i3;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout == null) {
                g();
                return;
            }
            this.f5249l = false;
            this.f5248k = true;
            recyclerView.v();
            OverScroller overScroller = this.f5246i;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i4 = currX - this.f5244c;
                int i5 = currY - this.f5245h;
                this.f5244c = currX;
                this.f5245h = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.mReusableIntPair;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.G(i4, i5, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.mReusableIntPair;
                    i4 -= iArr2[0];
                    i5 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.u(i4, i5);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.mAdapter != null) {
                    int[] iArr3 = recyclerView3.mReusableIntPair;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.k1(i4, i5, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.mReusableIntPair;
                    i3 = iArr4[0];
                    i2 = iArr4[1];
                    i4 -= i3;
                    i5 -= i2;
                    SmoothScroller smoothScroller = recyclerView4.mLayout.f5173g;
                    if (smoothScroller != null && !smoothScroller.g() && smoothScroller.h()) {
                        int b2 = RecyclerView.this.mState.b();
                        if (b2 == 0) {
                            smoothScroller.r();
                        } else if (smoothScroller.f() >= b2) {
                            smoothScroller.p(b2 - 1);
                            smoothScroller.j(i3, i2);
                        } else {
                            smoothScroller.j(i3, i2);
                        }
                    }
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.mReusableIntPair;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.H(i3, i2, i4, i5, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.mReusableIntPair;
                int i6 = i4 - iArr6[0];
                int i7 = i5 - iArr6[1];
                if (i3 != 0 || i2 != 0) {
                    recyclerView6.J(i3, i2);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i6 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i7 != 0));
                SmoothScroller smoothScroller2 = RecyclerView.this.mLayout.f5173g;
                if ((smoothScroller2 == null || !smoothScroller2.g()) && z) {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i8 = i6 < 0 ? -currVelocity : i6 > 0 ? currVelocity : 0;
                        if (i7 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i7 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.b(i8, currVelocity);
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView.this.mPrefetchRegistry.b();
                    }
                } else {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    GapWorker gapWorker = recyclerView7.mGapWorker;
                    if (gapWorker != null) {
                        gapWorker.f(recyclerView7, i3, i2);
                    }
                }
            }
            SmoothScroller smoothScroller3 = RecyclerView.this.mLayout.f5173g;
            if (smoothScroller3 != null && smoothScroller3.g()) {
                smoothScroller3.j(0, 0);
            }
            this.f5248k = false;
            if (this.f5249l) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.w1(1);
            }
        }
    }

    public static abstract class ViewHolder {

        /* renamed from: r, reason: collision with root package name */
        private static final List f5251r = Collections.emptyList();

        /* renamed from: a, reason: collision with root package name */
        public final View f5252a;

        /* renamed from: b, reason: collision with root package name */
        WeakReference f5253b;

        /* renamed from: j, reason: collision with root package name */
        int f5261j;

        /* renamed from: q, reason: collision with root package name */
        RecyclerView f5268q;

        /* renamed from: c, reason: collision with root package name */
        int f5254c = -1;

        /* renamed from: d, reason: collision with root package name */
        int f5255d = -1;

        /* renamed from: e, reason: collision with root package name */
        long f5256e = -1;

        /* renamed from: f, reason: collision with root package name */
        int f5257f = -1;

        /* renamed from: g, reason: collision with root package name */
        int f5258g = -1;

        /* renamed from: h, reason: collision with root package name */
        ViewHolder f5259h = null;

        /* renamed from: i, reason: collision with root package name */
        ViewHolder f5260i = null;

        /* renamed from: k, reason: collision with root package name */
        List f5262k = null;

        /* renamed from: l, reason: collision with root package name */
        List f5263l = null;

        /* renamed from: m, reason: collision with root package name */
        private int f5264m = 0;

        /* renamed from: n, reason: collision with root package name */
        Recycler f5265n = null;

        /* renamed from: o, reason: collision with root package name */
        boolean f5266o = false;

        /* renamed from: p, reason: collision with root package name */
        private int f5267p = 0;

        @VisibleForTesting
        int mPendingAccessibilityState = -1;

        public ViewHolder(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f5252a = view;
        }

        private void h() {
            if (this.f5262k == null) {
                ArrayList arrayList = new ArrayList();
                this.f5262k = arrayList;
                this.f5263l = Collections.unmodifiableList(arrayList);
            }
        }

        boolean A() {
            return (this.f5261j & 2) != 0;
        }

        void B(int i2, boolean z) {
            if (this.f5255d == -1) {
                this.f5255d = this.f5254c;
            }
            if (this.f5258g == -1) {
                this.f5258g = this.f5254c;
            }
            if (z) {
                this.f5258g += i2;
            }
            this.f5254c += i2;
            if (this.f5252a.getLayoutParams() != null) {
                ((LayoutParams) this.f5252a.getLayoutParams()).f5193c = true;
            }
        }

        void C(RecyclerView recyclerView) {
            int i2 = this.mPendingAccessibilityState;
            if (i2 != -1) {
                this.f5267p = i2;
            } else {
                this.f5267p = ViewCompat.t(this.f5252a);
            }
            recyclerView.setChildImportantForAccessibilityInternal(this, 4);
        }

        void D(RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(this, this.f5267p);
            this.f5267p = 0;
        }

        void E() {
            this.f5261j = 0;
            this.f5254c = -1;
            this.f5255d = -1;
            this.f5256e = -1L;
            this.f5258g = -1;
            this.f5264m = 0;
            this.f5259h = null;
            this.f5260i = null;
            e();
            this.f5267p = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.s(this);
        }

        void F() {
            if (this.f5255d == -1) {
                this.f5255d = this.f5254c;
            }
        }

        void G(int i2, int i3) {
            this.f5261j = (i2 & i3) | (this.f5261j & (~i3));
        }

        public final void H(boolean z) {
            int i2 = this.f5264m;
            int i3 = z ? i2 - 1 : i2 + 1;
            this.f5264m = i3;
            if (i3 < 0) {
                this.f5264m = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i3 == 1) {
                this.f5261j |= 16;
            } else if (z && i3 == 0) {
                this.f5261j &= -17;
            }
        }

        void I(Recycler recycler, boolean z) {
            this.f5265n = recycler;
            this.f5266o = z;
        }

        boolean J() {
            return (this.f5261j & 16) != 0;
        }

        boolean K() {
            return (this.f5261j & 128) != 0;
        }

        void L() {
            this.f5265n.J(this);
        }

        boolean M() {
            return (this.f5261j & 32) != 0;
        }

        void b(Object obj) {
            if (obj == null) {
                c(1024);
            } else if ((1024 & this.f5261j) == 0) {
                h();
                this.f5262k.add(obj);
            }
        }

        void c(int i2) {
            this.f5261j = i2 | this.f5261j;
        }

        void d() {
            this.f5255d = -1;
            this.f5258g = -1;
        }

        void e() {
            List list = this.f5262k;
            if (list != null) {
                list.clear();
            }
            this.f5261j &= -1025;
        }

        void f() {
            this.f5261j &= -33;
        }

        void g() {
            this.f5261j &= -257;
        }

        boolean i() {
            return (this.f5261j & 16) == 0 && ViewCompat.K(this.f5252a);
        }

        void j(int i2, int i3, boolean z) {
            c(8);
            B(i3, z);
            this.f5254c = i2;
        }

        public final int k() {
            RecyclerView recyclerView = this.f5268q;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.d0(this);
        }

        public final long l() {
            return this.f5256e;
        }

        public final int m() {
            return this.f5257f;
        }

        public final int n() {
            int i2 = this.f5258g;
            return i2 == -1 ? this.f5254c : i2;
        }

        public final int o() {
            return this.f5255d;
        }

        List p() {
            if ((this.f5261j & 1024) != 0) {
                return f5251r;
            }
            List list = this.f5262k;
            return (list == null || list.size() == 0) ? f5251r : this.f5263l;
        }

        boolean q(int i2) {
            return (this.f5261j & i2) != 0;
        }

        boolean r() {
            return (this.f5261j & 512) != 0 || u();
        }

        boolean s() {
            return (this.f5252a.getParent() == null || this.f5252a.getParent() == this.f5268q) ? false : true;
        }

        boolean t() {
            return (this.f5261j & 1) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f5254c + " id=" + this.f5256e + ", oldPos=" + this.f5255d + ", pLpos:" + this.f5258g);
            if (x()) {
                sb.append(" scrap ");
                sb.append(this.f5266o ? "[changeScrap]" : "[attachedScrap]");
            }
            if (u()) {
                sb.append(" invalid");
            }
            if (!t()) {
                sb.append(" unbound");
            }
            if (A()) {
                sb.append(" update");
            }
            if (w()) {
                sb.append(" removed");
            }
            if (K()) {
                sb.append(" ignored");
            }
            if (y()) {
                sb.append(" tmpDetached");
            }
            if (!v()) {
                sb.append(" not recyclable(" + this.f5264m + ")");
            }
            if (r()) {
                sb.append(" undefined adapter position");
            }
            if (this.f5252a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        boolean u() {
            return (this.f5261j & 4) != 0;
        }

        public final boolean v() {
            return (this.f5261j & 16) == 0 && !ViewCompat.K(this.f5252a);
        }

        boolean w() {
            return (this.f5261j & 8) != 0;
        }

        boolean x() {
            return this.f5265n != null;
        }

        boolean y() {
            return (this.f5261j & 256) != 0;
        }

        boolean z() {
            return (this.f5261j & 2) != 0;
        }
    }

    static {
        Class cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new Interpolator() { // from class: androidx.recyclerview.widget.RecyclerView.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private void B() {
        int i2 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i2 == 0 || !w0()) {
            return;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        obtain.setEventType(2048);
        AccessibilityEventCompat.b(obtain, i2);
        sendAccessibilityEventUnchecked(obtain);
    }

    private void D() {
        this.mState.a(1);
        R(this.mState);
        this.mState.f5236j = false;
        t1();
        this.mViewInfoStore.f();
        J0();
        R0();
        h1();
        State state = this.mState;
        state.f5235i = state.f5237k && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        state.f5234h = state.f5238l;
        state.f5232f = this.mAdapter.m();
        W(this.mMinMaxLayoutPositions);
        if (this.mState.f5237k) {
            int g2 = this.mChildHelper.g();
            for (int i2 = 0; i2 < g2; i2++) {
                ViewHolder i0 = i0(this.mChildHelper.f(i2));
                if (!i0.K() && (!i0.u() || this.mAdapter.q())) {
                    this.mViewInfoStore.e(i0, this.mItemAnimator.u(this.mState, i0, ItemAnimator.e(i0), i0.p()));
                    if (this.mState.f5235i && i0.z() && !i0.w() && !i0.K() && !i0.u()) {
                        this.mViewInfoStore.c(e0(i0), i0);
                    }
                }
            }
        }
        if (this.mState.f5238l) {
            i1();
            State state2 = this.mState;
            boolean z = state2.f5233g;
            state2.f5233g = false;
            this.mLayout.g1(this.mRecycler, state2);
            this.mState.f5233g = z;
            for (int i3 = 0; i3 < this.mChildHelper.g(); i3++) {
                ViewHolder i02 = i0(this.mChildHelper.f(i3));
                if (!i02.K() && !this.mViewInfoStore.i(i02)) {
                    int e2 = ItemAnimator.e(i02);
                    boolean q2 = i02.q(8192);
                    if (!q2) {
                        e2 |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo u = this.mItemAnimator.u(this.mState, i02, e2, i02.p());
                    if (q2) {
                        U0(i02, u);
                    } else {
                        this.mViewInfoStore.a(i02, u);
                    }
                }
            }
            t();
        } else {
            t();
        }
        K0();
        v1(false);
        this.mState.f5231e = 2;
    }

    private void E() {
        t1();
        J0();
        this.mState.a(6);
        this.mAdapterHelper.j();
        this.mState.f5232f = this.mAdapter.m();
        State state = this.mState;
        state.f5230d = 0;
        state.f5234h = false;
        this.mLayout.g1(this.mRecycler, state);
        State state2 = this.mState;
        state2.f5233g = false;
        this.mPendingSavedState = null;
        state2.f5237k = state2.f5237k && this.mItemAnimator != null;
        state2.f5231e = 4;
        K0();
        v1(false);
    }

    private void F() {
        this.mState.a(4);
        t1();
        J0();
        State state = this.mState;
        state.f5231e = 1;
        if (state.f5237k) {
            for (int g2 = this.mChildHelper.g() - 1; g2 >= 0; g2--) {
                ViewHolder i0 = i0(this.mChildHelper.f(g2));
                if (!i0.K()) {
                    long e0 = e0(i0);
                    ItemAnimator.ItemHolderInfo t = this.mItemAnimator.t(this.mState, i0);
                    ViewHolder g3 = this.mViewInfoStore.g(e0);
                    if (g3 == null || g3.K()) {
                        this.mViewInfoStore.d(i0, t);
                    } else {
                        boolean h2 = this.mViewInfoStore.h(g3);
                        boolean h3 = this.mViewInfoStore.h(i0);
                        if (h2 && g3 == i0) {
                            this.mViewInfoStore.d(i0, t);
                        } else {
                            ItemAnimator.ItemHolderInfo n2 = this.mViewInfoStore.n(g3);
                            this.mViewInfoStore.d(i0, t);
                            ItemAnimator.ItemHolderInfo m2 = this.mViewInfoStore.m(i0);
                            if (n2 == null) {
                                o0(e0, i0, g3);
                            } else {
                                n(g3, i0, n2, m2, h2, h3);
                            }
                        }
                    }
                }
            }
            this.mViewInfoStore.o(this.mViewInfoProcessCallback);
        }
        this.mLayout.u1(this.mRecycler);
        State state2 = this.mState;
        state2.f5229c = state2.f5232f;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        state2.f5237k = false;
        state2.f5238l = false;
        this.mLayout.f5174h = false;
        ArrayList arrayList = this.mRecycler.f5202b;
        if (arrayList != null) {
            arrayList.clear();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager.f5180n) {
            layoutManager.f5179m = 0;
            layoutManager.f5180n = false;
            this.mRecycler.K();
        }
        this.mLayout.h1(this.mState);
        K0();
        v1(false);
        this.mViewInfoStore.f();
        int[] iArr = this.mMinMaxLayoutPositions;
        if (y(iArr[0], iArr[1])) {
            J(0, 0);
        }
        V0();
        f1();
    }

    private boolean L(MotionEvent motionEvent) {
        OnItemTouchListener onItemTouchListener = this.mInterceptingOnItemTouchListener;
        if (onItemTouchListener == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return V(motionEvent);
        }
        onItemTouchListener.onTouchEvent(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.mInterceptingOnItemTouchListener = null;
        }
        return true;
    }

    private void M0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i2);
            int x = (int) (motionEvent.getX(i2) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i2) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    private boolean Q0() {
        return this.mItemAnimator != null && this.mLayout.U1();
    }

    private void R0() {
        boolean z;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.y();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.b1(this);
            }
        }
        if (Q0()) {
            this.mAdapterHelper.w();
        } else {
            this.mAdapterHelper.j();
        }
        boolean z2 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.f5237k = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z = this.mDataSetHasChangedAfterLayout) || z2 || this.mLayout.f5174h) && (!z || this.mAdapter.q());
        State state = this.mState;
        state.f5238l = state.f5237k && z2 && !this.mDataSetHasChangedAfterLayout && Q0();
    }

    private void T0(float f2, float f3, float f4, float f5) {
        boolean z = true;
        if (f3 < 0.0f) {
            N();
            EdgeEffectCompat.c(this.mLeftGlow, (-f3) / getWidth(), 1.0f - (f4 / getHeight()));
        } else if (f3 > 0.0f) {
            O();
            EdgeEffectCompat.c(this.mRightGlow, f3 / getWidth(), f4 / getHeight());
        } else {
            z = false;
        }
        if (f5 < 0.0f) {
            P();
            EdgeEffectCompat.c(this.mTopGlow, (-f5) / getHeight(), f2 / getWidth());
        } else if (f5 > 0.0f) {
            M();
            EdgeEffectCompat.c(this.mBottomGlow, f5 / getHeight(), 1.0f - (f2 / getWidth()));
        } else if (!z && f3 == 0.0f && f5 == 0.0f) {
            return;
        }
        ViewCompat.Z(this);
    }

    private boolean V(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i2);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private void V0() {
        View findViewById;
        if (!this.mPreserveFocusAfterLayout || this.mAdapter == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.mChildHelper.n(focusedChild)) {
                    return;
                }
            } else if (this.mChildHelper.g() == 0) {
                requestFocus();
                return;
            }
        }
        View view = null;
        ViewHolder a0 = (this.mState.f5240n == -1 || !this.mAdapter.q()) ? null : a0(this.mState.f5240n);
        if (a0 != null && !this.mChildHelper.n(a0.f5252a) && a0.f5252a.hasFocusable()) {
            view = a0.f5252a;
        } else if (this.mChildHelper.g() > 0) {
            view = Y();
        }
        if (view != null) {
            int i2 = this.mState.f5241o;
            if (i2 != -1 && (findViewById = view.findViewById(i2)) != null && findViewById.isFocusable()) {
                view = findViewById;
            }
            view.requestFocus();
        }
    }

    private void W(int[] iArr) {
        int g2 = this.mChildHelper.g();
        if (g2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < g2; i4++) {
            ViewHolder i0 = i0(this.mChildHelper.f(i4));
            if (!i0.K()) {
                int n2 = i0.n();
                if (n2 < i2) {
                    i2 = n2;
                }
                if (n2 > i3) {
                    i3 = n2;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    private void W0() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.Z(this);
        }
    }

    static RecyclerView X(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView X = X(viewGroup.getChildAt(i2));
            if (X != null) {
                return X;
            }
        }
        return null;
    }

    private View Y() {
        ViewHolder Z;
        State state = this.mState;
        int i2 = state.f5239m;
        if (i2 == -1) {
            i2 = 0;
        }
        int b2 = state.b();
        for (int i3 = i2; i3 < b2; i3++) {
            ViewHolder Z2 = Z(i3);
            if (Z2 == null) {
                break;
            }
            if (Z2.f5252a.hasFocusable()) {
                return Z2.f5252a;
            }
        }
        int min = Math.min(b2, i2);
        do {
            min--;
            if (min < 0 || (Z = Z(min)) == null) {
                return null;
            }
        } while (!Z.f5252a.hasFocusable());
        return Z.f5252a;
    }

    private void e1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.f5193c) {
                Rect rect = layoutParams2.f5192b;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.B1(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    private void f1() {
        State state = this.mState;
        state.f5240n = -1L;
        state.f5239m = -1;
        state.f5241o = -1;
    }

    private void g(ViewHolder viewHolder) {
        View view = viewHolder.f5252a;
        boolean z = view.getParent() == this;
        this.mRecycler.J(h0(view));
        if (viewHolder.y()) {
            this.mChildHelper.c(view, -1, view.getLayoutParams(), true);
        } else if (z) {
            this.mChildHelper.k(view);
        } else {
            this.mChildHelper.b(view, true);
        }
    }

    private void g1() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        w1(0);
        W0();
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private void h1() {
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) ? getFocusedChild() : null;
        ViewHolder U = focusedChild != null ? U(focusedChild) : null;
        if (U == null) {
            f1();
            return;
        }
        this.mState.f5240n = this.mAdapter.q() ? U.l() : -1L;
        this.mState.f5239m = this.mDataSetHasChangedAfterLayout ? -1 : U.w() ? U.f5255d : U.k();
        this.mState.f5241o = l0(U.f5252a);
    }

    static ViewHolder i0(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).f5191a;
    }

    static void k0(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.f5192b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    private int l0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String m0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private void m1(Adapter adapter, boolean z, boolean z2) {
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.K(this.mObserver);
            this.mAdapter.D(this);
        }
        if (!z || z2) {
            X0();
        }
        this.mAdapterHelper.y();
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.I(this.mObserver);
            adapter.z(this);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.N0(adapter3, this.mAdapter);
        }
        this.mRecycler.x(adapter3, this.mAdapter, z);
        this.mState.f5233g = true;
    }

    private void n(ViewHolder viewHolder, ViewHolder viewHolder2, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2, boolean z, boolean z2) {
        viewHolder.H(false);
        if (z) {
            g(viewHolder);
        }
        if (viewHolder != viewHolder2) {
            if (z2) {
                g(viewHolder2);
            }
            viewHolder.f5259h = viewHolder2;
            g(viewHolder);
            this.mRecycler.J(viewHolder);
            viewHolder2.H(false);
            viewHolder2.f5260i = viewHolder;
        }
        if (this.mItemAnimator.b(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            P0();
        }
    }

    private void o0(long j2, ViewHolder viewHolder, ViewHolder viewHolder2) {
        int g2 = this.mChildHelper.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ViewHolder i0 = i0(this.mChildHelper.f(i2));
            if (i0 != viewHolder && e0(i0) == j2) {
                Adapter adapter = this.mAdapter;
                if (adapter == null || !adapter.q()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + i0 + " \n View Holder 2:" + viewHolder + Q());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + i0 + " \n View Holder 2:" + viewHolder + Q());
            }
        }
        Log.e(TAG, "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + viewHolder2 + " cannot be found but it is necessary for " + viewHolder + Q());
    }

    private boolean q0() {
        int g2 = this.mChildHelper.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ViewHolder i0 = i0(this.mChildHelper.f(i2));
            if (i0 != null && !i0.K() && i0.z()) {
                return true;
            }
        }
        return false;
    }

    private void r() {
        g1();
        setScrollState(0);
    }

    static void s(ViewHolder viewHolder) {
        WeakReference weakReference = viewHolder.f5253b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view == viewHolder.f5252a) {
                    return;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            viewHolder.f5253b = null;
        }
    }

    private void s0() {
        if (ViewCompat.u(this) == 0) {
            ViewCompat.u0(this, 8);
        }
    }

    private void t0() {
        this.mChildHelper = new ChildHelper(new ChildHelper.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.5
            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public View a(int i2) {
                return RecyclerView.this.getChildAt(i2);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void b(View view) {
                ViewHolder i0 = RecyclerView.i0(view);
                if (i0 != null) {
                    i0.C(RecyclerView.this);
                }
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public int c() {
                return RecyclerView.this.getChildCount();
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public ViewHolder d(View view) {
                return RecyclerView.i0(view);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void e(int i2) {
                ViewHolder i0;
                View a2 = a(i2);
                if (a2 != null && (i0 = RecyclerView.i0(a2)) != null) {
                    if (i0.y() && !i0.K()) {
                        throw new IllegalArgumentException("called detach on an already detached child " + i0 + RecyclerView.this.Q());
                    }
                    i0.c(256);
                }
                RecyclerView.this.detachViewFromParent(i2);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void f(View view, int i2) {
                RecyclerView.this.addView(view, i2);
                RecyclerView.this.z(view);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void g() {
                int c2 = c();
                for (int i2 = 0; i2 < c2; i2++) {
                    View a2 = a(i2);
                    RecyclerView.this.A(a2);
                    a2.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public int h(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void i(View view) {
                ViewHolder i0 = RecyclerView.i0(view);
                if (i0 != null) {
                    i0.D(RecyclerView.this);
                }
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void j(int i2) {
                View childAt = RecyclerView.this.getChildAt(i2);
                if (childAt != null) {
                    RecyclerView.this.A(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i2);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void k(View view, int i2, ViewGroup.LayoutParams layoutParams) {
                ViewHolder i0 = RecyclerView.i0(view);
                if (i0 != null) {
                    if (!i0.y() && !i0.K()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + i0 + RecyclerView.this.Q());
                    }
                    i0.g();
                }
                RecyclerView.this.attachViewToParent(view, i2, layoutParams);
            }
        });
    }

    private void w(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Object[] objArr;
        Constructor constructor;
        if (str != null) {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            String m0 = m0(context, trim);
            try {
                Class<? extends U> asSubclass = Class.forName(m0, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(LayoutManager.class);
                try {
                    constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    objArr = null;
                    try {
                        constructor = asSubclass.getConstructor(null);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + m0, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((LayoutManager) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + m0, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + m0, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + m0, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + m0, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + m0, e8);
            }
        }
    }

    private boolean y(int i2, int i3) {
        W(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    private boolean y0(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || T(view2) == null) {
            return false;
        }
        if (view == null || T(view) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c2 = 65535;
        int i4 = this.mLayout.f0() == 1 ? -1 : 1;
        Rect rect = this.mTempRect;
        int i5 = rect.left;
        Rect rect2 = this.mTempRect2;
        int i6 = rect2.left;
        if ((i5 < i6 || rect.right <= i6) && rect.right < rect2.right) {
            i3 = 1;
        } else {
            int i7 = rect.right;
            int i8 = rect2.right;
            i3 = ((i7 > i8 || i5 >= i8) && i5 > i6) ? -1 : 0;
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        if ((i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom) {
            c2 = 1;
        } else {
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            if ((i11 <= i12 && i9 < i12) || i9 <= i10) {
                c2 = 0;
            }
        }
        if (i2 == 1) {
            return c2 < 0 || (c2 == 0 && i3 * i4 <= 0);
        }
        if (i2 == 2) {
            return c2 > 0 || (c2 == 0 && i3 * i4 >= 0);
        }
        if (i2 == 17) {
            return i3 < 0;
        }
        if (i2 == 33) {
            return c2 < 0;
        }
        if (i2 == 66) {
            return i3 > 0;
        }
        if (i2 == 130) {
            return c2 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i2 + Q());
    }

    private void y1() {
        this.mViewFlinger.g();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.T1();
        }
    }

    void A(View view) {
        ViewHolder i0 = i0(view);
        I0(view);
        Adapter adapter = this.mAdapter;
        if (adapter != null && i0 != null) {
            adapter.G(i0);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).a(view);
            }
        }
    }

    void A0() {
        int j2 = this.mChildHelper.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ((LayoutParams) this.mChildHelper.i(i2).getLayoutParams()).f5193c = true;
        }
        this.mRecycler.s();
    }

    void B0() {
        int j2 = this.mChildHelper.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i2));
            if (i0 != null && !i0.K()) {
                i0.c(6);
            }
        }
        A0();
        this.mRecycler.t();
    }

    void C() {
        if (this.mAdapter == null) {
            Log.e(TAG, "No adapter attached; skipping layout");
            return;
        }
        if (this.mLayout == null) {
            Log.e(TAG, "No layout manager attached; skipping layout");
            return;
        }
        State state = this.mState;
        state.f5236j = false;
        if (state.f5231e == 1) {
            D();
            this.mLayout.I1(this);
            E();
        } else if (!this.mAdapterHelper.q() && this.mLayout.w0() == getWidth() && this.mLayout.c0() == getHeight()) {
            this.mLayout.I1(this);
        } else {
            this.mLayout.I1(this);
            E();
        }
        F();
    }

    public void C0(int i2) {
        int g2 = this.mChildHelper.g();
        for (int i3 = 0; i3 < g2; i3++) {
            this.mChildHelper.f(i3).offsetLeftAndRight(i2);
        }
    }

    public void D0(int i2) {
        int g2 = this.mChildHelper.g();
        for (int i3 = 0; i3 < g2; i3++) {
            this.mChildHelper.f(i3).offsetTopAndBottom(i2);
        }
    }

    void E0(int i2, int i3) {
        int j2 = this.mChildHelper.j();
        for (int i4 = 0; i4 < j2; i4++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i4));
            if (i0 != null && !i0.K() && i0.f5254c >= i2) {
                i0.B(i3, false);
                this.mState.f5233g = true;
            }
        }
        this.mRecycler.u(i2, i3);
        requestLayout();
    }

    void F0(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int j2 = this.mChildHelper.j();
        if (i2 < i3) {
            i6 = -1;
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i8 = 0; i8 < j2; i8++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i8));
            if (i0 != null && (i7 = i0.f5254c) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    i0.B(i3 - i2, false);
                } else {
                    i0.B(i6, false);
                }
                this.mState.f5233g = true;
            }
        }
        this.mRecycler.v(i2, i3);
        requestLayout();
    }

    public boolean G(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().d(i2, i3, iArr, iArr2, i4);
    }

    void G0(int i2, int i3, boolean z) {
        int i4 = i2 + i3;
        int j2 = this.mChildHelper.j();
        for (int i5 = 0; i5 < j2; i5++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i5));
            if (i0 != null && !i0.K()) {
                int i6 = i0.f5254c;
                if (i6 >= i4) {
                    i0.B(-i3, z);
                    this.mState.f5233g = true;
                } else if (i6 >= i2) {
                    i0.j(i2 - 1, -i3, z);
                    this.mState.f5233g = true;
                }
            }
        }
        this.mRecycler.w(i2, i3, z);
        requestLayout();
    }

    public final void H(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void H0(View view) {
    }

    void I(int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.n1(i2);
        }
        N0(i2);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.a(this, i2);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).a(this, i2);
            }
        }
    }

    public void I0(View view) {
    }

    void J(int i2, int i3) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        O0(i2, i3);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.b(this, i2, i3);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).b(this, i2, i3);
            }
        }
        this.mDispatchScrollCounter--;
    }

    void J0() {
        this.mLayoutOrScrollCounter++;
    }

    void K() {
        int i2;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(size);
            if (viewHolder.f5252a.getParent() == this && !viewHolder.K() && (i2 = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.s0(viewHolder.f5252a, i2);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    void K0() {
        L0(true);
    }

    void L0(boolean z) {
        int i2 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i2;
        if (i2 < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                B();
                K();
            }
        }
    }

    void M() {
        if (this.mBottomGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 3);
        this.mBottomGlow = a2;
        if (this.mClipToPadding) {
            a2.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            a2.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    void N() {
        if (this.mLeftGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 0);
        this.mLeftGlow = a2;
        if (this.mClipToPadding) {
            a2.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            a2.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void N0(int i2) {
    }

    void O() {
        if (this.mRightGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 2);
        this.mRightGlow = a2;
        if (this.mClipToPadding) {
            a2.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            a2.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void O0(int i2, int i3) {
    }

    void P() {
        if (this.mTopGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 1);
        this.mTopGlow = a2;
        if (this.mClipToPadding) {
            a2.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            a2.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    void P0() {
        if (this.mPostedAnimatorRunner || !this.mIsAttached) {
            return;
        }
        ViewCompat.a0(this, this.mItemAnimatorRunner);
        this.mPostedAnimatorRunner = true;
    }

    String Q() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    final void R(State state) {
        if (getScrollState() != 2) {
            state.f5242p = 0;
            state.f5243q = 0;
        } else {
            OverScroller overScroller = this.mViewFlinger.f5246i;
            state.f5242p = overScroller.getFinalX() - overScroller.getCurrX();
            state.f5243q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    public View S(float f2, float f3) {
        for (int g2 = this.mChildHelper.g() - 1; g2 >= 0; g2--) {
            View f4 = this.mChildHelper.f(g2);
            float translationX = f4.getTranslationX();
            float translationY = f4.getTranslationY();
            if (f2 >= f4.getLeft() + translationX && f2 <= f4.getRight() + translationX && f3 >= f4.getTop() + translationY && f3 <= f4.getBottom() + translationY) {
                return f4;
            }
        }
        return null;
    }

    void S0(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        B0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View T(android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L4:
            if (r0 == 0) goto L14
            if (r0 == r2) goto L14
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L14
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L4
        L14:
            if (r0 != r2) goto L17
            goto L18
        L17:
            r3 = 0
        L18:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.T(android.view.View):android.view.View");
    }

    public ViewHolder U(View view) {
        View T = T(view);
        if (T == null) {
            return null;
        }
        return h0(T);
    }

    void U0(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.G(0, 8192);
        if (this.mState.f5235i && viewHolder.z() && !viewHolder.w() && !viewHolder.K()) {
            this.mViewInfoStore.c(e0(viewHolder), viewHolder);
        }
        this.mViewInfoStore.e(viewHolder, itemHolderInfo);
    }

    void X0() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.k();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.t1(this.mRecycler);
            this.mLayout.u1(this.mRecycler);
        }
        this.mRecycler.c();
    }

    boolean Y0(View view) {
        t1();
        boolean r2 = this.mChildHelper.r(view);
        if (r2) {
            ViewHolder i0 = i0(view);
            this.mRecycler.J(i0);
            this.mRecycler.C(i0);
        }
        v1(!r2);
        return r2;
    }

    public ViewHolder Z(int i2) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int j2 = this.mChildHelper.j();
        for (int i3 = 0; i3 < j2; i3++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i3));
            if (i0 != null && !i0.w() && d0(i0) == i2) {
                if (!this.mChildHelper.n(i0.f5252a)) {
                    return i0;
                }
                viewHolder = i0;
            }
        }
        return viewHolder;
    }

    public void Z0(ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.m("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        A0();
        requestLayout();
    }

    public ViewHolder a0(long j2) {
        Adapter adapter = this.mAdapter;
        ViewHolder viewHolder = null;
        if (adapter != null && adapter.q()) {
            int j3 = this.mChildHelper.j();
            for (int i2 = 0; i2 < j3; i2++) {
                ViewHolder i0 = i0(this.mChildHelper.i(i2));
                if (i0 != null && !i0.w() && i0.l() == j2) {
                    if (!this.mChildHelper.n(i0.f5252a)) {
                        return i0;
                    }
                    viewHolder = i0;
                }
            }
        }
        return viewHolder;
    }

    public void a1(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list == null) {
            return;
        }
        list.remove(onChildAttachStateChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i2, int i3) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.O0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    void b(int i2, int i3) {
        if (i2 < 0) {
            N();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            O();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            P();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            M();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        ViewCompat.Z(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    androidx.recyclerview.widget.RecyclerView.ViewHolder b0(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.ChildHelper r0 = r5.mChildHelper
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.ChildHelper r3 = r5.mChildHelper
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$ViewHolder r3 = i0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.w()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f5254c
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.n()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.ChildHelper r1 = r5.mChildHelper
            android.view.View r4 = r3.f5252a
            boolean r1 = r1.n(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.b0(int, boolean):androidx.recyclerview.widget.RecyclerView$ViewHolder");
    }

    public void b1(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mInterceptingOnItemTouchListener == onItemTouchListener) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public boolean c0(int i2, int i3) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.mLayoutSuppressed) {
            return false;
        }
        int q2 = layoutManager.q();
        boolean r2 = this.mLayout.r();
        if (q2 == 0 || Math.abs(i2) < this.mMinFlingVelocity) {
            i2 = 0;
        }
        if (!r2 || Math.abs(i3) < this.mMinFlingVelocity) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z = q2 != 0 || r2;
            dispatchNestedFling(f2, f3, z);
            OnFlingListener onFlingListener = this.mOnFlingListener;
            if (onFlingListener != null && onFlingListener.a(i2, i3)) {
                return true;
            }
            if (z) {
                if (r2) {
                    q2 = (q2 == true ? 1 : 0) | 2;
                }
                u1(q2, 1);
                int i4 = this.mMaxFlingVelocity;
                int max = Math.max(-i4, Math.min(i2, i4));
                int i5 = this.mMaxFlingVelocity;
                this.mViewFlinger.c(max, Math.max(-i5, Math.min(i3, i5)));
                return true;
            }
        }
        return false;
    }

    public void c1(OnScrollListener onScrollListener) {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.remove(onScrollListener);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.mLayout.s((LayoutParams) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.q()) {
            return this.mLayout.w(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.q()) {
            return this.mLayout.x(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.q()) {
            return this.mLayout.y(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.r()) {
            return this.mLayout.z(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.r()) {
            return this.mLayout.A(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.r()) {
            return this.mLayout.B(this.mState);
        }
        return 0;
    }

    int d0(ViewHolder viewHolder) {
        if (viewHolder.q(524) || !viewHolder.t()) {
            return -1;
        }
        return this.mAdapterHelper.e(viewHolder.f5254c);
    }

    void d1() {
        ViewHolder viewHolder;
        int g2 = this.mChildHelper.g();
        for (int i2 = 0; i2 < g2; i2++) {
            View f2 = this.mChildHelper.f(i2);
            ViewHolder h0 = h0(f2);
            if (h0 != null && (viewHolder = h0.f5260i) != null) {
                View view = viewHolder.f5252a;
                int left = f2.getLeft();
                int top = f2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return getScrollingChildHelper().a(f2, f3, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().f(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.mItemDecorations.get(i2).onDrawOver(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(save4);
        }
        if ((z || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.p()) && !z) {
            return;
        }
        ViewCompat.Z(this);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    long e0(ViewHolder viewHolder) {
        return this.mAdapter.q() ? viewHolder.l() : viewHolder.f5254c;
    }

    public int f0(View view) {
        ViewHolder i0 = i0(view);
        if (i0 != null) {
            return i0.k();
        }
        return -1;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View view2;
        boolean z;
        View Z0 = this.mLayout.Z0(view, i2);
        if (Z0 != null) {
            return Z0;
        }
        boolean z2 = (this.mAdapter == null || this.mLayout == null || x0() || this.mLayoutSuppressed) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z2 && (i2 == 2 || i2 == 1)) {
            if (this.mLayout.r()) {
                int i3 = i2 == 2 ? 130 : 33;
                z = focusFinder.findNextFocus(this, view, i3) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i3;
                }
            } else {
                z = false;
            }
            if (!z && this.mLayout.q()) {
                int i4 = (this.mLayout.f0() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z3 = focusFinder.findNextFocus(this, view, i4) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i4;
                }
                z = z3;
            }
            if (z) {
                v();
                if (T(view) == null) {
                    return null;
                }
                t1();
                this.mLayout.S0(view, i2, this.mRecycler, this.mState);
                v1(false);
            }
            view2 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View findNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (findNextFocus == null && z2) {
                v();
                if (T(view) == null) {
                    return null;
                }
                t1();
                view2 = this.mLayout.S0(view, i2, this.mRecycler, this.mState);
                v1(false);
            } else {
                view2 = findNextFocus;
            }
        }
        if (view2 == null || view2.hasFocusable()) {
            return y0(view, view2, i2) ? view2 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        e1(view2, null);
        return view;
    }

    public int g0(View view) {
        ViewHolder i0 = i0(view);
        if (i0 != null) {
            return i0.n();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.J();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.K(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    @Nullable
    public Adapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.View
    public int getBaseline() {
        LayoutManager layoutManager = this.mLayout;
        return layoutManager != null ? layoutManager.M() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
        return childDrawingOrderCallback == null ? super.getChildDrawingOrder(i2, i3) : childDrawingOrderCallback.a(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    @Nullable
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    @NonNull
    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    @Nullable
    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    @Nullable
    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    @NonNull
    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.i();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public void h(ItemDecoration itemDecoration) {
        i(itemDecoration, -1);
    }

    public ViewHolder h0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return i0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().j();
    }

    public void i(ItemDecoration itemDecoration, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.m("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.mItemDecorations.add(itemDecoration);
        } else {
            this.mItemDecorations.add(i2, itemDecoration);
        }
        A0();
        requestLayout();
    }

    void i1() {
        int j2 = this.mChildHelper.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i2));
            if (!i0.K()) {
                i0.F();
            }
        }
    }

    @VisibleForTesting
    void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(androidx.recyclerview.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(androidx.recyclerview.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(androidx.recyclerview.R.dimen.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + Q());
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }

    public void j0(View view, Rect rect) {
        k0(view, rect);
    }

    boolean j1(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        v();
        if (this.mAdapter != null) {
            int[] iArr = this.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            k1(i2, i3, iArr);
            int[] iArr2 = this.mReusableIntPair;
            int i8 = iArr2[0];
            int i9 = iArr2[1];
            i4 = i9;
            i5 = i8;
            i6 = i2 - i8;
            i7 = i3 - i9;
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.mReusableIntPair;
        iArr3[0] = 0;
        iArr3[1] = 0;
        H(i5, i4, i6, i7, this.mScrollOffset, 0, iArr3);
        int[] iArr4 = this.mReusableIntPair;
        int i10 = iArr4[0];
        int i11 = i6 - i10;
        int i12 = iArr4[1];
        int i13 = i7 - i12;
        boolean z = (i10 == 0 && i12 == 0) ? false : true;
        int i14 = this.mLastTouchX;
        int[] iArr5 = this.mScrollOffset;
        int i15 = iArr5[0];
        this.mLastTouchX = i14 - i15;
        int i16 = this.mLastTouchY;
        int i17 = iArr5[1];
        this.mLastTouchY = i16 - i17;
        int[] iArr6 = this.mNestedOffsets;
        iArr6[0] = iArr6[0] + i15;
        iArr6[1] = iArr6[1] + i17;
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !MotionEventCompat.a(motionEvent, 8194)) {
                T0(motionEvent.getX(), i11, motionEvent.getY(), i13);
            }
            u(i2, i3);
        }
        if (i5 != 0 || i4 != 0) {
            J(i5, i4);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z && i5 == 0 && i4 == 0) ? false : true;
    }

    public void k(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }

    void k1(int i2, int i3, int[] iArr) {
        t1();
        J0();
        TraceCompat.a(TRACE_SCROLL_TAG);
        R(this.mState);
        int F1 = i2 != 0 ? this.mLayout.F1(i2, this.mRecycler, this.mState) : 0;
        int H1 = i3 != 0 ? this.mLayout.H1(i3, this.mRecycler, this.mState) : 0;
        TraceCompat.b();
        d1();
        K0();
        v1(false);
        if (iArr != null) {
            iArr[0] = F1;
            iArr[1] = H1;
        }
    }

    public void l(OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    public void l1(int i2) {
        if (this.mLayoutSuppressed) {
            return;
        }
        x1();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.G1(i2);
            awakenScrollBars();
        }
    }

    void m(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        viewHolder.H(false);
        if (this.mItemAnimator.a(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            P0();
        }
    }

    Rect n0(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.f5193c) {
            return layoutParams.f5192b;
        }
        if (this.mState.e() && (layoutParams.b() || layoutParams.d())) {
            return layoutParams.f5192b;
        }
        Rect rect = layoutParams.f5192b;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i2).getItemOffsets(this.mTempRect, view, this, this.mState);
            int i3 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.f5193c = false;
        return rect;
    }

    boolean n1(AccessibilityEvent accessibilityEvent) {
        if (!x0()) {
            return false;
        }
        int a2 = accessibilityEvent != null ? AccessibilityEventCompat.a(accessibilityEvent) : 0;
        this.mEatenAccessibilityChangeFlags |= a2 != 0 ? a2 : 0;
        return true;
    }

    void o(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        g(viewHolder);
        viewHolder.H(false);
        if (this.mItemAnimator.c(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            P0();
        }
    }

    public void o1(int i2, int i3) {
        p1(i2, i3, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x004b, code lost:
    
        if (r1 >= 30.0f) goto L22;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.mLayoutOrScrollCounter = r0
            r1 = 1
            r5.mIsAttached = r1
            boolean r2 = r5.mFirstLayoutComplete
            if (r2 == 0) goto L14
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L14
            goto L15
        L14:
            r1 = r0
        L15:
            r5.mFirstLayoutComplete = r1
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r5.mLayout
            if (r1 == 0) goto L1e
            r1.F(r5)
        L1e:
            r5.mPostedAnimatorRunner = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
            if (r0 == 0) goto L61
            java.lang.ThreadLocal r0 = androidx.recyclerview.widget.GapWorker.f5005k
            java.lang.Object r1 = r0.get()
            androidx.recyclerview.widget.GapWorker r1 = (androidx.recyclerview.widget.GapWorker) r1
            r5.mGapWorker = r1
            if (r1 != 0) goto L5c
            androidx.recyclerview.widget.GapWorker r1 = new androidx.recyclerview.widget.GapWorker
            r1.<init>()
            r5.mGapWorker = r1
            android.view.Display r1 = androidx.core.view.ViewCompat.q(r5)
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L4e
            if (r1 == 0) goto L4e
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L4e
            goto L50
        L4e:
            r1 = 1114636288(0x42700000, float:60.0)
        L50:
            androidx.recyclerview.widget.GapWorker r2 = r5.mGapWorker
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.f5009i = r3
            r0.set(r2)
        L5c:
            androidx.recyclerview.widget.GapWorker r0 = r5.mGapWorker
            r0.a(r5)
        L61:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.k();
        }
        x1();
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.G(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.j();
        if (!ALLOW_THREAD_GAP_WORK || (gapWorker = this.mGapWorker) == null) {
            return;
        }
        gapWorker.j(this);
        this.mGapWorker = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mItemDecorations.get(i2).onDraw(canvas, this, this.mState);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r5.mLayout
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.mLayoutSuppressed
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L77
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3e
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r5.mLayout
            boolean r0 = r0.r()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r5.mLayout
            boolean r3 = r3.q()
            if (r3 == 0) goto L3c
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L64
        L3c:
            r3 = r2
            goto L64
        L3e:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L62
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r5.mLayout
            boolean r3 = r3.r()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r5.mLayout
            boolean r3 = r3.q()
            if (r3 == 0) goto L62
            r3 = r0
            r0 = r2
            goto L64
        L62:
            r0 = r2
            r3 = r0
        L64:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6c
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L77
        L6c:
            float r2 = r5.mScaledHorizontalScrollFactor
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.mScaledVerticalScrollFactor
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.j1(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ce, code lost:
    
        if (r0 != false) goto L46;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        TraceCompat.a(TRACE_ON_LAYOUT_TAG);
        C();
        TraceCompat.b();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            x(i2, i3);
            return;
        }
        if (layoutManager.A0()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.mLayout.i1(this.mRecycler, this.mState, i2, i3);
            if ((mode == 1073741824 && mode2 == 1073741824) || this.mAdapter == null) {
                return;
            }
            if (this.mState.f5231e == 1) {
                D();
            }
            this.mLayout.J1(i2, i3);
            this.mState.f5236j = true;
            E();
            this.mLayout.M1(i2, i3);
            if (this.mLayout.P1()) {
                this.mLayout.J1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
                this.mState.f5236j = true;
                E();
                this.mLayout.M1(i2, i3);
                return;
            }
            return;
        }
        if (this.mHasFixedSize) {
            this.mLayout.i1(this.mRecycler, this.mState, i2, i3);
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            t1();
            J0();
            R0();
            K0();
            State state = this.mState;
            if (state.f5238l) {
                state.f5234h = true;
            } else {
                this.mAdapterHelper.j();
                this.mState.f5234h = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            v1(false);
        } else if (this.mState.f5238l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        Adapter adapter = this.mAdapter;
        if (adapter != null) {
            this.mState.f5232f = adapter.m();
        } else {
            this.mState.f5232f = 0;
        }
        t1();
        this.mLayout.i1(this.mRecycler, this.mState, i2, i3);
        v1(false);
        this.mState.f5234h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (x0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.a());
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || (parcelable2 = this.mPendingSavedState.f5211i) == null) {
            return;
        }
        layoutManager.l1(parcelable2);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.b(savedState2);
        } else {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                savedState.f5211i = layoutManager.m1();
            } else {
                savedState.f5211i = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        u0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instructions count: 475
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    void p(String str) {
        if (x0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + Q());
        }
        if (this.mDispatchScrollCounter > 0) {
            Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + Q()));
        }
    }

    public boolean p0() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.p();
    }

    public void p1(int i2, int i3, Interpolator interpolator) {
        q1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    boolean q(ViewHolder viewHolder) {
        ItemAnimator itemAnimator = this.mItemAnimator;
        return itemAnimator == null || itemAnimator.g(viewHolder, viewHolder.p());
    }

    public void q1(int i2, int i3, Interpolator interpolator, int i4) {
        r1(i2, i3, interpolator, i4, false);
    }

    void r0() {
        this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.6
            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void a(int i2, int i3) {
                RecyclerView.this.F0(i2, i3);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void b(AdapterHelper.UpdateOp updateOp) {
                i(updateOp);
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void c(AdapterHelper.UpdateOp updateOp) {
                i(updateOp);
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void d(int i2, int i3) {
                RecyclerView.this.G0(i2, i3, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void e(int i2, int i3, Object obj) {
                RecyclerView.this.z1(i2, i3, obj);
                RecyclerView.this.mItemsChanged = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public ViewHolder f(int i2) {
                ViewHolder b0 = RecyclerView.this.b0(i2, true);
                if (b0 == null || RecyclerView.this.mChildHelper.n(b0.f5252a)) {
                    return null;
                }
                return b0;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void g(int i2, int i3) {
                RecyclerView.this.E0(i2, i3);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void h(int i2, int i3) {
                RecyclerView.this.G0(i2, i3, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mItemsAddedOrRemoved = true;
                recyclerView.mState.f5230d += i3;
            }

            void i(AdapterHelper.UpdateOp updateOp) {
                int i2 = updateOp.f4867a;
                if (i2 == 1) {
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.mLayout.a1(recyclerView, updateOp.f4868b, updateOp.f4870d);
                    return;
                }
                if (i2 == 2) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    recyclerView2.mLayout.d1(recyclerView2, updateOp.f4868b, updateOp.f4870d);
                } else if (i2 == 4) {
                    RecyclerView recyclerView3 = RecyclerView.this;
                    recyclerView3.mLayout.f1(recyclerView3, updateOp.f4868b, updateOp.f4870d, updateOp.f4869c);
                } else {
                    if (i2 != 8) {
                        return;
                    }
                    RecyclerView recyclerView4 = RecyclerView.this;
                    recyclerView4.mLayout.c1(recyclerView4, updateOp.f4868b, updateOp.f4870d, 1);
                }
            }
        });
    }

    void r1(int i2, int i3, Interpolator interpolator, int i4, boolean z) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        if (!layoutManager.q()) {
            i2 = 0;
        }
        if (!this.mLayout.r()) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        if (i4 != Integer.MIN_VALUE && i4 <= 0) {
            scrollBy(i2, i3);
            return;
        }
        if (z) {
            int i5 = i2 != 0 ? 1 : 0;
            if (i3 != 0) {
                i5 |= 2;
            }
            u1(i5, 1);
        }
        this.mViewFlinger.f(i2, i3, i4, interpolator);
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z) {
        ViewHolder i0 = i0(view);
        if (i0 != null) {
            if (i0.y()) {
                i0.g();
            } else if (!i0.K()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + i0 + Q());
            }
        }
        view.clearAnimation();
        A(view);
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.k1(this, this.mState, view, view2) && view2 != null) {
            e1(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.A1(this, view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mOnItemTouchListeners.get(i2).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutSuppressed) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public void s1(int i2) {
        if (this.mLayoutSuppressed) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.R1(this, this.mState, i2);
        }
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        boolean q2 = layoutManager.q();
        boolean r2 = this.mLayout.r();
        if (q2 || r2) {
            if (!q2) {
                i2 = 0;
            }
            if (!r2) {
                i3 = 0;
            }
            j1(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (n1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.i0(this, recyclerViewAccessibilityDelegate);
    }

    public void setAdapter(@Nullable Adapter adapter) {
        setLayoutFrozen(false);
        m1(adapter, false, true);
        S0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(@Nullable ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = childDrawingOrderCallback;
        setChildrenDrawingOrderEnabled(childDrawingOrderCallback != null);
    }

    @VisibleForTesting
    boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i2) {
        if (!x0()) {
            ViewCompat.s0(viewHolder.f5252a, i2);
            return true;
        }
        viewHolder.mPendingAccessibilityState = i2;
        this.mPendingAccessibilityImportanceChange.add(viewHolder);
        return false;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            u0();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(@NonNull EdgeEffectFactory edgeEffectFactory) {
        Preconditions.h(edgeEffectFactory);
        this.mEdgeEffectFactory = edgeEffectFactory;
        u0();
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setItemAnimator(@Nullable ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.mItemAnimator;
        if (itemAnimator2 != null) {
            itemAnimator2.k();
            this.mItemAnimator.y(null);
        }
        this.mItemAnimator = itemAnimator;
        if (itemAnimator != null) {
            itemAnimator.y(this.mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.mRecycler.G(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(@Nullable LayoutManager layoutManager) {
        if (layoutManager == this.mLayout) {
            return;
        }
        x1();
        if (this.mLayout != null) {
            ItemAnimator itemAnimator = this.mItemAnimator;
            if (itemAnimator != null) {
                itemAnimator.k();
            }
            this.mLayout.t1(this.mRecycler);
            this.mLayout.u1(this.mRecycler);
            this.mRecycler.c();
            if (this.mIsAttached) {
                this.mLayout.G(this, this.mRecycler);
            }
            this.mLayout.N1(null);
            this.mLayout = null;
        } else {
            this.mRecycler.c();
        }
        this.mChildHelper.o();
        this.mLayout = layoutManager;
        if (layoutManager != null) {
            if (layoutManager.f5168b != null) {
                throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.f5168b.Q());
            }
            layoutManager.N1(this);
            if (this.mIsAttached) {
                this.mLayout.F(this);
            }
        }
        this.mRecycler.K();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().m(z);
    }

    public void setOnFlingListener(@Nullable OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(@Nullable OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecycledViewPool(@Nullable RecycledViewPool recycledViewPool) {
        this.mRecycler.E(recycledViewPool);
    }

    public void setRecyclerListener(@Nullable RecyclerListener recyclerListener) {
        this.mRecyclerListener = recyclerListener;
    }

    void setScrollState(int i2) {
        if (i2 == this.mScrollState) {
            return;
        }
        this.mScrollState = i2;
        if (i2 != 2) {
            y1();
        }
        I(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w(TAG, "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(@Nullable ViewCacheExtension viewCacheExtension) {
        this.mRecycler.F(viewCacheExtension);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().o(i2);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            p("Do not suppressLayout in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutSuppressed = true;
                this.mIgnoreMotionEventTillDown = true;
                x1();
                return;
            }
            this.mLayoutSuppressed = false;
            if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                requestLayout();
            }
            this.mLayoutWasDefered = false;
        }
    }

    void t() {
        int j2 = this.mChildHelper.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ViewHolder i0 = i0(this.mChildHelper.i(i2));
            if (!i0.K()) {
                i0.d();
            }
        }
        this.mRecycler.d();
    }

    void t1() {
        int i2 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i2;
        if (i2 != 1 || this.mLayoutSuppressed) {
            return;
        }
        this.mLayoutWasDefered = false;
    }

    void u(int i2, int i3) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.Z(this);
        }
    }

    void u0() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public boolean u1(int i2, int i3) {
        return getScrollingChildHelper().p(i2, i3);
    }

    void v() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            TraceCompat.a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            C();
            TraceCompat.b();
            return;
        }
        if (this.mAdapterHelper.p()) {
            if (!this.mAdapterHelper.o(4) || this.mAdapterHelper.o(11)) {
                if (this.mAdapterHelper.p()) {
                    TraceCompat.a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                    C();
                    TraceCompat.b();
                    return;
                }
                return;
            }
            TraceCompat.a(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
            t1();
            J0();
            this.mAdapterHelper.w();
            if (!this.mLayoutWasDefered) {
                if (q0()) {
                    C();
                } else {
                    this.mAdapterHelper.i();
                }
            }
            v1(true);
            K0();
            TraceCompat.b();
        }
    }

    public void v0() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.m("Cannot invalidate item decorations during a scroll or layout");
        }
        A0();
        requestLayout();
    }

    void v1(boolean z) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                C();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    boolean w0() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void w1(int i2) {
        getScrollingChildHelper().r(i2);
    }

    void x(int i2, int i3) {
        setMeasuredDimension(LayoutManager.t(i2, getPaddingLeft() + getPaddingRight(), ViewCompat.x(this)), LayoutManager.t(i3, getPaddingTop() + getPaddingBottom(), ViewCompat.w(this)));
    }

    public boolean x0() {
        return this.mLayoutOrScrollCounter > 0;
    }

    public void x1() {
        setScrollState(0);
        y1();
    }

    void z(View view) {
        ViewHolder i0 = i0(view);
        H0(view);
        Adapter adapter = this.mAdapter;
        if (adapter != null && i0 != null) {
            adapter.F(i0);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).b(view);
            }
        }
    }

    void z0(int i2) {
        if (this.mLayout == null) {
            return;
        }
        setScrollState(2);
        this.mLayout.G1(i2);
        awakenScrollBars();
    }

    void z1(int i2, int i3, Object obj) {
        int i4;
        int j2 = this.mChildHelper.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < j2; i6++) {
            View i7 = this.mChildHelper.i(i6);
            ViewHolder i0 = i0(i7);
            if (i0 != null && !i0.K() && (i4 = i0.f5254c) >= i2 && i4 < i5) {
                i0.c(2);
                i0.b(obj);
                ((LayoutParams) i7.getLayoutParams()).f5193c = true;
            }
        }
        this.mRecycler.M(i2, i3);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, androidx.recyclerview.R.attr.recyclerViewStyle);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() { // from class: androidx.recyclerview.widget.RecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (!recyclerView.mFirstLayoutComplete || recyclerView.isLayoutRequested()) {
                    return;
                }
                RecyclerView recyclerView2 = RecyclerView.this;
                if (!recyclerView2.mIsAttached) {
                    recyclerView2.requestLayout();
                } else if (recyclerView2.mLayoutSuppressed) {
                    recyclerView2.mLayoutWasDefered = true;
                } else {
                    recyclerView2.v();
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new EdgeEffectFactory();
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() { // from class: androidx.recyclerview.widget.RecyclerView.2
            @Override // java.lang.Runnable
            public void run() {
                ItemAnimator itemAnimator = RecyclerView.this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.v();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() { // from class: androidx.recyclerview.widget.RecyclerView.4
            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void a(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.m(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void b(ViewHolder viewHolder) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.v1(viewHolder.f5252a, recyclerView.mRecycler);
            }

            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void c(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.mRecycler.J(viewHolder);
                RecyclerView.this.o(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void d(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                viewHolder.H(false);
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mDataSetHasChangedAfterLayout) {
                    if (recyclerView.mItemAnimator.b(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        RecyclerView.this.P0();
                    }
                } else if (recyclerView.mItemAnimator.d(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.P0();
                }
            }
        };
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.e(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.h(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.y(this.mItemAnimatorListener);
        r0();
        t0();
        s0();
        if (ViewCompat.t(this) == 0) {
            ViewCompat.s0(this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.recyclerview.R.styleable.RecyclerView, i2, 0);
        saveAttributeDataForStyleable(context, androidx.recyclerview.R.styleable.RecyclerView, attributeSet, obtainStyledAttributes, i2, 0);
        String string = obtainStyledAttributes.getString(androidx.recyclerview.R.styleable.RecyclerView_layoutManager);
        if (obtainStyledAttributes.getInt(androidx.recyclerview.R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(androidx.recyclerview.R.styleable.RecyclerView_android_clipToPadding, true);
        boolean z = obtainStyledAttributes.getBoolean(androidx.recyclerview.R.styleable.RecyclerView_fastScrollEnabled, false);
        this.mEnableFastScroller = z;
        if (z) {
            initFastScroller((StateListDrawable) obtainStyledAttributes.getDrawable(androidx.recyclerview.R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes.getDrawable(androidx.recyclerview.R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes.getDrawable(androidx.recyclerview.R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes.getDrawable(androidx.recyclerview.R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        obtainStyledAttributes.recycle();
        w(context, string, attributeSet, i2, 0);
        int[] iArr = NESTED_SCROLLING_ATTRS;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        saveAttributeDataForStyleable(context, iArr, attributeSet, obtainStyledAttributes2, i2, 0);
        boolean z2 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z2);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        ViewHolder f5191a;

        /* renamed from: b, reason: collision with root package name */
        final Rect f5192b;

        /* renamed from: c, reason: collision with root package name */
        boolean f5193c;

        /* renamed from: d, reason: collision with root package name */
        boolean f5194d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5192b = new Rect();
            this.f5193c = true;
            this.f5194d = false;
        }

        public int a() {
            return this.f5191a.n();
        }

        public boolean b() {
            return this.f5191a.z();
        }

        public boolean c() {
            return this.f5191a.w();
        }

        public boolean d() {
            return this.f5191a.u();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f5192b = new Rect();
            this.f5193c = true;
            this.f5194d = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f5192b = new Rect();
            this.f5193c = true;
            this.f5194d = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f5192b = new Rect();
            this.f5193c = true;
            this.f5194d = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.f5192b = new Rect();
            this.f5193c = true;
            this.f5194d = false;
        }
    }

    @RestrictTo
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.recyclerview.widget.RecyclerView.SavedState.1
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
        Parcelable f5211i;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f5211i = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        void b(SavedState savedState) {
            this.f5211i = savedState.f5211i;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.f5211i, 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.L(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }
}
