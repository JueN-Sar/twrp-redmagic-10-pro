package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;

@RestrictTo
/* loaded from: classes.dex */
public class ViewUtils {

    public interface OnApplyWindowInsetsListener {
        WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding);
    }

    public static void b(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static Rect c(View view, View view2) {
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int i4 = i2 - iArr2[0];
        int i5 = i3 - iArr2[1];
        return new Rect(i4, i5, view2.getWidth() + i4, view2.getHeight() + i5);
    }

    public static Rect d(View view) {
        return e(view, 0);
    }

    public static Rect e(View view, int i2) {
        return new Rect(view.getLeft(), view.getTop() + i2, view.getRight(), view.getBottom() + i2);
    }

    public static void f(View view, AttributeSet attributeSet, int i2, int i3, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.Insets, i2, i3);
        final boolean z = obtainStyledAttributes.getBoolean(R.styleable.Insets_paddingBottomSystemWindowInsets, false);
        final boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.Insets_paddingLeftSystemWindowInsets, false);
        final boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.Insets_paddingRightSystemWindowInsets, false);
        obtainStyledAttributes.recycle();
        g(view, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils.1
            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public WindowInsetsCompat a(View view2, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding) {
                if (z) {
                    relativePadding.f14804d += windowInsetsCompat.i();
                }
                boolean p2 = ViewUtils.p(view2);
                if (z2) {
                    if (p2) {
                        relativePadding.f14803c += windowInsetsCompat.j();
                    } else {
                        relativePadding.f14801a += windowInsetsCompat.j();
                    }
                }
                if (z3) {
                    if (p2) {
                        relativePadding.f14801a += windowInsetsCompat.k();
                    } else {
                        relativePadding.f14803c += windowInsetsCompat.k();
                    }
                }
                relativePadding.a(view2);
                OnApplyWindowInsetsListener onApplyWindowInsetsListener2 = onApplyWindowInsetsListener;
                return onApplyWindowInsetsListener2 != null ? onApplyWindowInsetsListener2.a(view2, windowInsetsCompat, relativePadding) : windowInsetsCompat;
            }
        });
    }

    public static void g(View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        final RelativePadding relativePadding = new RelativePadding(ViewCompat.z(view), view.getPaddingTop(), ViewCompat.y(view), view.getPaddingBottom());
        ViewCompat.x0(view, new androidx.core.view.OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils.2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat a(View view2, WindowInsetsCompat windowInsetsCompat) {
                return OnApplyWindowInsetsListener.this.a(view2, windowInsetsCompat, new RelativePadding(relativePadding));
            }
        });
        u(view);
    }

    public static float h(Context context, int i2) {
        return TypedValue.applyDimension(1, i2, context.getResources().getDisplayMetrics());
    }

    public static Integer i(View view) {
        ColorStateList g2 = DrawableUtils.g(view.getBackground());
        if (g2 != null) {
            return Integer.valueOf(g2.getDefaultColor());
        }
        return null;
    }

    public static ViewGroup j(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static ViewOverlayImpl k(View view) {
        return m(j(view));
    }

    private static InputMethodManager l(View view) {
        return (InputMethodManager) ContextCompat.i(view.getContext(), InputMethodManager.class);
    }

    public static ViewOverlayImpl m(View view) {
        if (view == null) {
            return null;
        }
        return new ViewOverlayApi18(view);
    }

    public static float n(View view) {
        float f2 = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            f2 += ViewCompat.r((View) parent);
        }
        return f2;
    }

    public static void o(View view, boolean z) {
        WindowInsetsControllerCompat F;
        if (z && (F = ViewCompat.F(view)) != null) {
            F.a(WindowInsetsCompat.Type.a());
            return;
        }
        InputMethodManager l2 = l(view);
        if (l2 != null) {
            l2.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean p(View view) {
        return ViewCompat.v(view) == 1;
    }

    public static PorterDuff.Mode r(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static void s(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            t(view.getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public static void t(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public static void u(View view) {
        if (ViewCompat.M(view)) {
            ViewCompat.f0(view);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.internal.ViewUtils.3
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view2) {
                    view2.removeOnAttachStateChangeListener(this);
                    ViewCompat.f0(view2);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view2) {
                }
            });
        }
    }

    public static void v(final View view, final boolean z) {
        view.requestFocus();
        view.post(new Runnable() { // from class: com.google.android.material.internal.e
            @Override // java.lang.Runnable
            public final void run() {
                ViewUtils.w(view, z);
            }
        });
    }

    public static void w(View view, boolean z) {
        WindowInsetsControllerCompat F;
        if (!z || (F = ViewCompat.F(view)) == null) {
            l(view).showSoftInput(view, 1);
        } else {
            F.e(WindowInsetsCompat.Type.a());
        }
    }

    public static class RelativePadding {

        /* renamed from: a, reason: collision with root package name */
        public int f14801a;

        /* renamed from: b, reason: collision with root package name */
        public int f14802b;

        /* renamed from: c, reason: collision with root package name */
        public int f14803c;

        /* renamed from: d, reason: collision with root package name */
        public int f14804d;

        public RelativePadding(int i2, int i3, int i4, int i5) {
            this.f14801a = i2;
            this.f14802b = i3;
            this.f14803c = i4;
            this.f14804d = i5;
        }

        public void a(View view) {
            ViewCompat.y0(view, this.f14801a, this.f14802b, this.f14803c, this.f14804d);
        }

        public RelativePadding(RelativePadding relativePadding) {
            this.f14801a = relativePadding.f14801a;
            this.f14802b = relativePadding.f14802b;
            this.f14803c = relativePadding.f14803c;
            this.f14804d = relativePadding.f14804d;
        }
    }
}
