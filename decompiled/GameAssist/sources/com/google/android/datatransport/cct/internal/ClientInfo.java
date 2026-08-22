package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ClientInfo;
import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
public abstract class ClientInfo {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract ClientInfo a();

        public abstract Builder b(AndroidClientInfo androidClientInfo);

        public abstract Builder c(ClientType clientType);
    }

    public enum ClientType {
        UNKNOWN(0),
        ANDROID_FIREBASE(23);

        private final int value;

        ClientType(int i2) {
            this.value = i2;
        }
    }

    public static Builder a() {
        return new AutoValue_ClientInfo.Builder();
    }

    public abstract AndroidClientInfo b();

    public abstract ClientType c();
}
