package cn.nubia.gamecenter.settings.recordsdb;

/* loaded from: classes.dex */
public class HighLightsDb {
    private String appName;
    private int isPreview;
    private String packageName;
    private String path;

    public HighLightsDb(String str, int i) {
        this.path = str;
        this.isPreview = i;
    }

    public HighLightsDb(String str, int i, String str2, String str3) {
        this.path = str;
        this.isPreview = i;
        this.packageName = str2;
        this.appName = str3;
    }

    public String getAppName() {
        return this.appName;
    }

    public int getIsPreview() {
        return this.isPreview;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPath() {
        return this.path;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setIsPreview(int i) {
        this.isPreview = i;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setPath(String str) {
        this.path = str;
    }
}
