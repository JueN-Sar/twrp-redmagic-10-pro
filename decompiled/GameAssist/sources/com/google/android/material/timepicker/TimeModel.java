package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.R;
import java.util.Arrays;

/* loaded from: classes.dex */
class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new Parcelable.Creator<TimeModel>() { // from class: com.google.android.material.timepicker.TimeModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TimeModel createFromParcel(Parcel parcel) {
            return new TimeModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TimeModel[] newArray(int i2) {
            return new TimeModel[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final MaxInputValidator f15475c;

    /* renamed from: h, reason: collision with root package name */
    private final MaxInputValidator f15476h;

    /* renamed from: i, reason: collision with root package name */
    final int f15477i;

    /* renamed from: j, reason: collision with root package name */
    int f15478j;

    /* renamed from: k, reason: collision with root package name */
    int f15479k;

    /* renamed from: l, reason: collision with root package name */
    int f15480l;

    /* renamed from: m, reason: collision with root package name */
    int f15481m;

    public TimeModel() {
        this(0);
    }

    public static String a(Resources resources, CharSequence charSequence) {
        return b(resources, charSequence, "%02d");
    }

    public static String b(Resources resources, CharSequence charSequence, String str) {
        try {
            return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static int k(int i2) {
        return i2 >= 12 ? 1 : 0;
    }

    public int d() {
        return this.f15477i == 1 ? R.string.material_hour_24h_suffix : R.string.material_hour_suffix;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeModel)) {
            return false;
        }
        TimeModel timeModel = (TimeModel) obj;
        return this.f15478j == timeModel.f15478j && this.f15479k == timeModel.f15479k && this.f15477i == timeModel.f15477i && this.f15480l == timeModel.f15480l;
    }

    public int f() {
        if (this.f15477i == 1) {
            return this.f15478j % 24;
        }
        int i2 = this.f15478j;
        if (i2 % 12 == 0) {
            return 12;
        }
        return this.f15481m == 1 ? i2 - 12 : i2;
    }

    public MaxInputValidator g() {
        return this.f15476h;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f15477i), Integer.valueOf(this.f15478j), Integer.valueOf(this.f15479k), Integer.valueOf(this.f15480l)});
    }

    public MaxInputValidator i() {
        return this.f15475c;
    }

    public void l(int i2) {
        if (this.f15477i == 1) {
            this.f15478j = i2;
        } else {
            this.f15478j = (i2 % 12) + (this.f15481m != 1 ? 0 : 12);
        }
    }

    public void m(int i2) {
        this.f15479k = i2 % 60;
    }

    public void n(int i2) {
        if (i2 != this.f15481m) {
            this.f15481m = i2;
            int i3 = this.f15478j;
            if (i3 < 12 && i2 == 1) {
                this.f15478j = i3 + 12;
            } else {
                if (i3 < 12 || i2 != 0) {
                    return;
                }
                this.f15478j = i3 - 12;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f15478j);
        parcel.writeInt(this.f15479k);
        parcel.writeInt(this.f15480l);
        parcel.writeInt(this.f15477i);
    }

    public TimeModel(int i2) {
        this(0, 0, 10, i2);
    }

    public TimeModel(int i2, int i3, int i4, int i5) {
        this.f15478j = i2;
        this.f15479k = i3;
        this.f15480l = i4;
        this.f15477i = i5;
        this.f15481m = k(i2);
        this.f15475c = new MaxInputValidator(59);
        this.f15476h = new MaxInputValidator(i5 == 1 ? 23 : 12);
    }

    protected TimeModel(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }
}
