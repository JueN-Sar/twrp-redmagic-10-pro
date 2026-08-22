package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzax extends AbstractSet {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzba f13117c;

    zzax(zzba zzbaVar) {
        this.f13117c = zzbaVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f13117c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f13117c.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzba zzbaVar = this.f13117c;
        Map o2 = zzbaVar.o();
        return o2 != null ? o2.keySet().iterator() : new zzar(zzbaVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        Object B;
        Object obj2;
        Map o2 = this.f13117c.o();
        if (o2 != null) {
            return o2.keySet().remove(obj);
        }
        B = this.f13117c.B(obj);
        obj2 = zzba.zzd;
        return B != obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13117c.size();
    }
}
