package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
final class zbte extends zbtg {

    /* renamed from: b, reason: collision with root package name */
    private int f12947b;

    /* renamed from: c, reason: collision with root package name */
    private int f12948c;

    /* renamed from: d, reason: collision with root package name */
    private int f12949d;

    /* synthetic */ zbte(byte[] bArr, int i2, int i3, boolean z, zbtd zbtdVar) {
        super(null);
        this.f12949d = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.f12947b = 0;
    }

    public final int c(int i2) {
        int i3 = this.f12949d;
        this.f12949d = 0;
        int i4 = this.f12947b + this.f12948c;
        this.f12947b = i4;
        if (i4 > 0) {
            this.f12948c = i4;
            this.f12947b = i4 - i4;
        } else {
            this.f12948c = 0;
        }
        return i3;
    }
}
