package com.google.android.gms.common.server.response;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public Object d(String str) {
        return null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        FastJsonResponse fastJsonResponse = (FastJsonResponse) obj;
        for (FastJsonResponse.Field field : a().values()) {
            if (f(field)) {
                if (!fastJsonResponse.f(field) || !Objects.a(b(field), fastJsonResponse.b(field))) {
                    return false;
                }
            } else if (fastJsonResponse.f(field)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public boolean g(String str) {
        return false;
    }

    public int hashCode() {
        int i2 = 0;
        for (FastJsonResponse.Field field : a().values()) {
            if (f(field)) {
                i2 = (i2 * 31) + Preconditions.i(b(field)).hashCode();
            }
        }
        return i2;
    }
}
