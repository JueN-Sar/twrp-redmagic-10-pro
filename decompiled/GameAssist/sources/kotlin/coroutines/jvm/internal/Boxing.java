package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata
@JvmName
/* loaded from: classes2.dex */
public final class Boxing {
    public static final Boolean a(boolean z) {
        return Boolean.valueOf(z);
    }

    public static final Integer b(int i2) {
        return new Integer(i2);
    }

    public static final Long c(long j2) {
        return new Long(j2);
    }
}
