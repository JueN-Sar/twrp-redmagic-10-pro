package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
final class zzev implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzev f12282a = new zzev();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12283b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12284c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12285d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12286e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("name");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12283b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("type");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12284c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a(Constants.EXTRA_VERSION);
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12285d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("featureLevel");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12286e = a5.b(zzaeVar4.b()).a();
    }

    private zzev() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
