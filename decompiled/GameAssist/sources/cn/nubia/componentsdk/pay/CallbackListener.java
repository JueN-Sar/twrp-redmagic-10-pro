package cn.nubia.componentsdk.pay;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class CallbackListener<T> implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean destroy = false;
    private String mMessage;

    public abstract void a(int i2, Object obj);

    public boolean b() {
        return this.destroy;
    }

    public void c(String str) {
        this.mMessage = str;
    }
}
