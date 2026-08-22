package com.zte.gameassist.lowsugar.ai.ocr;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;
import com.google.mlkit.vision.text.devanagari.DevanagariTextRecognizerOptions;
import com.google.mlkit.vision.text.japanese.JapaneseTextRecognizerOptions;
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.zte.gameassist.lowsugar.ai.LowSugarPurposeData;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class MLKitOcrModel extends BaseOcrModel {

    /* renamed from: j, reason: collision with root package name */
    private TextRecognizer f16777j;

    public MLKitOcrModel(Context context, String str) {
        super(context, str);
        if ("zh".equals(str)) {
            this.f16777j = TextRecognition.a(new ChineseTextRecognizerOptions.Builder().a());
            return;
        }
        if ("ko".equals(str)) {
            this.f16777j = TextRecognition.a(new KoreanTextRecognizerOptions.Builder().a());
            return;
        }
        if ("ja".equals(str)) {
            this.f16777j = TextRecognition.a(new JapaneseTextRecognizerOptions.Builder().a());
        } else if (LowSugarUtils.A.contains(str)) {
            this.f16777j = TextRecognition.a(new DevanagariTextRecognizerOptions.Builder().a());
        } else {
            this.f16777j = TextRecognition.a(TextRecognizerOptions.f16143c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        if (this.f16777j != null) {
            GaLog.a("MLKitOcrModel", "release mRecognizer = " + this.f16777j);
            this.f16777j.close();
        }
        this.f16777j = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(LowSugarPurposeData lowSugarPurposeData) {
        h();
        this.f16771f = lowSugarPurposeData;
        this.f16777j.X(InputImage.a(lowSugarPurposeData.f16755d, 0)).f(new OnSuccessListener<Text>() { // from class: com.zte.gameassist.lowsugar.ai.ocr.MLKitOcrModel.2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(Text text) {
                MLKitOcrModel mLKitOcrModel;
                LowSugarPurposeData lowSugarPurposeData2;
                GaLog.b("MLKitOcrModel", "addOnSuccessListener result = " + text);
                String a2 = text.a();
                if (TextUtils.isEmpty(a2) || (lowSugarPurposeData2 = (mLKitOcrModel = MLKitOcrModel.this).f16771f) == null) {
                    MLKitOcrModel.this.a(false);
                } else {
                    lowSugarPurposeData2.f16756e = a2;
                    mLKitOcrModel.a(true);
                }
            }
        }).d(new OnFailureListener() { // from class: com.zte.gameassist.lowsugar.ai.ocr.MLKitOcrModel.1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void d(Exception exc) {
                GaLog.b("MLKitOcrModel", "onFailure e = " + exc.toString());
                MLKitOcrModel.this.a(false);
            }
        });
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public void f() {
        this.f16768c.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.ocr.b
            @Override // java.lang.Runnable
            public final void run() {
                MLKitOcrModel.this.l();
            }
        });
    }

    @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel
    public void i(final LowSugarPurposeData lowSugarPurposeData) {
        this.f16768c.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.ocr.a
            @Override // java.lang.Runnable
            public final void run() {
                MLKitOcrModel.this.m(lowSugarPurposeData);
            }
        });
    }
}
