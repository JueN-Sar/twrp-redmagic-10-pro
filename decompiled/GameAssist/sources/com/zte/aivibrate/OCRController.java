package com.zte.aivibrate;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.zte.aivibrate.util.AIVibrateLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OCRController {

    /* renamed from: h, reason: collision with root package name */
    private static final Uri f16172h = Uri.parse("content://com.zte.aiengine.ocr.provider");

    /* renamed from: a, reason: collision with root package name */
    private final List f16173a;

    /* renamed from: b, reason: collision with root package name */
    private Context f16174b;

    /* renamed from: c, reason: collision with root package name */
    private Vibrate4DController f16175c;

    /* renamed from: d, reason: collision with root package name */
    private ContentObserver f16176d;

    /* renamed from: e, reason: collision with root package name */
    private Handler f16177e;

    /* renamed from: f, reason: collision with root package name */
    private long f16178f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f16179g;

    public interface Callback {
        default void a(List list) {
        }
    }

    private static class Holder {

        /* renamed from: a, reason: collision with root package name */
        private static final OCRController f16181a = new OCRController();
    }

    public static OCRController e() {
        return Holder.f16181a;
    }

    private List f(String str) {
        JSONArray jSONArray = new JSONArray(str);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject.has("text")) {
                String string = jSONObject.getString("text");
                if (!string.isEmpty()) {
                    arrayList.add(string);
                }
            }
        }
        return arrayList;
    }

    private void j(String str) {
        ArrayList arrayList;
        try {
            List f2 = f(str);
            synchronized (this.f16173a) {
                arrayList = new ArrayList(this.f16173a);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Callback) it.next()).a(f2);
            }
        } catch (Exception e2) {
            AIVibrateLog.d(".OCRController", "parse error" + e2);
        }
    }

    public void a(Callback callback) {
        synchronized (this.f16173a) {
            try {
                if (!this.f16173a.contains(callback)) {
                    this.f16173a.add(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a0, code lost:
    
        if (0 == 0) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b() {
        /*
            r8 = this;
            java.lang.String r0 = ".OCRController"
            java.lang.String r1 = ""
            r2 = 0
            android.content.Context r3 = r8.f16174b     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            android.net.Uri r4 = com.zte.aivibrate.OCRController.f16172h     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            android.content.ContentProviderClient r2 = r3.acquireUnstableContentProviderClient(r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            if (r2 != 0) goto L21
            r8.m()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            if (r2 == 0) goto L1b
            r2.close()
        L1b:
            return
        L1c:
            r8 = move-exception
            goto Lb0
        L1f:
            r3 = move-exception
            goto L89
        L21:
            android.os.Bundle r3 = new android.os.Bundle     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            r3.<init>()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            r4.<init>()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r5 = "ALL"
            r4.put(r5)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            r5.<init>()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r6 = "jsonParam :"
            r5.append(r6)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            r5.append(r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            com.zte.aivibrate.util.AIVibrateLog.b(r0, r5)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r5 = "key_word_param"
            r3.putString(r5, r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r4 = "ai_ocr_predict_get"
            java.lang.String r5 = "ai_ocr_predict_get_common"
            android.os.Bundle r3 = r2.call(r4, r5, r3)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            if (r3 == 0) goto L85
            java.lang.String r4 = "ai_ocr_predict_result"
            java.lang.String r1 = r3.getString(r4, r1)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            r3.<init>()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r4 = "callAiEngineOcrGet AiOcrPredictResult="
            r3.append(r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            r3.append(r1)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r4 = " , time="
            r3.append(r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            long r6 = r8.f16178f     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            long r4 = r4 - r6
            r3.append(r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r4 = "ms"
            r3.append(r4)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
            com.zte.aivibrate.util.AIVibrateLog.b(r0, r3)     // Catch: java.lang.Throwable -> L1c java.lang.Exception -> L1f
        L85:
            r2.close()
            goto La3
        L89:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c
            r4.<init>()     // Catch: java.lang.Throwable -> L1c
            java.lang.String r5 = "callAiEngineOcrGet error"
            r4.append(r5)     // Catch: java.lang.Throwable -> L1c
            r4.append(r3)     // Catch: java.lang.Throwable -> L1c
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L1c
            com.zte.aivibrate.util.AIVibrateLog.d(r0, r3)     // Catch: java.lang.Throwable -> L1c
            r8.m()     // Catch: java.lang.Throwable -> L1c
            if (r2 == 0) goto La3
            goto L85
        La3:
            r8.m()
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto Laf
            r8.j(r1)
        Laf:
            return
        Lb0:
            if (r2 == 0) goto Lb5
            r2.close()
        Lb5:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.aivibrate.OCRController.b():void");
    }

    public void c(Bitmap bitmap) {
        k();
        this.f16178f = System.currentTimeMillis();
        long currentTimeMillis = System.currentTimeMillis();
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                contentProviderClient = this.f16174b.getContentResolver().acquireUnstableContentProviderClient(f16172h);
            } catch (Exception e2) {
                AIVibrateLog.d(".OCRController", "callAiEngineOcrRequest error" + e2);
                m();
                if (0 == 0) {
                    return;
                }
            }
            if (contentProviderClient == null) {
                AIVibrateLog.d(".OCRController", "ContentProviderClient is null");
                m();
                if (contentProviderClient != null) {
                    contentProviderClient.release();
                    return;
                }
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putDouble("Precision_ratio", 0.8d);
            bundle.putParcelable("key_bitmap_param", bitmap);
            contentProviderClient.call("ai_ocr_predict_request", "ai_ocr_predict_request_common", bundle);
            AIVibrateLog.b(".OCRController", "callAiEngineOcrRequest AI_OCR_PREDICT_REQUEST time = " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            contentProviderClient.release();
        } catch (Throwable th) {
            if (0 != 0) {
                contentProviderClient.release();
            }
            throw th;
        }
    }

    public void d() {
        m();
    }

    public void g(Context context, Vibrate4DController vibrate4DController) {
        this.f16174b = context;
        this.f16175c = vibrate4DController;
    }

    public void h() {
        m();
    }

    public void i() {
        this.f16177e = new Handler(this.f16175c.r());
        this.f16176d = new ContentObserver(this.f16177e) { // from class: com.zte.aivibrate.OCRController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                OCRController.this.b();
            }
        };
    }

    public void k() {
        if (this.f16179g) {
            return;
        }
        try {
            ContentResolver contentResolver = this.f16174b.getContentResolver();
            if (contentResolver == null) {
                AIVibrateLog.d(".OCRController", "registerAIEngineOcrObserver ContentResolver is null");
            } else {
                contentResolver.registerContentObserver(f16172h, false, this.f16176d);
                this.f16179g = true;
            }
        } catch (Exception e2) {
            AIVibrateLog.d(".OCRController", "registerAIEngineOcrObserver error " + e2);
        }
    }

    public void l(Callback callback) {
        synchronized (this.f16173a) {
            this.f16173a.remove(callback);
        }
    }

    public void m() {
        if (this.f16179g) {
            try {
                this.f16174b.getContentResolver().unregisterContentObserver(this.f16176d);
                this.f16179g = false;
            } catch (Exception e2) {
                AIVibrateLog.d(".OCRController", "unregisterAiEngineObserver error" + e2);
            }
        }
    }

    private OCRController() {
        this.f16173a = new ArrayList();
    }
}
