package com.zte.gameassist.lowsugar.ai.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.ai.LowSugarPurposeData;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;
import com.zte.gameassist.lowsugar.detect.scene.GameSceneFactory;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public abstract class BaseOcrModel {

    /* renamed from: a, reason: collision with root package name */
    protected String f16766a;

    /* renamed from: b, reason: collision with root package name */
    protected Context f16767b;

    /* renamed from: c, reason: collision with root package name */
    protected Handler f16768c;

    /* renamed from: d, reason: collision with root package name */
    protected HandlerThread f16769d;

    /* renamed from: e, reason: collision with root package name */
    protected boolean f16770e;

    /* renamed from: f, reason: collision with root package name */
    protected LowSugarPurposeData f16771f;

    /* renamed from: g, reason: collision with root package name */
    protected OcrResultCallback f16772g;

    /* renamed from: h, reason: collision with root package name */
    protected Handler f16773h = new Handler(Looper.getMainLooper());

    /* renamed from: i, reason: collision with root package name */
    private final Runnable f16774i = new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel.1
        @Override // java.lang.Runnable
        public void run() {
            GaLog.a("BaseOcrModel", "quest ocr over 15 second should end");
            BaseOcrModel.this.a(false);
        }
    };

    public interface OcrResultCallback {
        void a(boolean z);
    }

    public BaseOcrModel(Context context, String str) {
        this.f16766a = "";
        this.f16767b = context;
        this.f16766a = str;
        HandlerThread handlerThread = new HandlerThread("LowSugarOcr", -2);
        this.f16769d = handlerThread;
        handlerThread.start();
        this.f16768c = new Handler(this.f16769d.getLooper());
    }

    private boolean d(LowSugarPurposeData lowSugarPurposeData) {
        if (lowSugarPurposeData == null || TextUtils.isEmpty(lowSugarPurposeData.f16756e)) {
            return false;
        }
        GaLog.a("BaseOcrModel", "parseData result = " + lowSugarPurposeData);
        GameBaseScene a2 = GameSceneFactory.a(this.f16767b, lowSugarPurposeData.f16754c);
        String e2 = e();
        if (TextUtils.isEmpty(e2)) {
            return false;
        }
        LowSugarPurposeData lowSugarPurposeData2 = this.f16771f;
        lowSugarPurposeData2.f16757f = e2;
        if (a2 != null && !a2.e(e2, lowSugarPurposeData2.f16753b)) {
            GaLog.b("BaseOcrModel", "parseData parseAiString has not time string so return!");
            return false;
        }
        if (LowSugarUtils.d(this.f16771f, this.f16767b, null)) {
            GaLog.b("BaseOcrModel", "parseData has same task and effectMode = " + this.f16771f.f16752a);
            LowSugarPurposeData lowSugarPurposeData3 = this.f16771f;
            lowSugarPurposeData3.f16760i = true;
            if (lowSugarPurposeData3.b()) {
                LowSugarUtils.v(this.f16767b.getString(this.f16771f.c() ? R.string.ic_qs_low_sugar_manual_no_purpose : R.string.ic_qs_low_sugar_manual_repeat_purpose), this.f16767b);
            }
            return false;
        }
        GaLog.e("BaseOcrModel", "parseData mCurrAiPurposeData.mDeadLineTime = " + this.f16771f.f16759h);
        LowSugarPurposeData lowSugarPurposeData4 = this.f16771f;
        if (lowSugarPurposeData4.f16759h == 0) {
            long l2 = LowSugarUtils.l(lowSugarPurposeData4.f16757f);
            if (l2 != 0) {
                lowSugarPurposeData.f16759h = System.currentTimeMillis() + l2;
                GaLog.b("BaseOcrModel", "parseData result.mDeadLineTime = " + lowSugarPurposeData.f16759h);
                GaLog.b("BaseOcrModel", "parseData result.mDeadLineTime = " + LowSugarUtils.i(lowSugarPurposeData.f16759h) + LowSugarUtils.m(lowSugarPurposeData.f16759h));
            }
        }
        return true;
    }

    public void a(boolean z) {
        LowSugarPurposeData lowSugarPurposeData;
        this.f16770e = false;
        GaLog.a("BaseOcrModel", "endOcr mIsOcrWorking = " + this.f16770e);
        if (z && (lowSugarPurposeData = this.f16771f) != null) {
            z = d(lowSugarPurposeData);
        }
        OcrResultCallback ocrResultCallback = this.f16772g;
        if (ocrResultCallback != null) {
            ocrResultCallback.a(z);
        }
        LowSugarPurposeData lowSugarPurposeData2 = this.f16771f;
        if (lowSugarPurposeData2 != null) {
            if (!z && lowSugarPurposeData2.b() && !this.f16771f.f16760i) {
                LowSugarUtils.v(this.f16767b.getString(R.string.ic_qs_low_sugar_manual_no_purpose), this.f16767b);
            }
            Bitmap bitmap = this.f16771f.f16755d;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.f16771f.f16755d.recycle();
                this.f16771f.f16755d = null;
                this.f16771f = null;
            }
        }
        this.f16773h.removeCallbacks(this.f16774i);
    }

    public LowSugarPurposeData b() {
        return this.f16771f;
    }

    public boolean c() {
        return this.f16770e;
    }

    public String e() {
        LowSugarPurposeData lowSugarPurposeData = this.f16771f;
        return lowSugarPurposeData != null ? lowSugarPurposeData.f16756e : "";
    }

    public void f() {
    }

    public void g(OcrResultCallback ocrResultCallback) {
        this.f16772g = ocrResultCallback;
    }

    public void h() {
        this.f16770e = true;
        GaLog.a("BaseOcrModel", "startOcr mIsOcrWorking = " + this.f16770e);
        this.f16773h.postDelayed(this.f16774i, 15000L);
    }

    public void i(LowSugarPurposeData lowSugarPurposeData) {
    }
}
