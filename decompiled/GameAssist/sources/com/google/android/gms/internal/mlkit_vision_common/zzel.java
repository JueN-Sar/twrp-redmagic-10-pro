package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzel implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzel f12219a = new zzel();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12220b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12221c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12222d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12220b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("eventType");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12221c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12222d = a4.b(zzaeVar3.b()).a();
    }

    private zzel() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
