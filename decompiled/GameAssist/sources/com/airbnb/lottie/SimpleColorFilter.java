package com.airbnb.lottie;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

/* loaded from: classes.dex */
public class SimpleColorFilter extends PorterDuffColorFilter {
    public SimpleColorFilter(int i2) {
        super(i2, PorterDuff.Mode.SRC_ATOP);
    }
}
