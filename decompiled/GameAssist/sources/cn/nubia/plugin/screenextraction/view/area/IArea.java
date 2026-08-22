package cn.nubia.plugin.screenextraction.view.area;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/* loaded from: classes.dex */
public interface IArea {

    public interface Callback {
        void a(IArea iArea, Rect rect, Rect rect2);
    }

    Point a();

    Rect b();

    boolean c(MotionEvent motionEvent);

    void d(Rect rect);

    void e(Callback callback);

    void onDraw(Canvas canvas);
}
