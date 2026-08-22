package kotlin.reflect;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public interface KParameter extends KAnnotatedElement {

    @Metadata
    public static final class DefaultImpls {
    }

    @Metadata
    public enum Kind {
        INSTANCE,
        EXTENSION_RECEIVER,
        VALUE
    }
}
