package kotlin.text;

import java.io.Serializable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class Regex implements Serializable {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private Set<? extends RegexOption> _options;

    @NotNull
    private final Pattern nativePattern;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int b(int i2) {
            return (i2 & 2) != 0 ? i2 | 64 : i2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    private static final class Serialized implements Serializable {

        @NotNull
        public static final Companion Companion = new Companion(null);
        private static final long serialVersionUID = 0;
        private final int flags;

        @NotNull
        private final String pattern;

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public Serialized(@NotNull String pattern, int i2) {
            Intrinsics.e(pattern, "pattern");
            this.pattern = pattern;
            this.flags = i2;
        }

        private final Object readResolve() {
            Pattern compile = Pattern.compile(this.pattern, this.flags);
            Intrinsics.d(compile, "compile(pattern, flags)");
            return new Regex(compile);
        }
    }

    @PublishedApi
    public Regex(@NotNull Pattern nativePattern) {
        Intrinsics.e(nativePattern, "nativePattern");
        this.nativePattern = nativePattern;
    }

    private final Object writeReplace() {
        String pattern = this.nativePattern.pattern();
        Intrinsics.d(pattern, "nativePattern.pattern()");
        return new Serialized(pattern, this.nativePattern.flags());
    }

    public final MatchResult b(CharSequence input, int i2) {
        MatchResult d2;
        Intrinsics.e(input, "input");
        Matcher matcher = this.nativePattern.matcher(input);
        Intrinsics.d(matcher, "nativePattern.matcher(input)");
        d2 = RegexKt.d(matcher, i2, input);
        return d2;
    }

    public String toString() {
        String pattern = this.nativePattern.toString();
        Intrinsics.d(pattern, "nativePattern.toString()");
        return pattern;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Regex(@org.jetbrains.annotations.NotNull java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            kotlin.jvm.internal.Intrinsics.e(r2, r0)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.lang.String r0 = "compile(pattern)"
            kotlin.jvm.internal.Intrinsics.d(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex.<init>(java.lang.String):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Regex(@org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.NotNull kotlin.text.RegexOption r3) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            kotlin.jvm.internal.Intrinsics.e(r2, r0)
            java.lang.String r0 = "option"
            kotlin.jvm.internal.Intrinsics.e(r3, r0)
            kotlin.text.Regex$Companion r0 = kotlin.text.Regex.Companion
            int r3 = r3.getValue()
            int r3 = kotlin.text.Regex.Companion.a(r0, r3)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2, r3)
            java.lang.String r3 = "compile(pattern, ensureUnicodeCase(option.value))"
            kotlin.jvm.internal.Intrinsics.d(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex.<init>(java.lang.String, kotlin.text.RegexOption):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Regex(@org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.NotNull java.util.Set<? extends kotlin.text.RegexOption> r3) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            kotlin.jvm.internal.Intrinsics.e(r2, r0)
            java.lang.String r0 = "options"
            kotlin.jvm.internal.Intrinsics.e(r3, r0)
            kotlin.text.Regex$Companion r0 = kotlin.text.Regex.Companion
            int r3 = kotlin.text.RegexKt.c(r3)
            int r3 = kotlin.text.Regex.Companion.a(r0, r3)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2, r3)
            java.lang.String r3 = "compile(pattern, ensureU…odeCase(options.toInt()))"
            kotlin.jvm.internal.Intrinsics.d(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex.<init>(java.lang.String, java.util.Set):void");
    }
}
