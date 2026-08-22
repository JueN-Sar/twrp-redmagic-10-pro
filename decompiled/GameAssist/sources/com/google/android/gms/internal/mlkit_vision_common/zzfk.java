package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfk implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfk f12337a = new zzfk();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12338b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12339c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12340d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12341e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12342f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12338b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12339c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("captionCount");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(4);
        f12340d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("highestScore");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(5);
        f12341e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("imageType");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(6);
        f12342f = a6.b(zzaeVar5.b()).a();
    }

    private zzfk() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
