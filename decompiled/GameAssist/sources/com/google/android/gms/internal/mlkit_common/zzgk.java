package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgk implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgk f11548a = new zzgk();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11549b;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("api");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11549b = a2.b(zzayVar.b()).a();
    }

    private zzgk() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        ((ObjectEncoderContext) obj2).c(f11549b, ((zzmp) obj).a());
    }
}
