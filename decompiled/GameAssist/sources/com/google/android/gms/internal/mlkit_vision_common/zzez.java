package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzez implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzez f12301a = new zzez();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12302b;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12302b = a2.b(zzaeVar.b()).a();
    }

    private zzez() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
