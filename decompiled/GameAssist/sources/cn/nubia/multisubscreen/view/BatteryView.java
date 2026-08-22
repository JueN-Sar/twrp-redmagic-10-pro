package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class BatteryView extends TextView {
    public BatteryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        setCompoundDrawables(drawable, null, null, null);
    }

    public void a(int i2) {
        setCompoundDrawablesWithIntrinsicBounds(b(i2));
    }

    protected Drawable b(int i2) {
        return getResources().getDrawable(i2 > 80 ? R.drawable.battery100 : i2 > 60 ? R.drawable.battery80 : i2 > 40 ? R.drawable.battery60 : i2 > 20 ? R.drawable.battery40 : i2 > 5 ? R.drawable.battery20 : R.drawable.battery5);
    }

    public BatteryView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
