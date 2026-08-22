package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new Parcelable.Creator<CalendarConstraints>() { // from class: com.google.android.material.datepicker.CalendarConstraints.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints[] newArray(int i2) {
            return new CalendarConstraints[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final Month f14416c;

    /* renamed from: h, reason: collision with root package name */
    private final Month f14417h;

    /* renamed from: i, reason: collision with root package name */
    private final DateValidator f14418i;

    /* renamed from: j, reason: collision with root package name */
    private Month f14419j;

    /* renamed from: k, reason: collision with root package name */
    private final int f14420k;

    /* renamed from: l, reason: collision with root package name */
    private final int f14421l;

    /* renamed from: m, reason: collision with root package name */
    private final int f14422m;

    public static final class Builder {

        /* renamed from: f, reason: collision with root package name */
        static final long f14423f = UtcDates.a(Month.f(1900, 0).f14496l);

        /* renamed from: g, reason: collision with root package name */
        static final long f14424g = UtcDates.a(Month.f(2100, 11).f14496l);

        /* renamed from: a, reason: collision with root package name */
        private long f14425a;

        /* renamed from: b, reason: collision with root package name */
        private long f14426b;

        /* renamed from: c, reason: collision with root package name */
        private Long f14427c;

        /* renamed from: d, reason: collision with root package name */
        private int f14428d;

        /* renamed from: e, reason: collision with root package name */
        private DateValidator f14429e;

        Builder(CalendarConstraints calendarConstraints) {
            this.f14425a = f14423f;
            this.f14426b = f14424g;
            this.f14429e = DateValidatorPointForward.a(Long.MIN_VALUE);
            this.f14425a = calendarConstraints.f14416c.f14496l;
            this.f14426b = calendarConstraints.f14417h.f14496l;
            this.f14427c = Long.valueOf(calendarConstraints.f14419j.f14496l);
            this.f14428d = calendarConstraints.f14420k;
            this.f14429e = calendarConstraints.f14418i;
        }

        public CalendarConstraints a() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.f14429e);
            Month l2 = Month.l(this.f14425a);
            Month l3 = Month.l(this.f14426b);
            DateValidator dateValidator = (DateValidator) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            Long l4 = this.f14427c;
            return new CalendarConstraints(l2, l3, dateValidator, l4 == null ? null : Month.l(l4.longValue()), this.f14428d);
        }

        public Builder b(long j2) {
            this.f14427c = Long.valueOf(j2);
            return this;
        }
    }

    public interface DateValidator extends Parcelable {
        boolean j(long j2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.f14416c.equals(calendarConstraints.f14416c) && this.f14417h.equals(calendarConstraints.f14417h) && ObjectsCompat.a(this.f14419j, calendarConstraints.f14419j) && this.f14420k == calendarConstraints.f14420k && this.f14418i.equals(calendarConstraints.f14418i);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f14416c, this.f14417h, this.f14419j, Integer.valueOf(this.f14420k), this.f14418i});
    }

    Month i(Month month) {
        return month.compareTo(this.f14416c) < 0 ? this.f14416c : month.compareTo(this.f14417h) > 0 ? this.f14417h : month;
    }

    public DateValidator k() {
        return this.f14418i;
    }

    Month l() {
        return this.f14417h;
    }

    int m() {
        return this.f14420k;
    }

    int n() {
        return this.f14422m;
    }

    Month o() {
        return this.f14419j;
    }

    Month q() {
        return this.f14416c;
    }

    int r() {
        return this.f14421l;
    }

    boolean u(long j2) {
        if (this.f14416c.r(1) <= j2) {
            Month month = this.f14417h;
            if (j2 <= month.r(month.f14495k)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f14416c, 0);
        parcel.writeParcelable(this.f14417h, 0);
        parcel.writeParcelable(this.f14419j, 0);
        parcel.writeParcelable(this.f14418i, 0);
        parcel.writeInt(this.f14420k);
    }

    private CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, int i2) {
        Objects.requireNonNull(month, "start cannot be null");
        Objects.requireNonNull(month2, "end cannot be null");
        Objects.requireNonNull(dateValidator, "validator cannot be null");
        this.f14416c = month;
        this.f14417h = month2;
        this.f14419j = month3;
        this.f14420k = i2;
        this.f14418i = dateValidator;
        if (month3 != null && month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i2 < 0 || i2 > UtcDates.m().getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.f14422m = month.y(month2) + 1;
        this.f14421l = (month2.f14493i - month.f14493i) + 1;
    }
}
