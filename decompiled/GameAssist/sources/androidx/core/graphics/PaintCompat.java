package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeUtils;

/* loaded from: classes.dex */
public final class PaintCompat {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal f2924a = new ThreadLocal();

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static boolean a(Paint paint, String str) {
            return paint.hasGlyph(str);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(Paint paint, Object obj) {
            paint.setBlendMode((BlendMode) obj);
        }
    }

    public static boolean a(Paint paint, String str) {
        return Api23Impl.a(paint, str);
    }

    public static boolean b(Paint paint, BlendModeCompat blendModeCompat) {
        Api29Impl.a(paint, blendModeCompat != null ? BlendModeUtils.Api29Impl.a(blendModeCompat) : null);
        return true;
    }
}
