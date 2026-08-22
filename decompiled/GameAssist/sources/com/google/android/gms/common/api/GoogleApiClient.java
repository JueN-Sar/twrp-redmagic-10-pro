package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.zada;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

@Deprecated
/* loaded from: classes.dex */
public abstract class GoogleApiClient {

    /* renamed from: a, reason: collision with root package name */
    private static final Set f10536a = Collections.newSetFromMap(new WeakHashMap());

    @Deprecated
    public static final class Builder {
    }

    @Deprecated
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
    }

    @Deprecated
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    public abstract void d();

    public abstract void e();

    public abstract void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public BaseImplementation.ApiMethodImpl g(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new UnsupportedOperationException();
    }

    public Looper h() {
        throw new UnsupportedOperationException();
    }

    public abstract void i(OnConnectionFailedListener onConnectionFailedListener);

    public void j(zada zadaVar) {
        throw new UnsupportedOperationException();
    }

    public void k(zada zadaVar) {
        throw new UnsupportedOperationException();
    }
}
