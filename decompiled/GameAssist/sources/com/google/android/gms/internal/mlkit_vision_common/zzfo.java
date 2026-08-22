package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfo implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfo f12355a = new zzfo();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12356b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12357c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12358d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12359e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12360f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12356b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCodes");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12357c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("totalInitializationMs");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12358d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("loggingInitializationMs");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12359e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("otherErrors");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12360f = a6.b(zzaeVar5.b()).a();
    }

    private zzfo() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
