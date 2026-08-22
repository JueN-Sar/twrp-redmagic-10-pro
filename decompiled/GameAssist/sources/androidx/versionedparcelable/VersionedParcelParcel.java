package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;

@RestrictTo
/* loaded from: classes.dex */
class VersionedParcelParcel extends VersionedParcel {

    /* renamed from: d, reason: collision with root package name */
    private final SparseIntArray f5727d;

    /* renamed from: e, reason: collision with root package name */
    private final Parcel f5728e;

    /* renamed from: f, reason: collision with root package name */
    private final int f5729f;

    /* renamed from: g, reason: collision with root package name */
    private final int f5730g;

    /* renamed from: h, reason: collision with root package name */
    private final String f5731h;

    /* renamed from: i, reason: collision with root package name */
    private int f5732i;

    /* renamed from: j, reason: collision with root package name */
    private int f5733j;

    /* renamed from: k, reason: collision with root package name */
    private int f5734k;

    VersionedParcelParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new ArrayMap(), new ArrayMap(), new ArrayMap());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f5728e.writeInt(-1);
        } else {
            this.f5728e.writeInt(bArr.length);
            this.f5728e.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f5728e, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void E(int i2) {
        this.f5728e.writeInt(i2);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void G(Parcelable parcelable) {
        this.f5728e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void I(String str) {
        this.f5728e.writeString(str);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a() {
        int i2 = this.f5732i;
        if (i2 >= 0) {
            int i3 = this.f5727d.get(i2);
            int dataPosition = this.f5728e.dataPosition();
            this.f5728e.setDataPosition(i3);
            this.f5728e.writeInt(dataPosition - i3);
            this.f5728e.setDataPosition(dataPosition);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel b() {
        Parcel parcel = this.f5728e;
        int dataPosition = parcel.dataPosition();
        int i2 = this.f5733j;
        if (i2 == this.f5729f) {
            i2 = this.f5730g;
        }
        return new VersionedParcelParcel(parcel, dataPosition, i2, this.f5731h + "  ", this.f5724a, this.f5725b, this.f5726c);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean g() {
        return this.f5728e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] i() {
        int readInt = this.f5728e.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.f5728e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f5728e);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean m(int i2) {
        while (this.f5733j < this.f5730g) {
            int i3 = this.f5734k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.f5728e.setDataPosition(this.f5733j);
            int readInt = this.f5728e.readInt();
            this.f5734k = this.f5728e.readInt();
            this.f5733j += readInt;
        }
        return this.f5734k == i2;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int o() {
        return this.f5728e.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public Parcelable q() {
        return this.f5728e.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String s() {
        return this.f5728e.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void w(int i2) {
        a();
        this.f5732i = i2;
        this.f5727d.put(i2, this.f5728e.dataPosition());
        E(0);
        E(i2);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void y(boolean z) {
        this.f5728e.writeInt(z ? 1 : 0);
    }

    private VersionedParcelParcel(Parcel parcel, int i2, int i3, String str, ArrayMap arrayMap, ArrayMap arrayMap2, ArrayMap arrayMap3) {
        super(arrayMap, arrayMap2, arrayMap3);
        this.f5727d = new SparseIntArray();
        this.f5732i = -1;
        this.f5734k = -1;
        this.f5728e = parcel;
        this.f5729f = i2;
        this.f5730g = i3;
        this.f5733j = i2;
        this.f5731h = str;
    }
}
