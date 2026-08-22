package kotlin.math;

import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class MathKt__MathJVMKt extends MathKt__MathHKt {
    public static long a(double d2) {
        if (Double.isNaN(d2)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(d2);
    }
}
