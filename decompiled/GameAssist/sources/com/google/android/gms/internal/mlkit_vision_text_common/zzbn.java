package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
public abstract class zzbn extends zzbf implements Set {

    @CheckForNull
    private transient zzbk zza;

    zzbn() {
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

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: g */
    public abstract zzco iterator();

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzcl.a(this);
    }

    public final zzbk i() {
        zzbk zzbkVar = this.zza;
        if (zzbkVar != null) {
            return zzbkVar;
        }
        zzbk j2 = j();
        this.zza = j2;
        return j2;
    }

    zzbk j() {
        Object[] array = toArray();
        int i2 = zzbk.zzd;
        return zzbk.j(array, array.length);
    }
}
