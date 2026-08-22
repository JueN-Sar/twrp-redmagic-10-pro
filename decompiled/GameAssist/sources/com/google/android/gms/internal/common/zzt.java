package com.google.android.gms.internal.common;

import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
final class zzt extends zzw {

    /* renamed from: n, reason: collision with root package name */
    final /* synthetic */ zzu f11402n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzt(zzu zzuVar, zzx zzxVar, CharSequence charSequence) {
        super(zzxVar, charSequence);
        this.f11402n = zzuVar;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int d(int i2) {
        return i2 + 1;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int e(int i2) {
        CharSequence charSequence = this.f11406i;
        int length = charSequence.length();
        zzs.b(i2, length, VirtualHandleWrapper.KEY_INDEX);
        while (i2 < length) {
            zzu zzuVar = this.f11402n;
            if (zzuVar.f11403a.a(charSequence.charAt(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }
}
