package com.google.android.datatransport.runtime;

import android.util.Base64;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.AutoValue_TransportContext;
import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
public abstract class TransportContext {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract TransportContext a();

        public abstract Builder b(String str);

        public abstract Builder c(byte[] bArr);

        public abstract Builder d(Priority priority);
    }

    public static Builder a() {
        return new AutoValue_TransportContext.Builder().d(Priority.DEFAULT);
    }

    public abstract String b();

    public abstract byte[] c();

    public abstract Priority d();

    public TransportContext e(Priority priority) {
        return a().b(b()).d(priority).c(c()).a();
    }

    public final String toString() {
        return String.format("TransportContext(%s, %s, %s)", b(), d(), c() == null ? "" : Base64.encodeToString(c(), 2));
    }
}
