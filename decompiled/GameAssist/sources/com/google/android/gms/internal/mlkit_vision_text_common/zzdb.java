package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdb implements EncoderConfig {

    /* renamed from: d, reason: collision with root package name */
    private static final ObjectEncoder f13153d = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzda
        @Override // com.google.firebase.encoders.ObjectEncoder
        public final void a(Object obj, Object obj2) {
            int i2 = zzdb.f13154e;
            throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };

    /* renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f13154e = 0;

    /* renamed from: a, reason: collision with root package name */
    private final Map f13155a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map f13156b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final ObjectEncoder f13157c = f13153d;

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public final /* bridge */ /* synthetic */ EncoderConfig a(Class cls, ObjectEncoder objectEncoder) {
        this.f13155a.put(cls, objectEncoder);
        this.f13156b.remove(cls);
        return this;
    }

    public final zzdc b() {
        return new zzdc(new HashMap(this.f13155a), new HashMap(this.f13156b), this.f13157c);
    }
}
