package cn.nubia.gameassist.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import cn.nubia.gameassist.R;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class StairSeekBar extends SeekBar {
    public static final int ALIGN_BOTTOM = 3;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 1;
    private final int mAlign;
    private float mDownX;
    private float mDownY;
    private boolean mIsMove;
    private float mNullWeight;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private final Paint mPaint;
    private Path mPath;
    private final int mStairCount;
    private final int mStairCoverColor;
    private final int mStairDefaultColor;
    private final float mStairRatio;
    private float mStairWeight;

    public StairSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean b(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(new int[2]);
        getLocationOnScreen(new int[2]);
        motionEvent.offsetLocation(r1[0] - r4[0], r1[1] - r4[1]);
        return onTouchEvent(motionEvent);
    }

    public void c(boolean z) {
        invalidate();
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onProgressChanged(this, getProgress(), z);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = (View) getParent();
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gameassist.view.c
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    boolean b2;
                    b2 = StairSeekBar.this.b(view2, motionEvent);
                    return b2;
                }
            });
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            int save = canvas.save();
            canvas.clipPath(this.mPath);
            int ceil = (int) Math.ceil(((getProgress() * 1.0f) * this.mStairCount) / getMax());
            int i2 = this.mAlign;
            int i3 = 0;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            canvas.restoreToCount(save);
                        }
                    }
                }
                float f2 = this.mStairWeight / 2.0f;
                while (i3 < this.mStairCount) {
                    if (i3 < ceil) {
                        this.mPaint.setColor(this.mStairCoverColor);
                    } else {
                        this.mPaint.setColor(this.mStairDefaultColor);
                    }
                    canvas.drawLine(f2, 0.0f, f2, getHeight(), this.mPaint);
                    f2 = f2 + this.mStairWeight + this.mNullWeight;
                    i3++;
                }
                canvas.restoreToCount(save);
            }
            float height = getHeight() - (this.mStairWeight / 2.0f);
            while (i3 < this.mStairCount) {
                if (i3 < ceil) {
                    this.mPaint.setColor(this.mStairCoverColor);
                } else {
                    this.mPaint.setColor(this.mStairDefaultColor);
                }
                canvas.drawLine(0.0f, height, getWidth(), height, this.mPaint);
                height = (height - this.mStairWeight) - this.mNullWeight;
                i3++;
            }
            canvas.restoreToCount(save);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        int i6 = this.mAlign;
        if (i6 == 0 || i6 == 2) {
            int i7 = this.mStairCount;
            float f2 = this.mStairRatio;
            float f3 = i3 / ((i7 - 1) + (i7 * f2));
            this.mNullWeight = f3;
            this.mStairWeight = f2 * f3;
        } else {
            int i8 = this.mStairCount;
            float f4 = this.mStairRatio;
            float f5 = i2 / ((i8 - 1) + (i8 * f4));
            this.mNullWeight = f5;
            this.mStairWeight = f4 * f5;
        }
        this.mPaint.setStrokeWidth(this.mStairWeight);
        Path path = new Path();
        int i9 = this.mAlign;
        if (i9 == 0) {
            path.moveTo(0.0f, 0.0f);
            float f6 = i2;
            path.lineTo(f6, 0.0f);
            float f7 = i3;
            path.lineTo(f6 * 0.16f, f7);
            path.lineTo(0.0f, f7);
            path.lineTo(0.0f, 0.0f);
        } else if (i9 == 1) {
            path.moveTo(0.0f, 0.0f);
            float f8 = i2;
            path.lineTo(f8, 0.0f);
            float f9 = i3;
            path.lineTo(f8, f9);
            path.lineTo(0.0f, f9 * 0.16f);
            path.lineTo(0.0f, 0.0f);
        } else if (i9 == 2) {
            path.moveTo(0.0f, 0.0f);
            float f10 = i2;
            path.lineTo(f10, 0.0f);
            float f11 = i3;
            path.lineTo(f10, f11);
            path.lineTo(f10 * 0.84000003f, f11);
            path.lineTo(0.0f, 0.0f);
        } else if (i9 == 3) {
            float f12 = i2;
            path.moveTo(f12, 0.0f);
            float f13 = i3;
            path.lineTo(f12, f13);
            path.lineTo(0.0f, f13);
            path.lineTo(0.0f, f13 * 0.84000003f);
            path.lineTo(f12, 0.0f);
        }
        this.mPath = path;
    }

    void onStartTrackingTouch() {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    void onStopTrackingTouch() {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        if (r4 != 6) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a6, code lost:
    
        if (r2 != 3) goto L67;
     */
    @Override // android.widget.AbsSeekBar, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.view.StairSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i2, boolean z) {
        super.setProgress(i2, z);
        c(isPressed());
    }

    public StairSeekBar(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public StairSeekBar(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StairSeekBar, i2, i2);
        this.mAlign = obtainStyledAttributes.getInt(R.styleable.StairSeekBar_align, -1);
        this.mStairCount = obtainStyledAttributes.getInt(R.styleable.StairSeekBar_stair_count, 15);
        this.mStairRatio = obtainStyledAttributes.getFloat(R.styleable.StairSeekBar_stair_line_ratio, 1.4f);
        this.mStairDefaultColor = obtainStyledAttributes.getColor(R.styleable.StairSeekBar_stair_default_color, -1722526636);
        this.mStairCoverColor = obtainStyledAttributes.getColor(R.styleable.StairSeekBar_stair_cover_color, -855638017);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }
}
