package com.airbnb.lottie.animation;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.LocaleList;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LPaint extends Paint {
    public LPaint() {
    }

    @Override // android.graphics.Paint
    public void setAlpha(int i2) {
        super.setAlpha(MiscUtils.c(i2, 0, 255));
    }

    @Override // android.graphics.Paint
    public void setTextLocales(LocaleList localeList) {
    }

    public LPaint(int i2) {
        super(i2);
    }

    public LPaint(PorterDuff.Mode mode) {
        setXfermode(new PorterDuffXfermode(mode));
    }

    public LPaint(int i2, PorterDuff.Mode mode) {
        super(i2);
        setXfermode(new PorterDuffXfermode(mode));
    }
}
