package cn.nubia.gamelauncher.atmosphere;

import java.util.Date;

/* loaded from: classes.dex */
public class LiveAtmosphereBean {
    Date mDate;
    String mPackageName;
    String mPath;
    String mTitle;

    public LiveAtmosphereBean(String str, String str2, String str3, Date date) {
        this.mPath = str;
        this.mTitle = str2;
        this.mPackageName = str3;
        this.mDate = date;
    }

    public Date getDate() {
        return this.mDate;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getUrl() {
        return this.mPath;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String toString() {
        return "LiveAtmosphereBean{mPath='" + this.mPath + "', mPackageName='" + this.mPackageName + "', mDate=" + this.mDate + '}';
    }
}
