package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfz implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfz f12393a = new zzfz();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12394b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12395c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12396d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12397e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12398f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12399g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectorMode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12394b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("multipleObjectsEnabled");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12395c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("classificationEnabled");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12396d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("maxPerObjectLabelCount");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12397e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("classificationConfidenceThreshold");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12398f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("customLocalModelOptions");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12399g = a7.b(zzaeVar6.b()).a();
    }

    private zzfz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
