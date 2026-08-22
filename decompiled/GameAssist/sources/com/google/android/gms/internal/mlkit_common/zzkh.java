package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzkh implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzkh f11696a = new zzkh();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11697b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11698c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11699d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11700e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11701f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11702g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11703h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f11704i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f11705j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f11706k;

    /* renamed from: l, reason: collision with root package name */
    private static final FieldDescriptor f11707l;

    /* renamed from: m, reason: collision with root package name */
    private static final FieldDescriptor f11708m;

    /* renamed from: n, reason: collision with root package name */
    private static final FieldDescriptor f11709n;

    /* renamed from: o, reason: collision with root package name */
    private static final FieldDescriptor f11710o;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("appId");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11697b = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("appVersion");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11698c = a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("firebaseProjectId");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        f11699d = a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("mlSdkVersion");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        f11700e = a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("tfliteSchemaVersion");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(5);
        f11701f = a6.b(zzayVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("gcmSenderId");
        zzay zzayVar6 = new zzay();
        zzayVar6.a(6);
        f11702g = a7.b(zzayVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("apiKey");
        zzay zzayVar7 = new zzay();
        zzayVar7.a(7);
        f11703h = a8.b(zzayVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("languages");
        zzay zzayVar8 = new zzay();
        zzayVar8.a(8);
        f11704i = a9.b(zzayVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("mlSdkInstanceId");
        zzay zzayVar9 = new zzay();
        zzayVar9.a(9);
        f11705j = a10.b(zzayVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("isClearcutClient");
        zzay zzayVar10 = new zzay();
        zzayVar10.a(10);
        f11706k = a11.b(zzayVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("isStandaloneMlkit");
        zzay zzayVar11 = new zzay();
        zzayVar11.a(11);
        f11707l = a12.b(zzayVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("isJsonLogging");
        zzay zzayVar12 = new zzay();
        zzayVar12.a(12);
        f11708m = a13.b(zzayVar12.b()).a();
        FieldDescriptor.Builder a14 = FieldDescriptor.a("buildLevel");
        zzay zzayVar13 = new zzay();
        zzayVar13.a(13);
        f11709n = a14.b(zzayVar13.b()).a();
        FieldDescriptor.Builder a15 = FieldDescriptor.a("optionalModuleVersion");
        zzay zzayVar14 = new zzay();
        zzayVar14.a(14);
        f11710o = a15.b(zzayVar14.b()).a();
    }

    private zzkh() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzqv zzqvVar = (zzqv) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f11697b, zzqvVar.g());
        objectEncoderContext.c(f11698c, zzqvVar.h());
        objectEncoderContext.c(f11699d, null);
        objectEncoderContext.c(f11700e, zzqvVar.j());
        objectEncoderContext.c(f11701f, zzqvVar.k());
        objectEncoderContext.c(f11702g, null);
        objectEncoderContext.c(f11703h, null);
        objectEncoderContext.c(f11704i, zzqvVar.a());
        objectEncoderContext.c(f11705j, zzqvVar.i());
        objectEncoderContext.c(f11706k, zzqvVar.b());
        objectEncoderContext.c(f11707l, zzqvVar.d());
        objectEncoderContext.c(f11708m, zzqvVar.c());
        objectEncoderContext.c(f11709n, zzqvVar.e());
        objectEncoderContext.c(f11710o, zzqvVar.f());
    }
}
