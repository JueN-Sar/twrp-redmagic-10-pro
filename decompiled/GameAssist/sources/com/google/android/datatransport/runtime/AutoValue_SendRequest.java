package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;

/* loaded from: classes.dex */
final class AutoValue_SendRequest extends SendRequest {

    /* renamed from: a, reason: collision with root package name */
    private final TransportContext f10191a;

    /* renamed from: b, reason: collision with root package name */
    private final String f10192b;

    /* renamed from: c, reason: collision with root package name */
    private final Event f10193c;

    /* renamed from: d, reason: collision with root package name */
    private final Transformer f10194d;

    /* renamed from: e, reason: collision with root package name */
    private final Encoding f10195e;

    static final class Builder extends SendRequest.Builder {

        /* renamed from: a, reason: collision with root package name */
        private TransportContext f10196a;

        /* renamed from: b, reason: collision with root package name */
        private String f10197b;

        /* renamed from: c, reason: collision with root package name */
        private Event f10198c;

        /* renamed from: d, reason: collision with root package name */
        private Transformer f10199d;

        /* renamed from: e, reason: collision with root package name */
        private Encoding f10200e;

        Builder() {
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest a() {
            String str = "";
            if (this.f10196a == null) {
                str = " transportContext";
            }
            if (this.f10197b == null) {
                str = str + " transportName";
            }
            if (this.f10198c == null) {
                str = str + " event";
            }
            if (this.f10199d == null) {
                str = str + " transformer";
            }
            if (this.f10200e == null) {
                str = str + " encoding";
            }
            if (str.isEmpty()) {
                return new AutoValue_SendRequest(this.f10196a, this.f10197b, this.f10198c, this.f10199d, this.f10200e);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        SendRequest.Builder b(Encoding encoding) {
            if (encoding == null) {
                throw new NullPointerException("Null encoding");
            }
            this.f10200e = encoding;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        SendRequest.Builder c(Event event) {
            if (event == null) {
                throw new NullPointerException("Null event");
            }
            this.f10198c = event;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        SendRequest.Builder d(Transformer transformer) {
            if (transformer == null) {
                throw new NullPointerException("Null transformer");
            }
            this.f10199d = transformer;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder e(TransportContext transportContext) {
            if (transportContext == null) {
                throw new NullPointerException("Null transportContext");
            }
            this.f10196a = transportContext;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder f(String str) {
            if (str == null) {
                throw new NullPointerException("Null transportName");
            }
            this.f10197b = str;
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Encoding b() {
        return this.f10195e;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    Event c() {
        return this.f10193c;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    Transformer e() {
        return this.f10194d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SendRequest)) {
            return false;
        }
        SendRequest sendRequest = (SendRequest) obj;
        return this.f10191a.equals(sendRequest.f()) && this.f10192b.equals(sendRequest.g()) && this.f10193c.equals(sendRequest.c()) && this.f10194d.equals(sendRequest.e()) && this.f10195e.equals(sendRequest.b());
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public TransportContext f() {
        return this.f10191a;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public String g() {
        return this.f10192b;
    }

    public int hashCode() {
        return this.f10195e.hashCode() ^ ((((((((this.f10191a.hashCode() ^ 1000003) * 1000003) ^ this.f10192b.hashCode()) * 1000003) ^ this.f10193c.hashCode()) * 1000003) ^ this.f10194d.hashCode()) * 1000003);
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.f10191a + ", transportName=" + this.f10192b + ", event=" + this.f10193c + ", transformer=" + this.f10194d + ", encoding=" + this.f10195e + "}";
    }

    private AutoValue_SendRequest(TransportContext transportContext, String str, Event event, Transformer transformer, Encoding encoding) {
        this.f10191a = transportContext;
        this.f10192b = str;
        this.f10193c = event;
        this.f10194d = transformer;
        this.f10195e = encoding;
    }
}
