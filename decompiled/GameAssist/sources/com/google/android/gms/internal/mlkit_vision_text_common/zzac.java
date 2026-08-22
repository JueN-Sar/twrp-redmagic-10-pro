package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzac implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    final Iterator f13090c;

    /* renamed from: h, reason: collision with root package name */
    Collection f13091h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zzad f13092i;

    zzac(zzad zzadVar) {
        this.f13092i = zzadVar;
        this.f13090c = zzadVar.f13093j.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13090c.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f13090c.next();
        this.f13091h = (Collection) entry.getValue();
        Object key = entry.getKey();
        return new zzbg(key, this.f13092i.f13094k.h(key, (Collection) entry.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i2;
        zzx.d(this.f13091h != null, "no calls to next() since the last call to remove()");
        this.f13090c.remove();
        zzal zzalVar = this.f13092i.f13094k;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 - this.f13091h.size();
        this.f13091h.clear();
        this.f13091h = null;
    }
}
