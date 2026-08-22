package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class AnimatorKt$addPauseListener$2 extends Lambda implements Function1<Animator, Unit> {
    public static final AnimatorKt$addPauseListener$2 INSTANCE = new AnimatorKt$addPauseListener$2();

    AnimatorKt$addPauseListener$2() {
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
