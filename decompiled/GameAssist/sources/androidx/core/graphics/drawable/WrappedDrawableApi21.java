package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

@RequiresApi
/* loaded from: classes.dex */
class WrappedDrawableApi21 extends WrappedDrawableApi14 {

    /* renamed from: n, reason: collision with root package name */
    private static Method f3002n;

    WrappedDrawableApi21(WrappedDrawableState wrappedDrawableState, Resources resources) {
        super(wrappedDrawableState, resources);
        g();
    }

    private void g() {
        if (f3002n == null) {
            try {
                f3002n = Drawable.class.getDeclaredMethod("isProjected", null);
            } catch (Exception e2) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", e2);
            }
        }
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14
    protected boolean c() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.f3001l.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.f3001l.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        Drawable drawable = this.f3001l;
        if (drawable == null || (method = f3002n) == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke(drawable, null)).booleanValue();
        } catch (Exception e2) {
            Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", e2);
            return false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        this.f3001l.setHotspot(f2, f3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        this.f3001l.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public void setTint(int i2) {
        if (c()) {
            super.setTint(i2);
        } else {
            this.f3001l.setTint(i2);
        }
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (c()) {
            super.setTintList(colorStateList);
        } else {
            this.f3001l.setTintList(colorStateList);
        }
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (c()) {
            super.setTintMode(mode);
        } else {
            this.f3001l.setTintMode(mode);
        }
    }
}
