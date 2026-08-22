package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfq implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfq f12365a = new zzfq();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12366b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12367c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12366b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12367c = a3.b(zzaeVar2.b()).a();
    }

    private zzfq() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
