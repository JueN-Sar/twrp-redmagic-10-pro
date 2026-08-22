package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class YieldContext extends AbstractCoroutineContextElement {

    /* renamed from: i, reason: collision with root package name */
    public static final Key f18942i = new Key(null);

    /* renamed from: h, reason: collision with root package name */
    public boolean f18943h;

    @Metadata
    public static final class Key implements CoroutineContext.Key<YieldContext> {
        private Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public YieldContext() {
        super(f18942i);
    }
}
