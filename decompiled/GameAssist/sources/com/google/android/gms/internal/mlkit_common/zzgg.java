package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgg implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgg f11541a = new zzgg();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageSource");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageFormat");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("imageByteSize");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("imageWidth");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(5);
        a6.b(zzayVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("imageHeight");
        zzay zzayVar6 = new zzay();
        zzayVar6.a(6);
        a7.b(zzayVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("rotationDegrees");
        zzay zzayVar7 = new zzay();
        zzayVar7.a(7);
        a8.b(zzayVar7.b()).a();
    }

    private zzgg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
