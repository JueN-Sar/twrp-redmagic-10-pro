package cn.nubia.gameassist.performance.monitor;

import android.os.Handler;
import android.text.TextUtils;
import cn.nubia.componentcenter.api.performance.IGpuMonitor;
import cn.nubia.gameassist.performance.monitor.GpuMonitor;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.Constants;
import com.zte.gameassist.common.ThreadManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GpuMonitor implements IGpuMonitor, Runnable {

    /* renamed from: c, reason: collision with root package name */
    private List f7128c = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private Handler f7129h = new Handler(ThreadManager.c().b());

    /* renamed from: i, reason: collision with root package name */
    private Handler f7130i = new Handler(ThreadManager.c().e());

    /* renamed from: j, reason: collision with root package name */
    private float f7131j;

    /* renamed from: k, reason: collision with root package name */
    private float f7132k;

    /* renamed from: l, reason: collision with root package name */
    private float f7133l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f7134m;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x007c -> B:35:0x008b). Please report as a decompilation issue!!! */
    public static int f(String str, String str2, String str3, int i2) {
        String readLine;
        if (!new File(str).exists()) {
            return i2;
        }
        BufferedReader bufferedReader = null;
        ?? r1 = 0;
        BufferedReader bufferedReader2 = null;
        bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader3 = new BufferedReader(new FileReader(str));
                    do {
                        try {
                            readLine = bufferedReader3.readLine();
                            if (readLine != null && (r1 = readLine.contains(str2)) != 0) {
                                r1 = readLine.split(",");
                                for (String str4 : r1) {
                                    if (str4.contains(":")) {
                                        String[] split = str4.trim().split(":");
                                        if (split.length == 2 && split[0].trim().contains(str3)) {
                                            int intValue = Integer.valueOf(split[1].trim()).intValue();
                                            try {
                                                bufferedReader3.close();
                                            } catch (IOException e2) {
                                                e2.printStackTrace();
                                            }
                                            return intValue;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            bufferedReader2 = bufferedReader3;
                            e.printStackTrace();
                            bufferedReader = bufferedReader2;
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                                bufferedReader = bufferedReader2;
                            }
                            return i2;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader3;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } while (!TextUtils.isEmpty(readLine));
                    bufferedReader3.close();
                    bufferedReader = r1;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
            bufferedReader = bufferedReader;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        if (this.f7134m) {
            this.f7131j = -1.0f;
            this.f7132k = -1.0f;
            this.f7129h.removeCallbacks(this);
            this.f7129h.postDelayed(this, 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(final IGpuMonitor.GpuParameter gpuParameter) {
        this.f7128c.forEach(new Consumer() { // from class: f.h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((IGpuMonitor.Callback) obj).onGpuPerformanceChanged(IGpuMonitor.GpuParameter.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(IGpuMonitor.Callback callback) {
        if (!this.f7128c.contains(callback)) {
            this.f7128c.add(callback);
            if (this.f7134m) {
                callback.onGpuPerformanceChanged(getCurrentValue());
            }
        }
        if (this.f7128c.size() <= 0 || this.f7134m) {
            return;
        }
        this.f7134m = true;
        resetValue();
        this.f7129h.post(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(IGpuMonitor.Callback callback) {
        if (this.f7128c.contains(callback)) {
            this.f7128c.remove(callback);
        }
        if (this.f7128c.size() == 0 && this.f7134m) {
            this.f7134m = false;
            this.f7129h.removeCallbacks(this);
        }
    }

    @Override // cn.nubia.componentcenter.api.performance.IGpuMonitor
    public IGpuMonitor.GpuParameter getCurrentValue() {
        return new IGpuMonitor.GpuParameter(this.f7131j, this.f7132k, this.f7133l);
    }

    public float l() {
        return Constants.f16469i ? f("/proc/gpufreqv2/gpufreq_status", "OPP", "Freq", 268000) * 1000 : Float.parseFloat(Utils.l(Constants.f16470j));
    }

    public float m() {
        return Constants.f16469i ? f("/proc/gpufreqv2/gpu_signed_opp_table", "freq", "freq", 650000) * 1000 : Float.parseFloat(Utils.l(Constants.f16471k));
    }

    @Override // cn.nubia.componentcenter.api.performance.IGpuMonitor
    public synchronized void resetValue() {
        this.f7130i.post(new Runnable() { // from class: f.j
            @Override // java.lang.Runnable
            public final void run() {
                GpuMonitor.this.g();
            }
        });
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f7134m) {
            float l2 = l();
            float m2 = m();
            this.f7133l = m2;
            if (m2 <= 0.0f || l2 < 0.0f) {
                return;
            }
            if (l2 != this.f7132k) {
                this.f7132k = l2;
                float random = (l2 / m2) + ((float) (0.10000000149011612d - (Math.random() * 0.20000000298023224d)));
                this.f7131j = random;
                final IGpuMonitor.GpuParameter gpuParameter = new IGpuMonitor.GpuParameter(random, this.f7132k, this.f7133l);
                this.f7130i.post(new Runnable() { // from class: f.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        GpuMonitor.this.i(gpuParameter);
                    }
                });
            }
            if (this.f7134m) {
                this.f7129h.postDelayed(this, 1000L);
            }
        }
    }

    @Override // cn.nubia.componentcenter.api.performance.IGpuMonitor
    public synchronized void startMonitor(final IGpuMonitor.Callback callback) {
        this.f7130i.post(new Runnable() { // from class: f.g
            @Override // java.lang.Runnable
            public final void run() {
                GpuMonitor.this.j(callback);
            }
        });
    }

    @Override // cn.nubia.componentcenter.api.performance.IGpuMonitor
    public synchronized void stopMonitor(final IGpuMonitor.Callback callback) {
        this.f7130i.post(new Runnable() { // from class: f.i
            @Override // java.lang.Runnable
            public final void run() {
                GpuMonitor.this.k(callback);
            }
        });
    }

    public String toString() {
        return "CpuMonitor{mGpuCurF=" + this.f7132k + ", mMaxMain=" + this.f7133l + ", mIsMonitor=" + this.f7134m + '}';
    }
}
