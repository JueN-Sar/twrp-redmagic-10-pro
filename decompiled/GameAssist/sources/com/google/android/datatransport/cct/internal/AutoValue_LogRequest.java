package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogRequest;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_LogRequest extends LogRequest {

    /* renamed from: a, reason: collision with root package name */
    private final long f10160a;

    /* renamed from: b, reason: collision with root package name */
    private final long f10161b;

    /* renamed from: c, reason: collision with root package name */
    private final ClientInfo f10162c;

    /* renamed from: d, reason: collision with root package name */
    private final Integer f10163d;

    /* renamed from: e, reason: collision with root package name */
    private final String f10164e;

    /* renamed from: f, reason: collision with root package name */
    private final List f10165f;

    /* renamed from: g, reason: collision with root package name */
    private final QosTier f10166g;

    static final class Builder extends LogRequest.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Long f10167a;

        /* renamed from: b, reason: collision with root package name */
        private Long f10168b;

        /* renamed from: c, reason: collision with root package name */
        private ClientInfo f10169c;

        /* renamed from: d, reason: collision with root package name */
        private Integer f10170d;

        /* renamed from: e, reason: collision with root package name */
        private String f10171e;

        /* renamed from: f, reason: collision with root package name */
        private List f10172f;

        /* renamed from: g, reason: collision with root package name */
        private QosTier f10173g;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest a() {
            String str = "";
            if (this.f10167a == null) {
                str = " requestTimeMs";
            }
            if (this.f10168b == null) {
                str = str + " requestUptimeMs";
            }
            if (str.isEmpty()) {
                return new AutoValue_LogRequest(this.f10167a.longValue(), this.f10168b.longValue(), this.f10169c, this.f10170d, this.f10171e, this.f10172f, this.f10173g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder b(ClientInfo clientInfo) {
            this.f10169c = clientInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder c(List list) {
            this.f10172f = list;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        LogRequest.Builder d(Integer num) {
            this.f10170d = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        LogRequest.Builder e(String str) {
            this.f10171e = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder f(QosTier qosTier) {
            this.f10173g = qosTier;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder g(long j2) {
            this.f10167a = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder h(long j2) {
            this.f10168b = Long.valueOf(j2);
            return this;
        }
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public ClientInfo b() {
        return this.f10162c;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public List c() {
        return this.f10165f;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public Integer d() {
        return this.f10163d;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public String e() {
        return this.f10164e;
    }

    public boolean equals(Object obj) {
        ClientInfo clientInfo;
        Integer num;
        String str;
        List list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogRequest)) {
            return false;
        }
        LogRequest logRequest = (LogRequest) obj;
        if (this.f10160a == logRequest.g() && this.f10161b == logRequest.h() && ((clientInfo = this.f10162c) != null ? clientInfo.equals(logRequest.b()) : logRequest.b() == null) && ((num = this.f10163d) != null ? num.equals(logRequest.d()) : logRequest.d() == null) && ((str = this.f10164e) != null ? str.equals(logRequest.e()) : logRequest.e() == null) && ((list = this.f10165f) != null ? list.equals(logRequest.c()) : logRequest.c() == null)) {
            QosTier qosTier = this.f10166g;
            if (qosTier == null) {
                if (logRequest.f() == null) {
                    return true;
                }
            } else if (qosTier.equals(logRequest.f())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public QosTier f() {
        return this.f10166g;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long g() {
        return this.f10160a;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long h() {
        return this.f10161b;
    }

    public int hashCode() {
        long j2 = this.f10160a;
        long j3 = this.f10161b;
        int i2 = (((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        ClientInfo clientInfo = this.f10162c;
        int hashCode = (i2 ^ (clientInfo == null ? 0 : clientInfo.hashCode())) * 1000003;
        Integer num = this.f10163d;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.f10164e;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List list = this.f10165f;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        QosTier qosTier = this.f10166g;
        return hashCode4 ^ (qosTier != null ? qosTier.hashCode() : 0);
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.f10160a + ", requestUptimeMs=" + this.f10161b + ", clientInfo=" + this.f10162c + ", logSource=" + this.f10163d + ", logSourceName=" + this.f10164e + ", logEvents=" + this.f10165f + ", qosTier=" + this.f10166g + "}";
    }

    private AutoValue_LogRequest(long j2, long j3, ClientInfo clientInfo, Integer num, String str, List list, QosTier qosTier) {
        this.f10160a = j2;
        this.f10161b = j3;
        this.f10162c = clientInfo;
        this.f10163d = num;
        this.f10164e = str;
        this.f10165f = list;
        this.f10166g = qosTier;
    }
}
