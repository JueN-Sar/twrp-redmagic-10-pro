package cn.nubia.multisubscreen.secondary;

import android.content.Context;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.secondary.SlideViewCtrl;
import cn.nubia.multisubscreen.utils.MultiSubScreenConstant;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import cn.nubia.multisubscreen.view.SlideView;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class SlideViewCtrl implements MultiSubScreenUtils.GameStatusCallback {

    /* renamed from: c, reason: collision with root package name */
    private Context f8061c;

    /* renamed from: i, reason: collision with root package name */
    private List f8063i;

    /* renamed from: l, reason: collision with root package name */
    private boolean f8066l;

    /* renamed from: n, reason: collision with root package name */
    private RecyclerView f8068n;

    /* renamed from: j, reason: collision with root package name */
    private final Map f8064j = Collections.synchronizedMap(new LinkedHashMap());

    /* renamed from: k, reason: collision with root package name */
    private List f8065k = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private SlideAdapter f8062h = new SlideAdapter(this);

    /* renamed from: m, reason: collision with root package name */
    private RecyclerView.ItemDecoration f8067m = new RecyclerView.ItemDecoration() { // from class: cn.nubia.multisubscreen.secondary.SlideViewCtrl.1
        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.top = SlideViewCtrl.this.f8061c.getResources().getDimensionPixelSize(R.dimen.sink_slide_view_margin_top);
        }
    };

    public class BrightnessSlide extends ProgressSlide {
        public BrightnessSlide(SlideViewCtrl slideViewCtrl, String str, int i2) {
            super(slideViewCtrl, str, i2);
        }

        @Override // cn.nubia.multisubscreen.secondary.SlideViewCtrl.Slider
        public String h(int i2) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(i2);
            jSONArray.put(b());
            jSONArray.put(1);
            jSONArray.put(j());
            return jSONArray.toString();
        }

        @Override // cn.nubia.multisubscreen.secondary.SlideViewCtrl.ProgressSlide, cn.nubia.multisubscreen.secondary.SlideViewCtrl.Slider
        public String k(int i2) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(i2);
            jSONArray.put(b());
            jSONArray.put(0);
            jSONArray.put(j());
            return jSONArray.toString();
        }
    }

    public class ProgressSlide extends Slider {
        public ProgressSlide(SlideViewCtrl slideViewCtrl, String str, int i2) {
            super(str, i2);
        }

        @Override // cn.nubia.multisubscreen.secondary.SlideViewCtrl.Slider
        public String k(int i2) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(i2);
            jSONArray.put(b());
            jSONArray.put(j());
            return jSONArray.toString();
        }

        @Override // cn.nubia.multisubscreen.secondary.SlideViewCtrl.Slider
        public boolean t(String str) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() != 3) {
                    return false;
                }
                long j2 = jSONArray.getLong(2);
                if (j2 > 0) {
                    long j3 = this.f8083k;
                    if (j3 > 0 && j2 < j3) {
                        GaLog.e("MultiSubScreen_SecData", "ignore earlier slide change for " + a() + " to " + jSONArray.getInt(0) + ", timeStamp=" + j2 + ", mTimeStamp" + this.f8083k);
                        return false;
                    }
                }
                q(jSONArray.getInt(0));
                n(jSONArray.getInt(1));
                return true;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return false;
            }
        }
    }

    public class SlideAdapter extends RecyclerView.Adapter<ViewHolder> {

        /* renamed from: c, reason: collision with root package name */
        private List f8070c;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: s, reason: collision with root package name */
            public SlideView f8072s;

            public ViewHolder(SlideView slideView) {
                super(slideView);
                this.f8072s = slideView;
            }
        }

        public SlideAdapter(SlideViewCtrl slideViewCtrl) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: L, reason: merged with bridge method [inline-methods] */
        public void A(ViewHolder viewHolder, int i2) {
            final Slider slider = (Slider) this.f8070c.get(i2);
            viewHolder.f8072s.setOnSeekBarChangeListener(null);
            viewHolder.f8072s.setSlide(slider);
            viewHolder.f8072s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) { // from class: cn.nubia.multisubscreen.secondary.SlideViewCtrl.SlideAdapter.1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i3, boolean z) {
                    SecDeviceDataMgr.f().k(slider.a(), slider.k(i3));
                    slider.r(i3);
                    slider.s(true);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    slider.s(false);
                    String h2 = slider.h(seekBar.getProgress());
                    if (TextUtils.isEmpty(h2)) {
                        return;
                    }
                    SecDeviceDataMgr.f().k(slider.a(), h2);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: M, reason: merged with bridge method [inline-methods] */
        public ViewHolder C(ViewGroup viewGroup, int i2) {
            return new ViewHolder((SlideView) InflaterHelper.g(R.layout.multi_sub_screen_slide_view, viewGroup, false));
        }

        public void N(List list) {
            this.f8070c = list;
            r();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int m() {
            return this.f8070c.size();
        }
    }

    public class Slider {

        /* renamed from: a, reason: collision with root package name */
        private String f8073a;

        /* renamed from: b, reason: collision with root package name */
        private int[] f8074b;

        /* renamed from: c, reason: collision with root package name */
        private int[] f8075c;

        /* renamed from: d, reason: collision with root package name */
        private int f8076d;

        /* renamed from: e, reason: collision with root package name */
        private int f8077e;

        /* renamed from: g, reason: collision with root package name */
        private int f8079g;

        /* renamed from: h, reason: collision with root package name */
        private int f8080h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f8081i;

        /* renamed from: f, reason: collision with root package name */
        private int f8078f = -1;

        /* renamed from: j, reason: collision with root package name */
        private boolean f8082j = true;

        /* renamed from: k, reason: collision with root package name */
        protected long f8083k = -1;

        public Slider(String str, int i2) {
            this.f8073a = str;
            this.f8076d = i2;
        }

        public String a() {
            return this.f8073a;
        }

        public int b() {
            return this.f8079g;
        }

        public int c() {
            return this.f8080h;
        }

        public int d(int i2) {
            int[] iArr = this.f8075c;
            return (iArr == null || i2 >= iArr.length) ? i2 : iArr[i2];
        }

        public int[] e() {
            return this.f8074b;
        }

        public int[] f() {
            return this.f8075c;
        }

        public int g() {
            return this.f8081i ? this.f8078f : this.f8077e;
        }

        public String h(int i2) {
            return null;
        }

        public int i() {
            return this.f8076d;
        }

        public long j() {
            long uptimeMillis = SystemClock.uptimeMillis();
            this.f8083k = uptimeMillis;
            return uptimeMillis;
        }

        public String k(int i2) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(d(i2));
            jSONArray.put(j());
            return jSONArray.toString();
        }

        public boolean l() {
            return this.f8082j && SlideViewCtrl.this.f8066l;
        }

        public void m(boolean z) {
            this.f8082j = z;
        }

        public void n(int i2) {
            this.f8079g = i2;
        }

        public void o(int[] iArr) {
            p(null, iArr);
        }

        public void p(int[] iArr, int[] iArr2) {
            this.f8075c = iArr;
            this.f8074b = iArr2;
            if (iArr2 == null || iArr2.length <= 1) {
                return;
            }
            n(iArr2.length - 1);
        }

        public void q(int i2) {
            this.f8077e = i2;
        }

        public void r(int i2) {
            this.f8078f = i2;
        }

        public void s(boolean z) {
            this.f8081i = z;
        }

        public boolean t(String str) {
            JSONArray jSONArray = new JSONArray(str);
            int i2 = 0;
            if (jSONArray.length() != 3) {
                return false;
            }
            long j2 = jSONArray.getLong(2);
            if (j2 > 0) {
                long j3 = this.f8083k;
                if (j3 > 0 && j2 < j3) {
                    GaLog.e("MultiSubScreen_SecData", "ignore earlier slide change for " + a() + "to " + jSONArray.getInt(0) + ", timeStamp=" + j2 + ", mTimeStamp" + this.f8083k);
                    return false;
                }
            }
            int i3 = jSONArray.getInt(0);
            m(jSONArray.getInt(1) == 1);
            if (this.f8075c != null) {
                while (true) {
                    int[] iArr = this.f8075c;
                    if (i2 >= iArr.length) {
                        break;
                    }
                    if (iArr[i2] == i3) {
                        q(i2);
                        break;
                    }
                    i2++;
                }
                return true;
            }
            q(i3);
            return true;
        }
    }

    public SlideViewCtrl(Context context) {
        this.f8061c = context;
    }

    private void f() {
        this.f8064j.clear();
        Slider slider = new Slider("performance_mode", R.string.sink_game_performance_mode);
        slider.p(MultiSubScreenConstant.f8163h, MultiSubScreenConstant.f8162g);
        this.f8064j.put(slider.a(), slider);
        Slider slider2 = new Slider("notification_mode", R.string.sink_meditation_notification_mode);
        slider2.o(new int[]{R.string.meditation_notification_common_title, R.string.meditation_notification_barrage_title, R.string.meditation_notification_shimmer_title, R.string.meditation_notification_hidden_title});
        this.f8064j.put(slider2.a(), slider2);
        Slider slider3 = new Slider("fan_mode", R.string.sink_fan_mode);
        slider3.p(new int[]{1, 0}, new int[]{R.string.sink_fan_mode_0, R.string.sink_fan_mode_1});
        this.f8064j.put(slider3.a(), slider3);
        Slider slider4 = new Slider("competition_light", R.string.ic_qs_competition_light);
        slider4.o(new int[]{R.string.sink_state_off, R.string.sink_state_on});
        this.f8064j.put(slider4.a(), slider4);
        BrightnessSlide brightnessSlide = new BrightnessSlide(this, "brightness", R.string.sink_brightness_adjustment);
        this.f8064j.put(brightnessSlide.a(), brightnessSlide);
        ProgressSlide progressSlide = new ProgressSlide(this, "volume", R.string.sink_volume_adjustment);
        this.f8064j.put(progressSlide.a(), progressSlide);
    }

    private void g() {
        this.f8065k.clear();
        List list = this.f8063i;
        if (list == null || list.isEmpty()) {
            this.f8064j.forEach(new BiConsumer() { // from class: cn.nubia.multisubscreen.secondary.b
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SlideViewCtrl.this.j((String) obj, (SlideViewCtrl.Slider) obj2);
                }
            });
        } else {
            this.f8064j.forEach(new BiConsumer() { // from class: cn.nubia.multisubscreen.secondary.a
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SlideViewCtrl.this.i((String) obj, (SlideViewCtrl.Slider) obj2);
                }
            });
            this.f8062h.N(this.f8065k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(String str, Slider slider) {
        if (this.f8063i.contains(str)) {
            this.f8065k.add(slider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(String str, Slider slider) {
        this.f8065k.add(slider);
    }

    public void h(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f8061c, 1, false));
        recyclerView.h(this.f8067m);
        recyclerView.setAdapter(this.f8062h);
        this.f8068n = recyclerView;
        f();
        g();
        this.f8062h.N(this.f8065k);
        this.f8066l = MultiSubScreenUtils.f8182l;
        MultiSubScreenUtils.B(this);
    }

    public void k() {
        MultiSubScreenUtils.M(this);
        RecyclerView recyclerView = this.f8068n;
        if (recyclerView != null) {
            recyclerView.Z0(this.f8067m);
            this.f8068n = null;
        }
    }

    public void l(String str, String str2) {
        GaLog.e("MultiSubScreen_SecData", "slide change " + str + ":" + str2);
        Slider slider = (Slider) this.f8064j.get(str);
        if (slider == null) {
            GaLog.e("MultiSubScreen_SecData", "unhandled slide data for " + str);
            return;
        }
        try {
            if (slider.t(str2)) {
                this.f8062h.r();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void m(boolean z) {
        Slider slider = (Slider) this.f8064j.get("fan_mode");
        if (slider == null) {
            return;
        }
        slider.m(z);
        this.f8062h.r();
    }

    public void n(List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        GaLog.e("MultiSubScreen_SecData", "slide keys " + TextUtils.join(",", list));
        this.f8063i = list;
        if (this.f8064j.isEmpty()) {
            return;
        }
        g();
    }

    public void o(boolean z) {
        Slider slider = (Slider) this.f8064j.get("performance_mode");
        if (slider == null || slider.l() == z) {
            return;
        }
        slider.m(z);
        this.f8062h.r();
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.GameStatusCallback
    public void p(boolean z) {
        o(!z);
    }

    public void q(String str, int[] iArr) {
        Slider slider = (Slider) this.f8064j.get(str);
        if (slider == null) {
            return;
        }
        int[] f2 = slider.f();
        int[] e2 = slider.e();
        if (f2 == null || e2 == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < f2.length; i2++) {
            int i3 = f2[i2];
            int length = iArr.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    break;
                }
                if (iArr[i4] == i3) {
                    arrayList.add(Integer.valueOf(i2));
                    break;
                }
                i4++;
            }
        }
        if (arrayList.size() == 0) {
            return;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        int[] iArr3 = new int[size];
        for (int i5 = 0; i5 < size; i5++) {
            iArr2[i5] = f2[((Integer) arrayList.get(i5)).intValue()];
            iArr3[i5] = e2[((Integer) arrayList.get(i5)).intValue()];
        }
        slider.p(iArr2, iArr3);
        this.f8062h.r();
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.GameStatusCallback
    public void s(boolean z) {
        this.f8066l = z;
        SlideAdapter slideAdapter = this.f8062h;
        if (slideAdapter != null) {
            slideAdapter.r();
        }
    }
}
