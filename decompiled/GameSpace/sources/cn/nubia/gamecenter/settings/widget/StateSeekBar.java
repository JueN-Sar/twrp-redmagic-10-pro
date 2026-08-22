package cn.nubia.gamecenter.settings.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class StateSeekBar extends View {
    private static final String TAG = "StateSeekBar";
    private int mBackGroundHeight;
    private int mBackGroundWidth;
    private OnProgressChangeListener mListener;
    private int mProgress;
    protected Bitmap mSeekBarBackground;
    protected Bitmap mSeekBarThumb;
    protected Bitmap mSeekBarTrack;
    private int mThumbHeight;
    private int mThumbWidth;
    private int mTrackHeight;
    private int mTrackWidth;
    protected NinePatchDrawable m_trackDrawable;

    public interface OnProgressChangeListener {
        void onProgressChanged(StateSeekBar stateSeekBar, int i);

        void onStartTrackingTouch(StateSeekBar stateSeekBar, int i);

        void onStopTrackingTouch(StateSeekBar stateSeekBar, int i);
    }

    public StateSeekBar(Context context) {
        super(context);
        this.mProgress = 0;
    }

    public StateSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgress = 0;
    }

    public StateSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgress = 0;
    }

    private void drawBackground(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        Rect rect = new Rect(0, 0, this.mBackGroundWidth, this.mBackGroundHeight);
        int i = this.mBackGroundHeight;
        rect.offset((width - this.mBackGroundWidth) / 2, height > i ? (height - i) / 2 : 0);
        canvas.drawBitmap(this.mSeekBarBackground, (Rect) null, rect, (Paint) null);
    }

    private void drawThumb(Canvas canvas) {
        int width = getWidth();
        Rect rect = new Rect(0, 0, this.mThumbWidth, this.mThumbHeight);
        rect.offset((this.mProgress * (width - this.mThumbWidth)) / 2, 0);
        canvas.drawBitmap(this.mSeekBarThumb, (Rect) null, rect, (Paint) null);
    }

    private void drawTrack(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int i = this.mTrackHeight;
        int i2 = height > i ? (height - i) / 2 : 0;
        int i3 = ((width - this.mBackGroundWidth) / 2) + ((this.mBackGroundHeight - i) / 2);
        int i4 = this.mProgress;
        int i5 = this.mThumbWidth;
        Rect rect = new Rect(i3, 0, ((i4 * (width - i5)) / 2) + (i5 / 2) + (i / 2), this.mTrackHeight);
        rect.offset(0, i2);
        this.m_trackDrawable.setBounds(rect);
        this.m_trackDrawable.draw(canvas);
    }

    private void init() {
        this.mSeekBarThumb = BitmapFactory.decodeResource(getResources(), R.drawable.gcs_seekbar_thumb);
        this.mSeekBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.gcs_seekbar_background);
        this.mSeekBarTrack = BitmapFactory.decodeResource(getResources(), R.drawable.gcs_seekbar_track);
        Resources resources = getResources();
        Bitmap bitmap = this.mSeekBarTrack;
        this.m_trackDrawable = new NinePatchDrawable(resources, bitmap, bitmap.getNinePatchChunk(), new Rect(), null);
        this.mBackGroundHeight = this.mSeekBarBackground.getHeight();
        this.mBackGroundWidth = this.mSeekBarBackground.getWidth();
        this.mThumbHeight = this.mSeekBarThumb.getHeight();
        this.mThumbWidth = this.mSeekBarThumb.getWidth();
        this.mTrackHeight = this.mSeekBarTrack.getHeight();
        this.mTrackWidth = this.mSeekBarTrack.getWidth();
    }

    private int touchXToPregress(float f) {
        int i = (this.mBackGroundWidth - this.mThumbWidth) / 4;
        int i2 = this.mProgress;
        if (f < (r1 / 2) + i) {
            return 0;
        }
        if (f >= (r0 - (r1 / 2)) - i) {
            return 2;
        }
        if (f < (r1 / 2) + i || f >= (r1 / 2) + (i * 3)) {
            return i2;
        }
        return 1;
    }

    public int getProgress() {
        return this.mProgress;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        Bitmap bitmap = this.mSeekBarThumb;
        if (bitmap != null) {
            bitmap.recycle();
        }
        Bitmap bitmap2 = this.mSeekBarTrack;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        Bitmap bitmap3 = this.mSeekBarBackground;
        if (bitmap3 != null) {
            bitmap3.recycle();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        if (isEnabled()) {
            drawTrack(canvas);
            drawThumb(canvas);
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            int i = touchXToPregress(motionEvent.getX());
            this.mProgress = i;
            OnProgressChangeListener onProgressChangeListener = this.mListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.onStartTrackingTouch(this, i);
            }
        } else if (action == 1) {
            OnProgressChangeListener onProgressChangeListener2 = this.mListener;
            if (onProgressChangeListener2 != null) {
                onProgressChangeListener2.onStopTrackingTouch(this, this.mProgress);
            }
            invalidate();
        } else if (action == 2) {
            int i2 = touchXToPregress(motionEvent.getX());
            this.mProgress = i2;
            OnProgressChangeListener onProgressChangeListener3 = this.mListener;
            if (onProgressChangeListener3 != null) {
                onProgressChangeListener3.onProgressChanged(this, i2);
            }
            invalidate();
        }
        return true;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.mListener = onProgressChangeListener;
    }

    public void setProgress(int i) {
        this.mProgress = i;
        invalidate();
    }
}
