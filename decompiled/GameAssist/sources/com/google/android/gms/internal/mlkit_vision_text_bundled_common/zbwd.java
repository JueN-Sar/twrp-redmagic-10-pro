package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zbwd implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    private int f13038c = -1;

    /* renamed from: h, reason: collision with root package name */
    private boolean f13039h;

    /* renamed from: i, reason: collision with root package name */
    private Iterator f13040i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ zbwh f13041j;

    /* synthetic */ zbwd(zbwh zbwhVar, zbwc zbwcVar) {
        this.f13041j = zbwhVar;
    }

    private final Iterator b() {
        Map map;
        if (this.f13040i == null) {
            map = this.f13041j.f13045i;
            this.f13040i = map.entrySet().iterator();
        }
        return this.f13040i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2;
        Map map;
        int i3 = this.f13038c + 1;
        zbwh zbwhVar = this.f13041j;
        i2 = zbwhVar.f13044h;
        if (i3 < i2) {
            return true;
        }
        map = zbwhVar.f13045i;
        return !map.isEmpty() && b().hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        int i2;
        Object[] objArr;
        this.f13039h = true;
        int i3 = this.f13038c + 1;
        this.f13038c = i3;
        zbwh zbwhVar = this.f13041j;
        i2 = zbwhVar.f13044h;
        if (i3 >= i2) {
            return (Map.Entry) b().next();
        }
        objArr = zbwhVar.f13043c;
        return (zbwb) objArr[i3];
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i2;
        if (!this.f13039h) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.f13039h = false;
        this.f13041j.o();
        int i3 = this.f13038c;
        zbwh zbwhVar = this.f13041j;
        i2 = zbwhVar.f13044h;
        if (i3 >= i2) {
            b().remove();
        } else {
            this.f13038c = i3 - 1;
            zbwhVar.m(i3);
        }
    }
}
