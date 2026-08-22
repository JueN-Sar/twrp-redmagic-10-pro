package androidx.core.os;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class BundleCompat {

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static <T> T a(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
            return (T) bundle.getParcelable(str, cls);
        }

        @DoNotInline
        static <T> T[] b(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
            return (T[]) bundle.getParcelableArray(str, cls);
        }

        @DoNotInline
        static <T> ArrayList<T> c(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends T> cls) {
            return bundle.getParcelableArrayList(str, cls);
        }

        @DoNotInline
        static <T extends Serializable> T d(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
            return (T) bundle.getSerializable(str, cls);
        }

        @DoNotInline
        static <T> SparseArray<T> e(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends T> cls) {
            return bundle.getSparseParcelableArray(str, cls);
        }
    }
}
