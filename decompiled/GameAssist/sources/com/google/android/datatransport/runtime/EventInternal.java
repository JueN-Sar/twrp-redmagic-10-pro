package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import com.google.auto.value.AutoValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AutoValue
/* loaded from: classes.dex */
public abstract class EventInternal {

    @AutoValue.Builder
    public static abstract class Builder {
        public final Builder a(String str, int i2) {
            e().put(str, String.valueOf(i2));
            return this;
        }

        public final Builder b(String str, long j2) {
            e().put(str, String.valueOf(j2));
            return this;
        }

        public final Builder c(String str, String str2) {
            e().put(str, str2);
            return this;
        }

        public abstract EventInternal d();

        protected abstract Map e();

        protected abstract Builder f(Map map);

        public abstract Builder g(Integer num);

        public abstract Builder h(EncodedPayload encodedPayload);

        public abstract Builder i(long j2);

        public abstract Builder j(String str);

        public abstract Builder k(long j2);
    }

    public static Builder a() {
        return new AutoValue_EventInternal.Builder().f(new HashMap());
    }

    public final String b(String str) {
        String str2 = (String) c().get(str);
        return str2 == null ? "" : str2;
    }

    protected abstract Map c();

    public abstract Integer d();

    public abstract EncodedPayload e();

    public abstract long f();

    public final int g(String str) {
        String str2 = (String) c().get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public final long h(String str) {
        String str2 = (String) c().get(str);
        if (str2 == null) {
            return 0L;
        }
        return Long.valueOf(str2).longValue();
    }

    public final Map i() {
        return Collections.unmodifiableMap(c());
    }

    public abstract String j();

    public abstract long k();

    public Builder l() {
        return new AutoValue_EventInternal.Builder().j(j()).g(d()).h(e()).i(f()).k(k()).f(new HashMap(c()));
    }
}
