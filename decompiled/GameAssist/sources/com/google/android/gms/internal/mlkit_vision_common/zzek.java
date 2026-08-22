package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzek implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzek f12215a = new zzek();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12216b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12217c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12218d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("modelType");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12216b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isDownloaded");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12217c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("modelName");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12218d = a4.b(zzaeVar3.b()).a();
    }

    private zzek() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
