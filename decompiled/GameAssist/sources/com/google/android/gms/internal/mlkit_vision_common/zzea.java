package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzea implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzea f12157a = new zzea();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12158b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12159c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12160d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12161e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12162f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12163g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("landmarkMode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12158b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("classificationMode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12159c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("performanceMode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12160d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("contourMode");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12161e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("isTrackingEnabled");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12162f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("minFaceSize");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12163g = a7.b(zzaeVar6.b()).a();
    }

    private zzea() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
