package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes.dex */
public final class AnimatorKt$addPauseListener$listener$1 implements Animator.AnimatorPauseListener {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f2614c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function1 f2615h;

    @Override // android.animation.Animator.AnimatorPauseListener
    public void onAnimationPause(Animator animator) {
        this.f2614c.c(animator);
    }

    @Override // android.animation.Animator.AnimatorPauseListener
    public void onAnimationResume(Animator animator) {
        this.f2615h.c(animator);
    }
}
