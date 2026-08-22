package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$1 implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonDataEncoderBuilder$$Lambda$1 f15874a = new JsonDataEncoderBuilder$$Lambda$1();

    private JsonDataEncoderBuilder$$Lambda$1() {
    }

    public static ObjectEncoder b() {
        return f15874a;
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public void a(Object obj, Object obj2) {
        JsonDataEncoderBuilder.i(obj, (ObjectEncoderContext) obj2);
    }
}
