package com.zte.gameassist.lowsugar.detect;

import android.view.MotionEvent;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface ISceneDetect {

    public interface SceneDetectCallback {
        void a(int i2);

        void b();
    }

    default void a() {
    }

    default boolean b(int i2, MotionEvent motionEvent) {
        return true;
    }

    default void c() {
    }

    boolean d(int i2, MotionEvent motionEvent);

    int e(List list, Map map);

    default boolean f() {
        return false;
    }

    default void g(SceneDetectCallback sceneDetectCallback) {
    }

    default boolean h() {
        return false;
    }

    default long i() {
        return 1000L;
    }
}
