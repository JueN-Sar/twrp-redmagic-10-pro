package com.google.mlkit.vision.common;

import android.media.Image;

/* loaded from: classes.dex */
final class zzb {

    /* renamed from: a, reason: collision with root package name */
    private final Image f16089a;

    zzb(Image image) {
        this.f16089a = image;
    }

    final Image a() {
        return this.f16089a;
    }

    final Image.Plane[] b() {
        return this.f16089a.getPlanes();
    }
}
