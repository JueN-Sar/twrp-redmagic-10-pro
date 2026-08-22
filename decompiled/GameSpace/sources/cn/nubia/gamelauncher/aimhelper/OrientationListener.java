package cn.nubia.gamelauncher.aimhelper;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.IRotationWatcher;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public abstract class OrientationListener implements DisplayManager.DisplayListener {
    private static final String TAG = "OrientationListener";
    private Context mContext;
    private DisplayManager mDisplayManager;
    private final Object mLock = new Object();
    IRotationWatcher mRotationWatcher = new IRotationWatcher.Stub() { // from class: cn.nubia.gamelauncher.aimhelper.OrientationListener.1
        @Override // android.view.IRotationWatcher
        public void onRotationChanged(int i) throws RemoteException {
            LogUtil.i("OrientationEventHandler", "onRotationChanged = " + OrientationListener.rotationValue2String(i));
            synchronized (OrientationListener.this.mLock) {
                OrientationListener.this.rotation = i;
            }
            OrientationListener.this.mUIHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.OrientationListener.1.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (OrientationListener.this.mLock) {
                        OrientationListener.this.onOrientationChange();
                    }
                }
            });
        }
    };
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    private WindowManager mWindowManager;
    int rotation;

    public OrientationListener(Context context) {
        this.mContext = context;
        this.mDisplayManager = (DisplayManager) context.getSystemService("display");
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String rotationValue2String(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "UNKNOW" : "ROTATION_270" : "ROTATION_180" : "ROTATION_90" : "ROTATION_0";
    }

    protected boolean isLandscape() {
        int i = this.rotation;
        return i == 1 || i == 3;
    }

    protected void listenOrientation() {
        watchRotationReflect();
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
        int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
        LogUtil.i(TAG, "onDisplayAdded newRotation=" + rotation);
        if (rotation != this.rotation) {
            this.rotation = rotation;
            onOrientationChange();
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
        LogUtil.i(TAG, "onDisplayChanged newRotation=" + rotationValue2String(rotation));
        if (rotation != this.rotation) {
            this.rotation = rotation;
            onOrientationChange();
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
        int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
        LogUtil.i(TAG, "onDisplayRemoved newRotation=" + rotation);
        if (rotation != this.rotation) {
            this.rotation = rotation;
            onOrientationChange();
        }
    }

    abstract void onOrientationChange();

    public void removeRotationWatcherReflect() {
        try {
            Object invoke = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "window"));
            invoke.getClass().getMethod("removeRotationWatcher", IRotationWatcher.class).invoke(invoke, this.mRotationWatcher);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        }
    }

    protected void unListenOrientation() {
        removeRotationWatcherReflect();
    }

    public void watchRotationReflect() {
        try {
            Object invoke = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, "window"));
            this.rotation = ((Integer) invoke.getClass().getMethod("watchRotation", IRotationWatcher.class, Integer.TYPE).invoke(invoke, this.mRotationWatcher, 0)).intValue();
            LogUtil.i(this, "watchRotationReflect ok, rotation = " + this.rotation);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        }
    }
}
