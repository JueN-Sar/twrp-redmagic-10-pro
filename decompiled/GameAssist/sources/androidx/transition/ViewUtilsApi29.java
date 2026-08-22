package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ViewUtilsApi29 extends ViewUtilsApi23 {
    ViewUtilsApi29() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // androidx.transition.ViewUtilsApi21, androidx.transition.ViewUtilsApi19
    public void e(View view, Matrix matrix) {
        view.setAnimationMatrix(matrix);
    }

    @Override // androidx.transition.ViewUtilsApi22, androidx.transition.ViewUtilsApi19
    public void f(View view, int i2, int i3, int i4, int i5) {
        view.setLeftTopRightBottom(i2, i3, i4, i5);
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void g(View view, float f2) {
        view.setTransitionAlpha(f2);
    }

    @Override // androidx.transition.ViewUtilsApi23, androidx.transition.ViewUtilsApi19
    public void h(View view, int i2) {
        view.setTransitionVisibility(i2);
    }

    @Override // androidx.transition.ViewUtilsApi21, androidx.transition.ViewUtilsApi19
    public void i(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // androidx.transition.ViewUtilsApi21, androidx.transition.ViewUtilsApi19
    public void j(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
