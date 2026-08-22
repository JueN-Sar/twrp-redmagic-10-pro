package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty1;

/* loaded from: classes2.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    public MutablePropertyReference1() {
    }

    @Override // kotlin.reflect.KProperty1
    public KProperty1.Getter b() {
        return ((KMutableProperty1) p()).b();
    }

    @Override // kotlin.jvm.functions.Function1
    public Object c(Object obj) {
        return get(obj);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.e(this);
    }

    @SinceKotlin
    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @SinceKotlin
    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }
}
