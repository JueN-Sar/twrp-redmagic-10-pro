package cn.nubia.gameassist.theme;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes.dex */
public class ThemeDrawable extends Drawable implements ThemeWidget {

    /* renamed from: c, reason: collision with root package name */
    protected Drawable f7493c;

    /* renamed from: h, reason: collision with root package name */
    protected Drawable f7494h;

    /* renamed from: i, reason: collision with root package name */
    protected ColorFilter f7495i;

    /* renamed from: j, reason: collision with root package name */
    protected int f7496j = 255;

    /* renamed from: k, reason: collision with root package name */
    protected Theme f7497k;

    public void d(Theme theme) {
        if (theme != null) {
            this.f7497k = theme;
            setColorFilter(theme.f7435b);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable;
        int i2 = this.f7496j;
        View view = (View) getCallback();
        if (this.f7497k != null && view != null && view.isSelected() && (drawable = this.f7494h) != null) {
            int i3 = 255 - ((int) (this.f7497k.f7449p * 255.0f));
            drawable.setAlpha(i3);
            this.f7494h.setColorFilter(this.f7495i);
            this.f7494h.setBounds(getBounds());
            this.f7494h.draw(canvas);
            i2 = 255 - i3;
        }
        Drawable drawable2 = this.f7493c;
        if (drawable2 != null) {
            drawable2.setBounds(getBounds());
            this.f7493c.setAlpha(i2);
            this.f7493c.setColorFilter(this.f7495i);
            this.f7493c.draw(canvas);
            this.f7493c.clearColorFilter();
            this.f7493c.setAlpha(255);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f7496j = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f7495i = colorFilter;
        invalidateSelf();
    }
}
