package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
public final class zad {

    /* renamed from: a, reason: collision with root package name */
    public static final Api.ClientKey f13660a;

    /* renamed from: b, reason: collision with root package name */
    public static final Api.ClientKey f13661b;

    /* renamed from: c, reason: collision with root package name */
    public static final Api.AbstractClientBuilder f13662c;

    /* renamed from: d, reason: collision with root package name */
    static final Api.AbstractClientBuilder f13663d;

    /* renamed from: e, reason: collision with root package name */
    public static final Scope f13664e;

    /* renamed from: f, reason: collision with root package name */
    public static final Scope f13665f;

    /* renamed from: g, reason: collision with root package name */
    public static final Api f13666g;

    /* renamed from: h, reason: collision with root package name */
    public static final Api f13667h;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        f13660a = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        f13661b = clientKey2;
        zaa zaaVar = new zaa();
        f13662c = zaaVar;
        zab zabVar = new zab();
        f13663d = zabVar;
        f13664e = new Scope(Constants.EXTRA_PROFILE);
        f13665f = new Scope("email");
        f13666g = new Api("SignIn.API", zaaVar, clientKey);
        f13667h = new Api("SignIn.INTERNAL_API", zabVar, clientKey2);
    }
}
