package com.zte.gameassist;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/* loaded from: classes2.dex */
public class BaseApplication extends Application {

    /* renamed from: c, reason: collision with root package name */
    protected static Context f16332c;

    public static Context a() {
        return f16332c;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f16332c = this;
    }
}
