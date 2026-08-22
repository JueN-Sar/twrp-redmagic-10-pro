package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzbv implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzbv f11900a = new zzbv();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11901b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11902c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11903d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11904e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11901b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11902c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11903d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("options");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11904e = a5.b(zzaeVar4.b()).a();
    }

    private zzbv() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
