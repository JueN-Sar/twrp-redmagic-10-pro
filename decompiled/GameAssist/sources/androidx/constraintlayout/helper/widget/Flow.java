package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.VirtualLayout;

/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    private static final String TAG = "Flow";
    public static final int VERTICAL = 1;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private androidx.constraintlayout.core.widgets.Flow mFlow;

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.mFlow = new androidx.constraintlayout.core.widgets.Flow();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_android_orientation) {
                    this.mFlow.I2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_padding) {
                    this.mFlow.N1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.mFlow.S1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.mFlow.P1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.mFlow.Q1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.mFlow.T1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.mFlow.R1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.mFlow.O1(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.mFlow.N2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.mFlow.C2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.mFlow.M2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.mFlow.w2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.mFlow.E2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.mFlow.y2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.mFlow.G2(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.mFlow.A2(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.mFlow.v2(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.mFlow.D2(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.mFlow.x2(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.mFlow.F2(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.mFlow.K2(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.mFlow.z2(obtainStyledAttributes.getInt(index, 2));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.mFlow.J2(obtainStyledAttributes.getInt(index, 2));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.mFlow.B2(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.mFlow.L2(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.mFlow.H2(obtainStyledAttributes.getInt(index, -1));
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mHelperWidget = this.mFlow;
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onMeasure(int i2, int i3) {
        x(this.mFlow, i2, i3);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray sparseArray) {
        super.p(constraint, helperWidget, layoutParams, sparseArray);
        if (helperWidget instanceof androidx.constraintlayout.core.widgets.Flow) {
            androidx.constraintlayout.core.widgets.Flow flow = (androidx.constraintlayout.core.widgets.Flow) helperWidget;
            int i2 = layoutParams.Z;
            if (i2 != -1) {
                flow.I2(i2);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void q(ConstraintWidget constraintWidget, boolean z) {
        this.mFlow.y1(z);
    }

    public void setFirstHorizontalBias(float f2) {
        this.mFlow.v2(f2);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i2) {
        this.mFlow.w2(i2);
        requestLayout();
    }

    public void setFirstVerticalBias(float f2) {
        this.mFlow.x2(f2);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i2) {
        this.mFlow.y2(i2);
        requestLayout();
    }

    public void setHorizontalAlign(int i2) {
        this.mFlow.z2(i2);
        requestLayout();
    }

    public void setHorizontalBias(float f2) {
        this.mFlow.A2(f2);
        requestLayout();
    }

    public void setHorizontalGap(int i2) {
        this.mFlow.B2(i2);
        requestLayout();
    }

    public void setHorizontalStyle(int i2) {
        this.mFlow.C2(i2);
        requestLayout();
    }

    public void setLastHorizontalBias(float f2) {
        this.mFlow.D2(f2);
        requestLayout();
    }

    public void setLastHorizontalStyle(int i2) {
        this.mFlow.E2(i2);
        requestLayout();
    }

    public void setLastVerticalBias(float f2) {
        this.mFlow.F2(f2);
        requestLayout();
    }

    public void setLastVerticalStyle(int i2) {
        this.mFlow.G2(i2);
        requestLayout();
    }

    public void setMaxElementsWrap(int i2) {
        this.mFlow.H2(i2);
        requestLayout();
    }

    public void setOrientation(int i2) {
        this.mFlow.I2(i2);
        requestLayout();
    }

    public void setPadding(int i2) {
        this.mFlow.N1(i2);
        requestLayout();
    }

    public void setPaddingBottom(int i2) {
        this.mFlow.O1(i2);
        requestLayout();
    }

    public void setPaddingLeft(int i2) {
        this.mFlow.Q1(i2);
        requestLayout();
    }

    public void setPaddingRight(int i2) {
        this.mFlow.R1(i2);
        requestLayout();
    }

    public void setPaddingTop(int i2) {
        this.mFlow.T1(i2);
        requestLayout();
    }

    public void setVerticalAlign(int i2) {
        this.mFlow.J2(i2);
        requestLayout();
    }

    public void setVerticalBias(float f2) {
        this.mFlow.K2(f2);
        requestLayout();
    }

    public void setVerticalGap(int i2) {
        this.mFlow.L2(i2);
        requestLayout();
    }

    public void setVerticalStyle(int i2) {
        this.mFlow.M2(i2);
        requestLayout();
    }

    public void setWrapMode(int i2) {
        this.mFlow.N2(i2);
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void x(androidx.constraintlayout.core.widgets.VirtualLayout virtualLayout, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (virtualLayout == null) {
            setMeasuredDimension(0, 0);
        } else {
            virtualLayout.H1(mode, size, mode2, size2);
            setMeasuredDimension(virtualLayout.C1(), virtualLayout.B1());
        }
    }

    public Flow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
