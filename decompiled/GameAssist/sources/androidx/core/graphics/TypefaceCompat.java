package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat;

/* loaded from: classes.dex */
public class TypefaceCompat {

    /* renamed from: a, reason: collision with root package name */
    private static final TypefaceCompatBaseImpl f2936a = new TypefaceCompatApi29Impl();

    /* renamed from: b, reason: collision with root package name */
    private static final LruCache f2937b = new LruCache(16);

    @RestrictTo
    public static class ResourcesCallbackAdapter extends FontsContractCompat.FontRequestCallback {

        /* renamed from: a, reason: collision with root package name */
        private ResourcesCompat.FontCallback f2938a;

        public ResourcesCallbackAdapter(ResourcesCompat.FontCallback fontCallback) {
            this.f2938a = fontCallback;
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void a(int i2) {
            ResourcesCompat.FontCallback fontCallback = this.f2938a;
            if (fontCallback != null) {
                fontCallback.f(i2);
            }
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void b(Typeface typeface) {
            ResourcesCompat.FontCallback fontCallback = this.f2938a;
            if (fontCallback != null) {
                fontCallback.g(typeface);
            }
        }
    }

    public static Typeface a(Context context, Typeface typeface, int i2) {
        if (context != null) {
            return Typeface.create(typeface, i2);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        return f2936a.c(context, cancellationSignal, fontInfoArr, i2);
    }

    public static Typeface c(Context context, FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, Resources resources, int i2, String str, int i3, int i4, ResourcesCompat.FontCallback fontCallback, Handler handler, boolean z) {
        Typeface b2;
        if (familyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry = (FontResourcesParserCompat.ProviderResourceEntry) familyResourceEntry;
            Typeface g2 = g(providerResourceEntry.c());
            if (g2 != null) {
                if (fontCallback != null) {
                    fontCallback.d(g2, handler);
                }
                return g2;
            }
            b2 = FontsContractCompat.c(context, providerResourceEntry.b(), i4, !z ? fontCallback != null : providerResourceEntry.a() != 0, z ? providerResourceEntry.d() : -1, ResourcesCompat.FontCallback.e(handler), new ResourcesCallbackAdapter(fontCallback));
        } else {
            b2 = f2936a.b(context, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) familyResourceEntry, resources, i4);
            if (fontCallback != null) {
                if (b2 != null) {
                    fontCallback.d(b2, handler);
                } else {
                    fontCallback.c(-3, handler);
                }
            }
        }
        if (b2 != null) {
            f2937b.e(e(resources, i2, str, i3, i4), b2);
        }
        return b2;
    }

    @RestrictTo
    @VisibleForTesting
    public static void clearCache() {
        f2937b.c();
    }

    public static Typeface d(Context context, Resources resources, int i2, String str, int i3, int i4) {
        Typeface e2 = f2936a.e(context, resources, i2, str, i4);
        if (e2 != null) {
            f2937b.e(e(resources, i2, str, i3, i4), e2);
        }
        return e2;
    }

    private static String e(Resources resources, int i2, String str, int i3, int i4) {
        return resources.getResourcePackageName(i2) + '-' + str + '-' + i3 + '-' + i2 + '-' + i4;
    }

    public static Typeface f(Resources resources, int i2, String str, int i3, int i4) {
        return (Typeface) f2937b.d(e(resources, i2, str, i3, i4));
    }

    private static Typeface g(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Typeface create = Typeface.create(str, 0);
        Typeface create2 = Typeface.create(Typeface.DEFAULT, 0);
        if (create == null || create.equals(create2)) {
            return null;
        }
        return create;
    }
}
