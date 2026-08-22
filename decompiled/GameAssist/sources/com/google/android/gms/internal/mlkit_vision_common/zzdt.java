package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdt implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdt f12124a = new zzdt();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12125b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12126c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12127d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12128e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12129f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12130g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12131h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f12132i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f12133j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f12134k;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("sdkVersion");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12125b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("osBuild");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12126c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("brand");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12127d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("device");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12128e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("hardware");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12129f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("manufacturer");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12130g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("model");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12131h = a8.b(zzaeVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("product");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.a(8);
        f12132i = a9.b(zzaeVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("soc");
        zzae zzaeVar9 = new zzae();
        zzaeVar9.a(9);
        f12133j = a10.b(zzaeVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("socMetaBuildId");
        zzae zzaeVar10 = new zzae();
        zzaeVar10.a(10);
        f12134k = a11.b(zzaeVar10.b()).a();
    }

    private zzdt() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
