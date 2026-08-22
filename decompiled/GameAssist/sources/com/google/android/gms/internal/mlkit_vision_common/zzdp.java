package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdp implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdp f12105a = new zzdp();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12106b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12107c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("type");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12106b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("dims");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12107c = a3.b(zzaeVar2.b()).a();
    }

    private zzdp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
