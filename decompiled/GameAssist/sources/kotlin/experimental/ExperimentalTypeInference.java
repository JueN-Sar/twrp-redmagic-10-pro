package kotlin.experimental;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.RequiresOptIn;
import kotlin.SinceKotlin;
import kotlin.annotation.MustBeDocumented;

@Target({ElementType.ANNOTATION_TYPE})
@SinceKotlin
@RequiresOptIn
@kotlin.annotation.Target
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention
@MustBeDocumented
@Metadata
@Documented
/* loaded from: classes2.dex */
public @interface ExperimentalTypeInference {
}
