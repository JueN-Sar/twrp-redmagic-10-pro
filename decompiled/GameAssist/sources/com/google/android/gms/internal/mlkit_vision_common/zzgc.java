package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgc implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgc f12411a = new zzgc();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12412b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12413c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12414d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12415e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12416f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12417g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12412b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12413c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12414d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("loadDurationMs");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12415e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("sessionDurationMs");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12416f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("sessionTotalInferenceDurationMs");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12417g = a7.b(zzaeVar6.b()).a();
    }

    private zzgc() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
