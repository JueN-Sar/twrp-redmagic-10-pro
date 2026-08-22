package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public interface SharingStarted {

    /* renamed from: a, reason: collision with root package name */
    public static final Companion f19269a = Companion.f19270a;

    @Metadata
    public static final class Companion {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ Companion f19270a = new Companion();

        /* renamed from: b, reason: collision with root package name */
        private static final SharingStarted f19271b = new StartedEagerly();

        /* renamed from: c, reason: collision with root package name */
        private static final SharingStarted f19272c = new StartedLazily();

        private Companion() {
        }

        public final SharingStarted a() {
            return f19271b;
        }

        public final SharingStarted b() {
            return f19272c;
        }
    }

    Flow a(StateFlow stateFlow);
}
