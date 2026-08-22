package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes.dex */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$4 implements ValueEncoder {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonDataEncoderBuilder$$Lambda$4 f15875a = new JsonDataEncoderBuilder$$Lambda$4();

    private JsonDataEncoderBuilder$$Lambda$4() {
    }

    public static ValueEncoder b() {
        return f15875a;
    }

    @Override // com.google.firebase.encoders.ValueEncoder
    public void a(Object obj, Object obj2) {
        ((ValueEncoderContext) obj2).b((String) obj);
    }
}
