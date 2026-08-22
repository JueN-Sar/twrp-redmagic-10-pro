package androidx.viewpager2.widget;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes.dex */
final class AnimateLayoutChangeDetector {

    /* renamed from: b, reason: collision with root package name */
    private static final ViewGroup.MarginLayoutParams f5805b;

    /* renamed from: a, reason: collision with root package name */
    private LinearLayoutManager f5806a;

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f5805b = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    AnimateLayoutChangeDetector(LinearLayoutManager linearLayoutManager) {
        this.f5806a = linearLayoutManager;
    }

    private boolean a() {
        int top;
        int i2;
        int bottom;
        int i3;
        int P = this.f5806a.P();
        if (P == 0) {
            return true;
        }
        boolean z = this.f5806a.y2() == 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, P, 2);
        for (int i4 = 0; i4 < P; i4++) {
            View O = this.f5806a.O(i4);
            if (O == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = O.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : f5805b;
            int[] iArr2 = iArr[i4];
            if (z) {
                top = O.getLeft();
                i2 = marginLayoutParams.leftMargin;
            } else {
                top = O.getTop();
                i2 = marginLayoutParams.topMargin;
            }
            iArr2[0] = top - i2;
            int[] iArr3 = iArr[i4];
            if (z) {
                bottom = O.getRight();
                i3 = marginLayoutParams.rightMargin;
            } else {
                bottom = O.getBottom();
                i3 = marginLayoutParams.bottomMargin;
            }
            iArr3[1] = bottom + i3;
        }
        Arrays.sort(iArr, new Comparator<int[]>() { // from class: androidx.viewpager2.widget.AnimateLayoutChangeDetector.1
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(int[] iArr4, int[] iArr5) {
                return iArr4[0] - iArr5[0];
            }
        });
        for (int i5 = 1; i5 < P; i5++) {
            if (iArr[i5 - 1][1] != iArr[i5][0]) {
                return false;
            }
        }
        int[] iArr4 = iArr[0];
        int i6 = iArr4[1];
        int i7 = iArr4[0];
        return i7 <= 0 && iArr[P - 1][1] >= i6 - i7;
    }

    private boolean b() {
        int P = this.f5806a.P();
        for (int i2 = 0; i2 < P; i2++) {
            if (c(this.f5806a.O(i2))) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                return true;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (c(viewGroup.getChildAt(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean d() {
        return (!a() || this.f5806a.P() <= 1) && b();
    }
}
