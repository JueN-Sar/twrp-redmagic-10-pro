package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.provider.FontRequest;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
public class FontResourcesParserCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static int a(TypedArray typedArray, int i2) {
            return typedArray.getType(i2);
        }
    }

    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FetchStrategy {
    }

    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {

        /* renamed from: a, reason: collision with root package name */
        private final FontFileResourceEntry[] f2877a;

        public FontFamilyFilesResourceEntry(FontFileResourceEntry[] fontFileResourceEntryArr) {
            this.f2877a = fontFileResourceEntryArr;
        }

        public FontFileResourceEntry[] a() {
            return this.f2877a;
        }
    }

    public static final class FontFileResourceEntry {

        /* renamed from: a, reason: collision with root package name */
        private final String f2878a;

        /* renamed from: b, reason: collision with root package name */
        private final int f2879b;

        /* renamed from: c, reason: collision with root package name */
        private final boolean f2880c;

        /* renamed from: d, reason: collision with root package name */
        private final String f2881d;

        /* renamed from: e, reason: collision with root package name */
        private final int f2882e;

        /* renamed from: f, reason: collision with root package name */
        private final int f2883f;

        public FontFileResourceEntry(String str, int i2, boolean z, String str2, int i3, int i4) {
            this.f2878a = str;
            this.f2879b = i2;
            this.f2880c = z;
            this.f2881d = str2;
            this.f2882e = i3;
            this.f2883f = i4;
        }

        public String a() {
            return this.f2878a;
        }

        public int b() {
            return this.f2883f;
        }

        public int c() {
            return this.f2882e;
        }

        public String d() {
            return this.f2881d;
        }

        public int e() {
            return this.f2879b;
        }

        public boolean f() {
            return this.f2880c;
        }
    }

    public static final class ProviderResourceEntry implements FamilyResourceEntry {

        /* renamed from: a, reason: collision with root package name */
        private final FontRequest f2884a;

        /* renamed from: b, reason: collision with root package name */
        private final int f2885b;

        /* renamed from: c, reason: collision with root package name */
        private final int f2886c;

        /* renamed from: d, reason: collision with root package name */
        private final String f2887d;

        public ProviderResourceEntry(FontRequest fontRequest, int i2, int i3, String str) {
            this.f2884a = fontRequest;
            this.f2886c = i2;
            this.f2885b = i3;
            this.f2887d = str;
        }

        public int a() {
            return this.f2886c;
        }

        public FontRequest b() {
            return this.f2884a;
        }

        public String c() {
            return this.f2887d;
        }

        public int d() {
            return this.f2885b;
        }
    }

    private static int a(TypedArray typedArray, int i2) {
        return Api21Impl.a(typedArray, i2);
    }

    public static FamilyResourceEntry b(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return d(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List c(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(obtainTypedArray, 0) == 1) {
                for (int i3 = 0; i3 < obtainTypedArray.length(); i3++) {
                    int resourceId = obtainTypedArray.getResourceId(i3, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i2)));
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static FamilyResourceEntry d(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return e(xmlPullParser, resources);
        }
        g(xmlPullParser);
        return null;
    }

    private static FamilyResourceEntry e(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.FontFamily);
        String string = obtainAttributes.getString(R.styleable.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(R.styleable.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(R.styleable.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(R.styleable.FontFamily_fontProviderFetchTimeout, 500);
        String string4 = obtainAttributes.getString(R.styleable.FontFamily_fontProviderSystemFontFamily);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new ProviderResourceEntry(new FontRequest(string, string2, string3, c(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(f(xmlPullParser, resources));
                } else {
                    g(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) arrayList.toArray(new FontFileResourceEntry[0]));
    }

    private static FontFileResourceEntry f(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.FontFamilyFont);
        int i2 = obtainAttributes.getInt(obtainAttributes.hasValue(R.styleable.FontFamilyFont_fontWeight) ? R.styleable.FontFamilyFont_fontWeight : R.styleable.FontFamilyFont_android_fontWeight, 400);
        boolean z = 1 == obtainAttributes.getInt(obtainAttributes.hasValue(R.styleable.FontFamilyFont_fontStyle) ? R.styleable.FontFamilyFont_fontStyle : R.styleable.FontFamilyFont_android_fontStyle, 0);
        int i3 = obtainAttributes.hasValue(R.styleable.FontFamilyFont_ttcIndex) ? R.styleable.FontFamilyFont_ttcIndex : R.styleable.FontFamilyFont_android_ttcIndex;
        String string = obtainAttributes.getString(obtainAttributes.hasValue(R.styleable.FontFamilyFont_fontVariationSettings) ? R.styleable.FontFamilyFont_fontVariationSettings : R.styleable.FontFamilyFont_android_fontVariationSettings);
        int i4 = obtainAttributes.getInt(i3, 0);
        int i5 = obtainAttributes.hasValue(R.styleable.FontFamilyFont_font) ? R.styleable.FontFamilyFont_font : R.styleable.FontFamilyFont_android_font;
        int resourceId = obtainAttributes.getResourceId(i5, 0);
        String string2 = obtainAttributes.getString(i5);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new FontFileResourceEntry(string2, i2, z, string, i4, resourceId);
    }

    private static void g(XmlPullParser xmlPullParser) {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    private static List h(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
