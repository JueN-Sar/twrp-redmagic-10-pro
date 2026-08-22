package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* loaded from: classes.dex */
final class zae implements View.OnClickListener {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Context f11338c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Intent f11339h;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            this.f11338c.startActivity(this.f11339h);
        } catch (ActivityNotFoundException e2) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e2);
        }
    }
}
