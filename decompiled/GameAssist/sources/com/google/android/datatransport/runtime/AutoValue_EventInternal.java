package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.EventInternal;
import java.util.Map;

/* loaded from: classes.dex */
final class AutoValue_EventInternal extends EventInternal {

    /* renamed from: a, reason: collision with root package name */
    private final String f10179a;

    /* renamed from: b, reason: collision with root package name */
    private final Integer f10180b;

    /* renamed from: c, reason: collision with root package name */
    private final EncodedPayload f10181c;

    /* renamed from: d, reason: collision with root package name */
    private final long f10182d;

    /* renamed from: e, reason: collision with root package name */
    private final long f10183e;

    /* renamed from: f, reason: collision with root package name */
    private final Map f10184f;

    static final class Builder extends EventInternal.Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f10185a;

        /* renamed from: b, reason: collision with root package name */
        private Integer f10186b;

        /* renamed from: c, reason: collision with root package name */
        private EncodedPayload f10187c;

        /* renamed from: d, reason: collision with root package name */
        private Long f10188d;

        /* renamed from: e, reason: collision with root package name */
        private Long f10189e;

        /* renamed from: f, reason: collision with root package name */
        private Map f10190f;

        Builder() {
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal d() {
            String str = "";
            if (this.f10185a == null) {
                str = " transportName";
            }
            if (this.f10187c == null) {
                str = str + " encodedPayload";
            }
            if (this.f10188d == null) {
                str = str + " eventMillis";
            }
            if (this.f10189e == null) {
                str = str + " uptimeMillis";
            }
            if (this.f10190f == null) {
                str = str + " autoMetadata";
            }
            if (str.isEmpty()) {
                return new AutoValue_EventInternal(this.f10185a, this.f10186b, this.f10187c, this.f10188d.longValue(), this.f10189e.longValue(), this.f10190f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        protected Map e() {
            Map map = this.f10190f;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        protected EventInternal.Builder f(Map map) {
            if (map == null) {
                throw new NullPointerException("Null autoMetadata");
            }
            this.f10190f = map;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder g(Integer num) {
            this.f10186b = num;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder h(EncodedPayload encodedPayload) {
            if (encodedPayload == null) {
                throw new NullPointerException("Null encodedPayload");
            }
            this.f10187c = encodedPayload;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder i(long j2) {
            this.f10188d = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder j(String str) {
            if (str == null) {
                throw new NullPointerException("Null transportName");
            }
            this.f10185a = str;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder k(long j2) {
            this.f10189e = Long.valueOf(j2);
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    protected Map c() {
        return this.f10184f;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public Integer d() {
        return this.f10180b;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public EncodedPayload e() {
        return this.f10181c;
    }

    public boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventInternal)) {
            return false;
        }
        EventInternal eventInternal = (EventInternal) obj;
        return this.f10179a.equals(eventInternal.j()) && ((num = this.f10180b) != null ? num.equals(eventInternal.d()) : eventInternal.d() == null) && this.f10181c.equals(eventInternal.e()) && this.f10182d == eventInternal.f() && this.f10183e == eventInternal.k() && this.f10184f.equals(eventInternal.c());
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long f() {
        return this.f10182d;
    }

    public int hashCode() {
        int hashCode = (this.f10179a.hashCode() ^ 1000003) * 1000003;
        Integer num = this.f10180b;
        int hashCode2 = (((hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.f10181c.hashCode()) * 1000003;
        long j2 = this.f10182d;
        int i2 = (hashCode2 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        long j3 = this.f10183e;
        return this.f10184f.hashCode() ^ ((i2 ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003);
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public String j() {
        return this.f10179a;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long k() {
        return this.f10183e;
    }

    public String toString() {
        return "EventInternal{transportName=" + this.f10179a + ", code=" + this.f10180b + ", encodedPayload=" + this.f10181c + ", eventMillis=" + this.f10182d + ", uptimeMillis=" + this.f10183e + ", autoMetadata=" + this.f10184f + "}";
    }

    private AutoValue_EventInternal(String str, Integer num, EncodedPayload encodedPayload, long j2, long j3, Map map) {
        this.f10179a = str;
        this.f10180b = num;
        this.f10181c = encodedPayload;
        this.f10182d = j2;
        this.f10183e = j3;
        this.f10184f = map;
    }
}
