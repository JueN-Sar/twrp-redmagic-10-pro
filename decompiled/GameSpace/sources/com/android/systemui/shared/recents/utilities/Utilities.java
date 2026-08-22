package com.android.systemui.shared.recents.utilities;

import android.graphics.Color;
import android.os.Handler;
import android.view.Surface;
import android.view.View;
import android.view.ViewRootImpl;

/* loaded from: classes2.dex */
public class Utilities {
    public static float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f3, f));
    }

    public static float computeContrastBetweenColors(int i, int i2) {
        float red = Color.red(i) / 255.0f;
        float green = Color.green(i) / 255.0f;
        float blue = Color.blue(i) / 255.0f;
        float pow = ((red < 0.03928f ? red / 12.92f : (float) Math.pow((red + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f) + ((green < 0.03928f ? green / 12.92f : (float) Math.pow((green + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.7152f) + ((blue < 0.03928f ? blue / 12.92f : (float) Math.pow((blue + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.0722f);
        float red2 = Color.red(i2) / 255.0f;
        float green2 = Color.green(i2) / 255.0f;
        float blue2 = Color.blue(i2) / 255.0f;
        return Math.abs((((((red2 < 0.03928f ? red2 / 12.92f : (float) Math.pow((red2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f) + ((green2 < 0.03928f ? green2 / 12.92f : (float) Math.pow((green2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.7152f)) + ((blue2 < 0.03928f ? blue2 / 12.92f : (float) Math.pow((blue2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.0722f)) + 0.05f) / (pow + 0.05f));
    }

    public static long getNextFrameNumber(Surface surface) {
        if (surface == null || !surface.isValid()) {
            return -1L;
        }
        return surface.getNextFrameNumber();
    }

    public static Surface getSurface(View view) {
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.mSurface;
    }

    public static float mapRange(float f, float f2, float f3) {
        return f2 + (f * (f3 - f2));
    }

    public static void postAtFrontOfQueueAsynchronously(Handler handler, Runnable runnable) {
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage().setCallback(runnable));
    }

    public static float unmapRange(float f, float f2, float f3) {
        return (f - f2) / (f3 - f2);
    }
}
