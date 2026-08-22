package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class
/* loaded from: classes.dex */
public class ModuleAvailabilityResponse extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleAvailabilityResponse> CREATOR = new zaa();

    /* renamed from: c, reason: collision with root package name */
    private final boolean f11136c;

    /* renamed from: h, reason: collision with root package name */
    private final int f11137h;

    @Retention(RetentionPolicy.CLASS)
    public @interface AvailabilityStatus {
    }

    public ModuleAvailabilityResponse(boolean z, int i2) {
        this.f11136c = z;
        this.f11137h = i2;
    }

    public boolean G() {
        return this.f11136c;
    }

    public int P() {
        return this.f11137h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.c(parcel, 1, G());
        SafeParcelWriter.g(parcel, 2, P());
        SafeParcelWriter.b(parcel, a2);
    }
}
