package com.zte.mifavor.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes2.dex */
public class MarqueeTextViewZTE extends AppCompatTextView {
    public MarqueeTextViewZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        r();
    }

    private void r() {
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            requestFocus();
        }
        return super.onTouchEvent(motionEvent);
    }
}
