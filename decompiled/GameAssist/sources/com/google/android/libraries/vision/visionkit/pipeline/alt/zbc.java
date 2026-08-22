package com.google.android.libraries.vision.visionkit.pipeline.alt;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcq;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq;
import com.google.android.libraries.vision.visionkit.pipeline.zbbe;
import com.google.android.libraries.vision.visionkit.pipeline.zbbf;
import com.google.android.libraries.vision.visionkit.pipeline.zbbx;
import com.google.android.libraries.vision.visionkit.pipeline.zbca;
import com.google.android.libraries.vision.visionkit.pipeline.zbcb;
import com.google.android.libraries.vision.visionkit.pipeline.zbcc;
import com.google.android.libraries.vision.visionkit.pipeline.zbcz;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class zbc implements zbbx, zbcc, zbcb {

    /* renamed from: a, reason: collision with root package name */
    private final zbbf f13742a;

    /* renamed from: b, reason: collision with root package name */
    private final zba f13743b;

    /* renamed from: c, reason: collision with root package name */
    private long f13744c;

    /* renamed from: d, reason: collision with root package name */
    private final long f13745d;

    /* renamed from: e, reason: collision with root package name */
    private final long f13746e;

    /* renamed from: f, reason: collision with root package name */
    private final long f13747f;

    /* renamed from: g, reason: collision with root package name */
    private final long f13748g;

    /* renamed from: h, reason: collision with root package name */
    protected final zbtp f13749h;

    public zbc(zbca zbcaVar, String str) {
        zbtp b2 = zbtp.b();
        zbtp a2 = b2 == null ? zbtp.a() : b2;
        if (zbcaVar.L()) {
            this.f13743b = new zbb(this);
        } else if (zbcaVar.K()) {
            this.f13743b = new NativePipelineImpl(this, this, this, a2);
        } else {
            this.f13743b = new NativePipelineImpl("mlkit_google_ocr_pipeline", this, this, this, a2);
        }
        if (zbcaVar.M()) {
            this.f13742a = new zbbf(zbcaVar.E());
        } else {
            this.f13742a = new zbbf(10);
        }
        this.f13749h = a2;
        long initializeFrameManager = this.f13743b.initializeFrameManager();
        this.f13745d = initializeFrameManager;
        long initializeFrameBufferReleaseCallback = this.f13743b.initializeFrameBufferReleaseCallback(initializeFrameManager);
        this.f13746e = initializeFrameBufferReleaseCallback;
        long initializeResultsCallback = this.f13743b.initializeResultsCallback();
        this.f13747f = initializeResultsCallback;
        long initializeIsolationCallback = this.f13743b.initializeIsolationCallback();
        this.f13748g = initializeIsolationCallback;
        this.f13744c = this.f13743b.initialize(zbcaVar.i(), initializeFrameBufferReleaseCallback, initializeResultsCallback, initializeIsolationCallback, 0L, 0L);
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.zbcc
    public final void a(zbcz zbczVar) {
        zbcq.f12749b.b(this, "Pipeline received results: ".concat(String.valueOf(zbczVar)), new Object[0]);
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.zbcb
    public final void b(int i2) {
        Log.w("VKP", "closeFileDescriptor called but is not available for this pipeline. Ignoring call.");
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.zbbx
    public final void c(long j2) {
        this.f13742a.a(j2);
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.zbcb
    public final int d(String str) {
        Log.w("VKP", "openFileDescriptor called but is not available for this pipeline. Ignoring call.");
        return -1;
    }

    public final zbki e(zbbe zbbeVar) {
        byte[] process;
        if (this.f13744c == 0) {
            throw new IllegalStateException("Pipeline has been closed or was not initialized");
        }
        if (!this.f13742a.b(zbbeVar, zbbeVar.a()) || (process = this.f13743b.process(this.f13744c, this.f13745d, zbbeVar.a(), zbbeVar.c(), zbbeVar.b().b(), zbbeVar.b().a(), zbbeVar.d() - 1, zbbeVar.e() - 1)) == null) {
            return zbki.d();
        }
        try {
            return zbki.e(zbcz.I(process, this.f13749h));
        } catch (zbuq e2) {
            throw new IllegalStateException("Could not parse results", e2);
        }
    }

    public final synchronized void f() {
        long j2 = this.f13744c;
        if (j2 != 0) {
            this.f13743b.stop(j2);
            this.f13743b.close(this.f13744c, this.f13745d, this.f13746e, this.f13747f, this.f13748g);
            this.f13744c = 0L;
            this.f13743b.a();
        }
    }

    public final void g() {
        long j2 = this.f13744c;
        if (j2 == 0) {
            throw new PipelineException(zbd.FAILED_PRECONDITION.ordinal(), "Pipeline has been closed or was not initialized");
        }
        try {
            this.f13743b.start(j2);
            this.f13743b.waitUntilIdle(this.f13744c);
        } catch (PipelineException e2) {
            this.f13743b.stop(this.f13744c);
            throw e2;
        }
    }

    public final void h() {
        long j2 = this.f13744c;
        if (j2 == 0) {
            throw new IllegalStateException("Pipeline has been closed or was not initialized");
        }
        if (!this.f13743b.stop(j2)) {
            throw new IllegalStateException("Pipeline did not stop successfully.");
        }
    }

    public final zbki i(long j2, Bitmap bitmap, int i2) {
        if (this.f13744c == 0) {
            throw new IllegalStateException("Pipeline has been closed or was not initialized");
        }
        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            throw new IllegalArgumentException("Unsupported bitmap config ".concat(String.valueOf(bitmap.getConfig())));
        }
        byte[] processBitmap = this.f13743b.processBitmap(this.f13744c, j2, bitmap, bitmap.getWidth(), bitmap.getHeight(), 0, i2 - 1);
        if (processBitmap == null) {
            return zbki.d();
        }
        try {
            return zbki.e(zbcz.I(processBitmap, this.f13749h));
        } catch (zbuq e2) {
            throw new IllegalStateException("Could not parse results", e2);
        }
    }

    public final zbki j(long j2, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (this.f13744c == 0) {
            throw new IllegalStateException("Pipeline has been closed or was not initialized");
        }
        if (!byteBuffer.isDirect() || !byteBuffer2.isDirect() || !byteBuffer3.isDirect()) {
            throw new IllegalStateException("Byte buffers are not direct.");
        }
        byte[] processYuvFrame = this.f13743b.processYuvFrame(this.f13744c, j2, byteBuffer, byteBuffer2, byteBuffer3, i2, i3, i4, i5, i6, i7 - 1);
        if (processYuvFrame == null) {
            return zbki.d();
        }
        try {
            return zbki.e(zbcz.I(processYuvFrame, this.f13749h));
        } catch (zbuq e2) {
            throw new IllegalStateException("Could not parse results", e2);
        }
    }
}
