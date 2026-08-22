package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzds implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzds f12120a = new zzds();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12121b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12122c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12123d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("modelType");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12121b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isSuccessful");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12122c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("modelName");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12123d = a4.b(zzaeVar3.b()).a();
    }

    private zzds() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
