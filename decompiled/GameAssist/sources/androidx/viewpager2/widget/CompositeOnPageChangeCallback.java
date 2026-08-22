package androidx.viewpager2.widget;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class CompositeOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {

    /* renamed from: a, reason: collision with root package name */
    private final List f5808a;

    CompositeOnPageChangeCallback(int i2) {
        this.f5808a = new ArrayList(i2);
    }

    private void f(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void a(int i2) {
        try {
            Iterator it = this.f5808a.iterator();
            while (it.hasNext()) {
                ((ViewPager2.OnPageChangeCallback) it.next()).a(i2);
            }
        } catch (ConcurrentModificationException e2) {
            f(e2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void b(int i2, float f2, int i3) {
        try {
            Iterator it = this.f5808a.iterator();
            while (it.hasNext()) {
                ((ViewPager2.OnPageChangeCallback) it.next()).b(i2, f2, i3);
            }
        } catch (ConcurrentModificationException e2) {
            f(e2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void c(int i2) {
        try {
            Iterator it = this.f5808a.iterator();
            while (it.hasNext()) {
                ((ViewPager2.OnPageChangeCallback) it.next()).c(i2);
            }
        } catch (ConcurrentModificationException e2) {
            f(e2);
        }
    }

    void d(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.f5808a.add(onPageChangeCallback);
    }

    void e(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.f5808a.remove(onPageChangeCallback);
    }
}
