package androidx.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class SidePropagation extends VisibilityPropagation {

    /* renamed from: b, reason: collision with root package name */
    private float f5506b = 3.0f;

    /* renamed from: c, reason: collision with root package name */
    private int f5507c = 80;

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0012, code lost:
    
        r4 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x001d, code lost:
    
        if (r5.getLayoutDirection() == 1) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000e, code lost:
    
        if (r5.getLayoutDirection() == 1) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
    
        r4 = 5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int h(android.view.View r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13) {
        /*
            r4 = this;
            int r4 = r4.f5507c
            r0 = 8388611(0x800003, float:1.1754948E-38)
            r1 = 1
            r2 = 3
            r3 = 5
            if (r4 != r0) goto L14
            int r4 = r5.getLayoutDirection()
            if (r4 != r1) goto L12
        L10:
            r4 = r3
            goto L20
        L12:
            r4 = r2
            goto L20
        L14:
            r0 = 8388613(0x800005, float:1.175495E-38)
            if (r4 != r0) goto L20
            int r4 = r5.getLayoutDirection()
            if (r4 != r1) goto L10
            goto L12
        L20:
            if (r4 == r2) goto L46
            if (r4 == r3) goto L3e
            r5 = 48
            if (r4 == r5) goto L36
            r5 = 80
            if (r4 == r5) goto L2e
            r4 = 0
            goto L4d
        L2e:
            int r7 = r7 - r11
            int r8 = r8 - r6
            int r4 = java.lang.Math.abs(r8)
            int r4 = r4 + r7
            goto L4d
        L36:
            int r13 = r13 - r7
            int r8 = r8 - r6
            int r4 = java.lang.Math.abs(r8)
            int r4 = r4 + r13
            goto L4d
        L3e:
            int r6 = r6 - r10
            int r9 = r9 - r7
            int r4 = java.lang.Math.abs(r9)
            int r4 = r4 + r6
            goto L4d
        L46:
            int r12 = r12 - r6
            int r9 = r9 - r7
            int r4 = java.lang.Math.abs(r9)
            int r4 = r4 + r12
        L4d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.SidePropagation.h(android.view.View, int, int, int, int, int, int, int, int):int");
    }

    private int i(ViewGroup viewGroup) {
        int i2 = this.f5507c;
        return (i2 == 3 || i2 == 5 || i2 == 8388611 || i2 == 8388613) ? viewGroup.getWidth() : viewGroup.getHeight();
    }

    @Override // androidx.transition.TransitionPropagation
    public long c(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i2;
        int i3;
        int i4;
        TransitionValues transitionValues3 = transitionValues;
        if (transitionValues3 == null && transitionValues2 == null) {
            return 0L;
        }
        Rect t = transition.t();
        if (transitionValues2 == null || e(transitionValues3) == 0) {
            i2 = -1;
        } else {
            transitionValues3 = transitionValues2;
            i2 = 1;
        }
        int f2 = f(transitionValues3);
        int g2 = g(transitionValues3);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(viewGroup.getTranslationX());
        int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        int width = round + viewGroup.getWidth();
        int height = round2 + viewGroup.getHeight();
        if (t != null) {
            i3 = t.centerX();
            i4 = t.centerY();
        } else {
            i3 = (round + width) / 2;
            i4 = (round2 + height) / 2;
        }
        float h2 = h(viewGroup, f2, g2, i3, i4, round, round2, width, height) / i(viewGroup);
        long s2 = transition.s();
        if (s2 < 0) {
            s2 = 300;
        }
        return Math.round(((s2 * i2) / this.f5506b) * h2);
    }

    public void j(int i2) {
        this.f5507c = i2;
    }
}
