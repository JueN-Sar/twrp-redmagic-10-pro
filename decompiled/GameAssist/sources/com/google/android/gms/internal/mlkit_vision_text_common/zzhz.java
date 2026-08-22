package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzhz implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzhz f13277a = new zzhz();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13278b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13279c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13280d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f13281e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("imageFormat");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13278b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("originalImageSize");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13279c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("compressedImageSize");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13280d = a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("isOdmlImage");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        f13281e = a5.b(zzctVar4.b()).a();
    }

    private zzhz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzod zzodVar = (zzod) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13278b, zzodVar.a());
        objectEncoderContext.c(f13279c, zzodVar.b());
        objectEncoderContext.c(f13280d, null);
        objectEncoderContext.c(f13281e, null);
    }
}
