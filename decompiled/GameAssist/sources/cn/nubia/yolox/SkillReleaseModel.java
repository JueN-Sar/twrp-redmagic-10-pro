package cn.nubia.yolox;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

/* loaded from: classes.dex */
public class SkillReleaseModel {

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f9226a = false;

    /* renamed from: b, reason: collision with root package name */
    private final Object f9227b = new Object();
    private volatile long nativePtr;

    static {
        if (Boolean.getBoolean("isUnitTest")) {
            return;
        }
        System.loadLibrary("yoloXncnn");
    }

    private native boolean CanRelease(Bitmap bitmap);

    private native boolean Init(AssetManager assetManager);

    private native void Release();

    public boolean a(AssetManager assetManager) {
        return Init(assetManager);
    }

    public boolean b(Bitmap bitmap) {
        synchronized (this.f9227b) {
            try {
                if (!this.f9226a && this.nativePtr != 0) {
                    return CanRelease(bitmap);
                }
                return false;
            } finally {
            }
        }
    }

    public void c() {
        synchronized (this.f9227b) {
            try {
                if (!this.f9226a) {
                    Release();
                    this.nativePtr = 0L;
                    this.f9226a = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void finalize() {
        super.finalize();
        Release();
    }
}
