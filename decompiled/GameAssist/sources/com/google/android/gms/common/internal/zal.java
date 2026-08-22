package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class zal {

    /* renamed from: a, reason: collision with root package name */
    private final SparseIntArray f11069a = new SparseIntArray();

    /* renamed from: b, reason: collision with root package name */
    private GoogleApiAvailabilityLight f11070b;

    public zal(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        Preconditions.i(googleApiAvailabilityLight);
        this.f11070b = googleApiAvailabilityLight;
    }

    public final int a(Context context, int i2) {
        return this.f11069a.get(i2, -1);
    }

    public final int b(Context context, Api.Client client) {
        Preconditions.i(context);
        Preconditions.i(client);
        int i2 = 0;
        if (!client.e()) {
            return 0;
        }
        int n2 = client.n();
        int a2 = a(context, n2);
        if (a2 == -1) {
            int i3 = 0;
            while (true) {
                if (i3 >= this.f11069a.size()) {
                    i2 = -1;
                    break;
                }
                int keyAt = this.f11069a.keyAt(i3);
                if (keyAt > n2 && this.f11069a.get(keyAt) == 0) {
                    break;
                }
                i3++;
            }
            a2 = i2 == -1 ? this.f11070b.j(context, n2) : i2;
            this.f11069a.put(n2, a2);
        }
        return a2;
    }

    public final void c() {
        this.f11069a.clear();
    }
}
