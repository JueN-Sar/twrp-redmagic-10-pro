package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzfk implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfk f11516a = new zzfk();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11517b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11518c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11519d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("modelType");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11517b = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isSuccessful");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11518c = a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("modelName");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        f11519d = a4.b(zzayVar3.b()).a();
    }

    private zzfk() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzlo zzloVar = (zzlo) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f11517b, zzloVar.a());
        objectEncoderContext.c(f11518c, zzloVar.b());
        objectEncoderContext.c(f11519d, null);
    }
}
