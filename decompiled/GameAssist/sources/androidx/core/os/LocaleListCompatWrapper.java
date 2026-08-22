package androidx.core.os;

import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import java.util.Locale;

/* loaded from: classes.dex */
final class LocaleListCompatWrapper implements LocaleListInterface {

    /* renamed from: c, reason: collision with root package name */
    private static final Locale[] f3116c = new Locale[0];

    /* renamed from: d, reason: collision with root package name */
    private static final Locale f3117d = new Locale("en", "XA");

    /* renamed from: e, reason: collision with root package name */
    private static final Locale f3118e = new Locale("ar", "XB");

    /* renamed from: f, reason: collision with root package name */
    private static final Locale f3119f = LocaleListCompat.b("en-Latn");

    /* renamed from: a, reason: collision with root package name */
    private final Locale[] f3120a;

    /* renamed from: b, reason: collision with root package name */
    private final String f3121b;

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static String a(Locale locale) {
            return locale.getScript();
        }
    }

    @VisibleForTesting
    static void toLanguageTag(StringBuilder sb, Locale locale) {
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (country == null || country.isEmpty()) {
            return;
        }
        sb.append('-');
        sb.append(locale.getCountry());
    }

    @Override // androidx.core.os.LocaleListInterface
    public String a() {
        return this.f3121b;
    }

    @Override // androidx.core.os.LocaleListInterface
    public Object b() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocaleListCompatWrapper)) {
            return false;
        }
        Locale[] localeArr = ((LocaleListCompatWrapper) obj).f3120a;
        if (this.f3120a.length != localeArr.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            Locale[] localeArr2 = this.f3120a;
            if (i2 >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i2].equals(localeArr[i2])) {
                return false;
            }
            i2++;
        }
    }

    @Override // androidx.core.os.LocaleListInterface
    public Locale get(int i2) {
        if (i2 >= 0) {
            Locale[] localeArr = this.f3120a;
            if (i2 < localeArr.length) {
                return localeArr[i2];
            }
        }
        return null;
    }

    public int hashCode() {
        int i2 = 1;
        for (Locale locale : this.f3120a) {
            i2 = (i2 * 31) + locale.hashCode();
        }
        return i2;
    }

    @Override // androidx.core.os.LocaleListInterface
    public boolean isEmpty() {
        return this.f3120a.length == 0;
    }

    @Override // androidx.core.os.LocaleListInterface
    public int size() {
        return this.f3120a.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.f3120a;
            if (i2 >= localeArr.length) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(localeArr[i2]);
            if (i2 < this.f3120a.length - 1) {
                sb.append(',');
            }
            i2++;
        }
    }
}
