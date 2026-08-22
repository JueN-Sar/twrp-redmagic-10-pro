package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzer implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzer f12266a = new zzer();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12267b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12268c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12269d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12270e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("modelInfo");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12267b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("initialDownloadConditions");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12268c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("updateDownloadConditions");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12269d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("isModelUpdateEnabled");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12270e = a5.b(zzaeVar4.b()).a();
    }

    private zzer() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
