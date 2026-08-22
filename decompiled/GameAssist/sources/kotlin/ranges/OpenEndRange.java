package kotlin.ranges;

import java.lang.Comparable;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;

@SinceKotlin
@Metadata
@ExperimentalStdlibApi
/* loaded from: classes2.dex */
public interface OpenEndRange<T extends Comparable<? super T>> {

    @Metadata
    public static final class DefaultImpls {
        public static boolean a(OpenEndRange openEndRange) {
            return openEndRange.b().compareTo(openEndRange.f()) >= 0;
        }
    }

    Comparable b();

    Comparable f();
}
