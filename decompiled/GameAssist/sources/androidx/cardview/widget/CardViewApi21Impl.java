package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class CardViewApi21Impl implements CardViewImpl {
    CardViewApi21Impl() {
    }

    private RoundRectDrawable p(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawable) cardViewDelegate.d();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void a(CardViewDelegate cardViewDelegate, float f2) {
        p(cardViewDelegate).h(f2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float b(CardViewDelegate cardViewDelegate) {
        return p(cardViewDelegate).d();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void c(CardViewDelegate cardViewDelegate, float f2) {
        cardViewDelegate.g().setElevation(f2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float d(CardViewDelegate cardViewDelegate) {
        return p(cardViewDelegate).c();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public ColorStateList e(CardViewDelegate cardViewDelegate) {
        return p(cardViewDelegate).b();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float f(CardViewDelegate cardViewDelegate) {
        return b(cardViewDelegate) * 2.0f;
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void g(CardViewDelegate cardViewDelegate) {
        o(cardViewDelegate, d(cardViewDelegate));
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void h(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        cardViewDelegate.b(new RoundRectDrawable(colorStateList, f2));
        View g2 = cardViewDelegate.g();
        g2.setClipToOutline(true);
        g2.setElevation(f3);
        o(cardViewDelegate, f4);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float i(CardViewDelegate cardViewDelegate) {
        return cardViewDelegate.g().getElevation();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void j(CardViewDelegate cardViewDelegate) {
        o(cardViewDelegate, d(cardViewDelegate));
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void k(CardViewDelegate cardViewDelegate) {
        if (!cardViewDelegate.c()) {
            cardViewDelegate.a(0, 0, 0, 0);
            return;
        }
        float d2 = d(cardViewDelegate);
        float b2 = b(cardViewDelegate);
        int ceil = (int) Math.ceil(RoundRectDrawableWithShadow.c(d2, b2, cardViewDelegate.f()));
        int ceil2 = (int) Math.ceil(RoundRectDrawableWithShadow.d(d2, b2, cardViewDelegate.f()));
        cardViewDelegate.a(ceil, ceil2, ceil, ceil2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void l() {
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float m(CardViewDelegate cardViewDelegate) {
        return b(cardViewDelegate) * 2.0f;
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void n(CardViewDelegate cardViewDelegate, ColorStateList colorStateList) {
        p(cardViewDelegate).f(colorStateList);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void o(CardViewDelegate cardViewDelegate, float f2) {
        p(cardViewDelegate).g(f2, cardViewDelegate.c(), cardViewDelegate.f());
        k(cardViewDelegate);
    }
}
