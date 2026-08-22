package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@Metadata
@IgnoreJRERequirement
/* loaded from: classes2.dex */
final class ClassValueCtorCache extends CtorCache {

    /* renamed from: a, reason: collision with root package name */
    public static final ClassValueCtorCache f19342a = new ClassValueCtorCache();

    /* renamed from: b, reason: collision with root package name */
    private static final ClassValueCtorCache$cache$1 f19343b = new ClassValue<Function1<? super Throwable, ? extends Throwable>>() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ClassValue
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Function1 computeValue(Class cls) {
            Function1 b2;
            if (cls == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<out kotlin.Throwable>");
            }
            b2 = ExceptionsConstructorKt.b(cls);
            return b2;
        }
    };

    private ClassValueCtorCache() {
    }
}
