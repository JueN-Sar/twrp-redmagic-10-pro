package androidx.core.view;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

@Deprecated
/* loaded from: classes.dex */
public final class GestureDetectorCompat {

    /* renamed from: a, reason: collision with root package name */
    private final GestureDetector f3338a;

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean a(MotionEvent motionEvent) {
        return this.f3338a.onTouchEvent(motionEvent);
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        this.f3338a = new GestureDetector(context, onGestureListener, handler);
    }
}
