package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class TintResources extends ResourcesWrapper {

    /* renamed from: b, reason: collision with root package name */
    private final WeakReference f1019b;

    public TintResources(Context context, Resources resources) {
        super(resources);
        this.f1019b = new WeakReference(context);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawable(int i2) {
        Drawable a2 = a(i2);
        Context context = (Context) this.f1019b.get();
        if (a2 != null && context != null) {
            ResourceManagerInternal.g().w(context, i2, a2);
        }
        return a2;
    }
}
