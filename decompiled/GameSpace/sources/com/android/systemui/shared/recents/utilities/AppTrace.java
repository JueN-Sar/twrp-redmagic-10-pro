package com.android.systemui.shared.recents.utilities;

import android.os.Trace;

/* loaded from: classes2.dex */
public class AppTrace {
    public static void beginSection(String str) {
        Trace.beginSection(str);
    }

    public static void count(String str, int i) {
        Trace.traceCounter(4096L, str, i);
    }

    public static void end(String str) {
        Trace.asyncTraceEnd(4096L, str, 0);
    }

    public static void end(String str, int i) {
        Trace.asyncTraceEnd(4096L, str, i);
    }

    public static void endSection() {
        Trace.endSection();
    }

    public static void start(String str) {
        Trace.asyncTraceBegin(4096L, str, 0);
    }

    public static void start(String str, int i) {
        Trace.asyncTraceBegin(4096L, str, i);
    }
}
