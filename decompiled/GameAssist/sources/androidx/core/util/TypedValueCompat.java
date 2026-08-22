package androidx.core.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class TypedValueCompat {

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        public static float a(int i2, float f2, DisplayMetrics displayMetrics) {
            return TypedValue.deriveDimension(i2, f2, displayMetrics);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ComplexDimensionUnit {
    }

    public static int a(int i2) {
        return i2 & 15;
    }
}
