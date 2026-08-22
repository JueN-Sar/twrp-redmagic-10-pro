package com.zte.shared.wrapper;

import android.view.Display;

/* loaded from: classes2.dex */
public class DisplayWrapper {
    public static final int TYPE_EXTERNAL = 2;
    public static final int TYPE_INTERNAL = 1;
    public static final int TYPE_OVERLAY = 4;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIRTUAL = 5;
    public static final int TYPE_WIFI = 3;

    public static int getType(Display display) {
        return display.getType();
    }

    public static String getUniqueId(Display display) {
        return display.getUniqueId();
    }
}
