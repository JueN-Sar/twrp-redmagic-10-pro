package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcl implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcl f11976a = new zzcl();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11977b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11978c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11979d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11980e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11977b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11978c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11979d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("options");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11980e = a5.b(zzaeVar4.b()).a();
    }

    private zzcl() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
