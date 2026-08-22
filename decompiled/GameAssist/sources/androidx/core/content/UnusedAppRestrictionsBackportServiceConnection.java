package androidx.core.content;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;

/* loaded from: classes.dex */
class UnusedAppRestrictionsBackportServiceConnection implements ServiceConnection {

    /* renamed from: c, reason: collision with root package name */
    ResolvableFuture f2854c;

    @Nullable
    @VisibleForTesting
    IUnusedAppRestrictionsBackportService mUnusedAppRestrictionsService;

    private IUnusedAppRestrictionsBackportCallback a() {
        return new IUnusedAppRestrictionsBackportCallback.Stub() { // from class: androidx.core.content.UnusedAppRestrictionsBackportServiceConnection.1
            @Override // androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback
            public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z2) {
                if (!z) {
                    UnusedAppRestrictionsBackportServiceConnection.this.f2854c.o(0);
                    Log.e("PackageManagerCompat", "Unable to retrieve the permission revocation setting from the backport");
                } else if (z2) {
                    UnusedAppRestrictionsBackportServiceConnection.this.f2854c.o(3);
                } else {
                    UnusedAppRestrictionsBackportServiceConnection.this.f2854c.o(2);
                }
            }
        };
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IUnusedAppRestrictionsBackportService asInterface = IUnusedAppRestrictionsBackportService.Stub.asInterface(iBinder);
        this.mUnusedAppRestrictionsService = asInterface;
        try {
            asInterface.isPermissionRevocationEnabledForApp(a());
        } catch (RemoteException unused) {
            this.f2854c.o(0);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.mUnusedAppRestrictionsService = null;
    }
}
