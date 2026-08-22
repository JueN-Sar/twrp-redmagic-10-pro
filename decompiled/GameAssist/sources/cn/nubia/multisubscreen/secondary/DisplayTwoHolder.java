package cn.nubia.multisubscreen.secondary;

import android.view.ViewGroup;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.view.LineChartView;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DisplayTwoHolder {

    /* renamed from: a, reason: collision with root package name */
    private LineChartView f8034a;

    /* renamed from: b, reason: collision with root package name */
    private LineChartView f8035b;

    /* renamed from: c, reason: collision with root package name */
    private LineChartView f8036c;

    /* renamed from: d, reason: collision with root package name */
    private LineChartView f8037d;

    public DisplayTwoHolder(ViewGroup viewGroup) {
        b(viewGroup);
    }

    private int a(float f2) {
        if (f2 < 1048576.0f) {
            return 1024;
        }
        return f2 < 1.0737418E9f ? WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY : WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
    }

    private void b(ViewGroup viewGroup) {
        LineChartView lineChartView = (LineChartView) viewGroup.findViewById(R.id.fps_chart);
        this.f8034a = lineChartView;
        lineChartView.setXAxisMax(300);
        LineChartView lineChartView2 = (LineChartView) viewGroup.findViewById(R.id.net_chart);
        this.f8035b = lineChartView2;
        lineChartView2.setDynamicTuning(true);
        this.f8035b.setXAxisMax(300);
        LineChartView lineChartView3 = (LineChartView) viewGroup.findViewById(R.id.cps_chart);
        this.f8036c = lineChartView3;
        lineChartView3.setXAxisMax(300);
        LineChartView lineChartView4 = (LineChartView) viewGroup.findViewById(R.id.mpm_chart);
        this.f8037d = lineChartView4;
        lineChartView4.i(null, 5);
        this.f8037d.setXAxisMax(300);
    }

    private void g(int i2, float f2) {
        String str;
        float f3;
        if (i2 == 1024) {
            str = "KB/S";
            f3 = 3.0f;
        } else if (i2 == 1048576) {
            str = "MB/S";
            f3 = 2.0f;
        } else if (i2 == 1073741824) {
            str = "GB/S";
            f3 = 1.1f;
        } else {
            str = null;
            f3 = 1.0f;
        }
        if (str != null) {
            this.f8035b.j(str, i2);
            this.f8035b.setMaxYAxis((f2 / i2) * f3);
        }
    }

    public void c(List list) {
        this.f8036c.setData(list);
    }

    public void d(List list) {
        this.f8034a.setData(list);
    }

    public void e(List list) {
        this.f8037d.setData(list);
    }

    public void f(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        float f2 = -1.0f;
        int i2 = 1;
        while (it.hasNext()) {
            Float f3 = (Float) it.next();
            int a2 = a(f3.floatValue());
            if (i2 < a2) {
                i2 = a2;
            }
            if (f3.floatValue() - f2 > 0.1f) {
                f2 = f3.floatValue();
            }
        }
        g(i2, f2);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(Float.valueOf(((Float) it2.next()).floatValue() / i2));
        }
        this.f8035b.setData(arrayList);
    }
}
