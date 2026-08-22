package cn.nubia.screensaver;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.card.BaseCard;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.screensaver.view.CardParentView;
import cn.nubia.screensaver.view.CardView;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class CardContainerController implements GSPowerController.PowerCallback {
    private static int v = 0;
    private static int w = 1;

    /* renamed from: c, reason: collision with root package name */
    private final CardParentView f8939c;

    /* renamed from: h, reason: collision with root package name */
    private final Context f8940h;

    /* renamed from: k, reason: collision with root package name */
    private CardView f8943k;

    /* renamed from: l, reason: collision with root package name */
    private CardView f8944l;

    /* renamed from: m, reason: collision with root package name */
    private int f8945m;

    /* renamed from: n, reason: collision with root package name */
    private int f8946n;

    /* renamed from: o, reason: collision with root package name */
    private int f8947o;

    /* renamed from: p, reason: collision with root package name */
    private int f8948p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f8949q;

    /* renamed from: r, reason: collision with root package name */
    private int f8950r;

    /* renamed from: s, reason: collision with root package name */
    private final GSPowerController f8951s;
    private final GSWindowController t;

    /* renamed from: i, reason: collision with root package name */
    private final List f8941i = new ArrayList();
    private final Runnable u = new Runnable() { // from class: cn.nubia.screensaver.CardContainerController.1
        @Override // java.lang.Runnable
        public void run() {
            if (CardContainerController.this.f8943k == null || CardContainerController.this.f8944l == null || CardContainerController.this.f8941i.isEmpty()) {
                GaLog.a("CardContainerController", "card is null");
                return;
            }
            if (CardContainerController.this.f8943k.e() && CardContainerController.this.f8944l.e()) {
                if (CardContainerController.this.f8949q) {
                    CardContainerController.this.f8944l.q(0.0f, -CardContainerController.this.f8944l.getHeight(), true);
                } else {
                    CardContainerController.this.f8943k.q(0.0f, -CardContainerController.this.f8943k.getHeight(), true);
                }
                CardContainerController.this.f8949q = !r0.f8949q;
                CardContainerController.this.f8950r++;
            }
            GaLog.a("CardContainerController", "auto flip card " + CardContainerController.this.f8950r + " last " + CardContainerController.this.f8949q);
            int nextInt = new Random().nextInt(3) + 3;
            CardContainerController.this.f8942j.removeCallbacks(this);
            CardContainerController.this.f8942j.postDelayed(this, CardContainerController.this.f8950r > 3 ? nextInt * 60000 : 60000L);
        }
    };

    /* renamed from: j, reason: collision with root package name */
    private final Handler f8942j = GameScreensaverManager.L().C();

    public CardContainerController(CardParentView cardParentView) {
        this.f8939c = cardParentView;
        this.f8940h = cardParentView.getContext();
        this.f8943k = (CardView) cardParentView.findViewById(R.id.left_card);
        this.f8944l = (CardView) cardParentView.findViewById(R.id.right_card);
        GSPowerController gSPowerController = (GSPowerController) GameScreensaverManager.L().I(GSPowerController.class);
        this.f8951s = gSPowerController;
        this.t = (GSWindowController) GameScreensaverManager.L().I(GSWindowController.class);
        gSPowerController.t(this);
        s();
    }

    public static int[] o() {
        return new int[]{v, w};
    }

    private int p(int i2, int i3, int i4) {
        if (i2 <= i4) {
            return i2;
        }
        if (i3 == 0) {
            return i3 + 1;
        }
        return 0;
    }

    private int q(int i2, int i3, int i4) {
        return i2 < 0 ? i3 == i4 ? i3 - 1 : i4 : i2;
    }

    private void s() {
        GaLog.a("CardContainerController", "init card list");
        ArrayList a2 = DefaultUtil.a(this.f8940h.getString(R.string.lock_card_list));
        for (int i2 = 0; i2 < a2.size(); i2++) {
            try {
                BaseCard baseCard = (BaseCard) Class.forName("cn.nubia.screensaver.card." + ((String) a2.get(i2)) + "Card").newInstance();
                baseCard.init(this.f8940h, i2);
                this.f8941i.add(baseCard);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        y();
        this.f8943k.c((BaseCard) this.f8941i.get(this.f8945m));
        this.f8943k.c((BaseCard) this.f8941i.get(v));
        this.f8943k.c((BaseCard) this.f8941i.get(this.f8946n));
        this.f8944l.c((BaseCard) this.f8941i.get(this.f8947o));
        this.f8944l.c((BaseCard) this.f8941i.get(w));
        this.f8944l.c((BaseCard) this.f8941i.get(this.f8948p));
        w();
    }

    private int t(int i2, int i3) {
        int i4 = i2 + 1;
        return i4 == i3 ? i2 + 2 : i4;
    }

    private int v(int i2, int i3) {
        int i4 = i2 - 1;
        return i4 == i3 ? i2 - 2 : i4;
    }

    private void w() {
        this.f8950r = 0;
        this.f8949q = true;
        this.f8942j.removeCallbacks(this.u);
        this.f8942j.postDelayed(this.u, 60000L);
    }

    @Override // cn.nubia.screensaver.power.GSPowerController.PowerCallback
    public void e(int i2, String str) {
        this.f8942j.removeCallbacks(this.u);
        this.f8941i.forEach(new Consumer() { // from class: cn.nubia.screensaver.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseCard) obj).h();
            }
        });
    }

    @Override // cn.nubia.screensaver.power.GSPowerController.PowerCallback
    public void h(int i2, String str) {
        w();
        this.f8941i.forEach(new Consumer() { // from class: cn.nubia.screensaver.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseCard) obj).l();
            }
        });
    }

    public void n(CardView cardView, boolean z, boolean z2) {
        if (this.f8941i.isEmpty() || this.f8943k == null || this.f8944l == null) {
            return;
        }
        if (!z2) {
            w();
        }
        int i2 = z ? 0 : 2;
        int i3 = z ? 2 : 0;
        GaLog.a("CardContainerController", "flip card " + cardView.j() + " up " + z + " l " + v + " r " + w);
        if (cardView.j()) {
            this.f8943k.m((BaseCard) this.f8941i.get(v), z);
            v = z ? this.f8946n : this.f8945m;
            y();
            x(this.f8943k, i2);
            this.f8943k.d((BaseCard) this.f8941i.get(z ? this.f8946n : this.f8945m), i3);
            this.f8943k.o((BaseCard) this.f8941i.get(v), z);
            x(this.f8944l, 2);
            x(this.f8944l, 0);
            this.f8944l.d((BaseCard) this.f8941i.get(this.f8947o), 0);
            this.f8944l.d((BaseCard) this.f8941i.get(this.f8948p), 2);
            return;
        }
        this.f8944l.m((BaseCard) this.f8941i.get(w), z);
        w = z ? this.f8948p : this.f8947o;
        y();
        x(this.f8944l, i2);
        this.f8944l.d((BaseCard) this.f8941i.get(z ? this.f8948p : this.f8947o), i3);
        this.f8944l.o((BaseCard) this.f8941i.get(w), z);
        x(this.f8943k, 2);
        x(this.f8943k, 0);
        this.f8943k.d((BaseCard) this.f8941i.get(this.f8945m), 0);
        this.f8943k.d((BaseCard) this.f8941i.get(this.f8946n), 2);
    }

    public void r() {
        this.t.y();
    }

    public void u() {
        GaLog.a("CardContainerController", "card release");
        this.f8951s.N(this);
        this.f8942j.removeCallbacks(this.u);
        try {
            try {
                this.f8941i.forEach(new Consumer() { // from class: cn.nubia.screensaver.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((BaseCard) obj).j();
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.f8943k.n();
            this.f8944l.n();
            this.f8941i.clear();
        }
    }

    public void x(ViewGroup viewGroup, int i2) {
        if (viewGroup == null) {
            return;
        }
        View childAt = viewGroup.getChildAt(i2);
        if (childAt != null) {
            viewGroup.removeView(childAt);
            return;
        }
        GaLog.a("CardContainerController", "remove view at " + i2 + " error");
    }

    public void y() {
        int size = this.f8941i.size() - 1;
        int v2 = v(v, w);
        this.f8945m = v2;
        this.f8945m = q(v2, w, size);
        int t = t(v, w);
        this.f8946n = t;
        this.f8946n = p(t, w, size);
        int v3 = v(w, v);
        this.f8947o = v3;
        this.f8947o = q(v3, v, size);
        int t2 = t(w, v);
        this.f8948p = t2;
        this.f8948p = p(t2, v, size);
        GaLog.a("CardContainerController", "lt " + this.f8945m + " lb " + this.f8946n + " rt " + this.f8947o + " rb " + this.f8948p);
    }
}
