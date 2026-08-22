package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;

@SinceKotlin
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class TypeParameterReference implements KTypeParameter {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18570k = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final Object f18571c;

    /* renamed from: h, reason: collision with root package name */
    private final String f18572h;

    /* renamed from: i, reason: collision with root package name */
    private final KVariance f18573i;

    /* renamed from: j, reason: collision with root package name */
    private volatile List f18574j;

    @Metadata
    public static final class Companion {

        @Metadata
        public /* synthetic */ class WhenMappings {

            /* renamed from: a, reason: collision with root package name */
            public static final /* synthetic */ int[] f18575a;

            static {
                int[] iArr = new int[KVariance.values().length];
                try {
                    iArr[KVariance.INVARIANT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[KVariance.IN.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[KVariance.OUT.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                f18575a = iArr;
            }
        }

        private Companion() {
        }

        public final String a(KTypeParameter typeParameter) {
            Intrinsics.e(typeParameter, "typeParameter");
            StringBuilder sb = new StringBuilder();
            int i2 = WhenMappings.f18575a[typeParameter.a().ordinal()];
            if (i2 == 2) {
                sb.append("in ");
            } else if (i2 == 3) {
                sb.append("out ");
            }
            sb.append(typeParameter.getName());
            String sb2 = sb.toString();
            Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // kotlin.reflect.KTypeParameter
    public KVariance a() {
        return this.f18573i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeParameterReference) {
            TypeParameterReference typeParameterReference = (TypeParameterReference) obj;
            if (Intrinsics.a(this.f18571c, typeParameterReference.f18571c) && Intrinsics.a(getName(), typeParameterReference.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.KTypeParameter
    public String getName() {
        return this.f18572h;
    }

    @Override // kotlin.reflect.KTypeParameter
    public List getUpperBounds() {
        List list = this.f18574j;
        if (list != null) {
            return list;
        }
        List e2 = CollectionsKt__CollectionsJVMKt.e(Reflection.g(Object.class));
        this.f18574j = e2;
        return e2;
    }

    public int hashCode() {
        Object obj = this.f18571c;
        return ((obj != null ? obj.hashCode() : 0) * 31) + getName().hashCode();
    }

    public String toString() {
        return f18570k.a(this);
    }
}
