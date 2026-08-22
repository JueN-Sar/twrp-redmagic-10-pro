package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzmi implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzmi f13454a = new zzmi();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectionType");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        a2.b(zzctVar.b()).a();
    }

    private zzmi() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
