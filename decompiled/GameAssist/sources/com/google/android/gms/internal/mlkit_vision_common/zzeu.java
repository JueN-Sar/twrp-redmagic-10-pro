package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzeu implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzeu f12279a = new zzeu();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12280b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12281c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("eventType");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12280b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12281c = a3.b(zzaeVar2.b()).a();
    }

    private zzeu() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
