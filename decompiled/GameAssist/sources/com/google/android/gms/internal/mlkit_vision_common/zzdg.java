package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdg implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdg f12069a = new zzdg();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12070b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12071c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12072d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12070b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("options");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12071c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12072d = a4.b(zzaeVar3.b()).a();
    }

    private zzdg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
