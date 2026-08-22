package cn.nubia.gamecenter.settings.widget.particle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

/* loaded from: classes.dex */
public class ParticleSurfaceView extends GLSurfaceView {
    private ValueAnimator mAnim;
    private ParticleRenderer mRenderer;

    public ParticleSurfaceView(Context context) {
        super(context);
        initRenderer(context);
    }

    public ParticleSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initRenderer(context);
    }

    private void initRenderer(Context context) {
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(-2);
        setZOrderOnTop(true);
        ParticleRenderer particleRenderer = new ParticleRenderer(context);
        this.mRenderer = particleRenderer;
        setRenderer(particleRenderer);
        setRenderMode(0);
    }

    public ValueAnimator createAnim() {
        stopAnim();
        this.mRenderer.initParticles();
        ValueAnimator ofInt = ValueAnimator.ofInt(0, this.mRenderer.getAnimDuration());
        this.mAnim = ofInt;
        ofInt.setInterpolator(new LinearInterpolator());
        this.mAnim.setDuration(this.mRenderer.getAnimDuration());
        this.mAnim.setStartDelay(100L);
        this.mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gamecenter.settings.widget.particle.ParticleSurfaceView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ParticleSurfaceView.this.mRenderer.update(((Integer) valueAnimator.getAnimatedValue()).intValue());
                ParticleSurfaceView.this.requestRender();
            }
        });
        return this.mAnim;
    }

    public void setColor(int i) {
        this.mRenderer.setColor(i);
    }

    public void stopAnim() {
        ValueAnimator valueAnimator = this.mAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAnim = null;
        }
        this.mRenderer.clearParticles();
        requestRender();
    }
}
