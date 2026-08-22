package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdl implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdl f12089a = new zzdl();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12090b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12091c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12092d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12093e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("callingSource");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12090b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("supportedFormats");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12091c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("durationMs");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12092d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("errorCode");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12093e = a5.b(zzaeVar4.b()).a();
    }

    private zzdl() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
