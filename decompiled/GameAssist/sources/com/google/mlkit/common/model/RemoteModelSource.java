package com.google.mlkit.common.model;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;

/* loaded from: classes.dex */
public abstract class RemoteModelSource {

    /* renamed from: a, reason: collision with root package name */
    private final String f15926a;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(getClass())) {
            return Objects.a(this.f15926a, ((RemoteModelSource) obj).f15926a);
        }
        return false;
    }

    public int hashCode() {
        return Objects.b(this.f15926a);
    }

    public String toString() {
        zzq b2 = zzr.b("RemoteModelSource");
        b2.a("firebaseModelName", this.f15926a);
        return b2.toString();
    }
}
