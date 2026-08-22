package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzln implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzln f13413a = new zzln();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13414b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13415c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13416d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13414b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageInfo");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13415c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("recognizerOptions");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13416d = a4.b(zzctVar3.b()).a();
    }

    private zzln() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzrz zzrzVar = (zzrz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13414b, zzrzVar.b());
        objectEncoderContext.c(f13415c, zzrzVar.a());
        objectEncoderContext.c(f13416d, zzrzVar.c());
    }
}
