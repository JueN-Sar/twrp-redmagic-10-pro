package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.NoSuchElementException;

/* loaded from: classes.dex */
final class zbsu extends zbsv {

    /* renamed from: c, reason: collision with root package name */
    private int f12944c = 0;

    /* renamed from: h, reason: collision with root package name */
    private final int f12945h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zbtc f12946i;

    zbsu(zbtc zbtcVar) {
        this.f12946i = zbtcVar;
        this.f12945h = zbtcVar.f();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsx
    public final byte a() {
        int i2 = this.f12944c;
        if (i2 >= this.f12945h) {
            throw new NoSuchElementException();
        }
        this.f12944c = i2 + 1;
        return this.f12946i.d(i2);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f12944c < this.f12945h;
    }
}
