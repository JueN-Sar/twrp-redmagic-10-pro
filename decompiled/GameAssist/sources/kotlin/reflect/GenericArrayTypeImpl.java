package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@ExperimentalStdlibApi
/* loaded from: classes2.dex */
final class GenericArrayTypeImpl implements GenericArrayType, TypeImpl {

    /* renamed from: c, reason: collision with root package name */
    private final Type f18653c;

    public GenericArrayTypeImpl(Type elementType) {
        Intrinsics.e(elementType, "elementType");
        this.f18653c = elementType;
    }

    public boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && Intrinsics.a(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.f18653c;
    }

    @Override // java.lang.reflect.Type
    public String getTypeName() {
        String g2;
        StringBuilder sb = new StringBuilder();
        g2 = TypesJVMKt.g(this.f18653c);
        sb.append(g2);
        sb.append("[]");
        return sb.toString();
    }

    public int hashCode() {
        return getGenericComponentType().hashCode();
    }

    public String toString() {
        return getTypeName();
    }
}
