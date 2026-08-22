package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Map;

/* loaded from: classes.dex */
final class zzay extends zzam {

    /* renamed from: c, reason: collision with root package name */
    private final Object f13118c;

    /* renamed from: h, reason: collision with root package name */
    private int f13119h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zzba f13120i;

    zzay(zzba zzbaVar, int i2) {
        this.f13120i = zzbaVar;
        this.f13118c = zzba.j(zzbaVar, i2);
        this.f13119h = i2;
    }

    private final void a() {
        int z;
        int i2 = this.f13119h;
        if (i2 == -1 || i2 >= this.f13120i.size() || !zzw.a(this.f13118c, zzba.j(this.f13120i, this.f13119h))) {
            z = this.f13120i.z(this.f13118c);
            this.f13119h = z;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzam, java.util.Map.Entry
    public final Object getKey() {
        return this.f13118c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzam, java.util.Map.Entry
    public final Object getValue() {
        Map o2 = this.f13120i.o();
        if (o2 != null) {
            return o2.get(this.f13118c);
        }
        a();
        int i2 = this.f13119h;
        if (i2 == -1) {
            return null;
        }
        return zzba.m(this.f13120i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzam, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map o2 = this.f13120i.o();
        if (o2 != null) {
            return o2.put(this.f13118c, obj);
        }
        a();
        int i2 = this.f13119h;
        if (i2 == -1) {
            this.f13120i.put(this.f13118c, obj);
            return null;
        }
        zzba zzbaVar = this.f13120i;
        Object m2 = zzba.m(zzbaVar, i2);
        zzba.q(zzbaVar, this.f13119h, obj);
        return m2;
    }
}
