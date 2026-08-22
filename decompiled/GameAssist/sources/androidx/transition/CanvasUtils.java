package androidx.transition;

import android.graphics.Canvas;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
class CanvasUtils {

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(Canvas canvas) {
            canvas.disableZ();
        }

        @DoNotInline
        static void b(Canvas canvas) {
            canvas.enableZ();
        }
    }

    static void a(Canvas canvas, boolean z) {
        if (z) {
            Api29Impl.b(canvas);
        } else {
            Api29Impl.a(canvas);
        }
    }
}
