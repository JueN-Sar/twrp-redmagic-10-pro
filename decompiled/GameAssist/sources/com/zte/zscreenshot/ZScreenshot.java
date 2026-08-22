package com.zte.zscreenshot;

import android.app.IActivityManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Singleton;
import com.zte.zscreenshot.aidl.IZScreenshotCallback;
import com.zte.zscreenshot.aidl.IZScreenshotController;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ZScreenshot {

    /* renamed from: e, reason: collision with root package name */
    private static final String f18208e = "ZScreenshot";

    /* renamed from: f, reason: collision with root package name */
    private static final Singleton f18209f = new Singleton<IZScreenshotController>() { // from class: com.zte.zscreenshot.ZScreenshot.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IZScreenshotController create() {
            IBinder service;
            IZScreenshotController iZScreenshotController;
            try {
                service = ServiceManager.getService("activity");
            } catch (Exception e2) {
                Log.e(ZScreenshot.f18208e, "Error creating IZScreenshotController", e2);
            }
            if (service == null) {
                throw new IllegalStateException("Service 'activity' not found");
            }
            IActivityManager asInterface = IActivityManager.Stub.asInterface(service);
            if (asInterface == null) {
                throw new IllegalStateException("IActivityManager not found");
            }
            for (Method method : asInterface.getClass().getMethods()) {
                if (method.getName().startsWith("getScreenshot") && (iZScreenshotController = (IZScreenshotController) method.invoke(asInterface, null)) != null) {
                    return iZScreenshotController;
                }
            }
            Log.w(ZScreenshot.f18208e, "Failed to find IZScreenshotController, returning default implementation");
            return new IZScreenshotController.Default() { // from class: com.zte.zscreenshot.ZScreenshot.1.1
                @Override // com.zte.zscreenshot.aidl.IZScreenshotController.Default, com.zte.zscreenshot.aidl.IZScreenshotController
                public void start(Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
                    Log.w(ZScreenshot.f18208e, "Failed to find IZScreenshotController");
                }
            };
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private OnBufferCallback f18212c;

    /* renamed from: a, reason: collision with root package name */
    private final Handler f18210a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    private IZScreenshotController f18211b = null;

    /* renamed from: d, reason: collision with root package name */
    private IZScreenshotCallback.Stub f18213d = new IZScreenshotCallback.Stub() { // from class: com.zte.zscreenshot.ZScreenshot.2
        @Override // com.zte.zscreenshot.aidl.IZScreenshotCallback
        public void callback(Bundle bundle) {
            Log.i(ZScreenshot.f18208e, "callback");
            HardwareBuffer hardwareBuffer = (HardwareBuffer) bundle.getParcelable("buffer", HardwareBuffer.class);
            ZScreenshot.this.f18212c.a(Bitmap.wrapHardwareBuffer(hardwareBuffer, null));
            if (!bundle.getBoolean("needclose") || hardwareBuffer == null || hardwareBuffer.isClosed()) {
                return;
            }
            hardwareBuffer.close();
        }
    };

    public interface OnBufferCallback {
        void a(Bitmap bitmap);
    }

    public ZScreenshot() {
        c();
    }

    private void c() {
        this.f18211b = (IZScreenshotController) f18209f.get();
    }

    public void d(Bundle bundle, OnBufferCallback onBufferCallback) {
        IZScreenshotController iZScreenshotController = this.f18211b;
        if (iZScreenshotController == null) {
            Log.e(f18208e, "start error, mController is null");
            return;
        }
        this.f18212c = onBufferCallback;
        try {
            iZScreenshotController.start(bundle, this.f18213d);
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(f18208e, "start e=" + e2);
        }
    }

    public void e(String str, long j2, float f2, Rect rect, OnBufferCallback onBufferCallback) {
        if (this.f18211b == null) {
            Log.e(f18208e, "start error, mController is null");
            return;
        }
        this.f18212c = onBufferCallback;
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        bundle.putLong("interval", j2);
        bundle.putFloat("scale", f2);
        bundle.putParcelable("rect", rect);
        try {
            this.f18211b.start(bundle, this.f18213d);
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(f18208e, "start e=" + e2);
        }
    }

    public void f() {
        IZScreenshotController iZScreenshotController = this.f18211b;
        if (iZScreenshotController == null) {
            Log.e(f18208e, "stop error, mController is null");
            return;
        }
        try {
            iZScreenshotController.stop(this.f18213d);
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(f18208e, "stop e=" + e2);
        }
    }
}
