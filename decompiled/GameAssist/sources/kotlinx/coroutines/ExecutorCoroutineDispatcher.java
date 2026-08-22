package kotlinx.coroutines;

import java.io.Closeable;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata
/* loaded from: classes2.dex */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {

    /* renamed from: i, reason: collision with root package name */
    public static final Key f18889i = new Key(null);

    @Metadata
    @ExperimentalStdlibApi
    public static final class Key extends AbstractCoroutineContextKey<CoroutineDispatcher, ExecutorCoroutineDispatcher> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
            super(CoroutineDispatcher.f18848h, new Function1<CoroutineContext.Element, ExecutorCoroutineDispatcher>() { // from class: kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1
                @Override // kotlin.jvm.functions.Function1
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final ExecutorCoroutineDispatcher c(CoroutineContext.Element element) {
                    if (element instanceof ExecutorCoroutineDispatcher) {
                        return (ExecutorCoroutineDispatcher) element;
                    }
                    return null;
                }
            });
        }
    }
}
