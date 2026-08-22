package com.google.android.material.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
public final class DrawableUtils {

    @RequiresApi
    private static class OutlineCompatL {
        @DoNotInline
        static void a(@NonNull Outline outline, @NonNull Path path) {
            outline.setConvexPath(path);
        }
    }

    @RequiresApi
    private static class OutlineCompatR {
        @DoNotInline
        static void a(@NonNull Outline outline, @NonNull Path path) {
            outline.setPath(path);
        }
    }

    public static Drawable a(Drawable drawable, Drawable drawable2) {
        return b(drawable, drawable2, -1, -1);
    }

    public static Drawable b(Drawable drawable, Drawable drawable2, int i2, int i3) {
        if (drawable == null) {
            return drawable2;
        }
        if (drawable2 == null) {
            return drawable;
        }
        if (i2 == -1) {
            i2 = i(drawable, drawable2);
        }
        if (i3 == -1) {
            i3 = h(drawable, drawable2);
        }
        if (i2 > drawable.getIntrinsicWidth() || i3 > drawable.getIntrinsicHeight()) {
            float f2 = i2 / i3;
            if (f2 >= drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                i3 = (int) (intrinsicWidth / f2);
                i2 = intrinsicWidth;
            } else {
                i3 = drawable.getIntrinsicHeight();
                i2 = (int) (f2 * i3);
            }
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerSize(1, i2, i3);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    public static Drawable c(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        return e(drawable, colorStateList, mode, false);
    }

    public static Drawable d(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        return e(drawable, colorStateList, mode, false);
    }

    private static Drawable e(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = DrawableCompat.r(drawable).mutate();
            if (mode != null) {
                DrawableCompat.p(drawable, mode);
            }
        } else if (z) {
            drawable.mutate();
        }
        return drawable;
    }

    public static int[] f(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 == 16842912) {
                return iArr;
            }
            if (i3 == 0) {
                int[] iArr2 = (int[]) iArr.clone();
                iArr2[i2] = 16842912;
                return iArr2;
            }
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[iArr.length] = 16842912;
        return copyOf;
    }

    public static ColorStateList g(Drawable drawable) {
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (drawable instanceof ColorStateListDrawable) {
            return ((ColorStateListDrawable) drawable).getColorStateList();
        }
        return null;
    }

    private static int h(Drawable drawable, Drawable drawable2) {
        int intrinsicHeight = drawable2.getIntrinsicHeight();
        return intrinsicHeight != -1 ? intrinsicHeight : drawable.getIntrinsicHeight();
    }

    private static int i(Drawable drawable, Drawable drawable2) {
        int intrinsicWidth = drawable2.getIntrinsicWidth();
        return intrinsicWidth != -1 ? intrinsicWidth : drawable.getIntrinsicWidth();
    }

    public static int[] j(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 != 16842912) {
                iArr2[i2] = i3;
                i2++;
            }
        }
        return iArr2;
    }

    public static AttributeSet k(Context context, int i2, CharSequence charSequence) {
        int next;
        try {
            XmlResourceParser xml = context.getResources().getXml(i2);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            if (TextUtils.equals(xml.getName(), charSequence)) {
                return Xml.asAttributeSet(xml);
            }
            throw new XmlPullParserException("Must have a <" + ((Object) charSequence) + "> start tag");
        } catch (IOException | XmlPullParserException e2) {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i2));
            notFoundException.initCause(e2);
            throw notFoundException;
        }
    }

    public static void l(Outline outline, Path path) {
        OutlineCompatR.a(outline, path);
    }

    public static void m(RippleDrawable rippleDrawable, int i2) {
        rippleDrawable.setRadius(i2);
    }

    public static void n(Drawable drawable, int i2) {
        if (i2 != 0) {
            DrawableCompat.n(drawable, i2);
        } else {
            DrawableCompat.o(drawable, null);
        }
    }

    public static PorterDuffColorFilter o(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
    }
}
