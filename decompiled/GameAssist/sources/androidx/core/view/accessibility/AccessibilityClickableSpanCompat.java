package androidx.core.view.accessibility;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* loaded from: classes.dex */
public final class AccessibilityClickableSpanCompat extends ClickableSpan {

    /* renamed from: c, reason: collision with root package name */
    private final int f3474c;

    /* renamed from: h, reason: collision with root package name */
    private final AccessibilityNodeInfoCompat f3475h;

    /* renamed from: i, reason: collision with root package name */
    private final int f3476i;

    public AccessibilityClickableSpanCompat(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i3) {
        this.f3474c = i2;
        this.f3475h = accessibilityNodeInfoCompat;
        this.f3476i = i3;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f3474c);
        this.f3475h.Y(this.f3476i, bundle);
    }
}
