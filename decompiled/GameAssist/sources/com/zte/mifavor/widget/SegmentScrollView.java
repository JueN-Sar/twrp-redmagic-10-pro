package com.zte.mifavor.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class SegmentScrollView extends FrameLayout {
    private static final int COUNT_2 = 2;
    private static final int COUNT_3 = 3;
    private static final int COUNT_4 = 4;
    private static final int COUNT_5 = 5;
    private String TAG;
    private boolean mItemBgAnimating;

    @Nullable
    private ValueAnimator mLastAnimation;
    private boolean mNightStyle;

    @Nullable
    private SegmentSelector mSegment;

    @Nullable
    private ImageView mSegmentItemBg;

    @Nullable
    private ImageView mSegmentViewBg;

    /* renamed from: com.zte.mifavor.widget.SegmentScrollView$3, reason: invalid class name */
    class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f17759c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ float f17760h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ float f17761i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ float f17762j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ SegmentScrollView f17763k;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float abs = Math.abs(floatValue - this.f17759c);
            float f2 = this.f17760h;
            float f3 = f2 + ((this.f17761i - f2) * (abs / this.f17762j));
            if (this.f17763k.mSegmentItemBg != null) {
                ViewGroup.LayoutParams layoutParams = this.f17763k.mSegmentItemBg.getLayoutParams();
                layoutParams.width = (int) f3;
                this.f17763k.mSegmentItemBg.setLayoutParams(layoutParams);
                this.f17763k.mSegmentItemBg.setX(floatValue - (f3 / 2.0f));
                this.f17763k.mSegmentItemBg.invalidate();
            }
        }
    }

    /* renamed from: com.zte.mifavor.widget.SegmentScrollView$4, reason: invalid class name */
    class AnonymousClass4 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f17764c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ SegmentScrollView f17765h;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f17765h.mLastAnimation = null;
            if (this.f17765h.mSegment != null) {
                this.f17765h.mSegment.c(this.f17764c, false);
            }
            this.f17765h.n(this.f17764c);
            this.f17765h.mItemBgAnimating = false;
        }
    }

    public SegmentScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m() {
        if (SegmentSelector.DBG) {
            Log.d(this.TAG, "set Segment View Bg in. mSegment=" + this.mSegment);
        }
        if (this.mSegment != null) {
            View view = null;
            int i2 = 0;
            for (int i3 = 0; i3 < this.mSegment.getChildCount(); i3++) {
                View childAt = this.mSegment.getChildAt(i3);
                if (childAt != null) {
                    if (view == null) {
                        view = childAt;
                    }
                    i2 += childAt.getWidth();
                }
            }
            if (view == null) {
                return;
            }
            if (i2 > 0) {
                i2 += getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_segmented_bg_padding) * 2;
            }
            ImageView imageView = this.mSegmentViewBg;
            if (imageView != null) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.width = i2;
                this.mSegmentViewBg.setX(view.getX());
                this.mSegmentViewBg.setLayoutParams(layoutParams);
                this.mSegmentViewBg.setBackgroundResource(R.drawable.segment_bg);
                if (SegmentSelector.DBG) {
                    Log.w(this.TAG, "set SegmentView Bg. totalWidth=" + i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void n(int i2) {
        ImageView imageView;
        final TextView textView;
        if (SegmentSelector.DBG) {
            Log.d(this.TAG, "show Item Bg in. position=" + i2);
        }
        if (i2 == -1 || (imageView = this.mSegmentItemBg) == null || this.mSegment == null) {
            return;
        }
        final ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        final View childAt = this.mSegment.getChildAt(i2);
        if (childAt == null || (textView = (TextView) childAt.findViewById(R.id.segment_text_item)) == null) {
            return;
        }
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.widget.SegmentScrollView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                layoutParams.width = textView.getWidth();
                layoutParams.height = textView.getHeight();
                if (SegmentScrollView.this.mSegmentItemBg != null) {
                    SegmentScrollView.this.mSegmentItemBg.setLayoutParams(layoutParams);
                    SegmentScrollView.this.mSegmentItemBg.setTranslationX(0.0f);
                    SegmentScrollView.this.mSegmentItemBg.setX(childAt.getX() + ((childAt.getWidth() - textView.getWidth()) / 2));
                    SegmentScrollView.this.mSegmentItemBg.invalidate();
                }
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        layoutParams.width = textView.getWidth();
        layoutParams.height = textView.getHeight();
        ImageView imageView2 = this.mSegmentItemBg;
        if (imageView2 != null) {
            imageView2.setLayoutParams(layoutParams);
            this.mSegmentItemBg.setTranslationX(0.0f);
            this.mSegmentItemBg.setX(childAt.getX() + ((childAt.getWidth() - textView.getWidth()) / 2));
            this.mSegmentItemBg.invalidate();
        }
        o(i2);
        this.mSegmentItemBg.setVisibility(4);
    }

    private final void o(int i2) {
        TextView textView;
        if (this.mSegment != null) {
            int i3 = 0;
            while (i3 < this.mSegment.getChildCount()) {
                View childAt = this.mSegment.getChildAt(i3);
                if (childAt != null && (textView = (TextView) childAt.findViewById(R.id.segment_text_item)) != null) {
                    textView.setEnabled(i3 == i2);
                }
                i3++;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2) {
            return false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setDEBUG(boolean z) {
        if (this.mSegment != null) {
            SegmentSelector.DBG = z;
        }
    }

    public void setLayoutPadding(int i2) {
    }

    public void setNightStyle(boolean z) {
        if (SegmentSelector.DBG) {
            Log.d(this.TAG, "setNightStyle in. bNightStyle = " + z);
        }
        if (this.mNightStyle == z) {
            return;
        }
        this.mNightStyle = z;
        ImageView imageView = this.mSegmentItemBg;
        if (imageView != null) {
            if (z) {
                imageView.setBackgroundResource(R.drawable.selector_item_bg_dark);
            } else {
                imageView.setBackgroundResource(R.drawable.selector_item_bg);
            }
        }
        SegmentSelector segmentSelector = this.mSegment;
        if (segmentSelector != null) {
            segmentSelector.setNightStyle(this.mNightStyle);
        }
    }

    public void setPadding(int i2) {
    }

    public SegmentScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "SS#SegmentScrollView";
        this.mNightStyle = false;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.widget.SegmentScrollView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (SegmentSelector.DBG) {
                    Log.d(SegmentScrollView.this.TAG, "onGlobalLayout in. ");
                }
                SegmentScrollView segmentScrollView = SegmentScrollView.this;
                segmentScrollView.mSegment = (SegmentSelector) segmentScrollView.findViewById(R.id.segment_selector);
                SegmentScrollView segmentScrollView2 = SegmentScrollView.this;
                segmentScrollView2.mSegmentItemBg = (ImageView) segmentScrollView2.findViewById(R.id.segment_selected_item_bg);
                SegmentScrollView segmentScrollView3 = SegmentScrollView.this;
                segmentScrollView3.mSegmentViewBg = (ImageView) segmentScrollView3.findViewById(R.id.segment_view_bg);
                if (SegmentScrollView.this.mSegmentItemBg != null) {
                    if (SegmentScrollView.this.mNightStyle) {
                        SegmentScrollView.this.mSegmentItemBg.setBackgroundResource(R.drawable.selector_item_bg_dark);
                    } else {
                        SegmentScrollView.this.mSegmentItemBg.setBackgroundResource(R.drawable.selector_item_bg);
                    }
                }
                if (SegmentScrollView.this.mSegment != null) {
                    SegmentScrollView.this.mSegment.setNightStyle(SegmentScrollView.this.mNightStyle);
                }
                SegmentScrollView.this.post(new Runnable() { // from class: com.zte.mifavor.widget.SegmentScrollView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!SegmentScrollView.this.mItemBgAnimating && SegmentScrollView.this.mSegment != null) {
                            SegmentScrollView segmentScrollView4 = SegmentScrollView.this;
                            segmentScrollView4.n(segmentScrollView4.mSegment.getSelectedPosition());
                        }
                        SegmentScrollView.this.m();
                    }
                });
                SegmentScrollView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (SegmentSelector.DBG) {
                    Log.d(SegmentScrollView.this.TAG, "onGlobalLayout out. ");
                }
            }
        });
    }

    public SegmentScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.TAG = "SS#SegmentScrollView";
        this.mNightStyle = false;
    }
}
