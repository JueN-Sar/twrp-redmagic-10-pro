package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes.dex */
public class Constraints extends ViewGroup {
    public static final String TAG = "Constraints";
    ConstraintSet mConstraintSet;

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
        super.setVisibility(8);
    }

    private void c() {
        Log.v(TAG, " ################# init");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public ConstraintSet getConstraintSet() {
        if (this.mConstraintSet == null) {
            this.mConstraintSet = new ConstraintSet();
        }
        this.mConstraintSet.q(this);
        return this.mConstraintSet;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.LayoutParams(layoutParams);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        c();
        super.setVisibility(8);
    }

    public static class LayoutParams extends ConstraintLayout.LayoutParams {
        public float A0;
        public float B0;
        public float C0;
        public float D0;
        public float E0;
        public float F0;
        public float G0;
        public float H0;
        public float I0;
        public float J0;
        public float x0;
        public boolean y0;
        public float z0;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.x0 = 1.0f;
            this.y0 = false;
            this.z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            this.C0 = 0.0f;
            this.D0 = 1.0f;
            this.E0 = 1.0f;
            this.F0 = 0.0f;
            this.G0 = 0.0f;
            this.H0 = 0.0f;
            this.I0 = 0.0f;
            this.J0 = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.x0 = 1.0f;
            this.y0 = false;
            this.z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            this.C0 = 0.0f;
            this.D0 = 1.0f;
            this.E0 = 1.0f;
            this.F0 = 0.0f;
            this.G0 = 0.0f;
            this.H0 = 0.0f;
            this.I0 = 0.0f;
            this.J0 = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintSet);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintSet_android_alpha) {
                    this.x0 = obtainStyledAttributes.getFloat(index, this.x0);
                } else if (index == R.styleable.ConstraintSet_android_elevation) {
                    this.z0 = obtainStyledAttributes.getFloat(index, this.z0);
                    this.y0 = true;
                } else if (index == R.styleable.ConstraintSet_android_rotationX) {
                    this.B0 = obtainStyledAttributes.getFloat(index, this.B0);
                } else if (index == R.styleable.ConstraintSet_android_rotationY) {
                    this.C0 = obtainStyledAttributes.getFloat(index, this.C0);
                } else if (index == R.styleable.ConstraintSet_android_rotation) {
                    this.A0 = obtainStyledAttributes.getFloat(index, this.A0);
                } else if (index == R.styleable.ConstraintSet_android_scaleX) {
                    this.D0 = obtainStyledAttributes.getFloat(index, this.D0);
                } else if (index == R.styleable.ConstraintSet_android_scaleY) {
                    this.E0 = obtainStyledAttributes.getFloat(index, this.E0);
                } else if (index == R.styleable.ConstraintSet_android_transformPivotX) {
                    this.F0 = obtainStyledAttributes.getFloat(index, this.F0);
                } else if (index == R.styleable.ConstraintSet_android_transformPivotY) {
                    this.G0 = obtainStyledAttributes.getFloat(index, this.G0);
                } else if (index == R.styleable.ConstraintSet_android_translationX) {
                    this.H0 = obtainStyledAttributes.getFloat(index, this.H0);
                } else if (index == R.styleable.ConstraintSet_android_translationY) {
                    this.I0 = obtainStyledAttributes.getFloat(index, this.I0);
                } else if (index == R.styleable.ConstraintSet_android_translationZ) {
                    this.J0 = obtainStyledAttributes.getFloat(index, this.J0);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }
}
