package cn.nubia.yolox;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Log;
import androidx.annotation.NonNull;
import cn.nubia.gameassist.view.NubiaTextClock;
import java.util.Arrays;

/* loaded from: classes.dex */
public class YOLOXncnn {

    /* renamed from: a, reason: collision with root package name */
    private final String f9229a;

    /* renamed from: b, reason: collision with root package name */
    private final String f9230b;

    /* renamed from: c, reason: collision with root package name */
    private final String[] f9231c;
    private long mNativePtr;

    public class Obj {

        /* renamed from: h, reason: collision with root package name */
        public float f9233h;
        public String label;
        public float prob;
        public float w;
        public float x;
        public float y;

        public Obj() {
        }

        public RectF a() {
            float f2 = this.x;
            float f3 = this.y;
            return new RectF(f2, f3, this.w + f2, this.f9233h + f3);
        }

        public String toString() {
            return "Obj{x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.f9233h + ", label='" + this.label + NubiaTextClock.QUOTE + ", prob=" + this.prob + '}';
        }
    }

    static {
        if (Boolean.getBoolean("isUnitTest")) {
            return;
        }
        System.loadLibrary("yoloXncnn");
    }

    public YOLOXncnn(@NonNull String str, @NonNull String str2, @NonNull String[] strArr) {
        this.f9229a = str;
        this.f9230b = str2;
        this.f9231c = strArr;
    }

    private native Obj[] detect(Bitmap bitmap, boolean z, String[] strArr);

    private native boolean init(AssetManager assetManager, String str, String str2);

    private native void releaseXncnn();

    public Obj[] a(Bitmap bitmap, boolean z) {
        synchronized (this) {
            if (this.mNativePtr == 0) {
                return new Obj[0];
            }
            try {
                return detect(bitmap, z, this.f9231c);
            } finally {
                Log.v("YOLOXncnn", "detect " + hashCode());
            }
        }
    }

    public boolean b(AssetManager assetManager) {
        synchronized (this) {
            if (this.mNativePtr != 0) {
                return false;
            }
            try {
                return init(assetManager, this.f9229a, this.f9230b);
            } finally {
                Log.v("YOLOXncnn", "init " + this);
            }
        }
    }

    public boolean c() {
        return this.mNativePtr != 0;
    }

    public void d() {
        synchronized (this) {
            try {
                if (this.mNativePtr != 0) {
                    Log.v("YOLOXncnn", "release " + hashCode());
                    releaseXncnn();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void finalize() {
        super.finalize();
        d();
    }

    public String toString() {
        return "YOLOXncnn{mParamFile='" + this.f9229a + NubiaTextClock.QUOTE + ", mBinFile='" + this.f9230b + NubiaTextClock.QUOTE + ", mNativePtr=" + this.mNativePtr + ", mClassNames=" + Arrays.toString(this.f9231c) + "}  " + hashCode();
    }
}
