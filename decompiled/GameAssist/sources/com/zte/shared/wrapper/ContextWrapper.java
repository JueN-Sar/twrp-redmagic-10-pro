package com.zte.shared.wrapper;

import android.app.ActivityThread;
import android.app.Application;
import android.content.Context;
import android.view.Display;

/* loaded from: classes2.dex */
public class ContextWrapper {
    public static Context createDisplayContext(Context context, Display display) {
        return context.createDisplayContext(display);
    }

    public static Context createWindowContext(Context context, int i2) {
        return context.createWindowContext(i2, null);
    }

    public static void destroy(Context context) {
        context.destroy();
    }

    public static Context getContext() {
        Application currentApplication = ActivityThread.currentApplication();
        if (currentApplication != null) {
            return currentApplication.getBaseContext();
        }
        return null;
    }

    public static Display getDisplay(Context context) {
        return context.getDisplay();
    }

    public static int getDisplayId(Context context) {
        return context.getDisplayId();
    }

    public static void updateDisplay(Context context) {
        context.updateDisplay(0);
    }

    public static void updateDisplay(Context context, int i2) {
        context.updateDisplay(i2);
    }
}
