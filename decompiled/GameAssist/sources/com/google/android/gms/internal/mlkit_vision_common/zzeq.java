package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
final class zzeq implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzeq f12256a = new zzeq();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12257b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12258c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12259d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12260e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12261f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12262g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12263h;

    /* renamed from: i, reason: collision with root package name */
    private static final FieldDescriptor f12264i;

    /* renamed from: j, reason: collision with root package name */
    private static final FieldDescriptor f12265j;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("name");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12257b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a(Constants.EXTRA_VERSION);
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12258c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("source");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12259d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("uri");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12260e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("hash");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12261f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("modelType");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12262g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("size");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12263h = a8.b(zzaeVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("hasLabelMap");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.a(8);
        f12264i = a9.b(zzaeVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("isManifestModel");
        zzae zzaeVar9 = new zzae();
        zzaeVar9.a(9);
        f12265j = a10.b(zzaeVar9.b()).a();
    }

    private zzeq() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
