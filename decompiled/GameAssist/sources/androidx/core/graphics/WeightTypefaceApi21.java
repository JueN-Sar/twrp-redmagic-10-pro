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
final class WeightTypefaceApi21 {

    /* renamed from: a, reason: collision with root package name */
    private static final Field f2961a;

    /* renamed from: b, reason: collision with root package name */
    private static final Method f2962b;

    /* renamed from: c, reason: collision with root package name */
    private static final Method f2963c;

    /* renamed from: d, reason: collision with root package name */
    private static final Constructor f2964d;

    /* renamed from: e, reason: collision with root package name */
    private static final LongSparseArray f2965e;

    /* renamed from: f, reason: collision with root package name */
    private static final Object f2966f;

    static {
        Field field;
        Constructor constructor;
        Method method;
        Method method2;
        try {
            field = Typeface.class.getDeclaredField("native_instance");
            Class cls = Long.TYPE;
            Class cls2 = Integer.TYPE;
            method = Typeface.class.getDeclaredMethod("nativeCreateFromTypeface", cls, cls2);
            method.setAccessible(true);
            method2 = Typeface.class.getDeclaredMethod("nativeCreateWeightAlias", cls, cls2);
            method2.setAccessible(true);
            constructor = Typeface.class.getDeclaredConstructor(cls);
            constructor.setAccessible(true);
        } catch (NoSuchFieldException | NoSuchMethodException e2) {
            Log.e("WeightTypeface", e2.getClass().getName(), e2);
            field = null;
            constructor = null;
            method = null;
            method2 = null;
        }
        f2961a = field;
        f2962b = method;
        f2963c = method2;
        f2964d = constructor;
        f2965e = new LongSparseArray(3);
        f2966f = new Object();
    }
}
