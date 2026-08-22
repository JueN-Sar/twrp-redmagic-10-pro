package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public class ListPreference extends DialogPreference {
    private CharSequence[] Y;
    private CharSequence[] Z;
    private String a0;
    private String b0;
    private boolean c0;

    public ListPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPreference, i2, i3);
        this.Y = TypedArrayUtils.q(obtainStyledAttributes, R.styleable.ListPreference_entries, R.styleable.ListPreference_android_entries);
        this.Z = TypedArrayUtils.q(obtainStyledAttributes, R.styleable.ListPreference_entryValues, R.styleable.ListPreference_android_entryValues);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.Preference, i2, i3);
        this.b0 = TypedArrayUtils.o(obtainStyledAttributes2, R.styleable.Preference_summary, R.styleable.Preference_android_summary);
        obtainStyledAttributes2.recycle();
    }

    private int L0() {
        return G0(this.a0);
    }

    @Override // androidx.preference.Preference
    public CharSequence F() {
        CharSequence I0 = I0();
        String str = this.b0;
        if (str == null) {
            return super.F();
        }
        if (I0 == null) {
            I0 = "";
        }
        return String.format(str, I0);
    }

    public int G0(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.Z) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (this.Z[length].equals(str)) {
                return length;
            }
        }
        return -1;
    }

    public CharSequence[] H0() {
        return this.Y;
    }

    public CharSequence I0() {
        CharSequence[] charSequenceArr;
        int L0 = L0();
        if (L0 < 0 || (charSequenceArr = this.Y) == null) {
            return null;
        }
        return charSequenceArr[L0];
    }

    public CharSequence[] J0() {
        return this.Z;
    }

    public String K0() {
        return this.a0;
    }

    public void M0(String str) {
        boolean z = !TextUtils.equals(this.a0, str);
        if (z || !this.c0) {
            this.a0 = str;
            this.c0 = true;
            e0(str);
            if (z) {
                N();
            }
        }
    }

    @Override // androidx.preference.Preference
    protected Object V(TypedArray typedArray, int i2) {
        return typedArray.getString(i2);
    }

    @Override // androidx.preference.Preference
    protected void Y(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.Y(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.Y(savedState.getSuperState());
        M0(savedState.f4650c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable Z() {
        Parcelable Z = super.Z();
        if (K()) {
            return Z;
        }
        SavedState savedState = new SavedState(Z);
        savedState.f4650c = K0();
        return savedState;
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.ListPreference.SavedState.1
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
        String f4650c;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f4650c = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f4650c);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }
}
