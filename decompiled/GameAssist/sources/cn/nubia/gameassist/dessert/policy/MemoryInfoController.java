package cn.nubia.gameassist.dessert.policy;

import android.app.ActivityManager;
import android.content.Context;

/* loaded from: classes.dex */
public class MemoryInfoController {

    /* renamed from: a, reason: collision with root package name */
    private ActivityManager f6296a;

    public MemoryInfoController(Context context) {
        this.f6296a = (ActivityManager) context.getSystemService("activity");
    }

    private String a(long j2) {
        return j2 < 1024000 ? String.format("%.0fKB", Float.valueOf(j2 / 1024)) : j2 < 1048576000 ? String.format("%.0fMB", Float.valueOf(j2 / 1048576)) : String.format("%.2fGB", Float.valueOf(j2 / 1073741824));
    }

    public String b(long j2) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        this.f6296a.getMemoryInfo(memoryInfo);
        return a(Math.abs(memoryInfo.availMem - j2));
    }

    public void c(ActivityManager.MemoryInfo memoryInfo) {
        this.f6296a.getMemoryInfo(memoryInfo);
    }
}
