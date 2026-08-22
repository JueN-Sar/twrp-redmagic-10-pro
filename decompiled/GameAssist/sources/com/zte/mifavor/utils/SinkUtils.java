package com.zte.mifavor.utils;

import android.content.res.Resources;
import android.graphics.Paint;

/* loaded from: classes2.dex */
public class SinkUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final Paint f17437a = new Paint();

    public static int a(String str, float f2) {
        Paint paint = f17437a;
        paint.setTextSize(f2);
        return (int) paint.measureText(str);
    }

    public static int b(float f2) {
        Paint paint = f17437a;
        paint.setTextSize(f2);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((int) Math.ceil(fontMetrics.bottom - fontMetrics.top)) + 6;
    }

    public static boolean c(Resources resources) {
        return resources.getConfiguration().orientation == 2;
    }
}
