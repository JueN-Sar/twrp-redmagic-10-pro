package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;

/* loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    private static final String TAG = "Layer";
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;
    protected float mComputedCenterX;
    protected float mComputedCenterY;
    protected float mComputedMaxX;
    protected float mComputedMaxY;
    protected float mComputedMinX;
    protected float mComputedMinY;
    ConstraintLayout mContainer;
    private float mGroupRotateAngle;
    boolean mNeedBounds;
    private float mRotationCenterX;
    private float mRotationCenterY;
    private float mScaleX;
    private float mScaleY;
    private float mShiftX;
    private float mShiftY;
    View[] mViews;

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    private void y() {
        int i2;
        if (this.mContainer == null || (i2 = this.mCount) == 0) {
            return;
        }
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != i2) {
            this.mViews = new View[i2];
        }
        for (int i3 = 0; i3 < this.mCount; i3++) {
            this.mViews[i3] = this.mContainer.q(this.mIds[i3]);
        }
    }

    private void z() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mViews == null) {
            y();
        }
        x();
        double radians = Float.isNaN(this.mGroupRotateAngle) ? 0.0d : Math.toRadians(this.mGroupRotateAngle);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f2 = this.mScaleX;
        float f3 = f2 * cos;
        float f4 = this.mScaleY;
        float f5 = (-f4) * sin;
        float f6 = f2 * sin;
        float f7 = f4 * cos;
        for (int i2 = 0; i2 < this.mCount; i2++) {
            View view = this.mViews[i2];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f8 = left - this.mComputedCenterX;
            float f9 = top - this.mComputedCenterY;
            float f10 = (((f3 * f8) + (f5 * f9)) - f8) + this.mShiftX;
            float f11 = (((f8 * f6) + (f7 * f9)) - f9) + this.mShiftY;
            view.setTranslationX(f10);
            view.setTranslationY(f11);
            view.setScaleY(this.mScaleY);
            view.setScaleX(this.mScaleX);
            if (!Float.isNaN(this.mGroupRotateAngle)) {
                view.setRotation(this.mGroupRotateAngle);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void j(ConstraintLayout constraintLayout) {
        i(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.mUseViewMeasure = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_android_visibility) {
                    this.mApplyVisibilityOnAttach = true;
                } else if (index == R.styleable.ConstraintLayout_Layout_android_elevation) {
                    this.mApplyElevationOnAttach = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout) getParent();
        if (this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i2 = 0; i2 < this.mCount; i2++) {
                View q2 = this.mContainer.q(this.mIds[i2]);
                if (q2 != null) {
                    if (this.mApplyVisibilityOnAttach) {
                        q2.setVisibility(visibility);
                    }
                    if (this.mApplyElevationOnAttach && elevation > 0.0f) {
                        q2.setTranslationZ(q2.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        y();
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        ConstraintWidget b2 = ((ConstraintLayout.LayoutParams) getLayoutParams()).b();
        b2.p1(0);
        b2.Q0(0);
        x();
        layout(((int) this.mComputedMinX) - getPaddingLeft(), ((int) this.mComputedMinY) - getPaddingTop(), ((int) this.mComputedMaxX) + getPaddingRight(), ((int) this.mComputedMaxY) + getPaddingBottom());
        z();
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        h();
    }

    @Override // android.view.View
    public void setPivotX(float f2) {
        this.mRotationCenterX = f2;
        z();
    }

    @Override // android.view.View
    public void setPivotY(float f2) {
        this.mRotationCenterY = f2;
        z();
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        this.mGroupRotateAngle = f2;
        z();
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        this.mScaleX = f2;
        z();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        this.mScaleY = f2;
        z();
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        this.mShiftX = f2;
        z();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        this.mShiftY = f2;
        z();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        h();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void t(ConstraintLayout constraintLayout) {
        this.mContainer = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.mGroupRotateAngle = rotation;
        } else {
            if (Float.isNaN(this.mGroupRotateAngle)) {
                return;
            }
            this.mGroupRotateAngle = rotation;
        }
    }

    protected void x() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mNeedBounds || Float.isNaN(this.mComputedCenterX) || Float.isNaN(this.mComputedCenterY)) {
            if (!Float.isNaN(this.mRotationCenterX) && !Float.isNaN(this.mRotationCenterY)) {
                this.mComputedCenterY = this.mRotationCenterY;
                this.mComputedCenterX = this.mRotationCenterX;
                return;
            }
            View[] n2 = n(this.mContainer);
            int left = n2[0].getLeft();
            int top = n2[0].getTop();
            int right = n2[0].getRight();
            int bottom = n2[0].getBottom();
            for (int i2 = 0; i2 < this.mCount; i2++) {
                View view = n2[i2];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.mComputedMaxX = right;
            this.mComputedMaxY = bottom;
            this.mComputedMinX = left;
            this.mComputedMinY = top;
            if (Float.isNaN(this.mRotationCenterX)) {
                this.mComputedCenterX = (left + right) / 2;
            } else {
                this.mComputedCenterX = this.mRotationCenterX;
            }
            if (Float.isNaN(this.mRotationCenterY)) {
                this.mComputedCenterY = (top + bottom) / 2;
            } else {
                this.mComputedCenterY = this.mRotationCenterY;
            }
        }
    }

    public Layer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }
}
