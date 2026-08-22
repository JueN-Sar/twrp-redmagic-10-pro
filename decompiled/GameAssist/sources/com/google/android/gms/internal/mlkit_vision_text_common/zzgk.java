package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgk implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgk f13225a = new zzgk();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13226b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13227c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13228d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f13229e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f13230f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13226b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("hasResult");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13227c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("isColdCall");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13228d = a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("imageInfo");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        f13229e = a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("recognizerOptions");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        f13230f = a6.b(zzctVar5.b()).a();
    }

    private zzgk() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzes zzesVar = (zzes) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13226b, zzesVar.a());
        objectEncoderContext.c(f13227c, null);
        objectEncoderContext.c(f13228d, zzesVar.c());
        objectEncoderContext.c(f13229e, null);
        objectEncoderContext.c(f13230f, zzesVar.b());
    }
}
