package com.google.android.gms.common.server.response;

import androidx.annotation.NonNull;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public class FastParser<T extends FastJsonResponse> {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f11206a = {'u', 'l', 'l'};

    /* renamed from: b, reason: collision with root package name */
    private static final char[] f11207b = {'r', 'u', 'e'};

    /* renamed from: c, reason: collision with root package name */
    private static final char[] f11208c = {'r', 'u', 'e', '\"'};

    /* renamed from: d, reason: collision with root package name */
    private static final char[] f11209d = {'a', 'l', NubiaTextClock.SECONDS, 'e'};

    /* renamed from: e, reason: collision with root package name */
    private static final char[] f11210e = {'a', 'l', NubiaTextClock.SECONDS, 'e', '\"'};

    /* renamed from: f, reason: collision with root package name */
    private static final char[] f11211f = {'\n'};

    /* renamed from: g, reason: collision with root package name */
    private static final zai f11212g = new zaa();

    /* renamed from: h, reason: collision with root package name */
    private static final zai f11213h = new zab();

    /* renamed from: i, reason: collision with root package name */
    private static final zai f11214i = new zac();

    /* renamed from: j, reason: collision with root package name */
    private static final zai f11215j = new zad();

    /* renamed from: k, reason: collision with root package name */
    private static final zai f11216k = new zae();

    /* renamed from: l, reason: collision with root package name */
    private static final zai f11217l = new zaf();

    /* renamed from: m, reason: collision with root package name */
    private static final zai f11218m = new zag();

    /* renamed from: n, reason: collision with root package name */
    private static final zai f11219n = new zah();

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        public ParseException(@NonNull String str) {
            super(str);
        }

        public ParseException(@NonNull String str, @NonNull Throwable th) {
            super("Error instantiating inner object", th);
        }

        public ParseException(@NonNull Throwable th) {
            super(th);
        }
    }
}
