package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@ExperimentalStdlibApi
@SourceDebugExtension
/* loaded from: classes2.dex */
final class WildcardTypeImpl implements WildcardType, TypeImpl {

    /* renamed from: i, reason: collision with root package name */
    public static final Companion f18664i = new Companion(null);

    /* renamed from: j, reason: collision with root package name */
    private static final WildcardTypeImpl f18665j = new WildcardTypeImpl(null, null);

    /* renamed from: c, reason: collision with root package name */
    private final Type f18666c;

    /* renamed from: h, reason: collision with root package name */
    private final Type f18667h;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final WildcardTypeImpl a() {
            return WildcardTypeImpl.f18665j;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WildcardTypeImpl(Type type, Type type2) {
        this.f18666c = type;
        this.f18667h = type2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) obj;
            if (Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds()) && Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getLowerBounds() {
        Type type = this.f18667h;
        return type == null ? new Type[0] : new Type[]{type};
    }

    @Override // java.lang.reflect.Type
    public String getTypeName() {
        String g2;
        String g3;
        if (this.f18667h != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("? super ");
            g3 = TypesJVMKt.g(this.f18667h);
            sb.append(g3);
            return sb.toString();
        }
        Type type = this.f18666c;
        if (type == null || Intrinsics.a(type, Object.class)) {
            return "?";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("? extends ");
        g2 = TypesJVMKt.g(this.f18666c);
        sb2.append(g2);
        return sb2.toString();
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getUpperBounds() {
        Type type = this.f18666c;
        if (type == null) {
            type = Object.class;
        }
        return new Type[]{type};
    }

    public int hashCode() {
        return Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
    }

    public String toString() {
        return getTypeName();
    }
}
