package com.zte.mifavor.androidx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayoutSpringBehavior;
import com.zte.extres.R;
import com.zte.mifavor.androidx.widget.swipe.AdapterWrapper;
import com.zte.mifavor.androidx.widget.swipe.OnItemClickListener;
import com.zte.mifavor.androidx.widget.swipe.OnItemLongClickListener;
import com.zte.mifavor.androidx.widget.swipe.OnItemMenuClickListener;
import com.zte.mifavor.androidx.widget.swipe.SwipeMenuBridge;
import com.zte.mifavor.androidx.widget.swipe.SwipeMenuCreator;
import com.zte.mifavor.androidx.widget.swipe.SwipeMenuLayout;
import com.zte.mifavor.androidx.widget.swipe.touch.DefaultItemTouchHelper;
import com.zte.mifavor.androidx.widget.swipe.touch.OnItemMoveListener;
import com.zte.mifavor.androidx.widget.swipe.touch.OnItemMovementListener;
import com.zte.mifavor.androidx.widget.swipe.touch.OnItemStateChangedListener;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;
import com.zte.mifavor.utils.overscroll.OverScrollDecoratorHelper;
import com.zte.mifavor.widget.Util;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RecyclerView extends androidx.recyclerview.widget.RecyclerView {
    public static final boolean DEBUG = false;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "Z#SQScrollRV";
    private boolean allowSwipeDelete;

    @Nullable
    private IOverScrollDecor iOverScrollDecor;
    private boolean isNeedDoFling;
    private RecyclerView.AdapterDataObserver mAdapterDataObserver;

    @Nullable
    private AdapterWrapper mAdapterWrapper;
    private int[] mDisableSwipeItem;
    private final List<Integer> mDisableSwipeItemMenuList;
    private int mDisableType;
    private int mDownX;
    private int mDownY;
    private boolean mForcedSpring;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterruptDirection;
    private Boolean mIsDisableSwipe;
    private boolean mIsDispalyMotion;
    private boolean mIsSmartSlideOptimizationMode;
    private boolean mIsUseSpring;
    protected int mItemActionState;
    private DefaultItemTouchHelper mItemTouchHelper;
    private int mLocalState;

    @Nullable
    protected SwipeMenuLayout mOldSwipedLayout;
    protected int mOldTouchedPosition;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemMenuClickListener mOnItemMenuClickListener;
    private OverScrollDecoratorHelper mOverScrollDecoratorHelper;
    protected int mScaleTouchSlop;
    SpringAnimationCommon mSpringAnimationCommon;
    private String mStringID;
    private boolean mSwipeItemMenuEnable;

    @Nullable
    private SwipeMenuCreator mSwipeMenuCreator;

    @Nullable
    protected SwipeMenuLayout mSwipedLayout;
    private boolean mSwpieContentIsCard;
    private int mTotalyDx;
    private int mTotalyDy;

    @Nullable
    private RecyclerView.ViewHolder mViewHolder;
    public static int[] DISABLE_SWIPE_FIRST = {0};
    public static int[] DISABLE_SWIPE_ALL = {1, 1, 1, 1, 1};
    private static boolean mIsSupportClick = true;

    private static class ItemClickListener implements OnItemClickListener {

        /* renamed from: a, reason: collision with root package name */
        private RecyclerView f17177a;

        /* renamed from: b, reason: collision with root package name */
        private OnItemClickListener f17178b;

        public ItemClickListener(RecyclerView recyclerView, OnItemClickListener onItemClickListener) {
            this.f17177a = recyclerView;
            this.f17178b = onItemClickListener;
        }

        @Override // com.zte.mifavor.androidx.widget.swipe.OnItemClickListener
        public void a(View view, int i2) {
            if (i2 >= 0) {
                SwipeMenuLayout swipeMenuLayout = this.f17177a.mSwipedLayout;
                if (swipeMenuLayout != null && swipeMenuLayout.p()) {
                    this.f17177a.mSwipedLayout.i(false);
                } else if (RecyclerView.mIsSupportClick && this.f17177a.mLocalState == 0) {
                    this.f17178b.a(view, i2);
                }
            }
        }
    }

    private static class ItemLongClickListener implements OnItemLongClickListener {

        /* renamed from: a, reason: collision with root package name */
        private RecyclerView f17179a;

        /* renamed from: b, reason: collision with root package name */
        private OnItemLongClickListener f17180b;

        public ItemLongClickListener(RecyclerView recyclerView, OnItemLongClickListener onItemLongClickListener) {
            this.f17179a = recyclerView;
            this.f17180b = onItemLongClickListener;
        }

        @Override // com.zte.mifavor.androidx.widget.swipe.OnItemLongClickListener
        public void a(View view, int i2) {
            if (i2 >= 0) {
                SwipeMenuLayout swipeMenuLayout = this.f17179a.mSwipedLayout;
                if (swipeMenuLayout != null && swipeMenuLayout.p()) {
                    this.f17179a.mSwipedLayout.i(false);
                } else if (RecyclerView.mIsSupportClick && this.f17179a.mLocalState == 0) {
                    this.f17180b.a(view, i2);
                }
            }
        }
    }

    private static class ItemMenuClickListener implements OnItemMenuClickListener {

        /* renamed from: a, reason: collision with root package name */
        private RecyclerView f17181a;

        /* renamed from: b, reason: collision with root package name */
        private OnItemMenuClickListener f17182b;

        public ItemMenuClickListener(RecyclerView recyclerView, OnItemMenuClickListener onItemMenuClickListener) {
            this.f17181a = recyclerView;
            this.f17182b = onItemMenuClickListener;
        }

        @Override // com.zte.mifavor.androidx.widget.swipe.OnItemMenuClickListener
        public void a(View view, SwipeMenuBridge swipeMenuBridge, int i2) {
            if (i2 >= 0) {
                this.f17182b.a(view, swipeMenuBridge, i2);
            }
        }
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void H1(String str) {
        if (this.mAdapterWrapper != null) {
            Log.e(TAG, str + " error,  mAdapterWrapper is null.");
        }
    }

    private View I1(View view) {
        if (view instanceof SwipeMenuLayout) {
            return view;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(view);
        while (!arrayList.isEmpty()) {
            View view2 = (View) arrayList.remove(0);
            if (view2 instanceof ViewGroup) {
                if (view2 instanceof SwipeMenuLayout) {
                    return view2;
                }
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    arrayList.add(viewGroup.getChildAt(i2));
                }
            }
        }
        return view;
    }

    private boolean J1(int i2, int i3, boolean z) {
        int i4 = this.mDownX - i2;
        int i5 = this.mDownY - i3;
        if (Math.abs(i4) > this.mScaleTouchSlop && Math.abs(i4) > Math.abs(i5)) {
            z = false;
        }
        if (Math.abs(i5) > this.mScaleTouchSlop) {
            Math.abs(i5);
            Math.abs(i4);
        }
        if (Math.abs(i5) >= this.mScaleTouchSlop || Math.abs(i4) >= this.mScaleTouchSlop) {
            return z;
        }
        return false;
    }

    private void K1() {
        if (this.mItemTouchHelper == null) {
            DefaultItemTouchHelper defaultItemTouchHelper = new DefaultItemTouchHelper();
            this.mItemTouchHelper = defaultItemTouchHelper;
            defaultItemTouchHelper.attachToRecyclerView(this);
        }
    }

    private boolean getAnimIsRunning() {
        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior;
        View rootView = getRootView();
        if (rootView != null) {
            View findViewById = rootView.findViewById(R.id.base_sink_app_bar_layout);
            if ((findViewById instanceof AppBarLayout) && (appBarLayoutSpringBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) ((AppBarLayout) findViewById).getLayoutParams()).f()) != null) {
                return appBarLayoutSpringBehavior.S0();
            }
        }
        return false;
    }

    private boolean getIsFirstDownScroll() {
        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior;
        View rootView = getRootView();
        if (rootView != null) {
            View findViewById = rootView.findViewById(R.id.base_sink_app_bar_layout);
            if ((findViewById instanceof AppBarLayout) && (appBarLayoutSpringBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) ((AppBarLayout) findViewById).getLayoutParams()).f()) != null) {
                return appBarLayoutSpringBehavior.V0();
            }
        }
        return false;
    }

    private void setApplayoutSpringDistance(int i2) {
        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior;
        View rootView = getRootView();
        if (rootView != null) {
            View findViewById = rootView.findViewById(R.id.base_sink_app_bar_layout);
            if (!(findViewById instanceof AppBarLayout) || (appBarLayoutSpringBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) ((AppBarLayout) findViewById).getLayoutParams()).f()) == null) {
                return;
            }
            appBarLayoutSpringBehavior.R0(i2);
        }
    }

    private void setSmartSlideOptimizationStatusOfRV(boolean z) {
        if (z == this.mIsSmartSlideOptimizationMode) {
            return;
        }
        try {
            Field declaredField = androidx.recyclerview.widget.RecyclerView.class.getDeclaredField("mViewFlinger");
            declaredField.setAccessible(true);
            Field declaredField2 = Class.forName("androidx.recyclerview.widget.RecyclerView$ViewFlinger").getDeclaredField("mOverScroller");
            declaredField2.setAccessible(true);
            boolean z2 = declaredField2.get(declaredField.get(this)) instanceof OverScroller;
            this.mIsSmartSlideOptimizationMode = z;
            Log.d(TAG, "set Smart Slide Optimization Status Of RV out, bEnable=" + z);
        } catch (ClassNotFoundException e2) {
            Log.e(TAG, "set Smart Slide Optimization Status Of RV, ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "set Smart Slide Optimization Status Of RV, IllegalAccessException", e3);
        } catch (IllegalArgumentException e4) {
            Log.e(TAG, "set Smart Slide Optimization Status Of RV, IllegalArgumentException", e4);
        } catch (NoSuchFieldException e5) {
            Log.e(TAG, "set Smart Slide Optimization Status Of RV, NoSuchFieldException", e5);
        } catch (NoSuchMethodError unused) {
            Log.w(TAG, "set Smart Slide Optimization Status Of RV, No Such Method Error.");
        } catch (Throwable th) {
            Log.e(TAG, "set Smart Slide Optimization Status Of RV, Throwable", th);
        }
    }

    public boolean L1() {
        if (!N1()) {
            return true;
        }
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.t();
        }
        return false;
    }

    public boolean M1() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.v();
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void N0(int i2) {
        super.N0(i2);
        if (!this.mIsUseSpring || this.mSpringAnimationCommon == null) {
            return;
        }
        View childAt = getChildAt(0);
        if (i2 == 0) {
            getAnimIsRunning();
            getIsFirstDownScroll();
        }
        int k2 = this.mSpringAnimationCommon.k();
        this.mLocalState = i2;
        if (i2 == 0 && (2 == k2 || this.mForcedSpring)) {
            canScrollVertically(-1);
            canScrollVertically(1);
            canScrollHorizontally(-1);
            canScrollHorizontally(1);
            this.mSpringAnimationCommon.x(childAt, 1000);
        }
        if (i2 == 0 && !L1() && 4 != k2 && this.isNeedDoFling) {
            int l2 = (int) (childAt != null ? this.mSpringAnimationCommon.l(childAt, 1000) : this.mSpringAnimationCommon.l(this, 1000));
            if (l2 > 33.0f) {
                setApplayoutSpringDistance(l2);
            }
        }
        if (i2 == 0) {
            this.isNeedDoFling = false;
        }
    }

    public boolean N1() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.w();
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void O0(int i2, int i3) {
        super.O0(i2, i3);
    }

    public void O1() {
        RecyclerView.ViewHolder viewHolder;
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null && defaultItemTouchHelper.A() != null && (viewHolder = this.mViewHolder) != null) {
            viewHolder.k();
            this.mItemTouchHelper.A().a(this.mViewHolder);
        } else {
            Log.e(TAG, "+++++++++ onItemDismiss error. mViewHolder or mItemTouchHelper is null. mViewHolder = " + this.mViewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean c0(int i2, int i3) {
        SpringAnimationCommon springAnimationCommon;
        boolean canScrollVertically = canScrollVertically(-1);
        boolean canScrollVertically2 = canScrollVertically(1);
        boolean canScrollHorizontally = canScrollHorizontally(-1);
        boolean canScrollHorizontally2 = canScrollHorizontally(1);
        if (!canScrollVertically && !canScrollVertically2 && (canScrollHorizontally || canScrollHorizontally2)) {
            return super.c0(i2, i3);
        }
        if (getIsBeingDragged() || Math.abs(i3) <= 250) {
            return false;
        }
        if (this.mIsUseSpring && (springAnimationCommon = this.mSpringAnimationCommon) != null) {
            springAnimationCommon.h(i3);
        }
        if (canScrollVertically) {
            setSmartSlideOptimizationStatusOfRV(true);
        } else {
            setSmartSlideOptimizationStatusOfRV(false);
        }
        return super.c0(i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return super.dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mSpringAnimationCommon == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        getTranslationY();
        if (action == 0) {
            if (this.mSpringAnimationCommon.p().f()) {
                this.mSpringAnimationCommon.p().t();
                this.mSpringAnimationCommon.p().b();
            }
            this.mSpringAnimationCommon.f();
            this.mTotalyDy = 0;
            this.mTotalyDx = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return super.getAdapter() instanceof AdapterWrapper ? ((AdapterWrapper) super.getAdapter()).O() : super.getAdapter();
    }

    public int getInterruptSlideDirection() {
        return this.mInterruptDirection;
    }

    public boolean getIsBeingDragged() {
        IOverScrollDecor iOverScrollDecor = this.iOverScrollDecor;
        if (iOverScrollDecor != null) {
            return iOverScrollDecor.getIsBeingDragged();
        }
        return false;
    }

    public boolean getUseSpring() {
        return this.mIsUseSpring;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mIsUseSpring) {
            l(new RecyclerView.OnScrollListener() { // from class: com.zte.mifavor.androidx.widget.RecyclerView.3
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void b(androidx.recyclerview.widget.RecyclerView recyclerView, int i2, int i3) {
                    RecyclerView.this.mTotalyDy -= i3;
                    RecyclerView.this.mTotalyDx -= i2;
                    RecyclerView recyclerView2 = RecyclerView.this;
                    recyclerView2.mSpringAnimationCommon.C(recyclerView2.mTotalyDy);
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00d4, code lost:
    
        if (r0 != 3) goto L68;
     */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r12) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.androidx.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return super.onNestedFling(view, f2, f3, z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            canScrollHorizontally(-1);
            canScrollHorizontally(1);
            motionEvent.getAction();
            return super.onTouchEvent(motionEvent);
        } catch (Exception e2) {
            Log.e(TAG, "on Touch Event error, ex=", e2);
            return false;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        OverScrollDecoratorHelper overScrollDecoratorHelper = new OverScrollDecoratorHelper(this);
        this.mOverScrollDecoratorHelper = overScrollDecoratorHelper;
        this.iOverScrollDecor = overScrollDecoratorHelper.b();
        if (this.mSwipeMenuCreator == null) {
            super.setAdapter(adapter);
            return;
        }
        AdapterWrapper adapterWrapper = this.mAdapterWrapper;
        if (adapterWrapper != null && adapterWrapper.O() != null) {
            this.mAdapterWrapper.O().K(this.mAdapterDataObserver);
        }
        if (adapter == null) {
            this.mAdapterWrapper = null;
        } else {
            adapter.I(this.mAdapterDataObserver);
            AdapterWrapper adapterWrapper2 = new AdapterWrapper(getContext(), adapter);
            this.mAdapterWrapper = adapterWrapper2;
            adapterWrapper2.Q(this.mOnItemClickListener);
            this.mAdapterWrapper.R(this.mOnItemLongClickListener);
            this.mAdapterWrapper.U(this.mSwipeMenuCreator);
            this.mAdapterWrapper.S(this.mOnItemMenuClickListener);
            this.mAdapterWrapper.T(this.mSwpieContentIsCard);
        }
        super.setAdapter(this.mAdapterWrapper);
    }

    public void setDampingRatio(String str) {
        try {
            Log.d(TAG, "setDampingRatio dampingRatio = " + str);
            this.mSpringAnimationCommon.y(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setDragAmplitude(String str) {
        try {
            Log.d(TAG, "setDragAmplitude dragAmplitude = " + str);
            this.mSpringAnimationCommon.z(Integer.valueOf(str).intValue());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setDuration(String str) {
        try {
            Log.d(TAG, "setDuration duration = " + str);
            this.mSpringAnimationCommon.A(Integer.parseInt(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setForcedSpring(boolean z) {
        this.mForcedSpring = z;
    }

    public void setInterruptSlideDirection(int i2) {
        this.mInterruptDirection = i2;
    }

    public void setItemViewSwipeEnabled(boolean z) {
        K1();
        this.allowSwipeDelete = z;
        this.mItemTouchHelper.B(z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (this.mSpringAnimationCommon == null) {
            return;
        }
        if (layoutManager != null) {
            DynamicAnimation.ViewProperty viewProperty = DynamicAnimation.f3652o;
            if (layoutManager.q() && !layoutManager.r()) {
                viewProperty = DynamicAnimation.f3651n;
            }
            this.mSpringAnimationCommon.E(this, viewProperty, 0.0f);
        }
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup l3 = gridLayoutManager.l3();
            gridLayoutManager.q3(new GridLayoutManager.SpanSizeLookup(this) { // from class: com.zte.mifavor.androidx.widget.RecyclerView.1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int f(int i2) {
                    GridLayoutManager.SpanSizeLookup spanSizeLookup = l3;
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.f(i2);
                    }
                    return 1;
                }
            });
        }
        super.setLayoutManager(layoutManager);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            return;
        }
        H1("Cannot set item click listener, setAdapter has already been called.");
        this.mOnItemClickListener = new ItemClickListener(this, onItemClickListener);
    }

    public void setOnItemLongClickListener(@NonNull OnItemLongClickListener onItemLongClickListener) {
        if (onItemLongClickListener == null) {
            return;
        }
        H1("Cannot set item click listener, setAdapter has already been called.");
        this.mOnItemLongClickListener = new ItemLongClickListener(this, onItemLongClickListener);
    }

    public void setOnItemMenuClickListener(OnItemMenuClickListener onItemMenuClickListener) {
        if (onItemMenuClickListener == null) {
            return;
        }
        H1("Cannot set menu item click listener, setAdapter has already been called.");
        this.mOnItemMenuClickListener = new ItemMenuClickListener(this, onItemMenuClickListener);
    }

    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        K1();
        this.mItemTouchHelper.C(onItemMoveListener);
    }

    public void setOnItemMovementListener(OnItemMovementListener onItemMovementListener) {
        K1();
        this.mItemTouchHelper.D(onItemMovementListener);
    }

    public void setOnItemStateChangedListener(OnItemStateChangedListener onItemStateChangedListener) {
        K1();
        this.mItemTouchHelper.E(onItemStateChangedListener);
    }

    public void setSlipAmplitude(String str) {
        try {
            Log.d(TAG, "setSlipAmplitude slipAmplitude = " + str);
            this.mSpringAnimationCommon.D(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setStiffness(String str) {
        try {
            Log.d(TAG, "setStiffness stiffness = " + str);
            this.mSpringAnimationCommon.F(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setSwipeContentIsCard(boolean z) {
        this.mSwpieContentIsCard = z;
    }

    public void setSwipeMenuCreator(SwipeMenuCreator swipeMenuCreator) {
        if (swipeMenuCreator == null) {
            return;
        }
        H1("Cannot set menu creator, setAdapter has already been called.");
        this.mSwipeMenuCreator = swipeMenuCreator;
    }

    public void setUseSpring(boolean z) {
        boolean z2 = z && this.mIsDispalyMotion;
        this.mIsUseSpring = z2;
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            springAnimationCommon.B(z2);
        }
        Log.d(TAG, "setUseSpring mIsUseSpring = " + this.mIsUseSpring);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        boolean z = false;
        this.mTotalyDx = 0;
        this.mTotalyDy = 0;
        this.mIsUseSpring = true;
        this.mIsDispalyMotion = true;
        this.mInterruptDirection = 1;
        this.mSwipedLayout = null;
        this.mOldSwipedLayout = null;
        this.mOldTouchedPosition = -1;
        this.mDownX = 0;
        this.mDownY = 0;
        this.mInitialTouchX = 0;
        this.mInitialTouchY = 0;
        this.allowSwipeDelete = false;
        this.mSwipeMenuCreator = null;
        this.mViewHolder = null;
        this.mSwipeItemMenuEnable = true;
        this.mDisableSwipeItem = new int[]{-1};
        this.mIsDisableSwipe = Boolean.FALSE;
        this.mDisableType = -1;
        this.mStringID = "";
        this.mDisableSwipeItemMenuList = new ArrayList();
        this.mIsSmartSlideOptimizationMode = true;
        this.mLocalState = 0;
        this.mItemActionState = 0;
        this.isNeedDoFling = false;
        this.mSwpieContentIsCard = true;
        this.mAdapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.zte.mifavor.androidx.widget.RecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void a() {
                if (RecyclerView.this.mAdapterWrapper != null) {
                    RecyclerView.this.mAdapterWrapper.r();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void b(int i3, int i4) {
                if (RecyclerView.this.mAdapterWrapper != null) {
                    RecyclerView.this.mAdapterWrapper.v(i3, i4);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void c(int i3, int i4, Object obj) {
                if (RecyclerView.this.mAdapterWrapper != null) {
                    RecyclerView.this.mAdapterWrapper.w(i3, i4, obj);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void d(int i3, int i4) {
                if (RecyclerView.this.mAdapterWrapper != null) {
                    RecyclerView.this.mAdapterWrapper.x(i3, i4);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void e(int i3, int i4, int i5) {
                if (RecyclerView.this.mAdapterWrapper != null) {
                    RecyclerView.this.mAdapterWrapper.u(i3, i4);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void f(int i3, int i4) {
                if (RecyclerView.this.mAdapterWrapper != null) {
                    RecyclerView.this.mAdapterWrapper.y(i3, i4);
                }
            }
        };
        this.mForcedSpring = false;
        SpringAnimationCommon springAnimationCommon = new SpringAnimationCommon();
        this.mSpringAnimationCommon = springAnimationCommon;
        springAnimationCommon.s(this, DynamicAnimation.f3652o, 3.0f);
        this.mSpringAnimationCommon.j(getContext());
        boolean booleanValue = Util.d(context).booleanValue();
        this.mIsDispalyMotion = booleanValue;
        if (this.mIsUseSpring && booleanValue) {
            z = true;
        }
        this.mIsUseSpring = z;
        this.mSpringAnimationCommon.B(z);
        this.mScaleTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        DefineItemAnimator defineItemAnimator = new DefineItemAnimator();
        defineItemAnimator.A(50L);
        setItemAnimator(defineItemAnimator);
        String viewGroup = toString();
        if (viewGroup != null) {
            int indexOf = viewGroup.indexOf("{");
            this.mStringID = "[" + viewGroup.substring(indexOf + 1, indexOf + 7) + "] ";
        }
    }
}
