package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcd implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcd f11939a = new zzcd();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11940b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11941c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11942d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11943e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11944f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11945g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11940b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11941c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11942d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11943e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("contourDetectedFaces");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f11944f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("nonContourDetectedFaces");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f11945g = a7.b(zzaeVar6.b()).a();
    }

    private zzcd() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
