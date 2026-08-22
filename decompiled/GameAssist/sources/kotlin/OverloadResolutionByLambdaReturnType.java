package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.experimental.ExperimentalTypeInference;

@Target({ElementType.METHOD})
@SinceKotlin
@ExperimentalTypeInference
@kotlin.annotation.Target
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention
@Metadata
/* loaded from: classes2.dex */
public @interface OverloadResolutionByLambdaReturnType {
}
