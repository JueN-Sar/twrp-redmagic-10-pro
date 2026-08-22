package androidx.core.app;

import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class FrameMetricsAggregator {

    @RequiresApi
    private static class FrameMetricsApi24Impl extends FrameMetricsBaseImpl {

        /* renamed from: a, reason: collision with root package name */
        int f2644a;

        /* renamed from: b, reason: collision with root package name */
        SparseIntArray[] f2645b;

        /* renamed from: androidx.core.app.FrameMetricsAggregator$FrameMetricsApi24Impl$1, reason: invalid class name */
        class AnonymousClass1 implements Window.OnFrameMetricsAvailableListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ FrameMetricsApi24Impl f2646a;

            @Override // android.view.Window.OnFrameMetricsAvailableListener
            public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i2) {
                FrameMetricsApi24Impl frameMetricsApi24Impl = this.f2646a;
                if ((frameMetricsApi24Impl.f2644a & 1) != 0) {
                    frameMetricsApi24Impl.a(frameMetricsApi24Impl.f2645b[0], frameMetrics.getMetric(8));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl2 = this.f2646a;
                if ((frameMetricsApi24Impl2.f2644a & 2) != 0) {
                    frameMetricsApi24Impl2.a(frameMetricsApi24Impl2.f2645b[1], frameMetrics.getMetric(1));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl3 = this.f2646a;
                if ((frameMetricsApi24Impl3.f2644a & 4) != 0) {
                    frameMetricsApi24Impl3.a(frameMetricsApi24Impl3.f2645b[2], frameMetrics.getMetric(3));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl4 = this.f2646a;
                if ((frameMetricsApi24Impl4.f2644a & 8) != 0) {
                    frameMetricsApi24Impl4.a(frameMetricsApi24Impl4.f2645b[3], frameMetrics.getMetric(4));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl5 = this.f2646a;
                if ((frameMetricsApi24Impl5.f2644a & 16) != 0) {
                    frameMetricsApi24Impl5.a(frameMetricsApi24Impl5.f2645b[4], frameMetrics.getMetric(5));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl6 = this.f2646a;
                if ((frameMetricsApi24Impl6.f2644a & 64) != 0) {
                    frameMetricsApi24Impl6.a(frameMetricsApi24Impl6.f2645b[6], frameMetrics.getMetric(7));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl7 = this.f2646a;
                if ((frameMetricsApi24Impl7.f2644a & 32) != 0) {
                    frameMetricsApi24Impl7.a(frameMetricsApi24Impl7.f2645b[5], frameMetrics.getMetric(6));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl8 = this.f2646a;
                if ((frameMetricsApi24Impl8.f2644a & 128) != 0) {
                    frameMetricsApi24Impl8.a(frameMetricsApi24Impl8.f2645b[7], frameMetrics.getMetric(0));
                }
                FrameMetricsApi24Impl frameMetricsApi24Impl9 = this.f2646a;
                if ((frameMetricsApi24Impl9.f2644a & 256) != 0) {
                    frameMetricsApi24Impl9.a(frameMetricsApi24Impl9.f2645b[8], frameMetrics.getMetric(2));
                }
            }
        }

        void a(SparseIntArray sparseIntArray, long j2) {
            if (sparseIntArray != null) {
                int i2 = (int) ((500000 + j2) / 1000000);
                if (j2 >= 0) {
                    sparseIntArray.put(i2, sparseIntArray.get(i2) + 1);
                }
            }
        }
    }

    private static class FrameMetricsBaseImpl {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface MetricType {
    }
}
