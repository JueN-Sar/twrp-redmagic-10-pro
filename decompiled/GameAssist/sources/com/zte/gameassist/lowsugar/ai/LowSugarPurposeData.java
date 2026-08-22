package com.zte.gameassist.lowsugar.ai;

import android.graphics.Bitmap;
import android.graphics.Rect;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.gameassist.lowsugar.common.DetectParam;

/* loaded from: classes2.dex */
public class LowSugarPurposeData {

    /* renamed from: a, reason: collision with root package name */
    public int f16752a;

    /* renamed from: b, reason: collision with root package name */
    public int f16753b;

    /* renamed from: c, reason: collision with root package name */
    public String f16754c;

    /* renamed from: d, reason: collision with root package name */
    public Bitmap f16755d;

    /* renamed from: e, reason: collision with root package name */
    public String f16756e;

    /* renamed from: f, reason: collision with root package name */
    public String f16757f;

    /* renamed from: g, reason: collision with root package name */
    public DetectParam f16758g;

    /* renamed from: h, reason: collision with root package name */
    public long f16759h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f16760i;

    /* renamed from: j, reason: collision with root package name */
    public Rect f16761j;

    /* renamed from: k, reason: collision with root package name */
    public String f16762k;

    public LowSugarPurposeData() {
        this.f16752a = 1;
    }

    public boolean a() {
        return this.f16760i;
    }

    public boolean b() {
        return this.f16752a == 2;
    }

    public boolean c() {
        return this.f16753b == 1 && ((Integer) this.f16758g.o("gift_icon", 0)).intValue() == 0 && ((Integer) this.f16758g.o("task_done_icon", 0)).intValue() > 0;
    }

    public String toString() {
        return "LowSugarPurposeData{effectMode='" + this.f16752a + NubiaTextClock.QUOTE + ", gameSceneType='" + this.f16753b + NubiaTextClock.QUOTE + ", ocrBitmap=" + this.f16754c + ", ocrString='" + this.f16756e + NubiaTextClock.QUOTE + ", aiString='" + this.f16757f + NubiaTextClock.QUOTE + ", detectParam='" + this.f16758g + NubiaTextClock.QUOTE + ", deadLineTime='" + this.f16759h + NubiaTextClock.QUOTE + ", mHasSamePurpose='" + this.f16760i + NubiaTextClock.QUOTE + ", mCropBitmapRect='" + this.f16761j + NubiaTextClock.QUOTE + ", mOcrBitmapDhash='" + this.f16762k + NubiaTextClock.QUOTE + '}';
    }

    public LowSugarPurposeData(int i2, int i3, String str, Bitmap bitmap, String str2, String str3, DetectParam detectParam, Rect rect, String str4) {
        this.f16752a = i2;
        this.f16753b = i3;
        this.f16754c = str;
        this.f16755d = bitmap;
        this.f16756e = str2;
        this.f16757f = str3;
        this.f16758g = detectParam;
        this.f16761j = rect;
        this.f16762k = str4;
    }
}
