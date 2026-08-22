package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
final class zzcj extends zzbm {
    final transient Object[] zza;

    private zzcj(@CheckForNull Object obj, Object[] objArr, int i2) {
        this.zza = objArr;
    }

    static zzcj g(int i2, Object[] objArr, zzbl zzblVar) {
        Object obj = objArr[0];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[1];
        Objects.requireNonNull(obj2);
        zzaq.b(obj, obj2);
        return new zzcj(null, objArr, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm
    final zzbf a() {
        return new zzci(this.zza, 1, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm
    final zzbn d() {
        return new zzcg(this, this.zza, 0, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm
    final zzbn e() {
        return new zzch(this, new zzci(this.zza, 0, 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x001b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(java.lang.Object r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto L5
        L3:
            r2 = r0
            goto L19
        L5:
            java.lang.Object[] r2 = r2.zza
            r1 = 0
            r1 = r2[r1]
            java.util.Objects.requireNonNull(r1)
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L3
            r3 = 1
            r2 = r2[r3]
            java.util.Objects.requireNonNull(r2)
        L19:
            if (r2 != 0) goto L1c
            return r0
        L1c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_common.zzcj.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return 1;
    }
}
