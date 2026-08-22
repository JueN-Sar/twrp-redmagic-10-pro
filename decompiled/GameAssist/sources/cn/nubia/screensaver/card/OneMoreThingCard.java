package cn.nubia.screensaver.card;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.onemorething.OMTInfo;
import cn.nubia.gameassist.onemorething.OneMoreThingManager;

/* loaded from: classes.dex */
public class OneMoreThingCard extends BaseCard {
    private TextView v;
    private String w;

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = (TextView) view.findViewById(R.id.tv_omt_content);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.one_more_thing_card_title);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_one_more_thing};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        OMTInfo h2 = OneMoreThingManager.g().h();
        if (h2 == null) {
            OneMoreThingManager.g().o();
        } else {
            this.w = h2.a(this.f8994h);
        }
        s(h2 == null || this.w == null);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        super.w(viewGroup, z);
        this.v.setText(this.w);
    }
}
