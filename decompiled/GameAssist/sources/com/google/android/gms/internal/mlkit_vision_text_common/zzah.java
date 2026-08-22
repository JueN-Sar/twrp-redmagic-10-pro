package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class zzah implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    final Iterator f13099c;

    /* renamed from: h, reason: collision with root package name */
    final Collection f13100h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zzai f13101i;

    zzah(zzai zzaiVar, Iterator it) {
        this.f13101i = zzaiVar;
        this.f13100h = zzaiVar.f13103h;
        this.f13099c = it;
    }

    final void b() {
        this.f13101i.d();
        if (this.f13101i.f13103h != this.f13100h) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        b();
        return this.f13099c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        b();
        return this.f13099c.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i2;
        this.f13099c.remove();
        zzal zzalVar = this.f13101i.f13106k;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 - 1;
        this.f13101i.f();
    }

    zzah(zzai zzaiVar) {
        this.f13101i = zzaiVar;
        Collection collection = zzaiVar.f13103h;
        this.f13100h = collection;
        this.f13099c = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }
}
