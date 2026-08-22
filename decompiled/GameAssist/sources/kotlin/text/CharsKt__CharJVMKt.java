package kotlin.text;

import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class CharsKt__CharJVMKt {
    public static int a(int i2) {
        if (new IntRange(2, 36).l(i2)) {
            return i2;
        }
        throw new IllegalArgumentException("radix " + i2 + " was not in valid range " + new IntRange(2, 36));
    }

    public static final int b(char c2, int i2) {
        return Character.digit((int) c2, i2);
    }

    public static final boolean c(char c2) {
        return Character.isWhitespace(c2) || Character.isSpaceChar(c2);
    }
}
