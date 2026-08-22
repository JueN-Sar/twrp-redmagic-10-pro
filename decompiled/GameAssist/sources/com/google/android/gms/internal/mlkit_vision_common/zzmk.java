package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public final class zzmk implements zzmb {

    /* renamed from: a, reason: collision with root package name */
    private final zziw f12626a;

    /* renamed from: b, reason: collision with root package name */
    private zzky f12627b = new zzky();

    private zzmk(zziw zziwVar, int i2) {
        this.f12626a = zziwVar;
        zzmw.a();
    }

    public static zzmb d(zziw zziwVar) {
        return new zzmk(zziwVar, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final zzmb a(zziv zzivVar) {
        this.f12626a.c(zzivVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final byte[] b(int i2, boolean z) {
        this.f12627b.f(Boolean.valueOf(1 == (i2 ^ 1)));
        this.f12627b.e(Boolean.FALSE);
        this.f12626a.e(this.f12627b.m());
        try {
            zzmw.a();
            if (i2 == 0) {
                return new JsonDataEncoderBuilder().g(zzhe.f12546a).h(true).f().b(this.f12626a.f()).getBytes("utf-8");
            }
            zziy f2 = this.f12626a.f();
            zzam zzamVar = new zzam();
            zzhe.f12546a.a(zzamVar);
            return zzamVar.b().a(f2);
        } catch (UnsupportedEncodingException e2) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final zzmb c(zzky zzkyVar) {
        this.f12627b = zzkyVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final String zzc() {
        zzla c2 = this.f12626a.f().c();
        return (c2 == null || zzg.b(c2.k())) ? "NA" : (String) Preconditions.i(c2.k());
    }
}
