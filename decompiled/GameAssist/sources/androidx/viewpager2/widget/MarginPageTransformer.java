package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* loaded from: classes.dex */
public final class MarginPageTransformer implements ViewPager2.PageTransformer {

    /* renamed from: a, reason: collision with root package name */
    private final int f5813a;

    private ViewPager2 b(View view) {
        ViewParent parent = view.getParent();
        ViewParent parent2 = parent.getParent();
        if ((parent instanceof RecyclerView) && (parent2 instanceof ViewPager2)) {
            return (ViewPager2) parent2;
        }
        throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void a(View view, float f2) {
        ViewPager2 b2 = b(view);
        float f3 = this.f5813a * f2;
        if (b2.getOrientation() != 0) {
            view.setTranslationY(f3);
            return;
        }
        if (b2.d()) {
            f3 = -f3;
        }
        view.setTranslationX(f3);
    }
}
