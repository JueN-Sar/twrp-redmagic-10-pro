package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.gameassist.R;
import com.google.android.material.card.MaterialCardView;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class GameRatioGuideWindow implements ViewPager.OnPageChangeListener {
    private static final int t;

    /* renamed from: c, reason: collision with root package name */
    private Context f8353c;

    /* renamed from: h, reason: collision with root package name */
    private WindowManager f8354h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f8355i;

    /* renamed from: j, reason: collision with root package name */
    private String f8356j;

    /* renamed from: k, reason: collision with root package name */
    private GameRatioDataMgr f8357k;

    /* renamed from: l, reason: collision with root package name */
    private OnCloseListener f8358l;

    /* renamed from: m, reason: collision with root package name */
    private View f8359m;

    /* renamed from: n, reason: collision with root package name */
    private TextView f8360n;

    /* renamed from: o, reason: collision with root package name */
    private ViewPager f8361o;

    /* renamed from: p, reason: collision with root package name */
    private ViewGroup f8362p;

    /* renamed from: q, reason: collision with root package name */
    private ImageView[] f8363q;

    /* renamed from: r, reason: collision with root package name */
    private RotationMgr.Callback f8364r = new RotationMgr.Callback() { // from class: cn.nubia.plugin.gameratio.e
        @Override // com.zte.gameassist.common.RotationMgr.Callback
        /* renamed from: onRotationChanged */
        public final void y(int i2) {
            GameRatioGuideWindow.this.l(i2);
        }
    };

    /* renamed from: s, reason: collision with root package name */
    private GuidePagerAdapter f8365s;

    private class GuidePagerAdapter extends PagerAdapter {

        /* renamed from: c, reason: collision with root package name */
        private View[] f8366c = new View[GameRatioGuideWindow.t];

        /* renamed from: d, reason: collision with root package name */
        private ViewHolder[] f8367d = new ViewHolder[GameRatioGuideWindow.t];

        private class ViewHolder {

            /* renamed from: a, reason: collision with root package name */
            public LinearLayout f8369a;

            /* renamed from: b, reason: collision with root package name */
            public ImageView f8370b;

            /* renamed from: c, reason: collision with root package name */
            public ImageView f8371c;

            /* renamed from: d, reason: collision with root package name */
            public ViewGroup f8372d;

            /* renamed from: e, reason: collision with root package name */
            public TextView f8373e;

            /* renamed from: f, reason: collision with root package name */
            public TextView f8374f;

            private ViewHolder(GuidePagerAdapter guidePagerAdapter) {
            }
        }

        public GuidePagerAdapter() {
            for (int i2 = 0; i2 < GameRatioGuideWindow.t; i2++) {
                this.f8366c[i2] = InflaterHelper.e(R.layout.gameratio_guide_item);
            }
            this.f8367d[0] = v(this.f8366c[0], R.drawable.gameratio_src, R.drawable.gameratio_dest_one, R.string.gameratio_help_title_one, R.string.gameratio_help_summary);
            x();
            if (GameRatioMgr.f8397s) {
                this.f8367d[1] = v(this.f8366c[1], R.drawable.gameratio_src, R.drawable.gameratio_dest_two, R.string.gameratio_help_title_two, R.string.gameratio_help_summary);
                y();
            }
        }

        private ViewHolder v(View view, int i2, int i3, int i4, int i5) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.image);
            ImageView imageView = (ImageView) view.findViewById(R.id.src);
            imageView.setImageResource(i2);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.dest);
            imageView2.setImageResource(i3);
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.text);
            TextView textView = (TextView) view.findViewById(R.id.msg);
            textView.setText(i4);
            TextView textView2 = (TextView) view.findViewById(R.id.summary);
            textView2.setText(i5);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.f8369a = linearLayout;
            viewHolder.f8370b = imageView;
            viewHolder.f8371c = imageView2;
            viewHolder.f8372d = viewGroup;
            viewHolder.f8373e = textView;
            viewHolder.f8374f = textView2;
            return viewHolder;
        }

        private void x() {
            ViewHolder viewHolder = this.f8367d[0];
            viewHolder.f8369a.setOrientation(RotationMgr.k() ? 1 : 0);
            if (RotationMgr.k()) {
                viewHolder.f8369a.setGravity(1);
            } else {
                viewHolder.f8369a.setGravity(17);
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewHolder.f8369a.getLayoutParams();
            marginLayoutParams.topMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_top);
            viewHolder.f8369a.setLayoutParams(marginLayoutParams);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewHolder.f8370b.getLayoutParams();
            marginLayoutParams2.width = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_w);
            marginLayoutParams2.height = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_h);
            viewHolder.f8370b.setLayoutParams(marginLayoutParams2);
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) viewHolder.f8371c.getLayoutParams();
            if (RotationMgr.k()) {
                marginLayoutParams3.leftMargin = 0;
                marginLayoutParams3.topMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_margin);
            } else {
                marginLayoutParams3.leftMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_margin);
                marginLayoutParams3.topMargin = 0;
            }
            marginLayoutParams3.width = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_w);
            marginLayoutParams3.height = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_one_image_h);
            viewHolder.f8371c.setLayoutParams(marginLayoutParams3);
            ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) viewHolder.f8372d.getLayoutParams();
            marginLayoutParams4.topMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_msg_top);
            viewHolder.f8372d.setLayoutParams(marginLayoutParams4);
        }

        private void y() {
            if (GameRatioGuideWindow.t == 2) {
                ViewHolder viewHolder = this.f8367d[1];
                viewHolder.f8369a.setOrientation(0);
                viewHolder.f8369a.setGravity(17);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewHolder.f8369a.getLayoutParams();
                marginLayoutParams.topMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_two_image_top);
                viewHolder.f8369a.setLayoutParams(marginLayoutParams);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.f8370b.getLayoutParams();
                layoutParams.width = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_two_src_w);
                layoutParams.height = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_two_src_h);
                layoutParams.gravity = 16;
                viewHolder.f8370b.setLayoutParams(layoutParams);
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewHolder.f8371c.getLayoutParams();
                marginLayoutParams2.leftMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_two_image_margin);
                marginLayoutParams2.width = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_two_dest_w);
                marginLayoutParams2.height = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_two_dest_h);
                viewHolder.f8371c.setLayoutParams(marginLayoutParams2);
                ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) viewHolder.f8372d.getLayoutParams();
                marginLayoutParams3.topMargin = GameRatioGuideWindow.this.f8353c.getResources().getDimensionPixelOffset(R.dimen.gameratio_guide_msg_top);
                viewHolder.f8372d.setLayoutParams(marginLayoutParams3);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void b(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView(this.f8366c[i2]);
            super.b(viewGroup, i2, obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int e() {
            return this.f8366c.length;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object j(ViewGroup viewGroup, int i2) {
            viewGroup.addView(this.f8366c[i2]);
            return this.f8366c[i2];
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean k(View view, Object obj) {
            return view == obj;
        }

        public void w() {
            x();
            y();
        }
    }

    public interface OnCloseListener {
        void a(String str);
    }

    static {
        t = GameRatioMgr.f8397s ? 2 : 1;
    }

    public GameRatioGuideWindow(Context context, GameRatioDataMgr gameRatioDataMgr) {
        this.f8353c = context;
        this.f8354h = (WindowManager) context.getSystemService("window");
        this.f8357k = gameRatioDataMgr;
    }

    private WindowManager.LayoutParams j() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038);
        layoutParams.flags = 67110696;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.format = -2;
        layoutParams.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.setTitle("PluginGameRatioGuide");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(int i2) {
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(View view) {
        if (this.f8361o.getCurrentItem() < t - 1) {
            ViewPager viewPager = this.f8361o;
            viewPager.N(viewPager.getCurrentItem() + 1, true);
        } else {
            this.f8357k.z();
            h();
            this.f8358l.a(this.f8356j);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void d(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void f(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void g(int i2) {
        int i3 = 0;
        while (true) {
            ImageView[] imageViewArr = this.f8363q;
            if (i3 >= imageViewArr.length) {
                break;
            }
            imageViewArr[i3].setImageResource(i2 == i3 ? R.mipmap.guild_page_slide_light : R.mipmap.guild_page_slide_default);
            i3++;
        }
        this.f8360n.setText(i2 == t + (-1) ? R.string.gameratio_ok : R.string.gameratio_help_next);
    }

    public void h() {
        if (this.f8355i) {
            this.f8361o.J(this);
            RotationMgr.e(this.f8353c).p(this.f8364r);
            this.f8355i = false;
            this.f8354h.removeView(this.f8359m);
        }
    }

    public void i(PrintWriter printWriter) {
        if (this.f8355i) {
            printWriter.println("  Guide show");
        }
    }

    public boolean k() {
        return this.f8355i;
    }

    public void n() {
        if (this.f8355i) {
            this.f8365s.w();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f8362p.getLayoutParams();
            marginLayoutParams.topMargin = this.f8353c.getResources().getDimensionPixelSize(R.dimen.gameratio_guide_indicator_top);
            marginLayoutParams.bottomMargin = this.f8353c.getResources().getDimensionPixelSize(R.dimen.gameratio_guide_indicator_bottom);
            this.f8362p.setLayoutParams(marginLayoutParams);
        }
    }

    public void o(OnCloseListener onCloseListener) {
        this.f8358l = onCloseListener;
    }

    public void p(String str) {
        if (this.f8355i) {
            this.f8356j = str;
            return;
        }
        this.f8355i = true;
        this.f8356j = str;
        View f2 = InflaterHelper.f(R.layout.gameratio_guide, null);
        this.f8359m = f2;
        this.f8361o = (ViewPager) f2.findViewById(R.id.pager);
        GuidePagerAdapter guidePagerAdapter = new GuidePagerAdapter();
        this.f8365s = guidePagerAdapter;
        this.f8361o.setAdapter(guidePagerAdapter);
        this.f8361o.c(this);
        TextView textView = (TextView) this.f8359m.findViewById(R.id.ok);
        this.f8360n = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioGuideWindow.this.m(view);
            }
        });
        this.f8360n.setText(t == 1 ? R.string.gameratio_ok : R.string.gameratio_help_next);
        ViewGroup viewGroup = (ViewGroup) this.f8359m.findViewById(R.id.indicator);
        this.f8362p = viewGroup;
        if (!GameRatioMgr.f8397s) {
            viewGroup.setVisibility(4);
        }
        this.f8363q = new ImageView[this.f8362p.getChildCount()];
        for (int i2 = 0; i2 < this.f8362p.getChildCount(); i2++) {
            if (this.f8362p.getChildAt(i2) instanceof ImageView) {
                this.f8363q[i2] = (ImageView) this.f8362p.getChildAt(i2);
            }
        }
        RotationMgr.e(this.f8353c).c(this.f8364r);
        this.f8354h.addView(this.f8359m, j());
    }
}
