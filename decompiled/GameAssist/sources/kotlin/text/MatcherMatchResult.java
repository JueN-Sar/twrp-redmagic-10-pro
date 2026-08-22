package kotlin.text;

import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class MatcherMatchResult implements MatchResult {

    /* renamed from: a, reason: collision with root package name */
    private final Matcher f18779a;

    /* renamed from: b, reason: collision with root package name */
    private final CharSequence f18780b;

    /* renamed from: c, reason: collision with root package name */
    private final MatchGroupCollection f18781c;

    public MatcherMatchResult(Matcher matcher, CharSequence input) {
        Intrinsics.e(matcher, "matcher");
        Intrinsics.e(input, "input");
        this.f18779a = matcher;
        this.f18780b = input;
        this.f18781c = new MatcherMatchResult$groups$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final java.util.regex.MatchResult b() {
        return this.f18779a;
    }

    @Override // kotlin.text.MatchResult
    public MatchResult next() {
        MatchResult d2;
        int end = b().end() + (b().end() == b().start() ? 1 : 0);
        if (end > this.f18780b.length()) {
            return null;
        }
        Matcher matcher = this.f18779a.pattern().matcher(this.f18780b);
        Intrinsics.d(matcher, "matcher.pattern().matcher(input)");
        d2 = RegexKt.d(matcher, end, this.f18780b);
        return d2;
    }
}
