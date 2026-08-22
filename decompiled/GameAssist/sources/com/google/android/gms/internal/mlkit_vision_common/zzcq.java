package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcq implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcq f12000a = new zzcq();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12001b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12002c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12003d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("logEventKey");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12001b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("eventCount");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12002c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("inferenceDurationStats");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12003d = a4.b(zzaeVar3.b()).a();
    }

    private zzcq() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
