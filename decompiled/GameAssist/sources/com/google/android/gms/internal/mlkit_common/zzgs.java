package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgs implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgs f11591a = new zzgs();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11592b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11593c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11594d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11595e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("modelInfo");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11592b = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("initialDownloadConditions");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11593c = a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("updateDownloadConditions");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        f11594d = a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("isModelUpdateEnabled");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        f11595e = a5.b(zzayVar4.b()).a();
    }

    private zzgs() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f11592b, ((zznl) obj).a());
        objectEncoderContext.c(f11593c, null);
        objectEncoderContext.c(f11594d, null);
        objectEncoderContext.c(f11595e, null);
    }
}
