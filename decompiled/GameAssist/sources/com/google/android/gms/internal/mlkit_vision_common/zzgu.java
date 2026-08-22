package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgu implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgu f12501a = new zzgu();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12502b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12503c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12504d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12505e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12506f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12507g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12508h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f12509i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f12510j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f12511k;

    /* renamed from: l, reason: collision with root package name */
    private static final FieldDescriptor f12512l;

    /* renamed from: m, reason: collision with root package name */
    private static final FieldDescriptor f12513m;

    /* renamed from: n, reason: collision with root package name */
    private static final FieldDescriptor f12514n;

    /* renamed from: o, reason: collision with root package name */
    private static final FieldDescriptor f12515o;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("appId");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12502b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("appVersion");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12503c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("firebaseProjectId");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12504d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("mlSdkVersion");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12505e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("tfliteSchemaVersion");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12506f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("gcmSenderId");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12507g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("apiKey");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12508h = a8.b(zzaeVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("languages");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.a(8);
        f12509i = a9.b(zzaeVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("mlSdkInstanceId");
        zzae zzaeVar9 = new zzae();
        zzaeVar9.a(9);
        f12510j = a10.b(zzaeVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("isClearcutClient");
        zzae zzaeVar10 = new zzae();
        zzaeVar10.a(10);
        f12511k = a11.b(zzaeVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("isStandaloneMlkit");
        zzae zzaeVar11 = new zzae();
        zzaeVar11.a(11);
        f12512l = a12.b(zzaeVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("isJsonLogging");
        zzae zzaeVar12 = new zzae();
        zzaeVar12.a(12);
        f12513m = a13.b(zzaeVar12.b()).a();
        FieldDescriptor.Builder a14 = FieldDescriptor.a("buildLevel");
        zzae zzaeVar13 = new zzae();
        zzaeVar13.a(13);
        f12514n = a14.b(zzaeVar13.b()).a();
        FieldDescriptor.Builder a15 = FieldDescriptor.a("optionalModuleVersion");
        zzae zzaeVar14 = new zzae();
        zzaeVar14.a(14);
        f12515o = a15.b(zzaeVar14.b()).a();
    }

    private zzgu() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzla zzlaVar = (zzla) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f12502b, zzlaVar.g());
        objectEncoderContext.c(f12503c, zzlaVar.h());
        objectEncoderContext.c(f12504d, null);
        objectEncoderContext.c(f12505e, zzlaVar.j());
        objectEncoderContext.c(f12506f, zzlaVar.k());
        objectEncoderContext.c(f12507g, null);
        objectEncoderContext.c(f12508h, null);
        objectEncoderContext.c(f12509i, zzlaVar.a());
        objectEncoderContext.c(f12510j, zzlaVar.i());
        objectEncoderContext.c(f12511k, zzlaVar.b());
        objectEncoderContext.c(f12512l, zzlaVar.d());
        objectEncoderContext.c(f12513m, zzlaVar.c());
        objectEncoderContext.c(f12514n, zzlaVar.e());
        objectEncoderContext.c(f12515o, zzlaVar.f());
    }
}
