package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzew implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzew f12287a = new zzew();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12288b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12289c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("deviceInfos");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12288b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12289c = a3.b(zzaeVar2.b()).a();
    }

    private zzew() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
