package com.zte.gameassist.lowsugar.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class LowSugarMarqueeText extends TextView {
    private boolean mIsAttachedToWindow;

    public LowSugarMarqueeText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setSingleLine();
        setMarqueeRepeatLimit(-1);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mIsAttachedToWindow;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        setFocusable(true);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        setFocusable(false);
    }
}
