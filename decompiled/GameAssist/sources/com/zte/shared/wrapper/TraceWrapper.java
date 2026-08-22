package com.zte.shared.wrapper;

import android.os.Trace;

/* loaded from: classes2.dex */
public class TraceWrapper {
    public static final long TRACE_TAG_ACTIVITY_MANAGER = 64;
    public static final long TRACE_TAG_ADB = 4194304;
    public static final long TRACE_TAG_AIDL = 16777216;
    public static final long TRACE_TAG_ALWAYS = 1;
    public static final long TRACE_TAG_APP = 4096;
    public static final long TRACE_TAG_AUDIO = 256;
    public static final long TRACE_TAG_BIONIC = 65536;
    public static final long TRACE_TAG_CAMERA = 1024;
    public static final long TRACE_TAG_DALVIK = 16384;
    public static final long TRACE_TAG_DATABASE = 1048576;
    public static final long TRACE_TAG_GRAPHICS = 2;
    public static final long TRACE_TAG_HAL = 2048;
    public static final long TRACE_TAG_INPUT = 4;
    public static final long TRACE_TAG_NETWORK = 2097152;
    public static final long TRACE_TAG_NEVER = 0;
    public static final long TRACE_TAG_NNAPI = 33554432;
    public static final long TRACE_TAG_PACKAGE_MANAGER = 262144;
    public static final long TRACE_TAG_POWER = 131072;
    public static final long TRACE_TAG_RESOURCES = 8192;
    public static final long TRACE_TAG_RRO = 67108864;
    public static final long TRACE_TAG_RS = 32768;
    public static final long TRACE_TAG_SYNC_MANAGER = 128;
    public static final long TRACE_TAG_SYSTEM_SERVER = 524288;
    public static final long TRACE_TAG_THERMAL = 134217728;
    public static final long TRACE_TAG_VIBRATOR = 8388608;
    public static final long TRACE_TAG_VIDEO = 512;
    public static final long TRACE_TAG_VIEW = 8;
    public static final long TRACE_TAG_WEBVIEW = 16;
    public static final long TRACE_TAG_WINDOW_MANAGER = 32;

    public static void asyncTraceBegin(long j2, String str, int i2) {
        Trace.asyncTraceBegin(j2, str, i2);
    }

    public static void asyncTraceEnd(long j2, String str, int i2) {
        Trace.asyncTraceEnd(j2, str, i2);
    }

    public static void asyncTraceForTrackBegin(long j2, String str, String str2, int i2) {
        Trace.asyncTraceForTrackBegin(j2, str, str2, i2);
    }

    public static void asyncTraceForTrackEnd(long j2, String str, int i2) {
        Trace.asyncTraceForTrackEnd(j2, str, i2);
    }

    public static void beginAsyncSection(String str, int i2) {
        Trace.beginAsyncSection(str, i2);
    }

    public static void beginSection(String str) {
        Trace.beginSection(str);
    }

    public static void endAsyncSection(String str, int i2) {
        Trace.endAsyncSection(str, i2);
    }

    public static void endSection() {
        Trace.endSection();
    }

    public static void setAppTracingAllowed(boolean z) {
        Trace.setAppTracingAllowed(z);
    }

    public static void setTracingEnabled(boolean z, int i2) {
        Trace.setTracingEnabled(z, i2);
    }

    public static void traceBegin(long j2, String str) {
        Trace.traceBegin(j2, str);
    }

    public static void traceEnd(long j2) {
        Trace.traceEnd(j2);
    }
}
