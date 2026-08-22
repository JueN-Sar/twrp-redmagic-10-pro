package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__StringsJVMKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class TypesJVMKt {

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18663a;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f18663a = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type c(KType kType, boolean z) {
        Object J;
        KClassifier d2 = kType.d();
        if (d2 instanceof KTypeParameter) {
            return new TypeVariableImpl((KTypeParameter) d2);
        }
        if (!(d2 instanceof KClass)) {
            throw new UnsupportedOperationException("Unsupported type classifier: " + kType);
        }
        KClass kClass = (KClass) d2;
        Class b2 = z ? JvmClassMappingKt.b(kClass) : JvmClassMappingKt.a(kClass);
        List e2 = kType.e();
        if (e2.isEmpty()) {
            return b2;
        }
        if (!b2.isArray()) {
            return e(b2, e2);
        }
        if (b2.getComponentType().isPrimitive()) {
            return b2;
        }
        J = CollectionsKt___CollectionsKt.J(e2);
        KTypeProjection kTypeProjection = (KTypeProjection) J;
        if (kTypeProjection == null) {
            throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + kType);
        }
        KVariance a2 = kTypeProjection.a();
        KType b3 = kTypeProjection.b();
        int i2 = a2 == null ? -1 : WhenMappings.f18663a[a2.ordinal()];
        if (i2 == -1 || i2 == 1) {
            return b2;
        }
        if (i2 != 2 && i2 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        Intrinsics.b(b3);
        Type d3 = d(b3, false, 1, null);
        return d3 instanceof Class ? b2 : new GenericArrayTypeImpl(d3);
    }

    static /* synthetic */ Type d(KType kType, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        return c(kType, z);
    }

    private static final Type e(Class cls, List list) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.n(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(f((KTypeProjection) it.next()));
            }
            return new ParameterizedTypeImpl(cls, null, arrayList);
        }
        if (Modifier.isStatic(cls.getModifiers())) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.n(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(f((KTypeProjection) it2.next()));
            }
            return new ParameterizedTypeImpl(cls, declaringClass, arrayList2);
        }
        int length = cls.getTypeParameters().length;
        Type e2 = e(declaringClass, list.subList(length, list.size()));
        List subList = list.subList(0, length);
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.n(subList, 10));
        Iterator it3 = subList.iterator();
        while (it3.hasNext()) {
            arrayList3.add(f((KTypeProjection) it3.next()));
        }
        return new ParameterizedTypeImpl(cls, e2, arrayList3);
    }

    private static final Type f(KTypeProjection kTypeProjection) {
        KVariance d2 = kTypeProjection.d();
        if (d2 == null) {
            return WildcardTypeImpl.f18664i.a();
        }
        KType c2 = kTypeProjection.c();
        Intrinsics.b(c2);
        int i2 = WhenMappings.f18663a[d2.ordinal()];
        if (i2 == 1) {
            return new WildcardTypeImpl(null, c(c2, true));
        }
        if (i2 == 2) {
            return c(c2, true);
        }
        if (i2 == 3) {
            return new WildcardTypeImpl(c(c2, true), null);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String g(Type type) {
        String name;
        Sequence e2;
        Object i2;
        int f2;
        String l2;
        if (!(type instanceof Class)) {
            return type.toString();
        }
        Class cls = (Class) type;
        if (cls.isArray()) {
            e2 = SequencesKt__SequencesKt.e(type, TypesJVMKt$typeToString$unwrap$1.INSTANCE);
            StringBuilder sb = new StringBuilder();
            i2 = SequencesKt___SequencesKt.i(e2);
            sb.append(((Class) i2).getName());
            f2 = SequencesKt___SequencesKt.f(e2);
            l2 = StringsKt__StringsJVMKt.l("[]", f2);
            sb.append(l2);
            name = sb.toString();
        } else {
            name = cls.getName();
        }
        Intrinsics.d(name, "{\n        if (type.isArr…   } else type.name\n    }");
        return name;
    }
}
