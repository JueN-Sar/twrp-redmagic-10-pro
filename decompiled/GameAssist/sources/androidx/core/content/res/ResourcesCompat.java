package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class ResourcesCompat {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal f2890a = new ThreadLocal();

    /* renamed from: b, reason: collision with root package name */
    private static final WeakHashMap f2891b = new WeakHashMap(0);

    /* renamed from: c, reason: collision with root package name */
    private static final Object f2892c = new Object();

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static Drawable a(Resources resources, int i2, Resources.Theme theme) {
            return resources.getDrawable(i2, theme);
        }

        @DoNotInline
        static Drawable b(Resources resources, int i2, int i3, Resources.Theme theme) {
            return resources.getDrawableForDensity(i2, i3, theme);
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static int a(Resources resources, int i2, Resources.Theme theme) {
            return resources.getColor(i2, theme);
        }

        @NonNull
        @DoNotInline
        static ColorStateList b(@NonNull Resources resources, @ColorRes int i2, @Nullable Resources.Theme theme) {
            return resources.getColorStateList(i2, theme);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static float a(@NonNull Resources resources, @DimenRes int i2) {
            return resources.getFloat(i2);
        }
    }

    private static class ColorStateListCacheEntry {

        /* renamed from: a, reason: collision with root package name */
        final ColorStateList f2893a;

        /* renamed from: b, reason: collision with root package name */
        final Configuration f2894b;

        /* renamed from: c, reason: collision with root package name */
        final int f2895c;

        ColorStateListCacheEntry(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
            this.f2893a = colorStateList;
            this.f2894b = configuration;
            this.f2895c = theme == null ? 0 : theme.hashCode();
        }
    }

    private static final class ColorStateListCacheKey {

        /* renamed from: a, reason: collision with root package name */
        final Resources f2896a;

        /* renamed from: b, reason: collision with root package name */
        final Resources.Theme f2897b;

        ColorStateListCacheKey(Resources resources, Resources.Theme theme) {
            this.f2896a = resources;
            this.f2897b = theme;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ColorStateListCacheKey.class != obj.getClass()) {
                return false;
            }
            ColorStateListCacheKey colorStateListCacheKey = (ColorStateListCacheKey) obj;
            return this.f2896a.equals(colorStateListCacheKey.f2896a) && ObjectsCompat.a(this.f2897b, colorStateListCacheKey.f2897b);
        }

        public int hashCode() {
            return ObjectsCompat.b(this.f2896a, this.f2897b);
        }
    }

    public static abstract class FontCallback {
        public static Handler e(Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }

        public final void c(final int i2, Handler handler) {
            e(handler).post(new Runnable() { // from class: androidx.core.content.res.b
                @Override // java.lang.Runnable
                public final void run() {
                    ResourcesCompat.FontCallback.this.f(i2);
                }
            });
        }

        public final void d(final Typeface typeface, Handler handler) {
            e(handler).post(new Runnable() { // from class: androidx.core.content.res.a
                @Override // java.lang.Runnable
                public final void run() {
                    ResourcesCompat.FontCallback.this.g(typeface);
                }
            });
        }

        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public abstract void f(int i2);

        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public abstract void g(Typeface typeface);
    }

    public static final class ThemeCompat {

        @RequiresApi
        static class Api23Impl {

            /* renamed from: a, reason: collision with root package name */
            private static final Object f2898a = new Object();
        }

        @RequiresApi
        static class Api29Impl {
            @DoNotInline
            static void a(@NonNull Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(Resources.Theme theme) {
            Api29Impl.a(theme);
        }
    }

    private static void a(ColorStateListCacheKey colorStateListCacheKey, int i2, ColorStateList colorStateList, Resources.Theme theme) {
        synchronized (f2892c) {
            try {
                WeakHashMap weakHashMap = f2891b;
                SparseArray sparseArray = (SparseArray) weakHashMap.get(colorStateListCacheKey);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    weakHashMap.put(colorStateListCacheKey, sparseArray);
                }
                sparseArray.append(i2, new ColorStateListCacheEntry(colorStateList, colorStateListCacheKey.f2896a.getConfiguration(), theme));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
    
        if (r2.f2895c == r5.hashCode()) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.res.ColorStateList b(androidx.core.content.res.ResourcesCompat.ColorStateListCacheKey r5, int r6) {
        /*
            java.lang.Object r0 = androidx.core.content.res.ResourcesCompat.f2892c
            monitor-enter(r0)
            java.util.WeakHashMap r1 = androidx.core.content.res.ResourcesCompat.f2891b     // Catch: java.lang.Throwable -> L32
            java.lang.Object r1 = r1.get(r5)     // Catch: java.lang.Throwable -> L32
            android.util.SparseArray r1 = (android.util.SparseArray) r1     // Catch: java.lang.Throwable -> L32
            if (r1 == 0) goto L45
            int r2 = r1.size()     // Catch: java.lang.Throwable -> L32
            if (r2 <= 0) goto L45
            java.lang.Object r2 = r1.get(r6)     // Catch: java.lang.Throwable -> L32
            androidx.core.content.res.ResourcesCompat$ColorStateListCacheEntry r2 = (androidx.core.content.res.ResourcesCompat.ColorStateListCacheEntry) r2     // Catch: java.lang.Throwable -> L32
            if (r2 == 0) goto L45
            android.content.res.Configuration r3 = r2.f2894b     // Catch: java.lang.Throwable -> L32
            android.content.res.Resources r4 = r5.f2896a     // Catch: java.lang.Throwable -> L32
            android.content.res.Configuration r4 = r4.getConfiguration()     // Catch: java.lang.Throwable -> L32
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L32
            if (r3 == 0) goto L42
            android.content.res.Resources$Theme r5 = r5.f2897b     // Catch: java.lang.Throwable -> L32
            if (r5 != 0) goto L34
            int r3 = r2.f2895c     // Catch: java.lang.Throwable -> L32
            if (r3 == 0) goto L3e
            goto L34
        L32:
            r5 = move-exception
            goto L48
        L34:
            if (r5 == 0) goto L42
            int r3 = r2.f2895c     // Catch: java.lang.Throwable -> L32
            int r5 = r5.hashCode()     // Catch: java.lang.Throwable -> L32
            if (r3 != r5) goto L42
        L3e:
            android.content.res.ColorStateList r5 = r2.f2893a     // Catch: java.lang.Throwable -> L32
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L32
            return r5
        L42:
            r1.remove(r6)     // Catch: java.lang.Throwable -> L32
        L45:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L32
            r5 = 0
            return r5
        L48:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L32
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.b(androidx.core.content.res.ResourcesCompat$ColorStateListCacheKey, int):android.content.res.ColorStateList");
    }

    public static Typeface c(Context context, int i2) {
        if (context.isRestricted()) {
            return null;
        }
        return m(context, i2, new TypedValue(), 0, null, null, false, true);
    }

    public static ColorStateList d(Resources resources, int i2, Resources.Theme theme) {
        ColorStateListCacheKey colorStateListCacheKey = new ColorStateListCacheKey(resources, theme);
        ColorStateList b2 = b(colorStateListCacheKey, i2);
        if (b2 != null) {
            return b2;
        }
        ColorStateList k2 = k(resources, i2, theme);
        if (k2 == null) {
            return Api23Impl.b(resources, i2, theme);
        }
        a(colorStateListCacheKey, i2, k2, theme);
        return k2;
    }

    public static Drawable e(Resources resources, int i2, Resources.Theme theme) {
        return Api21Impl.a(resources, i2, theme);
    }

    public static Drawable f(Resources resources, int i2, int i3, Resources.Theme theme) {
        return Api21Impl.b(resources, i2, i3, theme);
    }

    public static Typeface g(Context context, int i2) {
        if (context.isRestricted()) {
            return null;
        }
        return m(context, i2, new TypedValue(), 0, null, null, false, false);
    }

    public static Typeface h(Context context, int i2, TypedValue typedValue, int i3, FontCallback fontCallback) {
        if (context.isRestricted()) {
            return null;
        }
        return m(context, i2, typedValue, i3, fontCallback, null, true, false);
    }

    public static void i(Context context, int i2, FontCallback fontCallback, Handler handler) {
        Preconditions.h(fontCallback);
        if (context.isRestricted()) {
            fontCallback.c(-4, handler);
        } else {
            m(context, i2, new TypedValue(), 0, fontCallback, handler, false, false);
        }
    }

    private static TypedValue j() {
        ThreadLocal threadLocal = f2890a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    private static ColorStateList k(Resources resources, int i2, Resources.Theme theme) {
        if (l(resources, i2)) {
            return null;
        }
        try {
            return ColorStateListInflaterCompat.a(resources, resources.getXml(i2), theme);
        } catch (Exception e2) {
            Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    private static boolean l(Resources resources, int i2) {
        TypedValue j2 = j();
        resources.getValue(i2, j2, true);
        int i3 = j2.type;
        return i3 >= 28 && i3 <= 31;
    }

    private static Typeface m(Context context, int i2, TypedValue typedValue, int i3, FontCallback fontCallback, Handler handler, boolean z, boolean z2) {
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        Typeface n2 = n(context, resources, typedValue, i2, i3, fontCallback, handler, z, z2);
        if (n2 != null || fontCallback != null || z2) {
            return n2;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i2) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Typeface n(android.content.Context r16, android.content.res.Resources r17, android.util.TypedValue r18, int r19, int r20, androidx.core.content.res.ResourcesCompat.FontCallback r21, android.os.Handler r22, boolean r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.n(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, boolean):android.graphics.Typeface");
    }
}
