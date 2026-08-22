package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {

    /* renamed from: b, reason: collision with root package name */
    private static Class f2939b = null;

    /* renamed from: c, reason: collision with root package name */
    private static Constructor f2940c = null;

    /* renamed from: d, reason: collision with root package name */
    private static Method f2941d = null;

    /* renamed from: e, reason: collision with root package name */
    private static Method f2942e = null;

    /* renamed from: f, reason: collision with root package name */
    private static boolean f2943f = false;

    private static boolean k(Object obj, String str, int i2, boolean z) {
        n();
        try {
            return ((Boolean) f2941d.invoke(obj, str, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static Typeface l(Object obj) {
        n();
        try {
            Object newInstance = Array.newInstance((Class<?>) f2939b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f2942e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private File m(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
        } catch (ErrnoException unused) {
        }
        return null;
    }

    private static void n() {
        Method method;
        Class<?> cls;
        Method method2;
        if (f2943f) {
            return;
        }
        f2943f = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
            method = null;
            cls = null;
            method2 = null;
        }
        f2940c = constructor;
        f2939b = cls;
        f2941d = method2;
        f2942e = method;
    }

    private static Object o() {
        n();
        try {
            return f2940c.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface b(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i2) {
        Object o2 = o();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.a()) {
            File e2 = TypefaceCompatUtil.e(context);
            if (e2 == null) {
                return null;
            }
            try {
                if (!TypefaceCompatUtil.c(e2, resources, fontFileResourceEntry.b())) {
                    return null;
                }
                if (!k(o2, e2.getPath(), fontFileResourceEntry.e(), fontFileResourceEntry.f())) {
                    return null;
                }
                e2.delete();
            } catch (RuntimeException unused) {
                return null;
            } finally {
                e2.delete();
            }
        }
        return l(o2);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface c(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        if (fontInfoArr.length < 1) {
            return null;
        }
        FontsContractCompat.FontInfo i3 = i(fontInfoArr, i2);
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(i3.d(), "r", cancellationSignal);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            try {
                File m2 = m(openFileDescriptor);
                if (m2 != null && m2.canRead()) {
                    Typeface createFromFile = Typeface.createFromFile(m2);
                    openFileDescriptor.close();
                    return createFromFile;
                }
                FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                try {
                    Typeface d2 = super.d(context, fileInputStream);
                    fileInputStream.close();
                    openFileDescriptor.close();
                    return d2;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
