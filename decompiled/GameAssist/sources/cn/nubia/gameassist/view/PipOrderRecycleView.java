package cn.nubia.gameassist.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.pips.custom.PipOrderLayoutManager;

/* loaded from: classes.dex */
public class PipOrderRecycleView extends RecyclerView {
    private float mInitTranslationX;
    private PipOrderLayoutManager mLayoutManager;
    private float mRawDownX;

    public PipOrderRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B1(ValueAnimator valueAnimator) {
        setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    private void C1(float f2, float f3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        ofFloat.setDuration(50L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.view.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                PipOrderRecycleView.this.B1(valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter(this) { // from class: cn.nubia.gameassist.view.PipOrderRecycleView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });
        ofFloat.start();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mInitTranslationX = getTranslationX();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mLayoutManager = (PipOrderLayoutManager) getLayoutManager();
            this.mRawDownX = motionEvent.getRawX();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float translationX = getTranslationX();
        int action = motionEvent.getAction();
        if (action == 1) {
            float rawX = motionEvent.getRawX();
            if (translationX > 0.0f) {
                float f2 = this.mInitTranslationX;
                if (translationX < f2) {
                    if (rawX - this.mRawDownX > 0.0f) {
                        C1(translationX, f2);
                    } else {
                        C1(translationX, 0.0f);
                    }
                }
            }
        } else if (action == 2) {
            float rawX2 = translationX + ((motionEvent.getRawX() - this.mRawDownX) / 10.0f);
            PipOrderLayoutManager pipOrderLayoutManager = this.mLayoutManager;
            if (pipOrderLayoutManager == null) {
                return true;
            }
            if (!pipOrderLayoutManager.a2() && this.mLayoutManager.V1()) {
                float max = Math.max(0.0f, Math.min(this.mInitTranslationX, rawX2));
                if (max > 0.0f && this.mLayoutManager.Z1() == 0) {
                    setTranslationX(max);
                    return true;
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public PipOrderRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
