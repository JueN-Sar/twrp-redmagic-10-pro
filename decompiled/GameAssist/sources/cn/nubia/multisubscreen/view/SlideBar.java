package cn.nubia.multisubscreen.view;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* loaded from: classes.dex */
public class SlideBar extends SeekBar {
    private boolean mIsShowTickMark;
    private Paint mPaint;
    private int mThumbW;
    private int mTickMarkSize;
    private int mTickMarkTrackW;
    private final Rect mTmpRect;

    public SlideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTmpRect = new Rect();
        c();
    }

    private void a(Canvas canvas, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        int save = canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        drawable2.draw(canvas);
        canvas.save();
        float max = (this.mTickMarkTrackW * 1.0f) / (getMax() - getMin());
        int i2 = (int) ((((this.mThumbW - this.mTickMarkSize) * 1.0f) / 2.0f) + 0.5f);
        canvas.translate((-getThumbOffset()) + i2, (drawable4.getBounds().height() - this.mTickMarkSize) / 2);
        for (int i3 = 0; i3 < (getMax() - getMin()) - 1; i3++) {
            canvas.translate(max, 0.0f);
            int i4 = this.mTickMarkSize;
            canvas.drawOval(0.0f, 0.0f, i4, i4, this.mPaint);
        }
        canvas.translate(((max - i2) + this.mThumbW) - this.mTickMarkSize, 0.0f);
        int i5 = this.mTickMarkSize;
        canvas.drawOval(0.0f, 0.0f, i5, i5, this.mPaint);
        canvas.restore();
        drawable3.draw(canvas);
        canvas.restoreToCount(save);
    }

    private void b(Canvas canvas, boolean z, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (!z) {
            a(canvas, drawable3, drawable, drawable2, drawable4);
            return;
        }
        Insets opticalInsets = drawable3.getOpticalInsets();
        Rect rect = this.mTmpRect;
        drawable3.copyBounds(rect);
        rect.offset(getPaddingLeft() - getThumbOffset(), getPaddingTop());
        rect.left += opticalInsets.left;
        rect.right -= opticalInsets.right;
        int save = canvas.save();
        canvas.clipRect(rect, Region.Op.DIFFERENCE);
        a(canvas, drawable3, drawable, drawable2, drawable4);
        canvas.restoreToCount(save);
    }

    private void c() {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setColor(-2565928);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    private boolean d(Canvas canvas) {
        Drawable progressDrawable = getProgressDrawable();
        if (!(progressDrawable instanceof LayerDrawable)) {
            return false;
        }
        LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
        Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
        Drawable findDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(R.id.progress);
        Drawable thumb = getThumb();
        if (findDrawableByLayerId == null || findDrawableByLayerId2 == null || thumb == null) {
            return false;
        }
        b(canvas, getSplitTrack(), findDrawableByLayerId, findDrawableByLayerId2, thumb, layerDrawable);
        int save = canvas.save();
        canvas.translate(getPaddingLeft() - getThumbOffset(), getPaddingTop());
        thumb.draw(canvas);
        canvas.restoreToCount(save);
        return true;
    }

    private void f(int i2) {
        if (i2 != 0 && this.mIsShowTickMark) {
            Drawable progressDrawable = getProgressDrawable();
            Drawable thumb = getThumb();
            if (progressDrawable == null || thumb == null) {
                this.mIsShowTickMark = false;
                return;
            }
            int intrinsicHeight = progressDrawable.getIntrinsicHeight();
            int intrinsicWidth = thumb.getIntrinsicWidth();
            this.mThumbW = intrinsicWidth;
            if (intrinsicHeight > intrinsicWidth) {
                intrinsicHeight = intrinsicWidth;
            }
            this.mTickMarkSize = intrinsicHeight;
            this.mTickMarkTrackW = (((i2 - getPaddingLeft()) - getPaddingRight()) + (getThumbOffset() * 2)) - this.mThumbW;
        }
    }

    public void e(boolean z) {
        if (isIndeterminate()) {
            this.mIsShowTickMark = false;
        } else {
            if (this.mIsShowTickMark == z) {
                return;
            }
            this.mIsShowTickMark = z;
            f(getWidth());
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        if (this.mIsShowTickMark && d(canvas)) {
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        f(i2);
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        this.mPaint.setAlpha((int) (f2 * 255.0f));
    }

    public SlideBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTmpRect = new Rect();
        c();
    }

    public SlideBar(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTmpRect = new Rect();
        c();
    }
}
