package cn.nubia.multisubscreen.data;

/* loaded from: classes.dex */
public class SinkDisplayData {
    private int mBatteryLevel;
    private float mCurCpu;
    private int mCurFanSpeed;
    private float mCurGpu;
    private boolean mIsFanOn;
    private int mPerformanceMode;
    private float mMaxCpu = 1.0f;
    private float mMaxGpu = 1.0f;
    private int mMaxFanSpeed = 1;

    public int getBatteryLevel() {
        return this.mBatteryLevel;
    }

    public float getCurCpu() {
        return this.mCurCpu;
    }

    public int getCurFanSpeed() {
        return this.mCurFanSpeed;
    }

    public float getCurGpu() {
        return this.mCurGpu;
    }

    public float getMaxCpu() {
        return this.mMaxCpu;
    }

    public int getMaxFanSpeed() {
        return this.mMaxFanSpeed;
    }

    public float getMaxGpu() {
        return this.mMaxGpu;
    }

    public int getPerformanceMode() {
        return this.mPerformanceMode;
    }

    public boolean isFanOn() {
        return this.mIsFanOn;
    }

    public void setBatteryLevel(int i2) {
        this.mBatteryLevel = i2;
    }

    public void setCpu(float f2, float f3) {
        this.mCurCpu = f2;
        this.mMaxCpu = f3;
    }

    public void setFanOn(boolean z) {
        this.mIsFanOn = z;
    }

    public void setFanSpeed(int i2, int i3) {
        this.mCurFanSpeed = i2;
        this.mMaxFanSpeed = i3;
    }

    public void setGpu(float f2, float f3) {
        this.mCurGpu = f2;
        this.mMaxGpu = f3;
    }

    public void setPerformanceMode(int i2) {
        this.mPerformanceMode = i2;
    }
}
