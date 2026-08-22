package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes.dex */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$5 implements ValueEncoder {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonDataEncoderBuilder$$Lambda$5 f15876a = new JsonDataEncoderBuilder$$Lambda$5();

    private JsonDataEncoderBuilder$$Lambda$5() {
    }

    public static ValueEncoder b() {
        return f15876a;
    }

    @Override // com.google.firebase.encoders.ValueEncoder
    public void a(Object obj, Object obj2) {
        ((ValueEncoderContext) obj2).d(((Boolean) obj).booleanValue());
    }
}
