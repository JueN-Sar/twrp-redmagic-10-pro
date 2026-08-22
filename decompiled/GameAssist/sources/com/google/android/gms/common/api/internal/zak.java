package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public final class zak extends zap {

    /* renamed from: k, reason: collision with root package name */
    private final SparseArray f10847k;

    private final zaj j(int i2) {
        if (this.f10847k.size() <= i2) {
            return null;
        }
        SparseArray sparseArray = this.f10847k;
        return (zaj) sparseArray.get(sparseArray.keyAt(i2));
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void b(ConnectionResult connectionResult, int i2) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i2 < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaj zajVar = (zaj) this.f10847k.get(i2);
        if (zajVar != null) {
            i(i2);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zajVar.f10845c;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void c() {
        for (int i2 = 0; i2 < this.f10847k.size(); i2++) {
            zaj j2 = j(i2);
            if (j2 != null) {
                j2.f10844b.d();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i2 = 0; i2 < this.f10847k.size(); i2++) {
            zaj j2 = j(i2);
            if (j2 != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(j2.f10843a);
                printWriter.println(":");
                j2.f10844b.f(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void i(int i2) {
        zaj zajVar = (zaj) this.f10847k.get(i2);
        this.f10847k.remove(i2);
        if (zajVar != null) {
            zajVar.f10844b.i(zajVar);
            zajVar.f10844b.e();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        SparseArray sparseArray = this.f10847k;
        Log.d("AutoManageHelper", "onStart " + this.f10859c + " " + String.valueOf(sparseArray));
        if (this.f10860h.get() == null) {
            for (int i2 = 0; i2 < this.f10847k.size(); i2++) {
                zaj j2 = j(i2);
                if (j2 != null) {
                    j2.f10844b.d();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        for (int i2 = 0; i2 < this.f10847k.size(); i2++) {
            zaj j2 = j(i2);
            if (j2 != null) {
                j2.f10844b.e();
            }
        }
    }
}
