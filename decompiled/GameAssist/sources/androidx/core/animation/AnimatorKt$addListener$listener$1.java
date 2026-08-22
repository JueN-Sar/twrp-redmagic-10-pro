package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class AnimatorKt$addListener$listener$1 implements Animator.AnimatorListener {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f2610c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function1 f2611h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function1 f2612i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ Function1 f2613j;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f2612i.c(animator);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f2611h.c(animator);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        this.f2610c.c(animator);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f2613j.c(animator);
    }
}
