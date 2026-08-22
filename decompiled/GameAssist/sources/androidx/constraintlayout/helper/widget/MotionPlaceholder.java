package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.widget.VirtualLayout;

/* loaded from: classes.dex */
public class MotionPlaceholder extends VirtualLayout {
    private static final String TAG = "MotionPlaceholder";
    Placeholder mPlaceholder;

    public MotionPlaceholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.mHelperWidget = new Placeholder();
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onMeasure(int i2, int i3) {
        x(this.mPlaceholder, i2, i3);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void u(ConstraintWidgetContainer constraintWidgetContainer, Helper helper, SparseArray sparseArray) {
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

    public MotionPlaceholder(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public MotionPlaceholder(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2);
    }
}
