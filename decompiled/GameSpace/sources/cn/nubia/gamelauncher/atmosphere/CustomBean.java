package cn.nubia.gamelauncher.atmosphere;

/* loaded from: classes.dex */
public class CustomBean {
    String type;
    String url;

    public CustomBean(String str, String str2) {
        this.url = str;
        this.type = str2;
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "CustomBean{type = " + this.type + ", url = " + this.url + '}';
    }
}
