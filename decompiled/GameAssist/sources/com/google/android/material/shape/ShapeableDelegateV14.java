package com.google.android.material.shape;

import android.view.View;

/* loaded from: classes.dex */
class ShapeableDelegateV14 extends ShapeableDelegate {
    @Override // com.google.android.material.shape.ShapeableDelegate
    void b(View view) {
        if (this.f15220c == null || this.f15221d.isEmpty() || !j()) {
            return;
        }
        view.invalidate();
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    boolean j() {
        return true;
    }
}
