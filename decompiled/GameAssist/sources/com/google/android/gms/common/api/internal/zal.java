package com.google.android.gms.common.api.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

/* loaded from: classes.dex */
public final class zal {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayMap f10848a;

    /* renamed from: b, reason: collision with root package name */
    private final ArrayMap f10849b;

    /* renamed from: c, reason: collision with root package name */
    private final TaskCompletionSource f10850c;

    /* renamed from: d, reason: collision with root package name */
    private int f10851d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f10852e;

    public final Set a() {
        return this.f10848a.keySet();
    }

    public final void b(ApiKey apiKey, ConnectionResult connectionResult, String str) {
        this.f10848a.put(apiKey, connectionResult);
        this.f10849b.put(apiKey, str);
        this.f10851d--;
        if (!connectionResult.W()) {
            this.f10852e = true;
        }
        if (this.f10851d == 0) {
            if (!this.f10852e) {
                this.f10850c.c(this.f10849b);
            } else {
                this.f10850c.b(new AvailabilityException(this.f10848a));
            }
        }
    }
}
