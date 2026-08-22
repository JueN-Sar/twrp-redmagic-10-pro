package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ViewOverlayApi18 implements ViewOverlayImpl {

    /* renamed from: a, reason: collision with root package name */
    private final ViewOverlay f14794a;

    ViewOverlayApi18(View view) {
        this.f14794a = view.getOverlay();
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void a(Drawable drawable) {
        this.f14794a.add(drawable);
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void b(Drawable drawable) {
        this.f14794a.remove(drawable);
    }
}
