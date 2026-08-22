package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzen implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzen f12227a = new zzen();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12228b;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("api");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12228b = a2.b(zzaeVar.b()).a();
    }

    private zzen() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
