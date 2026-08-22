package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
final class zzgr implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgr f11581a = new zzgr();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11582b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11583c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11584d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11585e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11586f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11587g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11588h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f11589i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f11590j;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("name");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11582b = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a(Constants.EXTRA_VERSION);
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11583c = a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("source");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        f11584d = a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("uri");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        f11585e = a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("hash");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(5);
        f11586f = a6.b(zzayVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("modelType");
        zzay zzayVar6 = new zzay();
        zzayVar6.a(6);
        f11587g = a7.b(zzayVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("size");
        zzay zzayVar7 = new zzay();
        zzayVar7.a(7);
        f11588h = a8.b(zzayVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("hasLabelMap");
        zzay zzayVar8 = new zzay();
        zzayVar8.a(8);
        f11589i = a9.b(zzayVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("isManifestModel");
        zzay zzayVar9 = new zzay();
        zzayVar9.a(9);
        f11590j = a10.b(zzayVar9.b()).a();
    }

    private zzgr() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zznh zznhVar = (zznh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f11582b, zznhVar.d());
        objectEncoderContext.c(f11583c, null);
        objectEncoderContext.c(f11584d, zznhVar.b());
        objectEncoderContext.c(f11585e, null);
        objectEncoderContext.c(f11586f, zznhVar.c());
        objectEncoderContext.c(f11587g, zznhVar.a());
        objectEncoderContext.c(f11588h, null);
        objectEncoderContext.c(f11589i, null);
        objectEncoderContext.c(f11590j, null);
    }
}
