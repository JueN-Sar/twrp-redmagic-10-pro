package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdz implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdz f12151a = new zzdz();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12152b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12153c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12154d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12152b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("eventType");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12153c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12154d = a4.b(zzaeVar3.b()).a();
    }

    private zzdz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
