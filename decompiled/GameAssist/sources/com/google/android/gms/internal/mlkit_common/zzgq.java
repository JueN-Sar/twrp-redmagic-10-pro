package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgq implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgq f11573a = new zzgq();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11574b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11575c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11576d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11577e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11578f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11579g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11580h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11574b = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("roughDownloadDurationMs");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11575c = a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCode");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        f11576d = a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("exactDownloadDurationMs");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        f11577e = a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("downloadStatus");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(5);
        f11578f = a6.b(zzayVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("downloadFailureStatus");
        zzay zzayVar6 = new zzay();
        zzayVar6.a(6);
        f11579g = a7.b(zzayVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("mddDownloadErrorCodes");
        zzay zzayVar7 = new zzay();
        zzayVar7.a(7);
        f11580h = a8.b(zzayVar7.b()).a();
    }

    private zzgq() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zznc zzncVar = (zznc) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f11574b, zzncVar.c());
        objectEncoderContext.c(f11575c, zzncVar.f());
        objectEncoderContext.c(f11576d, zzncVar.a());
        objectEncoderContext.c(f11577e, zzncVar.e());
        objectEncoderContext.c(f11578f, zzncVar.b());
        objectEncoderContext.c(f11579g, zzncVar.d());
        objectEncoderContext.c(f11580h, null);
    }
}
