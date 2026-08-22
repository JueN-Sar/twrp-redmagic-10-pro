package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.KeylineState;

/* loaded from: classes.dex */
public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private float i(float f2, float f3, float f4) {
        float max = Math.max(1.5f * f4, f2);
        float f5 = 0.85f * f3;
        if (max > f5) {
            max = Math.max(f5, f4 * 1.2f);
        }
        return Math.min(f3, max);
    }

    private KeylineState j(float f2, float f3, float f4, int i2, float f5, float f6, float f7) {
        float min = Math.min(f6, f4);
        float b2 = CarouselStrategy.b(min, f4, f3);
        float b3 = CarouselStrategy.b(f5, f4, f3);
        float f8 = f5 / 2.0f;
        float f9 = (f7 + 0.0f) - f8;
        float f10 = f9 + f8;
        float f11 = min / 2.0f;
        float f12 = (i2 * f4) + f10;
        KeylineState.Builder h2 = new KeylineState.Builder(f4, f2).a((f9 - f8) - f11, b2, min).c(f9, b3, f5, false).h((f4 / 2.0f) + f10, 0.0f, f4, i2, true);
        h2.c(f8 + f12, b3, f5, false);
        h2.a(f12 + f5 + f11, b2, min);
        return h2.i();
    }

    private KeylineState k(Context context, float f2, float f3, float f4, int i2, float f5, int i3, float f6) {
        float min = Math.min(f6, f4);
        float max = Math.max(min, 0.5f * f5);
        float b2 = CarouselStrategy.b(max, f4, f2);
        float b3 = CarouselStrategy.b(min, f4, f2);
        float b4 = CarouselStrategy.b(f5, f4, f2);
        float f7 = (i2 * f4) + 0.0f;
        KeylineState.Builder h2 = new KeylineState.Builder(f4, f3).a(0.0f - (max / 2.0f), b2, max).h(f4 / 2.0f, 0.0f, f4, i2, true);
        if (i3 > 0) {
            float f8 = (f5 / 2.0f) + f7;
            f7 += f5;
            h2.c(f8, b4, f5, false);
        }
        h2.a(f7 + (CarouselStrategyHelper.f(context) / 2.0f), b3, min);
        return h2.i();
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    boolean f() {
        return false;
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    KeylineState g(Carousel carousel, View view) {
        float f2;
        float a2 = carousel.g() ? carousel.a() : carousel.b();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f3 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (carousel.g()) {
            float f4 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            measuredHeight = view.getMeasuredWidth();
            f2 = f4;
        } else {
            f2 = f3;
        }
        float f5 = measuredHeight + f2;
        float f6 = CarouselStrategyHelper.f(view.getContext()) + f2;
        float f7 = CarouselStrategyHelper.f(view.getContext()) + f2;
        int max = Math.max(1, (int) Math.floor(a2 / f5));
        float f8 = a2 - (max * f5);
        if (carousel.e() == 1) {
            float f9 = f8 / 2.0f;
            return j(a2, f2, f5, max, Math.max(Math.min(3.0f * f9, f5), d() + f2), f7, f9);
        }
        return k(view.getContext(), f2, a2, f5, max, i(f6, f5, f8), f8 > 0.0f ? 1 : 0, f7);
    }
}
