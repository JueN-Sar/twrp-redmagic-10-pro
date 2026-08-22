package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Map;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator<BackStackRecordState> CREATOR = new Parcelable.Creator<BackStackRecordState>() { // from class: androidx.fragment.app.BackStackRecordState.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackRecordState createFromParcel(Parcel parcel) {
            return new BackStackRecordState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackRecordState[] newArray(int i2) {
            return new BackStackRecordState[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    final int[] f3907c;

    /* renamed from: h, reason: collision with root package name */
    final ArrayList f3908h;

    /* renamed from: i, reason: collision with root package name */
    final int[] f3909i;

    /* renamed from: j, reason: collision with root package name */
    final int[] f3910j;

    /* renamed from: k, reason: collision with root package name */
    final int f3911k;

    /* renamed from: l, reason: collision with root package name */
    final String f3912l;

    /* renamed from: m, reason: collision with root package name */
    final int f3913m;

    /* renamed from: n, reason: collision with root package name */
    final int f3914n;

    /* renamed from: o, reason: collision with root package name */
    final CharSequence f3915o;

    /* renamed from: p, reason: collision with root package name */
    final int f3916p;

    /* renamed from: q, reason: collision with root package name */
    final CharSequence f3917q;

    /* renamed from: r, reason: collision with root package name */
    final ArrayList f3918r;

    /* renamed from: s, reason: collision with root package name */
    final ArrayList f3919s;
    final boolean t;

    BackStackRecordState(BackStackRecord backStackRecord) {
        int size = backStackRecord.f4152c.size();
        this.f3907c = new int[size * 6];
        if (!backStackRecord.f4158i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f3908h = new ArrayList(size);
        this.f3909i = new int[size];
        this.f3910j = new int[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) backStackRecord.f4152c.get(i3);
            int i4 = i2 + 1;
            this.f3907c[i2] = op.f4169a;
            ArrayList arrayList = this.f3908h;
            Fragment fragment = op.f4170b;
            arrayList.add(fragment != null ? fragment.f3979l : null);
            int[] iArr = this.f3907c;
            iArr[i4] = op.f4171c ? 1 : 0;
            iArr[i2 + 2] = op.f4172d;
            iArr[i2 + 3] = op.f4173e;
            int i5 = i2 + 5;
            iArr[i2 + 4] = op.f4174f;
            i2 += 6;
            iArr[i5] = op.f4175g;
            this.f3909i[i3] = op.f4176h.ordinal();
            this.f3910j[i3] = op.f4177i.ordinal();
        }
        this.f3911k = backStackRecord.f4157h;
        this.f3912l = backStackRecord.f4160k;
        this.f3913m = backStackRecord.v;
        this.f3914n = backStackRecord.f4161l;
        this.f3915o = backStackRecord.f4162m;
        this.f3916p = backStackRecord.f4163n;
        this.f3917q = backStackRecord.f4164o;
        this.f3918r = backStackRecord.f4165p;
        this.f3919s = backStackRecord.f4166q;
        this.t = backStackRecord.f4167r;
    }

    private void a(BackStackRecord backStackRecord) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.f3907c.length) {
                backStackRecord.f4157h = this.f3911k;
                backStackRecord.f4160k = this.f3912l;
                backStackRecord.f4158i = true;
                backStackRecord.f4161l = this.f3914n;
                backStackRecord.f4162m = this.f3915o;
                backStackRecord.f4163n = this.f3916p;
                backStackRecord.f4164o = this.f3917q;
                backStackRecord.f4165p = this.f3918r;
                backStackRecord.f4166q = this.f3919s;
                backStackRecord.f4167r = this.t;
                return;
            }
            FragmentTransaction.Op op = new FragmentTransaction.Op();
            int i4 = i2 + 1;
            op.f4169a = this.f3907c[i2];
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + i3 + " base fragment #" + this.f3907c[i4]);
            }
            op.f4176h = Lifecycle.State.values()[this.f3909i[i3]];
            op.f4177i = Lifecycle.State.values()[this.f3910j[i3]];
            int[] iArr = this.f3907c;
            int i5 = i2 + 2;
            if (iArr[i4] == 0) {
                z = false;
            }
            op.f4171c = z;
            int i6 = iArr[i5];
            op.f4172d = i6;
            int i7 = iArr[i2 + 3];
            op.f4173e = i7;
            int i8 = i2 + 5;
            int i9 = iArr[i2 + 4];
            op.f4174f = i9;
            i2 += 6;
            int i10 = iArr[i8];
            op.f4175g = i10;
            backStackRecord.f4153d = i6;
            backStackRecord.f4154e = i7;
            backStackRecord.f4155f = i9;
            backStackRecord.f4156g = i10;
            backStackRecord.f(op);
            i3++;
        }
    }

    public BackStackRecord b(FragmentManager fragmentManager) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        a(backStackRecord);
        backStackRecord.v = this.f3913m;
        for (int i2 = 0; i2 < this.f3908h.size(); i2++) {
            String str = (String) this.f3908h.get(i2);
            if (str != null) {
                ((FragmentTransaction.Op) backStackRecord.f4152c.get(i2)).f4170b = fragmentManager.i0(str);
            }
        }
        backStackRecord.u(1);
        return backStackRecord;
    }

    public BackStackRecord d(FragmentManager fragmentManager, Map map) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        a(backStackRecord);
        for (int i2 = 0; i2 < this.f3908h.size(); i2++) {
            String str = (String) this.f3908h.get(i2);
            if (str != null) {
                Fragment fragment = (Fragment) map.get(str);
                if (fragment == null) {
                    throw new IllegalStateException("Restoring FragmentTransaction " + this.f3912l + " failed due to missing saved state for Fragment (" + str + ")");
                }
                ((FragmentTransaction.Op) backStackRecord.f4152c.get(i2)).f4170b = fragment;
            }
        }
        return backStackRecord;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f3907c);
        parcel.writeStringList(this.f3908h);
        parcel.writeIntArray(this.f3909i);
        parcel.writeIntArray(this.f3910j);
        parcel.writeInt(this.f3911k);
        parcel.writeString(this.f3912l);
        parcel.writeInt(this.f3913m);
        parcel.writeInt(this.f3914n);
        TextUtils.writeToParcel(this.f3915o, parcel, 0);
        parcel.writeInt(this.f3916p);
        TextUtils.writeToParcel(this.f3917q, parcel, 0);
        parcel.writeStringList(this.f3918r);
        parcel.writeStringList(this.f3919s);
        parcel.writeInt(this.t ? 1 : 0);
    }

    BackStackRecordState(Parcel parcel) {
        this.f3907c = parcel.createIntArray();
        this.f3908h = parcel.createStringArrayList();
        this.f3909i = parcel.createIntArray();
        this.f3910j = parcel.createIntArray();
        this.f3911k = parcel.readInt();
        this.f3912l = parcel.readString();
        this.f3913m = parcel.readInt();
        this.f3914n = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f3915o = (CharSequence) creator.createFromParcel(parcel);
        this.f3916p = parcel.readInt();
        this.f3917q = (CharSequence) creator.createFromParcel(parcel);
        this.f3918r = parcel.createStringArrayList();
        this.f3919s = parcel.createStringArrayList();
        this.t = parcel.readInt() != 0;
    }
}
