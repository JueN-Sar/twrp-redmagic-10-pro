package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;
import com.zte.mifavor.utils.overscroll.OverScrollDecoratorHelper;

/* loaded from: classes2.dex */
public class ListView extends android.widget.ListView implements ISpringView {
    private static final boolean DEBUG = false;
    private static final String TAG = "Z#View-SpringLV";

    @Nullable
    private IOverScrollDecor iOverScrollDecor;
    private int mCurrentfirstVisibleItem;

    @NonNull
    private GestureDetector mGestureDetector;
    private int mInterruptDirection;
    private boolean mIsDispalyMotion;
    private boolean mIsUseSpring;

    @NonNull
    private OverScrollDecoratorHelper mOverScrollDecoratorHelper;

    @NonNull
    private SpringAnimationCommon mSpringAnimationCommon;
    private int mTotalyDy;
    private SparseArray recordSp;

    static class ItemRecod {

        /* renamed from: a, reason: collision with root package name */
        int f17669a = 0;

        /* renamed from: b, reason: collision with root package name */
        int f17670b = 0;

        ItemRecod() {
        }
    }

    private class TouchGesture extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            ListView.this.canScrollVertically(-1);
            ListView.this.canScrollVertically(1);
            if (ListView.this.getIsBeingDragged()) {
                return false;
            }
            if (ListView.this.mIsUseSpring && ListView.this.mSpringAnimationCommon != null) {
                ListView.this.mSpringAnimationCommon.h((int) (-f3));
            }
            return true;
        }

        private TouchGesture() {
        }
    }

    public ListView(Context context) {
        this(context, null);
    }

    private int getScrollerY() {
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i2 = this.mCurrentfirstVisibleItem;
            if (i3 >= i2) {
                break;
            }
            ItemRecod itemRecod = (ItemRecod) this.recordSp.get(i3);
            if (itemRecod != null) {
                i4 += itemRecod.f17669a;
            }
            i3++;
        }
        ItemRecod itemRecod2 = (ItemRecod) this.recordSp.get(i2);
        if (itemRecod2 == null) {
            itemRecod2 = new ItemRecod();
        }
        return i4 - itemRecod2.f17670b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mSpringAnimationCommon == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        getTranslationY();
        if (motionEvent.getAction() == 0) {
            if (this.mSpringAnimationCommon.p().f()) {
                this.mSpringAnimationCommon.p().t();
                this.mSpringAnimationCommon.p().b();
            }
            this.mSpringAnimationCommon.f();
            this.mTotalyDy = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getInterruptSlideDirection() {
        return this.mInterruptDirection;
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean getIsBeingDragged() {
        IOverScrollDecor iOverScrollDecor = this.iOverScrollDecor;
        if (iOverScrollDecor != null) {
            return iOverScrollDecor.getIsBeingDragged();
        }
        return false;
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean getUseSpring() {
        return this.mIsUseSpring;
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean isCollapsed() {
        if (!isSupportSink()) {
            return true;
        }
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.t();
        }
        return false;
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean isDisableSink() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.v();
        }
        return false;
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean isSupportSink() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.w();
        }
        return false;
    }

    @Override // android.widget.ListView, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mIsUseSpring) {
            setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.zte.mifavor.widget.ListView.1

                /* renamed from: a, reason: collision with root package name */
                private SparseArray f17664a = new SparseArray(0);

                /* renamed from: b, reason: collision with root package name */
                private int f17665b = 0;

                /* renamed from: com.zte.mifavor.widget.ListView$1$ItemRecod */
                class ItemRecod {

                    /* renamed from: a, reason: collision with root package name */
                    int f17667a = 0;

                    /* renamed from: b, reason: collision with root package name */
                    int f17668b = 0;

                    ItemRecod(AnonymousClass1 anonymousClass1) {
                    }
                }

                private int a() {
                    int i2;
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        i2 = this.f17665b;
                        if (i3 >= i2) {
                            break;
                        }
                        ItemRecod itemRecod = (ItemRecod) this.f17664a.get(i3);
                        if (itemRecod != null) {
                            i4 += itemRecod.f17667a;
                        }
                        i3++;
                    }
                    ItemRecod itemRecod2 = (ItemRecod) this.f17664a.get(i2);
                    if (itemRecod2 == null) {
                        itemRecod2 = new ItemRecod(this);
                    }
                    return i4 - itemRecod2.f17668b;
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                    this.f17665b = i2;
                    View childAt = absListView.getChildAt(0);
                    if (childAt != null) {
                        ItemRecod itemRecod = (ItemRecod) this.f17664a.get(i2);
                        if (itemRecod == null) {
                            itemRecod = new ItemRecod(this);
                        }
                        itemRecod.f17667a = childAt.getHeight();
                        itemRecod.f17668b = childAt.getTop();
                        this.f17664a.append(i2, itemRecod);
                        if (itemRecod.f17667a != 0) {
                            ListView.this.mTotalyDy -= a() / childAt.getHeight();
                            ListView.this.mSpringAnimationCommon.C(ListView.this.mTotalyDy);
                        }
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i2) {
                    if (ListView.this.mIsUseSpring && ListView.this.mSpringAnimationCommon != null && i2 == 0 && ListView.this.mSpringAnimationCommon.t()) {
                        ListView.this.canScrollVertically(-1);
                        ListView.this.canScrollVertically(1);
                        ListView.this.mSpringAnimationCommon.x(ListView.this.getChildAt(0), 1001);
                    }
                }
            });
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setDampingRatio(String str) {
        try {
            this.mSpringAnimationCommon.y(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setDragAmplitude(String str) {
        try {
            this.mSpringAnimationCommon.z(Integer.valueOf(str).intValue());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setInterruptSlideDirection(int i2) {
        this.mInterruptDirection = i2;
    }

    public void setSlipAmplitude(String str) {
        try {
            this.mSpringAnimationCommon.D(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setStiffness(String str) {
        try {
            this.mSpringAnimationCommon.F(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setUseSpring(boolean z) {
        boolean z2 = z && this.mIsDispalyMotion;
        this.mIsUseSpring = z2;
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            springAnimationCommon.B(z2);
        }
    }

    public ListView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        OverScrollDecoratorHelper overScrollDecoratorHelper = new OverScrollDecoratorHelper(this);
        this.mOverScrollDecoratorHelper = overScrollDecoratorHelper;
        this.iOverScrollDecor = overScrollDecoratorHelper.b();
    }

    public ListView(@NonNull Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListView(@NonNull Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        boolean z = false;
        this.mTotalyDy = 0;
        this.mIsUseSpring = true;
        this.mIsDispalyMotion = true;
        this.iOverScrollDecor = null;
        this.mInterruptDirection = 1;
        this.recordSp = new SparseArray(0);
        this.mCurrentfirstVisibleItem = 0;
        SpringAnimationCommon springAnimationCommon = new SpringAnimationCommon();
        this.mSpringAnimationCommon = springAnimationCommon;
        springAnimationCommon.s(this, DynamicAnimation.f3652o, 0.0f);
        this.mSpringAnimationCommon.j(getContext());
        boolean booleanValue = Util.d(context).booleanValue();
        this.mIsDispalyMotion = booleanValue;
        if (this.mIsUseSpring && booleanValue) {
            z = true;
        }
        this.mIsUseSpring = z;
        this.mSpringAnimationCommon.B(z);
        this.mGestureDetector = new GestureDetector(getContext(), new TouchGesture());
    }
}
