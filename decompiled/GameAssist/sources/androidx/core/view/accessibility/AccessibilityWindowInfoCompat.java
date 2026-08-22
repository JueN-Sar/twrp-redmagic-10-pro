package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.os.LocaleListCompat;

/* loaded from: classes.dex */
public class AccessibilityWindowInfoCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Object f3508a;

    @RequiresApi
    private static class Api21Impl {
        @DoNotInline
        static void a(AccessibilityWindowInfo accessibilityWindowInfo, Rect rect) {
            accessibilityWindowInfo.getBoundsInScreen(rect);
        }

        @DoNotInline
        static AccessibilityWindowInfo b(AccessibilityWindowInfo accessibilityWindowInfo, int i2) {
            return accessibilityWindowInfo.getChild(i2);
        }

        @DoNotInline
        static int c(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getChildCount();
        }

        @DoNotInline
        static int d(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getId();
        }

        @DoNotInline
        static int e(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLayer();
        }

        @DoNotInline
        static AccessibilityWindowInfo f(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getParent();
        }

        @DoNotInline
        static AccessibilityNodeInfo g(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getRoot();
        }

        @DoNotInline
        static int h(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getType();
        }

        @DoNotInline
        static boolean i(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isAccessibilityFocused();
        }

        @DoNotInline
        static boolean j(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isActive();
        }

        @DoNotInline
        static boolean k(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isFocused();
        }

        @DoNotInline
        static AccessibilityWindowInfo l() {
            return AccessibilityWindowInfo.obtain();
        }

        @DoNotInline
        static AccessibilityWindowInfo m(AccessibilityWindowInfo accessibilityWindowInfo) {
            return AccessibilityWindowInfo.obtain(accessibilityWindowInfo);
        }
    }

    @RequiresApi
    private static class Api24Impl {
        @DoNotInline
        static AccessibilityNodeInfo a(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getAnchor();
        }

        @DoNotInline
        static CharSequence b(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTitle();
        }
    }

    @RequiresApi
    private static class Api26Impl {
        @DoNotInline
        static boolean a(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isInPictureInPictureMode();
        }
    }

    @RequiresApi
    private static class Api30Impl {
        @DoNotInline
        static AccessibilityWindowInfo a() {
            return new AccessibilityWindowInfo();
        }
    }

    @RequiresApi
    private static class Api33Impl {
        @DoNotInline
        static int a(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getDisplayId();
        }

        @DoNotInline
        static void b(AccessibilityWindowInfo accessibilityWindowInfo, Region region) {
            accessibilityWindowInfo.getRegionInScreen(region);
        }

        @DoNotInline
        public static AccessibilityNodeInfoCompat c(Object obj, int i2) {
            return AccessibilityNodeInfoCompat.P0(((AccessibilityWindowInfo) obj).getRoot(i2));
        }
    }

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        static LocaleList a(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLocales();
        }

        @DoNotInline
        public static long b(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTransitionTimeMillis();
        }
    }

    private AccessibilityWindowInfoCompat(Object obj) {
        this.f3508a = obj;
    }

    private static String k(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY" : "TYPE_SYSTEM" : "TYPE_INPUT_METHOD" : "TYPE_APPLICATION";
    }

    static AccessibilityWindowInfoCompat l(Object obj) {
        if (obj != null) {
            return new AccessibilityWindowInfoCompat(obj);
        }
        return null;
    }

    public void a(Rect rect) {
        Api21Impl.a((AccessibilityWindowInfo) this.f3508a, rect);
    }

    public int b() {
        return Api21Impl.c((AccessibilityWindowInfo) this.f3508a);
    }

    public int c() {
        return Api21Impl.d((AccessibilityWindowInfo) this.f3508a);
    }

    public int d() {
        return Api21Impl.e((AccessibilityWindowInfo) this.f3508a);
    }

    public LocaleListCompat e() {
        return Build.VERSION.SDK_INT >= 34 ? LocaleListCompat.j(Api34Impl.a((AccessibilityWindowInfo) this.f3508a)) : LocaleListCompat.e();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat) obj;
        Object obj2 = this.f3508a;
        return obj2 == null ? accessibilityWindowInfoCompat.f3508a == null : obj2.equals(accessibilityWindowInfoCompat.f3508a);
    }

    public AccessibilityWindowInfoCompat f() {
        return l(Api21Impl.f((AccessibilityWindowInfo) this.f3508a));
    }

    public long g() {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.b((AccessibilityWindowInfo) this.f3508a);
        }
        return 0L;
    }

    public int h() {
        return Api21Impl.h((AccessibilityWindowInfo) this.f3508a);
    }

    public int hashCode() {
        Object obj = this.f3508a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean i() {
        return Api21Impl.j((AccessibilityWindowInfo) this.f3508a);
    }

    public boolean j() {
        return Api21Impl.k((AccessibilityWindowInfo) this.f3508a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Rect rect = new Rect();
        a(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=");
        sb.append(c());
        sb.append(", type=");
        sb.append(k(h()));
        sb.append(", layer=");
        sb.append(d());
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(j());
        sb.append(", active=");
        sb.append(i());
        sb.append(", hasParent=");
        sb.append(f() != null);
        sb.append(", hasChildren=");
        sb.append(b() > 0);
        sb.append(", transitionTime=");
        sb.append(g());
        sb.append(", locales=");
        sb.append(e());
        sb.append(']');
        return sb.toString();
    }
}
