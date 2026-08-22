package cn.nubia.gameassist.dessert.policy.clean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class HaloView extends TextView {
    private Bitmap mBg;
    private final Matrix mBgMatrix;
    private int mHaloAlpha;
    private final Paint mHaloPaint;
    private float mHaloRadiusInner;
    private final Paint mParticlePaint;
    private float mParticleRadiusOuter;
    private float mParticleWidth;
    private final ArrayList<Particle> mParticles;
    private float mRadius;
    private float mWidth;

    public HaloView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void b() {
        this.mWidth = getResources().getDimension(R.dimen.tile_clean_slide_width);
        this.mHaloRadiusInner = getResources().getDimension(R.dimen.halo_radius_inner);
        this.mParticleWidth = getResources().getDimension(R.dimen.particle_width);
        this.mParticleRadiusOuter = getResources().getDimension(R.dimen.particle_radius_halo_outer);
        setWillNotDraw(false);
        this.mHaloPaint.setAntiAlias(true);
        this.mParticlePaint.setAntiAlias(true);
        this.mParticlePaint.setColor(-3236502);
        getCleanAniParticleBg();
    }

    private Bitmap getCleanAniParticleBg() {
        if (this.mBg == null) {
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.clean_ani_particle_bg);
            float width = decodeResource.getWidth();
            float height = decodeResource.getHeight();
            Matrix matrix = new Matrix();
            float f2 = this.mWidth / width;
            matrix.postScale(f2, f2);
            this.mBg = Bitmap.createBitmap(decodeResource, 0, 0, (int) width, (int) height, matrix, true);
        }
        return this.mBg;
    }

    public void a() {
        this.mParticles.clear();
    }

    public void c() {
        for (int i2 = 0; i2 < 20; i2++) {
            this.mParticles.add(new Particle(this.mParticleWidth, this.mHaloRadiusInner, this.mParticleRadiusOuter));
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mHaloPaint.setStyle(Paint.Style.STROKE);
        this.mHaloPaint.setColor(-3236502);
        this.mHaloPaint.setAlpha(this.mHaloAlpha);
        this.mHaloPaint.setStrokeWidth(this.mRadius);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mHaloRadiusInner, this.mHaloPaint);
        this.mHaloPaint.setColor(-2607850);
        this.mHaloPaint.setAlpha(this.mHaloAlpha);
        this.mHaloPaint.setStrokeWidth(0.5f);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mHaloRadiusInner, this.mHaloPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mHaloRadiusInner + this.mHaloPaint.getStrokeWidth(), this.mHaloPaint);
        canvas.drawBitmap(getCleanAniParticleBg(), this.mBgMatrix, this.mParticlePaint);
        Iterator<Particle> it = this.mParticles.iterator();
        while (it.hasNext()) {
            it.next().a(canvas, this.mParticlePaint);
        }
    }

    public void setHaloAlpha(int i2) {
        this.mHaloAlpha = i2;
        invalidate();
    }

    public void setHaloRadius(float f2) {
        this.mRadius = f2;
        invalidate();
    }

    public void setParticleBGAlpha(int i2) {
        this.mParticlePaint.setAlpha(i2);
        invalidate();
    }

    public void setParticleBGRotation(float f2) {
        this.mParticles.add(new Particle(this.mParticleWidth, this.mHaloRadiusInner, this.mParticleRadiusOuter));
        this.mBgMatrix.setRotate(f2, getWidth() / 2, getHeight() / 2);
        invalidate();
    }

    public HaloView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBgMatrix = new Matrix();
        this.mParticlePaint = new Paint();
        this.mHaloPaint = new Paint();
        this.mParticles = new ArrayList<>();
        b();
    }
}
