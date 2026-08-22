package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class StringResourceValueReader {

    /* renamed from: a, reason: collision with root package name */
    private final Resources f11032a;

    /* renamed from: b, reason: collision with root package name */
    private final String f11033b;

    public StringResourceValueReader(Context context) {
        Preconditions.i(context);
        Resources resources = context.getResources();
        this.f11032a = resources;
        this.f11033b = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
    }

    public String a(String str) {
        int identifier = this.f11032a.getIdentifier(str, "string", this.f11033b);
        if (identifier == 0) {
            return null;
        }
        return this.f11032a.getString(identifier);
    }
}
