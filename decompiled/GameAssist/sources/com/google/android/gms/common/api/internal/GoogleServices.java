package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzah;

@KeepForSdk
@Deprecated
/* loaded from: classes.dex */
public final class GoogleServices {

    /* renamed from: e, reason: collision with root package name */
    private static final Object f10597e = new Object();

    /* renamed from: f, reason: collision with root package name */
    private static GoogleServices f10598f;

    /* renamed from: a, reason: collision with root package name */
    private final String f10599a;

    /* renamed from: b, reason: collision with root package name */
    private final Status f10600b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f10601c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f10602d;

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(String str, boolean z) {
        this.f10599a = str;
        this.f10600b = Status.f10543l;
        this.f10601c = z;
        this.f10602d = !z;
    }

    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        synchronized (f10597e) {
            f10598f = null;
        }
    }

    @KeepForSdk
    @VisibleForTesting
    Status checkGoogleAppId(String str) {
        String str2 = this.f10599a;
        if (str2 == null || str2.equals(str)) {
            return Status.f10543l;
        }
        return new Status(10, "Initialize was called with two different Google App IDs.  Only the first app ID will be used: '" + this.f10599a + "'.");
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            int integer = resources.getInteger(identifier);
            boolean z = integer == 0;
            r2 = integer != 0;
            this.f10602d = z;
        } else {
            this.f10602d = false;
        }
        this.f10601c = r2;
        String b2 = zzah.b(context);
        b2 = b2 == null ? new StringResourceValueReader(context).a("google_app_id") : b2;
        if (TextUtils.isEmpty(b2)) {
            this.f10600b = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.f10599a = null;
        } else {
            this.f10599a = b2;
            this.f10600b = Status.f10543l;
        }
    }
}
