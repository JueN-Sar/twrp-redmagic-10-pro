package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
public abstract class zzs extends zzl implements Set {

    @CheckForNull
    private transient zzp zza;

    zzs() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: g */
    public abstract zzab iterator();

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzaa.a(this);
    }

    public final zzp i() {
        zzp zzpVar = this.zza;
        if (zzpVar != null) {
            return zzpVar;
        }
        zzp j2 = j();
        this.zza = j2;
        return j2;
    }

    zzp j() {
        return zzp.j(toArray());
    }
}
