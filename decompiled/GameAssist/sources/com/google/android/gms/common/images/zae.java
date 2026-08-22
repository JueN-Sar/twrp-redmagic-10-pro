package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class zae extends zag {

    /* renamed from: c, reason: collision with root package name */
    private final WeakReference f10945c;

    @Override // com.google.android.gms.common.images.zag
    protected final void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageView imageView = (ImageView) this.f10945c.get();
        if (imageView != null) {
            if (!z2 && !z3 && (imageView instanceof zal)) {
                throw null;
            }
            boolean z4 = false;
            if (!z2 && !z) {
                z4 = true;
            }
            if (z4) {
                Drawable drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof zak) {
                    drawable2 = ((zak) drawable2).a();
                }
                drawable = new zak(drawable2, drawable);
            }
            imageView.setImageDrawable(drawable);
            if (imageView instanceof zal) {
                throw null;
            }
            if (drawable == null || !z4) {
                return;
            }
            ((zak) drawable).b(250);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zae)) {
            return false;
        }
        ImageView imageView = (ImageView) this.f10945c.get();
        ImageView imageView2 = (ImageView) ((zae) obj).f10945c.get();
        return (imageView2 == null || imageView == null || !Objects.a(imageView2, imageView)) ? false : true;
    }

    public final int hashCode() {
        return 0;
    }
}
