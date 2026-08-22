package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;

/* JADX WARN: Method from annotation default annotation not found: level */
@Target({ElementType.ANNOTATION_TYPE})
@Metadata
@kotlin.annotation.Target
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention
/* loaded from: classes.dex */
public @interface RequiresOptIn {

    @Metadata
    public enum Level {
        WARNING,
        ERROR
    }
}
