package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfb implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfb f12305a = new zzfb();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12306b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12307c;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12306b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12307c = a3.b(zzaeVar2.b()).a();
    }

    private zzfb() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
