package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzeb implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzeb f12164a = new zzeb();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12165b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12166c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12167d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12168e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12169f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12170g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("mode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12165b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("landmark");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12166c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("classification");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12167d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("prominentFaceOnly");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12168e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("tracking");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12169f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("minFaceSize");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12170g = a7.b(zzaeVar6.b()).a();
    }

    private zzeb() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
