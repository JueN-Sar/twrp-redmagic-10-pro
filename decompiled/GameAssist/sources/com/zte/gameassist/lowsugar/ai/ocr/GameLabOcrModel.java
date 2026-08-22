package com.zte.gameassist.lowsugar.ai.ocr;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.zte.gameassist.lowsugar.ai.LowSugarPurposeData;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class GameLabOcrModel extends BaseOcrModel {

    /* renamed from: j, reason: collision with root package name */
    protected static final Uri f16776j = Uri.parse("content://cn.nubia.gamelab.provider.OcrProvider");

    public GameLabOcrModel(Context context, String str) {
        super(context, str);
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public void i(LowSugarPurposeData lowSugarPurposeData) {
        LowSugarPurposeData lowSugarPurposeData2;
        this.f16771f = lowSugarPurposeData;
        try {
            ContentResolver contentResolver = this.f16767b.getContentResolver();
            if (contentResolver != null) {
                Bundle bundle = new Bundle();
                GaLog.b("GameLabOcrModel", "startOcrRequest mCropBitmapRect = " + lowSugarPurposeData.f16761j);
                Rect rect = lowSugarPurposeData.f16761j;
                if (rect != null) {
                    bundle.putInt("x", rect.left);
                    bundle.putInt("y", lowSugarPurposeData.f16761j.top);
                    bundle.putInt("endX", lowSugarPurposeData.f16761j.right);
                    bundle.putInt("endY", lowSugarPurposeData.f16761j.bottom);
                }
                bundle.putString("package", lowSugarPurposeData.f16754c);
                Bundle call = contentResolver.call(f16776j, "recognizeText", (String) null, bundle);
                if (call != null) {
                    String string = call.getString("results");
                    if (TextUtils.isEmpty(string) || (lowSugarPurposeData2 = this.f16771f) == null) {
                        a(false);
                    } else {
                        lowSugarPurposeData2.f16756e = string;
                        a(true);
                    }
                }
            }
        } catch (Exception e2) {
            GaLog.c("GameLabOcrModel", "startOcrRequest error", e2);
        }
    }
}
