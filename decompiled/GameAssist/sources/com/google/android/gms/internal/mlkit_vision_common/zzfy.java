package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfy implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfy f12390a = new zzfy();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12391b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12392c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12391b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12392c = a3.b(zzaeVar2.b()).a();
    }

    private zzfy() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
