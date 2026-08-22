package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE})
@SinceKotlin
@Metadata
@kotlin.annotation.Target
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention
/* loaded from: classes2.dex */
public @interface OptIn {
}
