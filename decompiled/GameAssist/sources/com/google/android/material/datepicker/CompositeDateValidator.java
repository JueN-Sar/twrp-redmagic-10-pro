package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.util.Preconditions;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {

    /* renamed from: c, reason: collision with root package name */
    private final Operator f14446c;

    /* renamed from: h, reason: collision with root package name */
    private final List f14447h;

    /* renamed from: i, reason: collision with root package name */
    private static final Operator f14444i = new Operator() { // from class: com.google.android.material.datepicker.CompositeDateValidator.1
        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public boolean a(List list, long j2) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) it.next();
                if (dateValidator != null && dateValidator.j(j2)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public int getId() {
            return 1;
        }
    };

    /* renamed from: j, reason: collision with root package name */
    private static final Operator f14445j = new Operator() { // from class: com.google.android.material.datepicker.CompositeDateValidator.2
        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public boolean a(List list, long j2) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) it.next();
                if (dateValidator != null && !dateValidator.j(j2)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public int getId() {
            return 2;
        }
    };
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new Parcelable.Creator<CompositeDateValidator>() { // from class: com.google.android.material.datepicker.CompositeDateValidator.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator createFromParcel(Parcel parcel) {
            ArrayList readArrayList = parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader());
            int readInt = parcel.readInt();
            return new CompositeDateValidator((List) Preconditions.h(readArrayList), readInt == 2 ? CompositeDateValidator.f14445j : readInt == 1 ? CompositeDateValidator.f14444i : CompositeDateValidator.f14445j);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator[] newArray(int i2) {
            return new CompositeDateValidator[i2];
        }
    };

    private interface Operator {
        boolean a(List list, long j2);

        int getId();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompositeDateValidator)) {
            return false;
        }
        CompositeDateValidator compositeDateValidator = (CompositeDateValidator) obj;
        return this.f14447h.equals(compositeDateValidator.f14447h) && this.f14446c.getId() == compositeDateValidator.f14446c.getId();
    }

    public int hashCode() {
        return this.f14447h.hashCode();
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean j(long j2) {
        return this.f14446c.a(this.f14447h, j2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.f14447h);
        parcel.writeInt(this.f14446c.getId());
    }

    private CompositeDateValidator(List list, Operator operator) {
        this.f14447h = list;
        this.f14446c = operator;
    }
}
