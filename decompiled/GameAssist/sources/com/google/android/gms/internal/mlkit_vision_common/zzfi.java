package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfi implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfi f12332a = new zzfi();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12333b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12334c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("isFaceMeshEnabled");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12333b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("useCase");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12334c = a3.b(zzaeVar2.b()).a();
    }

    private zzfi() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
