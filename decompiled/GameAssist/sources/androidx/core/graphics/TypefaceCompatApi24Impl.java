package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {

    /* renamed from: b, reason: collision with root package name */
    private static final Class f2944b;

    /* renamed from: c, reason: collision with root package name */
    private static final Constructor f2945c;

    /* renamed from: d, reason: collision with root package name */
    private static final Method f2946d;

    /* renamed from: e, reason: collision with root package name */
    private static final Method f2947e;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            Class cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            method2 = null;
        }
        f2945c = constructor;
        f2944b = cls;
        f2946d = method2;
        f2947e = method;
    }

    private static boolean k(Object obj, ByteBuffer byteBuffer, int i2, int i3, boolean z) {
        try {
            return ((Boolean) f2946d.invoke(obj, byteBuffer, Integer.valueOf(i2), null, Integer.valueOf(i3), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private static Typeface l(Object obj) {
        try {
            Object newInstance = Array.newInstance((Class<?>) f2944b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f2947e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    private static Object m() {
        try {
            return f2945c.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface b(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i2) {
        Object m2 = m();
        if (m2 == null) {
            return null;
        }
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.a()) {
            ByteBuffer b2 = TypefaceCompatUtil.b(context, resources, fontFileResourceEntry.b());
            if (b2 == null || !k(m2, b2, fontFileResourceEntry.c(), fontFileResourceEntry.e(), fontFileResourceEntry.f())) {
                return null;
            }
        }
        return l(m2);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface c(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        Object m2 = m();
        if (m2 == null) {
            return null;
        }
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        for (FontsContractCompat.FontInfo fontInfo : fontInfoArr) {
            Uri d2 = fontInfo.d();
            ByteBuffer byteBuffer = (ByteBuffer) simpleArrayMap.get(d2);
            if (byteBuffer == null) {
                byteBuffer = TypefaceCompatUtil.f(context, cancellationSignal, d2);
                simpleArrayMap.put(d2, byteBuffer);
            }
            if (byteBuffer == null || !k(m2, byteBuffer, fontInfo.c(), fontInfo.e(), fontInfo.f())) {
                return null;
            }
        }
        Typeface l2 = l(m2);
        if (l2 == null) {
            return null;
        }
        return Typeface.create(l2, i2);
    }
}
