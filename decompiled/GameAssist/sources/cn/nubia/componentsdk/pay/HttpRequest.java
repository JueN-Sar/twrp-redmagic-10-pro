package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.text.TextUtils;
import cn.nubia.componentsdk.pay.secret.FormatTransfer;
import cn.nubia.componentsdk.pay.secret.Md5;
import cn.nubia.componentsdk.pay.secret.ThreeDes;
import cn.nubia.componentsdk.until.PayLog;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpRequest {

    /* renamed from: a, reason: collision with root package name */
    private String f5946a;

    /* renamed from: b, reason: collision with root package name */
    private byte[] f5947b = new byte[64];

    /* renamed from: c, reason: collision with root package name */
    private byte f5948c;

    /* renamed from: d, reason: collision with root package name */
    private short f5949d;

    /* renamed from: e, reason: collision with root package name */
    private Context f5950e;

    public HttpRequest(byte b2, short s2, Context context, String str) {
        this.f5948c = b2;
        this.f5949d = s2;
        this.f5950e = context;
        this.f5946a = str;
    }

    private byte[] a(HashMap hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : hashMap.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
            String jSONObject2 = jSONObject.toString();
            if (jSONObject2.length() > 0) {
                return jSONObject2.getBytes("utf-8");
            }
            return null;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private void d(String str, int i2) {
        int i3;
        if (str != null && str.length() >= (i3 = i2 + 4)) {
            System.arraycopy(str.substring(i2, i3).getBytes(), 0, this.f5947b, 42, 4);
        }
    }

    private int e() {
        int random = (int) (Math.random() * 29.0d);
        this.f5947b[41] = (byte) random;
        return random;
    }

    private String h() {
        return (TextUtils.isEmpty(this.f5946a) || this.f5949d == 1) ? "11111111111111111111111111111111" : this.f5946a;
    }

    private void i(short s2) {
        byte[] d2 = FormatTransfer.d(s2);
        byte[] bArr = this.f5947b;
        bArr[4] = d2[0];
        bArr[5] = d2[1];
    }

    private void j() {
        byte[] c2 = FormatTransfer.c(15015);
        byte[] bArr = this.f5947b;
        bArr[0] = c2[0];
        bArr[1] = c2[1];
        bArr[2] = c2[2];
        bArr[3] = c2[3];
    }

    private void k(byte b2) {
        if (b2 != -1) {
            this.f5947b[40] = b2;
        }
    }

    private void l(byte b2) {
        this.f5947b[46] = b2;
    }

    private void m(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            System.arraycopy(bytes, 0, this.f5947b, 8, bytes.length);
        }
    }

    private void n(short s2) {
        byte[] d2 = FormatTransfer.d(s2);
        byte[] bArr = this.f5947b;
        bArr[6] = d2[0];
        bArr[7] = d2[1];
    }

    public byte[] b() {
        return g(null);
    }

    public byte[] c(HashMap hashMap) {
        byte[] a2 = a(hashMap);
        if (a2 != null) {
            a2 = f(a2);
        }
        byte[] b2 = (a2 == null || a2.length <= 0) ? b() : g(Md5.b(a2));
        if (a2 == null) {
            return b2;
        }
        byte[] bArr = new byte[b2.length + a2.length];
        System.arraycopy(b2, 0, bArr, 0, b2.length);
        System.arraycopy(a2, 0, bArr, b2.length, a2.length);
        PayLog.a("getHttpRequestData", "requestData[40] = " + ((int) bArr[40]));
        return bArr;
    }

    public byte[] f(byte[] bArr) {
        byte b2 = this.f5948c;
        if (b2 == 0) {
            return bArr;
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (b2 == 1) {
            return GZIPUitl.a(bArr);
        }
        if (b2 == 2) {
            PayLog.a("getRequestEncryptBody", "3DES running!");
            return ThreeDes.c(Util.b(), bArr);
        }
        if (b2 == 3) {
            return ThreeDes.c(Util.b(), GZIPUitl.a(bArr));
        }
        if (b2 == 4) {
            return Util.e(bArr, this.f5950e);
        }
        return bArr;
    }

    public byte[] g(String str) {
        j();
        i(this.f5949d);
        n((short) 1);
        m(h());
        PayLog.a("getRequestHead", "RequestHead:" + h() + " ;size =" + h().length());
        k(this.f5948c);
        StringBuilder sb = new StringBuilder();
        sb.append("encryptType");
        sb.append((int) this.f5948c);
        PayLog.a("getRequestHead", sb.toString());
        d(str, e());
        l(Constant.f5932d);
        PayLog.a("getRequestHead", "httpHeadData[40] = " + ((int) this.f5947b[40]));
        return this.f5947b;
    }
}
