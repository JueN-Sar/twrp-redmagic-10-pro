package androidx.appcompat.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.graphics.drawable.DrawableContainerCompat;

/* loaded from: classes.dex */
public class StateListDrawableCompat extends DrawableContainerCompat {

    /* renamed from: s, reason: collision with root package name */
    private StateListState f418s;
    private boolean t;

    static class StateListState extends DrawableContainerCompat.DrawableContainerState {
        int[][] J;

        StateListState(StateListState stateListState, StateListDrawableCompat stateListDrawableCompat, Resources resources) {
            super(stateListState, stateListDrawableCompat, resources);
            if (stateListState != null) {
                this.J = stateListState.J;
            } else {
                this.J = new int[g()][];
            }
        }

        int B(int[] iArr, Drawable drawable) {
            int a2 = a(drawable);
            this.J[a2] = iArr;
            return a2;
        }

        int C(int[] iArr) {
            int[][] iArr2 = this.J;
            int i2 = i();
            for (int i3 = 0; i3 < i2; i3++) {
                if (StateSet.stateSetMatches(iArr2[i3], iArr)) {
                    return i3;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new StateListDrawableCompat(this, null);
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat.DrawableContainerState
        public void p(int i2, int i3) {
            super.p(i2, i3);
            int[][] iArr = new int[i3][];
            System.arraycopy(this.J, 0, iArr, 0, i2);
            this.J = iArr;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat.DrawableContainerState
        void t() {
            int[][] iArr = this.J;
            int[][] iArr2 = new int[iArr.length][];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[] iArr3 = this.J[length];
                iArr2[length] = iArr3 != null ? (int[]) iArr3.clone() : null;
            }
            this.J = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawableCompat(this, resources);
        }
    }

    StateListDrawableCompat(StateListState stateListState, Resources resources) {
        g(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    void clearMutated() {
        super.clearMutated();
        this.t = false;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    void g(DrawableContainerCompat.DrawableContainerState drawableContainerState) {
        super.g(drawableContainerState);
        if (drawableContainerState instanceof StateListState) {
            this.f418s = (StateListState) drawableContainerState;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public StateListState b() {
        return new StateListState(this.f418s, this, null);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    int[] j(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i2 = 0;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i3);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i4 = i2 + 1;
                if (!attributeSet.getAttributeBooleanValue(i3, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i2] = attributeNameResource;
                i2 = i4;
            }
        }
        return StateSet.trimStateSet(iArr, i2);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.t && super.mutate() == this) {
            this.f418s.t();
            this.t = true;
        }
        return this;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int C = this.f418s.C(iArr);
        if (C < 0) {
            C = this.f418s.C(StateSet.WILD_CARD);
        }
        return f(C) || onStateChange;
    }

    StateListDrawableCompat(StateListState stateListState) {
        if (stateListState != null) {
            g(stateListState);
        }
    }
}
