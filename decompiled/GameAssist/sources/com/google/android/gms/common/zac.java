package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;

/* loaded from: classes.dex */
final class zac implements DialogInterface.OnClickListener {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Activity f11293c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ int f11294h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ ActivityResultLauncher f11295i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ GoogleApiAvailability f11296j;

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        PendingIntent e2 = this.f11296j.e(this.f11293c, this.f11294h, 0);
        if (e2 == null) {
            return;
        }
        this.f11295i.a(new IntentSenderRequest.Builder(e2.getIntentSender()).a());
    }
}
