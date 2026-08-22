package androidx.core.transition;

import android.transition.Transition;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class TransitionKt$doOnCancel$$inlined$addListener$default$1 implements Transition.TransitionListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Function1 f3251a;

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionCancel(Transition transition) {
        this.f3251a.c(transition);
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionEnd(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionStart(Transition transition) {
    }
}
