package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

/* loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;
    private androidx.constraintlayout.core.widgets.Barrier mBarrier;
    private int mIndicatedType;
    private int mResolvedType;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void x(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.mResolvedType = i2;
        if (z) {
            int i3 = this.mIndicatedType;
            if (i3 == 5) {
                this.mResolvedType = 1;
            } else if (i3 == 6) {
                this.mResolvedType = 0;
            }
        } else {
            int i4 = this.mIndicatedType;
            if (i4 == 5) {
                this.mResolvedType = 0;
            } else if (i4 == 6) {
                this.mResolvedType = 1;
            }
        }
        if (constraintWidget instanceof androidx.constraintlayout.core.widgets.Barrier) {
            ((androidx.constraintlayout.core.widgets.Barrier) constraintWidget).F1(this.mResolvedType);
        }
    }

    public boolean getAllowsGoneWidget() {
        return this.mBarrier.z1();
    }

    public int getMargin() {
        return this.mBarrier.B1();
    }

    public int getType() {
        return this.mIndicatedType;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.mBarrier = new androidx.constraintlayout.core.widgets.Barrier();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.mBarrier.E1(obtainStyledAttributes.getBoolean(index, true));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.mBarrier.G1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mHelperWidget = this.mBarrier;
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray sparseArray) {
        super.p(constraint, helperWidget, layoutParams, sparseArray);
        if (helperWidget instanceof androidx.constraintlayout.core.widgets.Barrier) {
            androidx.constraintlayout.core.widgets.Barrier barrier = (androidx.constraintlayout.core.widgets.Barrier) helperWidget;
            x(barrier, constraint.f2491e.h0, ((ConstraintWidgetContainer) helperWidget.M()).W1());
            barrier.E1(constraint.f2491e.p0);
            barrier.G1(constraint.f2491e.i0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void q(ConstraintWidget constraintWidget, boolean z) {
        x(constraintWidget, this.mIndicatedType, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mBarrier.E1(z);
    }

    public void setDpMargin(int i2) {
        this.mBarrier.G1((int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i2) {
        this.mBarrier.G1(i2);
    }

    public void setType(int i2) {
        this.mIndicatedType = i2;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        super.setVisibility(8);
    }
}
