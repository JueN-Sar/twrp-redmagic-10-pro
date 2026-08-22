package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdo implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdo f12102a = new zzdo();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12103b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12104c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12103b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12104c = a3.b(zzaeVar2.b()).a();
    }

    private zzdo() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
