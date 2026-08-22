package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgv implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgv f12516a = new zzgv();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12517b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12518c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12517b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("textDetectionOptions");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12518c = a3.b(zzaeVar2.b()).a();
    }

    private zzgv() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
