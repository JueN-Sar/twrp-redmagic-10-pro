package cn.nubia.componentcenter.api.performance;

import cn.nubia.componentcenter.api.IApi;
import java.util.Locale;

/* loaded from: classes.dex */
public interface ICpuMonitor extends IApi {

    public interface Callback {
        void onCpuPerformanceChanged(CpuParameter cpuParameter);
    }

    public static class CpuParameter {

        /* renamed from: a, reason: collision with root package name */
        public final float f5863a;

        /* renamed from: b, reason: collision with root package name */
        public final float f5864b;

        /* renamed from: c, reason: collision with root package name */
        public final float f5865c;

        /* renamed from: d, reason: collision with root package name */
        public final String f5866d = a();

        public CpuParameter(float f2, float f3, float f4) {
            this.f5863a = f2;
            this.f5864b = f3;
            this.f5865c = f4;
        }

        public String a() {
            return String.format(Locale.ENGLISH, "%.0f GHz", Float.valueOf(this.f5864b / 1000000.0f));
        }
    }

    CpuParameter getCurrentValue();

    void resetValue();

    void startMonitor(Callback callback);

    void stopMonitor(Callback callback);
}
