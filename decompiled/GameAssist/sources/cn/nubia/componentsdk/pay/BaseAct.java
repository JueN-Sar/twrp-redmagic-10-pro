package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import cn.nubia.componentsdk.pay.MessageHandler;
import cn.nubia.componentsdk.until.PayLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class BaseAct<T> {

    /* renamed from: a, reason: collision with root package name */
    protected Context f5897a;

    /* renamed from: b, reason: collision with root package name */
    protected CallbackListener f5898b;

    /* renamed from: d, reason: collision with root package name */
    protected int f5900d;

    /* renamed from: e, reason: collision with root package name */
    protected HttpResponse f5901e;

    /* renamed from: f, reason: collision with root package name */
    protected MessageHandler f5902f;

    /* renamed from: g, reason: collision with root package name */
    protected byte f5903g;

    /* renamed from: h, reason: collision with root package name */
    protected short f5904h;

    /* renamed from: i, reason: collision with root package name */
    protected String f5905i;

    /* renamed from: c, reason: collision with root package name */
    protected SparseArray f5899c = new SparseArray();

    /* renamed from: j, reason: collision with root package name */
    private String f5906j = "BaseAct";

    public BaseAct(Context context, byte b2, short s2) {
        this.f5897a = context;
        this.f5903g = b2;
        this.f5904h = s2;
    }

    private void e() {
        MessageHandler messageHandler = new MessageHandler();
        this.f5902f = messageHandler;
        messageHandler.c(new MessageHandler.OnProcessCompleteListener() { // from class: cn.nubia.componentsdk.pay.BaseAct.1
            @Override // cn.nubia.componentsdk.pay.MessageHandler.OnProcessCompleteListener
            public void a(int i2, int i3) {
                BaseAct baseAct = BaseAct.this;
                if (i2 == baseAct.f5900d) {
                    baseAct.f(i2);
                }
            }
        });
        this.f5902f.a(new MessageHandler.OnBuildConnectListener() { // from class: cn.nubia.componentsdk.pay.BaseAct.2
            @Override // cn.nubia.componentsdk.pay.MessageHandler.OnBuildConnectListener
            public void a(int i2, int i3) {
                if (i3 != 0) {
                    BaseAct baseAct = BaseAct.this;
                    if (i2 == baseAct.f5900d) {
                        baseAct.b(baseAct.f5901e, 120, null);
                    }
                }
            }
        });
        this.f5902f.d(new MessageHandler.OnProcessErrorListener() { // from class: cn.nubia.componentsdk.pay.BaseAct.3
            @Override // cn.nubia.componentsdk.pay.MessageHandler.OnProcessErrorListener
            public void a(int i2, int i3, Exception exc) {
                BaseAct baseAct = BaseAct.this;
                if (i2 != baseAct.f5900d || i3 == 0) {
                    return;
                }
                baseAct.b(baseAct.f5901e, i3, null);
            }
        });
        this.f5902f.b(new MessageHandler.OnHTTPStatusListener() { // from class: cn.nubia.componentsdk.pay.BaseAct.4
            @Override // cn.nubia.componentsdk.pay.MessageHandler.OnHTTPStatusListener
            public void a(int i2, int i3) {
                BaseAct baseAct = BaseAct.this;
                if (i2 == baseAct.f5900d) {
                    if (i3 < 200 || i3 > 400) {
                        baseAct.b(baseAct.f5901e, 120, null);
                    }
                }
            }
        });
    }

    protected int a() {
        e();
        return d();
    }

    protected void b(HttpResponse httpResponse, int i2, Object obj) {
        if (httpResponse != null) {
            PayLog.a(this.f5906j, "HttpResponse: " + httpResponse.b());
        }
        String str = "";
        if (httpResponse != null) {
            JSONObject f2 = httpResponse.f();
            if (f2 != null) {
                str = f2.optString("Result", "");
            } else if (i2 == 124) {
                i2 = 124;
            }
        }
        CallbackListener callbackListener = this.f5898b;
        if (callbackListener == null || callbackListener.b()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.f5898b.a(i2, obj);
        } else {
            this.f5898b.a(Integer.parseInt(str), obj);
        }
        this.f5898b.c(str);
    }

    protected void c() {
        try {
            CNetHttpTransfer.b().c(this.f5900d, this.f5902f);
            this.f5899c.remove(this.f5900d);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected int d() {
        BufferData bufferData = new BufferData();
        int d2 = CNetHttpTransfer.b().d(Urls.b(), g(), bufferData, null, this.f5902f, this.f5897a);
        this.f5900d = d2;
        this.f5899c.put(d2, bufferData);
        return this.f5900d;
    }

    protected abstract void f(int i2);

    protected byte[] g() {
        return new HttpRequest(this.f5903g, this.f5904h, this.f5897a, this.f5905i).c(h());
    }

    protected abstract HashMap h();
}
