package kotlin.reflect;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public final class KTypeProjection {

    /* renamed from: c, reason: collision with root package name */
    public static final Companion f18654c = new Companion(null);

    /* renamed from: d, reason: collision with root package name */
    public static final KTypeProjection f18655d = new KTypeProjection(null, null);

    /* renamed from: a, reason: collision with root package name */
    private final KVariance f18656a;

    /* renamed from: b, reason: collision with root package name */
    private final KType f18657b;

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
        public static final /* synthetic */ int[] f18658a;

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
            f18658a = iArr;
        }
    }

    public KTypeProjection(KVariance kVariance, KType kType) {
        String str;
        this.f18656a = kVariance;
        this.f18657b = kType;
        if ((kVariance == null) == (kType == null)) {
            return;
        }
        if (kVariance == null) {
            str = "Star projection must have no type specified.";
        } else {
            str = "The projection variance " + kVariance + " requires type to be specified.";
        }
        throw new IllegalArgumentException(str.toString());
    }

    public final KVariance a() {
        return this.f18656a;
    }

    public final KType b() {
        return this.f18657b;
    }

    public final KType c() {
        return this.f18657b;
    }

    public final KVariance d() {
        return this.f18656a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KTypeProjection)) {
            return false;
        }
        KTypeProjection kTypeProjection = (KTypeProjection) obj;
        return this.f18656a == kTypeProjection.f18656a && Intrinsics.a(this.f18657b, kTypeProjection.f18657b);
    }

    public int hashCode() {
        KVariance kVariance = this.f18656a;
        int hashCode = (kVariance == null ? 0 : kVariance.hashCode()) * 31;
        KType kType = this.f18657b;
        return hashCode + (kType != null ? kType.hashCode() : 0);
    }

    public String toString() {
        KVariance kVariance = this.f18656a;
        int i2 = kVariance == null ? -1 : WhenMappings.f18658a[kVariance.ordinal()];
        if (i2 == -1) {
            return "*";
        }
        if (i2 == 1) {
            return String.valueOf(this.f18657b);
        }
        if (i2 == 2) {
            return "in " + this.f18657b;
        }
        if (i2 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return "out " + this.f18657b;
    }
}
