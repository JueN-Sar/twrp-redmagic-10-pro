package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.MustBeDocumented;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@kotlin.annotation.Target
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention
@MustBeDocumented
@Metadata
@Documented
/* loaded from: classes.dex */
public @interface TransitionRes {
}
