package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzeh implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzeh f12193a = new zzeh();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12194b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12195c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12196d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12197e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12198f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12199g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12200h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f12201i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f12202j;

    /* renamed from: k, reason: collision with root package name */
    private static final FieldDescriptor f12203k;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12194b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12195c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12196d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("autoManageModelOnBackground");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12197e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("autoManageModelOnLowMemory");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12198f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("isNnApiEnabled");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12199g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("eventsCount");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12200h = a8.b(zzaeVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("otherErrors");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.a(8);
        f12201i = a9.b(zzaeVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("remoteConfigValueForAcceleration");
        zzae zzaeVar9 = new zzae();
        zzaeVar9.a(9);
        f12202j = a10.b(zzaeVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("isAccelerated");
        zzae zzaeVar10 = new zzae();
        zzaeVar10.a(10);
        f12203k = a11.b(zzaeVar10.b()).a();
    }

    private zzeh() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
