package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class Common {

    /* renamed from: a, reason: collision with root package name */
    public static final Api.ClientKey f11039a;

    /* renamed from: b, reason: collision with root package name */
    public static final Api f11040b;

    /* renamed from: c, reason: collision with root package name */
    private static final Api.AbstractClientBuilder f11041c;

    /* renamed from: d, reason: collision with root package name */
    public static final zae f11042d;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        f11039a = clientKey;
        zab zabVar = new zab();
        f11041c = zabVar;
        f11040b = new Api("Common.API", zabVar, clientKey);
        f11042d = new zae();
    }
}
