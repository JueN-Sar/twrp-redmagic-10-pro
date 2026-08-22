package cn.nubia.gameassist.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes.dex */
public class FocusTextView extends TextView {
    public FocusTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public void setTip(String str) {
        setText(str);
        setVisibility(8);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rotation", 0.0f, 90.0f);
        ofFloat.setDuration(0L);
        ofFloat.start();
    }
}
