package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Method from annotation default annotation not found: level */
/* JADX WARN: Method from annotation default annotation not found: message */
@Target({ElementType.ANNOTATION_TYPE})
@SinceKotlin
@Metadata
@kotlin.annotation.Target
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention
/* loaded from: classes2.dex */
public @interface RequiresOptIn {

    @Metadata
    public enum Level {
        WARNING,
        ERROR
    }
}
