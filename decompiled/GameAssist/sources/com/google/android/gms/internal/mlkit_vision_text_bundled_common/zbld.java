package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.Objects;

/* loaded from: classes.dex */
final class zbld extends zbkx {
    static final zbkx zba = new zbld(new Object[0], 0);
    final transient Object[] zbb;
    private final transient int zbc;

    zbld(Object[] objArr, int i2) {
        this.zbb = objArr;
        this.zbc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkx, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final int b(Object[] objArr, int i2) {
        System.arraycopy(this.zbb, 0, objArr, 0, this.zbc);
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final int d() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final int f() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zbkj.a(i2, this.zbc, VirtualHandleWrapper.KEY_INDEX);
        Object obj = this.zbb[i2];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkt
    final Object[] h() {
        return this.zbb;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbc;
    }
}
