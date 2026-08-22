package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendResponse;

/* loaded from: classes.dex */
final class AutoValue_BackendResponse extends BackendResponse {

    /* renamed from: a, reason: collision with root package name */
    private final BackendResponse.Status f10248a;

    /* renamed from: b, reason: collision with root package name */
    private final long f10249b;

    AutoValue_BackendResponse(BackendResponse.Status status, long j2) {
        if (status == null) {
            throw new NullPointerException("Null status");
        }
        this.f10248a = status;
        this.f10249b = j2;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public long b() {
        return this.f10249b;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public BackendResponse.Status c() {
        return this.f10248a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackendResponse)) {
            return false;
        }
        BackendResponse backendResponse = (BackendResponse) obj;
        return this.f10248a.equals(backendResponse.c()) && this.f10249b == backendResponse.b();
    }

    public int hashCode() {
        int hashCode = (this.f10248a.hashCode() ^ 1000003) * 1000003;
        long j2 = this.f10249b;
        return ((int) (j2 ^ (j2 >>> 32))) ^ hashCode;
    }

    public String toString() {
        return "BackendResponse{status=" + this.f10248a + ", nextRequestWaitMillis=" + this.f10249b + "}";
    }
}
