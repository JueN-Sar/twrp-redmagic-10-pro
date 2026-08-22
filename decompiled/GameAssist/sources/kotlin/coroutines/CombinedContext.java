package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@SinceKotlin
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class CombinedContext implements CoroutineContext, Serializable {

    @NotNull
    private final CoroutineContext.Element element;

    @NotNull
    private final CoroutineContext left;

    @Metadata
    @SourceDebugExtension
    private static final class Serialized implements Serializable {

        @NotNull
        public static final Companion Companion = new Companion(null);
        private static final long serialVersionUID = 0;

        @NotNull
        private final CoroutineContext[] elements;

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public Serialized(@NotNull CoroutineContext[] elements) {
            Intrinsics.e(elements, "elements");
            this.elements = elements;
        }

        private final Object readResolve() {
            CoroutineContext[] coroutineContextArr = this.elements;
            CoroutineContext coroutineContext = EmptyCoroutineContext.INSTANCE;
            for (CoroutineContext coroutineContext2 : coroutineContextArr) {
                coroutineContext = coroutineContext.R(coroutineContext2);
            }
            return coroutineContext;
        }
    }

    public CombinedContext(@NotNull CoroutineContext left, @NotNull CoroutineContext.Element element) {
        Intrinsics.e(left, "left");
        Intrinsics.e(element, "element");
        this.left = left;
        this.element = element;
    }

    private final boolean b(CoroutineContext.Element element) {
        return Intrinsics.a(c(element.getKey()), element);
    }

    private final boolean d(CombinedContext combinedContext) {
        while (b(combinedContext.element)) {
            CoroutineContext coroutineContext = combinedContext.left;
            if (!(coroutineContext instanceof CombinedContext)) {
                Intrinsics.c(coroutineContext, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                return b((CoroutineContext.Element) coroutineContext);
            }
            combinedContext = (CombinedContext) coroutineContext;
        }
        return false;
    }

    private final int f() {
        int i2 = 2;
        while (true) {
            CoroutineContext coroutineContext = this.left;
            this = coroutineContext instanceof CombinedContext ? (CombinedContext) coroutineContext : null;
            if (this == null) {
                return i2;
            }
            i2++;
        }
    }

    private final Object writeReplace() {
        int f2 = f();
        final CoroutineContext[] coroutineContextArr = new CoroutineContext[f2];
        final Ref.IntRef intRef = new Ref.IntRef();
        e0(Unit.f18288a, new Function2<Unit, CoroutineContext.Element, Unit>() { // from class: kotlin.coroutines.CombinedContext$writeReplace$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public final void d(Unit unit, CoroutineContext.Element element) {
                Intrinsics.e(unit, "<anonymous parameter 0>");
                Intrinsics.e(element, "element");
                CoroutineContext[] coroutineContextArr2 = coroutineContextArr;
                Ref.IntRef intRef2 = intRef;
                int i2 = intRef2.element;
                intRef2.element = i2 + 1;
                coroutineContextArr2[i2] = element;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
                d((Unit) obj, (CoroutineContext.Element) obj2);
                return Unit.f18288a;
            }
        });
        if (intRef.element == f2) {
            return new Serialized(coroutineContextArr);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext R(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.a(this, coroutineContext);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        Intrinsics.e(key, "key");
        if (this.element.c(key) != null) {
            return this.left;
        }
        CoroutineContext Y = this.left.Y(key);
        return Y == this.left ? this : Y == EmptyCoroutineContext.INSTANCE ? this.element : new CombinedContext(Y, this.element);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        Intrinsics.e(key, "key");
        while (true) {
            CoroutineContext.Element c2 = this.element.c(key);
            if (c2 != null) {
                return c2;
            }
            CoroutineContext coroutineContext = this.left;
            if (!(coroutineContext instanceof CombinedContext)) {
                return coroutineContext.c(key);
            }
            this = (CombinedContext) coroutineContext;
        }
    }

    @Override // kotlin.coroutines.CoroutineContext
    public Object e0(Object obj, Function2 operation) {
        Intrinsics.e(operation, "operation");
        return operation.y(this.left.e0(obj, operation), this.element);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CombinedContext) {
                CombinedContext combinedContext = (CombinedContext) obj;
                if (combinedContext.f() != f() || !combinedContext.d(this)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    public String toString() {
        return '[' + ((String) e0("", new Function2<String, CoroutineContext.Element, String>() { // from class: kotlin.coroutines.CombinedContext$toString$1
            @Override // kotlin.jvm.functions.Function2
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String y(String acc, CoroutineContext.Element element) {
                Intrinsics.e(acc, "acc");
                Intrinsics.e(element, "element");
                if (acc.length() == 0) {
                    return element.toString();
                }
                return acc + ", " + element;
            }
        })) + ']';
    }
}
