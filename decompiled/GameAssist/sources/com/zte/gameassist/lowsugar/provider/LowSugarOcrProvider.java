package com.zte.gameassist.lowsugar.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import cn.nubia.multisubscreen.data.TransferData;
import com.zte.gameassist.lowsugar.ai.LowSugarPurposeData;
import com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel;
import com.zte.gameassist.lowsugar.ai.ocr.GameLabOcrModel;
import com.zte.gameassist.lowsugar.ai.ocr.OcrModelFactory;
import com.zte.gameassist.lowsugar.provider.LowSugarOcrProvider;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.zscreenshot.ZScreenshot;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class LowSugarOcrProvider extends ContentProvider {

    /* renamed from: l, reason: collision with root package name */
    private static final AtomicLong f16924l = new AtomicLong(0);

    /* renamed from: c, reason: collision with root package name */
    private Context f16925c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f16926h;

    /* renamed from: i, reason: collision with root package name */
    private HandlerThread f16927i;

    /* renamed from: j, reason: collision with root package name */
    private final ConcurrentLinkedQueue f16928j = new ConcurrentLinkedQueue();

    /* renamed from: k, reason: collision with root package name */
    private volatile OcrRequest f16929k;

    /* renamed from: com.zte.gameassist.lowsugar.provider.LowSugarOcrProvider$1, reason: invalid class name */
    class AnonymousClass1 implements BaseOcrModel.OcrResultCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ OcrRequest f16930a;

        AnonymousClass1(OcrRequest ocrRequest) {
            this.f16930a = ocrRequest;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c() {
            LowSugarOcrProvider.this.f16929k = null;
            LowSugarOcrProvider.this.m();
        }

        @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel.OcrResultCallback
        public void a(boolean z) {
            if (this.f16930a.f16941j) {
                GaLog.k("LowSugarOcrProvider", "onOcrResultCallback request id=" + this.f16930a.f16932a + " already completed (timeout), ignore");
                LowSugarOcrProvider.this.k(this.f16930a);
                return;
            }
            BaseOcrModel baseOcrModel = this.f16930a.f16938g;
            if (baseOcrModel == null) {
                GaLog.k("LowSugarOcrProvider", "onOcrResultCallback ocrModel == null for request id=" + this.f16930a.f16932a);
                this.f16930a.a(false, null);
                return;
            }
            String e2 = baseOcrModel.e();
            GaLog.a("LowSugarOcrProvider", "onOcrResultCallback request id=" + this.f16930a.f16932a + " ocr success, text=" + e2);
            LowSugarOcrProvider.this.k(this.f16930a);
            this.f16930a.a(TextUtils.isEmpty(e2) ^ true, e2);
            LowSugarOcrProvider.this.f16926h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.provider.f
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarOcrProvider.AnonymousClass1.this.c();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class OcrRequest {

        /* renamed from: a, reason: collision with root package name */
        final long f16932a;

        /* renamed from: b, reason: collision with root package name */
        final String f16933b;

        /* renamed from: c, reason: collision with root package name */
        final Rect f16934c;

        /* renamed from: d, reason: collision with root package name */
        final String f16935d;

        /* renamed from: g, reason: collision with root package name */
        BaseOcrModel f16938g;

        /* renamed from: h, reason: collision with root package name */
        LowSugarPurposeData f16939h;

        /* renamed from: i, reason: collision with root package name */
        ZScreenshot f16940i;

        /* renamed from: e, reason: collision with root package name */
        final Bundle f16936e = new Bundle();

        /* renamed from: f, reason: collision with root package name */
        final CountDownLatch f16937f = new CountDownLatch(1);

        /* renamed from: j, reason: collision with root package name */
        volatile boolean f16941j = false;

        OcrRequest(long j2, String str, Rect rect, String str2) {
            this.f16932a = j2;
            this.f16933b = str;
            this.f16934c = rect;
            this.f16935d = str2;
        }

        void a(boolean z, String str) {
            if (this.f16941j) {
                return;
            }
            this.f16941j = true;
            this.f16936e.putBoolean(TransferData.MSG_SUCCESS, z);
            if (!z || TextUtils.isEmpty(str)) {
                this.f16936e.putString("error", z ? "empty_result" : "ocr_failed");
            } else {
                this.f16936e.putString("ocrText", str);
            }
            this.f16937f.countDown();
        }

        void b() {
            if (this.f16941j) {
                return;
            }
            this.f16941j = true;
            this.f16936e.putBoolean(TransferData.MSG_SUCCESS, false);
            this.f16936e.putString("error", "timeout");
            this.f16937f.countDown();
        }
    }

    private Rect j(int i2, int i3, int i4, int i5) {
        DisplayMetrics displayMetrics = this.f16925c.getResources().getDisplayMetrics();
        GaLog.b("LowSugarOcrProvider", "checkRect widthPixels = " + displayMetrics.widthPixels + ", heightPixels = " + displayMetrics.heightPixels);
        Rect rect = (i2 == -1 || i3 == -1 || i4 == -1 || i5 == -1 || i2 > i4 || i3 > i5 || i4 > displayMetrics.widthPixels || i5 > displayMetrics.heightPixels) ? new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels) : new Rect(i2, i3, i4, i5);
        GaLog.b("LowSugarOcrProvider", "checkRect rect = " + rect);
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(OcrRequest ocrRequest) {
        Bitmap bitmap;
        ZScreenshot zScreenshot = ocrRequest.f16940i;
        if (zScreenshot != null) {
            try {
                zScreenshot.f();
            } catch (Exception e2) {
                GaLog.c("LowSugarOcrProvider", "cleanupRequest zScreenshot exception", e2);
            }
            ocrRequest.f16940i = null;
        }
        LowSugarPurposeData lowSugarPurposeData = ocrRequest.f16939h;
        if (lowSugarPurposeData != null && (bitmap = lowSugarPurposeData.f16755d) != null) {
            try {
                if (!bitmap.isRecycled()) {
                    ocrRequest.f16939h.f16755d.recycle();
                }
            } catch (Exception e3) {
                GaLog.c("LowSugarOcrProvider", "cleanupRequest bitmap exception", e3);
            }
            ocrRequest.f16939h.f16755d = null;
        }
        BaseOcrModel baseOcrModel = ocrRequest.f16938g;
        if (baseOcrModel != null) {
            try {
                baseOcrModel.f();
            } catch (Exception e4) {
                GaLog.c("LowSugarOcrProvider", "cleanupRequest ocrModel exception", e4);
            }
            ocrRequest.f16938g = null;
        }
    }

    private Bundle l(String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(TransferData.MSG_SUCCESS, false);
        bundle.putString("error", str);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(OcrRequest ocrRequest) {
        if (this.f16929k == ocrRequest) {
            k(ocrRequest);
            this.f16929k = null;
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(OcrRequest ocrRequest) {
        if (this.f16929k == ocrRequest) {
            k(ocrRequest);
            this.f16929k = null;
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void q(final OcrRequest ocrRequest, final Bitmap bitmap) {
        GaLog.k("LowSugarOcrProvider", "onTakeScreenShotForRequest request id=" + ocrRequest.f16932a);
        if (!this.f16926h.getLooper().isCurrentThread()) {
            this.f16926h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.provider.e
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarOcrProvider.this.p(ocrRequest, bitmap);
                }
            });
            return;
        }
        if (ocrRequest.f16941j) {
            GaLog.k("LowSugarOcrProvider", "onTakeScreenShotForRequest request id=" + ocrRequest.f16932a + " already completed, ignore screenshot");
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
            bitmap.recycle();
            return;
        }
        try {
            Bitmap copy = (ocrRequest.f16940i == null || bitmap == null) ? bitmap : bitmap.copy(Bitmap.Config.ARGB_8888, true);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            LowSugarPurposeData lowSugarPurposeData = ocrRequest.f16939h;
            if (lowSugarPurposeData != null && copy != null) {
                lowSugarPurposeData.f16755d = copy;
            }
            ZScreenshot zScreenshot = ocrRequest.f16940i;
            if (zScreenshot != null) {
                zScreenshot.f();
                ocrRequest.f16940i = null;
            }
            BaseOcrModel baseOcrModel = ocrRequest.f16938g;
            if (baseOcrModel != null && ocrRequest.f16939h != null) {
                baseOcrModel.h();
                ocrRequest.f16938g.i(ocrRequest.f16939h);
            } else {
                k(ocrRequest);
                ocrRequest.a(false, null);
                this.f16929k = null;
                m();
            }
        } catch (Exception e2) {
            GaLog.c("LowSugarOcrProvider", "onTakeScreenShotForRequest request id=" + ocrRequest.f16932a + " exception", e2);
            k(ocrRequest);
            ocrRequest.a(false, null);
            this.f16929k = null;
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public void m() {
        GaLog.b("LowSugarOcrProvider", "processNextRequest mCurrentRequest=" + this.f16929k);
        if (this.f16929k != null) {
            return;
        }
        OcrRequest ocrRequest = (OcrRequest) this.f16928j.poll();
        if (ocrRequest == null) {
            GaLog.b("LowSugarOcrProvider", "processNextRequest request = null ");
            return;
        }
        this.f16929k = ocrRequest;
        GaLog.a("LowSugarOcrProvider", "processNextRequest request id=" + ocrRequest.f16932a);
        try {
            BaseOcrModel a2 = OcrModelFactory.a(this.f16925c, ocrRequest.f16935d);
            ocrRequest.f16938g = a2;
            a2.g(new AnonymousClass1(ocrRequest));
            LowSugarPurposeData lowSugarPurposeData = new LowSugarPurposeData();
            ocrRequest.f16939h = lowSugarPurposeData;
            lowSugarPurposeData.f16761j = ocrRequest.f16934c;
            lowSugarPurposeData.f16754c = ocrRequest.f16933b;
            BaseOcrModel baseOcrModel = ocrRequest.f16938g;
            if (baseOcrModel instanceof GameLabOcrModel) {
                baseOcrModel.i(lowSugarPurposeData);
            } else {
                t(ocrRequest);
            }
        } catch (Exception e2) {
            GaLog.c("LowSugarOcrProvider", "processNextRequest request id=" + ocrRequest.f16932a + " exception", e2);
            k(ocrRequest);
            ocrRequest.a(false, null);
            this.f16929k = null;
            m();
        }
    }

    private void t(final OcrRequest ocrRequest) {
        GaLog.a("LowSugarOcrProvider", "takeShotForRequest request=" + ocrRequest);
        ZScreenshot zScreenshot = new ZScreenshot();
        ocrRequest.f16940i = zScreenshot;
        zScreenshot.e("LowSugarOcrProvider", 0L, 1.0f, ocrRequest.f16934c, new ZScreenshot.OnBufferCallback() { // from class: com.zte.gameassist.lowsugar.provider.d
            @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
            public final void a(Bitmap bitmap) {
                LowSugarOcrProvider.this.q(ocrRequest, bitmap);
            }
        });
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (bundle == null) {
            GaLog.k("LowSugarOcrProvider", "call extra data is null ,not work");
            return l("invalid_bundle");
        }
        GaLog.a("LowSugarOcrProvider", "call in,method:" + str + ", remote:" + getCallingPackage() + "," + bundle.toString());
        if (!str.equalsIgnoreCase("recognizeText")) {
            GaLog.k("LowSugarOcrProvider", "call is not recognizeText, not work");
            return l("unknown_method");
        }
        if (!bundle.containsKey("package")) {
            GaLog.k("LowSugarOcrProvider", "call packageName is null ,not work");
            return l("missing_package");
        }
        Rect rect = (Rect) bundle.getParcelable("rect", Rect.class);
        if (rect != null) {
            i2 = rect.left;
            i4 = rect.top;
            i3 = rect.right;
            i5 = rect.bottom;
        } else {
            i2 = bundle.containsKey("x") ? bundle.getInt("x", -1) : -1;
            int i6 = bundle.containsKey("y") ? bundle.getInt("y", -1) : -1;
            i3 = bundle.containsKey("endX") ? bundle.getInt("endX", -1) : -1;
            i4 = i6;
            i5 = bundle.containsKey("endY") ? bundle.getInt("endY", -1) : -1;
        }
        Rect j2 = j(i2, i4, i3, i5);
        String string = bundle.getString("package");
        if (string == null || string.isEmpty()) {
            GaLog.a("LowSugarOcrProvider", "call packageName is null ,not work");
            return l("invalid_package");
        }
        String language = Locale.getDefault().getLanguage();
        GaLog.e("LowSugarOcrProvider", "call recognizeText language = " + language + ", pkgname = " + string);
        if (!LowSugarUtils.E.contains(language)) {
            GaLog.a("LowSugarOcrProvider", "call current language is " + language + " , and not support ocr, so not work");
            return l("unsupported_language");
        }
        long incrementAndGet = f16924l.incrementAndGet();
        final OcrRequest ocrRequest = new OcrRequest(incrementAndGet, string, j2, language);
        GaLog.a("LowSugarOcrProvider", "call create request id=" + incrementAndGet + ", pkg=" + string);
        this.f16928j.offer(ocrRequest);
        this.f16926h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.provider.a
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarOcrProvider.this.m();
            }
        });
        try {
            if (!ocrRequest.f16937f.await(2000L, TimeUnit.MILLISECONDS)) {
                GaLog.k("LowSugarOcrProvider", "call request id=" + incrementAndGet + " timeout");
                ocrRequest.b();
                this.f16926h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.provider.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        LowSugarOcrProvider.this.n(ocrRequest);
                    }
                });
            }
        } catch (InterruptedException e2) {
            GaLog.c("LowSugarOcrProvider", "call request id=" + incrementAndGet + " interrupted", e2);
            ocrRequest.b();
            this.f16926h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.provider.c
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarOcrProvider.this.o(ocrRequest);
                }
            });
        }
        GaLog.a("LowSugarOcrProvider", "call request id=" + incrementAndGet + " completed, success=" + ocrRequest.f16936e.getBoolean(TransferData.MSG_SUCCESS, false));
        return ocrRequest.f16936e;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f16925c = getContext().getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("LowSugarOcrProvider", -2);
        this.f16927i = handlerThread;
        handlerThread.start();
        this.f16926h = new Handler(this.f16927i.getLooper());
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
