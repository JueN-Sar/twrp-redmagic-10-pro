package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

/* loaded from: classes.dex */
class CreationContextFactory {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10254a;

    /* renamed from: b, reason: collision with root package name */
    private final Clock f10255b;

    /* renamed from: c, reason: collision with root package name */
    private final Clock f10256c;

    CreationContextFactory(Context context, Clock clock, Clock clock2) {
        this.f10254a = context;
        this.f10255b = clock;
        this.f10256c = clock2;
    }

    CreationContext a(String str) {
        return CreationContext.a(this.f10254a, this.f10255b, this.f10256c, str);
    }
}
