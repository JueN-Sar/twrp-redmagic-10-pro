package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.Lifecycle;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() { // from class: androidx.fragment.app.FragmentState.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i2) {
            return new FragmentState[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    final String f4112c;

    /* renamed from: h, reason: collision with root package name */
    final String f4113h;

    /* renamed from: i, reason: collision with root package name */
    final boolean f4114i;

    /* renamed from: j, reason: collision with root package name */
    final int f4115j;

    /* renamed from: k, reason: collision with root package name */
    final int f4116k;

    /* renamed from: l, reason: collision with root package name */
    final String f4117l;

    /* renamed from: m, reason: collision with root package name */
    final boolean f4118m;

    /* renamed from: n, reason: collision with root package name */
    final boolean f4119n;

    /* renamed from: o, reason: collision with root package name */
    final boolean f4120o;

    /* renamed from: p, reason: collision with root package name */
    final Bundle f4121p;

    /* renamed from: q, reason: collision with root package name */
    final boolean f4122q;

    /* renamed from: r, reason: collision with root package name */
    final int f4123r;

    /* renamed from: s, reason: collision with root package name */
    Bundle f4124s;

    FragmentState(Fragment fragment) {
        this.f4112c = fragment.getClass().getName();
        this.f4113h = fragment.f3979l;
        this.f4114i = fragment.u;
        this.f4115j = fragment.D;
        this.f4116k = fragment.E;
        this.f4117l = fragment.F;
        this.f4118m = fragment.I;
        this.f4119n = fragment.f3986s;
        this.f4120o = fragment.H;
        this.f4121p = fragment.f3980m;
        this.f4122q = fragment.G;
        this.f4123r = fragment.X.ordinal();
    }

    Fragment a(FragmentFactory fragmentFactory, ClassLoader classLoader) {
        Fragment a2 = fragmentFactory.a(classLoader, this.f4112c);
        Bundle bundle = this.f4121p;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        a2.J1(this.f4121p);
        a2.f3979l = this.f4113h;
        a2.u = this.f4114i;
        a2.w = true;
        a2.D = this.f4115j;
        a2.E = this.f4116k;
        a2.F = this.f4117l;
        a2.I = this.f4118m;
        a2.f3986s = this.f4119n;
        a2.H = this.f4120o;
        a2.G = this.f4122q;
        a2.X = Lifecycle.State.values()[this.f4123r];
        Bundle bundle2 = this.f4124s;
        if (bundle2 != null) {
            a2.f3975h = bundle2;
        } else {
            a2.f3975h = new Bundle();
        }
        return a2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f4112c);
        sb.append(" (");
        sb.append(this.f4113h);
        sb.append(")}:");
        if (this.f4114i) {
            sb.append(" fromLayout");
        }
        if (this.f4116k != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f4116k));
        }
        String str = this.f4117l;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f4117l);
        }
        if (this.f4118m) {
            sb.append(" retainInstance");
        }
        if (this.f4119n) {
            sb.append(" removing");
        }
        if (this.f4120o) {
            sb.append(" detached");
        }
        if (this.f4122q) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f4112c);
        parcel.writeString(this.f4113h);
        parcel.writeInt(this.f4114i ? 1 : 0);
        parcel.writeInt(this.f4115j);
        parcel.writeInt(this.f4116k);
        parcel.writeString(this.f4117l);
        parcel.writeInt(this.f4118m ? 1 : 0);
        parcel.writeInt(this.f4119n ? 1 : 0);
        parcel.writeInt(this.f4120o ? 1 : 0);
        parcel.writeBundle(this.f4121p);
        parcel.writeInt(this.f4122q ? 1 : 0);
        parcel.writeBundle(this.f4124s);
        parcel.writeInt(this.f4123r);
    }

    FragmentState(Parcel parcel) {
        this.f4112c = parcel.readString();
        this.f4113h = parcel.readString();
        this.f4114i = parcel.readInt() != 0;
        this.f4115j = parcel.readInt();
        this.f4116k = parcel.readInt();
        this.f4117l = parcel.readString();
        this.f4118m = parcel.readInt() != 0;
        this.f4119n = parcel.readInt() != 0;
        this.f4120o = parcel.readInt() != 0;
        this.f4121p = parcel.readBundle();
        this.f4122q = parcel.readInt() != 0;
        this.f4124s = parcel.readBundle();
        this.f4123r = parcel.readInt();
    }
}
