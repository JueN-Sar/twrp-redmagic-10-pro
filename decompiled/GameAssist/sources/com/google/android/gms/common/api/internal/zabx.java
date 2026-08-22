package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* loaded from: classes.dex */
public final class zabx extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    Context f10787a;

    /* renamed from: b, reason: collision with root package name */
    private final zabw f10788b;

    public zabx(zabw zabwVar) {
        this.f10788b = zabwVar;
    }

    public final void a(Context context) {
        this.f10787a = context;
    }

    public final synchronized void b() {
        try {
            Context context = this.f10787a;
            if (context != null) {
                context.unregisterReceiver(this);
            }
            this.f10787a = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.f10788b.a();
            b();
        }
    }
}
