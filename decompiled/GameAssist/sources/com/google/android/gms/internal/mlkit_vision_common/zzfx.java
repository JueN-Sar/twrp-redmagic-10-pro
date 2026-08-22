package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfx implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfx f12388a = new zzfx();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12389b;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("identifiedLanguages");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12389b = a2.b(zzaeVar.b()).a();
    }

    private zzfx() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
