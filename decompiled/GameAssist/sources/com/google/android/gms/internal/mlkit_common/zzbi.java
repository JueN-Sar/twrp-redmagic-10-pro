package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes.dex */
final class zzbi implements ValueEncoderContext {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11446a = false;

    /* renamed from: b, reason: collision with root package name */
    private boolean f11447b = false;

    /* renamed from: c, reason: collision with root package name */
    private FieldDescriptor f11448c;

    /* renamed from: d, reason: collision with root package name */
    private final zzbe f11449d;

    zzbi(zzbe zzbeVar) {
        this.f11449d = zzbeVar;
    }

    private final void c() {
        if (this.f11446a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f11446a = true;
    }

    final void a(FieldDescriptor fieldDescriptor, boolean z) {
        this.f11446a = false;
        this.f11448c = fieldDescriptor;
        this.f11447b = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext b(String str) {
        c();
        this.f11449d.e(this.f11448c, str, this.f11447b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext d(boolean z) {
        c();
        this.f11449d.f(this.f11448c, z ? 1 : 0, this.f11447b);
        return this;
    }
}
