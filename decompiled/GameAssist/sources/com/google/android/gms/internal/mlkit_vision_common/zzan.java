package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzan {

    /* renamed from: a, reason: collision with root package name */
    private final Map f11881a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f11882b;

    /* renamed from: c, reason: collision with root package name */
    private final ObjectEncoder f11883c;

    zzan(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f11881a = map;
        this.f11882b = map2;
        this.f11883c = objectEncoder;
    }

    public final byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new zzak(byteArrayOutputStream, this.f11881a, this.f11882b, this.f11883c).h(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
