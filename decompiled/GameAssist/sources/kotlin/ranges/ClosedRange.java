package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public interface ClosedRange<T extends Comparable<? super T>> {

    @Metadata
    public static final class DefaultImpls {
        public static boolean a(ClosedRange closedRange) {
            return closedRange.b().compareTo(closedRange.d()) > 0;
        }
    }

    Comparable b();

    Comparable d();
}
