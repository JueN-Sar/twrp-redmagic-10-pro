package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class ChangeScroll extends Transition {
    private static final String[] W = {"android:changeScroll:x", "android:changeScroll:y"};

    public ChangeScroll(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void q0(TransitionValues transitionValues) {
        transitionValues.f5570a.put("android:changeScroll:x", Integer.valueOf(transitionValues.f5571b.getScrollX()));
        transitionValues.f5570a.put("android:changeScroll:y", Integer.valueOf(transitionValues.f5571b.getScrollY()));
    }

    @Override // androidx.transition.Transition
    public String[] J() {
        return W;
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        q0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        q0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2 = null;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        View view = transitionValues2.f5571b;
        int intValue = ((Integer) transitionValues.f5570a.get("android:changeScroll:x")).intValue();
        int intValue2 = ((Integer) transitionValues2.f5570a.get("android:changeScroll:x")).intValue();
        int intValue3 = ((Integer) transitionValues.f5570a.get("android:changeScroll:y")).intValue();
        int intValue4 = ((Integer) transitionValues2.f5570a.get("android:changeScroll:y")).intValue();
        if (intValue != intValue2) {
            view.setScrollX(intValue);
            objectAnimator = ObjectAnimator.ofInt(view, "scrollX", intValue, intValue2);
        } else {
            objectAnimator = null;
        }
        if (intValue3 != intValue4) {
            view.setScrollY(intValue3);
            objectAnimator2 = ObjectAnimator.ofInt(view, "scrollY", intValue3, intValue4);
        }
        return TransitionUtils.c(objectAnimator, objectAnimator2);
    }
}
