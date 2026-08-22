package androidx.core.content;

import android.content.Intent;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class IntentCompat {

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static <T> T[] a(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
            return (T[]) intent.getParcelableArrayExtra(str, cls);
        }

        @DoNotInline
        static <T> ArrayList<T> b(@NonNull Intent intent, @Nullable String str, @NonNull Class<? extends T> cls) {
            return intent.getParcelableArrayListExtra(str, cls);
        }

        @DoNotInline
        static <T> T c(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
            return (T) intent.getParcelableExtra(str, cls);
        }

        @DoNotInline
        static <T extends Serializable> T d(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
            return (T) intent.getSerializableExtra(str, cls);
        }
    }
}
