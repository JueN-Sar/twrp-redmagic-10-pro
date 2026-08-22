package cn.nubia.gameassist.dessert.policy.performancemonitor.model;

import android.graphics.drawable.Drawable;
import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes.dex */
public class GameDurationInfo {
    public static final int SUGGEST_EXCESS = 2;
    public static final int SUGGEST_FATIGUE = 1;
    public static final int SUGGEST_HEALTH = 0;
    public String label;
    public long mGameMaxTime;
    public String mGameTimeSpan;
    public long mGameTotalTime;
    public Drawable mMaxTimeAppIcon;
    public String mMaxTimePkgName;
    public int mSuggestType = 0;

    public String toString() {
        return "GameDurationInfo{mGameTimeSpan='" + this.mGameTimeSpan + NubiaTextClock.QUOTE + ", mMaxTimePkgName='" + this.mMaxTimePkgName + NubiaTextClock.QUOTE + ", label='" + this.label + NubiaTextClock.QUOTE + ", mMaxTimeAppIcon=" + this.mMaxTimeAppIcon + ", mGameTotalTime=" + this.mGameTotalTime + ", mGameMaxTime=" + this.mGameMaxTime + ", mSuggestType=" + this.mSuggestType + '}';
    }
}
