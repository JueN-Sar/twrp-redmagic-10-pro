package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzda implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzda f12046a = new zzda();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12047b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12048c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12049d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12047b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("options");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12048c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12049d = a4.b(zzaeVar3.b()).a();
    }

    private zzda() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
