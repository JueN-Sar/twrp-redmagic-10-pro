package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzae implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    Map.Entry f13095c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Iterator f13096h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zzaf f13097i;

    zzae(zzaf zzafVar, Iterator it) {
        this.f13096h = it;
        this.f13097i = zzafVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13096h.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.f13096h.next();
        this.f13095c = entry;
        return entry.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i2;
        zzx.d(this.f13095c != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.f13095c.getValue();
        this.f13096h.remove();
        zzal zzalVar = this.f13097i.f13098h;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 - collection.size();
        collection.clear();
        this.f13095c = null;
    }
}
