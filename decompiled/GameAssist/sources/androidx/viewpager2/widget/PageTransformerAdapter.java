package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* loaded from: classes.dex */
final class PageTransformerAdapter extends ViewPager2.OnPageChangeCallback {

    /* renamed from: a, reason: collision with root package name */
    private final LinearLayoutManager f5814a;

    /* renamed from: b, reason: collision with root package name */
    private ViewPager2.PageTransformer f5815b;

    PageTransformerAdapter(LinearLayoutManager linearLayoutManager) {
        this.f5814a = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void a(int i2) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void b(int i2, float f2, int i3) {
        if (this.f5815b == null) {
            return;
        }
        float f3 = -f2;
        for (int i4 = 0; i4 < this.f5814a.P(); i4++) {
            View O = this.f5814a.O(i4);
            if (O == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i4), Integer.valueOf(this.f5814a.P())));
            }
            this.f5815b.a(O, (this.f5814a.p0(O) - i2) + f3);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void c(int i2) {
    }

    ViewPager2.PageTransformer d() {
        return this.f5815b;
    }

    void e(ViewPager2.PageTransformer pageTransformer) {
        this.f5815b = pageTransformer;
    }
}
