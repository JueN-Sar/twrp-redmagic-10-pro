package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
abstract class CarouselOrientationHelper {

    /* renamed from: a, reason: collision with root package name */
    final int f14144a;

    private static CarouselOrientationHelper b(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(0) { // from class: com.google.android.material.carousel.CarouselOrientationHelper.2
            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void a(RectF rectF, RectF rectF2, RectF rectF3) {
                float f2 = rectF2.left;
                float f3 = rectF3.left;
                if (f2 < f3 && rectF2.right > f3) {
                    float f4 = f3 - f2;
                    rectF.left += f4;
                    rectF2.left += f4;
                }
                float f5 = rectF2.right;
                float f6 = rectF3.right;
                if (f5 <= f6 || rectF2.left >= f6) {
                    return;
                }
                float f7 = f5 - f6;
                rectF.right = Math.max(rectF.right - f7, rectF.left);
                rectF2.right = Math.max(rectF2.right - f7, rectF2.left);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public float e(RecyclerView.LayoutParams layoutParams) {
                return ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public RectF f(float f2, float f3, float f4, float f5) {
                return new RectF(f5, 0.0f, f3 - f5, f2);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int g() {
                return carouselLayoutManager.c0() - carouselLayoutManager.j0();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int h() {
                return carouselLayoutManager.L2() ? i() : j();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int i() {
                return 0;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int j() {
                return carouselLayoutManager.w0();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int k() {
                return carouselLayoutManager.L2() ? j() : i();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int l() {
                return carouselLayoutManager.o0();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void m(View view, int i2, int i3) {
                int l2 = l();
                carouselLayoutManager.I0(view, i2, l2, i3, l2 + p(view));
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void n(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.right <= rectF3.left) {
                    float floor = ((float) Math.floor(rectF.right)) - 1.0f;
                    rectF.right = floor;
                    rectF.left = Math.min(rectF.left, floor);
                }
                if (rectF2.left >= rectF3.right) {
                    float ceil = ((float) Math.ceil(rectF.left)) + 1.0f;
                    rectF.left = ceil;
                    rectF.right = Math.max(ceil, rectF.right);
                }
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void o(View view, Rect rect, float f2, float f3) {
                view.offsetLeftAndRight((int) (f3 - (rect.left + f2)));
            }

            int p(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return carouselLayoutManager.X(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            }
        };
    }

    static CarouselOrientationHelper c(CarouselLayoutManager carouselLayoutManager, int i2) {
        if (i2 == 0) {
            return b(carouselLayoutManager);
        }
        if (i2 == 1) {
            return d(carouselLayoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    private static CarouselOrientationHelper d(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(1) { // from class: com.google.android.material.carousel.CarouselOrientationHelper.1
            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void a(RectF rectF, RectF rectF2, RectF rectF3) {
                float f2 = rectF2.top;
                float f3 = rectF3.top;
                if (f2 < f3 && rectF2.bottom > f3) {
                    float f4 = f3 - f2;
                    rectF.top += f4;
                    rectF3.top += f4;
                }
                float f5 = rectF2.bottom;
                float f6 = rectF3.bottom;
                if (f5 <= f6 || rectF2.top >= f6) {
                    return;
                }
                float f7 = f5 - f6;
                rectF.bottom = Math.max(rectF.bottom - f7, rectF.top);
                rectF2.bottom = Math.max(rectF2.bottom - f7, rectF2.top);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public float e(RecyclerView.LayoutParams layoutParams) {
                return ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public RectF f(float f2, float f3, float f4, float f5) {
                return new RectF(0.0f, f4, f3, f2 - f4);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int g() {
                return carouselLayoutManager.c0();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int h() {
                return g();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int i() {
                return carouselLayoutManager.l0();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int j() {
                return carouselLayoutManager.w0() - carouselLayoutManager.m0();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int k() {
                return l();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            int l() {
                return 0;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void m(View view, int i2, int i3) {
                int i4 = i();
                carouselLayoutManager.I0(view, i4, i2, i4 + p(view), i3);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void n(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.bottom <= rectF3.top) {
                    float floor = ((float) Math.floor(rectF.bottom)) - 1.0f;
                    rectF.bottom = floor;
                    rectF.top = Math.min(rectF.top, floor);
                }
                if (rectF2.top >= rectF3.bottom) {
                    float ceil = ((float) Math.ceil(rectF.top)) + 1.0f;
                    rectF.top = ceil;
                    rectF.bottom = Math.max(ceil, rectF.bottom);
                }
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void o(View view, Rect rect, float f2, float f3) {
                view.offsetTopAndBottom((int) (f3 - (rect.top + f2)));
            }

            int p(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return carouselLayoutManager.Y(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            }
        };
    }

    abstract void a(RectF rectF, RectF rectF2, RectF rectF3);

    abstract float e(RecyclerView.LayoutParams layoutParams);

    abstract RectF f(float f2, float f3, float f4, float f5);

    abstract int g();

    abstract int h();

    abstract int i();

    abstract int j();

    abstract int k();

    abstract int l();

    abstract void m(View view, int i2, int i3);

    abstract void n(RectF rectF, RectF rectF2, RectF rectF3);

    abstract void o(View view, Rect rect, float f2, float f3);

    private CarouselOrientationHelper(int i2) {
        this.f14144a = i2;
    }
}
