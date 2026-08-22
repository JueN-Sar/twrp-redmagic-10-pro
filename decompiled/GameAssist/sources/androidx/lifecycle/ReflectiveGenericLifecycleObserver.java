package androidx.lifecycle;

import androidx.lifecycle.ClassesInfoCache;
import androidx.lifecycle.Lifecycle;

@Deprecated
/* loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final Object f4352c;

    /* renamed from: h, reason: collision with root package name */
    private final ClassesInfoCache.CallbackInfo f4353h;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f4352c = obj;
        this.f4353h = ClassesInfoCache.f4269c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.f4353h.a(lifecycleOwner, event, this.f4352c);
    }
}
