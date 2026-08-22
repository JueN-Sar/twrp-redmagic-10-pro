package cn.nubia.gameassist.meditationmode.danmu;

import android.content.Context;
import android.os.Handler;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import com.zte.gameassist.config.ZteFeature;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* loaded from: classes.dex */
public class PreBarrageController {

    /* renamed from: f, reason: collision with root package name */
    private static final String f6620f = "PreBarrageController";

    /* renamed from: a, reason: collision with root package name */
    protected Context f6621a;

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f6623c;

    /* renamed from: e, reason: collision with root package name */
    private final Runnable f6625e = new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.PreBarrageController.1
        @Override // java.lang.Runnable
        public void run() {
            BarrageLog.b(PreBarrageController.f6620f, "run showPreBarrageTask, mTaskRunning:" + PreBarrageController.this.f6623c);
            if (PreBarrageController.this.f6623c) {
                BarrageManager.r().C(new DanmuNotificationBean("", PreBarrageController.this.g(), "", "", null, 1, ""));
                PreBarrageController.this.f6624d.postDelayed(this, 10000L);
            }
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private final List f6622b = f();

    /* renamed from: d, reason: collision with root package name */
    private final Handler f6624d = BarrageFactory.b();

    public PreBarrageController(Context context) {
        this.f6621a = context;
    }

    private List f() {
        ArrayList arrayList = new ArrayList(Arrays.asList(this.f6621a.getResources().getStringArray(R.array.pre_danmu_item)));
        if (!ZteFeatureWrapper.getBoolean(ZteFeature.ZTE_FEATURE_SUPPORT_WIDOWREPLY, false)) {
            arrayList.remove(3);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String g() {
        List list = this.f6622b;
        if (list == null || list.isEmpty()) {
            return "";
        }
        return (String) this.f6622b.get(new Random().nextInt(this.f6622b.size()));
    }

    public void e(PrintWriter printWriter) {
        printWriter.println("PreBarrageController:");
        printWriter.println("isTaskRunning:" + this.f6623c);
    }

    public boolean h() {
        return this.f6623c;
    }

    public void i() {
        this.f6624d.removeCallbacks(this.f6625e);
        this.f6624d.postDelayed(this.f6625e, 500L);
    }

    public void j() {
        if (this.f6623c) {
            return;
        }
        this.f6623c = true;
        i();
    }

    public void k() {
        this.f6624d.removeCallbacks(this.f6625e);
        this.f6623c = false;
    }
}
