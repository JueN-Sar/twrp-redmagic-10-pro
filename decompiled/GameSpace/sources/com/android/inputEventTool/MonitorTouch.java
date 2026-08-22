package com.android.inputEventTool;

import android.view.MotionEvent;

/* loaded from: classes2.dex */
public abstract class MonitorTouch implements MonitorTouchInterface {
    float high;
    float width;
    float x;
    float y;
    boolean supportMultiTouch = true;
    volatile boolean downBeginCallback = false;
    protected volatile int pointId = -1;

    public MonitorTouch() {
    }

    public MonitorTouch(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.high = f4;
    }

    public boolean getSupportMultiTouch() {
        return this.supportMultiTouch;
    }

    @Override // com.android.inputEventTool.MonitorTouchInterface
    public void onTouch(MotionEvent motionEvent) {
    }

    public void setMonitorPointXY(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.high = f4;
    }

    public void setSupportMultiTouch(boolean z) {
        this.supportMultiTouch = z;
    }
}
