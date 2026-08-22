package com.zte.mifavor.custom.internal.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.internal.app.AlertController;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.SpringUtils;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;
import com.zte.mifavor.utils.overscroll.OverScrollDecoratorHelper;
import com.zte.mifavor.widget.ISpringView;
import com.zte.mifavor.widget.Util;

/* loaded from: classes2.dex */
public class RecycleListView extends AlertController.RecycleListView implements ISpringView {
    private static final String TAG = "Z#View-RecycleListView";

    @Nullable
    private IOverScrollDecor iOverScrollDecor;

    @NonNull
    private GestureDetector mGestureDetector;
    private boolean mIsDispalyMotion;
    private boolean mIsUseSpring;

    @Nullable
    private OverScrollDecoratorHelper mOverScrollDecoratorHelper;

    @NonNull
    private SpringAnimationCommon mSpringAnimationCommon;
    private int mTotalyDy;

    private class TouchGesture extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            Log.d(RecycleListView.TAG, "onFling+++++++++++++++++++++, velocityX = " + f2 + ", velocityY = " + f3 + ", canScrollUp = " + RecycleListView.this.canScrollVertically(-1) + ", canScrollDown = " + RecycleListView.this.canScrollVertically(1) + ", IsBeingDragged = " + RecycleListView.this.getIsBeingDragged());
            if (!RecycleListView.this.getIsBeingDragged()) {
                if (RecycleListView.this.mIsUseSpring && RecycleListView.this.mSpringAnimationCommon != null) {
                    RecycleListView.this.mSpringAnimationCommon.h((int) (-f3));
                }
                return true;
            }
            Log.w(RecycleListView.TAG, "fling+++++++++++++++++++++, ignore fling, velocityY = " + f3);
            return false;
        }

        private TouchGesture() {
        }
    }

    public RecycleListView(@NonNull Context context) {
        this(context, null);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mSpringAnimationCommon.f();
            this.mTotalyDy = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean getIsBeingDragged() {
        return SpringUtils.a(this.iOverScrollDecor);
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean getUseSpring() {
        return this.mIsUseSpring;
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean isCollapsed() {
        return SpringUtils.b(this.mSpringAnimationCommon);
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean isDisableSink() {
        return SpringUtils.c(this.mSpringAnimationCommon);
    }

    @Override // com.zte.mifavor.widget.ISpringView
    public boolean isSupportSink() {
        return SpringUtils.d(this.mSpringAnimationCommon);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate in. mIsUseSpring = " + this.mIsUseSpring);
        if (this.mIsUseSpring) {
            setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.zte.mifavor.custom.internal.app.RecycleListView.1
                private SparseArray recordSp = new SparseArray(0);
                private int mCurrentfirstVisibleItem = 0;

                /* renamed from: com.zte.mifavor.custom.internal.app.RecycleListView$1$ItemRecod */
                class ItemRecod {
                    int height = 0;
                    int top = 0;

                    ItemRecod(AnonymousClass1 anonymousClass1) {
                    }
                }

                private int getScrollY() {
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
                            i4 += itemRecod.height;
                        }
                        i3++;
                    }
                    ItemRecod itemRecod2 = (ItemRecod) this.recordSp.get(i2);
                    if (itemRecod2 == null) {
                        itemRecod2 = new ItemRecod(this);
                    }
                    return i4 - itemRecod2.top;
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                    this.mCurrentfirstVisibleItem = i2;
                    View childAt = absListView.getChildAt(0);
                    if (childAt != null) {
                        ItemRecod itemRecod = (ItemRecod) this.recordSp.get(i2);
                        if (itemRecod == null) {
                            itemRecod = new ItemRecod(this);
                        }
                        itemRecod.height = childAt.getHeight();
                        itemRecod.top = childAt.getTop();
                        this.recordSp.append(i2, itemRecod);
                        if (itemRecod.height != 0) {
                            RecycleListView.this.mTotalyDy -= getScrollY() / childAt.getHeight();
                            RecycleListView.this.mSpringAnimationCommon.C(RecycleListView.this.mTotalyDy);
                        }
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i2) {
                    Log.d(RecycleListView.TAG, "onScrollStateChanged in, state = " + i2);
                    if (!RecycleListView.this.mIsUseSpring || RecycleListView.this.mSpringAnimationCommon == null) {
                        Log.w(RecycleListView.TAG, "onScrollStateChanged don't use animation, mIsUseSpring = " + RecycleListView.this.mIsUseSpring);
                        return;
                    }
                    if (i2 != 0 || !RecycleListView.this.mSpringAnimationCommon.t()) {
                        Log.d(RecycleListView.TAG, "onScrollStateChanged out, state = " + i2);
                        return;
                    }
                    Log.w(RecycleListView.TAG, "onScrollStateChanged, state = " + i2 + ", canScrollUp = " + RecycleListView.this.canScrollVertically(-1) + ", canScrollDown = " + RecycleListView.this.canScrollVertically(1));
                    RecycleListView.this.mSpringAnimationCommon.x(RecycleListView.this.getChildAt(0), 1001);
                }
            });
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(TAG, "+++++++++++++++++++ onTouchEvent ev = " + motionEvent.getAction());
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setUseSpring(boolean z) {
        boolean z2 = z && this.mIsDispalyMotion;
        this.mIsUseSpring = z2;
        SpringUtils.e(this.mSpringAnimationCommon, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecycleListView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z = false;
        this.mTotalyDy = 0;
        this.mIsUseSpring = true;
        this.mIsDispalyMotion = true;
        this.iOverScrollDecor = null;
        Log.d(TAG, "ListView in, context = " + context);
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
        Log.d(TAG, "ListView out. mIsDispalyMotion = " + this.mIsDispalyMotion + ", mIsUseSpring = " + this.mIsUseSpring);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        Log.e(TAG, "setAdapter in ");
        OverScrollDecoratorHelper overScrollDecoratorHelper = new OverScrollDecoratorHelper(this);
        this.mOverScrollDecoratorHelper = overScrollDecoratorHelper;
        this.iOverScrollDecor = overScrollDecoratorHelper.b();
    }
}
