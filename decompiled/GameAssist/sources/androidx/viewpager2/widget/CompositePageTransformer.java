package androidx.viewpager2.widget;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CompositePageTransformer implements ViewPager2.PageTransformer {

    /* renamed from: a, reason: collision with root package name */
    private final List f5809a;

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void a(View view, float f2) {
        Iterator it = this.f5809a.iterator();
        while (it.hasNext()) {
            ((ViewPager2.PageTransformer) it.next()).a(view, f2);
        }
    }
}
