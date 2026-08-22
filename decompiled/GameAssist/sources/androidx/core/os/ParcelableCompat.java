package androidx.core.os;

import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
/* loaded from: classes.dex */
public final class ParcelableCompat {

    static class ParcelableCompatCreatorHoneycombMR2<T> implements Parcelable.ClassLoaderCreator<T> {

        /* renamed from: a, reason: collision with root package name */
        private final ParcelableCompatCreatorCallbacks f3123a;

        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel parcel) {
            return this.f3123a.createFromParcel(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int i2) {
            return this.f3123a.newArray(i2);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return this.f3123a.createFromParcel(parcel, classLoader);
        }
    }
}
