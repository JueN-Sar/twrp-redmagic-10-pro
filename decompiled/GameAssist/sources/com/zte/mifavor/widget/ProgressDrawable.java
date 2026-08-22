package com.zte.mifavor.widget;

import android.animation.TimeAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ProgressDrawable extends Drawable implements Animatable {
    private static final float CIRCLE_DISTANCE = 360.0f;
    private static final int CIRCLE_INTERVAL = 160;
    private static final int CIRCLE_NUMBER = 6;
    private static final int CIRCLE_PERIOD = 1500;
    private static final int CIRCLE_PRE = 40;
    private static final int CIRCLE_START_END = 40;
    private static final int CIRCLE_TOTAL_TIME = 2300;
    private static final int PROGRESS_SPEED = 120;
    private static final String TAG = "ProgressDrawable";
    private int mCircleRadiusDelay;
    private int mCircleRadiusRun;
    private Paint mPaint;
    private float mProgressPoint;
    private int mProgressRadius;
    private int mProgressX;
    private int mProgressY;
    private RectF mRect;
    private TimeAnimator mTimeAnimator;
    private int curEndX = -1;
    private int curEndY = -1;
    private int curStartEndRadius = -1;
    private ArrayList<SingleCircle> mCircles = new ArrayList<>();
    private boolean mStarted = false;
    private MetaBall mMetaBall = new MetaBall();

    private class CircleEvaluator {

        /* renamed from: a, reason: collision with root package name */
        float f17721a;

        /* renamed from: c, reason: collision with root package name */
        private int f17723c;

        /* renamed from: b, reason: collision with root package name */
        float f17722b = 0.65217394f;

        /* renamed from: d, reason: collision with root package name */
        private Interpolator f17724d = new LinearInterpolator();

        public CircleEvaluator(int i2) {
            this.f17723c = i2;
            this.f17721a = (i2 * 160.0f) / 2300.0f;
        }

        public float a(float f2) {
            return (1.0f - (this.f17723c * 0.1f)) * 0.5f * 255.0f;
        }

        public float b(float f2) {
            float f3 = this.f17721a;
            if (f2 <= f3) {
                return 0.0f;
            }
            float f4 = this.f17722b;
            return f2 >= f4 + f3 ? ProgressDrawable.CIRCLE_DISTANCE : this.f17724d.getInterpolation((f2 - f3) / f4) * ProgressDrawable.CIRCLE_DISTANCE;
        }

        public float c(float f2) {
            float b2 = b(f2);
            return b2 < 280.0f ? a(1.0f) : (b2 < 280.0f || b2 > 320.0f) ? d(1.0f) : (new LinearInterpolator().getInterpolation((b2 - 280.0f) / 40.0f) * (d(1.0f) - a(1.0f))) + a(1.0f);
        }

        public float d(float f2) {
            if (f2 <= 0.4673913f) {
                return new LinearInterpolator().getInterpolation(1.0f - ((f2 * 0.5f) / 0.4673913f)) * 0.5f * 255.0f;
            }
            return 127.5f;
        }

        public float e(float f2) {
            float f3;
            float f4;
            if (f2 <= 0.4173913f) {
                f3 = ProgressDrawable.this.mCircleRadiusRun;
                f4 = ((ProgressDrawable.this.mCircleRadiusDelay - ProgressDrawable.this.mCircleRadiusRun) * (0.4173913f - f2)) / 0.4173913f;
            } else {
                if (f2 < 0.5826087f) {
                    return ProgressDrawable.this.mCircleRadiusRun;
                }
                f3 = ProgressDrawable.this.mCircleRadiusRun;
                f4 = (ProgressDrawable.this.mCircleRadiusDelay - ProgressDrawable.this.mCircleRadiusRun) * (1.0f - ((1.0f - f2) / 0.4173913f));
            }
            return f3 + f4;
        }
    }

    private enum CircleStatus {
        STARTING,
        PRERUN,
        RUNNING,
        PREEND,
        ENDING
    }

    private class SingleCircle {

        /* renamed from: a, reason: collision with root package name */
        private int f17726a;

        /* renamed from: b, reason: collision with root package name */
        private int f17727b = 0;

        /* renamed from: c, reason: collision with root package name */
        private int f17728c = 0;

        /* renamed from: d, reason: collision with root package name */
        private int f17729d = 0;

        /* renamed from: e, reason: collision with root package name */
        private int f17730e = 0;

        /* renamed from: f, reason: collision with root package name */
        private int f17731f = 0;

        /* renamed from: g, reason: collision with root package name */
        private int f17732g = 0;

        /* renamed from: h, reason: collision with root package name */
        private int f17733h = 0;

        /* renamed from: i, reason: collision with root package name */
        private int f17734i = 0;

        /* renamed from: j, reason: collision with root package name */
        private int f17735j = 0;

        /* renamed from: k, reason: collision with root package name */
        private int f17736k = 0;

        /* renamed from: l, reason: collision with root package name */
        private int f17737l = 0;

        /* renamed from: m, reason: collision with root package name */
        private CircleStatus f17738m = CircleStatus.STARTING;

        /* renamed from: n, reason: collision with root package name */
        private CircleEvaluator f17739n;

        public SingleCircle(int i2) {
            this.f17726a = 0;
            this.f17726a = i2;
            this.f17739n = ProgressDrawable.this.new CircleEvaluator(i2);
        }

        public void a(Canvas canvas) {
            ProgressDrawable.this.mPaint.setAlpha(this.f17735j);
            ProgressDrawable.this.mPaint.setAntiAlias(true);
            int ordinal = this.f17738m.ordinal();
            if (ordinal == 0) {
                ProgressDrawable.this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
                if (this.f17726a < 5) {
                    ProgressDrawable.this.mPaint.setAlpha(this.f17737l);
                    ProgressDrawable.this.mMetaBall.a(canvas, this.f17730e, this.f17731f, this.f17734i, this.f17727b, this.f17728c, this.f17729d, ProgressDrawable.this.mPaint, 0.9f, 2.0f, (float) (ProgressDrawable.this.mProgressRadius * Math.sin(20.0d) * 2.0d));
                    canvas.drawCircle(this.f17727b, this.f17728c, this.f17729d, ProgressDrawable.this.mPaint);
                    if (ProgressDrawable.this.curEndX != this.f17732g || ProgressDrawable.this.curEndY != this.f17733h || ProgressDrawable.this.curStartEndRadius != this.f17734i) {
                        canvas.drawCircle(this.f17732g, this.f17733h, this.f17734i, ProgressDrawable.this.mPaint);
                        ProgressDrawable.this.curEndX = this.f17732g;
                        ProgressDrawable.this.curEndY = this.f17733h;
                        ProgressDrawable.this.curStartEndRadius = this.f17734i;
                    }
                } else {
                    ProgressDrawable.this.mPaint.setAlpha(this.f17737l);
                    canvas.drawCircle(this.f17727b, this.f17728c, this.f17734i, ProgressDrawable.this.mPaint);
                }
                ProgressDrawable.this.mPaint.setXfermode(null);
                return;
            }
            if (ordinal == 2) {
                canvas.drawCircle(this.f17727b, this.f17728c, this.f17729d, ProgressDrawable.this.mPaint);
                return;
            }
            if (ordinal == 3) {
                ProgressDrawable.this.mPaint.setAlpha(this.f17736k);
                canvas.drawCircle(this.f17727b, this.f17728c, this.f17729d, ProgressDrawable.this.mPaint);
                return;
            }
            if (ordinal != 4) {
                return;
            }
            ProgressDrawable.this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
            if (this.f17726a > 0) {
                ProgressDrawable.this.mPaint.setAlpha(this.f17737l);
                ProgressDrawable.this.mMetaBall.a(canvas, this.f17727b, this.f17728c, this.f17729d, this.f17732g, this.f17733h, this.f17734i, ProgressDrawable.this.mPaint, 0.9f, 2.0f, (float) (ProgressDrawable.this.mProgressRadius * Math.sin(20.0d) * 2.0d));
                canvas.drawCircle(this.f17727b, this.f17728c, this.f17729d, ProgressDrawable.this.mPaint);
                if (ProgressDrawable.this.curEndX != this.f17732g || ProgressDrawable.this.curEndY != this.f17733h || ProgressDrawable.this.curStartEndRadius != this.f17734i) {
                    canvas.drawCircle(this.f17732g, this.f17733h, this.f17734i, ProgressDrawable.this.mPaint);
                    ProgressDrawable.this.curEndX = this.f17732g;
                    ProgressDrawable.this.curEndY = this.f17733h;
                    ProgressDrawable.this.curStartEndRadius = this.f17734i;
                }
            } else {
                ProgressDrawable.this.mPaint.setAlpha(this.f17737l);
                canvas.drawCircle(this.f17727b, this.f17728c, this.f17734i, ProgressDrawable.this.mPaint);
            }
            ProgressDrawable.this.mPaint.setXfermode(null);
        }

        public void b(float f2) {
            float b2 = this.f17739n.b(f2);
            double d2 = (((ProgressDrawable.this.mProgressPoint + b2) % ProgressDrawable.CIRCLE_DISTANCE) * 3.141592653589793d) / 180.0d;
            this.f17727b = (int) (ProgressDrawable.this.mProgressX + (ProgressDrawable.this.mProgressRadius * Math.sin(d2)));
            this.f17728c = (int) (ProgressDrawable.this.mProgressY - (ProgressDrawable.this.mProgressRadius * Math.cos(d2)));
            this.f17729d = ProgressDrawable.this.mCircleRadiusRun;
            this.f17730e = (int) (ProgressDrawable.this.mProgressX + (ProgressDrawable.this.mProgressRadius * Math.sin((ProgressDrawable.this.mProgressPoint * 3.141592653589793d) / 180.0d)));
            this.f17731f = (int) (ProgressDrawable.this.mProgressY - (ProgressDrawable.this.mProgressRadius * Math.cos((ProgressDrawable.this.mProgressPoint * 3.141592653589793d) / 180.0d)));
            this.f17732g = (int) (ProgressDrawable.this.mProgressX + (ProgressDrawable.this.mProgressRadius * Math.sin(((ProgressDrawable.this.mProgressPoint + ProgressDrawable.CIRCLE_DISTANCE) * 3.141592653589793d) / 180.0d)));
            this.f17733h = (int) (ProgressDrawable.this.mProgressY - (ProgressDrawable.this.mProgressRadius * Math.cos(((ProgressDrawable.this.mProgressPoint + ProgressDrawable.CIRCLE_DISTANCE) * 3.141592653589793d) / 180.0d)));
            this.f17734i = (int) this.f17739n.e(f2);
            this.f17735j = (int) this.f17739n.a(f2);
            this.f17736k = (int) this.f17739n.c(f2);
            this.f17737l = (int) this.f17739n.d(f2);
            if (b2 <= 40.0f) {
                this.f17738m = CircleStatus.STARTING;
                return;
            }
            if (b2 >= 320.0f) {
                this.f17738m = CircleStatus.ENDING;
            } else if (b2 >= 280.0f) {
                this.f17738m = CircleStatus.PREEND;
            } else {
                this.f17738m = CircleStatus.RUNNING;
            }
        }
    }

    public ProgressDrawable() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-16777216);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(5.0f);
        for (int i2 = 0; i2 < 6; i2++) {
            this.mCircles.add(new SingleCircle(i2));
        }
    }

    private RectF clipSquare(Rect rect) {
        int min = Math.min(rect.width(), rect.height());
        int centerX = rect.centerX();
        int centerY = rect.centerY();
        int i2 = min / 2;
        return new RectF(centerX - i2, centerY - i2, centerX + i2, centerY + i2);
    }

    private void generateTimeAnimtor() {
        TimeAnimator timeAnimator = new TimeAnimator();
        this.mTimeAnimator = timeAnimator;
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() { // from class: com.zte.mifavor.widget.ProgressDrawable.1
            @Override // android.animation.TimeAnimator.TimeListener
            public void onTimeUpdate(TimeAnimator timeAnimator2, long j2, long j3) {
                ProgressDrawable progressDrawable = ProgressDrawable.this;
                progressDrawable.mProgressPoint = (progressDrawable.mProgressPoint + ((j3 * 120) / 1000.0f)) % ProgressDrawable.CIRCLE_DISTANCE;
                ProgressDrawable.this.updateCircle(new PathInterpolator(0.5f, 0.0f, 0.5f, 1.0f).getInterpolation(j2 / 2300.0f));
                if (j2 >= 2300) {
                    timeAnimator2.setCurrentPlayTime(0L);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCircle(float f2) {
        for (int i2 = 0; i2 < this.mCircles.size(); i2++) {
            this.mCircles.get(i2).b(f2);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int saveLayer = canvas.saveLayer(this.mRect, null, 31);
        for (int i2 = 0; i2 < this.mCircles.size(); i2++) {
            this.mCircles.get(i2).a(canvas);
        }
        canvas.restoreToCount(saveLayer);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 1;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        TimeAnimator timeAnimator = this.mTimeAnimator;
        return timeAnimator != null && timeAnimator.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Log.d(TAG, "onBoundsChange");
        stop();
        RectF clipSquare = clipSquare(rect);
        this.mRect = clipSquare;
        this.mProgressX = (int) clipSquare.centerX();
        this.mProgressY = (int) this.mRect.centerY();
        int width = (int) (((this.mRect.width() / 2.0f) * 4.0f) / 5.0f);
        this.mProgressRadius = width;
        this.mCircleRadiusRun = (int) ((width * 5.0f) / 32.0f);
        this.mCircleRadiusDelay = (int) (width / 4.0f);
        generateTimeAnimtor();
        if (this.mStarted) {
            start();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        this.mPaint.setColor(colorStateList.getDefaultColor());
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.mStarted = true;
        if (this.mTimeAnimator == null || isRunning()) {
            return;
        }
        this.mTimeAnimator.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.mStarted = false;
        if (this.mTimeAnimator == null || !isRunning()) {
            return;
        }
        this.mTimeAnimator.end();
    }
}
