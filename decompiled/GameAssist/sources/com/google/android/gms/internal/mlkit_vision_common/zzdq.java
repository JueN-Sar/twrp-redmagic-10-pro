package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdq implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdq f12108a = new zzdq();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12109b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12110c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12111d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12112e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12113f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12109b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("options");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12110c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("inputsFormats");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12111d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("outputFormats");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12112e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("modelInitializationMs");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12113f = a6.b(zzaeVar5.b()).a();
    }

    private zzdq() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
