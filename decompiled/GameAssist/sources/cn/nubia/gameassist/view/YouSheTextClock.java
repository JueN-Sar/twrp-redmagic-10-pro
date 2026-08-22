package cn.nubia.gameassist.view;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class YouSheTextClock extends NubiaTextClock {
    public YouSheTextClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void m() {
        setTypeface(YouSheTextView.getYouSheHei());
    }

    public YouSheTextClock(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        m();
    }
}
