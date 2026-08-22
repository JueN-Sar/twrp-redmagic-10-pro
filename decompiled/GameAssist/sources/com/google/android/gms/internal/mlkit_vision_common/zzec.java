package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
final class zzec implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzec f12171a = new zzec();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12172b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12173c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12174d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12175e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12176f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12177g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12178h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("renderer");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12172b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("vendor");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12173c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a(Constants.EXTRA_VERSION);
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12174d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("maxImages");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12175e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("maxSsbo");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12176f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("workGroupSizes");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12177g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("errorCode");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12178h = a8.b(zzaeVar7.b()).a();
    }

    private zzec() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
