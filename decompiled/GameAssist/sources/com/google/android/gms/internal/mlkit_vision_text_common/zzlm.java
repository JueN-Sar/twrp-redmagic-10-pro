package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzlm implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzlm f13411a = new zzlm();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13412b;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13412b = a2.b(zzctVar.b()).a();
    }

    private zzlm() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        ((ObjectEncoderContext) obj2).c(f13412b, ((zzrw) obj).a());
    }
}
