package com.google.android.datatransport.cct.internal;

/* loaded from: classes.dex */
final class AutoValue_LogResponse extends LogResponse {

    /* renamed from: a, reason: collision with root package name */
    private final long f10174a;

    AutoValue_LogResponse(long j2) {
        this.f10174a = j2;
    }

    @Override // com.google.android.datatransport.cct.internal.LogResponse
    public long c() {
        return this.f10174a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LogResponse) && this.f10174a == ((LogResponse) obj).c();
    }

    public int hashCode() {
        long j2 = this.f10174a;
        return ((int) (j2 ^ (j2 >>> 32))) ^ 1000003;
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.f10174a + "}";
    }
}
