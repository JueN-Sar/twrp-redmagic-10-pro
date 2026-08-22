package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'IGNORE_CASE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
@Metadata
/* loaded from: classes2.dex */
public final class RegexOption implements FlagEnum {
    private static final /* synthetic */ RegexOption[] $VALUES = d();
    public static final RegexOption CANON_EQ;
    public static final RegexOption COMMENTS;
    public static final RegexOption DOT_MATCHES_ALL;
    public static final RegexOption IGNORE_CASE;
    public static final RegexOption LITERAL;
    public static final RegexOption MULTILINE;
    public static final RegexOption UNIX_LINES;
    private final int mask;
    private final int value;

    static {
        int i2 = 2;
        IGNORE_CASE = new RegexOption("IGNORE_CASE", 0, i2, 0, 2, null);
        int i3 = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i4 = 0;
        MULTILINE = new RegexOption("MULTILINE", 1, 8, i4, i3, defaultConstructorMarker);
        int i5 = 2;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        int i6 = 0;
        LITERAL = new RegexOption("LITERAL", i2, 16, i6, i5, defaultConstructorMarker2);
        UNIX_LINES = new RegexOption("UNIX_LINES", 3, 1, i4, i3, defaultConstructorMarker);
        COMMENTS = new RegexOption("COMMENTS", 4, 4, i6, i5, defaultConstructorMarker2);
        DOT_MATCHES_ALL = new RegexOption("DOT_MATCHES_ALL", 5, 32, i4, i3, defaultConstructorMarker);
        CANON_EQ = new RegexOption("CANON_EQ", 6, 128, i6, i5, defaultConstructorMarker2);
    }

    private RegexOption(String str, int i2, int i3, int i4) {
        this.value = i3;
        this.mask = i4;
    }

    private static final /* synthetic */ RegexOption[] d() {
        return new RegexOption[]{IGNORE_CASE, MULTILINE, LITERAL, UNIX_LINES, COMMENTS, DOT_MATCHES_ALL, CANON_EQ};
    }

    public static RegexOption valueOf(String str) {
        return (RegexOption) Enum.valueOf(RegexOption.class, str);
    }

    public static RegexOption[] values() {
        return (RegexOption[]) $VALUES.clone();
    }

    @Override // kotlin.text.FlagEnum
    public int c() {
        return this.mask;
    }

    @Override // kotlin.text.FlagEnum
    public int getValue() {
        return this.value;
    }

    /* synthetic */ RegexOption(String str, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i2, i3, (i5 & 2) != 0 ? i3 : i4);
    }
}
