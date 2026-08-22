package com.google.android.libraries.vision.visionkit.pipeline.alt;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcq;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq;
import com.google.android.libraries.vision.visionkit.pipeline.zbbx;
import com.google.android.libraries.vision.visionkit.pipeline.zbcb;
import com.google.android.libraries.vision.visionkit.pipeline.zbcc;
import com.google.android.libraries.vision.visionkit.pipeline.zbcz;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
class NativePipelineImpl implements zba {

    /* renamed from: a, reason: collision with root package name */
    private zbtp f13738a;

    /* renamed from: b, reason: collision with root package name */
    private zbbx f13739b;

    /* renamed from: c, reason: collision with root package name */
    private zbcc f13740c;

    /* renamed from: d, reason: collision with root package name */
    private zbcb f13741d;

    public NativePipelineImpl(zbbx zbbxVar, zbcc zbccVar, zbcb zbcbVar, zbtp zbtpVar) {
        this.f13739b = zbbxVar;
        this.f13740c = zbccVar;
        this.f13741d = zbcbVar;
        this.f13738a = zbtpVar;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public final void a() {
        this.f13738a = null;
        this.f13739b = null;
        this.f13740c = null;
        this.f13741d = null;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native void close(long j2, long j3, long j4, long j5, long j6);

    @Keep
    @UsedByNative("pipeline_jni.cc")
    public void closeFileDescriptor(int i2) {
        this.f13741d.b(i2);
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native long initialize(byte[] bArr, long j2, long j3, long j4, long j5, long j6);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native long initializeFrameBufferReleaseCallback(long j2);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native long initializeFrameManager();

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native long initializeIsolationCallback();

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native long initializeResultsCallback();

    @Keep
    @UsedByNative("pipeline_jni.cc")
    public void onReleaseAtTimestampUs(long j2) {
        this.f13739b.c(j2);
    }

    @Keep
    @UsedByNative("pipeline_jni.cc")
    public void onResult(byte[] bArr) {
        try {
            this.f13740c.a(zbcz.I(bArr, this.f13738a));
        } catch (zbuq e2) {
            zbcq.f12749b.a(e2, "Error in result from JNI layer", new Object[0]);
        }
    }

    @Keep
    @UsedByNative("pipeline_jni.cc")
    public int openFileDescriptor(String str) {
        this.f13741d.d(str);
        return -1;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native byte[] process(long j2, long j3, long j4, byte[] bArr, int i2, int i3, int i4, int i5);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native byte[] processBitmap(long j2, long j3, Bitmap bitmap, int i2, int i3, int i4, int i5);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native byte[] processYuvFrame(long j2, long j3, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i2, int i3, int i4, int i5, int i6, int i7);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native void start(long j2);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native boolean stop(long j2);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.zba
    public native void waitUntilIdle(long j2);

    public NativePipelineImpl(String str, zbbx zbbxVar, zbcc zbccVar, zbcb zbcbVar, zbtp zbtpVar) {
        this(zbbxVar, zbccVar, zbcbVar, zbtpVar);
        System.loadLibrary("mlkit_google_ocr_pipeline");
    }
}
