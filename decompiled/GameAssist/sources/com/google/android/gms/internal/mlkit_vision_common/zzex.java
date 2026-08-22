package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzex implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzex f12290a = new zzex();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12291b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12292c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12293d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12294e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("category");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12291b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("classificationConfidence");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12292c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("trackingId");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12293d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("labelCount");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12294e = a5.b(zzaeVar4.b()).a();
    }

    private zzex() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
