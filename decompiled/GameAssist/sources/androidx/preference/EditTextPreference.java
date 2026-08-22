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
public class EditTextPreference extends DialogPreference {
    private String Y;

    public EditTextPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public String G0() {
        return this.Y;
    }

    public void H0(String str) {
        boolean u0 = u0();
        this.Y = str;
        e0(str);
        boolean u02 = u0();
        if (u02 != u0) {
            O(u02);
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
        H0(savedState.f4647c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable Z() {
        Parcelable Z = super.Z();
        if (K()) {
            return Z;
        }
        SavedState savedState = new SavedState(Z);
        savedState.f4647c = G0();
        return savedState;
    }

    @Override // androidx.preference.Preference
    public boolean u0() {
        return TextUtils.isEmpty(this.Y) || super.u0();
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.EditTextPreference.SavedState.1
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
        String f4647c;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f4647c = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f4647c);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public EditTextPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.editTextPreferenceStyle, android.R.attr.editTextPreferenceStyle));
    }
}
