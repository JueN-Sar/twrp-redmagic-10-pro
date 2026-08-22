package com.zte.gameassist.lowsugar.ai.ocr;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.zte.gameassist.lowsugar.ai.LowSugarOcrData;
import com.zte.gameassist.lowsugar.ai.LowSugarPurposeData;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;
import com.zte.gameassist.lowsugar.detect.scene.GameSceneFactory;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ZteOcrModel extends BaseOcrModel {

    /* renamed from: l, reason: collision with root package name */
    protected static final Uri f16780l = Uri.parse("content://com.zte.aiengine.ocr.provider");

    /* renamed from: j, reason: collision with root package name */
    private boolean f16781j;

    /* renamed from: k, reason: collision with root package name */
    private final ContentObserver f16782k;

    public ZteOcrModel(Context context, String str) {
        super(context, str);
        this.f16781j = false;
        this.f16782k = new ContentObserver(this.f16773h) { // from class: com.zte.gameassist.lowsugar.ai.ocr.ZteOcrModel.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                GaLog.e("ZteOcrModel", "URI_AI_ENGINE onchange");
                ZteOcrModel.this.m();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        GaLog.e("ZteOcrModel", "callAiEngineOcrGet start");
        this.f16768c.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.ocr.d
            @Override // java.lang.Runnable
            public final void run() {
                ZteOcrModel.this.o();
            }
        });
    }

    private void n(final LowSugarPurposeData lowSugarPurposeData) {
        GaLog.e("ZteOcrModel", "callAiEngineOcrRequest start");
        this.f16768c.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.ocr.c
            @Override // java.lang.Runnable
            public final void run() {
                ZteOcrModel.this.p(lowSugarPurposeData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        ContentProviderClient acquireUnstableContentProviderClient;
        String str = "";
        ContentProviderClient contentProviderClient = null;
        try {
        } catch (Exception e2) {
            GaLog.c("ZteOcrModel", "callAiEngineOcrGet release client error", e2);
        }
        try {
            try {
                acquireUnstableContentProviderClient = this.f16767b.getContentResolver().acquireUnstableContentProviderClient(f16780l);
            } catch (Exception e3) {
                GaLog.c("ZteOcrModel", "callAiEngineOcrGet error", e3);
                if (0 != 0) {
                    contentProviderClient.release();
                }
            }
            if (acquireUnstableContentProviderClient == null) {
                GaLog.b("ZteOcrModel", "ContentProviderClient is null");
                a(false);
                if (acquireUnstableContentProviderClient != null) {
                    try {
                        acquireUnstableContentProviderClient.release();
                        return;
                    } catch (Exception e4) {
                        GaLog.c("ZteOcrModel", "callAiEngineOcrGet release client error", e4);
                        return;
                    }
                }
                return;
            }
            Bundle bundle = new Bundle();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("ALL");
            String jSONArray2 = jSONArray.toString();
            GaLog.e("ZteOcrModel", "jsonParam :" + jSONArray2);
            bundle.putString("key_word_param", jSONArray2);
            Bundle call = acquireUnstableContentProviderClient.call("ai_ocr_predict_get", "ai_ocr_predict_get_common", bundle);
            if (call != null) {
                str = call.getString("ai_ocr_predict_result", "");
                GaLog.e("ZteOcrModel", "callAiEngineOcrGet AiOcrPredictResult=" + str);
            } else {
                GaLog.b("ZteOcrModel", "callAiEngineOcrGet bundle is null");
            }
            acquireUnstableContentProviderClient.release();
            if (TextUtils.isEmpty(str)) {
                a(false);
            } else {
                this.f16771f.f16756e = str;
                a(true);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    contentProviderClient.release();
                } catch (Exception e5) {
                    GaLog.c("ZteOcrModel", "callAiEngineOcrGet release client error", e5);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(LowSugarPurposeData lowSugarPurposeData) {
        ContentProviderClient acquireUnstableContentProviderClient;
        h();
        this.f16771f = lowSugarPurposeData;
        long currentTimeMillis = System.currentTimeMillis();
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                try {
                    acquireUnstableContentProviderClient = this.f16767b.getContentResolver().acquireUnstableContentProviderClient(f16780l);
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            contentProviderClient.release();
                        } catch (Exception e2) {
                            GaLog.c("ZteOcrModel", "callAiEngineOcrRequest release client error", e2);
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                GaLog.c("ZteOcrModel", "callAiEngineOcrRequest error", e3);
                a(false);
                if (0 == 0) {
                    return;
                } else {
                    contentProviderClient.release();
                }
            }
            if (acquireUnstableContentProviderClient == null) {
                GaLog.b("ZteOcrModel", "ContentProviderClient is null");
                a(false);
                if (acquireUnstableContentProviderClient != null) {
                    try {
                        acquireUnstableContentProviderClient.release();
                        return;
                    } catch (Exception e4) {
                        GaLog.c("ZteOcrModel", "callAiEngineOcrRequest release client error", e4);
                        return;
                    }
                }
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putDouble("Precision_ratio", 0.6d);
            bundle.putParcelable("key_bitmap_param", this.f16771f.f16755d);
            acquireUnstableContentProviderClient.call("ai_ocr_predict_request", "ai_ocr_predict_request_common", bundle);
            GaLog.e("ZteOcrModel", "callAiEngineOcrRequest AI_OCR_PREDICT_REQUEST time = " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            acquireUnstableContentProviderClient.release();
        } catch (Exception e5) {
            GaLog.c("ZteOcrModel", "callAiEngineOcrRequest release client error", e5);
        }
    }

    private void q() {
        try {
            ContentResolver contentResolver = this.f16767b.getContentResolver();
            if (contentResolver == null) {
                GaLog.b("ZteOcrModel", "registerAIEngineOcrObserver ContentResolver is null");
            } else {
                contentResolver.registerContentObserver(f16780l, true, this.f16782k);
            }
        } catch (Exception e2) {
            GaLog.c("ZteOcrModel", "registerAIEngineOcrObserver error", e2);
        }
    }

    private void r() {
        try {
            this.f16767b.getContentResolver().unregisterContentObserver(this.f16782k);
        } catch (Exception e2) {
            GaLog.c("ZteOcrModel", "unregisterAiEngineObserver error", e2);
        }
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public void a(boolean z) {
        super.a(z);
        if (this.f16781j) {
            r();
            this.f16781j = false;
        }
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public String e() {
        String string;
        LowSugarPurposeData lowSugarPurposeData = this.f16771f;
        if (lowSugarPurposeData == null || TextUtils.isEmpty(lowSugarPurposeData.f16756e)) {
            return null;
        }
        GaLog.b("ZteOcrModel", "parseOcrData mCurrOcrPurposeData = " + this.f16771f);
        StringBuilder sb = new StringBuilder();
        GameBaseScene a2 = GameSceneFactory.a(this.f16767b, this.f16771f.f16754c);
        try {
            JSONArray jSONArray = new JSONArray(this.f16771f.f16756e);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.has("text") && (string = jSONObject.getString("text")) != null && !string.isEmpty()) {
                    if (a2 == null || !a2.d(string, this.f16771f.f16753b)) {
                        LowSugarOcrData lowSugarOcrData = new LowSugarOcrData();
                        lowSugarOcrData.f16746a = string;
                        if (jSONObject.has("boxPoint")) {
                            JSONArray jSONArray2 = jSONObject.getJSONArray("boxPoint");
                            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                                JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                                int i4 = jSONObject2.getInt("x");
                                int i5 = jSONObject2.getInt("y");
                                if (i3 == 0) {
                                    lowSugarOcrData.f16747b.f16748a = new Point(i4, i5);
                                } else if (i3 == 1) {
                                    lowSugarOcrData.f16747b.f16749b = new Point(i4, i5);
                                } else if (i3 == 2) {
                                    lowSugarOcrData.f16747b.f16751d = new Point(i4, i5);
                                } else if (i3 == 3) {
                                    lowSugarOcrData.f16747b.f16750c = new Point(i4, i5);
                                }
                            }
                        }
                        GaLog.b("ZteOcrModel", "parseData i = " + i2 + ", data = " + lowSugarOcrData);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(string);
                        sb2.append("\n");
                        sb.append(sb2.toString());
                    } else {
                        GaLog.b("ZteOcrModel", "parseData text is in filterOcrStr and text = " + string);
                    }
                }
            }
            return sb.toString().trim();
        } catch (Exception e2) {
            e2.printStackTrace();
            GaLog.b("ZteOcrModel", "parseData e = " + e2.toString());
            return null;
        }
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public void h() {
        super.h();
        q();
        this.f16781j = true;
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public void i(LowSugarPurposeData lowSugarPurposeData) {
        n(lowSugarPurposeData);
    }
}
