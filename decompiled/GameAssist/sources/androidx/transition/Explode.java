package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class Explode extends Visibility {
    private static final TimeInterpolator Z = new DecelerateInterpolator();
    private static final TimeInterpolator a0 = new AccelerateInterpolator();
    private int[] Y;

    public Explode(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Y = new int[2];
        m0(new CircularPropagation());
    }

    private void A0(View view, Rect rect, int[] iArr) {
        int centerY;
        int i2;
        view.getLocationOnScreen(this.Y);
        int[] iArr2 = this.Y;
        int i3 = iArr2[0];
        int i4 = iArr2[1];
        Rect t = t();
        if (t == null) {
            i2 = (view.getWidth() / 2) + i3 + Math.round(view.getTranslationX());
            centerY = (view.getHeight() / 2) + i4 + Math.round(view.getTranslationY());
        } else {
            int centerX = t.centerX();
            centerY = t.centerY();
            i2 = centerX;
        }
        float centerX2 = rect.centerX() - i2;
        float centerY2 = rect.centerY() - centerY;
        if (centerX2 == 0.0f && centerY2 == 0.0f) {
            centerX2 = ((float) (Math.random() * 2.0d)) - 1.0f;
            centerY2 = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float y0 = y0(centerX2, centerY2);
        float z0 = z0(view, i2 - i3, centerY - i4);
        iArr[0] = Math.round((centerX2 / y0) * z0);
        iArr[1] = Math.round(z0 * (centerY2 / y0));
    }

    private void q0(TransitionValues transitionValues) {
        View view = transitionValues.f5571b;
        view.getLocationOnScreen(this.Y);
        int[] iArr = this.Y;
        int i2 = iArr[0];
        int i3 = iArr[1];
        transitionValues.f5570a.put("android:explode:screenBounds", new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3));
    }

    private static float y0(float f2, float f3) {
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    private static float z0(View view, int i2, int i3) {
        return y0(Math.max(i2, view.getWidth() - i2), Math.max(i3, view.getHeight() - i3));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        super.i(transitionValues);
        q0(transitionValues);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        super.l(transitionValues);
        q0(transitionValues);
    }

    @Override // androidx.transition.Visibility
    public Animator t0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues2.f5570a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        A0(viewGroup, rect, this.Y);
        int[] iArr = this.Y;
        return TranslationAnimationCreator.a(view, transitionValues2, rect.left, rect.top, translationX + iArr[0], translationY + iArr[1], translationX, translationY, Z, this);
    }

    @Override // androidx.transition.Visibility
    public Animator v0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        float f2;
        float f3;
        if (transitionValues == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues.f5570a.get("android:explode:screenBounds");
        int i2 = rect.left;
        int i3 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) transitionValues.f5571b.getTag(R.id.transition_position);
        if (iArr != null) {
            f2 = (r7 - rect.left) + translationX;
            f3 = (r0 - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f2 = translationX;
            f3 = translationY;
        }
        A0(viewGroup, rect, this.Y);
        int[] iArr2 = this.Y;
        return TranslationAnimationCreator.a(view, transitionValues, i2, i3, translationX, translationY, f2 + iArr2[0], f3 + iArr2[1], a0, this);
    }
}
