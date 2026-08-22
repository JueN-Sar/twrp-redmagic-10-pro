package cn.nubia.multisubscreen.secondary;

import android.view.ViewGroup;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.utils.MultiSubScreenConstant;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import cn.nubia.multisubscreen.view.CpuGpuView;
import cn.nubia.multisubscreen.view.SemicircleProgressView;
import cn.nubia.multisubscreen.view.StripProgressView;
import java.util.List;

/* loaded from: classes.dex */
public class DisplayOneHolder {

    /* renamed from: a, reason: collision with root package name */
    private SemicircleProgressView f8023a;

    /* renamed from: b, reason: collision with root package name */
    private TextView f8024b;

    /* renamed from: c, reason: collision with root package name */
    private TextView f8025c;

    /* renamed from: d, reason: collision with root package name */
    private CpuGpuView f8026d;

    /* renamed from: e, reason: collision with root package name */
    private CpuGpuView f8027e;

    /* renamed from: f, reason: collision with root package name */
    private StripProgressView f8028f;

    /* renamed from: g, reason: collision with root package name */
    private TextView f8029g;

    /* renamed from: h, reason: collision with root package name */
    private TextView f8030h;

    public DisplayOneHolder(ViewGroup viewGroup) {
        this.f8023a = (SemicircleProgressView) viewGroup.findViewById(R.id.fan_progress);
        this.f8024b = (TextView) viewGroup.findViewById(R.id.fan_text);
        this.f8025c = (TextView) viewGroup.findViewById(R.id.fan_value_text);
        this.f8026d = (CpuGpuView) viewGroup.findViewById(R.id.cpu);
        this.f8027e = (CpuGpuView) viewGroup.findViewById(R.id.gpu);
        this.f8028f = (StripProgressView) viewGroup.findViewById(R.id.battery_progress);
        this.f8029g = (TextView) viewGroup.findViewById(R.id.battery_level);
        this.f8030h = (TextView) viewGroup.findViewById(R.id.performance_mode);
    }

    public void a(int i2) {
        if (i2 == -1 || MultiSubScreenUtils.f8183m) {
            this.f8028f.c(0.0f, 100.0f);
            this.f8029g.setText("--");
            return;
        }
        this.f8028f.c(i2, 100.0f);
        this.f8029g.setText(i2 + "%");
    }

    public void b(float f2, float f3) {
        c(f2, f3, false);
    }

    public void c(float f2, float f3, boolean z) {
        if (MultiSubScreenUtils.f8183m) {
            this.f8026d.j(-1.0f, f3, z);
        } else {
            this.f8026d.j(f2, f3, z);
        }
    }

    public void d(int i2, int i3) {
        e(i2, i3, false);
    }

    public void e(int i2, int i3, boolean z) {
        if (MultiSubScreenUtils.f8183m) {
            this.f8023a.h(-1, i3, z);
            this.f8025c.setText("0rpm");
            return;
        }
        this.f8023a.h(i2, i3, z);
        this.f8025c.setText(i2 + "rpm");
    }

    public void f(float f2, float f3) {
        g(f2, f3, false);
    }

    public void g(float f2, float f3, boolean z) {
        if (MultiSubScreenUtils.f8183m) {
            this.f8027e.j(-1.0f, f3, z);
        } else {
            this.f8027e.j(f2, f3, z);
        }
    }

    public void h(List list) {
        if (list == null || list.contains("fan_speed")) {
            return;
        }
        this.f8023a.setVisibility(8);
        this.f8024b.setVisibility(8);
        this.f8025c.setVisibility(8);
    }

    public void i(int i2) {
        if (i2 == -1 || MultiSubScreenUtils.f8183m) {
            this.f8030h.setText("--");
            return;
        }
        int i3 = 0;
        while (true) {
            int[] iArr = MultiSubScreenConstant.f8163h;
            if (i3 >= iArr.length) {
                i3 = -1;
                break;
            } else if (i2 == iArr[i3]) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            this.f8030h.setText(MultiSubScreenConstant.f8162g[i3]);
        }
    }
}
