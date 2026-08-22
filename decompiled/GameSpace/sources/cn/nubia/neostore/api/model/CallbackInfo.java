package cn.nubia.neostore.api.model;

/* loaded from: classes.dex */
public class CallbackInfo {
    private Object data;
    private String tag;

    public CallbackInfo() {
    }

    public CallbackInfo(Object obj, String str) {
        this.data = obj;
        this.tag = str;
    }

    public Object getData() {
        return this.data;
    }

    public String getTag() {
        return this.tag;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setTag(String str) {
        this.tag = str;
    }
}
