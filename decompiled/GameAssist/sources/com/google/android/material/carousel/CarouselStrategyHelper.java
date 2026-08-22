package com.google.android.material.carousel;

import android.content.Context;
import com.google.android.material.R;
import com.google.android.material.carousel.KeylineState;

/* loaded from: classes.dex */
final class CarouselStrategyHelper {
    static float a(float f2, float f3, int i2) {
        return f2 + (Math.max(0, i2 - 1) * f3);
    }

    static float b(float f2, float f3, int i2) {
        return i2 > 0 ? f2 + (f3 / 2.0f) : f2;
    }

    static KeylineState c(Context context, float f2, float f3, Arrangement arrangement) {
        float f4;
        float f5;
        float min = Math.min(f(context) + f2, arrangement.f14131f);
        float f6 = min / 2.0f;
        float f7 = 0.0f - f6;
        float b2 = b(0.0f, arrangement.f14127b, arrangement.f14128c);
        float j2 = j(0.0f, a(b2, arrangement.f14127b, (int) Math.floor(arrangement.f14128c / 2.0f)), arrangement.f14127b, arrangement.f14128c);
        float b3 = b(j2, arrangement.f14130e, arrangement.f14129d);
        float j3 = j(j2, a(b3, arrangement.f14130e, (int) Math.floor(arrangement.f14129d / 2.0f)), arrangement.f14130e, arrangement.f14129d);
        float b4 = b(j3, arrangement.f14131f, arrangement.f14132g);
        float j4 = j(j3, a(b4, arrangement.f14131f, arrangement.f14132g), arrangement.f14131f, arrangement.f14132g);
        float b5 = b(j4, arrangement.f14130e, arrangement.f14129d);
        float b6 = b(j(j4, a(b5, arrangement.f14130e, (int) Math.ceil(arrangement.f14129d / 2.0f)), arrangement.f14130e, arrangement.f14129d), arrangement.f14127b, arrangement.f14128c);
        float f8 = f6 + f3;
        float b7 = CarouselStrategy.b(min, arrangement.f14131f, f2);
        float b8 = CarouselStrategy.b(arrangement.f14127b, arrangement.f14131f, f2);
        float b9 = CarouselStrategy.b(arrangement.f14130e, arrangement.f14131f, f2);
        KeylineState.Builder a2 = new KeylineState.Builder(arrangement.f14131f, f3).a(f7, b7, min);
        if (arrangement.f14128c > 0) {
            f4 = f8;
            a2.g(b2, b8, arrangement.f14127b, (int) Math.floor(r7 / 2.0f));
        } else {
            f4 = f8;
        }
        if (arrangement.f14129d > 0) {
            a2.g(b3, b9, arrangement.f14130e, (int) Math.floor(r4 / 2.0f));
        }
        a2.h(b4, 0.0f, arrangement.f14131f, arrangement.f14132g, true);
        if (arrangement.f14129d > 0) {
            f5 = 2.0f;
            a2.g(b5, b9, arrangement.f14130e, (int) Math.ceil(r4 / 2.0f));
        } else {
            f5 = 2.0f;
        }
        if (arrangement.f14128c > 0) {
            a2.g(b6, b8, arrangement.f14127b, (int) Math.ceil(r0 / f5));
        }
        a2.a(f4, b7, min);
        return a2.i();
    }

    static KeylineState d(Context context, float f2, float f3, Arrangement arrangement, int i2) {
        return i2 == 1 ? c(context, f2, f3, arrangement) : e(context, f2, f3, arrangement);
    }

    static KeylineState e(Context context, float f2, float f3, Arrangement arrangement) {
        float min = Math.min(f(context) + f2, arrangement.f14131f);
        float f4 = min / 2.0f;
        float f5 = 0.0f - f4;
        float b2 = b(0.0f, arrangement.f14131f, arrangement.f14132g);
        float j2 = j(0.0f, a(b2, arrangement.f14131f, arrangement.f14132g), arrangement.f14131f, arrangement.f14132g);
        float b3 = b(j2, arrangement.f14130e, arrangement.f14129d);
        float b4 = b(j(j2, b3, arrangement.f14130e, arrangement.f14129d), arrangement.f14127b, arrangement.f14128c);
        float f6 = f4 + f3;
        float b5 = CarouselStrategy.b(min, arrangement.f14131f, f2);
        float b6 = CarouselStrategy.b(arrangement.f14127b, arrangement.f14131f, f2);
        float b7 = CarouselStrategy.b(arrangement.f14130e, arrangement.f14131f, f2);
        KeylineState.Builder h2 = new KeylineState.Builder(arrangement.f14131f, f3).a(f5, b5, min).h(b2, 0.0f, arrangement.f14131f, arrangement.f14132g, true);
        if (arrangement.f14129d > 0) {
            h2.b(b3, b7, arrangement.f14130e);
        }
        int i2 = arrangement.f14128c;
        if (i2 > 0) {
            h2.g(b4, b6, arrangement.f14127b, i2);
        }
        h2.a(f6, b5, min);
        return h2.i();
    }

    static float f(Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_gone_size);
    }

    static float g(Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_max);
    }

    static float h(Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);
    }

    static int i(int[] iArr) {
        int i2 = Integer.MIN_VALUE;
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    static float j(float f2, float f3, float f4, int i2) {
        return i2 > 0 ? f3 + (f4 / 2.0f) : f2;
    }
}
