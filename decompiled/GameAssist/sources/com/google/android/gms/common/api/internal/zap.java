package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {

    /* renamed from: c, reason: collision with root package name */
    protected volatile boolean f10859c;

    /* renamed from: h, reason: collision with root package name */
    protected final AtomicReference f10860h;

    /* renamed from: i, reason: collision with root package name */
    private final Handler f10861i;

    /* renamed from: j, reason: collision with root package name */
    protected final GoogleApiAvailability f10862j;

    @VisibleForTesting
    zap(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.f10860h = new AtomicReference(null);
        this.f10861i = new com.google.android.gms.internal.base.zau(Looper.getMainLooper());
        this.f10862j = googleApiAvailability;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(ConnectionResult connectionResult, int i2) {
        this.f10860h.set(null);
        b(connectionResult, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d() {
        this.f10860h.set(null);
        c();
    }

    private static final int e(zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.a();
    }

    protected abstract void b(ConnectionResult connectionResult, int i2);

    protected abstract void c();

    public final void h(ConnectionResult connectionResult, int i2) {
        AtomicReference atomicReference;
        zam zamVar = new zam(connectionResult, i2);
        do {
            atomicReference = this.f10860h;
            if (atomicReference.compareAndSet(null, zamVar)) {
                this.f10861i.post(new zao(this, zamVar));
                return;
            }
        } while (atomicReference.get() == null);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int i2, int i3, Intent intent) {
        zam zamVar = (zam) this.f10860h.get();
        if (i2 != 1) {
            if (i2 == 2) {
                int i4 = this.f10862j.i(getActivity());
                if (i4 == 0) {
                    d();
                    return;
                } else {
                    if (zamVar == null) {
                        return;
                    }
                    if (zamVar.b().G() == 18 && i4 == 18) {
                        return;
                    }
                }
            }
        } else if (i3 == -1) {
            d();
            return;
        } else if (i3 == 0) {
            if (zamVar != null) {
                a(new ConnectionResult(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, zamVar.b().toString()), e(zamVar));
                return;
            }
            return;
        }
        if (zamVar != null) {
            a(zamVar.b(), zamVar.a());
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        a(new ConnectionResult(13, null), e((zam) this.f10860h.get()));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f10860h.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zamVar = (zam) this.f10860h.get();
        if (zamVar == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", zamVar.a());
        bundle.putInt("failed_status", zamVar.b().G());
        bundle.putParcelable("failed_resolution", zamVar.b().R());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.f10859c = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.f10859c = false;
    }
}
