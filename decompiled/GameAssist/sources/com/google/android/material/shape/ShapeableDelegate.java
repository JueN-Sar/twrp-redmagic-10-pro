package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import androidx.annotation.RestrictTo;
import com.google.android.material.canvas.CanvasCompat;

@RestrictTo
/* loaded from: classes.dex */
public abstract class ShapeableDelegate {

    /* renamed from: c, reason: collision with root package name */
    ShapeAppearanceModel f15220c;

    /* renamed from: a, reason: collision with root package name */
    boolean f15218a = false;

    /* renamed from: b, reason: collision with root package name */
    boolean f15219b = false;

    /* renamed from: d, reason: collision with root package name */
    RectF f15221d = new RectF();

    /* renamed from: e, reason: collision with root package name */
    final Path f15222e = new Path();

    public static ShapeableDelegate a(View view) {
        return new ShapeableDelegateV33(view);
    }

    private boolean d() {
        RectF rectF = this.f15221d;
        return rectF.left <= rectF.right && rectF.top <= rectF.bottom;
    }

    private void k() {
        if (!d() || this.f15220c == null) {
            return;
        }
        ShapeAppearancePathProvider.k().d(this.f15220c, 1.0f, this.f15221d, this.f15222e);
    }

    abstract void b(View view);

    public boolean c() {
        return this.f15218a;
    }

    public void e(Canvas canvas, CanvasCompat.CanvasOperation canvasOperation) {
        if (!j() || this.f15222e.isEmpty()) {
            canvasOperation.a(canvas);
            return;
        }
        canvas.save();
        canvas.clipPath(this.f15222e);
        canvasOperation.a(canvas);
        canvas.restore();
    }

    public void f(View view, RectF rectF) {
        this.f15221d = rectF;
        k();
        b(view);
    }

    public void g(View view, ShapeAppearanceModel shapeAppearanceModel) {
        this.f15220c = shapeAppearanceModel;
        k();
        b(view);
    }

    public void h(View view, boolean z) {
        if (z != this.f15218a) {
            this.f15218a = z;
            b(view);
        }
    }

    public void i(View view, boolean z) {
        this.f15219b = z;
        b(view);
    }

    abstract boolean j();
}
