package com.zte.gameassist.lowsugar.detect.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import com.zte.gameassist.common.RotationMgr;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class GameBaseScene {

    /* renamed from: a, reason: collision with root package name */
    private final String f16843a;

    /* renamed from: b, reason: collision with root package name */
    protected Context f16844b;

    /* renamed from: c, reason: collision with root package name */
    protected ArrayList f16845c;

    /* renamed from: d, reason: collision with root package name */
    public CropBitmapSize f16846d = new CropBitmapSize(0, 0, RotationMgr.g(), RotationMgr.f());

    public static class CropBitmapSize {

        /* renamed from: a, reason: collision with root package name */
        public int f16847a;

        /* renamed from: b, reason: collision with root package name */
        public int f16848b;

        /* renamed from: c, reason: collision with root package name */
        public int f16849c;

        /* renamed from: d, reason: collision with root package name */
        public int f16850d;

        public CropBitmapSize(int i2, int i3, int i4, int i5) {
            this.f16847a = i2;
            this.f16848b = i3;
            this.f16849c = i4;
            this.f16850d = i5;
        }

        public String toString() {
            return "CropBitmapSize{mStartXCrop='" + this.f16847a + " ,mStartYCrop='" + this.f16848b + " ,mCropWidth='" + this.f16849c + " ,mCropHeight='" + this.f16850d + '}';
        }
    }

    public GameBaseScene(Context context, String str) {
        this.f16844b = context;
        this.f16843a = str;
    }

    public abstract Bitmap a(Bitmap bitmap, int i2);

    public abstract Rect b(Bitmap bitmap, int i2);

    public abstract String c(int i2);

    public boolean d(String str, int i2) {
        ArrayList arrayList = this.f16845c;
        return (arrayList == null || arrayList.isEmpty() || TextUtils.isEmpty(str) || !this.f16845c.contains(str)) ? false : true;
    }

    public boolean e(String str, int i2) {
        return true;
    }
}
