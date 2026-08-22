package cn.nubia.plugin.superresolution;

import cn.nubia.gameassist.view.NubiaTextClock;
import java.io.Serializable;

/* loaded from: classes.dex */
public class SuperResolutionTypeItemData implements Serializable {
    private String frameRateItem;
    private String imageQualityItem;
    private String packageName;

    public SuperResolutionTypeItemData(String str, String str2, String str3) {
        str2 = str2 == null ? "origin" : str2;
        str3 = str3 == null ? "frameRate_origin" : str3;
        this.packageName = str;
        this.imageQualityItem = str2;
        this.frameRateItem = str3;
    }

    public String a() {
        return this.frameRateItem;
    }

    public String b() {
        return this.imageQualityItem;
    }

    public String c() {
        return this.packageName;
    }

    public void d(String str) {
        this.frameRateItem = str;
    }

    public void e(String str) {
        this.imageQualityItem = str;
    }

    public String toString() {
        return "SuperResolutionTypeItemData{packageName='" + this.packageName + NubiaTextClock.QUOTE + ", imageQualityItem='" + this.imageQualityItem + NubiaTextClock.QUOTE + ", frameRateItem='" + this.frameRateItem + NubiaTextClock.QUOTE + '}';
    }
}
