package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EdgeEffect;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class EdgeEffectCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static void a(EdgeEffect edgeEffect, float f2, float f3) {
            edgeEffect.onPull(f2, f3);
        }
    }

    @RequiresApi
    private static class Api31Impl {
        @DoNotInline
        public static EdgeEffect a(Context context, AttributeSet attributeSet) {
            try {
                return new EdgeEffect(context, attributeSet);
            } catch (Throwable unused) {
                return new EdgeEffect(context);
            }
        }

        @DoNotInline
        public static float b(EdgeEffect edgeEffect) {
            try {
                return edgeEffect.getDistance();
            } catch (Throwable unused) {
                return 0.0f;
            }
        }

        @DoNotInline
        public static float c(EdgeEffect edgeEffect, float f2, float f3) {
            try {
                return edgeEffect.onPullDistance(f2, f3);
            } catch (Throwable unused) {
                edgeEffect.onPull(f2, f3);
                return 0.0f;
            }
        }
    }

    public static EdgeEffect a(Context context, AttributeSet attributeSet) {
        return Api31Impl.a(context, attributeSet);
    }

    public static float b(EdgeEffect edgeEffect) {
        return Api31Impl.b(edgeEffect);
    }

    public static void c(EdgeEffect edgeEffect, float f2, float f3) {
        Api21Impl.a(edgeEffect, f2, f3);
    }

    public static float d(EdgeEffect edgeEffect, float f2, float f3) {
        return Api31Impl.c(edgeEffect, f2, f3);
    }
}
