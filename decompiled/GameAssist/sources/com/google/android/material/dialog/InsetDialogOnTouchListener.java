package com.google.android.material.dialog;

import android.R;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class InsetDialogOnTouchListener implements View.OnTouchListener {

    /* renamed from: c, reason: collision with root package name */
    private final Dialog f14552c;

    /* renamed from: h, reason: collision with root package name */
    private final int f14553h;

    /* renamed from: i, reason: collision with root package name */
    private final int f14554i;

    /* renamed from: j, reason: collision with root package name */
    private final int f14555j;

    public InsetDialogOnTouchListener(Dialog dialog, Rect rect) {
        this.f14552c = dialog;
        this.f14553h = rect.left;
        this.f14554i = rect.top;
        this.f14555j = ViewConfiguration.get(dialog.getContext()).getScaledWindowTouchSlop();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View findViewById = view.findViewById(R.id.content);
        int left = this.f14553h + findViewById.getLeft();
        int width = findViewById.getWidth() + left;
        if (new RectF(left, this.f14554i + findViewById.getTop(), width, findViewById.getHeight() + r3).contains(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        if (motionEvent.getAction() == 1) {
            obtain.setAction(4);
        }
        view.performClick();
        return this.f14552c.onTouchEvent(obtain);
    }
}
