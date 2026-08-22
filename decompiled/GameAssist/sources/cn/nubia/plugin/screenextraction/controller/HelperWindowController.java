package cn.nubia.plugin.screenextraction.controller;

import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import com.zte.gameassist.common.InflaterHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Objects;

/* loaded from: classes.dex */
public class HelperWindowController extends BaseWindowController<ViewGroup> implements View.OnClickListener, ViewPager.OnPageChangeListener {

    /* renamed from: p, reason: collision with root package name */
    private ViewPager f8599p;

    /* renamed from: q, reason: collision with root package name */
    private View f8600q;

    /* renamed from: r, reason: collision with root package name */
    private TextView f8601r;

    /* renamed from: s, reason: collision with root package name */
    private View[] f8602s;
    private int t;

    private class Adapter extends PagerAdapter {

        /* renamed from: c, reason: collision with root package name */
        private final Holder[] f8603c;

        private final class Holder {

            /* renamed from: a, reason: collision with root package name */
            final int f8605a;

            /* renamed from: b, reason: collision with root package name */
            final int f8606b;

            /* renamed from: c, reason: collision with root package name */
            final View f8607c;

            private Holder(Adapter adapter, int i2, int i3) {
                this.f8605a = i2;
                this.f8606b = i3;
                View e2 = InflaterHelper.e(R.layout.screen_extraction_help_paper_horizontal_item_layout);
                this.f8607c = e2;
                ((TextView) e2.findViewById(R.id.screen_extraction_paper_content_describe)).setText(i3);
                View findViewById = e2.findViewById(R.id.screen_extraction_paper_content_pic);
                findViewById.setBackgroundResource(i2);
                if (HelperWindowController.this.f8600q == null) {
                    int i4 = GameAssistWindowManager.Q;
                    int i5 = GameAssistWindowManager.P;
                    if (i4 < i5) {
                        e2.setPadding(0, i5 / 4, 0, 0);
                        findViewById.setScaleX(0.75f);
                        findViewById.setScaleY(0.75f);
                    }
                }
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int e() {
            return this.f8603c.length;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object j(ViewGroup viewGroup, int i2) {
            viewGroup.addView(this.f8603c[i2].f8607c);
            return this.f8603c[i2].f8607c;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean k(View view, Object obj) {
            return view == obj;
        }

        private Adapter() {
            this.f8603c = new Holder[]{new Holder(R.drawable.screen_extraction_helper_tips_one, R.string.plugin_screen_extraction_helper_tips_one), new Holder(R.drawable.screen_extraction_helper_tips_two, R.string.plugin_screen_extraction_helper_tips_two)};
        }
    }

    public HelperWindowController(ScreenExtractionManager screenExtractionManager) {
        super(screenExtractionManager);
        this.f8602s = new View[2];
    }

    private void H() {
        ViewPager viewPager = this.f8599p;
        if (viewPager == null) {
            return;
        }
        int currentItem = viewPager.getCurrentItem();
        int i2 = 0;
        while (true) {
            View[] viewArr = this.f8602s;
            if (i2 >= viewArr.length) {
                return;
            }
            View view = viewArr[i2];
            if (view != null) {
                view.setBackgroundResource(currentItem == i2 ? R.drawable.screen_extraction_indicator_red_bg : R.drawable.screen_extraction_indicator_blue_bg);
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public ViewGroup j() {
        return (ViewGroup) InflaterHelper.e(R.layout.screen_extraction_help_layout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: F, reason: merged with bridge method [inline-methods] */
    public void x(ViewGroup viewGroup) {
        super.x(viewGroup);
        this.f8600q = viewGroup.findViewById(R.id.screen_extraction_table_parent);
        ViewPager viewPager = (ViewPager) viewGroup.findViewById(R.id.screen_extraction_view_pager);
        this.f8599p = viewPager;
        viewPager.c(this);
        this.f8599p.setAdapter(new Adapter());
        TextView textView = (TextView) viewGroup.findViewById(R.id.screen_extraction_done);
        this.f8601r = textView;
        textView.setOnClickListener(this);
        this.f8601r.setVisibility(8);
        this.f8602s[0] = viewGroup.findViewById(R.id.screen_extraction_indicator_0);
        this.f8602s[1] = viewGroup.findViewById(R.id.screen_extraction_indicator_1);
        H();
        if (this.f8600q == null) {
            int i2 = GameAssistWindowManager.Q;
            int i3 = GameAssistWindowManager.P;
            if (i2 < i3) {
                viewGroup.setPadding(0, 0, 0, i3 / 10);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public void y(ViewGroup viewGroup) {
        super.y(viewGroup);
        ViewPager viewPager = this.f8599p;
        if (viewPager != null) {
            viewPager.J(this);
            this.f8599p = null;
        }
        TextView textView = this.f8601r;
        if (textView != null) {
            textView.setOnClickListener(null);
            this.f8601r = null;
        }
        Handler handler = this.f8592j;
        ScreenExtractionManager screenExtractionManager = this.f8589c;
        Objects.requireNonNull(screenExtractionManager);
        handler.post(new l.a(screenExtractionManager));
    }

    @Override // cn.nubia.plugin.screenextraction.controller.IWindowController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "ScreenExtraction.Window");
        printWriter.println((str + "  ") + "isShowWindow=" + v());
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void d(int i2, float f2, int i3) {
        ViewPager viewPager;
        if (this.f8601r == null || (viewPager = this.f8599p) == null) {
            return;
        }
        this.f8601r.setVisibility(((((float) i2) + f2) > (((float) viewPager.getAdapter().e()) - 1.5f) ? 1 : ((((float) i2) + f2) == (((float) viewPager.getAdapter().e()) - 1.5f) ? 0 : -1)) > 0 ? 0 : 8);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void f(int i2) {
        if (this.t != i2 && i2 == 0) {
            H();
        }
        this.t = i2;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void g(int i2) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.screen_extraction_done) {
            b("click");
            Settings.Global.putInt(this.f8593k.getContentResolver(), "screen_extraction_helper_done", 1);
        }
    }
}
