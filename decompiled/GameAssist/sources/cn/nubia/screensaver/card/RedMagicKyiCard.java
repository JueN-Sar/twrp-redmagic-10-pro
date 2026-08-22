package cn.nubia.screensaver.card;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class RedMagicKyiCard extends BaseCard {
    private FrameLayout A;
    private FrameLayout B;
    private FrameLayout C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private TextView J;
    private int K;
    private int L;
    private View v;
    private View w;
    private View x;
    private View y;
    private FrameLayout z;

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = view.findViewById(R.id.view_favor_progress_mask);
        this.w = view.findViewById(R.id.view_dialogue_progress_mask);
        this.x = view.findViewById(R.id.view_interaction_progress_mask);
        this.y = view.findViewById(R.id.view_kyi);
        this.A = (FrameLayout) view.findViewById(R.id.fl_favor_container);
        this.B = (FrameLayout) view.findViewById(R.id.fl_dialogue_container);
        this.C = (FrameLayout) view.findViewById(R.id.fl_interaction_container);
        this.D = (TextView) view.findViewById(R.id.tv_favor_progress);
        this.E = (TextView) view.findViewById(R.id.tv_dialogue_progress);
        this.F = (TextView) view.findViewById(R.id.tv_interaction_progress);
        this.G = (TextView) view.findViewById(R.id.tv_favor_tag);
        this.H = (TextView) view.findViewById(R.id.tv_dialogue_tag);
        this.I = (TextView) view.findViewById(R.id.tv_interaction_tag);
        this.z = (FrameLayout) view.findViewById(R.id.view_kyi_heart);
        this.J = (TextView) view.findViewById(R.id.tv_heart_num);
        this.v.setClipBounds(new Rect(0, 0, 100, 126));
        this.w.setClipBounds(new Rect(0, 0, 150, 126));
        this.x.setClipBounds(new Rect(0, 0, 200, 126));
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return ZteFeature.isSupportDemi() ? this.f8994h.getString(R.string.demi_kyi_card_title) : this.f8994h.getString(R.string.red_magic_kyi_card_title);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_red_magic_kyi_left, R.layout.card_red_magic_kyi_right};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void e() {
        super.e();
        this.K = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.kyi_card_progress_width);
        this.L = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.kyi_card_progress_width);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        super.w(viewGroup, z);
        if (!z) {
            this.v.setClipBounds(new Rect(0, 0, (int) (this.K * 0.3f), this.L));
            this.w.setClipBounds(new Rect(0, 0, (int) (this.K * 0.4f), this.L));
            this.x.setClipBounds(new Rect(0, 0, (int) (this.K * 0.5f), this.L));
            return;
        }
        View view = this.v;
        int i2 = this.K;
        view.setClipBounds(new Rect((int) (i2 * 0.7f), 0, i2, this.L));
        View view2 = this.w;
        int i3 = this.K;
        view2.setClipBounds(new Rect((int) (i3 * 0.6f), 0, i3, this.L));
        View view3 = this.x;
        int i4 = this.K;
        view3.setClipBounds(new Rect((int) (i4 * 0.5f), 0, i4, this.L));
    }
}
