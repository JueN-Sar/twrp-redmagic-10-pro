package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes.dex */
final class zzdd implements ValueEncoderContext {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13161a = false;

    /* renamed from: b, reason: collision with root package name */
    private boolean f13162b = false;

    /* renamed from: c, reason: collision with root package name */
    private FieldDescriptor f13163c;

    /* renamed from: d, reason: collision with root package name */
    private final zzcz f13164d;

    zzdd(zzcz zzczVar) {
        this.f13164d = zzczVar;
    }

    private final void c() {
        if (this.f13161a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f13161a = true;
    }

    final void a(FieldDescriptor fieldDescriptor, boolean z) {
        this.f13161a = false;
        this.f13163c = fieldDescriptor;
        this.f13162b = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext b(String str) {
        c();
        this.f13164d.e(this.f13163c, str, this.f13162b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext d(boolean z) {
        c();
        this.f13164d.f(this.f13163c, z ? 1 : 0, this.f13162b);
        return this;
    }
}
