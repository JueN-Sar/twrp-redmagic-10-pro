package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public class MutablePropertyReference0Impl extends MutablePropertyReference0 {
    public MutablePropertyReference0Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((ClassBasedDeclarationContainer) kDeclarationContainer).c(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return b().f(new Object[0]);
    }

    @SinceKotlin
    public MutablePropertyReference0Impl(Class cls, String str, String str2, int i2) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i2);
    }

    @SinceKotlin
    public MutablePropertyReference0Impl(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }
}
