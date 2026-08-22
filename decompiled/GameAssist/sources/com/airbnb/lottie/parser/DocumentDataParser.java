package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class DocumentDataParser implements ValueParser<DocumentData> {

    /* renamed from: a, reason: collision with root package name */
    public static final DocumentDataParser f9807a = new DocumentDataParser();

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9808b = JsonReader.Options.a("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of", "ps", "sz");

    private DocumentDataParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public DocumentData a(JsonReader jsonReader, float f2) {
        DocumentData.Justification justification = DocumentData.Justification.CENTER;
        jsonReader.d();
        DocumentData.Justification justification2 = justification;
        String str = null;
        String str2 = null;
        PointF pointF = null;
        PointF pointF2 = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = true;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9808b)) {
                case 0:
                    str = jsonReader.A();
                    break;
                case 1:
                    str2 = jsonReader.A();
                    break;
                case 2:
                    f3 = (float) jsonReader.p();
                    break;
                case 3:
                    int s2 = jsonReader.s();
                    justification2 = DocumentData.Justification.CENTER;
                    if (s2 <= justification2.ordinal() && s2 >= 0) {
                        justification2 = DocumentData.Justification.values()[s2];
                        break;
                    }
                    break;
                case 4:
                    i2 = jsonReader.s();
                    break;
                case 5:
                    f4 = (float) jsonReader.p();
                    break;
                case 6:
                    f5 = (float) jsonReader.p();
                    break;
                case 7:
                    i3 = JsonUtils.d(jsonReader);
                    break;
                case 8:
                    i4 = JsonUtils.d(jsonReader);
                    break;
                case 9:
                    f6 = (float) jsonReader.p();
                    break;
                case 10:
                    z = jsonReader.k();
                    break;
                case 11:
                    jsonReader.c();
                    PointF pointF3 = new PointF(((float) jsonReader.p()) * f2, ((float) jsonReader.p()) * f2);
                    jsonReader.e();
                    pointF = pointF3;
                    break;
                case 12:
                    jsonReader.c();
                    PointF pointF4 = new PointF(((float) jsonReader.p()) * f2, ((float) jsonReader.p()) * f2);
                    jsonReader.e();
                    pointF2 = pointF4;
                    break;
                default:
                    jsonReader.F();
                    jsonReader.G();
                    break;
            }
        }
        jsonReader.h();
        return new DocumentData(str, str2, f3, justification2, i2, f4, f5, i3, i4, f6, z, pointF, pointF2);
    }
}
