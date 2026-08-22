package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgh implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgh f11542a = new zzgh();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11543b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11544c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11545d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("modelType");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11543b = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isDownloaded");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11544c = a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("modelName");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        f11545d = a4.b(zzayVar3.b()).a();
    }

    private zzgh() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzmj zzmjVar = (zzmj) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f11543b, zzmjVar.a());
        objectEncoderContext.c(f11544c, zzmjVar.b());
        objectEncoderContext.c(f11545d, null);
    }
}
