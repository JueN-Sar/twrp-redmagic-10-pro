package kotlin.jvm.internal;

import java.util.Collections;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;

/* loaded from: classes2.dex */
public class Reflection {

    /* renamed from: a, reason: collision with root package name */
    private static final ReflectionFactory f18565a;

    /* renamed from: b, reason: collision with root package name */
    private static final KClass[] f18566b;

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        f18565a = reflectionFactory;
        f18566b = new KClass[0];
    }

    public static KFunction a(FunctionReference functionReference) {
        return f18565a.a(functionReference);
    }

    public static KClass b(Class cls) {
        return f18565a.b(cls);
    }

    public static KDeclarationContainer c(Class cls) {
        return f18565a.c(cls, "");
    }

    public static KMutableProperty0 d(MutablePropertyReference0 mutablePropertyReference0) {
        return f18565a.d(mutablePropertyReference0);
    }

    public static KMutableProperty1 e(MutablePropertyReference1 mutablePropertyReference1) {
        return f18565a.e(mutablePropertyReference1);
    }

    public static KMutableProperty2 f(MutablePropertyReference2 mutablePropertyReference2) {
        return f18565a.f(mutablePropertyReference2);
    }

    public static KType g(Class cls) {
        return f18565a.l(b(cls), Collections.emptyList(), true);
    }

    public static KProperty0 h(PropertyReference0 propertyReference0) {
        return f18565a.g(propertyReference0);
    }

    public static KProperty1 i(PropertyReference1 propertyReference1) {
        return f18565a.h(propertyReference1);
    }

    public static KProperty2 j(PropertyReference2 propertyReference2) {
        return f18565a.i(propertyReference2);
    }

    public static String k(FunctionBase functionBase) {
        return f18565a.j(functionBase);
    }

    public static String l(Lambda lambda) {
        return f18565a.k(lambda);
    }
}
