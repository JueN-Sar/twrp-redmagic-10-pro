package androidx.core.app;

import android.app.LocaleManager;
import android.content.res.Configuration;
import android.os.LocaleList;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.LocaleListCompat;
import java.util.Locale;

/* loaded from: classes.dex */
public final class LocaleManagerCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static String a(Locale locale) {
            return locale.toLanguageTag();
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static LocaleListCompat a(Configuration configuration) {
            return LocaleListCompat.c(configuration.getLocales().toLanguageTags());
        }
    }

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static LocaleList a(Object obj) {
            return ((LocaleManager) obj).getApplicationLocales();
        }

        @DoNotInline
        static LocaleList b(Object obj) {
            return ((LocaleManager) obj).getSystemLocales();
        }
    }

    @VisibleForTesting
    static LocaleListCompat getConfigurationLocales(Configuration configuration) {
        return Api24Impl.a(configuration);
    }
}
