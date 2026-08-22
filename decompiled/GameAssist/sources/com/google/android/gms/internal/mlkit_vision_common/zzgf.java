package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgf implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgf f12428a = new zzgf();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12429b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12430c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12431d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectorMode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12429b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("streamModeSmoothingRatio");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12430c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("rawSizeMaskEnabled");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12431d = a4.b(zzaeVar3.b()).a();
    }

    private zzgf() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
