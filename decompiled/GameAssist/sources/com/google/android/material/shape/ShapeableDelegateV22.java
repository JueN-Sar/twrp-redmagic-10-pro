package com.google.android.material.shape;

import android.graphics.Outline;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;

@RequiresApi
/* loaded from: classes.dex */
class ShapeableDelegateV22 extends ShapeableDelegate {

    /* renamed from: f, reason: collision with root package name */
    private boolean f15223f;

    /* renamed from: g, reason: collision with root package name */
    private float f15224g;

    private float m() {
        RectF rectF;
        ShapeAppearanceModel shapeAppearanceModel = this.f15220c;
        if (shapeAppearanceModel == null || (rectF = this.f15221d) == null) {
            return 0.0f;
        }
        return shapeAppearanceModel.f15139f.a(rectF);
    }

    @DoNotInline
    private void n(View view) {
        view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.google.android.material.shape.ShapeableDelegateV22.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                ShapeableDelegateV22 shapeableDelegateV22 = ShapeableDelegateV22.this;
                if (shapeableDelegateV22.f15220c == null || shapeableDelegateV22.f15221d.isEmpty()) {
                    return;
                }
                ShapeableDelegateV22 shapeableDelegateV222 = ShapeableDelegateV22.this;
                RectF rectF = shapeableDelegateV222.f15221d;
                outline.setRoundRect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom, shapeableDelegateV222.f15224g);
            }
        });
    }

    private boolean o() {
        ShapeAppearanceModel shapeAppearanceModel;
        if (this.f15221d.isEmpty() || (shapeAppearanceModel = this.f15220c) == null) {
            return false;
        }
        return shapeAppearanceModel.u(this.f15221d);
    }

    private boolean p() {
        ShapeAppearanceModel shapeAppearanceModel;
        if (!this.f15221d.isEmpty() && (shapeAppearanceModel = this.f15220c) != null && this.f15219b && !shapeAppearanceModel.u(this.f15221d) && q(this.f15220c)) {
            float a2 = this.f15220c.r().a(this.f15221d);
            float a3 = this.f15220c.t().a(this.f15221d);
            float a4 = this.f15220c.j().a(this.f15221d);
            float a5 = this.f15220c.l().a(this.f15221d);
            if (a2 == 0.0f && a4 == 0.0f && a3 == a5) {
                RectF rectF = this.f15221d;
                rectF.set(rectF.left - a3, rectF.top, rectF.right, rectF.bottom);
                this.f15224g = a3;
                return true;
            }
            if (a2 == 0.0f && a3 == 0.0f && a4 == a5) {
                RectF rectF2 = this.f15221d;
                rectF2.set(rectF2.left, rectF2.top - a4, rectF2.right, rectF2.bottom);
                this.f15224g = a4;
                return true;
            }
            if (a3 == 0.0f && a5 == 0.0f && a2 == a4) {
                RectF rectF3 = this.f15221d;
                rectF3.set(rectF3.left, rectF3.top, rectF3.right + a2, rectF3.bottom);
                this.f15224g = a2;
                return true;
            }
            if (a4 == 0.0f && a5 == 0.0f && a2 == a3) {
                RectF rectF4 = this.f15221d;
                rectF4.set(rectF4.left, rectF4.top, rectF4.right, rectF4.bottom + a2);
                this.f15224g = a2;
                return true;
            }
        }
        return false;
    }

    private static boolean q(ShapeAppearanceModel shapeAppearanceModel) {
        return (shapeAppearanceModel.q() instanceof RoundedCornerTreatment) && (shapeAppearanceModel.s() instanceof RoundedCornerTreatment) && (shapeAppearanceModel.i() instanceof RoundedCornerTreatment) && (shapeAppearanceModel.k() instanceof RoundedCornerTreatment);
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    void b(View view) {
        this.f15224g = m();
        this.f15223f = o() || p();
        view.setClipToOutline(!j());
        if (j()) {
            view.invalidate();
        } else {
            view.invalidateOutline();
        }
    }

    @VisibleForTesting
    float getCornerRadius() {
        return this.f15224g;
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    boolean j() {
        return !this.f15223f || this.f15218a;
    }
}
