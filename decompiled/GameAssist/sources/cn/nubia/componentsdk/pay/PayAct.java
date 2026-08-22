package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.util.SparseArray;
import cn.nubia.componentsdk.until.PayLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PayAct extends BaseAct {

    /* renamed from: k, reason: collision with root package name */
    private String f5983k;

    /* renamed from: l, reason: collision with root package name */
    private HashMap f5984l;

    /* renamed from: m, reason: collision with root package name */
    private String f5985m;

    /* renamed from: n, reason: collision with root package name */
    private JSONObject f5986n;

    /* renamed from: o, reason: collision with root package name */
    private String f5987o;

    public PayAct(Context context) {
        super(context, (byte) 2, (short) 2);
        this.f5983k = null;
        this.f5987o = "PayAct";
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected void f(int i2) {
        int k2;
        SparseArray sparseArray = this.f5899c;
        BufferData bufferData = sparseArray != null ? (BufferData) sparseArray.get(i2) : null;
        c();
        if (bufferData == null || bufferData.a() == null || bufferData.a().length <= 0) {
            PayLog.a(this.f5987o, "bufferData.getByteBuffer() is empty");
            b(this.f5901e, 124, null);
            return;
        }
        HttpResponse httpResponse = new HttpResponse(bufferData.a());
        this.f5901e = httpResponse;
        if (httpResponse.i()) {
            b(this.f5901e, 124, null);
            return;
        }
        int c2 = this.f5901e.c();
        PayLog.a(this.f5987o, "code = mResponse.getErrorCode():" + c2);
        if (c2 == 0 && (k2 = k()) != 0) {
            c2 = k2;
        }
        if (this.f5985m.equals("WeiXinAppPay") || this.f5985m.equals("QqWalletPay")) {
            b(this.f5901e, c2, this.f5986n);
        } else {
            b(this.f5901e, c2, this.f5983k);
        }
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected HashMap h() {
        HashMap hashMap = new HashMap();
        hashMap.put("pay_channel_tag", this.f5985m);
        hashMap.put("currency", "cny");
        if (this.f5985m.equals("WeiXinAppPay")) {
            hashMap.put("trade_type", "APP");
        }
        for (Map.Entry entry : this.f5984l.entrySet()) {
            try {
                hashMap.put((String) entry.getKey(), entry.getValue() + "");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        PayLog.a(this.f5987o, "send server payInfo_map:" + hashMap);
        return hashMap;
    }

    public int i(HashMap hashMap, String str, String str2, CallbackListener callbackListener) {
        this.f5898b = callbackListener;
        this.f5984l = hashMap;
        this.f5985m = str;
        this.f5905i = str2;
        return a();
    }

    public int j(HashMap hashMap, String str, String str2, CallbackListener callbackListener) {
        PayLog.a(this.f5987o, "session = " + str2);
        this.f5898b = callbackListener;
        this.f5984l = hashMap;
        this.f5985m = str;
        this.f5905i = str2;
        return a();
    }

    protected int k() {
        int i2;
        String g2 = this.f5901e.g("Result");
        if (g2 != null) {
            try {
                i2 = Integer.parseInt(g2);
            } catch (Exception unused) {
                i2 = 2001;
            }
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            return i2;
        }
        if (this.f5985m.equals("WeiXinAppPay") || this.f5985m.equals("QqWalletPay")) {
            try {
                this.f5986n = new JSONObject(this.f5901e.g("pay_params"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            PayLog.a(this.f5987o, "Sever return payParams JsonString ：" + this.f5986n);
        } else {
            this.f5983k = this.f5901e.g("pay_params");
            PayLog.a(this.f5987o, "payParams ：" + this.f5983k);
        }
        return 0;
    }
}
