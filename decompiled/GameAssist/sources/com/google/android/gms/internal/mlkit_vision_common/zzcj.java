package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcj implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcj f11967a = new zzcj();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11968b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11969c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11970d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11971e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11968b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11969c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11970d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11971e = a5.b(zzaeVar4.b()).a();
    }

    private zzcj() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
