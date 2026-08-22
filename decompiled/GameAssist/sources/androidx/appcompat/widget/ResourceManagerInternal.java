package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.RestrictTo;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.appcompat.resources.Compatibility;
import androidx.appcompat.resources.R;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
public final class ResourceManagerInternal {

    /* renamed from: i, reason: collision with root package name */
    private static ResourceManagerInternal f925i;

    /* renamed from: a, reason: collision with root package name */
    private WeakHashMap f927a;

    /* renamed from: b, reason: collision with root package name */
    private SimpleArrayMap f928b;

    /* renamed from: c, reason: collision with root package name */
    private SparseArrayCompat f929c;

    /* renamed from: d, reason: collision with root package name */
    private final WeakHashMap f930d = new WeakHashMap(0);

    /* renamed from: e, reason: collision with root package name */
    private TypedValue f931e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f932f;

    /* renamed from: g, reason: collision with root package name */
    private ResourceManagerHooks f933g;

    /* renamed from: h, reason: collision with root package name */
    private static final PorterDuff.Mode f924h = PorterDuff.Mode.SRC_IN;

    /* renamed from: j, reason: collision with root package name */
    private static final ColorFilterLruCache f926j = new ColorFilterLruCache(6);

    static class AsldcInflateDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return AnimatedStateListDrawableCompat.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e2);
                return null;
            }
        }
    }

    private static class AvdcInflateDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.b(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e2);
                return null;
            }
        }
    }

    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i2) {
            super(i2);
        }

        private static int i(int i2, PorterDuff.Mode mode) {
            return ((i2 + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter j(int i2, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) d(Integer.valueOf(i(i2, mode)));
        }

        PorterDuffColorFilter k(int i2, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) e(Integer.valueOf(i(i2, mode)), porterDuffColorFilter);
        }
    }

    static class DrawableDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            String classAttribute = attributeSet.getClassAttribute();
            if (classAttribute != null) {
                try {
                    Drawable drawable = (Drawable) DrawableDelegate.class.getClassLoader().loadClass(classAttribute).asSubclass(Drawable.class).getDeclaredConstructor(null).newInstance(null);
                    Compatibility.Api21Impl.c(drawable, context.getResources(), xmlPullParser, attributeSet, theme);
                    return drawable;
                } catch (Exception e2) {
                    Log.e("DrawableDelegate", "Exception while inflating <drawable>", e2);
                }
            }
            return null;
        }
    }

    private interface InflateDelegate {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    @RestrictTo
    public interface ResourceManagerHooks {
        Drawable a(ResourceManagerInternal resourceManagerInternal, Context context, int i2);

        ColorStateList b(Context context, int i2);

        boolean c(Context context, int i2, Drawable drawable);

        PorterDuff.Mode d(int i2);

        boolean e(Context context, int i2, Drawable drawable);
    }

    private static class VdcInflateDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return VectorDrawableCompat.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e2);
                return null;
            }
        }
    }

    private synchronized boolean a(Context context, long j2, Drawable drawable) {
        try {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState == null) {
                return false;
            }
            LongSparseArray longSparseArray = (LongSparseArray) this.f930d.get(context);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray();
                this.f930d.put(context, longSparseArray);
            }
            longSparseArray.k(j2, new WeakReference(constantState));
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    private void b(Context context, int i2, ColorStateList colorStateList) {
        if (this.f927a == null) {
            this.f927a = new WeakHashMap();
        }
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.f927a.get(context);
        if (sparseArrayCompat == null) {
            sparseArrayCompat = new SparseArrayCompat();
            this.f927a.put(context, sparseArrayCompat);
        }
        sparseArrayCompat.a(i2, colorStateList);
    }

    private void c(Context context) {
        if (this.f932f) {
            return;
        }
        this.f932f = true;
        Drawable i2 = i(context, R.drawable.abc_vector_test);
        if (i2 == null || !p(i2)) {
            this.f932f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long d(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    private Drawable e(Context context, int i2) {
        if (this.f931e == null) {
            this.f931e = new TypedValue();
        }
        TypedValue typedValue = this.f931e;
        context.getResources().getValue(i2, typedValue, true);
        long d2 = d(typedValue);
        Drawable h2 = h(context, d2);
        if (h2 != null) {
            return h2;
        }
        ResourceManagerHooks resourceManagerHooks = this.f933g;
        Drawable a2 = resourceManagerHooks == null ? null : resourceManagerHooks.a(this, context, i2);
        if (a2 != null) {
            a2.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, d2, a2);
        }
        return a2;
    }

    private static PorterDuffColorFilter f(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return k(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized ResourceManagerInternal g() {
        ResourceManagerInternal resourceManagerInternal;
        synchronized (ResourceManagerInternal.class) {
            try {
                if (f925i == null) {
                    ResourceManagerInternal resourceManagerInternal2 = new ResourceManagerInternal();
                    f925i = resourceManagerInternal2;
                    o(resourceManagerInternal2);
                }
                resourceManagerInternal = f925i;
            } catch (Throwable th) {
                throw th;
            }
        }
        return resourceManagerInternal;
    }

    private synchronized Drawable h(Context context, long j2) {
        LongSparseArray longSparseArray = (LongSparseArray) this.f930d.get(context);
        if (longSparseArray == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) longSparseArray.f(j2);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            longSparseArray.l(j2);
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter k(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter j2;
        synchronized (ResourceManagerInternal.class) {
            ColorFilterLruCache colorFilterLruCache = f926j;
            j2 = colorFilterLruCache.j(i2, mode);
            if (j2 == null) {
                j2 = new PorterDuffColorFilter(i2, mode);
                colorFilterLruCache.k(i2, mode, j2);
            }
        }
        return j2;
    }

    private ColorStateList m(Context context, int i2) {
        SparseArrayCompat sparseArrayCompat;
        WeakHashMap weakHashMap = this.f927a;
        if (weakHashMap == null || (sparseArrayCompat = (SparseArrayCompat) weakHashMap.get(context)) == null) {
            return null;
        }
        return (ColorStateList) sparseArrayCompat.e(i2);
    }

    private static void o(ResourceManagerInternal resourceManagerInternal) {
    }

    private static boolean p(Drawable drawable) {
        return (drawable instanceof VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable q(Context context, int i2) {
        int next;
        SimpleArrayMap simpleArrayMap = this.f928b;
        if (simpleArrayMap == null || simpleArrayMap.isEmpty()) {
            return null;
        }
        SparseArrayCompat sparseArrayCompat = this.f929c;
        if (sparseArrayCompat != null) {
            String str = (String) sparseArrayCompat.e(i2);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.f928b.get(str) == null)) {
                return null;
            }
        } else {
            this.f929c = new SparseArrayCompat();
        }
        if (this.f931e == null) {
            this.f931e = new TypedValue();
        }
        TypedValue typedValue = this.f931e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long d2 = d(typedValue);
        Drawable h2 = h(context, d2);
        if (h2 != null) {
            return h2;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f929c.a(i2, name);
                InflateDelegate inflateDelegate = (InflateDelegate) this.f928b.get(name);
                if (inflateDelegate != null) {
                    h2 = inflateDelegate.a(context, xml, asAttributeSet, context.getTheme());
                }
                if (h2 != null) {
                    h2.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, d2, h2);
                }
            } catch (Exception e2) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
            }
        }
        if (h2 == null) {
            this.f929c.a(i2, "appcompat_skip_skip");
        }
        return h2;
    }

    private Drawable u(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList l2 = l(context, i2);
        if (l2 != null) {
            Drawable r2 = DrawableCompat.r(drawable.mutate());
            DrawableCompat.o(r2, l2);
            PorterDuff.Mode n2 = n(i2);
            if (n2 == null) {
                return r2;
            }
            DrawableCompat.p(r2, n2);
            return r2;
        }
        ResourceManagerHooks resourceManagerHooks = this.f933g;
        if ((resourceManagerHooks == null || !resourceManagerHooks.e(context, i2, drawable)) && !w(context, i2, drawable) && z) {
            return null;
        }
        return drawable;
    }

    static void v(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        int[] state = drawable.getState();
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z = tintInfo.f1018d;
        if (z || tintInfo.f1017c) {
            drawable.setColorFilter(f(z ? tintInfo.f1015a : null, tintInfo.f1017c ? tintInfo.f1016b : f924h, iArr));
        } else {
            drawable.clearColorFilter();
        }
    }

    public synchronized Drawable i(Context context, int i2) {
        return j(context, i2, false);
    }

    synchronized Drawable j(Context context, int i2, boolean z) {
        Drawable q2;
        try {
            c(context);
            q2 = q(context, i2);
            if (q2 == null) {
                q2 = e(context, i2);
            }
            if (q2 == null) {
                q2 = ContextCompat.e(context, i2);
            }
            if (q2 != null) {
                q2 = u(context, i2, z, q2);
            }
            if (q2 != null) {
                DrawableUtils.b(q2);
            }
        } catch (Throwable th) {
            throw th;
        }
        return q2;
    }

    synchronized ColorStateList l(Context context, int i2) {
        ColorStateList m2;
        m2 = m(context, i2);
        if (m2 == null) {
            ResourceManagerHooks resourceManagerHooks = this.f933g;
            m2 = resourceManagerHooks == null ? null : resourceManagerHooks.b(context, i2);
            if (m2 != null) {
                b(context, i2, m2);
            }
        }
        return m2;
    }

    PorterDuff.Mode n(int i2) {
        ResourceManagerHooks resourceManagerHooks = this.f933g;
        if (resourceManagerHooks == null) {
            return null;
        }
        return resourceManagerHooks.d(i2);
    }

    public synchronized void r(Context context) {
        LongSparseArray longSparseArray = (LongSparseArray) this.f930d.get(context);
        if (longSparseArray != null) {
            longSparseArray.b();
        }
    }

    synchronized Drawable s(Context context, VectorEnabledTintResources vectorEnabledTintResources, int i2) {
        try {
            Drawable q2 = q(context, i2);
            if (q2 == null) {
                q2 = vectorEnabledTintResources.a(i2);
            }
            if (q2 == null) {
                return null;
            }
            return u(context, i2, false, q2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void t(ResourceManagerHooks resourceManagerHooks) {
        this.f933g = resourceManagerHooks;
    }

    boolean w(Context context, int i2, Drawable drawable) {
        ResourceManagerHooks resourceManagerHooks = this.f933g;
        return resourceManagerHooks != null && resourceManagerHooks.c(context, i2, drawable);
    }
}
