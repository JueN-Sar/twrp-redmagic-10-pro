package cn.nubia.gameassist.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class DiplogenFrameLayout extends FrameLayout {
    private int mBottomInvagination;
    private int mButtonIndex;
    private int mCenterY;
    private Path mInerPath;
    private int mLeftInvagination;
    private int mRightInvagination;
    private boolean mSlopeLeft;
    private int mSlopeWidth;
    private int mTopInvagination;

    public DiplogenFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int i2 = this.mButtonIndex;
        if (i2 < 0) {
            i2 = 0;
        }
        View childAt = viewGroup.getChildAt(i2);
        if (childAt != null) {
            int left = childAt.getLeft() + this.mLeftInvagination;
            int top = childAt.getTop() + this.mTopInvagination;
            int right = childAt.getRight() - this.mRightInvagination;
            int bottom = childAt.getBottom() - this.mBottomInvagination;
            int i3 = this.mCenterY + top;
            int i4 = this.mSlopeWidth;
            if (i4 == 0) {
                i4 = (bottom - top) / 4;
            }
            Path path = new Path();
            float f2 = bottom;
            path.moveTo(0.0f, f2);
            if (this.mSlopeLeft) {
                path.lineTo(left - i4, f2);
                path.lineTo(left, i3);
                path.lineTo(left + i4, f2);
            } else {
                path.lineTo(right - i4, f2);
                path.lineTo(right, i3);
                path.lineTo(right + i4, f2);
            }
            path.lineTo(getWidth(), f2);
            path.lineTo(getWidth(), getHeight());
            path.lineTo(0.0f, getHeight());
            path.close();
            this.mInerPath = path;
            postInvalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.mInerPath == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.mInerPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        a();
    }

    public DiplogenFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public DiplogenFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DiplogenFrameLayout, i2, i2);
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_button_index))) {
            this.mButtonIndex = obtainStyledAttributes.getInt(R.styleable.DiplogenFrameLayout_button_index, 0);
        } else {
            this.mButtonIndex = -1;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_slope))) {
            this.mSlopeLeft = obtainStyledAttributes.getInt(R.styleable.DiplogenFrameLayout_slope, 0) == 0;
        } else {
            this.mSlopeLeft = true;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_slope_width))) {
            this.mSlopeWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DiplogenFrameLayout_slope_width, 0);
        } else {
            this.mSlopeWidth = -1;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_left_invagination))) {
            this.mLeftInvagination = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DiplogenFrameLayout_left_invagination, 0);
        } else {
            this.mLeftInvagination = 0;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_right_invagination))) {
            this.mRightInvagination = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DiplogenFrameLayout_right_invagination, 0);
        } else {
            this.mRightInvagination = 0;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_top_invagination))) {
            this.mTopInvagination = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DiplogenFrameLayout_top_invagination, 0);
        } else {
            this.mTopInvagination = 0;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_bottom_invagination))) {
            this.mBottomInvagination = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DiplogenFrameLayout_bottom_invagination, 0);
        } else {
            this.mBottomInvagination = 0;
        }
        if (obtainStyledAttributes.hasValue(obtainStyledAttributes.getIndex(R.styleable.DiplogenFrameLayout_center_y))) {
            this.mCenterY = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DiplogenFrameLayout_center_y, 0);
        } else {
            this.mCenterY = 0;
        }
        obtainStyledAttributes.recycle();
    }
}
