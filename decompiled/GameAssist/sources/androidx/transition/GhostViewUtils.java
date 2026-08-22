package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
class GhostViewUtils {
    static GhostView a(View view, ViewGroup viewGroup, Matrix matrix) {
        return GhostViewPort.b(view, viewGroup, matrix);
    }

    static void b(View view) {
        GhostViewPort.f(view);
    }
}
