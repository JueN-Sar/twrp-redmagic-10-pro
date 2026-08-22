package cn.nubia.gamelauncher.bean;

import android.graphics.BlurMaskFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.animation.PathInterpolator;
import cn.nubia.gamelauncher.recycler.Anim3DHelper;
import java.util.Random;

/* loaded from: classes.dex */
public class CircleBean {
    float mAngle;
    private float mDistance;
    float mEndDegree;
    int mEnterAnimDelayedTime;
    int mEnterAnimDuration;
    boolean mFlag;
    boolean mIsLift;
    boolean mIsPendulum;
    PathInterpolator mPathInterpolator;
    int mPendulumDuration;
    PathInterpolator mPendulumInterpolator;
    long mPendulumStartTime;
    float mRadius;
    float mRotateVelocity;
    private float mRotationAngle;
    float mStartDegree;
    private float mTranslate;
    final int ANGLE_360 = 360;
    final int ANGLE_270 = 270;
    final int ANGLE_180 = 180;
    final int ANGLE_90 = 90;
    Random mRandom = new Random();
    Paint mPaint = new Paint();

    public CircleBean(float f, float f2, int i, int i2, float f3, PathInterpolator pathInterpolator, float[] fArr, int i3, int i4, float f4, float f5, boolean z, int i5, PathInterpolator pathInterpolator2, boolean z2) {
        this.mRotateVelocity = 0.0f;
        this.mRadius = 0.0f;
        this.mRotationAngle = 0.0f;
        this.mTranslate = 0.0f;
        this.mDistance = 0.0f;
        this.mEnterAnimDuration = 0;
        this.mEnterAnimDelayedTime = 0;
        this.mFlag = false;
        this.mRotateVelocity = f2;
        this.mRadius = f;
        this.mEnterAnimDuration = i;
        this.mEnterAnimDelayedTime = i2;
        this.mPathInterpolator = pathInterpolator;
        this.mTranslate = f4;
        this.mRotationAngle = f5;
        this.mDistance = f3;
        this.mIsPendulum = z;
        this.mPendulumDuration = i5;
        this.mPendulumInterpolator = pathInterpolator2;
        if (z) {
            this.mEndDegree = f5;
            makeNextPendulumData();
        }
        if (z && fArr != null) {
            this.mFlag = true;
        }
        this.mIsLift = z2;
        initPaint(i3, i4, fArr);
    }

    private int getPendulumElapsedTime() {
        return (int) (System.currentTimeMillis() - this.mPendulumStartTime);
    }

    private void initPaint(int i, int i2, float[] fArr) {
        this.mPaint.setColor(i);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(i2);
        this.mPaint.setStyle(Paint.Style.STROKE);
        if (fArr != null && fArr.length >= 2) {
            this.mPaint.setPathEffect(new DashPathEffect(fArr, 5.0f));
            if (fArr[1] < this.mRadius) {
                this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            }
        }
        this.mPaint.setMaskFilter(new BlurMaskFilter(12.0f, BlurMaskFilter.Blur.SOLID));
    }

    private void makeNextPendulumData() {
        float f = this.mEndDegree;
        this.mStartDegree = f;
        float nextInt = (f > 270.0f ? 90 : 360) + this.mRandom.nextInt(180);
        this.mEndDegree = nextInt;
        this.mAngle = nextInt - this.mStartDegree;
        this.mPendulumStartTime = System.currentTimeMillis();
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public float getRotateVelocity() {
        return this.mRotateVelocity;
    }

    public float getRotationAngle() {
        if (!this.mIsPendulum) {
            return this.mRotationAngle;
        }
        int pendulumElapsedTime = getPendulumElapsedTime();
        int i = this.mPendulumDuration;
        if (pendulumElapsedTime < i) {
            return this.mStartDegree + (this.mAngle * Anim3DHelper.getValueOfInterpolator(this.mPendulumInterpolator, pendulumElapsedTime, i));
        }
        this.mRotationAngle = this.mEndDegree;
        makeNextPendulumData();
        return this.mRotationAngle;
    }

    public float getTranslate() {
        return this.mIsLift ? this.mTranslate : this.mTranslate - this.mDistance;
    }

    public float getTranslateByDuration(int i) {
        int i2;
        if (!this.mIsLift && i > (i2 = this.mEnterAnimDelayedTime)) {
            return this.mTranslate - (this.mDistance * Anim3DHelper.getValueOfInterpolator(this.mPathInterpolator, i - i2, this.mEnterAnimDuration));
        }
        return this.mTranslate;
    }

    public boolean isLiftCircle() {
        return this.mIsLift;
    }

    public boolean isRotateCircle() {
        return 0.0f != this.mRotateVelocity;
    }

    public void setRotateVelocity(float f) {
        this.mRotateVelocity = f;
    }

    public void setRotationAngle(float f) {
        this.mRotationAngle = f;
    }
}
