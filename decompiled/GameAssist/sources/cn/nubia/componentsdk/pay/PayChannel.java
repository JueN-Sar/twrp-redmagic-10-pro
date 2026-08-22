package cn.nubia.componentsdk.pay;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PayChannel implements Parcelable {
    public static final Parcelable.Creator<PayChannel> CREATOR = new Parcelable.Creator<PayChannel>() { // from class: cn.nubia.componentsdk.pay.PayChannel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PayChannel createFromParcel(Parcel parcel) {
            return new PayChannel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PayChannel[] newArray(int i2) {
            return new PayChannel[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private String f6005c;

    /* renamed from: h, reason: collision with root package name */
    private String f6006h;

    /* renamed from: i, reason: collision with root package name */
    private String f6007i;

    /* renamed from: j, reason: collision with root package name */
    private String f6008j;

    /* renamed from: k, reason: collision with root package name */
    private int f6009k;

    /* renamed from: l, reason: collision with root package name */
    private String f6010l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f6011m;

    /* renamed from: n, reason: collision with root package name */
    private String f6012n;

    public PayChannel() {
    }

    public String a() {
        return this.f6012n;
    }

    public String b() {
        return this.f6006h;
    }

    public String d() {
        return this.f6008j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String f() {
        return this.f6010l;
    }

    public void g(String str) {
        this.f6012n = str;
    }

    public void i(boolean z) {
        this.f6011m = z;
    }

    public void k(String str) {
        this.f6007i = str;
    }

    public void l(String str) {
        this.f6005c = str;
    }

    public void m(int i2) {
        this.f6009k = i2;
    }

    public void n(String str) {
        this.f6006h = str;
    }

    public void o(String str) {
        this.f6008j = str;
    }

    public void q(String str) {
        this.f6010l = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f6005c);
        parcel.writeString(this.f6006h);
        parcel.writeString(this.f6007i);
        parcel.writeString(this.f6008j);
        parcel.writeInt(this.f6009k);
        parcel.writeString(this.f6010l);
        parcel.writeByte(this.f6011m ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f6012n);
    }

    protected PayChannel(Parcel parcel) {
        this.f6005c = parcel.readString();
        this.f6006h = parcel.readString();
        this.f6007i = parcel.readString();
        this.f6008j = parcel.readString();
        this.f6009k = parcel.readInt();
        this.f6010l = parcel.readString();
        this.f6011m = parcel.readByte() != 0;
        this.f6012n = parcel.readString();
    }
}
