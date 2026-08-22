package com.google.android.libraries.vision.visionkit.pipeline.alt;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
interface zba {
    void a();

    void close(long j2, long j3, long j4, long j5, long j6);

    long initialize(byte[] bArr, long j2, long j3, long j4, long j5, long j6);

    long initializeFrameBufferReleaseCallback(long j2);

    long initializeFrameManager();

    long initializeIsolationCallback();

    long initializeResultsCallback();

    byte[] process(long j2, long j3, long j4, byte[] bArr, int i2, int i3, int i4, int i5);

    byte[] processBitmap(long j2, long j3, Bitmap bitmap, int i2, int i3, int i4, int i5);

    byte[] processYuvFrame(long j2, long j3, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i2, int i3, int i4, int i5, int i6, int i7);

    void start(long j2);

    boolean stop(long j2);

    void waitUntilIdle(long j2);
}
