package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX WARN: Method from annotation default annotation not found: attributeId */
/* JADX WARN: Method from annotation default annotation not found: enumMapping */
/* JADX WARN: Method from annotation default annotation not found: flagMapping */
/* JADX WARN: Method from annotation default annotation not found: hasAttributeId */
/* JADX WARN: Method from annotation default annotation not found: name */
/* JADX WARN: Method from annotation default annotation not found: valueType */
@Target({ElementType.METHOD})
@Metadata
@Deprecated
@kotlin.annotation.Target
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention
/* loaded from: classes.dex */
public @interface InspectableProperty {

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Metadata
    @kotlin.annotation.Target
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention
    public @interface EnumEntry {
    }

    /* JADX WARN: Method from annotation default annotation not found: mask */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Metadata
    @kotlin.annotation.Target
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention
    public @interface FlagEntry {
    }

    @Metadata
    public enum ValueType {
        NONE,
        INFERRED,
        INT_ENUM,
        INT_FLAG,
        COLOR,
        GRAVITY,
        RESOURCE_ID
    }
}
