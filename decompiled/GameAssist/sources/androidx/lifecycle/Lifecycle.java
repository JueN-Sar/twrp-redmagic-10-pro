package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
public abstract class Lifecycle {

    /* renamed from: a, reason: collision with root package name */
    private AtomicReference f4290a = new AtomicReference();

    @Metadata
    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;


        @NotNull
        public static final Companion Companion = new Companion(null);

        @Metadata
        public static final class Companion {

            @Metadata
            public /* synthetic */ class WhenMappings {

                /* renamed from: a, reason: collision with root package name */
                public static final /* synthetic */ int[] f4291a;

                static {
                    int[] iArr = new int[State.values().length];
                    try {
                        iArr[State.CREATED.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[State.STARTED.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[State.RESUMED.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[State.DESTROYED.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[State.INITIALIZED.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    f4291a = iArr;
                }
            }

            private Companion() {
            }

            public final Event a(State state) {
                Intrinsics.e(state, "state");
                int i2 = WhenMappings.f4291a[state.ordinal()];
                if (i2 == 1) {
                    return Event.ON_DESTROY;
                }
                if (i2 == 2) {
                    return Event.ON_STOP;
                }
                if (i2 != 3) {
                    return null;
                }
                return Event.ON_PAUSE;
            }

            public final Event b(State state) {
                Intrinsics.e(state, "state");
                int i2 = WhenMappings.f4291a[state.ordinal()];
                if (i2 == 1) {
                    return Event.ON_START;
                }
                if (i2 == 2) {
                    return Event.ON_RESUME;
                }
                if (i2 != 5) {
                    return null;
                }
                return Event.ON_CREATE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Metadata
        public /* synthetic */ class WhenMappings {

            /* renamed from: a, reason: collision with root package name */
            public static final /* synthetic */ int[] f4292a;

            static {
                int[] iArr = new int[Event.values().length];
                try {
                    iArr[Event.ON_CREATE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Event.ON_STOP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Event.ON_START.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Event.ON_PAUSE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Event.ON_RESUME.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Event.ON_DESTROY.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[Event.ON_ANY.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                f4292a = iArr;
            }
        }

        public final State d() {
            switch (WhenMappings.f4292a[ordinal()]) {
                case 1:
                case 2:
                    return State.CREATED;
                case 3:
                case 4:
                    return State.STARTED;
                case 5:
                    return State.RESUMED;
                case 6:
                    return State.DESTROYED;
                default:
                    throw new IllegalArgumentException(this + " has no target state");
            }
        }
    }

    @Metadata
    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public final boolean d(State state) {
            Intrinsics.e(state, "state");
            return compareTo(state) >= 0;
        }
    }

    public abstract void a(LifecycleObserver lifecycleObserver);

    public abstract State b();

    public abstract void c(LifecycleObserver lifecycleObserver);
}
