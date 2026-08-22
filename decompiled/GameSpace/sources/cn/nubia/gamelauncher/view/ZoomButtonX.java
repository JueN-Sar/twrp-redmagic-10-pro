package cn.nubia.gamelauncher.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class ZoomButtonX extends ZoomButton {
    public ZoomButtonX(Context context) {
        this(context, null);
    }

    public ZoomButtonX(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(true);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }
}
