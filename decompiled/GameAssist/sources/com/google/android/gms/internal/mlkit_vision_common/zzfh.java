package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfh implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfh f12327a = new zzfh();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12328b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12329c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12330d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12331e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12328b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12329c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12330d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("detectedFaces");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12331e = a5.b(zzaeVar4.b()).a();
    }

    private zzfh() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
