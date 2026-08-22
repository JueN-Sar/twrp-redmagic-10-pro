package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.SinceKotlin;

@Target({ElementType.METHOD})
@SinceKotlin
@Metadata
@Deprecated
@kotlin.annotation.Target
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface JvmDefault {
}
