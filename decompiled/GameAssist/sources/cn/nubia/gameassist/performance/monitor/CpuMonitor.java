package cn.nubia.gameassist.performance.monitor;

import android.os.Handler;
import cn.nubia.componentcenter.api.performance.ICpuMonitor;
import cn.nubia.gameassist.performance.monitor.CpuMonitor;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.ThreadManager;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class CpuMonitor implements ICpuMonitor, Runnable {

    /* renamed from: c, reason: collision with root package name */
    private List f7121c = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private Handler f7122h = new Handler(ThreadManager.c().b());

    /* renamed from: i, reason: collision with root package name */
    private Handler f7123i = new Handler(ThreadManager.c().e());

    /* renamed from: j, reason: collision with root package name */
    private float f7124j;

    /* renamed from: k, reason: collision with root package name */
    private float f7125k;

    /* renamed from: l, reason: collision with root package name */
    private float f7126l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f7127m;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        if (this.f7127m) {
            this.f7124j = -1.0f;
            this.f7125k = -1.0f;
            this.f7122h.removeCallbacks(this);
            this.f7122h.postDelayed(this, 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(final ICpuMonitor.CpuParameter cpuParameter) {
        this.f7121c.forEach(new Consumer() { // from class: f.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ICpuMonitor.Callback) obj).onCpuPerformanceChanged(ICpuMonitor.CpuParameter.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(ICpuMonitor.Callback callback) {
        if (!this.f7121c.contains(callback)) {
            this.f7121c.add(callback);
        }
        if (this.f7121c.size() <= 0 || this.f7127m) {
            return;
        }
        this.f7127m = true;
        this.f7122h.post(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(ICpuMonitor.Callback callback) {
        if (this.f7121c.contains(callback)) {
            this.f7121c.remove(callback);
        }
        if (this.f7121c.size() == 0 && this.f7127m) {
            this.f7127m = false;
            this.f7122h.removeCallbacks(this);
        }
    }

    @Override // cn.nubia.componentcenter.api.performance.ICpuMonitor
    public ICpuMonitor.CpuParameter getCurrentValue() {
        return new ICpuMonitor.CpuParameter(this.f7124j, this.f7125k, this.f7126l);
    }

    public float k() {
        return Math.max(Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq")), Math.max(Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq")), Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"))));
    }

    public float l() {
        return Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/cpuinfo_max_freq"));
    }

    @Override // cn.nubia.componentcenter.api.performance.ICpuMonitor
    public synchronized void resetValue() {
        this.f7123i.post(new Runnable() { // from class: f.e
            @Override // java.lang.Runnable
            public final void run() {
                CpuMonitor.this.f();
            }
        });
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f7127m) {
            this.f7126l = l();
            float k2 = k();
            float f2 = this.f7126l;
            if (f2 <= 0.0f || k2 < 0.0f) {
                return;
            }
            if (k2 != this.f7125k) {
                this.f7125k = k2;
                float random = (k2 / f2) + ((float) (0.10000000149011612d - (Math.random() * 0.20000000298023224d)));
                this.f7124j = random;
                final ICpuMonitor.CpuParameter cpuParameter = new ICpuMonitor.CpuParameter(random, this.f7125k, this.f7126l);
                this.f7123i.post(new Runnable() { // from class: f.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        CpuMonitor.this.h(cpuParameter);
                    }
                });
            }
            if (this.f7127m) {
                this.f7122h.postDelayed(this, 1000L);
            }
        }
    }

    @Override // cn.nubia.componentcenter.api.performance.ICpuMonitor
    public synchronized void startMonitor(final ICpuMonitor.Callback callback) {
        this.f7123i.post(new Runnable() { // from class: f.d
            @Override // java.lang.Runnable
            public final void run() {
                CpuMonitor.this.i(callback);
            }
        });
    }

    @Override // cn.nubia.componentcenter.api.performance.ICpuMonitor
    public synchronized void stopMonitor(final ICpuMonitor.Callback callback) {
        this.f7123i.post(new Runnable() { // from class: f.c
            @Override // java.lang.Runnable
            public final void run() {
                CpuMonitor.this.j(callback);
            }
        });
    }

    public String toString() {
        return "CpuMonitor{mCpuCurF=" + this.f7125k + ", mMaxMain=" + this.f7126l + ", mIsMonitor=" + this.f7127m + '}';
    }
}
