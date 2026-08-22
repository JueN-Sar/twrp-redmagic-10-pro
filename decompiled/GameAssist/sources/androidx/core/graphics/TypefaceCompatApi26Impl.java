package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {

    /* renamed from: g, reason: collision with root package name */
    protected final Class f2948g;

    /* renamed from: h, reason: collision with root package name */
    protected final Constructor f2949h;

    /* renamed from: i, reason: collision with root package name */
    protected final Method f2950i;

    /* renamed from: j, reason: collision with root package name */
    protected final Method f2951j;

    /* renamed from: k, reason: collision with root package name */
    protected final Method f2952k;

    /* renamed from: l, reason: collision with root package name */
    protected final Method f2953l;

    /* renamed from: m, reason: collision with root package name */
    protected final Method f2954m;

    private Object o() {
        try {
            return this.f2949h.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private void p(Object obj) {
        try {
            this.f2953l.invoke(obj, null);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    private boolean q(Context context, Object obj, String str, int i2, int i3, int i4, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f2950i.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean r(Object obj, ByteBuffer byteBuffer, int i2, int i3, int i4) {
        try {
            return ((Boolean) this.f2951j.invoke(obj, byteBuffer, Integer.valueOf(i2), null, Integer.valueOf(i3), Integer.valueOf(i4))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean s(Object obj) {
        try {
            return ((Boolean) this.f2952k.invoke(obj, null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean t() {
        if (this.f2950i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.f2950i != null;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface b(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i2) {
        if (!t()) {
            return super.b(context, fontFamilyFilesResourceEntry, resources, i2);
        }
        Object o2 = o();
        if (o2 == null) {
            return null;
        }
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.a()) {
            if (!q(context, o2, fontFileResourceEntry.a(), fontFileResourceEntry.c(), fontFileResourceEntry.e(), fontFileResourceEntry.f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontFileResourceEntry.d()))) {
                p(o2);
                return null;
            }
        }
        if (s(o2)) {
            return l(o2);
        }
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface c(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        Typeface l2;
        if (fontInfoArr.length < 1) {
            return null;
        }
        if (!t()) {
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
                    Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(i3.e()).setItalic(i3.f()).build();
                    openFileDescriptor.close();
                    return build;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        Map h2 = TypefaceCompatUtil.h(context, fontInfoArr, cancellationSignal);
        Object o2 = o();
        if (o2 == null) {
            return null;
        }
        boolean z = false;
        for (FontsContractCompat.FontInfo fontInfo : fontInfoArr) {
            ByteBuffer byteBuffer = (ByteBuffer) h2.get(fontInfo.d());
            if (byteBuffer != null) {
                if (!r(o2, byteBuffer, fontInfo.c(), fontInfo.e(), fontInfo.f() ? 1 : 0)) {
                    p(o2);
                    return null;
                }
                z = true;
            }
        }
        if (!z) {
            p(o2);
            return null;
        }
        if (s(o2) && (l2 = l(o2)) != null) {
            return Typeface.create(l2, i2);
        }
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface e(Context context, Resources resources, int i2, String str, int i3) {
        if (!t()) {
            return super.e(context, resources, i2, str, i3);
        }
        Object o2 = o();
        if (o2 == null) {
            return null;
        }
        if (!q(context, o2, str, 0, -1, -1, null)) {
            p(o2);
            return null;
        }
        if (s(o2)) {
            return l(o2);
        }
        return null;
    }

    protected Typeface l(Object obj) {
        try {
            Object newInstance = Array.newInstance((Class<?>) this.f2948g, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f2954m.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }
}
