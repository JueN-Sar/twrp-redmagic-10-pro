package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;

/* loaded from: classes.dex */
public class DateValidatorPointForward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<DateValidatorPointForward> CREATOR = new Parcelable.Creator<DateValidatorPointForward>() { // from class: com.google.android.material.datepicker.DateValidatorPointForward.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DateValidatorPointForward createFromParcel(Parcel parcel) {
            return new DateValidatorPointForward(parcel.readLong());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DateValidatorPointForward[] newArray(int i2) {
            return new DateValidatorPointForward[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final long f14457c;

    public static DateValidatorPointForward a(long j2) {
        return new DateValidatorPointForward(j2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DateValidatorPointForward) && this.f14457c == ((DateValidatorPointForward) obj).f14457c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f14457c)});
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean j(long j2) {
        return j2 >= this.f14457c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f14457c);
    }

    private DateValidatorPointForward(long j2) {
        this.f14457c = j2;
    }
}
