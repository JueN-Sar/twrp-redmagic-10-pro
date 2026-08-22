package com.google.android.material.shape;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ShapeableDelegateV33 extends ShapeableDelegate {
    ShapeableDelegateV33(View view) {
        l(view);
    }

    @DoNotInline
    private void l(View view) {
        view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.google.android.material.shape.ShapeableDelegateV33.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                if (ShapeableDelegateV33.this.f15222e.isEmpty()) {
                    return;
                }
                outline.setPath(ShapeableDelegateV33.this.f15222e);
            }
        });
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    void b(View view) {
        view.setClipToOutline(!j());
        if (j()) {
            view.invalidate();
        } else {
            view.invalidateOutline();
        }
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    boolean j() {
        return this.f15218a;
    }
}
