package androidx.core.view;

import android.os.Build;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class VelocityTrackerCompat {

    /* renamed from: a, reason: collision with root package name */
    private static Map f3367a = Collections.synchronizedMap(new WeakHashMap());

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        static float a(VelocityTracker velocityTracker, int i2) {
            return velocityTracker.getAxisVelocity(i2);
        }

        @DoNotInline
        static float b(VelocityTracker velocityTracker, int i2, int i3) {
            return velocityTracker.getAxisVelocity(i2, i3);
        }

        @DoNotInline
        static boolean c(VelocityTracker velocityTracker, int i2) {
            return velocityTracker.isAxisSupported(i2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface VelocityTrackableMotionEventAxis {
    }

    public static void a(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        velocityTracker.addMovement(motionEvent);
        if (Build.VERSION.SDK_INT < 34 && motionEvent.getSource() == 4194304) {
            if (!f3367a.containsKey(velocityTracker)) {
                f3367a.put(velocityTracker, new VelocityTrackerFallback());
            }
            ((VelocityTrackerFallback) f3367a.get(velocityTracker)).a(motionEvent);
        }
    }

    public static void b(VelocityTracker velocityTracker, int i2) {
        c(velocityTracker, i2, Float.MAX_VALUE);
    }

    public static void c(VelocityTracker velocityTracker, int i2, float f2) {
        velocityTracker.computeCurrentVelocity(i2, f2);
        VelocityTrackerFallback e2 = e(velocityTracker);
        if (e2 != null) {
            e2.c(i2, f2);
        }
    }

    public static float d(VelocityTracker velocityTracker, int i2) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.a(velocityTracker, i2);
        }
        if (i2 == 0) {
            return velocityTracker.getXVelocity();
        }
        if (i2 == 1) {
            return velocityTracker.getYVelocity();
        }
        VelocityTrackerFallback e2 = e(velocityTracker);
        if (e2 != null) {
            return e2.d(i2);
        }
        return 0.0f;
    }

    private static VelocityTrackerFallback e(VelocityTracker velocityTracker) {
        return (VelocityTrackerFallback) f3367a.get(velocityTracker);
    }
}
