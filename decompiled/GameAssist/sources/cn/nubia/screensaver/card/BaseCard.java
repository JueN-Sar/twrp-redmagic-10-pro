package cn.nubia.screensaver.card;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.view.YouSheTextView;
import cn.nubia.screensaver.CardContainerController;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.screensaver.view.CardView;

/* loaded from: classes.dex */
public abstract class BaseCard {

    /* renamed from: h, reason: collision with root package name */
    protected Context f8994h;

    /* renamed from: i, reason: collision with root package name */
    protected CardView f8995i;

    /* renamed from: j, reason: collision with root package name */
    protected ViewGroup f8996j;

    /* renamed from: k, reason: collision with root package name */
    protected ViewGroup f8997k;

    /* renamed from: l, reason: collision with root package name */
    protected ViewGroup f8998l;

    /* renamed from: m, reason: collision with root package name */
    private int f8999m;

    /* renamed from: n, reason: collision with root package name */
    protected boolean f9000n;

    /* renamed from: p, reason: collision with root package name */
    protected boolean f9002p;

    /* renamed from: q, reason: collision with root package name */
    protected int f9003q;

    /* renamed from: r, reason: collision with root package name */
    protected int f9004r;

    /* renamed from: s, reason: collision with root package name */
    protected int f9005s;
    protected int t;
    protected int u;

    /* renamed from: c, reason: collision with root package name */
    protected final String f8993c = getClass().getSimpleName();

    /* renamed from: o, reason: collision with root package name */
    protected boolean f9001o = true;

    private void u(ViewGroup viewGroup, boolean z) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.ll_card_content);
        if (viewGroup2 != null) {
            if (!this.f9000n) {
                viewGroup2.setVisibility(0);
                viewGroup.removeView(viewGroup.findViewWithTag("card_empty_view"));
                w(viewGroup2, z);
            } else {
                viewGroup2.setVisibility(8);
                if (viewGroup.findViewWithTag("card_empty_view") == null) {
                    viewGroup.addView(c(z));
                }
            }
        }
    }

    private void x(ViewGroup viewGroup, boolean z) {
        y(viewGroup, z);
    }

    protected abstract void a(View view);

    protected String b() {
        return null;
    }

    protected View c(boolean z) {
        FrameLayout frameLayout = new FrameLayout(this.f8994h);
        frameLayout.setTag("card_empty_view");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        frameLayout.setLayoutParams(layoutParams);
        ImageView imageView = new ImageView(this.f8994h);
        imageView.setImageDrawable(ContextCompat.e(this.f8994h, R.drawable.screen_saver_card_empty));
        YouSheTextView youSheTextView = new YouSheTextView(this.f8994h);
        youSheTextView.setGravity(80);
        youSheTextView.setTextColor(Color.parseColor("#66FFFFFF"));
        youSheTextView.setText(this.f8994h.getString(R.string.game_saver_card_empty_text));
        frameLayout.addView(imageView);
        frameLayout.addView(youSheTextView);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        int i2 = this.t;
        layoutParams2.width = i2;
        layoutParams2.height = i2;
        layoutParams2.topMargin = (-this.u) / 2;
        layoutParams2.gravity = 17;
        imageView.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) youSheTextView.getLayoutParams();
        layoutParams3.width = -2;
        layoutParams3.height = this.u;
        layoutParams3.topMargin = this.t / 2;
        layoutParams3.gravity = 17;
        youSheTextView.setLayoutParams(layoutParams3);
        if (z) {
            frameLayout.setPadding(frameLayout.getPaddingStart(), frameLayout.getPaddingTop(), this.f9003q, frameLayout.getPaddingBottom());
        } else {
            frameLayout.setPadding(this.f9003q, frameLayout.getPaddingTop(), frameLayout.getPaddingEnd(), frameLayout.getPaddingBottom());
        }
        return frameLayout;
    }

    protected abstract int[] d();

    protected void e() {
        this.f9003q = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.game_saver_card_padding_hor);
        this.f9004r = this.f8994h.getResources().getDimensionPixelSize(R.dimen.game_saver_card_title_padding_hor);
        this.f9005s = this.f8994h.getResources().getDimensionPixelSize(R.dimen.game_saver_card_title_pt);
        this.t = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.game_saver_card_empty_icon_width);
        this.u = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.game_saver_card_empty_text_height);
    }

    public abstract void f();

    public View g(CardView cardView) {
        this.f8995i = cardView;
        int[] o2 = CardContainerController.o();
        int i2 = o2[0];
        int i3 = this.f8999m;
        if (i2 == i3 || o2[1] == i3) {
            this.f9002p = true;
        }
        int[] d2 = d();
        if (d2 == null || d2.length == 0) {
            return null;
        }
        if (this.f8996j == null) {
            DefaultUtil.d(this.f8994h);
            ViewGroup viewGroup = (ViewGroup) View.inflate(this.f8994h, d2[0], null);
            this.f8996j = viewGroup;
            viewGroup.setContentDescription("left_" + this.f8999m);
        }
        if (this.f8997k == null) {
            DefaultUtil.d(this.f8994h);
            ViewGroup viewGroup2 = (ViewGroup) View.inflate(this.f8994h, d2.length == 2 ? d2[d2.length - 1] : d2[0], null);
            this.f8997k = viewGroup2;
            viewGroup2.setContentDescription("right_" + this.f8999m);
        }
        if (this.f8998l == null) {
            e();
        }
        ViewGroup viewGroup3 = this.f8995i.j() ? this.f8996j : this.f8997k;
        this.f8998l = viewGroup3;
        a(viewGroup3);
        if (this.f9001o) {
            f();
        }
        x(this.f8998l, this.f8995i.j());
        return this.f8998l;
    }

    public void h() {
        if (this.f9002p) {
            m();
        }
    }

    public void i(boolean z, boolean z2) {
        this.f9002p = false;
        if (this.f9000n) {
            return;
        }
        n(z ? this.f8996j : this.f8997k, z, z2);
    }

    public void init(Context context, int i2) {
        this.f8994h = context;
        this.f8999m = i2;
    }

    public void j() {
        o();
    }

    public void k(boolean z, boolean z2) {
        this.f9002p = true;
        if (this.f9000n) {
            return;
        }
        p(z ? this.f8996j : this.f8997k, z, z2);
    }

    public void l() {
        if (this.f9002p) {
            q();
        }
    }

    protected void m() {
    }

    protected void n(ViewGroup viewGroup, boolean z, boolean z2) {
    }

    protected void o() {
    }

    protected void p(ViewGroup viewGroup, boolean z, boolean z2) {
    }

    protected void q() {
    }

    protected void r(boolean z) {
        this.f9001o = z;
    }

    protected void s(boolean z) {
        this.f9000n = z;
    }

    protected void t() {
        ViewGroup viewGroup = this.f8996j;
        if (viewGroup != null) {
            a(viewGroup);
            y(this.f8996j, true);
        }
        ViewGroup viewGroup2 = this.f8997k;
        if (viewGroup2 != null) {
            a(viewGroup2);
            y(this.f8997k, false);
        }
    }

    protected void v(TextView textView, boolean z) {
        if (textView == null || TextUtils.isEmpty(b())) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        if (z) {
            layoutParams.setMargins(this.f9004r, this.f9005s, 0, 0);
            textView.setGravity(8388611);
            layoutParams.addRule(20);
        } else {
            layoutParams.setMargins(0, this.f9005s, this.f9004r, 0);
            textView.setGravity(8388613);
            layoutParams.addRule(21);
        }
        textView.setLayoutParams(layoutParams);
        textView.setText(b());
    }

    protected void w(ViewGroup viewGroup, boolean z) {
        if (z) {
            viewGroup.setPadding(viewGroup.getPaddingStart(), viewGroup.getPaddingTop(), this.f9003q, viewGroup.getPaddingBottom());
        } else {
            viewGroup.setPadding(this.f9003q, viewGroup.getPaddingTop(), viewGroup.getPaddingEnd(), viewGroup.getPaddingBottom());
        }
    }

    protected void y(ViewGroup viewGroup, boolean z) {
        v((TextView) viewGroup.findViewById(R.id.tv_card_title), z);
        u(viewGroup, z);
    }
}
