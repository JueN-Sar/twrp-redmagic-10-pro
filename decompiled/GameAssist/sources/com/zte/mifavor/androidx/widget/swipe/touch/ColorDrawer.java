package com.zte.mifavor.androidx.widget.swipe.touch;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes2.dex */
class ColorDrawer {

    /* renamed from: a, reason: collision with root package name */
    private final Drawable f17239a;

    /* renamed from: b, reason: collision with root package name */
    private final int f17240b;

    /* renamed from: c, reason: collision with root package name */
    private final int f17241c;

    public void a(View view, Canvas canvas) {
        int left = view.getLeft() - this.f17240b;
        int bottom = view.getBottom();
        this.f17239a.setBounds(left, bottom, view.getRight() + this.f17240b, this.f17241c + bottom);
        this.f17239a.draw(canvas);
    }

    public void b(View view, Canvas canvas) {
        int left = view.getLeft() - this.f17240b;
        this.f17239a.setBounds(left, view.getTop() - this.f17241c, this.f17240b + left, view.getBottom() + this.f17241c);
        this.f17239a.draw(canvas);
    }

    public void c(View view, Canvas canvas) {
        int right = view.getRight();
        this.f17239a.setBounds(right, view.getTop() - this.f17241c, this.f17240b + right, view.getBottom() + this.f17241c);
        this.f17239a.draw(canvas);
    }

    public void d(View view, Canvas canvas) {
        int left = view.getLeft() - this.f17240b;
        int top = view.getTop() - this.f17241c;
        this.f17239a.setBounds(left, top, view.getRight() + this.f17240b, this.f17241c + top);
        this.f17239a.draw(canvas);
    }
}
