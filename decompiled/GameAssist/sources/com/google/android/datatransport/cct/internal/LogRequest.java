package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_LogRequest;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
/* loaded from: classes.dex */
public abstract class LogRequest {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract LogRequest a();

        public abstract Builder b(ClientInfo clientInfo);

        public abstract Builder c(List list);

        abstract Builder d(Integer num);

        abstract Builder e(String str);

        public abstract Builder f(QosTier qosTier);

        public abstract Builder g(long j2);

        public abstract Builder h(long j2);

        public Builder i(int i2) {
            return d(Integer.valueOf(i2));
        }

        public Builder j(String str) {
            return e(str);
        }
    }

    public static Builder a() {
        return new AutoValue_LogRequest.Builder();
    }

    public abstract ClientInfo b();

    public abstract List c();

    public abstract Integer d();

    public abstract String e();

    public abstract QosTier f();

    public abstract long g();

    public abstract long h();
}
