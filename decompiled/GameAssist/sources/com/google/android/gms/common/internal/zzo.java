package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes.dex */
public final class zzo {

    /* renamed from: f, reason: collision with root package name */
    private static final Uri f11109f = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();

    /* renamed from: a, reason: collision with root package name */
    private final String f11110a;

    /* renamed from: b, reason: collision with root package name */
    private final String f11111b;

    /* renamed from: c, reason: collision with root package name */
    private final ComponentName f11112c;

    /* renamed from: d, reason: collision with root package name */
    private final int f11113d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f11114e;

    public zzo(String str, String str2, int i2, boolean z) {
        Preconditions.f(str);
        this.f11110a = str;
        Preconditions.f(str2);
        this.f11111b = str2;
        this.f11112c = null;
        this.f11113d = 4225;
        this.f11114e = z;
    }

    public final ComponentName a() {
        return this.f11112c;
    }

    public final Intent b(Context context) {
        Bundle bundle;
        if (this.f11110a == null) {
            return new Intent().setComponent(this.f11112c);
        }
        if (this.f11114e) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("serviceActionBundleKey", this.f11110a);
            try {
                bundle = context.getContentResolver().call(f11109f, "serviceIntentCall", (String) null, bundle2);
            } catch (IllegalArgumentException e2) {
                Log.w("ConnectionStatusConfig", "Dynamic intent resolution failed: ".concat(e2.toString()));
                bundle = null;
            }
            r2 = bundle != null ? (Intent) bundle.getParcelable("serviceResponseIntentKey") : null;
            if (r2 == null) {
                Log.w("ConnectionStatusConfig", "Dynamic lookup for intent failed for action: ".concat(String.valueOf(this.f11110a)));
            }
        }
        return r2 == null ? new Intent(this.f11110a).setPackage(this.f11111b) : r2;
    }

    public final String c() {
        return this.f11111b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo zzoVar = (zzo) obj;
        return Objects.a(this.f11110a, zzoVar.f11110a) && Objects.a(this.f11111b, zzoVar.f11111b) && Objects.a(this.f11112c, zzoVar.f11112c) && this.f11114e == zzoVar.f11114e;
    }

    public final int hashCode() {
        return Objects.b(this.f11110a, this.f11111b, this.f11112c, 4225, Boolean.valueOf(this.f11114e));
    }

    public final String toString() {
        String str = this.f11110a;
        if (str != null) {
            return str;
        }
        Preconditions.i(this.f11112c);
        return this.f11112c.flattenToString();
    }
}
