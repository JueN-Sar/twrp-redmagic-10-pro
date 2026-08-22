package com.google.android.datatransport.runtime;

import com.google.android.datatransport.TransportScheduleCallback;

/* loaded from: classes.dex */
final /* synthetic */ class TransportImpl$$Lambda$1 implements TransportScheduleCallback {

    /* renamed from: a, reason: collision with root package name */
    private static final TransportImpl$$Lambda$1 f10233a = new TransportImpl$$Lambda$1();

    private TransportImpl$$Lambda$1() {
    }

    public static TransportScheduleCallback b() {
        return f10233a;
    }

    @Override // com.google.android.datatransport.TransportScheduleCallback
    public void a(Exception exc) {
        TransportImpl.b(exc);
    }
}
