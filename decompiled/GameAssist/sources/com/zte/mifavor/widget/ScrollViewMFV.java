package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EdgeEffect;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class ScrollViewMFV extends android.widget.ScrollView {
    private static final float SCROLL_RATIO = 0.45f;
    private static final String TAG = "ScrollViewMFV";
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mHasMfvFrameworkMod;

    public ScrollViewMFV(Context context) {
        super(context);
        this.mHasMfvFrameworkMod = false;
        initScrollViewMFV();
    }

    @Override // android.widget.ScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mHasMfvFrameworkMod) {
            EdgeEffect edgeEffect = this.mEdgeGlowTop;
            if (edgeEffect != null) {
                edgeEffect.finish();
            }
            EdgeEffect edgeEffect2 = this.mEdgeGlowBottom;
            if (edgeEffect2 != null) {
                edgeEffect2.finish();
            }
        }
        super.draw(canvas);
    }

    public void initScrollViewMFV() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r2 != 0) goto L9;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean overScrollBy(int r11, int r12, int r13, int r14, int r15, int r16, int r17, int r18, boolean r19) {
        /*
            r10 = this;
            r0 = r10
            boolean r1 = r0.mHasMfvFrameworkMod
            if (r1 == 0) goto Lf
            r1 = r12
            float r2 = (float) r1
            r3 = 1055286886(0x3ee66666, float:0.45)
            float r2 = r2 * r3
            int r2 = (int) r2
            if (r2 == 0) goto L10
            goto L11
        Lf:
            r1 = r12
        L10:
            r2 = r1
        L11:
            r0 = r10
            r1 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            boolean r0 = super.overScrollBy(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.ScrollViewMFV.overScrollBy(int, int, int, int, int, int, int, int, boolean):boolean");
    }

    @Override // android.view.View
    public void setOverScrollMode(int i2) {
        super.setOverScrollMode(i2);
        if (this.mHasMfvFrameworkMod) {
            try {
                Field declaredField = android.widget.ScrollView.class.getDeclaredField("mEdgeGlowTop");
                declaredField.setAccessible(true);
                this.mEdgeGlowTop = (EdgeEffect) declaredField.get(this);
                Field declaredField2 = android.widget.ScrollView.class.getDeclaredField("mEdgeGlowBottom");
                declaredField2.setAccessible(true);
                this.mEdgeGlowBottom = (EdgeEffect) declaredField2.get(this);
            } catch (Exception e2) {
                Log.e(TAG, "setOverScrollMode e = " + e2);
            }
        }
    }

    public ScrollViewMFV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHasMfvFrameworkMod = false;
        initScrollViewMFV();
    }

    public ScrollViewMFV(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHasMfvFrameworkMod = false;
        initScrollViewMFV();
    }

    public ScrollViewMFV(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mHasMfvFrameworkMod = false;
        initScrollViewMFV();
    }
}
