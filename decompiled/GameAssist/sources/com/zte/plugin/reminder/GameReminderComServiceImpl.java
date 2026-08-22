package com.zte.plugin.reminder;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import cn.nubia.componentcenter.service.GameReminderComService;

/* loaded from: classes2.dex */
public class GameReminderComServiceImpl implements GameReminderComService {
    private GameReminderWindowManager mGameReminderWindowManager;

    protected GameReminderWindowManager getGameReminderWindowManager(Context context) {
        if (this.mGameReminderWindowManager == null) {
            this.mGameReminderWindowManager = GameReminderWindowManager.G(context);
        }
        return this.mGameReminderWindowManager;
    }

    @Override // cn.nubia.componentcenter.service.GameReminderComService
    @VisibleForTesting
    public boolean isGameReminderViewShow(Context context) {
        return getGameReminderWindowManager(context).isGameReminderViewShow();
    }

    @Override // cn.nubia.componentcenter.service.GameReminderComService
    @VisibleForTesting
    public void removeView(Context context) {
        getGameReminderWindowManager(context).removeView();
    }

    @Override // cn.nubia.componentcenter.service.GameReminderComService
    public void setGameReminder(Context context, String str, String str2, long j2, int i2) {
        getGameReminderWindowManager(context).P(str, str2, j2, i2, null);
    }

    @Override // cn.nubia.componentcenter.service.GameReminderComService
    public void showView(Context context) {
        getGameReminderWindowManager(context).R();
    }
}
