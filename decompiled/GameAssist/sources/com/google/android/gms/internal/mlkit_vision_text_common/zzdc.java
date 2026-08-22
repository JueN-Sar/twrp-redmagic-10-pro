package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdc {

    /* renamed from: a, reason: collision with root package name */
    private final Map f13158a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f13159b;

    /* renamed from: c, reason: collision with root package name */
    private final ObjectEncoder f13160c;

    zzdc(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f13158a = map;
        this.f13159b = map2;
        this.f13160c = objectEncoder;
    }

    public final byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new zzcz(byteArrayOutputStream, this.f13158a, this.f13159b, this.f13160c).h(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
