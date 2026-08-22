package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzau extends AbstractSet {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzba f13112c;

    zzau(zzba zzbaVar) {
        this.f13112c = zzbaVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f13112c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int z;
        Map o2 = this.f13112c.o();
        if (o2 != null) {
            return o2.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            z = this.f13112c.z(entry.getKey());
            if (z != -1 && zzw.a(zzba.m(this.f13112c, z), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzba zzbaVar = this.f13112c;
        Map o2 = zzbaVar.o();
        return o2 != null ? o2.entrySet().iterator() : new zzas(zzbaVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int y;
        int[] a2;
        Object[] b2;
        Object[] c2;
        int i2;
        Map o2 = this.f13112c.o();
        if (o2 != null) {
            return o2.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        zzba zzbaVar = this.f13112c;
        if (zzbaVar.u()) {
            return false;
        }
        y = zzbaVar.y();
        Object key = entry.getKey();
        Object value = entry.getValue();
        zzba zzbaVar2 = this.f13112c;
        Object l2 = zzba.l(zzbaVar2);
        a2 = zzbaVar2.a();
        b2 = zzbaVar2.b();
        c2 = zzbaVar2.c();
        int b3 = zzbb.b(key, value, y, l2, a2, b2, c2);
        if (b3 == -1) {
            return false;
        }
        this.f13112c.t(b3, y);
        zzba zzbaVar3 = this.f13112c;
        i2 = zzbaVar3.zzg;
        zzbaVar3.zzg = i2 - 1;
        this.f13112c.r();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13112c.size();
    }
}
