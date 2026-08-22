package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    public static final Parcelable.Creator<Scope> CREATOR = new zza();

    /* renamed from: c, reason: collision with root package name */
    final int f10540c;

    /* renamed from: h, reason: collision with root package name */
    private final String f10541h;

    Scope(int i2, String str) {
        Preconditions.g(str, "scopeUri must not be null or empty");
        this.f10540c = i2;
        this.f10541h = str;
    }

    public String G() {
        return this.f10541h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return this.f10541h.equals(((Scope) obj).f10541h);
        }
        return false;
    }

    public int hashCode() {
        return this.f10541h.hashCode();
    }

    public String toString() {
        return this.f10541h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f10540c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.m(parcel, 2, G(), false);
        SafeParcelWriter.b(parcel, a2);
    }

    public Scope(String str) {
        this(1, str);
    }
}
