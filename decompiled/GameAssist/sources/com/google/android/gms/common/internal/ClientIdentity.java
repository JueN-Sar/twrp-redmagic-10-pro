package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public class ClientIdentity extends AbstractSafeParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<ClientIdentity> CREATOR = new zaa();

    /* renamed from: c, reason: collision with root package name */
    public final int f10965c;

    /* renamed from: h, reason: collision with root package name */
    public final String f10966h;

    public ClientIdentity(int i2, String str) {
        this.f10965c = i2;
        this.f10966h = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientIdentity)) {
            return false;
        }
        ClientIdentity clientIdentity = (ClientIdentity) obj;
        return clientIdentity.f10965c == this.f10965c && Objects.a(clientIdentity.f10966h, this.f10966h);
    }

    public final int hashCode() {
        return this.f10965c;
    }

    public final String toString() {
        return this.f10965c + ":" + this.f10966h;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f10965c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.m(parcel, 2, this.f10966h, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
