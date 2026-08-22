package com.zte.mifavor.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class DialogTitle extends TextViewZTE {
    public DialogTitle(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int lineCount;
        super.onMeasure(i2, i3);
        Layout layout = getLayout();
        if (layout == null || (lineCount = layout.getLineCount()) <= 0 || layout.getEllipsisCount(lineCount - 1) <= 0) {
            return;
        }
        setSingleLine(false);
        setMaxLines(2);
        float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.mfvc_primary_font_size);
        if (dimensionPixelSize != 0.0f) {
            setTextSize(0, dimensionPixelSize);
        }
        Log.d("DialogTitle", "onMeasure AutoSizeTextType=" + getAutoSizeTextType() + ", minTextSize" + getAutoSizeMinTextSize() + ", maxTextSize" + getAutoSizeMaxTextSize() + ", textSize" + dimensionPixelSize + ", gettextSize" + getTextSize());
        super.onMeasure(i2, i3);
    }

    public void setDialogTitleColor(int i2) {
        setTextColor(i2);
    }

    public DialogTitle(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public DialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
