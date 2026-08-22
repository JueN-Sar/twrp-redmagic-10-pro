package com.zte.mifavor.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;

/* loaded from: classes2.dex */
public class DialogMessage extends TextViewZTE {
    private static final String TAG = "Z#DialogMessage";
    private boolean mIsMultiplelines;

    public DialogMessage(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mIsMultiplelines = false;
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    public boolean c() {
        Layout layout = getLayout();
        return layout != null && layout.getLineCount() > 1;
    }

    public boolean getIsMultiplelines() {
        Log.d(TAG, "get Is Multiple lines mIsMultiplelines=" + this.mIsMultiplelines);
        return this.mIsMultiplelines;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate out. isMore=" + c() + ", this=" + this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        Log.d(TAG, "onLayout out. isMore=" + c() + ", this=" + this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Layout layout = getLayout();
        if (layout != null) {
            if (layout.getLineCount() > 1 || this.mIsMultiplelines) {
                setTextAlignment(2);
                setGravity(8388611);
            } else {
                setTextAlignment(4);
                setGravity(17);
            }
            super.onMeasure(i2, i3);
        }
    }

    public void setIsMultiplelines(boolean z) {
        this.mIsMultiplelines = z;
        Log.d(TAG, "set Is Multiple lines mIsMultiplelines=" + this.mIsMultiplelines);
        invalidate();
    }

    public DialogMessage(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsMultiplelines = false;
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    public DialogMessage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsMultiplelines = false;
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }
}
