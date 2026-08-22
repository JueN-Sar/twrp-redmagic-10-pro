package com.android.inputEventTool;

import android.view.Choreographer;

/* loaded from: classes2.dex */
public class ChoreographerToolsWrapper {
    public static void postCallback(int i, Runnable runnable, Object obj) {
        Choreographer.getInstance().postCallback(i, runnable, obj);
    }

    public static void removeCallbacks(int i, Runnable runnable, Object obj) {
        Choreographer.getInstance().removeCallbacks(i, runnable, obj);
    }
}
