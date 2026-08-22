package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
final class zabs {

    /* renamed from: a, reason: collision with root package name */
    private final ApiKey f10776a;

    /* renamed from: b, reason: collision with root package name */
    private final Feature f10777b;

    /* synthetic */ zabs(ApiKey apiKey, Feature feature, zabr zabrVar) {
        this.f10776a = apiKey;
        this.f10777b = feature;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zabs)) {
            zabs zabsVar = (zabs) obj;
            if (Objects.a(this.f10776a, zabsVar.f10776a) && Objects.a(this.f10777b, zabsVar.f10777b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.b(this.f10776a, this.f10777b);
    }

    public final String toString() {
        return Objects.c(this).a("key", this.f10776a).a("feature", this.f10777b).toString();
    }
}
