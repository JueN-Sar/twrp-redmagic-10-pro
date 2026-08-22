package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.zte.distbus.basetransfer.Constants;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class Feature extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<Feature> CREATOR = new zzc();

    /* renamed from: c, reason: collision with root package name */
    private final String f10492c;

    /* renamed from: h, reason: collision with root package name */
    private final int f10493h;

    /* renamed from: i, reason: collision with root package name */
    private final long f10494i;

    public Feature(String str, int i2, long j2) {
        this.f10492c = str;
        this.f10493h = i2;
        this.f10494i = j2;
    }

    public String G() {
        return this.f10492c;
    }

    public long P() {
        long j2 = this.f10494i;
        return j2 == -1 ? this.f10493h : j2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            if (((G() != null && G().equals(feature.G())) || (G() == null && feature.G() == null)) && P() == feature.P()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.b(G(), Long.valueOf(P()));
    }

    public final String toString() {
        Objects.ToStringHelper c2 = Objects.c(this);
        c2.a("name", G());
        c2.a(Constants.EXTRA_VERSION, Long.valueOf(P()));
        return c2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, G(), false);
        SafeParcelWriter.g(parcel, 2, this.f10493h);
        SafeParcelWriter.i(parcel, 3, P());
        SafeParcelWriter.b(parcel, a2);
    }

    public Feature(String str, long j2) {
        this.f10492c = str;
        this.f10494i = j2;
        this.f10493h = -1;
    }
}
