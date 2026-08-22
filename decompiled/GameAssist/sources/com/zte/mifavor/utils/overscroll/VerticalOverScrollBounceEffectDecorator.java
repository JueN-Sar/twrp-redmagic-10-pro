package com.zte.mifavor.utils.overscroll;

import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator;
import com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter;

/* loaded from: classes2.dex */
public class VerticalOverScrollBounceEffectDecorator extends BaseOverScrollBounceEffectDecorator {

    protected static class AnimationAttributesVertical extends BaseOverScrollBounceEffectDecorator.BaseAnimationAttributes {
        public AnimationAttributesVertical() {
            this.f17478a = View.TRANSLATION_Y;
        }
    }

    protected static class MotionAttributesVertical extends BaseOverScrollBounceEffectDecorator.BaseMotionAttributes {
        protected MotionAttributesVertical() {
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.BaseMotionAttributes
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.f17479a = view.getTranslationY();
            this.f17480b = y;
            this.f17481c = y > 0.0f;
            return true;
        }
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, SpringAnimation springAnimation, VelocityTracker velocityTracker) {
        this(iOverScrollDecoratorAdapter, 1.0f, 1.0f, -2.0f, springAnimation, velocityTracker);
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected void C(View view, float f2) {
        float abs = Math.abs(f2);
        B(((abs / this.f17465h) * 3.5f) + 1.0f);
        A(((abs / this.f17465h) * 3.5f) + 1.0f);
        if (abs > this.f17465h) {
            Log.d("Z#QScroll-VBounceDec", "translateY View out do nothing. offset = " + f2 + ", mHeightTreshold = " + this.f17465h);
            return;
        }
        view.setTranslationY(f2);
        Log.d("Z#QScroll-VBounceDec", "translateY View out. offset = " + f2 + ", mHeightTreshold = " + this.f17465h);
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected void D(View view, float f2, MotionEvent motionEvent) {
        view.setTranslationY(f2);
        float y = motionEvent.getY(0);
        float f3 = f2 - y;
        motionEvent.offsetLocation(f3, 0.0f);
        Log.d("Z#QScroll-VBounceDec", "translateY ViewAndEvent out. offset = " + f2 + ", dy = " + y + ", deltaX = " + f3);
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected BaseOverScrollBounceEffectDecorator.BaseAnimationAttributes q() {
        return new AnimationAttributesVertical();
    }

    @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator
    protected BaseOverScrollBounceEffectDecorator.BaseMotionAttributes r() {
        return new MotionAttributesVertical();
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f2, float f3, float f4, SpringAnimation springAnimation, VelocityTracker velocityTracker) {
        super(iOverScrollDecoratorAdapter, f4, f2, f3, springAnimation, velocityTracker);
    }
}
