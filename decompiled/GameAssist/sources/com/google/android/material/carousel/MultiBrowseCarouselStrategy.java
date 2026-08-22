package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public final class MultiBrowseCarouselStrategy extends CarouselStrategy {

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f14184d = {1};

    /* renamed from: e, reason: collision with root package name */
    private static final int[] f14185e = {1, 0};

    /* renamed from: c, reason: collision with root package name */
    private int f14186c = 0;

    @Override // com.google.android.material.carousel.CarouselStrategy
    KeylineState g(Carousel carousel, View view) {
        float b2 = carousel.b();
        if (carousel.g()) {
            b2 = carousel.a();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f2 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (carousel.g()) {
            f2 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            measuredHeight = view.getMeasuredWidth();
        }
        float f3 = f2;
        float d2 = d() + f3;
        float max = Math.max(c() + f3, d2);
        float min = Math.min(measuredHeight + f3, b2);
        float a2 = MathUtils.a((measuredHeight / 3.0f) + f3, d2 + f3, max + f3);
        float f4 = (min + a2) / 2.0f;
        int[] iArr = f14184d;
        if (b2 < 2.0f * d2) {
            iArr = new int[]{0};
        }
        int[] iArr2 = f14185e;
        if (carousel.e() == 1) {
            iArr = CarouselStrategy.a(iArr);
            iArr2 = CarouselStrategy.a(iArr2);
        }
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int max2 = (int) Math.max(1.0d, Math.floor(((b2 - (CarouselStrategyHelper.i(iArr4) * f4)) - (CarouselStrategyHelper.i(iArr3) * max)) / min));
        int ceil = (int) Math.ceil(b2 / min);
        int i2 = (ceil - max2) + 1;
        int[] iArr5 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr5[i3] = ceil - i3;
        }
        Arrangement c2 = Arrangement.c(b2, a2, d2, max, iArr3, f4, iArr4, min, iArr5);
        this.f14186c = c2.e();
        if (i(c2, carousel.f())) {
            c2 = Arrangement.c(b2, a2, d2, max, new int[]{c2.f14128c}, f4, new int[]{c2.f14129d}, min, new int[]{c2.f14132g});
        }
        return CarouselStrategyHelper.d(view.getContext(), f3, b2, c2, carousel.e());
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    boolean h(Carousel carousel, int i2) {
        return (i2 < this.f14186c && carousel.f() >= this.f14186c) || (i2 >= this.f14186c && carousel.f() < this.f14186c);
    }

    boolean i(Arrangement arrangement, int i2) {
        int e2 = arrangement.e() - i2;
        boolean z = e2 > 0 && (arrangement.f14128c > 0 || arrangement.f14129d > 1);
        while (e2 > 0) {
            int i3 = arrangement.f14128c;
            if (i3 > 0) {
                arrangement.f14128c = i3 - 1;
            } else {
                int i4 = arrangement.f14129d;
                if (i4 > 1) {
                    arrangement.f14129d = i4 - 1;
                }
            }
            e2--;
        }
        return z;
    }
}
