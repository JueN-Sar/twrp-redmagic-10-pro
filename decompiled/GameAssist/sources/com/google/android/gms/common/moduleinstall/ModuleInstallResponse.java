package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public class ModuleInstallResponse extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleInstallResponse> CREATOR = new zad();

    /* renamed from: c, reason: collision with root package name */
    private final int f11145c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f11146h;

    public ModuleInstallResponse(int i2) {
        this(i2, false);
    }

    public int G() {
        return this.f11145c;
    }

    public final boolean P() {
        return this.f11146h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, G());
        SafeParcelWriter.c(parcel, 2, this.f11146h);
        SafeParcelWriter.b(parcel, a2);
    }

    public ModuleInstallResponse(int i2, boolean z) {
        this.f11145c = i2;
        this.f11146h = z;
    }
}
