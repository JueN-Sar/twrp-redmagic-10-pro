package androidx.core.graphics;

import android.graphics.Path;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class PathUtils {

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static float[] a(Path path, float f2) {
            return path.approximate(f2);
        }
    }
}
