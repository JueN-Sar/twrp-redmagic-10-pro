package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata
/* loaded from: classes2.dex */
public interface Channel<E> extends SendChannel<E>, ReceiveChannel<E> {

    /* renamed from: g, reason: collision with root package name */
    public static final Factory f19001g = Factory.f19002a;

    @Metadata
    public static final class DefaultImpls {
    }

    @Metadata
    public static final class Factory {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ Factory f19002a = new Factory();

        /* renamed from: b, reason: collision with root package name */
        private static final int f19003b = SystemPropsKt.b("kotlinx.coroutines.channels.defaultBuffer", 64, 1, 2147483646);

        private Factory() {
        }

        public final int a() {
            return f19003b;
        }
    }
}
