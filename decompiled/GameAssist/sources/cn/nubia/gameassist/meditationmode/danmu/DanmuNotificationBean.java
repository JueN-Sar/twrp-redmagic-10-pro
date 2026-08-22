package cn.nubia.gameassist.meditationmode.danmu;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes.dex */
public class DanmuNotificationBean implements Parcelable {
    public static final Parcelable.Creator<DanmuNotificationBean> CREATOR = new Parcelable.Creator<DanmuNotificationBean>() { // from class: cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DanmuNotificationBean createFromParcel(Parcel parcel) {
            return new DanmuNotificationBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DanmuNotificationBean[] newArray(int i2) {
            return new DanmuNotificationBean[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private String f6613c;

    /* renamed from: h, reason: collision with root package name */
    private String f6614h;

    /* renamed from: i, reason: collision with root package name */
    private String f6615i;

    /* renamed from: j, reason: collision with root package name */
    public String f6616j;

    /* renamed from: k, reason: collision with root package name */
    private PendingIntent f6617k;

    /* renamed from: l, reason: collision with root package name */
    private int f6618l;

    /* renamed from: m, reason: collision with root package name */
    private String f6619m;

    public String a() {
        return this.f6619m;
    }

    public String b() {
        return this.f6614h;
    }

    public PendingIntent d() {
        return this.f6617k;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String f() {
        return this.f6615i;
    }

    public String g() {
        return this.f6613c;
    }

    public String i() {
        return this.f6616j;
    }

    public void k(DanmuNotificationBean danmuNotificationBean) {
        this.f6613c = danmuNotificationBean.f6613c;
        this.f6614h = danmuNotificationBean.f6614h;
        this.f6615i = danmuNotificationBean.f6615i;
        this.f6616j = danmuNotificationBean.f6616j;
        this.f6617k = danmuNotificationBean.f6617k;
        this.f6618l = danmuNotificationBean.f6618l;
        this.f6619m = danmuNotificationBean.f6619m;
    }

    public String toString() {
        return "DanmuNotificationBean{mDanmuTitle='" + this.f6613c + NubiaTextClock.QUOTE + ", mDanmuContent='" + this.f6614h + NubiaTextClock.QUOTE + ", mDanmuPkg='" + this.f6615i + NubiaTextClock.QUOTE + ", mTargetPkgName='" + this.f6616j + NubiaTextClock.QUOTE + ", mDanmuIntent=" + this.f6617k + ", mShowPreView=" + this.f6618l + ", mChannelId='" + this.f6619m + NubiaTextClock.QUOTE + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f6613c);
        parcel.writeString(this.f6614h);
        parcel.writeString(this.f6615i);
        parcel.writeString(this.f6616j);
        parcel.writeTypedObject(this.f6617k, i2);
        parcel.writeInt(this.f6618l);
        parcel.writeString(this.f6619m);
    }

    public DanmuNotificationBean() {
    }

    public DanmuNotificationBean(String str, String str2, String str3, String str4, PendingIntent pendingIntent, int i2, String str5) {
        this.f6613c = str;
        this.f6614h = str2;
        this.f6615i = str3;
        this.f6616j = str4;
        this.f6617k = pendingIntent;
        this.f6618l = i2;
        this.f6619m = str5;
    }

    private DanmuNotificationBean(Parcel parcel) {
        this.f6613c = parcel.readString();
        this.f6614h = parcel.readString();
        this.f6615i = parcel.readString();
        this.f6616j = parcel.readString();
        this.f6617k = (PendingIntent) parcel.readTypedObject(PendingIntent.CREATOR);
        this.f6618l = parcel.readInt();
        this.f6619m = parcel.readString();
    }
}
