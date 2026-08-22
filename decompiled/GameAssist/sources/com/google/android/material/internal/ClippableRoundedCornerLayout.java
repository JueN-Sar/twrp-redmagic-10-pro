package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class ClippableRoundedCornerLayout extends FrameLayout {
    private float cornerRadius;

    @Nullable
    private Path path;

    public ClippableRoundedCornerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.path = null;
        this.cornerRadius = 0.0f;
        invalidate();
    }

    public void b(float f2, float f3, float f4, float f5, float f6) {
        d(new RectF(f2, f3, f4, f5), f6);
    }

    public void c(Rect rect, float f2) {
        b(rect.left, rect.top, rect.right, rect.bottom, f2);
    }

    public void d(RectF rectF, float f2) {
        if (this.path == null) {
            this.path = new Path();
        }
        this.cornerRadius = f2;
        this.path.reset();
        this.path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        this.path.close();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.path == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    public void e(float f2) {
        b(getLeft(), getTop(), getRight(), getBottom(), f2);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public ClippableRoundedCornerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
