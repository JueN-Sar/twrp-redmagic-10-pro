package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgo implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgo f12466a = new zzgo();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12467b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12468c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12469d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12470e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12471f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12472g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12473h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("pipelineNamespace");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12467b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("name");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12468c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("clientLibraryName");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12469d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("clientLibraryVersion");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12470e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("minClientLibraryVersion");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12471f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("maxClientLibraryVersion");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12472g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("sourceProduct");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12473h = a8.b(zzaeVar7.b()).a();
    }

    private zzgo() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
