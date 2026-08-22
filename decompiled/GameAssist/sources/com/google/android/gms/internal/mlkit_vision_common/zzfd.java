package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfd implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfd f12311a = new zzfd();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12312b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12313c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12314d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12315e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12316f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12317g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12312b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("options");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12313c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12314d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12315e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("contourDetectedFaces");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12316f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("nonContourDetectedFaces");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12317g = a7.b(zzaeVar6.b()).a();
    }

    private zzfd() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
