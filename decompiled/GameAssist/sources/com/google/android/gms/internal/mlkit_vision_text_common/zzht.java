package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzht implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzht f13265a = new zzht();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13266b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13267c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13268d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f13269e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f13270f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f13271g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("maxMs");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13266b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("minMs");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13267c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("avgMs");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13268d = a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("firstQuartileMs");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        f13269e = a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("medianMs");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        f13270f = a6.b(zzctVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("thirdQuartileMs");
        zzct zzctVar6 = new zzct();
        zzctVar6.a(6);
        f13271g = a7.b(zzctVar6.b()).a();
    }

    private zzht() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zznw zznwVar = (zznw) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13266b, zznwVar.c());
        objectEncoderContext.c(f13267c, zznwVar.e());
        objectEncoderContext.c(f13268d, zznwVar.a());
        objectEncoderContext.c(f13269e, zznwVar.b());
        objectEncoderContext.c(f13270f, zznwVar.d());
        objectEncoderContext.c(f13271g, zznwVar.f());
    }
}
