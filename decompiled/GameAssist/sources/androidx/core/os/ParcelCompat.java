package androidx.core.os;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class ParcelCompat {

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static <T extends Parcelable> List<T> a(@NonNull Parcel parcel, @NonNull List<T> list, @Nullable ClassLoader classLoader) {
            return parcel.readParcelableList(list, classLoader);
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static Parcelable.Creator<?> a(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            return parcel.readParcelableCreator(classLoader);
        }
    }

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static <T> T[] a(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T[]) parcel.readArray(classLoader, cls);
        }

        @DoNotInline
        static <T> ArrayList<T> b(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
            return parcel.readArrayList(classLoader, cls);
        }

        @DoNotInline
        static <V, K> HashMap<K, V> c(Parcel parcel, ClassLoader classLoader, Class<? extends K> cls, Class<? extends V> cls2) {
            return parcel.readHashMap(classLoader, cls, cls2);
        }

        @DoNotInline
        static <T> void d(@NonNull Parcel parcel, @NonNull List<? super T> list, @Nullable ClassLoader classLoader, @NonNull Class<T> cls) {
            parcel.readList(list, classLoader, cls);
        }

        @DoNotInline
        static <K, V> void e(Parcel parcel, Map<? super K, ? super V> map, ClassLoader classLoader, Class<K> cls, Class<V> cls2) {
            parcel.readMap(map, classLoader, cls, cls2);
        }

        @DoNotInline
        static <T extends Parcelable> T f(@NonNull Parcel parcel, @Nullable ClassLoader classLoader, @NonNull Class<T> cls) {
            return (T) parcel.readParcelable(classLoader, cls);
        }

        @DoNotInline
        static <T> T[] g(@NonNull Parcel parcel, @Nullable ClassLoader classLoader, @NonNull Class<T> cls) {
            return (T[]) parcel.readParcelableArray(classLoader, cls);
        }

        @DoNotInline
        static <T> Parcelable.Creator<T> h(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return parcel.readParcelableCreator(classLoader, cls);
        }

        @DoNotInline
        static <T> List<T> i(@NonNull Parcel parcel, @NonNull List<T> list, @Nullable ClassLoader classLoader, @NonNull Class<T> cls) {
            return parcel.readParcelableList(list, classLoader, cls);
        }

        @DoNotInline
        static <T extends Serializable> T j(@NonNull Parcel parcel, @Nullable ClassLoader classLoader, @NonNull Class<T> cls) {
            return (T) parcel.readSerializable(classLoader, cls);
        }

        @DoNotInline
        static <T> SparseArray<T> k(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
            return parcel.readSparseArray(classLoader, cls);
        }
    }
}
