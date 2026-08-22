package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator<BackStackState>() { // from class: androidx.fragment.app.BackStackState.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i2) {
            return new BackStackState[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    final List f3920c;

    /* renamed from: h, reason: collision with root package name */
    final List f3921h;

    BackStackState(List list, List list2) {
        this.f3920c = list;
        this.f3921h = list2;
    }

    List a(FragmentManager fragmentManager, Map map) {
        HashMap hashMap = new HashMap(this.f3920c.size());
        for (String str : this.f3920c) {
            Fragment fragment = (Fragment) map.get(str);
            if (fragment != null) {
                hashMap.put(fragment.f3979l, fragment);
            } else {
                FragmentState B = fragmentManager.y0().B(str, null);
                if (B != null) {
                    Fragment a2 = B.a(fragmentManager.x0(), fragmentManager.A0().r().getClassLoader());
                    hashMap.put(a2.f3979l, a2);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f3921h.iterator();
        while (it.hasNext()) {
            arrayList.add(((BackStackRecordState) it.next()).d(fragmentManager, hashMap));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStringList(this.f3920c);
        parcel.writeTypedList(this.f3921h);
    }

    BackStackState(Parcel parcel) {
        this.f3920c = parcel.createStringArrayList();
        this.f3921h = parcel.createTypedArrayList(BackStackRecordState.CREATOR);
    }
}
