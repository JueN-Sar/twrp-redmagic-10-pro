package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class HeroCarouselStrategy extends CarouselStrategy {

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f14153d = {1};

    /* renamed from: e, reason: collision with root package name */
    private static final int[] f14154e = {0, 1};

    /* renamed from: c, reason: collision with root package name */
    private int f14155c;

    @Override // com.google.android.material.carousel.CarouselStrategy
    KeylineState g(Carousel carousel, View view) {
        int i2;
        int b2 = carousel.b();
        if (carousel.g()) {
            b2 = carousel.a();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f2 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        float measuredWidth = view.getMeasuredWidth() * 2;
        if (carousel.g()) {
            f2 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            measuredWidth = view.getMeasuredHeight() * 2;
        }
        float d2 = d() + f2;
        float max = Math.max(c() + f2, d2);
        float f3 = b2;
        float min = Math.min(measuredWidth + f2, f3);
        float a2 = MathUtils.a((measuredWidth / 3.0f) + f2, d2 + f2, max + f2);
        float f4 = (min + a2) / 2.0f;
        int[] iArr = f3 < 2.0f * d2 ? new int[]{0} : f14153d;
        int max2 = (int) Math.max(1.0d, Math.floor((f3 - (CarouselStrategyHelper.i(r4) * max)) / min));
        int ceil = (((int) Math.ceil(f3 / min)) - max2) + 1;
        int[] iArr2 = new int[ceil];
        for (int i3 = 0; i3 < ceil; i3++) {
            iArr2[i3] = max2 + i3;
        }
        int i4 = carousel.e() == 1 ? 1 : 0;
        Arrangement c2 = Arrangement.c(f3, a2, d2, max, i4 != 0 ? CarouselStrategy.a(iArr) : iArr, f4, i4 != 0 ? CarouselStrategy.a(f14154e) : f14154e, min, iArr2);
        this.f14155c = c2.e();
        if (c2.e() > carousel.f()) {
            c2 = Arrangement.c(f3, a2, d2, max, iArr, f4, f14154e, min, iArr2);
            i2 = 0;
        } else {
            i2 = i4;
        }
        return CarouselStrategyHelper.d(view.getContext(), f2, f3, c2, i2);
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    boolean h(Carousel carousel, int i2) {
        if (carousel.e() == 1) {
            if (i2 < this.f14155c && carousel.f() >= this.f14155c) {
                return true;
            }
            if (i2 >= this.f14155c && carousel.f() < this.f14155c) {
                return true;
            }
        }
        return false;
    }
}
