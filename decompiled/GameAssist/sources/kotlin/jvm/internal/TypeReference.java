package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public final class TypeReference implements KType {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18576k = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final KClassifier f18577c;

    /* renamed from: h, reason: collision with root package name */
    private final List f18578h;

    /* renamed from: i, reason: collision with root package name */
    private final KType f18579i;

    /* renamed from: j, reason: collision with root package name */
    private final int f18580j;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18581a;

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
            f18581a = iArr;
        }
    }

    public TypeReference(KClassifier classifier, List arguments, KType kType, int i2) {
        Intrinsics.e(classifier, "classifier");
        Intrinsics.e(arguments, "arguments");
        this.f18577c = classifier;
        this.f18578h = arguments;
        this.f18579i = kType;
        this.f18580j = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String c(KTypeProjection kTypeProjection) {
        String valueOf;
        if (kTypeProjection.d() == null) {
            return "*";
        }
        KType c2 = kTypeProjection.c();
        TypeReference typeReference = c2 instanceof TypeReference ? (TypeReference) c2 : null;
        if (typeReference == null || (valueOf = typeReference.g(true)) == null) {
            valueOf = String.valueOf(kTypeProjection.c());
        }
        int i2 = WhenMappings.f18581a[kTypeProjection.d().ordinal()];
        if (i2 == 1) {
            return valueOf;
        }
        if (i2 == 2) {
            return "in " + valueOf;
        }
        if (i2 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return "out " + valueOf;
    }

    private final String g(boolean z) {
        String name;
        KClassifier d2 = d();
        KClass kClass = d2 instanceof KClass ? (KClass) d2 : null;
        Class a2 = kClass != null ? JvmClassMappingKt.a(kClass) : null;
        if (a2 == null) {
            name = d().toString();
        } else if ((this.f18580j & 4) != 0) {
            name = "kotlin.Nothing";
        } else if (a2.isArray()) {
            name = h(a2);
        } else if (z && a2.isPrimitive()) {
            KClassifier d3 = d();
            Intrinsics.c(d3, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
            name = JvmClassMappingKt.b((KClass) d3).getName();
        } else {
            name = a2.getName();
        }
        String str = name + (e().isEmpty() ? "" : CollectionsKt___CollectionsKt.E(e(), ", ", "<", ">", 0, null, new Function1<KTypeProjection, CharSequence>() { // from class: kotlin.jvm.internal.TypeReference$asString$args$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final CharSequence c(KTypeProjection it) {
                String c2;
                Intrinsics.e(it, "it");
                c2 = TypeReference.this.c(it);
                return c2;
            }
        }, 24, null)) + (i() ? "?" : "");
        KType kType = this.f18579i;
        if (!(kType instanceof TypeReference)) {
            return str;
        }
        String g2 = ((TypeReference) kType).g(true);
        if (Intrinsics.a(g2, str)) {
            return str;
        }
        if (Intrinsics.a(g2, str + '?')) {
            return str + '!';
        }
        return '(' + str + ".." + g2 + ')';
    }

    private final String h(Class cls) {
        return Intrinsics.a(cls, boolean[].class) ? "kotlin.BooleanArray" : Intrinsics.a(cls, char[].class) ? "kotlin.CharArray" : Intrinsics.a(cls, byte[].class) ? "kotlin.ByteArray" : Intrinsics.a(cls, short[].class) ? "kotlin.ShortArray" : Intrinsics.a(cls, int[].class) ? "kotlin.IntArray" : Intrinsics.a(cls, float[].class) ? "kotlin.FloatArray" : Intrinsics.a(cls, long[].class) ? "kotlin.LongArray" : Intrinsics.a(cls, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
    }

    @Override // kotlin.reflect.KType
    public KClassifier d() {
        return this.f18577c;
    }

    @Override // kotlin.reflect.KType
    public List e() {
        return this.f18578h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeReference) {
            TypeReference typeReference = (TypeReference) obj;
            if (Intrinsics.a(d(), typeReference.d()) && Intrinsics.a(e(), typeReference.e()) && Intrinsics.a(this.f18579i, typeReference.f18579i) && this.f18580j == typeReference.f18580j) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((d().hashCode() * 31) + e().hashCode()) * 31) + Integer.hashCode(this.f18580j);
    }

    public boolean i() {
        return (this.f18580j & 1) != 0;
    }

    public String toString() {
        return g(false) + " (Kotlin reflection is not available)";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TypeReference(KClassifier classifier, List arguments, boolean z) {
        this(classifier, arguments, null, z ? 1 : 0);
        Intrinsics.e(classifier, "classifier");
        Intrinsics.e(arguments, "arguments");
    }
}
