package kotlinx.coroutines;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.RequiresOptIn;

@Target({ElementType.TYPE, ElementType.METHOD})
@Metadata
@RequiresOptIn
@kotlin.annotation.Target
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention
/* loaded from: classes2.dex */
public @interface InternalCoroutinesApi {
}
