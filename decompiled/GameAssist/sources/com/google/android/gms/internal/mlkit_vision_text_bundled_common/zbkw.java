package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.List;

/* loaded from: classes.dex */
final class zbkw extends zbkx {
    final transient int zba;
    final transient int zbb;
    final /* synthetic */ zbkx zbc;

    zbkw(zbkx zbkxVar, int i2, int i3) {
        this.zbc = zbkxVar;
        this.zba = i2;
        this.zbb = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final int d() {
        return this.zbc.f() + this.zba + this.zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final int f() {
        return this.zbc.f() + this.zba;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zbkj.a(i2, this.zbb, VirtualHandleWrapper.KEY_INDEX);
        return this.zbc.get(i2 + this.zba);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final Object[] h() {
        return this.zbc.h();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkx
    /* renamed from: i */
    public final zbkx subList(int i2, int i3) {
        zbkj.d(i2, i3, this.zbb);
        int i4 = this.zba;
        return this.zbc.subList(i2 + i4, i3 + i4);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkx, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }
}
