package cn.nubia.componentsdk.pay;

import java.util.HashMap;

/* loaded from: classes.dex */
public class PatchOrderAct extends BaseAct<String> {
    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected int d() {
        BufferData bufferData = new BufferData();
        int d2 = CNetHttpTransfer.b().d(Urls.a(), g(), bufferData, null, this.f5902f, this.f5897a);
        this.f5900d = d2;
        this.f5899c.put(d2, bufferData);
        return this.f5900d;
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected void f(int i2) {
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected byte[] g() {
        return super.g();
    }

    @Override // cn.nubia.componentsdk.pay.BaseAct
    protected HashMap h() {
        return new HashMap();
    }
}
