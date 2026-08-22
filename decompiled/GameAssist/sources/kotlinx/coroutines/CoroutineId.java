package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@Metadata
@IgnoreJRERequirement
/* loaded from: classes2.dex */
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement<String> {

    /* renamed from: i, reason: collision with root package name */
    public static final Key f18853i = new Key(null);

    /* renamed from: h, reason: collision with root package name */
    private final long f18854h;

    @Metadata
    public static final class Key implements CoroutineContext.Key<CoroutineId> {
        private Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CoroutineId) && this.f18854h == ((CoroutineId) obj).f18854h;
    }

    public int hashCode() {
        return Long.hashCode(this.f18854h);
    }

    public final long j0() {
        return this.f18854h;
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    /* renamed from: k0, reason: merged with bridge method [inline-methods] */
    public void E(CoroutineContext coroutineContext, String str) {
        Thread.currentThread().setName(str);
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    /* renamed from: l0, reason: merged with bridge method [inline-methods] */
    public String f0(CoroutineContext coroutineContext) {
        String str;
        CoroutineName coroutineName = (CoroutineName) coroutineContext.c(CoroutineName.f18855i);
        if (coroutineName == null || (str = coroutineName.j0()) == null) {
            str = "coroutine";
        }
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        int B = StringsKt__StringsKt.B(name, " @", 0, false, 6, null);
        if (B < 0) {
            B = name.length();
        }
        StringBuilder sb = new StringBuilder(str.length() + B + 10);
        String substring = name.substring(0, B);
        Intrinsics.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(substring);
        sb.append(" @");
        sb.append(str);
        sb.append('#');
        sb.append(this.f18854h);
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder(capacity).…builderAction).toString()");
        currentThread.setName(sb2);
        return name;
    }

    public String toString() {
        return "CoroutineId(" + this.f18854h + ')';
    }
}
