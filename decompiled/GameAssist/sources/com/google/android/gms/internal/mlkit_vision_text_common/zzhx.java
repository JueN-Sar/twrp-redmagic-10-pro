package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
final class zzhx implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzhx f13275a = new zzhx();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("renderer");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("vendor");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a(Constants.EXTRA_VERSION);
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("maxImages");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("maxSsbo");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        a6.b(zzctVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("workGroupSizes");
        zzct zzctVar6 = new zzct();
        zzctVar6.a(6);
        a7.b(zzctVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("errorCode");
        zzct zzctVar7 = new zzct();
        zzctVar7.a(7);
        a8.b(zzctVar7.b()).a();
    }

    private zzhx() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
