package cn.nubia.nbgame.sdk.entities;

import cn.nubia.gameassist.view.NubiaTextClock;
import java.io.Serializable;

/* loaded from: classes.dex */
public class FcmInfo implements Serializable {
    public static final String BEBORE_TYPE = "BEBORE_TYPE";
    public static final String CENTER_TYPE = "CENTER_TYPE";
    public static boolean isFcmStatus = false;
    public static boolean isIdCard;
    public long currentTime;
    public long endTime;
    public String isHoliday;
    public String isLimitedPeroid;
    public boolean isLoginType;
    public long startTime;
    public String type;

    public String toString() {
        return "FcmInfo{type='" + this.type + NubiaTextClock.QUOTE + ", isHoliday=" + this.isHoliday + ", isLimitedPeroid=" + this.isLimitedPeroid + ", currentTime=" + this.currentTime + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", isLoginType=" + this.isLoginType + '}';
    }
}
