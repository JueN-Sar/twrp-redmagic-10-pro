package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public class PropertyReference2Impl extends PropertyReference2 {
    public PropertyReference2Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(((ClassBasedDeclarationContainer) kDeclarationContainer).c(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    @Override // kotlin.reflect.KProperty2
    public Object E(Object obj, Object obj2) {
        return b().f(obj, obj2);
    }

    @SinceKotlin
    public PropertyReference2Impl(Class cls, String str, String str2, int i2) {
        super(cls, str, str2, i2);
    }
}
