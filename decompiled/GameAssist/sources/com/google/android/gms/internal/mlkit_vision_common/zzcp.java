package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcp implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcp f11995a = new zzcp();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11996b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11997c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11998d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11999e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11996b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11997c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11998d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11999e = a5.b(zzaeVar4.b()).a();
    }

    private zzcp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
