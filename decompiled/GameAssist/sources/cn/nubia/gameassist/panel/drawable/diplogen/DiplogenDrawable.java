package cn.nubia.gameassist.panel.drawable.diplogen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.Pair;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.theme.ThemeWidget;
import com.zte.gameassist.common.FoldMgr;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class DiplogenDrawable extends Drawable implements ThemeWidget {

    /* renamed from: i, reason: collision with root package name */
    protected final int f6849i;

    /* renamed from: j, reason: collision with root package name */
    protected final Context f6850j;

    /* renamed from: l, reason: collision with root package name */
    protected Theme f6852l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f6853m;

    /* renamed from: n, reason: collision with root package name */
    protected ColorFilter f6854n;

    /* renamed from: c, reason: collision with root package name */
    private final Map f6847c = new ArrayMap();

    /* renamed from: h, reason: collision with root package name */
    protected final Point f6848h = new Point();

    /* renamed from: k, reason: collision with root package name */
    protected int f6851k = 1;

    public DiplogenDrawable(Context context, int i2) {
        this.f6850j = context;
        this.f6849i = i2;
    }

    protected static boolean e() {
        return FoldMgr.f() && FoldMgr.c().e();
    }

    public DiplogenDrawable a(boolean z) {
        boolean z2 = this.f6853m;
        if (!z2 && z) {
            this.f6853m = true;
            ThemeController.m().h(this);
        } else if (z2 && !z) {
            this.f6853m = false;
            ThemeController.m().p(this);
        }
        return this;
    }

    protected Bitmap b(int i2) {
        Drawable drawable = this.f6850j.getResources().getDrawable(i2);
        drawable.setCallback(getCallback());
        if (!(drawable instanceof BitmapDrawable)) {
            return null;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        this.f6847c.put(Integer.valueOf(i2), new Pair(drawable, bitmapDrawable.getBitmap()));
        return bitmapDrawable.getBitmap();
    }

    public Drawable c(int i2) {
        Drawable drawable = this.f6850j.getResources().getDrawable(i2, null);
        drawable.setCallback(getCallback());
        if (drawable instanceof BitmapDrawable) {
            this.f6847c.put(Integer.valueOf(i2), new Pair(drawable, ((BitmapDrawable) drawable).getBitmap()));
        }
        return drawable;
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        this.f6852l = theme;
        if (theme.h()) {
            return;
        }
        this.f6847c.clear();
    }

    abstract void f(boolean z);

    abstract void g(Rect rect);

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f6848h.set(rect.centerX(), rect.centerY());
        g(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f6851k = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f6854n = colorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (visible) {
            f(z);
        }
        return visible;
    }
}
