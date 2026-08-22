package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzee implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzee f12181a = new zzee();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12182b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12183c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12184d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12185e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("imageFormat");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12182b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("originalImageSize");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12183c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("compressedImageSize");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12184d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("isOdmlImage");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12185e = a5.b(zzaeVar4.b()).a();
    }

    private zzee() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
