package androidx.core.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class AccessibilityEventCompat {

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static boolean a(AccessibilityEvent accessibilityEvent) {
            return accessibilityEvent.isAccessibilityDataSensitive();
        }

        @DoNotInline
        static void b(AccessibilityEvent accessibilityEvent, boolean z) {
            accessibilityEvent.setAccessibilityDataSensitive(z);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ContentChangeType {
    }

    public static int a(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getContentChangeTypes();
    }

    public static void b(AccessibilityEvent accessibilityEvent, int i2) {
        accessibilityEvent.setContentChangeTypes(i2);
    }
}
