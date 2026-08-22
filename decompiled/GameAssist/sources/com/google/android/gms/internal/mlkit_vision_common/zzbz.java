package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzbz implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzbz f11919a = new zzbz();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f11920b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f11921c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f11922d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f11923e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f11924f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11925g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11926h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11920b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("hasResult");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11921c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("isColdCall");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f11922d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("imageInfo");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f11923e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("options");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f11924f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("detectedBarcodeFormats");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f11925g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("detectedBarcodeValueTypes");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f11926h = a8.b(zzaeVar7.b()).a();
    }

    private zzbz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
