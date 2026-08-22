package cn.nubia.componentsdk.pay;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.nubia.componentsdk.pay.BaseActivity;
import cn.nubia.componentsdk.until.PayLog;
import cn.nubia.multisubscreen.data.TransferData;
import com.alipay.sdk.app.PayTask;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PayActivity extends BaseActivity implements View.OnClickListener {
    public static PayActivity w;
    public static Handler x = new Handler() { // from class: cn.nubia.componentsdk.pay.PayActivity.9
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (PayActivity.w != null && message.what == 1) {
                String b2 = new PayResult((String) message.obj).b();
                if (TextUtils.equals(b2, "9000")) {
                    PayActivity.w.finish();
                    SendPayResult.a(PayActivity.w.f5988h, 0, "支付成功", PayActivity.w.getApplicationContext());
                    return;
                }
                if (TextUtils.equals(b2, "8000")) {
                    PayActivity.w.finish();
                    SendPayResult.a(PayActivity.w.f5988h, 106, "支付结果确认中", PayActivity.w.getApplicationContext());
                } else if (TextUtils.equals(b2, "4000")) {
                    Toast.makeText(PayActivity.w, "支付失败", 0).show();
                } else if (TextUtils.equals(b2, "6002")) {
                    Toast.makeText(PayActivity.w, "网络不给力，请查看网络设置", 0).show();
                } else if (TextUtils.equals(b2, "6001")) {
                    Toast.makeText(PayActivity.w, "您取消了本次支付", 0).show();
                }
            }
        }
    };

    /* renamed from: h, reason: collision with root package name */
    private String f5988h;

    /* renamed from: i, reason: collision with root package name */
    private HashMap f5989i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f5990j;

    /* renamed from: k, reason: collision with root package name */
    private TextView f5991k;

    /* renamed from: l, reason: collision with root package name */
    private IWXAPI f5992l;

    /* renamed from: m, reason: collision with root package name */
    private Button f5993m;

    /* renamed from: n, reason: collision with root package name */
    private TextView f5994n;

    /* renamed from: o, reason: collision with root package name */
    private RelativeLayout f5995o;

    /* renamed from: p, reason: collision with root package name */
    private ArrayList f5996p;

    /* renamed from: q, reason: collision with root package name */
    private NoScrollListView f5997q;

    /* renamed from: r, reason: collision with root package name */
    private PayChannelAdapter f5998r;

    /* renamed from: s, reason: collision with root package name */
    private int f5999s;
    private String t;
    private DialogFragment u = null;
    private BroadcastReceiver v = new BroadcastReceiver() { // from class: cn.nubia.componentsdk.pay.PayActivity.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("pay_result_component".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("ResultCode");
                if (TextUtils.equals(stringExtra, "0")) {
                    PayActivity.this.b("支付结果确认中");
                    PayActivity.this.f5911c = new BaseActivity.Timer(2000L, 1000L);
                    PayActivity.this.f5911c.start();
                    return;
                }
                if (TextUtils.equals(stringExtra, "-1")) {
                    Toast.makeText(PayActivity.this, "支付失败", 0).show();
                } else if (TextUtils.equals(stringExtra, "-2")) {
                    Toast.makeText(PayActivity.this, "您取消了本次支付", 0).show();
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void k(final String str) {
        new Thread(new Runnable() { // from class: cn.nubia.componentsdk.pay.PayActivity.4
            @Override // java.lang.Runnable
            public void run() {
                String pay = new PayTask(PayActivity.this).pay(str, true);
                Message message = new Message();
                message.what = 1;
                message.obj = pay;
                PayActivity.x.sendMessage(message);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        DialogFragment dialogFragment = this.u;
        if (dialogFragment != null) {
            dialogFragment.dismissAllowingStateLoss();
        }
    }

    private void m() {
        this.f5994n.setText("支付中心");
        this.f5990j.setText((String) this.f5989i.get("product_name"));
        this.f5991k.setText((String) this.f5989i.get("amount"));
        this.f5995o.setOnClickListener(this);
        this.f5993m.setOnClickListener(this);
    }

    private void n() {
        new PayAct(getApplicationContext()).i(this.f5989i, "AliPhonePay", this.t, new CallbackListener<String>() { // from class: cn.nubia.componentsdk.pay.PayActivity.3
            private static final long serialVersionUID = 1;

            @Override // cn.nubia.componentsdk.pay.CallbackListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void a(int i2, String str) {
                PayActivity.this.l();
                if (i2 == 0) {
                    if (TextUtils.isEmpty(str)) {
                        XToast.d("支付订单为空", 0, PayActivity.this.getApplicationContext());
                        PayLog.b("PayActivity", "服务端返回的支付宝支付凭证为空");
                    } else {
                        PayActivity.this.k(str);
                    }
                } else if (i2 == 110 || i2 == 122) {
                    XToast.d("网络不给力，请查看网络设置", 0, PayActivity.this.getApplicationContext());
                } else {
                    XToast.d("支付失败", 0, PayActivity.this.getApplicationContext());
                }
                PayLog.a("PayActivity", "do ali payAct responseCode : " + i2);
            }
        });
    }

    private void o() {
        if (!NetUtil.a(getApplicationContext())) {
            XToast.d("网络不给力，请查看网络设置", 0, getApplicationContext());
            return;
        }
        w();
        PayChannel payChannel = (PayChannel) this.f5996p.get(this.f5999s);
        PayLog.a("PayActivity", "mSelectPosition = " + ((PayChannel) this.f5996p.get(this.f5999s)).f());
        if ("AliPhonePay".equals(payChannel.d())) {
            n();
        } else if ("WeiXinAppPay".equals(payChannel.d())) {
            q();
        } else if ("UnionPay".equals(payChannel.d())) {
            p();
        }
    }

    private void p() {
        new PayAct(getApplicationContext()).i(this.f5989i, "UnionPay", this.t, new CallbackListener<String>() { // from class: cn.nubia.componentsdk.pay.PayActivity.8
            private static final long serialVersionUID = 1;

            @Override // cn.nubia.componentsdk.pay.CallbackListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void a(int i2, String str) {
                PayActivity.this.l();
                if (i2 == 0) {
                    if (TextUtils.isEmpty(str)) {
                        XToast.d("支付订单为空", 0, PayActivity.this.getApplicationContext());
                        PayLog.b("PayActivity", "服务端返回的银联支付凭证为空");
                    } else {
                        PayActivity.this.s(str);
                    }
                } else if (i2 == 110 || i2 == 122) {
                    XToast.d("网络不给力，请查看网络设置", 0, PayActivity.this.getApplicationContext());
                } else {
                    XToast.d("支付失败", 0, PayActivity.this.getApplicationContext());
                }
                PayLog.a("PayActivity", "do union payAct responseCode : " + i2);
            }
        });
    }

    private void q() {
        if (this.f5992l.isWXAppInstalled()) {
            new PayAct(getApplicationContext()).j(this.f5989i, "WeiXinAppPay", this.t, new CallbackListener<JSONObject>() { // from class: cn.nubia.componentsdk.pay.PayActivity.5
                private static final long serialVersionUID = 1;

                @Override // cn.nubia.componentsdk.pay.CallbackListener
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void a(int i2, JSONObject jSONObject) {
                    if (i2 == 0) {
                        PayActivity.this.t(jSONObject);
                    } else if (i2 == 110 || i2 == 122) {
                        XToast.d("网络错误", 0, PayActivity.w);
                    } else {
                        XToast.d("支付失败", 0, PayActivity.w);
                    }
                    PayLog.a("PayActivity", "do WXPay payAct responseCode : " + i2);
                    PayActivity.this.l();
                }
            });
        } else {
            XToast.d("未安装微信客户端", 0, this);
            l();
        }
    }

    private void r() {
        int c2 = Util.c(this, "title", VirtualHandleWrapper.KEY_ID);
        int c3 = Util.c(this, "re_back", VirtualHandleWrapper.KEY_ID);
        int c4 = Util.c(this, "coupen_amount", VirtualHandleWrapper.KEY_ID);
        int c5 = Util.c(this, "total_num", VirtualHandleWrapper.KEY_ID);
        int c6 = Util.c(this, "pay_channel", VirtualHandleWrapper.KEY_ID);
        int c7 = Util.c(this, "btn_comfirm", VirtualHandleWrapper.KEY_ID);
        this.f5994n = (TextView) findViewById(c2);
        this.f5995o = (RelativeLayout) findViewById(c3);
        this.f5990j = (TextView) findViewById(c4);
        this.f5991k = (TextView) findViewById(c5);
        this.f5997q = (NoScrollListView) findViewById(c6);
        PayChannelAdapter payChannelAdapter = new PayChannelAdapter(this, this.f5997q);
        this.f5998r = payChannelAdapter;
        payChannelAdapter.c(this.f5996p);
        this.f5998r.f(this.f5999s);
        this.f5997q.setAdapter((ListAdapter) this.f5998r);
        this.f5997q.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.nubia.componentsdk.pay.PayActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                PayActivity.this.f5999s = i2;
                PayActivity.this.f5998r.f(i2);
                PayActivity.this.f5998r.notifyDataSetChanged();
            }
        });
        this.f5993m = (Button) findViewById(c7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(String str) {
        UPPayAssistEx.startPayByJAR(this, com.unionpay.uppay.PayActivity.class, (String) null, (String) null, str, "00");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(JSONObject jSONObject) {
        try {
            PayReq payReq = new PayReq();
            payReq.appId = Constant.f5934f;
            PayLog.a("PayActivity", "PayReq appId:" + payReq.appId);
            payReq.partnerId = jSONObject.getString("partnerid");
            PayLog.a("PayActivity", "PayReq partnerId:" + payReq.partnerId);
            payReq.prepayId = jSONObject.getString("prepayid");
            PayLog.a("PayActivity", "PayReq prepayId:" + payReq.prepayId);
            payReq.packageValue = jSONObject.getString("package");
            PayLog.a("PayActivity", "PayReq packageValue:" + payReq.packageValue);
            payReq.nonceStr = jSONObject.getString("noncestr");
            PayLog.a("PayActivity", "PayReq nonceStr:" + payReq.nonceStr);
            payReq.timeStamp = jSONObject.getString("timestamp");
            PayLog.a("PayActivity", "PayReq timeStamp:" + payReq.timeStamp);
            payReq.sign = jSONObject.getString("sign");
            PayLog.a("PayActivity", "PayReq sign:" + payReq.sign);
            this.f5992l.sendReq(payReq);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void u() {
        int c2 = Util.c(this, "activity_pay", "layout");
        if (getIntent() != null) {
            this.f5989i = (HashMap) getIntent().getSerializableExtra("pay_info");
            this.f5996p = getIntent().getParcelableArrayListExtra("payChannels");
            this.f5988h = getIntent().getStringExtra("package_name");
            ArrayList arrayList = this.f5996p;
            if (arrayList != null && arrayList.size() > 0) {
                this.t = ((PayChannel) this.f5996p.get(0)).f();
                PayLog.a("PayActivity", "mPayChannels = " + this.f5996p.size());
            }
            PayLog.a("PayActivity", "PayActivity save sessionId = " + this.t);
        }
        setContentView(c2);
        r();
        m();
    }

    private void v() {
        int c2 = Util.c(this, "content_dialog", "layout");
        int c3 = Util.c(this, "content", VirtualHandleWrapper.KEY_ID);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(c2, (ViewGroup) null);
        ((TextView) inflate.findViewById(c3)).setText("确认放弃付款吗？");
        new DialogPlus.Builder(this).setContentHolder(new ViewHolder(inflate)).setCancelable(true).setOnClickListener(new OnClickListener() { // from class: cn.nubia.componentsdk.pay.PayActivity.2
        }).setFooter(Util.c(getApplicationContext(), "footer", "layout")).create().show();
    }

    private void w() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        BasicDialogFragment basicDialogFragment = new BasicDialogFragment();
        this.u = basicDialogFragment;
        basicDialogFragment.setCancelable(false);
        beginTransaction.add(this.u, getClass().getSimpleName());
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // cn.nubia.componentsdk.pay.BaseActivity
    public void c() {
        x();
        super.c();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        PayLog.a("PayActivity", "Pay return ");
        if (intent == null) {
            return;
        }
        String string = intent.getExtras().getString("pay_result");
        PayLog.a("PayActivity", "Pay return data = " + string);
        if (string.equalsIgnoreCase(TransferData.MSG_SUCCESS)) {
            finish();
            SendPayResult.a(this.f5988h, 0, "支付成功", getApplicationContext());
            return;
        }
        if (string.equalsIgnoreCase("fail")) {
            XToast.d("支付失败", 0, getApplicationContext());
            return;
        }
        if (string.equalsIgnoreCase("cancel")) {
            XToast.d("您取消了本次支付", 0, getApplicationContext());
            return;
        }
        if (string.equalsIgnoreCase("wap_failed")) {
            XToast.d("支付失败", 0, getApplicationContext());
            return;
        }
        if (string.equalsIgnoreCase("wap_cancel")) {
            XToast.d("您取消了本次支付", 0, getApplicationContext());
        } else if (string.equalsIgnoreCase("neterror")) {
            XToast.d("网络不给力，请查看网络设置", 0, getApplicationContext());
        } else {
            XToast.d("支付失败", 0, getApplicationContext());
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        PayLog.c("PayActivity", "onBackPressed====");
        v();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PayLog.a("PayActivity", "View = " + view);
        int c2 = Util.c(this, "btn_comfirm", VirtualHandleWrapper.KEY_ID);
        int c3 = Util.c(this, "re_back", VirtualHandleWrapper.KEY_ID);
        if (view.getId() != c2) {
            if (view.getId() == c3) {
                onBackPressed();
            }
        } else {
            if (!CommonUtils.b()) {
                o();
                return;
            }
            PayLog.a("PayActivity", "isFastDoubleClick = " + CommonUtils.b());
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        PayLog.a("PayActivity", "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        int i2 = configuration.orientation;
        if (i2 == 2) {
            u();
        } else if (i2 == 1) {
            u();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        w = this;
        try {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this, Constant.f5934f);
            this.f5992l = createWXAPI;
            createWXAPI.registerApp(Constant.f5934f);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        PayLog.a("PayActivity", "PayActivity  onCreate run !");
        if (getIntent() != null) {
            this.f5989i = (HashMap) getIntent().getSerializableExtra("pay_info");
            this.f5996p = getIntent().getParcelableArrayListExtra("payChannels");
            this.f5988h = getIntent().getStringExtra("package_name");
            ArrayList arrayList = this.f5996p;
            if (arrayList != null && arrayList.size() > 0) {
                this.t = ((PayChannel) this.f5996p.get(0)).f();
                PayLog.a("PayActivity", "mPayChannels = " + this.f5996p.size());
            }
            PayLog.a("PayActivity", "PayActivity save sessionId = " + this.t);
        }
        setContentView(Util.c(this, "activity_pay", "layout"));
        if (bundle != null) {
            this.f5999s = bundle.getInt("selectPosition", 0);
        } else {
            this.f5999s = 0;
        }
        IntentFilter intentFilter = new IntentFilter("pay_result_component");
        if (Build.VERSION.SDK_INT > 33) {
            registerReceiver(this.v, intentFilter, 2);
        } else {
            registerReceiver(this.v, intentFilter);
        }
        r();
        m();
        PayLog.a("PayActivity", "package from sdk:" + this.f5988h);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        PayLog.a("PayActivity", "onDestroy");
        unregisterReceiver(this.v);
        w = null;
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("selectPosition", this.f5999s);
    }

    public void x() {
        PayLog.a("PayActivity", "start verifyOrder()");
        new VerifyOrderAct(getApplicationContext()).i(((String) this.f5989i.get("app_id")) + "", ((String) this.f5989i.get("uid")) + "", ((String) this.f5989i.get("cp_order_id")) + "", this.t, new CallbackListener<String>() { // from class: cn.nubia.componentsdk.pay.PayActivity.7
            private static final long serialVersionUID = 1;

            @Override // cn.nubia.componentsdk.pay.CallbackListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void a(int i2, String str) {
                PayActivity.this.a();
                PayLog.a("PayActivity", "verifyOrder(): responseCode = " + i2);
                if (i2 == 0) {
                    if (str.equals("1")) {
                        SendPayResult.a(PayActivity.this.f5988h, 0, "支付成功", PayActivity.w);
                        PayActivity.this.finish();
                        return;
                    }
                    return;
                }
                if (i2 == 110 || i2 == 122) {
                    XToast.d("网络不给力，请查看网络设置", 0, PayActivity.w);
                    PayActivity.this.finish();
                } else {
                    XToast.d("支付结果查询失败", 0, PayActivity.w);
                    PayActivity.this.finish();
                }
            }
        });
    }
}
