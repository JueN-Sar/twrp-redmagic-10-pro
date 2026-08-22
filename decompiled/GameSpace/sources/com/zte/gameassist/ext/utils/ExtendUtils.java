package com.zte.gameassist.ext.utils;

import android.os.Bundle;
import android.view.View;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.ext.common.GAControllerProxy;
import com.zte.gameassist.ext.common.MutableData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class ExtendUtils {
    private static final List<ICallback> mCallbacks = new ArrayList();

    public static void fileExists(String str, final Consumer<Boolean> consumer) {
        ICallback.Stub stub = new ICallback.Stub() { // from class: com.zte.gameassist.ext.utils.ExtendUtils.1
            @Override // com.zte.gameassist.aidl.ICallback
            public void callback(String str2, Bundle bundle) {
                ExtendUtils.mCallbacks.remove(this);
                if (bundle.containsKey("bundle_key_value")) {
                    consumer.accept(Boolean.valueOf(bundle.getBoolean("bundle_key_value")));
                } else {
                    consumer.accept(false);
                }
            }
        };
        mCallbacks.add(stub);
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        invokeWithBundle(GAControllerProxy.INVAKE_EXISTS_FILE, bundle, stub);
    }

    public static boolean fileExists(String str) {
        final MutableData mutableData = new MutableData(-1);
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        fileExists(str, new Consumer() { // from class: com.zte.gameassist.ext.utils.ExtendUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ExtendUtils.lambda$fileExists$0(reentrantLock, mutableData, newCondition, (Boolean) obj);
            }
        });
        try {
            try {
                reentrantLock.lock();
                newCondition.await(5000L, TimeUnit.MILLISECONDS);
                reentrantLock.unlock();
                return ((Integer) mutableData.getData()).intValue() == 1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public static String fileRead(String str) {
        final MutableData mutableData = new MutableData("");
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        fileRead(str, new Consumer() { // from class: com.zte.gameassist.ext.utils.ExtendUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ExtendUtils.lambda$fileRead$1(reentrantLock, mutableData, newCondition, (String) obj);
            }
        });
        try {
            try {
                reentrantLock.lock();
                newCondition.await(5000L, TimeUnit.MILLISECONDS);
                reentrantLock.unlock();
                return (String) mutableData.getData();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public static void fileRead(String str, final Consumer<String> consumer) {
        ICallback.Stub stub = new ICallback.Stub() { // from class: com.zte.gameassist.ext.utils.ExtendUtils.2
            @Override // com.zte.gameassist.aidl.ICallback
            public void callback(String str2, Bundle bundle) {
                ExtendUtils.mCallbacks.remove(this);
                if (bundle.containsKey("bundle_key_value")) {
                    consumer.accept(bundle.getString("bundle_key_value"));
                } else {
                    consumer.accept("");
                }
            }
        };
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        invokeWithBundle(GAControllerProxy.INVAKE_READ_FILE, bundle, stub);
    }

    public static void fileWrite(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        bundle.putString("bundle_key_value", str2);
        invokeWithBundle(GAControllerProxy.INVAKE_WRITE_FILE, bundle);
    }

    public static void invokeWithBundle(String str, Bundle bundle) {
        invokeWithBundle(str, bundle, null);
    }

    public static void invokeWithBundle(String str, Bundle bundle, ICallback iCallback) {
        try {
            GAControllerProxy.getInstance().invake(str, bundle, iCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isVisible(View view) {
        if (view == null || view.getVisibility() != 0 || view.getParent() == null) {
            return false;
        }
        return view.getParent() instanceof View ? isVisible((View) view.getParent()) : view.getVisibility() == 0;
    }

    static /* synthetic */ void lambda$fileExists$0(ReentrantLock reentrantLock, MutableData mutableData, Condition condition, Boolean bool) {
        try {
            reentrantLock.lock();
            mutableData.setData(Integer.valueOf(bool.booleanValue() ? 1 : 0));
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    static /* synthetic */ void lambda$fileRead$1(ReentrantLock reentrantLock, MutableData mutableData, Condition condition, String str) {
        try {
            reentrantLock.lock();
            mutableData.setData(str);
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void restartPackage(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        invokeWithBundle(GAControllerProxy.INVAKE_RESTART_PACKAGE, bundle);
    }

    public static void setProp(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        bundle.putString("bundle_key_value", str2);
        invokeWithBundle(GAControllerProxy.INVAKE_SET_PROP, bundle);
    }

    public static void startHome(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(GAControllerProxy.BUNDLE_KEY_CODE, i);
        invokeWithBundle(GAControllerProxy.INVAKE_START_HOME, bundle);
    }

    public static void transactSurfaceflinger(int i, String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(GAControllerProxy.BUNDLE_KEY_CODE, i);
        bundle.putString("bundle_key_name", str);
        bundle.putString("bundle_key_value", str2);
        bundle.putInt("bundle_key_type", i2);
        invokeWithBundle(GAControllerProxy.INVAKE_SET_SURFACEFLINGER, bundle);
    }

    public static void updateAppBounds(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        invokeWithBundle(GAControllerProxy.INVAKE_UPDATE_APP_BOUNDS, bundle);
    }
}
