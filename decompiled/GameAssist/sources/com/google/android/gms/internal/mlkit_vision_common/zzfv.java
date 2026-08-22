package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfv implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfv f12383a = new zzfv();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12384b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12385c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("confidence");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12384b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("languageCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12385c = a3.b(zzaeVar2.b()).a();
    }

    private zzfv() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
