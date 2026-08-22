package androidx.constraintlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes.dex */
public class Group extends ConstraintHelper {
    public Group(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void j(ConstraintLayout constraintLayout) {
        i(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.mUseViewMeasure = false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        h();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.v0.p1(0);
        layoutParams.v0.Q0(0);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        h();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        h();
    }

    public Group(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
