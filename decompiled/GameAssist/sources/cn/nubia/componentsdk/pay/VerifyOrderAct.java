package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.util.SparseArray;
import cn.nubia.componentsdk.until.PayLog;
import java.util.HashMap;

/* loaded from: classes.dex */
public class VerifyOrderAct extends BaseAct {

    /* renamed from: k, reason: collision with root package name */
    private String f6029k;

    /* renamed from: l, reason: collision with root package name */
    private String f6030l;

    /* renamed from: m, reason: collision with root package name */
    private String f6031m;

    /* renamed from: n, reason: collision with root package name */
    private String f6032n;

    /* renamed from: o, reason: collision with root package name */
    private String f6033o;

    public VerifyOrderAct(Context context) {
        super(context, (byte) 2, (short) 3);
        this.f6033o = "VerifyOrderAct";
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected void f(int i2) {
        int j2;
        SparseArray sparseArray = this.f5899c;
        BufferData bufferData = sparseArray != null ? (BufferData) sparseArray.get(i2) : null;
        c();
        if (bufferData == null || bufferData.a() == null || bufferData.a().length <= 0) {
            PayLog.a(this.f6033o, "bufferData.getByteBuffer() is empty");
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
        PayLog.a(this.f6033o, "code = mResponse.getErrorCode():" + c2);
        if (c2 == 0 && (j2 = j()) != 0) {
            c2 = j2;
        }
        b(this.f5901e, c2, this.f6032n);
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected HashMap h() {
        HashMap hashMap = new HashMap();
        hashMap.put("app_id", this.f6029k);
        hashMap.put("uid", this.f6030l);
        hashMap.put("cp_order_id", this.f6031m);
        PayLog.a(this.f6033o, "send server map:" + hashMap);
        return hashMap;
    }

    public int i(String str, String str2, String str3, String str4, CallbackListener callbackListener) {
        PayLog.a(this.f6033o, "req() : appId =" + str + ";uid = " + str2 + ";cpOrderId = " + str3 + ";session = " + str4);
        this.f5898b = callbackListener;
        this.f6029k = str;
        this.f6030l = str2;
        this.f5905i = str4;
        this.f6031m = str3;
        return a();
    }

    protected int j() {
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
        this.f6032n = this.f5901e.g("data");
        PayLog.a(this.f6033o, "Sever return data  ：" + this.f6032n);
        return 0;
    }
}
