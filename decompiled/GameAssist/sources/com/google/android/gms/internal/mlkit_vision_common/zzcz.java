package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcz implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcz f12039a = new zzcz();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12040b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12041c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12042d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12043e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12044f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12045g;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("cameraSource");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12040b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("eventType");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12041c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("requestedPreviewHeight");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12042d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("requestedPreviewWidth");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12043e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("actualPreviewHeight");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12044f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("actualPreviewWidth");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12045g = a7.b(zzaeVar6.b()).a();
    }

    private zzcz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
