package androidx.core.os;

import android.os.Parcel;

@Deprecated
/* loaded from: classes.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    Object createFromParcel(Parcel parcel, ClassLoader classLoader);

    Object[] newArray(int i2);
}
