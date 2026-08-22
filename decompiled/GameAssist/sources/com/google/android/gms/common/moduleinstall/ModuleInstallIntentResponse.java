package com.google.android.gms.common.moduleinstall;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public class ModuleInstallIntentResponse extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleInstallIntentResponse> CREATOR = new zab();

    /* renamed from: c, reason: collision with root package name */
    private final PendingIntent f11138c;

    public ModuleInstallIntentResponse(PendingIntent pendingIntent) {
        this.f11138c = pendingIntent;
    }

    public PendingIntent G() {
        return this.f11138c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.l(parcel, 1, G(), i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
