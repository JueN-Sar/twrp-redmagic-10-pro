package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ClientInfo;

/* loaded from: classes.dex */
final class AutoValue_ClientInfo extends ClientInfo {

    /* renamed from: a, reason: collision with root package name */
    private final ClientInfo.ClientType f10142a;

    /* renamed from: b, reason: collision with root package name */
    private final AndroidClientInfo f10143b;

    static final class Builder extends ClientInfo.Builder {

        /* renamed from: a, reason: collision with root package name */
        private ClientInfo.ClientType f10144a;

        /* renamed from: b, reason: collision with root package name */
        private AndroidClientInfo f10145b;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo a() {
            return new AutoValue_ClientInfo(this.f10144a, this.f10145b);
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder b(AndroidClientInfo androidClientInfo) {
            this.f10145b = androidClientInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder c(ClientInfo.ClientType clientType) {
            this.f10144a = clientType;
            return this;
        }
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    public AndroidClientInfo b() {
        return this.f10143b;
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    public ClientInfo.ClientType c() {
        return this.f10142a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientInfo)) {
            return false;
        }
        ClientInfo clientInfo = (ClientInfo) obj;
        ClientInfo.ClientType clientType = this.f10142a;
        if (clientType != null ? clientType.equals(clientInfo.c()) : clientInfo.c() == null) {
            AndroidClientInfo androidClientInfo = this.f10143b;
            if (androidClientInfo == null) {
                if (clientInfo.b() == null) {
                    return true;
                }
            } else if (androidClientInfo.equals(clientInfo.b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ClientInfo.ClientType clientType = this.f10142a;
        int hashCode = ((clientType == null ? 0 : clientType.hashCode()) ^ 1000003) * 1000003;
        AndroidClientInfo androidClientInfo = this.f10143b;
        return hashCode ^ (androidClientInfo != null ? androidClientInfo.hashCode() : 0);
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.f10142a + ", androidClientInfo=" + this.f10143b + "}";
    }

    private AutoValue_ClientInfo(ClientInfo.ClientType clientType, AndroidClientInfo androidClientInfo) {
        this.f10142a = clientType;
        this.f10143b = androidClientInfo;
    }
}
