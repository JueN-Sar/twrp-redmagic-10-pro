package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* loaded from: classes.dex */
public class MarqueeRadioButton extends RadioButton {
    public MarqueeRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setSingleLine(true);
        setMarqueeRepeatLimit(-1);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            setSelected(true);
        } else {
            setSelected(false);
        }
    }

    public MarqueeRadioButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public MarqueeRadioButton(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a();
    }
}
