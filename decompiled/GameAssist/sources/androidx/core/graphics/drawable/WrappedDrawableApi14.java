package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
class WrappedDrawableApi14 extends Drawable implements Drawable.Callback, WrappedDrawable, TintAwareDrawable {

    /* renamed from: m, reason: collision with root package name */
    static final PorterDuff.Mode f2995m = PorterDuff.Mode.SRC_IN;

    /* renamed from: c, reason: collision with root package name */
    private int f2996c;

    /* renamed from: h, reason: collision with root package name */
    private PorterDuff.Mode f2997h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f2998i;

    /* renamed from: j, reason: collision with root package name */
    WrappedDrawableState f2999j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f3000k;

    /* renamed from: l, reason: collision with root package name */
    Drawable f3001l;

    WrappedDrawableApi14(WrappedDrawableState wrappedDrawableState, Resources resources) {
        this.f2999j = wrappedDrawableState;
        e(resources);
    }

    private WrappedDrawableState d() {
        return new WrappedDrawableState(this.f2999j);
    }

    private void e(Resources resources) {
        Drawable.ConstantState constantState;
        WrappedDrawableState wrappedDrawableState = this.f2999j;
        if (wrappedDrawableState == null || (constantState = wrappedDrawableState.f3004b) == null) {
            return;
        }
        b(constantState.newDrawable(resources));
    }

    private boolean f(int[] iArr) {
        if (!c()) {
            return false;
        }
        WrappedDrawableState wrappedDrawableState = this.f2999j;
        ColorStateList colorStateList = wrappedDrawableState.f3005c;
        PorterDuff.Mode mode = wrappedDrawableState.f3006d;
        if (colorStateList == null || mode == null) {
            this.f2998i = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.f2998i || colorForState != this.f2996c || mode != this.f2997h) {
                setColorFilter(colorForState, mode);
                this.f2996c = colorForState;
                this.f2997h = mode;
                this.f2998i = true;
                return true;
            }
        }
        return false;
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawable
    public final Drawable a() {
        return this.f3001l;
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawable
    public final void b(Drawable drawable) {
        Drawable drawable2 = this.f3001l;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f3001l = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            WrappedDrawableState wrappedDrawableState = this.f2999j;
            if (wrappedDrawableState != null) {
                wrappedDrawableState.f3004b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    protected boolean c() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f3001l.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        WrappedDrawableState wrappedDrawableState = this.f2999j;
        return this.f3001l.getChangingConfigurations() | changingConfigurations | (wrappedDrawableState != null ? wrappedDrawableState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        WrappedDrawableState wrappedDrawableState = this.f2999j;
        if (wrappedDrawableState == null || !wrappedDrawableState.a()) {
            return null;
        }
        this.f2999j.f3003a = getChangingConfigurations();
        return this.f2999j;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f3001l.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f3001l.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f3001l.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getLayoutDirection() {
        return DrawableCompat.f(this.f3001l);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.f3001l.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.f3001l.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3001l.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f3001l.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.f3001l.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.f3001l.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return DrawableCompat.h(this.f3001l);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        WrappedDrawableState wrappedDrawableState;
        ColorStateList colorStateList = (!c() || (wrappedDrawableState = this.f2999j) == null) ? null : wrappedDrawableState.f3005c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f3001l.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.f3001l.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f3000k && super.mutate() == this) {
            this.f2999j = d();
            Drawable drawable = this.f3001l;
            if (drawable != null) {
                drawable.mutate();
            }
            WrappedDrawableState wrappedDrawableState = this.f2999j;
            if (wrappedDrawableState != null) {
                Drawable drawable2 = this.f3001l;
                wrappedDrawableState.f3004b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f3000k = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f3001l;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        return DrawableCompat.m(this.f3001l, i2);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        return this.f3001l.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        scheduleSelf(runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f3001l.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        DrawableCompat.j(this.f3001l, z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i2) {
        this.f3001l.setChangingConfigurations(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3001l.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f3001l.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f3001l.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return f(iArr) || this.f3001l.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f2999j.f3005c = colorStateList;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f2999j.f3006d = mode;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f3001l.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
