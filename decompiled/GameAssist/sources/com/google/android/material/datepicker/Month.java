package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new Parcelable.Creator<Month>() { // from class: com.google.android.material.datepicker.Month.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.f(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i2) {
            return new Month[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final Calendar f14491c;

    /* renamed from: h, reason: collision with root package name */
    final int f14492h;

    /* renamed from: i, reason: collision with root package name */
    final int f14493i;

    /* renamed from: j, reason: collision with root package name */
    final int f14494j;

    /* renamed from: k, reason: collision with root package name */
    final int f14495k;

    /* renamed from: l, reason: collision with root package name */
    final long f14496l;

    /* renamed from: m, reason: collision with root package name */
    private String f14497m;

    private Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar e2 = UtcDates.e(calendar);
        this.f14491c = e2;
        this.f14492h = e2.get(2);
        this.f14493i = e2.get(1);
        this.f14494j = e2.getMaximum(7);
        this.f14495k = e2.getActualMaximum(5);
        this.f14496l = e2.getTimeInMillis();
    }

    static Month f(int i2, int i3) {
        Calendar m2 = UtcDates.m();
        m2.set(1, i2);
        m2.set(2, i3);
        return new Month(m2);
    }

    static Month l(long j2) {
        Calendar m2 = UtcDates.m();
        m2.setTimeInMillis(j2);
        return new Month(m2);
    }

    static Month n() {
        return new Month(UtcDates.k());
    }

    @Override // java.lang.Comparable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f14491c.compareTo(month.f14491c);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.f14492h == month.f14492h && this.f14493i == month.f14493i;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f14492h), Integer.valueOf(this.f14493i)});
    }

    int o(int i2) {
        int i3 = this.f14491c.get(7);
        if (i2 <= 0) {
            i2 = this.f14491c.getFirstDayOfWeek();
        }
        int i4 = i3 - i2;
        return i4 < 0 ? i4 + this.f14494j : i4;
    }

    long r(int i2) {
        Calendar e2 = UtcDates.e(this.f14491c);
        e2.set(5, i2);
        return e2.getTimeInMillis();
    }

    int u(long j2) {
        Calendar e2 = UtcDates.e(this.f14491c);
        e2.setTimeInMillis(j2);
        return e2.get(5);
    }

    String v() {
        if (this.f14497m == null) {
            this.f14497m = DateStrings.l(this.f14491c.getTimeInMillis());
        }
        return this.f14497m;
    }

    long w() {
        return this.f14491c.getTimeInMillis();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f14493i);
        parcel.writeInt(this.f14492h);
    }

    Month x(int i2) {
        Calendar e2 = UtcDates.e(this.f14491c);
        e2.add(2, i2);
        return new Month(e2);
    }

    int y(Month month) {
        if (this.f14491c instanceof GregorianCalendar) {
            return ((month.f14493i - this.f14493i) * 12) + (month.f14492h - this.f14492h);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }
}
