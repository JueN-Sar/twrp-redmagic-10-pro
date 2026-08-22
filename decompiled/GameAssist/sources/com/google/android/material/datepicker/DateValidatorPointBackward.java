package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;

/* loaded from: classes.dex */
public class DateValidatorPointBackward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<DateValidatorPointBackward> CREATOR = new Parcelable.Creator<DateValidatorPointBackward>() { // from class: com.google.android.material.datepicker.DateValidatorPointBackward.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DateValidatorPointBackward createFromParcel(Parcel parcel) {
            return new DateValidatorPointBackward(parcel.readLong());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DateValidatorPointBackward[] newArray(int i2) {
            return new DateValidatorPointBackward[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final long f14456c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DateValidatorPointBackward) && this.f14456c == ((DateValidatorPointBackward) obj).f14456c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f14456c)});
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean j(long j2) {
        return j2 <= this.f14456c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f14456c);
    }

    private DateValidatorPointBackward(long j2) {
        this.f14456c = j2;
    }
}
