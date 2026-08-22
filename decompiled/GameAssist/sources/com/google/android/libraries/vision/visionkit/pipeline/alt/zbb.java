package com.google.android.libraries.vision.visionkit.pipeline.alt;

import android.graphics.Bitmap;
import com.google.android.libraries.vision.visionkit.pipeline.zbbx;
import com.google.android.libraries.vision.visionkit.pipeline.zbcz;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
final class zbb implements zba {
    public zbb(zbbx zbbxVar) {
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final void a() {
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final void close(long j2, long j3, long j4, long j5, long j6) {
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final long initialize(byte[] bArr, long j2, long j3, long j4, long j5, long j6) {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final long initializeFrameBufferReleaseCallback(long j2) {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final long initializeFrameManager() {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final long initializeIsolationCallback() {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final long initializeResultsCallback() {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final byte[] process(long j2, long j3, long j4, byte[] bArr, int i2, int i3, int i4, int i5) {
        return zbcz.H().i();
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final byte[] processBitmap(long j2, long j3, Bitmap bitmap, int i2, int i3, int i4, int i5) {
        return zbcz.H().i();
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final byte[] processYuvFrame(long j2, long j3, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i2, int i3, int i4, int i5, int i6, int i7) {
        return zbcz.H().i();
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final void start(long j2) {
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final boolean stop(long j2) {
        return true;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final void waitUntilIdle(long j2) {
    }
}
