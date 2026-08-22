package androidx.core.app;

import android.app.GrammaticalInflectionManager;
import android.content.Context;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class GrammaticalInflectionManagerCompat {

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static int a(Context context) {
            return b(context).getApplicationGrammaticalGender();
        }

        private static GrammaticalInflectionManager b(Context context) {
            return (GrammaticalInflectionManager) context.getSystemService(GrammaticalInflectionManager.class);
        }

        @DoNotInline
        static void c(Context context, int i2) {
            b(context).setRequestedApplicationGrammaticalGender(i2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface GrammaticalGender {
    }
}
