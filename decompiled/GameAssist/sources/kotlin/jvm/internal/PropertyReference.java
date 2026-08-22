package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public abstract class PropertyReference extends CallableReference implements KProperty {
    private final boolean syntheticJavaProperty;

    public PropertyReference() {
        this.syntheticJavaProperty = false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            return m().equals(propertyReference.m()) && k().equals(propertyReference.k()) && o().equals(propertyReference.o()) && Intrinsics.a(i(), propertyReference.i());
        }
        if (obj instanceof KProperty) {
            return obj.equals(g());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable g() {
        return this.syntheticJavaProperty ? this : super.g();
    }

    public int hashCode() {
        return (((m().hashCode() * 31) + k().hashCode()) * 31) + o().hashCode();
    }

    protected KProperty p() {
        if (this.syntheticJavaProperty) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties");
        }
        return (KProperty) super.n();
    }

    public String toString() {
        KCallable g2 = g();
        if (g2 != this) {
            return g2.toString();
        }
        return "property " + k() + " (Kotlin reflection is not available)";
    }

    @SinceKotlin
    public PropertyReference(Object obj) {
        super(obj);
        this.syntheticJavaProperty = false;
    }

    @SinceKotlin
    public PropertyReference(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        this.syntheticJavaProperty = (i2 & 2) == 2;
    }
}
