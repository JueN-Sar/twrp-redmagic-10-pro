package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;

/* loaded from: classes.dex */
final class AutoValue_TransportContext extends TransportContext {

    /* renamed from: a, reason: collision with root package name */
    private final String f10201a;

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f10202b;

    /* renamed from: c, reason: collision with root package name */
    private final Priority f10203c;

    static final class Builder extends TransportContext.Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f10204a;

        /* renamed from: b, reason: collision with root package name */
        private byte[] f10205b;

        /* renamed from: c, reason: collision with root package name */
        private Priority f10206c;

        Builder() {
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext a() {
            String str = "";
            if (this.f10204a == null) {
                str = " backendName";
            }
            if (this.f10206c == null) {
                str = str + " priority";
            }
            if (str.isEmpty()) {
                return new AutoValue_TransportContext(this.f10204a, this.f10205b, this.f10206c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder b(String str) {
            if (str == null) {
                throw new NullPointerException("Null backendName");
            }
            this.f10204a = str;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder c(byte[] bArr) {
            this.f10205b = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder d(Priority priority) {
            if (priority == null) {
                throw new NullPointerException("Null priority");
            }
            this.f10206c = priority;
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public String b() {
        return this.f10201a;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public byte[] c() {
        return this.f10202b;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public Priority d() {
        return this.f10203c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransportContext)) {
            return false;
        }
        TransportContext transportContext = (TransportContext) obj;
        if (this.f10201a.equals(transportContext.b())) {
            if (Arrays.equals(this.f10202b, transportContext instanceof AutoValue_TransportContext ? ((AutoValue_TransportContext) transportContext).f10202b : transportContext.c()) && this.f10203c.equals(transportContext.d())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.f10203c.hashCode() ^ ((((this.f10201a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f10202b)) * 1000003);
    }

    private AutoValue_TransportContext(String str, byte[] bArr, Priority priority) {
        this.f10201a = str;
        this.f10202b = bArr;
        this.f10203c = priority;
    }
}
