package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.MustBeDocumented;

/* JADX WARN: Method from annotation default annotation not found: allOf */
/* JADX WARN: Method from annotation default annotation not found: anyOf */
/* JADX WARN: Method from annotation default annotation not found: conditional */
/* JADX WARN: Method from annotation default annotation not found: value */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@kotlin.annotation.Target
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention
@MustBeDocumented
@Metadata
@Documented
/* loaded from: classes.dex */
public @interface RequiresPermission {

    /* JADX WARN: Method from annotation default annotation not found: value */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata
    @kotlin.annotation.Target
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Read {
    }

    /* JADX WARN: Method from annotation default annotation not found: value */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata
    @kotlin.annotation.Target
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Write {
    }
}
