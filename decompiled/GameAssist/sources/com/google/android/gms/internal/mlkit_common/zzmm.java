package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
public enum zzmm implements zzba {
    UNKNOWN(0),
    TRANSLATE(1);

    private final int zzd;

    zzmm(int i2) {
        this.zzd = i2;
    }

    public static zzmm c(int i2) {
        for (zzmm zzmmVar : values()) {
            if (zzmmVar.zzd == i2) {
                return zzmmVar;
            }
        }
        return UNKNOWN;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzba
    public final int zza() {
        return this.zzd;
    }
}
