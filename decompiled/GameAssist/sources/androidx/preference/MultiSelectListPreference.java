package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.internal.AbstractMultiSelectListPreference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class MultiSelectListPreference extends AbstractMultiSelectListPreference {
    private CharSequence[] Y;
    private CharSequence[] Z;
    private Set a0;

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.a0 = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MultiSelectListPreference, i2, i3);
        this.Y = TypedArrayUtils.q(obtainStyledAttributes, R.styleable.MultiSelectListPreference_entries, R.styleable.MultiSelectListPreference_android_entries);
        this.Z = TypedArrayUtils.q(obtainStyledAttributes, R.styleable.MultiSelectListPreference_entryValues, R.styleable.MultiSelectListPreference_android_entryValues);
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.internal.AbstractMultiSelectListPreference
    public CharSequence[] G0() {
        return this.Y;
    }

    @Override // androidx.preference.internal.AbstractMultiSelectListPreference
    public CharSequence[] H0() {
        return this.Z;
    }

    @Override // androidx.preference.internal.AbstractMultiSelectListPreference
    public Set I0() {
        return this.a0;
    }

    @Override // androidx.preference.internal.AbstractMultiSelectListPreference
    public void J0(Set set) {
        this.a0.clear();
        this.a0.addAll(set);
        f0(set);
    }

    @Override // androidx.preference.Preference
    protected Object V(TypedArray typedArray, int i2) {
        CharSequence[] textArray = typedArray.getTextArray(i2);
        HashSet hashSet = new HashSet();
        for (CharSequence charSequence : textArray) {
            hashSet.add(charSequence.toString());
        }
        return hashSet;
    }

    @Override // androidx.preference.Preference
    protected void Y(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.Y(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.Y(savedState.getSuperState());
        J0(savedState.f4656c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable Z() {
        Parcelable Z = super.Z();
        if (K()) {
            return Z;
        }
        SavedState savedState = new SavedState(Z);
        savedState.f4656c = I0();
        return savedState;
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.MultiSelectListPreference.SavedState.1
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
        Set f4656c;

        SavedState(Parcel parcel) {
            super(parcel);
            int readInt = parcel.readInt();
            this.f4656c = new HashSet();
            String[] strArr = new String[readInt];
            parcel.readStringArray(strArr);
            Collections.addAll(this.f4656c, strArr);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f4656c.size());
            Set set = this.f4656c;
            parcel.writeStringArray((String[]) set.toArray(new String[set.size()]));
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }
}
