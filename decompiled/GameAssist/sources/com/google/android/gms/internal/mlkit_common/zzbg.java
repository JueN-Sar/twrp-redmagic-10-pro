package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzbg implements EncoderConfig {

    /* renamed from: d, reason: collision with root package name */
    private static final ObjectEncoder f11438d = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_common.zzbf
        @Override // com.google.firebase.encoders.ObjectEncoder
        public final void a(Object obj, Object obj2) {
            int i2 = zzbg.f11439e;
            throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };

    /* renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f11439e = 0;

    /* renamed from: a, reason: collision with root package name */
    private final Map f11440a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map f11441b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final ObjectEncoder f11442c = f11438d;

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public final /* bridge */ /* synthetic */ EncoderConfig a(Class cls, ObjectEncoder objectEncoder) {
        this.f11440a.put(cls, objectEncoder);
        this.f11441b.remove(cls);
        return this;
    }

    public final zzbh b() {
        return new zzbh(new HashMap(this.f11440a), new HashMap(this.f11441b), this.f11442c);
    }
}
