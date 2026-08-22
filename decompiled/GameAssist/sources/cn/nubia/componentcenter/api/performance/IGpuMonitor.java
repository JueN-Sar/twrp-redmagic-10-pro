package cn.nubia.componentcenter.api.performance;

import cn.nubia.componentcenter.api.IApi;
import java.util.Locale;

/* loaded from: classes.dex */
public interface IGpuMonitor extends IApi {

    public interface Callback {
        void onGpuPerformanceChanged(GpuParameter gpuParameter);
    }

    public static class GpuParameter {

        /* renamed from: a, reason: collision with root package name */
        public final float f5867a;

        /* renamed from: b, reason: collision with root package name */
        public final float f5868b;

        /* renamed from: c, reason: collision with root package name */
        public final float f5869c;

        /* renamed from: d, reason: collision with root package name */
        public final String f5870d = a();

        public GpuParameter(float f2, float f3, float f4) {
            this.f5867a = f2;
            this.f5868b = f3;
            this.f5869c = f4;
        }

        public String a() {
            return String.format(Locale.ENGLISH, "%.0f MHz", Float.valueOf(this.f5868b / 1000000.0f));
        }
    }

    GpuParameter getCurrentValue();

    void resetValue();

    void startMonitor(Callback callback);

    void stopMonitor(Callback callback);
}
