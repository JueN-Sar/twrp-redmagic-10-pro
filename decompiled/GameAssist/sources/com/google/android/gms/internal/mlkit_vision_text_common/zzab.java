package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes.dex */
final class zzab extends zzbx {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzad f13089c;

    zzab(zzad zzadVar) {
        this.f13089c = zzadVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbx
    final Map b() {
        return this.f13089c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbx, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Set entrySet = this.f13089c.f13093j.entrySet();
        entrySet.getClass();
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzac(this.f13089c);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbx, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Objects.requireNonNull(entry);
        zzad zzadVar = this.f13089c;
        zzal.n(zzadVar.f13094k, entry.getKey());
        return true;
    }
}
