package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzaz extends AbstractCollection {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzba f13121c;

    zzaz(zzba zzbaVar) {
        this.f13121c = zzbaVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f13121c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzba zzbaVar = this.f13121c;
        Map o2 = zzbaVar.o();
        return o2 != null ? o2.values().iterator() : new zzat(zzbaVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f13121c.size();
    }
}
