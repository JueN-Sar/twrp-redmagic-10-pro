package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class ActivityResult implements Parcelable {

    @NonNull
    public static final Parcelable.Creator<ActivityResult> CREATOR = new Parcelable.Creator<ActivityResult>() { // from class: androidx.activity.result.ActivityResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ActivityResult createFromParcel(Parcel parcel) {
            return new ActivityResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ActivityResult[] newArray(int i2) {
            return new ActivityResult[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private final int f95c;

    /* renamed from: h, reason: collision with root package name */
    private final Intent f96h;

    public ActivityResult(int i2, Intent intent) {
        this.f95c = i2;
        this.f96h = intent;
    }

    public static String d(int i2) {
        return i2 != -1 ? i2 != 0 ? String.valueOf(i2) : "RESULT_CANCELED" : "RESULT_OK";
    }

    public Intent a() {
        return this.f96h;
    }

    public int b() {
        return this.f95c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityResult{resultCode=" + d(this.f95c) + ", data=" + this.f96h + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f95c);
        parcel.writeInt(this.f96h == null ? 0 : 1);
        Intent intent = this.f96h;
        if (intent != null) {
            intent.writeToParcel(parcel, i2);
        }
    }

    ActivityResult(Parcel parcel) {
        this.f95c = parcel.readInt();
        this.f96h = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }
}
