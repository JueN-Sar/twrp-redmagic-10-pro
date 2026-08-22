package kotlin;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.jvm.JvmInline;
import kotlin.ranges.ULongRange;

/* compiled from: ULong.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 |2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001|B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001dJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u001fJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010\u000bJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u0016\u00102\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0005J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001dJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u001fJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\"J\u001b\u00109\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u0013J\u001b\u00109\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010\u000bJ\u001b\u00109\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bA\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u001dJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\"J\u001b\u0010G\u001a\u00020H2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u001dJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bM\u0010\u001fJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000bJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\"J\u001e\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bR\u0010\u001fJ\u001e\u0010S\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bV\u0010\u001dJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bW\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bX\u0010\u000bJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bY\u0010\"J\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\rH\u0087\b¢\u0006\u0004\bg\u0010/J\u0010\u0010h\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bi\u0010\u0005J\u0010\u0010j\u001a\u00020kH\u0087\b¢\u0006\u0004\bl\u0010mJ\u000f\u0010n\u001a\u00020oH\u0016¢\u0006\u0004\bp\u0010qJ\u0016\u0010r\u001a\u00020\u000eH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bs\u0010]J\u0016\u0010t\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bu\u0010/J\u0016\u0010v\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010\u0005J\u0016\u0010x\u001a\u00020\u0016H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\by\u0010mJ\u001b\u0010z\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b{\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006}"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-s-VKNKU", TtmlNode.TAG_DIV, "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(J)I", "inc", "inc-s-VKNKU", "inv", "inv-s-VKNKU", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(JB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
@JvmInline
/* loaded from: classes2.dex */
public final class ULong implements Comparable<ULong> {
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* renamed from: and-VKZWuLQ, reason: not valid java name */
    private static final long m632andVKZWuLQ(long j, long j2) {
        return m639constructorimpl(j & j2);
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ULong m633boximpl(long j) {
        return new ULong(j);
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m634compareTo7apg3OU(long j, byte b) {
        return UnsignedKt.ulongCompare(j, m639constructorimpl(b & 255));
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private int m635compareToVKZWuLQ(long j) {
        return UnsignedKt.ulongCompare(getData(), j);
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static int m636compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m637compareToWZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongCompare(j, m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m638compareToxj2QHRw(long j, short s) {
        return UnsignedKt.ulongCompare(j, m639constructorimpl(s & 65535));
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m639constructorimpl(long j) {
        return j;
    }

    /* renamed from: dec-s-VKNKU, reason: not valid java name */
    private static final long m640decsVKNKU(long j) {
        return m639constructorimpl(j - 1);
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final long m641div7apg3OU(long j, byte b) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, m639constructorimpl(b & 255));
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m642divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, j2);
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final long m643divWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final long m644divxj2QHRw(long j, short s) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, m639constructorimpl(s & 65535));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m645equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m646equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final long m647floorDiv7apg3OU(long j, byte b) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, m639constructorimpl(b & 255));
    }

    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m648floorDivVKZWuLQ(long j, long j2) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, j2);
    }

    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final long m649floorDivWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final long m650floorDivxj2QHRw(long j, short s) {
        return UnsignedKt.m816ulongDivideeb3DHEI(j, m639constructorimpl(s & 65535));
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m651hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: inc-s-VKNKU, reason: not valid java name */
    private static final long m652incsVKNKU(long j) {
        return m639constructorimpl(j + 1);
    }

    /* renamed from: inv-s-VKNKU, reason: not valid java name */
    private static final long m653invsVKNKU(long j) {
        return m639constructorimpl(~j);
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final long m654minus7apg3OU(long j, byte b) {
        return m639constructorimpl(j - m639constructorimpl(b & 255));
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m655minusVKZWuLQ(long j, long j2) {
        return m639constructorimpl(j - j2);
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final long m656minusWZ4Q5Ns(long j, int i) {
        return m639constructorimpl(j - m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final long m657minusxj2QHRw(long j, short s) {
        return m639constructorimpl(j - m639constructorimpl(s & 65535));
    }

    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m658mod7apg3OU(long j, byte b) {
        return UByte.m485constructorimpl((byte) UnsignedKt.m817ulongRemaindereb3DHEI(j, m639constructorimpl(b & 255)));
    }

    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m659modVKZWuLQ(long j, long j2) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(j, j2);
    }

    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m660modWZ4Q5Ns(long j, int i) {
        return UInt.m561constructorimpl((int) UnsignedKt.m817ulongRemaindereb3DHEI(j, m639constructorimpl(i & 4294967295L)));
    }

    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m661modxj2QHRw(long j, short s) {
        return UShort.m745constructorimpl((short) UnsignedKt.m817ulongRemaindereb3DHEI(j, m639constructorimpl(s & 65535)));
    }

    /* renamed from: or-VKZWuLQ, reason: not valid java name */
    private static final long m662orVKZWuLQ(long j, long j2) {
        return m639constructorimpl(j | j2);
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final long m663plus7apg3OU(long j, byte b) {
        return m639constructorimpl(j + m639constructorimpl(b & 255));
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m664plusVKZWuLQ(long j, long j2) {
        return m639constructorimpl(j + j2);
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final long m665plusWZ4Q5Ns(long j, int i) {
        return m639constructorimpl(j + m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final long m666plusxj2QHRw(long j, short s) {
        return m639constructorimpl(j + m639constructorimpl(s & 65535));
    }

    /* renamed from: rangeTo-VKZWuLQ, reason: not valid java name */
    private static final ULongRange m667rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2, null);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final long m668rem7apg3OU(long j, byte b) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(j, m639constructorimpl(b & 255));
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m669remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(j, j2);
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final long m670remWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(j, m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final long m671remxj2QHRw(long j, short s) {
        return UnsignedKt.m817ulongRemaindereb3DHEI(j, m639constructorimpl(s & 65535));
    }

    /* renamed from: shl-s-VKNKU, reason: not valid java name */
    private static final long m672shlsVKNKU(long j, int i) {
        return m639constructorimpl(j << i);
    }

    /* renamed from: shr-s-VKNKU, reason: not valid java name */
    private static final long m673shrsVKNKU(long j, int i) {
        return m639constructorimpl(j >>> i);
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final long m674times7apg3OU(long j, byte b) {
        return m639constructorimpl(j * m639constructorimpl(b & 255));
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m675timesVKZWuLQ(long j, long j2) {
        return m639constructorimpl(j * j2);
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final long m676timesWZ4Q5Ns(long j, int i) {
        return m639constructorimpl(j * m639constructorimpl(i & 4294967295L));
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final long m677timesxj2QHRw(long j, short s) {
        return m639constructorimpl(j * m639constructorimpl(s & 65535));
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m678toByteimpl(long j) {
        return (byte) j;
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m679toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m680toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m681toIntimpl(long j) {
        return (int) j;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m682toLongimpl(long j) {
        return j;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m683toShortimpl(long j) {
        return (short) j;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m684toStringimpl(long j) {
        return UnsignedKt.ulongToString(j);
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m685toUBytew2LRezQ(long j) {
        return UByte.m485constructorimpl((byte) j);
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m686toUIntpVg5ArA(long j) {
        return UInt.m561constructorimpl((int) j);
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m687toULongsVKNKU(long j) {
        return j;
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m688toUShortMh2AYeg(long j) {
        return UShort.m745constructorimpl((short) j);
    }

    /* renamed from: xor-VKZWuLQ, reason: not valid java name */
    private static final long m689xorVKZWuLQ(long j, long j2) {
        return m639constructorimpl(j ^ j2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(getData(), uLong.getData());
    }

    public boolean equals(Object obj) {
        return m645equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m651hashCodeimpl(this.data);
    }

    public String toString() {
        return m684toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getData() {
        return this.data;
    }
}
