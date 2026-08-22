package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
/* loaded from: classes.dex */
public class TelemetryLoggingOptions implements Api.ApiOptions.Optional {

    /* renamed from: h, reason: collision with root package name */
    public static final TelemetryLoggingOptions f11036h = a().a();

    /* renamed from: c, reason: collision with root package name */
    private final String f11037c;

    @KeepForSdk
    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f11038a;

        /* synthetic */ Builder(zaac zaacVar) {
        }

        public TelemetryLoggingOptions a() {
            return new TelemetryLoggingOptions(this.f11038a, null);
        }

        public Builder b(String str) {
            this.f11038a = str;
            return this;
        }
    }

    /* synthetic */ TelemetryLoggingOptions(String str, zaad zaadVar) {
        this.f11037c = str;
    }

    public static Builder a() {
        return new Builder(null);
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        String str = this.f11037c;
        if (str != null) {
            bundle.putString("api", str);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TelemetryLoggingOptions) {
            return Objects.a(this.f11037c, ((TelemetryLoggingOptions) obj).f11037c);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.b(this.f11037c);
    }
}
