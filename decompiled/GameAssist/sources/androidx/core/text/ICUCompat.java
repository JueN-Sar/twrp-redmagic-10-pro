package androidx.core.text;

import android.icu.util.ULocale;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.util.Locale;

/* loaded from: classes.dex */
public final class ICUCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static String a(Locale locale) {
            return locale.getScript();
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static ULocale a(Object obj) {
            return ULocale.addLikelySubtags((ULocale) obj);
        }

        @DoNotInline
        static ULocale b(Locale locale) {
            return ULocale.forLocale(locale);
        }

        @DoNotInline
        static String c(Object obj) {
            return ((ULocale) obj).getScript();
        }
    }

    public static String a(Locale locale) {
        return Api24Impl.c(Api24Impl.a(Api24Impl.b(locale)));
    }
}
