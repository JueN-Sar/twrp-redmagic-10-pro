package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
abstract class zbkn extends zbjz {

    /* renamed from: i, reason: collision with root package name */
    final CharSequence f12845i;

    /* renamed from: j, reason: collision with root package name */
    int f12846j = 0;

    /* renamed from: k, reason: collision with root package name */
    int f12847k = Api.BaseClientBuilder.API_PRIORITY_OTHER;

    protected zbkn(zbko zbkoVar, CharSequence charSequence) {
        this.f12845i = charSequence;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbjz
    protected final /* bridge */ /* synthetic */ Object b() {
        int d2;
        int i2 = this.f12846j;
        while (true) {
            int i3 = this.f12846j;
            if (i3 == -1) {
                c();
                return null;
            }
            int e2 = e(i3);
            if (e2 == -1) {
                e2 = this.f12845i.length();
                this.f12846j = -1;
                d2 = -1;
            } else {
                d2 = d(e2);
                this.f12846j = d2;
            }
            if (d2 != i2) {
                if (i2 < e2) {
                    this.f12845i.charAt(i2);
                }
                if (i2 < e2) {
                    this.f12845i.charAt(e2 - 1);
                }
                int i4 = this.f12847k;
                if (i4 == 1) {
                    e2 = this.f12845i.length();
                    this.f12846j = -1;
                    if (e2 > i2) {
                        this.f12845i.charAt(e2 - 1);
                    }
                } else {
                    this.f12847k = i4 - 1;
                }
                return this.f12845i.subSequence(i2, e2).toString();
            }
            int i5 = d2 + 1;
            this.f12846j = i5;
            if (i5 > this.f12845i.length()) {
                this.f12846j = -1;
            }
        }
    }

    abstract int d(int i2);

    abstract int e(int i2);
}
