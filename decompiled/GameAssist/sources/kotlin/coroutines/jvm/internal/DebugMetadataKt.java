package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class DebugMetadataKt {
    private static final void a(int i2, int i3) {
        if (i3 <= i2) {
            return;
        }
        throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + i2 + ", got " + i3 + ". Please update the Kotlin standard library.").toString());
    }

    private static final DebugMetadata b(BaseContinuationImpl baseContinuationImpl) {
        return (DebugMetadata) baseContinuationImpl.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int c(BaseContinuationImpl baseContinuationImpl) {
        try {
            Field declaredField = baseContinuationImpl.getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(baseContinuationImpl);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            return (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static final StackTraceElement d(BaseContinuationImpl baseContinuationImpl) {
        String str;
        Intrinsics.e(baseContinuationImpl, "<this>");
        DebugMetadata b2 = b(baseContinuationImpl);
        if (b2 == null) {
            return null;
        }
        a(1, b2.v());
        int c2 = c(baseContinuationImpl);
        int i2 = c2 < 0 ? -1 : b2.l()[c2];
        String b3 = ModuleNameRetriever.f18417a.b(baseContinuationImpl);
        if (b3 == null) {
            str = b2.c();
        } else {
            str = b3 + '/' + b2.c();
        }
        return new StackTraceElement(str, b2.m(), b2.f(), i2);
    }
}
