package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdm implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdm f12094a = new zzdm();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12095b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12096c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12097d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12098e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("supportedFormats");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12095b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("durationMs");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12096c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12097d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("allowManualInput");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12098e = a5.b(zzaeVar4.b()).a();
    }

    private zzdm() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
