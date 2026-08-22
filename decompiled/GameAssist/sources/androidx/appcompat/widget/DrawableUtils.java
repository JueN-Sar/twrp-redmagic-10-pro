package androidx.appcompat.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RestrictTo
/* loaded from: classes.dex */
public class DrawableUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f870a = {R.attr.state_checked};

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f871b = new int[0];

    /* renamed from: c, reason: collision with root package name */
    public static final Rect f872c = new Rect();

    static class Api18Impl {

        /* renamed from: a, reason: collision with root package name */
        private static final boolean f873a;

        /* renamed from: b, reason: collision with root package name */
        private static final Method f874b;

        /* renamed from: c, reason: collision with root package name */
        private static final Field f875c;

        /* renamed from: d, reason: collision with root package name */
        private static final Field f876d;

        /* renamed from: e, reason: collision with root package name */
        private static final Field f877e;

        /* renamed from: f, reason: collision with root package name */
        private static final Field f878f;

        /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
        static {
            /*
                r0 = 1
                r1 = 0
                r2 = 0
                java.lang.String r3 = "android.graphics.Insets"
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.NoSuchFieldException -> L3d java.lang.ClassNotFoundException -> L40 java.lang.NoSuchMethodException -> L43
                java.lang.Class<android.graphics.drawable.Drawable> r4 = android.graphics.drawable.Drawable.class
                java.lang.String r5 = "getOpticalInsets"
                java.lang.reflect.Method r4 = r4.getMethod(r5, r1)     // Catch: java.lang.NoSuchFieldException -> L3d java.lang.ClassNotFoundException -> L40 java.lang.NoSuchMethodException -> L43
                java.lang.String r5 = "left"
                java.lang.reflect.Field r5 = r3.getField(r5)     // Catch: java.lang.NoSuchFieldException -> L34 java.lang.ClassNotFoundException -> L37 java.lang.NoSuchMethodException -> L3a
                java.lang.String r6 = "top"
                java.lang.reflect.Field r6 = r3.getField(r6)     // Catch: java.lang.NoSuchFieldException -> L2d java.lang.ClassNotFoundException -> L30 java.lang.NoSuchMethodException -> L32
                java.lang.String r7 = "right"
                java.lang.reflect.Field r7 = r3.getField(r7)     // Catch: java.lang.Throwable -> L2b
                java.lang.String r8 = "bottom"
                java.lang.reflect.Field r3 = r3.getField(r8)     // Catch: java.lang.Throwable -> L46
                r8 = r0
                goto L48
            L2b:
                r7 = r1
                goto L46
            L2d:
                r6 = r1
            L2e:
                r7 = r6
                goto L46
            L30:
                r6 = r1
                goto L2e
            L32:
                r6 = r1
                goto L2e
            L34:
                r5 = r1
            L35:
                r6 = r5
                goto L2e
            L37:
                r5 = r1
            L38:
                r6 = r5
                goto L2e
            L3a:
                r5 = r1
            L3b:
                r6 = r5
                goto L2e
            L3d:
                r4 = r1
                r5 = r4
                goto L35
            L40:
                r4 = r1
                r5 = r4
                goto L38
            L43:
                r4 = r1
                r5 = r4
                goto L3b
            L46:
                r3 = r1
                r8 = r2
            L48:
                if (r8 == 0) goto L57
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f874b = r4
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f875c = r5
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f876d = r6
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f877e = r7
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f878f = r3
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f873a = r0
                goto L63
            L57:
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f874b = r1
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f875c = r1
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f876d = r1
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f877e = r1
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f878f = r1
                androidx.appcompat.widget.DrawableUtils.Api18Impl.f873a = r2
            L63:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.DrawableUtils.Api18Impl.<clinit>():void");
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static Insets a(Drawable drawable) {
            return drawable.getOpticalInsets();
        }
    }

    public static boolean a(Drawable drawable) {
        return true;
    }

    static void b(Drawable drawable) {
        drawable.getClass();
    }

    public static Rect c(Drawable drawable) {
        Insets a2 = Api29Impl.a(drawable);
        return new Rect(a2.left, a2.top, a2.right, a2.bottom);
    }

    public static PorterDuff.Mode d(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
