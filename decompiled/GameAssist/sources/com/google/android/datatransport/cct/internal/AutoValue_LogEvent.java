package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;

/* loaded from: classes.dex */
final class AutoValue_LogEvent extends LogEvent {

    /* renamed from: a, reason: collision with root package name */
    private final long f10146a;

    /* renamed from: b, reason: collision with root package name */
    private final Integer f10147b;

    /* renamed from: c, reason: collision with root package name */
    private final long f10148c;

    /* renamed from: d, reason: collision with root package name */
    private final byte[] f10149d;

    /* renamed from: e, reason: collision with root package name */
    private final String f10150e;

    /* renamed from: f, reason: collision with root package name */
    private final long f10151f;

    /* renamed from: g, reason: collision with root package name */
    private final NetworkConnectionInfo f10152g;

    static final class Builder extends LogEvent.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Long f10153a;

        /* renamed from: b, reason: collision with root package name */
        private Integer f10154b;

        /* renamed from: c, reason: collision with root package name */
        private Long f10155c;

        /* renamed from: d, reason: collision with root package name */
        private byte[] f10156d;

        /* renamed from: e, reason: collision with root package name */
        private String f10157e;

        /* renamed from: f, reason: collision with root package name */
        private Long f10158f;

        /* renamed from: g, reason: collision with root package name */
        private NetworkConnectionInfo f10159g;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent a() {
            String str = "";
            if (this.f10153a == null) {
                str = " eventTimeMs";
            }
            if (this.f10155c == null) {
                str = str + " eventUptimeMs";
            }
            if (this.f10158f == null) {
                str = str + " timezoneOffsetSeconds";
            }
            if (str.isEmpty()) {
                return new AutoValue_LogEvent(this.f10153a.longValue(), this.f10154b, this.f10155c.longValue(), this.f10156d, this.f10157e, this.f10158f.longValue(), this.f10159g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder b(Integer num) {
            this.f10154b = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder c(long j2) {
            this.f10153a = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder d(long j2) {
            this.f10155c = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder e(NetworkConnectionInfo networkConnectionInfo) {
            this.f10159g = networkConnectionInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        LogEvent.Builder f(byte[] bArr) {
            this.f10156d = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        LogEvent.Builder g(String str) {
            this.f10157e = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder h(long j2) {
            this.f10158f = Long.valueOf(j2);
            return this;
        }
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public Integer b() {
        return this.f10147b;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long c() {
        return this.f10146a;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long d() {
        return this.f10148c;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public NetworkConnectionInfo e() {
        return this.f10152g;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEvent)) {
            return false;
        }
        LogEvent logEvent = (LogEvent) obj;
        if (this.f10146a == logEvent.c() && ((num = this.f10147b) != null ? num.equals(logEvent.b()) : logEvent.b() == null) && this.f10148c == logEvent.d()) {
            if (Arrays.equals(this.f10149d, logEvent instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) logEvent).f10149d : logEvent.f()) && ((str = this.f10150e) != null ? str.equals(logEvent.g()) : logEvent.g() == null) && this.f10151f == logEvent.h()) {
                NetworkConnectionInfo networkConnectionInfo = this.f10152g;
                if (networkConnectionInfo == null) {
                    if (logEvent.e() == null) {
                        return true;
                    }
                } else if (networkConnectionInfo.equals(logEvent.e())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public byte[] f() {
        return this.f10149d;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public String g() {
        return this.f10150e;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long h() {
        return this.f10151f;
    }

    public int hashCode() {
        long j2 = this.f10146a;
        int i2 = (((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.f10147b;
        int hashCode = num == null ? 0 : num.hashCode();
        long j3 = this.f10148c;
        int hashCode2 = (((((i2 ^ hashCode) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.f10149d)) * 1000003;
        String str = this.f10150e;
        int hashCode3 = str == null ? 0 : str.hashCode();
        long j4 = this.f10151f;
        int i3 = (((hashCode2 ^ hashCode3) * 1000003) ^ ((int) ((j4 >>> 32) ^ j4))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo = this.f10152g;
        return i3 ^ (networkConnectionInfo != null ? networkConnectionInfo.hashCode() : 0);
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.f10146a + ", eventCode=" + this.f10147b + ", eventUptimeMs=" + this.f10148c + ", sourceExtension=" + Arrays.toString(this.f10149d) + ", sourceExtensionJsonProto3=" + this.f10150e + ", timezoneOffsetSeconds=" + this.f10151f + ", networkConnectionInfo=" + this.f10152g + "}";
    }

    private AutoValue_LogEvent(long j2, Integer num, long j3, byte[] bArr, String str, long j4, NetworkConnectionInfo networkConnectionInfo) {
        this.f10146a = j2;
        this.f10147b = num;
        this.f10148c = j3;
        this.f10149d = bArr;
        this.f10150e = str;
        this.f10151f = j4;
        this.f10152g = networkConnectionInfo;
    }
}
