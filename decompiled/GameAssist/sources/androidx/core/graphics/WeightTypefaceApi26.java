package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.LongSparseArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RequiresApi
@SuppressLint({"SoonBlockedPrivateApi"})
@RestrictTo
/* loaded from: classes.dex */
final class WeightTypefaceApi26 {

    /* renamed from: a, reason: collision with root package name */
    private static final Field f2967a;

    /* renamed from: b, reason: collision with root package name */
    private static final Method f2968b;

    /* renamed from: c, reason: collision with root package name */
    private static final Constructor f2969c;

    /* renamed from: d, reason: collision with root package name */
    private static final LongSparseArray f2970d;

    /* renamed from: e, reason: collision with root package name */
    private static final Object f2971e;

    static {
        Field field;
        Constructor constructor;
        Method method;
        try {
            field = Typeface.class.getDeclaredField("native_instance");
            Class cls = Long.TYPE;
            method = Typeface.class.getDeclaredMethod("nativeCreateFromTypefaceWithExactStyle", cls, Integer.TYPE, Boolean.TYPE);
            method.setAccessible(true);
            constructor = Typeface.class.getDeclaredConstructor(cls);
            constructor.setAccessible(true);
        } catch (NoSuchFieldException | NoSuchMethodException e2) {
            Log.e("WeightTypeface", e2.getClass().getName(), e2);
            field = null;
            constructor = null;
            method = null;
        }
        f2967a = field;
        f2968b = method;
        f2969c = constructor;
        f2970d = new LongSparseArray(3);
        f2971e = new Object();
    }
}
