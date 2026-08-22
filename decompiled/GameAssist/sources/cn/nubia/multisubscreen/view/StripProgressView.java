package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class StripProgressView extends View {
    private Drawable mBgDrawable;
    private int mCount;
    private float mOverlapWidth;
    private Drawable mProgressDrawable;
    private int mProgressDrawableHeight;
    private int mProgressDrawableWidth;

    public StripProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        b(attributeSet, 0, 0);
    }

    private void a(Canvas canvas) {
        int i2;
        canvas.save();
        int i3 = 0;
        while (true) {
            i2 = this.mCount;
            if (i3 >= i2) {
                break;
            }
            this.mProgressDrawable.draw(canvas);
            canvas.translate(this.mProgressDrawableWidth - this.mOverlapWidth, 0.0f);
            i3++;
        }
        while (i2 < 10) {
            this.mBgDrawable.draw(canvas);
            canvas.translate(this.mProgressDrawableWidth - this.mOverlapWidth, 0.0f);
            i2++;
        }
        canvas.restore();
    }

    private void b(AttributeSet attributeSet, int i2, int i3) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.StripProgressView, i2, i3);
        try {
            this.mProgressDrawable = obtainStyledAttributes.getDrawable(R.styleable.StripProgressView_progress_strip);
            this.mBgDrawable = obtainStyledAttributes.getDrawable(R.styleable.StripProgressView_progress_bg);
            this.mOverlapWidth = obtainStyledAttributes.getDimension(R.styleable.StripProgressView_overlap_width, 0.0f);
            obtainStyledAttributes.recycle();
            Drawable drawable = this.mProgressDrawable;
            if (drawable != null) {
                this.mProgressDrawableWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = this.mProgressDrawable.getIntrinsicHeight();
                this.mProgressDrawableHeight = intrinsicHeight;
                this.mProgressDrawable.setBounds(0, 0, this.mProgressDrawableWidth, intrinsicHeight);
                Drawable drawable2 = this.mBgDrawable;
                if (drawable2 != null) {
                    drawable2.setBounds(0, 0, this.mProgressDrawableWidth, this.mProgressDrawableHeight);
                }
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void c(float f2, float f3) {
        if (f3 < 0.1f) {
            return;
        }
        if (f2 - f3 > 0.1f) {
            f2 = f3;
        }
        int i2 = (int) (((f2 * 10.0f) / f3) + 0.5f);
        if (this.mCount == i2) {
            return;
        }
        this.mCount = i2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.mProgressDrawableWidth;
        if (i4 > 0) {
            setMeasuredDimension((i4 * 10) - ((int) (this.mOverlapWidth * 9.0f)), this.mProgressDrawableHeight);
        } else {
            super.onMeasure(i2, i3);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
    }

    public StripProgressView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b(attributeSet, i2, 0);
    }

    public StripProgressView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        b(attributeSet, i2, i3);
    }
}
