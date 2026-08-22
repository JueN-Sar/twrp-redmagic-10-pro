package cn.nubia.gameassist.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.bright.IBrightListener;

/* loaded from: classes.dex */
public class NubiaSeekBar extends View implements IBrightListener {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_MAX_PROGRESS = 100;
    private static final int DEFAULT_MIN_PROGRESS = 0;
    private static final int DEFAULT_PROGRESS_BACKGROUND_COLOR = -10747904;
    private static final int DEFAULT_PROGRESS_COLOR = -65536;
    private static final int DEFAULT_PROGRESS_CORNER = 2;
    private static final int DEFAULT_PROGRESS_WIDTH = 2;
    private static final int DEFAULT_THUMB_COLOR = -65536;
    private static final int DEFAULT_THUMB_RADIUS = 18;
    private static final boolean DEFAULT_THUMB_SCALE_ENABLE = false;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "NubiaSeekBar";
    private static final int THUMB_ANIM_CANCEL_SPACE = 500;
    public static final int VERTICAL = 1;
    private CompoundButton mBox;
    private Rect mBrightnessDrawableRect;
    private Drawable mBrightnessDrawble;
    private int mBrightnessOffset;
    private int mCurrProgress;
    private float mDownPos;
    private boolean mEnable;
    private boolean mEnableClickChange;
    private boolean mEnableTrack;
    private boolean mIsClickOnProgress;
    private boolean mIsDrag;
    private float mLastPos;
    private int mLastProgress;
    private OnSeekBarChangeListener mListener;
    private int mMaxProgress;
    private float mMaxThumbScale;
    private int mMinProgress;
    private int mNormalProgressColor;
    private int mOrientation;
    private int mProgressBackgroundColor;
    private Rect mProgressBackgroundRect;
    private Drawable mProgressBgDrawable;
    private int mProgressColor;
    private int mProgressCorner;
    private Drawable mProgressDrawable;
    private Paint mProgressPaint;
    private Path mProgressPath;
    private Rect mProgressRect;
    private RectF mProgressRectF;
    private Path mProgressRectPath;
    private int mProgressWidth;
    private int mTextColor;
    private Paint mTextPaint;
    private Rect mThumbDestRect;
    private Drawable mThumbDrawable;
    private float mThumbMoveLength;
    private Paint mThumbPaint;
    private int mThumbRadius;
    private float mThumbScale;
    private boolean mThumbScaleEnable;
    private int mTouchOffset;
    private float mTouchSlop;
    private float mTrackMoveLength;
    private int mTrackOffset;
    private ValueAnimator mValueAnimator;
    private int mViewHeight;
    private int mViewWidth;
    private int mWarnProgressColor;
    private Runnable thumbAnimRunnable;
    FocusTextView tipView;

    public interface OnSeekBarChangeListener {
        void a(int i2, boolean z, boolean z2, boolean z3);

        void b(boolean z);

        default void c(boolean z) {
        }

        void d(boolean z);
    }

    public NubiaSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        float f2 = this.mThumbScale;
        float f3 = z ? this.mMaxThumbScale : 1.0f;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.mValueAnimator = valueAnimator2;
            valueAnimator2.setDuration(200L);
            this.mValueAnimator.setInterpolator(new DecelerateInterpolator());
            this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.view.NubiaSeekBar.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    NubiaSeekBar.this.mThumbScale = ((Float) valueAnimator3.getAnimatedValue()).floatValue();
                    NubiaSeekBar.this.invalidate();
                }
            });
        } else {
            valueAnimator.cancel();
        }
        this.mValueAnimator.setFloatValues(f2, f3);
        this.mValueAnimator.start();
    }

    private boolean f(float f2, float f3) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.mOrientation == 0) {
            Rect rect = this.mProgressBackgroundRect;
            if (rect.left >= rect.right || (i4 = rect.top) >= (i5 = rect.bottom)) {
                return false;
            }
            int i6 = this.mThumbRadius;
            int i7 = this.mTrackOffset;
            if (f2 < (r3 - i6) + i7 || f2 > r4 + i6 + i7) {
                return false;
            }
            int i8 = this.mTouchOffset;
            return f3 >= ((float) (i4 - i8)) && f3 <= ((float) (i5 + i8));
        }
        Rect rect2 = this.mProgressBackgroundRect;
        if (rect2.left >= rect2.right || (i2 = rect2.top) >= (i3 = rect2.bottom)) {
            return false;
        }
        int i9 = this.mTouchOffset;
        if (f2 < r3 - i9 || f2 > r4 + i9) {
            return false;
        }
        int i10 = this.mThumbRadius;
        int i11 = this.mTrackOffset;
        return f3 >= ((float) ((i2 - i10) + i11)) && f3 <= ((float) ((i3 + i10) + i11));
    }

    private int g(float f2) {
        int round;
        int i2;
        float f3 = this.mThumbMoveLength;
        if (f2 > f3 / 2.0f) {
            return this.mOrientation == 0 ? this.mMaxProgress : this.mMinProgress;
        }
        if (f2 < (-f3) / 2.0f) {
            return this.mOrientation == 0 ? this.mMinProgress : this.mMaxProgress;
        }
        if (this.mOrientation == 0) {
            round = Math.round(((f2 + (f3 / 2.0f)) * (this.mMaxProgress - this.mMinProgress)) / f3);
            i2 = this.mMinProgress;
        } else {
            round = Math.round((((f3 / 2.0f) - f2) * (this.mMaxProgress - this.mMinProgress)) / f3);
            i2 = this.mMinProgress;
        }
        return round + i2;
    }

    private void h(boolean z) {
        if (this.mThumbScaleEnable) {
            if (z) {
                removeCallbacks(this.thumbAnimRunnable);
                e(true);
            } else {
                removeCallbacks(this.thumbAnimRunnable);
                postDelayed(this.thumbAnimRunnable, 500L);
            }
        }
    }

    private int i(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void j(Canvas canvas) {
        canvas.save();
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            drawable.setBounds(this.mProgressRect);
            this.mProgressDrawable.draw(canvas);
        } else {
            if (u() >= 0.9d) {
                this.mProgressColor = this.mWarnProgressColor;
            } else {
                this.mProgressColor = this.mNormalProgressColor;
            }
            this.mProgressPaint.setColor(this.mProgressColor);
            this.mProgressPath.reset();
            this.mProgressRectPath.reset();
            this.mProgressRectF.set(this.mProgressRect);
            Path path = this.mProgressRectPath;
            RectF rectF = this.mProgressRectF;
            Path.Direction direction = Path.Direction.CW;
            path.addRect(rectF, direction);
            RectF rectF2 = this.mProgressRectF;
            Rect rect = this.mProgressBackgroundRect;
            rectF2.set(rect.left, rect.top, rect.right, rect.bottom);
            Path path2 = this.mProgressPath;
            RectF rectF3 = this.mProgressRectF;
            int i2 = this.mProgressCorner;
            path2.addRoundRect(rectF3, i2, i2, direction);
            this.mProgressPath.op(this.mProgressRectPath, Path.Op.INTERSECT);
            canvas.drawPath(this.mProgressPath, this.mProgressPaint);
        }
        canvas.restore();
    }

    private void k(Canvas canvas) {
        canvas.save();
        Drawable drawable = this.mProgressBgDrawable;
        if (drawable != null) {
            drawable.setBounds(this.mProgressBackgroundRect);
            this.mProgressBgDrawable.draw(canvas);
        } else {
            this.mProgressPaint.setColor(this.mProgressBackgroundColor);
            Rect rect = this.mProgressBackgroundRect;
            float f2 = rect.left;
            float f3 = rect.top;
            float f4 = rect.right;
            float f5 = rect.bottom;
            int i2 = this.mProgressCorner;
            canvas.drawRoundRect(f2, f3, f4, f5, i2, i2, this.mProgressPaint);
        }
        canvas.restore();
    }

    private void l(Canvas canvas) {
        if (u() < 0.9d) {
            FocusTextView focusTextView = this.tipView;
            if (focusTextView == null || focusTextView.getVisibility() != 0) {
                return;
            }
            this.tipView.setVisibility(8);
            return;
        }
        if (this.mIsDrag) {
            canvas.save();
            canvas.translate((-this.mViewWidth) / 2, (-this.mViewHeight) / 2);
            String[] split = getContext().getResources().getString(R.string.game_progress_warn_text).split("");
            if (1 == this.mOrientation) {
                for (int i2 = 0; i2 < split.length; i2++) {
                    String string = getContext().getResources().getString(R.string.game_progress_warn_text);
                    if (p(string)) {
                        canvas.drawText(split[i2], canvas.getWidth() / 2, ((canvas.getHeight() / 2) - 61) + ((canvas.getHeight() / 9) * i2), this.mTextPaint);
                    } else if (this.tipView.getVisibility() == 8) {
                        this.tipView.setTip(string);
                        this.tipView.setVisibility(0);
                    }
                }
            } else {
                canvas.drawText(getContext().getResources().getString(R.string.game_progress_warn_text), canvas.getWidth() / 2, (canvas.getHeight() / 3) * 2, this.mTextPaint);
            }
            canvas.restore();
        }
    }

    private void m(Canvas canvas) {
        canvas.save();
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.setBounds(this.mThumbDestRect);
            this.mThumbDrawable.draw(canvas);
        } else {
            this.mThumbPaint.setColor(-65536);
            canvas.drawCircle(this.mThumbDestRect.centerX(), this.mThumbDestRect.centerY(), (this.mThumbDestRect.width() * this.mThumbScale) / 2.0f, this.mThumbPaint);
        }
        Drawable drawable2 = this.mBrightnessDrawble;
        if (drawable2 != null) {
            drawable2.setBounds(this.mBrightnessDrawableRect);
            this.mBrightnessDrawble.draw(canvas);
        }
        canvas.restore();
    }

    private void n(Context context, AttributeSet attributeSet) {
        int intrinsicWidth;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NubiaSeekBar);
            this.mProgressWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NubiaSeekBar_progress_height, i(context, 2.0f));
            this.mTouchOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.NubiaSeekBar_touch_offset, 0);
            this.mTrackOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.NubiaSeekBar_track_offset, 0);
            this.mBrightnessOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.NubiaSeekBar_brightness_drawable_offset, 0);
            this.mProgressCorner = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NubiaSeekBar_progress_corner, i(context, 2.0f));
            this.mThumbDrawable = obtainStyledAttributes.getDrawable(R.styleable.NubiaSeekBar_thumb);
            this.mBrightnessDrawble = obtainStyledAttributes.getDrawable(R.styleable.NubiaSeekBar_brightness_drawable);
            this.mProgressDrawable = obtainStyledAttributes.getDrawable(R.styleable.NubiaSeekBar_progress_drawable);
            this.mProgressBgDrawable = obtainStyledAttributes.getDrawable(R.styleable.NubiaSeekBar_progress_background_drawable);
            this.mProgressBackgroundColor = obtainStyledAttributes.getColor(R.styleable.NubiaSeekBar_background_color, DEFAULT_PROGRESS_BACKGROUND_COLOR);
            int color = obtainStyledAttributes.getColor(R.styleable.NubiaSeekBar_progress_color, -65536);
            this.mNormalProgressColor = color;
            this.mProgressColor = color;
            this.mWarnProgressColor = obtainStyledAttributes.getColor(R.styleable.NubiaSeekBar_progress_warn_color, -65536);
            this.mTextColor = obtainStyledAttributes.getColor(R.styleable.NubiaSeekBar_text_color, -65536);
            this.mMinProgress = obtainStyledAttributes.getInt(R.styleable.NubiaSeekBar_min_value, 0);
            this.mMaxProgress = obtainStyledAttributes.getInt(R.styleable.NubiaSeekBar_max_value, 100);
            this.mThumbScaleEnable = obtainStyledAttributes.getBoolean(R.styleable.NubiaSeekBar_thumb_scale_enable, false);
            this.mEnableTrack = obtainStyledAttributes.getBoolean(R.styleable.NubiaSeekBar_enable_track, true);
            int i2 = obtainStyledAttributes.getInt(R.styleable.NubiaSeekBar_seekbar_orientation, 0);
            this.mOrientation = i2;
            Drawable drawable = this.mThumbDrawable;
            if (drawable == null) {
                intrinsicWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NubiaSeekBar_thumb_radius, i(context, 18.0f));
            } else {
                intrinsicWidth = (i2 == 0 ? drawable.getIntrinsicWidth() : drawable.getIntrinsicHeight()) / 2;
            }
            this.mThumbRadius = intrinsicWidth;
            obtainStyledAttributes.recycle();
        }
        Paint paint = new Paint();
        this.mThumbPaint = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.mThumbPaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mProgressPaint = paint2;
        paint2.setStyle(style);
        this.mProgressPaint.setAntiAlias(true);
        this.mProgressPath = new Path();
        this.mProgressRectPath = new Path();
        this.mProgressBackgroundRect = new Rect();
        this.mProgressRect = new Rect();
        this.mProgressRectF = new RectF();
        this.mThumbDestRect = new Rect();
        this.mBrightnessDrawableRect = new Rect();
        this.mCurrProgress = this.mMinProgress;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        Paint paint3 = new Paint();
        this.mTextPaint = paint3;
        paint3.setColor(this.mTextColor);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setTextSize(30.0f);
    }

    private void o(int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        if (this.mOrientation == 0) {
            if (this.mThumbScaleEnable) {
                this.mThumbMoveLength = i2 - ((this.mThumbRadius * 2) * this.mMaxThumbScale);
            } else {
                this.mThumbMoveLength = i2 - (this.mThumbRadius * 2);
            }
            Rect rect = this.mProgressBackgroundRect;
            int i4 = (-this.mProgressWidth) / 2;
            rect.top = i4;
            rect.bottom = -i4;
            int i5 = (int) (((-this.mThumbMoveLength) / 2.0f) - this.mTrackOffset);
            rect.left = i5;
            rect.right = -i5;
            this.mTrackMoveLength = rect.width();
            Rect rect2 = this.mProgressRect;
            int i6 = (-this.mProgressWidth) / 2;
            rect2.top = i6;
            rect2.bottom = -i6;
            int i7 = (int) (((-this.mThumbMoveLength) / 2.0f) - this.mTrackOffset);
            rect2.left = i7;
            rect2.right = i7;
            Rect rect3 = this.mThumbDestRect;
            Drawable drawable = this.mThumbDrawable;
            rect3.top = drawable == null ? -this.mThumbRadius : (-drawable.getIntrinsicHeight()) / 2;
            Rect rect4 = this.mThumbDestRect;
            Drawable drawable2 = this.mThumbDrawable;
            rect4.bottom = drawable2 == null ? this.mThumbRadius : drawable2.getIntrinsicHeight() / 2;
            Rect rect5 = this.mThumbDestRect;
            float f2 = (-this.mThumbMoveLength) / 2.0f;
            int i8 = this.mThumbRadius;
            int i9 = (int) (f2 - i8);
            rect5.left = i9;
            rect5.right = i9 + (i8 * 2);
            Drawable drawable3 = this.mBrightnessDrawble;
            if (drawable3 != null) {
                Rect rect6 = this.mBrightnessDrawableRect;
                int i10 = this.mProgressBackgroundRect.left + this.mBrightnessOffset;
                rect6.left = i10;
                rect6.right = i10 + drawable3.getIntrinsicWidth();
                this.mBrightnessDrawableRect.top = (-this.mBrightnessDrawble.getIntrinsicHeight()) / 2;
                this.mBrightnessDrawableRect.bottom = this.mBrightnessDrawble.getIntrinsicHeight() / 2;
            }
        } else {
            if (this.mThumbScaleEnable) {
                this.mThumbMoveLength = i3 - ((this.mThumbRadius * 2) * this.mMaxThumbScale);
            } else {
                this.mThumbMoveLength = i3 - (this.mThumbRadius * 2);
            }
            Rect rect7 = this.mProgressBackgroundRect;
            int i11 = (int) (((-this.mThumbMoveLength) / 2.0f) - this.mTrackOffset);
            rect7.top = i11;
            rect7.bottom = -i11;
            int i12 = this.mProgressWidth;
            rect7.left = (-i12) / 2;
            rect7.right = i12 / 2;
            this.mTrackMoveLength = rect7.height();
            Rect rect8 = this.mProgressRect;
            float f3 = this.mThumbMoveLength;
            int i13 = (int) ((f3 / 2.0f) + this.mTrackOffset);
            rect8.top = i13;
            rect8.bottom = i13;
            int i14 = this.mProgressWidth;
            rect8.left = (-i14) / 2;
            rect8.right = i14 / 2;
            Rect rect9 = this.mThumbDestRect;
            int i15 = this.mThumbRadius;
            int i16 = (int) (((-f3) / 2.0f) - i15);
            rect9.top = i16;
            rect9.bottom = i16 + (i15 * 2);
            Drawable drawable4 = this.mThumbDrawable;
            rect9.left = drawable4 == null ? -i15 : (-drawable4.getIntrinsicHeight()) / 2;
            Rect rect10 = this.mThumbDestRect;
            Drawable drawable5 = this.mThumbDrawable;
            rect10.right = drawable5 == null ? this.mThumbRadius : drawable5.getIntrinsicHeight() / 2;
            Drawable drawable6 = this.mBrightnessDrawble;
            if (drawable6 != null) {
                this.mBrightnessDrawableRect.left = (-drawable6.getIntrinsicWidth()) / 2;
                this.mBrightnessDrawableRect.right = this.mBrightnessDrawble.getIntrinsicWidth() / 2;
                Rect rect11 = this.mBrightnessDrawableRect;
                int i17 = this.mProgressBackgroundRect.top + this.mBrightnessOffset;
                rect11.top = i17;
                rect11.bottom = i17 + this.mBrightnessDrawble.getIntrinsicWidth();
            }
        }
        setProgress(this.mCurrProgress);
    }

    private static boolean p(String str) {
        return str.matches("[\\u4e00-\\u9fa5]+");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(boolean z) {
        this.mBox.setChecked(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void s(float r6) {
        /*
            r5 = this;
            float r0 = r5.mThumbMoveLength
            r1 = 1073741824(0x40000000, float:2.0)
            float r1 = r0 / r1
            int r2 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r2 <= 0) goto L17
            int r6 = r5.mOrientation
            if (r6 != 0) goto L11
            int r6 = r5.mMaxProgress
            goto L13
        L11:
            int r6 = r5.mMinProgress
        L13:
            r5.mCurrProgress = r6
        L15:
            r6 = r1
            goto L28
        L17:
            float r1 = -r1
            int r2 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r2 >= 0) goto L28
            int r6 = r5.mOrientation
            if (r6 != 0) goto L23
            int r6 = r5.mMinProgress
            goto L25
        L23:
            int r6 = r5.mMaxProgress
        L25:
            r5.mCurrProgress = r6
            goto L15
        L28:
            int r1 = r5.mOrientation
            if (r1 != 0) goto L42
            android.graphics.Rect r1 = r5.mThumbDestRect
            int r2 = (int) r6
            int r3 = r5.mThumbRadius
            int r4 = r2 - r3
            r1.left = r4
            int r2 = r2 + r3
            r1.right = r2
            android.graphics.Rect r1 = r5.mProgressRect
            float r2 = r5.mTrackMoveLength
            float r6 = r6 * r2
            float r6 = r6 / r0
            int r6 = (int) r6
            r1.right = r6
            goto L57
        L42:
            android.graphics.Rect r1 = r5.mThumbDestRect
            int r2 = (int) r6
            int r3 = r5.mThumbRadius
            int r4 = r2 - r3
            r1.top = r4
            int r2 = r2 + r3
            r1.bottom = r2
            android.graphics.Rect r1 = r5.mProgressRect
            float r2 = r5.mTrackMoveLength
            float r6 = r6 * r2
            float r6 = r6 / r0
            int r6 = (int) r6
            r1.top = r6
        L57:
            r5.postInvalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.view.NubiaSeekBar.s(float):void");
    }

    private void setCoord(float f2) {
        this.mCurrProgress = g(f2);
        s(f2);
        OnSeekBarChangeListener onSeekBarChangeListener = this.mListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.a(this.mCurrProgress, this.mBox.isChecked(), this.mIsDrag, true);
        }
        this.mLastProgress = this.mCurrProgress;
    }

    private float t(int i2) {
        int i3 = this.mMinProgress;
        if (i2 <= i3) {
            i2 = i3;
        } else {
            int i4 = this.mMaxProgress;
            if (i2 >= i4) {
                i2 = i4;
            }
        }
        if (this.mOrientation == 0) {
            float f2 = this.mThumbMoveLength;
            return (((i2 - i3) * f2) / (this.mMaxProgress - i3)) - (f2 / 2.0f);
        }
        float f3 = this.mThumbMoveLength;
        return (f3 / 2.0f) - ((f3 * (i2 - i3)) / (this.mMaxProgress - i3));
    }

    private float u() {
        return this.mCurrProgress / this.mMaxProgress;
    }

    private void v(int i2, boolean z) {
        if (this.mEnable && q()) {
            this.mCurrProgress = i2;
            s(t(i2));
            OnSeekBarChangeListener onSeekBarChangeListener = this.mListener;
            if (onSeekBarChangeListener != null) {
                int i3 = this.mLastProgress;
                int i4 = this.mCurrProgress;
                if (i3 != i4) {
                    onSeekBarChangeListener.a(i4, this.mBox.isChecked(), this.mIsDrag, z);
                }
                this.mLastProgress = this.mCurrProgress;
            }
        }
    }

    public int getLastProgress() {
        return this.mLastProgress;
    }

    public int getProgress() {
        return this.mCurrProgress;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(this.mViewWidth / 2, this.mViewHeight / 2);
        k(canvas);
        j(canvas);
        l(canvas);
        m(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        Drawable drawable = this.mThumbDrawable;
        int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 18;
        if (mode == 1073741824) {
            this.mViewWidth = size;
        } else {
            this.mViewWidth = View.getDefaultSize(getSuggestedMinimumWidth(), i2);
        }
        if (mode2 == 1073741824) {
            this.mViewHeight = size2;
        } else {
            this.mViewHeight = Math.max(intrinsicHeight, View.getDefaultSize(getSuggestedMinimumHeight(), i3));
        }
        o(this.mViewWidth, this.mViewHeight);
        setMeasuredDimension(this.mViewWidth, this.mViewHeight);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r5 != 6) goto L61;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00d9  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.view.NubiaSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean q() {
        return !this.mIsClickOnProgress;
    }

    public void setCompoundButton(CompoundButton compoundButton) {
        this.mBox = compoundButton;
        compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cn.nubia.gameassist.view.NubiaSeekBar.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton2, boolean z) {
                if (NubiaSeekBar.this.mListener != null) {
                    NubiaSeekBar.this.mListener.c(z);
                }
            }
        });
    }

    public void setLightChecked(final boolean z) {
        CompoundButton compoundButton = this.mBox;
        if (compoundButton == null) {
            return;
        }
        compoundButton.post(new Runnable() { // from class: cn.nubia.gameassist.view.a
            @Override // java.lang.Runnable
            public final void run() {
                NubiaSeekBar.this.r(z);
            }
        });
    }

    public void setLightMax(int i2) {
        setMaxProgress(i2);
    }

    public void setLightValue(int i2) {
        if (this.mIsDrag) {
            return;
        }
        setProgress(i2);
    }

    public void setMaxProgress(int i2) {
        this.mMaxProgress = i2;
    }

    public void setMinProgress(int i2) {
        this.mMinProgress = i2;
        if (this.mCurrProgress < i2) {
            this.mCurrProgress = i2;
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mListener = onSeekBarChangeListener;
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
        requestLayout();
    }

    public void setProgress(int i2) {
        v(i2, false);
    }

    public void setProgressBackgroundColor(int i2) {
        this.mProgressBackgroundColor = i2;
    }

    public void setProgressBgDrawable(Drawable drawable) {
        this.mProgressBgDrawable = drawable;
    }

    public void setProgressColor(int i2) {
        this.mProgressColor = i2;
    }

    public void setProgressDrawable(Drawable drawable) {
        this.mProgressDrawable = drawable;
    }

    public void setProgressEnable(boolean z) {
        this.mEnable = z;
    }

    public void setThumbDrawable(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        this.mThumbDrawable = drawable;
        if (this.mOrientation == 0) {
            this.mThumbRadius = drawable.getIntrinsicWidth() / 2;
            this.mThumbDestRect.top = (-this.mThumbDrawable.getIntrinsicHeight()) / 2;
            this.mThumbDestRect.bottom = this.mThumbDrawable.getIntrinsicHeight() / 2;
            Rect rect = this.mThumbDestRect;
            float f2 = (-this.mThumbMoveLength) / 2.0f;
            int i2 = this.mThumbRadius;
            int i3 = (int) (f2 - i2);
            rect.left = i3;
            rect.right = i3 + (i2 * 2);
        } else {
            int intrinsicHeight = drawable.getIntrinsicHeight() / 2;
            this.mThumbRadius = intrinsicHeight;
            Rect rect2 = this.mThumbDestRect;
            int i4 = (int) (((-this.mThumbMoveLength) / 2.0f) - intrinsicHeight);
            rect2.top = i4;
            rect2.bottom = i4 + (intrinsicHeight * 2);
            rect2.left = (-this.mThumbDrawable.getIntrinsicHeight()) / 2;
            this.mThumbDestRect.right = this.mThumbDrawable.getIntrinsicHeight() / 2;
        }
        setProgress(this.mCurrProgress);
    }

    public NubiaSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOrientation = 0;
        this.mProgressBackgroundColor = DEFAULT_PROGRESS_BACKGROUND_COLOR;
        this.mProgressColor = -65536;
        this.mThumbRadius = 18;
        this.mMinProgress = 0;
        this.mMaxProgress = 100;
        this.mProgressCorner = 2;
        this.mProgressWidth = 2;
        this.mNormalProgressColor = DEFAULT_PROGRESS_BACKGROUND_COLOR;
        this.mWarnProgressColor = DEFAULT_PROGRESS_BACKGROUND_COLOR;
        this.mTextColor = DEFAULT_PROGRESS_BACKGROUND_COLOR;
        this.mThumbDrawable = null;
        this.mBrightnessDrawble = null;
        this.mIsClickOnProgress = false;
        this.mEnableClickChange = false;
        this.mEnable = true;
        this.mEnableTrack = true;
        this.mThumbScaleEnable = false;
        this.mMaxThumbScale = 1.4f;
        this.mThumbScale = 1.0f;
        this.thumbAnimRunnable = new Runnable() { // from class: cn.nubia.gameassist.view.NubiaSeekBar.2
            @Override // java.lang.Runnable
            public void run() {
                NubiaSeekBar.this.e(false);
            }
        };
        n(context, attributeSet);
    }
}
