package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.ViewGroupOverlay;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ViewGroupOverlayApi18 implements ViewGroupOverlayImpl {

    /* renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f14792a;

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void a(Drawable drawable) {
        this.f14792a.add(drawable);
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void b(Drawable drawable) {
        this.f14792a.remove(drawable);
    }
}
