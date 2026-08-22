package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
abstract class zzaw implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    int f13113c;

    /* renamed from: h, reason: collision with root package name */
    int f13114h;

    /* renamed from: i, reason: collision with root package name */
    int f13115i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ zzba f13116j;

    /* synthetic */ zzaw(zzba zzbaVar, zzav zzavVar) {
        int i2;
        this.f13116j = zzbaVar;
        i2 = zzbaVar.zzf;
        this.f13113c = i2;
        this.f13114h = zzbaVar.h();
        this.f13115i = -1;
    }

    private final void c() {
        int i2;
        i2 = this.f13116j.zzf;
        if (i2 != this.f13113c) {
            throw new ConcurrentModificationException();
        }
    }

    abstract Object b(int i2);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13114h >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f13114h;
        this.f13115i = i2;
        Object b2 = b(i2);
        this.f13114h = this.f13116j.i(this.f13114h);
        return b2;
    }

    @Override // java.util.Iterator
    public final void remove() {
        c();
        zzx.d(this.f13115i >= 0, "no calls to next() since the last call to remove()");
        this.f13113c += 32;
        int i2 = this.f13115i;
        zzba zzbaVar = this.f13116j;
        zzbaVar.remove(zzba.j(zzbaVar, i2));
        this.f13114h--;
        this.f13115i = -1;
    }
}
