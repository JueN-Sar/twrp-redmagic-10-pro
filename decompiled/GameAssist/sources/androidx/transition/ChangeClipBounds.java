package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.transition.Transition;

/* loaded from: classes.dex */
public class ChangeClipBounds extends Transition {
    private static final String[] W = {"android:clipBounds:clip"};
    static final Rect X = new Rect();

    private static class Listener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final Rect f5440c;

        /* renamed from: h, reason: collision with root package name */
        private final Rect f5441h;

        /* renamed from: i, reason: collision with root package name */
        private final View f5442i;

        Listener(View view, Rect rect, Rect rect2) {
            this.f5442i = view;
            this.f5440c = rect;
            this.f5441h = rect2;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            this.f5442i.setClipBounds((Rect) this.f5442i.getTag(R.id.transition_clip));
            this.f5442i.setTag(R.id.transition_clip, null);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            Rect clipBounds = this.f5442i.getClipBounds();
            if (clipBounds == null) {
                clipBounds = ChangeClipBounds.X;
            }
            this.f5442i.setTag(R.id.transition_clip, clipBounds);
            this.f5442i.setClipBounds(this.f5441h);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            if (z) {
                this.f5442i.setClipBounds(this.f5440c);
            } else {
                this.f5442i.setClipBounds(this.f5441h);
            }
        }
    }

    public ChangeClipBounds(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void q0(TransitionValues transitionValues, boolean z) {
        View view = transitionValues.f5571b;
        if (view.getVisibility() == 8) {
            return;
        }
        Rect rect = z ? (Rect) view.getTag(R.id.transition_clip) : null;
        if (rect == null) {
            rect = view.getClipBounds();
        }
        Rect rect2 = rect != X ? rect : null;
        transitionValues.f5570a.put("android:clipBounds:clip", rect2);
        if (rect2 == null) {
            transitionValues.f5570a.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    @Override // androidx.transition.Transition
    public String[] J() {
        return W;
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        q0(transitionValues, false);
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        q0(transitionValues, true);
    }

    @Override // androidx.transition.Transition
    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.f5570a.containsKey("android:clipBounds:clip") || !transitionValues2.f5570a.containsKey("android:clipBounds:clip")) {
            return null;
        }
        Rect rect = (Rect) transitionValues.f5570a.get("android:clipBounds:clip");
        Rect rect2 = (Rect) transitionValues2.f5570a.get("android:clipBounds:clip");
        if (rect == null && rect2 == null) {
            return null;
        }
        Rect rect3 = rect == null ? (Rect) transitionValues.f5570a.get("android:clipBounds:bounds") : rect;
        Rect rect4 = rect2 == null ? (Rect) transitionValues2.f5570a.get("android:clipBounds:bounds") : rect2;
        if (rect3.equals(rect4)) {
            return null;
        }
        transitionValues2.f5571b.setClipBounds(rect);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(transitionValues2.f5571b, (Property<View, V>) ViewUtils.f5587c, new RectEvaluator(new Rect()), rect3, rect4);
        Listener listener = new Listener(transitionValues2.f5571b, rect, rect2);
        ofObject.addListener(listener);
        a(listener);
        return ofObject;
    }
}
