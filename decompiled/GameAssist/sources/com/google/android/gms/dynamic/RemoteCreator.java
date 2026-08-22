package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class RemoteCreator<T> {

    /* renamed from: a, reason: collision with root package name */
    private final String f11336a;

    /* renamed from: b, reason: collision with root package name */
    private Object f11337b;

    @KeepForSdk
    public static class RemoteCreatorException extends Exception {
        @KeepForSdk
        public RemoteCreatorException(@NonNull String str) {
            super(str);
        }

        @KeepForSdk
        public RemoteCreatorException(@NonNull String str, @NonNull Throwable th) {
            super(str, th);
        }
    }

    protected RemoteCreator(String str) {
        this.f11336a = str;
    }

    protected abstract Object a(IBinder iBinder);

    protected final Object b(Context context) {
        if (this.f11337b == null) {
            Preconditions.i(context);
            Context e2 = GooglePlayServicesUtilLight.e(context);
            if (e2 == null) {
                throw new RemoteCreatorException("Could not get remote context.");
            }
            try {
                this.f11337b = a((IBinder) e2.getClassLoader().loadClass(this.f11336a).newInstance());
            } catch (ClassNotFoundException e3) {
                throw new RemoteCreatorException("Could not load creator class.", e3);
            } catch (IllegalAccessException e4) {
                throw new RemoteCreatorException("Could not access creator.", e4);
            } catch (InstantiationException e5) {
                throw new RemoteCreatorException("Could not instantiate creator.", e5);
            }
        }
        return this.f11337b;
    }
}
