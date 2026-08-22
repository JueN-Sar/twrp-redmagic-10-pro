package kotlin.text;

import java.util.Iterator;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class RegexKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final MatchResult d(Matcher matcher, int i2, CharSequence charSequence) {
        if (matcher.find(i2)) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRange e(java.util.regex.MatchResult matchResult, int i2) {
        IntRange h2;
        h2 = RangesKt___RangesKt.h(matchResult.start(i2), matchResult.end(i2));
        return h2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int f(Iterable iterable) {
        Iterator it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 |= ((FlagEnum) it.next()).getValue();
        }
        return i2;
    }
}
