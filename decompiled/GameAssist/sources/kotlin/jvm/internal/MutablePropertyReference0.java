package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KProperty0;

/* loaded from: classes2.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements KMutableProperty0 {
    public MutablePropertyReference0() {
    }

    @Override // kotlin.jvm.functions.Function0
    public Object a() {
        return get();
    }

    @Override // kotlin.reflect.KProperty0
    public KProperty0.Getter b() {
        return ((KMutableProperty0) p()).b();
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.d(this);
    }

    @SinceKotlin
    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @SinceKotlin
    public MutablePropertyReference0(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }
}
