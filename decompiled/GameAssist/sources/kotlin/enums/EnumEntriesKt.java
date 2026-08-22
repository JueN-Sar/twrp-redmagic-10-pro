package kotlin.enums;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class EnumEntriesKt {
    public static final EnumEntries a(final Enum[] entries) {
        Intrinsics.e(entries, "entries");
        EnumEntriesList enumEntriesList = new EnumEntriesList(new Function0<Enum<Object>[]>() { // from class: kotlin.enums.EnumEntriesKt$enumEntries$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Enum[] a() {
                return entries;
            }
        });
        enumEntriesList.size();
        return enumEntriesList;
    }
}
