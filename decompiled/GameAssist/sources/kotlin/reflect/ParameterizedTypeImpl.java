package kotlin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@ExperimentalStdlibApi
@SourceDebugExtension
/* loaded from: classes2.dex */
final class ParameterizedTypeImpl implements ParameterizedType, TypeImpl {

    /* renamed from: c, reason: collision with root package name */
    private final Class f18659c;

    /* renamed from: h, reason: collision with root package name */
    private final Type f18660h;

    /* renamed from: i, reason: collision with root package name */
    private final Type[] f18661i;

    public ParameterizedTypeImpl(Class rawType, Type type, List typeArguments) {
        Intrinsics.e(rawType, "rawType");
        Intrinsics.e(typeArguments, "typeArguments");
        this.f18659c = rawType;
        this.f18660h = type;
        this.f18661i = (Type[]) typeArguments.toArray(new Type[0]);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) obj;
            if (Intrinsics.a(this.f18659c, parameterizedType.getRawType()) && Intrinsics.a(this.f18660h, parameterizedType.getOwnerType()) && Arrays.equals(getActualTypeArguments(), parameterizedType.getActualTypeArguments())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.f18661i;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.f18660h;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.f18659c;
    }

    @Override // java.lang.reflect.Type
    public String getTypeName() {
        String g2;
        String g3;
        StringBuilder sb = new StringBuilder();
        Type type = this.f18660h;
        if (type != null) {
            g3 = TypesJVMKt.g(type);
            sb.append(g3);
            sb.append("$");
            sb.append(this.f18659c.getSimpleName());
        } else {
            g2 = TypesJVMKt.g(this.f18659c);
            sb.append(g2);
        }
        Type[] typeArr = this.f18661i;
        if (!(typeArr.length == 0)) {
            ArraysKt.L(typeArr, sb, null, "<", ">", 0, null, ParameterizedTypeImpl$getTypeName$1$1.INSTANCE, 50, null);
        }
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public int hashCode() {
        int hashCode = this.f18659c.hashCode();
        Type type = this.f18660h;
        return Arrays.hashCode(getActualTypeArguments()) ^ (hashCode ^ (type != null ? type.hashCode() : 0));
    }

    public String toString() {
        return getTypeName();
    }
}
