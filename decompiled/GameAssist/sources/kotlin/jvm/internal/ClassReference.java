package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ClassReference implements KClass<Object>, ClassBasedDeclarationContainer {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f18550h = new Companion(null);

    /* renamed from: i, reason: collision with root package name */
    private static final Map f18551i;

    /* renamed from: j, reason: collision with root package name */
    private static final HashMap f18552j;

    /* renamed from: k, reason: collision with root package name */
    private static final HashMap f18553k;

    /* renamed from: l, reason: collision with root package name */
    private static final HashMap f18554l;

    /* renamed from: m, reason: collision with root package name */
    private static final Map f18555m;

    /* renamed from: c, reason: collision with root package name */
    private final Class f18556c;

    @Metadata
    @SourceDebugExtension
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List j2;
        Map i2;
        int c2;
        String P;
        String P2;
        j2 = CollectionsKt__CollectionsKt.j(Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.n(j2, 10));
        int i3 = 0;
        for (Object obj : j2) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.m();
            }
            arrayList.add(TuplesKt.a((Class) obj, Integer.valueOf(i3)));
            i3 = i4;
        }
        i2 = MapsKt__MapsKt.i(arrayList);
        f18551i = i2;
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        f18552j = hashMap;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        f18553k = hashMap2;
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        Intrinsics.d(values, "primitiveFqNames.values");
        for (String kotlinName : values) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            Intrinsics.d(kotlinName, "kotlinName");
            P2 = StringsKt__StringsKt.P(kotlinName, '.', null, 2, null);
            sb.append(P2);
            sb.append("CompanionObject");
            Pair a2 = TuplesKt.a(sb.toString(), kotlinName + ".Companion");
            hashMap3.put(a2.c(), a2.d());
        }
        for (Map.Entry entry : f18551i.entrySet()) {
            hashMap3.put(((Class) entry.getKey()).getName(), "kotlin.Function" + ((Number) entry.getValue()).intValue());
        }
        f18554l = hashMap3;
        c2 = MapsKt__MapsJVMKt.c(hashMap3.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(c2);
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            Object key = entry2.getKey();
            P = StringsKt__StringsKt.P((String) entry2.getValue(), '.', null, 2, null);
            linkedHashMap.put(key, P);
        }
        f18555m = linkedHashMap;
    }

    public ClassReference(Class jClass) {
        Intrinsics.e(jClass, "jClass");
        this.f18556c = jClass;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class c() {
        return this.f18556c;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ClassReference) && Intrinsics.a(JvmClassMappingKt.b(this), JvmClassMappingKt.b((KClass) obj));
    }

    public int hashCode() {
        return JvmClassMappingKt.b(this).hashCode();
    }

    public String toString() {
        return c().toString() + " (Kotlin reflection is not available)";
    }
}
