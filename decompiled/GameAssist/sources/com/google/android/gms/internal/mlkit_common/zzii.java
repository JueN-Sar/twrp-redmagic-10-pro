package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzii implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzii f11645a = new zzii();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageInfo");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("captionCount");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(4);
        a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("highestScore");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(5);
        a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("imageType");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(6);
        a6.b(zzayVar5.b()).a();
    }

    private zzii() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
