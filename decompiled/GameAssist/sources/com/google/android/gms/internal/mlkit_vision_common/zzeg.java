package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzeg implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzeg f12190a = new zzeg();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12191b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12192c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12191b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12192c = a3.b(zzaeVar2.b()).a();
    }

    private zzeg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
