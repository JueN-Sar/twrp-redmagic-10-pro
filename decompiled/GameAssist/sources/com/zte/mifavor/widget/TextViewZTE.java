package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/* loaded from: classes2.dex */
public class TextViewZTE extends TextView implements FontScaleSupport {
    private static final String TAG = "TextViewZTE";
    private FontScale mFontScale;
    private long mLastActionDownTime;

    public TextViewZTE(Context context) {
        this(context, null);
    }

    private boolean b(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        ClickableSpan[] a2;
        int action = motionEvent.getAction();
        if ((action != 1 && action != 0) || (a2 = a(textView, spannable, motionEvent)) == null || a2.length == 0) {
            return false;
        }
        if (action == 1) {
            if (System.currentTimeMillis() - this.mLastActionDownTime > ViewConfiguration.getLongPressTimeout()) {
                return false;
            }
            a2[0].onClick(textView);
            Selection.removeSelection(spannable);
        } else if (action == 0) {
            Selection.setSelection(spannable, spannable.getSpanStart(a2[0]), spannable.getSpanEnd(a2[0]));
            this.mLastActionDownTime = System.currentTimeMillis();
        }
        return true;
    }

    public ClickableSpan[] a(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1 && action != 0) {
            return null;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int totalPaddingLeft = x - textView.getTotalPaddingLeft();
        int totalPaddingTop = y - textView.getTotalPaddingTop();
        int scrollX = totalPaddingLeft + textView.getScrollX();
        int scrollY = totalPaddingTop + textView.getScrollY();
        Layout layout = textView.getLayout();
        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
        return (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        CharSequence text = getText();
        if (text == null || !(text instanceof Spannable) || !getLinksClickable()) {
            return super.onTouchEvent(motionEvent);
        }
        if (b(this, (Spannable) text, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(@StyleRes int i2) {
        super.setTextAppearance(i2);
        this.mFontScale.d(i2);
    }

    public void setTextFontScale(int i2) {
        this.mFontScale.c(i2);
    }

    public TextViewZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public TextViewZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public TextViewZTE(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mLastActionDownTime = -1L;
        this.mFontScale = new FontScale();
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setOverScrollMode(2);
        this.mFontScale.b(this, attributeSet, i2, i3);
    }
}
