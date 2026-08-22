package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
class TransitionUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f5566a = true;

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static Bitmap a(Picture picture) {
            return Bitmap.createBitmap(picture);
        }
    }

    static class MatrixEvaluator implements TypeEvaluator<Matrix> {

        /* renamed from: a, reason: collision with root package name */
        final float[] f5567a = new float[9];

        /* renamed from: b, reason: collision with root package name */
        final float[] f5568b = new float[9];

        /* renamed from: c, reason: collision with root package name */
        final Matrix f5569c = new Matrix();

        MatrixEvaluator() {
        }

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
            matrix.getValues(this.f5567a);
            matrix2.getValues(this.f5568b);
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f5568b;
                float f3 = fArr[i2];
                float f4 = this.f5567a[i2];
                fArr[i2] = f4 + ((f3 - f4) * f2);
            }
            this.f5569c.setValues(this.f5568b);
            return this.f5569c;
        }
    }

    static View a(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(-view2.getScrollX(), -view2.getScrollY());
        ViewUtils.h(view, matrix);
        ViewUtils.i(viewGroup, matrix);
        RectF rectF = new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        matrix.mapRect(rectF);
        int round = Math.round(rectF.left);
        int round2 = Math.round(rectF.top);
        int round3 = Math.round(rectF.right);
        int round4 = Math.round(rectF.bottom);
        ImageView imageView = new ImageView(view.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap b2 = b(view, matrix, rectF, viewGroup);
        if (b2 != null) {
            imageView.setImageBitmap(b2);
        }
        imageView.measure(View.MeasureSpec.makeMeasureSpec(round3 - round, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(round4 - round2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
        imageView.layout(round, round2, round3, round4);
        return imageView;
    }

    private static Bitmap b(View view, Matrix matrix, RectF rectF, ViewGroup viewGroup) {
        ViewGroup viewGroup2;
        boolean z = !view.isAttachedToWindow();
        int i2 = 0;
        boolean z2 = viewGroup != null && viewGroup.isAttachedToWindow();
        Bitmap bitmap = null;
        if (!z) {
            viewGroup2 = null;
        } else {
            if (!z2) {
                return null;
            }
            viewGroup2 = (ViewGroup) view.getParent();
            i2 = viewGroup2.indexOfChild(view);
            viewGroup.getOverlay().add(view);
        }
        int round = Math.round(rectF.width());
        int round2 = Math.round(rectF.height());
        if (round > 0 && round2 > 0) {
            float min = Math.min(1.0f, 1048576.0f / (round * round2));
            int round3 = Math.round(round * min);
            int round4 = Math.round(round2 * min);
            matrix.postTranslate(-rectF.left, -rectF.top);
            matrix.postScale(min, min);
            if (f5566a) {
                Picture picture = new Picture();
                Canvas beginRecording = picture.beginRecording(round3, round4);
                beginRecording.concat(matrix);
                view.draw(beginRecording);
                picture.endRecording();
                bitmap = Api28Impl.a(picture);
            } else {
                bitmap = Bitmap.createBitmap(round3, round4, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.concat(matrix);
                view.draw(canvas);
            }
        }
        if (z) {
            viewGroup.getOverlay().remove(view);
            viewGroup2.addView(view, i2);
        }
        return bitmap;
    }

    static Animator c(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animator2);
        return animatorSet;
    }
}
