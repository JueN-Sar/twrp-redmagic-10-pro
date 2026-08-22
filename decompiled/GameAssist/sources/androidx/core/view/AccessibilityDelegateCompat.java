package androidx.core.view;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class AccessibilityDelegateCompat {

    /* renamed from: c, reason: collision with root package name */
    private static final View.AccessibilityDelegate f3306c = new View.AccessibilityDelegate();

    /* renamed from: a, reason: collision with root package name */
    private final View.AccessibilityDelegate f3307a;

    /* renamed from: b, reason: collision with root package name */
    private final View.AccessibilityDelegate f3308b;

    static final class AccessibilityDelegateAdapter extends View.AccessibilityDelegate {

        /* renamed from: a, reason: collision with root package name */
        final AccessibilityDelegateCompat f3309a;

        AccessibilityDelegateAdapter(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            this.f3309a = accessibilityDelegateCompat;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f3309a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            AccessibilityNodeProviderCompat b2 = this.f3309a.b(view);
            if (b2 != null) {
                return (AccessibilityNodeProvider) b2.e();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f3309a.f(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfoCompat O0 = AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo);
            O0.D0(ViewCompat.Q(view));
            O0.r0(ViewCompat.L(view));
            O0.y0(ViewCompat.l(view));
            O0.J0(ViewCompat.C(view));
            this.f3309a.g(view, O0);
            O0.e(accessibilityNodeInfo.getText(), view);
            List c2 = AccessibilityDelegateCompat.c(view);
            for (int i2 = 0; i2 < c2.size(); i2++) {
                O0.b((AccessibilityNodeInfoCompat.AccessibilityActionCompat) c2.get(i2));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f3309a.h(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f3309a.i(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            return this.f3309a.j(view, i2, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i2) {
            this.f3309a.l(view, i2);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f3309a.m(view, accessibilityEvent);
        }
    }

    public AccessibilityDelegateCompat() {
        this(f3306c);
    }

    static List c(View view) {
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    private boolean e(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] p2 = AccessibilityNodeInfoCompat.p(view.createAccessibilityNodeInfo().getText());
            for (int i2 = 0; p2 != null && i2 < p2.length; i2++) {
                if (clickableSpan.equals(p2[i2])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean k(int i2, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i2)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!e(clickableSpan, view)) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f3307a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public AccessibilityNodeProviderCompat b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.f3307a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }

    View.AccessibilityDelegate d() {
        return this.f3308b;
    }

    public void f(View view, AccessibilityEvent accessibilityEvent) {
        this.f3307a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.f3307a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.N0());
    }

    public void h(View view, AccessibilityEvent accessibilityEvent) {
        this.f3307a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f3307a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean j(View view, int i2, Bundle bundle) {
        List c2 = c(view);
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (i3 >= c2.size()) {
                break;
            }
            AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = (AccessibilityNodeInfoCompat.AccessibilityActionCompat) c2.get(i3);
            if (accessibilityActionCompat.b() == i2) {
                z = accessibilityActionCompat.d(view, bundle);
                break;
            }
            i3++;
        }
        if (!z) {
            z = this.f3307a.performAccessibilityAction(view, i2, bundle);
        }
        return (z || i2 != R.id.accessibility_action_clickable_span || bundle == null) ? z : k(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    public void l(View view, int i2) {
        this.f3307a.sendAccessibilityEvent(view, i2);
    }

    public void m(View view, AccessibilityEvent accessibilityEvent) {
        this.f3307a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public AccessibilityDelegateCompat(View.AccessibilityDelegate accessibilityDelegate) {
        this.f3307a = accessibilityDelegate;
        this.f3308b = new AccessibilityDelegateAdapter(this);
    }
}
