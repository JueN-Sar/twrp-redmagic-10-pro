package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {

    /* renamed from: d, reason: collision with root package name */
    final RecyclerView f5269d;

    /* renamed from: e, reason: collision with root package name */
    private final ItemDelegate f5270e;

    public static class ItemDelegate extends AccessibilityDelegateCompat {

        /* renamed from: d, reason: collision with root package name */
        final RecyclerViewAccessibilityDelegate f5271d;

        /* renamed from: e, reason: collision with root package name */
        private Map f5272e = new WeakHashMap();

        public ItemDelegate(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
            this.f5271d = recyclerViewAccessibilityDelegate;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            return accessibilityDelegateCompat != null ? accessibilityDelegateCompat.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public AccessibilityNodeProviderCompat b(View view) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            return accessibilityDelegateCompat != null ? accessibilityDelegateCompat.b(view) : super.b(view);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.f5271d.o() || this.f5271d.f5269d.getLayoutManager() == null) {
                super.g(view, accessibilityNodeInfoCompat);
                return;
            }
            this.f5271d.f5269d.getLayoutManager().X0(view, accessibilityNodeInfoCompat);
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.g(view, accessibilityNodeInfoCompat);
            } else {
                super.g(view, accessibilityNodeInfoCompat);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(viewGroup);
            return accessibilityDelegateCompat != null ? accessibilityDelegateCompat.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean j(View view, int i2, Bundle bundle) {
            if (this.f5271d.o() || this.f5271d.f5269d.getLayoutManager() == null) {
                return super.j(view, i2, bundle);
            }
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            if (accessibilityDelegateCompat != null) {
                if (accessibilityDelegateCompat.j(view, i2, bundle)) {
                    return true;
                }
            } else if (super.j(view, i2, bundle)) {
                return true;
            }
            return this.f5271d.f5269d.getLayoutManager().r1(view, i2, bundle);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void l(View view, int i2) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.l(view, i2);
            } else {
                super.l(view, i2);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = (AccessibilityDelegateCompat) this.f5272e.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        AccessibilityDelegateCompat n(View view) {
            return (AccessibilityDelegateCompat) this.f5272e.remove(view);
        }

        void o(View view) {
            AccessibilityDelegateCompat j2 = ViewCompat.j(view);
            if (j2 == null || j2 == this) {
                return;
            }
            this.f5272e.put(view, j2);
        }
    }

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        this.f5269d = recyclerView;
        AccessibilityDelegateCompat n2 = n();
        if (n2 == null || !(n2 instanceof ItemDelegate)) {
            this.f5270e = new ItemDelegate(this);
        } else {
            this.f5270e = (ItemDelegate) n2;
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().T0(accessibilityEvent);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.g(view, accessibilityNodeInfoCompat);
        if (o() || this.f5269d.getLayoutManager() == null) {
            return;
        }
        this.f5269d.getLayoutManager().V0(accessibilityNodeInfoCompat);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public boolean j(View view, int i2, Bundle bundle) {
        if (super.j(view, i2, bundle)) {
            return true;
        }
        if (o() || this.f5269d.getLayoutManager() == null) {
            return false;
        }
        return this.f5269d.getLayoutManager().p1(i2, bundle);
    }

    public AccessibilityDelegateCompat n() {
        return this.f5270e;
    }

    boolean o() {
        return this.f5269d.p0();
    }
}
