package com.google.android.datatransport.cct.internal;

import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_BatchedLogRequest extends BatchedLogRequest {

    /* renamed from: a, reason: collision with root package name */
    private final List f10141a;

    AutoValue_BatchedLogRequest(List list) {
        if (list == null) {
            throw new NullPointerException("Null logRequests");
        }
        this.f10141a = list;
    }

    @Override // com.google.android.datatransport.cct.internal.BatchedLogRequest
    public List c() {
        return this.f10141a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BatchedLogRequest) {
            return this.f10141a.equals(((BatchedLogRequest) obj).c());
        }
        return false;
    }

    public int hashCode() {
        return this.f10141a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.f10141a + "}";
    }
}
