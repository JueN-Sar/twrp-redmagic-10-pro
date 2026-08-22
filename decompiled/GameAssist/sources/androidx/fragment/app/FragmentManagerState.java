package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new Parcelable.Creator<FragmentManagerState>() { // from class: androidx.fragment.app.FragmentManagerState.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState[] newArray(int i2) {
            return new FragmentManagerState[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    ArrayList f4091c;

    /* renamed from: h, reason: collision with root package name */
    ArrayList f4092h;

    /* renamed from: i, reason: collision with root package name */
    BackStackRecordState[] f4093i;

    /* renamed from: j, reason: collision with root package name */
    int f4094j;

    /* renamed from: k, reason: collision with root package name */
    String f4095k;

    /* renamed from: l, reason: collision with root package name */
    ArrayList f4096l;

    /* renamed from: m, reason: collision with root package name */
    ArrayList f4097m;

    /* renamed from: n, reason: collision with root package name */
    ArrayList f4098n;

    public FragmentManagerState() {
        this.f4095k = null;
        this.f4096l = new ArrayList();
        this.f4097m = new ArrayList();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStringList(this.f4091c);
        parcel.writeStringList(this.f4092h);
        parcel.writeTypedArray(this.f4093i, i2);
        parcel.writeInt(this.f4094j);
        parcel.writeString(this.f4095k);
        parcel.writeStringList(this.f4096l);
        parcel.writeTypedList(this.f4097m);
        parcel.writeTypedList(this.f4098n);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f4095k = null;
        this.f4096l = new ArrayList();
        this.f4097m = new ArrayList();
        this.f4091c = parcel.createStringArrayList();
        this.f4092h = parcel.createStringArrayList();
        this.f4093i = (BackStackRecordState[]) parcel.createTypedArray(BackStackRecordState.CREATOR);
        this.f4094j = parcel.readInt();
        this.f4095k = parcel.readString();
        this.f4096l = parcel.createStringArrayList();
        this.f4097m = parcel.createTypedArrayList(BackStackState.CREATOR);
        this.f4098n = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
