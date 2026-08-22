package cn.nubia.multisubscreen.secondary;

/* loaded from: classes.dex */
public class RemoveNotificationMsgData {

    /* renamed from: a, reason: collision with root package name */
    public String f8044a;

    /* renamed from: b, reason: collision with root package name */
    public long f8045b;

    public RemoveNotificationMsgData(String str, long j2) {
        this.f8044a = str;
        this.f8045b = j2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mPkgName = " + this.f8044a + ", mNotiId = " + this.f8045b);
        return sb.toString();
    }
}
