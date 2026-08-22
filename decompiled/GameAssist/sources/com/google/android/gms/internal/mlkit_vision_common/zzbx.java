package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzbx implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzbx f11909a = new zzbx();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11910b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11911c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11912d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11913e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11914f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11910b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11911c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("inputsFormats");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11912d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("outputFormats");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11913e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("options");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f11914f = a6.b(zzaeVar5.b()).a();
    }

    private zzbx() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
