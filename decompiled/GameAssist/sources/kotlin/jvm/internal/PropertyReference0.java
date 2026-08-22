package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;

/* loaded from: classes2.dex */
public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    public PropertyReference0() {
    }

    @Override // kotlin.jvm.functions.Function0
    public Object a() {
        return get();
    }

    @Override // kotlin.reflect.KProperty0
    public KProperty0.Getter b() {
        return ((KProperty0) p()).b();
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.h(this);
    }

    @SinceKotlin
    public PropertyReference0(Object obj) {
        super(obj);
    }

    @SinceKotlin
    public PropertyReference0(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }
}
