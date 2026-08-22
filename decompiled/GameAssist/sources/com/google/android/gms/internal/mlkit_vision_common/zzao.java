package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes.dex */
final class zzao implements ValueEncoderContext {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11884a = false;

    /* renamed from: b, reason: collision with root package name */
    private boolean f11885b = false;

    /* renamed from: c, reason: collision with root package name */
    private FieldDescriptor f11886c;

    /* renamed from: d, reason: collision with root package name */
    private final zzak f11887d;

    zzao(zzak zzakVar) {
        this.f11887d = zzakVar;
    }

    private final void c() {
        if (this.f11884a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f11884a = true;
    }

    final void a(FieldDescriptor fieldDescriptor, boolean z) {
        this.f11884a = false;
        this.f11886c = fieldDescriptor;
        this.f11885b = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext b(String str) {
        c();
        this.f11887d.e(this.f11886c, str, this.f11885b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext d(boolean z) {
        c();
        this.f11887d.f(this.f11886c, z ? 1 : 0, this.f11885b);
        return this;
    }
}
