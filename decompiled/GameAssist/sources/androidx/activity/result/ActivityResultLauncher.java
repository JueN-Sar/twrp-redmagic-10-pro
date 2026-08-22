package androidx.activity.result;

import androidx.core.app.ActivityOptionsCompat;

/* loaded from: classes.dex */
public abstract class ActivityResultLauncher<I> {
    public void a(Object obj) {
        b(obj, null);
    }

    public abstract void b(Object obj, ActivityOptionsCompat activityOptionsCompat);

    public abstract void c();
}
