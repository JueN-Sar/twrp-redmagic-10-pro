package com.android.inputEventTool;

import android.os.Looper;
import android.util.Log;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ListenInputEventReceiver extends InputEventReceiver {
    ArrayList<MonitorTouchInterface> list;

    public ListenInputEventReceiver(InputChannel inputChannel, Looper looper) {
        super(inputChannel, looper);
        this.list = new ArrayList<>();
    }

    static boolean isInView(float f, float f2, MonitorTouch monitorTouch) {
        return f > monitorTouch.x && f < monitorTouch.x + monitorTouch.width && f2 > monitorTouch.y && f2 < monitorTouch.y + monitorTouch.high;
    }

    public void onInputEvent(InputEvent inputEvent) {
        int i;
        if (inputEvent instanceof MotionEvent) {
            MotionEvent motionEvent = (MotionEvent) inputEvent;
            int action = motionEvent.getAction();
            Iterator<MonitorTouchInterface> it = this.list.iterator();
            while (it.hasNext()) {
                MonitorTouchInterface next = it.next();
                if (next instanceof MonitorTouch) {
                    MonitorTouch monitorTouch = (MonitorTouch) next;
                    if (monitorTouch.supportMultiTouch) {
                        int actionMasked = motionEvent.getActionMasked();
                        if (action == 0 || actionMasked == 5) {
                            i = action != 0 ? action / 255 : 0;
                            if (isInView(motionEvent.getX(i), motionEvent.getY(i), monitorTouch)) {
                                monitorTouch.pointId = motionEvent.getPointerId(i);
                                monitorTouch.onTouch(motionEvent);
                            } else if (action == 0 && monitorTouch.pointId != -1) {
                                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                                monitorTouch.pointId = -1;
                                obtain.setAction(3);
                                monitorTouch.onTouch(obtain);
                            }
                        } else if (action != 2 || monitorTouch.pointId == -1) {
                            if (action == 1 || actionMasked == 6) {
                                if (monitorTouch.pointId != -1) {
                                    i = action != 1 ? action / 255 : 0;
                                    int findPointerIndex = motionEvent.findPointerIndex(monitorTouch.pointId);
                                    if (findPointerIndex == -1 || findPointerIndex == i) {
                                        monitorTouch.pointId = -1;
                                        monitorTouch.onTouch(motionEvent);
                                    } else {
                                        Log.d("ListenInputEventReceiver", "action = " + action + " actionMulti" + actionMasked + " motionevent = " + inputEvent);
                                    }
                                }
                            }
                        } else if (motionEvent.findPointerIndex(monitorTouch.pointId) != -1) {
                            monitorTouch.onTouch(motionEvent);
                        } else {
                            MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
                            monitorTouch.pointId = -1;
                            obtain2.setAction(1);
                            monitorTouch.onTouch(obtain2);
                        }
                    } else if (action == 0) {
                        if (isInView(motionEvent.getX(), motionEvent.getY(), monitorTouch)) {
                            monitorTouch.downBeginCallback = true;
                            monitorTouch.onTouch(motionEvent);
                        } else if (monitorTouch.downBeginCallback) {
                            monitorTouch.downBeginCallback = false;
                            MotionEvent obtain3 = MotionEvent.obtain(motionEvent);
                            obtain3.setAction(3);
                            monitorTouch.onTouch(obtain3);
                        }
                    } else if (monitorTouch.downBeginCallback && 5 == motionEvent.getActionMasked()) {
                        monitorTouch.downBeginCallback = false;
                        MotionEvent obtain4 = MotionEvent.obtain(motionEvent);
                        obtain4.setAction(3);
                        monitorTouch.onTouch(obtain4);
                    } else if (monitorTouch.downBeginCallback) {
                        if (action == 1 || action == 3) {
                            monitorTouch.downBeginCallback = false;
                        }
                        monitorTouch.onTouch(motionEvent);
                    }
                } else {
                    next.onTouch(motionEvent);
                }
            }
            finishInputEvent(inputEvent, true);
        }
    }

    public synchronized void registerTouchListener(MonitorTouchInterface monitorTouchInterface) {
        if (!this.list.contains(monitorTouchInterface)) {
            this.list.add(monitorTouchInterface);
        }
    }

    public synchronized void unregisterTouchListener(MonitorTouchInterface monitorTouchInterface) {
        if (this.list.contains(monitorTouchInterface)) {
            this.list.remove(monitorTouchInterface);
        }
    }
}
