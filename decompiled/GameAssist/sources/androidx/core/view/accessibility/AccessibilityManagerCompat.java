package androidx.core.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class AccessibilityManagerCompat {

    @Deprecated
    public interface AccessibilityStateChangeListener {
        void onAccessibilityStateChanged(boolean z);
    }

    @Deprecated
    public static abstract class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener {
    }

    private static class AccessibilityStateChangeListenerWrapper implements AccessibilityManager.AccessibilityStateChangeListener {

        /* renamed from: c, reason: collision with root package name */
        AccessibilityStateChangeListener f3477c;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AccessibilityStateChangeListenerWrapper) {
                return this.f3477c.equals(((AccessibilityStateChangeListenerWrapper) obj).f3477c);
            }
            return false;
        }

        public int hashCode() {
            return this.f3477c.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            this.f3477c.onAccessibilityStateChanged(z);
        }
    }

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static boolean a(AccessibilityManager accessibilityManager) {
            return accessibilityManager.isRequestFromAccessibilityTool();
        }
    }

    public interface TouchExplorationStateChangeListener {
        void onTouchExplorationStateChanged(boolean z);
    }

    private static final class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager.TouchExplorationStateChangeListener {

        /* renamed from: a, reason: collision with root package name */
        final TouchExplorationStateChangeListener f3478a;

        TouchExplorationStateChangeListenerWrapper(TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            this.f3478a = touchExplorationStateChangeListener;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TouchExplorationStateChangeListenerWrapper) {
                return this.f3478a.equals(((TouchExplorationStateChangeListenerWrapper) obj).f3478a);
            }
            return false;
        }

        public int hashCode() {
            return this.f3478a.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            this.f3478a.onTouchExplorationStateChanged(z);
        }
    }

    public static boolean a(AccessibilityManager accessibilityManager, TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        return accessibilityManager.addTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(touchExplorationStateChangeListener));
    }

    public static boolean b(AccessibilityManager accessibilityManager, TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        return accessibilityManager.removeTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(touchExplorationStateChangeListener));
    }
}
