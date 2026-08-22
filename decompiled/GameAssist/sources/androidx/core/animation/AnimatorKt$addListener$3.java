package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class AnimatorKt$addListener$3 extends Lambda implements Function1<Animator, Unit> {
    public static final AnimatorKt$addListener$3 INSTANCE = new AnimatorKt$addListener$3();

    public AnimatorKt$addListener$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Animator) obj);
        return Unit.f18288a;
    }

    public final void d(Animator animator) {
    }
}
