package com.zte.mifavor.androidx.widget.swipe;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.extres.R;
import com.zte.mifavor.utils.Utils;

/* loaded from: classes2.dex */
public class SwipeContentView extends FrameLayout {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SCROLLER_DURATION = 200;
    private static final String TAG = "Z#SwipeContentView";
    private int mMenuItemCount;
    private int mMenuItemWidth;
    private int mMenuWidth;

    @Nullable
    private OverScroller mOverScroller;

    @Nullable
    private ValueAnimator valueTranslationAnim;

    /* renamed from: com.zte.mifavor.androidx.widget.swipe.SwipeContentView$2, reason: invalid class name */
    class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SwipeContentView f17215c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f17215c.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            this.f17215c.invalidate();
        }
    }

    /* renamed from: com.zte.mifavor.androidx.widget.swipe.SwipeContentView$3, reason: invalid class name */
    class AnonymousClass3 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            Log.i(SwipeContentView.TAG, "+++++++++++ onAnimationCancel ");
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Log.i(SwipeContentView.TAG, "+++++++++++ onAnimationEnd ");
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            Log.i(SwipeContentView.TAG, "+++++++++++ onAnimationStart ");
        }
    }

    public SwipeContentView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.View
    public void computeScroll() {
        OverScroller overScroller = this.mOverScroller;
        if (overScroller == null || !overScroller.computeScrollOffset()) {
            return;
        }
        scrollTo(Math.abs(this.mOverScroller.getCurrX()), 0);
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        super.scrollBy(i2, i3);
    }

    public void setMenuItemCount(int i2) {
        int i3;
        this.mMenuItemCount = i2;
        if (i2 == 0 || (i3 = this.mMenuItemWidth) == 0) {
            return;
        }
        this.mMenuWidth = i3 * i2;
    }

    public void setMenuItemWidth(int i2) {
        this.mMenuItemWidth = i2;
        int i3 = this.mMenuItemCount;
        if (i3 == 0 || i2 == 0) {
            return;
        }
        this.mMenuWidth = i2 * i3;
    }

    public void setOverScroller(@NonNull OverScroller overScroller) {
        this.mOverScroller = overScroller;
    }

    public SwipeContentView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMenuItemCount = 0;
        this.mMenuItemWidth = 0;
        this.mMenuWidth = 0;
        this.valueTranslationAnim = null;
        this.mOverScroller = null;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwipeContentView, i2, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SwipeContentView_background_isCard, true);
        obtainStyledAttributes.recycle();
        if (z) {
            setOutlineProvider(new ViewOutlineProvider() { // from class: com.zte.mifavor.androidx.widget.swipe.SwipeContentView.1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), Utils.c(SwipeContentView.this.getContext(), 16.0f));
                }
            });
            setClipToOutline(true);
        }
    }
}
