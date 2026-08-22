package androidx.transition;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        E0();
    }

    private void E0() {
        B0(1);
        s0(new Fade(2)).s0(new ChangeBounds()).s0(new Fade(1));
    }

    public AutoTransition(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        E0();
    }
}
