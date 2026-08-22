package cn.nubia.componentcenter.service;

import android.content.Context;

/* loaded from: classes.dex */
public interface GameReminderComService {
    boolean isGameReminderViewShow(Context context);

    void removeView(Context context);

    void setGameReminder(Context context, String str, String str2, long j2, int i2);

    void showView(Context context);
}
