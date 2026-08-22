package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;

@KeepForSdk
/* loaded from: classes.dex */
public final class ApiKey<O extends Api.ApiOptions> {

    /* renamed from: a, reason: collision with root package name */
    private final int f10554a;

    /* renamed from: b, reason: collision with root package name */
    private final Api f10555b;

    /* renamed from: c, reason: collision with root package name */
    private final Api.ApiOptions f10556c;

    /* renamed from: d, reason: collision with root package name */
    private final String f10557d;

    private ApiKey(Api api, Api.ApiOptions apiOptions, String str) {
        this.f10555b = api;
        this.f10556c = apiOptions;
        this.f10557d = str;
        this.f10554a = Objects.b(api, apiOptions, str);
    }

    public static ApiKey a(Api api, Api.ApiOptions apiOptions, String str) {
        return new ApiKey(api, apiOptions, str);
    }

    public final String b() {
        return this.f10555b.d();
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        return Objects.a(this.f10555b, apiKey.f10555b) && Objects.a(this.f10556c, apiKey.f10556c) && Objects.a(this.f10557d, apiKey.f10557d);
    }

    public final int hashCode() {
        return this.f10554a;
    }
}
