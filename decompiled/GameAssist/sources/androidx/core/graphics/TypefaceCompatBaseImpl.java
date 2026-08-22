package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import com.google.android.gms.common.api.Api;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

@RestrictTo
/* loaded from: classes.dex */
class TypefaceCompatBaseImpl {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap f2955a = new ConcurrentHashMap();

    /* renamed from: androidx.core.graphics.TypefaceCompatBaseImpl$3, reason: invalid class name */
    class AnonymousClass3 implements StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry> {
        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int b(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.e();
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean a(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.f();
        }
    }

    private interface StyleExtractor<T> {
        boolean a(Object obj);

        int b(Object obj);
    }

    TypefaceCompatBaseImpl() {
    }

    private void a(Typeface typeface, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry) {
        long j2 = j(typeface);
        if (j2 != 0) {
            this.f2955a.put(Long.valueOf(j2), fontFamilyFilesResourceEntry);
        }
    }

    private FontResourcesParserCompat.FontFileResourceEntry f(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i2) {
        return (FontResourcesParserCompat.FontFileResourceEntry) g(fontFamilyFilesResourceEntry.a(), i2, new StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry>() { // from class: androidx.core.graphics.TypefaceCompatBaseImpl.2
            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int b(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.e();
            }

            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public boolean a(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.f();
            }
        });
    }

    private static Object g(Object[] objArr, int i2, StyleExtractor styleExtractor) {
        return h(objArr, (i2 & 1) == 0 ? 400 : 700, (i2 & 2) != 0, styleExtractor);
    }

    private static Object h(Object[] objArr, int i2, boolean z, StyleExtractor styleExtractor) {
        Object obj = null;
        int i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        for (Object obj2 : objArr) {
            int abs = (Math.abs(styleExtractor.b(obj2) - i2) * 2) + (styleExtractor.a(obj2) == z ? 0 : 1);
            if (obj == null || i3 > abs) {
                obj = obj2;
                i3 = abs;
            }
        }
        return obj;
    }

    private static long j(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        } catch (NoSuchFieldException e3) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e3);
            return 0L;
        }
    }

    public Typeface b(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i2) {
        FontResourcesParserCompat.FontFileResourceEntry f2 = f(fontFamilyFilesResourceEntry, i2);
        if (f2 == null) {
            return null;
        }
        Typeface d2 = TypefaceCompat.d(context, resources, f2.b(), f2.a(), 0, i2);
        a(d2, fontFamilyFilesResourceEntry);
        return d2;
    }

    public Typeface c(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (fontInfoArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(i(fontInfoArr, i2).d());
            try {
                Typeface d2 = d(context, inputStream);
                TypefaceCompatUtil.a(inputStream);
                return d2;
            } catch (IOException unused) {
                TypefaceCompatUtil.a(inputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                TypefaceCompatUtil.a(inputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected Typeface d(Context context, InputStream inputStream) {
        File e2 = TypefaceCompatUtil.e(context);
        if (e2 == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.d(e2, inputStream)) {
                return Typeface.createFromFile(e2.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            e2.delete();
        }
    }

    public Typeface e(Context context, Resources resources, int i2, String str, int i3) {
        File e2 = TypefaceCompatUtil.e(context);
        if (e2 == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.c(e2, resources, i2)) {
                return Typeface.createFromFile(e2.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            e2.delete();
        }
    }

    protected FontsContractCompat.FontInfo i(FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        return (FontsContractCompat.FontInfo) g(fontInfoArr, i2, new StyleExtractor<FontsContractCompat.FontInfo>() { // from class: androidx.core.graphics.TypefaceCompatBaseImpl.1
            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int b(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.e();
            }

            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public boolean a(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.f();
            }
        });
    }
}
