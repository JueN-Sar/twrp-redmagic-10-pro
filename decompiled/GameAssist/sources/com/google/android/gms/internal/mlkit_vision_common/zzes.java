package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzes implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzes f12271a = new zzes();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12272b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12273c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12274d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12275e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("isChargingRequired");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12272b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isWifiRequired");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12273c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("isDeviceIdleRequired");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12274d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("canDownloadInBackground");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12275e = a5.b(zzaeVar4.b()).a();
    }

    private zzes() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
