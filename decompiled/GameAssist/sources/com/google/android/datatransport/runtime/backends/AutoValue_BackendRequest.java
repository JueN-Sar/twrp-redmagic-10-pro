package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendRequest;
import java.util.Arrays;

/* loaded from: classes.dex */
final class AutoValue_BackendRequest extends BackendRequest {

    /* renamed from: a, reason: collision with root package name */
    private final Iterable f10244a;

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f10245b;

    static final class Builder extends BackendRequest.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Iterable f10246a;

        /* renamed from: b, reason: collision with root package name */
        private byte[] f10247b;

        Builder() {
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest a() {
            String str = "";
            if (this.f10246a == null) {
                str = " events";
            }
            if (str.isEmpty()) {
                return new AutoValue_BackendRequest(this.f10246a, this.f10247b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest.Builder b(Iterable iterable) {
            if (iterable == null) {
                throw new NullPointerException("Null events");
            }
            this.f10246a = iterable;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest.Builder c(byte[] bArr) {
            this.f10247b = bArr;
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRequest
    public Iterable b() {
        return this.f10244a;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRequest
    public byte[] c() {
        return this.f10245b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackendRequest)) {
            return false;
        }
        BackendRequest backendRequest = (BackendRequest) obj;
        if (this.f10244a.equals(backendRequest.b())) {
            if (Arrays.equals(this.f10245b, backendRequest instanceof AutoValue_BackendRequest ? ((AutoValue_BackendRequest) backendRequest).f10245b : backendRequest.c())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f10245b) ^ ((this.f10244a.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        return "BackendRequest{events=" + this.f10244a + ", extras=" + Arrays.toString(this.f10245b) + "}";
    }

    private AutoValue_BackendRequest(Iterable iterable, byte[] bArr) {
        this.f10244a = iterable;
        this.f10245b = bArr;
    }
}
