package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
final class zad extends zau {

    /* renamed from: a, reason: collision with root package name */
    private final Context f11297a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ GoogleApiAvailability f11298b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zad(GoogleApiAvailability googleApiAvailability, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.f11298b = googleApiAvailability;
        this.f11297a = context.getApplicationContext();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 != 1) {
            Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + i2);
            return;
        }
        GoogleApiAvailability googleApiAvailability = this.f11298b;
        int i3 = googleApiAvailability.i(this.f11297a);
        if (googleApiAvailability.m(i3)) {
            this.f11298b.s(this.f11297a, i3);
        }
    }
}
