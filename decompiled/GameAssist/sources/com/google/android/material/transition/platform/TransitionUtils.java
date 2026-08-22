package com.google.android.material.transition.platform;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.transition.PathMotion;
import android.transition.PatternPathMotion;
import android.transition.Transition;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.PathParser;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;

@RequiresApi
/* loaded from: classes.dex */
class TransitionUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final RectF f15768a = new RectF();

    interface CornerSizeBinaryOperator {
        CornerSize a(CornerSize cornerSize, CornerSize cornerSize2);
    }

    static float b(RectF rectF) {
        return rectF.width() * rectF.height();
    }

    static ShapeAppearanceModel c(ShapeAppearanceModel shapeAppearanceModel, final RectF rectF) {
        return shapeAppearanceModel.y(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.transition.platform.a
            @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
            public final CornerSize a(CornerSize cornerSize) {
                CornerSize b2;
                b2 = RelativeCornerSize.b(rectF, cornerSize);
                return b2;
            }
        });
    }

    static Shader d(int i2) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, i2, i2, Shader.TileMode.CLAMP);
    }

    static Object e(Object obj, Object obj2) {
        return obj != null ? obj : obj2;
    }

    static View f(View view, int i2) {
        String resourceName = view.getResources().getResourceName(i2);
        while (view != null) {
            if (view.getId() != i2) {
                Object parent = view.getParent();
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return view;
            }
        }
        throw new IllegalArgumentException(resourceName + " is not a valid ancestor");
    }

    static View g(View view, int i2) {
        View findViewById = view.findViewById(i2);
        return findViewById != null ? findViewById : f(view, i2);
    }

    static RectF h(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF(iArr[0], iArr[1], view.getWidth() + r1, view.getHeight() + r0);
    }

    static RectF i(View view) {
        return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    static Rect j(View view) {
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    private static boolean k(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return (shapeAppearanceModel.r().a(rectF) == 0.0f && shapeAppearanceModel.t().a(rectF) == 0.0f && shapeAppearanceModel.l().a(rectF) == 0.0f && shapeAppearanceModel.j().a(rectF) == 0.0f) ? false : true;
    }

    static float m(float f2, float f3, float f4) {
        return f2 + (f4 * (f3 - f2));
    }

    static float n(float f2, float f3, float f4, float f5, float f6) {
        return o(f2, f3, f4, f5, f6, false);
    }

    static float o(float f2, float f3, float f4, float f5, float f6, boolean z) {
        return (!z || (f6 >= 0.0f && f6 <= 1.0f)) ? f6 < f4 ? f2 : f6 > f5 ? f3 : m(f2, f3, (f6 - f4) / (f5 - f4)) : m(f2, f3, f6);
    }

    static int p(int i2, int i3, float f2, float f3, float f4) {
        return f4 < f2 ? i2 : f4 > f3 ? i3 : (int) m(i2, i3, (f4 - f2) / (f3 - f2));
    }

    static ShapeAppearanceModel q(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, final RectF rectF, final RectF rectF2, final float f2, final float f3, final float f4) {
        return f4 < f2 ? shapeAppearanceModel : f4 > f3 ? shapeAppearanceModel2 : x(shapeAppearanceModel, shapeAppearanceModel2, rectF, new CornerSizeBinaryOperator() { // from class: com.google.android.material.transition.platform.TransitionUtils.1
            @Override // com.google.android.material.transition.platform.TransitionUtils.CornerSizeBinaryOperator
            public CornerSize a(CornerSize cornerSize, CornerSize cornerSize2) {
                return new AbsoluteCornerSize(TransitionUtils.n(cornerSize.a(rectF), cornerSize2.a(rectF2), f2, f3, f4));
            }
        });
    }

    static boolean r(Transition transition, Context context, int i2) {
        int f2;
        if (i2 == 0 || transition.getDuration() != -1 || (f2 = MotionUtils.f(context, i2, -1)) == -1) {
            return false;
        }
        transition.setDuration(f2);
        return true;
    }

    static boolean s(Transition transition, Context context, int i2, TimeInterpolator timeInterpolator) {
        if (i2 == 0 || transition.getInterpolator() != null) {
            return false;
        }
        transition.setInterpolator(MotionUtils.g(context, i2, timeInterpolator));
        return true;
    }

    static boolean t(Transition transition, Context context, int i2) {
        PathMotion u;
        if (i2 == 0 || (u = u(context, i2)) == null) {
            return false;
        }
        transition.setPathMotion(u);
        return true;
    }

    static PathMotion u(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.type;
        if (i3 != 16) {
            if (i3 == 3) {
                return new PatternPathMotion(PathParser.e(String.valueOf(typedValue.string)));
            }
            throw new IllegalArgumentException("Motion path theme attribute must either be an enum value or path data string");
        }
        int i4 = typedValue.data;
        if (i4 == 0) {
            return null;
        }
        if (i4 == 1) {
            return new MaterialArcMotion();
        }
        throw new IllegalArgumentException("Invalid motion path type: " + i4);
    }

    private static int v(Canvas canvas, Rect rect, int i2) {
        RectF rectF = f15768a;
        rectF.set(rect);
        return canvas.saveLayerAlpha(rectF, i2);
    }

    static void w(Canvas canvas, Rect rect, float f2, float f3, float f4, int i2, CanvasCompat.CanvasOperation canvasOperation) {
        if (i2 <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(f2, f3);
        canvas.scale(f4, f4);
        if (i2 < 255) {
            v(canvas, rect, i2);
        }
        canvasOperation.a(canvas);
        canvas.restoreToCount(save);
    }

    static ShapeAppearanceModel x(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, CornerSizeBinaryOperator cornerSizeBinaryOperator) {
        return (k(shapeAppearanceModel, rectF) ? shapeAppearanceModel : shapeAppearanceModel2).v().F(cornerSizeBinaryOperator.a(shapeAppearanceModel.r(), shapeAppearanceModel2.r())).J(cornerSizeBinaryOperator.a(shapeAppearanceModel.t(), shapeAppearanceModel2.t())).w(cornerSizeBinaryOperator.a(shapeAppearanceModel.j(), shapeAppearanceModel2.j())).A(cornerSizeBinaryOperator.a(shapeAppearanceModel.l(), shapeAppearanceModel2.l())).m();
    }
}
