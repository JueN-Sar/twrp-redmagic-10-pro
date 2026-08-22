package androidx.core.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

/* loaded from: classes.dex */
public class AccessibilityRecordCompat {

    /* renamed from: a, reason: collision with root package name */
    private final AccessibilityRecord f3506a;

    public static void a(AccessibilityRecord accessibilityRecord, int i2) {
        accessibilityRecord.setMaxScrollX(i2);
    }

    public static void b(AccessibilityRecord accessibilityRecord, int i2) {
        accessibilityRecord.setMaxScrollY(i2);
    }

    public static void c(AccessibilityRecord accessibilityRecord, View view, int i2) {
        accessibilityRecord.setSource(view, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityRecordCompat)) {
            return false;
        }
        AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
        AccessibilityRecord accessibilityRecord = this.f3506a;
        return accessibilityRecord == null ? accessibilityRecordCompat.f3506a == null : accessibilityRecord.equals(accessibilityRecordCompat.f3506a);
    }

    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.f3506a;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }
}
