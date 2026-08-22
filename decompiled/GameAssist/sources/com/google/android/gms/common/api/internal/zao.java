package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
final class zao implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final zam f10857c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zap f10858h;

    zao(zap zapVar, zam zamVar) {
        this.f10858h = zapVar;
        this.f10857c = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f10858h.f10859c) {
            ConnectionResult b2 = this.f10857c.b();
            if (b2.T()) {
                zap zapVar = this.f10858h;
                zapVar.mLifecycleFragment.startActivityForResult(GoogleApiActivity.a(zapVar.getActivity(), (PendingIntent) Preconditions.i(b2.R()), this.f10857c.a(), false), 1);
                return;
            }
            zap zapVar2 = this.f10858h;
            if (zapVar2.f10862j.d(zapVar2.getActivity(), b2.G(), null) != null) {
                zap zapVar3 = this.f10858h;
                zapVar3.f10862j.z(zapVar3.getActivity(), zapVar3.mLifecycleFragment, b2.G(), 2, this.f10858h);
                return;
            }
            if (b2.G() != 18) {
                this.f10858h.a(b2, this.f10857c.a());
                return;
            }
            zap zapVar4 = this.f10858h;
            Dialog u = zapVar4.f10862j.u(zapVar4.getActivity(), zapVar4);
            zap zapVar5 = this.f10858h;
            zapVar5.f10862j.v(zapVar5.getActivity().getApplicationContext(), new zan(this, u));
        }
    }
}
