package androidx.core.transition;

import android.transition.Transition;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class TransitionKt$addListener$listener$1 implements Transition.TransitionListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Function1 f3256a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Function1 f3257b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f3258c;

    /* renamed from: d, reason: collision with root package name */
    final /* synthetic */ Function1 f3259d;

    /* renamed from: e, reason: collision with root package name */
    final /* synthetic */ Function1 f3260e;

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionCancel(Transition transition) {
        this.f3259d.c(transition);
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionEnd(Transition transition) {
        this.f3256a.c(transition);
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionPause(Transition transition) {
        this.f3258c.c(transition);
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionResume(Transition transition) {
        this.f3257b.c(transition);
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionStart(Transition transition) {
        this.f3260e.c(transition);
    }
}
