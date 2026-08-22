package androidx.core.widget;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class TextViewKt$addTextChangedListener$2 extends Lambda implements Function4<CharSequence, Integer, Integer, Integer, Unit> {
    public static final TextViewKt$addTextChangedListener$2 INSTANCE = new TextViewKt$addTextChangedListener$2();

    public TextViewKt$addTextChangedListener$2() {
        super(4);
    }

    public final void d(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Object j(Object obj, Object obj2, Object obj3, Object obj4) {
        d((CharSequence) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
        return Unit.f18288a;
    }
}
