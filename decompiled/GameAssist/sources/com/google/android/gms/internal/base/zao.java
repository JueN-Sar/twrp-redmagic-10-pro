package com.google.android.gms.internal.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public final class zao extends ContextCompat {
    public static Intent n(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (zan.a()) {
            return context.registerReceiver(broadcastReceiver, intentFilter, true != zan.a() ? 0 : 2);
        }
        return context.registerReceiver(broadcastReceiver, intentFilter);
    }
}
