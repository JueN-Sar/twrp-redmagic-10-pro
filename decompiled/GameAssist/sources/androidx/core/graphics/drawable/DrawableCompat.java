package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public final class DrawableCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static void a(Drawable drawable, Resources.Theme theme) {
            drawable.applyTheme(theme);
        }

        @DoNotInline
        static boolean b(Drawable drawable) {
            return drawable.canApplyTheme();
        }

        @DoNotInline
        static ColorFilter c(Drawable drawable) {
            return drawable.getColorFilter();
        }

        @DoNotInline
        static void d(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        }

        @DoNotInline
        static void e(Drawable drawable, float f2, float f3) {
            drawable.setHotspot(f2, f3);
        }

        @DoNotInline
        static void f(Drawable drawable, int i2, int i3, int i4, int i5) {
            drawable.setHotspotBounds(i2, i3, i4, i5);
        }

        @DoNotInline
        static void g(Drawable drawable, int i2) {
            drawable.setTint(i2);
        }

        @DoNotInline
        static void h(Drawable drawable, ColorStateList colorStateList) {
            drawable.setTintList(colorStateList);
        }

        @DoNotInline
        static void i(Drawable drawable, PorterDuff.Mode mode) {
            drawable.setTintMode(mode);
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static int a(Drawable drawable) {
            return drawable.getLayoutDirection();
        }

        @DoNotInline
        static boolean b(Drawable drawable, int i2) {
            return drawable.setLayoutDirection(i2);
        }
    }

    public static void a(Drawable drawable, Resources.Theme theme) {
        Api21Impl.a(drawable, theme);
    }

    public static boolean b(Drawable drawable) {
        return Api21Impl.b(drawable);
    }

    public static void c(Drawable drawable) {
        drawable.clearColorFilter();
    }

    public static int d(Drawable drawable) {
        return drawable.getAlpha();
    }

    public static ColorFilter e(Drawable drawable) {
        return Api21Impl.c(drawable);
    }

    public static int f(Drawable drawable) {
        return Api23Impl.a(drawable);
    }

    public static void g(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Api21Impl.d(drawable, resources, xmlPullParser, attributeSet, theme);
    }

    public static boolean h(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static void i(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void j(Drawable drawable, boolean z) {
        drawable.setAutoMirrored(z);
    }

    public static void k(Drawable drawable, float f2, float f3) {
        Api21Impl.e(drawable, f2, f3);
    }

    public static void l(Drawable drawable, int i2, int i3, int i4, int i5) {
        Api21Impl.f(drawable, i2, i3, i4, i5);
    }

    public static boolean m(Drawable drawable, int i2) {
        return Api23Impl.b(drawable, i2);
    }

    public static void n(Drawable drawable, int i2) {
        Api21Impl.g(drawable, i2);
    }

    public static void o(Drawable drawable, ColorStateList colorStateList) {
        Api21Impl.h(drawable, colorStateList);
    }

    public static void p(Drawable drawable, PorterDuff.Mode mode) {
        Api21Impl.i(drawable, mode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable q(Drawable drawable) {
        return drawable instanceof WrappedDrawable ? ((WrappedDrawable) drawable).a() : drawable;
    }

    public static Drawable r(Drawable drawable) {
        return drawable;
    }
}
