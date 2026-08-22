package com.zte.gameassist.aiagent;

import android.content.Context;
import com.zte.gameassist.aiagent.bean.InMsg;

/* loaded from: classes2.dex */
public class BaseCommand {
    protected Context mContext;
    protected IGameAssistClientCallback remoteClient;
    protected String topGamePackage;

    protected void confirmIfOpenSettings(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        GameAgentUtil.a(this.mContext, iGameAssistClientCallback, inMsg, i2);
    }

    protected void finishOpenSettings(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        GameAgentUtil.c(this.mContext, iGameAssistClientCallback, inMsg, i2);
    }

    protected void finishTurnOff(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        GameAgentUtil.d(this.mContext, iGameAssistClientCallback, inMsg, i2);
    }

    protected void finishTurnOn(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        GameAgentUtil.e(this.mContext, iGameAssistClientCallback, inMsg, i2);
    }

    protected void replyFinishMessage(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2, boolean z) {
        replyFinishMessage(iGameAssistClientCallback, inMsg, this.mContext.getString(i2), 0, z);
    }

    protected void replyHandledMessage(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        GameAgentUtil.k(this.mContext, iGameAssistClientCallback, inMsg);
    }

    protected void replyToBeSupportedMessage(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        GameAgentUtil.m(this.mContext, iGameAssistClientCallback, inMsg);
    }

    protected void replyFinishMessage(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, String str, int i2, boolean z) {
        GameAgentUtil.h(this.mContext, iGameAssistClientCallback, inMsg, str, 0, z);
    }
}
