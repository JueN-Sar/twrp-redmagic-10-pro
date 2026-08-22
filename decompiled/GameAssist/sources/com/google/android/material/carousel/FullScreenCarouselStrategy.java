package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class FullScreenCarouselStrategy extends CarouselStrategy {
    @Override // com.google.android.material.carousel.CarouselStrategy
    KeylineState g(Carousel carousel, View view) {
        float b2;
        int i2;
        int i3;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (carousel.g()) {
            b2 = carousel.a();
            i2 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            i3 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        } else {
            b2 = carousel.b();
            i2 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            i3 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        float f2 = i2 + i3;
        return CarouselStrategyHelper.e(view.getContext(), f2, b2, new Arrangement(0, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0, Math.min(b2 + f2, b2), 1, b2));
    }
}
