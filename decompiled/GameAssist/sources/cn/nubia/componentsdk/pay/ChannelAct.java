package cn.nubia.componentsdk.pay;

import android.util.SparseArray;
import cn.nubia.componentsdk.until.PayLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChannelAct extends BaseAct<List<PayChannel>> {

    /* renamed from: k, reason: collision with root package name */
    private List f5925k;

    /* renamed from: l, reason: collision with root package name */
    private HashMap f5926l;

    /* renamed from: m, reason: collision with root package name */
    private String f5927m;

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected void f(int i2) {
        int i3;
        SparseArray sparseArray = this.f5899c;
        BufferData bufferData = sparseArray != null ? (BufferData) sparseArray.get(i2) : null;
        c();
        if (bufferData == null || bufferData.a() == null || bufferData.a().length <= 0) {
            PayLog.a(this.f5927m, "HttpResponse bufferData is empty！");
            b(this.f5901e, 124, null);
            return;
        }
        this.f5901e = new HttpResponse(bufferData.a());
        PayLog.a(this.f5927m, "mResponse.getErrorCode() =" + this.f5901e.c());
        if (!this.f5901e.i()) {
            int c2 = this.f5901e.c();
            if (c2 == 0 && (i3 = i()) != 0) {
                c2 = i3;
            }
            b(this.f5901e, c2, this.f5925k);
            return;
        }
        PayLog.a(this.f5927m, "mResponse.isInvalidFlag() = " + this.f5901e.i());
        b(this.f5901e, 124, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0101  */
    @Override // cn.nubia.componentsdk.pay.BaseAct
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.HashMap h() {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.componentsdk.pay.ChannelAct.h():java.util.HashMap");
    }

    protected int i() {
        int i2;
        String g2 = this.f5901e.g("Result");
        PayLog.a(this.f5927m, "server return session:" + this.f5901e.g("session_id"));
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
        JSONArray d2 = this.f5901e.d("data");
        try {
            int length = d2.length();
            if (length > 0) {
                this.f5925k = new ArrayList(length);
                for (int i3 = 0; i3 < length; i3++) {
                    JSONObject jSONObject = (JSONObject) d2.get(i3);
                    PayChannel payChannel = new PayChannel();
                    payChannel.l(jSONObject.getString("pay_channel_desc"));
                    payChannel.n(jSONObject.getString("pay_channel_name"));
                    payChannel.k(jSONObject.getString("logo_url"));
                    payChannel.o(jSONObject.getString("pay_channel_tag"));
                    payChannel.m(jSONObject.getInt("pay_channel_id"));
                    payChannel.q(this.f5901e.g("session_id"));
                    payChannel.g(jSONObject.getString("activity_tip"));
                    payChannel.i(jSONObject.getBoolean("is_default"));
                    PayLog.a(this.f5927m, "Save PayChannel session = " + payChannel.f());
                    this.f5925k.add(payChannel);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return 0;
    }
}
