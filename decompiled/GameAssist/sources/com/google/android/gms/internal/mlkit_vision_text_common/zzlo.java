package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzlo implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzlo f13417a = new zzlo();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13418b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13419c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13420d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("languageOption");
        zzct zzctVar = new zzct();
        zzctVar.a(3);
        f13418b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isUsingLegacyApi");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(4);
        f13419c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("sdkVersion");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(5);
        f13420d = a4.b(zzctVar3.b()).a();
    }

    private zzlo() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13418b, ((zzsd) obj).a());
        objectEncoderContext.c(f13419c, null);
        objectEncoderContext.c(f13420d, null);
    }
}
