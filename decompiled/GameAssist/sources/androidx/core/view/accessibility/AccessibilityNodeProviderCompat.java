package androidx.core.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AccessibilityNodeProviderCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Object f3504a;

    static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProvider {

        /* renamed from: a, reason: collision with root package name */
        final AccessibilityNodeProviderCompat f3505a;

        AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            this.f3505a = accessibilityNodeProviderCompat;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
            AccessibilityNodeInfoCompat b2 = this.f3505a.b(i2);
            if (b2 == null) {
                return null;
            }
            return b2.N0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List findAccessibilityNodeInfosByText(String str, int i2) {
            List c2 = this.f3505a.c(str, i2);
            if (c2 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = c2.size();
            for (int i3 = 0; i3 < size; i3++) {
                arrayList.add(((AccessibilityNodeInfoCompat) c2.get(i3)).N0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i2) {
            AccessibilityNodeInfoCompat d2 = this.f3505a.d(i2);
            if (d2 == null) {
                return null;
            }
            return d2.N0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i2, int i3, Bundle bundle) {
            return this.f3505a.f(i2, i3, bundle);
        }
    }

    @RequiresApi
    static class AccessibilityNodeProviderApi26 extends AccessibilityNodeProviderApi19 {
        AccessibilityNodeProviderApi26(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            super(accessibilityNodeProviderCompat);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int i2, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            this.f3505a.a(i2, AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo), str, bundle);
        }
    }

    public AccessibilityNodeProviderCompat() {
        this.f3504a = new AccessibilityNodeProviderApi26(this);
    }

    public void a(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, Bundle bundle) {
    }

    public AccessibilityNodeInfoCompat b(int i2) {
        return null;
    }

    public List c(String str, int i2) {
        return null;
    }

    public AccessibilityNodeInfoCompat d(int i2) {
        return null;
    }

    public Object e() {
        return this.f3504a;
    }

    public boolean f(int i2, int i3, Bundle bundle) {
        return false;
    }

    public AccessibilityNodeProviderCompat(Object obj) {
        this.f3504a = obj;
    }
}
