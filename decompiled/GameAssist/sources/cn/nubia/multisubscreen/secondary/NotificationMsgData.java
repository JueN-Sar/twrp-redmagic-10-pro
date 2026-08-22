package cn.nubia.multisubscreen.secondary;

/* loaded from: classes.dex */
public class NotificationMsgData {

    /* renamed from: a, reason: collision with root package name */
    public String f8038a;

    /* renamed from: b, reason: collision with root package name */
    public String f8039b;

    /* renamed from: c, reason: collision with root package name */
    public String f8040c;

    /* renamed from: d, reason: collision with root package name */
    public long f8041d;

    /* renamed from: e, reason: collision with root package name */
    public int f8042e;

    /* renamed from: f, reason: collision with root package name */
    public long f8043f;

    public NotificationMsgData(int i2) {
        this.f8042e = i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mType = " + this.f8042e + ", mPkgName = " + this.f8038a + ", mTitle = " + this.f8039b + ", mContent = " + this.f8040c + ", mTime = " + this.f8041d + ", mNotiId = " + this.f8043f);
        return sb.toString();
    }

    public NotificationMsgData(int i2, String str) {
        this.f8042e = i2;
        this.f8038a = str;
    }
}
