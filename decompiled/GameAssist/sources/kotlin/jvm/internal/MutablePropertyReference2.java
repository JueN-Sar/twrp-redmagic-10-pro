package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty2;

/* loaded from: classes2.dex */
public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    public MutablePropertyReference2() {
    }

    @Override // kotlin.reflect.KProperty2
    public KProperty2.Getter b() {
        return ((KMutableProperty2) p()).b();
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.f(this);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object y(Object obj, Object obj2) {
        return E(obj, obj2);
    }

    @SinceKotlin
    public MutablePropertyReference2(Class cls, String str, String str2, int i2) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i2);
    }
}
