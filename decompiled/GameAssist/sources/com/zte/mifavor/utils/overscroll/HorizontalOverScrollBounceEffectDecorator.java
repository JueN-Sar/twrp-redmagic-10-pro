package com.zte.mifavor.utils.overscroll;

import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator;
import com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter;

/* loaded from: classes2.dex */
public class HorizontalOverScrollBounceEffectDecorator extends BaseOverScrollBounceEffectDecorator {

    protected static class AnimationAttributesHorizontal extends BaseOverScrollBounceEffectDecorator.BaseAnimationAttributes {
        public AnimationAttributesHorizontal() {
            this.f17478a = View.TRANSLATION_X;
        }
    }

    protected static class MotionAttributesHorizontal extends BaseOverScrollBounceEffectDecorator.BaseMotionAttributes {
        protected MotionAttributesHorizontal() {
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.BaseMotionAttributes
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x) < Math.abs(y)) {
                return false;
            }
            this.f17479a = view.getTranslationX();
            this.f17480b = x;
            this.f17481c = x > 0.0f;
            return true;
        }
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, SpringAnimation springAnimation, VelocityTracker velocityTracker) {
        this(iOverScrollDecoratorAdapter, 1.0f, 1.0f, -2.0f, springAnimation, velocityTracker);
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected void C(View view, float f2) {
        float abs = Math.abs(f2);
        B(((abs / this.f17465h) * 3.5f) + 1.0f);
        A(((abs / this.f17465h) * 3.5f) + 1.0f);
        if (abs > this.f17464c) {
            Log.d("Z#QScrollBaseDecorator", "translateX View out do nothing. offset = " + f2 + ", mWidthTreshold = " + this.f17464c);
            return;
        }
        view.setTranslationX(f2);
        Log.d("Z#QScrollBaseDecorator", "translateX  View out. offset = " + f2 + ", mWidthTreshold = " + this.f17464c);
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected void D(View view, float f2, MotionEvent motionEvent) {
        view.setTranslationX(f2);
        float x = f2 - motionEvent.getX(0);
        motionEvent.offsetLocation(x, 0.0f);
        Log.d("Z#QScrollBaseDecorator", "translateX View And Event out. offset = " + f2 + ", deltaX = " + x);
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected BaseOverScrollBounceEffectDecorator.BaseAnimationAttributes q() {
        return new AnimationAttributesHorizontal();
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected BaseOverScrollBounceEffectDecorator.BaseMotionAttributes r() {
        return new MotionAttributesHorizontal();
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f2, float f3, float f4, SpringAnimation springAnimation, VelocityTracker velocityTracker) {
        super(iOverScrollDecoratorAdapter, f4, f2, f3, springAnimation, velocityTracker);
    }
}
