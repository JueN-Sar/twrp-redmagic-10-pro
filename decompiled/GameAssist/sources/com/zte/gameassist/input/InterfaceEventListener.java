package com.zte.gameassist.input;

import android.view.MotionEvent;

/* loaded from: classes2.dex */
public interface InterfaceEventListener {
    default void e() {
    }

    void f(MotionEvent motionEvent);

    default void onDispose() {
    }
}
