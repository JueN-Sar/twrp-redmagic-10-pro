package kotlin.text;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public enum CharDirectionality {
    UNDEFINED(-1),
    LEFT_TO_RIGHT(0),
    RIGHT_TO_LEFT(1),
    RIGHT_TO_LEFT_ARABIC(2),
    EUROPEAN_NUMBER(3),
    EUROPEAN_NUMBER_SEPARATOR(4),
    EUROPEAN_NUMBER_TERMINATOR(5),
    ARABIC_NUMBER(6),
    COMMON_NUMBER_SEPARATOR(7),
    NONSPACING_MARK(8),
    BOUNDARY_NEUTRAL(9),
    PARAGRAPH_SEPARATOR(10),
    SEGMENT_SEPARATOR(11),
    WHITESPACE(12),
    OTHER_NEUTRALS(13),
    LEFT_TO_RIGHT_EMBEDDING(14),
    LEFT_TO_RIGHT_OVERRIDE(15),
    RIGHT_TO_LEFT_EMBEDDING(16),
    RIGHT_TO_LEFT_OVERRIDE(17),
    POP_DIRECTIONAL_FORMAT(18);


    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Lazy<Map<Integer, CharDirectionality>> directionalityMap$delegate;
    private final int value;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Lazy<Map<Integer, CharDirectionality>> a2;
        a2 = LazyKt__LazyJVMKt.a(new Function0<Map<Integer, ? extends CharDirectionality>>() { // from class: kotlin.text.CharDirectionality$Companion$directionalityMap$2
            @Override // kotlin.jvm.functions.Function0
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Map a() {
                int c2;
                int a3;
                CharDirectionality[] values = CharDirectionality.values();
                c2 = MapsKt__MapsJVMKt.c(values.length);
                a3 = RangesKt___RangesKt.a(c2, 16);
                LinkedHashMap linkedHashMap = new LinkedHashMap(a3);
                for (CharDirectionality charDirectionality : values) {
                    linkedHashMap.put(Integer.valueOf(charDirectionality.d()), charDirectionality);
                }
                return linkedHashMap;
            }
        });
        directionalityMap$delegate = a2;
    }

    CharDirectionality(int i2) {
        this.value = i2;
    }

    public final int d() {
        return this.value;
    }
}
