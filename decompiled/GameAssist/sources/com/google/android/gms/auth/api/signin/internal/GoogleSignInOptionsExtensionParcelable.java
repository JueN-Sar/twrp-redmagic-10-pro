package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = new zaa();

    /* renamed from: c, reason: collision with root package name */
    final int f10474c;

    /* renamed from: h, reason: collision with root package name */
    private int f10475h;

    /* renamed from: i, reason: collision with root package name */
    private Bundle f10476i;

    GoogleSignInOptionsExtensionParcelable(int i2, int i3, Bundle bundle) {
        this.f10474c = i2;
        this.f10475h = i3;
        this.f10476i = bundle;
    }

    public int G() {
        return this.f10475h;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f10474c);
        SafeParcelWriter.g(parcel, 2, G());
        SafeParcelWriter.d(parcel, 3, this.f10476i, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
