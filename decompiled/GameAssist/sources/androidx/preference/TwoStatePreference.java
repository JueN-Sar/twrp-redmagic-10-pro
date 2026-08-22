package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public abstract class TwoStatePreference extends Preference {
    private CharSequence S;
    private CharSequence T;
    protected boolean U;
    private boolean V;
    private boolean W;

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public boolean A0() {
        return this.U;
    }

    public void B0(boolean z) {
        boolean z2 = this.U != z;
        if (z2 || !this.V) {
            this.U = z;
            this.V = true;
            c0(z);
            if (z2) {
                O(u0());
                N();
            }
        }
    }

    public void C0(boolean z) {
        this.W = z;
    }

    public void D0(CharSequence charSequence) {
        this.T = charSequence;
        if (A0()) {
            return;
        }
        N();
    }

    public void E0(CharSequence charSequence) {
        this.S = charSequence;
        if (A0()) {
            N();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void F0(android.view.View r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.widget.TextView
            if (r0 != 0) goto L5
            return
        L5:
            android.widget.TextView r4 = (android.widget.TextView) r4
            boolean r0 = r3.U
            r1 = 0
            if (r0 == 0) goto L1b
            java.lang.CharSequence r0 = r3.S
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L1b
            java.lang.CharSequence r0 = r3.S
            r4.setText(r0)
        L19:
            r0 = r1
            goto L2e
        L1b:
            boolean r0 = r3.U
            if (r0 != 0) goto L2d
            java.lang.CharSequence r0 = r3.T
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L2d
            java.lang.CharSequence r0 = r3.T
            r4.setText(r0)
            goto L19
        L2d:
            r0 = 1
        L2e:
            if (r0 == 0) goto L3e
            java.lang.CharSequence r3 = r3.F()
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L3e
            r4.setText(r3)
            r0 = r1
        L3e:
            if (r0 != 0) goto L41
            goto L43
        L41:
            r1 = 8
        L43:
            int r3 = r4.getVisibility()
            if (r1 == r3) goto L4c
            r4.setVisibility(r1)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.TwoStatePreference.F0(android.view.View):void");
    }

    protected void G0(PreferenceViewHolder preferenceViewHolder) {
        F0(preferenceViewHolder.N(android.R.id.summary));
    }

    @Override // androidx.preference.Preference
    protected void S() {
        super.S();
        boolean z = !A0();
        if (c(Boolean.valueOf(z))) {
            B0(z);
        }
    }

    @Override // androidx.preference.Preference
    protected Object V(TypedArray typedArray, int i2) {
        return Boolean.valueOf(typedArray.getBoolean(i2, false));
    }

    @Override // androidx.preference.Preference
    protected void Y(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.Y(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.Y(savedState.getSuperState());
        B0(savedState.f4767c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable Z() {
        Parcelable Z = super.Z();
        if (K()) {
            return Z;
        }
        SavedState savedState = new SavedState(Z);
        savedState.f4767c = A0();
        return savedState;
    }

    @Override // androidx.preference.Preference
    public boolean u0() {
        if (!this.W ? this.U : !this.U) {
            if (!super.u0()) {
                return false;
            }
        }
        return true;
    }

    static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.TwoStatePreference.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        boolean f4767c;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f4767c = parcel.readInt() == 1;
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f4767c ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
