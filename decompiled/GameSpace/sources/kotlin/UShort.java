package kotlin;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

/* compiled from: UShort.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u0010J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0013J\u001b\u0010&\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0010J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0013J\u001b\u00102\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u0013J\u001b\u00107\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010\u001fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0010J\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0013J\u001b\u0010?\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u0010J\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0013J\u001b\u0010H\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u0010J\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u0013J\u001b\u0010M\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020SH\u0087\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020WH\u0087\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020\rH\u0087\b¢\u0006\u0004\b_\u0010-J\u0010\u0010`\u001a\u00020aH\u0087\b¢\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\be\u0010\u0005J\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u000eH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010UJ\u0016\u0010l\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010cJ\u0016\u0010p\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bq\u0010\u0005J\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", TtmlNode.TAG_DIV, "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(SLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(S)I", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(SB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
@JvmInline
/* loaded from: classes2.dex */
public final class UShort implements Comparable<UShort> {
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    private /* synthetic */ UShort(short s) {
        this.data = s;
    }

    /* renamed from: and-xj2QHRw, reason: not valid java name */
    private static final short m738andxj2QHRw(short s, short s2) {
        return m745constructorimpl((short) (s & s2));
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShort m739boximpl(short s) {
        return new UShort(s);
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m740compareTo7apg3OU(short s, byte b) {
        return Intrinsics.compare(s & MAX_VALUE, b & 255);
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m741compareToVKZWuLQ(short s, long j) {
        return UnsignedKt.ulongCompare(ULong.m639constructorimpl(s & 65535), j);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m742compareToWZ4Q5Ns(short s, int i) {
        return UnsignedKt.uintCompare(UInt.m561constructorimpl(s & MAX_VALUE), i);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private int m743compareToxj2QHRw(short s) {
        return Intrinsics.compare(getData() & MAX_VALUE, s & MAX_VALUE);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static int m744compareToxj2QHRw(short s, short s2) {
        return Intrinsics.compare(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static short m745constructorimpl(short s) {
        return s;
    }

    /* renamed from: dec-Mh2AYeg, reason: not valid java name */
    private static final short m746decMh2AYeg(short s) {
        return m745constructorimpl((short) (s - 1));
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m747div7apg3OU(short s, byte b) {
        return UnsignedKt.m814uintDivideJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(b & 255));
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m748divVKZWuLQ(short s, long j) {
        return UnsignedKt.m816ulongDivideeb3DHEI(ULong.m639constructorimpl(s & 65535), j);
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m749divWZ4Q5Ns(short s, int i) {
        return UnsignedKt.m814uintDivideJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), i);
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m750divxj2QHRw(short s, short s2) {
        return UnsignedKt.m814uintDivideJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(s2 & MAX_VALUE));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m751equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m752equalsimpl0(short s, short s2) {
        return s == s2;
    }

    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m753floorDiv7apg3OU(short s, byte b) {
        return UnsignedKt.m814uintDivideJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(b & 255));
    }

    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m754floorDivVKZWuLQ(short s, long j) {
        return UnsignedKt.m816ulongDivideeb3DHEI(ULong.m639constructorimpl(s & 65535), j);
    }

    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m755floorDivWZ4Q5Ns(short s, int i) {
        return UnsignedKt.m814uintDivideJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), i);
    }

    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m756floorDivxj2QHRw(short s, short s2) {
        return UnsignedKt.m814uintDivideJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(s2 & MAX_VALUE));
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m757hashCodeimpl(short s) {
        return s;
    }

    /* renamed from: inc-Mh2AYeg, reason: not valid java name */
    private static final short m758incMh2AYeg(short s) {
        return m745constructorimpl((short) (s + 1));
    }

    /* renamed from: inv-Mh2AYeg, reason: not valid java name */
    private static final short m759invMh2AYeg(short s) {
        return m745constructorimpl((short) (~s));
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m760minus7apg3OU(short s, byte b) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) - UInt.m561constructorimpl(b & 255));
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m761minusVKZWuLQ(short s, long j) {
        return ULong.m639constructorimpl(ULong.m639constructorimpl(s & 65535) - j);
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m762minusWZ4Q5Ns(short s, int i) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) - i);
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m763minusxj2QHRw(short s, short s2) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) - UInt.m561constructorimpl(s2 & MAX_VALUE));
    }

    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m764mod7apg3OU(short s, byte b) {
        return UByte.m485constructorimpl((byte) UnsignedKt.m815uintRemainderJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(b & 255)));
    }

    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m765modVKZWuLQ(short s, long j) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(ULong.m639constructorimpl(s & 65535), j);
    }

    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m766modWZ4Q5Ns(short s, int i) {
        return UnsignedKt.m815uintRemainderJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), i);
    }

    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m767modxj2QHRw(short s, short s2) {
        return m745constructorimpl((short) UnsignedKt.m815uintRemainderJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(s2 & MAX_VALUE)));
    }

    /* renamed from: or-xj2QHRw, reason: not valid java name */
    private static final short m768orxj2QHRw(short s, short s2) {
        return m745constructorimpl((short) (s | s2));
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m769plus7apg3OU(short s, byte b) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) + UInt.m561constructorimpl(b & 255));
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m770plusVKZWuLQ(short s, long j) {
        return ULong.m639constructorimpl(ULong.m639constructorimpl(s & 65535) + j);
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m771plusWZ4Q5Ns(short s, int i) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) + i);
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m772plusxj2QHRw(short s, short s2) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) + UInt.m561constructorimpl(s2 & MAX_VALUE));
    }

    /* renamed from: rangeTo-xj2QHRw, reason: not valid java name */
    private static final UIntRange m773rangeToxj2QHRw(short s, short s2) {
        return new UIntRange(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(s2 & MAX_VALUE), null);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m774rem7apg3OU(short s, byte b) {
        return UnsignedKt.m815uintRemainderJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(b & 255));
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m775remVKZWuLQ(short s, long j) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(ULong.m639constructorimpl(s & 65535), j);
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m776remWZ4Q5Ns(short s, int i) {
        return UnsignedKt.m815uintRemainderJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), i);
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m777remxj2QHRw(short s, short s2) {
        return UnsignedKt.m815uintRemainderJ1ME1BU(UInt.m561constructorimpl(s & MAX_VALUE), UInt.m561constructorimpl(s2 & MAX_VALUE));
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m778times7apg3OU(short s, byte b) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) * UInt.m561constructorimpl(b & 255));
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m779timesVKZWuLQ(short s, long j) {
        return ULong.m639constructorimpl(ULong.m639constructorimpl(s & 65535) * j);
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m780timesWZ4Q5Ns(short s, int i) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) * i);
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m781timesxj2QHRw(short s, short s2) {
        return UInt.m561constructorimpl(UInt.m561constructorimpl(s & MAX_VALUE) * UInt.m561constructorimpl(s2 & MAX_VALUE));
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m782toByteimpl(short s) {
        return (byte) s;
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m783toDoubleimpl(short s) {
        return s & MAX_VALUE;
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m784toFloatimpl(short s) {
        return s & MAX_VALUE;
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m785toIntimpl(short s) {
        return s & MAX_VALUE;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m786toLongimpl(short s) {
        return s & 65535;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m787toShortimpl(short s) {
        return s;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m788toStringimpl(short s) {
        return String.valueOf(s & MAX_VALUE);
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m789toUBytew2LRezQ(short s) {
        return UByte.m485constructorimpl((byte) s);
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m790toUIntpVg5ArA(short s) {
        return UInt.m561constructorimpl(s & MAX_VALUE);
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m791toULongsVKNKU(short s) {
        return ULong.m639constructorimpl(s & 65535);
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m792toUShortMh2AYeg(short s) {
        return s;
    }

    /* renamed from: xor-xj2QHRw, reason: not valid java name */
    private static final short m793xorxj2QHRw(short s, short s2) {
        return m745constructorimpl((short) (s ^ s2));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.compare(getData() & MAX_VALUE, uShort.getData() & MAX_VALUE);
    }

    public boolean equals(Object obj) {
        return m751equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m757hashCodeimpl(this.data);
    }

    public String toString() {
        return m788toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ short getData() {
        return this.data;
    }
}
