package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgz implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgz f12527a = new zzgz();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12528b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12529c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12530d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("language");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12528b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("durationMs");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12529c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12530d = a4.b(zzaeVar3.b()).a();
    }

    private zzgz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
