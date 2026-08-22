package cn.nubia.yolox;

import android.graphics.Rect;
import android.graphics.RectF;

/* loaded from: classes.dex */
public class YoloObject {

    /* renamed from: a, reason: collision with root package name */
    public RectF f9234a;

    /* renamed from: b, reason: collision with root package name */
    public float f9235b;

    public YoloObject(RectF rectF, float f2) {
        this.f9234a = rectF;
        this.f9235b = f2;
    }

    public Rect a() {
        RectF rectF = this.f9234a;
        return new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    public String toString() {
        return "YoloObject{rectF=" + this.f9234a + ", prob=" + this.f9235b + '}';
    }
}
