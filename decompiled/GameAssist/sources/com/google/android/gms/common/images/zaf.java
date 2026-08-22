package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Objects;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class zaf extends zag {

    /* renamed from: c, reason: collision with root package name */
    private final WeakReference f10946c;

    @Override // com.google.android.gms.common.images.zag
    protected final void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageManager.OnImageLoadedListener onImageLoadedListener;
        if (z2 || (onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.f10946c.get()) == null) {
            return;
        }
        onImageLoadedListener.a(this.f10947a.f10944a, drawable, z3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zaf)) {
            return false;
        }
        zaf zafVar = (zaf) obj;
        ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.f10946c.get();
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zafVar.f10946c.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && Objects.a(onImageLoadedListener2, onImageLoadedListener) && Objects.a(zafVar.f10947a, this.f10947a);
    }

    public final int hashCode() {
        return Objects.b(this.f10947a);
    }
}
