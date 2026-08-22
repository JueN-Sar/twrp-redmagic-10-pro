package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;

/* loaded from: classes.dex */
final class AutoValue_NetworkConnectionInfo extends NetworkConnectionInfo {

    /* renamed from: a, reason: collision with root package name */
    private final NetworkConnectionInfo.NetworkType f10175a;

    /* renamed from: b, reason: collision with root package name */
    private final NetworkConnectionInfo.MobileSubtype f10176b;

    static final class Builder extends NetworkConnectionInfo.Builder {

        /* renamed from: a, reason: collision with root package name */
        private NetworkConnectionInfo.NetworkType f10177a;

        /* renamed from: b, reason: collision with root package name */
        private NetworkConnectionInfo.MobileSubtype f10178b;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo a() {
            return new AutoValue_NetworkConnectionInfo(this.f10177a, this.f10178b);
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder b(NetworkConnectionInfo.MobileSubtype mobileSubtype) {
            this.f10178b = mobileSubtype;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder c(NetworkConnectionInfo.NetworkType networkType) {
            this.f10177a = networkType;
            return this;
        }
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    public NetworkConnectionInfo.MobileSubtype b() {
        return this.f10176b;
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    public NetworkConnectionInfo.NetworkType c() {
        return this.f10175a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NetworkConnectionInfo)) {
            return false;
        }
        NetworkConnectionInfo networkConnectionInfo = (NetworkConnectionInfo) obj;
        NetworkConnectionInfo.NetworkType networkType = this.f10175a;
        if (networkType != null ? networkType.equals(networkConnectionInfo.c()) : networkConnectionInfo.c() == null) {
            NetworkConnectionInfo.MobileSubtype mobileSubtype = this.f10176b;
            if (mobileSubtype == null) {
                if (networkConnectionInfo.b() == null) {
                    return true;
                }
            } else if (mobileSubtype.equals(networkConnectionInfo.b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        NetworkConnectionInfo.NetworkType networkType = this.f10175a;
        int hashCode = ((networkType == null ? 0 : networkType.hashCode()) ^ 1000003) * 1000003;
        NetworkConnectionInfo.MobileSubtype mobileSubtype = this.f10176b;
        return hashCode ^ (mobileSubtype != null ? mobileSubtype.hashCode() : 0);
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.f10175a + ", mobileSubtype=" + this.f10176b + "}";
    }

    private AutoValue_NetworkConnectionInfo(NetworkConnectionInfo.NetworkType networkType, NetworkConnectionInfo.MobileSubtype mobileSubtype) {
        this.f10175a = networkType;
        this.f10176b = mobileSubtype;
    }
}
