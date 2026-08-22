package kotlin.jvm.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.reflect.KDeclarationContainer;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public class MutableLocalVariableReference extends MutablePropertyReference0 {
    @Override // kotlin.reflect.KProperty0
    public Object get() {
        LocalVariableReferencesKt.b();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer m() {
        LocalVariableReferencesKt.b();
        throw new KotlinNothingValueException();
    }
}
