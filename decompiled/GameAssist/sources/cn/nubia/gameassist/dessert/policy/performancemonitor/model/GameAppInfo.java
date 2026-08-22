package cn.nubia.gameassist.dessert.policy.performancemonitor.model;

import android.graphics.drawable.Drawable;
import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes.dex */
public class GameAppInfo {
    public Drawable icon;
    public String label;
    public long totalTimeInForeground;

    public String toString() {
        return "GameAppInfo{icon=" + this.icon + ", label='" + this.label + NubiaTextClock.QUOTE + ", totalTimeInForeground=" + this.totalTimeInForeground + '}';
    }
}
