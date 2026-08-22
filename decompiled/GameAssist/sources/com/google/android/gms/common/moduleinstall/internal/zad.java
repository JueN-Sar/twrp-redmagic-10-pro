package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;

/* loaded from: classes.dex */
public abstract class zad extends com.google.android.gms.internal.base.zab implements zae {
    public zad() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            Status status = (Status) com.google.android.gms.internal.base.zac.a(parcel, Status.CREATOR);
            ModuleAvailabilityResponse moduleAvailabilityResponse = (ModuleAvailabilityResponse) com.google.android.gms.internal.base.zac.a(parcel, ModuleAvailabilityResponse.CREATOR);
            com.google.android.gms.internal.base.zac.b(parcel);
            zae(status, moduleAvailabilityResponse);
        } else if (i2 == 2) {
            Status status2 = (Status) com.google.android.gms.internal.base.zac.a(parcel, Status.CREATOR);
            ModuleInstallResponse moduleInstallResponse = (ModuleInstallResponse) com.google.android.gms.internal.base.zac.a(parcel, ModuleInstallResponse.CREATOR);
            com.google.android.gms.internal.base.zac.b(parcel);
            zad(status2, moduleInstallResponse);
        } else if (i2 == 3) {
            Status status3 = (Status) com.google.android.gms.internal.base.zac.a(parcel, Status.CREATOR);
            ModuleInstallIntentResponse moduleInstallIntentResponse = (ModuleInstallIntentResponse) com.google.android.gms.internal.base.zac.a(parcel, ModuleInstallIntentResponse.CREATOR);
            com.google.android.gms.internal.base.zac.b(parcel);
            zac(status3, moduleInstallIntentResponse);
        } else {
            if (i2 != 4) {
                return false;
            }
            Status status4 = (Status) com.google.android.gms.internal.base.zac.a(parcel, Status.CREATOR);
            com.google.android.gms.internal.base.zac.b(parcel);
            zab(status4);
        }
        return true;
    }
}
