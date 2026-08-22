package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public final class SignInOptions implements Api.ApiOptions.Optional {

    /* renamed from: p, reason: collision with root package name */
    public static final SignInOptions f13640p = new SignInOptions(false, false, null, false, null, null, false, null, null, null);

    /* renamed from: c, reason: collision with root package name */
    private final boolean f13641c = false;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f13642h = false;

    /* renamed from: i, reason: collision with root package name */
    private final String f13643i = null;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f13644j = false;

    /* renamed from: m, reason: collision with root package name */
    private final boolean f13647m = false;

    /* renamed from: k, reason: collision with root package name */
    private final String f13645k = null;

    /* renamed from: l, reason: collision with root package name */
    private final String f13646l = null;

    /* renamed from: n, reason: collision with root package name */
    private final Long f13648n = null;

    /* renamed from: o, reason: collision with root package name */
    private final Long f13649o = null;

    /* synthetic */ SignInOptions(boolean z, boolean z2, String str, boolean z3, String str2, String str3, boolean z4, Long l2, Long l3, zaf zafVar) {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignInOptions)) {
            return false;
        }
        boolean z = ((SignInOptions) obj).f13641c;
        return Objects.a(null, null) && Objects.a(null, null) && Objects.a(null, null) && Objects.a(null, null) && Objects.a(null, null);
    }

    public final int hashCode() {
        Boolean bool = Boolean.FALSE;
        return Objects.b(bool, bool, null, bool, bool, null, null, null, null);
    }
}
