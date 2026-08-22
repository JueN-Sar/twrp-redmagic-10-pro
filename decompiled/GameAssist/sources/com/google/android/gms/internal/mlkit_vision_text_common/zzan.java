package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
abstract class zzan implements zzcc {

    @CheckForNull
    private transient Set zza;

    @CheckForNull
    private transient Map zzb;

    zzan() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcc
    public final Map c() {
        Map map = this.zzb;
        if (map != null) {
            return map;
        }
        Map e2 = e();
        this.zzb = e2;
        return e2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcc
    public final Set d() {
        Set set = this.zza;
        if (set != null) {
            return set;
        }
        Set f2 = f();
        this.zza = f2;
        return f2;
    }

    abstract Map e();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcc) {
            return c().equals(((zzcc) obj).c());
        }
        return false;
    }

    abstract Set f();

    public final int hashCode() {
        return c().hashCode();
    }

    public final String toString() {
        return c().toString();
    }
}
