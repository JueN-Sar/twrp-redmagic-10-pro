package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class RegexKt$fromInt$1$1 extends Lambda implements Function1<Enum<Object>, Boolean> {
    final /* synthetic */ int $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegexKt$fromInt$1$1(int i2) {
        super(1);
        this.$value = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Boolean c(Enum r2) {
        FlagEnum flagEnum = (FlagEnum) r2;
        return Boolean.valueOf((this.$value & flagEnum.c()) == flagEnum.getValue());
    }
}
