package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzbt implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzbt f11888a = new zzbt();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11889b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11890c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11891d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11892e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11893f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11894g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11895h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11889b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("handledErrors");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11890c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("partiallyHandledErrors");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11891d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("unhandledErrors");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11892e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("modelNamespace");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f11893f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("delegateFilter");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f11894g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("httpResponseCode");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f11895h = a8.b(zzaeVar7.b()).a();
    }

    private zzbt() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
