package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zbuu implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f12987c;

    public zbuu(Iterator it) {
        this.f12987c = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f12987c.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f12987c.next();
        return entry.getValue() instanceof zbuv ? new zbut(entry, null) : entry;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f12987c.remove();
    }
}
