package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class ConfigurationCompat {

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static LocaleList a(Configuration configuration) {
            return configuration.getLocales();
        }

        @DoNotInline
        static void b(@NonNull Configuration configuration, @NonNull LocaleListCompat localeListCompat) {
            configuration.setLocales((LocaleList) localeListCompat.i());
        }
    }

    public static LocaleListCompat a(Configuration configuration) {
        return LocaleListCompat.j(Api24Impl.a(configuration));
    }
}
