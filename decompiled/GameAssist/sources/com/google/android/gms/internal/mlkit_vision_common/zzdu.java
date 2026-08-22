package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdu implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdu f12135a = new zzdu();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12136b;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12136b = a2.b(zzaeVar.b()).a();
    }

    private zzdu() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
