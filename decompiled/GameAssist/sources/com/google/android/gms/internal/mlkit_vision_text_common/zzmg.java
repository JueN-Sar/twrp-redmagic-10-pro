package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzmg implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzmg f13438a = new zzmg();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13439b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13440c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13441d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f13442e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f13443f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f13444g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f13445h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f13446i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f13447j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f13448k;

    /* renamed from: l, reason: collision with root package name */
    private static final FieldDescriptor f13449l;

    /* renamed from: m, reason: collision with root package name */
    private static final FieldDescriptor f13450m;

    /* renamed from: n, reason: collision with root package name */
    private static final FieldDescriptor f13451n;

    /* renamed from: o, reason: collision with root package name */
    private static final FieldDescriptor f13452o;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("appId");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13439b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("appVersion");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13440c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("firebaseProjectId");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13441d = a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("mlSdkVersion");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        f13442e = a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("tfliteSchemaVersion");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        f13443f = a6.b(zzctVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("gcmSenderId");
        zzct zzctVar6 = new zzct();
        zzctVar6.a(6);
        f13444g = a7.b(zzctVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("apiKey");
        zzct zzctVar7 = new zzct();
        zzctVar7.a(7);
        f13445h = a8.b(zzctVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("languages");
        zzct zzctVar8 = new zzct();
        zzctVar8.a(8);
        f13446i = a9.b(zzctVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("mlSdkInstanceId");
        zzct zzctVar9 = new zzct();
        zzctVar9.a(9);
        f13447j = a10.b(zzctVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("isClearcutClient");
        zzct zzctVar10 = new zzct();
        zzctVar10.a(10);
        f13448k = a11.b(zzctVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("isStandaloneMlkit");
        zzct zzctVar11 = new zzct();
        zzctVar11.a(11);
        f13449l = a12.b(zzctVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("isJsonLogging");
        zzct zzctVar12 = new zzct();
        zzctVar12.a(12);
        f13450m = a13.b(zzctVar12.b()).a();
        FieldDescriptor.Builder a14 = FieldDescriptor.a("buildLevel");
        zzct zzctVar13 = new zzct();
        zzctVar13.a(13);
        f13451n = a14.b(zzctVar13.b()).a();
        FieldDescriptor.Builder a15 = FieldDescriptor.a("optionalModuleVersion");
        zzct zzctVar14 = new zzct();
        zzctVar14.a(14);
        f13452o = a15.b(zzctVar14.b()).a();
    }

    private zzmg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzst zzstVar = (zzst) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13439b, zzstVar.g());
        objectEncoderContext.c(f13440c, zzstVar.h());
        objectEncoderContext.c(f13441d, null);
        objectEncoderContext.c(f13442e, zzstVar.j());
        objectEncoderContext.c(f13443f, zzstVar.k());
        objectEncoderContext.c(f13444g, null);
        objectEncoderContext.c(f13445h, null);
        objectEncoderContext.c(f13446i, zzstVar.a());
        objectEncoderContext.c(f13447j, zzstVar.i());
        objectEncoderContext.c(f13448k, zzstVar.b());
        objectEncoderContext.c(f13449l, zzstVar.d());
        objectEncoderContext.c(f13450m, zzstVar.c());
        objectEncoderContext.c(f13451n, zzstVar.e());
        objectEncoderContext.c(f13452o, zzstVar.f());
    }
}
