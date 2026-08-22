package kotlin.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.Repeatable;
import kotlin.jvm.internal.RepeatableContainer;

/* JADX WARN: Method from annotation default annotation not found: errorCode */
/* JADX WARN: Method from annotation default annotation not found: level */
/* JADX WARN: Method from annotation default annotation not found: message */
/* JADX WARN: Method from annotation default annotation not found: versionKind */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@SinceKotlin
@kotlin.annotation.Target
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention
@Metadata
@Repeatable
@java.lang.annotation.Repeatable(Container.class)
/* loaded from: classes2.dex */
public @interface RequireKotlin {

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Metadata
    @kotlin.annotation.Target
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention
    @RepeatableContainer
    public @interface Container {
    }
}
