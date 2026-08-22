package com.google.android.gms.internal.mlkit_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
public abstract class zzaj extends zzab implements Set {

    @CheckForNull
    private transient zzaf zza;

    zzaj() {
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

    @Override // com.google.android.gms.internal.mlkit_common.zzab, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: g */
    public abstract zzas iterator();

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzar.a(this);
    }

    public final zzaf i() {
        zzaf zzafVar = this.zza;
        if (zzafVar != null) {
            return zzafVar;
        }
        zzaf j2 = j();
        this.zza = j2;
        return j2;
    }

    zzaf j() {
        Object[] array = toArray();
        int i2 = zzaf.zzd;
        return zzaf.j(array, array.length);
    }
}
