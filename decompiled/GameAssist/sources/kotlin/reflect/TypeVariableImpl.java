package kotlin.reflect;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@ExperimentalStdlibApi
@SourceDebugExtension
/* loaded from: classes2.dex */
final class TypeVariableImpl implements TypeVariable<GenericDeclaration>, TypeImpl {

    /* renamed from: c, reason: collision with root package name */
    private final KTypeParameter f18662c;

    public TypeVariableImpl(KTypeParameter typeParameter) {
        Intrinsics.e(typeParameter, "typeParameter");
        this.f18662c = typeParameter;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) obj;
            if (Intrinsics.a(getName(), typeVariable.getName()) && Intrinsics.a(getGenericDeclaration(), typeVariable.getGenericDeclaration())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.TypeVariable
    public Type[] getBounds() {
        Type c2;
        List upperBounds = this.f18662c.getUpperBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.n(upperBounds, 10));
        Iterator it = upperBounds.iterator();
        while (it.hasNext()) {
            c2 = TypesJVMKt.c((KType) it.next(), true);
            arrayList.add(c2);
        }
        return (Type[]) arrayList.toArray(new Type[0]);
    }

    @Override // java.lang.reflect.TypeVariable
    public GenericDeclaration getGenericDeclaration() {
        throw new NotImplementedError("An operation is not implemented: " + ("getGenericDeclaration() is not yet supported for type variables created from KType: " + this.f18662c));
    }

    @Override // java.lang.reflect.TypeVariable
    public String getName() {
        return this.f18662c.getName();
    }

    @Override // java.lang.reflect.Type
    public String getTypeName() {
        return getName();
    }

    public int hashCode() {
        return getGenericDeclaration().hashCode() ^ getName().hashCode();
    }

    public String toString() {
        return getTypeName();
    }
}
