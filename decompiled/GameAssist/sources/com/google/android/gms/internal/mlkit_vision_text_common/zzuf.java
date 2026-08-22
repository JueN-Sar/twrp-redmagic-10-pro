package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public final class zzuf implements zztr {

    /* renamed from: a, reason: collision with root package name */
    private final zzow f13582a;

    /* renamed from: b, reason: collision with root package name */
    private zzsr f13583b = new zzsr();

    /* renamed from: c, reason: collision with root package name */
    private final int f13584c;

    private zzuf(zzow zzowVar, int i2) {
        this.f13582a = zzowVar;
        zzuo.a();
        this.f13584c = i2;
    }

    public static zztr d(zzow zzowVar) {
        return new zzuf(zzowVar, 0);
    }

    public static zztr e(zzow zzowVar, int i2) {
        return new zzuf(zzowVar, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztr
    public final byte[] a(int i2, boolean z) {
        this.f13583b.f(Boolean.valueOf(1 == (i2 ^ 1)));
        this.f13583b.e(Boolean.FALSE);
        this.f13582a.i(this.f13583b.m());
        try {
            zzuo.a();
            if (i2 == 0) {
                return new JsonDataEncoderBuilder().g(zzmq.f13462a).h(true).f().b(this.f13582a.j()).getBytes("utf-8");
            }
            zzoy j2 = this.f13582a.j();
            zzdb zzdbVar = new zzdb();
            zzmq.f13462a.a(zzdbVar);
            return zzdbVar.b().a(j2);
        } catch (UnsupportedEncodingException e2) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztr
    public final zztr b(zzsr zzsrVar) {
        this.f13583b = zzsrVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztr
    public final zztr c(zzov zzovVar) {
        this.f13582a.f(zzovVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztr
    public final int zza() {
        return this.f13584c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztr
    public final String zzd() {
        zzst f2 = this.f13582a.j().f();
        return (f2 == null || zzy.b(f2.k())) ? "NA" : (String) Preconditions.i(f2.k());
    }
}
