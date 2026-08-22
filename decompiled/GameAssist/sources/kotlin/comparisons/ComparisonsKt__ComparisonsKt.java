package kotlin.comparisons;

import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class ComparisonsKt__ComparisonsKt {
    public static int a(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }
}
