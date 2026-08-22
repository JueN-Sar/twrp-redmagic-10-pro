package androidx.preference;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

@RestrictTo
/* loaded from: classes.dex */
public class PreferenceRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {

    /* renamed from: f, reason: collision with root package name */
    final RecyclerView f4755f;

    /* renamed from: g, reason: collision with root package name */
    final AccessibilityDelegateCompat f4756g;

    /* renamed from: h, reason: collision with root package name */
    final AccessibilityDelegateCompat f4757h;

    public PreferenceRecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        super(recyclerView);
        this.f4756g = super.n();
        this.f4757h = new AccessibilityDelegateCompat() { // from class: androidx.preference.PreferenceRecyclerViewAccessibilityDelegate.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                Preference O;
                PreferenceRecyclerViewAccessibilityDelegate.this.f4756g.g(view, accessibilityNodeInfoCompat);
                int f0 = PreferenceRecyclerViewAccessibilityDelegate.this.f4755f.f0(view);
                RecyclerView.Adapter adapter = PreferenceRecyclerViewAccessibilityDelegate.this.f4755f.getAdapter();
                if ((adapter instanceof PreferenceGroupAdapter) && (O = ((PreferenceGroupAdapter) adapter).O(f0)) != null) {
                    O.W(accessibilityNodeInfoCompat);
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean j(View view, int i2, Bundle bundle) {
                return PreferenceRecyclerViewAccessibilityDelegate.this.f4756g.j(view, i2, bundle);
            }
        };
        this.f4755f = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
    public AccessibilityDelegateCompat n() {
        return this.f4757h;
    }
}
