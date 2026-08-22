package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;
import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
abstract class SendRequest {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract SendRequest a();

        abstract Builder b(Encoding encoding);

        abstract Builder c(Event event);

        abstract Builder d(Transformer transformer);

        public abstract Builder e(TransportContext transportContext);

        public abstract Builder f(String str);
    }

    SendRequest() {
    }

    public static Builder a() {
        return new AutoValue_SendRequest.Builder();
    }

    public abstract Encoding b();

    abstract Event c();

    public byte[] d() {
        return (byte[]) e().apply(c().b());
    }

    abstract Transformer e();

    public abstract TransportContext f();

    public abstract String g();
}
