package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public final class zzsk implements zzry {

    /* renamed from: a, reason: collision with root package name */
    private final zzmw f11844a;

    /* renamed from: b, reason: collision with root package name */
    private zzqt f11845b = new zzqt();

    private zzsk(zzmw zzmwVar, int i2) {
        this.f11844a = zzmwVar;
        zzsv.a();
    }

    public static zzry e(zzmw zzmwVar) {
        return new zzsk(zzmwVar, 0);
    }

    public static zzry f() {
        return new zzsk(new zzmw(), 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final byte[] a(int i2, boolean z) {
        this.f11845b.f(Boolean.valueOf(1 == (i2 ^ 1)));
        this.f11845b.e(Boolean.FALSE);
        this.f11844a.j(this.f11845b.m());
        try {
            zzsv.a();
            if (i2 == 0) {
                return new JsonDataEncoderBuilder().g(zzkr.f11720a).h(true).f().b(this.f11844a.k()).getBytes("utf-8");
            }
            zzmy k2 = this.f11844a.k();
            zzbg zzbgVar = new zzbg();
            zzkr.f11720a.a(zzbgVar);
            return zzbgVar.b().a(k2);
        } catch (UnsupportedEncodingException e2) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final zzry b(zzmv zzmvVar) {
        this.f11844a.f(zzmvVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final zzry c(zznc zzncVar) {
        this.f11844a.i(zzncVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final zzry d(zzqt zzqtVar) {
        this.f11845b = zzqtVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final String zzd() {
        String k2;
        zzqv f2 = this.f11844a.k().f();
        return (f2 == null || (k2 = f2.k()) == null || k2.isEmpty()) ? "NA" : (String) Preconditions.i(f2.k());
    }
}
