package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzam implements EncoderConfig {

    /* renamed from: d, reason: collision with root package name */
    private static final ObjectEncoder f11876d = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzal
        @Override // com.google.firebase.encoders.ObjectEncoder
        public final void a(Object obj, Object obj2) {
            int i2 = zzam.f11877e;
            throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };

    /* renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f11877e = 0;

    /* renamed from: a, reason: collision with root package name */
    private final Map f11878a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map f11879b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final ObjectEncoder f11880c = f11876d;

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public final /* bridge */ /* synthetic */ EncoderConfig a(Class cls, ObjectEncoder objectEncoder) {
        this.f11878a.put(cls, objectEncoder);
        this.f11879b.remove(cls);
        return this;
    }

    public final zzan b() {
        return new zzan(new HashMap(this.f11878a), new HashMap(this.f11879b), this.f11880c);
    }
}
