package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgl implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgl f12450a = new zzgl();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12451b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12452c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12453d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12454e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12455f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12456g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12457h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f12458i;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12451b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("options");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12452c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("inputLength");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12453d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("outputLength");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12454e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("loadDictionaryErrorCode");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12455f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("translateResultStatusCode");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12456g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("status");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12457h = a8.b(zzaeVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("downloadHttpResponseCode");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.a(8);
        f12458i = a9.b(zzaeVar8.b()).a();
    }

    private zzgl() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
