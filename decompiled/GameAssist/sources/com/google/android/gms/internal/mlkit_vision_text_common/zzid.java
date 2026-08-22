package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzid implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzid f13285a = new zzid();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13286b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13287c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13288d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f13289e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f13290f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f13291g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f13292h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f13293i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f13294j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f13295k;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13286b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13287c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("isColdCall");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13288d = a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("autoManageModelOnBackground");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        f13289e = a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("autoManageModelOnLowMemory");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        f13290f = a6.b(zzctVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("isNnApiEnabled");
        zzct zzctVar6 = new zzct();
        zzctVar6.a(6);
        f13291g = a7.b(zzctVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("eventsCount");
        zzct zzctVar7 = new zzct();
        zzctVar7.a(7);
        f13292h = a8.b(zzctVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("otherErrors");
        zzct zzctVar8 = new zzct();
        zzctVar8.a(8);
        f13293i = a9.b(zzctVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("remoteConfigValueForAcceleration");
        zzct zzctVar9 = new zzct();
        zzctVar9.a(9);
        f13294j = a10.b(zzctVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("isAccelerated");
        zzct zzctVar10 = new zzct();
        zzctVar10.a(10);
        f13295k = a11.b(zzctVar10.b()).a();
    }

    private zzid() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzoj zzojVar = (zzoj) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13286b, zzojVar.e());
        objectEncoderContext.c(f13287c, zzojVar.a());
        objectEncoderContext.c(f13288d, zzojVar.d());
        objectEncoderContext.c(f13289e, zzojVar.b());
        objectEncoderContext.c(f13290f, zzojVar.c());
        objectEncoderContext.c(f13291g, null);
        objectEncoderContext.c(f13292h, null);
        objectEncoderContext.c(f13293i, null);
        objectEncoderContext.c(f13294j, null);
        objectEncoderContext.c(f13295k, null);
    }
}
