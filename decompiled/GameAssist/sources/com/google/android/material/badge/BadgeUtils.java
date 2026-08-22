package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.android.material.internal.ToolbarUtils;

@ExperimentalBadgeUtils
/* loaded from: classes.dex */
public class BadgeUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f13948a = false;

    /* renamed from: com.google.android.material.badge.BadgeUtils$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Toolbar f13949c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f13950h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ BadgeDrawable f13951i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ FrameLayout f13952j;

        @Override // java.lang.Runnable
        public void run() {
            ActionMenuItemView a2 = ToolbarUtils.a(this.f13949c, this.f13950h);
            if (a2 != null) {
                BadgeUtils.setToolbarOffset(this.f13951i, this.f13949c.getResources());
                BadgeUtils.c(this.f13951i, a2, this.f13952j);
                BadgeUtils.b(this.f13951i, a2);
            }
        }
    }

    /* renamed from: com.google.android.material.badge.BadgeUtils$4, reason: invalid class name */
    class AnonymousClass4 extends AccessibilityDelegateCompat {
        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.g(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.l0(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final BadgeDrawable badgeDrawable, View view) {
        if (ViewCompat.I(view)) {
            ViewCompat.i0(view, new AccessibilityDelegateCompat(view.getAccessibilityDelegate()) { // from class: com.google.android.material.badge.BadgeUtils.2
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.g(view2, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.l0(badgeDrawable.i());
                }
            });
        } else {
            ViewCompat.i0(view, new AccessibilityDelegateCompat() { // from class: com.google.android.material.badge.BadgeUtils.3
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.g(view2, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.l0(BadgeDrawable.this.i());
                }
            });
        }
    }

    public static void c(BadgeDrawable badgeDrawable, View view, FrameLayout frameLayout) {
        g(badgeDrawable, view, frameLayout);
        if (badgeDrawable.j() != null) {
            badgeDrawable.j().setForeground(badgeDrawable);
        } else {
            if (f13948a) {
                throw new IllegalArgumentException("Trying to reference null customBadgeParent");
            }
            view.getOverlay().add(badgeDrawable);
        }
    }

    public static SparseArray d(Context context, ParcelableSparseArray parcelableSparseArray) {
        SparseArray sparseArray = new SparseArray(parcelableSparseArray.size());
        for (int i2 = 0; i2 < parcelableSparseArray.size(); i2++) {
            int keyAt = parcelableSparseArray.keyAt(i2);
            BadgeState.State state = (BadgeState.State) parcelableSparseArray.valueAt(i2);
            sparseArray.put(keyAt, state != null ? BadgeDrawable.e(context, state) : null);
        }
        return sparseArray;
    }

    public static ParcelableSparseArray e(SparseArray sparseArray) {
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            int keyAt = sparseArray.keyAt(i2);
            BadgeDrawable badgeDrawable = (BadgeDrawable) sparseArray.valueAt(i2);
            parcelableSparseArray.put(keyAt, badgeDrawable != null ? badgeDrawable.t() : null);
        }
        return parcelableSparseArray;
    }

    public static void f(BadgeDrawable badgeDrawable, View view) {
        if (badgeDrawable == null) {
            return;
        }
        if (f13948a || badgeDrawable.j() != null) {
            badgeDrawable.j().setForeground(null);
        } else {
            view.getOverlay().remove(badgeDrawable);
        }
    }

    public static void g(BadgeDrawable badgeDrawable, View view, FrameLayout frameLayout) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        badgeDrawable.setBounds(rect);
        badgeDrawable.S(view, frameLayout);
    }

    public static void h(Rect rect, float f2, float f3, float f4, float f5) {
        rect.set((int) (f2 - f4), (int) (f3 - f5), (int) (f2 + f4), (int) (f3 + f5));
    }

    @VisibleForTesting
    static void removeToolbarOffset(BadgeDrawable badgeDrawable) {
        badgeDrawable.O(0);
        badgeDrawable.P(0);
    }

    @VisibleForTesting
    static void setToolbarOffset(BadgeDrawable badgeDrawable, Resources resources) {
        badgeDrawable.O(resources.getDimensionPixelOffset(R.dimen.mtrl_badge_toolbar_action_menu_item_horizontal_offset));
        badgeDrawable.P(resources.getDimensionPixelOffset(R.dimen.mtrl_badge_toolbar_action_menu_item_vertical_offset));
    }
}
