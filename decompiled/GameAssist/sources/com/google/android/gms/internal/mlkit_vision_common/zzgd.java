package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgd implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgd f12418a = new zzgd();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12419b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12420c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12421d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12422e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12423f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectorMode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12419b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("personDetectionMode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12420c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("landmarkDetectionMode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12421d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("preferredHardwareConfigs");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12422e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("runConfig");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12423f = a6.b(zzaeVar5.b()).a();
    }

    private zzgd() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
