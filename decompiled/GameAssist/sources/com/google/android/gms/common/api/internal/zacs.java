package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

@WorkerThread
/* loaded from: classes.dex */
public interface zacs {
    void b(IAccountAccessor iAccountAccessor, Set set);

    void c(ConnectionResult connectionResult);

    void d(int i2);
}
