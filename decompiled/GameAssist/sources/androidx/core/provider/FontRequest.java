package androidx.core.provider;

import android.util.Base64;
import androidx.core.util.Preconditions;
import java.util.List;

/* loaded from: classes.dex */
public final class FontRequest {

    /* renamed from: a, reason: collision with root package name */
    private final String f3138a;

    /* renamed from: b, reason: collision with root package name */
    private final String f3139b;

    /* renamed from: c, reason: collision with root package name */
    private final String f3140c;

    /* renamed from: d, reason: collision with root package name */
    private final List f3141d;

    /* renamed from: e, reason: collision with root package name */
    private final int f3142e = 0;

    /* renamed from: f, reason: collision with root package name */
    private final String f3143f;

    public FontRequest(String str, String str2, String str3, List list) {
        this.f3138a = (String) Preconditions.h(str);
        this.f3139b = (String) Preconditions.h(str2);
        this.f3140c = (String) Preconditions.h(str3);
        this.f3141d = (List) Preconditions.h(list);
        this.f3143f = a(str, str2, str3);
    }

    private String a(String str, String str2, String str3) {
        return str + "-" + str2 + "-" + str3;
    }

    public List b() {
        return this.f3141d;
    }

    public int c() {
        return this.f3142e;
    }

    String d() {
        return this.f3143f;
    }

    public String e() {
        return this.f3138a;
    }

    public String f() {
        return this.f3139b;
    }

    public String g() {
        return this.f3140c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f3138a + ", mProviderPackage: " + this.f3139b + ", mQuery: " + this.f3140c + ", mCertificates:");
        for (int i2 = 0; i2 < this.f3141d.size(); i2++) {
            sb.append(" [");
            List list = (List) this.f3141d.get(i2);
            for (int i3 = 0; i3 < list.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f3142e);
        return sb.toString();
    }
}
