package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public class MutablePropertyReference2Impl extends MutablePropertyReference2 {
    public MutablePropertyReference2Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(((ClassBasedDeclarationContainer) kDeclarationContainer).c(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    @Override // kotlin.reflect.KProperty2
    public Object E(Object obj, Object obj2) {
        return b().f(obj, obj2);
    }

    @SinceKotlin
    public MutablePropertyReference2Impl(Class cls, String str, String str2, int i2) {
        super(cls, str, str2, i2);
    }
}
