package com.zte.gameassist.common;

import android.content.Context;
import android.os.Handler;

/* loaded from: classes2.dex */
public class ModuleProxyContext {

    /* renamed from: a, reason: collision with root package name */
    private final Handler f16530a = new Handler(ThreadManager.c().b());

    /* renamed from: b, reason: collision with root package name */
    private final Context f16531b;

    public ModuleProxyContext(Context context) {
        this.f16531b = context;
    }

    public Context a() {
        return this.f16531b;
    }

    public Handler b() {
        return this.f16530a;
    }
}
