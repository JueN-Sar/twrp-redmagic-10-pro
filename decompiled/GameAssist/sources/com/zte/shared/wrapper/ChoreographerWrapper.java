package com.zte.shared.wrapper;

import android.view.Choreographer;

/* loaded from: classes2.dex */
public class ChoreographerWrapper {
    public static Choreographer getSfInstance() {
        return Choreographer.getSfInstance();
    }

    public static void postInputFrame(Choreographer choreographer, Runnable runnable) {
        choreographer.postCallback(0, runnable, null);
    }

    public static void removeInputFrame(Choreographer choreographer, Runnable runnable) {
        choreographer.removeCallbacks(0, runnable, null);
    }
}
