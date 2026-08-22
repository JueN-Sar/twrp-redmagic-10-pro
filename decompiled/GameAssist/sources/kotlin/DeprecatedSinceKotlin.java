package kotlin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.MustBeDocumented;

/* JADX WARN: Method from annotation default annotation not found: errorSince */
/* JADX WARN: Method from annotation default annotation not found: hiddenSince */
/* JADX WARN: Method from annotation default annotation not found: warningSince */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@SinceKotlin
@kotlin.annotation.Target
@Retention(RetentionPolicy.RUNTIME)
@MustBeDocumented
@Metadata
@Documented
/* loaded from: classes2.dex */
public @interface DeprecatedSinceKotlin {
}
