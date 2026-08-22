package com.zte.gameassist.lowsugar.ai.ocr;

import android.content.Context;
import android.text.TextUtils;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class OcrModelFactory {
    public static BaseOcrModel a(Context context, String str) {
        GaLog.a("OcrModelFactory", "getOcrModel " + str + ", sIsZteOcrAvailable = " + LowSugarUtils.G);
        return (LowSugarUtils.G && (TextUtils.isEmpty(str) || "zh".equals(str))) ? new ZteOcrModel(context, str) : LowSugarUtils.D.contains(str) ? new GameLabOcrModel(context, str) : new MLKitOcrModel(context, str);
    }
}
