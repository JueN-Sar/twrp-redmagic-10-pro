package androidx.core.content.pm;

import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class ShortcutInfoCompat {

    /* renamed from: a, reason: collision with root package name */
    IconCompat f2855a;

    @RequiresApi
    private static class Api33Impl {
    }

    public static class Builder {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Surface {
    }

    @RequiresApi
    @RestrictTo
    @VisibleForTesting
    static boolean getLongLivedFromExtra(@Nullable PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey("extraLongLived")) {
            return false;
        }
        return persistableBundle.getBoolean("extraLongLived");
    }

    @VisibleForTesting
    @Nullable
    @RequiresApi
    @RestrictTo
    static Person[] getPersonsFromExtra(@NonNull PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey("extraPersonCount")) {
            return null;
        }
        int i2 = persistableBundle.getInt("extraPersonCount");
        Person[] personArr = new Person[i2];
        int i3 = 0;
        while (i3 < i2) {
            StringBuilder sb = new StringBuilder();
            sb.append("extraPerson_");
            int i4 = i3 + 1;
            sb.append(i4);
            personArr[i3] = Person.a(persistableBundle.getPersistableBundle(sb.toString()));
            i3 = i4;
        }
        return personArr;
    }
}
