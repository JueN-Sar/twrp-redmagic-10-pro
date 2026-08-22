package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgp implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgp f12474a = new zzgp();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12475b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12476c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12477d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12478e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12479f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12480g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12481h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f12482i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f12483j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f12484k;

    /* renamed from: l, reason: collision with root package name */
    private static final FieldDescriptor f12485l;

    /* renamed from: m, reason: collision with root package name */
    private static final FieldDescriptor f12486m;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("deviceInfo");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12475b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("nnapiInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12476c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("gpuInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12477d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("pipelineIdentifier");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12478e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("acceptedConfigurations");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12479f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("action");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12480g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("status");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12481h = a8.b(zzaeVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("customErrors");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.a(8);
        f12482i = a9.b(zzaeVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("benchmarkStatus");
        zzae zzaeVar9 = new zzae();
        zzaeVar9.a(9);
        f12483j = a10.b(zzaeVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("validationTestResult");
        zzae zzaeVar10 = new zzae();
        zzaeVar10.a(10);
        f12484k = a11.b(zzaeVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("timestampUs");
        zzae zzaeVar11 = new zzae();
        zzaeVar11.a(11);
        f12485l = a12.b(zzaeVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("elapsedUs");
        zzae zzaeVar12 = new zzae();
        zzaeVar12.a(12);
        f12486m = a13.b(zzaeVar12.b()).a();
    }

    private zzgp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
