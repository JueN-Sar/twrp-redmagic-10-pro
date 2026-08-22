package cn.nubia.gameassist.performance;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class NubiaPerformanceRadioButton extends RadioButton {
    private Drawable mIcon;

    public NubiaPerformanceRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void a(boolean z) {
        Drawable drawable = this.mIcon;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), this.mIcon.getMinimumHeight());
        if (z) {
            setCompoundDrawables(null, null, null, this.mIcon);
        } else {
            setCompoundDrawables(null, null, null, null);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        a(z);
    }

    public NubiaPerformanceRadioButton(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public NubiaPerformanceRadioButton(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.performanceView);
        this.mIcon = obtainStyledAttributes.getDrawable(R.styleable.performanceView_bottomIcon);
        obtainStyledAttributes.recycle();
    }
}
