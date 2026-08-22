package com.zte.gameassist.ext.system;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import com.zte.gameassist.ext.common.GAControllerProxy;
import com.zte.gameassist.ext.common.MutableData;
import com.zte.gameassist.ext.system.PowerStateMonitorProxy;
import com.zte.gameassist.ext.utils.RemoteList;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class PowerStateMonitorProxy extends RemoteList {
    public static final String POWER_STATE_DOZE = "doze";
    public static final String POWER_STATE_MONITOR = "power_state_monitor";
    public static final String POWER_STATE_WAKEUP = "wakeup";
    public static final String TAG = "PowerStateMonitorRroxy";
    private static volatile PowerStateMonitorProxy mMonitor;
    private final Handler mHandler;
    private boolean mIsDoze;
    private boolean mIsWakeup;
    private final List<PowerStateCallback> mPowerStateCallbacks;

    public interface PowerStateCallback {
        default void b(boolean z) {
        }

        default void onGotoSleep() {
        }

        default void onWakingUp() {
        }
    }

    private PowerStateMonitorProxy() {
        super(POWER_STATE_MONITOR);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mPowerStateCallbacks = new ArrayList();
        monitorRemoteList();
        this.mValues.e(true, new MutableData.Observer() { // from class: p.e
            @Override // com.zte.gameassist.ext.common.MutableData.Observer
            public final void a(Object obj) {
                PowerStateMonitorProxy.this.onPowerStateChanged((List) obj);
            }
        });
    }

    public static PowerStateMonitorProxy getInstance() {
        if (mMonitor == null) {
            synchronized (GAControllerProxy.class) {
                try {
                    if (mMonitor == null) {
                        mMonitor = new PowerStateMonitorProxy();
                    }
                } finally {
                }
            }
        }
        return mMonitor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addCallback$0(PowerStateCallback powerStateCallback) {
        if (this.mPowerStateCallbacks.contains(powerStateCallback)) {
            return;
        }
        this.mPowerStateCallbacks.add(powerStateCallback);
        if (this.mIsWakeup) {
            powerStateCallback.b(this.mIsDoze);
            powerStateCallback.onWakingUp();
        } else {
            powerStateCallback.onGotoSleep();
            powerStateCallback.b(this.mIsDoze);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeCallback$1(PowerStateCallback powerStateCallback) {
        if (this.mPowerStateCallbacks.contains(powerStateCallback)) {
            this.mPowerStateCallbacks.remove(powerStateCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setIsDoze$2(PowerStateCallback powerStateCallback) {
        powerStateCallback.b(this.mIsDoze);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPowerStateChanged(List<String> list) {
        boolean z = false;
        setIsDoze(list != null && list.contains(POWER_STATE_DOZE));
        if (list != null && list.contains(POWER_STATE_WAKEUP)) {
            z = true;
        }
        setIsWakeup(z);
    }

    private void setIsDoze(boolean z) {
        if (this.mIsDoze != z) {
            this.mIsDoze = z;
            Log.d(TAG, "updateDoze doze=" + z);
            this.mPowerStateCallbacks.forEach(new Consumer() { // from class: p.b
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PowerStateMonitorProxy.this.lambda$setIsDoze$2((PowerStateMonitorProxy.PowerStateCallback) obj);
                }
            });
        }
    }

    private void setIsWakeup(boolean z) {
        if (this.mIsWakeup != z) {
            this.mIsWakeup = z;
            if (z) {
                Log.d(TAG, "---waking up--- ");
                this.mPowerStateCallbacks.forEach(new Consumer() { // from class: p.c
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((PowerStateMonitorProxy.PowerStateCallback) obj).onWakingUp();
                    }
                });
            } else {
                Log.d(TAG, "---goToSleep--- ");
                this.mPowerStateCallbacks.forEach(new Consumer() { // from class: p.d
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((PowerStateMonitorProxy.PowerStateCallback) obj).onGotoSleep();
                    }
                });
            }
        }
    }

    public void addCallback(final PowerStateCallback powerStateCallback) {
        this.mHandler.post(new Runnable() { // from class: p.a
            @Override // java.lang.Runnable
            public final void run() {
                PowerStateMonitorProxy.this.lambda$addCallback$0(powerStateCallback);
            }
        });
    }

    public boolean isDoze() {
        return this.mIsDoze && !isWakeup();
    }

    public boolean isWakeup() {
        return this.mIsWakeup;
    }

    public boolean isWakeuping() {
        return this.mIsDoze && isWakeup();
    }

    public void removeCallback(final PowerStateCallback powerStateCallback) {
        this.mHandler.post(new Runnable() { // from class: p.f
            @Override // java.lang.Runnable
            public final void run() {
                PowerStateMonitorProxy.this.lambda$removeCallback$1(powerStateCallback);
            }
        });
    }

    @NonNull
    public String toString() {
        return "mIsDoze=" + this.mIsDoze + ", mIsWakeup=" + this.mIsWakeup + " values:" + this.mValues.b();
    }
}
