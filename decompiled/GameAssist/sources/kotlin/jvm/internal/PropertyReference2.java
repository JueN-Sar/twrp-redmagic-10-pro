package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;

/* loaded from: classes2.dex */
public abstract class PropertyReference2 extends PropertyReference implements KProperty2 {
    public PropertyReference2() {
    }

    @Override // kotlin.reflect.KProperty2
    public KProperty2.Getter b() {
        return ((KProperty2) p()).b();
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.j(this);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object y(Object obj, Object obj2) {
        return E(obj, obj2);
    }

    @SinceKotlin
    public PropertyReference2(Class cls, String str, String str2, int i2) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i2);
    }
}
