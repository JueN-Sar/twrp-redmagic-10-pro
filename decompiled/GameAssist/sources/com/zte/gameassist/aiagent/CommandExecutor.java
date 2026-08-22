package com.zte.gameassist.aiagent;

import android.content.Context;
import com.zte.gameassist.aiagent.bean.InMsg;

/* loaded from: classes2.dex */
public interface CommandExecutor {
    void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg);

    void init(Context context);
}
