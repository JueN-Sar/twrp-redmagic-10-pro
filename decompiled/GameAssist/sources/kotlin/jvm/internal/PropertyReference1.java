package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;

/* loaded from: classes2.dex */
public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    public PropertyReference1() {
    }

    @Override // kotlin.reflect.KProperty1
    public KProperty1.Getter b() {
        return ((KProperty1) p()).b();
    }

    @Override // kotlin.jvm.functions.Function1
    public Object c(Object obj) {
        return get(obj);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.i(this);
    }

    @SinceKotlin
    public PropertyReference1(Object obj) {
        super(obj);
    }

    @SinceKotlin
    public PropertyReference1(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }
}
