package com.android.systemui.shared.system;

import android.view.Choreographer;

/* loaded from: classes2.dex */
public class ChoreographerCompat {
    public static Choreographer getSfInstance() {
        return Choreographer.getSfInstance();
    }

    public static void postInputFrame(Choreographer choreographer, Runnable runnable) {
        choreographer.postCallback(0, runnable, null);
    }
}
