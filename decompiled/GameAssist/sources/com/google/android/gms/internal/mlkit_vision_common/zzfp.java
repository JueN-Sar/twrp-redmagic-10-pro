package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfp implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfp f12361a = new zzfp();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12362b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12363c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12364d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("maxLabels");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12362b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("confidenceThreshold");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12363c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("customLocalModelOptions");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12364d = a4.b(zzaeVar3.b()).a();
    }

    private zzfp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
