package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class TintContextWrapper extends ContextWrapper {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f1011c = new Object();

    /* renamed from: d, reason: collision with root package name */
    private static ArrayList f1012d;

    /* renamed from: a, reason: collision with root package name */
    private final Resources f1013a;

    /* renamed from: b, reason: collision with root package name */
    private final Resources.Theme f1014b;

    private TintContextWrapper(Context context) {
        super(context);
        if (!VectorEnabledTintResources.c()) {
            this.f1013a = new TintResources(this, context.getResources());
            this.f1014b = null;
            return;
        }
        VectorEnabledTintResources vectorEnabledTintResources = new VectorEnabledTintResources(this, context.getResources());
        this.f1013a = vectorEnabledTintResources;
        Resources.Theme newTheme = vectorEnabledTintResources.newTheme();
        this.f1014b = newTheme;
        newTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        if ((context instanceof TintContextWrapper) || (context.getResources() instanceof TintResources) || (context.getResources() instanceof VectorEnabledTintResources)) {
            return false;
        }
        return VectorEnabledTintResources.c();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f1011c) {
            try {
                ArrayList arrayList = f1012d;
                if (arrayList == null) {
                    f1012d = new ArrayList();
                } else {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        WeakReference weakReference = (WeakReference) f1012d.get(size);
                        if (weakReference == null || weakReference.get() == null) {
                            f1012d.remove(size);
                        }
                    }
                    for (int size2 = f1012d.size() - 1; size2 >= 0; size2--) {
                        WeakReference weakReference2 = (WeakReference) f1012d.get(size2);
                        TintContextWrapper tintContextWrapper = weakReference2 != null ? (TintContextWrapper) weakReference2.get() : null;
                        if (tintContextWrapper != null && tintContextWrapper.getBaseContext() == context) {
                            return tintContextWrapper;
                        }
                    }
                }
                TintContextWrapper tintContextWrapper2 = new TintContextWrapper(context);
                f1012d.add(new WeakReference(tintContextWrapper2));
                return tintContextWrapper2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f1013a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f1013a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1014b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        Resources.Theme theme = this.f1014b;
        if (theme == null) {
            super.setTheme(i2);
        } else {
            theme.applyStyle(i2, true);
        }
    }
}
