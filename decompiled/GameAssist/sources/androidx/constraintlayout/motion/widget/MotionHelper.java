package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;

/* loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements MotionHelperInterface {
    private float mProgress;
    private boolean mUseOnHide;
    private boolean mUseOnShow;
    protected View[] views;

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
        o(attributeSet);
    }

    public void A(MotionLayout motionLayout) {
    }

    public void B(Canvas canvas) {
    }

    public void C(Canvas canvas) {
    }

    public void D(MotionLayout motionLayout, HashMap hashMap) {
    }

    public void E(View view, float f2) {
    }

    public void a(MotionLayout motionLayout, int i2, int i3, float f2) {
    }

    public void b(MotionLayout motionLayout, int i2) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
    public void c(MotionLayout motionLayout, int i2, int i3) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
    public void d(MotionLayout motionLayout, int i2, boolean z, float f2) {
    }

    public float getProgress() {
        return this.mProgress;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionHelper);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionHelper_onShow) {
                    this.mUseOnShow = obtainStyledAttributes.getBoolean(index, this.mUseOnShow);
                } else if (index == R.styleable.MotionHelper_onHide) {
                    this.mUseOnHide = obtainStyledAttributes.getBoolean(index, this.mUseOnHide);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setProgress(float f2) {
        this.mProgress = f2;
        int i2 = 0;
        if (this.mCount > 0) {
            this.views = n((ConstraintLayout) getParent());
            while (i2 < this.mCount) {
                E(this.views[i2], f2);
                i2++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i2 < childCount) {
            View childAt = viewGroup.getChildAt(i2);
            if (!(childAt instanceof MotionHelper)) {
                E(childAt, f2);
            }
            i2++;
        }
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return this.mUseOnHide;
    }

    public boolean z() {
        return this.mUseOnShow;
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
        o(attributeSet);
    }
}
