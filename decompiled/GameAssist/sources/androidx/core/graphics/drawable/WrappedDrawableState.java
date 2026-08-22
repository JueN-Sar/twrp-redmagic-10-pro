package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
final class WrappedDrawableState extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    int f3003a;

    /* renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f3004b;

    /* renamed from: c, reason: collision with root package name */
    ColorStateList f3005c;

    /* renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f3006d;

    WrappedDrawableState(WrappedDrawableState wrappedDrawableState) {
        this.f3005c = null;
        this.f3006d = WrappedDrawableApi14.f2995m;
        if (wrappedDrawableState != null) {
            this.f3003a = wrappedDrawableState.f3003a;
            this.f3004b = wrappedDrawableState.f3004b;
            this.f3005c = wrappedDrawableState.f3005c;
            this.f3006d = wrappedDrawableState.f3006d;
        }
    }

    boolean a() {
        return this.f3004b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i2 = this.f3003a;
        Drawable.ConstantState constantState = this.f3004b;
        return (constantState != null ? constantState.getChangingConfigurations() : 0) | i2;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return new WrappedDrawableApi21(this, resources);
    }
}
