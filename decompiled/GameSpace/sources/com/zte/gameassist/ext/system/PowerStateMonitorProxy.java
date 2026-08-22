package com.zte.gameassist.ext.system;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
        default void onDozeUpate(boolean z) {
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
        this.mValues.observe(true, new MutableData.Observer() { // from class: com.zte.gameassist.ext.system.PowerStateMonitorProxy$$ExternalSyntheticLambda4
            @Override // com.zte.gameassist.ext.common.MutableData.Observer
            public final void onChanged(Object obj) {
                PowerStateMonitorProxy.this.onPowerStateChanged((List) obj);
            }
        });
    }

    public static PowerStateMonitorProxy getInstance() {
        if (mMonitor == null) {
            synchronized (GAControllerProxy.class) {
                if (mMonitor == null) {
                    mMonitor = new PowerStateMonitorProxy();
                }
            }
        }
        return mMonitor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPowerStateChanged(List<String> list) {
        setIsDoze(list != null && list.contains(POWER_STATE_DOZE));
        setIsWakeup(list != null && list.contains(POWER_STATE_WAKEUP));
    }

    private void setIsDoze(boolean z) {
        if (this.mIsDoze != z) {
            this.mIsDoze = z;
            Log.d(TAG, "updateDoze doze=" + z);
            this.mPowerStateCallbacks.forEach(new Consumer() { // from class: com.zte.gameassist.ext.system.PowerStateMonitorProxy$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PowerStateMonitorProxy.this.m456xdbe40e3f((PowerStateMonitorProxy.PowerStateCallback) obj);
                }
            });
        }
    }

    private void setIsWakeup(boolean z) {
        if (this.mIsWakeup != z) {
            this.mIsWakeup = z;
            if (z) {
                Log.d(TAG, "---waking up--- ");
                this.mPowerStateCallbacks.forEach(new Consumer() { // from class: com.zte.gameassist.ext.system.PowerStateMonitorProxy$$ExternalSyntheticLambda2
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((PowerStateMonitorProxy.PowerStateCallback) obj).onWakingUp();
                    }
                });
            } else {
                Log.d(TAG, "---goToSleep--- ");
                this.mPowerStateCallbacks.forEach(new Consumer() { // from class: com.zte.gameassist.ext.system.PowerStateMonitorProxy$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((PowerStateMonitorProxy.PowerStateCallback) obj).onGotoSleep();
                    }
                });
            }
        }
    }

    public void addCallback(final PowerStateCallback powerStateCallback) {
        this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.ext.system.PowerStateMonitorProxy$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PowerStateMonitorProxy.this.m454xfc29557d(powerStateCallback);
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

    /* renamed from: lambda$addCallback$0$com-zte-gameassist-ext-system-PowerStateMonitorProxy, reason: not valid java name */
    /* synthetic */ void m454xfc29557d(PowerStateCallback powerStateCallback) {
        if (this.mPowerStateCallbacks.contains(powerStateCallback)) {
            return;
        }
        this.mPowerStateCallbacks.add(powerStateCallback);
        if (this.mIsWakeup) {
            powerStateCallback.onDozeUpate(this.mIsDoze);
            powerStateCallback.onWakingUp();
        } else {
            powerStateCallback.onGotoSleep();
            powerStateCallback.onDozeUpate(this.mIsDoze);
        }
    }

    /* renamed from: lambda$removeCallback$1$com-zte-gameassist-ext-system-PowerStateMonitorProxy, reason: not valid java name */
    /* synthetic */ void m455x35f482bf(PowerStateCallback powerStateCallback) {
        if (this.mPowerStateCallbacks.contains(powerStateCallback)) {
            this.mPowerStateCallbacks.remove(powerStateCallback);
        }
    }

    /* renamed from: lambda$setIsDoze$2$com-zte-gameassist-ext-system-PowerStateMonitorProxy, reason: not valid java name */
    /* synthetic */ void m456xdbe40e3f(PowerStateCallback powerStateCallback) {
        powerStateCallback.onDozeUpate(this.mIsDoze);
    }

    public void removeCallback(final PowerStateCallback powerStateCallback) {
        this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.ext.system.PowerStateMonitorProxy$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                PowerStateMonitorProxy.this.m455x35f482bf(powerStateCallback);
            }
        });
    }

    public String toString() {
        return "mIsDoze=" + this.mIsDoze + ", mIsWakeup=" + this.mIsWakeup + " values:" + this.mValues.getData();
    }
}
