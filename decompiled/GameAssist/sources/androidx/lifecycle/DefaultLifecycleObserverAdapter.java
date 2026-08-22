package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class DefaultLifecycleObserverAdapter implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final DefaultLifecycleObserver f4280c;

    /* renamed from: h, reason: collision with root package name */
    private final LifecycleEventObserver f4281h;

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4282a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            f4282a = iArr;
        }
    }

    public DefaultLifecycleObserverAdapter(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleEventObserver lifecycleEventObserver) {
        Intrinsics.e(defaultLifecycleObserver, "defaultLifecycleObserver");
        this.f4280c = defaultLifecycleObserver;
        this.f4281h = lifecycleEventObserver;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        switch (WhenMappings.f4282a[event.ordinal()]) {
            case 1:
                this.f4280c.a(source);
                break;
            case 2:
                this.f4280c.k(source);
                break;
            case 3:
                this.f4280c.d(source);
                break;
            case 4:
                this.f4280c.h(source);
                break;
            case 5:
                this.f4280c.i(source);
                break;
            case 6:
                this.f4280c.j(source);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        LifecycleEventObserver lifecycleEventObserver = this.f4281h;
        if (lifecycleEventObserver != null) {
            lifecycleEventObserver.c(source, event);
        }
    }
}
