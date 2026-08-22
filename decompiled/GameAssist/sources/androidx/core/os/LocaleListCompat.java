package androidx.core.os;

import android.os.LocaleList;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.text.ICUCompat;
import java.util.Locale;

/* loaded from: classes.dex */
public final class LocaleListCompat {

    /* renamed from: b, reason: collision with root package name */
    private static final LocaleListCompat f3113b = a(new Locale[0]);

    /* renamed from: a, reason: collision with root package name */
    private final LocaleListInterface f3114a;

    @RequiresApi
    static class Api21Impl {

        /* renamed from: a, reason: collision with root package name */
        private static final Locale[] f3115a = {new Locale("en", "XA"), new Locale("ar", "XB")};

        @DoNotInline
        static Locale a(String str) {
            return Locale.forLanguageTag(str);
        }

        private static boolean b(Locale locale) {
            for (Locale locale2 : f3115a) {
                if (locale2.equals(locale)) {
                    return true;
                }
            }
            return false;
        }

        @DoNotInline
        static boolean c(@NonNull Locale locale, @NonNull Locale locale2) {
            if (locale.equals(locale2)) {
                return true;
            }
            if (!locale.getLanguage().equals(locale2.getLanguage()) || b(locale) || b(locale2)) {
                return false;
            }
            String a2 = ICUCompat.a(locale);
            if (!a2.isEmpty()) {
                return a2.equals(ICUCompat.a(locale2));
            }
            String country = locale.getCountry();
            return country.isEmpty() || country.equals(locale2.getCountry());
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static LocaleList a(Locale... localeArr) {
            return new LocaleList(localeArr);
        }

        @DoNotInline
        static LocaleList b() {
            return LocaleList.getAdjustedDefault();
        }

        @DoNotInline
        static LocaleList c() {
            return LocaleList.getDefault();
        }
    }

    private LocaleListCompat(LocaleListInterface localeListInterface) {
        this.f3114a = localeListInterface;
    }

    public static LocaleListCompat a(Locale... localeArr) {
        return j(Api24Impl.a(localeArr));
    }

    static Locale b(String str) {
        if (str.contains("-")) {
            String[] split = str.split("-", -1);
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        } else {
            if (!str.contains("_")) {
                return new Locale(str);
            }
            String[] split2 = str.split("_", -1);
            if (split2.length > 2) {
                return new Locale(split2[0], split2[1], split2[2]);
            }
            if (split2.length > 1) {
                return new Locale(split2[0], split2[1]);
            }
            if (split2.length == 1) {
                return new Locale(split2[0]);
            }
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    public static LocaleListCompat c(String str) {
        if (str == null || str.isEmpty()) {
            return e();
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i2 = 0; i2 < length; i2++) {
            localeArr[i2] = Api21Impl.a(split[i2]);
        }
        return a(localeArr);
    }

    public static LocaleListCompat e() {
        return f3113b;
    }

    public static LocaleListCompat j(LocaleList localeList) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList));
    }

    public Locale d(int i2) {
        return this.f3114a.get(i2);
    }

    public boolean equals(Object obj) {
        return (obj instanceof LocaleListCompat) && this.f3114a.equals(((LocaleListCompat) obj).f3114a);
    }

    public boolean f() {
        return this.f3114a.isEmpty();
    }

    public int g() {
        return this.f3114a.size();
    }

    public String h() {
        return this.f3114a.a();
    }

    public int hashCode() {
        return this.f3114a.hashCode();
    }

    public Object i() {
        return this.f3114a.b();
    }

    public String toString() {
        return this.f3114a.toString();
    }
}
