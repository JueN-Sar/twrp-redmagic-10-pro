package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    private Font k(FontFamily fontFamily, int i2) {
        FontStyle fontStyle = new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int l2 = l(fontStyle, font.getStyle());
        for (int i3 = 1; i3 < fontFamily.getSize(); i3++) {
            Font font2 = fontFamily.getFont(i3);
            int l3 = l(fontStyle, font2.getStyle());
            if (l3 < l2) {
                font = font2;
                l2 = l3;
            }
        }
        return font;
    }

    private static int l(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface b(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i2) {
        try {
            FontFamily.Builder builder = null;
            for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.a()) {
                try {
                    Font build = new Font.Builder(resources, fontFileResourceEntry.b()).setWeight(fontFileResourceEntry.e()).setSlant(fontFileResourceEntry.f() ? 1 : 0).setTtcIndex(fontFileResourceEntry.c()).setFontVariationSettings(fontFileResourceEntry.d()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(k(build2, i2).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface c(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        int i3;
        ParcelFileDescriptor openFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int length = fontInfoArr.length;
            FontFamily.Builder builder = null;
            while (i3 < length) {
                FontsContractCompat.FontInfo fontInfo = fontInfoArr[i3];
                try {
                    openFileDescriptor = contentResolver.openFileDescriptor(fontInfo.d(), "r", cancellationSignal);
                } catch (IOException unused) {
                }
                if (openFileDescriptor == null) {
                    i3 = openFileDescriptor == null ? i3 + 1 : 0;
                } else {
                    try {
                        Font build = new Font.Builder(openFileDescriptor).setWeight(fontInfo.e()).setSlant(fontInfo.f() ? 1 : 0).setTtcIndex(fontInfo.c()).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(build);
                        } else {
                            builder.addFont(build);
                        }
                    } catch (Throwable th) {
                        try {
                            openFileDescriptor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                openFileDescriptor.close();
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(k(build2, i2).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected Typeface d(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface e(Context context, Resources resources, int i2, String str, int i3) {
        try {
            Font build = new Font.Builder(resources, i2).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected FontsContractCompat.FontInfo i(FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
