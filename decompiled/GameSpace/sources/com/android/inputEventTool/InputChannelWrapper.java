package com.android.inputEventTool;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Looper;
import android.os.Process;
import android.view.InputMonitor;

/* loaded from: classes2.dex */
public class InputChannelWrapper {
    private static InputChannelWrapper sInstance;
    private ListenInputEventReceiver mInputEventReceiver;
    private InputMonitor mInputMonitor;

    private InputChannelWrapper(Context context, Looper looper) {
        this.mInputEventReceiver = null;
        this.mInputMonitor = null;
        this.mInputMonitor = getMonitorGestureInput(context);
        this.mInputEventReceiver = new ListenInputEventReceiver(this.mInputMonitor.getInputChannel(), looper);
    }

    public static synchronized InputChannelWrapper getInputChannelWrapper(Context context, Looper looper) {
        InputChannelWrapper inputChannelWrapper;
        synchronized (InputChannelWrapper.class) {
            if (sInstance == null) {
                sInstance = new InputChannelWrapper(context, looper);
            }
            InputChannelWrapper inputChannelWrapper2 = sInstance;
            if (inputChannelWrapper2.mInputMonitor == null) {
                inputChannelWrapper2.mInputMonitor = getMonitorGestureInput(context);
            }
            InputChannelWrapper inputChannelWrapper3 = sInstance;
            if (inputChannelWrapper3.mInputEventReceiver == null) {
                inputChannelWrapper3.mInputEventReceiver = new ListenInputEventReceiver(sInstance.mInputMonitor.getInputChannel(), looper);
            }
            inputChannelWrapper = sInstance;
        }
        return inputChannelWrapper;
    }

    static InputMonitor getMonitorGestureInput(Context context) {
        try {
            return ((InputManager) context.getSystemService("input")).monitorGestureInput(InputChannelWrapper.class.getName() + Process.myPid(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void dispose() {
        InputMonitor inputMonitor = this.mInputMonitor;
        if (inputMonitor != null) {
            inputMonitor.dispose();
        }
        this.mInputMonitor = null;
        ListenInputEventReceiver listenInputEventReceiver = this.mInputEventReceiver;
        if (listenInputEventReceiver != null) {
            listenInputEventReceiver.dispose();
        }
        this.mInputEventReceiver = null;
    }

    public synchronized void registerTouchListener(MonitorTouchInterface monitorTouchInterface) {
        ListenInputEventReceiver listenInputEventReceiver = this.mInputEventReceiver;
        if (listenInputEventReceiver != null) {
            listenInputEventReceiver.registerTouchListener(monitorTouchInterface);
        }
    }

    public synchronized void unregisterTouchListener(MonitorTouchInterface monitorTouchInterface) {
        ListenInputEventReceiver listenInputEventReceiver = this.mInputEventReceiver;
        if (listenInputEventReceiver != null) {
            listenInputEventReceiver.unregisterTouchListener(monitorTouchInterface);
        }
    }
}
