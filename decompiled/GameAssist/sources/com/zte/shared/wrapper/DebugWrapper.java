package com.zte.shared.wrapper;

import android.os.Debug;

/* loaded from: classes2.dex */
public class DebugWrapper {
    public static String getCallers(int i2) {
        return Debug.getCallers(i2);
    }

    public static String getCallers(int i2, int i3) {
        return Debug.getCallers(i2, i3);
    }
}
