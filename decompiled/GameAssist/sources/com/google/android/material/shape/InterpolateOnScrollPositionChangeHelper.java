package com.google.android.material.shape;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/* loaded from: classes.dex */
public class InterpolateOnScrollPositionChangeHelper {

    /* renamed from: a, reason: collision with root package name */
    private View f15087a;

    /* renamed from: b, reason: collision with root package name */
    private MaterialShapeDrawable f15088b;

    /* renamed from: c, reason: collision with root package name */
    private ScrollView f15089c;

    /* renamed from: d, reason: collision with root package name */
    private final int[] f15090d;

    /* renamed from: e, reason: collision with root package name */
    private final int[] f15091e;

    /* renamed from: com.google.android.material.shape.InterpolateOnScrollPositionChangeHelper$1, reason: invalid class name */
    class AnonymousClass1 implements ViewTreeObserver.OnScrollChangedListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ InterpolateOnScrollPositionChangeHelper f15092a;

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            this.f15092a.a();
        }
    }

    public void a() {
        ScrollView scrollView = this.f15089c;
        if (scrollView == null) {
            return;
        }
        if (scrollView.getChildCount() == 0) {
            throw new IllegalStateException("Scroll bar must contain a child to calculate interpolation.");
        }
        this.f15089c.getLocationInWindow(this.f15090d);
        this.f15089c.getChildAt(0).getLocationInWindow(this.f15091e);
        int top = (this.f15087a.getTop() - this.f15090d[1]) + this.f15091e[1];
        int height = this.f15087a.getHeight();
        int height2 = this.f15089c.getHeight();
        if (top < 0) {
            this.f15088b.b0(Math.max(0.0f, Math.min(1.0f, (top / height) + 1.0f)));
            this.f15087a.invalidate();
            return;
        }
        if (top + height > height2) {
            this.f15088b.b0(Math.max(0.0f, Math.min(1.0f, 1.0f - ((r0 - height2) / height))));
            this.f15087a.invalidate();
        } else if (this.f15088b.y() != 1.0f) {
            this.f15088b.b0(1.0f);
            this.f15087a.invalidate();
        }
    }
}
