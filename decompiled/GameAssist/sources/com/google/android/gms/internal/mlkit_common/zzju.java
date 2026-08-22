package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzju implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzju f11683a = new zzju();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("deviceInfo");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("nnapiInfo");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("gpuInfo");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("pipelineIdentifier");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("acceptedConfigurations");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(5);
        a6.b(zzayVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("action");
        zzay zzayVar6 = new zzay();
        zzayVar6.a(6);
        a7.b(zzayVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("status");
        zzay zzayVar7 = new zzay();
        zzayVar7.a(7);
        a8.b(zzayVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("customErrors");
        zzay zzayVar8 = new zzay();
        zzayVar8.a(8);
        a9.b(zzayVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("benchmarkStatus");
        zzay zzayVar9 = new zzay();
        zzayVar9.a(9);
        a10.b(zzayVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("validationTestResult");
        zzay zzayVar10 = new zzay();
        zzayVar10.a(10);
        a11.b(zzayVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("timestampUs");
        zzay zzayVar11 = new zzay();
        zzayVar11.a(11);
        a12.b(zzayVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("elapsedUs");
        zzay zzayVar12 = new zzay();
        zzayVar12.a(12);
        a13.b(zzayVar12.b()).a();
    }

    private zzju() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
