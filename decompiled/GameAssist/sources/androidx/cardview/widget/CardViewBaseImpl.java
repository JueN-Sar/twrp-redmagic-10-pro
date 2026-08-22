package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.cardview.widget.RoundRectDrawableWithShadow;

/* loaded from: classes.dex */
class CardViewBaseImpl implements CardViewImpl {

    /* renamed from: a, reason: collision with root package name */
    final RectF f1139a;

    private RoundRectDrawableWithShadow p(Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        return new RoundRectDrawableWithShadow(context.getResources(), colorStateList, f2, f3, f4);
    }

    private RoundRectDrawableWithShadow q(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow) cardViewDelegate.d();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void a(CardViewDelegate cardViewDelegate, float f2) {
        q(cardViewDelegate).p(f2);
        k(cardViewDelegate);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float b(CardViewDelegate cardViewDelegate) {
        return q(cardViewDelegate).g();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void c(CardViewDelegate cardViewDelegate, float f2) {
        q(cardViewDelegate).r(f2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float d(CardViewDelegate cardViewDelegate) {
        return q(cardViewDelegate).i();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public ColorStateList e(CardViewDelegate cardViewDelegate) {
        return q(cardViewDelegate).f();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float f(CardViewDelegate cardViewDelegate) {
        return q(cardViewDelegate).j();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void g(CardViewDelegate cardViewDelegate) {
        q(cardViewDelegate).m(cardViewDelegate.f());
        k(cardViewDelegate);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void h(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        RoundRectDrawableWithShadow p2 = p(context, colorStateList, f2, f3, f4);
        p2.m(cardViewDelegate.f());
        cardViewDelegate.b(p2);
        k(cardViewDelegate);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float i(CardViewDelegate cardViewDelegate) {
        return q(cardViewDelegate).l();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void j(CardViewDelegate cardViewDelegate) {
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void k(CardViewDelegate cardViewDelegate) {
        Rect rect = new Rect();
        q(cardViewDelegate).h(rect);
        cardViewDelegate.e((int) Math.ceil(m(cardViewDelegate)), (int) Math.ceil(f(cardViewDelegate)));
        cardViewDelegate.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void l() {
        RoundRectDrawableWithShadow.f1153r = new RoundRectDrawableWithShadow.RoundRectHelper() { // from class: androidx.cardview.widget.CardViewBaseImpl.1
            @Override // androidx.cardview.widget.RoundRectDrawableWithShadow.RoundRectHelper
            public void a(Canvas canvas, RectF rectF, float f2, Paint paint) {
                float f3 = 2.0f * f2;
                float width = (rectF.width() - f3) - 1.0f;
                float height = (rectF.height() - f3) - 1.0f;
                if (f2 >= 1.0f) {
                    float f4 = f2 + 0.5f;
                    float f5 = -f4;
                    CardViewBaseImpl.this.f1139a.set(f5, f5, f4, f4);
                    int save = canvas.save();
                    canvas.translate(rectF.left + f4, rectF.top + f4);
                    canvas.drawArc(CardViewBaseImpl.this.f1139a, 180.0f, 90.0f, true, paint);
                    canvas.translate(width, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.f1139a, 180.0f, 90.0f, true, paint);
                    canvas.translate(height, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.f1139a, 180.0f, 90.0f, true, paint);
                    canvas.translate(width, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.f1139a, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(save);
                    float f6 = (rectF.left + f4) - 1.0f;
                    float f7 = rectF.top;
                    canvas.drawRect(f6, f7, (rectF.right - f4) + 1.0f, f7 + f4, paint);
                    float f8 = (rectF.left + f4) - 1.0f;
                    float f9 = rectF.bottom;
                    canvas.drawRect(f8, f9 - f4, (rectF.right - f4) + 1.0f, f9, paint);
                }
                canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, rectF.bottom - f2, paint);
            }
        };
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float m(CardViewDelegate cardViewDelegate) {
        return q(cardViewDelegate).k();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void n(CardViewDelegate cardViewDelegate, ColorStateList colorStateList) {
        q(cardViewDelegate).o(colorStateList);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void o(CardViewDelegate cardViewDelegate, float f2) {
        q(cardViewDelegate).q(f2);
        k(cardViewDelegate);
    }
}
