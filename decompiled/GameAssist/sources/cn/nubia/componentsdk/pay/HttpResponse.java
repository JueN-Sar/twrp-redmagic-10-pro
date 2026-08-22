package cn.nubia.componentsdk.pay;

import cn.nubia.componentsdk.pay.secret.FormatTransfer;
import cn.nubia.componentsdk.pay.secret.Hex;
import cn.nubia.componentsdk.pay.secret.Md5;
import cn.nubia.componentsdk.pay.secret.ThreeDes;
import cn.nubia.componentsdk.until.PayLog;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpResponse {

    /* renamed from: c, reason: collision with root package name */
    private byte f5953c;

    /* renamed from: d, reason: collision with root package name */
    private short f5954d;

    /* renamed from: e, reason: collision with root package name */
    private int f5955e;

    /* renamed from: f, reason: collision with root package name */
    private byte f5956f;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f5951a = new byte[32];

    /* renamed from: b, reason: collision with root package name */
    private byte[] f5952b = null;

    /* renamed from: g, reason: collision with root package name */
    private boolean f5957g = true;

    /* renamed from: h, reason: collision with root package name */
    private String f5958h = "HttpResponse";

    /* renamed from: i, reason: collision with root package name */
    private JSONObject f5959i = null;

    public HttpResponse(byte[] bArr) {
        h(bArr);
    }

    private String e() {
        try {
            String a2 = Hex.a(Md5.a(this.f5952b));
            byte b2 = this.f5956f;
            return a2.substring(b2, b2 + 4);
        } catch (Exception unused) {
            return null;
        }
    }

    private void h(byte[] bArr) {
        this.f5952b = new byte[bArr.length - 32];
        System.arraycopy(bArr, 0, this.f5951a, 0, 32);
        byte[] bArr2 = this.f5952b;
        System.arraycopy(bArr, 32, bArr2, 0, bArr2.length);
        j();
        PayLog.a(this.f5958h, "act:" + ((int) this.f5954d));
        l();
        PayLog.a(this.f5958h, "encryptType:" + ((int) this.f5953c));
        k();
        PayLog.a(this.f5958h, "checkCode:" + ((int) this.f5956f));
        n();
        m();
        PayLog.a(this.f5958h, "errorCode" + this.f5955e);
    }

    private void j() {
        byte[] bArr = new byte[2];
        System.arraycopy(this.f5951a, 0, bArr, 0, 2);
        this.f5954d = FormatTransfer.b(bArr);
    }

    private void k() {
        this.f5956f = this.f5951a[3];
    }

    private void l() {
        this.f5953c = this.f5951a[2];
    }

    private void m() {
        byte[] bArr = new byte[4];
        System.arraycopy(this.f5951a, 8, bArr, 0, 4);
        this.f5955e = FormatTransfer.a(bArr);
    }

    private void n() {
        byte[] bArr = this.f5952b;
        if (bArr == null || bArr.length == 0) {
            this.f5957g = true;
            PayLog.a("REPONSE", "body == null || body.length == 0");
        }
        String e2 = e();
        byte[] bArr2 = this.f5951a;
        String str = new String(new byte[]{bArr2[4], bArr2[5], bArr2[6], bArr2[7]});
        PayLog.a("invalidFlag", "mds.equalsIgnoreCase(localMd)" + str.equalsIgnoreCase(e2));
        if (str.equalsIgnoreCase(e2)) {
            this.f5957g = false;
        }
    }

    public byte[] a() {
        byte b2;
        byte[] bArr = this.f5952b;
        if (bArr == null || bArr.length == 0 || (b2 = this.f5953c) == 0) {
            return bArr;
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (b2 == 1) {
            return GZIPUitl.b(bArr);
        }
        if (b2 == 2) {
            return ThreeDes.a(Util.b(), this.f5952b);
        }
        if (b2 == 3) {
            return GZIPUitl.b(ThreeDes.a(Util.b(), this.f5952b));
        }
        return this.f5952b;
    }

    public String b() {
        try {
            byte[] a2 = a();
            if (a2 != null && a2.length != 0) {
                return new String(a2, "utf-8");
            }
            return null;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int c() {
        return this.f5955e;
    }

    public JSONArray d(String str) {
        try {
            if (this.f5959i == null) {
                f();
            }
            JSONObject jSONObject = this.f5959i;
            if (jSONObject == null || jSONObject.isNull(str)) {
                return null;
            }
            return this.f5959i.getJSONArray(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public JSONObject f() {
        String b2 = b();
        if (b2 == null) {
            return null;
        }
        try {
            this.f5959i = new JSONObject(b2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this.f5959i;
    }

    public String g(String str) {
        try {
            if (this.f5959i == null) {
                f();
            }
            JSONObject jSONObject = this.f5959i;
            if (jSONObject == null || jSONObject.isNull(str)) {
                return null;
            }
            return this.f5959i.getString(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean i() {
        return this.f5957g;
    }
}
