package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;

/* loaded from: classes.dex */
public abstract class zag extends com.google.android.gms.internal.base.zab implements zah {
    public zag() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallStatusListener");
    }

    @Override // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 1) {
            return false;
        }
        ModuleInstallStatusUpdate moduleInstallStatusUpdate = (ModuleInstallStatusUpdate) com.google.android.gms.internal.base.zac.a(parcel, ModuleInstallStatusUpdate.CREATOR);
        com.google.android.gms.internal.base.zac.b(parcel);
        zab(moduleInstallStatusUpdate);
        return true;
    }
}
