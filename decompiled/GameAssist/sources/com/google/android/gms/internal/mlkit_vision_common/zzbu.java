package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzbu implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzbu f11896a = new zzbu();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11897b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11898c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11899d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("logEventKey");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11897b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("eventCount");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11898c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("inferenceDurationStats");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11899d = a4.b(zzaeVar3.b()).a();
    }

    private zzbu() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
