package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.transition.Transition;
import androidx.transition.TransitionUtils;
import java.util.Map;

/* loaded from: classes.dex */
public class ChangeImageTransform extends Transition {
    private static final String[] W = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    private static final TypeEvaluator X = new TypeEvaluator<Matrix>() { // from class: androidx.transition.ChangeImageTransform.1
        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
            return null;
        }
    };
    private static final Property Y = new Property<ImageView, Matrix>(Matrix.class, "animatedTransform") { // from class: androidx.transition.ChangeImageTransform.2
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix get(ImageView imageView) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(ImageView imageView, Matrix matrix) {
            ImageViewUtils.a(imageView, matrix);
        }
    };

    /* renamed from: androidx.transition.ChangeImageTransform$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5443a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            f5443a = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5443a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class Listener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final ImageView f5444c;

        /* renamed from: h, reason: collision with root package name */
        private final Matrix f5445h;

        /* renamed from: i, reason: collision with root package name */
        private final Matrix f5446i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f5447j = true;

        Listener(ImageView imageView, Matrix matrix, Matrix matrix2) {
            this.f5444c = imageView;
            this.f5445h = matrix;
            this.f5446i = matrix2;
        }

        private void a() {
            Matrix matrix = (Matrix) this.f5444c.getTag(R.id.transition_image_transform);
            if (matrix != null) {
                ImageViewUtils.a(this.f5444c, matrix);
                this.f5444c.setTag(R.id.transition_image_transform, null);
            }
        }

        private void i(Matrix matrix) {
            this.f5444c.setTag(R.id.transition_image_transform, matrix);
            ImageViewUtils.a(this.f5444c, this.f5446i);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            a();
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            if (this.f5447j) {
                i(this.f5445h);
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            this.f5447j = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            i((Matrix) ((ObjectAnimator) animator).getAnimatedValue());
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            a();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator, boolean z) {
            this.f5447j = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5447j = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f5447j = false;
        }
    }

    public ChangeImageTransform(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void q0(TransitionValues transitionValues, boolean z) {
        View view = transitionValues.f5571b;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() == null) {
                return;
            }
            Map map = transitionValues.f5570a;
            map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            Matrix matrix = z ? (Matrix) imageView.getTag(R.id.transition_image_transform) : null;
            if (matrix == null) {
                matrix = s0(imageView);
            }
            map.put("android:changeImageTransform:matrix", matrix);
        }
    }

    private static Matrix r0(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        float width = imageView.getWidth();
        float f2 = intrinsicWidth;
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float height = imageView.getHeight();
        float f3 = intrinsicHeight;
        float max = Math.max(width / f2, height / f3);
        int round = Math.round((width - (f2 * max)) / 2.0f);
        int round2 = Math.round((height - (f3 * max)) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.postScale(max, max);
        matrix.postTranslate(round, round2);
        return matrix;
    }

    private static Matrix s0(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return new Matrix(imageView.getImageMatrix());
        }
        int i2 = AnonymousClass3.f5443a[imageView.getScaleType().ordinal()];
        return i2 != 1 ? i2 != 2 ? new Matrix(imageView.getImageMatrix()) : r0(imageView) : v0(imageView);
    }

    private ObjectAnimator t0(ImageView imageView, Matrix matrix, Matrix matrix2) {
        return ObjectAnimator.ofObject(imageView, (Property<ImageView, V>) Y, new TransitionUtils.MatrixEvaluator(), matrix, matrix2);
    }

    private ObjectAnimator u0(ImageView imageView) {
        Property property = Y;
        TypeEvaluator typeEvaluator = X;
        Matrix matrix = MatrixUtils.f5493a;
        return ObjectAnimator.ofObject(imageView, (Property<ImageView, V>) property, typeEvaluator, matrix, matrix);
    }

    private static Matrix v0(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Matrix matrix = new Matrix();
        matrix.postScale(imageView.getWidth() / drawable.getIntrinsicWidth(), imageView.getHeight() / drawable.getIntrinsicHeight());
        return matrix;
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
        if (transitionValues != null && transitionValues2 != null) {
            Rect rect = (Rect) transitionValues.f5570a.get("android:changeImageTransform:bounds");
            Rect rect2 = (Rect) transitionValues2.f5570a.get("android:changeImageTransform:bounds");
            if (rect != null && rect2 != null) {
                Matrix matrix = (Matrix) transitionValues.f5570a.get("android:changeImageTransform:matrix");
                Matrix matrix2 = (Matrix) transitionValues2.f5570a.get("android:changeImageTransform:matrix");
                boolean z = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
                if (rect.equals(rect2) && z) {
                    return null;
                }
                ImageView imageView = (ImageView) transitionValues2.f5571b;
                Drawable drawable = imageView.getDrawable();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                    return u0(imageView);
                }
                if (matrix == null) {
                    matrix = MatrixUtils.f5493a;
                }
                if (matrix2 == null) {
                    matrix2 = MatrixUtils.f5493a;
                }
                Y.set(imageView, matrix);
                ObjectAnimator t0 = t0(imageView, matrix, matrix2);
                Listener listener = new Listener(imageView, matrix, matrix2);
                t0.addListener(listener);
                t0.addPauseListener(listener);
                a(listener);
                return t0;
            }
        }
        return null;
    }
}
