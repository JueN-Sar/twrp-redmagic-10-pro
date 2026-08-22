package com.zte.plugin.reminder.permission;

import android.content.Context;

/* loaded from: classes2.dex */
public class RequestPermissionActivity extends RequestPermissionActivityBase {

    /* renamed from: i, reason: collision with root package name */
    protected static final String[] f18090i = {"org.codeaurora.permission.POWER_OFF_ALARM"};

    public static boolean j(Context context) {
        return RequestPermissionActivityBase.i(context, f18090i, RequestPermissionActivity.class);
    }

    @Override // com.zte.plugin.reminder.permission.RequestPermissionActivityBase
    protected String[] a() {
        return f18090i;
    }
}
