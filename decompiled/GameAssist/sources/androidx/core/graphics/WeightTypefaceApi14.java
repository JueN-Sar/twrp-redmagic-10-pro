package androidx.core.graphics;

import android.graphics.Typeface;
import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.collection.LongSparseArray;
import java.lang.reflect.Field;

@RestrictTo
/* loaded from: classes.dex */
final class WeightTypefaceApi14 {

    /* renamed from: a, reason: collision with root package name */
    private static final Field f2958a;

    /* renamed from: b, reason: collision with root package name */
    private static final LongSparseArray f2959b;

    /* renamed from: c, reason: collision with root package name */
    private static final Object f2960c;

    static {
        Field field;
        try {
            field = Typeface.class.getDeclaredField("native_instance");
            field.setAccessible(true);
        } catch (Exception e2) {
            Log.e("WeightTypeface", e2.getClass().getName(), e2);
            field = null;
        }
        f2958a = field;
        f2959b = new LongSparseArray(3);
        f2960c = new Object();
    }
}
