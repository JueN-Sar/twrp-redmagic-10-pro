package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzbh {

    /* renamed from: a, reason: collision with root package name */
    private final Map f11443a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f11444b;

    /* renamed from: c, reason: collision with root package name */
    private final ObjectEncoder f11445c;

    zzbh(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f11443a = map;
        this.f11444b = map2;
        this.f11445c = objectEncoder;
    }

    public final byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new zzbe(byteArrayOutputStream, this.f11443a, this.f11444b, this.f11445c).h(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
