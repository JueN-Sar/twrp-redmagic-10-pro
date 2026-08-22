package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfn implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfn f12349a = new zzfn();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12350b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12351c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12352d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12353e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12354f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12350b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("options");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12351c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12352d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("labelCount");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12353e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("highestConfidence");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12354f = a6.b(zzaeVar5.b()).a();
    }

    private zzfn() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
