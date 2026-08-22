package cn.nubia.screensaver.card;

import android.view.View;
import android.view.ViewGroup;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.view.NubiaTextClock;
import cn.nubia.gameassist.view.YouSheTextClock;

/* loaded from: classes.dex */
public class TimeCard extends BaseCard {
    private NubiaTextClock v;
    private YouSheTextClock w;
    private NubiaTextClock x;

    public void A() {
        NubiaTextClock nubiaTextClock = this.v;
        if (nubiaTextClock == null || this.w == null || this.x == null) {
            return;
        }
        nubiaTextClock.p();
        this.w.p();
        this.x.p();
    }

    public void B() {
        NubiaTextClock nubiaTextClock = this.v;
        if (nubiaTextClock == null || this.w == null || this.x == null) {
            return;
        }
        nubiaTextClock.o();
        this.w.o();
        this.x.o();
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = (NubiaTextClock) view.findViewById(R.id.tv_week);
        this.w = (YouSheTextClock) view.findViewById(R.id.tv_hour_minute);
        this.x = (NubiaTextClock) view.findViewById(R.id.tv_month_day);
        String string = this.f8994h.getString(R.string.year_month_day);
        this.x.setFormat12Hour(string);
        this.x.setFormat24Hour(string);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_time};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void m() {
        super.m();
        B();
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void n(ViewGroup viewGroup, boolean z, boolean z2) {
        super.n(viewGroup, z, z2);
        a(viewGroup);
        B();
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void o() {
        super.o();
        z(this.f8996j);
        z(this.f8997k);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void p(ViewGroup viewGroup, boolean z, boolean z2) {
        super.p(viewGroup, z, z2);
        a(viewGroup);
        A();
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void q() {
        super.q();
        A();
    }

    public void z(ViewGroup viewGroup) {
        if (viewGroup != null) {
            a(viewGroup);
            B();
        }
    }
}
