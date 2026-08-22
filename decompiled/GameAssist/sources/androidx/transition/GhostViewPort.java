package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class GhostViewPort extends ViewGroup implements GhostView {

    @Nullable
    private Matrix mMatrix;
    private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    int mReferences;
    ViewGroup mStartParent;
    View mStartView;
    final View mView;

    GhostViewPort(View view) {
        super(view.getContext());
        this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: androidx.transition.GhostViewPort.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                View view2;
                GhostViewPort.this.postInvalidateOnAnimation();
                GhostViewPort ghostViewPort = GhostViewPort.this;
                ViewGroup viewGroup = ghostViewPort.mStartParent;
                if (viewGroup == null || (view2 = ghostViewPort.mStartView) == null) {
                    return true;
                }
                viewGroup.endViewTransition(view2);
                GhostViewPort.this.mStartParent.postInvalidateOnAnimation();
                GhostViewPort ghostViewPort2 = GhostViewPort.this;
                ghostViewPort2.mStartParent = null;
                ghostViewPort2.mStartView = null;
                return true;
            }
        };
        this.mView = view;
        setWillNotDraw(false);
        setClipChildren(false);
        setLayerType(2, null);
    }

    static GhostViewPort b(View view, ViewGroup viewGroup, Matrix matrix) {
        int i2;
        GhostViewHolder ghostViewHolder;
        if (!(view.getParent() instanceof ViewGroup)) {
            throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
        }
        GhostViewHolder b2 = GhostViewHolder.b(viewGroup);
        GhostViewPort e2 = e(view);
        if (e2 == null || (ghostViewHolder = (GhostViewHolder) e2.getParent()) == b2) {
            i2 = 0;
        } else {
            i2 = e2.mReferences;
            ghostViewHolder.removeView(e2);
            e2 = null;
        }
        if (e2 == null) {
            if (matrix == null) {
                matrix = new Matrix();
                c(view, viewGroup, matrix);
            }
            e2 = new GhostViewPort(view);
            e2.h(matrix);
            if (b2 == null) {
                b2 = new GhostViewHolder(viewGroup);
            } else {
                b2.g();
            }
            d(viewGroup, b2);
            d(viewGroup, e2);
            b2.a(e2);
            e2.mReferences = i2;
        } else if (matrix != null) {
            e2.h(matrix);
        }
        e2.mReferences++;
        return e2;
    }

    static void c(View view, ViewGroup viewGroup, Matrix matrix) {
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        matrix.reset();
        ViewUtils.h(viewGroup2, matrix);
        matrix.preTranslate(-viewGroup2.getScrollX(), -viewGroup2.getScrollY());
        ViewUtils.i(viewGroup, matrix);
    }

    static void d(View view, View view2) {
        ViewUtils.e(view2, view2.getLeft(), view2.getTop(), view2.getLeft() + view.getWidth(), view2.getTop() + view.getHeight());
    }

    static GhostViewPort e(View view) {
        return (GhostViewPort) view.getTag(R.id.ghost_view);
    }

    static void f(View view) {
        GhostViewPort e2 = e(view);
        if (e2 != null) {
            int i2 = e2.mReferences - 1;
            e2.mReferences = i2;
            if (i2 <= 0) {
                ((GhostViewHolder) e2.getParent()).removeView(e2);
            }
        }
    }

    static void g(View view, GhostViewPort ghostViewPort) {
        view.setTag(R.id.ghost_view, ghostViewPort);
    }

    @Override // androidx.transition.GhostView
    public void a(ViewGroup viewGroup, View view) {
        this.mStartParent = viewGroup;
        this.mStartView = view;
    }

    void h(Matrix matrix) {
        this.mMatrix = matrix;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        g(this.mView, this);
        this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        ViewUtils.g(this.mView, 4);
        if (this.mView.getParent() != null) {
            ((View) this.mView.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        ViewUtils.g(this.mView, 0);
        g(this.mView, null);
        if (this.mView.getParent() != null) {
            ((View) this.mView.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        CanvasUtils.a(canvas, true);
        canvas.setMatrix(this.mMatrix);
        ViewUtils.g(this.mView, 0);
        this.mView.invalidate();
        ViewUtils.g(this.mView, 4);
        drawChild(canvas, this.mView, getDrawingTime());
        CanvasUtils.a(canvas, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View, androidx.transition.GhostView
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (e(this.mView) == this) {
            ViewUtils.g(this.mView, i2 == 0 ? 4 : 0);
        }
    }
}
